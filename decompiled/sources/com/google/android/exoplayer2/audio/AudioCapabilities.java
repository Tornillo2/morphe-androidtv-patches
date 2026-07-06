package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.net.Uri;
import android.provider.Settings;
import android.util.Pair;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AudioCapabilities {
    public static final ImmutableMap<Integer, Integer> ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS;
    public static final int DEFAULT_MAX_CHANNEL_COUNT = 8;
    public static final int DEFAULT_SAMPLE_RATE_HZ = 48000;
    public static final String EXTERNAL_SURROUND_SOUND_KEY = "external_surround_sound_enabled";
    public static final String USE_EXTERNAL_SURROUND_SOUND_FLAG = "use_external_surround_sound_flag";
    public final int maxChannelCount;
    public final int[] supportedEncodings;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 8);
    public static final AudioCapabilities EXTERNAL_SURROUND_SOUND_CAPABILITIES = new AudioCapabilities(new int[]{2, 5, 6}, 8);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static final class Api29 {
        public static final android.media.AudioAttributes DEFAULT_AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(0).build();

        /* JADX WARN: Multi-variable type inference failed */
        @DoNotInline
        public static int[] getDirectPlaybackSupportedEncodings() {
            ImmutableList.Builder builder = ImmutableList.builder();
            UnmodifiableIterator it = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.keySet().iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(num.intValue()).setSampleRate(48000).build(), DEFAULT_AUDIO_ATTRIBUTES)) {
                    builder.add(num);
                }
            }
            builder.add(2);
            return Ints.toArray(builder.build());
        }

        @DoNotInline
        public static int getMaxSupportedChannelCountForPassthrough(int i, int i2) {
            for (int i3 = 8; i3 > 0; i3--) {
                if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(i).setSampleRate(i2).setChannelMask(Util.getAudioTrackChannelConfig(i3)).build(), DEFAULT_AUDIO_ATTRIBUTES)) {
                    return i3;
                }
            }
            return 0;
        }
    }

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
        builder.put(5, 6);
        builder.put(17, 6);
        builder.put(7, 6);
        builder.put(18, 6);
        builder.put(6, 8);
        builder.put(8, 8);
        builder.put(14, 8);
        ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = builder.build(true);
    }

    public AudioCapabilities(@Nullable int[] iArr, int i) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.supportedEncodings = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = i;
    }

    public static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        if (Util.SDK_INT < 17) {
            return false;
        }
        String str = Util.MANUFACTURER;
        return "Amazon".equals(str) || "Xiaomi".equals(str);
    }

    public static AudioCapabilities getCapabilities(Context context) {
        return getCapabilities(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    public static int getChannelConfigForPassthrough(int i) {
        int i2 = Util.SDK_INT;
        if (i2 <= 28) {
            if (i == 7) {
                i = 8;
            } else if (i == 3 || i == 4 || i == 5) {
                i = 6;
            }
        }
        if (i2 <= 26 && "fugu".equals(Util.DEVICE) && i == 1) {
            i = 2;
        }
        return Util.getAudioTrackChannelConfig(i);
    }

    @Nullable
    public static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting()) {
            return Settings.Global.getUriFor("external_surround_sound_enabled");
        }
        return null;
    }

    public static int getMaxSupportedChannelCountForPassthrough(int i, int i2) {
        if (Util.SDK_INT >= 29) {
            return Api29.getMaxSupportedChannelCountForPassthrough(i, i2);
        }
        Integer num = ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.get(Integer.valueOf(i));
        return (num != null ? num : 0).intValue();
    }

    @TargetApi(17)
    public static boolean isSurroundSoundEnabledV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, "external_surround_sound_enabled", 0) == 1;
    }

    public static boolean useSurroundSoundFlagV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, "use_external_surround_sound_flag", 0) == 1;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    @Nullable
    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format format) {
        String str = format.sampleMimeType;
        str.getClass();
        int encoding = MimeTypes.getEncoding(str, format.codecs);
        if (!ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(encoding))) {
            return null;
        }
        if (encoding == 18 && !supportsEncoding(18)) {
            encoding = 6;
        } else if (encoding == 8 && !supportsEncoding(8)) {
            encoding = 7;
        }
        if (!supportsEncoding(encoding)) {
            return null;
        }
        int maxSupportedChannelCountForPassthrough = format.channelCount;
        if (maxSupportedChannelCountForPassthrough == -1 || encoding == 18) {
            int i = format.sampleRate;
            if (i == -1) {
                i = 48000;
            }
            maxSupportedChannelCountForPassthrough = getMaxSupportedChannelCountForPassthrough(encoding, i);
        } else if (maxSupportedChannelCountForPassthrough > this.maxChannelCount) {
            return null;
        }
        int channelConfigForPassthrough = getChannelConfigForPassthrough(maxSupportedChannelCountForPassthrough);
        if (channelConfigForPassthrough == 0) {
            return null;
        }
        return Pair.create(Integer.valueOf(encoding), Integer.valueOf(channelConfigForPassthrough));
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.supportedEncodings) * 31) + this.maxChannelCount;
    }

    public boolean isPassthroughPlaybackSupported(Format format) {
        return getEncodingAndChannelConfigForPassthrough(format) != null;
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }

    @SuppressLint({"InlinedApi"})
    public static AudioCapabilities getCapabilities(Context context, @Nullable Intent intent) {
        boolean zIsSurroundSoundEnabledV17;
        boolean zUseSurroundSoundFlagV17;
        int i = Util.SDK_INT;
        if (i >= 17) {
            ContentResolver contentResolver = context.getContentResolver();
            zUseSurroundSoundFlagV17 = useSurroundSoundFlagV17(contentResolver);
            zIsSurroundSoundEnabledV17 = isSurroundSoundEnabledV17(contentResolver);
        } else {
            zIsSurroundSoundEnabledV17 = false;
            zUseSurroundSoundFlagV17 = false;
        }
        if (zUseSurroundSoundFlagV17) {
            if (zIsSurroundSoundEnabledV17) {
                return EXTERNAL_SURROUND_SOUND_CAPABILITIES;
            }
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        if (deviceMaySetExternalSurroundSoundGlobalSetting() && Settings.Global.getInt(context.getContentResolver(), "external_surround_sound_enabled", 0) == 1) {
            return EXTERNAL_SURROUND_SOUND_CAPABILITIES;
        }
        if (i >= 29 && (Util.isTv(context) || Util.isAutomotive(context))) {
            return new AudioCapabilities(Api29.getDirectPlaybackSupportedEncodings(), 8);
        }
        if (intent != null && intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) != 0) {
            return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 8));
        }
        return DEFAULT_AUDIO_CAPABILITIES;
    }
}
