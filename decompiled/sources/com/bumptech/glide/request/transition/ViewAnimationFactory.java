package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewTransition;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ViewAnimationFactory<R> implements TransitionFactory<R> {
    public Transition<R> transition;
    public final ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConcreteViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        public final Animation animation;

        public ConcreteViewTransitionAnimationFactory(Animation animation) {
            this.animation = animation;
        }

        @Override // com.bumptech.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory
        public Animation build(Context context) {
            return this.animation;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ResourceViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        public final int animationId;

        public ResourceViewTransitionAnimationFactory(int i) {
            this.animationId = i;
        }

        @Override // com.bumptech.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory
        public Animation build(Context context) {
            return AnimationUtils.loadAnimation(context, this.animationId);
        }
    }

    public ViewAnimationFactory(Animation animation) {
        this(new ConcreteViewTransitionAnimationFactory(animation));
    }

    @Override // com.bumptech.glide.request.transition.TransitionFactory
    public Transition<R> build(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.NO_ANIMATION;
        }
        if (this.transition == null) {
            this.transition = new ViewTransition(this.viewTransitionAnimationFactory);
        }
        return this.transition;
    }

    public ViewAnimationFactory(int i) {
        this(new ResourceViewTransitionAnimationFactory(i));
    }

    public ViewAnimationFactory(ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory;
    }
}
