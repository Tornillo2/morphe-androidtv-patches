package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zza extends AsyncTask {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ ProviderInstaller.ProviderInstallListener zzb;

    public zza(Context context, ProviderInstaller.ProviderInstallListener providerInstallListener) {
        this.zza = context;
        this.zzb = providerInstallListener;
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        try {
            ProviderInstaller.installIfNeeded(this.zza);
            return 0;
        } catch (GooglePlayServicesNotAvailableException e) {
            return Integer.valueOf(e.errorCode);
        } catch (GooglePlayServicesRepairableException e2) {
            return Integer.valueOf(e2.getConnectionStatusCode());
        }
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.zzb.onProviderInstalled();
            return;
        }
        this.zzb.onProviderInstallFailed(num.intValue(), ProviderInstaller.zza.getErrorResolutionIntent(this.zza, num.intValue(), "pi"));
    }
}
