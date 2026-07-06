package androidx.media3.exoplayer;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil$$ExternalSyntheticOutline0;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioFocusManager {
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int AUDIOFOCUS_NONE = 0;
    public static final int AUDIO_FOCUS_STATE_HAVE_FOCUS = 1;
    public static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = 2;
    public static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_DUCK = 3;
    public static final int AUDIO_FOCUS_STATE_NO_FOCUS = 0;
    public static final int PLAYER_COMMAND_DO_NOT_PLAY = -1;
    public static final int PLAYER_COMMAND_PLAY_WHEN_READY = 1;
    public static final int PLAYER_COMMAND_WAIT_FOR_CALLBACK = 0;
    public static final String TAG = "AudioFocusManager";
    public static final float VOLUME_MULTIPLIER_DEFAULT = 1.0f;
    public static final float VOLUME_MULTIPLIER_DUCK = 0.2f;

    @Nullable
    public AudioAttributes audioAttributes;
    public AudioFocusRequest audioFocusRequest;
    public int audioFocusState;
    public final AudioManager audioManager;
    public int focusGainToRequest;
    public final AudioFocusListener focusListener;

    @Nullable
    public PlayerControl playerControl;
    public boolean rebuildAudioFocusRequest;
    public float volumeMultiplier = 1.0f;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        public final Handler eventHandler;

        public AudioFocusListener(Handler handler) {
            this.eventHandler = handler;
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(final int i) {
            this.eventHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.AudioFocusManager$AudioFocusListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AudioFocusManager.this.handlePlatformAudioFocusChange(i);
                }
            });
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerCommand {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    public AudioFocusManager(Context context, Handler handler, PlayerControl playerControl) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        audioManager.getClass();
        this.audioManager = audioManager;
        this.playerControl = playerControl;
        this.focusListener = new AudioFocusListener(handler);
        this.audioFocusState = 0;
    }

    public static int convertAudioAttributesToFocusGain(@Nullable AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return 0;
        }
        switch (audioAttributes.usage) {
            case 0:
                Log.w("AudioFocusManager", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 11:
                if (audioAttributes.contentType == 1) {
                    return 2;
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                return 3;
            case 15:
            default:
                Log.w("AudioFocusManager", "Unidentified audio usage: " + audioAttributes.usage);
                return 0;
            case 16:
                return Util.SDK_INT >= 19 ? 4 : 2;
        }
    }

    public final void abandonAudioFocusDefault() {
        this.audioManager.abandonAudioFocus(this.focusListener);
    }

    public final void abandonAudioFocusIfHeld() {
        if (this.audioFocusState == 0) {
            return;
        }
        if (Util.SDK_INT >= 26) {
            abandonAudioFocusV26();
        } else {
            abandonAudioFocusDefault();
        }
        setAudioFocusState(0);
    }

    @RequiresApi(26)
    public final void abandonAudioFocusV26() {
        AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
        if (audioFocusRequest != null) {
            this.audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }
    }

    public final void executePlayerCommand(int i) {
        PlayerControl playerControl = this.playerControl;
        if (playerControl != null) {
            playerControl.executePlayerCommand(i);
        }
    }

    @VisibleForTesting
    public AudioManager.OnAudioFocusChangeListener getFocusListener() {
        return this.focusListener;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public final void handlePlatformAudioFocusChange(int i) {
        if (i == -3 || i == -2) {
            if (i != -2 && !willPauseWhenDucked()) {
                setAudioFocusState(3);
                return;
            } else {
                executePlayerCommand(0);
                setAudioFocusState(2);
                return;
            }
        }
        if (i == -1) {
            executePlayerCommand(-1);
            abandonAudioFocusIfHeld();
        } else if (i != 1) {
            NalUnitUtil$$ExternalSyntheticOutline0.m("Unknown focus change type: ", i, "AudioFocusManager");
        } else {
            setAudioFocusState(1);
            executePlayerCommand(1);
        }
    }

    public void release() {
        this.playerControl = null;
        abandonAudioFocusIfHeld();
    }

    public final int requestAudioFocus() {
        if (this.audioFocusState == 1) {
            return 1;
        }
        if ((Util.SDK_INT >= 26 ? requestAudioFocusV26() : requestAudioFocusDefault()) == 1) {
            setAudioFocusState(1);
            return 1;
        }
        setAudioFocusState(0);
        return -1;
    }

    public final int requestAudioFocusDefault() {
        AudioManager audioManager = this.audioManager;
        AudioFocusListener audioFocusListener = this.focusListener;
        AudioAttributes audioAttributes = this.audioAttributes;
        audioAttributes.getClass();
        return audioManager.requestAudioFocus(audioFocusListener, Util.getStreamTypeForAudioUsage(audioAttributes.usage), this.focusGainToRequest);
    }

    @RequiresApi(26)
    public final int requestAudioFocusV26() {
        AudioFocusRequest.Builder builderM;
        AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
        if (audioFocusRequest == null || this.rebuildAudioFocusRequest) {
            if (audioFocusRequest == null) {
                AudioFocusManager$$ExternalSyntheticApiModelOutline2.m();
                builderM = AudioFocusManager$$ExternalSyntheticApiModelOutline0.m(this.focusGainToRequest);
            } else {
                AudioFocusManager$$ExternalSyntheticApiModelOutline2.m();
                builderM = AudioFocusManager$$ExternalSyntheticApiModelOutline1.m(this.audioFocusRequest);
            }
            boolean zWillPauseWhenDucked = willPauseWhenDucked();
            AudioAttributes audioAttributes = this.audioAttributes;
            audioAttributes.getClass();
            this.audioFocusRequest = builderM.setAudioAttributes(audioAttributes.getAudioAttributesV21().audioAttributes).setWillPauseWhenDucked(zWillPauseWhenDucked).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return this.audioManager.requestAudioFocus(this.audioFocusRequest);
    }

    public void setAudioAttributes(@Nullable AudioAttributes audioAttributes) {
        if (Util.areEqual(this.audioAttributes, audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        int iConvertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(audioAttributes);
        this.focusGainToRequest = iConvertAudioAttributesToFocusGain;
        boolean z = true;
        if (iConvertAudioAttributesToFocusGain != 1 && iConvertAudioAttributesToFocusGain != 0) {
            z = false;
        }
        Assertions.checkArgument(z, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
    }

    public final void setAudioFocusState(int i) {
        if (this.audioFocusState == i) {
            return;
        }
        this.audioFocusState = i;
        float f = i == 3 ? 0.2f : 1.0f;
        if (this.volumeMultiplier == f) {
            return;
        }
        this.volumeMultiplier = f;
        PlayerControl playerControl = this.playerControl;
        if (playerControl != null) {
            playerControl.setVolumeMultiplier(f);
        }
    }

    public final boolean shouldAbandonAudioFocusIfHeld(int i) {
        return i == 1 || this.focusGainToRequest != 1;
    }

    public int updateAudioFocus(boolean z, int i) {
        if (shouldAbandonAudioFocusIfHeld(i)) {
            abandonAudioFocusIfHeld();
            return z ? 1 : -1;
        }
        if (z) {
            return requestAudioFocus();
        }
        return -1;
    }

    public final boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes = this.audioAttributes;
        return audioAttributes != null && audioAttributes.contentType == 1;
    }
}
