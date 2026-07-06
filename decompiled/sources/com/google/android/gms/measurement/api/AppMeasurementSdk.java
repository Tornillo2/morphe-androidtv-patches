package com.google.android.gms.measurement.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.internal.zzhi;
import com.google.android.gms.measurement.internal.zzhj;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@KeepForSdk
public class AppMeasurementSdk {
    public final zzee zza;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static final class ConditionalUserProperty {

        @NonNull
        @KeepForSdk
        public static final String ACTIVE = "active";

        @NonNull
        @KeepForSdk
        public static final String CREATION_TIMESTAMP = "creation_timestamp";

        @NonNull
        @KeepForSdk
        public static final String EXPIRED_EVENT_NAME = "expired_event_name";

        @NonNull
        @KeepForSdk
        public static final String EXPIRED_EVENT_PARAMS = "expired_event_params";

        @NonNull
        @KeepForSdk
        public static final String NAME = "name";

        @NonNull
        @KeepForSdk
        public static final String ORIGIN = "origin";

        @NonNull
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_NAME = "timed_out_event_name";

        @NonNull
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_PARAMS = "timed_out_event_params";

        @NonNull
        @KeepForSdk
        public static final String TIME_TO_LIVE = "time_to_live";

        @NonNull
        @KeepForSdk
        public static final String TRIGGERED_EVENT_NAME = "triggered_event_name";

        @NonNull
        @KeepForSdk
        public static final String TRIGGERED_EVENT_PARAMS = "triggered_event_params";

        @NonNull
        @KeepForSdk
        public static final String TRIGGERED_TIMESTAMP = "triggered_timestamp";

        @NonNull
        @KeepForSdk
        public static final String TRIGGER_EVENT_NAME = "trigger_event_name";

        @NonNull
        @KeepForSdk
        public static final String TRIGGER_TIMEOUT = "trigger_timeout";

        @NonNull
        @KeepForSdk
        public static final String VALUE = "value";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ShowFirstParty
    @KeepForSdk
    public interface EventInterceptor extends zzhi {
        @Override // com.google.android.gms.measurement.internal.zzhi
        @ShowFirstParty
        @KeepForSdk
        @WorkerThread
        void interceptEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, long j);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ShowFirstParty
    @KeepForSdk
    public interface OnEventListener extends zzhj {
        @Override // com.google.android.gms.measurement.internal.zzhj
        @ShowFirstParty
        @KeepForSdk
        @WorkerThread
        void onEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, long j);
    }

    public AppMeasurementSdk(zzee zzeeVar) {
        this.zza = zzeeVar;
    }

    @NonNull
    @RequiresPermission(allOf = {"android.permission.INTERNET", DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, "android.permission.WAKE_LOCK"})
    @ShowFirstParty
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context) {
        return zzee.zzg(context, null, null, null, null).zze;
    }

    @KeepForSdk
    public void beginAdUnitExposure(@NonNull @Size(min = 1) String str) {
        this.zza.zzu(str);
    }

    @KeepForSdk
    public void clearConditionalUserProperty(@NonNull @Size(max = 24, min = 1) String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zza.zzv(str, str2, bundle);
    }

    @KeepForSdk
    public void endAdUnitExposure(@NonNull @Size(min = 1) String str) {
        this.zza.zzw(str);
    }

    @KeepForSdk
    public long generateEventId() {
        return this.zza.zzb();
    }

    @Nullable
    @KeepForSdk
    public String getAppIdOrigin() {
        return this.zza.zzi;
    }

    @Nullable
    @KeepForSdk
    public String getAppInstanceId() {
        return this.zza.zzl();
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public List<Bundle> getConditionalUserProperties(@Nullable String str, @Nullable @Size(max = 23, min = 1) String str2) {
        return this.zza.zzp(str, str2);
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenClass() {
        return this.zza.zzm();
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenName() {
        return this.zza.zzn();
    }

    @Nullable
    @KeepForSdk
    public String getGmpAppId() {
        return this.zza.zzo();
    }

    @KeepForSdk
    @WorkerThread
    public int getMaxUserProperties(@NonNull @Size(min = 1) String str) {
        return this.zza.zza(str);
    }

    @NonNull
    @KeepForSdk
    @WorkerThread
    public Map<String, Object> getUserProperties(@Nullable String str, @Nullable @Size(max = 24, min = 1) String str2, boolean z) {
        return this.zza.zzq(str, str2, z);
    }

    @KeepForSdk
    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) {
        this.zza.zzy(str, str2, bundle);
    }

    @KeepForSdk
    public void logEventNoInterceptor(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, long j) {
        this.zza.zzz(str, str2, bundle, j);
    }

    @Nullable
    @KeepForSdk
    public void performAction(@NonNull Bundle bundle) {
        this.zza.zzc(bundle, false);
    }

    @Nullable
    @KeepForSdk
    public Bundle performActionWithResponse(@NonNull Bundle bundle) {
        return this.zza.zzc(bundle, true);
    }

    @ShowFirstParty
    @KeepForSdk
    public void registerOnMeasurementEventListener(@NonNull OnEventListener onEventListener) {
        this.zza.zzB(onEventListener);
    }

    @KeepForSdk
    public void setConditionalUserProperty(@NonNull Bundle bundle) {
        this.zza.zzD(bundle);
    }

    @KeepForSdk
    public void setConsent(@NonNull Bundle bundle) {
        this.zza.zzE(bundle);
    }

    @KeepForSdk
    public void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        this.zza.zzG(activity, str, str2);
    }

    @ShowFirstParty
    @KeepForSdk
    @WorkerThread
    public void setEventInterceptor(@NonNull EventInterceptor eventInterceptor) {
        this.zza.zzJ(eventInterceptor);
    }

    @KeepForSdk
    public void setMeasurementEnabled(@Nullable Boolean bool) {
        this.zza.zzK(bool);
    }

    @KeepForSdk
    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull Object obj) {
        this.zza.zzN(str, str2, obj, true);
    }

    @ShowFirstParty
    @KeepForSdk
    public void unregisterOnMeasurementEventListener(@NonNull OnEventListener onEventListener) {
        this.zza.zzO(onEventListener);
    }

    public final void zza(boolean z) {
        this.zza.zzH(z);
    }

    @KeepForSdk
    public void setMeasurementEnabled(boolean z) {
        this.zza.zzK(Boolean.valueOf(z));
    }

    @NonNull
    @RequiresPermission(allOf = {"android.permission.INTERNET", DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context, @NonNull String str, @NonNull String str2, @Nullable String str3, @NonNull Bundle bundle) {
        return zzee.zzg(context, str, str2, str3, bundle).zze;
    }
}
