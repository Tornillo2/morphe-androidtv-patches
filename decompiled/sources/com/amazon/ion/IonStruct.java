package com.amazon.ion;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonStruct extends IonContainer {
    ValueFactory add(String str);

    void add(SymbolToken symbolToken, IonValue ionValue) throws ContainedValueException;

    void add(String str, IonValue ionValue) throws ContainedValueException;

    @Override // com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    IonStruct clone() throws UnknownSymbolException;

    IonStruct cloneAndRemove(String... strArr) throws UnknownSymbolException;

    IonStruct cloneAndRetain(String... strArr) throws UnknownSymbolException;

    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    IonValue get(String str);

    ValueFactory put(String str);

    void put(String str, IonValue ionValue) throws ContainedValueException;

    void putAll(Map<? extends String, ? extends IonValue> map);

    IonValue remove(String str);

    boolean removeAll(String... strArr);

    boolean retainAll(String... strArr);

    @Override // com.amazon.ion.IonContainer, java.util.List, java.util.Collection
    int size() throws NullValueException;
}
