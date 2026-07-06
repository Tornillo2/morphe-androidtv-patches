package kotlinx.coroutines.internal;

import com.amazon.livingroom.mediapipelinebackend.MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2;
import java.lang.reflect.InvocationTargetException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class OnUndeliveredElementKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CoroutineContext $context;
        public final /* synthetic */ E $element;
        public final /* synthetic */ Function1<E, Unit> $this_bindCancellationFun;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super E, Unit> function1, E e, CoroutineContext coroutineContext) {
            super(1);
            this.$this_bindCancellationFun = function1;
            this.$element = e;
            this.$context = coroutineContext;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws IllegalAccessException, InvocationTargetException {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Throwable th) throws IllegalAccessException, InvocationTargetException {
            OnUndeliveredElementKt.callUndeliveredElement(this.$this_bindCancellationFun, this.$element, this.$context);
        }
    }

    @NotNull
    public static final <E> Function1<Throwable, Unit> bindCancellationFun(@NotNull Function1<? super E, Unit> function1, E e, @NotNull CoroutineContext coroutineContext) {
        return new AnonymousClass1(function1, e, coroutineContext);
    }

    public static final <E> void callUndeliveredElement(@NotNull Function1<? super E, Unit> function1, E e, @NotNull CoroutineContext coroutineContext) throws IllegalAccessException, InvocationTargetException {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException = callUndeliveredElementCatchingException(function1, e, null);
        if (undeliveredElementExceptionCallUndeliveredElementCatchingException != null) {
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, undeliveredElementExceptionCallUndeliveredElementCatchingException);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <E> UndeliveredElementException callUndeliveredElementCatchingException(@NotNull Function1<? super E, Unit> function1, E e, @Nullable UndeliveredElementException undeliveredElementException) throws IllegalAccessException, InvocationTargetException {
        try {
            function1.invoke(e);
            return undeliveredElementException;
        } catch (Throwable th) {
            if (undeliveredElementException == null || undeliveredElementException.getCause() == th) {
                return new UndeliveredElementException(MediaPipelineBackendEngineManager$$ExternalSyntheticOutline2.m("Exception in undelivered element handler for ", e), th);
            }
            ExceptionsKt__ExceptionsKt.addSuppressed(undeliveredElementException, th);
            return undeliveredElementException;
        }
    }

    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i, Object obj2) {
        if ((i & 2) != 0) {
            undeliveredElementException = null;
        }
        return callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
    }
}
