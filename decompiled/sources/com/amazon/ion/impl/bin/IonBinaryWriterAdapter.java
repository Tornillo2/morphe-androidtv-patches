package com.amazon.ion.impl.bin;

import com.amazon.ion.IonBinaryWriter;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class IonBinaryWriterAdapter implements IonBinaryWriter {
    public final InternalByteArrayOutputStream buffer;
    public final IonWriter delegate;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        IonWriter create(OutputStream outputStream) throws IOException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InternalByteArrayOutputStream extends ByteArrayOutputStream {
        public InternalByteArrayOutputStream() {
        }

        public byte[] bytes() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public InternalByteArrayOutputStream(AnonymousClass1 anonymousClass1) {
        }
    }

    public IonBinaryWriterAdapter(Factory factory) throws IOException {
        InternalByteArrayOutputStream internalByteArrayOutputStream = new InternalByteArrayOutputStream();
        this.buffer = internalByteArrayOutputStream;
        this.delegate = factory.create(internalByteArrayOutputStream);
    }

    @Override // com.amazon.ion.IonWriter
    public void addTypeAnnotation(String str) {
        this.delegate.addTypeAnnotation(str);
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int byteSize() {
        return this.buffer.size();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // com.amazon.ion.IonWriter
    public void finish() throws IOException {
        this.delegate.finish();
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public byte[] getBytes() throws IOException {
        return this.buffer.toByteArray();
    }

    public IonWriter getDelegate() {
        return this.delegate;
    }

    @Override // com.amazon.ion.IonWriter
    public SymbolTable getSymbolTable() {
        return this.delegate.getSymbolTable();
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this.delegate.isInStruct();
    }

    public void reset() {
        this.buffer.reset();
    }

    @Override // com.amazon.ion.IonWriter
    public void setFieldName(String str) {
        this.delegate.setFieldName(str);
    }

    @Override // com.amazon.ion.IonWriter
    public void setFieldNameSymbol(SymbolToken symbolToken) {
        this.delegate.setFieldNameSymbol(symbolToken);
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        this.delegate.setTypeAnnotationSymbols(symbolTokenArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotations(String... strArr) {
        this.delegate.setTypeAnnotations(strArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        this.delegate.stepIn(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        this.delegate.stepOut();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr) throws IOException {
        this.delegate.writeBlob(bArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this.delegate.writeBool(z);
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int writeBytes(OutputStream outputStream) throws IOException {
        this.buffer.writeTo(outputStream);
        return this.buffer.size();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr) throws IOException {
        this.delegate.writeClob(bArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this.delegate.writeDecimal(bigDecimal);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this.delegate.writeFloat(d);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this.delegate.writeInt(j);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        this.delegate.writeNull();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this.delegate.writeString(str);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbol(String str) throws IOException {
        this.delegate.writeSymbol(str);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbolToken(SymbolToken symbolToken) throws IOException {
        this.delegate.writeSymbolToken(symbolToken);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this.delegate.writeTimestamp(timestamp);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestampUTC(Date date) throws IOException {
        this.delegate.writeTimestampUTC(date);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeValue(IonValue ionValue) throws IOException {
        this.delegate.writeValue(ionValue);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeValues(IonReader ionReader) throws IOException {
        this.delegate.writeValues(ionReader);
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int getBytes(byte[] bArr, int i, int i2) throws IOException {
        int iMin = Math.min(i2, this.buffer.size());
        System.arraycopy(this.buffer.bytes(), 0, bArr, i, iMin);
        return iMin;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this.delegate.writeBlob(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        this.delegate.writeClob(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this.delegate.writeInt(bigInteger);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this.delegate.writeNull(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeValue(IonReader ionReader) throws IOException {
        this.delegate.writeValue(ionReader);
    }
}
