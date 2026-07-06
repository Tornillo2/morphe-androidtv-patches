package com.amazon.ion.impl;

import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class _Private_IonWriterBase implements IonWriter, _Private_ReaderWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String ERROR_FINISH_NOT_AT_TOP_LEVEL = "IonWriter.finish() can only be called at top-level.";
    public static final String ERROR_MISSING_FIELD_NAME = "IonWriter.setFieldName() must be called before writing a value into a struct.";
    public int _symbol_table_top = 0;
    public SymbolTable[] _symbol_table_stack = new SymbolTable[3];

    /* JADX INFO: renamed from: com.amazon.ion.impl._Private_IonWriterBase$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private void clear_system_value_stack() {
        while (true) {
            int i = this._symbol_table_top;
            if (i <= 0) {
                return;
            }
            int i2 = i - 1;
            this._symbol_table_top = i2;
            this._symbol_table_stack[i2] = null;
        }
    }

    private void push_symbol_table(SymbolTable symbolTable) {
        int i = this._symbol_table_top;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        if (i >= symbolTableArr.length) {
            SymbolTable[] symbolTableArr2 = new SymbolTable[symbolTableArr.length * 2];
            System.arraycopy(symbolTableArr, 0, symbolTableArr2, 0, symbolTableArr.length);
            this._symbol_table_stack = symbolTableArr2;
        }
        SymbolTable[] symbolTableArr3 = this._symbol_table_stack;
        int i2 = this._symbol_table_top;
        this._symbol_table_top = i2 + 1;
        symbolTableArr3[i2] = symbolTable;
    }

    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    public abstract String assumeKnownSymbol(int i);

    public abstract int findAnnotation(String str);

    public abstract int getDepth();

    public abstract int[] getTypeAnnotationIds();

    public abstract String[] getTypeAnnotations();

    public abstract boolean isFieldNameSet();

    public boolean isStreamCopyOptimized() {
        return false;
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public final SymbolTable pop_passed_symbol_table() {
        int i = this._symbol_table_top;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        this._symbol_table_top = i2;
        SymbolTable[] symbolTableArr = this._symbol_table_stack;
        SymbolTable symbolTable = symbolTableArr[i2];
        symbolTableArr[i2] = null;
        return symbolTable;
    }

    public abstract void setSymbolTable(SymbolTable symbolTable) throws IOException;

    public final void transfer_symbol_tables(_Private_ReaderWriter _private_readerwriter) throws IOException {
        SymbolTable symbolTablePop_passed_symbol_table = _private_readerwriter.pop_passed_symbol_table();
        if (symbolTablePop_passed_symbol_table != null) {
            clear_system_value_stack();
            setSymbolTable(symbolTablePop_passed_symbol_table);
            while (symbolTablePop_passed_symbol_table != null) {
                push_symbol_table(symbolTablePop_passed_symbol_table);
                symbolTablePop_passed_symbol_table = _private_readerwriter.pop_passed_symbol_table();
            }
        }
    }

    public final void validateSymbolId(int i) {
        if (i > getSymbolTable().getMaxId()) {
            throw new UnknownSymbolException(i);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr) throws IOException {
        if (bArr == null) {
            writeNull(IonType.BLOB);
        } else {
            writeBlob(bArr, 0, bArr.length);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr) throws IOException {
        if (bArr == null) {
            writeNull(IonType.CLOB);
        } else {
            writeClob(bArr, 0, bArr.length);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public abstract void writeDecimal(BigDecimal bigDecimal) throws IOException;

    public void writeFloat(float f) throws IOException {
        writeFloat(f);
    }

    public abstract void writeIonVersionMarker() throws IOException;

    @Override // com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        writeNull(IonType.NULL);
    }

    public abstract void writeSymbol(int i) throws IOException;

    @Override // com.amazon.ion.IonWriter
    public final void writeSymbolToken(SymbolToken symbolToken) throws IOException {
        if (symbolToken == null) {
            writeNull(IonType.SYMBOL);
            return;
        }
        String text = symbolToken.getText();
        if (text != null) {
            writeSymbol(text);
            return;
        }
        int sid = symbolToken.getSid();
        validateSymbolId(sid);
        writeSymbol(sid);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestampUTC(Date date) throws IOException {
        writeTimestamp(Timestamp.forDateZ(date));
    }

    @Override // com.amazon.ion.IonWriter
    @Deprecated
    public void writeValue(IonValue ionValue) throws IOException {
        if (ionValue != null) {
            ionValue.writeTo(this);
        }
    }

    public final void writeValueRecursively(IonReader ionReader) throws IOException {
        boolean z;
        IonType next;
        int depth = getDepth();
        boolean z2 = false;
        while (true) {
            if (getDepth() != depth) {
                z = z2;
                next = ionReader.next();
            } else {
                if (z2) {
                    return;
                }
                next = ionReader.getType();
                z = true;
            }
            if (next != null) {
                write_value_field_name_helper(ionReader);
                write_value_annotations_helper(ionReader);
                if (ionReader.isNullValue()) {
                    writeNull(next);
                } else {
                    switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[next.ordinal()]) {
                        case 1:
                            throw new IllegalStateException("isNullValue() was false but IonType was NULL.");
                        case 2:
                            writeBool(ionReader.booleanValue());
                            break;
                        case 3:
                            writeInt(ionReader.bigIntegerValue());
                            break;
                        case 4:
                            writeFloat(ionReader.doubleValue());
                            break;
                        case 5:
                            writeDecimal(ionReader.decimalValue());
                            break;
                        case 6:
                            writeTimestamp(ionReader.timestampValue());
                            break;
                        case 7:
                            writeString(ionReader.stringValue());
                            break;
                        case 8:
                            writeSymbolToken(ionReader.symbolValue());
                            break;
                        case 9:
                            writeBlob(ionReader.newBytes());
                            break;
                        case 10:
                            writeClob(ionReader.newBytes());
                            break;
                        case 11:
                        case 12:
                        case 13:
                            ionReader.stepIn();
                            stepIn(next);
                            break;
                        default:
                            throw new IllegalStateException("Unknown value type: " + next);
                    }
                }
            } else {
                if (getDepth() == depth) {
                    return;
                }
                ionReader.stepOut();
                stepOut();
            }
            z2 = z;
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeValues(IonReader ionReader) throws IOException {
        if (ionReader.getDepth() == 0) {
            clear_system_value_stack();
        }
        if (ionReader.getType() == null) {
            ionReader.next();
        }
        if (getDepth() != 0 || !(ionReader instanceof _Private_ReaderWriter)) {
            while (ionReader.getType() != null) {
                writeValue(ionReader);
                ionReader.next();
            }
        } else {
            _Private_ReaderWriter _private_readerwriter = (_Private_ReaderWriter) ionReader;
            while (ionReader.getType() != null) {
                transfer_symbol_tables(_private_readerwriter);
                writeValue(ionReader);
                ionReader.next();
            }
        }
    }

    public final void write_value_annotations_helper(IonReader ionReader) {
        setTypeAnnotationSymbols(ionReader.getTypeAnnotationSymbols());
    }

    public final void write_value_field_name_helper(IonReader ionReader) {
        if (!isInStruct() || isFieldNameSet()) {
            return;
        }
        SymbolToken fieldNameSymbol = ionReader.getFieldNameSymbol();
        if (fieldNameSymbol == null) {
            throw new IllegalStateException("Field name not set");
        }
        setFieldNameSymbol(fieldNameSymbol);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeValue(IonReader ionReader) throws IOException {
        writeValueRecursively(ionReader);
    }
}
