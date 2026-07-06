package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbny;
import com.google.android.gms.internal.ads.zzbsy;
import com.google.android.gms.internal.ads.zzbta;
import com.google.android.gms.internal.ads.zzbzt;
import com.google.android.gms.internal.ads.zzbzw;
import com.google.android.gms.internal.ads.zzbzx;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzk extends RemoteCreator {
    public zzbta zza;

    @VisibleForTesting
    public zzk() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        return iInterfaceQueryLocalInterface instanceof zzbv ? (zzbv) iInterfaceQueryLocalInterface : new zzbv(iBinder);
    }

    @Nullable
    public final zzbu zza(Context context, zzq zzqVar, String str, zzbny zzbnyVar, int i) {
        zzbbk.zza(context);
        if (((Boolean) zzba.zza.zzd.zzb(zzbbk.zzjl)).booleanValue()) {
            try {
                IBinder iBinderZze = ((zzbv) zzbzx.zzb(context, "com.google.android.gms.ads.ChimeraAdManagerCreatorImpl", zzj.zza)).zze(ObjectWrapper.wrap(context), zzqVar, str, zzbnyVar, 231700000, i);
                if (iBinderZze != null) {
                    IInterface iInterfaceQueryLocalInterface = iBinderZze.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    return iInterfaceQueryLocalInterface instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface : new zzbs(iBinderZze);
                }
            } catch (RemoteException e) {
                e = e;
                Throwable th = e;
                zzbta zzbtaVarZza = zzbsy.zza(context);
                this.zza = zzbtaVarZza;
                zzbtaVarZza.zzf(th, "AdManagerCreator.newAdManagerByDynamiteLoader");
                zzbzt.zzl("#007 Could not call remote method.", th);
                return null;
            } catch (zzbzw e2) {
                e = e2;
                Throwable th2 = e;
                zzbta zzbtaVarZza2 = zzbsy.zza(context);
                this.zza = zzbtaVarZza2;
                zzbtaVarZza2.zzf(th2, "AdManagerCreator.newAdManagerByDynamiteLoader");
                zzbzt.zzl("#007 Could not call remote method.", th2);
                return null;
            } catch (NullPointerException e3) {
                e = e3;
                Throwable th22 = e;
                zzbta zzbtaVarZza22 = zzbsy.zza(context);
                this.zza = zzbtaVarZza22;
                zzbtaVarZza22.zzf(th22, "AdManagerCreator.newAdManagerByDynamiteLoader");
                zzbzt.zzl("#007 Could not call remote method.", th22);
                return null;
            }
        } else {
            try {
                IBinder iBinderZze2 = ((zzbv) getRemoteCreatorInstance(context)).zze(ObjectWrapper.wrap(context), zzqVar, str, zzbnyVar, 231700000, i);
                if (iBinderZze2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = iBinderZze2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    return iInterfaceQueryLocalInterface2 instanceof zzbu ? (zzbu) iInterfaceQueryLocalInterface2 : new zzbs(iBinderZze2);
                }
            } catch (RemoteException e4) {
                e = e4;
                zzbzt.zzf("Could not create remote AdManager.", e);
                return null;
            } catch (RemoteCreator.RemoteCreatorException e5) {
                e = e5;
                zzbzt.zzf("Could not create remote AdManager.", e);
                return null;
            }
        }
        return null;
    }
}
