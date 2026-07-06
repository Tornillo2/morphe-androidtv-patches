package androidx.media3.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DummyTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.Id3Peeker;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.mp3.Seeker;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 8;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int FLAG_ENABLE_INDEX_SEEKING = 4;
    public static final int MAX_SNIFF_BYTES = 32768;
    public static final int MAX_SYNC_BYTES = 131072;
    public static final int MPEG_AUDIO_HEADER_MASK = -128000;
    public static final int SCRATCH_LENGTH = 10;
    public static final int SEEK_HEADER_INFO = 1231971951;
    public static final int SEEK_HEADER_UNSET = 0;
    public static final int SEEK_HEADER_VBRI = 1447187017;
    public static final int SEEK_HEADER_XING = 1483304551;
    public long basisTimeUs;
    public TrackOutput currentTrackOutput;
    public boolean disableSeeking;
    public ExtractorOutput extractorOutput;
    public long firstSamplePosition;
    public final int flags;
    public final long forcedFirstSampleTimestampUs;
    public final GaplessInfoHolder gaplessInfoHolder;
    public final Id3Peeker id3Peeker;
    public boolean isSeekInProgress;

    @Nullable
    public Metadata metadata;
    public TrackOutput realTrackOutput;
    public int sampleBytesRemaining;
    public long samplesRead;
    public final ParsableByteArray scratch;
    public long seekTimeUs;
    public Seeker seeker;
    public final TrackOutput skippingTrackOutput;
    public final MpegAudioUtil.Header synchronizedHeader;
    public int synchronizedHeaderData;
    public static final ExtractorsFactory FACTORY = new Mp3Extractor$$ExternalSyntheticLambda0();
    public static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = new Mp3Extractor$$ExternalSyntheticLambda1();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static Extractor[] $r8$lambda$VlN23tvbrkcLKliae6icwKqXLAE() {
        return new Extractor[]{new Mp3Extractor(0)};
    }

    public static /* synthetic */ boolean $r8$lambda$bVD6_snQ71sbMsHH4YoyozOnKO8(int i, int i2, int i3, int i4, int i5) {
        if (i2 == 67 && i3 == 79 && i4 == 77 && (i5 == 77 || i == 2)) {
            return true;
        }
        if (i2 == 77 && i3 == 76 && i4 == 76) {
            return i5 == 84 || i == 2;
        }
        return false;
    }

    public Mp3Extractor() {
        this(0);
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    public static long getId3TlenUs(@Nullable Metadata metadata) {
        if (metadata == null) {
            return -9223372036854775807L;
        }
        int length = metadata.entries.length;
        for (int i = 0; i < length; i++) {
            Metadata.Entry entry = metadata.entries[i];
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                if (textInformationFrame.id.equals("TLEN")) {
                    return Util.msToUs(Long.parseLong(textInformationFrame.values.get(0)));
                }
            }
        }
        return -9223372036854775807L;
    }

    public static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit >= i + 4) {
            parsableByteArray.setPosition(i);
            int i2 = parsableByteArray.readInt();
            if (i2 == 1483304551 || i2 == 1231971951) {
                return i2;
            }
        }
        if (parsableByteArray.limit < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        return parsableByteArray.readInt() == 1447187017 ? 1447187017 : 0;
    }

    public static boolean headersMatch(int i, long j) {
        return ((long) (i & (-128000))) == (j & (-128000));
    }

    @Nullable
    public static MlltSeeker maybeHandleSeekMetadata(@Nullable Metadata metadata, long j) {
        if (metadata == null) {
            return null;
        }
        int length = metadata.entries.length;
        for (int i = 0; i < length; i++) {
            Metadata.Entry entry = metadata.entries[i];
            if (entry instanceof MlltFrame) {
                return MlltSeeker.create(j, (MlltFrame) entry, getId3TlenUs(metadata));
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a8  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"realTrackOutput", "seeker"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readSample(androidx.media3.extractor.ExtractorInput r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp3.Mp3Extractor.readSample(androidx.media3.extractor.ExtractorInput):int");
    }

    public final Seeker computeSeeker(ExtractorInput extractorInput) throws IOException {
        long id3TlenUs;
        long dataEndPosition;
        long j;
        Seeker seekerMaybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
        MlltSeeker mlltSeekerMaybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        if ((this.flags & 4) != 0) {
            if (mlltSeekerMaybeHandleSeekMetadata != null) {
                id3TlenUs = mlltSeekerMaybeHandleSeekMetadata.durationUs;
            } else if (seekerMaybeReadSeekFrame != null) {
                long durationUs = seekerMaybeReadSeekFrame.getDurationUs();
                dataEndPosition = seekerMaybeReadSeekFrame.getDataEndPosition();
                j = durationUs;
                seekerMaybeReadSeekFrame = new IndexSeeker(j, extractorInput.getPosition(), dataEndPosition);
            } else {
                id3TlenUs = getId3TlenUs(this.metadata);
            }
            j = id3TlenUs;
            dataEndPosition = -1;
            seekerMaybeReadSeekFrame = new IndexSeeker(j, extractorInput.getPosition(), dataEndPosition);
        } else if (mlltSeekerMaybeHandleSeekMetadata != null) {
            seekerMaybeReadSeekFrame = mlltSeekerMaybeHandleSeekMetadata;
        } else if (seekerMaybeReadSeekFrame == null) {
            seekerMaybeReadSeekFrame = null;
        }
        if (seekerMaybeReadSeekFrame == null || !(seekerMaybeReadSeekFrame.isSeekable() || (this.flags & 1) == 0)) {
            return getConstantBitrateSeeker(extractorInput, -1L, (this.flags & 2) != 0);
        }
        return seekerMaybeReadSeekFrame;
    }

    public final long computeTimeUs(long j) {
        return ((j * 1000000) / ((long) this.synchronizedHeader.sampleRate)) + this.basisTimeUs;
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    public final Seeker getConstantBitrateSeeker(ExtractorInput extractorInput, boolean z) throws IOException {
        return getConstantBitrateSeeker(extractorInput, -1L, z);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        TrackOutput trackOutputTrack = extractorOutput.track(0, 1);
        this.realTrackOutput = trackOutputTrack;
        this.currentTrackOutput = trackOutputTrack;
        this.extractorOutput.endTracks();
    }

    @Nullable
    public final Seeker maybeReadSeekFrame(ExtractorInput extractorInput) throws IOException {
        int i;
        int i2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.data, 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header header = this.synchronizedHeader;
        int i3 = 21;
        if ((header.version & 1) != 0) {
            if (header.channels != 1) {
                i3 = 36;
            }
        } else if (header.channels == 1) {
            i3 = 13;
        }
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i3);
        if (seekFrameHeader != 1231971951) {
            if (seekFrameHeader == 1447187017) {
                VbriSeeker vbriSeekerCreate = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
                extractorInput.skipFully(this.synchronizedHeader.frameSize);
                return vbriSeekerCreate;
            }
            if (seekFrameHeader != 1483304551) {
                extractorInput.resetPeekPosition();
                return null;
            }
        }
        XingFrame xingFrame = XingFrame.parse(this.synchronizedHeader, parsableByteArray);
        if (!this.gaplessInfoHolder.hasGaplessInfo() && (i = xingFrame.encoderDelay) != -1 && (i2 = xingFrame.encoderPadding) != -1) {
            GaplessInfoHolder gaplessInfoHolder = this.gaplessInfoHolder;
            gaplessInfoHolder.encoderDelay = i;
            gaplessInfoHolder.encoderPadding = i2;
        }
        long position = extractorInput.getPosition();
        extractorInput.skipFully(this.synchronizedHeader.frameSize);
        if (seekFrameHeader == 1483304551) {
            return XingSeeker.create(extractorInput.getLength(), xingFrame, position);
        }
        long j = xingFrame.dataSize;
        return getConstantBitrateSeeker(extractorInput, j != -1 ? position + j : -1L, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean peekEndOfStreamOrHeader(androidx.media3.extractor.ExtractorInput r9) throws java.io.IOException {
        /*
            r8 = this;
            androidx.media3.extractor.mp3.Seeker r0 = r8.seeker
            r1 = 1
            if (r0 == 0) goto L1b
            long r2 = r0.getDataEndPosition()
            r4 = -1
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L1b
            long r4 = r9.getPeekPosition()
            r6 = 4
            long r2 = r2 - r6
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 <= 0) goto L1b
            goto L27
        L1b:
            androidx.media3.common.util.ParsableByteArray r0 = r8.scratch     // Catch: java.io.EOFException -> L27
            byte[] r0 = r0.data     // Catch: java.io.EOFException -> L27
            r2 = 0
            r3 = 4
            boolean r9 = r9.peekFully(r0, r2, r3, r1)     // Catch: java.io.EOFException -> L27
            r9 = r9 ^ r1
            return r9
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp3.Mp3Extractor.peekEndOfStreamOrHeader(androidx.media3.extractor.ExtractorInput):boolean");
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int internal = readInternal(extractorInput);
        if (internal == -1 && (this.seeker instanceof IndexSeeker)) {
            long jComputeTimeUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != jComputeTimeUs) {
                Seeker seeker = this.seeker;
                ((IndexSeeker) seeker).durationUs = jComputeTimeUs;
                this.extractorOutput.seekMap(seeker);
            }
        }
        return internal;
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    public final int readInternal(ExtractorInput extractorInput) throws IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            Seeker seekerComputeSeeker = computeSeeker(extractorInput);
            this.seeker = seekerComputeSeeker;
            this.extractorOutput.seekMap(seekerComputeSeeker);
            Format.Builder builder = new Format.Builder();
            builder.sampleMimeType = MimeTypes.normalizeMimeType(this.synchronizedHeader.mimeType);
            builder.maxInputSize = 4096;
            MpegAudioUtil.Header header = this.synchronizedHeader;
            builder.channelCount = header.channels;
            builder.sampleRate = header.sampleRate;
            GaplessInfoHolder gaplessInfoHolder = this.gaplessInfoHolder;
            builder.encoderDelay = gaplessInfoHolder.encoderDelay;
            builder.encoderPadding = gaplessInfoHolder.encoderPadding;
            builder.metadata = (this.flags & 8) != 0 ? null : this.metadata;
            if (this.seeker.getAverageBitrate() != -2147483647) {
                builder.averageBitrate = this.seeker.getAverageBitrate();
            }
            this.currentTrackOutput.format(new Format(builder));
            this.firstSamplePosition = extractorInput.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long position = extractorInput.getPosition();
            long j = this.firstSamplePosition;
            if (position < j) {
                extractorInput.skipFully((int) (j - position));
            }
        }
        return readSample(extractorInput);
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = -9223372036854775807L;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = j2;
        Seeker seeker = this.seeker;
        if (!(seeker instanceof IndexSeeker) || ((IndexSeeker) seeker).isTimeUsInIndex(j2)) {
            return;
        }
        this.isSeekInProgress = true;
        this.currentTrackOutput = this.skippingTrackOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return synchronize(extractorInput, true);
    }

    public final boolean synchronize(ExtractorInput extractorInput, boolean z) throws IOException {
        int i;
        int peekPosition;
        int frameSize;
        int i2 = z ? 32768 : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            Metadata metadataPeekId3Data = this.id3Peeker.peekId3Data(extractorInput, (this.flags & 8) == 0 ? null : REQUIRED_ID3_FRAME_PREDICATE);
            this.metadata = metadataPeekId3Data;
            if (metadataPeekId3Data != null) {
                this.gaplessInfoHolder.setFromMetadata(metadataPeekId3Data);
            }
            peekPosition = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(peekPosition);
            }
            i = 0;
        } else {
            i = 0;
            peekPosition = 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (!peekEndOfStreamOrHeader(extractorInput)) {
                this.scratch.setPosition(0);
                int i5 = this.scratch.readInt();
                if ((i == 0 || headersMatch(i5, i)) && (frameSize = MpegAudioUtil.getFrameSize(i5)) != -1) {
                    i3++;
                    if (i3 != 1) {
                        if (i3 == 4) {
                            break;
                        }
                    } else {
                        this.synchronizedHeader.setForHeaderData(i5);
                        i = i5;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                } else {
                    int i6 = i4 + 1;
                    if (i4 == i2) {
                        if (z) {
                            return false;
                        }
                        throw ParserException.createForMalformedContainer("Searched too many bytes.", null);
                    }
                    if (z) {
                        extractorInput.resetPeekPosition();
                        extractorInput.advancePeekPosition(peekPosition + i6);
                    } else {
                        extractorInput.skipFully(1);
                    }
                    i4 = i6;
                    i = 0;
                    i3 = 0;
                }
            } else if (i3 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            extractorInput.skipFully(peekPosition + i4);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i;
        return true;
    }

    public Mp3Extractor(int i) {
        this(i, -9223372036854775807L);
    }

    public final Seeker getConstantBitrateSeeker(ExtractorInput extractorInput, long j, boolean z) throws IOException {
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        if (extractorInput.getLength() != -1) {
            j = extractorInput.getLength();
        }
        return new ConstantBitrateSeeker(j, extractorInput.getPosition(), this.synchronizedHeader, z);
    }

    public Mp3Extractor(int i, long j) {
        this.flags = (i & 2) != 0 ? i | 1 : i;
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = -9223372036854775807L;
        this.id3Peeker = new Id3Peeker();
        DummyTrackOutput dummyTrackOutput = new DummyTrackOutput();
        this.skippingTrackOutput = dummyTrackOutput;
        this.currentTrackOutput = dummyTrackOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }
}
