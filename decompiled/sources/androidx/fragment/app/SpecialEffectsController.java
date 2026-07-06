package androidx.fragment.app;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.text.AlphabetConverter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class SpecialEffectsController {
    public final ViewGroup mContainer;
    public final ArrayList<Operation> mPendingOperations = new ArrayList<>();
    public final ArrayList<Operation> mRunningOperations = new ArrayList<>();
    public boolean mOperationDirectionIsPop = false;
    public boolean mIsContainerPostponed = false;

    /* JADX INFO: renamed from: androidx.fragment.app.SpecialEffectsController$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact;
        public static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact = iArr;
            try {
                iArr[Operation.LifecycleImpact.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[Operation.LifecycleImpact.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Operation.State.values().length];
            $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = iArr2;
            try {
                iArr2[Operation.State.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[Operation.State.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FragmentStateManagerOperation extends Operation {

        @NonNull
        public final FragmentStateManager mFragmentStateManager;

        public FragmentStateManagerOperation(@NonNull Operation.State state, @NonNull Operation.LifecycleImpact lifecycleImpact, @NonNull FragmentStateManager fragmentStateManager, @NonNull CancellationSignal cancellationSignal) {
            super(state, lifecycleImpact, fragmentStateManager.getFragment(), cancellationSignal);
            this.mFragmentStateManager = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void onStart() {
            if (getLifecycleImpact() != Operation.LifecycleImpact.ADDING) {
                if (getLifecycleImpact() == Operation.LifecycleImpact.REMOVING) {
                    Fragment fragment = this.mFragmentStateManager.getFragment();
                    View viewRequireView = fragment.requireView();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Clearing focus " + viewRequireView.findFocus() + " on view " + viewRequireView + " for Fragment " + fragment);
                    }
                    viewRequireView.clearFocus();
                    return;
                }
                return;
            }
            Fragment fragment2 = this.mFragmentStateManager.getFragment();
            View viewFindFocus = fragment2.mView.findFocus();
            if (viewFindFocus != null) {
                fragment2.setFocusedView(viewFindFocus);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + fragment2);
                }
            }
            View viewRequireView2 = this.mFragment.requireView();
            if (viewRequireView2.getParent() == null) {
                this.mFragmentStateManager.addViewToContainer();
                viewRequireView2.setAlpha(0.0f);
            }
            if (viewRequireView2.getAlpha() == 0.0f && viewRequireView2.getVisibility() == 0) {
                viewRequireView2.setVisibility(4);
            }
            viewRequireView2.setAlpha(fragment2.getPostOnViewCreatedAlpha());
        }
    }

    public SpecialEffectsController(@NonNull ViewGroup viewGroup) {
        this.mContainer = viewGroup;
    }

    @NonNull
    public static SpecialEffectsController getOrCreateController(@NonNull ViewGroup viewGroup, @NonNull FragmentManager fragmentManager) {
        return getOrCreateController(viewGroup, fragmentManager.getSpecialEffectsControllerFactory());
    }

    public final void enqueue(@NonNull Operation.State state, @NonNull Operation.LifecycleImpact lifecycleImpact, @NonNull FragmentStateManager fragmentStateManager) {
        synchronized (this.mPendingOperations) {
            try {
                CancellationSignal cancellationSignal = new CancellationSignal();
                Operation operationFindPendingOperation = findPendingOperation(fragmentStateManager.getFragment());
                if (operationFindPendingOperation != null) {
                    operationFindPendingOperation.mergeWith(state, lifecycleImpact);
                    return;
                }
                final FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager, cancellationSignal);
                this.mPendingOperations.add(fragmentStateManagerOperation);
                fragmentStateManagerOperation.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SpecialEffectsController.this.mPendingOperations.contains(fragmentStateManagerOperation)) {
                            fragmentStateManagerOperation.getFinalState().applyState(fragmentStateManagerOperation.mFragment.mView);
                        }
                    }
                });
                fragmentStateManagerOperation.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SpecialEffectsController.this.mPendingOperations.remove(fragmentStateManagerOperation);
                        SpecialEffectsController.this.mRunningOperations.remove(fragmentStateManagerOperation);
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void enqueueAdd(@NonNull Operation.State state, @NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(state, Operation.LifecycleImpact.ADDING, fragmentStateManager);
    }

    public void enqueueHide(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.GONE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    public void enqueueRemove(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, fragmentStateManager);
    }

    public void enqueueShow(@NonNull FragmentStateManager fragmentStateManager) {
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    public abstract void executeOperations(@NonNull List<Operation> list, boolean z);

    public void executePendingOperations() {
        if (this.mIsContainerPostponed) {
            return;
        }
        if (!ViewCompat.isAttachedToWindow(this.mContainer)) {
            forceCompleteAllOperations();
            this.mOperationDirectionIsPop = false;
            return;
        }
        synchronized (this.mPendingOperations) {
            try {
                if (!this.mPendingOperations.isEmpty()) {
                    ArrayList arrayList = new ArrayList(this.mRunningOperations);
                    this.mRunningOperations.clear();
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList.get(i);
                        i++;
                        Operation operation = (Operation) obj;
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + operation);
                        }
                        operation.cancel();
                        if (!operation.mIsComplete) {
                            this.mRunningOperations.add(operation);
                        }
                    }
                    updateFinalState();
                    ArrayList arrayList2 = new ArrayList(this.mPendingOperations);
                    this.mPendingOperations.clear();
                    this.mRunningOperations.addAll(arrayList2);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");
                    }
                    int size2 = arrayList2.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        Object obj2 = arrayList2.get(i2);
                        i2++;
                        ((Operation) obj2).onStart();
                    }
                    executeOperations(arrayList2, this.mOperationDirectionIsPop);
                    this.mOperationDirectionIsPop = false;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Nullable
    public final Operation findPendingOperation(@NonNull Fragment fragment) {
        ArrayList<Operation> arrayList = this.mPendingOperations;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Operation operation = arrayList.get(i);
            i++;
            Operation operation2 = operation;
            if (operation2.mFragment.equals(fragment) && !operation2.mIsCanceled) {
                return operation2;
            }
        }
        return null;
    }

    @Nullable
    public final Operation findRunningOperation(@NonNull Fragment fragment) {
        ArrayList<Operation> arrayList = this.mRunningOperations;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Operation operation = arrayList.get(i);
            i++;
            Operation operation2 = operation;
            if (operation2.mFragment.equals(fragment) && !operation2.mIsCanceled) {
                return operation2;
            }
        }
        return null;
    }

    public void forceCompleteAllOperations() {
        String str;
        String str2;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        boolean zIsAttachedToWindow = ViewCompat.isAttachedToWindow(this.mContainer);
        synchronized (this.mPendingOperations) {
            try {
                updateFinalState();
                ArrayList<Operation> arrayList = this.mPendingOperations;
                int size = arrayList.size();
                int i = 0;
                int i2 = 0;
                while (i2 < size) {
                    Operation operation = arrayList.get(i2);
                    i2++;
                    operation.onStart();
                }
                ArrayList arrayList2 = new ArrayList(this.mRunningOperations);
                int size2 = arrayList2.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj = arrayList2.get(i3);
                    i3++;
                    Operation operation2 = (Operation) obj;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: ");
                        if (zIsAttachedToWindow) {
                            str2 = "";
                        } else {
                            str2 = "Container " + this.mContainer + " is not attached to window. ";
                        }
                        sb.append(str2);
                        sb.append("Cancelling running operation ");
                        sb.append(operation2);
                        Log.v("FragmentManager", sb.toString());
                    }
                    operation2.cancel();
                }
                ArrayList arrayList3 = new ArrayList(this.mPendingOperations);
                int size3 = arrayList3.size();
                while (i < size3) {
                    Object obj2 = arrayList3.get(i);
                    i++;
                    Operation operation3 = (Operation) obj2;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: ");
                        if (zIsAttachedToWindow) {
                            str = "";
                        } else {
                            str = "Container " + this.mContainer + " is not attached to window. ";
                        }
                        sb2.append(str);
                        sb2.append("Cancelling pending operation ");
                        sb2.append(operation3);
                        Log.v("FragmentManager", sb2.toString());
                    }
                    operation3.cancel();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void forcePostponedExecutePendingOperations() {
        if (this.mIsContainerPostponed) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
            }
            this.mIsContainerPostponed = false;
            executePendingOperations();
        }
    }

    @Nullable
    public Operation.LifecycleImpact getAwaitingCompletionLifecycleImpact(@NonNull FragmentStateManager fragmentStateManager) {
        Operation operationFindPendingOperation = findPendingOperation(fragmentStateManager.getFragment());
        Operation.LifecycleImpact lifecycleImpact = operationFindPendingOperation != null ? operationFindPendingOperation.getLifecycleImpact() : null;
        Operation operationFindRunningOperation = findRunningOperation(fragmentStateManager.getFragment());
        return (operationFindRunningOperation == null || !(lifecycleImpact == null || lifecycleImpact == Operation.LifecycleImpact.NONE)) ? lifecycleImpact : operationFindRunningOperation.getLifecycleImpact();
    }

    @NonNull
    public ViewGroup getContainer() {
        return this.mContainer;
    }

    public void markPostponedState() {
        synchronized (this.mPendingOperations) {
            try {
                updateFinalState();
                this.mIsContainerPostponed = false;
                int size = this.mPendingOperations.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    Operation operation = this.mPendingOperations.get(size);
                    Operation.State stateFrom = Operation.State.from(operation.mFragment.mView);
                    Operation.State finalState = operation.getFinalState();
                    Operation.State state = Operation.State.VISIBLE;
                    if (finalState == state && stateFrom != state) {
                        this.mIsContainerPostponed = operation.mFragment.isPostponed();
                        break;
                    }
                    size--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void updateFinalState() {
        ArrayList<Operation> arrayList = this.mPendingOperations;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Operation operation = arrayList.get(i);
            i++;
            Operation operation2 = operation;
            if (operation2.getLifecycleImpact() == Operation.LifecycleImpact.ADDING) {
                operation2.mergeWith(Operation.State.from(operation2.mFragment.requireView().getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    public void updateOperationDirection(boolean z) {
        this.mOperationDirectionIsPop = z;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Operation {

        @NonNull
        public State mFinalState;

        @NonNull
        public final Fragment mFragment;

        @NonNull
        public LifecycleImpact mLifecycleImpact;

        @NonNull
        public final List<Runnable> mCompletionListeners = new ArrayList();

        @NonNull
        public final HashSet<CancellationSignal> mSpecialEffectsSignals = new HashSet<>();
        public boolean mIsCanceled = false;
        public boolean mIsComplete = false;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        public Operation(@NonNull State state, @NonNull LifecycleImpact lifecycleImpact, @NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            this.mFinalState = state;
            this.mLifecycleImpact = lifecycleImpact;
            this.mFragment = fragment;
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.cancel();
                }
            });
        }

        public final void addCompletionListener(@NonNull Runnable runnable) {
            this.mCompletionListeners.add(runnable);
        }

        public final void cancel() {
            if (this.mIsCanceled) {
                return;
            }
            this.mIsCanceled = true;
            if (this.mSpecialEffectsSignals.isEmpty()) {
                complete();
                return;
            }
            ArrayList arrayList = new ArrayList(this.mSpecialEffectsSignals);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((CancellationSignal) obj).cancel();
            }
        }

        @CallSuper
        public void complete() {
            if (this.mIsComplete) {
                return;
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.mIsComplete = true;
            Iterator<Runnable> it = this.mCompletionListeners.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        public final void completeSpecialEffect(@NonNull CancellationSignal cancellationSignal) {
            if (this.mSpecialEffectsSignals.remove(cancellationSignal) && this.mSpecialEffectsSignals.isEmpty()) {
                complete();
            }
        }

        @NonNull
        public State getFinalState() {
            return this.mFinalState;
        }

        @NonNull
        public final Fragment getFragment() {
            return this.mFragment;
        }

        @NonNull
        public LifecycleImpact getLifecycleImpact() {
            return this.mLifecycleImpact;
        }

        public final boolean isCanceled() {
            return this.mIsCanceled;
        }

        public final boolean isComplete() {
            return this.mIsComplete;
        }

        public final void markStartedSpecialEffect(@NonNull CancellationSignal cancellationSignal) {
            onStart();
            this.mSpecialEffectsSignals.add(cancellationSignal);
        }

        public final void mergeWith(@NonNull State state, @NonNull LifecycleImpact lifecycleImpact) {
            int i = AnonymousClass3.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact[lifecycleImpact.ordinal()];
            if (i == 1) {
                if (this.mFinalState == State.REMOVED) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.mLifecycleImpact + " to ADDING.");
                    }
                    this.mFinalState = State.VISIBLE;
                    this.mLifecycleImpact = LifecycleImpact.ADDING;
                    return;
                }
                return;
            }
            if (i == 2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> REMOVED. mLifecycleImpact  = " + this.mLifecycleImpact + " to REMOVING.");
                }
                this.mFinalState = State.REMOVED;
                this.mLifecycleImpact = LifecycleImpact.REMOVING;
                return;
            }
            if (i == 3 && this.mFinalState != State.REMOVED) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + AlphabetConverter.ARROW + state + ". ");
                }
                this.mFinalState = state;
            }
        }

        @NonNull
        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.mFinalState + "} {mLifecycleImpact = " + this.mLifecycleImpact + "} {mFragment = " + this.mFragment + "}";
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            @NonNull
            public static State from(@NonNull View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : from(view.getVisibility());
            }

            public void applyState(@NonNull View view) {
                int i = AnonymousClass3.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[ordinal()];
                if (i == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                    return;
                }
                if (i == 3) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                    return;
                }
                if (i != 4) {
                    return;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                }
                view.setVisibility(4);
            }

            @NonNull
            public static State from(int i) {
                if (i == 0) {
                    return VISIBLE;
                }
                if (i == 4) {
                    return INVISIBLE;
                }
                if (i == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unknown visibility ", i));
            }
        }

        public void onStart() {
        }
    }

    @NonNull
    public static SpecialEffectsController getOrCreateController(@NonNull ViewGroup viewGroup, @NonNull SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController specialEffectsControllerCreateController = specialEffectsControllerFactory.createController(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, specialEffectsControllerCreateController);
        return specialEffectsControllerCreateController;
    }
}
