package com.amazon.ion;

import java.math.BigDecimal;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonTimestamp extends IonValue {
    @Override // com.amazon.ion.IonValue
    IonTimestamp clone() throws UnknownSymbolException;

    Date dateValue();

    BigDecimal getDecimalMillis();

    Integer getLocalOffset() throws NullValueException;

    long getMillis() throws NullValueException;

    void makeNull();

    void setCurrentTime();

    void setCurrentTimeUtc();

    void setDecimalMillis(BigDecimal bigDecimal);

    void setLocalOffset(int i) throws NullValueException;

    void setLocalOffset(Integer num) throws NullValueException;

    void setMillis(long j);

    void setMillisUtc(long j);

    void setTime(Date date);

    void setValue(long j, Integer num);

    void setValue(Timestamp timestamp);

    void setValue(BigDecimal bigDecimal, Integer num);

    Timestamp timestampValue();
}
