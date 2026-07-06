package com.amazon.ignitionshared.service;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.dtid.DtidCache;
import com.amazon.livingroom.deviceproperties.dtid.DtidRequester;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DtidRequestOnStartupWorker extends Worker {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final String TAG = "DtidRequestOnStartupWorker";

    @Inject
    public String defaultDtid;

    @Inject
    public DeviceProperties deviceProperties;

    @Inject
    public DtidCache dtidCache;

    @Inject
    public DtidRequester dtidRequester;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final void schedule(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Constraints.Builder builder = new Constraints.Builder();
            builder.mRequiredNetworkType = NetworkType.CONNECTED;
            WorkManagerImpl.getInstance(context).enqueueUniqueWork("DtidRequestOnStartupWorker", ExistingWorkPolicy.REPLACE, new OneTimeWorkRequest.Builder(DtidRequestOnStartupWorker.class).setConstraints(new Constraints(builder)).build());
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DtidRequestOnStartupWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        ApplicationComponent.Companion.getInstance().inject(this);
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        try {
            String defaultDtid = (String) DtidRequester.requestDtid$default(getDtidRequester(), getDeviceProperties(), null, 2, null).get(10L, TimeUnit.SECONDS);
            if (defaultDtid == null) {
                defaultDtid = getDefaultDtid();
            }
            getDtidCache().cacheDtid(defaultDtid);
            return new ListenableWorker.Result.Success();
        } catch (ExecutionException e) {
            Log.e(TAG, "DTID request failed with an error:", e);
            return new ListenableWorker.Result.Failure();
        } catch (TimeoutException e2) {
            Log.e(TAG, "DTID request timed out.", e2);
            return new ListenableWorker.Result.Failure();
        }
    }

    @NotNull
    public final String getDefaultDtid() {
        String str = this.defaultDtid;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("defaultDtid");
        throw null;
    }

    @NotNull
    public final DeviceProperties getDeviceProperties() {
        DeviceProperties deviceProperties = this.deviceProperties;
        if (deviceProperties != null) {
            return deviceProperties;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceProperties");
        throw null;
    }

    @NotNull
    public final DtidCache getDtidCache() {
        DtidCache dtidCache = this.dtidCache;
        if (dtidCache != null) {
            return dtidCache;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dtidCache");
        throw null;
    }

    @NotNull
    public final DtidRequester getDtidRequester() {
        DtidRequester dtidRequester = this.dtidRequester;
        if (dtidRequester != null) {
            return dtidRequester;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dtidRequester");
        throw null;
    }

    public final void setDefaultDtid(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultDtid = str;
    }

    public final void setDeviceProperties(@NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(deviceProperties, "<set-?>");
        this.deviceProperties = deviceProperties;
    }

    public final void setDtidCache(@NotNull DtidCache dtidCache) {
        Intrinsics.checkNotNullParameter(dtidCache, "<set-?>");
        this.dtidCache = dtidCache;
    }

    public final void setDtidRequester(@NotNull DtidRequester dtidRequester) {
        Intrinsics.checkNotNullParameter(dtidRequester, "<set-?>");
        this.dtidRequester = dtidRequester;
    }

    @Named(Names.IGNITION_APPLICATION_DEFAULT_DTID)
    public static /* synthetic */ void getDefaultDtid$annotations() {
    }

    @Named(Names.NON_OVERRIDABLE)
    public static /* synthetic */ void getDeviceProperties$annotations() {
    }
}
