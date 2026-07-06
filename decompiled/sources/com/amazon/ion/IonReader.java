package com.amazon.ion;

import com.amazon.ion.facet.Faceted;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonReader extends Closeable, Faceted {
    BigDecimal bigDecimalValue();

    BigInteger bigIntegerValue();

    boolean booleanValue();

    int byteSize();

    Date dateValue();

    Decimal decimalValue();

    double doubleValue();

    int getBytes(byte[] bArr, int i, int i2);

    int getDepth();

    @Deprecated
    int getFieldId();

    String getFieldName();

    SymbolToken getFieldNameSymbol();

    IntegerSize getIntegerSize();

    SymbolTable getSymbolTable();

    IonType getType();

    SymbolToken[] getTypeAnnotationSymbols();

    String[] getTypeAnnotations();

    @Deprecated
    boolean hasNext();

    int intValue();

    boolean isInStruct();

    boolean isNullValue();

    Iterator<String> iterateTypeAnnotations();

    long longValue();

    byte[] newBytes();

    IonType next();

    void stepIn();

    void stepOut();

    String stringValue();

    SymbolToken symbolValue();

    Timestamp timestampValue();
}
