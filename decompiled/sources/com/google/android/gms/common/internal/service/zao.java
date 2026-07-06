package com.google.android.gms.common.internal.service;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zao extends GoogleApi implements TelemetryLoggingClient {
    public static final /* synthetic */ int zab = 0;
    public static final Api.ClientKey zac;
    public static final Api.AbstractClientBuilder zad;
    public static final Api zae;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zac = clientKey;
        zan zanVar = new zan();
        zad = zanVar;
        zae = new Api("ClientTelemetry.API", zanVar, clientKey);
    }

    public zao(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        super(context, (Activity) null, zae, telemetryLoggingOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.common.internal.TelemetryLoggingClient
    public final Task<Void> log(final TelemetryData telemetryData) {
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zac = new Feature[]{com.google.android.gms.internal.base.zaf.zaa};
        builder.zab = false;
        builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.internal.service.zam
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                int i = zao.zab;
                ((zai) ((zap) obj).getService()).zae(telemetryData);
                ((TaskCompletionSource) obj2).setResult(null);
            }
        };
        return zae(2, builder.build());
    }
}
