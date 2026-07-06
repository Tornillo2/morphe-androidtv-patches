package com.amazon.ion;

import java.math.BigDecimal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonFloat extends IonValue {
    BigDecimal bigDecimalValue() throws NullValueException;

    @Override // com.amazon.ion.IonValue
    IonFloat clone() throws UnknownSymbolException;

    double doubleValue() throws NullValueException;

    float floatValue() throws NullValueException;

    boolean isNumericValue();

    void setValue(double d);

    void setValue(float f);

    void setValue(BigDecimal bigDecimal);
}
