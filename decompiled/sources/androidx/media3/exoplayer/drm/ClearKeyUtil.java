package androidx.media3.exoplayer.drm;

import androidx.lifecycle.SavedStateHandle;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Charsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ClearKeyUtil {
    public static final String TAG = "ClearKeyUtil";

    public static byte[] adjustRequestData(byte[] bArr) {
        return Util.SDK_INT >= 27 ? bArr : base64ToBase64Url(Util.fromUtf8Bytes(bArr)).getBytes(Charsets.UTF_8);
    }

    public static byte[] adjustResponseData(byte[] bArr) {
        if (Util.SDK_INT >= 27) {
            return bArr;
        }
        try {
            JSONObject jSONObject = new JSONObject(Util.fromUtf8Bytes(bArr));
            StringBuilder sb = new StringBuilder("{\"keys\":[");
            JSONArray jSONArray = jSONObject.getJSONArray(SavedStateHandle.KEYS);
            for (int i = 0; i < jSONArray.length(); i++) {
                if (i != 0) {
                    sb.append(",");
                }
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                sb.append("{\"k\":\"");
                sb.append(base64UrlToBase64(jSONObject2.getString("k")));
                sb.append("\",\"kid\":\"");
                sb.append(base64UrlToBase64(jSONObject2.getString("kid")));
                sb.append("\",\"kty\":\"");
                sb.append(jSONObject2.getString("kty"));
                sb.append("\"}");
            }
            sb.append("]}");
            return sb.toString().getBytes(Charsets.UTF_8);
        } catch (JSONException e) {
            Log.e("ClearKeyUtil", "Failed to adjust response data: ".concat(Util.fromUtf8Bytes(bArr)), e);
            return bArr;
        }
    }

    public static String base64ToBase64Url(String str) {
        return str.replace('+', '-').replace('/', '_');
    }

    public static String base64UrlToBase64(String str) {
        return str.replace('-', '+').replace('_', '/');
    }
}
