package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbpb extends zzbod {
    public final Adapter zza;
    public final zzbvh zzb;

    public zzbpb(Adapter adapter, zzbvh zzbvhVar) {
        this.zza = adapter;
        this.zzb = zzbvhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zze() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zze(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzf() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzf(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzg(int i) throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzg(new ObjectWrapper(this.zza), i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzo() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzi(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzp() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzj(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzt(zzbvm zzbvmVar) throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzm(new ObjectWrapper(this.zza), new zzbvi(zzbvmVar.zzf(), zzbvmVar.zze()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzu() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzn(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzy() throws RemoteException {
        zzbvh zzbvhVar = this.zzb;
        if (zzbvhVar != null) {
            zzbvhVar.zzo(new ObjectWrapper(this.zza));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzm() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzn() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzv() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzw() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzx() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzh(zze zzeVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzj(int i) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzk(zze zzeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzl(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzs(zzbvi zzbviVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzi(int i, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzq(String str, String str2) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final void zzr(zzbfk zzbfkVar, String str) throws RemoteException {
    }
}
