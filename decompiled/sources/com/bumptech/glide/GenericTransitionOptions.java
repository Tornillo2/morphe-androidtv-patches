package com.bumptech.glide;

import androidx.annotation.NonNull;
import com.bumptech.glide.request.transition.NoTransition;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class GenericTransitionOptions<TranscodeType> extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType> {
    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(int i) {
        GenericTransitionOptions<TranscodeType> genericTransitionOptions = new GenericTransitionOptions<>();
        genericTransitionOptions.transition(i);
        return genericTransitionOptions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> withNoTransition() {
        GenericTransitionOptions<TranscodeType> genericTransitionOptions = (GenericTransitionOptions<TranscodeType>) new GenericTransitionOptions();
        genericTransitionOptions.transition(NoTransition.NO_ANIMATION_FACTORY);
        return genericTransitionOptions;
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull ViewPropertyTransition.Animator animator) {
        GenericTransitionOptions<TranscodeType> genericTransitionOptions = new GenericTransitionOptions<>();
        genericTransitionOptions.transition(animator);
        return genericTransitionOptions;
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        GenericTransitionOptions<TranscodeType> genericTransitionOptions = new GenericTransitionOptions<>();
        genericTransitionOptions.transition(transitionFactory);
        return genericTransitionOptions;
    }
}
