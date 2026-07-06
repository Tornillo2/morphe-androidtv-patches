package org.apache.commons.lang3.time;

import j$.time.ZoneId;
import j$.util.DesugarTimeZone;
import j$.util.TimeZoneRetargetInterface;
import java.util.Date;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class GmtTimeZone extends TimeZone implements TimeZoneRetargetInterface {
    public static final int HOURS_PER_DAY = 24;
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    public static final int MINUTES_PER_HOUR = 60;
    public static final long serialVersionUID = 1;
    public final int offset;
    public final String zoneId;

    public GmtTimeZone(boolean z, int i, int i2) {
        if (i >= 24) {
            throw new IllegalArgumentException(i + " hours out of range");
        }
        if (i2 >= 60) {
            throw new IllegalArgumentException(i2 + " minutes out of range");
        }
        int i3 = ((i * 60) + i2) * MILLISECONDS_PER_MINUTE;
        this.offset = z ? -i3 : i3;
        StringBuilder sb = new StringBuilder(9);
        sb.append(TimeZones.GMT_ID);
        sb.append(z ? '-' : '+');
        twoDigits(sb, i);
        sb.append(':');
        twoDigits(sb, i2);
        this.zoneId = sb.toString();
    }

    public static StringBuilder twoDigits(StringBuilder sb, int i) {
        sb.append((char) ((i / 10) + 48));
        sb.append((char) ((i % 10) + 48));
        return sb;
    }

    public boolean equals(Object obj) {
        return (obj instanceof GmtTimeZone) && this.zoneId == ((GmtTimeZone) obj).zoneId;
    }

    @Override // java.util.TimeZone
    public String getID() {
        return this.zoneId;
    }

    @Override // java.util.TimeZone
    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.offset;
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.offset;
    }

    public int hashCode() {
        return this.offset;
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return false;
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int i) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "[GmtTimeZone id=\"" + this.zoneId + "\",offset=" + this.offset + AbstractJsonLexerKt.END_LIST;
    }

    @Override // java.util.TimeZone, j$.util.TimeZoneRetargetInterface
    public /* synthetic */ ZoneId toZoneId() {
        return DesugarTimeZone.toZoneId(this);
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return false;
    }
}
