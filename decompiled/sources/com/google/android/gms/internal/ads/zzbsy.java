package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.core.os.EnvironmentCompat;
import com.amazon.ignitionshared.BuildConfig;
import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.ParametersAreNonnullByDefault;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.JavaPlatformStringLookup;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ParametersAreNonnullByDefault
public final class zzbsy implements zzbta {

    @VisibleForTesting
    public static zzbta zza;

    @VisibleForTesting
    public static zzbta zzb;
    public static final Object zzc = new Object();
    public final Context zze;
    public final zzbzz zzh;
    public final Object zzd = new Object();
    public final WeakHashMap zzf = new WeakHashMap();
    public final ExecutorService zzg = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());

    public zzbsy(Context context, zzbzz zzbzzVar) {
        this.zze = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzh = zzbzzVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: all -> 0x0033, TryCatch #0 {all -> 0x0033, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0015, B:10:0x0027, B:13:0x0035, B:14:0x003c), top: B:19:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzbta zza(android.content.Context r3) {
        /*
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbsy.zzc
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzbta r1 = com.google.android.gms.internal.ads.zzbsy.zza     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L3c
            com.google.android.gms.internal.ads.zzbcp r1 = com.google.android.gms.internal.ads.zzbdm.zze     // Catch: java.lang.Throwable -> L33
            java.lang.Object r1 = r1.zze()     // Catch: java.lang.Throwable -> L33
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> L33
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L35
            com.google.android.gms.internal.ads.zzbbc r1 = com.google.android.gms.internal.ads.zzbbk.zzho     // Catch: java.lang.Throwable -> L33
            com.google.android.gms.internal.ads.zzbbi r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch: java.lang.Throwable -> L33
            java.lang.Object r1 = r2.zzb(r1)     // Catch: java.lang.Throwable -> L33
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> L33
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L35
            com.google.android.gms.internal.ads.zzbsy r1 = new com.google.android.gms.internal.ads.zzbsy     // Catch: java.lang.Throwable -> L33
            com.google.android.gms.internal.ads.zzbzz r2 = com.google.android.gms.internal.ads.zzbzz.zza()     // Catch: java.lang.Throwable -> L33
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L33
            com.google.android.gms.internal.ads.zzbsy.zza = r1     // Catch: java.lang.Throwable -> L33
            goto L3c
        L33:
            r3 = move-exception
            goto L40
        L35:
            com.google.android.gms.internal.ads.zzbsz r3 = new com.google.android.gms.internal.ads.zzbsz     // Catch: java.lang.Throwable -> L33
            r3.<init>()     // Catch: java.lang.Throwable -> L33
            com.google.android.gms.internal.ads.zzbsy.zza = r3     // Catch: java.lang.Throwable -> L33
        L3c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L33
            com.google.android.gms.internal.ads.zzbta r3 = com.google.android.gms.internal.ads.zzbsy.zza
            return r3
        L40:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L33
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsy.zza(android.content.Context):com.google.android.gms.internal.ads.zzbta");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0062 A[Catch: all -> 0x004e, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0015, B:10:0x0027, B:12:0x0036, B:13:0x0038, B:16:0x0041, B:22:0x0052, B:23:0x0053, B:24:0x0062, B:25:0x0069, B:14:0x0039, B:15:0x0040), top: B:32:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzbta zzb(android.content.Context r4, com.google.android.gms.internal.ads.zzbzz r5) {
        /*
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzbsy.zzc
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzbta r1 = com.google.android.gms.internal.ads.zzbsy.zzb     // Catch: java.lang.Throwable -> L4e
            if (r1 != 0) goto L69
            com.google.android.gms.internal.ads.zzbcp r1 = com.google.android.gms.internal.ads.zzbdm.zze     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r1 = r1.zze()     // Catch: java.lang.Throwable -> L4e
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> L4e
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L62
            com.google.android.gms.internal.ads.zzbbc r1 = com.google.android.gms.internal.ads.zzbbk.zzho     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbbi r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r1 = r2.zzb(r1)     // Catch: java.lang.Throwable -> L4e
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> L4e
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L4e
            if (r1 != 0) goto L62
            com.google.android.gms.internal.ads.zzbsy r1 = new com.google.android.gms.internal.ads.zzbsy     // Catch: java.lang.Throwable -> L4e
            r1.<init>(r4, r5)     // Catch: java.lang.Throwable -> L4e
            android.os.Looper r4 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L4e
            java.lang.Thread r4 = r4.getThread()     // Catch: java.lang.Throwable -> L4e
            if (r4 == 0) goto L53
            java.lang.Object r5 = r1.zzd     // Catch: java.lang.Throwable -> L4e
            monitor-enter(r5)     // Catch: java.lang.Throwable -> L4e
            java.util.WeakHashMap r2 = r1.zzf     // Catch: java.lang.Throwable -> L50
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L50
            r2.put(r4, r3)     // Catch: java.lang.Throwable -> L50
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.Thread$UncaughtExceptionHandler r5 = r4.getUncaughtExceptionHandler()     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbsx r2 = new com.google.android.gms.internal.ads.zzbsx     // Catch: java.lang.Throwable -> L4e
            r2.<init>(r1, r5)     // Catch: java.lang.Throwable -> L4e
            r4.setUncaughtExceptionHandler(r2)     // Catch: java.lang.Throwable -> L4e
            goto L53
        L4e:
            r4 = move-exception
            goto L6d
        L50:
            r4 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L50
            throw r4     // Catch: java.lang.Throwable -> L4e
        L53:
            java.lang.Thread$UncaughtExceptionHandler r4 = java.lang.Thread.getDefaultUncaughtExceptionHandler()     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbsw r5 = new com.google.android.gms.internal.ads.zzbsw     // Catch: java.lang.Throwable -> L4e
            r5.<init>(r1, r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r5)     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbsy.zzb = r1     // Catch: java.lang.Throwable -> L4e
            goto L69
        L62:
            com.google.android.gms.internal.ads.zzbsz r4 = new com.google.android.gms.internal.ads.zzbsz     // Catch: java.lang.Throwable -> L4e
            r4.<init>()     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbsy.zzb = r4     // Catch: java.lang.Throwable -> L4e
        L69:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.internal.ads.zzbta r4 = com.google.android.gms.internal.ads.zzbsy.zzb
            return r4
        L6d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4e
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsy.zzb(android.content.Context, com.google.android.gms.internal.ads.zzbzz):com.google.android.gms.internal.ads.zzbta");
    }

    public static String zzc(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static String zzd(Throwable th) {
        return zzfpo.zzc(zzbzm.zzf(zzc(th)));
    }

    public final void zze(Thread thread, Throwable th) {
        if (th != null) {
            boolean zZzo = false;
            boolean zEquals = false;
            for (Throwable cause = th; cause != null; cause = cause.getCause()) {
                for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
                    zZzo |= zzbzm.zzo(stackTraceElement.getClassName());
                    zEquals |= zzbsy.class.getName().equals(stackTraceElement.getClassName());
                }
            }
            if (!zZzo || zEquals) {
                return;
            }
            zzg(th, "", 1.0f);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbta
    public final void zzf(Throwable th, String str) {
        zzg(th, str, 1.0f);
    }

    @Override // com.google.android.gms.internal.ads.zzbta
    public final void zzg(Throwable th, String str, float f) {
        Throwable th2;
        boolean zIsCallerInstantApp;
        String packageName;
        Handler handler = zzbzm.zza;
        int i = 0;
        if (((Boolean) zzbdm.zzf.zze()).booleanValue()) {
            th2 = th;
        } else {
            LinkedList linkedList = new LinkedList();
            for (Throwable cause = th; cause != null; cause = cause.getCause()) {
                linkedList.push(cause);
            }
            th2 = null;
            while (!linkedList.isEmpty()) {
                Throwable th3 = (Throwable) linkedList.pop();
                StackTraceElement[] stackTrace = th3.getStackTrace();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new StackTraceElement(th3.getClass().getName(), "<filtered>", "<filtered>", 1));
                boolean z = false;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (zzbzm.zzo(stackTraceElement.getClassName())) {
                        arrayList.add(stackTraceElement);
                        z = true;
                    } else {
                        String className = stackTraceElement.getClassName();
                        if (!TextUtils.isEmpty(className) && (className.startsWith("android.") || className.startsWith("java."))) {
                            arrayList.add(stackTraceElement);
                        } else {
                            arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                        }
                    }
                }
                if (z) {
                    th2 = th2 == null ? new Throwable(th3.getMessage()) : new Throwable(th3.getMessage(), th2);
                    th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
                }
            }
        }
        if (th2 == null) {
            return;
        }
        String name = th.getClass().getName();
        String strZzc = zzc(th);
        String strZzd = ((Boolean) zzba.zza.zzd.zzb(zzbbk.zzim)).booleanValue() ? zzd(th) : "";
        double d = f;
        double dRandom = Math.random();
        int i2 = f > 0.0f ? (int) (1.0f / f) : 1;
        if (dRandom < d) {
            ArrayList arrayList2 = new ArrayList();
            try {
                zIsCallerInstantApp = Wrappers.zza.zza(this.zze).isCallerInstantApp();
            } catch (Throwable th4) {
                zzbzt.zzh("Error fetching instant app info", th4);
                zIsCallerInstantApp = false;
            }
            try {
                packageName = this.zze.getPackageName();
            } catch (Throwable unused) {
                zzbzt.zzj("Cannot obtain package name, proceeding.");
                packageName = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(zIsCallerInstantApp)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter(JavaPlatformStringLookup.KEY_OS, Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
            String str2 = Build.MANUFACTURER;
            String strM = Build.MODEL;
            if (!strM.startsWith(str2)) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str2, StringUtils.SPACE, strM);
            }
            Uri.Builder builderAppendQueryParameter2 = builderAppendQueryParameter.appendQueryParameter(CctTransportBackend.KEY_DEVICE, strM).appendQueryParameter(BuildConfig.DEFAULT_IGNITIONX_APP_STARTUP_MODE, this.zzh.zza).appendQueryParameter("appid", packageName).appendQueryParameter("exceptiontype", name).appendQueryParameter("stacktrace", strZzc).appendQueryParameter("eids", TextUtils.join(",", zzba.zza.zzb.zza())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "533571732").appendQueryParameter("rc", "dev").appendQueryParameter("sampling_rate", Integer.toString(i2)).appendQueryParameter("pb_tm", String.valueOf(zzbdm.zzc.zze())).appendQueryParameter("gmscv", String.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zze))).appendQueryParameter("lite", true != this.zzh.zze ? "0" : "1");
            if (!TextUtils.isEmpty(strZzd)) {
                builderAppendQueryParameter2.appendQueryParameter("hash", strZzd);
            }
            arrayList2.add(builderAppendQueryParameter2.toString());
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                final String str3 = (String) obj;
                final zzbzy zzbzyVar = new zzbzy(null);
                this.zzg.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbsv
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbzyVar.zza(str3);
                    }
                });
            }
        }
    }
}
