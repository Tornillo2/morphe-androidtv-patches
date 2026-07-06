package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.DesugarCollections;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Throwables {

    @J2ktIncompatible
    @GwtIncompatible
    public static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";

    @VisibleForTesting
    @J2ktIncompatible
    @GwtIncompatible
    public static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";

    @J2ktIncompatible
    @GwtIncompatible
    public static final Method getStackTraceDepthMethod;

    @J2ktIncompatible
    @GwtIncompatible
    public static final Method getStackTraceElementMethod;

    @J2ktIncompatible
    @GwtIncompatible
    public static final Object jla;

    static {
        Object jla2 = getJla();
        jla = jla2;
        getStackTraceElementMethod = jla2 == null ? null : getGetMethod();
        getStackTraceDepthMethod = jla2 != null ? getSizeMethod(jla2) : null;
    }

    public static List<Throwable> getCausalChain(Throwable throwable) {
        throwable.getClass();
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(throwable);
        boolean z = false;
        Throwable cause = throwable;
        while (true) {
            throwable = throwable.getCause();
            if (throwable == null) {
                return DesugarCollections.unmodifiableList(arrayList);
            }
            arrayList.add(throwable);
            if (throwable == cause) {
                throw new IllegalArgumentException("Loop in causal chain detected.", throwable);
            }
            if (z) {
                cause = cause.getCause();
            }
            z = !z;
        }
    }

    @GwtIncompatible
    public static <X extends Throwable> X getCauseAs(Throwable throwable, Class<X> expectedCauseType) {
        try {
            return expectedCauseType.cast(throwable.getCause());
        } catch (ClassCastException e) {
            e.initCause(throwable);
            throw e;
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Object getJla() {
        try {
            return Class.forName(SHARED_SECRETS_CLASSNAME, false, null).getMethod("getJavaLangAccess", null).invoke(null, null);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Method getJlaMethod(String name, Class<?>... parameterTypes) throws ThreadDeath {
        try {
            return Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, null).getMethod(name, parameterTypes);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Throwable getRootCause(Throwable throwable) {
        boolean z = false;
        Throwable cause = throwable;
        while (true) {
            Throwable cause2 = throwable.getCause();
            if (cause2 == null) {
                return throwable;
            }
            if (cause2 == cause) {
                throw new IllegalArgumentException("Loop in causal chain detected.", cause2);
            }
            if (z) {
                cause = cause.getCause();
            }
            z = !z;
            throwable = cause2;
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Method getSizeMethod(Object jla2) {
        try {
            Method jlaMethod = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (jlaMethod == null) {
                return null;
            }
            jlaMethod.invoke(jla2, new Throwable());
            return jlaMethod;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    @GwtIncompatible
    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Object invokeAccessibleNonThrowingMethod(Method method, Object receiver, Object... params) {
        try {
            return method.invoke(receiver, params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            propagate(e2.getCause());
            throw null;
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static List<StackTraceElement> jlaStackTrace(final Throwable t) {
        t.getClass();
        return new AbstractList<StackTraceElement>() { // from class: com.google.common.base.Throwables.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                Method method = Throwables.getStackTraceDepthMethod;
                j$.util.Objects.requireNonNull(method);
                Object obj = Throwables.jla;
                j$.util.Objects.requireNonNull(obj);
                return ((Integer) Throwables.invokeAccessibleNonThrowingMethod(method, obj, t)).intValue();
            }

            @Override // java.util.AbstractList, java.util.List
            public StackTraceElement get(int n) {
                Method method = Throwables.getStackTraceElementMethod;
                j$.util.Objects.requireNonNull(method);
                Object obj = Throwables.jla;
                j$.util.Objects.requireNonNull(obj);
                return (StackTraceElement) Throwables.invokeAccessibleNonThrowingMethod(method, obj, t, Integer.valueOf(n));
            }
        };
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static List<StackTraceElement> lazyStackTrace(Throwable throwable) {
        return lazyStackTraceIsLazy() ? jlaStackTrace(throwable) : DesugarCollections.unmodifiableList(Arrays.asList(throwable.getStackTrace()));
    }

    @J2ktIncompatible
    @GwtIncompatible
    @Deprecated
    public static boolean lazyStackTraceIsLazy() {
        return (getStackTraceElementMethod == null || getStackTraceDepthMethod == null) ? false : true;
    }

    @GwtIncompatible
    @Deprecated
    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static RuntimeException propagate(Throwable throwable) {
        throwIfUnchecked(throwable);
        throw new RuntimeException(throwable);
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static <X extends Throwable> void propagateIfInstanceOf(Throwable throwable, Class<X> declaredType) throws Throwable {
        if (throwable != null) {
            throwIfInstanceOf(throwable, declaredType);
        }
    }

    @J2ktIncompatible
    @GwtIncompatible
    @Deprecated
    public static void propagateIfPossible(Throwable throwable) {
        if (throwable != null) {
            throwIfUnchecked(throwable);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    @GwtIncompatible
    public static <X extends Throwable> void throwIfInstanceOf(Throwable throwable, Class<X> declaredType) throws Throwable {
        throwable.getClass();
        if (declaredType.isInstance(throwable)) {
            throw declaredType.cast(throwable);
        }
    }

    public static void throwIfUnchecked(Throwable throwable) {
        throwable.getClass();
        if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        }
        if (throwable instanceof Error) {
            throw ((Error) throwable);
        }
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static <X extends Throwable> void propagateIfPossible(Throwable throwable, Class<X> declaredType) throws Throwable {
        propagateIfInstanceOf(throwable, declaredType);
        propagateIfPossible(throwable);
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(Throwable throwable, Class<X1> declaredType1, Class<X2> declaredType2) throws Throwable {
        declaredType2.getClass();
        propagateIfInstanceOf(throwable, declaredType1);
        propagateIfPossible(throwable, declaredType2);
    }
}
