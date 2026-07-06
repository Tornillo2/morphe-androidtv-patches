package com.amazon.ion.impl;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonList;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.ValueFactory;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonIteratorImpl implements Iterator<IonValue> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean _at_eof;
    public IonValue _curr;
    public IonValue _next;
    public final IonReader _reader;
    public final ValueFactory _valueFactory;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonIteratorImpl$1, reason: invalid class name */
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

    public IonIteratorImpl(ValueFactory valueFactory, IonReader ionReader) {
        if (valueFactory == null || ionReader == null) {
            throw null;
        }
        this._valueFactory = valueFactory;
        this._reader = ionReader;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this._at_eof) {
            return false;
        }
        return (this._next == null && prefetch() == null) ? false : true;
    }

    public final IonValue prefetch() {
        if (this._reader.next() == null) {
            this._at_eof = true;
        } else {
            this._next = readValue();
        }
        return this._next;
    }

    public final IonValue readValue() {
        IonValue ionValueNewBool;
        IonType type = this._reader.getType();
        SymbolToken[] typeAnnotationSymbols = this._reader.getTypeAnnotationSymbols();
        if (this._reader.isNullValue()) {
            ionValueNewBool = this._valueFactory.newNull(type);
        } else {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[type.ordinal()]) {
                case 1:
                    throw new IllegalStateException();
                case 2:
                    ionValueNewBool = this._valueFactory.newBool(this._reader.booleanValue());
                    break;
                case 3:
                    ionValueNewBool = this._valueFactory.newInt(this._reader.bigIntegerValue());
                    break;
                case 4:
                    ionValueNewBool = this._valueFactory.newFloat(this._reader.doubleValue());
                    break;
                case 5:
                    ionValueNewBool = this._valueFactory.newDecimal(this._reader.decimalValue());
                    break;
                case 6:
                    ionValueNewBool = this._valueFactory.newTimestamp(this._reader.timestampValue());
                    break;
                case 7:
                    ionValueNewBool = this._valueFactory.newString(this._reader.stringValue());
                    break;
                case 8:
                    ionValueNewBool = this._valueFactory.newSymbol(this._reader.symbolValue());
                    break;
                case 9:
                    IonBlob ionBlobNewNullBlob = this._valueFactory.newNullBlob();
                    ionBlobNewNullBlob.setBytes(this._reader.newBytes());
                    ionValueNewBool = ionBlobNewNullBlob;
                    break;
                case 10:
                    IonClob ionClobNewNullClob = this._valueFactory.newNullClob();
                    ionClobNewNullClob.setBytes(this._reader.newBytes());
                    ionValueNewBool = ionClobNewNullClob;
                    break;
                case 11:
                    IonStruct ionStructNewEmptyStruct = this._valueFactory.newEmptyStruct();
                    this._reader.stepIn();
                    while (this._reader.next() != null) {
                        ionStructNewEmptyStruct.add(this._reader.getFieldNameSymbol(), readValue());
                    }
                    this._reader.stepOut();
                    ionValueNewBool = ionStructNewEmptyStruct;
                    break;
                case 12:
                    IonList ionListNewEmptyList = this._valueFactory.newEmptyList();
                    this._reader.stepIn();
                    while (this._reader.next() != null) {
                        ionListNewEmptyList.add(readValue());
                    }
                    this._reader.stepOut();
                    ionValueNewBool = ionListNewEmptyList;
                    break;
                case 13:
                    IonSexp ionSexpNewEmptySexp = this._valueFactory.newEmptySexp();
                    this._reader.stepIn();
                    while (this._reader.next() != null) {
                        ionSexpNewEmptySexp.add(readValue());
                    }
                    this._reader.stepOut();
                    ionValueNewBool = ionSexpNewEmptySexp;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
        _Private_IonValue _private_ionvalue = (_Private_IonValue) ionValueNewBool;
        _private_ionvalue.setSymbolTable(this._reader.getSymbolTable());
        if (typeAnnotationSymbols.length != 0) {
            _private_ionvalue.setTypeAnnotationSymbols(typeAnnotationSymbols);
        }
        return ionValueNewBool;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public IonValue next() {
        if (!this._at_eof) {
            this._curr = null;
            if (this._next == null) {
                prefetch();
            }
            IonValue ionValue = this._next;
            if (ionValue != null) {
                this._curr = ionValue;
                this._next = null;
                return ionValue;
            }
        }
        throw new NoSuchElementException();
    }
}
