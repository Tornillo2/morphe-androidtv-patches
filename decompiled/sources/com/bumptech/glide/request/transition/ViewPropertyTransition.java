package com.bumptech.glide.request.transition;

import android.view.View;
import com.bumptech.glide.request.transition.Transition;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ViewPropertyTransition<R> implements Transition<R> {
    public final Animator animator;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Animator {
        void animate(View view);
    }

    public ViewPropertyTransition(Animator animator) {
        this.animator = animator;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(R r, Transition.ViewAdapter viewAdapter) {
        if (viewAdapter.getView() == null) {
            return false;
        }
        this.animator.animate(viewAdapter.getView());
        return false;
    }
}
