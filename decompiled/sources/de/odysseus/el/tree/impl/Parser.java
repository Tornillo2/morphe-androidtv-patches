package de.odysseus.el.tree.impl;

import com.amazon.minerva.identifiers.schemaid.MetricSchemaUUID;
import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.FunctionNode;
import de.odysseus.el.tree.IdentifierNode;
import de.odysseus.el.tree.Tree;
import de.odysseus.el.tree.impl.Builder;
import de.odysseus.el.tree.impl.Scanner;
import de.odysseus.el.tree.impl.ast.AstBinary;
import de.odysseus.el.tree.impl.ast.AstBoolean;
import de.odysseus.el.tree.impl.ast.AstBracket;
import de.odysseus.el.tree.impl.ast.AstChoice;
import de.odysseus.el.tree.impl.ast.AstComposite;
import de.odysseus.el.tree.impl.ast.AstDot;
import de.odysseus.el.tree.impl.ast.AstEval;
import de.odysseus.el.tree.impl.ast.AstFunction;
import de.odysseus.el.tree.impl.ast.AstIdentifier;
import de.odysseus.el.tree.impl.ast.AstMethod;
import de.odysseus.el.tree.impl.ast.AstNested;
import de.odysseus.el.tree.impl.ast.AstNode;
import de.odysseus.el.tree.impl.ast.AstNull;
import de.odysseus.el.tree.impl.ast.AstNumber;
import de.odysseus.el.tree.impl.ast.AstParameters;
import de.odysseus.el.tree.impl.ast.AstProperty;
import de.odysseus.el.tree.impl.ast.AstString;
import de.odysseus.el.tree.impl.ast.AstText;
import de.odysseus.el.tree.impl.ast.AstUnary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Parser {
    public static final String EXPR_FIRST = Scanner.Symbol.IDENTIFIER + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.STRING + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.FLOAT + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.INTEGER + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.TRUE + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.FALSE + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.NULL + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.MINUS + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.NOT + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.EMPTY + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.LPAREN;
    public final Builder context;
    public Map<Scanner.ExtensionToken, ExtensionHandler> extensions;
    public List<FunctionNode> functions;
    public List<IdentifierNode> identifiers;
    public List<LookaheadToken> lookahead;
    public int position;
    public final Scanner scanner;
    public Scanner.Token token;

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.Parser$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol;

        static {
            int[] iArr = new int[Scanner.Symbol.values().length];
            $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol = iArr;
            try {
                iArr[Scanner.Symbol.OR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.EXTENSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.AND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.EQ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.NE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.LT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.LE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.GE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.GT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.PLUS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.MINUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.MUL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.DIV.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.MOD.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.NOT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.EMPTY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.DOT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.LBRACK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.IDENTIFIER.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.LPAREN.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.TRUE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.FALSE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.STRING.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.INTEGER.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.FLOAT.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[Scanner.Symbol.NULL.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class ExtensionHandler {
        public final ExtensionPoint point;

        public ExtensionHandler(ExtensionPoint extensionPoint) {
            this.point = extensionPoint;
        }

        public abstract AstNode createAstNode(AstNode... astNodeArr);

        public ExtensionPoint getExtensionPoint() {
            return this.point;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ExtensionPoint {
        OR,
        AND,
        EQ,
        CMP,
        ADD,
        MUL,
        UNARY,
        LITERAL
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LookaheadToken {
        public final int position;
        public final Scanner.Token token;

        public LookaheadToken(Scanner.Token token, int i) {
            this.token = token;
            this.position = i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ParseException extends Exception {
        public final String encountered;
        public final String expected;
        public final int position;

        public ParseException(int i, String str, String str2) {
            super(LocalMessages.get("error.parse", Integer.valueOf(i), str, str2));
            this.position = i;
            this.encountered = str;
            this.expected = str2;
        }
    }

    public Parser(Builder builder, String str) {
        List list = Collections.EMPTY_LIST;
        this.identifiers = list;
        this.functions = list;
        this.lookahead = list;
        this.extensions = Collections.EMPTY_MAP;
        this.context = builder;
        this.scanner = createScanner(str);
    }

    public AstNode add(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeMul = mul(z);
        if (astNodeMul == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i == 2) {
                if (getExtensionHandler(this.token).getExtensionPoint() != ExtensionPoint.ADD) {
                    break;
                }
                astNodeMul = getExtensionHandler(consumeToken()).createAstNode(astNodeMul, mul(true));
            } else if (i == 10) {
                consumeToken();
                astNodeMul = createAstBinary(astNodeMul, mul(true), AstBinary.ADD);
            } else {
                if (i != 11) {
                    break;
                }
                consumeToken();
                astNodeMul = createAstBinary(astNodeMul, mul(true), AstBinary.SUB);
            }
        }
        return astNodeMul;
    }

    public AstNode and(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeEq = eq(z);
        if (astNodeEq == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i == 2) {
                if (getExtensionHandler(this.token).getExtensionPoint() != ExtensionPoint.AND) {
                    break;
                }
                astNodeEq = getExtensionHandler(consumeToken()).createAstNode(astNodeEq, eq(true));
            } else {
                if (i != 3) {
                    break;
                }
                consumeToken();
                astNodeEq = createAstBinary(astNodeEq, eq(true), AstBinary.AND);
            }
        }
        return astNodeEq;
    }

    public AstNode cmp(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeAdd = add(z);
        if (astNodeAdd == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i != 2) {
                switch (i) {
                    case 6:
                        consumeToken();
                        astNodeAdd = createAstBinary(astNodeAdd, add(true), AstBinary.LT);
                        break;
                    case 7:
                        consumeToken();
                        astNodeAdd = createAstBinary(astNodeAdd, add(true), AstBinary.LE);
                        break;
                    case 8:
                        consumeToken();
                        astNodeAdd = createAstBinary(astNodeAdd, add(true), AstBinary.GE);
                        break;
                    case 9:
                        consumeToken();
                        astNodeAdd = createAstBinary(astNodeAdd, add(true), AstBinary.GT);
                        break;
                }
            } else if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.CMP) {
                astNodeAdd = getExtensionHandler(consumeToken()).createAstNode(astNodeAdd, add(true));
            }
        }
        return astNodeAdd;
    }

    public final Scanner.Token consumeToken() throws Scanner.ScanException, ParseException {
        Scanner.Token token = this.token;
        if (this.lookahead.isEmpty()) {
            this.token = this.scanner.next();
            this.position = this.scanner.getPosition();
            return token;
        }
        LookaheadToken lookaheadTokenRemove = this.lookahead.remove(0);
        this.token = lookaheadTokenRemove.token;
        this.position = lookaheadTokenRemove.position;
        return token;
    }

    public AstBinary createAstBinary(AstNode astNode, AstNode astNode2, AstBinary.Operator operator) {
        return new AstBinary(astNode, astNode2, operator);
    }

    public AstBracket createAstBracket(AstNode astNode, AstNode astNode2, boolean z, boolean z2) {
        return new AstBracket(astNode, astNode2, z, z2, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
    }

    public AstChoice createAstChoice(AstNode astNode, AstNode astNode2, AstNode astNode3) {
        return new AstChoice(astNode, astNode2, astNode3);
    }

    public AstComposite createAstComposite(List<AstNode> list) {
        return new AstComposite(list);
    }

    public AstDot createAstDot(AstNode astNode, String str, boolean z) {
        return new AstDot(astNode, str, z, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
    }

    public AstFunction createAstFunction(String str, int i, AstParameters astParameters) {
        return new AstFunction(str, i, astParameters, this.context.isEnabled(Builder.Feature.VARARGS));
    }

    public AstIdentifier createAstIdentifier(String str, int i) {
        return new AstIdentifier(str, i, this.context.isEnabled(Builder.Feature.IGNORE_RETURN_TYPE));
    }

    public AstMethod createAstMethod(AstProperty astProperty, AstParameters astParameters) {
        return new AstMethod(astProperty, astParameters);
    }

    public AstUnary createAstUnary(AstNode astNode, AstUnary.Operator operator) {
        return new AstUnary(astNode, operator);
    }

    public Scanner createScanner(String str) {
        return new Scanner(str);
    }

    public AstNode eq(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeCmp = cmp(z);
        if (astNodeCmp == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i == 2) {
                if (getExtensionHandler(this.token).getExtensionPoint() != ExtensionPoint.EQ) {
                    break;
                }
                astNodeCmp = getExtensionHandler(consumeToken()).createAstNode(astNodeCmp, cmp(true));
            } else if (i == 4) {
                consumeToken();
                astNodeCmp = createAstBinary(astNodeCmp, cmp(true), AstBinary.EQ);
            } else {
                if (i != 5) {
                    break;
                }
                consumeToken();
                astNodeCmp = createAstBinary(astNodeCmp, cmp(true), AstBinary.NE);
            }
        }
        return astNodeCmp;
    }

    public AstEval eval() throws Scanner.ScanException, ParseException {
        AstEval astEvalEval = eval(false, false);
        if (astEvalEval != null) {
            return astEvalEval;
        }
        AstEval astEvalEval2 = eval(false, true);
        if (astEvalEval2 != null) {
            return astEvalEval2;
        }
        fail(Scanner.Symbol.START_EVAL_DEFERRED + MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER + Scanner.Symbol.START_EVAL_DYNAMIC);
        throw null;
    }

    public AstNode expr(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeOr = or(z);
        if (astNodeOr == null) {
            return null;
        }
        if (this.token.getSymbol() != Scanner.Symbol.QUESTION) {
            return astNodeOr;
        }
        consumeToken();
        AstNode astNodeExpr = expr(true);
        consumeToken(Scanner.Symbol.COLON);
        return createAstChoice(astNodeOr, astNodeExpr, expr(true));
    }

    public void fail(String str) throws ParseException {
        throw new ParseException(this.position, "'" + this.token.getImage() + "'", str);
    }

    public final AstFunction function(String str, AstParameters astParameters) {
        if (this.functions.isEmpty()) {
            this.functions = new ArrayList(4);
        }
        AstFunction astFunctionCreateAstFunction = createAstFunction(str, this.functions.size(), astParameters);
        this.functions.add(astFunctionCreateAstFunction);
        return astFunctionCreateAstFunction;
    }

    public ExtensionHandler getExtensionHandler(Scanner.Token token) {
        return this.extensions.get(token);
    }

    public final List<FunctionNode> getFunctions() {
        return this.functions;
    }

    public final List<IdentifierNode> getIdentifiers() {
        return this.identifiers;
    }

    public final Scanner.Token getToken() {
        return this.token;
    }

    public final AstIdentifier identifier(String str) {
        if (this.identifiers.isEmpty()) {
            this.identifiers = new ArrayList(4);
        }
        AstIdentifier astIdentifierCreateAstIdentifier = createAstIdentifier(str, this.identifiers.size());
        this.identifiers.add(astIdentifierCreateAstIdentifier);
        return astIdentifierCreateAstIdentifier;
    }

    public AstNode literal() throws Scanner.ScanException, ParseException {
        int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
        if (i == 2) {
            if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.LITERAL) {
                return getExtensionHandler(consumeToken()).createAstNode(new AstNode[0]);
            }
            return null;
        }
        switch (i) {
            case 21:
                AstBoolean astBoolean = new AstBoolean(true);
                consumeToken();
                return astBoolean;
            case 22:
                AstBoolean astBoolean2 = new AstBoolean(false);
                consumeToken();
                return astBoolean2;
            case 23:
                AstString astString = new AstString(this.token.getImage());
                consumeToken();
                return astString;
            case 24:
                AstNumber astNumber = new AstNumber(parseInteger(this.token.getImage()));
                consumeToken();
                return astNumber;
            case 25:
                AstNumber astNumber2 = new AstNumber(parseFloat(this.token.getImage()));
                consumeToken();
                return astNumber2;
            case 26:
                AstNull astNull = new AstNull();
                consumeToken();
                return astNull;
            default:
                return null;
        }
    }

    public final Scanner.Token lookahead(int i) throws Scanner.ScanException, ParseException {
        if (this.lookahead.isEmpty()) {
            this.lookahead = new LinkedList();
        }
        while (i >= this.lookahead.size()) {
            this.lookahead.add(new LookaheadToken(this.scanner.next(), this.scanner.getPosition()));
        }
        return this.lookahead.get(i).token;
    }

    public AstNode mul(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeUnary = unary(z);
        if (astNodeUnary == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i != 2) {
                switch (i) {
                    case 12:
                        consumeToken();
                        astNodeUnary = createAstBinary(astNodeUnary, unary(true), AstBinary.MUL);
                        break;
                    case 13:
                        consumeToken();
                        astNodeUnary = createAstBinary(astNodeUnary, unary(true), AstBinary.DIV);
                        break;
                    case 14:
                        consumeToken();
                        astNodeUnary = createAstBinary(astNodeUnary, unary(true), AstBinary.MOD);
                        break;
                }
            } else if (getExtensionHandler(this.token).getExtensionPoint() == ExtensionPoint.MUL) {
                astNodeUnary = getExtensionHandler(consumeToken()).createAstNode(astNodeUnary, unary(true));
            }
        }
        return astNodeUnary;
    }

    public AstNode nonliteral() throws Scanner.ScanException, ParseException {
        int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
        if (i != 19) {
            if (i != 20) {
                return null;
            }
            consumeToken();
            AstNode astNodeExpr = expr(true);
            consumeToken(Scanner.Symbol.RPAREN);
            return new AstNested(astNodeExpr);
        }
        String image = consumeToken().getImage();
        if (this.token.getSymbol() == Scanner.Symbol.COLON && lookahead(0).getSymbol() == Scanner.Symbol.IDENTIFIER && lookahead(1).getSymbol() == Scanner.Symbol.LPAREN) {
            consumeToken();
            image = image + ":" + this.token.getImage();
            consumeToken();
        }
        return this.token.getSymbol() == Scanner.Symbol.LPAREN ? function(image, params()) : identifier(image);
    }

    public AstNode or(boolean z) throws Scanner.ScanException, ParseException {
        AstNode astNodeAnd = and(z);
        if (astNodeAnd == null) {
            return null;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i == 1) {
                consumeToken();
                astNodeAnd = createAstBinary(astNodeAnd, and(true), AstBinary.OR);
            } else {
                if (i != 2 || getExtensionHandler(this.token).getExtensionPoint() != ExtensionPoint.OR) {
                    break;
                }
                astNodeAnd = getExtensionHandler(consumeToken()).createAstNode(astNodeAnd, and(true));
            }
        }
        return astNodeAnd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList] */
    public AstParameters params() throws Scanner.ScanException, ParseException {
        consumeToken(Scanner.Symbol.LPAREN);
        ?? arrayList = Collections.EMPTY_LIST;
        AstNode astNodeExpr = expr(false);
        if (astNodeExpr != null) {
            arrayList = new ArrayList();
            arrayList.add(astNodeExpr);
            while (this.token.getSymbol() == Scanner.Symbol.COMMA) {
                consumeToken();
                arrayList.add(expr(true));
            }
        }
        consumeToken(Scanner.Symbol.RPAREN);
        return new AstParameters(arrayList);
    }

    public Number parseFloat(String str) throws ParseException {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException unused) {
            fail(Scanner.Symbol.FLOAT);
            return null;
        }
    }

    public Number parseInteger(String str) throws ParseException {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException unused) {
            fail(Scanner.Symbol.INTEGER);
            return null;
        }
    }

    public void putExtensionHandler(Scanner.ExtensionToken extensionToken, ExtensionHandler extensionHandler) {
        if (this.extensions.isEmpty()) {
            this.extensions = new HashMap(16);
        }
        this.extensions.put(extensionToken, extensionHandler);
    }

    public AstNode text() throws Scanner.ScanException, ParseException {
        if (this.token.getSymbol() != Scanner.Symbol.TEXT) {
            return null;
        }
        AstText astText = new AstText(this.token.getImage());
        consumeToken();
        return astText;
    }

    public Tree tree() throws Scanner.ScanException, ParseException {
        consumeToken();
        AstNode astNodeText = text();
        Scanner.Symbol symbol = this.token.getSymbol();
        Scanner.Symbol symbol2 = Scanner.Symbol.EOF;
        if (symbol == symbol2) {
            if (astNodeText == null) {
                astNodeText = new AstText("");
            }
            return new Tree(astNodeText, this.functions, this.identifiers, false);
        }
        AstEval astEvalEval = eval();
        if (this.token.getSymbol() == symbol2 && astNodeText == null) {
            return new Tree(astEvalEval, this.functions, this.identifiers, astEvalEval.deferred);
        }
        ArrayList arrayList = new ArrayList();
        if (astNodeText != null) {
            arrayList.add(astNodeText);
        }
        arrayList.add(astEvalEval);
        AstNode astNodeText2 = text();
        if (astNodeText2 != null) {
            arrayList.add(astNodeText2);
        }
        while (this.token.getSymbol() != Scanner.Symbol.EOF) {
            if (astEvalEval.deferred) {
                arrayList.add(eval(true, true));
            } else {
                arrayList.add(eval(true, false));
            }
            AstNode astNodeText3 = text();
            if (astNodeText3 != null) {
                arrayList.add(astNodeText3);
            }
        }
        return new Tree(createAstComposite(arrayList), this.functions, this.identifiers, astEvalEval.deferred);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public de.odysseus.el.tree.impl.ast.AstNode unary(boolean r5) throws de.odysseus.el.tree.impl.Scanner.ScanException, de.odysseus.el.tree.impl.Parser.ParseException {
        /*
            r4 = this;
            int[] r0 = de.odysseus.el.tree.impl.Parser.AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol
            de.odysseus.el.tree.impl.Scanner$Token r1 = r4.token
            de.odysseus.el.tree.impl.Scanner$Symbol r1 = r1.getSymbol()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 2
            r2 = 1
            if (r0 == r1) goto L49
            r1 = 11
            if (r0 == r1) goto L3b
            r1 = 15
            if (r0 == r1) goto L2d
            r1 = 16
            if (r0 == r1) goto L1f
            goto L6d
        L1f:
            r4.consumeToken()
            de.odysseus.el.tree.impl.ast.AstNode r0 = r4.unary(r2)
            de.odysseus.el.tree.impl.ast.AstUnary$Operator r1 = de.odysseus.el.tree.impl.ast.AstUnary.EMPTY
            de.odysseus.el.tree.impl.ast.AstUnary r0 = r4.createAstUnary(r0, r1)
            goto L71
        L2d:
            r4.consumeToken()
            de.odysseus.el.tree.impl.ast.AstNode r0 = r4.unary(r2)
            de.odysseus.el.tree.impl.ast.AstUnary$Operator r1 = de.odysseus.el.tree.impl.ast.AstUnary.NOT
            de.odysseus.el.tree.impl.ast.AstUnary r0 = r4.createAstUnary(r0, r1)
            goto L71
        L3b:
            r4.consumeToken()
            de.odysseus.el.tree.impl.ast.AstNode r0 = r4.unary(r2)
            de.odysseus.el.tree.impl.ast.AstUnary$Operator r1 = de.odysseus.el.tree.impl.ast.AstUnary.NEG
            de.odysseus.el.tree.impl.ast.AstUnary r0 = r4.createAstUnary(r0, r1)
            goto L71
        L49:
            de.odysseus.el.tree.impl.Scanner$Token r0 = r4.token
            de.odysseus.el.tree.impl.Parser$ExtensionHandler r0 = r4.getExtensionHandler(r0)
            de.odysseus.el.tree.impl.Parser$ExtensionPoint r0 = r0.getExtensionPoint()
            de.odysseus.el.tree.impl.Parser$ExtensionPoint r1 = de.odysseus.el.tree.impl.Parser.ExtensionPoint.UNARY
            if (r0 != r1) goto L6d
            de.odysseus.el.tree.impl.Scanner$Token r0 = r4.consumeToken()
            de.odysseus.el.tree.impl.Parser$ExtensionHandler r0 = r4.getExtensionHandler(r0)
            de.odysseus.el.tree.impl.ast.AstNode r1 = r4.unary(r2)
            de.odysseus.el.tree.impl.ast.AstNode[] r2 = new de.odysseus.el.tree.impl.ast.AstNode[r2]
            r3 = 0
            r2[r3] = r1
            de.odysseus.el.tree.impl.ast.AstNode r0 = r0.createAstNode(r2)
            goto L71
        L6d:
            de.odysseus.el.tree.impl.ast.AstNode r0 = r4.value()
        L71:
            if (r0 != 0) goto L7d
            if (r5 != 0) goto L76
            goto L7d
        L76:
            java.lang.String r5 = de.odysseus.el.tree.impl.Parser.EXPR_FIRST
            r4.fail(r5)
            r5 = 0
            throw r5
        L7d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: de.odysseus.el.tree.impl.Parser.unary(boolean):de.odysseus.el.tree.impl.ast.AstNode");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [de.odysseus.el.tree.impl.ast.AstNode] */
    /* JADX WARN: Type inference failed for: r0v3, types: [de.odysseus.el.tree.impl.ast.AstBracket, de.odysseus.el.tree.impl.ast.AstProperty] */
    /* JADX WARN: Type inference failed for: r0v4, types: [de.odysseus.el.tree.impl.ast.AstMethod] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [de.odysseus.el.tree.impl.ast.AstDot, de.odysseus.el.tree.impl.ast.AstProperty] */
    /* JADX WARN: Type inference failed for: r0v7, types: [de.odysseus.el.tree.impl.ast.AstMethod] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [de.odysseus.el.tree.impl.Parser] */
    public AstNode value() throws Scanner.ScanException, ParseException {
        boolean z;
        ?? CreateAstBracket;
        AstNode astNodeNonliteral = nonliteral();
        if (astNodeNonliteral == null) {
            AstNode astNodeLiteral = literal();
            if (astNodeLiteral == null) {
                return null;
            }
            z = false;
            CreateAstBracket = astNodeLiteral;
        } else {
            z = true;
            CreateAstBracket = astNodeNonliteral;
        }
        while (true) {
            int i = AnonymousClass1.$SwitchMap$de$odysseus$el$tree$impl$Scanner$Symbol[this.token.getSymbol().ordinal()];
            if (i == 17) {
                consumeToken();
                CreateAstBracket = createAstDot(CreateAstBracket, consumeToken(Scanner.Symbol.IDENTIFIER).getImage(), z);
                if (this.token.getSymbol() == Scanner.Symbol.LPAREN && this.context.isEnabled(Builder.Feature.METHOD_INVOCATIONS)) {
                    CreateAstBracket = createAstMethod(CreateAstBracket, params());
                }
            } else {
                if (i != 18) {
                    return CreateAstBracket;
                }
                consumeToken();
                AstNode astNodeExpr = expr(true);
                boolean z2 = !this.context.isEnabled(Builder.Feature.NULL_PROPERTIES);
                consumeToken(Scanner.Symbol.RBRACK);
                CreateAstBracket = createAstBracket(CreateAstBracket, astNodeExpr, z, z2);
                if (this.token.getSymbol() == Scanner.Symbol.LPAREN && this.context.isEnabled(Builder.Feature.METHOD_INVOCATIONS)) {
                    CreateAstBracket = createAstMethod(CreateAstBracket, params());
                }
            }
        }
    }

    public void fail(Scanner.Symbol symbol) throws ParseException {
        fail(symbol.toString());
        throw null;
    }

    public AstEval eval(boolean z, boolean z2) throws Scanner.ScanException, ParseException {
        Scanner.Symbol symbol = z2 ? Scanner.Symbol.START_EVAL_DEFERRED : Scanner.Symbol.START_EVAL_DYNAMIC;
        if (this.token.getSymbol() != symbol) {
            if (z) {
                fail(symbol);
            }
            return null;
        }
        consumeToken();
        AstEval astEval = new AstEval(expr(true), z2);
        consumeToken(Scanner.Symbol.END_EVAL);
        return astEval;
    }

    public final Scanner.Token consumeToken(Scanner.Symbol symbol) throws Scanner.ScanException, ParseException {
        if (this.token.getSymbol() != symbol) {
            fail(symbol);
        }
        return consumeToken();
    }
}
