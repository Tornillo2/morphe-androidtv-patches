package com.google.android.gms.ads.internal.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.impl.WorkManagerImpl;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.ads.internal.offline.buffering.OfflineNotificationPoster;
import com.google.android.gms.ads.internal.offline.buffering.OfflinePingSender;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class WorkManagerUtil extends zzbq {
    @UsedByReflection("This class must be instantiated reflectively so that the default class loader can be used.")
    public WorkManagerUtil() {
    }

    public static void zzb(Context context) {
        try {
            WorkManagerImpl.initialize(context.getApplicationContext(), new Configuration(new Configuration.Builder()));
        } catch (IllegalStateException unused) {
        }
    }

    @Override // com.google.android.gms.ads.internal.util.zzbr
    public final void zze(@NonNull IObjectWrapper iObjectWrapper) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzb(context);
        try {
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            workManagerImpl.cancelAllWorkByTag("offline_ping_sender_work");
            Constraints.Builder builder = new Constraints.Builder();
            builder.mRequiredNetworkType = NetworkType.CONNECTED;
            workManagerImpl.enqueue(new OneTimeWorkRequest.Builder(OfflinePingSender.class).setConstraints(new Constraints(builder)).addTag("offline_ping_sender_work").build());
        } catch (IllegalStateException e) {
            zzbzt.zzk("Failed to instantiate WorkManager.", e);
        }
    }

    @Override // com.google.android.gms.ads.internal.util.zzbr
    public final boolean zzf(@NonNull IObjectWrapper iObjectWrapper, @NonNull String str, @NonNull String str2) throws Throwable {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzb(context);
        Constraints.Builder builder = new Constraints.Builder();
        builder.mRequiredNetworkType = NetworkType.CONNECTED;
        Constraints constraints = new Constraints(builder);
        Data.Builder builder2 = new Data.Builder();
        builder2.mValues.put("uri", str);
        builder2.mValues.put("gws_query_id", str2);
        try {
            WorkManagerImpl.getInstance(context).enqueue(new OneTimeWorkRequest.Builder(OfflineNotificationPoster.class).setConstraints(constraints).setInputData(builder2.build()).addTag("offline_notification_work").build());
            return true;
        } catch (IllegalStateException e) {
            zzbzt.zzk("Failed to instantiate WorkManager.", e);
            return false;
        }
    }
}
