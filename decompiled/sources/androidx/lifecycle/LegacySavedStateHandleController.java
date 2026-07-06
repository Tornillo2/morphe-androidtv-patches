package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LegacySavedStateHandleController {

    @NotNull
    public static final LegacySavedStateHandleController INSTANCE = new LegacySavedStateHandleController();

    @NotNull
    public static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void onRecreated(@NotNull SavedStateRegistryOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            if (!(owner instanceof ViewModelStoreOwner)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
            }
            ViewModelStore viewModelStore = ((ViewModelStoreOwner) owner).getViewModelStore();
            SavedStateRegistry savedStateRegistry = owner.getSavedStateRegistry();
            Iterator it = ((HashSet) viewModelStore.keys()).iterator();
            while (it.hasNext()) {
                ViewModel viewModel = viewModelStore.get((String) it.next());
                Intrinsics.checkNotNull(viewModel);
                LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, savedStateRegistry, owner.getLifecycle());
            }
            if (((HashSet) viewModelStore.keys()).isEmpty()) {
                return;
            }
            savedStateRegistry.runOnNextRecreation(OnRecreation.class);
        }
    }

    @JvmStatic
    public static final void attachHandleIfNeeded(@NotNull ViewModel viewModel, @NotNull SavedStateRegistry registry, @NotNull Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModel.getTag("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.isAttached) {
            return;
        }
        savedStateHandleController.attachToLifecycle(registry, lifecycle);
        INSTANCE.tryToAddRecreator(registry, lifecycle);
    }

    @JvmStatic
    @NotNull
    public static final SavedStateHandleController create(@NotNull SavedStateRegistry registry, @NotNull Lifecycle lifecycle, @Nullable String str, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNull(str);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, SavedStateHandle.Companion.createHandle(registry.consumeRestoredStateForKey(str), bundle));
        savedStateHandleController.attachToLifecycle(registry, lifecycle);
        INSTANCE.tryToAddRecreator(registry, lifecycle);
        return savedStateHandleController;
    }

    public final void tryToAddRecreator(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState == Lifecycle.State.INITIALIZED || currentState.isAtLeast(Lifecycle.State.STARTED)) {
            savedStateRegistry.runOnNextRecreation(OnRecreation.class);
        } else {
            lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.lifecycle.LegacySavedStateHandleController.tryToAddRecreator.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
                    Intrinsics.checkNotNullParameter(source, "source");
                    Intrinsics.checkNotNullParameter(event, "event");
                    if (event == Lifecycle.Event.ON_START) {
                        lifecycle.removeObserver(this);
                        savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    }
                }
            });
        }
    }
}
