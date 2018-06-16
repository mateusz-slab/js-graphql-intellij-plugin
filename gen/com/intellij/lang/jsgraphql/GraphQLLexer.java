/* The following code was generated by JFlex 1.7.0-1 tweaked for IntelliJ platform */

/**
 * Copyright (c) 2018-present, Jim Kynde Meyer
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql;

import java.util.Stack;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.intellij.lang.jsgraphql.psi.GraphQLElementTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-1
 * from the specification file <tt>C:/Users/jimky/git/js-graphql-intellij-plugin/src/grammars/GraphQLLexer.flex</tt>
 */
public class GraphQLLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int QUO_STRING = 2;
  public static final int THREE_QUO_STRING = 4;
  public static final int VARIABLE_OR_TEMPLATE = 6;
  public static final int TEMPLATE = 8;
  public static final int SCHEMA_DEFINITION = 10;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5, 5
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [9, 6, 6]
   * Total runtime size is 1696 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>12]|((ch>>6)&0x3f)]<<6)|(ch&0x3f)];
  }

  /* The ZZ_CMAP_Z table has 272 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\16\100\1\200\u0100\300");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\271\2\1\3\4\2\100\4");

  /* The ZZ_CMAP_A table has 320 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\3\1\4\2\0\1\5\22\0\1\3\1\40\1\17\1\6\1\11\1\1\1\52\1\1\1\41\1\42\1"+
    "\1\1\15\1\2\1\12\1\13\1\1\12\10\1\43\2\1\1\44\2\1\1\45\4\7\1\14\25\7\1\46"+
    "\1\16\1\47\1\1\1\7\1\1\1\27\1\34\1\35\1\60\1\22\1\53\1\54\1\55\1\30\2\7\1"+
    "\56\1\25\1\32\1\31\1\36\1\20\1\23\1\33\1\26\1\21\1\61\1\7\1\57\1\24\1\7\1"+
    "\50\1\51\1\37\201\1\1\2\100\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\6\0\1\1\3\2\1\3\1\4\1\5\1\6\2\1"+
    "\1\7\10\4\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\2\4\1\24"+
    "\2\25\1\26\1\24\2\25\1\27\1\30\1\31\1\32"+
    "\1\33\3\4\1\34\1\0\1\35\1\5\2\0\10\4"+
    "\1\36\4\4\4\0\3\4\1\0\1\37\1\40\16\4"+
    "\1\0\3\4\1\5\1\0\2\4\1\41\2\4\1\42"+
    "\13\4\1\43\1\44\4\4\1\45\5\4\1\46\2\4"+
    "\1\47\4\4\1\50\1\51\14\4\1\52\3\4\1\53"+
    "\1\4\1\54\2\4\1\55\1\4\1\56\1\4\1\57"+
    "\4\4\1\60\1\61";

  private static int [] zzUnpackAction() {
    int [] result = new int[174];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u012c"+
    "\0\u015e\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u0226\0\u028a"+
    "\0\u02bc\0\u02ee\0\u0320\0\u0352\0\u0384\0\u03b6\0\u03e8\0\u041a"+
    "\0\u044c\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c"+
    "\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c\0\u047e\0\u04b0\0\u04e2"+
    "\0\u012c\0\u0514\0\u012c\0\u0546\0\u0578\0\u05aa\0\u05dc\0\u012c"+
    "\0\u060e\0\u012c\0\u012c\0\u0640\0\u0672\0\u06a4\0\u012c\0\u06d6"+
    "\0\u0708\0\u073a\0\u076c\0\u079e\0\u07d0\0\u0802\0\u0834\0\u0866"+
    "\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u01f4\0\u0960\0\u0992\0\u09c4"+
    "\0\u09f6\0\u0514\0\u0578\0\u0a28\0\u0a5a\0\u0a8c\0\u0abe\0\u0af0"+
    "\0\u0b22\0\u012c\0\u012c\0\u0b54\0\u0b86\0\u0bb8\0\u0bea\0\u0c1c"+
    "\0\u0c4e\0\u0c80\0\u0cb2\0\u0ce4\0\u0d16\0\u0d48\0\u0d7a\0\u0dac"+
    "\0\u0dde\0\u0e10\0\u0e42\0\u0e74\0\u0ea6\0\u0ed8\0\u0ed8\0\u0f0a"+
    "\0\u0f3c\0\u01f4\0\u0f6e\0\u0fa0\0\u01f4\0\u0fd2\0\u1004\0\u1036"+
    "\0\u1068\0\u109a\0\u10cc\0\u10fe\0\u1130\0\u1162\0\u1194\0\u11c6"+
    "\0\u01f4\0\u01f4\0\u11f8\0\u122a\0\u125c\0\u128e\0\u01f4\0\u12c0"+
    "\0\u12f2\0\u1324\0\u1356\0\u1388\0\u01f4\0\u13ba\0\u13ec\0\u01f4"+
    "\0\u141e\0\u1450\0\u1482\0\u14b4\0\u01f4\0\u01f4\0\u14e6\0\u1518"+
    "\0\u154a\0\u157c\0\u15ae\0\u15e0\0\u1612\0\u1644\0\u1676\0\u16a8"+
    "\0\u16da\0\u170c\0\u01f4\0\u173e\0\u1770\0\u17a2\0\u01f4\0\u17d4"+
    "\0\u01f4\0\u1806\0\u1838\0\u01f4\0\u186a\0\u01f4\0\u189c\0\u01f4"+
    "\0\u18ce\0\u1900\0\u1932\0\u1964\0\u01f4\0\u01f4";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[174];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\2\7\1\10\1\11\1\10\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\14\2\7\1\21\1\22\1\23"+
    "\1\24\2\14\1\25\1\26\1\14\1\27\1\30\1\14"+
    "\1\31\3\14\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\4\14"+
    "\1\47\1\14\4\50\2\51\10\50\1\52\1\53\42\50"+
    "\3\54\1\11\1\10\1\12\10\54\1\55\1\56\42\54"+
    "\7\51\1\57\4\51\1\57\3\51\17\57\11\51\1\60"+
    "\2\51\7\57\37\61\1\62\10\61\1\63\11\61\2\7"+
    "\1\10\1\11\1\10\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\14\2\7\1\21\1\64\1\23\1\24"+
    "\2\14\1\65\1\26\1\14\1\27\1\30\1\14\1\66"+
    "\3\14\1\67\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\4\14\1\47"+
    "\1\14\65\0\1\11\62\0\1\10\56\0\3\13\2\0"+
    "\54\13\7\0\2\14\3\0\1\14\3\0\17\14\14\0"+
    "\7\14\10\0\1\15\2\0\1\70\55\0\1\71\4\0"+
    "\1\71\3\0\17\71\14\0\7\71\10\0\1\72\2\0"+
    "\1\73\65\0\1\74\51\0\2\14\3\0\1\14\3\0"+
    "\1\14\1\75\15\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\12\14\1\76\4\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\12\14\1\77\4\14\14\0"+
    "\4\14\1\100\2\14\7\0\2\14\3\0\1\14\3\0"+
    "\1\14\1\101\15\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\4\14\1\102\12\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\5\14\1\103\4\14\1\104"+
    "\4\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\12\14\1\105\4\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\1\14\1\106\13\14\1\107\1\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\3\14\1\110"+
    "\13\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\10\14\1\111\6\14\14\0\7\14\4\50\2\0\10\50"+
    "\1\112\1\0\124\50\3\54\3\0\10\54\1\113\1\114"+
    "\142\54\1\113\1\115\42\54\7\0\2\57\3\0\1\57"+
    "\3\0\17\57\14\0\7\57\37\61\1\0\10\61\1\0"+
    "\11\61\7\0\2\14\3\0\1\14\3\0\1\14\1\116"+
    "\15\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\1\14\1\117\15\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\1\14\1\120\13\14\1\107\1\14\14\0"+
    "\7\14\10\0\1\72\60\0\2\71\3\0\1\71\3\0"+
    "\17\71\14\0\7\71\10\0\1\72\3\0\1\121\5\0"+
    "\1\121\52\0\1\122\65\0\1\123\51\0\2\14\3\0"+
    "\1\14\3\0\2\14\1\124\14\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\10\14\1\125\6\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\1\14\1\126"+
    "\15\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\6\14\1\127\10\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\6\14\1\130\10\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\16\14\1\131\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\16\14\1\132\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\6\14\1\133"+
    "\7\14\1\134\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\14\14\1\135\2\14\14\0\7\14\7\0\2\14"+
    "\3\0\1\14\3\0\7\14\1\136\7\14\14\0\2\14"+
    "\1\137\4\14\7\0\2\14\3\0\1\14\3\0\7\14"+
    "\1\140\7\14\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\3\14\1\141\13\14\14\0\7\14\16\54\1\113"+
    "\1\142\60\54\1\113\1\53\42\54\7\0\2\14\3\0"+
    "\1\14\3\0\2\14\1\143\14\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\6\14\1\144\10\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\14\14\1\145"+
    "\2\14\14\0\7\14\10\0\1\146\1\0\1\147\2\0"+
    "\1\147\53\0\2\14\3\0\1\14\3\0\3\14\1\150"+
    "\13\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\11\14\1\151\5\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\5\14\1\152\11\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\2\14\1\153\14\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\7\14\1\154"+
    "\7\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\2\14\1\155\14\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\17\14\14\0\3\14\1\156\3\14\7\0"+
    "\2\14\3\0\1\14\3\0\2\14\1\157\14\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\1\14\1\160"+
    "\15\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\13\14\1\161\3\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\17\14\14\0\3\14\1\162\3\14\7\0"+
    "\2\14\3\0\1\14\3\0\2\14\1\163\14\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\17\14\14\0"+
    "\1\14\1\164\5\14\7\0\2\14\3\0\1\14\3\0"+
    "\2\14\1\165\14\14\14\0\7\14\16\54\1\113\1\0"+
    "\42\54\7\0\2\14\3\0\1\14\3\0\3\14\1\166"+
    "\13\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\7\14\1\167\7\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\13\14\1\170\3\14\14\0\7\14\10\0"+
    "\1\146\60\0\2\14\3\0\1\14\3\0\4\14\1\171"+
    "\12\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\12\14\1\172\4\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\12\14\1\173\4\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\6\14\1\174\10\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\2\14\1\175"+
    "\14\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\3\14\1\176\13\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\6\14\1\177\10\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\15\14\1\200\1\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\7\14\1\201"+
    "\7\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\5\14\1\202\11\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\5\14\1\203\11\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\15\14\1\204\1\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\4\14\1\205"+
    "\12\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\6\14\1\206\10\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\15\14\1\207\1\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\17\14\14\0\5\14\1\210"+
    "\1\14\7\0\2\14\3\0\1\14\3\0\10\14\1\211"+
    "\6\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\5\14\1\212\11\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\17\14\14\0\1\213\6\14\7\0\2\14"+
    "\3\0\1\14\3\0\3\14\1\214\13\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\3\14\1\215\13\14"+
    "\14\0\7\14\7\0\2\14\3\0\1\14\3\0\7\14"+
    "\1\216\7\14\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\2\14\1\217\14\14\14\0\7\14\7\0\2\14"+
    "\3\0\1\14\3\0\6\14\1\220\10\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\10\14\1\221\6\14"+
    "\14\0\7\14\7\0\2\14\3\0\1\14\3\0\3\14"+
    "\1\222\13\14\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\11\14\1\223\5\14\14\0\7\14\7\0\2\14"+
    "\3\0\1\14\3\0\2\14\1\224\14\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\7\14\1\225\7\14"+
    "\14\0\7\14\7\0\2\14\3\0\1\14\3\0\10\14"+
    "\1\226\6\14\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\12\14\1\227\4\14\14\0\7\14\7\0\2\14"+
    "\3\0\1\14\3\0\10\14\1\230\6\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\11\14\1\231\5\14"+
    "\14\0\7\14\7\0\2\14\3\0\1\14\3\0\10\14"+
    "\1\232\6\14\14\0\7\14\7\0\2\14\3\0\1\14"+
    "\3\0\12\14\1\233\4\14\14\0\7\14\7\0\2\14"+
    "\3\0\1\14\3\0\12\14\1\234\4\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\15\14\1\235\1\14"+
    "\14\0\7\14\7\0\2\14\3\0\1\14\3\0\16\14"+
    "\1\236\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\6\14\1\237\10\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\17\14\14\0\6\14\1\240\7\0\2\14"+
    "\3\0\1\14\3\0\12\14\1\241\4\14\14\0\7\14"+
    "\7\0\2\14\3\0\1\14\3\0\16\14\1\242\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\6\14\1\243"+
    "\10\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\2\14\1\244\14\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\6\14\1\245\10\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\2\14\1\246\14\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\6\14\1\247"+
    "\10\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\13\14\1\250\3\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\10\14\1\251\6\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\10\14\1\252\6\14\14\0"+
    "\7\14\7\0\2\14\3\0\1\14\3\0\11\14\1\253"+
    "\5\14\14\0\7\14\7\0\2\14\3\0\1\14\3\0"+
    "\11\14\1\254\5\14\14\0\7\14\7\0\2\14\3\0"+
    "\1\14\3\0\12\14\1\255\4\14\14\0\7\14\7\0"+
    "\2\14\3\0\1\14\3\0\12\14\1\256\4\14\14\0"+
    "\7\14";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6550];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\6\0\2\11\21\1\14\11\3\1\1\11\1\1\1\11"+
    "\4\1\1\11\1\1\2\11\3\1\1\11\1\0\2\1"+
    "\2\0\15\1\4\0\3\1\1\0\2\11\16\1\1\0"+
    "\4\1\1\0\107\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[174];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

    private static final class State {
        final int lBraceCount;
        final int state;

        public State(int state, int lBraceCount) {
            this.state = state;
            this.lBraceCount = lBraceCount;
        }

        @Override
        public String toString() {
            return "yystate = " + state + (lBraceCount == 0 ? "" : "lBraceCount = " + lBraceCount);
        }
    }

    protected final Stack<State> myStateStack = new Stack<State>();
    protected int myLeftBraceCount;

    private void pushState(int state) {
        myStateStack.push(new State(yystate(), myLeftBraceCount));
        myLeftBraceCount = 0;
        yybegin(state);
    }

    private void popState() {
        State state = myStateStack.pop();
        myLeftBraceCount = state.lBraceCount;
        yybegin(state.state);
    }

    private IElementType keywordOrName(IElementType keyword) {
        if(GraphQLLexerUtil.isKeywordAtPos(zzBuffer, zzMarkedPos)) {
            if(SCHEMA_KEYWORD.equals(keyword)) {
                // the schema keyword, so handle the operation types correctly in that state
                pushState(SCHEMA_DEFINITION);
            }
            return keyword;
        }
        return NAME;
    }

  public GraphQLLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public GraphQLLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
      myLeftBraceCount = 0;
  myStateStack.clear();

    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return BAD_CHARACTER;
            } 
            // fall through
          case 50: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 51: break;
          case 3: 
            { return COMMENT;
            } 
            // fall through
          case 52: break;
          case 4: 
            { return NAME;
            } 
            // fall through
          case 53: break;
          case 5: 
            { return NUMBER;
            } 
            // fall through
          case 54: break;
          case 6: 
            { pushState(VARIABLE_OR_TEMPLATE); return DOLLAR;
            } 
            // fall through
          case 55: break;
          case 7: 
            { pushState(QUO_STRING);        return OPEN_QUOTE;
            } 
            // fall through
          case 56: break;
          case 8: 
            { return BRACE_R;
            } 
            // fall through
          case 57: break;
          case 9: 
            { return BANG;
            } 
            // fall through
          case 58: break;
          case 10: 
            { return PAREN_L;
            } 
            // fall through
          case 59: break;
          case 11: 
            { return PAREN_R;
            } 
            // fall through
          case 60: break;
          case 12: 
            { return COLON;
            } 
            // fall through
          case 61: break;
          case 13: 
            { return EQUALS;
            } 
            // fall through
          case 62: break;
          case 14: 
            { return AT;
            } 
            // fall through
          case 63: break;
          case 15: 
            { return BRACKET_L;
            } 
            // fall through
          case 64: break;
          case 16: 
            { return BRACKET_R;
            } 
            // fall through
          case 65: break;
          case 17: 
            { return BRACE_L;
            } 
            // fall through
          case 66: break;
          case 18: 
            { return PIPE;
            } 
            // fall through
          case 67: break;
          case 19: 
            { return AMP;
            } 
            // fall through
          case 68: break;
          case 20: 
            { return REGULAR_STRING_PART;
            } 
            // fall through
          case 69: break;
          case 21: 
            { popState(); return BAD_CHARACTER;
            } 
            // fall through
          case 70: break;
          case 22: 
            { popState(); return CLOSING_QUOTE;
            } 
            // fall through
          case 71: break;
          case 23: 
            { popState(); return NAME;
            } 
            // fall through
          case 72: break;
          case 24: 
            { pushState(TEMPLATE); return BRACE_L;
            } 
            // fall through
          case 73: break;
          case 25: 
            { return TEMPLATE_CHAR;
            } 
            // fall through
          case 74: break;
          case 26: 
            { if (myLeftBraceCount == 0) { popState(); popState(); return BRACE_R; } myLeftBraceCount--; return TEMPLATE_CHAR;
            } 
            // fall through
          case 75: break;
          case 27: 
            { myLeftBraceCount++; return TEMPLATE_CHAR;
            } 
            // fall through
          case 76: break;
          case 28: 
            { popState(); return BRACE_R;
            } 
            // fall through
          case 77: break;
          case 29: 
            { return VARIABLE_NAME;
            } 
            // fall through
          case 78: break;
          case 30: 
            { return keywordOrName(ON_KEYWORD);
            } 
            // fall through
          case 79: break;
          case 31: 
            { return SPREAD;
            } 
            // fall through
          case 80: break;
          case 32: 
            { pushState(THREE_QUO_STRING);  return OPEN_QUOTE;
            } 
            // fall through
          case 81: break;
          case 33: 
            { return keywordOrName(ENUM_KEYWORD);
            } 
            // fall through
          case 82: break;
          case 34: 
            { return keywordOrName(TYPE_KEYWORD);
            } 
            // fall through
          case 83: break;
          case 35: 
            { return keywordOrName(QUERY_KEYWORD);
            } 
            // fall through
          case 84: break;
          case 36: 
            { return keywordOrName(UNION_KEYWORD);
            } 
            // fall through
          case 85: break;
          case 37: 
            { return keywordOrName(INPUT_KEYWORD);
            } 
            // fall through
          case 86: break;
          case 38: 
            { return QUERY_KEYWORD;
            } 
            // fall through
          case 87: break;
          case 39: 
            { return keywordOrName(EXTEND_KEYWORD);
            } 
            // fall through
          case 88: break;
          case 40: 
            { return keywordOrName(SCALAR_KEYWORD);
            } 
            // fall through
          case 89: break;
          case 41: 
            { return keywordOrName(SCHEMA_KEYWORD);
            } 
            // fall through
          case 90: break;
          case 42: 
            { return keywordOrName(MUTATION_KEYWORD);
            } 
            // fall through
          case 91: break;
          case 43: 
            { return keywordOrName(FRAGMENT_KEYWORD);
            } 
            // fall through
          case 92: break;
          case 44: 
            { return MUTATION_KEYWORD;
            } 
            // fall through
          case 93: break;
          case 45: 
            { return keywordOrName(INTERFACE_KEYWORD);
            } 
            // fall through
          case 94: break;
          case 46: 
            { return keywordOrName(DIRECTIVE_KEYWORD);
            } 
            // fall through
          case 95: break;
          case 47: 
            { return keywordOrName(IMPLEMENTS_KEYWORD);
            } 
            // fall through
          case 96: break;
          case 48: 
            { return keywordOrName(SUBSCRIPTION_KEYWORD);
            } 
            // fall through
          case 97: break;
          case 49: 
            { return SUBSCRIPTION_KEYWORD;
            } 
            // fall through
          case 98: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
