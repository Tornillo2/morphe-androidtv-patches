package com.google.android.gms.internal.measurement;

import j$.util.DesugarCollections;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzkq extends zzim implements RandomAccess, zzkr {
    public static final zzkr zza;
    public static final zzkq zzb;
    public final List zzc;

    static {
        zzkq zzkqVar = new zzkq(10);
        zzb = zzkqVar;
        zzkqVar.zza = false;
        zza = zzkqVar;
    }

    public zzkq() {
        this(10);
    }

    public static String zzj(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzjb ? ((zzjb) obj).zzn(zzkk.zzb) : zzkk.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zzbS();
        this.zzc.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zzbS();
        if (collection instanceof zzkr) {
            collection = ((zzkr) collection).zzh();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzbS();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzbS();
        Object objRemove = this.zzc.remove(i);
        ((AbstractList) this).modCount++;
        return zzj(objRemove);
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zzbS();
        return zzj(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzkj
    public final zzkj zzd(int i) {
        if (i < this.zzc.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzkq(arrayList);
    }

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final zzkr zze() {
        return this.zza ? new zzmq(this) : this;
    }

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzjb) {
            zzjb zzjbVar = (zzjb) obj;
            String strZzn = zzjbVar.zzn(zzkk.zzb);
            if (zzjbVar.zzi()) {
                this.zzc.set(i, strZzn);
            }
            return strZzn;
        }
        byte[] bArr = (byte[]) obj;
        String strZzh = zzkk.zzh(bArr);
        if (zzna.zze(bArr)) {
            this.zzc.set(i, strZzh);
        }
        return strZzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final List zzh() {
        return DesugarCollections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final void zzi(zzjb zzjbVar) {
        zzbS();
        this.zzc.add(zzjbVar);
        ((AbstractList) this).modCount++;
    }

    public zzkq(int i) {
        this.zzc = new ArrayList(i);
    }

    public zzkq(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.zzc.size(), collection);
    }
}
