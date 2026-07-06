package com.amazon.ion;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonContainer extends IonValue, Iterable<IonValue> {
    void clear();

    @Override // com.amazon.ion.IonValue
    IonContainer clone() throws UnknownSymbolException;

    boolean isEmpty() throws NullValueException;

    @Override // java.lang.Iterable
    Iterator<IonValue> iterator();

    void makeNull();

    boolean remove(IonValue ionValue);

    int size();
}
