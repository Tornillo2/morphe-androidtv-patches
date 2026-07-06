package com.amazon.livingroom.deviceproperties;

import com.amazon.livingroom.di.ApplicationScope;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class OperatingSystemProperties {
    @Inject
    public OperatingSystemProperties() {
    }

    public String getDeviceLanguage() {
        return Locale.getDefault().toLanguageTag();
    }

    public String getDeviceTimeZone() {
        return TimeZone.getDefault().getID();
    }
}
