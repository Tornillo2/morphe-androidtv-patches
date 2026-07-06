package com.amazon.minerva.client.thirdparty.api;

import com.amazonaws.mobileconnectors.remoteconfiguration.internal.AttributesImpl;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum Predefined {
    SOFTWARE_VERSION("_softwareVersion"),
    SOFTWARE_VERSION_FINGERPRINT("_softwareVersionFingerprint"),
    OS_FILE_TAG("_osFileTag"),
    BUILD_TYPE("_buildType"),
    PLATFORM(AttributesImpl.KEY_PLATFORM),
    MODEL("_model"),
    HARDWARE("_hardware"),
    DEVICE_TYPE("_deviceType"),
    DEVICE_ID("_deviceId"),
    CUSTOMER_ID("_customerId"),
    MARKETPLACE_ID("_marketPlaceId"),
    COUNTRY_OF_RESIDENCE("_countryOfResidence"),
    DEVICE_LANGUAGE("_deviceLanguage"),
    OTA_GROUP_NAME("_otaGroupName"),
    TIME_ZONE("_timeZone");

    public final String key;

    Predefined(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }
}
