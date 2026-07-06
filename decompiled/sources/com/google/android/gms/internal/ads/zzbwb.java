package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbwb {
    @Nullable
    public static final zzbvp zza(Context context, String str, zzbny zzbnyVar) {
        try {
            IBinder iBinderZze = ((zzbvt) zzbzx.zzb(context, "com.google.android.gms.ads.rewarded.ChimeraRewardedAdCreatorImpl", zzbwa.zza)).zze(ObjectWrapper.wrap(context), str, zzbnyVar, 231700000);
            if (iBinderZze == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZze.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
            return iInterfaceQueryLocalInterface instanceof zzbvp ? (zzbvp) iInterfaceQueryLocalInterface : new zzbvn(iBinderZze);
        } catch (RemoteException e) {
            e = e;
            zzbzt.zzl("#007 Could not call remote method.", e);
            return null;
        } catch (zzbzw e2) {
            e = e2;
            zzbzt.zzl("#007 Could not call remote method.", e);
            return null;
        }
    }
}
