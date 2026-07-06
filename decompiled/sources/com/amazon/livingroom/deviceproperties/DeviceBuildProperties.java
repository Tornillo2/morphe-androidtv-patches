package com.amazon.livingroom.deviceproperties;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.os.EnvironmentCompat;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class DeviceBuildProperties {
    public static final String ALTERNATIVE_CHIPSET_PROPERTY_NAME = "ro.board.platform";
    public static final String BUILD_TIMESTAMP_PROPERTY_NAME = "ro.vendor.build.date.utc";
    public static final String MANUFACTURER_PHILIPS = "Philips";
    public static final String PHILLIPS_MODEL_NAME_SYSTEM_PROPERTY_KEY = "persist.sys.set_type";
    public final SystemProperties systemProperties;

    @Inject
    public DeviceBuildProperties(@NonNull SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    @NonNull
    public String getChipset() {
        String str = Build.BOARD;
        return EnvironmentCompat.MEDIA_UNKNOWN.equals(str) ? this.systemProperties.get(ALTERNATIVE_CHIPSET_PROPERTY_NAME, EnvironmentCompat.MEDIA_UNKNOWN) : str;
    }

    public long getFirmwareBuildTimestamp() {
        return this.systemProperties.getLong(BUILD_TIMESTAMP_PROPERTY_NAME, 0L);
    }

    @NonNull
    public String getFirmwareVersion() {
        return Build.FINGERPRINT;
    }

    @NonNull
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    @NonNull
    public String getModelName() {
        getManufacturer();
        String str = "Philips".equalsIgnoreCase(Build.MANUFACTURER) ? this.systemProperties.get(PHILLIPS_MODEL_NAME_SYSTEM_PROPERTY_KEY, "") : "";
        return str.isEmpty() ? Build.MODEL : str;
    }

    @NonNull
    public String getOperatingSystemVersionRelease() {
        return Build.VERSION.RELEASE;
    }
}
