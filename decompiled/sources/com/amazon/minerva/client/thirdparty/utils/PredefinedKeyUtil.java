package com.amazon.minerva.client.thirdparty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class PredefinedKeyUtil {
    public static final String ANONYMOUS_CUSTOMER_ID_NAME = "AnonymousCustomerId";
    public static final String ANONYMOUS_DEVICE_ID_NAME = "AnonymousDeviceId";
    public static final String ANONYMOUS_ID_PREFIX = "minerva.";
    public static final String SHARED_PREF_FILENAME = "minerva-device-util";
    public static final String TAG = "PredefinedKeyUtil";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String UNSUPPORTED = "UNSUPPORTED";
    public String mAnonymousCustomerId;
    public String mAnonymousDeviceId;
    public Context mContext;
    public CustomDeviceUtil mCustomDeviceUtil;
    public final SharedPreferences sharedPreferences;

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined;

        static {
            int[] iArr = new int[Predefined.values().length];
            $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined = iArr;
            try {
                iArr[Predefined.SOFTWARE_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.SOFTWARE_VERSION_FINGERPRINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.OS_FILE_TAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.BUILD_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.PLATFORM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.MODEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.HARDWARE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_TYPE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_ID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.CUSTOMER_ID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.MARKETPLACE_ID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.COUNTRY_OF_RESIDENCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_LANGUAGE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.OTA_GROUP_NAME.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.TIME_ZONE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public PredefinedKeyUtil(Context context, CustomDeviceUtil customDeviceUtil) {
        if (context == null) {
            throw new NullPointerException(String.format("%s: context must not be null.", TAG));
        }
        this.mContext = context;
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILENAME, 0);
        this.mCustomDeviceUtil = customDeviceUtil;
    }

    public final String getAnonymousCustomerId() {
        if (!isEmptyOrNull(this.mAnonymousCustomerId)) {
            return this.mAnonymousCustomerId;
        }
        String string = this.sharedPreferences.getString(ANONYMOUS_CUSTOMER_ID_NAME, null);
        this.mAnonymousCustomerId = string;
        if (!isEmptyOrNull(string)) {
            return this.mAnonymousCustomerId;
        }
        Log.i(TAG, "Generating an anonymous customer ID.");
        this.mAnonymousCustomerId = ANONYMOUS_ID_PREFIX.concat(String.format(Locale.US, "%09d", Integer.valueOf(new SecureRandom().nextInt(((int) Math.pow(10.0d, 9.0d)) - 1))));
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(ANONYMOUS_CUSTOMER_ID_NAME, this.mAnonymousCustomerId);
        editorEdit.apply();
        return this.mAnonymousCustomerId;
    }

    public final synchronized String getAnonymousDeviceId() {
        if (!isEmptyOrNull(this.mAnonymousDeviceId)) {
            return this.mAnonymousDeviceId;
        }
        String string = this.sharedPreferences.getString(ANONYMOUS_DEVICE_ID_NAME, null);
        this.mAnonymousDeviceId = string;
        if (!isEmptyOrNull(string)) {
            return this.mAnonymousDeviceId;
        }
        Log.i(TAG, "Generating a random UUID for use as anonymous device ID.");
        this.mAnonymousDeviceId = ANONYMOUS_ID_PREFIX + UUID.randomUUID().toString();
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(ANONYMOUS_DEVICE_ID_NAME, this.mAnonymousDeviceId);
        editorEdit.apply();
        return this.mAnonymousDeviceId;
    }

    public String getBuildType() {
        return Build.TYPE;
    }

    public String getCountryOfResidence() {
        String countryOfResidence = this.mCustomDeviceUtil.getCustomerInfoProvider() != null ? this.mCustomDeviceUtil.getCustomerInfoProvider().getCountryOfResidence() : Locale.getDefault().getCountry();
        return !isEmptyOrNull(countryOfResidence) ? countryOfResidence : "UNKNOWN";
    }

    public String getCustomerId(String str) {
        return new SchemaId(str).versionedAttributes.getBooleanValue(AttributeEnumV2.ALLOW_CUSTOMER_INFO).booleanValue() ? getNonAnonymousCustomerId() : getAnonymousCustomerId();
    }

    public String getDeviceId(String str) {
        return new SchemaId(str).versionedAttributes.getBooleanValue(AttributeEnumV2.ALLOW_DSN_INFO).booleanValue() ? getNonAnonymousDeviceId() : getAnonymousDeviceId();
    }

    public String getDeviceLanguage() {
        String language = Locale.getDefault().getLanguage();
        return isEmptyOrNull(language) ? "UNKNOWN" : language;
    }

    public String getDeviceType() {
        String deviceType = this.mCustomDeviceUtil.getDeviceType();
        return deviceType == null ? "UNKNOWN" : deviceType;
    }

    public String getHardware() {
        return Build.HARDWARE;
    }

    @Deprecated
    public String getMarketPlaceId() {
        String marketplaceId = this.mCustomDeviceUtil.getMarketplaceId();
        return marketplaceId == null ? "UNKNOWN" : marketplaceId;
    }

    public String getMarketplaceId() {
        String marketplaceId = this.mCustomDeviceUtil.getMarketplaceId();
        if (this.mCustomDeviceUtil.getCustomerInfoProvider() != null) {
            marketplaceId = this.mCustomDeviceUtil.getCustomerInfoProvider().getMarketplaceId();
        }
        return isEmptyOrNull(marketplaceId) ? "UNKNOWN" : marketplaceId;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public final String getNonAnonymousCustomerId() {
        try {
            if (this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider() != null && this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider().getNonAnonymousCustomerId() != null) {
                String nonAnonymousCustomerId = this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider().getNonAnonymousCustomerId();
                if (!nonAnonymousCustomerId.isEmpty()) {
                    return nonAnonymousCustomerId;
                }
                Log.e(TAG, "nonAnonymousCustomerId passed by customer is empty, set UNKNOWN as value for non_anonymous customerId");
                return "UNKNOWN";
            }
            Log.e(TAG, "nonAnonymousCustomerId is null, customer might miss to provide API: withNonAnonymousCustomerIdProvider");
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Unable to retrieve non_anonymous customerId passed by customer from API: withNonAnonymousCustomerIdProvider due to exception: " + e);
            return null;
        }
    }

    public final String getNonAnonymousDeviceId() {
        try {
            String nonAnonymousDeviceId = this.mCustomDeviceUtil.getNonAnonymousDeviceId();
            if (!nonAnonymousDeviceId.isEmpty()) {
                return nonAnonymousDeviceId;
            }
            Log.i(TAG, "Non_anonymous deviceId passed by customer is empty, replace to UNKNOWN as it's value");
            return "UNKNOWN";
        } catch (Exception e) {
            Log.e(TAG, "Unable to get non_anonymous deviceId passed by customer from API: withNonAnonymousDeviceId due to exception: " + e);
            return null;
        }
    }

    public String getOsFileTag() {
        return UNSUPPORTED;
    }

    public String getOtaGroupName() {
        return UNSUPPORTED;
    }

    public String getPlatform() {
        return Build.DEVICE;
    }

    public String getPredefinedValue(Predefined predefined, String str) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[predefined.ordinal()]) {
            case 1:
                getSoftwareVersion();
                return Build.VERSION.INCREMENTAL;
            case 2:
                getSoftwareVersionFingerprint();
                return Build.FINGERPRINT;
            case 3:
                return UNSUPPORTED;
            case 4:
                getBuildType();
                return Build.TYPE;
            case 5:
                getPlatform();
                return Build.DEVICE;
            case 6:
                getModel();
                return Build.MODEL;
            case 7:
                getHardware();
                return Build.HARDWARE;
            case 8:
                return getDeviceType();
            case 9:
                return getDeviceId(str);
            case 10:
                return getCustomerId(str);
            case 11:
                return getMarketplaceId();
            case 12:
                return getCountryOfResidence();
            case 13:
                return getDeviceLanguage();
            case 14:
                return UNSUPPORTED;
            case 15:
                return getTimeZone();
            default:
                return "UNKNOWN";
        }
    }

    public String getSoftwareVersion() {
        return Build.VERSION.INCREMENTAL;
    }

    public String getSoftwareVersionFingerprint() {
        return Build.FINGERPRINT;
    }

    public String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    public final boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }
}
