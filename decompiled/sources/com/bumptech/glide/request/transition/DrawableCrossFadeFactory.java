package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DrawableCrossFadeFactory implements TransitionFactory<Drawable> {
    public final int duration;
    public final boolean isCrossFadeEnabled;
    public DrawableCrossFadeTransition resourceTransition;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public static final int DEFAULT_DURATION_MS = 300;
        public final int durationMillis;
        public boolean isCrossFadeEnabled;

        public Builder() {
            this(DEFAULT_DURATION_MS);
        }

        public DrawableCrossFadeFactory build() {
            return new DrawableCrossFadeFactory(this.durationMillis, this.isCrossFadeEnabled);
        }

        public Builder setCrossFadeEnabled(boolean z) {
            this.isCrossFadeEnabled = z;
            return this;
        }

        public Builder(int i) {
            this.durationMillis = i;
        }
    }

    public DrawableCrossFadeFactory(int i, boolean z) {
        this.duration = i;
        this.isCrossFadeEnabled = z;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<Drawable> build(DataSource dataSource, boolean z) {
        return dataSource == DataSource.MEMORY_CACHE ? NoTransition.NO_ANIMATION : getResourceTransition();
    }

    public final Transition<Drawable> getResourceTransition() {
        if (this.resourceTransition == null) {
            this.resourceTransition = new DrawableCrossFadeTransition(this.duration, this.isCrossFadeEnabled);
        }
        return this.resourceTransition;
    }
}
