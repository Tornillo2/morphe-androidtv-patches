package com.amazon.minerva.client.thirdparty.throttle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ThrottleProfile {
    public double mCredit;
    public long mTimestampInMillis;

    public ThrottleProfile(long j, double d) {
        setTimestamp(j);
        setCredit(d);
    }

    public double getCredit() {
        return this.mCredit;
    }

    public long getTimestamp() {
        return this.mTimestampInMillis;
    }

    public void setCredit(double d) {
        if (d < 0.0d) {
            throw new IllegalArgumentException("Credit is invalid!");
        }
        this.mCredit = d;
    }

    public void setTimestamp(long j) {
        this.mTimestampInMillis = j;
    }
}
