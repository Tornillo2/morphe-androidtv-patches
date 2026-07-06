package com.amazon.livingroom.appstartupconfig;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.ignitionshared.HashingHandler;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigCache;
import com.amazon.livingroom.auth.RefreshTokenProvider;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import com.android.volley.toolbox.RequestFuture;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class AppStartupConfigProvider {
    public static final long CACHE_TIMEOUT_MS = TimeUnit.DAYS.toMillis(2);
    public static final String LOG_TAG = "AppStartupConfigProvider";
    public final AppStartupConfigCache cache;
    public final RefreshTokenProvider refreshTokenProvider;
    public RequestFuture<JSONObject> requestFuture;
    public final AppStartupConfigRequester requester;

    @Inject
    public AppStartupConfigProvider(@NonNull AppStartupConfigRequester appStartupConfigRequester, @NonNull AppStartupConfigCache appStartupConfigCache, @NonNull RefreshTokenProvider refreshTokenProvider) {
        this.requester = appStartupConfigRequester;
        this.cache = appStartupConfigCache;
        this.refreshTokenProvider = refreshTokenProvider;
        this.requestFuture = appStartupConfigRequester.requestAppStartupConfig();
    }

    public final boolean checkCacheAuthenticationStatus(AppStartupConfigCache.Wrapper wrapper) {
        try {
            if (TextUtils.equals(HashingHandler.generatePBKDF2Hash(this.refreshTokenProvider.getRefreshToken()), wrapper.refreshToken)) {
                return true;
            }
            Log.w(LOG_TAG, "AppStartupConfig cache authentication status changed. It will only be used if a new config can't be fetched.");
            return false;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.e(LOG_TAG, "The refresh token could not be hashed for the app AppStartupConfigProvider", e);
            return true;
        }
    }

    public final boolean checkCacheLocale(AppStartupConfigCache.Wrapper wrapper) {
        if (Locale.getDefault().toString().equals(wrapper.locale)) {
            return true;
        }
        Log.w(LOG_TAG, "AppStartupConfig cache locale changed. It will only be used if a new config can't be fetched.");
        return false;
    }

    public final boolean checkCacheTimestamp(AppStartupConfigCache.Wrapper wrapper) {
        if (wrapper.timestamp >= System.currentTimeMillis() - CACHE_TIMEOUT_MS) {
            return true;
        }
        Log.w(LOG_TAG, "AppStartupConfig cache is stale. It will only be used if a more recent value can't be fetched.");
        return false;
    }

    @Nullable
    public synchronized JSONObject getAppStartupConfig(long j, TimeUnit timeUnit) {
        JSONObject requestResponse;
        boolean z;
        try {
            boolean z2 = true;
            boolean z3 = false;
            if (this.requestFuture.isDone()) {
                requestResponse = getRequestResponse(j, timeUnit);
                z = true;
            } else {
                requestResponse = null;
                z = false;
            }
            AppStartupConfigCache.Wrapper wrapperLoad = this.cache.load();
            if (wrapperLoad == null) {
                z2 = false;
            } else {
                boolean z4 = (checkCacheAuthenticationStatus(wrapperLoad) && checkCacheLocale(wrapperLoad)) ? false : true;
                if (z && z4) {
                    this.requestFuture = this.requester.requestAppStartupConfig();
                }
                if (requestResponse == null) {
                    requestResponse = wrapperLoad.data;
                    if (requestResponse == null || !checkCacheTimestamp(wrapperLoad)) {
                        z2 = false;
                    }
                    z3 = z4;
                } else {
                    z3 = z4;
                    z2 = false;
                }
            }
            if (z3 || (!z2 && !z)) {
                JSONObject requestResponse2 = getRequestResponse(j, timeUnit);
                if (requestResponse2 != null) {
                    requestResponse = requestResponse2;
                }
            }
        } finally {
        }
        return requestResponse;
    }

    @Nullable
    public final JSONObject getRequestResponse(long j, TimeUnit timeUnit) {
        try {
            return this.requestFuture.get(j, timeUnit);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "Interrupted while waiting for AppStartupConfig response", e);
            Thread.currentThread().interrupt();
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            Log.e(LOG_TAG, "Failed to get AppStartupConfig. A cached value will be used, if available", e);
            return null;
        } catch (TimeoutException e3) {
            e = e3;
            Log.e(LOG_TAG, "Failed to get AppStartupConfig. A cached value will be used, if available", e);
            return null;
        }
    }
}
