package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioOffloadSupport;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DefaultAudioOffloadSupportProvider implements DefaultAudioSink.AudioOffloadSupportProvider {
    public static final String OFFLOAD_VARIABLE_RATE_SUPPORTED_KEY = "offloadVariableRateSupported";

    @Nullable
    public final Context context;
    public Boolean isOffloadVariableRateSupported;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static final class Api29 {
        @DoNotInline
        public static AudioOffloadSupport getOffloadedPlaybackSupport(AudioFormat audioFormat, AudioAttributes audioAttributes, boolean z) {
            if (!AudioManager.isOffloadedPlaybackSupported(audioFormat, audioAttributes)) {
                return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
            }
            AudioOffloadSupport.Builder builder = new AudioOffloadSupport.Builder();
            builder.isFormatSupported = true;
            builder.isSpeedChangeSupported = z;
            return builder.build();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class Api31 {
        @DoNotInline
        public static AudioOffloadSupport getOffloadedPlaybackSupport(AudioFormat audioFormat, AudioAttributes audioAttributes, boolean z) {
            int playbackOffloadSupport = AudioManager.getPlaybackOffloadSupport(audioFormat, audioAttributes);
            if (playbackOffloadSupport == 0) {
                return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
            }
            AudioOffloadSupport.Builder builder = new AudioOffloadSupport.Builder();
            boolean z2 = Util.SDK_INT > 32 && playbackOffloadSupport == 2;
            builder.isFormatSupported = true;
            builder.isGaplessSupported = z2;
            builder.isSpeedChangeSupported = z;
            return builder.build();
        }
    }

    public DefaultAudioOffloadSupportProvider() {
        this(null);
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioOffloadSupportProvider
    public AudioOffloadSupport getAudioOffloadSupport(Format format, androidx.media3.common.AudioAttributes audioAttributes) {
        format.getClass();
        audioAttributes.getClass();
        int i = Util.SDK_INT;
        if (i < 29 || format.sampleRate == -1) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
        boolean zIsOffloadVariableRateSupported = isOffloadVariableRateSupported(this.context);
        String str = format.sampleMimeType;
        str.getClass();
        int encoding = MimeTypes.getEncoding(str, format.codecs);
        if (encoding == 0 || i < Util.getApiLevelThatAudioFormatIntroducedAudioEncoding(encoding)) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
        int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(format.channelCount);
        if (audioTrackChannelConfig == 0) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
        try {
            AudioFormat audioFormat = Util.getAudioFormat(format.sampleRate, audioTrackChannelConfig, encoding);
            return i >= 31 ? Api31.getOffloadedPlaybackSupport(audioFormat, audioAttributes.getAudioAttributesV21().audioAttributes, zIsOffloadVariableRateSupported) : Api29.getOffloadedPlaybackSupport(audioFormat, audioAttributes.getAudioAttributesV21().audioAttributes, zIsOffloadVariableRateSupported);
        } catch (IllegalArgumentException unused) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
    }

    public final boolean isOffloadVariableRateSupported(@Nullable Context context) {
        AudioManager audioManager;
        Boolean bool = this.isOffloadVariableRateSupported;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            this.isOffloadVariableRateSupported = Boolean.FALSE;
        } else {
            String parameters = audioManager.getParameters(OFFLOAD_VARIABLE_RATE_SUPPORTED_KEY);
            this.isOffloadVariableRateSupported = Boolean.valueOf(parameters != null && parameters.equals("offloadVariableRateSupported=1"));
        }
        return this.isOffloadVariableRateSupported.booleanValue();
    }

    public DefaultAudioOffloadSupportProvider(@Nullable Context context) {
        this.context = context;
    }
}
