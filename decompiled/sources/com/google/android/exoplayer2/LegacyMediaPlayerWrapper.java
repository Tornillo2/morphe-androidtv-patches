package com.google.android.exoplayer2;

import android.media.MediaPlayer;
import android.os.Looper;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LegacyMediaPlayerWrapper extends SimpleBasePlayer {
    public boolean playWhenReady;
    public final MediaPlayer player;

    public LegacyMediaPlayerWrapper(Looper looper) {
        super(looper);
        this.player = new MediaPlayer();
    }

    @Override // com.google.android.exoplayer2.SimpleBasePlayer
    public SimpleBasePlayer.State getState() {
        SimpleBasePlayer.State.Builder builder = new SimpleBasePlayer.State.Builder();
        Player.Commands.Builder builder2 = new Player.Commands.Builder();
        builder2.flagsBuilder.addAll(1);
        builder.availableCommands = builder2.build();
        builder.playWhenReady = this.playWhenReady;
        builder.playWhenReadyChangeReason = 1;
        return new SimpleBasePlayer.State(builder);
    }

    @Override // com.google.android.exoplayer2.SimpleBasePlayer
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        this.playWhenReady = z;
        if (z) {
            this.player.start();
        } else {
            this.player.pause();
        }
        return ImmediateFuture.NULL;
    }
}
