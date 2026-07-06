package com.amazon.ion;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonDatagram extends IonSequence {
    @Override // com.amazon.ion.IonSequence
    ValueFactory add(int i) throws ContainedValueException, NullPointerException;

    @Override // com.amazon.ion.IonSequence
    void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException;

    @Override // com.amazon.ion.IonSequence, java.util.List
    boolean addAll(int i, Collection<? extends IonValue> collection);

    @Override // com.amazon.ion.IonValue
    void addTypeAnnotation(String str);

    int byteSize() throws IonException;

    @Override // com.amazon.ion.IonSequence, com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    IonDatagram clone() throws UnknownSymbolException;

    @Override // com.amazon.ion.IonSequence, java.util.List
    IonValue get(int i) throws IndexOutOfBoundsException;

    int getBytes(OutputStream outputStream) throws IOException, IonException;

    @Deprecated
    int getBytes(byte[] bArr) throws IonException;

    @Deprecated
    int getBytes(byte[] bArr, int i) throws IonException;

    byte[] getBytes() throws IonException;

    @Override // com.amazon.ion.IonValue
    IonContainer getContainer();

    @Override // com.amazon.ion.IonValue
    SymbolTable getSymbolTable();

    @Override // com.amazon.ion.IonValue
    boolean isNullValue();

    @Override // com.amazon.ion.IonContainer, java.lang.Iterable
    Iterator<IonValue> iterator();

    @Override // com.amazon.ion.IonContainer
    void makeNull();

    @Override // com.amazon.ion.IonSequence
    IonValue set(int i, IonValue ionValue);

    @Override // com.amazon.ion.IonContainer, java.util.List, java.util.Collection
    int size();

    IonValue systemGet(int i) throws IndexOutOfBoundsException;

    ListIterator<IonValue> systemIterator();

    int systemSize();

    @Deprecated
    byte[] toBytes() throws IonException;
}
