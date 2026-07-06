package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MenuHostHelper {
    public final Runnable mOnInvalidateMenuCallback;
    public final CopyOnWriteArrayList<MenuProvider> mMenuProviders = new CopyOnWriteArrayList<>();
    public final Map<MenuProvider, LifecycleContainer> mProviderToLifecycleContainers = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LifecycleContainer {
        public final Lifecycle mLifecycle;
        public LifecycleEventObserver mObserver;

        public LifecycleContainer(@NonNull Lifecycle lifecycle, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle = lifecycle;
            this.mObserver = lifecycleEventObserver;
            lifecycle.addObserver(lifecycleEventObserver);
        }

        public void clearObservers() {
            this.mLifecycle.removeObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public static void $r8$lambda$L4bp1I_Mq82BF4CTDbjIos9_dFE(MenuHostHelper menuHostHelper, Lifecycle.State state, MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        menuHostHelper.getClass();
        if (event == Lifecycle.Event.upTo(state)) {
            menuHostHelper.addMenuProvider(menuProvider);
            return;
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            menuHostHelper.removeMenuProvider(menuProvider);
        } else if (event == Lifecycle.Event.Companion.downFrom(state)) {
            menuHostHelper.mMenuProviders.remove(menuProvider);
            menuHostHelper.mOnInvalidateMenuCallback.run();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$zr_Wn7n1nYo-eZCJzTn8vTFGOUc, reason: not valid java name */
    public static /* synthetic */ void m50$r8$lambda$zr_Wn7n1nYoeZCJzTn8vTFGOUc(MenuHostHelper menuHostHelper, MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        menuHostHelper.getClass();
        if (event == Lifecycle.Event.ON_DESTROY) {
            menuHostHelper.removeMenuProvider(menuProvider);
        }
    }

    public MenuHostHelper(@NonNull Runnable runnable) {
        this.mOnInvalidateMenuCallback = runnable;
    }

    public void addMenuProvider(@NonNull MenuProvider menuProvider) {
        this.mMenuProviders.add(menuProvider);
        this.mOnInvalidateMenuCallback.run();
    }

    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        Iterator<MenuProvider> it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            it.next().onCreateMenu(menu, menuInflater);
        }
    }

    public void onMenuClosed(@NonNull Menu menu) {
        Iterator<MenuProvider> it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            it.next().onMenuClosed(menu);
        }
    }

    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        Iterator<MenuProvider> it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            if (it.next().onMenuItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void onPrepareMenu(@NonNull Menu menu) {
        Iterator<MenuProvider> it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            it.next().onPrepareMenu(menu);
        }
    }

    public void removeMenuProvider(@NonNull MenuProvider menuProvider) {
        this.mMenuProviders.remove(menuProvider);
        LifecycleContainer lifecycleContainerRemove = this.mProviderToLifecycleContainers.remove(menuProvider);
        if (lifecycleContainerRemove != null) {
            lifecycleContainerRemove.clearObservers();
        }
        this.mOnInvalidateMenuCallback.run();
    }

    public void addMenuProvider(@NonNull final MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner) {
        addMenuProvider(menuProvider);
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        LifecycleContainer lifecycleContainerRemove = this.mProviderToLifecycleContainers.remove(menuProvider);
        if (lifecycleContainerRemove != null) {
            lifecycleContainerRemove.clearObservers();
        }
        this.mProviderToLifecycleContainers.put(menuProvider, new LifecycleContainer(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.MenuHostHelper$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                MenuHostHelper.m50$r8$lambda$zr_Wn7n1nYoeZCJzTn8vTFGOUc(this.f$0, menuProvider, lifecycleOwner2, event);
            }
        }));
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(@NonNull final MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner, @NonNull final Lifecycle.State state) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        LifecycleContainer lifecycleContainerRemove = this.mProviderToLifecycleContainers.remove(menuProvider);
        if (lifecycleContainerRemove != null) {
            lifecycleContainerRemove.clearObservers();
        }
        this.mProviderToLifecycleContainers.put(menuProvider, new LifecycleContainer(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.MenuHostHelper$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                MenuHostHelper.$r8$lambda$L4bp1I_Mq82BF4CTDbjIos9_dFE(this.f$0, state, menuProvider, lifecycleOwner2, event);
            }
        }));
    }
}
