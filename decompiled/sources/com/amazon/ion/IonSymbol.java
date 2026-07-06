package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonSymbol extends IonText {
    @Override // com.amazon.ion.IonText, com.amazon.ion.IonValue
    IonSymbol clone() throws UnknownSymbolException;

    @Deprecated
    int getSymbolId() throws NullValueException;

    @Override // com.amazon.ion.IonText
    void setValue(String str);

    @Override // com.amazon.ion.IonText
    String stringValue() throws UnknownSymbolException;

    SymbolToken symbolValue();
}
