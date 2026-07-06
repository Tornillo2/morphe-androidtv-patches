package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.measurement.internal.zzga;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzcx extends zzdt {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Context zzc;
    public final /* synthetic */ Bundle zzd;
    public final /* synthetic */ zzee zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcx(zzee zzeeVar, String str, String str2, Context context, Bundle bundle) {
        super(zzeeVar, true);
        this.zze = zzeeVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = context;
        this.zzd = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        String str;
        String str2;
        String str3;
        try {
            if (zzee.zzV(this.zza, this.zzb)) {
                str3 = this.zzb;
                str2 = this.zza;
                str = this.zze.zzd;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            Preconditions.checkNotNull(this.zzc);
            zzee zzeeVar = this.zze;
            zzeeVar.zzj = zzeeVar.zzf(this.zzc, true);
            if (this.zze.zzj == null) {
                Log.w(this.zze.zzd, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.zzc, "com.google.android.gms.measurement.dynamite");
            zzcl zzclVar = new zzcl(61000L, Math.max(localVersion, r0), DynamiteModule.zza(this.zzc, "com.google.android.gms.measurement.dynamite", false) < localVersion, str, str2, str3, this.zzd, zzga.zza(this.zzc));
            zzcc zzccVar = this.zze.zzj;
            Preconditions.checkNotNull(zzccVar);
            zzccVar.initialize(ObjectWrapper.wrap(this.zzc), zzclVar, this.zzh);
        } catch (Exception e) {
            this.zze.zzS(e, true, false);
        }
    }
}
