package com.amazon.ignitionshared.pear;

import android.net.Uri;
import android.util.Log;
import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class PearUriBuilder {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(PearUriBuilder.class)).getSimpleName();

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final Map<String, String> pearPlacementIdMap;

    @NotNull
    public final String recommendationApplicationKey;

    @NotNull
    public final TerminatorIdProvider terminatorIdProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public PearUriBuilder(@NotNull DeviceProperties deviceProperties, @NotNull TerminatorIdProvider terminatorIdProvider, @Named(Names.PEAR_PLACEMENT_ID_MAP) @NotNull Map<String, String> pearPlacementIdMap, @Named(Names.RECOMMENDATION_APPLICATION_KEY) @NotNull String recommendationApplicationKey) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(terminatorIdProvider, "terminatorIdProvider");
        Intrinsics.checkNotNullParameter(pearPlacementIdMap, "pearPlacementIdMap");
        Intrinsics.checkNotNullParameter(recommendationApplicationKey, "recommendationApplicationKey");
        this.deviceProperties = deviceProperties;
        this.terminatorIdProvider = terminatorIdProvider;
        this.pearPlacementIdMap = pearPlacementIdMap;
        this.recommendationApplicationKey = recommendationApplicationKey;
    }

    @NotNull
    public final String buildPearBootstrapUri() {
        Uri.Builder builderPath = new Uri.Builder().scheme("https").authority(this.terminatorIdProvider.getPearTerminatorId().concat(".api.amazonvideo.com")).path("ode/recommendations/v1/bootstrap_placements");
        Iterator<String> it = this.pearPlacementIdMap.values().iterator();
        while (it.hasNext()) {
            builderPath.appendQueryParameter("placement", it.next());
        }
        String string = builderPath.appendQueryParameter("app", this.recommendationApplicationKey).appendQueryParameter("deviceTypeId", (String) this.deviceProperties.get(DeviceProperties.DEVICE_TYPE_ID)).appendQueryParameter("deviceId", (String) this.deviceProperties.get(DeviceProperties.DEVICE_ID)).appendQueryParameter("language", (String) this.deviceProperties.get(DeviceProperties.DEVICE_LANGUAGE)).appendQueryParameter("firmware", (String) this.deviceProperties.get(DeviceProperties.FIRMWARE_VERSION)).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @NotNull
    public final String buildPearCredentialsUri(@NotNull Oauth20V1 credentials) {
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        HttpRequestV1 httpRequestV1 = credentials.refreshRequest.requestV1;
        try {
            Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme(httpRequestV1.protocol).authority(httpRequestV1.host).path(httpRequestV1.path).appendQueryParameter("refresh_token", credentials.refreshToken);
            for (Map.Entry<String, JsonElement> entry : httpRequestV1.queryParams.content.entrySet()) {
                if (!Intrinsics.areEqual(entry.getKey(), "refresh_token")) {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.serialization.json.JsonPrimitive");
                    builderAppendQueryParameter.appendQueryParameter(key, ((JsonPrimitive) value).getContent());
                }
            }
            String string = builderAppendQueryParameter.build().toString();
            Intrinsics.checkNotNull(string);
            return string;
        } catch (ClassCastException e) {
            Log.e(TAG, "Could not cast json value to json primitive", e);
            throw new IllegalArgumentException("Failed to parse query parameters", e);
        }
    }

    @NotNull
    public final String buildPearDynamicUri(@NotNull HttpRequestV1 requestParameters) {
        Intrinsics.checkNotNullParameter(requestParameters, "requestParameters");
        try {
            Uri.Builder builderPath = new Uri.Builder().scheme(requestParameters.protocol).authority(this.terminatorIdProvider.getPearTerminatorId().concat(".api.amazonvideo.com")).path(requestParameters.path);
            Iterator<String> it = this.pearPlacementIdMap.values().iterator();
            while (it.hasNext()) {
                builderPath.appendQueryParameter("placement", it.next());
            }
            for (Map.Entry<String, JsonElement> entry : requestParameters.queryParams.content.entrySet()) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlinx.serialization.json.JsonPrimitive");
                builderPath.appendQueryParameter(key, ((JsonPrimitive) value).getContent());
            }
            String string = builderPath.build().toString();
            Intrinsics.checkNotNull(string);
            return string;
        } catch (ClassCastException e) {
            Log.e(TAG, "Could not cast json value to json primitive", e);
            throw new IllegalArgumentException("Failed to parse query parameters", e);
        }
    }

    @NotNull
    public final Map<String, String> getPearPlacementIdMap() {
        return this.pearPlacementIdMap;
    }
}
