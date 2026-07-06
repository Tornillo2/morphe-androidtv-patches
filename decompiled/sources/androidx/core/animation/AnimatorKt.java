package androidx.core.animation;

import android.animation.Animator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nAnimator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Animator.kt\nandroidx/core/animation/AnimatorKt\n*L\n1#1,123:1\n85#1,18:124\n85#1,18:142\n85#1,18:160\n85#1,18:178\n*S KotlinDebug\n*F\n+ 1 Animator.kt\nandroidx/core/animation/AnimatorKt\n*L\n29#1:124,18\n39#1:142,18\n49#1:160,18\n58#1:178,18\n*E\n"})
public final class AnimatorKt {

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function1<Animator, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(1);

        public AnonymousClass1() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 extends Lambda implements Function1<Animator, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2(1);

        public AnonymousClass2() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3 extends Lambda implements Function1<Animator, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3(1);

        public AnonymousClass3() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass4 extends Lambda implements Function1<Animator, Unit> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4(1);

        public AnonymousClass4() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addPauseListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00341 extends Lambda implements Function1<Animator, Unit> {
        public static final C00341 INSTANCE = new C00341(1);

        public C00341() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addPauseListener$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class C00352 extends Lambda implements Function1<Animator, Unit> {
        public static final C00352 INSTANCE = new C00352(1);

        public C00352() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Animator animator) {
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public static final Animator.AnimatorListener addListener(@NotNull Animator animator, @NotNull Function1<? super Animator, Unit> function1, @NotNull Function1<? super Animator, Unit> function12, @NotNull Function1<? super Animator, Unit> function13, @NotNull Function1<? super Animator, Unit> function14) {
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    public static /* synthetic */ Animator.AnimatorListener addListener$default(Animator animator, Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = AnonymousClass1.INSTANCE;
        }
        if ((i & 2) != 0) {
            function12 = AnonymousClass2.INSTANCE;
        }
        if ((i & 4) != 0) {
            function13 = AnonymousClass3.INSTANCE;
        }
        if ((i & 8) != 0) {
            function14 = AnonymousClass4.INSTANCE;
        }
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    @NotNull
    public static final Animator.AnimatorPauseListener addPauseListener(@NotNull Animator animator, @NotNull final Function1<? super Animator, Unit> function1, @NotNull final Function1<? super Animator, Unit> function12) {
        Animator.AnimatorPauseListener animatorPauseListener = new Animator.AnimatorPauseListener() { // from class: androidx.core.animation.AnimatorKt$addPauseListener$listener$1
            @Override // android.animation.Animator.AnimatorPauseListener
            public void onAnimationPause(Animator animator2) {
                function12.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorPauseListener
            public void onAnimationResume(Animator animator2) {
                function1.invoke(animator2);
            }
        };
        animator.addPauseListener(animatorPauseListener);
        return animatorPauseListener;
    }

    public static /* synthetic */ Animator.AnimatorPauseListener addPauseListener$default(Animator animator, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = C00341.INSTANCE;
        }
        if ((i & 2) != 0) {
            function12 = C00352.INSTANCE;
        }
        return addPauseListener(animator, function1, function12);
    }

    @NotNull
    public static final Animator.AnimatorListener doOnCancel(@NotNull Animator animator, @NotNull final Function1<? super Animator, Unit> function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnCancel$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    @NotNull
    public static final Animator.AnimatorListener doOnEnd(@NotNull Animator animator, @NotNull final Function1<? super Animator, Unit> function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnEnd$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    @NotNull
    public static final Animator.AnimatorPauseListener doOnPause(@NotNull Animator animator, @NotNull Function1<? super Animator, Unit> function1) {
        return addPauseListener$default(animator, null, function1, 1, null);
    }

    @NotNull
    public static final Animator.AnimatorListener doOnRepeat(@NotNull Animator animator, @NotNull final Function1<? super Animator, Unit> function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnRepeat$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    @NotNull
    public static final Animator.AnimatorPauseListener doOnResume(@NotNull Animator animator, @NotNull Function1<? super Animator, Unit> function1) {
        return addPauseListener$default(animator, function1, null, 2, null);
    }

    @NotNull
    public static final Animator.AnimatorListener doOnStart(@NotNull Animator animator, @NotNull final Function1<? super Animator, Unit> function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnStart$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }
}
