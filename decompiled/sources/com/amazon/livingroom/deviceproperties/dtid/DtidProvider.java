package com.amazon.livingroom.deviceproperties.dtid;

import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nDtidProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DtidProvider.kt\ncom/amazon/livingroom/deviceproperties/dtid/DtidProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,105:1\n1#2:106\n*E\n"})
public final class DtidProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String MINERVA_CACHE_REFRESH_SUCCESS_RATE_METRIC = "DtidProvider.CacheRefreshSuccessRate";

    @NotNull
    public static final String MINERVA_DTID_CACHE_MISMATCH_METRIC = "DtidProvider.DtidCacheMismatch";

    @NotNull
    public static final String MINERVA_USING_CACHED_DTID_METRIC = "DtidProvider.UsingCachedDTID";
    public static final String TAG = "DtidProvider";

    @NotNull
    public final String defaultDtid;

    @Nullable
    public String deviceTypeId;

    @NotNull
    public final DtidCache dtidCache;
    public boolean dtidRequestFailed;

    @NotNull
    public final DtidRequester dtidRequester;

    @NotNull
    public final MetricsRecorder metricsRecorder;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public DtidProvider(@NotNull DtidRequester dtidRequester, @NotNull DtidCache dtidCache, @NotNull MetricsRecorder metricsRecorder, @Named(Names.IGNITION_APPLICATION_DEFAULT_DTID) @NotNull String defaultDtid) {
        Intrinsics.checkNotNullParameter(dtidRequester, "dtidRequester");
        Intrinsics.checkNotNullParameter(dtidCache, "dtidCache");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        Intrinsics.checkNotNullParameter(defaultDtid, "defaultDtid");
        this.dtidRequester = dtidRequester;
        this.dtidCache = dtidCache;
        this.metricsRecorder = metricsRecorder;
        this.defaultDtid = defaultDtid;
    }

    public static final Unit getCachedDtid$lambda$2(DtidProvider dtidProvider, MetricGroup metricGroup, String str, String str2) {
        if (str2 == null) {
            str2 = dtidProvider.defaultDtid;
        }
        MetricGroup.addCounterMetric$default(metricGroup, MINERVA_CACHE_REFRESH_SUCCESS_RATE_METRIC, 1, null, 4, null);
        if (!Intrinsics.areEqual(str, str2)) {
            MetricGroup.addCounterMetric$default(metricGroup, MINERVA_DTID_CACHE_MISMATCH_METRIC, 1, null, 4, null);
        }
        dtidProvider.dtidCache.cacheDtid(str2);
        return Unit.INSTANCE;
    }

    public static final Unit getCachedDtid$lambda$3(MetricGroup metricGroup, VolleyError it) {
        Intrinsics.checkNotNullParameter(it, "it");
        MetricGroup.addCounterMetric$default(metricGroup, MINERVA_CACHE_REFRESH_SUCCESS_RATE_METRIC, 0, null, 4, null);
        return Unit.INSTANCE;
    }

    public final String getCachedDtid(DeviceProperties deviceProperties) {
        Pair<String, Boolean> cachedDtid = this.dtidCache.getCachedDtid();
        final String str = cachedDtid.first;
        boolean zBooleanValue = cachedDtid.second.booleanValue();
        if (str != null) {
            final MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.DTID_PROVIDER_SCHEMA_ID);
            Log.d(TAG, "Using cached DTID: \"" + str + "\", staleCache: " + zBooleanValue);
            MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, MINERVA_USING_CACHED_DTID_METRIC, 1, null, 4, null);
            if (zBooleanValue) {
                this.dtidRequester.requestDtid(deviceProperties, new Function1() { // from class: com.amazon.livingroom.deviceproperties.dtid.DtidProvider$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DtidProvider.getCachedDtid$lambda$2(this.f$0, metricGroupCreateMetricGroup, str, (String) obj);
                    }
                }, new Function1() { // from class: com.amazon.livingroom.deviceproperties.dtid.DtidProvider$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DtidProvider.getCachedDtid$lambda$3(metricGroupCreateMetricGroup, (VolleyError) obj);
                    }
                });
            }
            this.metricsRecorder.recordMinerva(metricGroupCreateMetricGroup);
        }
        return str;
    }

    @NotNull
    public final synchronized String getDeviceTypeId(@NotNull DeviceProperties deviceProperties) {
        String cachedDtid;
        try {
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            cachedDtid = this.deviceTypeId;
            if (cachedDtid == null) {
                cachedDtid = getCachedDtid(deviceProperties);
                if (cachedDtid == null) {
                    String requestedDtid = getRequestedDtid(deviceProperties);
                    if (requestedDtid == null) {
                        requestedDtid = this.defaultDtid;
                    }
                    cachedDtid = requestedDtid;
                }
                this.deviceTypeId = cachedDtid;
            }
        } catch (Throwable th) {
            throw th;
        }
        return cachedDtid;
    }

    public final synchronized boolean getDtidRequestFailed() {
        return this.dtidRequestFailed;
    }

    public final String getRequestedDtid(DeviceProperties deviceProperties) {
        try {
            String str = (String) DtidRequester.requestDtid$default(this.dtidRequester, deviceProperties, null, 2, null).get(5000L, TimeUnit.MILLISECONDS);
            Log.d(TAG, "Using mapped DTID: " + str);
            this.dtidCache.cacheDtid(str == null ? this.defaultDtid : str);
            return str;
        } catch (ExecutionException e) {
            Log.e(TAG, "DTID request failed with an error:", e);
            this.dtidRequestFailed = true;
            return null;
        } catch (TimeoutException e2) {
            Log.e(TAG, "DTID request timed out.", e2);
            this.dtidRequestFailed = true;
            return null;
        }
    }
}
