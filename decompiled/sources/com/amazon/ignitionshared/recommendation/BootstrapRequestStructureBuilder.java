package com.amazon.ignitionshared.recommendation;

import com.amazon.android.ignitionx.BuildConfig;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nBootstrapRequestStructureBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BootstrapRequestStructureBuilder.kt\ncom/amazon/ignitionshared/recommendation/BootstrapRequestStructureBuilder\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,53:1\n29#2,2:54\n29#2,2:56\n29#2,3:58\n31#2:61\n29#2,3:62\n31#2:65\n29#2,2:66\n29#2,2:68\n29#2,3:70\n29#2,3:73\n29#2,3:76\n31#2:79\n31#2:80\n*S KotlinDebug\n*F\n+ 1 BootstrapRequestStructureBuilder.kt\ncom/amazon/ignitionshared/recommendation/BootstrapRequestStructureBuilder\n*L\n15#1:54,2\n18#1:56,2\n19#1:58,3\n18#1:61\n24#1:62,3\n15#1:65\n28#1:66,2\n30#1:68,2\n35#1:70,3\n42#1:73,3\n46#1:76,3\n30#1:79\n28#1:80\n*E\n"})
public final class BootstrapRequestStructureBuilder {

    @NotNull
    public final DeviceProperties deviceProperties;

    @Inject
    public BootstrapRequestStructureBuilder(@NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        this.deviceProperties = deviceProperties;
    }

    @NotNull
    public final String buildRequestStructureForBootstrap() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "version", "1.0.0");
        JsonElementBuildersKt.put(jsonObjectBuilder, "app", BuildConfig.FLAVOR_App);
        JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
        JsonObjectBuilder jsonObjectBuilder3 = new JsonObjectBuilder();
        jsonObjectBuilder3.put("request", getRequestBody());
        jsonObjectBuilder3.put("error", getRequestBody());
        jsonObjectBuilder2.put("recommendations-v1", jsonObjectBuilder3.build());
        jsonObjectBuilder.put("update", jsonObjectBuilder2.build());
        jsonObjectBuilder.put("_metadata", new JsonObjectBuilder().build());
        return jsonObjectBuilder.build().toString();
    }

    public final JsonObject getRequestBody() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        Object obj = this.deviceProperties.get(DeviceProperties.DEVICE_TYPE_ID);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        String str = (String) obj;
        JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder2, "host", "threeplr-" + str + "-0.api.amazonvideo.com");
        JsonElementBuildersKt.put(jsonObjectBuilder2, "port", "443");
        JsonElementBuildersKt.put(jsonObjectBuilder2, "protocol", "https");
        JsonElementBuildersKt.put(jsonObjectBuilder2, "path", "/ode/recommendations/v1/bootstrap_placements");
        JsonObjectBuilder jsonObjectBuilder3 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder3, "app", BuildConfig.FLAVOR_App);
        JsonElementBuildersKt.put(jsonObjectBuilder3, "deviceTypeId", str);
        JsonElementBuildersKt.put(jsonObjectBuilder3, "deviceId", (String) this.deviceProperties.get(DeviceProperties.DEVICE_ID));
        JsonElementBuildersKt.put(jsonObjectBuilder3, "language", (String) this.deviceProperties.get(DeviceProperties.DEVICE_LANGUAGE));
        JsonElementBuildersKt.put(jsonObjectBuilder3, "firmware", (String) this.deviceProperties.get(DeviceProperties.FIRMWARE_VERSION));
        jsonObjectBuilder2.put("query_params", jsonObjectBuilder3.build());
        JsonObjectBuilder jsonObjectBuilder4 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder4, "x-gasc-enabled", "true");
        jsonObjectBuilder2.put("headers", jsonObjectBuilder4.build());
        JsonElementBuildersKt.put(jsonObjectBuilder2, "verb", "GET");
        JsonObjectBuilder jsonObjectBuilder5 = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder5, "encoding", "base64");
        JsonElementBuildersKt.put(jsonObjectBuilder5, "contents", "");
        jsonObjectBuilder2.put("body", jsonObjectBuilder5.build());
        jsonObjectBuilder.put("http-request-v1", jsonObjectBuilder2.build());
        return jsonObjectBuilder.build();
    }
}
