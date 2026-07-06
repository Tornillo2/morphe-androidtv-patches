package com.amazon.ignitionshared.service;

import android.content.Context;
import android.os.Build;
import androidx.work.Data;
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
public final class UpdateWatchNextWorker extends Worker {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String UPDATE_WATCH_NEXT = "updateWatchNext";

    @NotNull
    public static final String WORKER_PARAMETERS = "workerParameters";

    @Inject
    public WatchNextHandler watchNextHandler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final void performUpdate(@NotNull String parameters, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(workManagerImpl, "getInstance(...)");
            Data.Builder builder = new Data.Builder();
            builder.putString(UpdateWatchNextWorker.WORKER_PARAMETERS, parameters);
            workManagerImpl.beginUniqueWork(UpdateWatchNextWorker.UPDATE_WATCH_NEXT, ExistingWorkPolicy.REPLACE, new OneTimeWorkRequest.Builder(UpdateWatchNextWorker.class).setInputData(builder.build()).build()).enqueue();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateWatchNextWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        if (Build.VERSION.SDK_INT >= 26) {
            ApplicationComponent.Companion.getInstance().inject(this);
            getWatchNextHandler().updateCarousel(getInputData().getString(WORKER_PARAMETERS));
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
