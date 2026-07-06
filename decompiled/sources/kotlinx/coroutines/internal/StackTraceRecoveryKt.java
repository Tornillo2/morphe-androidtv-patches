package kotlinx.coroutines.internal;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StackTraceRecoveryKt {

    @NotNull
    public static final String baseContinuationImplClass = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
    public static final String baseContinuationImplClassName;

    @NotNull
    public static final String stackTraceRecoveryClass = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
    public static final String stackTraceRecoveryClassName;

    static {
        Object objCreateFailure;
        Object objCreateFailure2;
        try {
            objCreateFailure = BaseContinuationImpl.class.getCanonicalName();
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        if (Result.m583exceptionOrNullimpl(objCreateFailure) != null) {
            objCreateFailure = baseContinuationImplClass;
        }
        baseContinuationImplClassName = (String) objCreateFailure;
        try {
            objCreateFailure2 = StackTraceRecoveryKt.class.getCanonicalName();
        } catch (Throwable th2) {
            objCreateFailure2 = ResultKt.createFailure(th2);
        }
        if (Result.m583exceptionOrNullimpl(objCreateFailure2) != null) {
            objCreateFailure2 = stackTraceRecoveryClass;
        }
        stackTraceRecoveryClassName = (String) objCreateFailure2;
    }

    @InternalCoroutinesApi
    @NotNull
    public static final StackTraceElement artificialFrame(@NotNull String str) {
        return new StackTraceElement(RemoteInput$$ExternalSyntheticOutline0.m("\b\b\b(", str), "\b", "\b", -1);
    }

    public static final <E extends Throwable> Pair<E, StackTraceElement[]> causeAndStacktrace(E e) {
        Throwable cause = e.getCause();
        if (cause == null || !cause.getClass().equals(e.getClass())) {
            return new Pair<>(e, new StackTraceElement[0]);
        }
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (isArtificial(stackTraceElement)) {
                return new Pair<>(cause, stackTrace);
            }
        }
        return new Pair<>(e, new StackTraceElement[0]);
    }

    public static final <E extends Throwable> E createFinalException(E e, E e2, ArrayDeque<StackTraceElement> arrayDeque) {
        arrayDeque.addFirst(artificialFrame("Coroutine boundary"));
        StackTraceElement[] stackTrace = e.getStackTrace();
        int iFrameIndex = frameIndex(stackTrace, baseContinuationImplClassName);
        int i = 0;
        if (iFrameIndex == -1) {
            Object[] array = arrayDeque.toArray(new StackTraceElement[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            e2.setStackTrace((StackTraceElement[]) array);
            return e2;
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[arrayDeque.size() + iFrameIndex];
        for (int i2 = 0; i2 < iFrameIndex; i2++) {
            stackTraceElementArr[i2] = stackTrace[i2];
        }
        Iterator<StackTraceElement> it = arrayDeque.iterator();
        while (it.hasNext()) {
            int i3 = i + 1;
            stackTraceElementArr[i + iFrameIndex] = it.next();
            i = i3;
        }
        e2.setStackTrace(stackTraceElementArr);
        return e2;
    }

    public static final ArrayDeque<StackTraceElement> createStackTrace(CoroutineStackFrame coroutineStackFrame) {
        ArrayDeque<StackTraceElement> arrayDeque = new ArrayDeque<>();
        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
        if (stackTraceElement != null) {
            arrayDeque.add(stackTraceElement);
        }
        while (true) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return arrayDeque;
            }
            StackTraceElement stackTraceElement2 = coroutineStackFrame.getStackTraceElement();
            if (stackTraceElement2 != null) {
                arrayDeque.add(stackTraceElement2);
            }
        }
    }

    public static final boolean elementWiseEquals(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        return stackTraceElement.getLineNumber() == stackTraceElement2.getLineNumber() && Intrinsics.areEqual(stackTraceElement.getMethodName(), stackTraceElement2.getMethodName()) && Intrinsics.areEqual(stackTraceElement.getFileName(), stackTraceElement2.getFileName()) && Intrinsics.areEqual(stackTraceElement.getClassName(), stackTraceElement2.getClassName());
    }

    public static final int frameIndex(StackTraceElement[] stackTraceElementArr, String str) {
        int length = stackTraceElementArr.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(str, stackTraceElementArr[i].getClassName())) {
                return i;
            }
        }
        return -1;
    }

    public static final void initCause(@NotNull Throwable th, @NotNull Throwable th2) {
        th.initCause(th2);
    }

    public static final boolean isArtificial(@NotNull StackTraceElement stackTraceElement) {
        return StringsKt__StringsJVMKt.startsWith$default(stackTraceElement.getClassName(), "\b\b\b", false, 2, null);
    }

    public static final void mergeRecoveredTraces(StackTraceElement[] stackTraceElementArr, ArrayDeque<StackTraceElement> arrayDeque) {
        int length = stackTraceElementArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (isArtificial(stackTraceElementArr[i])) {
                break;
            } else {
                i++;
            }
        }
        int i2 = i + 1;
        int length2 = stackTraceElementArr.length - 1;
        if (i2 > length2) {
            return;
        }
        while (true) {
            if (elementWiseEquals(stackTraceElementArr[length2], arrayDeque.getLast())) {
                arrayDeque.removeLast();
            }
            arrayDeque.addFirst(stackTraceElementArr[length2]);
            if (length2 == i2) {
                return;
            } else {
                length2--;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends Throwable> E recoverFromStackFrame(E e, CoroutineStackFrame coroutineStackFrame) {
        Pair pairCauseAndStacktrace = causeAndStacktrace(e);
        Throwable th = (Throwable) pairCauseAndStacktrace.first;
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) pairCauseAndStacktrace.second;
        Throwable thTryCopyAndVerify = tryCopyAndVerify(th);
        if (thTryCopyAndVerify != null) {
            ArrayDeque<StackTraceElement> arrayDequeCreateStackTrace = createStackTrace(coroutineStackFrame);
            if (!arrayDequeCreateStackTrace.isEmpty()) {
                if (th != e) {
                    mergeRecoveredTraces(stackTraceElementArr, arrayDequeCreateStackTrace);
                }
                return (E) createFinalException(th, thTryCopyAndVerify, arrayDequeCreateStackTrace);
            }
        }
        return e;
    }

    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e) {
        return e;
    }

    public static final <E extends Throwable> E sanitizeStackTrace(E e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        int length = stackTrace.length;
        int iFrameIndex = frameIndex(stackTrace, stackTraceRecoveryClassName);
        int i = iFrameIndex + 1;
        int iFrameIndex2 = frameIndex(stackTrace, baseContinuationImplClassName);
        int i2 = 0;
        int i3 = (length - iFrameIndex) - (iFrameIndex2 == -1 ? 0 : length - iFrameIndex2);
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i3];
        while (i2 < i3) {
            stackTraceElementArr[i2] = i2 == 0 ? artificialFrame("Coroutine boundary") : stackTrace[(i + i2) - 1];
            i2++;
        }
        e.setStackTrace(stackTraceElementArr);
        return e;
    }

    public static final <E extends Throwable> E tryCopyAndVerify(E e) {
        E e2 = (E) ExceptionsConstructorKt.tryCopyException(e);
        if (e2 == null) {
            return null;
        }
        if ((e instanceof CopyableThrowable) || Intrinsics.areEqual(e2.getMessage(), e.getMessage())) {
            return e2;
        }
        return null;
    }

    @NotNull
    public static final <E extends Throwable> E unwrapImpl(@NotNull E e) {
        E e2 = (E) e.getCause();
        if (e2 != null && e2.getClass().equals(e.getClass())) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (isArtificial(stackTraceElement)) {
                    return e2;
                }
            }
        }
        return e;
    }

    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e, @NotNull Continuation<?> continuation) {
        return e;
    }

    public static /* synthetic */ void CoroutineStackFrame$annotations() {
    }

    public static /* synthetic */ void StackTraceElement$annotations() {
    }

    @NotNull
    public static final <E extends Throwable> E unwrap(@NotNull E e) {
        return e;
    }

    @Nullable
    public static final Object recoverAndThrow(@NotNull Throwable th, @NotNull Continuation<?> continuation) throws Throwable {
        throw th;
    }

    public static final Object recoverAndThrow$$forInline(Throwable th, Continuation<?> continuation) throws Throwable {
        throw th;
    }
}
