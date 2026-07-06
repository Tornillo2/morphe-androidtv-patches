package androidx.collection;

import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@JvmInline
@SourceDebugExtension({"SMAP\nIntIntPair.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntIntPair.kt\nandroidx/collection/IntIntPair\n+ 2 PackingUtils.kt\nandroidx/collection/PackingUtilsKt\n*L\n1#1,83:1\n33#2:84\n*S KotlinDebug\n*F\n+ 1 IntIntPair.kt\nandroidx/collection/IntIntPair\n*L\n41#1:84\n*E\n"})
public final class IntIntPair {

    @JvmField
    public final long packedValue;

    public /* synthetic */ IntIntPair(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IntIntPair m16boximpl(long j) {
        return new IntIntPair(j);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final int m17component1impl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final int m18component2impl(long j) {
        return (int) (j & 4294967295L);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m19constructorimpl(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m21equalsimpl(long j, Object obj) {
        return (obj instanceof IntIntPair) && j == ((IntIntPair) obj).packedValue;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m22equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getFirst-impl, reason: not valid java name */
    public static final int m23getFirstimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: getSecond-impl, reason: not valid java name */
    public static final int m24getSecondimpl(long j) {
        return (int) (j & 4294967295L);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m25hashCodeimpl(long j) {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(j);
    }

    @NotNull
    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m26toStringimpl(long j) {
        return "(" + ((int) (j >> 32)) + ", " + ((int) (j & 4294967295L)) + ')';
    }

    public boolean equals(Object obj) {
        return m21equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.packedValue);
    }

    @NotNull
    public String toString() {
        return m26toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m27unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m20constructorimpl(long j) {
        return j;
    }

    @PublishedApi
    public static /* synthetic */ void getPackedValue$annotations() {
    }
}
