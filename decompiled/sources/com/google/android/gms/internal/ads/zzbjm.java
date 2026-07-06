package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.ads.h5.OnH5AdsEventListener;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 21)
public final class zzbjm {
    public final Context zza;
    public final OnH5AdsEventListener zzb;

    @Nullable
    public zzbji zzc;

    public zzbjm(Context context, OnH5AdsEventListener onH5AdsEventListener) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(onH5AdsEventListener);
        this.zza = context;
        this.zzb = onH5AdsEventListener;
        zzbbk.zza(context);
    }

    public static final boolean zzc(String str) {
        if (!((Boolean) zzba.zzc().zzb(zzbbk.zziY)).booleanValue()) {
            return false;
        }
        Preconditions.checkNotNull(str);
        if (str.length() > ((Integer) zzba.zza.zzd.zzb(zzbbk.zzja)).intValue()) {
            zzbzt.zze("H5 GMSG exceeds max length");
            return false;
        }
        Uri uri = Uri.parse(str);
        return "gmsg".equals(uri.getScheme()) && "mobileads.google.com".equals(uri.getHost()) && "/h5ads".equals(uri.getPath());
    }

    public final void zza() {
        if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zziY)).booleanValue()) {
            zzd();
            zzbji zzbjiVar = this.zzc;
            if (zzbjiVar != null) {
                try {
                    zzbjiVar.zze();
                } catch (RemoteException e) {
                    zzbzt.zzl("#007 Could not call remote method.", e);
                }
            }
        }
    }

    public final boolean zzb(String str) {
        if (!zzc(str)) {
            return false;
        }
        zzd();
        zzbji zzbjiVar = this.zzc;
        if (zzbjiVar == null) {
            return false;
        }
        try {
            zzbjiVar.zzf(str);
            return true;
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            return true;
        }
    }

    public final void zzd() {
        if (this.zzc != null) {
            return;
        }
        this.zzc = zzay.zza().zzl(this.zza, new zzbnv(), this.zzb);
    }
}
