package androidx.media3.extractor.flv;

import androidx.annotation.Nullable;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.DummyTrackOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ScriptTagPayloadReader extends TagPayloadReader {
    public static final int AMF_TYPE_BOOLEAN = 1;
    public static final int AMF_TYPE_DATE = 11;
    public static final int AMF_TYPE_ECMA_ARRAY = 8;
    public static final int AMF_TYPE_END_MARKER = 9;
    public static final int AMF_TYPE_NUMBER = 0;
    public static final int AMF_TYPE_OBJECT = 3;
    public static final int AMF_TYPE_STRICT_ARRAY = 10;
    public static final int AMF_TYPE_STRING = 2;
    public static final String KEY_DURATION = "duration";
    public static final String KEY_FILE_POSITIONS = "filepositions";
    public static final String KEY_KEY_FRAMES = "keyframes";
    public static final String KEY_TIMES = "times";
    public static final String NAME_METADATA = "onMetaData";
    public long durationUs;
    public long[] keyFrameTagPositions;
    public long[] keyFrameTimesUs;

    public ScriptTagPayloadReader() {
        super(new DummyTrackOutput());
        this.durationUs = -9223372036854775807L;
        this.keyFrameTimesUs = new long[0];
        this.keyFrameTagPositions = new long[0];
    }

    public static Boolean readAmfBoolean(ParsableByteArray parsableByteArray) {
        return Boolean.valueOf(parsableByteArray.readUnsignedByte() == 1);
    }

    @Nullable
    public static Object readAmfData(ParsableByteArray parsableByteArray, int i) {
        if (i == 0) {
            return readAmfDouble(parsableByteArray);
        }
        if (i == 1) {
            return readAmfBoolean(parsableByteArray);
        }
        if (i == 2) {
            return readAmfString(parsableByteArray);
        }
        if (i == 3) {
            return readAmfObject(parsableByteArray);
        }
        if (i == 8) {
            return readAmfEcmaArray(parsableByteArray);
        }
        if (i == 10) {
            return readAmfStrictArray(parsableByteArray);
        }
        if (i != 11) {
            return null;
        }
        return readAmfDate(parsableByteArray);
    }

    public static Date readAmfDate(ParsableByteArray parsableByteArray) {
        Date date = new Date((long) readAmfDouble(parsableByteArray).doubleValue());
        parsableByteArray.skipBytes(2);
        return date;
    }

    public static Double readAmfDouble(ParsableByteArray parsableByteArray) {
        return Double.valueOf(Double.longBitsToDouble(parsableByteArray.readLong()));
    }

    public static HashMap<String, Object> readAmfEcmaArray(ParsableByteArray parsableByteArray) {
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        HashMap<String, Object> map = new HashMap<>(unsignedIntToInt);
        for (int i = 0; i < unsignedIntToInt; i++) {
            String amfString = readAmfString(parsableByteArray);
            Object amfData = readAmfData(parsableByteArray, parsableByteArray.readUnsignedByte());
            if (amfData != null) {
                map.put(amfString, amfData);
            }
        }
        return map;
    }

    public static HashMap<String, Object> readAmfObject(ParsableByteArray parsableByteArray) {
        HashMap<String, Object> map = new HashMap<>();
        while (true) {
            String amfString = readAmfString(parsableByteArray);
            int unsignedByte = parsableByteArray.readUnsignedByte();
            if (unsignedByte == 9) {
                return map;
            }
            Object amfData = readAmfData(parsableByteArray, unsignedByte);
            if (amfData != null) {
                map.put(amfString, amfData);
            }
        }
    }

    public static ArrayList<Object> readAmfStrictArray(ParsableByteArray parsableByteArray) {
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        ArrayList<Object> arrayList = new ArrayList<>(unsignedIntToInt);
        for (int i = 0; i < unsignedIntToInt; i++) {
            Object amfData = readAmfData(parsableByteArray, parsableByteArray.readUnsignedByte());
            if (amfData != null) {
                arrayList.add(amfData);
            }
        }
        return arrayList;
    }

    public static String readAmfString(ParsableByteArray parsableByteArray) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int i = parsableByteArray.position;
        parsableByteArray.skipBytes(unsignedShort);
        return new String(parsableByteArray.data, i, unsignedShort);
    }

    public static int readAmfType(ParsableByteArray parsableByteArray) {
        return parsableByteArray.readUnsignedByte();
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long[] getKeyFrameTagPositions() {
        return this.keyFrameTagPositions;
    }

    public long[] getKeyFrameTimesUs() {
        return this.keyFrameTimesUs;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray parsableByteArray) {
        return true;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray parsableByteArray, long j) {
        if (parsableByteArray.readUnsignedByte() != 2 || !"onMetaData".equals(readAmfString(parsableByteArray)) || parsableByteArray.bytesLeft() == 0 || parsableByteArray.readUnsignedByte() != 8) {
            return false;
        }
        HashMap<String, Object> amfEcmaArray = readAmfEcmaArray(parsableByteArray);
        Object obj = amfEcmaArray.get("duration");
        if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            if (dDoubleValue > 0.0d) {
                this.durationUs = (long) (dDoubleValue * 1000000.0d);
            }
        }
        Object obj2 = amfEcmaArray.get("keyframes");
        if (obj2 instanceof Map) {
            Map map = (Map) obj2;
            Object obj3 = map.get("filepositions");
            Object obj4 = map.get("times");
            if ((obj3 instanceof List) && (obj4 instanceof List)) {
                List list = (List) obj3;
                List list2 = (List) obj4;
                int size = list2.size();
                this.keyFrameTimesUs = new long[size];
                this.keyFrameTagPositions = new long[size];
                for (int i = 0; i < size; i++) {
                    Object obj5 = list.get(i);
                    Object obj6 = list2.get(i);
                    if (!(obj6 instanceof Double) || !(obj5 instanceof Double)) {
                        this.keyFrameTimesUs = new long[0];
                        this.keyFrameTagPositions = new long[0];
                        break;
                    }
                    this.keyFrameTimesUs[i] = (long) (((Double) obj6).doubleValue() * 1000000.0d);
                    this.keyFrameTagPositions[i] = ((Double) obj5).longValue();
                }
            }
        }
        return false;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public void seek() {
    }
}
