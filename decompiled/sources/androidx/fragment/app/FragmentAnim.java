package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.core.view.OneShotPreDrawListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FragmentAnim {
    @AnimRes
    public static int getNextAnim(Fragment fragment, boolean z, boolean z2) {
        return z2 ? z ? fragment.getPopEnterAnim() : fragment.getPopExitAnim() : z ? fragment.getEnterAnim() : fragment.getExitAnim();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[Catch: RuntimeException -> 0x005f, TRY_LEAVE, TryCatch #1 {RuntimeException -> 0x005f, blocks: (B:24:0x0053, B:26:0x0059), top: B:37:0x0053 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.fragment.app.FragmentAnim.AnimationOrAnimator loadAnimation(@androidx.annotation.NonNull android.content.Context r4, @androidx.annotation.NonNull androidx.fragment.app.Fragment r5, boolean r6, boolean r7) {
        /*
            int r0 = r5.getNextTransition()
            int r7 = getNextAnim(r5, r6, r7)
            r1 = 0
            r5.setAnimations(r1, r1, r1, r1)
            android.view.ViewGroup r1 = r5.mContainer
            r2 = 0
            if (r1 == 0) goto L20
            int r3 = androidx.fragment.R.id.visible_removing_fragment_view_tag
            java.lang.Object r1 = r1.getTag(r3)
            if (r1 == 0) goto L20
            android.view.ViewGroup r1 = r5.mContainer
            int r3 = androidx.fragment.R.id.visible_removing_fragment_view_tag
            r1.setTag(r3, r2)
        L20:
            android.view.ViewGroup r5 = r5.mContainer
            if (r5 == 0) goto L2b
            android.animation.LayoutTransition r5 = r5.getLayoutTransition()
            if (r5 == 0) goto L2b
            goto L6f
        L2b:
            if (r7 != 0) goto L33
            if (r0 == 0) goto L33
            int r7 = transitToAnimResourceId(r4, r0, r6)
        L33:
            if (r7 == 0) goto L6f
            android.content.res.Resources r5 = r4.getResources()
            java.lang.String r5 = r5.getResourceTypeName(r7)
            java.lang.String r6 = "anim"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L53
            android.view.animation.Animation r6 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)     // Catch: android.content.res.Resources.NotFoundException -> L51 java.lang.RuntimeException -> L53
            if (r6 == 0) goto L6f
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r0 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch: android.content.res.Resources.NotFoundException -> L51 java.lang.RuntimeException -> L53
            r0.<init>(r6)     // Catch: android.content.res.Resources.NotFoundException -> L51 java.lang.RuntimeException -> L53
            return r0
        L51:
            r4 = move-exception
            throw r4
        L53:
            android.animation.Animator r6 = android.animation.AnimatorInflater.loadAnimator(r4, r7)     // Catch: java.lang.RuntimeException -> L5f
            if (r6 == 0) goto L6f
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r0 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator     // Catch: java.lang.RuntimeException -> L5f
            r0.<init>(r6)     // Catch: java.lang.RuntimeException -> L5f
            return r0
        L5f:
            r6 = move-exception
            if (r5 != 0) goto L6e
            android.view.animation.Animation r4 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)
            if (r4 == 0) goto L6f
            androidx.fragment.app.FragmentAnim$AnimationOrAnimator r5 = new androidx.fragment.app.FragmentAnim$AnimationOrAnimator
            r5.<init>(r4)
            return r5
        L6e:
            throw r6
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentAnim.loadAnimation(android.content.Context, androidx.fragment.app.Fragment, boolean, boolean):androidx.fragment.app.FragmentAnim$AnimationOrAnimator");
    }

    @AnimRes
    public static int toActivityTransitResId(@NonNull Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Animation.Activity, new int[]{i});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    @AnimRes
    public static int transitToAnimResourceId(@NonNull Context context, int i, boolean z) {
        if (i == 4097) {
            return z ? androidx.fragment.R.animator.fragment_open_enter : androidx.fragment.R.animator.fragment_open_exit;
        }
        if (i == 8194) {
            return z ? androidx.fragment.R.animator.fragment_close_enter : androidx.fragment.R.animator.fragment_close_exit;
        }
        if (i == 8197) {
            return z ? toActivityTransitResId(context, R.attr.activityCloseEnterAnimation) : toActivityTransitResId(context, R.attr.activityCloseExitAnimation);
        }
        if (i == 4099) {
            return z ? androidx.fragment.R.animator.fragment_fade_enter : androidx.fragment.R.animator.fragment_fade_exit;
        }
        if (i != 4100) {
            return -1;
        }
        return z ? toActivityTransitResId(context, R.attr.activityOpenEnterAnimation) : toActivityTransitResId(context, R.attr.activityOpenExitAnimation);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        public AnimationOrAnimator(Animation animation) {
            this.animation = animation;
            this.animator = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public AnimationOrAnimator(Animator animator) {
            this.animation = null;
            this.animator = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        public boolean mAnimating;
        public final View mChild;
        public boolean mEnded;
        public final ViewGroup mParent;
        public boolean mTransitionEnded;

        public EndViewTransitionAnimation(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.mAnimating = true;
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
            } else {
                this.mAnimating = false;
                this.mParent.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation, float f) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }
    }
}
