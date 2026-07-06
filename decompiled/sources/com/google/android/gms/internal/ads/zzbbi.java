package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
public final class zzbbi implements SharedPreferences.OnSharedPreferenceChangeListener {
    public Context zzg;
    public final Object zzb = new Object();
    public final ConditionVariable zzc = new ConditionVariable();
    public volatile boolean zzd = false;

    @VisibleForTesting
    public volatile boolean zza = false;

    @Nullable
    public SharedPreferences zze = null;
    public Bundle zzf = new Bundle();
    public JSONObject zzh = new JSONObject();

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("flag_configuration".equals(str)) {
            zzf();
        }
    }

    public final Object zzb(final zzbbc zzbbcVar) {
        if (!this.zzc.block(5000L)) {
            synchronized (this.zzb) {
                try {
                    if (!this.zza) {
                        throw new IllegalStateException("Flags.initialize() was not called!");
                    }
                } finally {
                }
            }
        }
        if (!this.zzd || this.zze == null) {
            synchronized (this.zzb) {
                if (this.zzd && this.zze != null) {
                }
                return zzbbcVar.zzc;
            }
        }
        int i = zzbbcVar.zza;
        if (i != 2) {
            return (i == 1 && this.zzh.has(zzbbcVar.zzb)) ? zzbbcVar.zza(this.zzh) : zzbbp.zza(new zzfpp() { // from class: com.google.android.gms.internal.ads.zzbbf
                @Override // com.google.android.gms.internal.ads.zzfpp
                public final Object zza() {
                    return zzbbcVar.zzc(this.zza.zze);
                }
            });
        }
        Bundle bundle = this.zzf;
        return bundle == null ? zzbbcVar.zzc : zzbbcVar.zzb(bundle);
    }

    public final /* synthetic */ Object zzc(zzbbc zzbbcVar) {
        return zzbbcVar.zzc(this.zze);
    }

    public final /* synthetic */ String zzd() {
        return this.zze.getString("flag_configuration", "{}");
    }

    public final void zze(Context context) {
        if (this.zzd) {
            return;
        }
        synchronized (this.zzb) {
            try {
                if (this.zzd) {
                    return;
                }
                if (!this.zza) {
                    this.zza = true;
                }
                Context applicationContext = context.getApplicationContext() == null ? context : context.getApplicationContext();
                this.zzg = applicationContext;
                try {
                    this.zzf = Wrappers.zza.zza(applicationContext).getApplicationInfo(this.zzg.getPackageName(), 128).metaData;
                } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
                }
                try {
                    Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                    if (remoteContext != null || (remoteContext = context.getApplicationContext()) != null) {
                        context = remoteContext;
                    }
                    if (context == null) {
                        return;
                    }
                    zzba.zzb();
                    SharedPreferences sharedPreferences = context.getSharedPreferences("google_ads_flags", 0);
                    this.zze = sharedPreferences;
                    if (sharedPreferences != null) {
                        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
                    }
                    zzbdv.zzc(new zzbbh(this));
                    zzf();
                    this.zzd = true;
                } finally {
                    this.zza = false;
                    this.zzc.open();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzf() {
        if (this.zze == null) {
            return;
        }
        try {
            this.zzh = new JSONObject((String) zzbbp.zza(new zzfpp() { // from class: com.google.android.gms.internal.ads.zzbbg
                @Override // com.google.android.gms.internal.ads.zzfpp
                public final Object zza() {
                    return this.zza.zzd();
                }
            }));
        } catch (JSONException unused) {
        }
    }
}
