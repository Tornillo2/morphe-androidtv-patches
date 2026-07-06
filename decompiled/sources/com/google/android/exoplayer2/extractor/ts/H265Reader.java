package com.google.android.exoplayer2.extractor.ts;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class H265Reader implements ElementaryStreamReader {
    public static final int AUD_NUT = 35;
    public static final int BLA_W_LP = 16;
    public static final int CRA_NUT = 21;
    public static final int PPS_NUT = 34;
    public static final int PREFIX_SEI_NUT = 39;
    public static final int RASL_R = 9;
    public static final int SPS_NUT = 33;
    public static final int SUFFIX_SEI_NUT = 40;
    public static final String TAG = "H265Reader";
    public static final int VPS_NUT = 32;
    public String formatId;
    public boolean hasOutputFormat;
    public TrackOutput output;
    public SampleReader sampleReader;
    public final SeiReader seiReader;
    public long totalBytesWritten;
    public final boolean[] prefixFlags = new boolean[3];
    public final NalUnitTargetBuffer vps = new NalUnitTargetBuffer(32, 128);
    public final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(33, 128);
    public final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(34, 128);
    public final NalUnitTargetBuffer prefixSei = new NalUnitTargetBuffer(39, 128);
    public final NalUnitTargetBuffer suffixSei = new NalUnitTargetBuffer(40, 128);
    public long pesTimeUs = -9223372036854775807L;
    public final ParsableByteArray seiWrapper = new ParsableByteArray();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleReader {
        public static final int FIRST_SLICE_FLAG_OFFSET = 2;
        public boolean isFirstPrefixNalUnit;
        public boolean isFirstSlice;
        public boolean lookingForFirstSliceFlag;
        public int nalUnitBytesRead;
        public boolean nalUnitHasKeyframeData;
        public long nalUnitPosition;
        public long nalUnitTimeUs;
        public final TrackOutput output;
        public boolean readingPrefix;
        public boolean readingSample;
        public boolean sampleIsKeyframe;
        public long samplePosition;
        public long sampleTimeUs;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public static boolean isPrefixNalUnit(int i) {
            return (32 <= i && i <= 35) || i == 39;
        }

        public static boolean isVclBodyNalUnit(int i) {
            return i < 32 || i == 40;
        }

        public void endNalUnit(long j, int i, boolean z) {
            if (this.readingPrefix && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingPrefix = false;
            } else if (this.isFirstPrefixNalUnit || this.isFirstSlice) {
                if (z && this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitPosition)));
                }
                this.samplePosition = this.nalUnitPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingSample = true;
            }
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void outputSample(int i) {
            long j = this.sampleTimeUs;
            if (j == -9223372036854775807L) {
                return;
            }
            boolean z = this.sampleIsKeyframe;
            this.output.sampleMetadata(j, z ? 1 : 0, (int) (this.nalUnitPosition - this.samplePosition), i, null);
        }

        public void readNalUnitData(byte[] bArr, int i, int i2) {
            if (this.lookingForFirstSliceFlag) {
                int i3 = this.nalUnitBytesRead;
                int i4 = (i + 2) - i3;
                if (i4 >= i2) {
                    this.nalUnitBytesRead = (i2 - i) + i3;
                } else {
                    this.isFirstSlice = (bArr[i4] & 128) != 0;
                    this.lookingForFirstSliceFlag = false;
                }
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.readingSample = false;
            this.readingPrefix = false;
        }

        public void startNalUnit(long j, int i, int i2, long j2, boolean z) {
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.nalUnitTimeUs = j2;
            this.nalUnitBytesRead = 0;
            this.nalUnitPosition = j;
            if (!isVclBodyNalUnit(i2)) {
                if (this.readingSample && !this.readingPrefix) {
                    if (z) {
                        outputSample(i);
                    }
                    this.readingSample = false;
                }
                if (isPrefixNalUnit(i2)) {
                    this.isFirstPrefixNalUnit = !this.readingPrefix;
                    this.readingPrefix = true;
                }
            }
            boolean z2 = i2 >= 16 && i2 <= 21;
            this.nalUnitHasKeyframeData = z2;
            this.lookingForFirstSliceFlag = z2 || i2 <= 9;
        }
    }

    public H265Reader(SeiReader seiReader) {
        this.seiReader = seiReader;
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long j, int i, int i2, long j2) {
        this.sampleReader.endNalUnit(j, i, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.endNalUnit(i2);
            this.sps.endNalUnit(i2);
            this.pps.endNalUnit(i2);
            NalUnitTargetBuffer nalUnitTargetBuffer = this.vps;
            if (nalUnitTargetBuffer.isCompleted) {
                NalUnitTargetBuffer nalUnitTargetBuffer2 = this.sps;
                if (nalUnitTargetBuffer2.isCompleted) {
                    NalUnitTargetBuffer nalUnitTargetBuffer3 = this.pps;
                    if (nalUnitTargetBuffer3.isCompleted) {
                        this.output.format(parseMediaFormat(this.formatId, nalUnitTargetBuffer, nalUnitTargetBuffer2, nalUnitTargetBuffer3));
                        this.hasOutputFormat = true;
                    }
                }
            }
        }
        if (this.prefixSei.endNalUnit(i2)) {
            NalUnitTargetBuffer nalUnitTargetBuffer4 = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer4.nalData, nalUnitTargetBuffer4.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i2)) {
            NalUnitTargetBuffer nalUnitTargetBuffer5 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer5.nalData, nalUnitTargetBuffer5.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j2, this.seiWrapper);
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] bArr, int i, int i2) {
        this.sampleReader.readNalUnitData(bArr, i, i2);
        if (!this.hasOutputFormat) {
            this.vps.appendToNalUnit(bArr, i, i2);
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.prefixSei.appendToNalUnit(bArr, i, i2);
        this.suffixSei.appendToNalUnit(bArr, i, i2);
    }

    public static Format parseMediaFormat(@Nullable String str, NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3) {
        int i = nalUnitTargetBuffer.nalLength;
        byte[] bArr = new byte[nalUnitTargetBuffer2.nalLength + i + nalUnitTargetBuffer3.nalLength];
        System.arraycopy(nalUnitTargetBuffer.nalData, 0, bArr, 0, i);
        System.arraycopy(nalUnitTargetBuffer2.nalData, 0, bArr, nalUnitTargetBuffer.nalLength, nalUnitTargetBuffer2.nalLength);
        System.arraycopy(nalUnitTargetBuffer3.nalData, 0, bArr, nalUnitTargetBuffer.nalLength + nalUnitTargetBuffer2.nalLength, nalUnitTargetBuffer3.nalLength);
        NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(nalUnitTargetBuffer2.nalData, 3, nalUnitTargetBuffer2.nalLength);
        String strBuildHevcCodecString = CodecSpecificDataUtil.buildHevcCodecString(h265SpsNalUnit.generalProfileSpace, h265SpsNalUnit.generalTierFlag, h265SpsNalUnit.generalProfileIdc, h265SpsNalUnit.generalProfileCompatibilityFlags, h265SpsNalUnit.constraintBytes, h265SpsNalUnit.generalLevelIdc);
        Format.Builder builder = new Format.Builder();
        builder.id = str;
        builder.sampleMimeType = "video/hevc";
        builder.codecs = strBuildHevcCodecString;
        builder.width = h265SpsNalUnit.width;
        builder.height = h265SpsNalUnit.height;
        builder.pixelWidthHeightRatio = h265SpsNalUnit.pixelWidthHeightRatio;
        builder.initializationData = Collections.singletonList(bArr);
        return new Format(builder);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int i = parsableByteArray.position;
            int i2 = parsableByteArray.limit;
            byte[] bArr = parsableByteArray.data;
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (i < i2) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, i, i2, this.prefixFlags);
                if (iFindNalUnit == i2) {
                    nalUnitData(bArr, i, i2);
                    return;
                }
                int h265NalUnitType = NalUnitUtil.getH265NalUnitType(bArr, iFindNalUnit);
                int i3 = iFindNalUnit - i;
                if (i3 > 0) {
                    nalUnitData(bArr, i, iFindNalUnit);
                }
                int i4 = i2 - iFindNalUnit;
                long j = this.totalBytesWritten - ((long) i4);
                endNalUnit(j, i4, i3 < 0 ? -i3 : 0, this.pesTimeUs);
                startNalUnit(j, i4, h265NalUnitType, this.pesTimeUs);
                i = iFindNalUnit + 3;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 2);
        this.output = trackOutputTrack;
        this.sampleReader = new SampleReader(trackOutputTrack);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        if (j != -9223372036854775807L) {
            this.pesTimeUs = j;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0L;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
    }

    @RequiresNonNull({"sampleReader"})
    public final void startNalUnit(long j, int i, int i2, long j2) {
        this.sampleReader.startNalUnit(j, i, i2, j2, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.startNalUnit(i2);
            this.sps.startNalUnit(i2);
            this.pps.startNalUnit(i2);
        }
        this.prefixSei.startNalUnit(i2);
        this.suffixSei.startNalUnit(i2);
    }

    @Override // com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
