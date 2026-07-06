package androidx.media3.extractor.ts;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.primitives.Ints;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DtsReader implements ElementaryStreamReader {
    public static final int CORE_HEADER_SIZE = 18;
    public static final int EXTSS_HEADER_SIZE_MAX = 4096;
    public static final int FTOC_MAX_HEADER_SIZE = 5408;
    public static final int STATE_FINDING_EXTSS_HEADER_SIZE = 2;
    public static final int STATE_FINDING_SYNC = 0;
    public static final int STATE_FINDING_UHD_HEADER_SIZE = 4;
    public static final int STATE_READING_CORE_HEADER = 1;
    public static final int STATE_READING_EXTSS_HEADER = 3;
    public static final int STATE_READING_SAMPLE = 6;
    public static final int STATE_READING_UHD_HEADER = 5;
    public int bytesRead;
    public Format format;
    public String formatId;
    public int frameType;
    public final ParsableByteArray headerScratchBytes;

    @Nullable
    public final String language;
    public TrackOutput output;
    public final int roleFlags;
    public long sampleDurationUs;
    public int sampleSize;
    public int syncBytes;
    public int state = 0;
    public long timeUs = -9223372036854775807L;
    public final AtomicInteger uhdAudioChunkId = new AtomicInteger();
    public int extensionSubstreamHeaderSize = -1;
    public int uhdHeaderSize = -1;

    public DtsReader(@Nullable String str, int i, int i2) {
        this.headerScratchBytes = new ParsableByteArray(new byte[i2]);
        this.language = str;
        this.roleFlags = i;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i2 = this.bytesRead + iMin;
        this.bytesRead = i2;
        return i2 == i;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            switch (this.state) {
                case 0:
                    if (skipToNextSyncWord(parsableByteArray)) {
                        int i = this.frameType;
                        if (i == 3 || i == 4) {
                            this.state = 4;
                        } else if (i != 1) {
                            this.state = 2;
                        } else {
                            this.state = 1;
                        }
                    }
                    break;
                case 1:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.data, 18)) {
                        parseCoreHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, 18);
                        this.state = 6;
                    }
                    break;
                case 2:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.data, 7)) {
                        this.extensionSubstreamHeaderSize = DtsUtil.parseDtsHdHeaderSize(this.headerScratchBytes.data);
                        this.state = 3;
                    }
                    break;
                case 3:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.data, this.extensionSubstreamHeaderSize)) {
                        parseExtensionSubstreamHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.extensionSubstreamHeaderSize);
                        this.state = 6;
                    }
                    break;
                case 4:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.data, 6)) {
                        int dtsUhdHeaderSize = DtsUtil.parseDtsUhdHeaderSize(this.headerScratchBytes.data);
                        this.uhdHeaderSize = dtsUhdHeaderSize;
                        int i2 = this.bytesRead;
                        if (i2 > dtsUhdHeaderSize) {
                            int i3 = i2 - dtsUhdHeaderSize;
                            this.bytesRead = i2 - i3;
                            parsableByteArray.setPosition(parsableByteArray.position - i3);
                        }
                        this.state = 5;
                    }
                    break;
                case 5:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.data, this.uhdHeaderSize)) {
                        parseUhdHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.uhdHeaderSize);
                        this.state = 6;
                    }
                    break;
                case 6:
                    int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    this.output.sampleData(parsableByteArray, iMin);
                    int i4 = this.bytesRead + iMin;
                    this.bytesRead = i4;
                    if (i4 == this.sampleSize) {
                        Assertions.checkState(this.timeUs != -9223372036854775807L);
                        this.output.sampleMetadata(this.timeUs, this.frameType == 4 ? 0 : 1, this.sampleSize, 0, null);
                        this.timeUs += this.sampleDurationUs;
                        this.state = 0;
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
        trackIdGenerator.maybeThrowUninitializedError();
        this.output = extractorOutput.track(trackIdGenerator.trackId, 1);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    @RequiresNonNull({"output"})
    public final void parseCoreHeader() {
        byte[] bArr = this.headerScratchBytes.data;
        if (this.format == null) {
            Format dtsFormat = DtsUtil.parseDtsFormat(bArr, this.formatId, this.language, this.roleFlags, null);
            this.format = dtsFormat;
            this.output.format(dtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        this.sampleDurationUs = Ints.checkedCast(Util.sampleCountToDurationUs(DtsUtil.parseDtsAudioSampleCount(bArr), this.format.sampleRate));
    }

    @RequiresNonNull({"output"})
    public final void parseExtensionSubstreamHeader() throws ParserException {
        DtsUtil.DtsHeader dtsHdHeader = DtsUtil.parseDtsHdHeader(this.headerScratchBytes.data);
        updateFormatWithDtsHeaderInfo(dtsHdHeader);
        this.sampleSize = dtsHdHeader.frameSize;
        long j = dtsHdHeader.frameDurationUs;
        if (j == -9223372036854775807L) {
            j = 0;
        }
        this.sampleDurationUs = j;
    }

    @RequiresNonNull({"output"})
    public final void parseUhdHeader() throws ParserException {
        DtsUtil.DtsHeader dtsUhdHeader = DtsUtil.parseDtsUhdHeader(this.headerScratchBytes.data, this.uhdAudioChunkId);
        if (this.frameType == 3) {
            updateFormatWithDtsHeaderInfo(dtsUhdHeader);
        }
        this.sampleSize = dtsUhdHeader.frameSize;
        long j = dtsUhdHeader.frameDurationUs;
        if (j == -9223372036854775807L) {
            j = 0;
        }
        this.sampleDurationUs = j;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
        this.timeUs = -9223372036854775807L;
        this.uhdAudioChunkId.set(0);
    }

    public final boolean skipToNextSyncWord(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.syncBytes << 8;
            this.syncBytes = i;
            int unsignedByte = i | parsableByteArray.readUnsignedByte();
            this.syncBytes = unsignedByte;
            int frameType = DtsUtil.getFrameType(unsignedByte);
            this.frameType = frameType;
            if (frameType != 0) {
                byte[] bArr = this.headerScratchBytes.data;
                int i2 = this.syncBytes;
                bArr[0] = (byte) ((i2 >> 24) & 255);
                bArr[1] = (byte) ((i2 >> 16) & 255);
                bArr[2] = (byte) ((i2 >> 8) & 255);
                bArr[3] = (byte) (i2 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @RequiresNonNull({"output"})
    public final void updateFormatWithDtsHeaderInfo(DtsUtil.DtsHeader dtsHeader) {
        int i;
        Format.Builder builder;
        int i2 = dtsHeader.sampleRate;
        if (i2 == -2147483647 || (i = dtsHeader.channelCount) == -1) {
            return;
        }
        Format format = this.format;
        if (format != null && i == format.channelCount && i2 == format.sampleRate && Util.areEqual(dtsHeader.mimeType, format.sampleMimeType)) {
            return;
        }
        Format format2 = this.format;
        if (format2 == null) {
            builder = new Format.Builder();
        } else {
            format2.getClass();
            builder = new Format.Builder(format2);
        }
        builder.id = this.formatId;
        builder.sampleMimeType = MimeTypes.normalizeMimeType(dtsHeader.mimeType);
        builder.channelCount = dtsHeader.channelCount;
        builder.sampleRate = dtsHeader.sampleRate;
        builder.language = this.language;
        builder.roleFlags = this.roleFlags;
        Format format3 = new Format(builder);
        this.format = format3;
        this.output.format(format3);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
