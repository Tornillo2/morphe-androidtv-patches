package androidx.media3.extractor.mkv;

import android.net.Uri;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.base.Ascii;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class MatroskaExtractor implements Extractor {
    public static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    public static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    public static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    public static final int BLOCK_STATE_DATA = 2;
    public static final int BLOCK_STATE_HEADER = 1;
    public static final int BLOCK_STATE_START = 0;
    public static final String CODEC_ID_AAC = "A_AAC";
    public static final String CODEC_ID_AC3 = "A_AC3";
    public static final String CODEC_ID_ACM = "A_MS/ACM";
    public static final String CODEC_ID_ASS = "S_TEXT/ASS";
    public static final String CODEC_ID_AV1 = "V_AV1";
    public static final String CODEC_ID_DTS = "A_DTS";
    public static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    public static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    public static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    public static final String CODEC_ID_E_AC3 = "A_EAC3";
    public static final String CODEC_ID_FLAC = "A_FLAC";
    public static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    public static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    public static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    public static final String CODEC_ID_MP2 = "A_MPEG/L2";
    public static final String CODEC_ID_MP3 = "A_MPEG/L3";
    public static final String CODEC_ID_MPEG2 = "V_MPEG2";
    public static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    public static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    public static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    public static final String CODEC_ID_OPUS = "A_OPUS";
    public static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    public static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    public static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    public static final String CODEC_ID_PGS = "S_HDMV/PGS";
    public static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    public static final String CODEC_ID_THEORA = "V_THEORA";
    public static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    public static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    public static final String CODEC_ID_VORBIS = "A_VORBIS";
    public static final String CODEC_ID_VP8 = "V_VP8";
    public static final String CODEC_ID_VP9 = "V_VP9";
    public static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    public static final String DOC_TYPE_MATROSKA = "matroska";
    public static final String DOC_TYPE_WEBM = "webm";
    public static final int ENCRYPTION_IV_SIZE = 8;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 2;
    public static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    public static final int FOURCC_COMPRESSION_H263 = 859189832;
    public static final int FOURCC_COMPRESSION_VC1 = 826496599;
    public static final int ID_AUDIO = 225;
    public static final int ID_AUDIO_BIT_DEPTH = 25188;
    public static final int ID_BLOCK = 161;
    public static final int ID_BLOCK_ADDITIONAL = 165;
    public static final int ID_BLOCK_ADDITIONS = 30113;
    public static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    public static final int ID_BLOCK_ADD_ID = 238;
    public static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    public static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    public static final int ID_BLOCK_DURATION = 155;
    public static final int ID_BLOCK_GROUP = 160;
    public static final int ID_BLOCK_MORE = 166;
    public static final int ID_CHANNELS = 159;
    public static final int ID_CLUSTER = 524531317;
    public static final int ID_CODEC_DELAY = 22186;
    public static final int ID_CODEC_ID = 134;
    public static final int ID_CODEC_PRIVATE = 25506;
    public static final int ID_COLOUR = 21936;
    public static final int ID_COLOUR_BITS_PER_CHANNEL = 21938;
    public static final int ID_COLOUR_PRIMARIES = 21947;
    public static final int ID_COLOUR_RANGE = 21945;
    public static final int ID_COLOUR_TRANSFER = 21946;
    public static final int ID_CONTENT_COMPRESSION = 20532;
    public static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    public static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    public static final int ID_CONTENT_ENCODING = 25152;
    public static final int ID_CONTENT_ENCODINGS = 28032;
    public static final int ID_CONTENT_ENCODING_ORDER = 20529;
    public static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    public static final int ID_CONTENT_ENCRYPTION = 20533;
    public static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    public static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    public static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    public static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    public static final int ID_CUES = 475249515;
    public static final int ID_CUE_CLUSTER_POSITION = 241;
    public static final int ID_CUE_POINT = 187;
    public static final int ID_CUE_TIME = 179;
    public static final int ID_CUE_TRACK_POSITIONS = 183;
    public static final int ID_DEFAULT_DURATION = 2352003;
    public static final int ID_DISCARD_PADDING = 30114;
    public static final int ID_DISPLAY_HEIGHT = 21690;
    public static final int ID_DISPLAY_UNIT = 21682;
    public static final int ID_DISPLAY_WIDTH = 21680;
    public static final int ID_DOC_TYPE = 17026;
    public static final int ID_DOC_TYPE_READ_VERSION = 17029;
    public static final int ID_DURATION = 17545;
    public static final int ID_EBML = 440786851;
    public static final int ID_EBML_READ_VERSION = 17143;
    public static final int ID_FLAG_DEFAULT = 136;
    public static final int ID_FLAG_FORCED = 21930;
    public static final int ID_INFO = 357149030;
    public static final int ID_LANGUAGE = 2274716;
    public static final int ID_LUMNINANCE_MAX = 21977;
    public static final int ID_LUMNINANCE_MIN = 21978;
    public static final int ID_MASTERING_METADATA = 21968;
    public static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    public static final int ID_MAX_CLL = 21948;
    public static final int ID_MAX_FALL = 21949;
    public static final int ID_NAME = 21358;
    public static final int ID_PIXEL_HEIGHT = 186;
    public static final int ID_PIXEL_WIDTH = 176;
    public static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    public static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    public static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    public static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    public static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    public static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    public static final int ID_PROJECTION = 30320;
    public static final int ID_PROJECTION_POSE_PITCH = 30324;
    public static final int ID_PROJECTION_POSE_ROLL = 30325;
    public static final int ID_PROJECTION_POSE_YAW = 30323;
    public static final int ID_PROJECTION_PRIVATE = 30322;
    public static final int ID_PROJECTION_TYPE = 30321;
    public static final int ID_REFERENCE_BLOCK = 251;
    public static final int ID_SAMPLING_FREQUENCY = 181;
    public static final int ID_SEEK = 19899;
    public static final int ID_SEEK_HEAD = 290298740;
    public static final int ID_SEEK_ID = 21419;
    public static final int ID_SEEK_POSITION = 21420;
    public static final int ID_SEEK_PRE_ROLL = 22203;
    public static final int ID_SEGMENT = 408125543;
    public static final int ID_SEGMENT_INFO = 357149030;
    public static final int ID_SIMPLE_BLOCK = 163;
    public static final int ID_STEREO_MODE = 21432;
    public static final int ID_TIMECODE_SCALE = 2807729;
    public static final int ID_TIME_CODE = 231;
    public static final int ID_TRACKS = 374648427;
    public static final int ID_TRACK_ENTRY = 174;
    public static final int ID_TRACK_NUMBER = 215;
    public static final int ID_TRACK_TYPE = 131;
    public static final int ID_VIDEO = 224;
    public static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    public static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    public static final int LACING_EBML = 3;
    public static final int LACING_FIXED_SIZE = 2;
    public static final int LACING_NONE = 0;
    public static final int LACING_XIPH = 1;
    public static final int OPUS_MAX_INPUT_SIZE = 5760;
    public static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    public static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    public static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    public static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    public static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    public static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    public static final String TAG = "MatroskaExtractor";
    public static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    public static final int TRACK_TYPE_AUDIO = 2;
    public static final int UNSET_ENTRY_ID = -1;
    public static final int VORBIS_MAX_INPUT_SIZE = 8192;
    public static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    public static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    public static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    public static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    public static final int WAVE_FORMAT_PCM = 1;
    public static final int WAVE_FORMAT_SIZE = 18;
    public int blockAdditionalId;
    public long blockDurationUs;
    public int blockFlags;
    public long blockGroupDiscardPaddingNs;
    public boolean blockHasReferenceBlock;
    public int blockSampleCount;
    public int blockSampleIndex;
    public int[] blockSampleSizes;
    public int blockState;
    public long blockTimeUs;
    public int blockTrackNumber;
    public int blockTrackNumberLength;
    public long clusterTimecodeUs;

    @Nullable
    public LongArray cueClusterPositions;

    @Nullable
    public LongArray cueTimesUs;
    public long cuesContentPosition;

    @Nullable
    public Track currentTrack;
    public long durationTimecode;
    public long durationUs;
    public final ParsableByteArray encryptionInitializationVector;
    public final ParsableByteArray encryptionSubsampleData;
    public ByteBuffer encryptionSubsampleDataBuffer;
    public ExtractorOutput extractorOutput;
    public boolean haveOutputSample;
    public final ParsableByteArray nalLength;
    public final ParsableByteArray nalStartCode;
    public final boolean parseSubtitlesDuringExtraction;
    public final EbmlReader reader;
    public int sampleBytesRead;
    public int sampleBytesWritten;
    public int sampleCurrentNalBytesRemaining;
    public boolean sampleEncodingHandled;
    public boolean sampleInitializationVectorRead;
    public int samplePartitionCount;
    public boolean samplePartitionCountRead;
    public byte sampleSignalByte;
    public boolean sampleSignalByteRead;
    public final ParsableByteArray sampleStrippedBytes;
    public final ParsableByteArray scratch;
    public int seekEntryId;
    public final ParsableByteArray seekEntryIdBytes;
    public long seekEntryPosition;
    public boolean seekForCues;
    public final boolean seekForCuesEnabled;
    public long seekPositionAfterBuildingCues;
    public boolean seenClusterPositionForCurrentCuePoint;
    public long segmentContentPosition;
    public long segmentContentSize;
    public boolean sentSeekMap;
    public final SubtitleParser.Factory subtitleParserFactory;
    public final ParsableByteArray subtitleSample;
    public final ParsableByteArray supplementalData;
    public long timecodeScale;
    public final SparseArray<Track> tracks;
    public final VarintReader varintReader;
    public final ParsableByteArray vorbisNumPageSamples;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new MatroskaExtractor$$ExternalSyntheticLambda1();
    public static final byte[] SUBRIP_PREFIX = {TarConstants.LF_LINK, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    public static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    public static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class InnerEbmlProcessor implements EbmlProcessor {
        public InnerEbmlProcessor() {
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Track {
        public static final int DEFAULT_MAX_CLL = 1000;
        public static final int DEFAULT_MAX_FALL = 200;
        public static final int DISPLAY_UNIT_PIXELS = 0;
        public static final int MAX_CHROMATICITY = 50000;
        public int blockAddIdType;
        public String codecId;
        public byte[] codecPrivate;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagForced;
        public boolean hasContentEncryption;
        public int maxBlockAdditionId;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public byte[] sampleStrippedBytes;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public int width = -1;
        public int height = -1;
        public int bitsPerChannel = -1;
        public int displayWidth = -1;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int projectionType = -1;
        public float projectionPoseYaw = 0.0f;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public byte[] projectionData = null;
        public int stereoMode = -1;
        public boolean hasColorInfo = false;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public int colorRange = -1;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int channelCount = 1;
        public int audioBitDepth = -1;
        public int sampleRate = 8000;
        public long codecDelayNs = 0;
        public long seekPreRollNs = 0;
        public boolean flagDefault = true;
        public String language = "eng";

        public static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (littleEndianUnsignedInt == 1482049860) {
                    return new Pair<>("video/divx", null);
                }
                if (littleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", null);
                }
                if (littleEndianUnsignedInt != 826496599) {
                    Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                    return new Pair<>("video/x-unknown", null);
                }
                byte[] bArr = parsableByteArray.data;
                for (int i = parsableByteArray.position + 20; i < bArr.length - 4; i++) {
                    if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 1 && bArr[i + 3] == 15) {
                        return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, i, bArr.length)));
                    }
                }
                throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
            }
        }

        public static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort == 65534) {
                    parsableByteArray.setPosition(24);
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits()) {
                        if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
            }
        }

        public static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            int i;
            int i2;
            try {
                if (bArr[0] != 2) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i3 = 1;
                int i4 = 0;
                while (true) {
                    i = bArr[i3];
                    if ((i & 255) != 255) {
                        break;
                    }
                    i4 += 255;
                    i3++;
                }
                int i5 = i3 + 1;
                int i6 = i4 + (i & 255);
                int i7 = 0;
                while (true) {
                    i2 = bArr[i5];
                    if ((i2 & 255) != 255) {
                        break;
                    }
                    i7 += 255;
                    i5++;
                }
                int i8 = i5 + 1;
                int i9 = i7 + (i2 & 255);
                if (bArr[i8] != 1) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr2 = new byte[i6];
                System.arraycopy(bArr, i8, bArr2, 0, i6);
                int i10 = i8 + i6;
                if (bArr[i10] != 3) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i11 = i10 + i9;
                if (bArr[i11] != 5) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr3 = new byte[bArr.length - i11];
                System.arraycopy(bArr, i11, bArr3, 0, bArr.length - i11);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
            }
        }

        @EnsuresNonNull({"output"})
        public final void assertOutputInitialized() {
            this.output.getClass();
        }

        @EnsuresNonNull({"codecPrivate"})
        public final byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, null);
        }

        @Nullable
        public final byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.put((byte) 0);
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) (this.maxMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) (this.minMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) this.maxContentLuminance);
            byteBufferOrder.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:209:0x042d  */
        /* JADX WARN: Removed duplicated region for block: B:214:0x0446  */
        /* JADX WARN: Removed duplicated region for block: B:215:0x0448  */
        /* JADX WARN: Removed duplicated region for block: B:218:0x0455  */
        /* JADX WARN: Removed duplicated region for block: B:219:0x0462  */
        /* JADX WARN: Removed duplicated region for block: B:285:0x0571  */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
        @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"this.output"})
        @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void initializeOutput(androidx.media3.extractor.ExtractorOutput r20, int r21) throws androidx.media3.common.ParserException {
            /*
                Method dump skipped, instruction units count: 1660
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.Track.initializeOutput(androidx.media3.extractor.ExtractorOutput, int):void");
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
        }

        public final boolean samplesHaveSupplementalData(boolean z) {
            return "A_OPUS".equals(this.codecId) ? z : this.maxBlockAdditionId > 0;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Qltx3R0UUReCHx-33a_Adj1tag8, reason: not valid java name */
    public static /* synthetic */ Extractor[] m178$r8$lambda$Qltx3R0UUReCHx33a_Adj1tag8(SubtitleParser.Factory factory) {
        return new Extractor[]{new MatroskaExtractor(factory)};
    }

    /* JADX INFO: renamed from: $r8$lambda$kMhMytRa6Vcol9U_-_NkwwlQDB4, reason: not valid java name */
    public static /* synthetic */ Extractor[] m179$r8$lambda$kMhMytRa6Vcol9U__NkwwlQDB4() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    static {
        HashMap map = new HashMap();
        map.put("htc_video_rotA-000", 0);
        map.put("htc_video_rotA-090", 90);
        map.put("htc_video_rotA-180", 180);
        map.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = DesugarCollections.unmodifiableMap(map);
    }

    @Deprecated
    public MatroskaExtractor() {
        this(new DefaultEbmlReader(), 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    public static int[] ensureArrayCapacity(@Nullable int[] iArr, int i) {
        return iArr == null ? new int[i] : iArr.length >= i ? iArr : new int[Math.max(iArr.length * 2, i)];
    }

    public static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Assertions.checkArgument(j != -9223372036854775807L);
        int i = (int) (j / 3600000000L);
        long j3 = j - (((long) i) * 3600000000L);
        int i2 = (int) (j3 / 60000000);
        long j4 = j3 - (((long) i2) * 60000000);
        int i3 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - (((long) i3) * 1000000)) / j2))));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean isCodecSupported(String str) {
        str.getClass();
        byte b = -1;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals("V_MPEG4/ISO/AP")) {
                    b = 0;
                }
                break;
            case -2095575984:
                if (str.equals("V_MPEG4/ISO/SP")) {
                    b = 1;
                }
                break;
            case -1985379776:
                if (str.equals("A_MS/ACM")) {
                    b = 2;
                }
                break;
            case -1784763192:
                if (str.equals("A_TRUEHD")) {
                    b = 3;
                }
                break;
            case -1730367663:
                if (str.equals("A_VORBIS")) {
                    b = 4;
                }
                break;
            case -1482641358:
                if (str.equals("A_MPEG/L2")) {
                    b = 5;
                }
                break;
            case -1482641357:
                if (str.equals("A_MPEG/L3")) {
                    b = 6;
                }
                break;
            case -1373388978:
                if (str.equals("V_MS/VFW/FOURCC")) {
                    b = 7;
                }
                break;
            case -933872740:
                if (str.equals("S_DVBSUB")) {
                    b = 8;
                }
                break;
            case -538363189:
                if (str.equals("V_MPEG4/ISO/ASP")) {
                    b = 9;
                }
                break;
            case -538363109:
                if (str.equals("V_MPEG4/ISO/AVC")) {
                    b = 10;
                }
                break;
            case -425012669:
                if (str.equals("S_VOBSUB")) {
                    b = Ascii.VT;
                }
                break;
            case -356037306:
                if (str.equals("A_DTS/LOSSLESS")) {
                    b = Ascii.FF;
                }
                break;
            case 62923557:
                if (str.equals("A_AAC")) {
                    b = 13;
                }
                break;
            case 62923603:
                if (str.equals("A_AC3")) {
                    b = Ascii.SO;
                }
                break;
            case 62927045:
                if (str.equals("A_DTS")) {
                    b = Ascii.SI;
                }
                break;
            case 82318131:
                if (str.equals("V_AV1")) {
                    b = 16;
                }
                break;
            case 82338133:
                if (str.equals("V_VP8")) {
                    b = 17;
                }
                break;
            case 82338134:
                if (str.equals("V_VP9")) {
                    b = Ascii.DC2;
                }
                break;
            case 99146302:
                if (str.equals("S_HDMV/PGS")) {
                    b = 19;
                }
                break;
            case 444813526:
                if (str.equals("V_THEORA")) {
                    b = Ascii.DC4;
                }
                break;
            case 542569478:
                if (str.equals("A_DTS/EXPRESS")) {
                    b = Ascii.NAK;
                }
                break;
            case 635596514:
                if (str.equals("A_PCM/FLOAT/IEEE")) {
                    b = Ascii.SYN;
                }
                break;
            case 725948237:
                if (str.equals("A_PCM/INT/BIG")) {
                    b = Ascii.ETB;
                }
                break;
            case 725957860:
                if (str.equals("A_PCM/INT/LIT")) {
                    b = Ascii.CAN;
                }
                break;
            case 738597099:
                if (str.equals("S_TEXT/ASS")) {
                    b = Ascii.EM;
                }
                break;
            case 855502857:
                if (str.equals("V_MPEGH/ISO/HEVC")) {
                    b = Ascii.SUB;
                }
                break;
            case 1045209816:
                if (str.equals("S_TEXT/WEBVTT")) {
                    b = Ascii.ESC;
                }
                break;
            case 1422270023:
                if (str.equals("S_TEXT/UTF8")) {
                    b = Ascii.FS;
                }
                break;
            case 1809237540:
                if (str.equals("V_MPEG2")) {
                    b = Ascii.GS;
                }
                break;
            case 1950749482:
                if (str.equals("A_EAC3")) {
                    b = Ascii.RS;
                }
                break;
            case 1950789798:
                if (str.equals("A_FLAC")) {
                    b = 31;
                }
                break;
            case 1951062397:
                if (str.equals("A_OPUS")) {
                    b = 32;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return true;
            default:
                return false;
        }
    }

    public static ExtractorsFactory newFactory(final SubtitleParser.Factory factory) {
        return new ExtractorsFactory() { // from class: androidx.media3.extractor.mkv.MatroskaExtractor$$ExternalSyntheticLambda0
            @Override // androidx.media3.extractor.ExtractorsFactory
            public final Extractor[] createExtractors() {
                return MatroskaExtractor.m178$r8$lambda$Qltx3R0UUReCHx33a_Adj1tag8(factory);
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
                ExtractorsFactory.CC.$default$experimentalSetTextTrackTranscodingEnabled(this, z);
                return this;
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory2) {
                ExtractorsFactory.CC.$default$setSubtitleParserFactory(this, factory2);
                return this;
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
                return createExtractors();
            }
        };
    }

    public static void setSubtitleEndTime(String str, long j, byte[] bArr) {
        byte[] subtitleTimecode;
        int i;
        str.getClass();
        switch (str) {
            case "S_TEXT/ASS":
                subtitleTimecode = formatSubtitleTimecode(j, "%01d:%02d:%02d:%02d", 10000L);
                i = 21;
                break;
            case "S_TEXT/WEBVTT":
                subtitleTimecode = formatSubtitleTimecode(j, "%02d:%02d:%02d.%03d", 1000L);
                i = 25;
                break;
            case "S_TEXT/UTF8":
                subtitleTimecode = formatSubtitleTimecode(j, "%02d:%02d:%02d,%03d", 1000L);
                i = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(subtitleTimecode, 0, bArr, i, subtitleTimecode.length);
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    public final void assertInCues(int i) throws ParserException {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + i + " must be in a Cues", null);
        }
    }

    @EnsuresNonNull({"currentTrack"})
    public final void assertInTrackEntry(int i) throws ParserException {
        if (this.currentTrack != null) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + i + " must be in a TrackEntry", null);
    }

    @CallSuper
    public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
        char c;
        char c2;
        int i3;
        long j;
        int i4;
        int i5;
        int i6;
        ExtractorInput extractorInput2 = extractorInput;
        int i7 = 0;
        int i8 = 1;
        if (i != 161 && i != 163) {
            if (i == 165) {
                if (this.blockState != 2) {
                    return;
                }
                handleBlockAdditionalData(this.tracks.get(this.blockTrackNumber), this.blockAdditionalId, extractorInput2, i2);
                return;
            }
            if (i == 16877) {
                handleBlockAddIDExtraData(getCurrentTrack(i), extractorInput2, i2);
                return;
            }
            if (i == 16981) {
                assertInTrackEntry(i);
                byte[] bArr = new byte[i2];
                this.currentTrack.sampleStrippedBytes = bArr;
                extractorInput2.readFully(bArr, 0, i2);
                return;
            }
            if (i == 18402) {
                byte[] bArr2 = new byte[i2];
                extractorInput2.readFully(bArr2, 0, i2);
                getCurrentTrack(i).cryptoData = new TrackOutput.CryptoData(1, bArr2, 0, 0);
                return;
            }
            if (i == 21419) {
                Arrays.fill(this.seekEntryIdBytes.data, (byte) 0);
                extractorInput2.readFully(this.seekEntryIdBytes.data, 4 - i2, i2);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i == 25506) {
                assertInTrackEntry(i);
                byte[] bArr3 = new byte[i2];
                this.currentTrack.codecPrivate = bArr3;
                extractorInput2.readFully(bArr3, 0, i2);
                return;
            }
            if (i != 30322) {
                throw ParserException.createForMalformedContainer("Unexpected id: " + i, null);
            }
            assertInTrackEntry(i);
            byte[] bArr4 = new byte[i2];
            this.currentTrack.projectionData = bArr4;
            extractorInput2.readFully(bArr4, 0, i2);
            return;
        }
        int i9 = 8;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput2, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.length;
            this.blockDurationUs = -9223372036854775807L;
            this.blockState = 1;
            this.scratch.reset(0);
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput2.skipFully(i2 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        track.assertOutputInitialized();
        if (this.blockState == 1) {
            readScratch(extractorInput2, 3);
            int i10 = (this.scratch.data[2] & 6) >> 1;
            if (i10 == 0) {
                this.blockSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockSampleSizes, 1);
                this.blockSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i2 - this.blockTrackNumberLength) - 3;
            } else {
                readScratch(extractorInput2, 4);
                int i11 = (this.scratch.data[3] & 255) + 1;
                this.blockSampleCount = i11;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockSampleSizes, i11);
                this.blockSampleSizes = iArrEnsureArrayCapacity2;
                if (i10 == 2) {
                    int i12 = (i2 - this.blockTrackNumberLength) - 4;
                    int i13 = this.blockSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i13, i12 / i13);
                } else {
                    if (i10 != 1) {
                        if (i10 != 3) {
                            throw ParserException.createForMalformedContainer("Unexpected lacing value: " + i10, null);
                        }
                        int i14 = 0;
                        int i15 = 0;
                        int i16 = 4;
                        while (true) {
                            int i17 = this.blockSampleCount - i8;
                            if (i14 >= i17) {
                                c = 1;
                                c2 = 0;
                                this.blockSampleSizes[i17] = ((i2 - this.blockTrackNumberLength) - i16) - i15;
                                break;
                            }
                            this.blockSampleSizes[i14] = i7;
                            int i18 = i16 + 1;
                            readScratch(extractorInput2, i18);
                            if (this.scratch.data[i16] == 0) {
                                throw ParserException.createForMalformedContainer("No valid varint length mask found", null);
                            }
                            int i19 = 0;
                            while (true) {
                                if (i19 >= i9) {
                                    i3 = i14;
                                    j = 0;
                                    i16 = i18;
                                    break;
                                }
                                int i20 = 1 << (7 - i19);
                                if ((this.scratch.data[i16] & i20) != 0) {
                                    int i21 = i18 + i19;
                                    readScratch(extractorInput2, i21);
                                    j = this.scratch.data[i16] & 255 & (~i20);
                                    while (i18 < i21) {
                                        j = (j << 8) | ((long) (this.scratch.data[i18] & 255));
                                        i18++;
                                        i14 = i14;
                                    }
                                    i3 = i14;
                                    if (i3 > 0) {
                                        j -= (1 << ((i19 * 7) + 6)) - 1;
                                    }
                                    i16 = i21;
                                } else {
                                    i19++;
                                    extractorInput2 = extractorInput;
                                    i9 = 8;
                                }
                            }
                            if (j < -2147483648L || j > 2147483647L) {
                                break;
                            }
                            int i22 = (int) j;
                            int[] iArr = this.blockSampleSizes;
                            if (i3 != 0) {
                                i22 += iArr[i3 - 1];
                            }
                            iArr[i3] = i22;
                            i15 += i22;
                            i14 = i3 + 1;
                            extractorInput2 = extractorInput;
                            i7 = 0;
                            i8 = 1;
                            i9 = 8;
                        }
                        throw ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
                    }
                    int i23 = 0;
                    int i24 = 0;
                    int i25 = 4;
                    while (true) {
                        i4 = this.blockSampleCount - 1;
                        if (i23 >= i4) {
                            break;
                        }
                        this.blockSampleSizes[i23] = 0;
                        while (true) {
                            i5 = i25 + 1;
                            readScratch(extractorInput2, i5);
                            int i26 = this.scratch.data[i25] & 255;
                            int[] iArr2 = this.blockSampleSizes;
                            i6 = iArr2[i23] + i26;
                            iArr2[i23] = i6;
                            if (i26 != 255) {
                                break;
                            } else {
                                i25 = i5;
                            }
                        }
                        i24 += i6;
                        i23++;
                        i25 = i5;
                    }
                    this.blockSampleSizes[i4] = ((i2 - this.blockTrackNumberLength) - i25) - i24;
                }
            }
            c = 1;
            c2 = 0;
            byte[] bArr5 = this.scratch.data;
            this.blockTimeUs = scaleTimecodeToUs((bArr5[c] & 255) | (bArr5[c2] << 8)) + this.clusterTimecodeUs;
            this.blockFlags = (track.type == 2 || (i == 163 && (this.scratch.data[2] & 128) == 128)) ? 1 : 0;
            this.blockState = 2;
            this.blockSampleIndex = 0;
        }
        if (i == 163) {
            while (true) {
                int i27 = this.blockSampleIndex;
                if (i27 >= this.blockSampleCount) {
                    this.blockState = 0;
                    return;
                }
                int iWriteSampleData = writeSampleData(extractorInput, track, this.blockSampleSizes[i27], false);
                Track track2 = track;
                commitSampleToOutput(track2, this.blockTimeUs + ((long) ((this.blockSampleIndex * track.defaultSampleDurationNs) / 1000)), this.blockFlags, iWriteSampleData, 0);
                this.blockSampleIndex++;
                track = track2;
            }
        } else {
            while (true) {
                int i28 = this.blockSampleIndex;
                if (i28 >= this.blockSampleCount) {
                    return;
                }
                int[] iArr3 = this.blockSampleSizes;
                iArr3[i28] = writeSampleData(extractorInput, track, iArr3[i28], true);
                this.blockSampleIndex++;
            }
        }
    }

    public final SeekMap buildSeekMap(@Nullable LongArray longArray, @Nullable LongArray longArray2) {
        int i;
        int i2;
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || longArray == null || (i = longArray.size) == 0 || longArray2 == null || longArray2.size != i) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int[] iArrCopyOf = new int[i];
        long[] jArrCopyOf = new long[i];
        long[] jArrCopyOf2 = new long[i];
        long[] jArrCopyOf3 = new long[i];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            jArrCopyOf3[i4] = longArray.get(i4);
            jArrCopyOf[i4] = longArray2.get(i4) + this.segmentContentPosition;
        }
        while (true) {
            i2 = i - 1;
            if (i3 >= i2) {
                break;
            }
            int i5 = i3 + 1;
            iArrCopyOf[i3] = (int) (jArrCopyOf[i5] - jArrCopyOf[i3]);
            jArrCopyOf2[i3] = jArrCopyOf3[i5] - jArrCopyOf3[i3];
            i3 = i5;
        }
        iArrCopyOf[i2] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArrCopyOf[i2]);
        long j = this.durationUs - jArrCopyOf3[i2];
        jArrCopyOf2[i2] = j;
        if (j <= 0) {
            Log.w("MatroskaExtractor", "Discarding last cue point with unexpected duration: " + j);
            iArrCopyOf = Arrays.copyOf(iArrCopyOf, i2);
            jArrCopyOf = Arrays.copyOf(jArrCopyOf, i2);
            jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i2);
            jArrCopyOf3 = Arrays.copyOf(jArrCopyOf3, i2);
        }
        return new ChunkIndex(iArrCopyOf, jArrCopyOf, jArrCopyOf2, jArrCopyOf3);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor.Track r18, long r19, int r21, int r22, int r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.media3.extractor.TrueHdSampleRechunker r2 = r1.trueHdSampleRechunker
            r9 = 1
            if (r2 == 0) goto L1c
            r3 = r2
            androidx.media3.extractor.TrackOutput r2 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r8 = r1.cryptoData
            r5 = r21
            r6 = r22
            r7 = r23
            r1 = r3
            r3 = r19
            r1.sampleMetadata(r2, r3, r5, r6, r7, r8)
            goto Lb4
        L1c:
            java.lang.String r2 = "S_TEXT/UTF8"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3a
            java.lang.String r2 = "S_TEXT/ASS"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3a
            java.lang.String r2 = "S_TEXT/WEBVTT"
            java.lang.String r3 = r1.codecId
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L56
        L3a:
            int r2 = r0.blockSampleCount
            java.lang.String r3 = "MatroskaExtractor"
            if (r2 <= r9) goto L46
            java.lang.String r2 = "Skipping subtitle sample in laced block."
            androidx.media3.common.util.Log.w(r3, r2)
            goto L56
        L46:
            long r4 = r0.blockDurationUs
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L59
            java.lang.String r2 = "Skipping subtitle sample with no duration."
            androidx.media3.common.util.Log.w(r3, r2)
        L56:
            r2 = r22
            goto L88
        L59:
            java.lang.String r2 = r1.codecId
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            byte[] r3 = r3.data
            setSubtitleEndTime(r2, r4, r3)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.position
        L66:
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            int r4 = r3.limit
            if (r2 >= r4) goto L79
            byte[] r4 = r3.data
            r4 = r4[r2]
            if (r4 != 0) goto L76
            r3.setLimit(r2)
            goto L79
        L76:
            int r2 = r2 + 1
            goto L66
        L79:
            androidx.media3.extractor.TrackOutput r2 = r1.output
            androidx.media3.common.util.ParsableByteArray r3 = r0.subtitleSample
            int r4 = r3.limit
            r2.sampleData(r3, r4)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.limit
            int r2 = r22 + r2
        L88:
            r3 = 268435456(0x10000000, float:2.524355E-29)
            r3 = r21 & r3
            if (r3 == 0) goto La4
            int r3 = r0.blockSampleCount
            if (r3 <= r9) goto L99
            androidx.media3.common.util.ParsableByteArray r3 = r0.supplementalData
            r4 = 0
            r3.reset(r4)
            goto La4
        L99:
            androidx.media3.common.util.ParsableByteArray r3 = r0.supplementalData
            int r4 = r3.limit
            androidx.media3.extractor.TrackOutput r5 = r1.output
            r6 = 2
            r5.sampleData(r3, r4, r6)
            int r2 = r2 + r4
        La4:
            r14 = r2
            androidx.media3.extractor.TrackOutput r10 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r1 = r1.cryptoData
            r11 = r19
            r13 = r21
            r15 = r23
            r16 = r1
            r10.sampleMetadata(r11, r13, r14, r15, r16)
        Lb4:
            r0.haveOutputSample = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    @CallSuper
    public void endMasterElement(int i) throws ParserException {
        assertInitialized();
        if (i == 160) {
            if (this.blockState != 2) {
                return;
            }
            Track track = this.tracks.get(this.blockTrackNumber);
            track.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && "A_OPUS".equals(track.codecId)) {
                ParsableByteArray parsableByteArray = this.supplementalData;
                byte[] bArrArray = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array();
                parsableByteArray.getClass();
                parsableByteArray.reset(bArrArray, bArrArray.length);
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.blockSampleCount; i3++) {
                i2 += this.blockSampleSizes[i3];
            }
            int i4 = 0;
            while (i4 < this.blockSampleCount) {
                long j = this.blockTimeUs + ((long) ((track.defaultSampleDurationNs * i4) / 1000));
                int i5 = this.blockFlags;
                if (i4 == 0 && !this.blockHasReferenceBlock) {
                    i5 |= 1;
                }
                int i6 = this.blockSampleSizes[i4];
                int i7 = i2 - i6;
                commitSampleToOutput(track, j, i5, i6, i7);
                i4++;
                i2 = i7;
            }
            this.blockState = 0;
            return;
        }
        if (i == 174) {
            Track track2 = this.currentTrack;
            Assertions.checkStateNotNull(track2);
            String str = track2.codecId;
            if (str == null) {
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
            }
            if (isCodecSupported(str)) {
                track2.initializeOutput(this.extractorOutput, track2.number);
                this.tracks.put(track2.number, track2);
            }
            this.currentTrack = null;
            return;
        }
        if (i == 19899) {
            int i8 = this.seekEntryId;
            if (i8 != -1) {
                long j2 = this.seekEntryPosition;
                if (j2 != -1) {
                    if (i8 == 475249515) {
                        this.cuesContentPosition = j2;
                        return;
                    }
                    return;
                }
            }
            throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
        }
        if (i == 25152) {
            assertInTrackEntry(i);
            Track track3 = this.currentTrack;
            if (track3.hasContentEncryption) {
                TrackOutput.CryptoData cryptoData = track3.cryptoData;
                if (cryptoData == null) {
                    throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
                }
                track3.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, null, "video/webm", cryptoData.encryptionKey));
                return;
            }
            return;
        }
        if (i == 28032) {
            assertInTrackEntry(i);
            Track track4 = this.currentTrack;
            if (track4.hasContentEncryption && track4.sampleStrippedBytes != null) {
                throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
            }
            return;
        }
        if (i == 357149030) {
            if (this.timecodeScale == -9223372036854775807L) {
                this.timecodeScale = 1000000L;
            }
            long j3 = this.durationTimecode;
            if (j3 != -9223372036854775807L) {
                this.durationUs = scaleTimecodeToUs(j3);
                return;
            }
            return;
        }
        if (i == 374648427) {
            if (this.tracks.size() == 0) {
                throw ParserException.createForMalformedContainer("No valid tracks were found", null);
            }
            this.extractorOutput.endTracks();
        } else {
            if (i != 475249515) {
                return;
            }
            if (!this.sentSeekMap) {
                this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                this.sentSeekMap = true;
            }
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
        }
    }

    public final int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    @CallSuper
    public void floatElement(int i, double d) throws ParserException {
        if (i == 181) {
            getCurrentTrack(i).sampleRate = (int) d;
            return;
        }
        if (i == 17545) {
            this.durationTimecode = (long) d;
            return;
        }
        switch (i) {
            case 21969:
                getCurrentTrack(i).primaryRChromaticityX = (float) d;
                break;
            case 21970:
                getCurrentTrack(i).primaryRChromaticityY = (float) d;
                break;
            case 21971:
                getCurrentTrack(i).primaryGChromaticityX = (float) d;
                break;
            case 21972:
                getCurrentTrack(i).primaryGChromaticityY = (float) d;
                break;
            case 21973:
                getCurrentTrack(i).primaryBChromaticityX = (float) d;
                break;
            case 21974:
                getCurrentTrack(i).primaryBChromaticityY = (float) d;
                break;
            case 21975:
                getCurrentTrack(i).whitePointChromaticityX = (float) d;
                break;
            case 21976:
                getCurrentTrack(i).whitePointChromaticityY = (float) d;
                break;
            case 21977:
                getCurrentTrack(i).maxMasteringLuminance = (float) d;
                break;
            case 21978:
                getCurrentTrack(i).minMasteringLuminance = (float) d;
                break;
            default:
                switch (i) {
                    case 30323:
                        getCurrentTrack(i).projectionPoseYaw = (float) d;
                        break;
                    case 30324:
                        getCurrentTrack(i).projectionPosePitch = (float) d;
                        break;
                    case 30325:
                        getCurrentTrack(i).projectionPoseRoll = (float) d;
                        break;
                }
                break;
        }
    }

    public Track getCurrentTrack(int i) throws ParserException {
        assertInTrackEntry(i);
        return this.currentTrack;
    }

    @CallSuper
    public int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 238:
            case 241:
            case 251:
            case 16871:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 21998:
            case 22186:
            case 22203:
            case 25188:
            case 30114:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 21358:
            case 2274716:
                return 3;
            case 160:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 16868:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30113:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 165:
            case 16877:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i) throws IOException {
        int i2 = track.blockAddIdType;
        if (i2 != 1685485123 && i2 != 1685480259) {
            extractorInput.skipFully(i);
            return;
        }
        byte[] bArr = new byte[i];
        track.dolbyVisionConfigBytes = bArr;
        extractorInput.readFully(bArr, 0, i);
    }

    public void handleBlockAdditionalData(Track track, int i, ExtractorInput extractorInput, int i2) throws IOException {
        if (i != 4 || !"V_VP9".equals(track.codecId)) {
            extractorInput.skipFully(i2);
        } else {
            this.supplementalData.reset(i2);
            extractorInput.readFully(this.supplementalData.data, 0, i2);
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
    }

    @CallSuper
    public void integerElement(int i, long j) throws ParserException {
        if (i == 20529) {
            if (j == 0) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j + " not supported", null);
        }
        if (i == 20530) {
            if (j == 1) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingScope " + j + " not supported", null);
        }
        switch (i) {
            case 131:
                getCurrentTrack(i).type = (int) j;
                return;
            case 136:
                getCurrentTrack(i).flagDefault = j == 1;
                return;
            case 155:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case 159:
                getCurrentTrack(i).channelCount = (int) j;
                return;
            case 176:
                getCurrentTrack(i).width = (int) j;
                return;
            case 179:
                assertInCues(i);
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case 186:
                getCurrentTrack(i).height = (int) j;
                return;
            case 215:
                getCurrentTrack(i).number = (int) j;
                return;
            case 231:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case 238:
                this.blockAdditionalId = (int) j;
                return;
            case 241:
                if (this.seenClusterPositionForCurrentCuePoint) {
                    return;
                }
                assertInCues(i);
                this.cueClusterPositions.add(j);
                this.seenClusterPositionForCurrentCuePoint = true;
                return;
            case 251:
                this.blockHasReferenceBlock = true;
                return;
            case 16871:
                getCurrentTrack(i).blockAddIdType = (int) j;
                return;
            case 16980:
                if (j == 3) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentCompAlgo " + j + " not supported", null);
            case 17029:
                if (j < 1 || j > 2) {
                    throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j + " not supported", null);
                }
                return;
            case 17143:
                if (j == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("EBMLReadVersion " + j + " not supported", null);
            case 18401:
                if (j == 5) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentEncAlgo " + j + " not supported", null);
            case 18408:
                if (j == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j + " not supported", null);
            case 21420:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case 21432:
                int i2 = (int) j;
                assertInTrackEntry(i);
                if (i2 == 0) {
                    this.currentTrack.stereoMode = 0;
                    return;
                }
                if (i2 == 1) {
                    this.currentTrack.stereoMode = 2;
                    return;
                } else if (i2 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else {
                    if (i2 != 15) {
                        return;
                    }
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case 21680:
                getCurrentTrack(i).displayWidth = (int) j;
                return;
            case 21682:
                getCurrentTrack(i).displayUnit = (int) j;
                return;
            case 21690:
                getCurrentTrack(i).displayHeight = (int) j;
                return;
            case 21930:
                getCurrentTrack(i).flagForced = j == 1;
                return;
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
                assertInTrackEntry(i);
                Track track = this.currentTrack;
                track.hasColorInfo = true;
                track.bitsPerChannel = (int) j;
                return;
            case 21998:
                getCurrentTrack(i).maxBlockAdditionId = (int) j;
                return;
            case 22186:
                getCurrentTrack(i).codecDelayNs = j;
                return;
            case 22203:
                getCurrentTrack(i).seekPreRollNs = j;
                return;
            case 25188:
                getCurrentTrack(i).audioBitDepth = (int) j;
                return;
            case 30114:
                this.blockGroupDiscardPaddingNs = j;
                return;
            case 30321:
                assertInTrackEntry(i);
                int i3 = (int) j;
                if (i3 == 0) {
                    this.currentTrack.projectionType = 0;
                    return;
                }
                if (i3 == 1) {
                    this.currentTrack.projectionType = 1;
                    return;
                } else if (i3 == 2) {
                    this.currentTrack.projectionType = 2;
                    return;
                } else {
                    if (i3 != 3) {
                        return;
                    }
                    this.currentTrack.projectionType = 3;
                    return;
                }
            case 2352003:
                getCurrentTrack(i).defaultSampleDurationNs = (int) j;
                return;
            case 2807729:
                this.timecodeScale = j;
                return;
            default:
                switch (i) {
                    case 21945:
                        assertInTrackEntry(i);
                        int i4 = (int) j;
                        if (i4 == 1) {
                            this.currentTrack.colorRange = 2;
                            return;
                        } else {
                            if (i4 != 2) {
                                return;
                            }
                            this.currentTrack.colorRange = 1;
                            return;
                        }
                    case 21946:
                        assertInTrackEntry(i);
                        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j);
                        if (iIsoTransferCharacteristicsToColorTransfer != -1) {
                            this.currentTrack.colorTransfer = iIsoTransferCharacteristicsToColorTransfer;
                            return;
                        }
                        return;
                    case 21947:
                        assertInTrackEntry(i);
                        this.currentTrack.hasColorInfo = true;
                        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j);
                        if (iIsoColorPrimariesToColorSpace != -1) {
                            this.currentTrack.colorSpace = iIsoColorPrimariesToColorSpace;
                            return;
                        }
                        return;
                    case 21948:
                        getCurrentTrack(i).maxContentLuminance = (int) j;
                        return;
                    case 21949:
                        getCurrentTrack(i).maxFrameAverageLuminance = (int) j;
                        return;
                    default:
                        return;
                }
        }
    }

    @CallSuper
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public final boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            Track trackValueAt = this.tracks.valueAt(i);
            trackValueAt.assertOutputInitialized();
            trackValueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    public final void readScratch(ExtractorInput extractorInput, int i) throws IOException {
        ParsableByteArray parsableByteArray = this.scratch;
        if (parsableByteArray.limit >= i) {
            return;
        }
        byte[] bArr = parsableByteArray.data;
        if (bArr.length < i) {
            parsableByteArray.ensureCapacity(Math.max(bArr.length * 2, i));
        }
        ParsableByteArray parsableByteArray2 = this.scratch;
        byte[] bArr2 = parsableByteArray2.data;
        int i2 = parsableByteArray2.limit;
        extractorInput.readFully(bArr2, i2, i - i2);
        this.scratch.setLimit(i);
    }

    public final void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    public final long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != -9223372036854775807L) {
            return Util.scaleLargeValue(j, j2, 1000L, RoundingMode.FLOOR);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
    }

    @Override // androidx.media3.extractor.Extractor
    @CallSuper
    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @CallSuper
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        assertInitialized();
        if (i == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0L;
            return;
        }
        if (i == 174) {
            this.currentTrack = new Track();
            return;
        }
        if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
            return;
        }
        if (i == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i == 20533) {
            getCurrentTrack(i).hasContentEncryption = true;
            return;
        }
        if (i == 21968) {
            getCurrentTrack(i).hasColorInfo = true;
            return;
        }
        if (i == 408125543) {
            long j3 = this.segmentContentPosition;
            if (j3 != -1 && j3 != j) {
                throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
            }
            this.segmentContentPosition = j;
            this.segmentContentSize = j2;
            return;
        }
        if (i == 475249515) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i == 524531317 && !this.sentSeekMap) {
            if (this.seekForCuesEnabled && this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
            }
        }
    }

    @CallSuper
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            getCurrentTrack(i).codecId = str;
            return;
        }
        if (i != 17026) {
            if (i == 21358) {
                getCurrentTrack(i).name = str;
                return;
            } else {
                if (i != 2274716) {
                    return;
                }
                getCurrentTrack(i).language = str;
                return;
            }
        }
        if ("webm".equals(str) || "matroska".equals(str)) {
            return;
        }
        throw ParserException.createForMalformedContainer("DocType " + str + " not supported", null);
    }

    @RequiresNonNull({"#2.output"})
    public final int writeSampleData(ExtractorInput extractorInput, Track track, int i, boolean z) throws IOException {
        int i2;
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
            int i3 = this.sampleBytesWritten;
            resetWriteSampleData();
            return i3;
        }
        if ("S_TEXT/ASS".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
            int i4 = this.sampleBytesWritten;
            resetWriteSampleData();
            return i4;
        }
        if ("S_TEXT/WEBVTT".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i);
            int i5 = this.sampleBytesWritten;
            resetWriteSampleData();
            return i5;
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    byte b = this.scratch.data[0];
                    if ((b & 128) == 128) {
                        throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
                    }
                    this.sampleSignalByte = b;
                    this.sampleSignalByteRead = true;
                }
                byte b2 = this.sampleSignalByte;
                if ((b2 & 1) == 1) {
                    boolean z2 = (b2 & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        ParsableByteArray parsableByteArray = this.scratch;
                        parsableByteArray.data[0] = (byte) ((z2 ? 128 : 0) | 8);
                        parsableByteArray.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                        this.sampleBytesWritten += 8;
                    }
                    if (z2) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i6 = this.samplePartitionCount * 4;
                        this.scratch.reset(i6);
                        extractorInput.readFully(this.scratch.data, 0, i6);
                        this.sampleBytesRead += i6;
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i7 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i7) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i7);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s);
                        int i8 = 0;
                        int i9 = 0;
                        while (true) {
                            i2 = this.samplePartitionCount;
                            if (i8 >= i2) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i8 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (unsignedIntToInt - i9));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(unsignedIntToInt - i9);
                            }
                            i8++;
                            i9 = unsignedIntToInt;
                        }
                        int i10 = (i - this.sampleBytesRead) - i9;
                        if (i2 % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i10);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i10);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i7);
                        trackOutput.sampleData(this.encryptionSubsampleData, i7, 1);
                        this.sampleBytesWritten += i7;
                    }
                }
            } else {
                byte[] bArr = track.sampleStrippedBytes;
                if (bArr != null) {
                    this.sampleStrippedBytes.reset(bArr, bArr.length);
                }
            }
            if (track.samplesHaveSupplementalData(z)) {
                this.blockFlags |= 268435456;
                this.supplementalData.reset(0);
                int i11 = (this.sampleStrippedBytes.limit + i) - this.sampleBytesRead;
                this.scratch.reset(4);
                ParsableByteArray parsableByteArray2 = this.scratch;
                byte[] bArr2 = parsableByteArray2.data;
                bArr2[0] = (byte) ((i11 >> 24) & 255);
                bArr2[1] = (byte) ((i11 >> 16) & 255);
                bArr2[2] = (byte) ((i11 >> 8) & 255);
                bArr2[3] = (byte) (i11 & 255);
                trackOutput.sampleData(parsableByteArray2, 4, 2);
                this.sampleBytesWritten += 4;
            }
            this.sampleEncodingHandled = true;
        }
        int i12 = i + this.sampleStrippedBytes.limit;
        if (!"V_MPEG4/ISO/AVC".equals(track.codecId) && !"V_MPEGH/ISO/HEVC".equals(track.codecId)) {
            if (track.trueHdSampleRechunker != null) {
                Assertions.checkState(this.sampleStrippedBytes.limit == 0);
                track.trueHdSampleRechunker.startSample(extractorInput);
            }
            while (true) {
                int i13 = this.sampleBytesRead;
                if (i13 >= i12) {
                    break;
                }
                int iWriteToOutput = writeToOutput(extractorInput, trackOutput, i12 - i13);
                this.sampleBytesRead += iWriteToOutput;
                this.sampleBytesWritten += iWriteToOutput;
            }
        } else {
            byte[] bArr3 = this.nalLength.data;
            bArr3[0] = 0;
            bArr3[1] = 0;
            bArr3[2] = 0;
            int i14 = track.nalUnitLengthFieldLength;
            int i15 = 4 - i14;
            while (this.sampleBytesRead < i12) {
                int i16 = this.sampleCurrentNalBytesRemaining;
                if (i16 == 0) {
                    writeToTarget(extractorInput, bArr3, i15, i14);
                    this.sampleBytesRead += i14;
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    int iWriteToOutput2 = writeToOutput(extractorInput, trackOutput, i16);
                    this.sampleBytesRead += iWriteToOutput2;
                    this.sampleBytesWritten += iWriteToOutput2;
                    this.sampleCurrentNalBytesRemaining -= iWriteToOutput2;
                }
            }
        }
        if ("A_VORBIS".equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
        int i17 = this.sampleBytesWritten;
        resetWriteSampleData();
        return i17;
    }

    public final void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException {
        int length = bArr.length + i;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        byte[] bArr2 = parsableByteArray.data;
        if (bArr2.length < length) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, length + i);
            parsableByteArray.getClass();
            parsableByteArray.reset(bArrCopyOf, bArrCopyOf.length);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    public final int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException {
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i, false);
        }
        int iMin = Math.min(i, iBytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, iMin);
        return iMin;
    }

    public final void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException {
        int iMin = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + iMin, i2 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, iMin);
        }
    }

    @Deprecated
    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i | 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory) {
        this(new DefaultEbmlReader(), 0, factory);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i) {
        this(new DefaultEbmlReader(), i, factory);
    }

    public MatroskaExtractor(EbmlReader ebmlReader, int i, SubtitleParser.Factory factory) {
        this.segmentContentPosition = -1L;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.subtitleParserFactory = factory;
        this.seekForCuesEnabled = (i & 1) == 0;
        this.parseSubtitlesDuringExtraction = (i & 2) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this;
    }

    @Override // androidx.media3.extractor.Extractor
    public final void release() {
    }
}
