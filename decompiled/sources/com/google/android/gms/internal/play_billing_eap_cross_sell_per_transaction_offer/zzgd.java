package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgd extends LinkedHashMap {
    public static final zzgd zza;
    public boolean zzb;

    static {
        zzgd zzgdVar = new zzgd();
        zza = zzgdVar;
        zzgdVar.zzb = false;
    }

    public zzgd() {
        this.zzb = true;
    }

    public static zzgd zza() {
        return zza;
    }

    public static int zzf(Object obj) {
        if (!(obj instanceof byte[])) {
            if (obj instanceof zzfi) {
                throw new UnsupportedOperationException();
            }
            return obj.hashCode();
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = zzfm.zzb;
        int length = bArr.length;
        int iZzb = zzfm.zzb(length, bArr, 0, length);
        if (iZzb == 0) {
            return 1;
        }
        return iZzb;
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
        byte[] bArr = zzfm.zzb;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        zzg();
        for (Object obj : map.keySet()) {
            byte[] bArr = zzfm.zzb;
            obj.getClass();
            map.get(obj).getClass();
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzgd zzb() {
        return isEmpty() ? new zzgd() : new zzgd(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzgd zzgdVar) {
        zzg();
        if (zzgdVar.isEmpty()) {
            return;
        }
        putAll(zzgdVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    public final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public zzgd(Map map) {
        super(map);
        this.zzb = true;
    }
}
