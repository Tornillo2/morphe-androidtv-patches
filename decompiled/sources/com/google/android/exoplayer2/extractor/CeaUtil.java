package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CeaUtil {
    public static final int COUNTRY_CODE = 181;
    public static final int PAYLOAD_TYPE_CC = 4;
    public static final int PROVIDER_CODE_ATSC = 49;
    public static final int PROVIDER_CODE_DIRECTV = 47;
    public static final String TAG = "CeaUtil";
    public static final int USER_DATA_IDENTIFIER_GA94 = 1195456820;
    public static final int USER_DATA_TYPE_CODE_MPEG_CC = 3;

    public static void consume(long j, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        while (true) {
            if (parsableByteArray.bytesLeft() <= 1) {
                return;
            }
            int non255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
            int non255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
            int i = parsableByteArray.position + non255TerminatedValue2;
            if (non255TerminatedValue2 == -1 || non255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                i = parsableByteArray.limit;
            } else if (non255TerminatedValue == 4 && non255TerminatedValue2 >= 8) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedShort = parsableByteArray.readUnsignedShort();
                int i2 = unsignedShort == 49 ? parsableByteArray.readInt() : 0;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                if (unsignedShort == 47) {
                    parsableByteArray.skipBytes(1);
                }
                boolean z = unsignedByte == 181 && (unsignedShort == 49 || unsignedShort == 47) && unsignedByte2 == 3;
                if (unsignedShort == 49) {
                    z &= i2 == 1195456820;
                }
                if (z) {
                    consumeCcData(j, parsableByteArray, trackOutputArr);
                }
            }
            parsableByteArray.setPosition(i);
        }
    }

    public static void consumeCcData(long j, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        long j2;
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(1);
            int i = (unsignedByte & 31) * 3;
            int i2 = parsableByteArray.position;
            int length = trackOutputArr.length;
            int i3 = 0;
            while (i3 < length) {
                TrackOutput trackOutput = trackOutputArr[i3];
                parsableByteArray.setPosition(i2);
                trackOutput.sampleData(parsableByteArray, i);
                if (j != -9223372036854775807L) {
                    j2 = j;
                    trackOutput.sampleMetadata(j2, 1, i, 0, null);
                } else {
                    j2 = j;
                }
                i3++;
                j = j2;
            }
        }
    }

    public static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            i += unsignedByte;
            if (unsignedByte != 255) {
                return i;
            }
        }
        return -1;
    }
}
