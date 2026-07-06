package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.Decimal;
import com.amazon.ion.IonList;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_LocalSymbolTableFactory;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl._Private_ValueFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ValueFactoryLite implements _Private_ValueFactory {
    public ContainerlessContext _context;
    public final _Private_LocalSymbolTableFactory _lstFactory = _Private_Utils.newLocalSymbolTableAsStructFactory(this);

    /* JADX INFO: renamed from: com.amazon.ion.impl.lite.ValueFactoryLite$1, reason: invalid class name */
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
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    @Override // com.amazon.ion.impl._Private_ValueFactory
    public _Private_LocalSymbolTableFactory getLstFactory() {
        return this._lstFactory;
    }

    public final ArrayList<IonIntLite> newInts(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        ArrayList<IonIntLite> arrayList = new ArrayList<>(iArr.length);
        for (int i : iArr) {
            arrayList.add(newInt(i));
        }
        return arrayList;
    }

    public void set_system(IonSystemLite ionSystemLite) {
        this._context = ContainerlessContext.wrap(ionSystemLite);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newEmptyList() {
        return new IonListLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newEmptySexp() {
        return new IonSexpLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStructLite newEmptyStruct() {
        return new IonStructLite(this._context, false);
    }

    @Override // com.amazon.ion.ValueFactory
    public /* bridge */ /* synthetic */ IonList newList(Collection collection) throws ContainedValueException, NullPointerException {
        return newList((Collection<? extends IonValue>) collection);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlobLite newNullBlob() {
        return new IonBlobLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBoolLite newNullBool() {
        return new IonBoolLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClobLite newNullClob() {
        return new IonClobLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newNullDecimal() {
        return new IonDecimalLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloatLite newNullFloat() {
        return new IonFloatLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newNullInt() {
        return new IonIntLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newNullList() {
        return new IonListLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newNullSexp() {
        return new IonSexpLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStringLite newNullString() {
        return new IonStringLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStructLite newNullStruct() {
        return new IonStructLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbolLite newNullSymbol() {
        return new IonSymbolLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestampLite newNullTimestamp() {
        return new IonTimestampLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public /* bridge */ /* synthetic */ IonSexp newSexp(Collection collection) throws ContainedValueException, NullPointerException {
        return newSexp((Collection<? extends IonValue>) collection);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStringLite newString(String str) {
        IonStringLite ionStringLite = new IonStringLite(this._context, str == null);
        if (str != null) {
            ionStringLite.setValue(str);
        }
        return ionStringLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestampLite newTimestamp(Timestamp timestamp) {
        IonTimestampLite ionTimestampLite = new IonTimestampLite(this._context, timestamp == null);
        if (timestamp != null) {
            ionTimestampLite.setValue(timestamp);
        }
        return ionTimestampLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlobLite newBlob(byte[] bArr) {
        return newBlob(bArr, 0, bArr == null ? 0 : bArr.length);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBoolLite newBool(boolean z) {
        IonBoolLite ionBoolLite = new IonBoolLite(this._context, false);
        ionBoolLite.setValue(z);
        return ionBoolLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClobLite newClob(byte[] bArr) {
        return newClob(bArr, 0, bArr == null ? 0 : bArr.length);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloatLite newFloat(long j) {
        IonFloatLite ionFloatLite = new IonFloatLite(this._context, false);
        ionFloatLite.setValue(j);
        return ionFloatLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonNullLite newNull() {
        return new IonNullLite(this._context, true);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbolLite newSymbol(String str) {
        IonSymbolLite ionSymbolLite = new IonSymbolLite(this._context, str == null);
        if (str != null) {
            ionSymbolLite.setValue(str);
        }
        return ionSymbolLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlobLite newBlob(byte[] bArr, int i, int i2) {
        IonBlobLite ionBlobLite = new IonBlobLite(this._context, bArr == null);
        ionBlobLite.setBytes(bArr, i, i2);
        return ionBlobLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClobLite newClob(byte[] bArr, int i, int i2) {
        IonClobLite ionClobLite = new IonClobLite(this._context, bArr == null);
        ionClobLite.setBytes(bArr, i, i2);
        return ionClobLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(int i) {
        IonIntLite ionIntLite = new IonIntLite(this._context, false);
        ionIntLite.setValue(i);
        return ionIntLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newDecimal(long j) {
        IonDecimalLite ionDecimalLite = new IonDecimalLite(this._context, false);
        ionDecimalLite.setValue(j);
        return ionDecimalLite;
    }

    public final ArrayList<IonIntLite> newInts(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        ArrayList<IonIntLite> arrayList = new ArrayList<>(jArr.length);
        for (long j : jArr) {
            arrayList.add(newInt(j));
        }
        return arrayList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonValueLite newNull(IonType ionType) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) {
            case 1:
                return newNull();
            case 2:
                return newNullBool();
            case 3:
                return newNullInt();
            case 4:
                return newNullFloat();
            case 5:
                return newNullDecimal();
            case 6:
                return newNullTimestamp();
            case 7:
                return newNullSymbol();
            case 8:
                return newNullString();
            case 9:
                return newNullClob();
            case 10:
                return newNullBlob();
            case 11:
                return newNullList();
            case 12:
                return newNullSexp();
            case 13:
                return newNullStruct();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newSexp(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException {
        IonSexpLite ionSexpLiteNewEmptySexp = newEmptySexp();
        if (collection == null) {
            ionSexpLiteNewEmptySexp.makeNull();
            return ionSexpLiteNewEmptySexp;
        }
        ionSexpLiteNewEmptySexp.addAll(collection);
        return ionSexpLiteNewEmptySexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbolLite newSymbol(SymbolToken symbolToken) {
        return new IonSymbolLite(this._context, symbolToken);
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBoolLite newBool(Boolean bool) {
        IonBoolLite ionBoolLite = new IonBoolLite(this._context, bool == null);
        ionBoolLite.setValue(bool);
        return ionBoolLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloatLite newFloat(double d) {
        IonFloatLite ionFloatLite = new IonFloatLite(this._context, false);
        ionFloatLite.setValue(d);
        return ionFloatLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newList(Collection<? extends IonValue> collection) throws ContainedValueException, NullPointerException {
        IonListLite ionListLiteNewEmptyList = newEmptyList();
        if (collection == null) {
            ionListLiteNewEmptyList.makeNull();
            return ionListLiteNewEmptyList;
        }
        ionListLiteNewEmptyList.addAll(collection);
        return ionListLiteNewEmptyList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(long j) {
        IonIntLite ionIntLite = new IonIntLite(this._context, false);
        ionIntLite.setValue(j);
        return ionIntLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newDecimal(double d) {
        IonDecimalLite ionDecimalLite = new IonDecimalLite(this._context, false);
        ionDecimalLite.setValue(d);
        return ionDecimalLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newSexp(IonSequence ionSequence) throws ContainedValueException, NullPointerException {
        IonSexpLite ionSexpLiteNewEmptySexp = newEmptySexp();
        ionSexpLiteNewEmptySexp.add((IonValue) ionSequence);
        return ionSexpLiteNewEmptySexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newList(IonSequence ionSequence) throws ContainedValueException, NullPointerException {
        IonListLite ionListLiteNewEmptyList = newEmptyList();
        ionListLiteNewEmptyList.add((IonValue) ionSequence);
        return ionListLiteNewEmptyList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonIntLite newInt(Number number) {
        IonIntLite ionIntLite = new IonIntLite(this._context, number == null);
        if (number != null) {
            ionIntLite.setValue(number);
        }
        return ionIntLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newSexp(IonValue... ionValueArr) throws ContainedValueException, NullPointerException {
        List listAsList = ionValueArr == null ? null : Arrays.asList(ionValueArr);
        IonSexpLite ionSexpLiteNewEmptySexp = newEmptySexp();
        if (listAsList == null) {
            ionSexpLiteNewEmptySexp.makeNull();
            return ionSexpLiteNewEmptySexp;
        }
        ionSexpLiteNewEmptySexp.addAll(listAsList);
        return ionSexpLiteNewEmptySexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newDecimal(BigInteger bigInteger) {
        IonDecimalLite ionDecimalLite = new IonDecimalLite(this._context, bigInteger == null);
        if (bigInteger != null) {
            ionDecimalLite.setValue(Decimal.valueOf(bigInteger));
        }
        return ionDecimalLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newList(IonValue... ionValueArr) throws ContainedValueException, NullPointerException {
        List listAsList = ionValueArr == null ? null : Arrays.asList(ionValueArr);
        IonListLite ionListLiteNewEmptyList = newEmptyList();
        if (listAsList == null) {
            ionListLiteNewEmptyList.makeNull();
            return ionListLiteNewEmptyList;
        }
        ionListLiteNewEmptyList.addAll(listAsList);
        return ionListLiteNewEmptyList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimalLite newDecimal(BigDecimal bigDecimal) {
        IonDecimalLite ionDecimalLite = new IonDecimalLite(this._context, bigDecimal == null);
        if (bigDecimal != null) {
            ionDecimalLite.setValue(bigDecimal);
        }
        return ionDecimalLite;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newSexp(int[] iArr) {
        return newSexp((Collection<? extends IonValue>) newInts(iArr));
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newList(int[] iArr) {
        return newList((Collection<? extends IonValue>) newInts(iArr));
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexpLite newSexp(long[] jArr) {
        return newSexp((Collection<? extends IonValue>) newInts(jArr));
    }

    @Override // com.amazon.ion.ValueFactory
    public IonListLite newList(long[] jArr) {
        return newList((Collection<? extends IonValue>) newInts(jArr));
    }
}
