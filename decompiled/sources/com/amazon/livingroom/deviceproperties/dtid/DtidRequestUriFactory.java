package com.amazon.livingroom.deviceproperties.dtid;

import android.net.Uri;
import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.google.android.datatransport.cct.CctTransportBackend;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class DtidRequestUriFactory {

    @NotNull
    public final String acmConfigName;

    @NotNull
    public final String defaultDtid;

    @NotNull
    public final TerminatorIdProvider terminatorIdProvider;

    @Inject
    public DtidRequestUriFactory(@Named(Names.IGNITION_APPLICATION_DEFAULT_DTID) @NotNull String defaultDtid, @Named(Names.DTID_ACM_CONFIG_NAME) @NotNull String acmConfigName, @NotNull TerminatorIdProvider terminatorIdProvider) {
        Intrinsics.checkNotNullParameter(defaultDtid, "defaultDtid");
        Intrinsics.checkNotNullParameter(acmConfigName, "acmConfigName");
        Intrinsics.checkNotNullParameter(terminatorIdProvider, "terminatorIdProvider");
        this.defaultDtid = defaultDtid;
        this.acmConfigName = acmConfigName;
        this.terminatorIdProvider = terminatorIdProvider;
    }

    @NotNull
    public final Uri getUri(@NotNull DeviceProperties deviceProperties) {
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Uri.Builder builderPath = new Uri.Builder().scheme("https").authority(this.terminatorIdProvider.getGenericTerminatorId().concat(".api.amazonvideo.com")).path("/acm/GetConfiguration/" + this.acmConfigName);
        builderPath.appendQueryParameter("deviceId", (String) deviceProperties.get(DeviceProperties.DEVICE_ID));
        builderPath.appendQueryParameter("deviceTypeId", this.defaultDtid);
        Object obj = deviceProperties.get(DeviceProperties.MANUFACTURER);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        Locale locale = Locale.ROOT;
        String lowerCase = ((String) obj).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        builderPath.appendQueryParameter(CctTransportBackend.KEY_MANUFACTURER, lowerCase);
        Object obj2 = deviceProperties.get(DeviceProperties.CHIPSET);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        String lowerCase2 = ((String) obj2).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        builderPath.appendQueryParameter("chipset_id", lowerCase2);
        Object obj3 = deviceProperties.get(DeviceProperties.MODEL_NAME);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        String lowerCase3 = ((String) obj3).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
        builderPath.appendQueryParameter("model_name", lowerCase3);
        builderPath.appendQueryParameter("firmware", (String) deviceProperties.get(DeviceProperties.FIRMWARE_VERSION));
        builderPath.appendQueryParameter("format", "json");
        Uri uriBuild = builderPath.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }
}
