package com.amazon.ignitionshared.service;

import android.content.Context;
import android.os.Build;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import com.amazon.ignitionshared.watchnext.WatchNextHandler;
import com.amazon.livingroom.di.ApplicationComponent;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ClearWatchNextWorker extends Worker {

    @NotNull
    public static final String CLEAR_WATCH_NEXT = "clearWatchNext";

    @NotNull
    public static final Companion Companion = new Companion();

    @Inject
    public WatchNextHandler watchNextHandler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final void performClear(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(workManagerImpl, "getInstance(...)");
            workManagerImpl.beginUniqueWork(ClearWatchNextWorker.CLEAR_WATCH_NEXT, ExistingWorkPolicy.REPLACE, OneTimeWorkRequest.from((Class<? extends ListenableWorker>) ClearWatchNextWorker.class)).enqueue();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClearWatchNextWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        if (Build.VERSION.SDK_INT >= 26) {
            ApplicationComponent.Companion.getInstance().inject(this);
            getWatchNextHandler().clearCarousel();
        }
        return new ListenableWorker.Result.Success();
    }

    @NotNull
    public final WatchNextHandler getWatchNextHandler() {
        WatchNextHandler watchNextHandler = this.watchNextHandler;
        if (watchNextHandler != null) {
            return watchNextHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("watchNextHandler");
        throw null;
    }

    public final void setWatchNextHandler(@NotNull WatchNextHandler watchNextHandler) {
        Intrinsics.checkNotNullParameter(watchNextHandler, "<set-?>");
        this.watchNextHandler = watchNextHandler;
    }
}
