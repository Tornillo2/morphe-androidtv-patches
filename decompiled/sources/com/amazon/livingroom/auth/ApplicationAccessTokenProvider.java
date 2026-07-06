package com.amazon.livingroom.auth;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.livingroom.auth.RefreshTokenProvider;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class ApplicationAccessTokenProvider implements AccessTokenProvider {
    public static final String TAG = "ApplicationAccessTokenProvider";
    public String accessToken;
    public final ApplicationAccessTokenRequester applicationAccessTokenRequester;
    public final CopyOnWriteArrayList<AuthenticationChangeListener> authenticationChangeListeners;
    public Long expiryTime;
    public final RefreshTokenProvider refreshTokenProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AuthenticationChangeListener {
        void onChange(boolean z);
    }

    @Inject
    public ApplicationAccessTokenProvider(@ApplicationContext Context context, ApplicationAccessTokenRequester applicationAccessTokenRequester, RefreshTokenProvider refreshTokenProvider) {
        this.applicationAccessTokenRequester = applicationAccessTokenRequester;
        this.refreshTokenProvider = refreshTokenProvider;
        refreshTokenProvider.addRefreshTokenChangeListener(new RefreshTokenProvider.RefreshTokenChangeListener() { // from class: com.amazon.livingroom.auth.ApplicationAccessTokenProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.livingroom.auth.RefreshTokenProvider.RefreshTokenChangeListener
            public final void onChange() {
                this.f$0.notifyAuthenticationListeners();
            }
        });
        this.authenticationChangeListeners = new CopyOnWriteArrayList<>();
    }

    private void clearAccessToken() {
        Log.d(TAG, "Access token cleared");
        this.accessToken = null;
        this.expiryTime = null;
    }

    private boolean isExpired() {
        return this.accessToken == null || this.expiryTime == null || System.currentTimeMillis() > this.expiryTime.longValue();
    }

    private void setExpiryTime(long j) {
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        this.expiryTime = lValueOf;
        this.expiryTime = Long.valueOf((j * 1000) + lValueOf.longValue());
        Log.d(TAG, "Expiry time updated: " + this.expiryTime);
    }

    public void addAuthenticationChangeListener(AuthenticationChangeListener authenticationChangeListener) {
        this.authenticationChangeListeners.add(authenticationChangeListener);
    }

    @Override // com.amazon.livingroom.auth.AccessTokenProvider
    @Nullable
    public String getAccessToken() {
        tryUpdateAccessToken();
        return this.accessToken;
    }

    public final void notifyAuthenticationListeners() {
        boolean z = this.refreshTokenProvider.getRefreshToken() != null;
        Log.d(TAG, "Notifying listeners");
        Iterator<AuthenticationChangeListener> it = this.authenticationChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onChange(z);
        }
    }

    public final void setAccessToken(String str) {
        this.accessToken = str;
        Log.d(TAG, "Access token updated");
    }

    public final synchronized void tryUpdateAccessToken() {
        String str = TAG;
        Log.d(str, "Checking for new access token");
        String refreshToken = this.refreshTokenProvider.getRefreshToken();
        if (refreshToken == null) {
            clearAccessToken();
            return;
        }
        if (isExpired()) {
            Log.d(str, "Requesting access token");
            try {
                JSONObject jSONObject = this.applicationAccessTokenRequester.requestAccessToken(refreshToken).get();
                setAccessToken(jSONObject.getString("access_token"));
                setExpiryTime(jSONObject.getLong("expires_in"));
                Log.d(str, "Received access token");
            } catch (InterruptedException e) {
                e = e;
                Log.e(TAG, "Failed to request access token", e);
                clearAccessToken();
            } catch (ExecutionException e2) {
                e = e2;
                Log.e(TAG, "Failed to request access token", e);
                clearAccessToken();
            } catch (JSONException e3) {
                Log.e(TAG, "Failed to process access token data", e3);
                clearAccessToken();
            }
        }
    }
}
