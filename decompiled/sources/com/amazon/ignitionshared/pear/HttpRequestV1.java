package com.amazon.ignitionshared.pear;

import androidx.media3.common.DrmInitData$SchemeData$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransporter;
import com.android.volley.toolbox.HttpClientStack;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class HttpRequestV1 {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final JsonObject body;

    @NotNull
    public final JsonObject headers;

    @NotNull
    public final String host;

    @NotNull
    public final String path;

    @NotNull
    public final String port;

    @NotNull
    public final String protocol;

    @NotNull
    public final JsonObject queryParams;

    @NotNull
    public final Lazy verb$delegate;

    @NotNull
    public final String verbString;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<HttpRequestV1> serializer() {
            return HttpRequestV1$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ HttpRequestV1(int i, JsonObject jsonObject, JsonObject jsonObject2, String str, String str2, String str3, String str4, JsonObject jsonObject3, String str5, SerializationConstructorMarker serializationConstructorMarker) {
        if (255 != (i & 255)) {
            PluginExceptionsKt.throwMissingFieldException(i, 255, HttpRequestV1$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.body = jsonObject;
        this.headers = jsonObject2;
        this.host = str;
        this.path = str2;
        this.port = str3;
        this.protocol = str4;
        this.queryParams = jsonObject3;
        this.verbString = str5;
        this.verb$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.amazon.ignitionshared.pear.HttpRequestV1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(HttpRequestV1._init_$lambda$1(this.f$0));
            }
        });
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final int _init_$lambda$1(HttpRequestV1 httpRequestV1) {
        String str = httpRequestV1.verbString;
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String upperCase = str.toUpperCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        switch (upperCase.hashCode()) {
            case -531492226:
                return !upperCase.equals("OPTIONS") ? -1 : 5;
            case 70454:
                return !upperCase.equals("GET") ? -1 : 0;
            case 79599:
                return !upperCase.equals("PUT") ? -1 : 2;
            case 2213344:
                return !upperCase.equals("HEAD") ? -1 : 4;
            case 2461856:
                return !upperCase.equals(MetricsTransporter.REQUEST_METHOD) ? -1 : 1;
            case 75900968:
                return !upperCase.equals(HttpClientStack.HttpPatch.METHOD_NAME) ? -1 : 7;
            case 80083237:
                return !upperCase.equals("TRACE") ? -1 : 6;
            case 2012838315:
                return !upperCase.equals("DELETE") ? -1 : 3;
            default:
                return -1;
        }
    }

    public static /* synthetic */ HttpRequestV1 copy$default(HttpRequestV1 httpRequestV1, JsonObject jsonObject, JsonObject jsonObject2, String str, String str2, String str3, String str4, JsonObject jsonObject3, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = httpRequestV1.body;
        }
        if ((i & 2) != 0) {
            jsonObject2 = httpRequestV1.headers;
        }
        if ((i & 4) != 0) {
            str = httpRequestV1.host;
        }
        if ((i & 8) != 0) {
            str2 = httpRequestV1.path;
        }
        if ((i & 16) != 0) {
            str3 = httpRequestV1.port;
        }
        if ((i & 32) != 0) {
            str4 = httpRequestV1.protocol;
        }
        if ((i & 64) != 0) {
            jsonObject3 = httpRequestV1.queryParams;
        }
        if ((i & 128) != 0) {
            str5 = httpRequestV1.verbString;
        }
        JsonObject jsonObject4 = jsonObject3;
        String str6 = str5;
        String str7 = str3;
        String str8 = str4;
        return httpRequestV1.copy(jsonObject, jsonObject2, str, str2, str7, str8, jsonObject4, str6);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final int verb_delegate$lambda$0(HttpRequestV1 httpRequestV1) {
        String str = httpRequestV1.verbString;
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String upperCase = str.toUpperCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        switch (upperCase.hashCode()) {
            case -531492226:
                return !upperCase.equals("OPTIONS") ? -1 : 5;
            case 70454:
                return !upperCase.equals("GET") ? -1 : 0;
            case 79599:
                return !upperCase.equals("PUT") ? -1 : 2;
            case 2213344:
                return !upperCase.equals("HEAD") ? -1 : 4;
            case 2461856:
                return !upperCase.equals(MetricsTransporter.REQUEST_METHOD) ? -1 : 1;
            case 75900968:
                return !upperCase.equals(HttpClientStack.HttpPatch.METHOD_NAME) ? -1 : 7;
            case 80083237:
                return !upperCase.equals("TRACE") ? -1 : 6;
            case 2012838315:
                return !upperCase.equals("DELETE") ? -1 : 3;
            default:
                return -1;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(HttpRequestV1 httpRequestV1, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        JsonObjectSerializer jsonObjectSerializer = JsonObjectSerializer.INSTANCE;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, jsonObjectSerializer, httpRequestV1.body);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, jsonObjectSerializer, httpRequestV1.headers);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, httpRequestV1.host);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, httpRequestV1.path);
        compositeEncoder.encodeStringElement(serialDescriptor, 4, httpRequestV1.port);
        compositeEncoder.encodeStringElement(serialDescriptor, 5, httpRequestV1.protocol);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 6, jsonObjectSerializer, httpRequestV1.queryParams);
        compositeEncoder.encodeStringElement(serialDescriptor, 7, httpRequestV1.verbString);
    }

    @NotNull
    public final JsonObject component1() {
        return this.body;
    }

    @NotNull
    public final JsonObject component2() {
        return this.headers;
    }

    @NotNull
    public final String component3() {
        return this.host;
    }

    @NotNull
    public final String component4() {
        return this.path;
    }

    @NotNull
    public final String component5() {
        return this.port;
    }

    @NotNull
    public final String component6() {
        return this.protocol;
    }

    @NotNull
    public final JsonObject component7() {
        return this.queryParams;
    }

    @NotNull
    public final String component8() {
        return this.verbString;
    }

    @NotNull
    public final HttpRequestV1 copy(@NotNull JsonObject body, @NotNull JsonObject headers, @NotNull String host, @NotNull String path, @NotNull String port, @NotNull String protocol, @NotNull JsonObject queryParams, @NotNull String verbString) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(port, "port");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(queryParams, "queryParams");
        Intrinsics.checkNotNullParameter(verbString, "verbString");
        return new HttpRequestV1(body, headers, host, path, port, protocol, queryParams, verbString);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRequestV1)) {
            return false;
        }
        HttpRequestV1 httpRequestV1 = (HttpRequestV1) obj;
        return Intrinsics.areEqual(this.body, httpRequestV1.body) && Intrinsics.areEqual(this.headers, httpRequestV1.headers) && Intrinsics.areEqual(this.host, httpRequestV1.host) && Intrinsics.areEqual(this.path, httpRequestV1.path) && Intrinsics.areEqual(this.port, httpRequestV1.port) && Intrinsics.areEqual(this.protocol, httpRequestV1.protocol) && Intrinsics.areEqual(this.queryParams, httpRequestV1.queryParams) && Intrinsics.areEqual(this.verbString, httpRequestV1.verbString);
    }

    @NotNull
    public final JsonObject getBody() {
        return this.body;
    }

    @NotNull
    public final JsonObject getHeaders() {
        return this.headers;
    }

    @NotNull
    public final String getHost() {
        return this.host;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    @NotNull
    public final String getPort() {
        return this.port;
    }

    @NotNull
    public final String getProtocol() {
        return this.protocol;
    }

    @NotNull
    public final JsonObject getQueryParams() {
        return this.queryParams;
    }

    public final int getVerb() {
        return ((Number) this.verb$delegate.getValue()).intValue();
    }

    @NotNull
    public final String getVerbString() {
        return this.verbString;
    }

    public int hashCode() {
        return this.verbString.hashCode() + ((this.queryParams.content.hashCode() + DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.protocol, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.port, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.path, DrmInitData$SchemeData$$ExternalSyntheticOutline0.m(this.host, (this.headers.content.hashCode() + (this.body.content.hashCode() * 31)) * 31, 31), 31), 31), 31)) * 31);
    }

    @NotNull
    public String toString() {
        return "HttpRequestV1(body=" + this.body + ", headers=" + this.headers + ", host=" + this.host + ", path=" + this.path + ", port=" + this.port + ", protocol=" + this.protocol + ", queryParams=" + this.queryParams + ", verbString=" + this.verbString + ")";
    }

    public HttpRequestV1(@NotNull JsonObject body, @NotNull JsonObject headers, @NotNull String host, @NotNull String path, @NotNull String port, @NotNull String protocol, @NotNull JsonObject queryParams, @NotNull String verbString) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(port, "port");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(queryParams, "queryParams");
        Intrinsics.checkNotNullParameter(verbString, "verbString");
        this.body = body;
        this.headers = headers;
        this.host = host;
        this.path = path;
        this.port = port;
        this.protocol = protocol;
        this.queryParams = queryParams;
        this.verbString = verbString;
        this.verb$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.amazon.ignitionshared.pear.HttpRequestV1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(HttpRequestV1.verb_delegate$lambda$0(this.f$0));
            }
        });
    }

    @SerialName("query_params")
    public static /* synthetic */ void getQueryParams$annotations() {
    }

    @SerialName("verb")
    public static /* synthetic */ void getVerbString$annotations() {
    }
}
