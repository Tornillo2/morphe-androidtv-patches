package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zacd implements OnCompleteListener {
    public final GoogleApiManager zaa;
    public final int zab;
    public final ApiKey zac;
    public final long zad;
    public final long zae;

    @VisibleForTesting
    public zacd(GoogleApiManager googleApiManager, int i, ApiKey apiKey, long j, long j2, @Nullable String str, @Nullable String str2) {
        this.zaa = googleApiManager;
        this.zab = i;
        this.zac = apiKey;
        this.zad = j;
        this.zae = j2;
    }

    @Nullable
    public static zacd zaa(GoogleApiManager googleApiManager, int i, ApiKey apiKey) {
        boolean methodTimingTelemetryEnabled;
        if (!googleApiManager.zaD()) {
            return null;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().zzc;
        if (rootTelemetryConfiguration == null) {
            methodTimingTelemetryEnabled = true;
        } else {
            if (!rootTelemetryConfiguration.getMethodInvocationTelemetryEnabled()) {
                return null;
            }
            methodTimingTelemetryEnabled = rootTelemetryConfiguration.getMethodTimingTelemetryEnabled();
            zabq zabqVarZai = googleApiManager.zai(apiKey);
            if (zabqVarZai != null) {
                Object obj = zabqVarZai.zac;
                if (!(obj instanceof BaseGmsClient)) {
                    return null;
                }
                BaseGmsClient baseGmsClient = (BaseGmsClient) obj;
                if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                    ConnectionTelemetryConfiguration connectionTelemetryConfigurationZab = zab(zabqVarZai, baseGmsClient, i);
                    if (connectionTelemetryConfigurationZab == null) {
                        return null;
                    }
                    zabqVarZai.zaq();
                    methodTimingTelemetryEnabled = connectionTelemetryConfigurationZab.getMethodTimingTelemetryEnabled();
                }
            }
        }
        return new zacd(googleApiManager, i, apiKey, methodTimingTelemetryEnabled ? System.currentTimeMillis() : 0L, methodTimingTelemetryEnabled ? SystemClock.elapsedRealtime() : 0L, null, null);
    }

    @Nullable
    public static ConnectionTelemetryConfiguration zab(zabq zabqVar, BaseGmsClient baseGmsClient, int i) {
        ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !telemetryConfiguration.getMethodInvocationTelemetryEnabled()) {
            return null;
        }
        int[] methodInvocationMethodKeyAllowlist = telemetryConfiguration.getMethodInvocationMethodKeyAllowlist();
        if (methodInvocationMethodKeyAllowlist == null) {
            int[] methodInvocationMethodKeyDisallowlist = telemetryConfiguration.getMethodInvocationMethodKeyDisallowlist();
            if (methodInvocationMethodKeyDisallowlist != null && ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i)) {
                return null;
            }
        } else if (!ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i)) {
            return null;
        }
        if (zabqVar.zam < telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return telemetryConfiguration;
        }
        return null;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    @WorkerThread
    public final void onComplete(@NonNull Task task) {
        zabq zabqVarZai;
        int version;
        int i;
        int i2;
        int i3;
        int i4;
        long j;
        long jCurrentTimeMillis;
        int iElapsedRealtime;
        if (this.zaa.zaD()) {
            RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().zzc;
            if ((rootTelemetryConfiguration == null || rootTelemetryConfiguration.getMethodInvocationTelemetryEnabled()) && (zabqVarZai = this.zaa.zai(this.zac)) != null) {
                Object obj = zabqVarZai.zac;
                if (obj instanceof BaseGmsClient) {
                    BaseGmsClient baseGmsClient = (BaseGmsClient) obj;
                    boolean methodTimingTelemetryEnabled = this.zad > 0;
                    int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                    if (rootTelemetryConfiguration != null) {
                        methodTimingTelemetryEnabled &= rootTelemetryConfiguration.getMethodTimingTelemetryEnabled();
                        int batchPeriodMillis = rootTelemetryConfiguration.getBatchPeriodMillis();
                        int maxMethodInvocationsInBatch = rootTelemetryConfiguration.getMaxMethodInvocationsInBatch();
                        version = rootTelemetryConfiguration.getVersion();
                        if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                            ConnectionTelemetryConfiguration connectionTelemetryConfigurationZab = zab(zabqVarZai, baseGmsClient, this.zab);
                            if (connectionTelemetryConfigurationZab == null) {
                                return;
                            }
                            boolean z = connectionTelemetryConfigurationZab.getMethodTimingTelemetryEnabled() && this.zad > 0;
                            maxMethodInvocationsInBatch = connectionTelemetryConfigurationZab.getMaxMethodInvocationsLogged();
                            methodTimingTelemetryEnabled = z;
                        }
                        i = batchPeriodMillis;
                        i2 = maxMethodInvocationsInBatch;
                    } else {
                        version = 0;
                        i = 5000;
                        i2 = 100;
                    }
                    GoogleApiManager googleApiManager = this.zaa;
                    if (task.isSuccessful()) {
                        i3 = 0;
                        i4 = 0;
                    } else {
                        if (task.isCanceled()) {
                            i3 = 100;
                        } else {
                            Exception exception = task.getException();
                            if (exception instanceof ApiException) {
                                Status status = ((ApiException) exception).getStatus();
                                int i5 = status.zzb;
                                ConnectionResult connectionResult = status.zze;
                                if (connectionResult == null) {
                                    i3 = i5;
                                } else {
                                    i4 = connectionResult.zzb;
                                    i3 = i5;
                                }
                            } else {
                                i3 = ProxyBillingActivity.REQUEST_CODE_IN_APP_MESSAGE_FLOW;
                            }
                        }
                        i4 = -1;
                    }
                    if (methodTimingTelemetryEnabled) {
                        long j2 = this.zad;
                        long j3 = this.zae;
                        j = j2;
                        jCurrentTimeMillis = System.currentTimeMillis();
                        iElapsedRealtime = (int) (SystemClock.elapsedRealtime() - j3);
                    } else {
                        j = 0;
                        jCurrentTimeMillis = 0;
                        iElapsedRealtime = -1;
                    }
                    googleApiManager.zaw(new MethodInvocation(this.zab, i3, i4, j, jCurrentTimeMillis, null, null, gCoreServiceId, iElapsedRealtime), version, i, i2);
                }
            }
        }
    }
}
