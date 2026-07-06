package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbke;
import com.google.android.gms.internal.ads.zzbkm;
import com.google.android.gms.internal.ads.zzbkn;
import com.google.android.gms.internal.ads.zzbnr;
import com.google.android.gms.internal.ads.zzbzt;
import com.google.android.gms.internal.ads.zzfpo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzej {
    public static zzej zza;
    public zzco zzg;
    public final Object zzb = new Object();
    public boolean zzd = false;
    public boolean zze = false;
    public final Object zzf = new Object();

    @Nullable
    public OnAdInspectorClosedListener zzh = null;

    @NonNull
    public RequestConfiguration zzi = new RequestConfiguration.Builder().build();
    public final ArrayList zzc = new ArrayList();

    public static zzej zzf() {
        zzej zzejVar;
        synchronized (zzej.class) {
            try {
                if (zza == null) {
                    zza = new zzej();
                }
                zzejVar = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzejVar;
    }

    public static InitializationStatus zzy(List list) {
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzbke zzbkeVar = (zzbke) it.next();
            map.put(zzbkeVar.zza, new zzbkm(zzbkeVar.zzb ? AdapterStatus.State.READY : AdapterStatus.State.NOT_READY, zzbkeVar.zzd, zzbkeVar.zzc));
        }
        return new zzbkn(map);
    }

    public final void zzA(Context context) {
        if (this.zzg == null) {
            this.zzg = (zzco) new zzaq(zzay.zza(), context).zzd(context, false);
        }
    }

    public final void zzB(@NonNull RequestConfiguration requestConfiguration) {
        try {
            this.zzg.zzu(new zzff(requestConfiguration));
        } catch (RemoteException e) {
            zzbzt.zzh("Unable to set request configuration parcel.", e);
        }
    }

    public final float zza() {
        synchronized (this.zzf) {
            zzco zzcoVar = this.zzg;
            float fZze = 1.0f;
            if (zzcoVar == null) {
                return 1.0f;
            }
            try {
                fZze = zzcoVar.zze();
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to get app volume.", e);
            }
            return fZze;
        }
    }

    @NonNull
    public final RequestConfiguration zzc() {
        return this.zzi;
    }

    public final InitializationStatus zze() {
        InitializationStatus initializationStatusZzy;
        synchronized (this.zzf) {
            try {
                Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to getting initialization status.");
                try {
                    initializationStatusZzy = zzy(this.zzg.zzg());
                } catch (RemoteException unused) {
                    zzbzt.zzg("Unable to get Initialization status.");
                    return new InitializationStatus() { // from class: com.google.android.gms.ads.internal.client.zzeb
                        @Override // com.google.android.gms.ads.initialization.InitializationStatus
                        public final Map getAdapterStatusMap() {
                            HashMap map = new HashMap();
                            map.put("com.google.android.gms.ads.MobileAds", new zzee());
                            return map;
                        }
                    };
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return initializationStatusZzy;
    }

    public final String zzh() {
        String strZzc;
        synchronized (this.zzf) {
            try {
                Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to getting version string.");
                try {
                    strZzc = zzfpo.zzc(this.zzg.zzf());
                } catch (RemoteException e) {
                    zzbzt.zzh("Unable to get internal version.", e);
                    return "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return strZzc;
    }

    public final void zzl(Context context) {
        synchronized (this.zzf) {
            zzA(context);
            try {
                this.zzg.zzi();
            } catch (RemoteException unused) {
                zzbzt.zzg("Unable to disable mediation adapter initialization.");
            }
        }
    }

    public final void zzm(boolean z) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to enable/disable Same App Key.");
            try {
                this.zzg.zzj(z);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to " + (z ? "enable" : "disable") + " Same App Key.", e);
                if (e.getMessage() != null && e.getMessage().toLowerCase(Locale.ROOT).contains("paid")) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0 A[Catch: all -> 0x005d, TryCatch #1 {all -> 0x005d, RemoteException -> 0x0060, blocks: (B:26:0x0034, B:28:0x0054, B:38:0x006d, B:40:0x007e, B:42:0x0090, B:49:0x00d3, B:43:0x00a0, B:45:0x00ae, B:47:0x00c0, B:48:0x00cb, B:35:0x0062, B:37:0x0068), top: B:57:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cb A[Catch: all -> 0x005d, TryCatch #1 {all -> 0x005d, RemoteException -> 0x0060, blocks: (B:26:0x0034, B:28:0x0054, B:38:0x006d, B:40:0x007e, B:42:0x0090, B:49:0x00d3, B:43:0x00a0, B:45:0x00ae, B:47:0x00c0, B:48:0x00cb, B:35:0x0062, B:37:0x0068), top: B:57:0x0034 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzn(final android.content.Context r3, @javax.annotation.Nullable java.lang.String r4, @javax.annotation.Nullable com.google.android.gms.ads.initialization.OnInitializationCompleteListener r5) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.client.zzej.zzn(android.content.Context, java.lang.String, com.google.android.gms.ads.initialization.OnInitializationCompleteListener):void");
    }

    public final /* synthetic */ void zzo(Context context, String str) {
        synchronized (this.zzf) {
            zzz(context, null);
        }
    }

    public final /* synthetic */ void zzp(Context context, String str) {
        synchronized (this.zzf) {
            zzz(context, null);
        }
    }

    public final void zzq(Context context, OnAdInspectorClosedListener onAdInspectorClosedListener) {
        synchronized (this.zzf) {
            zzA(context);
            this.zzh = onAdInspectorClosedListener;
            try {
                this.zzg.zzm(new zzeg());
            } catch (RemoteException unused) {
                zzbzt.zzg("Unable to open the ad inspector.");
                if (onAdInspectorClosedListener != null) {
                    onAdInspectorClosedListener.onAdInspectorClosed(new AdInspectorError(0, "Ad inspector had an internal error.", MobileAds.ERROR_DOMAIN, null));
                }
            }
        }
    }

    public final void zzr(Context context, String str) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to opening debug menu.");
            try {
                this.zzg.zzn(ObjectWrapper.wrap(context), str);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to open debug menu.", e);
            }
        }
    }

    public final void zzs(Class cls) {
        synchronized (this.zzf) {
            try {
                this.zzg.zzh(cls.getCanonicalName());
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to register RtbAdapter", e);
            }
        }
    }

    public final void zzt(boolean z) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to setting app muted state.");
            try {
                this.zzg.zzp(z);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to set app mute state.", e);
            }
        }
    }

    public final void zzu(float f) {
        boolean z = true;
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        synchronized (this.zzf) {
            if (this.zzg == null) {
                z = false;
            }
            Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
            try {
                this.zzg.zzq(f);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to set app volume.", e);
            }
        }
    }

    public final void zzv(String str) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to setting the plugin.");
            try {
                this.zzg.zzt(str);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to set plugin.", e);
            }
        }
    }

    public final void zzw(@NonNull RequestConfiguration requestConfiguration) {
        Preconditions.checkArgument(requestConfiguration != null, "Null passed to setRequestConfiguration.");
        synchronized (this.zzf) {
            try {
                RequestConfiguration requestConfiguration2 = this.zzi;
                this.zzi = requestConfiguration;
                if (this.zzg == null) {
                    return;
                }
                if (requestConfiguration2.getTagForChildDirectedTreatment() != requestConfiguration.getTagForChildDirectedTreatment() || requestConfiguration2.getTagForUnderAgeOfConsent() != requestConfiguration.getTagForUnderAgeOfConsent()) {
                    zzB(requestConfiguration);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean zzx() {
        synchronized (this.zzf) {
            zzco zzcoVar = this.zzg;
            boolean zZzv = false;
            if (zzcoVar == null) {
                return false;
            }
            try {
                zZzv = zzcoVar.zzv();
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to get app mute state.", e);
            }
            return zZzv;
        }
    }

    public final void zzz(Context context, @Nullable String str) {
        try {
            zzbnr.zza().zzb(context, null);
            this.zzg.zzk();
            this.zzg.zzl(null, ObjectWrapper.wrap(null));
        } catch (RemoteException e) {
            zzbzt.zzk("MobileAdsSettingManager initialization failed", e);
        }
    }
}
