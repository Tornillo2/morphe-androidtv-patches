package androidx.media3.exoplayer.upstream;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import com.google.common.collect.EmptyImmutableListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CmcdConfiguration {
    public static final String CMCD_QUERY_PARAMETER_KEY = "CMCD";
    public static final String KEY_BITRATE = "br";
    public static final String KEY_BUFFER_LENGTH = "bl";
    public static final String KEY_BUFFER_STARVATION = "bs";
    public static final String KEY_CMCD_OBJECT = "CMCD-Object";
    public static final String KEY_CMCD_REQUEST = "CMCD-Request";
    public static final String KEY_CMCD_SESSION = "CMCD-Session";
    public static final String KEY_CMCD_STATUS = "CMCD-Status";
    public static final String KEY_CONTENT_ID = "cid";
    public static final String KEY_DEADLINE = "dl";
    public static final String KEY_MAXIMUM_REQUESTED_BITRATE = "rtp";
    public static final String KEY_MEASURED_THROUGHPUT = "mtp";
    public static final String KEY_NEXT_OBJECT_REQUEST = "nor";
    public static final String KEY_NEXT_RANGE_REQUEST = "nrr";
    public static final String KEY_OBJECT_DURATION = "d";
    public static final String KEY_OBJECT_TYPE = "ot";
    public static final String KEY_PLAYBACK_RATE = "pr";
    public static final String KEY_SESSION_ID = "sid";
    public static final String KEY_STARTUP = "su";
    public static final String KEY_STREAMING_FORMAT = "sf";
    public static final String KEY_STREAM_TYPE = "st";
    public static final String KEY_TOP_BITRATE = "tb";
    public static final String KEY_VERSION = "v";
    public static final int MAX_ID_LENGTH = 64;
    public static final int MODE_QUERY_PARAMETER = 1;
    public static final int MODE_REQUEST_HEADER = 0;

    @Nullable
    public final String contentId;
    public final int dataTransmissionMode;
    public final RequestConfig requestConfig;

    @Nullable
    public final String sessionId;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CmcdKey {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataTransmissionMode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Factory {
        public static final Factory DEFAULT = new CmcdConfiguration$Factory$$ExternalSyntheticLambda0();

        /* JADX INFO: renamed from: androidx.media3.exoplayer.upstream.CmcdConfiguration$Factory$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static CmcdConfiguration lambda$static$0(MediaItem mediaItem) {
                String string = UUID.randomUUID().toString();
                String str = mediaItem.mediaId;
                if (str == null) {
                    str = "";
                }
                return new CmcdConfiguration(string, str, new AnonymousClass1(), 0);
            }
        }

        /* JADX INFO: renamed from: androidx.media3.exoplayer.upstream.CmcdConfiguration$Factory$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public class AnonymousClass1 implements RequestConfig {
            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public ImmutableListMultimap getCustomData() {
                return EmptyImmutableListMultimap.INSTANCE;
            }

            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public /* synthetic */ int getRequestedMaximumThroughputKbps(int i) {
                return -2147483647;
            }

            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.RequestConfig
            public /* synthetic */ boolean isKeyAllowed(String str) {
                return true;
            }
        }

        CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface HeaderKey {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface RequestConfig {

        /* JADX INFO: renamed from: androidx.media3.exoplayer.upstream.CmcdConfiguration$RequestConfig$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static int $default$getRequestedMaximumThroughputKbps(RequestConfig requestConfig, int i) {
                return -2147483647;
            }

            public static boolean $default$isKeyAllowed(RequestConfig requestConfig, String str) {
                return true;
            }
        }

        ImmutableListMultimap<String, String> getCustomData();

        int getRequestedMaximumThroughputKbps(int i);

        boolean isKeyAllowed(String str);
    }

    public CmcdConfiguration(@Nullable String str, @Nullable String str2, RequestConfig requestConfig) {
        this(str, str2, requestConfig, 0);
    }

    public boolean isBitrateLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isBufferLengthLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isBufferStarvationLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isContentIdLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isDeadlineLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isMaximumRequestThroughputLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isMeasuredThroughputLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isNextObjectRequestLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isNextRangeRequestLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isObjectDurationLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isObjectTypeLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isPlaybackRateLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isSessionIdLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isStartupLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isStreamTypeLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isStreamingFormatLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public boolean isTopBitrateLoggingAllowed() {
        this.requestConfig.getClass();
        return true;
    }

    public CmcdConfiguration(@Nullable String str, @Nullable String str2, RequestConfig requestConfig, int i) {
        boolean z = true;
        Assertions.checkArgument(str == null || str.length() <= 64);
        if (str2 != null && str2.length() > 64) {
            z = false;
        }
        Assertions.checkArgument(z);
        requestConfig.getClass();
        this.sessionId = str;
        this.contentId = str2;
        this.requestConfig = requestConfig;
        this.dataTransmissionMode = i;
    }
}
