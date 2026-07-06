package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class HdcpVersion implements Comparable<HdcpVersion> {
    public static final Pattern HDCP_VERSION_FORMAT = Pattern.compile("(\\d+)\\.(\\d+|.+)");
    public final String fullVersion;
    public final int majorVersion;
    public final int minorVersion;

    public HdcpVersion(String str, int i, int i2) {
        this.fullVersion = str;
        this.majorVersion = i;
        this.minorVersion = i2;
    }

    @NonNull
    public static HdcpVersion parse(@NonNull String str) {
        Matcher matcher = HDCP_VERSION_FORMAT.matcher(str);
        int i = 0;
        if (matcher.find()) {
            String strGroup = matcher.group();
            int i2 = Integer.parseInt(matcher.group(1));
            try {
                i = Integer.parseInt(matcher.group(2));
            } catch (NumberFormatException unused) {
            }
            return new HdcpVersion(strGroup, i2, i);
        }
        MpbLog.t("Failed to parse HDCP version '" + str + "'");
        return new HdcpVersion(str, 0, 0);
    }

    @NonNull
    public String getFullVersion() {
        return this.fullVersion;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public String getNormalisedFullVersion() {
        return String.format(Locale.US, "%d.%d", Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion));
    }

    @Override // java.lang.Comparable
    public int compareTo(HdcpVersion hdcpVersion) {
        if (hdcpVersion == null) {
            return 1;
        }
        int iCompare = Integer.compare(this.majorVersion, hdcpVersion.majorVersion);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Integer.compare(this.minorVersion, hdcpVersion.minorVersion);
        return iCompare2 != 0 ? iCompare2 : this.fullVersion.compareTo(hdcpVersion.fullVersion);
    }
}
