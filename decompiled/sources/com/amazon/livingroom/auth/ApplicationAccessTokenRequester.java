package com.amazon.livingroom.auth;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import javax.inject.Inject;
import javax.inject.Named;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class ApplicationAccessTokenRequester {
    public static final String AUTHENTICATION_PATH = "/auth/token";
    public static final String PANDA_END_POINT = "api.amazon.com";
    public final String applicationName;
    public final RequestQueue requestQueue;

    @Inject
    public ApplicationAccessTokenRequester(@Named(Names.BACKGROUND_DELIVERY) RequestQueue requestQueue, @Named(Names.APPLICATION_PACKAGE_NAME) String str) {
        this.requestQueue = requestQueue;
        this.applicationName = str;
    }

    public static JSONObject getAccessParams(@NonNull String str, @NonNull String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requested_token_type", "access_token");
        jSONObject.put("app_name", str);
        jSONObject.put("app_version", "1.1");
        jSONObject.put("source_token", str2);
        jSONObject.put("source_token_type", "refresh_token");
        return jSONObject;
    }

    public static Uri getUri() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority(PANDA_END_POINT);
        builder.path(AUTHENTICATION_PATH);
        return builder.build();
    }

    public RequestFuture<JSONObject> requestAccessToken(String str) throws JSONException {
        RequestFuture<JSONObject> requestFuture = new RequestFuture<>();
        this.requestQueue.add(new JsonObjectRequest(1, getUri().toString(), getAccessParams(this.applicationName, str), requestFuture, requestFuture));
        return requestFuture;
    }
}
