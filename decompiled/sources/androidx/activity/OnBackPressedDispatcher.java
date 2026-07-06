package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.DoNotInline;
import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nOnBackPressedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,317:1\n1747#2,3:318\n533#2,6:321\n*S KotlinDebug\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n*L\n194#1:318,3\n209#1:321,6\n*E\n"})
public final class OnBackPressedDispatcher {
    public boolean backInvokedCallbackRegistered;

    @Nullable
    public Function0<Unit> enabledChangedCallback;

    @Nullable
    public final Runnable fallbackOnBackPressed;

    @Nullable
    public OnBackInvokedDispatcher invokedDispatcher;

    @Nullable
    public OnBackInvokedCallback onBackInvokedCallback;

    @NotNull
    public final ArrayDeque<OnBackPressedCallback> onBackPressedCallbacks;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static final class Api33Impl {

        @NotNull
        public static final Api33Impl INSTANCE = new Api33Impl();

        public static final void createOnBackInvokedCallback$lambda$0(Function0 onBackInvoked) {
            Intrinsics.checkNotNullParameter(onBackInvoked, "$onBackInvoked");
            onBackInvoked.invoke();
        }

        @DoNotInline
        @NotNull
        public final OnBackInvokedCallback createOnBackInvokedCallback(@NotNull final Function0<Unit> onBackInvoked) {
            Intrinsics.checkNotNullParameter(onBackInvoked, "onBackInvoked");
            return new OnBackInvokedCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    OnBackPressedDispatcher.Api33Impl.createOnBackInvokedCallback$lambda$0(onBackInvoked);
                }
            };
        }

        @DoNotInline
        public final void registerOnBackInvokedCallback(@NotNull Object dispatcher, int i, @NotNull Object callback) {
            Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
            Intrinsics.checkNotNullParameter(callback, "callback");
            ((OnBackInvokedDispatcher) dispatcher).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) callback);
        }

        @DoNotInline
        public final void unregisterOnBackInvokedCallback(@NotNull Object dispatcher, @NotNull Object callback) {
            Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
            Intrinsics.checkNotNullParameter(callback, "callback");
            ((OnBackInvokedDispatcher) dispatcher).unregisterOnBackInvokedCallback((OnBackInvokedCallback) callback);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, Cancellable {

        @Nullable
        public Cancellable currentCancellable;

        @NotNull
        public final Lifecycle lifecycle;

        @NotNull
        public final OnBackPressedCallback onBackPressedCallback;
        public final /* synthetic */ OnBackPressedDispatcher this$0;

        public LifecycleOnBackPressedCancellable(@NotNull OnBackPressedDispatcher onBackPressedDispatcher, @NotNull Lifecycle lifecycle, OnBackPressedCallback onBackPressedCallback) {
            Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
            Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
            this.this$0 = onBackPressedDispatcher;
            this.lifecycle = lifecycle;
            this.onBackPressedCallback = onBackPressedCallback;
            lifecycle.addObserver(this);
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.lifecycle.removeObserver(this);
            this.onBackPressedCallback.removeCancellable(this);
            Cancellable cancellable = this.currentCancellable;
            if (cancellable != null) {
                cancellable.cancel();
            }
            this.currentCancellable = null;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(event, "event");
            if (event == Lifecycle.Event.ON_START) {
                this.currentCancellable = this.this$0.addCancellableCallback$activity_release(this.onBackPressedCallback);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                Cancellable cancellable = this.currentCancellable;
                if (cancellable != null) {
                    cancellable.cancel();
                }
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class OnBackPressedCancellable implements Cancellable {

        @NotNull
        public final OnBackPressedCallback onBackPressedCallback;
        public final /* synthetic */ OnBackPressedDispatcher this$0;

        public OnBackPressedCancellable(@NotNull OnBackPressedDispatcher onBackPressedDispatcher, OnBackPressedCallback onBackPressedCallback) {
            Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
            this.this$0 = onBackPressedDispatcher;
            this.onBackPressedCallback = onBackPressedCallback;
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.this$0.onBackPressedCallbacks.remove(this.onBackPressedCallback);
            this.onBackPressedCallback.removeCancellable(this);
            if (Build.VERSION.SDK_INT >= 33) {
                this.onBackPressedCallback.enabledChangedCallback = null;
                this.this$0.updateBackInvokedCallbackState$activity_release();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public OnBackPressedDispatcher() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @MainThread
    public final void addCallback(@NotNull OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
        addCancellableCallback$activity_release(onBackPressedCallback);
    }

    @MainThread
    @NotNull
    public final Cancellable addCancellableCallback$activity_release(@NotNull OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
        this.onBackPressedCallbacks.addLast(onBackPressedCallback);
        OnBackPressedCancellable onBackPressedCancellable = new OnBackPressedCancellable(this, onBackPressedCallback);
        onBackPressedCallback.addCancellable(onBackPressedCancellable);
        if (Build.VERSION.SDK_INT >= 33) {
            updateBackInvokedCallbackState$activity_release();
            onBackPressedCallback.enabledChangedCallback = this.enabledChangedCallback;
        }
        return onBackPressedCancellable;
    }

    @MainThread
    public final boolean hasEnabledCallbacks() {
        ArrayDeque<OnBackPressedCallback> arrayDeque = this.onBackPressedCallbacks;
        if (ComponentActivity$$ExternalSyntheticNonNull0.m(arrayDeque) && arrayDeque.isEmpty()) {
            return false;
        }
        Iterator<OnBackPressedCallback> it = arrayDeque.iterator();
        while (it.hasNext()) {
            if (it.next().isEnabled) {
                return true;
            }
        }
        return false;
    }

    @MainThread
    public final void onBackPressed() {
        OnBackPressedCallback onBackPressedCallbackPrevious;
        ArrayDeque<OnBackPressedCallback> arrayDeque = this.onBackPressedCallbacks;
        ListIterator<OnBackPressedCallback> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                onBackPressedCallbackPrevious = null;
                break;
            } else {
                onBackPressedCallbackPrevious = listIterator.previous();
                if (onBackPressedCallbackPrevious.isEnabled) {
                    break;
                }
            }
        }
        OnBackPressedCallback onBackPressedCallback = onBackPressedCallbackPrevious;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.handleOnBackPressed();
            return;
        }
        Runnable runnable = this.fallbackOnBackPressed;
        if (runnable != null) {
            runnable.run();
        }
    }

    @RequiresApi(33)
    public final void setOnBackInvokedDispatcher(@NotNull OnBackInvokedDispatcher invoker) {
        Intrinsics.checkNotNullParameter(invoker, "invoker");
        this.invokedDispatcher = invoker;
        updateBackInvokedCallbackState$activity_release();
    }

    @RequiresApi(33)
    public final void updateBackInvokedCallbackState$activity_release() {
        boolean zHasEnabledCallbacks = hasEnabledCallbacks();
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.invokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback = this.onBackInvokedCallback;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        if (zHasEnabledCallbacks && !this.backInvokedCallbackRegistered) {
            Api33Impl.INSTANCE.registerOnBackInvokedCallback(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.backInvokedCallbackRegistered = true;
        } else {
            if (zHasEnabledCallbacks || !this.backInvokedCallbackRegistered) {
                return;
            }
            Api33Impl.INSTANCE.unregisterOnBackInvokedCallback(onBackInvokedDispatcher, onBackInvokedCallback);
            this.backInvokedCallbackRegistered = false;
        }
    }

    @JvmOverloads
    public OnBackPressedDispatcher(@Nullable Runnable runnable) {
        this.fallbackOnBackPressed = runnable;
        this.onBackPressedCallbacks = new ArrayDeque<>();
        if (Build.VERSION.SDK_INT >= 33) {
            this.enabledChangedCallback = new Function0<Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OnBackPressedDispatcher.this.updateBackInvokedCallbackState$activity_release();
                }
            };
            this.onBackInvokedCallback = Api33Impl.INSTANCE.createOnBackInvokedCallback(new Function0<Unit>() { // from class: androidx.activity.OnBackPressedDispatcher.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OnBackPressedDispatcher.this.onBackPressed();
                }
            });
        }
    }

    @MainThread
    public final void addCallback(@NotNull LifecycleOwner owner, @NotNull OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
        Lifecycle lifecycle = owner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        onBackPressedCallback.addCancellable(new LifecycleOnBackPressedCancellable(this, lifecycle, onBackPressedCallback));
        if (Build.VERSION.SDK_INT >= 33) {
            updateBackInvokedCallbackState$activity_release();
            onBackPressedCallback.enabledChangedCallback = this.enabledChangedCallback;
        }
    }

    public /* synthetic */ OnBackPressedDispatcher(Runnable runnable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : runnable);
    }
}
