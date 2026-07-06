package com.amazon.ion;

import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonList extends IonValue, IonSequence, Collection<IonValue> {
    @Override // com.amazon.ion.IonValue
    IonList clone() throws UnknownSymbolException;
}
