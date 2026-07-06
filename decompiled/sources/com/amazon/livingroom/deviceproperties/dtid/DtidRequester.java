package com.amazon.livingroom.deviceproperties.dtid;

import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nDtidRequester.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DtidRequester.kt\ncom/amazon/livingroom/deviceproperties/dtid/DtidRequester\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,89:1\n1#2:90\n*E\n"})
public final class DtidRequester {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String DTID_FIELD = "generatedDTID";

    @NotNull
    public static final String MINERVA_REQUEST_LATENCY_METRIC_NAME = "DtidRequester.RequestLatency";

    @NotNull
    public static final String MINERVA_REQUEST_SUCCESS_RATE_METRIC_NAME = "DtidRequester.RequestSuccessRate";
    public static final String TAG = "DtidRequester";

    @NotNull
    public final DtidRequestUriFactory dtidRequestUriFactory;

    @NotNull
    public final MetricsRecorder metricsRecorder;

    @NotNull
    public final RequestQueue requestQueue;

    @NotNull
    public final RetryPolicy retryPolicy;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public DtidRequester(@NotNull DtidRequestUriFactory dtidRequestUriFactory, @NotNull MetricsRecorder metricsRecorder, @Named(Names.BACKGROUND_DELIVERY) @NotNull RequestQueue requestQueue, @Named(Names.GET_DTID_RETRY_POLICY) @NotNull RetryPolicy retryPolicy) {
        Intrinsics.checkNotNullParameter(dtidRequestUriFactory, "dtidRequestUriFactory");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        Intrinsics.checkNotNullParameter(requestQueue, "requestQueue");
        Intrinsics.checkNotNullParameter(retryPolicy, "retryPolicy");
        this.dtidRequestUriFactory = dtidRequestUriFactory;
        this.metricsRecorder = metricsRecorder;
        this.requestQueue = requestQueue;
        this.retryPolicy = retryPolicy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestFuture requestDtid$default(DtidRequester dtidRequester, DeviceProperties deviceProperties, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new DtidRequester$$ExternalSyntheticLambda0();
        }
        return dtidRequester.requestDtid(deviceProperties, function1);
    }

    public static final Unit requestDtid$lambda$1(VolleyError it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public static final void requestDtid$lambda$4(MetricGroup metricGroup, DtidRequester dtidRequester, RequestFuture requestFuture, Function1 function1, JSONObject jSONObject) {
        Object objCreateFailure;
        metricGroup.stopTimerMetric(MINERVA_REQUEST_LATENCY_METRIC_NAME);
        MetricGroup.addCounterMetric$default(metricGroup, MINERVA_REQUEST_SUCCESS_RATE_METRIC_NAME, 1, null, 4, null);
        dtidRequester.metricsRecorder.recordMinerva(metricGroup);
        try {
            String string = jSONObject.getString(DTID_FIELD);
            boolean zIsBlank = StringsKt__StringsKt.isBlank(string);
            objCreateFailure = string;
            if (zIsBlank) {
                objCreateFailure = null;
            }
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        String str = (String) (objCreateFailure instanceof Result.Failure ? null : objCreateFailure);
        Log.d(TAG, "DTID response: " + str);
        requestFuture.onResponse(str);
        function1.invoke(str);
    }

    public static final void requestDtid$lambda$5(MetricGroup metricGroup, DtidRequester dtidRequester, RequestFuture requestFuture, Function1 function1, VolleyError volleyError) {
        metricGroup.stopTimerMetric(MINERVA_REQUEST_LATENCY_METRIC_NAME);
        MetricGroup.addCounterMetric$default(metricGroup, MINERVA_REQUEST_SUCCESS_RATE_METRIC_NAME, 0, null, 4, null);
        dtidRequester.metricsRecorder.recordMinerva(metricGroup);
        Log.w(TAG, "DTID request failed!", volleyError);
        requestFuture.onErrorResponse(volleyError);
        Intrinsics.checkNotNull(volleyError);
        function1.invoke(volleyError);
    }

    @NotNull
    public final RequestFuture<String> requestDtid(@NotNull DeviceProperties deviceProperties, @NotNull Function1<? super String, Unit> onResponse) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(onResponse, "onResponse");
        return requestDtid(deviceProperties, onResponse, new DtidRequester$$ExternalSyntheticLambda3());
    }

    @NotNull
    public final RequestFuture<String> requestDtid(@NotNull DeviceProperties deviceProperties, @NotNull final Function1<? super String, Unit> onResponse, @NotNull final Function1<? super VolleyError, Unit> onError) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(onResponse, "onResponse");
        Intrinsics.checkNotNullParameter(onError, "onError");
        final RequestFuture<String> requestFuture = new RequestFuture<>();
        String string = this.dtidRequestUriFactory.getUri(deviceProperties).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Log.d(TAG, "DTID request URI: ".concat(string));
        final MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.DTID_REQUESTER_SCHEMA_ID);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(0, string, (String) null, new Response.Listener() { // from class: com.amazon.livingroom.deviceproperties.dtid.DtidRequester$$ExternalSyntheticLambda1
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                DtidRequester.requestDtid$lambda$4(metricGroupCreateMetricGroup, this, requestFuture, onResponse, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.amazon.livingroom.deviceproperties.dtid.DtidRequester$$ExternalSyntheticLambda2
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                DtidRequester.requestDtid$lambda$5(metricGroupCreateMetricGroup, this, requestFuture, onError, volleyError);
            }
        });
        jsonObjectRequest.mRetryPolicy = this.retryPolicy;
        MetricGroup.startTimerMetric$default(metricGroupCreateMetricGroup, MINERVA_REQUEST_LATENCY_METRIC_NAME, null, 2, null);
        requestFuture.mRequest = this.requestQueue.add(jsonObjectRequest);
        return requestFuture;
    }
}
