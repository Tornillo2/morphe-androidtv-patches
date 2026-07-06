package com.amazon.livingroom.auth;

import androidx.annotation.NonNull;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import java.nio.charset.StandardCharsets;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class RefreshTokenParser {
    public static final String TAG = "RefreshTokenParser";

    @Inject
    public RefreshTokenParser() {
    }

    public String parse(@NonNull byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONObject(new String(bArr, StandardCharsets.UTF_8)).getJSONObject("PandaAuth").getString("RefreshToken");
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse refresh token", e);
            return null;
        }
    }
}
