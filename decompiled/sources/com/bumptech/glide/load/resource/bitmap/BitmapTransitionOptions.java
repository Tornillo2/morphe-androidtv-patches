package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BitmapTransitionOptions extends TransitionOptions<BitmapTransitionOptions, Bitmap> {
    @NonNull
    public static BitmapTransitionOptions with(@NonNull TransitionFactory<Bitmap> transitionFactory) {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.transition(transitionFactory);
        return bitmapTransitionOptions;
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade() {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.crossFade();
        return bitmapTransitionOptions;
    }

    @NonNull
    public static BitmapTransitionOptions withWrapped(@NonNull TransitionFactory<Drawable> transitionFactory) {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.transitionUsing(transitionFactory);
        return bitmapTransitionOptions;
    }

    @NonNull
    public BitmapTransitionOptions crossFade() {
        transitionUsing(new DrawableCrossFadeFactory.Builder().build());
        return this;
    }

    @NonNull
    public BitmapTransitionOptions transitionUsing(@NonNull TransitionFactory<Drawable> transitionFactory) {
        this.transitionFactory = new BitmapTransitionFactory(transitionFactory);
        return this;
    }

    @NonNull
    public BitmapTransitionOptions crossFade(int i) {
        transitionUsing(new DrawableCrossFadeFactory.Builder(i).build());
        return this;
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(int i) {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.crossFade(i);
        return bitmapTransitionOptions;
    }

    @NonNull
    public BitmapTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        transitionUsing(drawableCrossFadeFactory);
        return this;
    }

    @NonNull
    public BitmapTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        transitionUsing(builder.build());
        return this;
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.transitionUsing(drawableCrossFadeFactory);
        return bitmapTransitionOptions;
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        bitmapTransitionOptions.crossFade(builder);
        return bitmapTransitionOptions;
    }
}
