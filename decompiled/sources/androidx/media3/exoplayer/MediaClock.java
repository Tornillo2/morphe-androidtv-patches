package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface MediaClock {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.MediaClock$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static boolean $default$hasSkippedSilenceSinceLastCall(MediaClock mediaClock) {
            return false;
        }
    }

    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    boolean hasSkippedSilenceSinceLastCall();

    void setPlaybackParameters(PlaybackParameters playbackParameters);
}
