package j$.time;

import com.google.common.net.HttpHeaders;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.time.TimeZones;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ZoneId implements Serializable {
    public static final Map a;
    private static final long serialVersionUID = 8352817235686L;

    public abstract j$.time.zone.f N();

    public abstract void R(DataOutput dataOutput);

    public abstract String i();

    static {
        Map.Entry[] entryArr = {j$.com.android.tools.r8.a.H("ACT", "Australia/Darwin"), j$.com.android.tools.r8.a.H("AET", "Australia/Sydney"), j$.com.android.tools.r8.a.H("AGT", "America/Argentina/Buenos_Aires"), j$.com.android.tools.r8.a.H("ART", "Africa/Cairo"), j$.com.android.tools.r8.a.H("AST", "America/Anchorage"), j$.com.android.tools.r8.a.H("BET", "America/Sao_Paulo"), j$.com.android.tools.r8.a.H("BST", "Asia/Dhaka"), j$.com.android.tools.r8.a.H("CAT", "Africa/Harare"), j$.com.android.tools.r8.a.H("CNT", "America/St_Johns"), j$.com.android.tools.r8.a.H("CST", "America/Chicago"), j$.com.android.tools.r8.a.H("CTT", "Asia/Shanghai"), j$.com.android.tools.r8.a.H("EAT", "Africa/Addis_Ababa"), j$.com.android.tools.r8.a.H(HttpHeaders.ECT, "Europe/Paris"), j$.com.android.tools.r8.a.H("IET", "America/Indiana/Indianapolis"), j$.com.android.tools.r8.a.H("IST", "Asia/Kolkata"), j$.com.android.tools.r8.a.H("JST", "Asia/Tokyo"), j$.com.android.tools.r8.a.H("MIT", "Pacific/Apia"), j$.com.android.tools.r8.a.H("NET", "Asia/Yerevan"), j$.com.android.tools.r8.a.H("NST", "Pacific/Auckland"), j$.com.android.tools.r8.a.H("PLT", "Asia/Karachi"), j$.com.android.tools.r8.a.H("PNT", "America/Phoenix"), j$.com.android.tools.r8.a.H("PRT", "America/Puerto_Rico"), j$.com.android.tools.r8.a.H("PST", "America/Los_Angeles"), j$.com.android.tools.r8.a.H("SST", "Pacific/Guadalcanal"), j$.com.android.tools.r8.a.H("VST", "Asia/Ho_Chi_Minh"), j$.com.android.tools.r8.a.H("EST", "-05:00"), j$.com.android.tools.r8.a.H("MST", "-07:00"), j$.com.android.tools.r8.a.H("HST", "-10:00")};
        HashMap map = new HashMap(28);
        for (int i = 0; i < 28; i++) {
            Map.Entry entry = entryArr[i];
            Object objRequireNonNull = Objects.requireNonNull(entry.getKey());
            if (map.put(objRequireNonNull, Objects.requireNonNull(entry.getValue())) != null) {
                throw new IllegalArgumentException("duplicate key: " + objRequireNonNull);
            }
        }
        a = Collections.unmodifiableMap(map);
    }

    public static ZoneId P(String str, z zVar) {
        Objects.requireNonNull(str, "prefix");
        Objects.requireNonNull(zVar, "offset");
        if (str.isEmpty()) {
            return zVar;
        }
        if (!str.equals(TimeZones.GMT_ID) && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: ".concat(str));
        }
        if (zVar.b != 0) {
            str = str.concat(zVar.c);
        }
        return new a0(str, zVar.N());
    }

    public static ZoneId O(String str, boolean z) {
        Objects.requireNonNull(str, "zoneId");
        if (str.length() <= 1 || str.startsWith("+") || str.startsWith("-")) {
            return z.S(str);
        }
        if (str.startsWith("UTC") || str.startsWith(TimeZones.GMT_ID)) {
            return Q(str, 3, z);
        }
        if (str.startsWith("UT")) {
            return Q(str, 2, z);
        }
        return a0.S(str, z);
    }

    public static ZoneId Q(String str, int i, boolean z) {
        String strSubstring = str.substring(0, i);
        if (str.length() == i) {
            return P(strSubstring, z.f);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return a0.S(str, z);
        }
        try {
            z zVarS = z.S(str.substring(i));
            if (zVarS == z.f) {
                return P(strSubstring, zVarS);
            }
            return P(strSubstring, zVarS);
        } catch (b e) {
            throw new b("Invalid ID for offset-based ZoneId: ".concat(str), e);
        }
    }

    public ZoneId() {
        if (getClass() != z.class && getClass() != a0.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return i().equals(((ZoneId) obj).i());
        }
        return false;
    }

    public int hashCode() {
        return i().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public String toString() {
        return i();
    }

    private Object writeReplace() {
        return new u((byte) 7, this);
    }
}
