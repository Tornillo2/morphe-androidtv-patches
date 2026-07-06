package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbec extends zzbep {
    public final Drawable zza;
    public final Uri zzb;
    public final double zzc;
    public final int zzd;
    public final int zze;

    public zzbec(Drawable drawable, Uri uri, double d, int i, int i2) {
        this.zza = drawable;
        this.zzb = uri;
        this.zzc = d;
        this.zzd = i;
        this.zze = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final double zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final int zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final Uri zze() throws RemoteException {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzbeq
    public final IObjectWrapper zzf() throws RemoteException {
        return new ObjectWrapper(this.zza);
    }
}
