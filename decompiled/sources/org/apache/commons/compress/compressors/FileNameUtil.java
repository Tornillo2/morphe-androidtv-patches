package org.apache.commons.compress.compressors;

import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class FileNameUtil {
    public final Map<String, String> compressSuffix = new HashMap();
    public final String defaultExtension;
    public final int longestCompressedSuffix;
    public final int longestUncompressedSuffix;
    public final int shortestCompressedSuffix;
    public final int shortestUncompressedSuffix;
    public final Map<String, String> uncompressSuffix;

    public FileNameUtil(Map<String, String> map, String str) {
        this.uncompressSuffix = DesugarCollections.unmodifiableMap(map);
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            int length = entry.getKey().length();
            i = length > i ? length : i;
            i3 = length < i3 ? length : i3;
            String value = entry.getValue();
            int length2 = value.length();
            if (length2 > 0) {
                if (!this.compressSuffix.containsKey(value)) {
                    this.compressSuffix.put(value, entry.getKey());
                }
                i2 = length2 > i2 ? length2 : i2;
                if (length2 < i4) {
                    i4 = length2;
                }
            }
        }
        this.longestCompressedSuffix = i;
        this.longestUncompressedSuffix = i2;
        this.shortestCompressedSuffix = i3;
        this.shortestUncompressedSuffix = i4;
        this.defaultExtension = str;
    }

    public String getCompressedFilename(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        for (int i = this.shortestUncompressedSuffix; i <= this.longestUncompressedSuffix && i < length; i++) {
            int i2 = length - i;
            String str2 = this.compressSuffix.get(lowerCase.substring(i2));
            if (str2 != null) {
                return str.substring(0, i2) + str2;
            }
        }
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
        sbM.append(this.defaultExtension);
        return sbM.toString();
    }

    public String getUncompressedFilename(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < length; i++) {
            int i2 = length - i;
            String str2 = this.uncompressSuffix.get(lowerCase.substring(i2));
            if (str2 != null) {
                return str.substring(0, i2) + str2;
            }
        }
        return str;
    }

    public boolean isCompressedFilename(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        int length = lowerCase.length();
        for (int i = this.shortestCompressedSuffix; i <= this.longestCompressedSuffix && i < length; i++) {
            if (this.uncompressSuffix.containsKey(lowerCase.substring(length - i))) {
                return true;
            }
        }
        return false;
    }
}
