package j$.util;

import j$.time.ZoneId;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class DesugarTimeZone {
    public static TimeZone getTimeZone(String str) {
        return TimeZone.getTimeZone(str);
    }

    public static ZoneId toZoneId(TimeZone timeZone) {
        String id = timeZone.getID();
        java.util.Map map = ZoneId.a;
        Objects.requireNonNull(id, "zoneId");
        Objects.requireNonNull(map, "aliasMap");
        Object objRequireNonNull = (String) map.get(id);
        if (objRequireNonNull == null) {
            objRequireNonNull = Objects.requireNonNull(id, "defaultObj");
        }
        return ZoneId.O((String) objRequireNonNull, true);
    }
}
