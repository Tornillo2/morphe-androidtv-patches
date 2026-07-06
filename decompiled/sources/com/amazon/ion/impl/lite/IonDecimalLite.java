package com.amazon.ion.impl.lite;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonDecimalLite extends IonValueLite implements IonDecimal {
    public static final int HASH_SIGNATURE = IonType.DECIMAL.toString().hashCode();
    public static final int NEGATIVE_ZERO_HASH_SIGNATURE = -1819393101;
    public BigDecimal _decimal_value;

    public IonDecimalLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    public static boolean isNegativeZero(float f) {
        return f == 0.0f && (Float.floatToRawIntBits(f) & Integer.MIN_VALUE) != 0;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonDecimal
    public BigDecimal bigDecimalValue() throws NullValueException {
        return Decimal.bigDecimalValue(this._decimal_value);
    }

    @Override // com.amazon.ion.IonDecimal
    public Decimal decimalValue() throws NullValueException {
        return Decimal.valueOf(this._decimal_value);
    }

    @Override // com.amazon.ion.IonDecimal
    public double doubleValue() throws NullValueException {
        if (is_true(4)) {
            throw new NullValueException();
        }
        return this._decimal_value.doubleValue();
    }

    @Override // com.amazon.ion.IonDecimal
    public float floatValue() throws NullValueException {
        if (is_true(4)) {
            throw new NullValueException();
        }
        return this._decimal_value.floatValue();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.DECIMAL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!is_true(4)) {
            Decimal decimalValueOf = Decimal.valueOf(this._decimal_value);
            iHashCode ^= decimalValueOf.hashCode();
            if (decimalValueOf.isNegativeZero()) {
                iHashCode ^= NEGATIVE_ZERO_HASH_SIGNATURE;
            }
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(long j) {
        setValue(Decimal.valueOf(j));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeDecimal(this._decimal_value);
    }

    public IonDecimalLite(IonDecimalLite ionDecimalLite, IonContext ionContext) {
        super(ionDecimalLite, ionContext);
        this._decimal_value = ionDecimalLite._decimal_value;
    }

    public static boolean isNegativeZero(double d) {
        return d == 0.0d && (Double.doubleToLongBits(d) & Long.MIN_VALUE) != 0;
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(float f) {
        setValue(Decimal.valueOf(f));
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(double d) {
        setValue(Decimal.valueOf(d));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonDecimalLite clone(IonContext ionContext) {
        return new IonDecimalLite(this, ionContext);
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(BigDecimal bigDecimal) {
        checkForLock();
        this._decimal_value = bigDecimal;
        _isNullValue(bigDecimal == null);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonDecimalLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonDecimalLite mo245clone() {
        return new IonDecimalLite(this, ContainerlessContext.wrap(this._context.getSystem()));
    }
}
