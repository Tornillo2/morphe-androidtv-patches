package com.amazon.ion;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonSequence extends IonContainer, List<IonValue> {
    ValueFactory add();

    ValueFactory add(int i);

    void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException;

    boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException;

    boolean addAll(int i, Collection<? extends IonValue> collection);

    @Override // java.util.List, java.util.Collection
    boolean addAll(Collection<? extends IonValue> collection);

    @Override // com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    IonSequence clone() throws UnknownSymbolException;

    @Override // java.util.List, java.util.Collection
    boolean contains(Object obj);

    @Override // java.util.List, java.util.Collection
    boolean containsAll(Collection<?> collection);

    <T extends IonValue> T[] extract(Class<T> cls);

    IonValue get(int i) throws IndexOutOfBoundsException, NullValueException;

    @Override // java.util.List
    int indexOf(Object obj);

    @Override // java.util.List
    int lastIndexOf(Object obj);

    @Override // java.util.List
    ListIterator<IonValue> listIterator();

    @Override // java.util.List
    ListIterator<IonValue> listIterator(int i);

    @Override // java.util.List
    IonValue remove(int i);

    @Override // java.util.List, java.util.Collection
    boolean remove(Object obj);

    @Override // java.util.List, java.util.Collection
    boolean removeAll(Collection<?> collection);

    @Override // java.util.List, java.util.Collection
    boolean retainAll(Collection<?> collection);

    IonValue set(int i, IonValue ionValue);

    @Override // java.util.List
    List<IonValue> subList(int i, int i2);

    @Override // java.util.List, java.util.Collection
    IonValue[] toArray();

    @Override // java.util.List, java.util.Collection
    <T> T[] toArray(T[] tArr);
}
