package com.amazon.livingroom.mediapipelinebackend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AudioInfoReceiver extends BroadcastReceiver {
    public static final int STEREO = 2;
    public AvAudioDeviceInfo audioDeviceInfo = new AvAudioDeviceInfo(2, new int[]{2});

    @VisibleForTesting
    public AudioInfoReceiver() {
    }

    @Nullable
    public static Intent getHdmiAudioPlugInStatusIntent(Context context, @Nullable BroadcastReceiver broadcastReceiver) {
        return context.registerReceiver(broadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
    }

    public static boolean isHDMIAudioDevicePluggedIn(Context context) {
        Intent hdmiAudioPlugInStatusIntent = getHdmiAudioPlugInStatusIntent(context, null);
        return hdmiAudioPlugInStatusIntent != null && hdmiAudioPlugInStatusIntent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 1;
    }

    public static AudioInfoReceiver registerNewInstance(Context context) {
        AudioInfoReceiver audioInfoReceiver = new AudioInfoReceiver();
        audioInfoReceiver.onReceive(context, getHdmiAudioPlugInStatusIntent(context, audioInfoReceiver));
        return audioInfoReceiver;
    }

    public synchronized AvAudioDeviceInfo getAudioDeviceInfo() {
        return new AvAudioDeviceInfo(this.audioDeviceInfo);
    }

    @Override // android.content.BroadcastReceiver
    public synchronized void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("android.media.action.HDMI_AUDIO_PLUG".equalsIgnoreCase(intent.getAction())) {
                short intExtra = (short) intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 2);
                int[] intArrayExtra = intent.getIntArrayExtra("android.media.extra.ENCODINGS");
                MpbLog.i("New audio device info received. Channel count: " + ((int) intExtra) + ", Supported encodings: " + Arrays.toString(intArrayExtra));
                this.audioDeviceInfo.setChannels(intExtra);
                if (intArrayExtra != null && intArrayExtra.length > 0) {
                    this.audioDeviceInfo.setEncodings(intArrayExtra);
                }
            }
        }
    }
}
