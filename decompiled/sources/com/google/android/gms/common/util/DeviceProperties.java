package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.apps.common.proguard.SideEffectFree;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class DeviceProperties {

    @Nullable
    public static Boolean zza;

    @Nullable
    public static Boolean zzb;

    @Nullable
    public static Boolean zzc;

    @Nullable
    public static Boolean zzd;

    @Nullable
    public static Boolean zze;

    @Nullable
    public static Boolean zzf;

    @Nullable
    public static Boolean zzg;

    @Nullable
    public static Boolean zzh;

    @Nullable
    public static Boolean zzi;

    @Nullable
    public static Boolean zzj;

    @Nullable
    public static Boolean zzk;

    @Nullable
    public static Boolean zzl;

    @Nullable
    public static Boolean zzm;

    @Nullable
    public static Boolean zzn;

    @KeepForSdk
    public static boolean isAuto(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzj == null) {
            boolean z = false;
            if (PlatformVersion.isAtLeastO() && packageManager.hasSystemFeature("android.hardware.type.automotive")) {
                z = true;
            }
            zzj = Boolean.valueOf(z);
        }
        return zzj.booleanValue();
    }

    @KeepForSdk
    public static boolean isBstar(@NonNull Context context) {
        if (zzm == null) {
            boolean z = false;
            if (PlatformVersion.isAtLeastR() && context.getPackageManager().hasSystemFeature("com.google.android.play.feature.HPE_EXPERIENCE")) {
                z = true;
            }
            zzm = Boolean.valueOf(z);
        }
        return zzm.booleanValue();
    }

    @KeepForSdk
    public static boolean isFoldable(@NonNull Context context) {
        if (zzc == null) {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            boolean z = false;
            if (PlatformVersion.isAtLeastR() && sensorManager != null && sensorManager.getDefaultSensor(36) != null) {
                z = true;
            }
            zzc = Boolean.valueOf(z);
        }
        return zzc.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(@NonNull Context context) {
        if (zzg == null) {
            PackageManager packageManager = context.getPackageManager();
            boolean z = false;
            if (packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services")) {
                z = true;
            }
            zzg = Boolean.valueOf(z);
        }
        return zzg.booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0073  */
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isPhone(@androidx.annotation.NonNull android.content.Context r4) {
        /*
            java.lang.Boolean r0 = com.google.android.gms.common.util.DeviceProperties.zza
            if (r0 != 0) goto L7a
            boolean r0 = isFoldable(r4)
            r1 = 1
            if (r0 != 0) goto L74
            boolean r0 = isTablet(r4)
            r2 = 0
            if (r0 != 0) goto L73
            android.content.pm.PackageManager r0 = r4.getPackageManager()
            boolean r0 = zzd(r0)
            if (r0 != 0) goto L73
            boolean r0 = zzb(r4)
            if (r0 != 0) goto L73
            java.lang.Boolean r0 = com.google.android.gms.common.util.DeviceProperties.zzi
            if (r0 != 0) goto L36
            android.content.pm.PackageManager r0 = r4.getPackageManager()
            java.lang.String r3 = "org.chromium.arc"
            boolean r0 = r0.hasSystemFeature(r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            com.google.android.gms.common.util.DeviceProperties.zzi = r0
        L36:
            java.lang.Boolean r0 = com.google.android.gms.common.util.DeviceProperties.zzi
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L73
            boolean r0 = isAuto(r4)
            if (r0 != 0) goto L73
            boolean r0 = isTv(r4)
            if (r0 != 0) goto L73
            java.lang.Boolean r0 = com.google.android.gms.common.util.DeviceProperties.zzl
            if (r0 != 0) goto L5e
            android.content.pm.PackageManager r0 = r4.getPackageManager()
            java.lang.String r3 = "com.google.android.feature.AMATI_EXPERIENCE"
            boolean r0 = r0.hasSystemFeature(r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            com.google.android.gms.common.util.DeviceProperties.zzl = r0
        L5e:
            java.lang.Boolean r0 = com.google.android.gms.common.util.DeviceProperties.zzl
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L73
            boolean r0 = isBstar(r4)
            if (r0 != 0) goto L73
            boolean r4 = isXr(r4)
            if (r4 != 0) goto L73
            goto L74
        L73:
            r1 = 0
        L74:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
            com.google.android.gms.common.util.DeviceProperties.zza = r4
        L7a:
            java.lang.Boolean r4 = com.google.android.gms.common.util.DeviceProperties.zza
            boolean r4 = r4.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.DeviceProperties.isPhone(android.content.Context):boolean");
    }

    @KeepForSdk
    public static boolean isSevenInchTablet(@NonNull Context context) {
        return zzc(context.getResources());
    }

    @KeepForSdk
    @TargetApi(21)
    public static boolean isSidewinder(@NonNull Context context) {
        return zza(context);
    }

    @KeepForSdk
    public static boolean isTablet(@NonNull Context context) {
        return isTablet(context.getResources());
    }

    @KeepForSdk
    public static boolean isTv(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzk == null) {
            boolean z = true;
            if (!packageManager.hasSystemFeature("com.google.android.tv") && !packageManager.hasSystemFeature("android.hardware.type.television") && !packageManager.hasSystemFeature("android.software.leanback")) {
                z = false;
            }
            zzk = Boolean.valueOf(z);
        }
        return zzk.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        int i = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return "user".equals(Build.TYPE);
    }

    @SideEffectFree
    @KeepForSdk
    @TargetApi(20)
    public static boolean isWearable(@NonNull Context context) {
        return zzd(context.getPackageManager());
    }

    @KeepForSdk
    @TargetApi(26)
    public static boolean isWearableWithoutPlayStore(@NonNull Context context) {
        if (isWearable(context) && !PlatformVersion.isAtLeastN()) {
            return true;
        }
        if (zza(context)) {
            return !PlatformVersion.isAtLeastO() || PlatformVersion.isAtLeastR();
        }
        return false;
    }

    @KeepForSdk
    public static boolean isXr(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (zzn == null) {
            zzn = Boolean.valueOf(packageManager.hasSystemFeature("android.software.xr.immersive"));
        }
        return zzn.booleanValue();
    }

    @TargetApi(21)
    public static boolean zza(@NonNull Context context) {
        if (zzf == null) {
            zzf = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzf.booleanValue();
    }

    public static boolean zzb(@NonNull Context context) {
        if (zzh == null) {
            boolean z = true;
            if (!context.getPackageManager().hasSystemFeature("android.hardware.type.iot") && !context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z = false;
            }
            zzh = Boolean.valueOf(z);
        }
        return zzh.booleanValue();
    }

    public static boolean zzc(@NonNull Resources resources) {
        boolean z = false;
        if (resources == null) {
            return false;
        }
        if (zzd == null) {
            Configuration configuration = resources.getConfiguration();
            if ((configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600) {
                z = true;
            }
            zzd = Boolean.valueOf(z);
        }
        return zzd.booleanValue();
    }

    @SideEffectFree
    @TargetApi(20)
    public static boolean zzd(@NonNull PackageManager packageManager) {
        if (zze == null) {
            zze = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        return zze.booleanValue();
    }

    @KeepForSdk
    public static boolean isTablet(@NonNull Resources resources) {
        if (resources == null) {
            return false;
        }
        if (zzb == null) {
            zzb = Boolean.valueOf((resources.getConfiguration().screenLayout & 15) > 3 || zzc(resources));
        }
        return zzb.booleanValue();
    }
}
