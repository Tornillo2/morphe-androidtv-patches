package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.ogg.StreamReader;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class OpusReader extends StreamReader {
    public boolean firstCommentHeaderSeen;
    public static final byte[] OPUS_ID_HEADER_SIGNATURE = {79, IonRawBinaryWriter.SYMBOL_TYPE, 117, 115, 72, 101, 97, DtsUtil.FIRST_BYTE_EXTSS_BE};
    public static final byte[] OPUS_COMMENT_HEADER_SIGNATURE = {79, IonRawBinaryWriter.SYMBOL_TYPE, 117, 115, 84, 97, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 115};

    public static boolean peekPacketStartsWith(ParsableByteArray parsableByteArray, byte[] bArr) {
        if (parsableByteArray.bytesLeft() < bArr.length) {
            return false;
        }
        int i = parsableByteArray.position;
        byte[] bArr2 = new byte[bArr.length];
        parsableByteArray.readBytes(bArr2, 0, bArr.length);
        parsableByteArray.setPosition(i);
        return Arrays.equals(bArr2, bArr);
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE);
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(OpusUtil.getPacketDurationUs(parsableByteArray.data));
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j, StreamReader.SetupData setupData) throws ParserException {
        if (peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE)) {
            byte[] bArrCopyOf = Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit);
            int channelCount = OpusUtil.getChannelCount(bArrCopyOf);
            List<byte[]> listBuildInitializationData = OpusUtil.buildInitializationData(bArrCopyOf);
            if (setupData.format != null) {
                return true;
            }
            Format.Builder builder = new Format.Builder();
            builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/opus");
            builder.channelCount = channelCount;
            builder.sampleRate = 48000;
            builder.initializationData = listBuildInitializationData;
            setupData.format = new Format(builder);
            return true;
        }
        byte[] bArr = OPUS_COMMENT_HEADER_SIGNATURE;
        if (!peekPacketStartsWith(parsableByteArray, bArr)) {
            Assertions.checkStateNotNull(setupData.format);
            return false;
        }
        Assertions.checkStateNotNull(setupData.format);
        if (this.firstCommentHeaderSeen) {
            return true;
        }
        this.firstCommentHeaderSeen = true;
        parsableByteArray.skipBytes(bArr.length);
        Metadata vorbisComments = VorbisUtil.parseVorbisComments(ImmutableList.copyOf(VorbisUtil.readVorbisCommentHeader(parsableByteArray, false, false).comments));
        if (vorbisComments == null) {
            return true;
        }
        Format format = setupData.format;
        format.getClass();
        Format.Builder builder2 = new Format.Builder(format);
        builder2.metadata = vorbisComments.copyWithAppendedEntriesFrom(setupData.format.metadata);
        setupData.format = new Format(builder2);
        return true;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.firstCommentHeaderSeen = false;
        }
    }
}
