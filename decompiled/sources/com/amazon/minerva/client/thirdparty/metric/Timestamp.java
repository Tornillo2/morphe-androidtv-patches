package com.amazon.minerva.client.thirdparty.metric;

import j$.util.Objects;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Timestamp {
    public final long epochMillis;
    public final TimeZone timeZone;

    public Timestamp(long j, TimeZone timeZone) {
        this.epochMillis = j;
        this.timeZone = timeZone;
    }

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis(), TimeZone.getDefault());
    }

    public static Timestamp of(long j, TimeZone timeZone) {
        Objects.requireNonNull(timeZone, "Time zone cannot be null");
        return new Timestamp(j, (TimeZone) timeZone.clone());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Timestamp.class == obj.getClass()) {
            Timestamp timestamp = (Timestamp) obj;
            if (this.epochMillis == timestamp.epochMillis && Objects.equals(this.timeZone, timestamp.timeZone)) {
                return true;
            }
        }
        return false;
    }

    public long getEpochMillis() {
        return this.epochMillis;
    }

    public TimeZone getTimeZone() {
        return (TimeZone) this.timeZone.clone();
    }

    public int getTimeZoneOffset() {
        return this.timeZone.getOffset(this.epochMillis);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.epochMillis), this.timeZone);
    }

    public String toString() {
        return this.epochMillis + "@" + this.timeZone.getID();
    }
}
