package androidx.media3.extractor.mp4;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.nio.ByteBuffer;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class PsshAtomUtil {
    public static final String TAG = "PsshAtomUtil";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PsshAtom {

        @Nullable
        public final UUID[] keyIds;
        public final byte[] schemeData;
        public final UUID uuid;
        public final int version;

        public PsshAtom(UUID uuid, int i, byte[] bArr, @Nullable UUID[] uuidArr) {
            this.uuid = uuid;
            this.version = i;
            this.schemeData = bArr;
            this.keyIds = uuidArr;
        }
    }

    public static byte[] buildPsshAtom(UUID uuid, @Nullable byte[] bArr) {
        return buildPsshAtom(uuid, null, bArr);
    }

    public static boolean isPsshAtom(byte[] bArr) {
        return parsePsshAtom(bArr) != null;
    }

    @Nullable
    public static PsshAtom parsePsshAtom(byte[] bArr) {
        UUID[] uuidArr;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (parsableByteArray.limit < 32) {
            return null;
        }
        parsableByteArray.setPosition(0);
        int iBytesLeft = parsableByteArray.bytesLeft();
        int i = parsableByteArray.readInt();
        if (i != iBytesLeft) {
            Log.w("PsshAtomUtil", "Advertised atom size (" + i + ") does not match buffer size: " + iBytesLeft);
            return null;
        }
        int i2 = parsableByteArray.readInt();
        if (i2 != 1886614376) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Atom type is not pssh: ", i2, "PsshAtomUtil");
            return null;
        }
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        if (fullAtomVersion > 1) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unsupported pssh version: ", fullAtomVersion, "PsshAtomUtil");
            return null;
        }
        UUID uuid = new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
        if (fullAtomVersion == 1) {
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            uuidArr = new UUID[unsignedIntToInt];
            for (int i3 = 0; i3 < unsignedIntToInt; i3++) {
                uuidArr[i3] = new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
            }
        } else {
            uuidArr = null;
        }
        int unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        int iBytesLeft2 = parsableByteArray.bytesLeft();
        if (unsignedIntToInt2 == iBytesLeft2) {
            byte[] bArr2 = new byte[unsignedIntToInt2];
            parsableByteArray.readBytes(bArr2, 0, unsignedIntToInt2);
            return new PsshAtom(uuid, fullAtomVersion, bArr2, uuidArr);
        }
        Log.w("PsshAtomUtil", "Atom data size (" + unsignedIntToInt2 + ") does not match the bytes left: " + iBytesLeft2);
        return null;
    }

    @Nullable
    public static byte[] parseSchemeSpecificData(byte[] bArr, UUID uuid) {
        PsshAtom psshAtom = parsePsshAtom(bArr);
        if (psshAtom == null) {
            return null;
        }
        if (uuid.equals(psshAtom.uuid)) {
            return psshAtom.schemeData;
        }
        Log.w("PsshAtomUtil", "UUID mismatch. Expected: " + uuid + ", got: " + psshAtom.uuid + ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        return null;
    }

    @Nullable
    public static UUID parseUuid(byte[] bArr) {
        PsshAtom psshAtom = parsePsshAtom(bArr);
        if (psshAtom == null) {
            return null;
        }
        return psshAtom.uuid;
    }

    public static int parseVersion(byte[] bArr) {
        PsshAtom psshAtom = parsePsshAtom(bArr);
        if (psshAtom == null) {
            return -1;
        }
        return psshAtom.version;
    }

    public static byte[] buildPsshAtom(UUID uuid, @Nullable UUID[] uuidArr, @Nullable byte[] bArr) {
        int length = (bArr != null ? bArr.length : 0) + 32;
        if (uuidArr != null) {
            length += (uuidArr.length * 16) + 4;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length);
        byteBufferAllocate.putInt(1886614376);
        byteBufferAllocate.putInt(uuidArr != null ? 16777216 : 0);
        byteBufferAllocate.putLong(uuid.getMostSignificantBits());
        byteBufferAllocate.putLong(uuid.getLeastSignificantBits());
        if (uuidArr != null) {
            byteBufferAllocate.putInt(uuidArr.length);
            for (UUID uuid2 : uuidArr) {
                byteBufferAllocate.putLong(uuid2.getMostSignificantBits());
                byteBufferAllocate.putLong(uuid2.getLeastSignificantBits());
            }
        }
        if (bArr == null || bArr.length == 0) {
            byteBufferAllocate.putInt(0);
        } else {
            byteBufferAllocate.putInt(bArr.length);
            byteBufferAllocate.put(bArr);
        }
        return byteBufferAllocate.array();
    }
}
