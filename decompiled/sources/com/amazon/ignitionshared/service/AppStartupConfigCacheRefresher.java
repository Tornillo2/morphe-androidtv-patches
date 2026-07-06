package com.amazon.ignitionshared.service;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import com.amazon.ignitionshared.work.AvPeriodicWorker;
import com.amazon.livingroom.appstartupconfig.AppStartupConfigRequester;
import com.amazon.livingroom.di.ApplicationComponent;
import com.amazon.reporting.Log;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AppStartupConfigCacheRefresher extends AvPeriodicWorker {
    public static final int JOB_CANCEL_DAYS = 30;

    @NotNull
    public static final String KEY_SCHEDULE_DATE = "ScheduleDate";
    public static final String TAG = "AppStartupConfigCacheRefresher";
    public static final long TIMEOUT_DURATION_MINUTES = 9;

    @NotNull
    public static final String UNIQUE_WORKER_NAME = "AppStartupConfigCacheRefresher";

    @NotNull
    public final Class<InternalWorker> internalWorker;

    @NotNull
    public final String internalWorkerName;

    @NotNull
    public static final Companion Companion = new Companion();
    public static final DateFormat dateFormat = DateFormat.getInstance();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nAppStartupConfigCacheRefresher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppStartupConfigCacheRefresher.kt\ncom/amazon/ignitionshared/service/AppStartupConfigCacheRefresher$Companion\n+ 2 Data.kt\nandroidx/work/DataKt\n*L\n1#1,94:1\n31#2,5:95\n*S KotlinDebug\n*F\n+ 1 AppStartupConfigCacheRefresher.kt\ncom/amazon/ignitionshared/service/AppStartupConfigCacheRefresher$Companion\n*L\n90#1:95,5\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @NotNull
        public final AvPeriodicWorker.SchedulingResult schedule(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            AvPeriodicWorker.Companion companion = AvPeriodicWorker.Companion;
            NetworkType networkType = NetworkType.UNMETERED;
            Pair<Long, ? extends TimeUnit> pair = new Pair<>(1L, TimeUnit.DAYS);
            Pair<Long, ? extends TimeUnit> pair2 = new Pair<>(4L, TimeUnit.HOURS);
            Pair[] pairArr = {new Pair(AppStartupConfigCacheRefresher.KEY_SCHEDULE_DATE, AppStartupConfigCacheRefresher.dateFormat.format(new Date()))};
            Data.Builder builder = new Data.Builder();
            Pair pair3 = pairArr[0];
            builder.put((String) pair3.first, pair3.second);
            return companion.schedule(context, AppStartupConfigCacheRefresher.class, AppStartupConfigCacheRefresher.UNIQUE_WORKER_NAME, networkType, pair, pair2, builder.build());
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nAppStartupConfigCacheRefresher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppStartupConfigCacheRefresher.kt\ncom/amazon/ignitionshared/service/AppStartupConfigCacheRefresher$InternalWorker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,94:1\n1#2:95\n*E\n"})
    public static final class InternalWorker extends Worker {

        @Inject
        public AppStartupConfigRequester requester;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InternalWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
            super(context, workerParams);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        }

        @Override // androidx.work.Worker
        @NotNull
        public ListenableWorker.Result doWork() {
            ListenableWorker.Result failure;
            Log.i(AppStartupConfigCacheRefresher.TAG, "Refreshing app startup config");
            ApplicationComponent.Companion.getInstance().inject(this);
            try {
                getRequester().requestAppStartupConfig().get(9L, TimeUnit.MINUTES);
                Log.d(AppStartupConfigCacheRefresher.TAG, "App startup config refreshed");
                failure = new ListenableWorker.Result.Success();
            } catch (InterruptedException e) {
                Log.e(AppStartupConfigCacheRefresher.TAG, "Interrupted while refreshing AppStartupConfig", e);
                failure = new ListenableWorker.Result.Failure();
            } catch (ExecutionException e2) {
                Log.e(AppStartupConfigCacheRefresher.TAG, "Failed to refresh AppStartupConfig", e2);
                failure = new ListenableWorker.Result.Failure();
            } catch (TimeoutException e3) {
                Log.e(AppStartupConfigCacheRefresher.TAG, "Failed to refresh AppStartupConfig", e3);
                failure = new ListenableWorker.Result.Failure();
            }
            maybeCancel();
            return failure;
        }

        @NotNull
        public final AppStartupConfigRequester getRequester() {
            AppStartupConfigRequester appStartupConfigRequester = this.requester;
            if (appStartupConfigRequester != null) {
                return appStartupConfigRequester;
            }
            Intrinsics.throwUninitializedPropertyAccessException("requester");
            throw null;
        }

        public final void maybeCancel() {
            Object objCreateFailure;
            try {
                String string = getInputData().getString(AppStartupConfigCacheRefresher.KEY_SCHEDULE_DATE);
                Intrinsics.checkNotNull(string);
                Date date = AppStartupConfigCacheRefresher.dateFormat.parse(string);
                Intrinsics.checkNotNull(date);
                objCreateFailure = Boolean.valueOf(TimeUnit.MILLISECONDS.toDays(new Date().getTime() - date.getTime()) > 30);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
            }
            Object obj = Boolean.TRUE;
            if (objCreateFailure instanceof Result.Failure) {
                objCreateFailure = obj;
            }
            if (((Boolean) objCreateFailure).booleanValue()) {
                WorkManagerImpl.getInstance(getApplicationContext()).cancelUniqueWork(AppStartupConfigCacheRefresher.UNIQUE_WORKER_NAME);
            }
        }

        public final void setRequester(@NotNull AppStartupConfigRequester appStartupConfigRequester) {
            Intrinsics.checkNotNullParameter(appStartupConfigRequester, "<set-?>");
            this.requester = appStartupConfigRequester;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppStartupConfigCacheRefresher(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.internalWorker = InternalWorker.class;
        this.internalWorkerName = "AppStartupConfigCacheRefresher.internal";
    }

    @JvmStatic
    @NotNull
    public static final AvPeriodicWorker.SchedulingResult schedule(@NotNull Context context) {
        return Companion.schedule(context);
    }

    @Override // com.amazon.ignitionshared.work.AvPeriodicWorker
    @NotNull
    public Class<InternalWorker> getInternalWorker() {
        return this.internalWorker;
    }

    @Override // com.amazon.ignitionshared.work.AvPeriodicWorker
    @NotNull
    public String getInternalWorkerName() {
        return this.internalWorkerName;
    }

    @Override // com.amazon.ignitionshared.work.AvPeriodicWorker
    public void recordSchedulingResult(@NotNull AvPeriodicWorker.SchedulingResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
    }
}
