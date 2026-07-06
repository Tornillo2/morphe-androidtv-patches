package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.internal.client.zzej;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbsm;
import com.google.android.gms.internal.ads.zzbyk;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MobileAds {

    @NonNull
    public static final String ERROR_DOMAIN = "com.google.android.gms.ads";

    public static void disableMediationAdapterInitialization(@NonNull Context context) {
        zzej.zzf().zzl(context);
    }

    public static void enableSameAppKey(boolean z) {
        zzej.zzf().zzm(z);
    }

    @Nullable
    public static InitializationStatus getInitializationStatus() {
        return zzej.zzf().zze();
    }

    @KeepForSdk
    public static String getInternalVersion() {
        return zzej.zzf().zzh();
    }

    @NonNull
    public static RequestConfiguration getRequestConfiguration() {
        return zzej.zzf().zzi;
    }

    @NonNull
    public static VersionInfo getVersion() {
        zzej.zzf();
        String[] strArrSplit = TextUtils.split("22.2.0", "\\.");
        if (strArrSplit.length != 3) {
            return new VersionInfo(0, 0, 0);
        }
        try {
            return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
        } catch (NumberFormatException unused) {
            return new VersionInfo(0, 0, 0);
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(@NonNull Context context) {
        zzej.zzf().zzn(context, null, null);
    }

    public static void openAdInspector(@NonNull Context context, @NonNull OnAdInspectorClosedListener onAdInspectorClosedListener) {
        zzej.zzf().zzq(context, onAdInspectorClosedListener);
    }

    public static void openDebugMenu(@NonNull Context context, @NonNull String str) {
        zzej.zzf().zzr(context, str);
    }

    @KeepForSdk
    public static void registerRtbAdapter(@NonNull Class<? extends RtbAdapter> cls) {
        zzej.zzf().zzs(cls);
    }

    @RequiresApi(api = 21)
    public static void registerWebView(@NonNull WebView webView) {
        zzej.zzf();
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (webView == null) {
            zzbzt.zzg("The webview to be registered cannot be null.");
            return;
        }
        zzbyk zzbykVarZza = zzbsm.zza(webView.getContext());
        if (zzbykVarZza == null) {
            zzbzt.zzj("Internal error, query info generator is null.");
            return;
        }
        try {
            zzbykVarZza.zzi(ObjectWrapper.wrap(webView));
        } catch (RemoteException e) {
            zzbzt.zzh("", e);
        }
    }

    public static void setAppMuted(boolean z) {
        zzej.zzf().zzt(z);
    }

    public static void setAppVolume(float f) {
        zzej.zzf().zzu(f);
    }

    @KeepForSdk
    private static void setPlugin(String str) {
        zzej.zzf().zzv(str);
    }

    public static void setRequestConfiguration(@NonNull RequestConfiguration requestConfiguration) {
        zzej.zzf().zzw(requestConfiguration);
    }

    public static void initialize(@NonNull Context context, @NonNull OnInitializationCompleteListener onInitializationCompleteListener) {
        zzej.zzf().zzn(context, null, onInitializationCompleteListener);
    }
}
