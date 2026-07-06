package com.amazon.livingroom.deviceproperties;

import android.annotation.SuppressLint;
import com.amazon.ignitionshared.network.TerminatorIdProvider;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.dtid.DtidProvider;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class DefaultDeviceProperties extends AbstractDeviceProperties {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long DEFAULT_AUDIO_RENDERER_TIME_LIMIT_MS = -9223372036854775807L;
    public static final boolean DEFAULT_DECODER_INACTIVE_WORKAROUND = false;

    @NotNull
    public static final String DEFAULT_DEVICE_NAME = "android-tv";

    @SuppressLint({"InlinedApi"})
    public static final int DEFAULT_DIRECT_AUDIO_PLAYBACK_SUPPORT_FLAGS = 5;
    public static final long DEFAULT_DISPLAY_MODE_CHANGE_HDCP_DROP_TIMEOUT_MS = 5000;
    public static final long DEFAULT_DISPLAY_MODE_CHANGE_HDCP_POLL_INTERVAL_MS = 100;
    public static final long DEFAULT_DISPLAY_MODE_CHANGE_HDCP_RECOVERY_TIMEOUT_MS = 15000;
    public static final long DEFAULT_DISPLAY_MODE_CHANGE_TIMEOUT_MS = 20000;
    public static final boolean DEFAULT_ENABLE_DEFERRED_SURFACE_PLAYBACK = false;
    public static final boolean DEFAULT_FALLBACK_TO_LEGACY_EAC3_PASSTHROUGH_API = false;
    public static final boolean DEFAULT_FINE_ADJUSTMENT_REAL_TIME_PLAYBACK = true;
    public static final boolean DEFAULT_FORCE_DISABLE_MEDIA_CODEC_ASYNC_QUEUEING = false;
    public static final boolean DEFAULT_FORCE_ENABLE_MEDIA_CODEC_ASYNC_QUEUEING = false;
    public static final boolean DEFAULT_IGNITIONX_USE_JEMALLOC_ALLOCATOR = false;
    public static final boolean DEFAULT_IS_ALEXA_PLUS_ENABLED = false;
    public static final boolean DEFAULT_IS_DOLBY_HDMI_PASSTHROUGH_AVAILABLE = true;
    public static final boolean DEFAULT_IS_HANDLE_MIDSTREAM_SURFACE_DESTROY_ENABLED = false;
    public static final boolean DEFAULT_IS_HIGH_CONTRAST_MODE_ENABLED = false;
    public static final boolean DEFAULT_IS_OPTICAL_OUTPUT_ENABLED = false;
    public static final boolean DEFAULT_IS_WATCH_NEXT_ENABLED = true;
    public static final boolean DEFAULT_KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT = true;
    public static final long DEFAULT_KILL_APP_ON_EXCESSIVE_BACKGROUND_TRANSITION_WAIT_THRESHOLD_MS = 4900;
    public static final int DEFAULT_MAX_MPB_INSTANCES = 1;
    public static final int DEFAULT_MEDIA_FRAGMENT_CACHE_SIZE_BYTES = 125829120;
    public static final boolean DEFAULT_MPB_LOG_EVERYTHING_AS_WARNING = false;

    @NotNull
    public static final String DEFAULT_OS_NAME = "Android";

    @NotNull
    public static final String DEFAULT_RECOMMENDATIONS_PARTNER_PACKAGE = "";

    @NotNull
    public static final String DEFAULT_RECOMMENDATIONS_PARTNER_SIGNATURE = "";
    public static final boolean DEFAULT_RECOMMENDATION_REQUEST_STRUCTURE_SHARING = false;
    public static final boolean DEFAULT_REPLACE_AUDIO_ZERO_PTS = false;
    public static final boolean DEFAULT_REPLACE_VIDEO_ZERO_PTS = false;
    public static final boolean DEFAULT_RESTORE_ORIGINAL_VIDEO_PTS = false;
    public static final boolean DEFAULT_SET_UI_SURFACE_VISIBILITY = true;
    public static final boolean DEFAULT_SHOULD_SEND_ACCESSIBILITY_FOCUS_EVENT = false;

    @NotNull
    public static final String DEFAULT_SPLASH_SCREEN_PATH = "";
    public static final boolean DEFAULT_SUPPORTS_ALEXA_FOCUS_EVENT = false;
    public static final boolean DEFAULT_SUPPORTS_AUDIO_CODEC_SWITCHING = true;
    public static final boolean DEFAULT_SUPPORTS_AUDIO_VOLUME = true;
    public static final boolean DEFAULT_SUPPORTS_FILMMAKER_MODE = false;
    public static final boolean DEFAULT_SUPPORTS_INTRA_CHUNK_SEEKING = false;
    public static final boolean DEFAULT_SUPPORTS_VARIABLE_ASPECT_RATIO = true;
    public static final boolean DEFAULT_TUNNELED_VIDEO_PLAYBACK_ENABLED = false;
    public static final boolean DEFAULT_TUNNELED_VIDEO_PLAYBACK_OVER_BT_ENABLED = false;
    public static final long DEFAULT_TVIF_TRACK_MESSAGE_TIMEOUT_MS = 1000;
    public static final boolean DEFAULT_VERBOSE_AV_SYNC_LOGGING_ENABLED = false;
    public static final long DEFAULT_VIDEO_RENDERER_TIME_LIMIT_MS = 10;
    public static final boolean DEFAULT_VOICE_SERVICE_ENABLED = true;
    public static final boolean SUPPORTS_FOREGROUND_UNFOCUSED = false;

    @JvmField
    @NotNull
    public final AdvertisingProperties advertisingProperties;

    @JvmField
    @NotNull
    public final ApplicationPackagePropertiesProvider applicationPackagePropertiesProvider;

    @JvmField
    @NotNull
    public final ApplicationSourcePropertiesProvider applicationSourcePropertiesProvider;

    @JvmField
    @NotNull
    public final AudioProperties audioProperties;

    @JvmField
    @NotNull
    public final BillingProperties billingProperties;

    @JvmField
    @NotNull
    public final DeviceBuildProperties deviceBuildProperties;

    @JvmField
    @NotNull
    public final DeviceIdProvider deviceIdProvider;

    @JvmField
    @NotNull
    public final DisplayModeProperties displayModeProperties;

    @JvmField
    @NotNull
    public final DtidProvider dtidProvider;

    @JvmField
    @NotNull
    public final NetworkProperties networkProperties;

    @JvmField
    @NotNull
    public final OperatingSystemProperties operatingSystemProperties;

    @JvmField
    @NotNull
    public final RecommendationsPropertiesProvider recommendationsPropertiesProvider;

    @JvmField
    @NotNull
    public final SonyCalibratedModePropertiesProvider sonyCalibratedModePropertiesProvider;

    @JvmField
    @NotNull
    public final TerminatorIdProvider terminatorIdProvider;

    @JvmField
    @NotNull
    public final VideoCapabilitiesProvider videoCapabilitiesProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public DefaultDeviceProperties(@NotNull AdvertisingProperties advertisingProperties, @NotNull DeviceBuildProperties deviceBuildProperties, @NotNull DeviceIdProvider deviceIdProvider, @NotNull DtidProvider dtidProvider, @NotNull ApplicationPackagePropertiesProvider applicationPackagePropertiesProvider, @NotNull VideoCapabilitiesProvider videoCapabilitiesProvider, @NotNull DisplayModeProperties displayModeProperties, @NotNull NetworkProperties networkProperties, @NotNull AudioProperties audioProperties, @NotNull OperatingSystemProperties operatingSystemProperties, @NotNull ApplicationSourcePropertiesProvider applicationSourcePropertiesProvider, @NotNull BillingProperties billingProperties, @NotNull TerminatorIdProvider terminatorIdProvider, @NotNull SonyCalibratedModePropertiesProvider sonyCalibratedModePropertiesProvider, @NotNull RecommendationsPropertiesProvider recommendationsPropertiesProvider) {
        Intrinsics.checkNotNullParameter(advertisingProperties, "advertisingProperties");
        Intrinsics.checkNotNullParameter(deviceBuildProperties, "deviceBuildProperties");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        Intrinsics.checkNotNullParameter(dtidProvider, "dtidProvider");
        Intrinsics.checkNotNullParameter(applicationPackagePropertiesProvider, "applicationPackagePropertiesProvider");
        Intrinsics.checkNotNullParameter(videoCapabilitiesProvider, "videoCapabilitiesProvider");
        Intrinsics.checkNotNullParameter(displayModeProperties, "displayModeProperties");
        Intrinsics.checkNotNullParameter(networkProperties, "networkProperties");
        Intrinsics.checkNotNullParameter(audioProperties, "audioProperties");
        Intrinsics.checkNotNullParameter(operatingSystemProperties, "operatingSystemProperties");
        Intrinsics.checkNotNullParameter(applicationSourcePropertiesProvider, "applicationSourcePropertiesProvider");
        Intrinsics.checkNotNullParameter(billingProperties, "billingProperties");
        Intrinsics.checkNotNullParameter(terminatorIdProvider, "terminatorIdProvider");
        Intrinsics.checkNotNullParameter(sonyCalibratedModePropertiesProvider, "sonyCalibratedModePropertiesProvider");
        Intrinsics.checkNotNullParameter(recommendationsPropertiesProvider, "recommendationsPropertiesProvider");
        this.advertisingProperties = advertisingProperties;
        this.deviceBuildProperties = deviceBuildProperties;
        this.deviceIdProvider = deviceIdProvider;
        this.dtidProvider = dtidProvider;
        this.applicationPackagePropertiesProvider = applicationPackagePropertiesProvider;
        this.videoCapabilitiesProvider = videoCapabilitiesProvider;
        this.displayModeProperties = displayModeProperties;
        this.networkProperties = networkProperties;
        this.audioProperties = audioProperties;
        this.operatingSystemProperties = operatingSystemProperties;
        this.applicationSourcePropertiesProvider = applicationSourcePropertiesProvider;
        this.billingProperties = billingProperties;
        this.terminatorIdProvider = terminatorIdProvider;
        this.sonyCalibratedModePropertiesProvider = sonyCalibratedModePropertiesProvider;
        this.recommendationsPropertiesProvider = recommendationsPropertiesProvider;
    }

    @Override // com.amazon.livingroom.deviceproperties.AbstractDeviceProperties
    public <T> T get(@NotNull DeviceProperties.Property<T> property, @NotNull DeviceProperties otherProperties) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(otherProperties, "otherProperties");
        return property.getFrom(this, otherProperties);
    }
}
