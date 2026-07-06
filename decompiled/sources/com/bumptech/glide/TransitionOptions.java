package com.bumptech.glide;

import androidx.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.bumptech.glide.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class TransitionOptions<CHILD extends TransitionOptions<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    public TransitionFactory<? super TranscodeType> transitionFactory = (TransitionFactory<? super TranscodeType>) NoTransition.NO_ANIMATION_FACTORY;

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public final CHILD dontTransition() {
        transition(NoTransition.NO_ANIMATION_FACTORY);
        return this;
    }

    public final TransitionFactory<? super TranscodeType> getTransitionFactory() {
        return this.transitionFactory;
    }

    @NonNull
    public final CHILD transition(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        Preconditions.checkNotNull(transitionFactory, "Argument must not be null");
        this.transitionFactory = transitionFactory;
        return this;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final CHILD m347clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public final CHILD transition(int i) {
        this.transitionFactory = new ViewAnimationFactory(i);
        return this;
    }

    @NonNull
    public final CHILD transition(@NonNull ViewPropertyTransition.Animator animator) {
        this.transitionFactory = new ViewPropertyAnimationFactory(animator);
        return this;
    }

    public final CHILD self() {
        return this;
    }
}
