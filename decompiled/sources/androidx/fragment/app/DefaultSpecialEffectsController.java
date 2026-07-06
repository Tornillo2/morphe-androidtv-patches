package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* JADX INFO: renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass10 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnimationInfo extends SpecialEffectsInfo {

        @Nullable
        public FragmentAnim.AnimationOrAnimator mAnimation;
        public boolean mIsPop;
        public boolean mLoadedAnim;

        public AnimationInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
            this.mIsPop = z;
        }

        @Nullable
        public FragmentAnim.AnimationOrAnimator getAnimation(@NonNull Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().mFragment, getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = animationOrAnimatorLoadAnimation;
            this.mLoadedAnim = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SpecialEffectsInfo {

        @NonNull
        public final SpecialEffectsController.Operation mOperation;

        @NonNull
        public final CancellationSignal mSignal;

        public SpecialEffectsInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }

        @NonNull
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        @NonNull
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State stateFrom = SpecialEffectsController.Operation.State.from(this.mOperation.mFragment.mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            if (stateFrom == finalState) {
                return true;
            }
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            return (stateFrom == state || finalState == state) ? false : true;
        }
    }

    public DefaultSpecialEffectsController(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
    }

    public void applyContainerChanges(@NonNull SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.mFragment.mView);
    }

    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isTransitionGroup()) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0) {
                captureTransitioningViews(arrayList, childAt);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a4  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void executeOperations(@androidx.annotation.NonNull java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    public void findNamedViews(Map<String, View> map, @NonNull View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    public void retainMatchingViews(@NonNull ArrayMap<String, View> arrayMap, @NonNull Collection<String> collection) {
        Iterator it = ((ArrayMap.EntrySet) arrayMap.entrySet()).iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName((View) ((Map.Entry) it.next()).getValue()))) {
                it.remove();
            }
        }
    }

    public final void startAnimations(@NonNull List<AnimationInfo> list, @NonNull List<SpecialEffectsController.Operation> list2, boolean z, @NonNull Map<SpecialEffectsController.Operation, Boolean> map) {
        final SpecialEffectsController.Operation operation;
        final AnimationInfo animationInfo;
        final View view;
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z2 = false;
        for (final AnimationInfo animationInfo2 : list) {
            if (animationInfo2.isVisibilityUnchanged()) {
                animationInfo2.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = animationInfo2.getAnimation(context);
                if (animation == null) {
                    animationInfo2.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo2);
                    } else {
                        final SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
                        Fragment fragment = operation2.mFragment;
                        if (Boolean.TRUE.equals(map.get(operation2))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo2.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operation2);
                            }
                            final View view2 = fragment.mView;
                            container.startViewTransition(view2);
                            final ViewGroup viewGroup = container;
                            container = viewGroup;
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    viewGroup.endViewTransition(view2);
                                    if (z3) {
                                        operation2.getFinalState().applyState(view2);
                                    }
                                    animationInfo2.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation2 + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animator from operation " + operation2 + " has started.");
                            }
                            animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation2 + " has been canceled.");
                                    }
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
        }
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            AnimationInfo animationInfo3 = (AnimationInfo) obj;
            SpecialEffectsController.Operation operation3 = animationInfo3.getOperation();
            Fragment fragment2 = operation3.mFragment;
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo3.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo3.completeSpecialEffect();
            } else {
                View view3 = fragment2.mView;
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo3.getAnimation(context);
                animation2.getClass();
                Animation animation3 = animation2.animation;
                animation3.getClass();
                if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view3.startAnimation(animation3);
                    animationInfo3.completeSpecialEffect();
                    operation = operation3;
                    animationInfo = animationInfo3;
                    view = view3;
                } else {
                    container.startViewTransition(view3);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, container, view3);
                    operation = operation3;
                    animationInfo = animationInfo3;
                    view = view3;
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation4) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    container.endViewTransition(view);
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation + " has ended.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation4) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation + " has reached onAnimationStart.");
                            }
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation4) {
                        }
                    });
                    view.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation + " has started.");
                    }
                }
                CancellationSignal signal = animationInfo.getSignal();
                final AnimationInfo animationInfo4 = animationInfo;
                final SpecialEffectsController.Operation operation4 = operation;
                final View view4 = view;
                signal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view4.clearAnimation();
                        container.endViewTransition(view4);
                        animationInfo4.completeSpecialEffect();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Animation from operation " + operation4 + " has been cancelled.");
                        }
                    }
                });
            }
        }
    }

    @NonNull
    public final Map<SpecialEffectsController.Operation, Boolean> startTransitions(@NonNull List<TransitionInfo> list, @NonNull List<SpecialEffectsController.Operation> list2, boolean z, @Nullable SpecialEffectsController.Operation operation, @Nullable SpecialEffectsController.Operation operation2) {
        String str;
        View view;
        ArrayList<View> arrayList;
        String str2;
        ArrayList<View> arrayList2;
        ArrayList arrayList3;
        String str3;
        View view2;
        View view3;
        ArrayList<View> arrayList4;
        Object objMergeTransitionsTogether;
        Object objMergeTransitionsTogether2;
        Object obj;
        HashMap map;
        SpecialEffectsController.Operation operation3;
        View view4;
        ArrayList<View> arrayList5;
        ArrayMap arrayMap;
        ArrayList<View> arrayList6;
        HashMap map2;
        Rect rect;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList7;
        int i;
        View view5;
        final View view6;
        int i2;
        String strFindKeyForValue;
        int i3;
        final boolean z2 = z;
        final SpecialEffectsController.Operation operation4 = operation;
        final SpecialEffectsController.Operation operation5 = operation2;
        HashMap map3 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl == null) {
                    fragmentTransitionImpl = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().mFragment + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl == null) {
            for (TransitionInfo transitionInfo2 : list) {
                map3.put(transitionInfo2.getOperation(), Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            return map3;
        }
        View view7 = new View(getContainer().getContext());
        final Rect rect2 = new Rect();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayList<View> arrayList9 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator<TransitionInfo> it = list.iterator();
        Object obj2 = null;
        View view8 = null;
        boolean z3 = false;
        while (true) {
            str = "FragmentManager";
            view = view8;
            if (!it.hasNext()) {
                break;
            }
            TransitionInfo next = it.next();
            if (!next.hasSharedElementTransition() || operation4 == null || operation5 == null) {
                view4 = view7;
                arrayList5 = arrayList8;
                arrayMap = arrayMap2;
                arrayList6 = arrayList9;
                map2 = map3;
                rect = rect2;
                view8 = view;
            } else {
                Object objWrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(next.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation5.mFragment.getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation4.mFragment.getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation4.mFragment.getSharedElementTargetNames();
                HashMap map4 = map3;
                int i4 = 0;
                while (i4 < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i4));
                    ArrayList<String> arrayList10 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i4));
                    }
                    i4++;
                    sharedElementTargetNames = arrayList10;
                }
                ArrayList<String> sharedElementTargetNames2 = operation5.mFragment.getSharedElementTargetNames();
                if (z2) {
                    enterTransitionCallback = operation4.mFragment.getEnterTransitionCallback();
                    exitTransitionCallback = operation5.mFragment.getExitTransitionCallback();
                } else {
                    enterTransitionCallback = operation4.mFragment.getExitTransitionCallback();
                    exitTransitionCallback = operation5.mFragment.getEnterTransitionCallback();
                }
                int size = sharedElementSourceNames.size();
                SharedElementCallback sharedElementCallback = enterTransitionCallback;
                int i5 = 0;
                while (i5 < size) {
                    arrayMap2.put(sharedElementSourceNames.get(i5), sharedElementTargetNames2.get(i5));
                    i5++;
                    exitTransitionCallback = exitTransitionCallback;
                }
                SharedElementCallback sharedElementCallback2 = exitTransitionCallback;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    int i6 = 0;
                    for (int size2 = sharedElementTargetNames2.size(); i6 < size2; size2 = size2) {
                        String str4 = sharedElementTargetNames2.get(i6);
                        Log.v("FragmentManager", "Name: " + str4);
                        i6++;
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    int i7 = 0;
                    for (int size3 = sharedElementSourceNames.size(); i7 < size3; size3 = size3) {
                        String str5 = sharedElementSourceNames.get(i7);
                        Log.v("FragmentManager", "Name: " + str5);
                        i7++;
                    }
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                findNamedViews(arrayMap3, operation4.mFragment.mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (sharedElementCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing exit callback for operation " + operation4);
                    }
                    int size4 = sharedElementSourceNames.size() - 1;
                    while (size4 >= 0) {
                        String str6 = sharedElementSourceNames.get(size4);
                        View view9 = arrayMap3.get(str6);
                        if (view9 == null) {
                            arrayMap2.remove(str6);
                            i3 = size4;
                        } else {
                            i3 = size4;
                            if (!str6.equals(ViewCompat.getTransitionName(view9))) {
                                arrayMap2.put(ViewCompat.Api21Impl.getTransitionName(view9), (String) arrayMap2.remove(str6));
                            }
                        }
                        size4 = i3 - 1;
                    }
                } else {
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                findNamedViews(arrayMap4, operation5.mFragment.mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (sharedElementCallback2 != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        arrayList7 = sharedElementSourceNames;
                        Log.v("FragmentManager", "Executing enter callback for operation " + operation5);
                    } else {
                        arrayList7 = sharedElementSourceNames;
                    }
                    int size5 = sharedElementTargetNames2.size() - 1;
                    while (size5 >= 0) {
                        String str7 = sharedElementTargetNames2.get(size5);
                        View view10 = arrayMap4.get(str7);
                        if (view10 == null) {
                            String strFindKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str7);
                            if (strFindKeyForValue2 != null) {
                                arrayMap2.remove(strFindKeyForValue2);
                            }
                            i2 = size5;
                        } else {
                            i2 = size5;
                            if (!str7.equals(ViewCompat.getTransitionName(view10)) && (strFindKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str7)) != null) {
                                arrayMap2.put(strFindKeyForValue, ViewCompat.Api21Impl.getTransitionName(view10));
                            }
                        }
                        size5 = i2 - 1;
                    }
                } else {
                    arrayList7 = sharedElementSourceNames;
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList8.clear();
                    arrayList9.clear();
                    view4 = view7;
                    rect = rect2;
                    arrayList5 = arrayList8;
                    arrayMap = arrayMap2;
                    arrayList6 = arrayList9;
                    view8 = view;
                    map2 = map4;
                    obj2 = null;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation5.mFragment, operation4.mFragment, z2, arrayMap3, true);
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation5.mFragment, operation4.mFragment, z2, arrayMap4, false);
                        }
                    });
                    arrayList8.addAll(arrayMap3.values());
                    if (arrayList7.isEmpty()) {
                        i = 0;
                        view5 = view;
                    } else {
                        i = 0;
                        view5 = arrayMap3.get(arrayList7.get(0));
                        fragmentTransitionImpl.setEpicenter(objWrapTransitionInSet, view5);
                    }
                    arrayList9.addAll(arrayMap4.values());
                    if (!sharedElementTargetNames2.isEmpty() && (view6 = arrayMap4.get(sharedElementTargetNames2.get(i))) != null) {
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl.getBoundsOnScreen(view6, rect2);
                            }
                        });
                        z3 = true;
                    }
                    fragmentTransitionImpl.setSharedElementTargets(objWrapTransitionInSet, view7, arrayList8);
                    rect = rect2;
                    arrayList5 = arrayList8;
                    arrayMap = arrayMap2;
                    view4 = view7;
                    fragmentTransitionImpl.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList9);
                    arrayList6 = arrayList9;
                    Boolean bool = Boolean.TRUE;
                    map2 = map4;
                    map2.put(operation4, bool);
                    map2.put(operation5, bool);
                    view8 = view5;
                    obj2 = objWrapTransitionInSet;
                }
            }
            z2 = z;
            rect2 = rect;
            arrayList9 = arrayList6;
            map3 = map2;
            arrayMap2 = arrayMap;
            view7 = view4;
            arrayList8 = arrayList5;
        }
        ArrayList<View> arrayList11 = arrayList8;
        HashMap map5 = map3;
        Rect rect3 = rect2;
        ArrayList<View> arrayList12 = arrayList11;
        View view11 = view7;
        ArrayMap arrayMap5 = arrayMap2;
        ArrayList<View> arrayList13 = arrayList9;
        ArrayList arrayList14 = new ArrayList();
        Object obj3 = null;
        Object obj4 = null;
        for (TransitionInfo transitionInfo3 : list) {
            if (transitionInfo3.isVisibilityUnchanged()) {
                map5.put(transitionInfo3.getOperation(), Boolean.FALSE);
                transitionInfo3.completeSpecialEffect();
            } else {
                Object objCloneTransition = fragmentTransitionImpl.cloneTransition(transitionInfo3.getTransition());
                SpecialEffectsController.Operation operation6 = transitionInfo3.getOperation();
                boolean z4 = obj2 != null && (operation6 == operation4 || operation6 == operation5);
                if (objCloneTransition == null) {
                    if (!z4) {
                        map5.put(operation6, Boolean.FALSE);
                        transitionInfo3.completeSpecialEffect();
                    }
                    View view12 = view;
                    view2 = view11;
                    view3 = view12;
                    str3 = str;
                    arrayList4 = arrayList13;
                    arrayList2 = arrayList12;
                    map = map5;
                    arrayList3 = arrayList14;
                } else {
                    HashMap map6 = map5;
                    final ArrayList<View> arrayList15 = new ArrayList<>();
                    ArrayList arrayList16 = arrayList14;
                    captureTransitioningViews(arrayList15, operation6.mFragment.mView);
                    if (z4) {
                        if (operation6 == operation4) {
                            arrayList15.removeAll(arrayList12);
                        } else {
                            arrayList15.removeAll(arrayList13);
                        }
                    }
                    if (arrayList15.isEmpty()) {
                        fragmentTransitionImpl.addTarget(objCloneTransition, view11);
                        View view13 = view;
                        view2 = view11;
                        view3 = view13;
                        str3 = str;
                        arrayList4 = arrayList13;
                        arrayList2 = arrayList12;
                        objMergeTransitionsTogether2 = obj3;
                        objMergeTransitionsTogether = obj4;
                        operation3 = operation6;
                        arrayList3 = arrayList16;
                        obj = objCloneTransition;
                        map = map6;
                    } else {
                        fragmentTransitionImpl.addTargets(objCloneTransition, arrayList15);
                        arrayList2 = arrayList12;
                        String str8 = str;
                        arrayList3 = arrayList16;
                        str3 = str8;
                        View view14 = view;
                        view2 = view11;
                        view3 = view14;
                        arrayList4 = arrayList13;
                        objMergeTransitionsTogether = obj4;
                        objMergeTransitionsTogether2 = obj3;
                        obj = objCloneTransition;
                        map = map6;
                        fragmentTransitionImpl.scheduleRemoveTargets(obj, objCloneTransition, arrayList15, null, null, null, null);
                        if (operation6.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation6;
                            list2.remove(operation3);
                            ArrayList<View> arrayList17 = new ArrayList<>(arrayList15);
                            arrayList17.remove(operation3.mFragment.mView);
                            fragmentTransitionImpl.scheduleHideFragmentView(obj, operation3.mFragment.mView, arrayList17);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList15, 4);
                                }
                            });
                        } else {
                            operation3 = operation6;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList3.addAll(arrayList15);
                        if (z3) {
                            fragmentTransitionImpl.setEpicenter(obj, rect3);
                        }
                    } else {
                        fragmentTransitionImpl.setEpicenter(obj, view3);
                    }
                    map.put(operation3, Boolean.TRUE);
                    if (transitionInfo3.isOverlapAllowed()) {
                        objMergeTransitionsTogether2 = fragmentTransitionImpl.mergeTransitionsTogether(objMergeTransitionsTogether2, obj, null);
                    } else {
                        objMergeTransitionsTogether = fragmentTransitionImpl.mergeTransitionsTogether(objMergeTransitionsTogether, obj, null);
                    }
                    obj3 = objMergeTransitionsTogether2;
                    obj4 = objMergeTransitionsTogether;
                }
                View view15 = view2;
                view = view3;
                view11 = view15;
                operation4 = operation;
                operation5 = operation2;
                map5 = map;
                arrayList14 = arrayList3;
                arrayList13 = arrayList4;
                str = str3;
                arrayList12 = arrayList2;
            }
        }
        String str9 = str;
        ArrayList<View> arrayList18 = arrayList13;
        ArrayList<View> arrayList19 = arrayList12;
        HashMap map7 = map5;
        ArrayList arrayList20 = arrayList14;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(obj3, obj4, obj2);
        if (objMergeTransitionsInSequence == null) {
            return map7;
        }
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged()) {
                Object transition = transitionInfo4.getTransition();
                final SpecialEffectsController.Operation operation7 = transitionInfo4.getOperation();
                boolean z5 = obj2 != null && (operation7 == operation || operation7 == operation2);
                if (transition == null && !z5) {
                    str2 = str9;
                } else if (ViewCompat.isLaidOut(getContainer())) {
                    str2 = str9;
                    fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo4.getOperation().mFragment, objMergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                        @Override // java.lang.Runnable
                        public void run() {
                            transitionInfo4.completeSpecialEffect();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Transition for operation " + operation7 + "has completed");
                            }
                        }
                    });
                } else {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        str2 = str9;
                        Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation7);
                    } else {
                        str2 = str9;
                    }
                    transitionInfo4.completeSpecialEffect();
                }
                str9 = str2;
            }
        }
        String str10 = str9;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return map7;
        }
        FragmentTransition.setViewVisibility(arrayList20, 4);
        ArrayList<String> arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList18);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str10, ">>>>> Beginning transition <<<<<");
            Log.v(str10, ">>>>> SharedElementFirstOutViews <<<<<");
            int size6 = arrayList19.size();
            int i8 = 0;
            while (i8 < size6) {
                View view16 = arrayList19.get(i8);
                i8++;
                View view17 = view16;
                Log.v(str10, "View: " + view17 + " Name: " + ViewCompat.Api21Impl.getTransitionName(view17));
            }
            arrayList = arrayList19;
            Log.v(str10, ">>>>> SharedElementLastInViews <<<<<");
            int size7 = arrayList18.size();
            int i9 = 0;
            while (i9 < size7) {
                View view18 = arrayList18.get(i9);
                i9++;
                View view19 = view18;
                Log.v(str10, "View: " + view19 + " Name: " + ViewCompat.Api21Impl.getTransitionName(view19));
            }
        } else {
            arrayList = arrayList19;
        }
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList, arrayList18, arrayListPrepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList20, 0);
        fragmentTransitionImpl.swapSharedElementTargets(obj2, arrayList, arrayList18);
        return map7;
    }

    public final void syncAnimations(@NonNull List<SpecialEffectsController.Operation> list) {
        Fragment fragment = list.get(list.size() - 1).mFragment;
        Iterator<SpecialEffectsController.Operation> it = list.iterator();
        while (it.hasNext()) {
            Fragment.AnimationInfo animationInfo = it.next().mFragment.mAnimationInfo;
            Fragment.AnimationInfo animationInfo2 = fragment.mAnimationInfo;
            animationInfo.mEnterAnim = animationInfo2.mEnterAnim;
            animationInfo.mExitAnim = animationInfo2.mExitAnim;
            animationInfo.mPopEnterAnim = animationInfo2.mPopEnterAnim;
            animationInfo.mPopExitAnim = animationInfo2.mPopExitAnim;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TransitionInfo extends SpecialEffectsInfo {
        public final boolean mOverlapAllowed;

        @Nullable
        public final Object mSharedElementTransition;

        @Nullable
        public final Object mTransition;

        public TransitionInfo(@NonNull SpecialEffectsController.Operation operation, @NonNull CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                this.mTransition = z ? operation.mFragment.getReenterTransition() : operation.mFragment.getEnterTransition();
                this.mOverlapAllowed = z ? operation.mFragment.getAllowReturnTransitionOverlap() : operation.mFragment.getAllowEnterTransitionOverlap();
            } else {
                this.mTransition = z ? operation.mFragment.getReturnTransition() : operation.mFragment.getExitTransition();
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.mFragment.getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.mFragment.getSharedElementEnterTransition();
            }
        }

        @Nullable
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().mFragment + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        @Nullable
        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        @Nullable
        public Object getTransition() {
            return this.mTransition;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        @Nullable
        public final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().mFragment + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
