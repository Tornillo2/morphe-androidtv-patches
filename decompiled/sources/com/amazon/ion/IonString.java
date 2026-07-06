package com.amazon.ion;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonString extends IonText {
    @Override // com.amazon.ion.IonText, com.amazon.ion.IonValue
    IonString clone() throws UnknownSymbolException;

    @Override // com.amazon.ion.IonText
    void setValue(String str);

    @Override // com.amazon.ion.IonText
    String stringValue();
}
