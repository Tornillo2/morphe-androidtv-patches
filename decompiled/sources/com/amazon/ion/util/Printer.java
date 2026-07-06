package com.amazon.ion.util;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.SystemSymbols;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_IonSymbol;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.util.IonTextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Printer {
    public Options myOptions;

    /* JADX INFO: renamed from: com.amazon.ion.util.Printer$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[IonTextUtils.SymbolVariant.values().length];
            $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant = iArr2;
            try {
                iArr2[IonTextUtils.SymbolVariant.IDENTIFIER.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.OPERATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.QUOTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BasicSymbolTableProvider implements _Private_IonValue.SymbolTableProvider {
        public final SymbolTable symbolTable;

        public BasicSymbolTableProvider(SymbolTable symbolTable) {
            this.symbolTable = symbolTable;
        }

        @Override // com.amazon.ion.impl._Private_IonValue.SymbolTableProvider
        public SymbolTable getSymbolTable() {
            return this.symbolTable;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class Options implements Cloneable {
        public boolean blobAsString;
        public boolean clobAsString;
        public boolean datagramAsList;
        public boolean decimalAsFloat;
        public boolean sexpAsList;
        public boolean simplifySystemValues;
        public boolean skipAnnotations;
        public boolean skipSystemValues;
        public boolean stringAsJson;
        public boolean symbolAsString;
        public boolean timestampAsMillis;
        public boolean timestampAsString;
        public boolean untypedNulls;

        public Options() {
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Options m246clone() {
            try {
                return (Options) super.clone();
            } catch (CloneNotSupportedException unused) {
                throw new InternalError();
            }
        }
    }

    public Printer() {
        this.myOptions = new Options();
    }

    public final void _print(IonValue ionValue, PrinterVisitor printerVisitor) throws IOException {
        try {
            if (!(ionValue instanceof IonDatagram)) {
                printerVisitor.setSymbolTableProvider(new BasicSymbolTableProvider(ionValue.getSymbolTable()));
            }
            ionValue.accept(printerVisitor);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    public synchronized boolean getPrintBlobAsString() {
        return this.myOptions.blobAsString;
    }

    public synchronized boolean getPrintClobAsString() {
        return this.myOptions.clobAsString;
    }

    public synchronized boolean getPrintDatagramAsList() {
        return this.myOptions.datagramAsList;
    }

    public synchronized boolean getPrintDecimalAsFloat() {
        return this.myOptions.decimalAsFloat;
    }

    public synchronized boolean getPrintSexpAsList() {
        return this.myOptions.sexpAsList;
    }

    public synchronized boolean getPrintStringAsJson() {
        return this.myOptions.stringAsJson;
    }

    public synchronized boolean getPrintSymbolAsString() {
        return this.myOptions.symbolAsString;
    }

    public synchronized boolean getPrintTimestampAsMillis() {
        return this.myOptions.timestampAsMillis;
    }

    public synchronized boolean getPrintTimestampAsString() {
        return this.myOptions.timestampAsString;
    }

    public synchronized boolean getPrintUntypedNulls() {
        return this.myOptions.untypedNulls;
    }

    public synchronized boolean getSkipAnnotations() {
        return this.myOptions.skipAnnotations;
    }

    public synchronized boolean getSkipSystemValues() {
        return this.myOptions.skipSystemValues;
    }

    public PrinterVisitor makeVisitor(Options options, Appendable appendable) {
        return new PrinterVisitor(options, appendable);
    }

    public void print(IonValue ionValue, Appendable appendable) throws IOException {
        Options optionsM246clone;
        synchronized (this) {
            optionsM246clone = this.myOptions.m246clone();
        }
        _print(ionValue, makeVisitor(optionsM246clone, appendable));
    }

    public synchronized void setJsonMode() {
        Options options = this.myOptions;
        options.blobAsString = true;
        options.clobAsString = true;
        options.datagramAsList = true;
        options.decimalAsFloat = true;
        options.sexpAsList = true;
        options.skipAnnotations = true;
        options.skipSystemValues = true;
        options.stringAsJson = true;
        options.symbolAsString = true;
        options.timestampAsString = false;
        options.timestampAsMillis = true;
        options.untypedNulls = true;
    }

    public synchronized void setPrintBlobAsString(boolean z) {
        this.myOptions.blobAsString = z;
    }

    public synchronized void setPrintClobAsString(boolean z) {
        this.myOptions.clobAsString = z;
    }

    public synchronized void setPrintDatagramAsList(boolean z) {
        this.myOptions.datagramAsList = z;
    }

    public synchronized void setPrintDecimalAsFloat(boolean z) {
        this.myOptions.decimalAsFloat = z;
    }

    public synchronized void setPrintSexpAsList(boolean z) {
        this.myOptions.sexpAsList = z;
    }

    public synchronized void setPrintStringAsJson(boolean z) {
        this.myOptions.stringAsJson = z;
    }

    public synchronized void setPrintSymbolAsString(boolean z) {
        this.myOptions.symbolAsString = z;
    }

    public synchronized void setPrintTimestampAsMillis(boolean z) {
        this.myOptions.timestampAsMillis = z;
    }

    public synchronized void setPrintTimestampAsString(boolean z) {
        this.myOptions.timestampAsString = z;
    }

    public synchronized void setPrintUntypedNulls(boolean z) {
        this.myOptions.untypedNulls = z;
    }

    public synchronized void setSkipAnnotations(boolean z) {
        this.myOptions.skipAnnotations = z;
    }

    public synchronized void setSkipSystemValues(boolean z) {
        this.myOptions.skipSystemValues = z;
    }

    public Printer(Options options) {
        this.myOptions = new Options();
        this.myOptions = options.m246clone();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PrinterVisitor extends AbstractValueVisitor {
        public final Options myOptions;
        public final Appendable myOut;
        public boolean myQuoteOperators = true;
        public _Private_IonValue.SymbolTableProvider mySymbolTableProvider = null;

        public PrinterVisitor(Options options, Appendable appendable) {
            this.myOptions = options;
            this.myOut = appendable;
        }

        public static final boolean symbol_table_struct_has_imports(IonValue ionValue) {
            IonValue ionValue2 = ((IonStruct) ionValue).get(SystemSymbols.IMPORTS);
            return (ionValue2 instanceof IonList) && ((IonList) ionValue2).size() != 0;
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor
        public void defaultVisit(IonValue ionValue) {
            throw new UnsupportedOperationException("cannot print ".concat(ionValue.getClass().getName()));
        }

        public void setSymbolTableProvider(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
            this.mySymbolTableProvider = symbolTableProvider;
        }

        public final IonValue simplify(IonValue ionValue, SymbolTable symbolTable) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionValue.getType().ordinal()];
            if (i != 1) {
                if (i != 2 || ((IonSymbol) ionValue).getSymbolId() != 2 || symbolTable == null || !symbolTable.isSystemTable()) {
                    return ionValue;
                }
            } else {
                if (!ionValue.hasTypeAnnotation(SystemSymbols.ION_SYMBOL_TABLE)) {
                    return ionValue;
                }
                if (symbol_table_struct_has_imports(ionValue)) {
                    return ((IonStruct) ionValue).cloneAndRemove(SystemSymbols.SYMBOLS);
                }
            }
            return null;
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonBlob ionBlob) throws IOException {
            writeAnnotations(ionBlob);
            if (ionBlob.isNullValue()) {
                writeNull("blob");
                return;
            }
            Appendable appendable = this.myOut;
            boolean z = this.myOptions.blobAsString;
            String str = ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE;
            appendable.append(z ? ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE : "{{");
            ionBlob.printBase64(this.myOut);
            Appendable appendable2 = this.myOut;
            if (!this.myOptions.blobAsString) {
                str = "}}";
            }
            appendable2.append(str);
        }

        public void writeAnnotations(IonValue ionValue) throws IOException {
            SymbolToken[] typeAnnotationSymbols;
            if (this.myOptions.skipAnnotations || (typeAnnotationSymbols = ((_Private_IonValue) ionValue).getTypeAnnotationSymbols(this.mySymbolTableProvider)) == null) {
                return;
            }
            for (SymbolToken symbolToken : typeAnnotationSymbols) {
                String text = symbolToken.getText();
                if (text == null) {
                    this.myOut.append('$');
                    this.myOut.append(Integer.toString(symbolToken.getSid()));
                } else {
                    IonTextUtils.printSymbol(this.myOut, text);
                }
                this.myOut.append("::");
            }
        }

        public void writeChild(IonValue ionValue, boolean z) throws Exception {
            boolean z2 = this.myQuoteOperators;
            this.myQuoteOperators = z;
            ionValue.accept(this);
            this.myQuoteOperators = z2;
        }

        public void writeNull(String str) throws IOException {
            if (this.myOptions.untypedNulls) {
                this.myOut.append(AbstractJsonLexerKt.NULL);
            } else {
                this.myOut.append("null.");
                this.myOut.append(str);
            }
        }

        public void writeSequenceContent(IonSequence ionSequence, boolean z, char c, char c2, char c3) throws Exception {
            this.myOut.append(c);
            boolean z2 = false;
            for (IonValue ionValue : ionSequence) {
                if (z2) {
                    this.myOut.append(c2);
                }
                writeChild(ionValue, z);
                z2 = true;
            }
            this.myOut.append(c3);
        }

        public void writeString(String str) throws IOException {
            if (this.myOptions.stringAsJson) {
                IonTextUtils.printJsonString(this.myOut, str);
            } else {
                IonTextUtils.printString(this.myOut, str);
            }
        }

        public void writeSymbol(String str) throws IOException {
            if (this.myOptions.symbolAsString) {
                writeString(str);
                return;
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.symbolVariant(str).ordinal()];
            if (i == 1) {
                this.myOut.append(str);
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
            } else if (!this.myQuoteOperators) {
                this.myOut.append(str);
                return;
            }
            IonTextUtils.printQuotedSymbol(this.myOut, str);
        }

        public void writeSymbolToken(SymbolToken symbolToken) throws IOException {
            String text = symbolToken.getText();
            if (text != null) {
                writeSymbol(text);
                return;
            }
            int sid = symbolToken.getSid();
            if (sid < 0) {
                throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Bad SID ", sid));
            }
            String str = "$" + symbolToken.getSid();
            if (this.myOptions.symbolAsString) {
                writeString(str);
            } else {
                this.myOut.append(str);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonBool ionBool) throws IOException {
            writeAnnotations(ionBool);
            if (ionBool.isNullValue()) {
                writeNull("bool");
            } else {
                this.myOut.append(ionBool.booleanValue() ? "true" : "false");
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonClob ionClob) throws IOException {
            writeAnnotations(ionClob);
            if (ionClob.isNullValue()) {
                writeNull("clob");
                return;
            }
            if (!this.myOptions.clobAsString) {
                this.myOut.append("{{");
            }
            this.myOut.append('\"');
            InputStream inputStreamNewInputStream = ionClob.newInputStream();
            try {
                if (!this.myOptions.stringAsJson) {
                    while (true) {
                        int i = inputStreamNewInputStream.read();
                        if (i == -1) {
                            break;
                        } else {
                            IonTextUtils.printStringCodePoint(this.myOut, i);
                        }
                    }
                } else {
                    while (true) {
                        int i2 = inputStreamNewInputStream.read();
                        if (i2 == -1) {
                            break;
                        } else {
                            IonTextUtils.printJsonCodePoint(this.myOut, i2);
                        }
                    }
                }
                inputStreamNewInputStream.close();
                this.myOut.append('\"');
                if (this.myOptions.clobAsString) {
                    return;
                }
                this.myOut.append("}}");
            } catch (Throwable th) {
                inputStreamNewInputStream.close();
                throw th;
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonDatagram ionDatagram) throws Exception {
            Iterator<IonValue> itSystemIterator;
            if (this.myOptions.skipSystemValues) {
                itSystemIterator = ionDatagram.iterator();
            } else {
                itSystemIterator = ionDatagram.systemIterator();
            }
            boolean z = this.myOptions.datagramAsList;
            if (z) {
                this.myOut.append(AbstractJsonLexerKt.BEGIN_LIST);
            }
            Options options = this.myOptions;
            boolean z2 = false;
            boolean z3 = options.simplifySystemValues && !options.skipSystemValues;
            SymbolTable symbolTable = null;
            while (itSystemIterator.hasNext()) {
                IonValue next = itSystemIterator.next();
                SymbolTable symbolTable2 = next.getSymbolTable();
                this.mySymbolTableProvider = new BasicSymbolTableProvider(symbolTable2);
                if (z3) {
                    next = simplify(next, symbolTable);
                    symbolTable = symbolTable2;
                }
                if (next != null) {
                    if (z2) {
                        this.myOut.append(z ? ',' : ' ');
                    }
                    writeChild(next, true);
                    z2 = true;
                }
            }
            if (z) {
                this.myOut.append(AbstractJsonLexerKt.END_LIST);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonDecimal ionDecimal) throws IOException {
            int i;
            int i2;
            writeAnnotations(ionDecimal);
            if (ionDecimal.isNullValue()) {
                writeNull("decimal");
                return;
            }
            Decimal decimalDecimalValue = ionDecimal.decimalValue();
            BigInteger bigIntegerUnscaledValue = decimalDecimalValue.unscaledValue();
            int iSignum = decimalDecimalValue.signum();
            if (iSignum < 0) {
                this.myOut.append('-');
                bigIntegerUnscaledValue = bigIntegerUnscaledValue.negate();
            } else if (iSignum == 0 && decimalDecimalValue.isNegativeZero()) {
                this.myOut.append('-');
            }
            String string = bigIntegerUnscaledValue.toString();
            int length = string.length();
            int iScale = decimalDecimalValue.scale();
            int i3 = -iScale;
            if (this.myOptions.decimalAsFloat) {
                this.myOut.append(string);
                this.myOut.append('e');
                this.myOut.append(Integer.toString(i3));
                return;
            }
            if (i3 == 0) {
                this.myOut.append(string);
                this.myOut.append('.');
                return;
            }
            if (iScale > 0) {
                if (length > iScale) {
                    i2 = length - iScale;
                    i = 0;
                } else {
                    i = (iScale - length) + 1;
                    i2 = 1;
                }
                this.myOut.append(string, 0, i2);
                if (i2 < length) {
                    this.myOut.append('.');
                    this.myOut.append(string, i2, length);
                }
                if (i != 0) {
                    this.myOut.append("d-");
                    this.myOut.append(Integer.toString(i));
                    return;
                }
                return;
            }
            this.myOut.append(string);
            this.myOut.append('d');
            this.myOut.append(Integer.toString(i3));
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonFloat ionFloat) throws IOException {
            writeAnnotations(ionFloat);
            if (ionFloat.isNullValue()) {
                writeNull("float");
            } else {
                IonTextUtils.printFloat(this.myOut, ionFloat.doubleValue());
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonInt ionInt) throws IOException {
            writeAnnotations(ionInt);
            if (ionInt.isNullValue()) {
                writeNull("int");
            } else {
                this.myOut.append(ionInt.bigIntegerValue().toString(10));
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonList ionList) throws Exception {
            writeAnnotations(ionList);
            if (ionList.isNullValue()) {
                writeNull("list");
            } else {
                writeSequenceContent(ionList, true, AbstractJsonLexerKt.BEGIN_LIST, ',', AbstractJsonLexerKt.END_LIST);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonNull ionNull) throws IOException {
            writeAnnotations(ionNull);
            this.myOut.append(AbstractJsonLexerKt.NULL);
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonSexp ionSexp) throws Exception {
            writeAnnotations(ionSexp);
            if (ionSexp.isNullValue()) {
                writeNull("sexp");
            } else if (this.myOptions.sexpAsList) {
                writeSequenceContent(ionSexp, true, AbstractJsonLexerKt.BEGIN_LIST, ',', AbstractJsonLexerKt.END_LIST);
            } else {
                writeSequenceContent(ionSexp, false, '(', ' ', ')');
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonString ionString) throws IOException {
            writeAnnotations(ionString);
            if (ionString.isNullValue()) {
                writeNull("string");
            } else {
                writeString(ionString.stringValue());
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonStruct ionStruct) throws Exception {
            writeAnnotations(ionStruct);
            if (ionStruct.isNullValue()) {
                writeNull("struct");
                return;
            }
            this.myOut.append('{');
            boolean z = false;
            for (IonValue ionValue : ionStruct) {
                if (z) {
                    this.myOut.append(',');
                }
                writeSymbolToken(((_Private_IonValue) ionValue).getFieldNameSymbol(this.mySymbolTableProvider));
                this.myOut.append(':');
                z = true;
                writeChild(ionValue, true);
            }
            this.myOut.append('}');
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonSymbol ionSymbol) throws IOException {
            writeAnnotations(ionSymbol);
            SymbolToken symbolTokenSymbolValue = ((_Private_IonSymbol) ionSymbol).symbolValue(this.mySymbolTableProvider);
            if (symbolTokenSymbolValue == null) {
                writeNull("symbol");
            } else {
                writeSymbolToken(symbolTokenSymbolValue);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonTimestamp ionTimestamp) throws IOException {
            writeAnnotations(ionTimestamp);
            if (ionTimestamp.isNullValue()) {
                writeNull("timestamp");
                return;
            }
            if (this.myOptions.timestampAsMillis) {
                this.myOut.append(Long.toString(ionTimestamp.getMillis()));
                return;
            }
            Timestamp timestampTimestampValue = ionTimestamp.timestampValue();
            if (this.myOptions.timestampAsString) {
                this.myOut.append('\"');
                timestampTimestampValue.print(this.myOut);
                this.myOut.append('\"');
                return;
            }
            timestampTimestampValue.print(this.myOut);
        }
    }
}
