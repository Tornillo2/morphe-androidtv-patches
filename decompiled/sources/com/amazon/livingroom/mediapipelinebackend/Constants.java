package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.audio.AudioCapabilities;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Constants {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AUDIO {
        public static final int AUDIO_TRACK_ERROR_NATIVE_DEAD_OBJECT = -32;
        public static final int CHANNEL_COUNT_SURROUND_5_1 = 6;

        @OptIn(markerClass = {UnstableApi.class})
        public static final AudioCapabilities SURROUND_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{6}, 6);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MANUFACTURERS {
        public static final String NVIDIA = "nVIDIA";
        public static final String PHILIPS = "Philips";
        public static final String SONY = "Sony";
    }
}
