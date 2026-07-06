package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbcy;
import com.google.android.gms.internal.ads.zzbdm;
import com.google.android.gms.internal.ads.zzbzm;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzax {

    @Nullable
    public static final zzce zza;

    static {
        zzce zzccVar = null;
        try {
            Object objNewInstance = zzaw.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").getDeclaredConstructor(null).newInstance(null);
            if (objNewInstance instanceof IBinder) {
                IBinder iBinder = (IBinder) objNewInstance;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
                zzccVar = iInterfaceQueryLocalInterface instanceof zzce ? (zzce) iInterfaceQueryLocalInterface : new zzcc(iBinder);
            } else {
                zzbzt.zzj("ClientApi class is not an instance of IBinder.");
            }
        } catch (Exception unused) {
            zzbzt.zzj("Failed to instantiate ClientApi class.");
        }
        zza = zzccVar;
    }

    @NonNull
    public abstract Object zza();

    @Nullable
    public abstract Object zzb(zzce zzceVar) throws RemoteException;

    @Nullable
    public abstract Object zzc() throws RemoteException;

    public final Object zzd(Context context, boolean z) {
        boolean z2;
        Object objZze;
        if (!z) {
            zzay.zzb();
            if (!zzbzm.zzs(context, 12451000)) {
                zzbzt.zze("Google Play Services is not available.");
                z = true;
            }
        }
        boolean z3 = false;
        boolean z4 = !(DynamiteModule.getLocalVersion(context, "com.google.android.gms.ads.dynamite") <= DynamiteModule.zza(context, "com.google.android.gms.ads.dynamite", false));
        zzbbk.zza(context);
        if (((Boolean) zzbcy.zza.zze()).booleanValue()) {
            z2 = false;
        } else if (((Boolean) zzbcy.zzb.zze()).booleanValue()) {
            z2 = true;
            z3 = true;
        } else {
            z3 = z | z4;
            z2 = false;
        }
        if (z3) {
            objZze = zze();
            if (objZze == null && !z2) {
                objZze = zzf();
            }
        } else {
            Object objZzf = zzf();
            if (objZzf == null) {
                if (zzay.zze().nextInt(((Long) zzbdm.zza.zze()).intValue()) == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "dynamite_load");
                    bundle.putInt("is_missing", 1);
                    zzay zzayVar = zzay.zza;
                    zzayVar.zzb.zzn(context, zzayVar.zze.zza, "gmob-apps", bundle, true);
                }
            }
            objZze = objZzf == null ? zze() : objZzf;
        }
        return objZze == null ? zza() : objZze;
    }

    @Nullable
    public final Object zze() {
        zzce zzceVar = zza;
        if (zzceVar == null) {
            zzbzt.zzj("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return zzb(zzceVar);
        } catch (RemoteException e) {
            zzbzt.zzk("Cannot invoke local loader using ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    public final Object zzf() {
        try {
            return zzc();
        } catch (RemoteException e) {
            zzbzt.zzk("Cannot invoke remote loader.", e);
            return null;
        }
    }
}
