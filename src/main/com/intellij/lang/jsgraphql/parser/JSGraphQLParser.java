/**
 *  Copyright (c) 2015-present, Jim Kynde Meyer
 *  All rights reserved.
 *
 *  This source code is licensed under the MIT license found in the
 *  LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.parser;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.lang.jsgraphql.JSGraphQLKeywords;
import com.intellij.lang.jsgraphql.JSGraphQLTokenTypes;
import com.intellij.lang.jsgraphql.lexer.JSGraphQLLexer;
import com.intellij.lang.jsgraphql.lexer.JSGraphQLToken;
import com.intellij.lang.jsgraphql.psi.JSGraphQLElementType;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JSGraphQLParser implements PsiParser {

    private final boolean schema;

    public JSGraphQLParser() {
        this(false);
    }

    public JSGraphQLParser(boolean schema) {
        this.schema = schema;
    }

    private static class MarkerScope {

        PsiBuilder.Marker marker;
        JSGraphQLElementType tokenType;
        boolean field;

        public MarkerScope(PsiBuilder.Marker marker, JSGraphQLElementType tokenType, boolean field) {
            this.marker = marker;
            this.tokenType = tokenType;
            this.field = field;
        }

    }

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {

        if(!(builder instanceof PsiBuilderImpl)) {
            throw new IllegalArgumentException("Unable to get lexer from builder implementation " + builder);
        }

        final JSGraphQLLexer lexer = (JSGraphQLLexer)((PsiBuilderImpl) builder).getLexer();
        final List<JSGraphQLToken> tokens = lexer.getTokens();

        final Ref<Integer> tokenIndex = new Ref<>(0);
        builder.setWhitespaceSkippedCallback((type, start, end) -> tokenIndex.set(tokenIndex.get()+1));

        final PsiBuilder.Marker rootMarker = builder.mark();

        final List<PropertyScope> propertyScopes = getPropertyScopes(tokens);
        final Map<JSGraphQLToken, PropertyScope> tokenToPropertyScope = Maps.newLinkedHashMap();
        for (PropertyScope propertyScope : propertyScopes) {
            tokenToPropertyScope.put(propertyScope.propertyOrOperation, propertyScope);
            if(propertyScope.lbrace != null && propertyScope.rbrace != null) {
                tokenToPropertyScope.put(propertyScope.lbrace, propertyScope);
                tokenToPropertyScope.put(propertyScope.rbrace, propertyScope);
            }
        }

        final Stack<MarkerScope> scopes = new Stack<>();
        while(!builder.eof()) {

            JSGraphQLToken currentToken = tokens.get(tokenIndex.get());

            // ---- property scopes ----

            final PropertyScope propertyScope = tokenToPropertyScope.get(currentToken);
            if(propertyScope != null) {

                if(scopes.isEmpty()) {
                    if(currentToken.tokenType == JSGraphQLTokenTypes.RBRACE || currentToken.tokenType == JSGraphQLTokenTypes.RBRACKET || currentToken.tokenType == JSGraphQLTokenTypes.RPAREN) {
                        // closing scope without an open scope, so skip ahead to continue parsing
                        builder.advanceLexer();
                        tokenIndex.set(tokenIndex.get()+1);
                        continue;
                    }
                }

                if (currentToken.tokenType == JSGraphQLTokenTypes.PROPERTY || currentToken.tokenType == JSGraphQLTokenTypes.KEYWORD/* query etc.*/) {
                    if (propertyScope.lbrace != null) {
                        // Field property token with selection set is considered a scope
                        startScope(builder, scopes, currentToken, true);
                    }
                    if(currentToken.tokenType == JSGraphQLTokenTypes.PROPERTY) {
                        markCurrentToken(builder, tokenIndex, JSGraphQLElementType.PROPERTY_KIND);
                        continue;
                    }
                } else if (currentToken.tokenType == JSGraphQLTokenTypes.LBRACE) {
                    if (propertyScope.lbrace != null) {
                        startScope(builder, scopes, currentToken, false);
                    }
                } else if (currentToken.tokenType == JSGraphQLTokenTypes.RBRACE) {
                    if(JSGraphQLElementType.OBJECT_VALUE_KIND.equals(propertyScope.lbrace.sourceToken.getKind())) {
                        // close object value
                        endScope(builder, tokenIndex, scopes, true);
                        if(propertyScope.parentToClose != null) {
                            endScope(builder, tokenIndex, scopes, false);
                        }
                        continue;
                    } else {
                        // close selection set
                        endScope(builder, tokenIndex, scopes, true);
                        // '}' in selection sets also closes the parent field it belongs to without advancing:
                        endScope(builder, tokenIndex, scopes, false);
                        continue;
                    }
                } else if(currentToken.tokenType == JSGraphQLTokenTypes.LPAREN) {
                    if (propertyScope.lbrace != null) {
                        startScope(builder, scopes, currentToken, false);
                    }
                } else if(currentToken.tokenType == JSGraphQLTokenTypes.RPAREN) {
                    endScope(builder, tokenIndex, scopes, true);
                    continue;
                } else if(currentToken.tokenType == JSGraphQLTokenTypes.LBRACKET) {
                    if (propertyScope.lbrace != null) {
                        startScope(builder, scopes, currentToken, false);
                    }
                } else if(currentToken.tokenType == JSGraphQLTokenTypes.RBRACKET) {
                    endScope(builder, tokenIndex, scopes, true);
                    if(propertyScope.parentToClose != null) {
                        endScope(builder, tokenIndex, scopes, false);
                    }
                    continue;
                } else if(currentToken.tokenType == JSGraphQLTokenTypes.ATTRIBUTE) {
                    // atribute with list/object value, so it's a scope for indentation, folding etc.
                    startScope(builder, scopes, currentToken, false);
                    markCurrentToken(builder, tokenIndex, JSGraphQLElementType.ATTRIBUTE_KIND);
                    continue;
                }
            } else if(currentToken.tokenType == JSGraphQLTokenTypes.PROPERTY) {
                markCurrentToken(builder, tokenIndex, JSGraphQLElementType.PROPERTY_KIND);
                continue;
            } else if(currentToken.tokenType == JSGraphQLTokenTypes.ATOM) {
                markCurrentToken(builder, tokenIndex, JSGraphQLElementType.ATOM_KIND);
                continue;
            } else if(currentToken.tokenType == JSGraphQLTokenTypes.DEF) {
                markCurrentToken(builder, tokenIndex, JSGraphQLElementType.DEFINITION_KIND);
                continue;
            } else if(currentToken.tokenType == JSGraphQLTokenTypes.ATTRIBUTE) {
                // attribute with literal value (not a scope)
                markCurrentToken(builder, tokenIndex, JSGraphQLElementType.ATTRIBUTE_KIND);
                continue;
            }

            // ---- template fragment ----

            if(currentToken.tokenType == JSGraphQLTokenTypes.TEMPLATE_FRAGMENT) {
                markCurrentToken(builder, tokenIndex, JSGraphQLElementType.TEMPLATE_FRAGMENT_KIND);
                continue;
            }

            // ---- next token ----

            builder.advanceLexer();
            tokenIndex.set(tokenIndex.get()+1);

        }

        // close up leftover scopes from missing '}' etc.
        while(!scopes.isEmpty()) {
            MarkerScope markerScope = scopes.pop();
            markerScope.marker.done(markerScope.tokenType);
        }

        rootMarker.done(root);

        return builder.getTreeBuilt();

    }

    private void markCurrentToken(@NotNull PsiBuilder builder, Ref<Integer> tokenIndex, String psiKind) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        tokenIndex.set(tokenIndex.get()+1);
        marker.done(JSGraphQLElementType.create(psiKind));
    }

    private void startScope(@NotNull PsiBuilder builder, Stack<MarkerScope> scopes, JSGraphQLToken currentToken, boolean field) {
        scopes.push(new MarkerScope(builder.mark(), JSGraphQLElementType.create(currentToken.sourceToken), field));
    }

    private void endScope(@NotNull PsiBuilder builder, Ref<Integer> tokenIndex, Stack<MarkerScope> scopes, boolean advance) {
        if(scopes.isEmpty()) {
            // unbalanced scope, e.g. missing opening '{'
            return;
        }
        MarkerScope endedScope = scopes.pop();
        if(advance) {
            builder.advanceLexer();
            tokenIndex.set(tokenIndex.get() + 1);
        }
        endedScope.marker.done(endedScope.tokenType);
    }

    private List<PropertyScope> getPropertyScopes(List<JSGraphQLToken> tokens) {

        List<JSGraphQLToken> astTokens = tokens.stream()
                .filter((token) -> token.tokenType != JSGraphQLTokenTypes.WHITESPACE && token.tokenType != JSGraphQLTokenTypes.COMMENT)
                .collect(Collectors.toList());

        // remove ${jsVar} placeholders from consideration
        astTokens = removeVariablePlaceholders(astTokens);

        final List<PropertyScope> ret = Lists.newArrayList();
        final Stack<PropertyScope> scopes = new Stack<>();

        boolean parseArguments = false;

        for (int i = 0; i < astTokens.size(); i++) {
            JSGraphQLToken token = astTokens.get(i);
            if(token.tokenType == JSGraphQLTokenTypes.KEYWORD) {
                final String text = token.sourceToken.getText();
                if(isPropertyScopeDefinition(text, i, astTokens, scopes)) {
                    PropertyScope propertyScope = new PropertyScope(token, getNextLBrace(astTokens, i, 2));
                    scopes.add(propertyScope); // optional name of operation so allow token before '{'
                    ret.add(propertyScope);
                }
            } else if(token.tokenType == JSGraphQLTokenTypes.PROPERTY) {
                PropertyScope propertyScope = new PropertyScope(token, getNextLBrace(astTokens, i, 1));
                if(propertyScope.lbrace != null) {
                    // only a scope if there's an '{' to signal a selection set
                    scopes.add(propertyScope);
                    ret.add(propertyScope);
                }
            } else if (token.tokenType == JSGraphQLTokenTypes.RBRACE) {
                if(!scopes.isEmpty()) {
                    if(parseArguments) {
                        scopes.pop().rbrace = token;
                    } else {
                        final String kind = token.sourceToken.getKind();
                        if (JSGraphQLElementType.SELECTION_SET_KIND.equals(kind) || JSGraphQLElementType.DOCUMENT_KIND.equals(kind) || isSchemaDefWithLBrace(kind)) {
                            PropertyScope propertyScope = scopes.pop();
                            if (propertyScope.lbrace == null) {
                                // closing the parent scope
                                if (!scopes.isEmpty()) {
                                    propertyScope = scopes.pop();
                                }
                            }
                            propertyScope.rbrace = token;
                        }
                    }
                }
            } else if(token.tokenType == JSGraphQLTokenTypes.LBRACE) {
                if(parseArguments) {
                    PropertyScope propertyScope = new PropertyScope(token, token);
                    scopes.add(propertyScope);
                    ret.add(propertyScope);
                } else {
                    // if top level scope, it's shorthand for a query
                    if(scopes.isEmpty()) {
                        PropertyScope propertyScope = new PropertyScope(token, token);
                        scopes.add(propertyScope);
                        ret.add(propertyScope);
                    }
                }
            } else if(token.tokenType == JSGraphQLTokenTypes.LPAREN) {
                if(!schema) {
                    parseArguments = true;
                    PropertyScope propertyScope = new PropertyScope(token, token);
                    scopes.add(propertyScope);
                    ret.add(propertyScope);
                }
            } else if(token.tokenType == JSGraphQLTokenTypes.RPAREN) {
                if(!schema) {
                    if (!scopes.isEmpty()) {
                        scopes.pop().rbrace = token;
                    }
                    parseArguments = false;
                }
            } else if (token.tokenType == JSGraphQLTokenTypes.LBRACKET) {
                if(!schema && parseArguments) {
                    PropertyScope propertyScope = new PropertyScope(token, token);
                    scopes.add(propertyScope);
                    ret.add(propertyScope);
                }
            } else if (token.tokenType == JSGraphQLTokenTypes.RBRACKET) {
                if (!schema && parseArguments && !scopes.isEmpty()) {
                    scopes.pop().rbrace = token;
                }
            } else if(token.tokenType == JSGraphQLTokenTypes.ATTRIBUTE) {
                if(!schema && parseArguments) {
                    PropertyScope propertyScope = new PropertyScope(token, null);
                    propertyScope.astTokenStartIndex = i;
                    ret.add(propertyScope);
                }
            }
        }

        // associate the attribute scopes with their corresponding list/object values
        final Set<PropertyScope> literalAttributeValues = Sets.newHashSet();
        for (int i = 0; i < ret.size(); i++) {
            final PropertyScope attributeNameScope = ret.get(i);
            if(attributeNameScope.closedBy == null && attributeNameScope.propertyOrOperation.tokenType == JSGraphQLTokenTypes.ATTRIBUTE) {
                if(i + 1 < ret.size()) {
                    final PropertyScope attributeValueScope = ret.get(i + 1);
                    if(!isValueForAttribute(astTokens, attributeNameScope, attributeValueScope)) {
                        literalAttributeValues.add(attributeNameScope);
                        continue;
                    }
                    final JSGraphQLToken valueToken = attributeValueScope.lbrace;
                    if(valueToken != null && (valueToken.tokenType == JSGraphQLTokenTypes.LBRACE || valueToken.tokenType == JSGraphQLTokenTypes.LBRACKET)) {
                        attributeValueScope.parentToClose = attributeNameScope;
                        attributeNameScope.closedBy = attributeValueScope;
                    } else {
                        literalAttributeValues.add(attributeNameScope);
                    }
                } else {
                    literalAttributeValues.add(attributeNameScope);
                }
            }
        }
        ret.removeAll(literalAttributeValues);

        return ret;
    }

    /**
     * Removes placeholders like ${jsVariable.foo.bar} from the astTokens since the curly braces shouldn't count as selection sets
     */
    private List<JSGraphQLToken> removeVariablePlaceholders(List<JSGraphQLToken> astTokens) {
        final List<JSGraphQLToken> tokens = Lists.newArrayListWithExpectedSize(astTokens.size());
        for (int i = 0; i < astTokens.size(); i++) {
            JSGraphQLToken currentToken = astTokens.get(i);
            if(currentToken.tokenType == JSGraphQLTokenTypes.VARIABLE) {
                // found a '$' so if the next token is a '{' then it's a placeholder
                if(i < astTokens.size() - 1 /* there is a next token */) {
                    final JSGraphQLToken nextToken = astTokens.get(i + 1);
                    if(nextToken.tokenType == JSGraphQLTokenTypes.LBRACE) {
                        // found a placeholder, so skip tokens until we on the other side of the placeholder
                        tokens.add(currentToken);
                        currentToken = null;
                        int openBraces = 1;
                        // start at the token that comes after '$' and '{'
                        for(int j = i + 2; j < astTokens.size(); j++) {
                            final JSGraphQLToken token = astTokens.get(j);
                            i = j;
                            if(token.tokenType == JSGraphQLTokenTypes.LBRACE) {
                                openBraces++;
                            } else if(token.tokenType == JSGraphQLTokenTypes.RBRACE) {
                                openBraces--;
                                if(openBraces == 0) {
                                    // found the end of the placeholder
                                    if(j < astTokens.size() - 1) {
                                        // there's a next token which is the one we want to add
                                        i++;
                                        currentToken = astTokens.get(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(currentToken != null) {
                tokens.add(currentToken);
            }
        }
        return tokens;
    }

    private boolean isValueForAttribute(List<JSGraphQLToken> astTokens, PropertyScope attributeNameScope, PropertyScope attributeValue) {
        for(int i = attributeNameScope.astTokenStartIndex + 1; i < astTokens.size(); i++) {
            final JSGraphQLToken token = astTokens.get(i);
            if(token == attributeValue.lbrace) {
                return true;
            }
            if(token.tokenType == JSGraphQLTokenTypes.PUNCTUATION) {
                // expecting colon before the attribute value
                continue;
            }
            break;
        }
        return false;
    }

    private boolean isPropertyScopeDefinition(String text, int tokenIndex, List<JSGraphQLToken> astTokens, Stack<PropertyScope> scopes) {
        switch(text) {
            case JSGraphQLKeywords.TYPE:
                // type after extend is not a new scope
                if(tokenIndex > 0) {
                    final JSGraphQLToken prevToken = astTokens.get(tokenIndex - 1);
                    if(JSGraphQLKeywords.EXTEND.equals(prevToken.sourceToken.getText())) {
                        return false;
                    }
                }
                return true;
            case JSGraphQLKeywords.INTERFACE:
            case JSGraphQLKeywords.ENUM:
            case JSGraphQLKeywords.INPUT:
            case JSGraphQLKeywords.EXTEND:
            case JSGraphQLKeywords.SCHEMA:
                return true;
        }
        if(!schema) {
            switch (text) {
                case JSGraphQLKeywords.QUERY:
                case JSGraphQLKeywords.MUTATION:
                case JSGraphQLKeywords.SUBSCRIPTION:
                    // not property scopes inside "schema {}"
                    return scopes.isEmpty();
                case JSGraphQLKeywords.FRAGMENT:
                    return true;
            }
            if (JSGraphQLKeywords.FRAGMENT_DOTS.equals(text)) {
                // possible anonymous fragment if no def of the fragment name right after
                if (tokenIndex + 1 < astTokens.size()) {
                    final JSGraphQLToken nextToken = astTokens.get(tokenIndex + 1);
                    return nextToken.tokenType != JSGraphQLTokenTypes.DEF;
                }
            }
        }
        return false;
    }

    private boolean isSchemaDefWithLBrace(String tokenKind) {
        switch (tokenKind) {
            case JSGraphQLElementType.SCHEMA_DEF_KIND:
            case JSGraphQLElementType.OBJECT_TYPE_DEF_KIND:
            case JSGraphQLElementType.INTERFACE_DEF_KIND:
            case JSGraphQLElementType.ENUM_DEF_KIND:
            case JSGraphQLElementType.INPUT_DEF_KIND:
            case JSGraphQLElementType.EXTEND_DEF_KIND:
                return true;
        }
        return false;
    }
    
    private JSGraphQLToken getNextLBrace(List<JSGraphQLToken> tokens, int currentIndex, int maxLookAhead) {
        int index = currentIndex + 1;
        while (maxLookAhead > 0) {
            if (index < tokens.size()) {
                JSGraphQLToken lbrace = tokens.get(index);
                final boolean isSelectionSet = JSGraphQLElementType.SELECTION_SET_KIND.equals(lbrace.sourceToken.getKind());
                if(lbrace.tokenType == JSGraphQLTokenTypes.LBRACE && (isSelectionSet || isSchemaDefWithLBrace(lbrace.sourceToken.getKind()))) {
                    return lbrace;
                } else if(lbrace.tokenType == JSGraphQLTokenTypes.LPAREN || lbrace.tokenType == JSGraphQLTokenTypes.META || lbrace.tokenType == JSGraphQLTokenTypes.DEF || isFragmentOnKeyword(lbrace)) {
                    // property args, meta (@directive), fragment definition, mutation
                    boolean foundRParen = false;
                    for(int i = index + 1; i < tokens.size(); i++) {
                        lbrace = tokens.get(i);
                        if(lbrace.tokenType == JSGraphQLTokenTypes.RPAREN) {
                            foundRParen = true;
                            index = i + 1;
                            break;
                        } else if(lbrace.tokenType == JSGraphQLTokenTypes.LBRACE) {
                            final String kind = lbrace.sourceToken.getKind();
                            if(JSGraphQLElementType.SELECTION_SET_KIND.equals(kind) || isSchemaDefWithLBrace(kind)) {
                                return lbrace;
                            }
                        }
                    }
                    if(foundRParen) {
                        // don't decrement maxLookAhead and try again after the newly found '(' and ')'
                        continue;
                    }
                }
            }
            index++;
            maxLookAhead--;
        }
        return null;
    }

    private boolean isFragmentOnKeyword(JSGraphQLToken token) {
        return token.tokenType == JSGraphQLTokenTypes.KEYWORD && JSGraphQLKeywords.FRAGMENT_ON.equals(token.sourceToken.getText());
    }

    private static class PropertyScope {

        JSGraphQLToken propertyOrOperation;
        JSGraphQLToken lbrace;
        JSGraphQLToken rbrace;

        PropertyScope closedBy;
        PropertyScope parentToClose;

        Integer astTokenStartIndex;

        public PropertyScope(JSGraphQLToken propertyOrOperation, JSGraphQLToken lbrace) {
            this.propertyOrOperation = propertyOrOperation;
            this.lbrace = lbrace;
        }
    }


}
