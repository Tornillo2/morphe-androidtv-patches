package com.amazon.ion;

import com.amazon.ion.facet.Faceted;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface IonWriter extends Closeable, Flushable, Faceted {
    void addTypeAnnotation(String str);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    void finish() throws IOException;

    @Override // java.io.Flushable
    void flush() throws IOException;

    SymbolTable getSymbolTable();

    boolean isInStruct();

    void setFieldName(String str);

    void setFieldNameSymbol(SymbolToken symbolToken);

    void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr);

    void setTypeAnnotations(String... strArr);

    void stepIn(IonType ionType) throws IOException;

    void stepOut() throws IOException;

    void writeBlob(byte[] bArr) throws IOException;

    void writeBlob(byte[] bArr, int i, int i2) throws IOException;

    void writeBool(boolean z) throws IOException;

    void writeClob(byte[] bArr) throws IOException;

    void writeClob(byte[] bArr, int i, int i2) throws IOException;

    void writeDecimal(BigDecimal bigDecimal) throws IOException;

    void writeFloat(double d) throws IOException;

    void writeInt(long j) throws IOException;

    void writeInt(BigInteger bigInteger) throws IOException;

    void writeNull() throws IOException;

    void writeNull(IonType ionType) throws IOException;

    void writeString(String str) throws IOException;

    void writeSymbol(String str) throws IOException;

    void writeSymbolToken(SymbolToken symbolToken) throws IOException;

    void writeTimestamp(Timestamp timestamp) throws IOException;

    @Deprecated
    void writeTimestampUTC(Date date) throws IOException;

    void writeValue(IonReader ionReader) throws IOException;

    @Deprecated
    void writeValue(IonValue ionValue) throws IOException;

    void writeValues(IonReader ionReader) throws IOException;
}
