package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.DesugarCollections;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzhb extends AbstractMap {
    public Object[] zza;
    public int zzb;
    public Map zzc;
    public boolean zzd;
    public volatile zzgz zze;
    public Map zzf;

    public zzhb() {
        Map map = Collections.EMPTY_MAP;
        this.zzc = map;
        this.zzf = map;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzo();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzl(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzgz(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhb)) {
            return super.equals(obj);
        }
        zzhb zzhbVar = (zzhb) obj;
        int size = size();
        if (size != zzhbVar.size()) {
            return false;
        }
        int i = this.zzb;
        if (i != zzhbVar.zzb) {
            return entrySet().equals(zzhbVar.entrySet());
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzg(i2).equals(zzhbVar.zzg(i2))) {
                return false;
            }
        }
        if (i != size) {
            return this.zzc.equals(zzhbVar.zzc);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZzl = zzl(comparable);
        return iZzl >= 0 ? ((zzgx) this.zza[iZzl]).zzc : this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i = this.zzb;
        int iHashCode = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iHashCode += this.zza[i2].hashCode();
        }
        return this.zzc.size() > 0 ? this.zzc.hashCode() + iHashCode : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzo();
        Comparable comparable = (Comparable) obj;
        int iZzl = zzl(comparable);
        if (iZzl >= 0) {
            return zzm(iZzl);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzc.size() + this.zzb;
    }

    public void zza() {
        if (this.zzd) {
            return;
        }
        this.zzc = this.zzc.isEmpty() ? Collections.EMPTY_MAP : DesugarCollections.unmodifiableMap(this.zzc);
        this.zzf = this.zzf.isEmpty() ? Collections.EMPTY_MAP : DesugarCollections.unmodifiableMap(this.zzf);
        this.zzd = true;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final Iterable zzd() {
        return this.zzc.isEmpty() ? Collections.EMPTY_SET : this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        zzo();
        int iZzl = zzl(comparable);
        if (iZzl >= 0) {
            return ((zzgx) this.zza[iZzl]).setValue(obj);
        }
        zzo();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i = -(iZzl + 1);
        if (i >= 16) {
            return zzn().put(comparable, obj);
        }
        if (this.zzb == 16) {
            zzgx zzgxVar = (zzgx) this.zza[15];
            this.zzb = 15;
            zzn().put(zzgxVar.zzb, zzgxVar.zzc);
        }
        Object[] objArr = this.zza;
        int length = objArr.length;
        System.arraycopy(objArr, i, objArr, i + 1, 15 - i);
        this.zza[i] = new zzgx(this, comparable, obj);
        this.zzb++;
        return null;
    }

    public final Map.Entry zzg(int i) {
        if (i < this.zzb) {
            return (zzgx) this.zza[i];
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public final boolean zzj() {
        return this.zzd;
    }

    public final int zzl(Comparable comparable) {
        int i = this.zzb;
        int i2 = i - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int iCompareTo = comparable.compareTo(((zzgx) this.zza[i2]).zzb);
            if (iCompareTo > 0) {
                return -(i + 1);
            }
            if (iCompareTo == 0) {
                return i2;
            }
        }
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            int iCompareTo2 = comparable.compareTo(((zzgx) this.zza[i4]).zzb);
            if (iCompareTo2 < 0) {
                i2 = i4 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i4;
                }
                i3 = i4 + 1;
            }
        }
        return -(i3 + 1);
    }

    public final Object zzm(int i) {
        zzo();
        Object[] objArr = this.zza;
        Object obj = ((zzgx) objArr[i]).zzc;
        System.arraycopy(objArr, i + 1, objArr, i, (this.zzb - i) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzn().entrySet().iterator();
            Object[] objArr2 = this.zza;
            int i2 = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i2] = new zzgx(this, (Comparable) entry.getKey(), entry.getValue());
            this.zzb++;
            it.remove();
        }
        return obj;
    }

    public final SortedMap zzn() {
        zzo();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    public final void zzo() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ zzhb(zzha zzhaVar) {
        Map map = Collections.EMPTY_MAP;
        this.zzc = map;
        this.zzf = map;
    }
}
