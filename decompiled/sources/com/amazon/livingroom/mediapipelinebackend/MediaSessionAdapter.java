package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.ignitionshared.IgnitionContext;
import com.amazon.ignitionshared.IgnitionContextListener;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nMediaSessionAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaSessionAdapter.kt\ncom/amazon/livingroom/mediapipelinebackend/MediaSessionAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,145:1\n1#2:146\n*E\n"})
public final class MediaSessionAdapter extends MediaPipelineListener implements IgnitionContextListener, DefaultLifecycleObserver {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final float PLAYBACK_SPEED = 1.0f;
    public static final long TRANSPORT_CONTROLS = 1049455;

    @NotNull
    public final MediaSessionCompat.Callback callback;

    @Nullable
    public MediaSessionCompat mediaSession;
    public int playbackState;
    public final PlaybackStateCompat.Builder playbackStateBuilder;
    public long positionMs;

    @NotNull
    public final String programName;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final MediaSessionCompat createMediaSessionCompat$ignitionshared_release(@NotNull Context context, @NotNull String programName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(programName, "programName");
            return new MediaSessionCompat(context, programName, null, null);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public MediaSessionAdapter(@NotNull IgnitionContextProvider ignitionContextProvider, @NotNull MediaSessionCompat.Callback callback, @Named(Names.APPLICATION_PACKAGE_NAME) @NotNull String programName) {
        Intrinsics.checkNotNullParameter(ignitionContextProvider, "ignitionContextProvider");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(programName, "programName");
        this.callback = callback;
        this.programName = programName;
        PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
        builder.mActions = TRANSPORT_CONTROLS;
        this.playbackStateBuilder = builder;
        IgnitionContext ignitionContext = ignitionContextProvider.context;
        if (ignitionContext != null) {
            onContextCreated(ignitionContext);
        }
        ignitionContextProvider.addListener(this);
    }

    public final void createMediaSession(AppCompatActivity appCompatActivity) {
        releaseMediaSession();
        MediaSessionCompat mediaSessionCompatCreateMediaSessionCompat$ignitionshared_release = Companion.createMediaSessionCompat$ignitionshared_release(appCompatActivity, this.programName);
        mediaSessionCompatCreateMediaSessionCompat$ignitionshared_release.setCallback(this.callback, null);
        this.mediaSession = mediaSessionCompatCreateMediaSessionCompat$ignitionshared_release;
        updatePlaybackState();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void init() {
        this.playbackState = 6;
        this.positionMs = 0L;
        updatePlaybackState();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onBufferUnderrun() {
        this.playbackState = 6;
        updatePlaybackState();
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextCreated(@NotNull IgnitionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        createMediaSession(context.activity);
        context.activity.getLifecycle().addObserver(this);
    }

    @Override // com.amazon.ignitionshared.IgnitionContextListener
    public void onContextDestroyed() {
        releaseMediaSession();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onError(int i, @NotNull String message, boolean z, @Nullable Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            this.playbackState = 7;
            updatePlaybackState();
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void onPlaybackPositionUpdate(long j) {
        this.positionMs = j;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.setActive(true);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.setActive(false);
        }
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void pause() {
        this.playbackState = 2;
        updatePlaybackState();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void play() {
        this.playbackState = 3;
        updatePlaybackState();
    }

    public final void releaseMediaSession() {
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.release();
        }
        this.mediaSession = null;
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void seek(long j) {
        this.positionMs = j;
        updatePlaybackState();
    }

    @Override // com.amazon.livingroom.mediapipelinebackend.MediaPipelineListener
    public void shutdown() {
        this.playbackState = 0;
        this.positionMs = 0L;
        updatePlaybackState();
    }

    public final void updatePlaybackState() {
        this.playbackStateBuilder.setState(this.playbackState, this.positionMs, 1.0f);
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.setPlaybackState(this.playbackStateBuilder.build());
        }
    }
}
