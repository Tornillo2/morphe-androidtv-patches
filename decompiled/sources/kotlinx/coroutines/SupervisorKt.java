package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SupervisorKt {
    @NotNull
    public static final CompletableJob SupervisorJob(@Nullable Job job) {
        return new SupervisorJobImpl(job);
    }

    public static CompletableJob SupervisorJob$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return new SupervisorJobImpl(job);
    }

    @Nullable
    public static final <R> Object supervisorScope(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        SupervisorCoroutine supervisorCoroutine = new SupervisorCoroutine(continuation.getContext(), continuation);
        Object objStartUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(supervisorCoroutine, supervisorCoroutine, function2);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return objStartUndispatchedOrReturn;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    @JvmName(name = "SupervisorJob")
    /* JADX INFO: renamed from: SupervisorJob, reason: collision with other method in class */
    public static final Job m2074SupervisorJob(Job job) {
        return new SupervisorJobImpl(job);
    }

    /* JADX INFO: renamed from: SupervisorJob$default, reason: collision with other method in class */
    public static Job m2075SupervisorJob$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return new SupervisorJobImpl(job);
    }
}
