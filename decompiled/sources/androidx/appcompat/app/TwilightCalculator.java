package androidx.appcompat.app;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TwilightCalculator {
    public static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976f;
    public static final float C1 = 0.0334196f;
    public static final float C2 = 3.49066E-4f;
    public static final float C3 = 5.236E-6f;
    public static final int DAY = 0;
    public static final float DEGREES_TO_RADIANS = 0.017453292f;
    public static final float J0 = 9.0E-4f;
    public static final int NIGHT = 1;
    public static final float OBLIQUITY = 0.4092797f;
    public static final long UTC_2000 = 946728000000L;
    public static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    public static TwilightCalculator getInstance() {
        if (sInstance == null) {
            sInstance = new TwilightCalculator();
        }
        return sInstance;
    }

    public void calculateTwilight(long j, double d, double d2) {
        double d3 = (0.01720197f * ((j - UTC_2000) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(r3 * 3.0f) * 5.236000106378924E-6d) + (Math.sin(2.0f * r3) * 3.4906598739326E-4d) + (Math.sin(d3) * 0.03341960161924362d) + d3 + 1.796593063d + 3.141592653589793d;
        double dSin2 = (Math.sin(2.0d * dSin) * (-0.0069d)) + (Math.sin(d3) * 0.0053d) + ((double) (Math.round(((double) (r2 - 9.0E-4f)) - r6) + 9.0E-4f)) + ((-d2) / 360.0d);
        double dAsin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(dSin));
        double d4 = 0.01745329238474369d * d;
        double dSin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(dAsin) * Math.sin(d4))) / (Math.cos(dAsin) * Math.cos(d4));
        if (dSin3 >= 1.0d) {
            this.state = 1;
            this.sunset = -1L;
            this.sunrise = -1L;
        } else {
            if (dSin3 <= -1.0d) {
                this.state = 0;
                this.sunset = -1L;
                this.sunrise = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin3) / 6.283185307179586d);
            this.sunset = Math.round((dSin2 + dAcos) * 8.64E7d) + UTC_2000;
            long jRound = Math.round((dSin2 - dAcos) * 8.64E7d) + UTC_2000;
            this.sunrise = jRound;
            if (jRound >= j || this.sunset <= j) {
                this.state = 1;
            } else {
                this.state = 0;
            }
        }
    }
}
