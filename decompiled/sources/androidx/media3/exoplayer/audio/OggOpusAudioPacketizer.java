package androidx.media3.exoplayer.audio;

import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.OpusUtil;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class OggOpusAudioPacketizer {
    public static final int CHECKSUM_INDEX = 22;
    public static final int FIRST_AUDIO_SAMPLE_PAGE_SEQUENCE_NUMBER = 2;
    public static final int OGG_PACKET_HEADER_LENGTH = 28;
    public static final int SERIAL_NUMBER = 0;
    public static final byte[] OGG_DEFAULT_ID_HEADER_PAGE = {79, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_GNUTYPE_SPARSE, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Ascii.FS, -43, ExifInterface.MARKER_SOF5, -9, 1, 19, 79, IonRawBinaryWriter.SYMBOL_TYPE, 117, 115, 72, 101, 97, DtsUtil.FIRST_BYTE_EXTSS_BE, 1, 2, 56, 1, -128, -69, 0, 0, 0, 0, 0};
    public static final byte[] OGG_DEFAULT_COMMENT_HEADER_PAGE = {79, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_GNUTYPE_SPARSE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, Ascii.VT, -103, 87, TarConstants.LF_GNUTYPE_SPARSE, 1, 16, 79, IonRawBinaryWriter.SYMBOL_TYPE, 117, 115, 84, 97, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 115, 0, 0, 0, 0, 0, 0, 0, 0};
    public ByteBuffer outputBuffer = AudioProcessor.EMPTY_BUFFER;
    public int granulePosition = 0;
    public int pageSequenceNumber = 2;

    public void packetize(DecoderInputBuffer decoderInputBuffer, List<byte[]> list) {
        decoderInputBuffer.data.getClass();
        if (decoderInputBuffer.data.limit() - decoderInputBuffer.data.position() == 0) {
            return;
        }
        this.outputBuffer = packetizeInternal(decoderInputBuffer.data, (this.pageSequenceNumber == 2 && (list.size() == 1 || list.size() == 3)) ? list.get(0) : null);
        decoderInputBuffer.clear();
        decoderInputBuffer.ensureSpaceForWrite(this.outputBuffer.remaining());
        decoderInputBuffer.data.put(this.outputBuffer);
        decoderInputBuffer.flip();
    }

    public final ByteBuffer packetizeInternal(ByteBuffer byteBuffer, @Nullable byte[] bArr) {
        int i;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i2 = iLimit - iPosition;
        int i3 = (i2 + 255) / 255;
        int length = i3 + 27 + i2;
        if (this.pageSequenceNumber == 2) {
            int length2 = bArr != null ? bArr.length + 28 : OGG_DEFAULT_ID_HEADER_PAGE.length;
            length += OGG_DEFAULT_COMMENT_HEADER_PAGE.length + length2;
            i = length2;
        } else {
            i = 0;
        }
        ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(length);
        if (this.pageSequenceNumber == 2) {
            if (bArr != null) {
                writeOggIdHeaderPage(byteBufferReplaceOutputBuffer, bArr);
            } else {
                byteBufferReplaceOutputBuffer.put(OGG_DEFAULT_ID_HEADER_PAGE);
            }
            byteBufferReplaceOutputBuffer.put(OGG_DEFAULT_COMMENT_HEADER_PAGE);
        }
        int packetAudioSampleCount = this.granulePosition + OpusUtil.parsePacketAudioSampleCount(byteBuffer);
        this.granulePosition = packetAudioSampleCount;
        writeOggPacketHeader(byteBufferReplaceOutputBuffer, packetAudioSampleCount, this.pageSequenceNumber, i3, false);
        for (int i4 = 0; i4 < i3; i4++) {
            if (i2 >= 255) {
                byteBufferReplaceOutputBuffer.put((byte) -1);
                i2 -= 255;
            } else {
                byteBufferReplaceOutputBuffer.put((byte) i2);
                i2 = 0;
            }
        }
        while (iPosition < iLimit) {
            byteBufferReplaceOutputBuffer.put(byteBuffer.get(iPosition));
            iPosition++;
        }
        byteBuffer.position(byteBuffer.limit());
        byteBufferReplaceOutputBuffer.flip();
        if (this.pageSequenceNumber == 2) {
            byte[] bArrArray = byteBufferReplaceOutputBuffer.array();
            int iArrayOffset = byteBufferReplaceOutputBuffer.arrayOffset() + i;
            byte[] bArr2 = OGG_DEFAULT_COMMENT_HEADER_PAGE;
            byteBufferReplaceOutputBuffer.putInt(i + bArr2.length + 22, Util.crc32(bArrArray, iArrayOffset + bArr2.length, byteBufferReplaceOutputBuffer.limit() - byteBufferReplaceOutputBuffer.position(), 0));
        } else {
            byteBufferReplaceOutputBuffer.putInt(22, Util.crc32(byteBufferReplaceOutputBuffer.array(), byteBufferReplaceOutputBuffer.arrayOffset(), byteBufferReplaceOutputBuffer.limit() - byteBufferReplaceOutputBuffer.position(), 0));
        }
        this.pageSequenceNumber++;
        return byteBufferReplaceOutputBuffer;
    }

    public final ByteBuffer replaceOutputBuffer(int i) {
        if (this.outputBuffer.capacity() < i) {
            this.outputBuffer = ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.outputBuffer.clear();
        }
        return this.outputBuffer;
    }

    public void reset() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.granulePosition = 0;
        this.pageSequenceNumber = 2;
    }

    public final void writeOggIdHeaderPage(ByteBuffer byteBuffer, byte[] bArr) {
        writeOggPacketHeader(byteBuffer, 0L, 0, 1, true);
        byteBuffer.put(UnsignedBytes.checkedCast(bArr.length));
        byteBuffer.put(bArr);
        byteBuffer.putInt(22, Util.crc32(byteBuffer.array(), byteBuffer.arrayOffset(), bArr.length + 28, 0));
        byteBuffer.position(bArr.length + 28);
    }

    public final void writeOggPacketHeader(ByteBuffer byteBuffer, long j, int i, int i2, boolean z) {
        byteBuffer.put((byte) 79);
        byteBuffer.put(TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER);
        byteBuffer.put(TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER);
        byteBuffer.put(TarConstants.LF_GNUTYPE_SPARSE);
        byteBuffer.put((byte) 0);
        byteBuffer.put(z ? (byte) 2 : (byte) 0);
        byteBuffer.putLong(j);
        byteBuffer.putInt(0);
        byteBuffer.putInt(i);
        byteBuffer.putInt(0);
        byteBuffer.put(UnsignedBytes.checkedCast(i2));
    }
}
