package kotlin.uuid;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nUuidJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n1#1,277:1\n277#1:278\n277#1:279\n277#1:280\n277#1:281\n277#1:282\n277#1:283\n277#1:284\n277#1:285\n*S KotlinDebug\n*F\n+ 1 UuidJVM.kt\nkotlin/uuid/UuidKt__UuidJVMKt\n*L\n139#1:278\n140#1:279\n184#1:280\n185#1:281\n224#1:282\n225#1:283\n271#1:284\n272#1:285\n*E\n"})
public class UuidKt__UuidJVMKt {
    @ExperimentalUuidApi
    public static final void formatBytesInto(long j, @NotNull byte[] dst, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        UuidKt__UuidKt.formatBytesIntoCommonImpl(j, dst, i, i2, i3);
    }

    @ExperimentalUuidApi
    public static final long getLongAt(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return UuidKt__UuidKt.getLongAtCommonImpl(bArr, i);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final Uuid getUuid(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        if (byteBuffer.position() + 15 >= byteBuffer.limit()) {
            throw new BufferUnderflowException();
        }
        long jReverseBytes = byteBuffer.getLong();
        long jReverseBytes2 = byteBuffer.getLong();
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.LITTLE_ENDIAN)) {
            jReverseBytes = Long.reverseBytes(jReverseBytes);
            jReverseBytes2 = Long.reverseBytes(jReverseBytes2);
        }
        return Uuid.Companion.fromLongs(jReverseBytes, jReverseBytes2);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final ByteBuffer putUuid(@NotNull ByteBuffer byteBuffer, int i, @NotNull Uuid uuid) {
        ByteBuffer byteBufferPutLong;
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        long j = uuid.mostSignificantBits;
        long j2 = uuid.leastSignificantBits;
        if (i < 0) {
            throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Negative index: ", i));
        }
        if (i + 15 >= byteBuffer.limit()) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Not enough capacity to write a uuid at index: ", i, ", with limit: ");
            sbM.append(byteBuffer.limit());
            sbM.append(' ');
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.BIG_ENDIAN)) {
            byteBuffer.putLong(i, j);
            byteBufferPutLong = byteBuffer.putLong(i + 8, j2);
        } else {
            byteBuffer.putLong(i, Long.reverseBytes(j));
            byteBufferPutLong = byteBuffer.putLong(i + 8, Long.reverseBytes(j2));
        }
        Intrinsics.checkNotNullExpressionValue(byteBufferPutLong, "toLongs(...)");
        return byteBufferPutLong;
    }

    public static final long reverseBytes(long j) {
        return Long.reverseBytes(j);
    }

    @ExperimentalUuidApi
    @NotNull
    public static final Uuid secureRandomUuid() {
        byte[] bArr = new byte[16];
        SecureRandomHolder.INSTANCE.getClass();
        SecureRandomHolder.instance.nextBytes(bArr);
        return UuidKt__UuidKt.uuidFromRandomBytes(bArr);
    }

    @ExperimentalUuidApi
    @NotNull
    public static final Object serializedUuid(@NotNull Uuid uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new UuidSerialized(uuid.mostSignificantBits, uuid.leastSignificantBits);
    }

    @ExperimentalUuidApi
    public static final void setLongAt(@NotNull byte[] bArr, int i, long j) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        UuidKt__UuidKt.setLongAtCommonImpl(bArr, i, j);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final UUID toJavaUuid(@NotNull Uuid uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return new UUID(uuid.mostSignificantBits, uuid.leastSignificantBits);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final Uuid toKotlinUuid(@NotNull UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<this>");
        return Uuid.Companion.fromLongs(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    @ExperimentalUuidApi
    @NotNull
    public static final Uuid uuidParseHex(@NotNull String hexString) {
        Intrinsics.checkNotNullParameter(hexString, "hexString");
        return UuidKt__UuidKt.uuidParseHexCommonImpl(hexString);
    }

    @ExperimentalUuidApi
    @NotNull
    public static final Uuid uuidParseHexDash(@NotNull String hexDashString) {
        Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
        return UuidKt__UuidKt.uuidParseHexDashCommonImpl(hexDashString);
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final Uuid getUuid(@NotNull ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        if (i >= 0) {
            if (i + 15 < byteBuffer.limit()) {
                long jReverseBytes = byteBuffer.getLong(i);
                long jReverseBytes2 = byteBuffer.getLong(i + 8);
                if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.LITTLE_ENDIAN)) {
                    jReverseBytes = Long.reverseBytes(jReverseBytes);
                    jReverseBytes2 = Long.reverseBytes(jReverseBytes2);
                }
                return Uuid.Companion.fromLongs(jReverseBytes, jReverseBytes2);
            }
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Not enough bytes to read a uuid at index: ", i, ", with limit: ");
            sbM.append(byteBuffer.limit());
            sbM.append(' ');
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        throw new IndexOutOfBoundsException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Negative index: ", i));
    }

    @SinceKotlin(version = "2.0")
    @ExperimentalUuidApi
    @NotNull
    public static final ByteBuffer putUuid(@NotNull ByteBuffer byteBuffer, @NotNull Uuid uuid) {
        ByteBuffer byteBufferPutLong;
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        long j = uuid.mostSignificantBits;
        long j2 = uuid.leastSignificantBits;
        if (byteBuffer.position() + 15 < byteBuffer.limit()) {
            if (Intrinsics.areEqual(byteBuffer.order(), ByteOrder.BIG_ENDIAN)) {
                byteBuffer.putLong(j);
                byteBufferPutLong = byteBuffer.putLong(j2);
            } else {
                byteBuffer.putLong(Long.reverseBytes(j));
                byteBufferPutLong = byteBuffer.putLong(Long.reverseBytes(j2));
            }
            Intrinsics.checkNotNullExpressionValue(byteBufferPutLong, "toLongs(...)");
            return byteBufferPutLong;
        }
        throw new BufferOverflowException();
    }
}
