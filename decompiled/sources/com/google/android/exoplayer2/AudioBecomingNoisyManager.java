package com.google.android.exoplayer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AudioBecomingNoisyManager {
    public final Context context;
    public final AudioBecomingNoisyReceiver receiver;
    public boolean receiverRegistered;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AudioBecomingNoisyReceiver extends BroadcastReceiver implements Runnable {
        public final Handler eventHandler;
        public final EventListener listener;

        public AudioBecomingNoisyReceiver(Handler handler, EventListener eventListener) {
            this.eventHandler = handler;
            this.listener = eventListener;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                this.eventHandler.post(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AudioBecomingNoisyManager.this.receiverRegistered) {
                this.listener.onAudioBecomingNoisy();
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface EventListener {
        void onAudioBecomingNoisy();
    }

    public AudioBecomingNoisyManager(Context context, Handler handler, EventListener eventListener) {
        this.context = context.getApplicationContext();
        this.receiver = new AudioBecomingNoisyReceiver(handler, eventListener);
    }

    public void setEnabled(boolean z) {
        if (z && !this.receiverRegistered) {
            this.context.registerReceiver(this.receiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
            this.receiverRegistered = true;
        } else {
            if (z || !this.receiverRegistered) {
                return;
            }
            this.context.unregisterReceiver(this.receiver);
            this.receiverRegistered = false;
        }
    }
}
