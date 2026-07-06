package org.apache.commons.text.lookup;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class InterpolatorStringLookup extends AbstractStringLookup {
    public static final AbstractStringLookup INSTANCE = new InterpolatorStringLookup((Map) null);
    public static final char PREFIX_SEPARATOR = ':';
    public final StringLookup defaultStringLookup;
    public final Map<String, StringLookup> stringLookupMap;

    public InterpolatorStringLookup(Map<String, StringLookup> map, StringLookup stringLookup, boolean z) {
        this.defaultStringLookup = stringLookup;
        this.stringLookupMap = new HashMap(map.size());
        for (Map.Entry<String, StringLookup> entry : map.entrySet()) {
            this.stringLookupMap.put(entry.getKey().toLowerCase(Locale.ROOT), entry.getValue());
        }
        if (z) {
            StringLookupFactory.INSTANCE.addDefaultStringLookups(this.stringLookupMap);
        }
    }

    public static String toKey(String str) {
        return str.toLowerCase(Locale.ROOT);
    }

    public Map<String, StringLookup> getStringLookupMap() {
        return this.stringLookupMap;
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(58);
        if (iIndexOf >= 0) {
            String lowerCase = str.substring(0, iIndexOf).toLowerCase(Locale.ROOT);
            int i = iIndexOf + 1;
            String strSubstring = str.substring(i);
            StringLookup stringLookup = this.stringLookupMap.get(lowerCase);
            String strLookup = stringLookup != null ? stringLookup.lookup(strSubstring) : null;
            if (strLookup != null) {
                return strLookup;
            }
            str = str.substring(i);
        }
        StringLookup stringLookup2 = this.defaultStringLookup;
        if (stringLookup2 != null) {
            return stringLookup2.lookup(str);
        }
        return null;
    }

    public String toString() {
        return getClass().getName() + " [stringLookupMap=" + this.stringLookupMap + ", defaultStringLookup=" + this.defaultStringLookup + "]";
    }

    public InterpolatorStringLookup() {
        this((Map) null);
    }

    public <V> InterpolatorStringLookup(Map<String, V> map) {
        this(new MapStringLookup(map == null ? new HashMap<>() : map));
    }

    public InterpolatorStringLookup(StringLookup stringLookup) {
        this(new HashMap(), stringLookup, true);
    }
}
