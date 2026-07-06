package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentRequestParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzo {
    public final zzn zza;
    public final Activity zzb;
    public final ConsentDebugSettings zzc;
    public final ConsentRequestParameters zzd;

    public /* synthetic */ zzo(zzn zznVar, Activity activity, ConsentDebugSettings consentDebugSettings, ConsentRequestParameters consentRequestParameters, zzm zzmVar) {
        this.zza = zznVar;
        this.zzb = activity;
        this.zzc = consentDebugSettings;
        this.zzd = consentRequestParameters;
    }

    public static zzbu zza(zzo zzoVar) throws zzj {
        Bundle bundle;
        List<zzbp> list;
        List<zzbr> list2;
        PackageInfo packageInfo;
        zzbu zzbuVar = new zzbu();
        String string = zzoVar.zzd.zzb;
        if (TextUtils.isEmpty(string)) {
            try {
                bundle = zzoVar.zza.zza.getPackageManager().getApplicationInfo(zzoVar.zza.zza.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException unused) {
                bundle = null;
            }
            if (bundle != null) {
                string = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            }
            if (TextUtils.isEmpty(string)) {
                throw new zzj(3, "The UMP SDK requires a valid application ID in your AndroidManifest.xml through a com.google.android.gms.ads.APPLICATION_ID meta-data tag.\nExample AndroidManifest:\n    <meta-data\n        android:name=\"com.google.android.gms.ads.APPLICATION_ID\"\n        android:value=\"ca-app-pub-0000000000000000~0000000000\">");
            }
        }
        zzbuVar.zza = string;
        zza zzaVarZza = zzoVar.zza.zzb.zza();
        if (zzaVarZza != null) {
            zzbuVar.zzc = zzaVarZza.zza;
            zzbuVar.zzb = Boolean.valueOf(zzaVarZza.zzb);
        }
        if (zzoVar.zzc.isTestDevice()) {
            ArrayList arrayList = new ArrayList();
            int debugGeography = zzoVar.zzc.getDebugGeography();
            if (debugGeography == 1) {
                arrayList.add(zzbp.GEO_OVERRIDE_EEA);
            } else if (debugGeography == 2) {
                arrayList.add(zzbp.GEO_OVERRIDE_NON_EEA);
            }
            arrayList.add(zzbp.PREVIEWING_DEBUG_MESSAGES);
            list = arrayList;
        } else {
            list = Collections.EMPTY_LIST;
        }
        zzbuVar.zzk = list;
        zzbuVar.zzg = zzoVar.zza.zzc.zzb();
        zzbuVar.zzf = Boolean.valueOf(zzoVar.zzd.isTagForUnderAgeOfConsent());
        int i = Build.VERSION.SDK_INT;
        zzbuVar.zze = Locale.getDefault().toLanguageTag();
        zzbq zzbqVar = new zzbq();
        zzbqVar.zzb = Integer.valueOf(i);
        zzbqVar.zza = Build.MODEL;
        zzbqVar.zzc = 2;
        zzbuVar.zzd = zzbqVar;
        Configuration configuration = zzoVar.zza.zza.getResources().getConfiguration();
        zzoVar.zza.zza.getResources().getConfiguration();
        zzbs zzbsVar = new zzbs();
        zzbsVar.zza = Integer.valueOf(configuration.screenWidthDp);
        zzbsVar.zzb = Integer.valueOf(configuration.screenHeightDp);
        zzbsVar.zzc = Double.valueOf(zzoVar.zza.zza.getResources().getDisplayMetrics().density);
        if (i < 28) {
            list2 = Collections.EMPTY_LIST;
        } else {
            Activity activity = zzoVar.zzb;
            Window window = activity == null ? null : activity.getWindow();
            View decorView = window == null ? null : window.getDecorView();
            WindowInsets rootWindowInsets = decorView == null ? null : decorView.getRootWindowInsets();
            DisplayCutout displayCutout = rootWindowInsets == null ? null : rootWindowInsets.getDisplayCutout();
            if (displayCutout == null) {
                list2 = Collections.EMPTY_LIST;
            } else {
                displayCutout.getSafeInsetBottom();
                ArrayList arrayList2 = new ArrayList();
                for (Rect rect : displayCutout.getBoundingRects()) {
                    if (rect != null) {
                        zzbr zzbrVar = new zzbr();
                        zzbrVar.zzb = Integer.valueOf(rect.left);
                        zzbrVar.zzc = Integer.valueOf(rect.right);
                        zzbrVar.zza = Integer.valueOf(rect.top);
                        zzbrVar.zzd = Integer.valueOf(rect.bottom);
                        arrayList2.add(zzbrVar);
                    }
                }
                list2 = arrayList2;
            }
        }
        zzbsVar.zzd = list2;
        zzbuVar.zzh = zzbsVar;
        Application application = zzoVar.zza.zza;
        try {
            packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused2) {
            packageInfo = null;
        }
        zzbo zzboVar = new zzbo();
        zzboVar.zza = application.getPackageName();
        CharSequence applicationLabel = zzoVar.zza.zza.getPackageManager().getApplicationLabel(zzoVar.zza.zza.getApplicationInfo());
        zzboVar.zzb = applicationLabel != null ? applicationLabel.toString() : null;
        if (packageInfo != null) {
            zzboVar.zzc = Long.toString(Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode);
        }
        zzbuVar.zzi = zzboVar;
        zzbt zzbtVar = new zzbt();
        zzbtVar.zza = "2.0.0";
        zzbuVar.zzj = zzbtVar;
        return zzbuVar;
    }
}
