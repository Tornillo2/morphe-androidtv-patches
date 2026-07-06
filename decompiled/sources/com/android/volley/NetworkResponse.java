package com.android.volley;

import androidx.annotation.Nullable;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class NetworkResponse {

    @Nullable
    public final List<Header> allHeaders;
    public final byte[] data;

    @Nullable
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    @Deprecated
    public NetworkResponse(int i, byte[] bArr, @Nullable Map<String, String> map, boolean z, long j) {
        this(i, bArr, map, toAllHeaderList(map), z, j);
    }

    @Nullable
    public static List<Header> toAllHeaderList(@Nullable Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(new Header(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    @Nullable
    public static Map<String, String> toHeaderMap(@Nullable List<Header> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header header : list) {
            treeMap.put(header.mName, header.mValue);
        }
        return treeMap;
    }

    public NetworkResponse(int i, byte[] bArr, boolean z, long j, @Nullable List<Header> list) {
        this(i, bArr, toHeaderMap(list), list, z, j);
    }

    @Deprecated
    public NetworkResponse(int i, byte[] bArr, @Nullable Map<String, String> map, boolean z) {
        this(i, bArr, map, z, 0L);
    }

    public NetworkResponse(byte[] bArr) {
        this(200, bArr, false, 0L, (List<Header>) Collections.EMPTY_LIST);
    }

    @Deprecated
    public NetworkResponse(byte[] bArr, @Nullable Map<String, String> map) {
        this(200, bArr, map, false, 0L);
    }

    public NetworkResponse(int i, byte[] bArr, @Nullable Map<String, String> map, @Nullable List<Header> list, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.headers = map;
        if (list == null) {
            this.allHeaders = null;
        } else {
            this.allHeaders = DesugarCollections.unmodifiableList(list);
        }
        this.notModified = z;
        this.networkTimeMs = j;
    }
}
