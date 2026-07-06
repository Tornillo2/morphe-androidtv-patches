package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalTime.class})
@SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,202:1\n80#2:203\n80#2:204\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/TestTimeSource\n*L\n176#1:203\n183#1:204\n*E\n"})
public final class TestTimeSource extends AbstractLongTimeSource {
    public long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        markNow();
    }

    /* JADX INFO: renamed from: overflow-LRDsOJo, reason: not valid java name */
    public final void m2036overflowLRDsOJo(long j) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt__DurationUnitKt.shortName(this.unit) + " is advanced by " + ((Object) Duration.m1965toStringimpl(j)) + '.');
    }

    /* JADX INFO: renamed from: plusAssign-LRDsOJo, reason: not valid java name */
    public final void m2037plusAssignLRDsOJo(long j) {
        long jM1964toLongimpl = Duration.m1964toLongimpl(j, this.unit);
        if (((jM1964toLongimpl - 1) | 1) != Long.MAX_VALUE) {
            long j2 = this.reading;
            long j3 = j2 + jM1964toLongimpl;
            if ((jM1964toLongimpl ^ j2) < 0 || (j2 ^ j3) >= 0) {
                this.reading = j3;
                return;
            } else {
                m2036overflowLRDsOJo(j);
                throw null;
            }
        }
        long jM1928divUwyO8pc = Duration.m1928divUwyO8pc(j, 2);
        if ((1 | (Duration.m1964toLongimpl(jM1928divUwyO8pc, this.unit) - 1)) == Long.MAX_VALUE) {
            m2036overflowLRDsOJo(j);
            throw null;
        }
        long j4 = this.reading;
        try {
            m2037plusAssignLRDsOJo(jM1928divUwyO8pc);
            m2037plusAssignLRDsOJo(Duration.m1953minusLRDsOJo(j, jM1928divUwyO8pc));
        } catch (IllegalStateException e) {
            this.reading = j4;
            throw e;
        }
    }

    @Override // kotlin.time.AbstractLongTimeSource
    public long read() {
        return this.reading;
    }
}
