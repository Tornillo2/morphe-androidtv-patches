package com.google.android.exoplayer2.extractor.mp3;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.Id3Peeker;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp3.Seeker;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
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
/* JADX INFO: loaded from: classes3.dex */
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

    /* JADX INFO: renamed from: $r8$lambda$5hBhKwpQMo5Y1V-wXbZpz_A2KwY, reason: not valid java name */
    public static Extractor[] m454$r8$lambda$5hBhKwpQMo5Y1VwXbZpz_A2KwY() {
        return new Extractor[]{new Mp3Extractor(0)};
    }

    /* JADX INFO: renamed from: $r8$lambda$itzi5sQ4APfHauk2-YMljPCFep0, reason: not valid java name */
    public static /* synthetic */ boolean m455$r8$lambda$itzi5sQ4APfHauk2YMljPCFep0(int i, int i2, int i3, int i4, int i5) {
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

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int i = this.scratch.readInt();
            if (!headersMatch(i, this.synchronizedHeaderData) || MpegAudioUtil.getFrameSize(i) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            this.synchronizedHeader.setForHeaderData(i);
            if (this.basisTimeUs == -9223372036854775807L) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != -9223372036854775807L) {
                    this.basisTimeUs = (this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L)) + this.basisTimeUs;
                }
            }
            MpegAudioUtil.Header header = this.synchronizedHeader;
            this.sampleBytesRemaining = header.frameSize;
            Seeker seeker = this.seeker;
            if (seeker instanceof IndexSeeker) {
                IndexSeeker indexSeeker = (IndexSeeker) seeker;
                indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + ((long) header.samplesPerFrame)), extractorInput.getPosition() + ((long) this.synchronizedHeader.frameSize));
                if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                    this.isSeekInProgress = false;
                    this.currentTrackOutput = this.realTrackOutput;
                }
            }
        }
        int iSampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        int i2 = this.sampleBytesRemaining - iSampleData;
        this.sampleBytesRemaining = i2;
        if (i2 > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    public final Seeker computeSeeker(ExtractorInput extractorInput) throws IOException {
        long id3TlenUs;
        long durationUs;
        long dataEndPosition;
        Seeker seekerMaybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
        MlltSeeker mlltSeekerMaybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        if ((this.flags & 4) != 0) {
            if (mlltSeekerMaybeHandleSeekMetadata != null) {
                id3TlenUs = mlltSeekerMaybeHandleSeekMetadata.durationUs;
            } else if (seekerMaybeReadSeekFrame != null) {
                durationUs = seekerMaybeReadSeekFrame.getDurationUs();
                dataEndPosition = seekerMaybeReadSeekFrame.getDataEndPosition();
                seekerMaybeReadSeekFrame = new IndexSeeker(durationUs, extractorInput.getPosition(), dataEndPosition);
            } else {
                id3TlenUs = getId3TlenUs(this.metadata);
            }
            durationUs = id3TlenUs;
            dataEndPosition = -1;
            seekerMaybeReadSeekFrame = new IndexSeeker(durationUs, extractorInput.getPosition(), dataEndPosition);
        } else if (mlltSeekerMaybeHandleSeekMetadata != null) {
            seekerMaybeReadSeekFrame = mlltSeekerMaybeHandleSeekMetadata;
        } else if (seekerMaybeReadSeekFrame == null) {
            seekerMaybeReadSeekFrame = null;
        }
        if (seekerMaybeReadSeekFrame == null || !(seekerMaybeReadSeekFrame.isSeekable() || (this.flags & 1) == 0)) {
            return getConstantBitrateSeeker(extractorInput, (this.flags & 2) != 0);
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
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, z);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        TrackOutput trackOutputTrack = extractorOutput.track(0, 1);
        this.realTrackOutput = trackOutputTrack;
        this.currentTrackOutput = trackOutputTrack;
        this.extractorOutput.endTracks();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0026  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.exoplayer2.extractor.mp3.Seeker maybeReadSeekFrame(com.google.android.exoplayer2.extractor.ExtractorInput r11) throws java.io.IOException {
        /*
            r10 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r5 = new com.google.android.exoplayer2.util.ParsableByteArray
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r10.synchronizedHeader
            int r0 = r0.frameSize
            r5.<init>(r0)
            byte[] r0 = r5.data
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r6 = 0
            r11.peekFully(r0, r6, r1)
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r10.synchronizedHeader
            int r1 = r0.version
            r2 = 1
            r1 = r1 & r2
            r3 = 21
            if (r1 == 0) goto L29
            int r0 = r0.channels
            if (r0 == r2) goto L26
            r3 = 36
            r7 = 36
            goto L32
        L26:
            r7 = 21
            goto L32
        L29:
            int r0 = r0.channels
            if (r0 == r2) goto L2e
            goto L26
        L2e:
            r3 = 13
            r7 = 13
        L32:
            int r8 = getSeekFrameHeader(r5, r7)
            r0 = 1483304551(0x58696e67, float:1.02664153E15)
            r9 = 1231971951(0x496e666f, float:976486.94)
            if (r8 == r0) goto L61
            if (r8 != r9) goto L41
            goto L61
        L41:
            r0 = 1447187017(0x56425249, float:5.3414667E13)
            if (r8 != r0) goto L5c
            long r0 = r11.getLength()
            long r2 = r11.getPosition()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r4 = r10.synchronizedHeader
            com.google.android.exoplayer2.extractor.mp3.VbriSeeker r0 = com.google.android.exoplayer2.extractor.mp3.VbriSeeker.create(r0, r2, r4, r5)
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r11.skipFully(r1)
            return r0
        L5c:
            r11.resetPeekPosition()
            r11 = 0
            return r11
        L61:
            long r0 = r11.getLength()
            long r2 = r11.getPosition()
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r4 = r10.synchronizedHeader
            com.google.android.exoplayer2.extractor.mp3.XingSeeker r0 = com.google.android.exoplayer2.extractor.mp3.XingSeeker.create(r0, r2, r4, r5)
            if (r0 == 0) goto L99
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r1 = r10.gaplessInfoHolder
            boolean r1 = r1.hasGaplessInfo()
            if (r1 != 0) goto L99
            r11.resetPeekPosition()
            int r7 = r7 + 141
            r11.advancePeekPosition(r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r10.scratch
            byte[] r1 = r1.data
            r2 = 3
            r11.peekFully(r1, r6, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r10.scratch
            r1.setPosition(r6)
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r1 = r10.gaplessInfoHolder
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r10.scratch
            int r2 = r2.readUnsignedInt24()
            r1.setFromXingHeaderValue(r2)
        L99:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r1 = r10.synchronizedHeader
            int r1 = r1.frameSize
            r11.skipFully(r1)
            if (r0 == 0) goto Laf
            boolean r1 = r0.isSeekable()
            if (r1 != 0) goto Laf
            if (r8 != r9) goto Laf
            com.google.android.exoplayer2.extractor.mp3.Seeker r11 = r10.getConstantBitrateSeeker(r11, r6)
            return r11
        Laf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.maybeReadSeekFrame(com.google.android.exoplayer2.extractor.ExtractorInput):com.google.android.exoplayer2.extractor.mp3.Seeker");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean peekEndOfStreamOrHeader(com.google.android.exoplayer2.extractor.ExtractorInput r9) throws java.io.IOException {
        /*
            r8 = this;
            com.google.android.exoplayer2.extractor.mp3.Seeker r0 = r8.seeker
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
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r8.scratch     // Catch: java.io.EOFException -> L27
            byte[] r0 = r0.data     // Catch: java.io.EOFException -> L27
            r2 = 0
            r3 = 4
            boolean r9 = r9.peekFully(r0, r2, r3, r1)     // Catch: java.io.EOFException -> L27
            r9 = r9 ^ r1
            return r9
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.peekEndOfStreamOrHeader(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
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
            TrackOutput trackOutput = this.currentTrackOutput;
            Format.Builder builder = new Format.Builder();
            MpegAudioUtil.Header header = this.synchronizedHeader;
            builder.sampleMimeType = header.mimeType;
            builder.maxInputSize = 4096;
            builder.channelCount = header.channels;
            builder.sampleRate = header.sampleRate;
            GaplessInfoHolder gaplessInfoHolder = this.gaplessInfoHolder;
            builder.encoderDelay = gaplessInfoHolder.encoderDelay;
            builder.encoderPadding = gaplessInfoHolder.encoderPadding;
            builder.metadata = (this.flags & 8) != 0 ? null : this.metadata;
            trackOutput.format(new Format(builder));
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

    @Override // com.google.android.exoplayer2.extractor.Extractor
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

    @Override // com.google.android.exoplayer2.extractor.Extractor
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

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }
}
