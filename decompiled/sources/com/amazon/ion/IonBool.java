package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonBool extends IonValue {
    boolean booleanValue() throws NullValueException;

    @Override // com.amazon.ion.IonValue
    IonBool clone() throws UnknownSymbolException;

    void setValue(Boolean bool);

    void setValue(boolean z);
}
