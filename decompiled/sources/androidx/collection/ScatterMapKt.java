package androidx.collection;

import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nScatterMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1#1,1850:1\n1661#1:1851\n1661#1:1852\n1661#1:1853\n1715#1:1854\n*S KotlinDebug\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1680#1:1851\n1682#1:1852\n1685#1:1853\n1721#1:1854\n*E\n"})
public final class ScatterMapKt {
    public static final long AllEmpty = -9187201950435737472L;
    public static final long BitmaskLsb = 72340172838076673L;
    public static final long BitmaskMsb = -9187201950435737472L;
    public static final int ClonedMetadataCount = 7;
    public static final int DefaultScatterCapacity = 6;
    public static final long Deleted = 254;
    public static final long Empty = 128;

    @JvmField
    @NotNull
    public static final long[] EmptyGroup = {-9187201950435737345L, -1};

    @NotNull
    public static final MutableScatterMap EmptyScatterMap = new MutableScatterMap(0);
    public static final int GroupWidth = 8;
    public static final int MurmurHashC1 = -862048943;
    public static final long Sentinel = 255;

    @NotNull
    public static final <K, V> ScatterMap<K, V> emptyScatterMap() {
        MutableScatterMap mutableScatterMap = EmptyScatterMap;
        Intrinsics.checkNotNull(mutableScatterMap, "null cannot be cast to non-null type androidx.collection.ScatterMap<K of androidx.collection.ScatterMapKt.emptyScatterMap, V of androidx.collection.ScatterMapKt.emptyScatterMap>");
        return mutableScatterMap;
    }

    public static final int get(long j) {
        return Long.numberOfTrailingZeros(j) >> 3;
    }

    public static final long group(@NotNull long[] metadata, int i) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        int i2 = i >> 3;
        int i3 = (i & 7) << 3;
        return (((-i3) >> 63) & (metadata[i2 + 1] << (64 - i3))) | (metadata[i2] >>> i3);
    }

    public static final int h1(int i) {
        return i >>> 7;
    }

    public static final int h2(int i) {
        return i & 127;
    }

    public static final boolean hasNext(long j) {
        return j != 0;
    }

    public static final int hash(@Nullable Object obj) {
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        return iHashCode ^ (iHashCode << 16);
    }

    public static final boolean isDeleted(@NotNull long[] metadata, int i) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return ((metadata[i >> 3] >> ((i & 7) << 3)) & 255) == 254;
    }

    public static final boolean isEmpty(@NotNull long[] metadata, int i) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return ((metadata[i >> 3] >> ((i & 7) << 3)) & 255) == 128;
    }

    @PublishedApi
    public static final boolean isFull(long j) {
        return j < 128;
    }

    public static final int loadedCapacity(int i) {
        if (i == 7) {
            return 6;
        }
        return i - (i / 8);
    }

    @PublishedApi
    public static final int lowestBitSet(long j) {
        return Long.numberOfTrailingZeros(j) >> 3;
    }

    public static final long maskEmpty(long j) {
        return j & ((~j) << 6) & (-9187201950435737472L);
    }

    @PublishedApi
    public static final long maskEmptyOrDeleted(long j) {
        return j & ((~j) << 7) & (-9187201950435737472L);
    }

    @PublishedApi
    public static final long match(long j, int i) {
        long j2 = j ^ (((long) i) * BitmaskLsb);
        return (~j2) & (j2 - BitmaskLsb) & (-9187201950435737472L);
    }

    @NotNull
    public static final <K, V> MutableScatterMap<K, V> mutableScatterMapOf() {
        return new MutableScatterMap<>(0, 1, null);
    }

    public static final long next(long j) {
        return j & (j - 1);
    }

    public static final int nextCapacity(int i) {
        if (i == 0) {
            return 6;
        }
        return (i * 2) + 1;
    }

    public static final int normalizeCapacity(int i) {
        if (i > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i);
        }
        return 0;
    }

    @PublishedApi
    public static final long readRawMetadata(@NotNull long[] data, int i) {
        Intrinsics.checkNotNullParameter(data, "data");
        return (data[i >> 3] >> ((i & 7) << 3)) & 255;
    }

    public static final int unloadedCapacity(int i) {
        if (i == 7) {
            return 8;
        }
        return ((i - 1) / 7) + i;
    }

    public static final void writeRawMetadata(@NotNull long[] data, int i, long j) {
        Intrinsics.checkNotNullParameter(data, "data");
        int i2 = i >> 3;
        int i3 = (i & 7) << 3;
        data[i2] = (j << i3) | (data[i2] & (~(255 << i3)));
    }

    public static final boolean isFull(@NotNull long[] metadata, int i) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return ((metadata[i >> 3] >> ((i & 7) << 3)) & 255) < 128;
    }

    @NotNull
    public static final <K, V> MutableScatterMap<K, V> mutableScatterMapOf(@NotNull Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        MutableScatterMap<K, V> mutableScatterMap = new MutableScatterMap<>(pairs.length);
        mutableScatterMap.putAll(pairs);
        return mutableScatterMap;
    }

    @PublishedApi
    public static /* synthetic */ void getBitmaskLsb$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getBitmaskMsb$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSentinel$annotations() {
    }
}
