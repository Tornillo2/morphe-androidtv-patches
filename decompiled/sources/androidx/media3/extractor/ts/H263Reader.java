package androidx.media3.extractor.ts;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class H263Reader implements ElementaryStreamReader {
    public static final float[] PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    public static final int START_CODE_VALUE_GROUP_OF_VOP = 179;
    public static final int START_CODE_VALUE_MAX_VIDEO_OBJECT = 31;
    public static final int START_CODE_VALUE_UNSET = -1;
    public static final int START_CODE_VALUE_USER_DATA = 178;
    public static final int START_CODE_VALUE_VISUAL_OBJECT = 181;
    public static final int START_CODE_VALUE_VISUAL_OBJECT_SEQUENCE = 176;
    public static final int START_CODE_VALUE_VOP = 182;
    public static final String TAG = "H263Reader";
    public static final int VIDEO_OBJECT_LAYER_SHAPE_RECTANGULAR = 0;
    public final CsdBuffer csdBuffer;
    public String formatId;
    public boolean hasOutputFormat;
    public TrackOutput output;
    public long pesTimeUs;
    public final boolean[] prefixFlags;
    public SampleReader sampleReader;
    public long totalBytesWritten;

    @Nullable
    public final NalUnitTargetBuffer userData;

    @Nullable
    public final ParsableByteArray userDataParsable;

    @Nullable
    public final UserDataReader userDataReader;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CsdBuffer {
        public static final byte[] START_CODE = {0, 0, 1};
        public static final int STATE_EXPECT_VIDEO_OBJECT_LAYER_START = 3;
        public static final int STATE_EXPECT_VIDEO_OBJECT_START = 2;
        public static final int STATE_EXPECT_VISUAL_OBJECT_START = 1;
        public static final int STATE_SKIP_TO_VISUAL_OBJECT_SEQUENCE_START = 0;
        public static final int STATE_WAIT_FOR_VOP_START = 4;
        public byte[] data;
        public boolean isFilling;
        public int length;
        public int state;
        public int volStartPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int i4 = this.length;
                if (length < i4 + i3) {
                    this.data = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }

        public boolean onStartCode(int i, int i2) {
            int i3 = this.state;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 != 3) {
                            if (i3 != 4) {
                                throw new IllegalStateException();
                            }
                            if (i == 179 || i == 181) {
                                this.length -= i2;
                                this.isFilling = false;
                                return true;
                            }
                        } else if ((i & 240) != 32) {
                            Log.w("H263Reader", "Unexpected start code value");
                            reset();
                        } else {
                            this.volStartPosition = this.length;
                            this.state = 4;
                        }
                    } else if (i > 31) {
                        Log.w("H263Reader", "Unexpected start code value");
                        reset();
                    } else {
                        this.state = 3;
                    }
                } else if (i != 181) {
                    Log.w("H263Reader", "Unexpected start code value");
                    reset();
                } else {
                    this.state = 2;
                }
            } else if (i == 176) {
                this.state = 1;
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.state = 0;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleReader {
        public static final int OFFSET_VOP_CODING_TYPE = 1;
        public static final int VOP_CODING_TYPE_INTRA = 0;
        public boolean lookingForVopCodingType;
        public final TrackOutput output;
        public boolean readingSample;
        public boolean sampleIsKeyframe;
        public long samplePosition;
        public long sampleTimeUs;
        public int startCodeValue;
        public int vopBytesRead;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.lookingForVopCodingType) {
                int i3 = this.vopBytesRead;
                int i4 = (i + 1) - i3;
                if (i4 >= i2) {
                    this.vopBytesRead = (i2 - i) + i3;
                } else {
                    this.sampleIsKeyframe = ((bArr[i4] & 192) >> 6) == 0;
                    this.lookingForVopCodingType = false;
                }
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
        public void onDataEnd(long j, int i, boolean z) {
            Assertions.checkState(this.sampleTimeUs != -9223372036854775807L);
            if (this.startCodeValue == 182 && z && this.readingSample) {
                this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, (int) (j - this.samplePosition), i, null);
            }
            if (this.startCodeValue != 179) {
                this.samplePosition = j;
            }
        }

        public void onStartCode(int i, long j) {
            this.startCodeValue = i;
            this.sampleIsKeyframe = false;
            this.readingSample = i == 182 || i == 179;
            this.lookingForVopCodingType = i == 182;
            this.vopBytesRead = 0;
            this.sampleTimeUs = j;
        }

        public void reset() {
            this.readingSample = false;
            this.lookingForVopCodingType = false;
            this.sampleIsKeyframe = false;
            this.startCodeValue = -1;
        }
    }

    public H263Reader() {
        this(null);
    }

    public static Format parseCsdBuffer(CsdBuffer csdBuffer, int i, String str) {
        byte[] bArrCopyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArrCopyOf, bArrCopyOf.length);
        parsableBitArray.skipBytes(i);
        parsableBitArray.skipBytes(4);
        parsableBitArray.skipBit();
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        int bits = parsableBitArray.readBits(4);
        float f = 1.0f;
        if (bits == 15) {
            int bits2 = parsableBitArray.readBits(8);
            int bits3 = parsableBitArray.readBits(8);
            if (bits3 == 0) {
                Log.w("H263Reader", "Invalid aspect ratio");
            } else {
                f = bits2 / bits3;
            }
        } else {
            float[] fArr = PIXEL_WIDTH_HEIGHT_RATIO_BY_ASPECT_RATIO_INFO;
            if (bits < fArr.length) {
                f = fArr[bits];
            } else {
                Log.w("H263Reader", "Invalid aspect ratio");
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(3);
                parsableBitArray.skipBits(11);
                parsableBitArray.skipBit();
                parsableBitArray.skipBits(15);
                parsableBitArray.skipBit();
            }
        }
        if (parsableBitArray.readBits(2) != 0) {
            Log.w("H263Reader", "Unhandled video object layer shape");
        }
        parsableBitArray.skipBit();
        int bits4 = parsableBitArray.readBits(16);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            if (bits4 == 0) {
                Log.w("H263Reader", "Invalid vop_increment_time_resolution");
            } else {
                int i2 = 0;
                for (int i3 = bits4 - 1; i3 > 0; i3 >>= 1) {
                    i2++;
                }
                parsableBitArray.skipBits(i2);
            }
        }
        parsableBitArray.skipBit();
        int bits5 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        int bits6 = parsableBitArray.readBits(13);
        parsableBitArray.skipBit();
        parsableBitArray.skipBit();
        Format.Builder builder = new Format.Builder();
        builder.id = str;
        builder.sampleMimeType = MimeTypes.normalizeMimeType("video/mp4v-es");
        builder.width = bits5;
        builder.height = bits6;
        builder.pixelWidthHeightRatio = f;
        builder.initializationData = Collections.singletonList(bArrCopyOf);
        return new Format(builder);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.sampleReader);
        Assertions.checkStateNotNull(this.output);
        int i = parsableByteArray.position;
        int i2 = parsableByteArray.limit;
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, i, i2, this.prefixFlags);
            if (iFindNalUnit == i2) {
                break;
            }
            int i3 = iFindNalUnit + 3;
            int i4 = parsableByteArray.data[i3] & 255;
            int i5 = iFindNalUnit - i;
            int i6 = 0;
            if (!this.hasOutputFormat) {
                if (i5 > 0) {
                    this.csdBuffer.onData(bArr, i, iFindNalUnit);
                }
                if (this.csdBuffer.onStartCode(i4, i5 < 0 ? -i5 : 0)) {
                    TrackOutput trackOutput = this.output;
                    CsdBuffer csdBuffer = this.csdBuffer;
                    int i7 = csdBuffer.volStartPosition;
                    String str = this.formatId;
                    str.getClass();
                    trackOutput.format(parseCsdBuffer(csdBuffer, i7, str));
                    this.hasOutputFormat = true;
                }
            }
            this.sampleReader.onData(bArr, i, iFindNalUnit);
            NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
            if (nalUnitTargetBuffer != null) {
                if (i5 > 0) {
                    nalUnitTargetBuffer.appendToNalUnit(bArr, i, iFindNalUnit);
                } else {
                    i6 = -i5;
                }
                if (this.userData.endNalUnit(i6)) {
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.userData;
                    int iUnescapeStream = NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength);
                    ParsableByteArray parsableByteArray2 = this.userDataParsable;
                    Util.castNonNull(parsableByteArray2);
                    parsableByteArray2.reset(this.userData.nalData, iUnescapeStream);
                    this.userDataReader.consume(this.pesTimeUs, this.userDataParsable);
                }
                if (i4 == 178 && parsableByteArray.data[iFindNalUnit + 2] == 1) {
                    this.userData.startNalUnit(i4);
                }
            }
            int i8 = i2 - iFindNalUnit;
            this.sampleReader.onDataEnd(this.totalBytesWritten - ((long) i8), i8, this.hasOutputFormat);
            this.sampleReader.onStartCode(i4, this.pesTimeUs);
            i = i3;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(bArr, i, i2);
        }
        this.sampleReader.onData(bArr, i, i2);
        NalUnitTargetBuffer nalUnitTargetBuffer3 = this.userData;
        if (nalUnitTargetBuffer3 != null) {
            nalUnitTargetBuffer3.appendToNalUnit(bArr, i, i2);
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
        this.sampleReader = new SampleReader(trackOutputTrack);
        UserDataReader userDataReader = this.userDataReader;
        if (userDataReader != null) {
            userDataReader.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
        NalUnitTargetBuffer nalUnitTargetBuffer = this.userData;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.reset();
        }
        this.totalBytesWritten = 0L;
        this.pesTimeUs = -9223372036854775807L;
    }

    public H263Reader(@Nullable UserDataReader userDataReader) {
        this.userDataReader = userDataReader;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        this.pesTimeUs = -9223372036854775807L;
        if (userDataReader != null) {
            this.userData = new NalUnitTargetBuffer(178, 128);
            this.userDataParsable = new ParsableByteArray();
        } else {
            this.userData = null;
            this.userDataParsable = null;
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }
}
