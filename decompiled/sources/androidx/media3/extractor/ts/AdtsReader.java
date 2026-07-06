package androidx.media3.extractor.ts;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.DummyTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AdtsReader implements ElementaryStreamReader {
    public static final int CRC_SIZE = 2;
    public static final int HEADER_SIZE = 5;
    public static final int ID3_HEADER_SIZE = 10;
    public static final byte[] ID3_IDENTIFIER = {73, 68, TarConstants.LF_CHR};
    public static final int ID3_SIZE_OFFSET = 6;
    public static final int MATCH_STATE_FF = 512;
    public static final int MATCH_STATE_I = 768;
    public static final int MATCH_STATE_ID = 1024;
    public static final int MATCH_STATE_START = 256;
    public static final int MATCH_STATE_VALUE_SHIFT = 8;
    public static final int STATE_CHECKING_ADTS_HEADER = 1;
    public static final int STATE_FINDING_SAMPLE = 0;
    public static final int STATE_READING_ADTS_HEADER = 3;
    public static final int STATE_READING_ID3_HEADER = 2;
    public static final int STATE_READING_SAMPLE = 4;
    public static final String TAG = "AdtsReader";
    public static final int VERSION_UNSET = -1;
    public final ParsableBitArray adtsScratch;
    public int bytesRead;
    public int currentFrameVersion;
    public TrackOutput currentOutput;
    public long currentSampleDuration;
    public final boolean exposeId3;
    public int firstFrameSampleRateIndex;
    public int firstFrameVersion;
    public String formatId;
    public boolean foundFirstFrame;
    public boolean hasCrc;
    public boolean hasOutputFormat;
    public final ParsableByteArray id3HeaderBuffer;
    public TrackOutput id3Output;

    @Nullable
    public final String language;
    public int matchState;
    public TrackOutput output;
    public final int roleFlags;
    public long sampleDurationUs;
    public int sampleSize;
    public int state;
    public long timeUs;

    public AdtsReader(boolean z) {
        this(z, null, 0);
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i2 = this.bytesRead + iMin;
        this.bytesRead = i2;
        return i2 == i;
    }

    public static boolean isAdtsSyncWord(int i) {
        return (i & 65526) == 65520;
    }

    @EnsuresNonNull({"output", "currentOutput", "id3Output"})
    public final void assertTracksCreated() {
        this.output.getClass();
        Util.castNonNull(this.currentOutput);
    }

    public final void checkAdtsHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() == 0) {
            return;
        }
        ParsableBitArray parsableBitArray = this.adtsScratch;
        parsableBitArray.data[0] = parsableByteArray.data[parsableByteArray.position];
        parsableBitArray.setPosition(2);
        int bits = this.adtsScratch.readBits(4);
        int i = this.firstFrameSampleRateIndex;
        if (i != -1 && bits != i) {
            resetSync();
            return;
        }
        if (!this.foundFirstFrame) {
            this.foundFirstFrame = true;
            this.firstFrameVersion = this.currentFrameVersion;
            this.firstFrameSampleRateIndex = bits;
        }
        setReadingAdtsHeaderState();
    }

    public final boolean checkSyncPositionValid(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 1);
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
            return false;
        }
        this.adtsScratch.setPosition(4);
        int bits = this.adtsScratch.readBits(1);
        int i2 = this.firstFrameVersion;
        if (i2 != -1 && bits != i2) {
            return false;
        }
        if (this.firstFrameSampleRateIndex != -1) {
            if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
                return true;
            }
            this.adtsScratch.setPosition(2);
            if (this.adtsScratch.readBits(4) != this.firstFrameSampleRateIndex) {
                return false;
            }
            parsableByteArray.setPosition(i + 2);
        }
        if (!tryRead(parsableByteArray, this.adtsScratch.data, 4)) {
            return true;
        }
        this.adtsScratch.setPosition(14);
        int bits2 = this.adtsScratch.readBits(13);
        if (bits2 < 7) {
            return false;
        }
        byte[] bArr = parsableByteArray.data;
        int i3 = parsableByteArray.limit;
        int i4 = i + bits2;
        if (i4 >= i3) {
            return true;
        }
        byte b = bArr[i4];
        if (b == -1) {
            int i5 = i4 + 1;
            if (i5 == i3) {
                return true;
            }
            return isAdtsSyncBytes((byte) -1, bArr[i5]) && ((bArr[i5] & 8) >> 3) == bits;
        }
        if (b != 73) {
            return false;
        }
        int i6 = i4 + 1;
        if (i6 == i3) {
            return true;
        }
        if (bArr[i6] != 68) {
            return false;
        }
        int i7 = i4 + 2;
        return i7 == i3 || bArr[i7] == 51;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i == 0) {
                findNextSample(parsableByteArray);
            } else if (i == 1) {
                checkAdtsHeader(parsableByteArray);
            } else if (i != 2) {
                if (i == 3) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else {
                    if (i != 4) {
                        throw new IllegalStateException();
                    }
                    readSample(parsableByteArray);
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.data, 10)) {
                parseId3Header();
            }
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 1);
        this.output = trackOutputTrack;
        this.currentOutput = trackOutputTrack;
        if (!this.exposeId3) {
            this.id3Output = new DummyTrackOutput();
            return;
        }
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack2 = extractorOutput.track(trackIdGenerator.trackId, 5);
        this.id3Output = trackOutputTrack2;
        Format.Builder builder = new Format.Builder();
        trackIdGenerator.maybeThrowUninitializedError();
        builder.id = trackIdGenerator.formatId;
        builder.sampleMimeType = MimeTypes.normalizeMimeType("application/id3");
        trackOutputTrack2.format(new Format(builder));
    }

    public final void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] bArr = parsableByteArray.data;
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            int i4 = b & 255;
            if (this.matchState == 512 && isAdtsSyncBytes((byte) -1, (byte) i4) && (this.foundFirstFrame || checkSyncPositionValid(parsableByteArray, i - 1))) {
                this.currentFrameVersion = (b & 8) >> 3;
                this.hasCrc = (b & 1) == 0;
                if (this.foundFirstFrame) {
                    setReadingAdtsHeaderState();
                } else {
                    setCheckingAdtsHeaderState();
                }
                parsableByteArray.setPosition(i3);
                return;
            }
            int i5 = this.matchState;
            int i6 = i4 | i5;
            if (i6 == 329) {
                this.matchState = 768;
            } else if (i6 == 511) {
                this.matchState = 512;
            } else if (i6 == 836) {
                this.matchState = 1024;
            } else if (i6 == 1075) {
                setReadingId3HeaderState();
                parsableByteArray.setPosition(i3);
                return;
            } else if (i5 != 256) {
                this.matchState = 256;
            }
            i = i3;
        }
        parsableByteArray.setPosition(i);
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    public final boolean isAdtsSyncBytes(byte b, byte b2) {
        return isAdtsSyncWord(((b & 255) << 8) | (b2 & 255));
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    @RequiresNonNull({"output"})
    public final void parseAdtsHeader() throws ParserException {
        this.adtsScratch.setPosition(0);
        if (this.hasOutputFormat) {
            this.adtsScratch.skipBits(10);
        } else {
            int i = 2;
            int bits = this.adtsScratch.readBits(2) + 1;
            if (bits != 2) {
                Log.w("AdtsReader", "Detected audio object type: " + bits + ", but assuming AAC LC.");
            } else {
                i = bits;
            }
            this.adtsScratch.skipBits(5);
            byte[] bArrBuildAudioSpecificConfig = AacUtil.buildAudioSpecificConfig(i, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
            AacUtil.Config audioSpecificConfig = AacUtil.parseAudioSpecificConfig(bArrBuildAudioSpecificConfig);
            Format.Builder builder = new Format.Builder();
            builder.id = this.formatId;
            builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/mp4a-latm");
            builder.codecs = audioSpecificConfig.codecs;
            builder.channelCount = audioSpecificConfig.channelCount;
            builder.sampleRate = audioSpecificConfig.sampleRateHz;
            builder.initializationData = Collections.singletonList(bArrBuildAudioSpecificConfig);
            builder.language = this.language;
            builder.roleFlags = this.roleFlags;
            Format format = new Format(builder);
            this.sampleDurationUs = 1024000000 / ((long) format.sampleRate);
            this.output.format(format);
            this.hasOutputFormat = true;
        }
        this.adtsScratch.skipBits(4);
        int bits2 = this.adtsScratch.readBits(13);
        int i2 = bits2 - 7;
        if (this.hasCrc) {
            i2 = bits2 - 9;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, i2);
    }

    @RequiresNonNull({"id3Output"})
    public final void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0L, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    @RequiresNonNull({"currentOutput"})
    public final void readSample(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, iMin);
        int i = this.bytesRead + iMin;
        this.bytesRead = i;
        if (i == this.sampleSize) {
            Assertions.checkState(this.timeUs != -9223372036854775807L);
            this.currentOutput.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
            this.timeUs += this.currentSampleDuration;
            setFindingSampleState();
        }
    }

    public final void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.timeUs = -9223372036854775807L;
        resetSync();
    }

    public final void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    public final void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    public final void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    public final void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    public final void setReadingSampleState(TrackOutput trackOutput, long j, int i, int i2) {
        this.state = 4;
        this.bytesRead = i;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j;
        this.sampleSize = i2;
    }

    public final boolean tryRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        if (parsableByteArray.bytesLeft() < i) {
            return false;
        }
        parsableByteArray.readBytes(bArr, 0, i);
        return true;
    }

    public AdtsReader(boolean z, @Nullable String str, int i) {
        this.adtsScratch = new ParsableBitArray(new byte[7], 7);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = -9223372036854775807L;
        this.timeUs = -9223372036854775807L;
        this.exposeId3 = z;
        this.language = str;
        this.roleFlags = i;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
