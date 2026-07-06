package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ParsableNalUnitBitArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class H264Reader implements ElementaryStreamReader {
    public final boolean allowNonIdrKeyframes;
    public final boolean detectAccessUnits;
    public String formatId;
    public boolean hasOutputFormat;
    public TrackOutput output;
    public boolean randomAccessIndicator;
    public SampleReader sampleReader;
    public final SeiReader seiReader;
    public long totalBytesWritten;
    public final boolean[] prefixFlags = new boolean[3];
    public final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    public final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    public final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    public long pesTimeUs = -9223372036854775807L;
    public final ParsableByteArray seiWrapper = new ParsableByteArray();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleReader {
        public static final int DEFAULT_BUFFER_SIZE = 128;
        public final boolean allowNonIdrKeyframes;
        public final ParsableNalUnitBitArray bitArray;
        public byte[] buffer;
        public int bufferLength;
        public final boolean detectAccessUnits;
        public boolean isFilling;
        public long nalUnitStartPosition;
        public long nalUnitTimeUs;
        public int nalUnitType;
        public final TrackOutput output;
        public boolean randomAccessIndicator;
        public boolean readingSample;
        public boolean sampleIsKeyframe;
        public long samplePosition;
        public long sampleTimeUs;
        public final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();
        public final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();
        public SliceHeaderData previousSliceHeader = new SliceHeaderData();
        public SliceHeaderData sliceHeader = new SliceHeaderData();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class SliceHeaderData {
            public static final int SLICE_TYPE_ALL_I = 7;
            public static final int SLICE_TYPE_I = 2;
            public boolean bottomFieldFlag;
            public boolean bottomFieldFlagPresent;
            public int deltaPicOrderCnt0;
            public int deltaPicOrderCnt1;
            public int deltaPicOrderCntBottom;
            public boolean fieldPicFlag;
            public int frameNum;
            public boolean hasSliceType;
            public boolean idrPicFlag;
            public int idrPicId;
            public boolean isComplete;
            public int nalRefIdc;
            public int picOrderCntLsb;
            public int picParameterSetId;
            public int sliceType;

            @Nullable
            public NalUnitUtil.SpsData spsData;

            public SliceHeaderData() {
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public final boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                int i;
                int i2;
                int i3;
                boolean z;
                if (!this.isComplete) {
                    return false;
                }
                if (!sliceHeaderData.isComplete) {
                    return true;
                }
                NalUnitUtil.SpsData spsData = this.spsData;
                Assertions.checkStateNotNull(spsData);
                NalUnitUtil.SpsData spsData2 = sliceHeaderData.spsData;
                Assertions.checkStateNotNull(spsData2);
                return (this.frameNum == sliceHeaderData.frameNum && this.picParameterSetId == sliceHeaderData.picParameterSetId && this.fieldPicFlag == sliceHeaderData.fieldPicFlag && (!this.bottomFieldFlagPresent || !sliceHeaderData.bottomFieldFlagPresent || this.bottomFieldFlag == sliceHeaderData.bottomFieldFlag) && (((i = this.nalRefIdc) == (i2 = sliceHeaderData.nalRefIdc) || (i != 0 && i2 != 0)) && (((i3 = spsData.picOrderCountType) != 0 || spsData2.picOrderCountType != 0 || (this.picOrderCntLsb == sliceHeaderData.picOrderCntLsb && this.deltaPicOrderCntBottom == sliceHeaderData.deltaPicOrderCntBottom)) && ((i3 != 1 || spsData2.picOrderCountType != 1 || (this.deltaPicOrderCnt0 == sliceHeaderData.deltaPicOrderCnt0 && this.deltaPicOrderCnt1 == sliceHeaderData.deltaPicOrderCnt1)) && (z = this.idrPicFlag) == sliceHeaderData.idrPicFlag && (!z || this.idrPicId == sliceHeaderData.idrPicId))))) ? false : true;
            }

            public boolean isISlice() {
                if (!this.hasSliceType) {
                    return false;
                }
                int i = this.sliceType;
                return i == 7 || i == 2;
            }

            public void setAll(NalUnitUtil.SpsData spsData, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.spsData = spsData;
                this.nalRefIdc = i;
                this.sliceType = i2;
                this.frameNum = i3;
                this.picParameterSetId = i4;
                this.fieldPicFlag = z;
                this.bottomFieldFlagPresent = z2;
                this.bottomFieldFlag = z3;
                this.idrPicFlag = z4;
                this.idrPicId = i5;
                this.picOrderCntLsb = i6;
                this.deltaPicOrderCntBottom = i7;
                this.deltaPicOrderCnt0 = i8;
                this.deltaPicOrderCnt1 = i9;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int i) {
                this.sliceType = i;
                this.hasSliceType = true;
            }

            public SliceHeaderData(AnonymousClass1 anonymousClass1) {
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z2) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z;
            this.detectAccessUnits = z2;
            byte[] bArr = new byte[128];
            this.buffer = bArr;
            this.bitArray = new ParsableNalUnitBitArray(bArr, 0, 0);
            reset();
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x0109  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x010c  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0123  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x015f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void appendToNalUnit(byte[] r24, int r25, int r26) {
            /*
                Method dump skipped, instruction units count: 420
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.H264Reader.SampleReader.appendToNalUnit(byte[], int, int):void");
        }

        public boolean endNalUnit(long j, int i, boolean z) {
            boolean z2 = false;
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z && this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            boolean zIsISlice = this.allowNonIdrKeyframes ? this.sliceHeader.isISlice() : this.randomAccessIndicator;
            boolean z3 = this.sampleIsKeyframe;
            int i2 = this.nalUnitType;
            if (i2 == 5 || (zIsISlice && i2 == 1)) {
                z2 = true;
            }
            boolean z4 = z3 | z2;
            this.sampleIsKeyframe = z4;
            return z4;
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
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
            this.output.sampleMetadata(j, z ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i, null);
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j, int i, long j2, boolean z) {
            this.nalUnitType = i;
            this.nalUnitTimeUs = j2;
            this.nalUnitStartPosition = j;
            this.randomAccessIndicator = z;
            if (!this.allowNonIdrKeyframes || i != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (i != 5 && i != 1 && i != 2) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }
    }

    public H264Reader(SeiReader seiReader, boolean z, boolean z2) {
        this.seiReader = seiReader;
        this.allowNonIdrKeyframes = z;
        this.detectAccessUnits = z2;
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        assertTracksCreated();
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, i, i2, this.prefixFlags);
            if (iFindNalUnit == i2) {
                nalUnitData(bArr, i, i2);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(bArr, iFindNalUnit);
            int i3 = iFindNalUnit - i;
            if (i3 > 0) {
                nalUnitData(bArr, i, iFindNalUnit);
            }
            int i4 = i2 - iFindNalUnit;
            long j = this.totalBytesWritten - ((long) i4);
            endNalUnit(j, i4, i3 < 0 ? -i3 : 0, this.pesTimeUs);
            startNalUnit(j, nalUnitType, this.pesTimeUs);
            i = iFindNalUnit + 3;
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        trackIdGenerator.maybeThrowUninitializedError();
        this.formatId = trackIdGenerator.formatId;
        trackIdGenerator.maybeThrowUninitializedError();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 2);
        this.output = trackOutputTrack;
        this.sampleReader = new SampleReader(trackOutputTrack, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    @RequiresNonNull({"output", "sampleReader"})
    public final void endNalUnit(long j, int i, int i2, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.detectAccessUnits) {
            this.sps.endNalUnit(i2);
            this.pps.endNalUnit(i2);
            if (this.hasOutputFormat) {
                NalUnitTargetBuffer nalUnitTargetBuffer = this.sps;
                if (nalUnitTargetBuffer.isCompleted) {
                    NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer.nalData, 3, nalUnitTargetBuffer.nalLength);
                    this.sampleReader.sps.append(spsNalUnit.seqParameterSetId, spsNalUnit);
                    this.sps.reset();
                } else {
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.pps;
                    if (nalUnitTargetBuffer2.isCompleted) {
                        NalUnitUtil.PpsData ppsNalUnit = NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer2.nalData, 3, nalUnitTargetBuffer2.nalLength);
                        this.sampleReader.pps.append(ppsNalUnit.picParameterSetId, ppsNalUnit);
                        this.pps.reset();
                    }
                }
            } else if (this.sps.isCompleted && this.pps.isCompleted) {
                ArrayList arrayList = new ArrayList();
                NalUnitTargetBuffer nalUnitTargetBuffer3 = this.sps;
                arrayList.add(Arrays.copyOf(nalUnitTargetBuffer3.nalData, nalUnitTargetBuffer3.nalLength));
                NalUnitTargetBuffer nalUnitTargetBuffer4 = this.pps;
                arrayList.add(Arrays.copyOf(nalUnitTargetBuffer4.nalData, nalUnitTargetBuffer4.nalLength));
                NalUnitTargetBuffer nalUnitTargetBuffer5 = this.sps;
                NalUnitUtil.SpsData spsNalUnit2 = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer5.nalData, 3, nalUnitTargetBuffer5.nalLength);
                NalUnitTargetBuffer nalUnitTargetBuffer6 = this.pps;
                NalUnitUtil.PpsData ppsNalUnit2 = NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer6.nalData, 3, nalUnitTargetBuffer6.nalLength);
                String strBuildAvcCodecString = CodecSpecificDataUtil.buildAvcCodecString(spsNalUnit2.profileIdc, spsNalUnit2.constraintsFlagsAndReservedZero2Bits, spsNalUnit2.levelIdc);
                TrackOutput trackOutput = this.output;
                Format.Builder builder = new Format.Builder();
                builder.id = this.formatId;
                builder.sampleMimeType = MimeTypes.normalizeMimeType("video/avc");
                builder.codecs = strBuildAvcCodecString;
                builder.width = spsNalUnit2.width;
                builder.height = spsNalUnit2.height;
                ColorInfo.Builder builder2 = new ColorInfo.Builder();
                builder2.colorSpace = spsNalUnit2.colorSpace;
                builder2.colorRange = spsNalUnit2.colorRange;
                builder2.colorTransfer = spsNalUnit2.colorTransfer;
                builder2.lumaBitdepth = spsNalUnit2.bitDepthLumaMinus8 + 8;
                builder2.chromaBitdepth = spsNalUnit2.bitDepthChromaMinus8 + 8;
                builder.colorInfo = builder2.build();
                builder.pixelWidthHeightRatio = spsNalUnit2.pixelWidthHeightRatio;
                builder.initializationData = arrayList;
                trackOutput.format(new Format(builder));
                this.hasOutputFormat = true;
                this.sampleReader.sps.append(spsNalUnit2.seqParameterSetId, spsNalUnit2);
                this.sampleReader.pps.append(ppsNalUnit2.picParameterSetId, ppsNalUnit2);
                this.sps.reset();
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i2)) {
            NalUnitTargetBuffer nalUnitTargetBuffer7 = this.sei;
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer7.nalData, nalUnitTargetBuffer7.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j, i, this.hasOutputFormat)) {
            this.randomAccessIndicator = false;
        }
    }

    @RequiresNonNull({"sampleReader"})
    public final void nalUnitData(byte[] bArr, int i, int i2) {
        if (!this.hasOutputFormat || this.sampleReader.detectAccessUnits) {
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.sei.appendToNalUnit(bArr, i, i2);
        this.sampleReader.appendToNalUnit(bArr, i, i2);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
        this.randomAccessIndicator |= (i & 2) != 0;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0L;
        this.randomAccessIndicator = false;
        this.pesTimeUs = -9223372036854775807L;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
    }

    @RequiresNonNull({"sampleReader"})
    public final void startNalUnit(long j, int i, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.detectAccessUnits) {
            this.sps.startNalUnit(i);
            this.pps.startNalUnit(i);
        }
        this.sei.startNalUnit(i);
        this.sampleReader.startNalUnit(j, i, j2, this.randomAccessIndicator);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
