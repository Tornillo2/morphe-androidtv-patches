package kotlin.uuid;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import java.io.Serializable;
import java.util.Comparator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "2.0")
@ExperimentalUuidApi
public final class Uuid implements Comparable<Uuid>, Serializable {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Uuid NIL = new Uuid(0, 0);
    public static final int SIZE_BITS = 128;
    public static final int SIZE_BYTES = 16;
    public final long leastSignificantBits;
    public final long mostSignificantBits;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final Uuid fromByteArray(@NotNull byte[] byteArray) {
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            if (byteArray.length == 16) {
                return fromLongs(UuidKt__UuidKt.getLongAtCommonImpl(byteArray, 0), UuidKt__UuidKt.getLongAtCommonImpl(byteArray, 8));
            }
            throw new IllegalArgumentException(("Expected exactly 16 bytes, but was " + UuidKt__UuidKt.truncateForErrorMessage$UuidKt__UuidKt(byteArray, 32) + " of size " + byteArray.length).toString());
        }

        @NotNull
        public final Uuid fromLongs(long j, long j2) {
            return (j == 0 && j2 == 0) ? Uuid.NIL : new Uuid(j, j2);
        }

        @SinceKotlin(version = "2.1")
        @ExperimentalUnsignedTypes
        @NotNull
        /* JADX INFO: renamed from: fromUByteArray-GBYM_sE, reason: not valid java name */
        public final Uuid m2065fromUByteArrayGBYM_sE(@NotNull byte[] ubyteArray) {
            Intrinsics.checkNotNullParameter(ubyteArray, "ubyteArray");
            return fromByteArray(ubyteArray);
        }

        @NotNull
        /* JADX INFO: renamed from: fromULongs-eb3DHEI, reason: not valid java name */
        public final Uuid m2066fromULongseb3DHEI(long j, long j2) {
            return fromLongs(j, j2);
        }

        @NotNull
        public final Comparator<Uuid> getLEXICAL_ORDER() {
            return ComparisonsKt__ComparisonsKt.naturalOrder();
        }

        @NotNull
        public final Uuid getNIL() {
            return Uuid.NIL;
        }

        @NotNull
        public final Uuid parse(@NotNull String uuidString) {
            Intrinsics.checkNotNullParameter(uuidString, "uuidString");
            int length = uuidString.length();
            if (length == 32) {
                return UuidKt__UuidKt.uuidParseHexCommonImpl(uuidString);
            }
            if (length == 36) {
                return UuidKt__UuidKt.uuidParseHexDashCommonImpl(uuidString);
            }
            throw new IllegalArgumentException("Expected either a 36-char string in the standard hex-and-dash UUID format or a 32-char hexadecimal string, but was \"" + UuidKt__UuidKt.truncateForErrorMessage$UuidKt__UuidKt(uuidString, 64) + "\" of length " + uuidString.length());
        }

        @NotNull
        public final Uuid parseHex(@NotNull String hexString) {
            Intrinsics.checkNotNullParameter(hexString, "hexString");
            if (hexString.length() == 32) {
                return UuidKt__UuidKt.uuidParseHexCommonImpl(hexString);
            }
            throw new IllegalArgumentException(("Expected a 32-char hexadecimal string, but was \"" + UuidKt__UuidKt.truncateForErrorMessage$UuidKt__UuidKt(hexString, 64) + "\" of length " + hexString.length()).toString());
        }

        @SinceKotlin(version = "2.1")
        @NotNull
        public final Uuid parseHexDash(@NotNull String hexDashString) {
            Intrinsics.checkNotNullParameter(hexDashString, "hexDashString");
            if (hexDashString.length() == 36) {
                return UuidKt__UuidKt.uuidParseHexDashCommonImpl(hexDashString);
            }
            throw new IllegalArgumentException(("Expected a 36-char string in the standard hex-and-dash UUID format, but was \"" + UuidKt__UuidKt.truncateForErrorMessage$UuidKt__UuidKt(hexDashString, 64) + "\" of length " + hexDashString.length()).toString());
        }

        @NotNull
        public final Uuid random() {
            return UuidKt__UuidJVMKt.secureRandomUuid();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Deprecated(message = "Use naturalOrder<Uuid>() instead", replaceWith = @ReplaceWith(expression = "naturalOrder<Uuid>()", imports = {"kotlin.comparisons.naturalOrder"}))
        @DeprecatedSinceKotlin(warningSince = "2.1")
        public static /* synthetic */ void getLEXICAL_ORDER$annotations() {
        }
    }

    public /* synthetic */ Uuid(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Uuid)) {
            return false;
        }
        Uuid uuid = (Uuid) obj;
        return this.mostSignificantBits == uuid.mostSignificantBits && this.leastSignificantBits == uuid.leastSignificantBits;
    }

    public final long getLeastSignificantBits() {
        return this.leastSignificantBits;
    }

    public final long getMostSignificantBits() {
        return this.mostSignificantBits;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.mostSignificantBits ^ this.leastSignificantBits);
    }

    @NotNull
    public final byte[] toByteArray() {
        byte[] bArr = new byte[16];
        UuidKt__UuidKt.setLongAtCommonImpl(bArr, 0, this.mostSignificantBits);
        UuidKt__UuidKt.setLongAtCommonImpl(bArr, 8, this.leastSignificantBits);
        return bArr;
    }

    @SinceKotlin(version = "2.1")
    @NotNull
    public final String toHexDashString() {
        byte[] bArr = new byte[36];
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.mostSignificantBits, bArr, 0, 0, 4);
        bArr[8] = 45;
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.mostSignificantBits, bArr, 9, 4, 6);
        bArr[13] = 45;
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.mostSignificantBits, bArr, 14, 6, 8);
        bArr[18] = 45;
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.leastSignificantBits, bArr, 19, 0, 2);
        bArr[23] = 45;
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.leastSignificantBits, bArr, 24, 2, 8);
        return StringsKt__StringsJVMKt.decodeToString(bArr);
    }

    @NotNull
    public final String toHexString() {
        byte[] bArr = new byte[32];
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.mostSignificantBits, bArr, 0, 0, 8);
        UuidKt__UuidKt.formatBytesIntoCommonImpl(this.leastSignificantBits, bArr, 16, 0, 8);
        return StringsKt__StringsJVMKt.decodeToString(bArr);
    }

    @InlineOnly
    public final <T> T toLongs(Function2<? super Long, ? super Long, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(this.mostSignificantBits), Long.valueOf(this.leastSignificantBits));
    }

    @NotNull
    public String toString() {
        return toHexDashString();
    }

    @SinceKotlin(version = "2.1")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toUByteArray-TcUX1vc, reason: not valid java name */
    public final byte[] m2064toUByteArrayTcUX1vc() {
        return toByteArray();
    }

    @InlineOnly
    public final <T> T toULongs(Function2<? super ULong, ? super ULong, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(new ULong(this.mostSignificantBits), new ULong(this.leastSignificantBits));
    }

    public final Object writeReplace() {
        return UuidKt__UuidJVMKt.serializedUuid(this);
    }

    public Uuid(long j, long j2) {
        this.mostSignificantBits = j;
        this.leastSignificantBits = j2;
    }

    @Override // java.lang.Comparable
    @SinceKotlin(version = "2.1")
    public int compareTo(@NotNull Uuid other) {
        Intrinsics.checkNotNullParameter(other, "other");
        long j = this.mostSignificantBits;
        long j2 = other.mostSignificantBits;
        return j != j2 ? Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) : Long.compare(this.leastSignificantBits ^ Long.MIN_VALUE, other.leastSignificantBits ^ Long.MIN_VALUE);
    }

    @PublishedApi
    public static /* synthetic */ void getLeastSignificantBits$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMostSignificantBits$annotations() {
    }
}
