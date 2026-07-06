package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzld extends LinkedHashMap {
    public static final zzld zza;
    public boolean zzb;

    static {
        zzld zzldVar = new zzld();
        zza = zzldVar;
        zzldVar.zzb = false;
    }

    public zzld() {
        this.zzb = true;
    }

    public static zzld zza() {
        return zza;
    }

    public static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzkk.zzb((byte[]) obj);
        }
        if (obj instanceof zzke) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return isEmpty() ? Collections.EMPTY_SET : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzf = 0;
        for (Map.Entry entry : entrySet()) {
            iZzf += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return iZzf;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        zzg();
        zzkk.zze(obj);
        obj2.getClass();
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        zzg();
        for (Object obj : map.keySet()) {
            zzkk.zze(obj);
            map.get(obj).getClass();
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzld zzb() {
        return isEmpty() ? new zzld() : new zzld(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzld zzldVar) {
        zzg();
        if (zzldVar.isEmpty()) {
            return;
        }
        putAll(zzldVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    public final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public zzld(Map map) {
        super(map);
        this.zzb = true;
    }
}
