package com.amazon.ion.impl.lite;

import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonTimestampLite extends IonValueLite implements IonTimestamp {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BIT_FLAG_DAY = 4;
    public static final int BIT_FLAG_FRACTION = 32;
    public static final int BIT_FLAG_MINUTE = 8;
    public static final int BIT_FLAG_MONTH = 2;
    public static final int BIT_FLAG_SECOND = 16;
    public static final int BIT_FLAG_YEAR = 1;
    public Timestamp _timestamp_value;
    public static final Integer UTC_OFFSET = Timestamp.UTC_OFFSET;
    public static final int HASH_SIGNATURE = IonType.TIMESTAMP.toString().hashCode();

    public IonTimestampLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonTimestamp
    public Date dateValue() {
        if (is_true(4)) {
            return null;
        }
        return this._timestamp_value.dateValue();
    }

    @Override // com.amazon.ion.IonTimestamp
    public BigDecimal getDecimalMillis() {
        if (is_true(4)) {
            return null;
        }
        return this._timestamp_value.getDecimalMillis();
    }

    public final Integer getInternalLocalOffset() {
        if (is_true(4)) {
            return null;
        }
        return this._timestamp_value._offset;
    }

    @Override // com.amazon.ion.IonTimestamp
    public Integer getLocalOffset() throws NullValueException {
        if (is_true(4)) {
            throw new NullValueException();
        }
        return this._timestamp_value._offset;
    }

    @Override // com.amazon.ion.IonTimestamp
    public long getMillis() {
        if (is_true(4)) {
            throw new NullValueException();
        }
        return this._timestamp_value.getMillis();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.TIMESTAMP;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!is_true(4)) {
            iHashCode ^= timestampValue().hashCode();
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonTimestamp
    public void makeNull() {
        checkForLock();
        this._timestamp_value = null;
        set_flag(4);
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setCurrentTime() {
        setMillis(System.currentTimeMillis());
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setCurrentTimeUtc() {
        setMillisUtc(System.currentTimeMillis());
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setDecimalMillis(BigDecimal bigDecimal) {
        setValue(bigDecimal, getInternalLocalOffset());
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setLocalOffset(int i) throws NullValueException {
        setLocalOffset(new Integer(i));
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setMillis(long j) {
        setValue(j, getInternalLocalOffset());
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setMillisUtc(long j) {
        setValue(j, UTC_OFFSET);
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setTime(Date date) {
        if (date == null) {
            makeNull();
        } else {
            setMillis(date.getTime());
        }
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setValue(Timestamp timestamp) {
        checkForLock();
        this._timestamp_value = timestamp;
        _isNullValue(timestamp == null);
    }

    @Override // com.amazon.ion.IonTimestamp
    public Timestamp timestampValue() {
        if (is_true(4)) {
            return null;
        }
        return this._timestamp_value;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeTimestamp(this._timestamp_value);
    }

    public IonTimestampLite(IonTimestampLite ionTimestampLite, IonContext ionContext) {
        super(ionTimestampLite, ionContext);
        this._timestamp_value = ionTimestampLite._timestamp_value;
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setLocalOffset(Integer num) throws NullValueException {
        validateThisNotNull();
        setValue(this._timestamp_value.getDecimalMillis(), num);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonTimestampLite clone(IonContext ionContext) {
        return new IonTimestampLite(this, ionContext);
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setValue(BigDecimal bigDecimal, Integer num) {
        setValue(new Timestamp(bigDecimal, num));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite clone(IonContext ionContext) {
        return new IonTimestampLite(this, ionContext);
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setValue(long j, Integer num) {
        setValue(new Timestamp(j, num));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonTimestampLite mo245clone() {
        return new IonTimestampLite(this, ContainerlessContext.wrap(this._context.getSystem()));
    }
}
