package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ThreadContextKt$updateState$1 extends Lambda implements Function2<ThreadState, CoroutineContext.Element, ThreadState> {
    public static final ThreadContextKt$updateState$1 INSTANCE = new ThreadContextKt$updateState$1(2);

    public ThreadContextKt$updateState$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ ThreadState invoke(ThreadState threadState, CoroutineContext.Element element) {
        ThreadState threadState2 = threadState;
        invoke2(threadState2, element);
        return threadState2;
    }

    @NotNull
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final ThreadState invoke2(@NotNull ThreadState threadState, @NotNull CoroutineContext.Element element) {
        if (element instanceof ThreadContextElement) {
            ThreadContextElement<?> threadContextElement = (ThreadContextElement) element;
            threadState.append(threadContextElement, threadContextElement.updateThreadContext(threadState.context));
        }
        return threadState;
    }
}
