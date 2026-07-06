package com.google.android.exoplayer2.extractor.ogg;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.VorbisUtil;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class VorbisReader extends StreamReader {

    @Nullable
    public VorbisUtil.CommentHeader commentHeader;
    public int previousPacketBlockSize;
    public boolean seenFirstAudioPacket;

    @Nullable
    public VorbisUtil.VorbisIdHeader vorbisIdHeader;

    @Nullable
    public VorbisSetup vorbisSetup;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader, byte[] bArr, VorbisUtil.Mode[] modeArr, int i) {
            this.idHeader = vorbisIdHeader;
            this.commentHeader = commentHeader;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i;
        }
    }

    @VisibleForTesting
    public static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j) {
        byte[] bArr = parsableByteArray.data;
        int length = bArr.length;
        int i = parsableByteArray.limit;
        if (length < i + 4) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, i + 4);
            parsableByteArray.reset(bArrCopyOf, bArrCopyOf.length);
        } else {
            parsableByteArray.setLimit(i + 4);
        }
        byte[] bArr2 = parsableByteArray.data;
        int i2 = parsableByteArray.limit;
        bArr2[i2 - 4] = (byte) (j & 255);
        bArr2[i2 - 3] = (byte) ((j >>> 8) & 255);
        bArr2[i2 - 2] = (byte) ((j >>> 16) & 255);
        bArr2[i2 - 1] = (byte) ((j >>> 24) & 255);
    }

    public static int decodeBlockSize(byte b, VorbisSetup vorbisSetup) {
        return !vorbisSetup.modes[readBits(b, vorbisSetup.iLogModes, 1)].blockFlag ? vorbisSetup.idHeader.blockSize0 : vorbisSetup.idHeader.blockSize1;
    }

    @VisibleForTesting
    public static int readBits(byte b, int i, int i2) {
        return (b >> i2) & (255 >>> (8 - i));
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void onSeekEnd(long j) {
        this.currentGranule = j;
        this.seenFirstAudioPacket = j != 0;
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.vorbisIdHeader;
        this.previousPacketBlockSize = vorbisIdHeader != null ? vorbisIdHeader.blockSize0 : 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        byte b = parsableByteArray.data[0];
        if ((b & 1) == 1) {
            return -1L;
        }
        VorbisSetup vorbisSetup = this.vorbisSetup;
        Assertions.checkStateNotNull(vorbisSetup);
        int iDecodeBlockSize = decodeBlockSize(b, vorbisSetup);
        long j = this.seenFirstAudioPacket ? (this.previousPacketBlockSize + iDecodeBlockSize) / 4 : 0;
        appendNumberOfSamples(parsableByteArray, j);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = iDecodeBlockSize;
        return j;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j, StreamReader.SetupData setupData) throws IOException {
        if (this.vorbisSetup != null) {
            setupData.format.getClass();
            return false;
        }
        VorbisSetup setupHeaders = readSetupHeaders(parsableByteArray);
        this.vorbisSetup = setupHeaders;
        if (setupHeaders == null) {
            return true;
        }
        VorbisUtil.VorbisIdHeader vorbisIdHeader = setupHeaders.idHeader;
        ArrayList arrayList = new ArrayList();
        arrayList.add(vorbisIdHeader.data);
        arrayList.add(setupHeaders.setupHeaderData);
        Metadata vorbisComments = VorbisUtil.parseVorbisComments(ImmutableList.copyOf(setupHeaders.commentHeader.comments));
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = "audio/vorbis";
        builder.averageBitrate = vorbisIdHeader.bitrateNominal;
        builder.peakBitrate = vorbisIdHeader.bitrateMaximum;
        builder.channelCount = vorbisIdHeader.channels;
        builder.sampleRate = vorbisIdHeader.sampleRate;
        builder.initializationData = arrayList;
        builder.metadata = vorbisComments;
        setupData.format = new Format(builder);
        return true;
    }

    @Nullable
    @VisibleForTesting
    public VorbisSetup readSetupHeaders(ParsableByteArray parsableByteArray) throws IOException {
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.vorbisIdHeader;
        if (vorbisIdHeader == null) {
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            return null;
        }
        VorbisUtil.CommentHeader commentHeader = this.commentHeader;
        if (commentHeader == null) {
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray, true, true);
            return null;
        }
        int i = parsableByteArray.limit;
        byte[] bArr = new byte[i];
        System.arraycopy(parsableByteArray.data, 0, bArr, 0, i);
        VorbisUtil.Mode[] vorbisModes = VorbisUtil.readVorbisModes(parsableByteArray, vorbisIdHeader.channels);
        return new VorbisSetup(vorbisIdHeader, commentHeader, bArr, vorbisModes, VorbisUtil.iLog(vorbisModes.length - 1));
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.vorbisSetup = null;
            this.vorbisIdHeader = null;
            this.commentHeader = null;
        }
        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }
}
