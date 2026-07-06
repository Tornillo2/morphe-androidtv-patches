package com.amazon.ignitionshared.work;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nAvPeriodicWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AvPeriodicWorker.kt\ncom/amazon/ignitionshared/work/AvPeriodicWorker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,96:1\n1#2:97\n*E\n"})
public abstract class AvPeriodicWorker extends Worker {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long MAXIMUM_DELAY = 7200000;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nAvPeriodicWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AvPeriodicWorker.kt\ncom/amazon/ignitionshared/work/AvPeriodicWorker$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,96:1\n1#2:97\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        public final SchedulingResult getAsSchedulingResult(ListenableFuture<Operation.State.SUCCESS> listenableFuture) {
            Object objCreateFailure;
            try {
                objCreateFailure = (Operation.State.SUCCESS) listenableFuture.get();
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
            }
            return !(objCreateFailure instanceof Result.Failure) ? SchedulingResult.Success : SchedulingResult.Failure;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final SchedulingResult schedule(@NotNull Context context, @NotNull Class<? extends AvPeriodicWorker> periodicWorkerClass, @NotNull String periodicWorkerName, @NotNull NetworkType networkType, @NotNull Pair<Long, ? extends TimeUnit> interval, @NotNull Pair<Long, ? extends TimeUnit> flex, @NotNull Data data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(periodicWorkerClass, "periodicWorkerClass");
            Intrinsics.checkNotNullParameter(periodicWorkerName, "periodicWorkerName");
            Intrinsics.checkNotNullParameter(networkType, "networkType");
            Intrinsics.checkNotNullParameter(interval, "interval");
            Intrinsics.checkNotNullParameter(flex, "flex");
            Intrinsics.checkNotNullParameter(data, "data");
            Constraints.Builder builder = new Constraints.Builder();
            builder.mRequiredNetworkType = networkType;
            ListenableFuture<Operation.State.SUCCESS> result = WorkManagerImpl.getInstance(context).enqueueUniquePeriodicWork(periodicWorkerName, ExistingPeriodicWorkPolicy.REPLACE, new PeriodicWorkRequest.Builder(periodicWorkerClass, interval.first.longValue(), (TimeUnit) interval.second, flex.first.longValue(), (TimeUnit) flex.second).setConstraints(new Constraints(builder)).setInputData(data).build()).getResult();
            Intrinsics.checkNotNullExpressionValue(result, "getResult(...)");
            return getAsSchedulingResult(result);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SchedulingResult {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ SchedulingResult[] $VALUES;
        public static final SchedulingResult Success = new SchedulingResult("Success", 0);
        public static final SchedulingResult Failure = new SchedulingResult("Failure", 1);

        public static final /* synthetic */ SchedulingResult[] $values() {
            return new SchedulingResult[]{Success, Failure};
        }

        static {
            SchedulingResult[] schedulingResultArr$values = $values();
            $VALUES = schedulingResultArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(schedulingResultArr$values);
        }

        public SchedulingResult(String str, int i) {
        }

        @NotNull
        public static EnumEntries<SchedulingResult> getEntries() {
            return $ENTRIES;
        }

        public static SchedulingResult valueOf(String str) {
            return (SchedulingResult) Enum.valueOf(SchedulingResult.class, str);
        }

        public static SchedulingResult[] values() {
            return (SchedulingResult[]) $VALUES.clone();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AvPeriodicWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.Worker
    @NotNull
    public final ListenableWorker.Result doWork() {
        Constraints.Builder builder = new Constraints.Builder();
        builder.mRequiredNetworkType = NetworkType.CONNECTED;
        Constraints constraints = new Constraints(builder);
        Random.Default.getClass();
        OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(getInternalWorker()).setConstraints(constraints).setInitialDelay(Random.defaultRandom.nextLong(0L, MAXIMUM_DELAY), TimeUnit.MILLISECONDS).setInputData(getInputData()).build();
        Companion companion = Companion;
        ListenableFuture<Operation.State.SUCCESS> result = WorkManagerImpl.getInstance(getApplicationContext()).enqueueUniqueWork(getInternalWorkerName(), ExistingWorkPolicy.REPLACE, oneTimeWorkRequestBuild).getResult();
        Intrinsics.checkNotNullExpressionValue(result, "getResult(...)");
        recordSchedulingResult(companion.getAsSchedulingResult(result));
        return new ListenableWorker.Result.Success();
    }

    @NotNull
    public abstract Class<? extends Worker> getInternalWorker();

    @NotNull
    public abstract String getInternalWorkerName();

    public abstract void recordSchedulingResult(@NotNull SchedulingResult schedulingResult);
}
