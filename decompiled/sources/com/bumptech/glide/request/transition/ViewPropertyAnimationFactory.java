package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ViewPropertyAnimationFactory<R> implements TransitionFactory<R> {
    public ViewPropertyTransition<R> animation;
    public final ViewPropertyTransition.Animator animator;

    public ViewPropertyAnimationFactory(ViewPropertyTransition.Animator animator) {
        this.animator = animator;
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.NO_ANIMATION;
        }
        if (this.animation == null) {
            this.animation = new ViewPropertyTransition<>(this.animator);
        }
        return this.animation;
    }
}
