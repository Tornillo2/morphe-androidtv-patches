package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String queryParameter = intent.getData().getQueryParameter("backendName");
        String queryParameter2 = intent.getData().getQueryParameter("extras");
        int iIntValue = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int i = intent.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(context);
        TransportContext.Builder builder = TransportContext.builder();
        builder.setBackendName(queryParameter);
        Priority priorityValueOf = PriorityMapping.valueOf(iIntValue);
        AutoValue_TransportContext.Builder builder2 = (AutoValue_TransportContext.Builder) builder;
        builder2.priority = priorityValueOf;
        if (queryParameter2 != null) {
            builder2.extras = Base64.decode(queryParameter2, 0);
        }
        TransportRuntime.getInstance().getUploader().upload(builder.build(), i, new AlarmManagerSchedulerBroadcastReceiver$$ExternalSyntheticLambda0());
    }

    public static /* synthetic */ void $r8$lambda$a3lqwlC30QrIt23aPM9gZcB4sXs() {
    }

    public static /* synthetic */ void lambda$onReceive$0() {
    }
}
