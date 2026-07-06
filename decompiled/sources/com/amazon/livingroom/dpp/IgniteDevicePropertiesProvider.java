package com.amazon.livingroom.dpp;

import androidx.annotation.VisibleForTesting;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import com.amazon.livingroom.mediapipelinebackend.DisplayInformation;
import com.amazon.livingroom.mediapipelinebackend.HdcpChecker;
import com.amazon.livingroom.mediapipelinebackend.ResultHolder;
import com.amazon.livingroom.mediapipelinebackend.WidevineCapabilitiesProvider;
import com.amazon.reporting.Log;
import com.google.android.datatransport.cct.CctTransportBackend;
import j$.util.DesugarCollections;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class IgniteDevicePropertiesProvider {
    public static final int DPP_SUCCESS = 0;
    public static final int DPP_UNKNOWN_PROPERTY = 1;
    public static final int DPP_WRONG_TYPE = 2;
    public static final int IGNITIONX_HDR_FLAG_DOLBY_VISION = 2;
    public static final int IGNITIONX_HDR_FLAG_HDR10 = 1;
    public static final String TAG = "IgniteDevicePropertiesProvider";
    public final Map<String, PropertyGetter<?>> propertyGetterMap;
    public final Set<String> unknownProperties;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PropertyGetter<T> {
        T get();
    }

    public static /* synthetic */ Object $r8$lambda$VKuow1qHm5mzheL4GQZeKweZJS8() {
        return "0";
    }

    /* JADX INFO: renamed from: $r8$lambda$cNzwx5nQJ3x4fS87bvBl-nr7Bvs, reason: not valid java name */
    public static /* synthetic */ Object m269$r8$lambda$cNzwx5nQJ3x4fS87bvBlnr7Bvs() {
        return 0;
    }

    @Inject
    public IgniteDevicePropertiesProvider(DeviceProperties deviceProperties, DisplayInformation displayInformation, HdcpChecker hdcpChecker, WidevineCapabilitiesProvider widevineCapabilitiesProvider) {
        this(buildPropertyMap(deviceProperties, displayInformation, hdcpChecker, widevineCapabilitiesProvider));
    }

    public static Map<String, PropertyGetter<?>> buildPropertyMap(final DeviceProperties deviceProperties, final DisplayInformation displayInformation, final HdcpChecker hdcpChecker, final WidevineCapabilitiesProvider widevineCapabilitiesProvider) {
        HashMap map = new HashMap();
        map.put(CctTransportBackend.KEY_MANUFACTURER, new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.MANUFACTURER);
            }
        });
        map.put("firmwareVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda11
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.FIRMWARE_VERSION);
            }
        });
        map.put("osName", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda22
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.OPERATING_SYSTEM_NAME);
            }
        });
        map.put("osVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda33
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.OPERATING_SYSTEM_VERSION_RELEASE);
            }
        });
        map.put("chipset", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda43
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.CHIPSET);
            }
        });
        map.put("modelName", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda51
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.MODEL_NAME);
            }
        });
        map.put("platformPackageVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda52
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.APPLICATION_VERSION_NAME);
            }
        });
        map.put("supportsUHD", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda53
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_UHD);
            }
        });
        map.put("supportsHEVC", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda54
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_HEVC);
            }
        });
        map.put("supportsHEVC10Bits", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda55
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_HEVC_MAIN10);
            }
        });
        map.put("supportsAV1", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda1
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_AV1);
            }
        });
        map.put("supportsHDR", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda2
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return Boolean.valueOf(IgniteDevicePropertiesProvider.collectHdrFlags(deviceProperties) != 0);
            }
        });
        map.put("hdrFlags", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda3
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return Integer.valueOf(IgniteDevicePropertiesProvider.collectHdrFlags(deviceProperties));
            }
        });
        map.put("hasExternalOutput", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda4
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.HAS_EXTERNAL_OUTPUT);
            }
        });
        map.put("hdmiAudioPluggedIn", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda5
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.HDMI_AUDIO_PLUGGED_IN);
            }
        });
        map.put("supportsHDCP", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda6
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_HDCP);
            }
        });
        map.put("hdcpFullVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda7
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return hdcpChecker.getCurrentHdcpVersion(false).getNormalisedFullVersion();
            }
        });
        map.put("supportsCVBR", new IgniteDevicePropertiesProvider$$ExternalSyntheticLambda8());
        map.put("supportsIntraChunkSeeking", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda9
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_INTRA_CHUNK_SEEKING);
            }
        });
        map.put("supportsAudioCodecSwitching", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda10
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_AUDIO_CODEC_SWITCHING);
            }
        });
        map.put("updateFrequency", new IgniteDevicePropertiesProvider$$ExternalSyntheticLambda12());
        Objects.requireNonNull(widevineCapabilitiesProvider);
        map.put("maxSupportedMediaKeySessions", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda13
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return Integer.valueOf(widevineCapabilitiesProvider.getMaxNumberOfSessions());
            }
        });
        Objects.requireNonNull(displayInformation);
        map.put("displayWidth", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda14
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return Integer.valueOf(displayInformation.getUiDisplayWidth());
            }
        });
        map.put("displayHeight", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda15
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return Integer.valueOf(displayInformation.getUiDisplayHeight());
            }
        });
        map.put("panelWidth", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda16
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.MAX_VIDEO_WIDTH);
            }
        });
        map.put("panelHeight", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda17
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.MAX_VIDEO_HEIGHT);
            }
        });
        map.put("deviceLanguage", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda18
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.DEVICE_LANGUAGE);
            }
        });
        map.put("dolbyDigitalAudioPassthroughOnly", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda19
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.IS_DOLBY_DIGITAL_AUDIO_PASSTHROUGH_ONLY);
            }
        });
        map.put("supportsSurroundSound", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda20
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_SURROUND_SOUND);
            }
        });
        map.put("supportsEac3PlaybackRateAdjustment", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda21
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_EAC3_PLAYBACK_RATE_ADJUSTMENT);
            }
        });
        map.put("WiFiNetworkConnection", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda23
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.IS_WIFI_CONNECTION);
            }
        });
        map.put("networkConnectionStrength", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda24
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.NETWORK_CONNECTION_STRENGTH);
            }
        });
        map.put("deviceId", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda25
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.DEVICE_ID);
            }
        });
        map.put("fragmentCacheSize", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda26
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.MEDIA_FRAGMENT_CACHE_SIZE_BYTES);
            }
        });
        map.put("deviceTypeId", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda27
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.DEVICE_TYPE_ID);
            }
        });
        map.put("advertisingId", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda28
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.ADVERTISING_ID);
            }
        });
        map.put("isAdvertisingOptOut", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda29
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.IS_ADVERTISING_OPT_OUT);
            }
        });
        map.put("deviceTerminatorLabel", new IgniteDevicePropertiesProvider$$ExternalSyntheticLambda30());
        map.put("deviceTerminatorId", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda31
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.DEVICE_TERMINATOR_ID);
            }
        });
        map.put("installSource", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda32
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.INSTALL_SOURCE);
            }
        });
        map.put("applicationVersionCode", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda34
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return ((Long) deviceProperties.get(DeviceProperties.APPLICATION_VERSION_CODE)).toString();
            }
        });
        map.put(Names.APPLICATION_PACKAGE_NAME, new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda35
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.APPLICATION_PACKAGE_NAME);
            }
        });
        map.put("supportsFilmmakerMode", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda36
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_FILMMAKER_MODE);
            }
        });
        map.put("supportsCalibratedMode", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda37
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_CALIBRATED_MODE);
            }
        });
        map.put("supportsDynamicPlayerResizing", new IgniteDevicePropertiesProvider$$ExternalSyntheticLambda8());
        map.put("timeZone", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda38
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.DEVICE_TIME_ZONE);
            }
        });
        map.put("empPrimeVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda39
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.EMP_PRIME_VERSION);
            }
        });
        map.put("empTvodVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda40
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.EMP_TVOD_VERSION);
            }
        });
        map.put("empChannelsVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda41
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.EMP_CHANNELS_VERSION);
            }
        });
        map.put("empPrimeAddOnVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda42
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.EMP_PRIME_ADD_ON_VERSION);
            }
        });
        map.put("supportsVariableAspectRatio", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda44
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_VARIABLE_ASPECT_RATIO);
            }
        });
        map.put("ampAlternativeBillingVersion", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda45
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.AMP_ALTERNATIVE_BILLING_VERSION);
            }
        });
        map.put("isHighContrastModeEnabled", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda46
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.IS_HIGH_CONTRAST_MODE_ENABLED);
            }
        });
        map.put("shouldSendAccessibilityFocusEvent", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda47
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SHOULD_SEND_ACCESSIBILITY_FOCUS_EVENT);
            }
        });
        map.put("splashScreenPath", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda48
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SPLASH_SCREEN_PATH);
            }
        });
        map.put("supportsAlexaFocusEvent", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda49
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.SUPPORTS_ALEXA_FOCUS_EVENT);
            }
        });
        map.put("isAlexaPlusEnabled", new PropertyGetter() { // from class: com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider$$ExternalSyntheticLambda50
            @Override // com.amazon.livingroom.dpp.IgniteDevicePropertiesProvider.PropertyGetter
            public final Object get() {
                return deviceProperties.get(DeviceProperties.IS_ALEXA_PLUS_ENABLED);
            }
        });
        return DesugarCollections.unmodifiableMap(map);
    }

    public static int collectHdrFlags(DeviceProperties deviceProperties) {
        boolean zBooleanValue = ((Boolean) deviceProperties.get(DeviceProperties.SUPPORTS_HDR10)).booleanValue();
        return ((Boolean) deviceProperties.get(DeviceProperties.SUPPORTS_DOLBY_VISION)).booleanValue() ? (zBooleanValue ? 1 : 0) | 2 : zBooleanValue ? 1 : 0;
    }

    public static /* synthetic */ Object lambda$buildPropertyMap$20() {
        return 0;
    }

    public static /* synthetic */ Object lambda$buildPropertyMap$34() {
        return "0";
    }

    @CalledFromNative
    public ResultHolder<Boolean> getBoolProperty(String str) {
        return getPropertyInternal(str, Boolean.class);
    }

    @CalledFromNative
    public ResultHolder<Integer> getInt32Property(String str) {
        return getPropertyInternal(str, Integer.class);
    }

    @CalledFromNative
    public ResultHolder<Long> getInt64Property(String str) {
        return getPropertyInternal(str, Long.class);
    }

    public final <T> ResultHolder<T> getPropertyInternal(String str, Class<T> cls) {
        PropertyGetter<?> propertyGetter = this.propertyGetterMap.get(str);
        if (propertyGetter != null) {
            try {
                return ResultHolder.fromResult(cls.cast(propertyGetter.get()));
            } catch (ClassCastException e) {
                Log.e(TAG, String.format("Cannot cast property '%s'", str), e);
                return ResultHolder.fromErrorCode(2);
            }
        }
        if (this.unknownProperties.add(str)) {
            Log.w(TAG, "Unknown DPP property: " + str);
        }
        return ResultHolder.fromErrorCode(1);
    }

    @CalledFromNative
    public ResultHolder<String> getStringProperty(String str) {
        return getPropertyInternal(str, String.class);
    }

    @VisibleForTesting
    public IgniteDevicePropertiesProvider(Map<String, PropertyGetter<?>> map) {
        this.unknownProperties = Collections.newSetFromMap(new ConcurrentHashMap());
        this.propertyGetterMap = map;
    }
}
