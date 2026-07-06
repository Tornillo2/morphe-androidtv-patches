package com.amazon.ignitionshared.networking;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class JsonRequestWithHeaders extends JsonRequest<JsonObject> {

    @NotNull
    public final Map<String, String> headers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonRequestWithHeaders(int i, @NotNull String url, @Nullable JsonObject jsonObject, @NotNull Response.Listener<JsonObject> listener, @NotNull Response.ErrorListener errorListener, @NotNull Map<String, String> headers) {
        super(i, url, jsonObject != null ? jsonObject.toString() : null, listener, errorListener);
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(errorListener, "errorListener");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.headers = headers;
    }

    @Override // com.android.volley.Request
    @NotNull
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override // com.android.volley.toolbox.JsonRequest, com.android.volley.Request
    @NotNull
    public Response<JsonObject> parseNetworkResponse(@NotNull NetworkResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            byte[] data = response.data;
            Intrinsics.checkNotNullExpressionValue(data, "data");
            Charset charsetForName = Charset.forName(HttpHeaderParser.parseCharset(response.headers, JsonRequest.PROTOCOL_CHARSET));
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
            return new Response<>(JsonElementKt.getJsonObject(Json.Default.parseToJsonElement(new String(data, charsetForName))), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return new Response<>(new ParseError(e));
        } catch (SerializationException e2) {
            return new Response<>(new ParseError(e2));
        }
    }
}
