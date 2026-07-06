package com.amazon.livingroom.deviceproperties;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.livingroom.deviceproperties.dtid.DtidCache;
import java.util.Collection;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DeviceProperties {
    public static final Property<String> ADVERTISING_ID;
    public static final Property<Integer> AMP_ALTERNATIVE_BILLING_VERSION;
    public static final Property<String> APPLICATION_PACKAGE_NAME;
    public static final Property<Long> APPLICATION_VERSION_CODE;
    public static final Property<String> APPLICATION_VERSION_NAME;
    public static final Property<Long> AUDIO_RENDERER_TIME_LIMIT_MS;
    public static final Property<Boolean> DECODER_INACTIVE_WORKAROUND;
    public static final Property<String> DEVICE_ID;
    public static final Property<String> DEVICE_LANGUAGE;
    public static final Property<String> DEVICE_NAME;
    public static final Property<String> DEVICE_TERMINATOR_ID;
    public static final Property<String> DEVICE_TIME_ZONE;
    public static final Property<String> DEVICE_TYPE_ID;
    public static final Property<Integer> DIRECT_AUDIO_PLAYBACK_SUPPORT_FLAGS;
    public static final Property<Long> DISPLAY_MODE_CHANGE_HDCP_DROP_TIMEOUT_MS;
    public static final Property<Long> DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS;
    public static final Property<Long> DISPLAY_MODE_CHANGE_HDCP_RECOVERY_TIMEOUT_MS;
    public static final Property<Long> DISPLAY_MODE_CHANGE_TIMEOUT_MS;
    public static final Property<Boolean> DTID_REQUEST_FAILED;
    public static final Property<Integer> EMP_CHANNELS_VERSION;
    public static final Property<Integer> EMP_PRIME_ADD_ON_VERSION;
    public static final Property<Integer> EMP_PRIME_VERSION;
    public static final Property<Integer> EMP_TVOD_VERSION;
    public static final Property<Boolean> ENABLE_DEFERRED_SURFACE_PLAYBACK;
    public static final Property<Boolean> FALLBACK_TO_LEGACY_EAC3_PASSTHROUGH_API;
    public static final Property<Boolean> FINE_ADJUSTMENT_REAL_TIME_PLAYBACK;
    public static final Property<Long> FIRMWARE_BUILD_TIMESTAMP;
    public static final Property<Boolean> FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING;
    public static final Property<Boolean> FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING;
    public static final Property<Boolean> FRAME_RATE_MATCHING_ENABLED;
    public static final Property<Boolean> HAS_EXTERNAL_OUTPUT;
    public static final Property<Boolean> HDMI_AUDIO_PLUGGED_IN;
    public static final Property<Boolean> IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT;
    public static final Property<String> IGNITIONX_APP_STARTUP_MODE;
    public static final Property<String> IGNITIONX_BLAST_URL;
    public static final Property<String> IGNITIONX_BLUR_URI_PREFIX;
    public static final Property<Boolean> IGNITIONX_BYPASS_BLUR_SERVER;
    public static final Property<String> IGNITIONX_CLIENT_CONFIG;
    public static final Property<String> IGNITIONX_DEVICE_LABEL;
    public static final Property<String> IGNITIONX_DEVICE_PROXY_URL;
    public static final Property<Boolean> IGNITIONX_DISABLE_SSL_CERT;
    public static final Property<Boolean> IGNITIONX_DISABLE_STDOUT_LOGS;
    public static final Property<Boolean> IGNITIONX_ENABLE_WAMR_DEBUGGER;
    public static final Property<String> IGNITIONX_HTTP_PROXY_SERVER;
    public static final Property<String> IGNITIONX_LOCAL_NATIVE_MODULE_PATH;
    public static final Property<String> IGNITIONX_LOCAL_WASM_MODULE_PATH;
    public static final Property<String> IGNITIONX_LOCAL_WASM_PATH;
    public static final Property<String> IGNITIONX_LOG_EVENT_BUFFER_SIZE;
    public static final Property<String> IGNITIONX_LOG_LEVEL;
    public static final Property<String> IGNITIONX_NO_YIELD_JS_ENGINE;
    public static final Property<String> IGNITIONX_REACT_URI_PREFIX;
    public static final Property<Integer> IGNITIONX_STACK_SIZE;
    public static final Property<Boolean> IGNITIONX_USE_JEMALLOC_ALLOCATOR;
    public static final Property<Boolean> IGNITIONX_USE_LOCAL_JS;
    public static final Property<Boolean> IGNITIONX_USE_LOCAL_LUA;
    public static final Property<String> IGNITIONX_WASM_ENGINE;
    public static final Property<String> IGNITIONX_WEBSOCKET_PROXY_SERVER;
    public static final Property<String> INSTALL_SOURCE;
    public static final Property<Boolean> IS_ADVERTISING_OPT_OUT;
    public static final Property<Boolean> IS_ALEXA_PLUS_ENABLED;
    public static final Property<Boolean> IS_DOLBY_DIGITAL_AUDIO_PASSTHROUGH_ONLY;
    public static final Property<Boolean> IS_DOLBY_HDMI_PASSTHROUGH_AVAILABLE;
    public static final Property<Boolean> IS_HANDLE_MIDSTREAM_SURFACE_DESTROY_ENABLED;
    public static final Property<Boolean> IS_HIGH_CONTRAST_MODE_ENABLED;
    public static final Property<Boolean> IS_OPTICAL_OUTPUT_ENABLED;
    public static final Property<Boolean> IS_WATCH_NEXT_ENABLED;
    public static final Property<Boolean> IS_WIFI_CONNECTION;
    public static final Property<Boolean> KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT;
    public static final Property<Long> KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT_THRESHOLD_MS;
    public static final Property<Integer> MAX_MPB_INSTANCES;
    public static final Property<Integer> MAX_VIDEO_HEIGHT;
    public static final Property<Integer> MAX_VIDEO_WIDTH;
    public static final Property<Integer> MEDIA_FRAGMENT_CACHE_SIZE_BYTES;
    public static final Property<Boolean> MPB_LOG_EVERYTHING_AS_WARNING;
    public static final Property<Integer> NETWORK_CONNECTION_STRENGTH;
    public static final Property<Long> PEAR_REFRESH_INTERVAL_IN_MINUTES;
    public static final Property<Boolean> PEAR_WATCH_NEXT;
    public static final Property<Boolean> RECOMMENDATIONS_ENABLED;
    public static final Property<String> RECOMMENDATIONS_PARTNER_PACKAGE;
    public static final Property<String> RECOMMENDATIONS_PARTNER_SIGNATURE;
    public static final Property<Boolean> RECOMMENDATION_REQUEST_STRUCTURE_SHARING;
    public static final Property<Boolean> REPLACE_AUDIO_ZERO_PTS;
    public static final Property<Boolean> REPLACE_VIDEO_ZERO_PTS;
    public static final Property<Boolean> RESTORE_ORIGINAL_VIDEO_PTS;
    public static final Property<Boolean> SET_UI_SURFACE_VISIBILITY;
    public static final Property<Boolean> SHOULD_SEND_ACCESSIBILITY_FOCUS_EVENT;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_BROADCAST;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_CINEMATIC;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_BROADCAST;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_CINEMATIC;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_BROADCAST;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_CINEMATIC;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_BROADCAST;
    public static final Property<String> SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_CINEMATIC;
    public static final Property<Boolean> SONY_CALIBRATED_MODE_ENABLED;
    public static final Property<String> SPLASH_SCREEN_PATH;
    public static final Property<Boolean> SUPPORTS_ALEXA_FOCUS_EVENT;
    public static final Property<Boolean> SUPPORTS_AUDIO_CODEC_SWITCHING;
    public static final Property<Boolean> SUPPORTS_AUDIO_VOLUME;
    public static final Property<Boolean> SUPPORTS_AV1;
    public static final Property<Boolean> SUPPORTS_CALIBRATED_MODE;
    public static final Property<Boolean> SUPPORTS_DOLBY_VISION;
    public static final Property<Boolean> SUPPORTS_EAC3_PASSTHROUGH;
    public static final Property<Boolean> SUPPORTS_EAC3_PLAYBACK_RATE_ADJUSTMENT;
    public static final Property<Boolean> SUPPORTS_FILMMAKER_MODE;
    public static final Property<Boolean> SUPPORTS_FOREGROUND_UNFOCUSED;
    public static final Property<Boolean> SUPPORTS_HDCP;
    public static final Property<Boolean> SUPPORTS_HDR10;
    public static final Property<Boolean> SUPPORTS_HDR10_PLUS;
    public static final Property<Boolean> SUPPORTS_HEVC;
    public static final Property<Boolean> SUPPORTS_HEVC_MAIN10;
    public static final Property<Boolean> SUPPORTS_INTRA_CHUNK_SEEKING;
    public static final Property<Boolean> SUPPORTS_ON_DEVICE_EAC3_DECODE;
    public static final Property<Boolean> SUPPORTS_SURROUND_SOUND;
    public static final Property<Boolean> SUPPORTS_UHD;
    public static final Property<Boolean> SUPPORTS_VARIABLE_ASPECT_RATIO;
    public static final Property<Boolean> TUNNELED_VIDEO_PLAYBACK_ENABLED;
    public static final Property<Boolean> TUNNELED_VIDEO_PLAYBACK_OVER_BT_ENABLED;
    public static final Property<Long> TVIF_TRACK_MESSAGE_TIMEOUT_MS;
    public static final Property<Boolean> USE_MEDIA_CODEC_BACKED_MPB;
    public static final Property<Boolean> VERBOSE_AV_SYNC_LOGGING_ENABLED;
    public static final Property<Long> VIDEO_RENDERER_TIME_LIMIT_MS;
    public static final Property<Boolean> VOICE_SERVICE_ENABLED;
    public static final Property<String> MANUFACTURER = DevicePropertiesCompanion.registerProperty(String.class, "MANUFACTURER", new DeviceProperties$$ExternalSyntheticLambda0());
    public static final Property<String> CHIPSET = DevicePropertiesCompanion.registerProperty(String.class, "CHIPSET", new DeviceProperties$$ExternalSyntheticLambda40());
    public static final Property<String> MODEL_NAME = DevicePropertiesCompanion.registerProperty(String.class, "MODEL_NAME", new DeviceProperties$$ExternalSyntheticLambda51());
    public static final Property<String> OPERATING_SYSTEM_VERSION_RELEASE = DevicePropertiesCompanion.registerProperty(String.class, "OPERATING_SYSTEM_VERSION_RELEASE", new DeviceProperties$$ExternalSyntheticLambda62());
    public static final Property<String> OPERATING_SYSTEM_NAME = DevicePropertiesCompanion.registerProperty(String.class, "OPERATING_SYSTEM_NAME", new DeviceProperties$$ExternalSyntheticLambda73());
    public static final Property<String> FIRMWARE_VERSION = DevicePropertiesCompanion.registerProperty(String.class, "FIRMWARE_VERSION", new DeviceProperties$$ExternalSyntheticLambda84());

    /* JADX INFO: renamed from: com.amazon.livingroom.deviceproperties.DeviceProperties$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        static {
            Property<String> property = DeviceProperties.MANUFACTURER;
        }

        @Nullable
        public static Property<?> findProperty(@NonNull String str) {
            return DevicePropertiesCompanion.PROPERTIES_BY_NAME.get(str);
        }

        public static /* synthetic */ String lambda$static$0(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            defaultDeviceProperties.deviceBuildProperties.getManufacturer();
            return Build.MANUFACTURER;
        }

        public static /* synthetic */ String lambda$static$118(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$119(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ Integer lambda$static$121(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 2097152;
        }

        public static /* synthetic */ String lambda$static$125(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ Long lambda$static$128(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 1000L;
        }

        public static /* synthetic */ String lambda$static$3(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            defaultDeviceProperties.deviceBuildProperties.getOperatingSystemVersionRelease();
            return Build.VERSION.RELEASE;
        }

        public static /* synthetic */ String lambda$static$4(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "Android";
        }

        public static /* synthetic */ Long lambda$static$47(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 100L;
        }

        public static /* synthetic */ Long lambda$static$48(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 5000L;
        }

        public static /* synthetic */ Long lambda$static$49(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 15000L;
        }

        public static /* synthetic */ String lambda$static$5(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            defaultDeviceProperties.deviceBuildProperties.getFirmwareVersion();
            return Build.FINGERPRINT;
        }

        public static /* synthetic */ Integer lambda$static$50(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 5;
        }

        public static /* synthetic */ Long lambda$static$61(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return -9223372036854775807L;
        }

        public static /* synthetic */ Long lambda$static$62(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 10L;
        }

        public static /* synthetic */ Long lambda$static$72(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 20000L;
        }

        public static /* synthetic */ Integer lambda$static$73(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return 1;
        }

        public static /* synthetic */ String lambda$static$78(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$79(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$80(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$81(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$82(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$84(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$85(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$87(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$88(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$89(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static /* synthetic */ String lambda$static$90(DefaultDeviceProperties defaultDeviceProperties, DeviceProperties deviceProperties) {
            return "";
        }

        public static Collection<Property<?>> values() {
            return DevicePropertiesCompanion.PROPERTIES_BY_NAME.values();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Property<T> {
        public final PropertyGetter<T> getter;
        public final String name;
        public final Class<T> valueClass;

        public Property(Class<T> cls, String str, PropertyGetter<T> propertyGetter) {
            this.valueClass = cls;
            this.name = str;
            this.getter = propertyGetter;
        }

        @NonNull
        public T getFrom(@NonNull DefaultDeviceProperties defaultDeviceProperties, @NonNull DeviceProperties deviceProperties) {
            return this.getter.getFrom(defaultDeviceProperties, deviceProperties);
        }

        @NonNull
        public String getName() {
            return this.name;
        }

        @NonNull
        public Class<T> getValueClass() {
            return this.valueClass;
        }

        @NonNull
        public String toString() {
            return getName();
        }
    }

    static {
        DeviceProperties$$ExternalSyntheticLambda95 deviceProperties$$ExternalSyntheticLambda95 = new DeviceProperties$$ExternalSyntheticLambda95();
        Class cls = Long.TYPE;
        FIRMWARE_BUILD_TIMESTAMP = DevicePropertiesCompanion.registerProperty(cls, "FIRMWARE_BUILD_TIMESTAMP", deviceProperties$$ExternalSyntheticLambda95);
        DEVICE_TYPE_ID = DevicePropertiesCompanion.registerProperty(String.class, DtidCache.CACHED_DTID_KEY, new DeviceProperties$$ExternalSyntheticLambda106());
        DeviceProperties$$ExternalSyntheticLambda117 deviceProperties$$ExternalSyntheticLambda117 = new DeviceProperties$$ExternalSyntheticLambda117();
        Class cls2 = Boolean.TYPE;
        DTID_REQUEST_FAILED = DevicePropertiesCompanion.registerProperty(cls2, "DTID_REQUEST_FAILED", deviceProperties$$ExternalSyntheticLambda117);
        DEVICE_ID = DevicePropertiesCompanion.registerProperty(String.class, DeviceIdProvider.CACHED_DID_KEY, new DeviceProperties$$ExternalSyntheticLambda128());
        DEVICE_NAME = DevicePropertiesCompanion.registerProperty(String.class, "DEVICE_NAME", new DeviceProperties$$ExternalSyntheticLambda11());
        DEVICE_LANGUAGE = DevicePropertiesCompanion.registerProperty(String.class, "DEVICE_LANGUAGE", new DeviceProperties$$ExternalSyntheticLambda22());
        DEVICE_TIME_ZONE = DevicePropertiesCompanion.registerProperty(String.class, "DEVICE_TIME_ZONE", new DeviceProperties$$ExternalSyntheticLambda32());
        DEVICE_TERMINATOR_ID = DevicePropertiesCompanion.registerProperty(String.class, "DEVICE_TERMINATOR_ID", new DeviceProperties$$ExternalSyntheticLambda33());
        APPLICATION_VERSION_NAME = DevicePropertiesCompanion.registerProperty(String.class, "APPLICATION_VERSION_NAME", new DeviceProperties$$ExternalSyntheticLambda34());
        APPLICATION_VERSION_CODE = DevicePropertiesCompanion.registerProperty(cls, "APPLICATION_VERSION_CODE", new DeviceProperties$$ExternalSyntheticLambda35());
        APPLICATION_PACKAGE_NAME = DevicePropertiesCompanion.registerProperty(String.class, "APPLICATION_PACKAGE_NAME", new DeviceProperties$$ExternalSyntheticLambda36());
        ADVERTISING_ID = DevicePropertiesCompanion.registerProperty(String.class, "ADVERTISING_ID", new DeviceProperties$$ExternalSyntheticLambda37());
        IS_ADVERTISING_OPT_OUT = DevicePropertiesCompanion.registerProperty(cls2, "IS_ADVERTISING_OPT_OUT", new DeviceProperties$$ExternalSyntheticLambda38());
        PEAR_REFRESH_INTERVAL_IN_MINUTES = DevicePropertiesCompanion.registerProperty(cls, "PEAR_REFRESH_INTERVAL_IN_MINUTES", new DeviceProperties$$ExternalSyntheticLambda39());
        IS_WATCH_NEXT_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "IS_WATCH_NEXT_ENABLED", new DeviceProperties$$ExternalSyntheticLambda41());
        PEAR_WATCH_NEXT = DevicePropertiesCompanion.registerProperty(cls2, "PEAR_WATCH_NEXT", new DeviceProperties$$ExternalSyntheticLambda42());
        DeviceProperties$$ExternalSyntheticLambda43 deviceProperties$$ExternalSyntheticLambda43 = new DeviceProperties$$ExternalSyntheticLambda43();
        Class cls3 = Integer.TYPE;
        MAX_VIDEO_WIDTH = DevicePropertiesCompanion.registerProperty(cls3, "MAX_VIDEO_WIDTH", deviceProperties$$ExternalSyntheticLambda43);
        MAX_VIDEO_HEIGHT = DevicePropertiesCompanion.registerProperty(cls3, "MAX_VIDEO_HEIGHT", new DeviceProperties$$ExternalSyntheticLambda44());
        SUPPORTS_HDCP = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_HDCP", new DeviceProperties$$ExternalSyntheticLambda45());
        SUPPORTS_UHD = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_UHD", new DeviceProperties$$ExternalSyntheticLambda46());
        SUPPORTS_HEVC = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_HEVC", new DeviceProperties$$ExternalSyntheticLambda47());
        SUPPORTS_HEVC_MAIN10 = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_HEVC_MAIN10", new DeviceProperties$$ExternalSyntheticLambda48());
        SUPPORTS_HDR10 = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_HDR10", new DeviceProperties$$ExternalSyntheticLambda49());
        SUPPORTS_HDR10_PLUS = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_HDR10_PLUS", new DeviceProperties$$ExternalSyntheticLambda50());
        SUPPORTS_DOLBY_VISION = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_DOLBY_VISION", new DeviceProperties$$ExternalSyntheticLambda52());
        SUPPORTS_AV1 = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_AV1", new DeviceProperties$$ExternalSyntheticLambda53());
        SUPPORTS_INTRA_CHUNK_SEEKING = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_INTRA_CHUNK_SEEKING", new DeviceProperties$$ExternalSyntheticLambda54());
        HAS_EXTERNAL_OUTPUT = DevicePropertiesCompanion.registerProperty(cls2, "HAS_EXTERNAL_OUTPUT", new DeviceProperties$$ExternalSyntheticLambda55());
        SUPPORTS_SURROUND_SOUND = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_SURROUND_SOUND", new DeviceProperties$$ExternalSyntheticLambda56());
        SUPPORTS_ON_DEVICE_EAC3_DECODE = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_ON_DEVICE_EAC3_DECODE", new DeviceProperties$$ExternalSyntheticLambda57());
        SUPPORTS_EAC3_PASSTHROUGH = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_EAC3_PASSTHROUGH", new DeviceProperties$$ExternalSyntheticLambda58());
        IS_DOLBY_DIGITAL_AUDIO_PASSTHROUGH_ONLY = DevicePropertiesCompanion.registerProperty(cls2, "IS_DOLBY_DIGITAL_AUDIO_PASSTHROUGH_ONLY", new DeviceProperties$$ExternalSyntheticLambda59());
        SUPPORTS_EAC3_PLAYBACK_RATE_ADJUSTMENT = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_EAC3_PLAYBACK_RATE_ADJUSTMENT", new DeviceProperties$$ExternalSyntheticLambda60());
        FALLBACK_TO_LEGACY_EAC3_PASSTHROUGH_API = DevicePropertiesCompanion.registerProperty(cls2, "FALLBACK_TO_LEGACY_EAC3_PASSTHROUGH_API", new DeviceProperties$$ExternalSyntheticLambda61());
        SUPPORTS_AUDIO_CODEC_SWITCHING = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_AUDIO_CODEC_SWITCHING", new DeviceProperties$$ExternalSyntheticLambda63());
        SUPPORTS_VARIABLE_ASPECT_RATIO = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_VARIABLE_ASPECT_RATIO", new DeviceProperties$$ExternalSyntheticLambda64());
        IS_OPTICAL_OUTPUT_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "IS_OPTICAL_OUTPUT_ENABLED", new DeviceProperties$$ExternalSyntheticLambda65());
        IS_DOLBY_HDMI_PASSTHROUGH_AVAILABLE = DevicePropertiesCompanion.registerProperty(cls2, "IS_DOLBY_HDMI_PASSTHROUGH_AVAILABLE", new DeviceProperties$$ExternalSyntheticLambda66());
        IS_HANDLE_MIDSTREAM_SURFACE_DESTROY_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "IS_HANDLE_MIDSTREAM_SURFACE_DESTROY_ENABLED", new DeviceProperties$$ExternalSyntheticLambda67());
        FRAME_RATE_MATCHING_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "FRAME_RATE_MATCHING_ENABLED", new DeviceProperties$$ExternalSyntheticLambda68());
        HDMI_AUDIO_PLUGGED_IN = DevicePropertiesCompanion.registerProperty(cls2, "HDMI_AUDIO_PLUGGED_IN", new DeviceProperties$$ExternalSyntheticLambda69());
        DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS = DevicePropertiesCompanion.registerProperty(cls, "DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS", new DeviceProperties$$ExternalSyntheticLambda70());
        DISPLAY_MODE_CHANGE_HDCP_DROP_TIMEOUT_MS = DevicePropertiesCompanion.registerProperty(cls, "DISPLAY_MODE_CHANGE_HDCP_DROP_TIMEOUT_MS", new DeviceProperties$$ExternalSyntheticLambda71());
        DISPLAY_MODE_CHANGE_HDCP_RECOVERY_TIMEOUT_MS = DevicePropertiesCompanion.registerProperty(cls, "DISPLAY_MODE_CHANGE_HDCP_RECOVERY_TIMEOUT_MS", new DeviceProperties$$ExternalSyntheticLambda72());
        DIRECT_AUDIO_PLAYBACK_SUPPORT_FLAGS = DevicePropertiesCompanion.registerProperty(cls3, "DIRECT_AUDIO_PLAYBACK_SUPPORT_FLAGS", new DeviceProperties$$ExternalSyntheticLambda74());
        IS_WIFI_CONNECTION = DevicePropertiesCompanion.registerProperty(cls2, "IS_WIFI_CONNECTION", new DeviceProperties$$ExternalSyntheticLambda75());
        NETWORK_CONNECTION_STRENGTH = DevicePropertiesCompanion.registerProperty(cls3, "NETWORK_CONNECTION_STRENGTH", new DeviceProperties$$ExternalSyntheticLambda76());
        RECOMMENDATIONS_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "RECOMMENDATIONS_ENABLED", new DeviceProperties$$ExternalSyntheticLambda77());
        MPB_LOG_EVERYTHING_AS_WARNING = DevicePropertiesCompanion.registerProperty(cls2, "MPB_LOG_EVERYTHING_AS_WARNING", new DeviceProperties$$ExternalSyntheticLambda78());
        KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT = DevicePropertiesCompanion.registerProperty(cls2, "KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT", new DeviceProperties$$ExternalSyntheticLambda79());
        KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT_THRESHOLD_MS = DevicePropertiesCompanion.registerProperty(cls, "KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT_THRESHOLD_MS", new DeviceProperties$$ExternalSyntheticLambda80());
        SET_UI_SURFACE_VISIBILITY = DevicePropertiesCompanion.registerProperty(cls2, "SET_UI_SURFACE_VISIBILITY", new DeviceProperties$$ExternalSyntheticLambda81());
        SUPPORTS_FOREGROUND_UNFOCUSED = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_FOREGROUND_UNFOCUSED", new DeviceProperties$$ExternalSyntheticLambda82());
        TUNNELED_VIDEO_PLAYBACK_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "TUNNELED_VIDEO_PLAYBACK_ENABLED", new DeviceProperties$$ExternalSyntheticLambda83());
        TUNNELED_VIDEO_PLAYBACK_OVER_BT_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "TUNNELED_VIDEO_PLAYBACK_OVER_BT_ENABLED", new DeviceProperties$$ExternalSyntheticLambda85());
        AUDIO_RENDERER_TIME_LIMIT_MS = DevicePropertiesCompanion.registerProperty(cls, "AUDIO_RENDERER_TIME_LIMIT_MS", new DeviceProperties$$ExternalSyntheticLambda86());
        VIDEO_RENDERER_TIME_LIMIT_MS = DevicePropertiesCompanion.registerProperty(cls, "VIDEO_RENDERER_TIME_LIMIT_MS", new DeviceProperties$$ExternalSyntheticLambda87());
        MEDIA_FRAGMENT_CACHE_SIZE_BYTES = DevicePropertiesCompanion.registerProperty(cls3, "MEDIA_FRAGMENT_CACHE_SIZE_BYTES", new DeviceProperties$$ExternalSyntheticLambda88());
        FINE_ADJUSTMENT_REAL_TIME_PLAYBACK = DevicePropertiesCompanion.registerProperty(cls2, "FINE_ADJUSTMENT_REAL_TIME_PLAYBACK", new DeviceProperties$$ExternalSyntheticLambda89());
        RESTORE_ORIGINAL_VIDEO_PTS = DevicePropertiesCompanion.registerProperty(cls2, "RESTORE_ORIGINAL_VIDEO_PTS", new DeviceProperties$$ExternalSyntheticLambda90());
        FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING = DevicePropertiesCompanion.registerProperty(cls2, "FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING", new DeviceProperties$$ExternalSyntheticLambda91());
        FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING = DevicePropertiesCompanion.registerProperty(cls2, "FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING", new DeviceProperties$$ExternalSyntheticLambda92());
        REPLACE_AUDIO_ZERO_PTS = DevicePropertiesCompanion.registerProperty(cls2, "REPLACE_AUDIO_ZERO_PTS", new DeviceProperties$$ExternalSyntheticLambda93());
        REPLACE_VIDEO_ZERO_PTS = DevicePropertiesCompanion.registerProperty(cls2, "REPLACE_VIDEO_ZERO_PTS", new DeviceProperties$$ExternalSyntheticLambda94());
        USE_MEDIA_CODEC_BACKED_MPB = DevicePropertiesCompanion.registerProperty(cls2, "USE_MEDIA_CODEC_BACKED_MPB", new DeviceProperties$$ExternalSyntheticLambda96());
        VERBOSE_AV_SYNC_LOGGING_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "VERBOSE_AV_SYNC_LOGGING_ENABLED", new DeviceProperties$$ExternalSyntheticLambda97());
        DISPLAY_MODE_CHANGE_TIMEOUT_MS = DevicePropertiesCompanion.registerProperty(cls, "DISPLAY_MODE_CHANGE_TIMEOUT_MS", new DeviceProperties$$ExternalSyntheticLambda98());
        MAX_MPB_INSTANCES = DevicePropertiesCompanion.registerProperty(cls3, "MAX_MPB_INSTANCES", new DeviceProperties$$ExternalSyntheticLambda99());
        SUPPORTS_AUDIO_VOLUME = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_AUDIO_VOLUME", new DeviceProperties$$ExternalSyntheticLambda100());
        DECODER_INACTIVE_WORKAROUND = DevicePropertiesCompanion.registerProperty(cls2, "DECODER_INACTIVE_WORKAROUND", new DeviceProperties$$ExternalSyntheticLambda101());
        ENABLE_DEFERRED_SURFACE_PLAYBACK = DevicePropertiesCompanion.registerProperty(cls2, "ENABLE_DEFERRED_SURFACE_PLAYBACK", new DeviceProperties$$ExternalSyntheticLambda102());
        IGNITIONX_CLIENT_CONFIG = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_CLIENT_CONFIG", new DeviceProperties$$ExternalSyntheticLambda103());
        IGNITIONX_DEVICE_LABEL = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_DEVICE_LABEL", new DeviceProperties$$ExternalSyntheticLambda104());
        IGNITIONX_DEVICE_PROXY_URL = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_DEVICE_PROXY_URL", new DeviceProperties$$ExternalSyntheticLambda105());
        IGNITIONX_BLAST_URL = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_BLAST_URL", new DeviceProperties$$ExternalSyntheticLambda107());
        IGNITIONX_BLUR_URI_PREFIX = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_BLUR_URI_PREFIX", new DeviceProperties$$ExternalSyntheticLambda108());
        IGNITIONX_REACT_URI_PREFIX = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_REACT_URI_PREFIX", new DeviceProperties$$ExternalSyntheticLambda109());
        IGNITIONX_APP_STARTUP_MODE = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_APP_STARTUP_MODE", new DeviceProperties$$ExternalSyntheticLambda110());
        IGNITIONX_HTTP_PROXY_SERVER = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_HTTP_PROXY_SERVER", new DeviceProperties$$ExternalSyntheticLambda111());
        IGNITIONX_WEBSOCKET_PROXY_SERVER = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_WEBSOCKET_PROXY_SERVER", new DeviceProperties$$ExternalSyntheticLambda112());
        IGNITIONX_LOG_LEVEL = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_LOG_LEVEL", new DeviceProperties$$ExternalSyntheticLambda113());
        IGNITIONX_LOG_EVENT_BUFFER_SIZE = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_LOG_EVENT_BUFFER_SIZE", new DeviceProperties$$ExternalSyntheticLambda114());
        IGNITIONX_LOCAL_NATIVE_MODULE_PATH = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_LOCAL_NATIVE_MODULE_PATH", new DeviceProperties$$ExternalSyntheticLambda115());
        IGNITIONX_LOCAL_WASM_PATH = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_LOCAL_WASM_PATH", new DeviceProperties$$ExternalSyntheticLambda116());
        IGNITIONX_LOCAL_WASM_MODULE_PATH = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_LOCAL_WASM_MODULE_PATH", new DeviceProperties$$ExternalSyntheticLambda118());
        IGNITIONX_NO_YIELD_JS_ENGINE = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_NO_YIELD_JS_ENGINE", new DeviceProperties$$ExternalSyntheticLambda119());
        IGNITIONX_WASM_ENGINE = DevicePropertiesCompanion.registerProperty(String.class, "IGNITIONX_WASM_ENGINE", new DeviceProperties$$ExternalSyntheticLambda120());
        IGNITIONX_USE_LOCAL_LUA = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_USE_LOCAL_LUA", new DeviceProperties$$ExternalSyntheticLambda121());
        IGNITIONX_USE_LOCAL_JS = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_USE_LOCAL_JS", new DeviceProperties$$ExternalSyntheticLambda122());
        IGNITIONX_BYPASS_BLUR_SERVER = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_BYPASS_BLUR_SERVER", new DeviceProperties$$ExternalSyntheticLambda123());
        IGNITIONX_DISABLE_SSL_CERT = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_DISABLE_SSL_CERT", new DeviceProperties$$ExternalSyntheticLambda124());
        IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT", new DeviceProperties$$ExternalSyntheticLambda125());
        IGNITIONX_DISABLE_STDOUT_LOGS = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_DISABLE_STDOUT_LOGS", new DeviceProperties$$ExternalSyntheticLambda126());
        IGNITIONX_ENABLE_WAMR_DEBUGGER = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_ENABLE_WAMR_DEBUGGER", new DeviceProperties$$ExternalSyntheticLambda127());
        INSTALL_SOURCE = DevicePropertiesCompanion.registerProperty(String.class, "INSTALL_SOURCE", new DeviceProperties$$ExternalSyntheticLambda1());
        EMP_PRIME_VERSION = DevicePropertiesCompanion.registerProperty(cls3, "EMP_PRIME_VERSION", new DeviceProperties$$ExternalSyntheticLambda2());
        EMP_TVOD_VERSION = DevicePropertiesCompanion.registerProperty(cls3, "EMP_TVOD_VERSION", new DeviceProperties$$ExternalSyntheticLambda3());
        EMP_CHANNELS_VERSION = DevicePropertiesCompanion.registerProperty(cls3, "EMP_CHANNELS_VERSION", new DeviceProperties$$ExternalSyntheticLambda4());
        AMP_ALTERNATIVE_BILLING_VERSION = DevicePropertiesCompanion.registerProperty(cls3, "AMP_ALTERNATIVE_BILLING_VERSION", new DeviceProperties$$ExternalSyntheticLambda5());
        EMP_PRIME_ADD_ON_VERSION = DevicePropertiesCompanion.registerProperty(cls3, "EMP_PRIME_ADD_ON_VERSION", new DeviceProperties$$ExternalSyntheticLambda6());
        VOICE_SERVICE_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "VOICE_SERVICE_ENABLED", new DeviceProperties$$ExternalSyntheticLambda7());
        SUPPORTS_CALIBRATED_MODE = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_CALIBRATED_MODE", new DeviceProperties$$ExternalSyntheticLambda8());
        SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_CINEMATIC = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_CINEMATIC", new DeviceProperties$$ExternalSyntheticLambda9());
        SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_BROADCAST = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_LCD_HDR_BROADCAST", new DeviceProperties$$ExternalSyntheticLambda10());
        SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_CINEMATIC = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_CINEMATIC", new DeviceProperties$$ExternalSyntheticLambda12());
        SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_BROADCAST = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_LCD_SDR_BROADCAST", new DeviceProperties$$ExternalSyntheticLambda13());
        SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_CINEMATIC = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_CINEMATIC", new DeviceProperties$$ExternalSyntheticLambda14());
        SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_BROADCAST = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_OLED_HDR_BROADCAST", new DeviceProperties$$ExternalSyntheticLambda15());
        SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_CINEMATIC = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_CINEMATIC", new DeviceProperties$$ExternalSyntheticLambda16());
        SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_BROADCAST = DevicePropertiesCompanion.registerProperty(String.class, "SONY_CALIBRATED_MODE_CONFIG_OLED_SDR_BROADCAST", new DeviceProperties$$ExternalSyntheticLambda17());
        SONY_CALIBRATED_MODE_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "SONY_CALIBRATED_MODE_ENABLED", new DeviceProperties$$ExternalSyntheticLambda18());
        SUPPORTS_FILMMAKER_MODE = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_FILMMAKER_MODE", new DeviceProperties$$ExternalSyntheticLambda19());
        RECOMMENDATIONS_PARTNER_PACKAGE = DevicePropertiesCompanion.registerProperty(String.class, "RECOMMENDATIONS_PARTNER_PACKAGE", new DeviceProperties$$ExternalSyntheticLambda20());
        RECOMMENDATIONS_PARTNER_SIGNATURE = DevicePropertiesCompanion.registerProperty(String.class, "RECOMMENDATIONS_PARTNER_SIGNATURE", new DeviceProperties$$ExternalSyntheticLambda21());
        RECOMMENDATION_REQUEST_STRUCTURE_SHARING = DevicePropertiesCompanion.registerProperty(cls2, "RECOMMENDATION_REQUEST_STRUCTURE_SHARING", new DeviceProperties$$ExternalSyntheticLambda23());
        IGNITIONX_STACK_SIZE = DevicePropertiesCompanion.registerProperty(cls3, "IGNITIONX_STACK_SIZE", new DeviceProperties$$ExternalSyntheticLambda24());
        IGNITIONX_USE_JEMALLOC_ALLOCATOR = DevicePropertiesCompanion.registerProperty(cls2, "IGNITIONX_USE_JEMALLOC_ALLOCATOR", new DeviceProperties$$ExternalSyntheticLambda25());
        IS_HIGH_CONTRAST_MODE_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "IS_HIGH_CONTRAST_MODE_ENABLED", new DeviceProperties$$ExternalSyntheticLambda26());
        SHOULD_SEND_ACCESSIBILITY_FOCUS_EVENT = DevicePropertiesCompanion.registerProperty(cls2, "SHOULD_SEND_ACCESSIBILITY_FOCUS_EVENT", new DeviceProperties$$ExternalSyntheticLambda27());
        SPLASH_SCREEN_PATH = DevicePropertiesCompanion.registerProperty(String.class, "SPLASH_SCREEN_PATH", new DeviceProperties$$ExternalSyntheticLambda28());
        SUPPORTS_ALEXA_FOCUS_EVENT = DevicePropertiesCompanion.registerProperty(cls2, "SUPPORTS_ALEXA_FOCUS_EVENT", new DeviceProperties$$ExternalSyntheticLambda29());
        IS_ALEXA_PLUS_ENABLED = DevicePropertiesCompanion.registerProperty(cls2, "IS_ALEXA_PLUS_ENABLED", new DeviceProperties$$ExternalSyntheticLambda30());
        TVIF_TRACK_MESSAGE_TIMEOUT_MS = DevicePropertiesCompanion.registerProperty(cls, "TVIF_TRACK_MESSAGE_TIMEOUT_MS", new DeviceProperties$$ExternalSyntheticLambda31());
    }

    @NonNull
    <T> T get(@NonNull Property<T> property);
}
