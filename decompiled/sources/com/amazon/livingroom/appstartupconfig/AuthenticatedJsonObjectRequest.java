package com.amazon.livingroom.appstartupconfig;

import androidx.annotation.Nullable;
import com.amazon.livingroom.auth.AccessTokenProvider;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.common.net.HttpHeaders;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AuthenticatedJsonObjectRequest extends JsonObjectRequest {
    public AccessTokenProvider accessTokenProvider;

    public AuthenticatedJsonObjectRequest(AccessTokenProvider accessTokenProvider, String str, @Nullable JSONObject jSONObject, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(str, jSONObject, listener, errorListener);
        this.accessTokenProvider = accessTokenProvider;
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() {
        HashMap map = new HashMap();
        map.put("x-gasc-enabled", "true");
        String accessToken = this.accessTokenProvider.getAccessToken();
        if (accessToken != null) {
            map.put(HttpHeaders.AUTHORIZATION, "Bearer ".concat(accessToken));
        }
        return DesugarCollections.unmodifiableMap(map);
    }
}
