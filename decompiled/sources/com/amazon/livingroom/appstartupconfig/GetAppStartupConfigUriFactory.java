package com.amazon.livingroom.appstartupconfig;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.localisation.DeviceLocaleProvider;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class GetAppStartupConfigUriFactory {
    public final DeviceLocaleProvider deviceLocaleProvider;
    public final DeviceProperties deviceProperties;
    public final List<String> languageTags;
    public final TerminatorIdProvider terminatorIdProvider;

    @Inject
    public GetAppStartupConfigUriFactory(@NonNull PlatformDeviceProperties platformDeviceProperties, @NonNull TerminatorIdProvider terminatorIdProvider, @NonNull DeviceLocaleProvider deviceLocaleProvider, @NonNull @Named(Names.SUPPORTED_LOCALE_LANGUAGE_TAGS) List<String> list) {
        this.deviceProperties = platformDeviceProperties;
        this.terminatorIdProvider = terminatorIdProvider;
        this.deviceLocaleProvider = deviceLocaleProvider;
        this.languageTags = list;
    }

    @NonNull
    public Uri createGetAppStartupConfigUri() {
        return new Uri.Builder().scheme("https").authority(this.terminatorIdProvider.getGenericTerminatorId().concat(".api.amazonvideo.com")).path("/acm/GetConfiguration/android-tv-configuration").appendQueryParameter("deviceId", (String) this.deviceProperties.get(DeviceProperties.DEVICE_ID)).appendQueryParameter("deviceTypeID", (String) this.deviceProperties.get(DeviceProperties.DEVICE_TYPE_ID)).appendQueryParameter("firmware", (String) this.deviceProperties.get(DeviceProperties.FIRMWARE_VERSION)).appendQueryParameter("format", "json").appendQueryParameter("osLocale", this.deviceLocaleProvider.getCurrentLocaleName()).appendQueryParameter("supportedLocales", TextUtils.join(",", this.languageTags)).build();
    }
}
