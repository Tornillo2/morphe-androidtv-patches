package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyLog;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import j$.util.DesugarTimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.lang3.time.TimeZones;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class HttpHeaderParser {
    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String RFC1123_OUTPUT_FORMAT = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
    public static final String RFC1123_PARSE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static List<Header> combineHeaders(List<Header> list, Cache.Entry entry) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            Iterator<Header> it = list.iterator();
            while (it.hasNext()) {
                treeSet.add(it.next().mName);
            }
        }
        ArrayList arrayList = new ArrayList(list);
        List<Header> list2 = entry.allResponseHeaders;
        if (list2 != null) {
            if (!list2.isEmpty()) {
                for (Header header : entry.allResponseHeaders) {
                    if (!treeSet.contains(header.mName)) {
                        arrayList.add(header);
                    }
                }
            }
        } else if (!entry.responseHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry2 : entry.responseHeaders.entrySet()) {
                if (!treeSet.contains(entry2.getKey())) {
                    arrayList.add(new Header(entry2.getKey(), entry2.getValue()));
                }
            }
        }
        return arrayList;
    }

    public static String formatEpochAsRfc1123(long j) {
        return newUsGmtFormatter(RFC1123_OUTPUT_FORMAT).format(new Date(j));
    }

    public static Map<String, String> getCacheHeaders(Cache.Entry entry) {
        if (entry == null) {
            return Collections.EMPTY_MAP;
        }
        HashMap map = new HashMap();
        String str = entry.etag;
        if (str != null) {
            map.put(HttpHeaders.IF_NONE_MATCH, str);
        }
        long j = entry.lastModified;
        if (j > 0) {
            map.put(HttpHeaders.IF_MODIFIED_SINCE, formatEpochAsRfc1123(j));
        }
        return map;
    }

    public static SimpleDateFormat newUsGmtFormatter(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone(TimeZones.GMT_ID));
        return simpleDateFormat;
    }

    @Nullable
    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long j;
        long j2;
        long j3;
        boolean z;
        long j4;
        long j5;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        if (map == null) {
            return null;
        }
        String str = map.get(HttpHeaders.DATE);
        long dateAsEpoch = str != null ? parseDateAsEpoch(str) : 0L;
        String str2 = map.get(HttpHeaders.CACHE_CONTROL);
        int i = 0;
        if (str2 != null) {
            String[] strArrSplit = str2.split(",", 0);
            z = false;
            j2 = 0;
            j3 = 0;
            while (i < strArrSplit.length) {
                String strTrim = strArrSplit[i].trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(strTrim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    j3 = Long.parseLong(strTrim.substring(23));
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    z = true;
                }
                i++;
            }
            j = 0;
            i = 1;
        } else {
            j = 0;
            j2 = 0;
            j3 = 0;
            z = false;
        }
        String str3 = map.get(HttpHeaders.EXPIRES);
        long dateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : j;
        String str4 = map.get(HttpHeaders.LAST_MODIFIED);
        long dateAsEpoch3 = str4 != null ? parseDateAsEpoch(str4) : j;
        String str5 = map.get(HttpHeaders.ETAG);
        if (i != 0) {
            long j6 = (j2 * 1000) + jCurrentTimeMillis;
            if (z) {
                j5 = j6;
            } else {
                Long.signum(j3);
                j5 = (j3 * 1000) + j6;
            }
            j4 = j6;
        } else {
            j4 = (dateAsEpoch <= j || dateAsEpoch2 < dateAsEpoch) ? j : (dateAsEpoch2 - dateAsEpoch) + jCurrentTimeMillis;
            j5 = j4;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = str5;
        entry.softTtl = j4;
        entry.ttl = j5;
        entry.serverDate = dateAsEpoch;
        entry.lastModified = dateAsEpoch3;
        entry.responseHeaders = map;
        entry.allResponseHeaders = networkResponse.allHeaders;
        return entry;
    }

    public static String parseCharset(@Nullable Map<String, String> map, String str) {
        String str2;
        if (map != null && (str2 = map.get("Content-Type")) != null) {
            String[] strArrSplit = str2.split(";", 0);
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=", 0);
                if (strArrSplit2.length == 2 && strArrSplit2[0].equals(MediaType.CHARSET_ATTRIBUTE)) {
                    return strArrSplit2[1];
                }
            }
        }
        return str;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return newUsGmtFormatter(RFC1123_PARSE_FORMAT).parse(str).getTime();
        } catch (ParseException e) {
            if ("0".equals(str) || "-1".equals(str)) {
                VolleyLog.v("Unable to parse dateStr: %s, falling back to 0", str);
                return 0L;
            }
            VolleyLog.e(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0L;
        }
    }

    public static List<Header> toAllHeaderList(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(new Header(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    public static Map<String, String> toHeaderMap(List<Header> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header header : list) {
            treeMap.put(header.mName, header.mValue);
        }
        return treeMap;
    }

    public static String parseCharset(@Nullable Map<String, String> map) {
        return parseCharset(map, "ISO-8859-1");
    }
}
