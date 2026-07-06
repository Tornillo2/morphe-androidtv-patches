package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DrawableTransitionOptions extends TransitionOptions<DrawableTransitionOptions, Drawable> {
    @NonNull
    public static DrawableTransitionOptions with(@NonNull TransitionFactory<Drawable> transitionFactory) {
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
        drawableTransitionOptions.transition(transitionFactory);
        return drawableTransitionOptions;
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade() {
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
        drawableTransitionOptions.crossFade();
        return drawableTransitionOptions;
    }

    @NonNull
    public DrawableTransitionOptions crossFade() {
        this.transitionFactory = new DrawableCrossFadeFactory.Builder().build();
        return this;
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(int i) {
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
        drawableTransitionOptions.crossFade(i);
        return drawableTransitionOptions;
    }

    @NonNull
    public DrawableTransitionOptions crossFade(int i) {
        this.transitionFactory = new DrawableCrossFadeFactory.Builder(i).build();
        return this;
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
        drawableTransitionOptions.transition(drawableCrossFadeFactory);
        return drawableTransitionOptions;
    }

    @NonNull
    public DrawableTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        transition(drawableCrossFadeFactory);
        return this;
    }

    @NonNull
    public DrawableTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        this.transitionFactory = builder.build();
        return this;
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions();
        drawableTransitionOptions.crossFade(builder);
        return drawableTransitionOptions;
    }
}
