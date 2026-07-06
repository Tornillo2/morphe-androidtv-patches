package com.amazon.ion.impl;

import com.amazon.ion.IonBinaryWriter;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl.BlockedBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class _Private_IonBinaryWriterImpl extends IonWriterUserBinary implements IonBinaryWriter {
    public _Private_IonBinaryWriterImpl(_Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilder, IonWriterSystemBinary ionWriterSystemBinary) {
        super(_private_ionbinarywriterbuilder, ionWriterSystemBinary);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void addTypeAnnotation(String str) {
        super.addTypeAnnotation(str);
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int byteSize() {
        try {
            finish();
            return getOutputStream().byteSize();
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter, java.io.Flushable
    public /* bridge */ /* synthetic */ void flush() throws IOException {
        super.flush();
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public byte[] getBytes() throws IOException {
        finish();
        return getOutputStream().getBytes();
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.impl._Private_IonWriter
    public IonCatalog getCatalog() {
        return this._catalog;
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.impl._Private_IonWriterBase
    public /* bridge */ /* synthetic */ int getDepth() {
        return super.getDepth();
    }

    public final BlockedBuffer.BufferedOutputStream getOutputStream() {
        return (BlockedBuffer.BufferedOutputStream) ((IonWriterSystemBinary) this._system_writer)._user_output_stream;
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ boolean isInStruct() {
        return super.isInStruct();
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.impl._Private_IonWriter
    public /* bridge */ /* synthetic */ boolean isStreamCopyOptimized() {
        return super.isStreamCopyOptimized();
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void setTypeAnnotationSymbols(SymbolToken[] symbolTokenArr) {
        super.setTypeAnnotationSymbols(symbolTokenArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void setTypeAnnotations(String[] strArr) {
        super.setTypeAnnotations(strArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void stepIn(IonType ionType) throws IOException {
        super.stepIn(ionType);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void stepOut() throws IOException {
        super.stepOut();
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        super.writeBlob(bArr, i, i2);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeBool(boolean z) throws IOException {
        super.writeBool(z);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeBoolList(boolean[] zArr) throws IOException {
        super.writeBoolList(zArr);
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int writeBytes(OutputStream outputStream) throws IOException {
        finish();
        return getOutputStream().writeBytes(outputStream);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeClob(byte[] bArr, int i, int i2) throws IOException {
        super.writeClob(bArr, i, i2);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeDecimal(BigDecimal bigDecimal) throws IOException {
        super.writeDecimal(bigDecimal);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeFloat(double d) throws IOException {
        super.writeFloat(d);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeFloatList(double[] dArr) throws IOException {
        super.writeFloatList(dArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser
    public /* bridge */ /* synthetic */ void writeInt(int i) throws IOException {
        super.writeInt(i);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeIntList(byte[] bArr) throws IOException {
        super.writeIntList(bArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeNull(IonType ionType) throws IOException {
        super.writeNull(ionType);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeString(String str) throws IOException {
        super.writeString(str);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeStringList(String[] strArr) throws IOException {
        super.writeStringList(strArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeTimestamp(Timestamp timestamp) throws IOException {
        super.writeTimestamp(timestamp);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeValue(IonReader ionReader) throws IOException {
        super.writeValue(ionReader);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeFloatList(float[] fArr) throws IOException {
        super.writeFloatList(fArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeInt(long j) throws IOException {
        super.writeInt(j);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeIntList(int[] iArr) throws IOException {
        super.writeIntList(iArr);
    }

    @Override // com.amazon.ion.IonBinaryWriter
    public int getBytes(byte[] bArr, int i, int i2) throws IOException {
        finish();
        return getOutputStream().getBytes(bArr, i, i2);
    }

    @Override // com.amazon.ion.impl.IonWriterUser, com.amazon.ion.IonWriter
    public /* bridge */ /* synthetic */ void writeInt(BigInteger bigInteger) throws IOException {
        super.writeInt(bigInteger);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeIntList(long[] jArr) throws IOException {
        super.writeIntList(jArr);
    }

    @Override // com.amazon.ion.impl.IonWriterUserBinary, com.amazon.ion.impl._Private_ListWriter
    public /* bridge */ /* synthetic */ void writeIntList(short[] sArr) throws IOException {
        super.writeIntList(sArr);
    }
}
