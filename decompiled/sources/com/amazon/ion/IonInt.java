package com.amazon.ion;

import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonInt extends IonValue {
    BigInteger bigIntegerValue();

    @Override // com.amazon.ion.IonValue
    IonInt clone() throws UnknownSymbolException;

    IntegerSize getIntegerSize();

    int intValue() throws NullValueException;

    long longValue() throws NullValueException;

    void setValue(int i);

    void setValue(long j);

    void setValue(Number number);
}
