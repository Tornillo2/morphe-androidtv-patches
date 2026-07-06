package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ByFunctionOrdering;
import com.google.common.collect.CompoundOrdering;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import com.google.common.collect.ReverseOrdering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class FuturesGetChecked {
    public static final Ordering<List<Class<?>>> ORDERING_BY_CONSTRUCTOR_PARAMETER_LIST;
    public static final Ordering<Constructor<?>> WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public interface GetCheckedTypeValidator {
        void validateClass(Class<? extends Exception> exceptionClass);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class GetCheckedTypeValidatorHolder {
        public static final GetCheckedTypeValidator BEST_VALIDATOR = FuturesGetChecked.weakSetValidator();

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public enum WeakSetValidator implements GetCheckedTypeValidator {
            INSTANCE;

            public static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet();

            public static /* synthetic */ WeakSetValidator[] $values() {
                return new WeakSetValidator[]{INSTANCE};
            }

            @Override // com.google.common.util.concurrent.FuturesGetChecked.GetCheckedTypeValidator
            public void validateClass(Class<? extends Exception> exceptionClass) {
                Iterator<WeakReference<Class<? extends Exception>>> it = validClasses.iterator();
                while (it.hasNext()) {
                    if (exceptionClass.equals(it.next().get())) {
                        return;
                    }
                }
                FuturesGetChecked.checkExceptionClassValidity(exceptionClass);
                Set<WeakReference<Class<? extends Exception>>> set = validClasses;
                if (set.size() > 1000) {
                    set.clear();
                }
                set.add(new WeakReference<>(exceptionClass));
            }
        }

        public static GetCheckedTypeValidator getBestValidator() {
            return FuturesGetChecked.weakSetValidator();
        }
    }

    static {
        NaturalOrdering naturalOrdering = NaturalOrdering.INSTANCE;
        FuturesGetChecked$$ExternalSyntheticLambda0 futuresGetChecked$$ExternalSyntheticLambda0 = new FuturesGetChecked$$ExternalSyntheticLambda0();
        naturalOrdering.getClass();
        ReverseOrdering reverseOrdering = new ReverseOrdering(new CompoundOrdering(new ByFunctionOrdering(futuresGetChecked$$ExternalSyntheticLambda0, naturalOrdering), new ByFunctionOrdering(new FuturesGetChecked$$ExternalSyntheticLambda1(), naturalOrdering)));
        ORDERING_BY_CONSTRUCTOR_PARAMETER_LIST = reverseOrdering;
        WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM = new ByFunctionOrdering(new FuturesGetChecked$$ExternalSyntheticLambda2(), reverseOrdering);
    }

    public static GetCheckedTypeValidator bestGetCheckedTypeValidator() {
        return GetCheckedTypeValidatorHolder.BEST_VALIDATOR;
    }

    @VisibleForTesting
    public static void checkExceptionClassValidity(Class<? extends Exception> exceptionClass) {
        Preconditions.checkArgument(isCheckedException(exceptionClass), "Futures.getChecked exception type (%s) must not be a RuntimeException", exceptionClass);
        Preconditions.checkArgument(hasConstructorUsableByGetChecked(exceptionClass), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", exceptionClass);
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    @ParametricNullness
    public static <V, X extends Exception> V getChecked(GetCheckedTypeValidator validator, Future<V> future, Class<X> exceptionClass) throws Exception {
        validator.validateClass(exceptionClass);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), exceptionClass);
            throw null;
        }
    }

    public static boolean hasConstructorUsableByGetChecked(Class<? extends Exception> exceptionClass) {
        try {
            newWithCause(exceptionClass, new Exception());
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @VisibleForTesting
    public static boolean isCheckedException(Class<? extends Exception> type) {
        return !RuntimeException.class.isAssignableFrom(type);
    }

    public static <X> X newFromConstructor(Constructor<X> constructor, Throwable cause) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> cls = parameterTypes[i];
            if (cls.equals(String.class)) {
                objArr[i] = cause.toString();
            } else {
                if (!cls.equals(Throwable.class)) {
                    return null;
                }
                objArr[i] = cause;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public static <X extends Exception> X newWithCause(Class<X> exceptionClass, Throwable cause) {
        ArrayList arrayList = (ArrayList) WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM.sortedCopy(Arrays.asList(exceptionClass.getConstructors()));
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            X x = (X) newFromConstructor((Constructor) obj, cause);
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(cause);
                }
                return x;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + exceptionClass + " in response to chained exception", cause);
    }

    public static <X extends Exception> List<Constructor<X>> preferringStringsThenThrowables(List<Constructor<X>> list) {
        return (List<Constructor<X>>) WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM.sortedCopy(list);
    }

    @VisibleForTesting
    public static GetCheckedTypeValidator weakSetValidator() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }

    public static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable cause, Class<X> exceptionClass) throws Exception {
        if (cause instanceof Error) {
            throw new ExecutionError(cause);
        }
        if (!(cause instanceof RuntimeException)) {
            throw newWithCause(exceptionClass, cause);
        }
        throw new UncheckedExecutionException(cause);
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls) throws Exception {
        return (V) getChecked(GetCheckedTypeValidatorHolder.BEST_VALIDATOR, future, cls);
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> exceptionClass, long timeout, TimeUnit unit) throws Exception {
        GetCheckedTypeValidatorHolder.BEST_VALIDATOR.validateClass(exceptionClass);
        try {
            return future.get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), exceptionClass);
            throw null;
        } catch (TimeoutException e3) {
            throw newWithCause(exceptionClass, e3);
        }
    }
}
