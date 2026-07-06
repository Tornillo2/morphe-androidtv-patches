package com.amazon.ion.impl.lite;

import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonIntLite extends IonValueLite implements IonInt {
    public static final int INT_SIZE_MASK = 24;
    public static final int INT_SIZE_SHIFT = 3;
    public BigInteger _big_int_value;
    public long _long_value;
    public static final BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    public static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    public static final int HASH_SIGNATURE = IonType.INT.toString().hashCode();
    public static final IntegerSize[] SIZES = IntegerSize.values();

    public IonIntLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonInt
    public BigInteger bigIntegerValue() throws NullValueException {
        if (is_true(4)) {
            return null;
        }
        BigInteger bigInteger = this._big_int_value;
        return bigInteger == null ? BigInteger.valueOf(this._long_value) : bigInteger;
    }

    public final void doSetValue(long j, boolean z) {
        this._long_value = j;
        this._big_int_value = null;
        _isNullValue(z);
        if (z) {
            return;
        }
        if (j < -2147483648L || j > 2147483647L) {
            setSize(IntegerSize.LONG);
        } else {
            setSize(IntegerSize.INT);
        }
    }

    @Override // com.amazon.ion.IonInt
    public IntegerSize getIntegerSize() {
        if (is_true(4)) {
            return null;
        }
        return SIZES[_getMetadata(24, 3)];
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.INT;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!is_true(4)) {
            BigInteger bigInteger = this._big_int_value;
            if (bigInteger == null) {
                long jLongValue = longValue();
                iHashCode ^= (int) jLongValue;
                int i = (int) (jLongValue >>> 32);
                if (i != 0 && i != -1) {
                    iHashCode ^= i;
                }
            } else {
                iHashCode = bigInteger.hashCode();
            }
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonInt
    public int intValue() throws NullValueException {
        validateThisNotNull();
        BigInteger bigInteger = this._big_int_value;
        return bigInteger == null ? (int) this._long_value : bigInteger.intValue();
    }

    @Override // com.amazon.ion.IonInt
    public long longValue() throws NullValueException {
        validateThisNotNull();
        BigInteger bigInteger = this._big_int_value;
        return bigInteger == null ? this._long_value : bigInteger.longValue();
    }

    public final void setSize(IntegerSize integerSize) {
        _setMetadata(integerSize.ordinal(), 24, 3);
    }

    @Override // com.amazon.ion.IonInt
    public void setValue(int i) {
        setValue(i);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (is_true(4)) {
            ionWriter.writeNull(IonType.INT);
            return;
        }
        BigInteger bigInteger = this._big_int_value;
        if (bigInteger != null) {
            ionWriter.writeInt(bigInteger);
        } else {
            ionWriter.writeInt(this._long_value);
        }
    }

    public IonIntLite(IonIntLite ionIntLite, IonContext ionContext) {
        super(ionIntLite, ionContext);
        this._long_value = ionIntLite._long_value;
        this._big_int_value = ionIntLite._big_int_value;
    }

    @Override // com.amazon.ion.IonInt
    public void setValue(long j) {
        checkForLock();
        doSetValue(j, false);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonIntLite clone(IonContext ionContext) {
        return new IonIntLite(this, ionContext);
    }

    @Override // com.amazon.ion.IonInt
    public void setValue(Number number) {
        checkForLock();
        if (number == null) {
            doSetValue(0L, true);
            return;
        }
        if (number instanceof BigInteger) {
            doSetValue((BigInteger) number);
        } else if (number instanceof BigDecimal) {
            doSetValue(((BigDecimal) number).toBigInteger());
        } else {
            doSetValue(number.longValue(), false);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonIntLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonIntLite mo245clone() {
        return new IonIntLite(this, ContainerlessContext.wrap(this._context.getSystem()));
    }

    public final void doSetValue(BigInteger bigInteger) {
        if (bigInteger.compareTo(LONG_MIN_VALUE) >= 0 && bigInteger.compareTo(LONG_MAX_VALUE) <= 0) {
            doSetValue(bigInteger.longValue(), false);
            return;
        }
        setSize(IntegerSize.BIG_INTEGER);
        this._long_value = 0L;
        this._big_int_value = bigInteger;
        clear_flag(4);
    }
}
