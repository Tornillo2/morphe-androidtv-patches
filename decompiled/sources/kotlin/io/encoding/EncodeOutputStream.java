package kotlin.io.encoding;

import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalEncodingApi
public final class EncodeOutputStream extends OutputStream {

    @NotNull
    public final Base64 base64;

    @NotNull
    public final byte[] byteBuffer;
    public int byteBufferLength;
    public boolean isClosed;
    public int lineLength;

    @NotNull
    public final OutputStream output;

    @NotNull
    public final byte[] symbolBuffer;

    public EncodeOutputStream(@NotNull OutputStream output, @NotNull Base64 base64) {
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(base64, "base64");
        this.output = output;
        this.base64 = base64;
        this.lineLength = base64.isMimeScheme ? 76 : -1;
        this.symbolBuffer = new byte[1024];
        this.byteBuffer = new byte[3];
    }

    public final void checkOpen() throws IOException {
        if (this.isClosed) {
            throw new IOException("The output stream is closed.");
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        if (this.byteBufferLength != 0) {
            encodeByteBufferIntoOutput();
        }
        this.output.close();
    }

    public final int copyIntoByteBuffer(byte[] bArr, int i, int i2) {
        int iMin = Math.min(3 - this.byteBufferLength, i2 - i);
        ArraysKt___ArraysJvmKt.copyInto(bArr, this.byteBuffer, this.byteBufferLength, i, i + iMin);
        int i3 = this.byteBufferLength + iMin;
        this.byteBufferLength = i3;
        if (i3 == 3) {
            encodeByteBufferIntoOutput();
        }
        return iMin;
    }

    public final void encodeByteBufferIntoOutput() {
        if (encodeIntoOutput(this.byteBuffer, 0, this.byteBufferLength) != 4) {
            throw new IllegalStateException("Check failed.");
        }
        this.byteBufferLength = 0;
    }

    public final int encodeIntoOutput(byte[] bArr, int i, int i2) throws IOException {
        int iEncodeIntoByteArray = this.base64.encodeIntoByteArray(bArr, this.symbolBuffer, 0, i, i2);
        if (this.lineLength == 0) {
            OutputStream outputStream = this.output;
            Base64.Default.getClass();
            outputStream.write(Base64.mimeLineSeparatorSymbols);
            this.lineLength = 76;
            if (iEncodeIntoByteArray > 76) {
                throw new IllegalStateException("Check failed.");
            }
        }
        this.output.write(this.symbolBuffer, 0, iEncodeIntoByteArray);
        this.lineLength -= iEncodeIntoByteArray;
        return iEncodeIntoByteArray;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        checkOpen();
        this.output.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        checkOpen();
        byte[] bArr = this.byteBuffer;
        int i2 = this.byteBufferLength;
        int i3 = i2 + 1;
        this.byteBufferLength = i3;
        bArr[i2] = (byte) i;
        if (i3 == 3) {
            encodeByteBufferIntoOutput();
        }
    }

    @Override // java.io.OutputStream
    public void write(@NotNull byte[] source, int i, int i2) throws IOException {
        int i3;
        Intrinsics.checkNotNullParameter(source, "source");
        checkOpen();
        if (i < 0 || i2 < 0 || (i3 = i + i2) > source.length) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("offset: ", i, ", length: ", i2, ", source size: ");
            sbM.append(source.length);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.byteBufferLength;
        if (i4 < 3) {
            if (i4 != 0) {
                i += copyIntoByteBuffer(source, i, i3);
                if (this.byteBufferLength != 0) {
                    return;
                }
            }
            while (i + 3 <= i3) {
                int iMin = Math.min((this.base64.isMimeScheme ? this.lineLength : this.symbolBuffer.length) / 4, (i3 - i) / 3);
                int i5 = (iMin * 3) + i;
                if (encodeIntoOutput(source, i, i5) != iMin * 4) {
                    throw new IllegalStateException("Check failed.");
                }
                i = i5;
            }
            ArraysKt___ArraysJvmKt.copyInto(source, this.byteBuffer, 0, i, i3);
            this.byteBufferLength = i3 - i;
            return;
        }
        throw new IllegalStateException("Check failed.");
    }
}
