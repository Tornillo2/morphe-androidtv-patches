package com.amazon.livingroom.mediapipelinebackend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
    public final Listener listener;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Listener {
        void onHdmiAudioPlugEvent(boolean z);
    }

    @VisibleForTesting
    public HdmiAudioPlugBroadcastReceiver(Listener listener) {
        this.listener = listener;
    }

    public static HdmiAudioPlugBroadcastReceiver register(Context context, Listener listener) {
        HdmiAudioPlugBroadcastReceiver hdmiAudioPlugBroadcastReceiver = new HdmiAudioPlugBroadcastReceiver(listener);
        context.registerReceiver(hdmiAudioPlugBroadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        return hdmiAudioPlugBroadcastReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null || !"android.media.action.HDMI_AUDIO_PLUG".equalsIgnoreCase(intent.getAction()) || (extras = intent.getExtras()) == null) {
            return;
        }
        Object obj = extras.get("android.media.extra.AUDIO_PLUG_STATE");
        if (obj instanceof Integer) {
            this.listener.onHdmiAudioPlugEvent(((Integer) obj).intValue() == 1);
        }
    }
}
