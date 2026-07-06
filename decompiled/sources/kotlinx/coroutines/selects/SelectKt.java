package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SelectKt {

    @NotNull
    public static final Object NOT_SELECTED = new Symbol("NOT_SELECTED");

    @NotNull
    public static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");

    @NotNull
    public static final Object UNDECIDED = new Symbol("UNDECIDED");

    @NotNull
    public static final Object RESUMED = new Symbol("RESUMED");

    @NotNull
    public static final SeqNumber selectOpSequenceNumber = new SeqNumber();

    @NotNull
    public static final Object getALREADY_SELECTED() {
        return ALREADY_SELECTED;
    }

    @NotNull
    public static final Object getNOT_SELECTED() {
        return NOT_SELECTED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalCoroutinesApi
    /* JADX INFO: renamed from: onTimeout-8Mi8wO0, reason: not valid java name */
    public static final <R> void m2142onTimeout8Mi8wO0(@NotNull SelectBuilder<? super R> selectBuilder, long j, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectBuilder.onTimeout(DelayKt.m2069toDelayMillisLRDsOJo(j), function1);
    }

    @Nullable
    public static final <R> Object select(@NotNull Function1<? super SelectBuilder<? super R>, Unit> function1, @NotNull Continuation<? super R> continuation) throws Throwable {
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final <R> Object select$$forInline(Function1<? super SelectBuilder<? super R>, Unit> function1, Continuation<? super R> continuation) throws Throwable {
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static /* synthetic */ void getALREADY_SELECTED$annotations() {
    }

    public static /* synthetic */ void getNOT_SELECTED$annotations() {
    }

    public static /* synthetic */ void getRESUMED$annotations() {
    }

    public static /* synthetic */ void getSelectOpSequenceNumber$annotations() {
    }

    public static /* synthetic */ void getUNDECIDED$annotations() {
    }
}
