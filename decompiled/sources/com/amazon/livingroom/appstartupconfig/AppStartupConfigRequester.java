package com.amazon.livingroom.appstartupconfig;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.ignitionshared.HashingHandler;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigCache;
import com.amazon.livingroom.auth.ApplicationAccessTokenProvider;
import com.amazon.livingroom.auth.RefreshTokenProvider;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class AppStartupConfigRequester {
    public static final String LOG_TAG = "AppStartupConfigRequester";
    public static final String REQUESTS_METRIC = "AppStartupConfigRequest.Requests";
    public static final String TIME_TAKEN_METRIC = "AppStartupConfigRequest.TimeTaken";
    public static long startTime;
    public final ApplicationAccessTokenProvider applicationAccessTokenProvider;
    public final AppStartupConfigCache cache;
    public final GetAppStartupConfigUriFactory getAppStartupConfigUriFactory;
    public final MetricsRecorder metricsRecorder;
    public final RefreshTokenProvider refreshTokenProvider;
    public final RequestQueue requestQueue;
    public final RetryPolicy retryPolicy;

    @Inject
    public AppStartupConfigRequester(@NonNull @Named(Names.BACKGROUND_DELIVERY) RequestQueue requestQueue, @NonNull GetAppStartupConfigUriFactory getAppStartupConfigUriFactory, @NonNull @Named(Names.GET_APP_STARTUP_CONFIG_RETRY_POLICY) RetryPolicy retryPolicy, @NonNull AppStartupConfigCache appStartupConfigCache, @NonNull MetricsRecorder metricsRecorder, @NonNull ApplicationAccessTokenProvider applicationAccessTokenProvider, @NonNull RefreshTokenProvider refreshTokenProvider) {
        this.requestQueue = requestQueue;
        this.getAppStartupConfigUriFactory = getAppStartupConfigUriFactory;
        this.retryPolicy = retryPolicy;
        this.cache = appStartupConfigCache;
        this.metricsRecorder = metricsRecorder;
        this.applicationAccessTokenProvider = applicationAccessTokenProvider;
        this.refreshTokenProvider = refreshTokenProvider;
    }

    /* JADX INFO: renamed from: completeAndLog, reason: merged with bridge method [inline-methods] */
    public final void lambda$requestAppStartupConfig$1(RequestFuture<JSONObject> requestFuture, VolleyError volleyError, MetricGroup metricGroup) {
        Log.e(LOG_TAG, "GetAppStartupConfig request failed", volleyError);
        requestFuture.onErrorResponse(volleyError);
        metricGroup.addCounterMetric(REQUESTS_METRIC, 0);
        this.metricsRecorder.recordMinerva(metricGroup);
    }

    /* JADX INFO: renamed from: completeAndSave, reason: merged with bridge method [inline-methods] */
    public final void lambda$requestAppStartupConfig$0(RequestFuture<JSONObject> requestFuture, JSONObject jSONObject, MetricGroup metricGroup) {
        requestFuture.onResponse(jSONObject);
        saveCache(jSONObject);
        metricGroup.addCounterMetric(TIME_TAKEN_METRIC, (int) (System.currentTimeMillis() - startTime));
        metricGroup.addCounterMetric(REQUESTS_METRIC, 1);
        this.metricsRecorder.recordMinerva(metricGroup);
    }

    @NonNull
    public RequestFuture<JSONObject> requestAppStartupConfig() {
        final MetricGroup metricGroupCreateMetricGroup = this.metricsRecorder.createMetricGroup(MinervaConstants.APP_STARTUP_CONFIG_REQUEST_SCHEMA_ID);
        startTime = System.currentTimeMillis();
        Uri uriCreateGetAppStartupConfigUri = this.getAppStartupConfigUriFactory.createGetAppStartupConfigUri();
        final RequestFuture<JSONObject> requestFuture = new RequestFuture<>();
        AuthenticatedJsonObjectRequest authenticatedJsonObjectRequest = new AuthenticatedJsonObjectRequest(this.applicationAccessTokenProvider, uriCreateGetAppStartupConfigUri.toString(), null, new Response.Listener() { // from class: com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester$$ExternalSyntheticLambda0
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                this.f$0.lambda$requestAppStartupConfig$0(requestFuture, (JSONObject) obj, metricGroupCreateMetricGroup);
            }
        }, new Response.ErrorListener() { // from class: com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester$$ExternalSyntheticLambda1
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                this.f$0.lambda$requestAppStartupConfig$1(requestFuture, volleyError, metricGroupCreateMetricGroup);
            }
        });
        authenticatedJsonObjectRequest.mRetryPolicy = this.retryPolicy;
        requestFuture.mRequest = this.requestQueue.add(authenticatedJsonObjectRequest);
        return requestFuture;
    }

    public final void saveCache(JSONObject jSONObject) {
        String strGeneratePBKDF2Hash;
        try {
            strGeneratePBKDF2Hash = HashingHandler.generatePBKDF2Hash(this.refreshTokenProvider.getRefreshToken());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.e(LOG_TAG, "The refresh token could not be hashed for the AppStartupConfigCache.Wrapper", e);
            strGeneratePBKDF2Hash = "";
        }
        this.cache.save(new AppStartupConfigCache.Wrapper(System.currentTimeMillis(), Locale.getDefault().toString(), strGeneratePBKDF2Hash, jSONObject));
    }
}
