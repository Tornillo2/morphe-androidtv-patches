package com.amazon.ion.impl.lite;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonFloatLite extends IonValueLite implements IonFloat {
    public static final int HASH_SIGNATURE = IonType.FLOAT.toString().hashCode();
    public Double _float_value;

    public IonFloatLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonFloat
    public BigDecimal bigDecimalValue() throws NullValueException {
        if (is_true(4)) {
            return null;
        }
        return Decimal.valueOf(this._float_value.doubleValue());
    }

    @Override // com.amazon.ion.IonFloat
    public double doubleValue() throws NullValueException {
        validateThisNotNull();
        return this._float_value.doubleValue();
    }

    @Override // com.amazon.ion.IonFloat
    public float floatValue() throws NullValueException {
        validateThisNotNull();
        return this._float_value.floatValue();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.FLOAT;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!is_true(4)) {
            long jDoubleToLongBits = Double.doubleToLongBits(doubleValue());
            i ^= (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonFloat
    public boolean isNumericValue() {
        return (is_true(4) || this._float_value.isNaN() || this._float_value.isInfinite()) ? false : true;
    }

    @Override // com.amazon.ion.IonFloat
    public void setValue(float f) {
        setValue(new Double(f));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (is_true(4)) {
            ionWriter.writeNull(IonType.FLOAT);
        } else {
            ionWriter.writeFloat(this._float_value.doubleValue());
        }
    }

    public IonFloatLite(IonFloatLite ionFloatLite, IonContext ionContext) {
        super(ionFloatLite, ionContext);
        this._float_value = ionFloatLite._float_value;
    }

    @Override // com.amazon.ion.IonFloat
    public void setValue(double d) {
        setValue(new Double(d));
    }

    @Override // com.amazon.ion.IonFloat
    public void setValue(BigDecimal bigDecimal) {
        checkForLock();
        if (bigDecimal == null) {
            this._float_value = null;
            set_flag(4);
        } else {
            setValue(bigDecimal.doubleValue());
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonFloatLite clone(IonContext ionContext) {
        return new IonFloatLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonFloatLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonFloatLite mo245clone() {
        return new IonFloatLite(this, ContainerlessContext.wrap(this._context.getSystem()));
    }

    public void setValue(Double d) {
        checkForLock();
        this._float_value = d;
        _isNullValue(d == null);
    }
}
