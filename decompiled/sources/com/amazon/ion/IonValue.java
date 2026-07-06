package com.amazon.ion;

import com.amazon.ion.system.IonTextWriterBuilder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonValue extends Cloneable {
    public static final IonValue[] EMPTY_ARRAY = new IonValue[0];

    void accept(ValueVisitor valueVisitor) throws Exception;

    void addTypeAnnotation(String str);

    void clearTypeAnnotations();

    IonValue clone() throws UnknownSymbolException;

    boolean equals(Object obj);

    IonContainer getContainer();

    @Deprecated
    int getFieldId();

    String getFieldName();

    SymbolToken getFieldNameSymbol();

    SymbolTable getSymbolTable();

    IonSystem getSystem();

    IonType getType();

    SymbolToken[] getTypeAnnotationSymbols();

    String[] getTypeAnnotations();

    boolean hasTypeAnnotation(String str);

    int hashCode();

    boolean isNullValue();

    boolean isReadOnly();

    void makeReadOnly();

    boolean removeFromContainer();

    void removeTypeAnnotation(String str);

    void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr);

    void setTypeAnnotations(String... strArr);

    String toPrettyString();

    String toString();

    String toString(IonTextWriterBuilder ionTextWriterBuilder);

    IonValue topLevelValue();

    void writeTo(IonWriter ionWriter);
}
