package kotlin.time;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ExperimentalTime
public final class UnboundLocalDateTime {

    @NotNull
    public static final Companion Companion = new Companion();
    public final int day;
    public final int hour;
    public final int minute;
    public final int month;
    public final int nanosecond;
    public final int second;
    public final int year;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final UnboundLocalDateTime fromInstant(@NotNull Instant instant) {
            long j;
            long j2;
            Intrinsics.checkNotNullParameter(instant, "instant");
            long j3 = instant.epochSeconds;
            long j4 = j3 / 86400;
            if ((j3 ^ 86400) < 0 && j4 * 86400 != j3) {
                j4--;
            }
            long j5 = j3 % 86400;
            int i = (int) (j5 + (86400 & (((j5 ^ 86400) & ((-j5) | j5)) >> 63)));
            long j6 = (j4 + ((long) InstantKt.DAYS_0000_TO_1970)) - ((long) 60);
            if (j6 < 0) {
                long j7 = InstantKt.DAYS_PER_CYCLE;
                long j8 = ((j6 + 1) / j7) - 1;
                j = -1;
                j2 = ((long) 400) * j8;
                j6 += (-j8) * j7;
            } else {
                j = -1;
                j2 = 0;
            }
            long j9 = 400;
            long j10 = ((j9 * j6) + ((long) 591)) / ((long) InstantKt.DAYS_PER_CYCLE);
            long j11 = 365;
            long j12 = j11 * j10;
            long j13 = 4;
            long j14 = (j10 / j13) + j12;
            long j15 = 100;
            long j16 = j6 - ((j10 / j9) + (j14 - (j10 / j15)));
            if (j16 < 0) {
                j10 += j;
                j16 = j6 - ((j10 / j9) + (((j10 / j13) + (j11 * j10)) - (j10 / j15)));
            }
            int i2 = (int) j16;
            int i3 = ((i2 * 5) + 2) / 153;
            int i4 = (int) (j10 + j2 + ((long) (i3 / 10)));
            int i5 = i / 3600;
            int i6 = i - (i5 * 3600);
            int i7 = i6 / 60;
            return new UnboundLocalDateTime(i4, ((i3 + 2) % 12) + 1, (i2 - (((i3 * 306) + 5) / 10)) + 1, i5, i7, i6 - (i7 * 60), instant.nanosecondsOfSecond);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public UnboundLocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
        this.nanosecond = i7;
    }

    public final int getDay() {
        return this.day;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getNanosecond() {
        return this.nanosecond;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getYear() {
        return this.year;
    }

    @NotNull
    public final Instant toInstant(int i) {
        int i2 = this.year;
        long j = i2;
        long j2 = ((long) 365) * j;
        long j3 = j >= 0 ? ((j + ((long) 399)) / ((long) 400)) + (((((long) 3) + j) / ((long) 4)) - ((((long) 99) + j) / ((long) 100))) + j2 : j2 - ((j / ((long) (-400))) + ((j / ((long) (-4))) - (j / ((long) (-100)))));
        long j4 = j3 + ((long) (((r3 * 367) - 362) / 12)) + ((long) (this.day - 1));
        if (this.month > 2) {
            j4 = !InstantKt.isLeapYear(i2) ? j4 - 2 : (-1) + j4;
        }
        long j5 = (((j4 - ((long) InstantKt.DAYS_0000_TO_1970)) * ((long) 86400)) + ((long) (((this.minute * 60) + (this.hour * 3600)) + this.second))) - ((long) i);
        Instant.Companion companion = Instant.Companion;
        companion.getClass();
        if (j5 >= Instant.MIN.epochSeconds) {
            companion.getClass();
            if (j5 <= Instant.MAX.epochSeconds) {
                return companion.fromEpochSeconds(j5, this.nanosecond);
            }
        }
        throw new InstantFormatException("The parsed date is outside the range representable by Instant (Unix epoch second " + j5 + ')');
    }

    @NotNull
    public String toString() {
        return "UnboundLocalDateTime(" + this.year + '-' + this.month + '-' + this.day + ' ' + this.hour + ':' + this.minute + ':' + this.second + '.' + this.nanosecond + ')';
    }
}
