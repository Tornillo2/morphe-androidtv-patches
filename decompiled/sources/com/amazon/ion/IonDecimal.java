package com.amazon.ion;

import java.math.BigDecimal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonDecimal extends IonNumber {
    BigDecimal bigDecimalValue();

    @Override // com.amazon.ion.IonValue
    IonDecimal clone() throws UnknownSymbolException;

    Decimal decimalValue();

    double doubleValue() throws NullValueException;

    float floatValue() throws NullValueException;

    void setValue(double d);

    void setValue(float f);

    void setValue(long j);

    void setValue(BigDecimal bigDecimal);
}
