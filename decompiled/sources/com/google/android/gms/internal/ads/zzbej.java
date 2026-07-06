package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbej extends NativeAd.AdChoicesInfo {
    public final zzbei zza;
    public final List zzb = new ArrayList();
    public String zzc;

    public zzbej(zzbei zzbeiVar) {
        zzbeq zzbeoVar;
        this.zza = zzbeiVar;
        try {
            this.zzc = zzbeiVar.zzg();
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
            this.zzc = "";
        }
        try {
            for (Object obj : zzbeiVar.zzh()) {
                if (obj instanceof IBinder) {
                    IBinder iBinder = (IBinder) obj;
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzbeoVar = iInterfaceQueryLocalInterface instanceof zzbeq ? (zzbeq) iInterfaceQueryLocalInterface : new zzbeo(iBinder);
                } else {
                    zzbeoVar = null;
                }
                if (zzbeoVar != null) {
                    this.zzb.add(new zzber(zzbeoVar));
                }
            }
        } catch (RemoteException e2) {
            zzbzt.zzh("", e2);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final List<NativeAd.Image> getImages() {
        return this.zzb;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final CharSequence getText() {
        return this.zzc;
    }
}
