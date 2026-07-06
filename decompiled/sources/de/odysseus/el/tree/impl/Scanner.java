package de.odysseus.el.tree.impl;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.common.net.MediaType;
import de.odysseus.el.misc.LocalMessages;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.text.StringSubstitutor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Scanner {
    public static final HashMap<Symbol, Token> FIXMAP;
    public static final HashMap<String, Token> KEYMAP;
    public final StringBuilder builder = new StringBuilder();
    public final String input;
    public int position;
    public Token token;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ExtensionToken extends Token {
        public ExtensionToken(String str) {
            super(Symbol.EXTENSION, str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ScanException extends Exception {
        public final String encountered;
        public final String expected;
        public final int position;

        public ScanException(int i, String str, String str2) {
            super(LocalMessages.get("error.scan", Integer.valueOf(i), str, str2));
            this.position = i;
            this.encountered = str;
            this.expected = str2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Symbol {
        EOF(null),
        PLUS("'+'"),
        MINUS("'-'"),
        MUL("'*'"),
        DIV("'/'|'div'"),
        MOD("'%'|'mod'"),
        LPAREN("'('"),
        RPAREN("')'"),
        IDENTIFIER(null),
        NOT("'!'|'not'"),
        AND("'&&'|'and'"),
        OR("'||'|'or'"),
        EMPTY("'empty'"),
        INSTANCEOF("'instanceof'"),
        INTEGER(null),
        FLOAT(null),
        TRUE("'true'"),
        FALSE("'false'"),
        STRING(null),
        NULL("'null'"),
        LE("'<='|'le'"),
        LT("'<'|'lt'"),
        GE("'>='|'ge'"),
        GT("'>'|'gt'"),
        EQ("'=='|'eq'"),
        NE("'!='|'ne'"),
        QUESTION("'?'"),
        COLON("':'"),
        TEXT(null),
        DOT("'.'"),
        LBRACK("'['"),
        RBRACK("']'"),
        COMMA("','"),
        START_EVAL_DEFERRED("'#{'"),
        START_EVAL_DYNAMIC("'${'"),
        END_EVAL("'}'"),
        EXTENSION(null);

        public final String string;

        Symbol() {
            this(null);
        }

        @Override // java.lang.Enum
        public String toString() {
            String str = this.string;
            if (str != null) {
                return str;
            }
            return "<" + name() + ">";
        }

        Symbol(String str) {
            this.string = str;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Token {
        public final String image;
        public final int length;
        public final Symbol symbol;

        public Token(Symbol symbol, String str) {
            this(symbol, str, str.length());
        }

        public String getImage() {
            return this.image;
        }

        public int getSize() {
            return this.length;
        }

        public Symbol getSymbol() {
            return this.symbol;
        }

        public String toString() {
            return this.symbol.toString();
        }

        public Token(Symbol symbol, String str, int i) {
            this.symbol = symbol;
            this.image = str;
            this.length = i;
        }
    }

    static {
        HashMap<String, Token> map = new HashMap<>();
        KEYMAP = map;
        HashMap<Symbol, Token> map2 = new HashMap<>();
        FIXMAP = map2;
        Symbol symbol = Symbol.PLUS;
        map2.put(symbol, new Token(symbol, "+", 1));
        Symbol symbol2 = Symbol.MINUS;
        map2.put(symbol2, new Token(symbol2, "-", 1));
        Symbol symbol3 = Symbol.MUL;
        map2.put(symbol3, new Token(symbol3, MediaType.WILDCARD, 1));
        Symbol symbol4 = Symbol.DIV;
        map2.put(symbol4, new Token(symbol4, SchemaId.METRIC_SCHEMA_ID_DELIMITER, 1));
        Symbol symbol5 = Symbol.MOD;
        map2.put(symbol5, new Token(symbol5, "%", 1));
        Symbol symbol6 = Symbol.LPAREN;
        map2.put(symbol6, new Token(symbol6, "(", 1));
        Symbol symbol7 = Symbol.RPAREN;
        map2.put(symbol7, new Token(symbol7, ")", 1));
        Symbol symbol8 = Symbol.NOT;
        map2.put(symbol8, new Token(symbol8, "!", 1));
        Symbol symbol9 = Symbol.AND;
        map2.put(symbol9, new Token(symbol9, "&&", 2));
        Symbol symbol10 = Symbol.OR;
        map2.put(symbol10, new Token(symbol10, "||", 2));
        Symbol symbol11 = Symbol.EQ;
        map2.put(symbol11, new Token(symbol11, "==", 2));
        Symbol symbol12 = Symbol.NE;
        map2.put(symbol12, new Token(symbol12, "!=", 2));
        Symbol symbol13 = Symbol.LT;
        map2.put(symbol13, new Token(symbol13, "<", 1));
        Symbol symbol14 = Symbol.LE;
        map2.put(symbol14, new Token(symbol14, "<=", 2));
        Symbol symbol15 = Symbol.GT;
        map2.put(symbol15, new Token(symbol15, ">", 1));
        Symbol symbol16 = Symbol.GE;
        map2.put(symbol16, new Token(symbol16, ">=", 2));
        Symbol symbol17 = Symbol.QUESTION;
        map2.put(symbol17, new Token(symbol17, "?", 1));
        Symbol symbol18 = Symbol.COLON;
        map2.put(symbol18, new Token(symbol18, ":", 1));
        Symbol symbol19 = Symbol.COMMA;
        map2.put(symbol19, new Token(symbol19, ",", 1));
        Symbol symbol20 = Symbol.DOT;
        map2.put(symbol20, new Token(symbol20, ExternalFourCCMapper.CODEC_NAME_SPLITTER, 1));
        Symbol symbol21 = Symbol.LBRACK;
        map2.put(symbol21, new Token(symbol21, "[", 1));
        Symbol symbol22 = Symbol.RBRACK;
        map2.put(symbol22, new Token(symbol22, "]", 1));
        Symbol symbol23 = Symbol.START_EVAL_DEFERRED;
        map2.put(symbol23, new Token(symbol23, "#{", 2));
        Symbol symbol24 = Symbol.START_EVAL_DYNAMIC;
        map2.put(symbol24, new Token(symbol24, StringSubstitutor.DEFAULT_VAR_START, 2));
        Symbol symbol25 = Symbol.END_EVAL;
        map2.put(symbol25, new Token(symbol25, "}", 1));
        Symbol symbol26 = Symbol.EOF;
        map2.put(symbol26, new Token(symbol26, null, 0));
        map.put(AbstractJsonLexerKt.NULL, new Token(Symbol.NULL, AbstractJsonLexerKt.NULL, 4));
        map.put("true", new Token(Symbol.TRUE, "true", 4));
        map.put("false", new Token(Symbol.FALSE, "false", 5));
        map.put("empty", new Token(Symbol.EMPTY, "empty", 5));
        map.put("div", new Token(symbol4, "div", 3));
        map.put("mod", new Token(symbol5, "mod", 3));
        map.put("not", new Token(symbol8, "not", 3));
        map.put("and", new Token(symbol9, "and", 3));
        map.put("or", new Token(symbol10, "or", 2));
        map.put("le", new Token(symbol14, "le", 2));
        map.put("lt", new Token(symbol13, "lt", 2));
        map.put("eq", new Token(symbol11, "eq", 2));
        map.put("ne", new Token(symbol12, "ne", 2));
        map.put("ge", new Token(symbol16, "ge", 2));
        map.put("gt", new Token(symbol15, "gt", 2));
        map.put("instanceof", new Token(Symbol.INSTANCEOF, "instanceof", 10));
    }

    public Scanner(String str) {
        this.input = str;
    }

    public static void addFixToken(Token token) {
        FIXMAP.put(token.getSymbol(), token);
    }

    public static void addKeyToken(Token token) {
        KEYMAP.put(token.getImage(), token);
    }

    public Token fixed(Symbol symbol) {
        return FIXMAP.get(symbol);
    }

    public String getInput() {
        return this.input;
    }

    public int getPosition() {
        return this.position;
    }

    public Token getToken() {
        return this.token;
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isEval() {
        Token token = this.token;
        return (token == null || token.getSymbol() == Symbol.TEXT || this.token.getSymbol() == Symbol.END_EVAL) ? false : true;
    }

    public Token keyword(String str) {
        return KEYMAP.get(str);
    }

    public Token next() throws ScanException {
        Token token = this.token;
        if (token != null) {
            this.position = token.getSize() + this.position;
        }
        int length = this.input.length();
        if (isEval()) {
            while (true) {
                int i = this.position;
                if (i >= length || !Character.isWhitespace(this.input.charAt(i))) {
                    break;
                }
                this.position++;
            }
        }
        if (this.position == length) {
            Token tokenFixed = fixed(Symbol.EOF);
            this.token = tokenFixed;
            return tokenFixed;
        }
        Token tokenNextToken = nextToken();
        this.token = tokenNextToken;
        return tokenNextToken;
    }

    public Token nextEval() throws ScanException {
        char cCharAt = this.input.charAt(this.position);
        char cCharAt2 = this.position < this.input.length() + (-1) ? this.input.charAt(this.position + 1) : (char) 0;
        if (cCharAt == '!') {
            return cCharAt2 == '=' ? fixed(Symbol.NE) : fixed(Symbol.NOT);
        }
        if (cCharAt != '\"') {
            if (cCharAt == ':') {
                return fixed(Symbol.COLON);
            }
            if (cCharAt == '[') {
                return fixed(Symbol.LBRACK);
            }
            if (cCharAt == ']') {
                return fixed(Symbol.RBRACK);
            }
            if (cCharAt != '|') {
                switch (cCharAt) {
                    case '%':
                        return fixed(Symbol.MOD);
                    case '&':
                        if (cCharAt2 == '&') {
                            return fixed(Symbol.AND);
                        }
                        break;
                    case '\'':
                        break;
                    case '(':
                        return fixed(Symbol.LPAREN);
                    case ')':
                        return fixed(Symbol.RPAREN);
                    case '*':
                        return fixed(Symbol.MUL);
                    case '+':
                        return fixed(Symbol.PLUS);
                    case ',':
                        return fixed(Symbol.COMMA);
                    case '-':
                        return fixed(Symbol.MINUS);
                    case '.':
                        if (!isDigit(cCharAt2)) {
                            return fixed(Symbol.DOT);
                        }
                        break;
                    case '/':
                        return fixed(Symbol.DIV);
                    default:
                        switch (cCharAt) {
                            case '<':
                                return cCharAt2 == '=' ? fixed(Symbol.LE) : fixed(Symbol.LT);
                            case '=':
                                if (cCharAt2 == '=') {
                                    return fixed(Symbol.EQ);
                                }
                                break;
                            case '>':
                                return cCharAt2 == '=' ? fixed(Symbol.GE) : fixed(Symbol.GT);
                            case '?':
                                return fixed(Symbol.QUESTION);
                        }
                        break;
                }
            } else if (cCharAt2 == '|') {
                return fixed(Symbol.OR);
            }
            if (isDigit(cCharAt) || cCharAt == '.') {
                return nextNumber();
            }
            if (!Character.isJavaIdentifierStart(cCharAt)) {
                throw new ScanException(this.position, "invalid character '" + cCharAt + "'", "expression token");
            }
            int i = this.position + 1;
            int length = this.input.length();
            while (i < length && Character.isJavaIdentifierPart(this.input.charAt(i))) {
                i++;
            }
            String strSubstring = this.input.substring(this.position, i);
            Token tokenKeyword = keyword(strSubstring);
            return tokenKeyword == null ? token(Symbol.IDENTIFIER, strSubstring, i - this.position) : tokenKeyword;
        }
        return nextString();
    }

    public Token nextNumber() throws ScanException {
        int i = this.position;
        int length = this.input.length();
        while (i < length && isDigit(this.input.charAt(i))) {
            i++;
        }
        Symbol symbol = Symbol.INTEGER;
        if (i < length && this.input.charAt(i) == '.') {
            do {
                i++;
                if (i >= length) {
                    break;
                }
            } while (isDigit(this.input.charAt(i)));
            symbol = Symbol.FLOAT;
        }
        if (i < length && (this.input.charAt(i) == 'e' || this.input.charAt(i) == 'E')) {
            int i2 = i + 1;
            if (i2 < length && (this.input.charAt(i2) == '+' || this.input.charAt(i2) == '-')) {
                i2 = i + 2;
            }
            if (i2 < length && isDigit(this.input.charAt(i2))) {
                i = i2 + 1;
                while (i < length && isDigit(this.input.charAt(i))) {
                    i++;
                }
                symbol = Symbol.FLOAT;
            }
        }
        return token(symbol, this.input.substring(this.position, i), i - this.position);
    }

    public Token nextString() throws ScanException {
        this.builder.setLength(0);
        char cCharAt = this.input.charAt(this.position);
        int i = this.position + 1;
        int length = this.input.length();
        while (i < length) {
            int i2 = i + 1;
            char cCharAt2 = this.input.charAt(i);
            if (cCharAt2 == '\\') {
                if (i2 == length) {
                    throw new ScanException(this.position, "unterminated string", cCharAt + " or \\");
                }
                i += 2;
                char cCharAt3 = this.input.charAt(i2);
                if (cCharAt3 != '\\' && cCharAt3 != cCharAt) {
                    throw new ScanException(this.position, "invalid escape sequence \\" + cCharAt3, CCTDestination.EXTRAS_DELIMITER + cCharAt + " or \\\\");
                }
                this.builder.append(cCharAt3);
            } else {
                if (cCharAt2 == cCharAt) {
                    return token(Symbol.STRING, this.builder.toString(), i2 - this.position);
                }
                this.builder.append(cCharAt2);
                i = i2;
            }
        }
        throw new ScanException(this.position, "unterminated string", String.valueOf(cCharAt));
    }

    public Token nextText() throws ScanException {
        this.builder.setLength(0);
        int i = this.position;
        int length = this.input.length();
        boolean z = false;
        while (i < length) {
            char cCharAt = this.input.charAt(i);
            if (cCharAt == '#' || cCharAt == '$') {
                int i2 = i + 1;
                if (i2 >= length || this.input.charAt(i2) != '{') {
                    if (z) {
                        this.builder.append('\\');
                    }
                    this.builder.append(cCharAt);
                } else {
                    if (!z) {
                        return token(Symbol.TEXT, this.builder.toString(), i - this.position);
                    }
                    this.builder.append(cCharAt);
                }
            } else if (cCharAt != '\\') {
                if (z) {
                    this.builder.append('\\');
                }
                this.builder.append(cCharAt);
            } else {
                if (z) {
                    this.builder.append('\\');
                } else {
                    z = true;
                }
                i++;
            }
            z = false;
            i++;
        }
        if (z) {
            this.builder.append('\\');
        }
        return token(Symbol.TEXT, this.builder.toString(), i - this.position);
    }

    public Token nextToken() throws ScanException {
        if (isEval()) {
            return this.input.charAt(this.position) == '}' ? fixed(Symbol.END_EVAL) : nextEval();
        }
        if (this.position + 1 < this.input.length() && this.input.charAt(this.position + 1) == '{') {
            char cCharAt = this.input.charAt(this.position);
            if (cCharAt == '#') {
                return fixed(Symbol.START_EVAL_DEFERRED);
            }
            if (cCharAt == '$') {
                return fixed(Symbol.START_EVAL_DYNAMIC);
            }
        }
        return nextText();
    }

    public Token token(Symbol symbol, String str, int i) {
        return new Token(symbol, str, i);
    }
}
