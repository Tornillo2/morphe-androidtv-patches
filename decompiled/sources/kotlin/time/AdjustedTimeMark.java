package kotlin.time;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AdjustedTimeMark implements TimeMark {
    public final long adjustment;

    @NotNull
    public final TimeMark mark;

    public /* synthetic */ AdjustedTimeMark(TimeMark timeMark, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(timeMark, j);
    }

    @Override // kotlin.time.TimeMark
    /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
    public long mo1915elapsedNowUwyO8pc() {
        return Duration.m1953minusLRDsOJo(this.mark.mo1915elapsedNowUwyO8pc(), this.adjustment);
    }

    /* JADX INFO: renamed from: getAdjustment-UwyO8pc, reason: not valid java name */
    public final long m1919getAdjustmentUwyO8pc() {
        return this.adjustment;
    }

    @NotNull
    public final TimeMark getMark() {
        return this.mark;
    }

    @Override // kotlin.time.TimeMark
    public boolean hasNotPassedNow() {
        return Duration.m1951isNegativeimpl(mo1915elapsedNowUwyO8pc());
    }

    @Override // kotlin.time.TimeMark
    public boolean hasPassedNow() {
        return !Duration.m1951isNegativeimpl(mo1915elapsedNowUwyO8pc());
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* JADX INFO: renamed from: minus-LRDsOJo */
    public TimeMark mo1916minusLRDsOJo(long j) {
        return TimeMark.DefaultImpls.m2038minusLRDsOJo(this, j);
    }

    @Override // kotlin.time.TimeMark
    @NotNull
    /* JADX INFO: renamed from: plus-LRDsOJo */
    public TimeMark mo1918plusLRDsOJo(long j) {
        return new AdjustedTimeMark(this.mark, Duration.m1954plusLRDsOJo(this.adjustment, j));
    }

    public AdjustedTimeMark(TimeMark mark, long j) {
        Intrinsics.checkNotNullParameter(mark, "mark");
        this.mark = mark;
        this.adjustment = j;
    }
}
