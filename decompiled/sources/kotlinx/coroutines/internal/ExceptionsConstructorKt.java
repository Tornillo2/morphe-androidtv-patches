package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ExceptionsConstructorKt {

    @NotNull
    public static final CtorCache ctorCache;
    public static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    static {
        WeakMapCtorCache weakMapCtorCache;
        try {
            FastServiceLoaderKt.getANDROID_DETECTED();
            weakMapCtorCache = WeakMapCtorCache.INSTANCE;
        } catch (Throwable unused) {
            weakMapCtorCache = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = weakMapCtorCache;
    }

    public static final <E extends Throwable> Function1<Throwable, Throwable> createConstructor(Class<E> cls) {
        ExceptionsConstructorKt$createConstructor$nullResult$1 exceptionsConstructorKt$createConstructor$nullResult$1 = ExceptionsConstructorKt$createConstructor$nullResult$1.INSTANCE;
        if (throwableFields == fieldsCountOrDefault(cls, 0)) {
            Iterator it = ArraysKt___ArraysKt.sortedWith(cls.getConstructors(), new ExceptionsConstructorKt$createConstructor$$inlined$sortedByDescending$1()).iterator();
            while (it.hasNext()) {
                Function1<Throwable, Throwable> function1CreateSafeConstructor = createSafeConstructor((Constructor) it.next());
                if (function1CreateSafeConstructor != null) {
                    return function1CreateSafeConstructor;
                }
            }
        }
        return exceptionsConstructorKt$createConstructor$nullResult$1;
    }

    public static final Function1<Throwable, Throwable> createSafeConstructor(final Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length == 0) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final Throwable invoke(@NotNull Throwable th) {
                    Object objCreateFailure;
                    Object objNewInstance;
                    try {
                        objNewInstance = constructor.newInstance(null);
                    } catch (Throwable th2) {
                        objCreateFailure = ResultKt.createFailure(th2);
                    }
                    if (objNewInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    Throwable th3 = (Throwable) objNewInstance;
                    th3.initCause(th);
                    objCreateFailure = th3;
                    return (Throwable) (objCreateFailure instanceof Result.Failure ? null : objCreateFailure);
                }
            };
        }
        if (length != 1) {
            if (length == 2 && Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final Throwable invoke(@NotNull Throwable th) {
                        Object objCreateFailure;
                        Object objNewInstance;
                        try {
                            objNewInstance = constructor.newInstance(th.getMessage(), th);
                        } catch (Throwable th2) {
                            objCreateFailure = ResultKt.createFailure(th2);
                        }
                        if (objNewInstance == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                        }
                        objCreateFailure = (Throwable) objNewInstance;
                        if (objCreateFailure instanceof Result.Failure) {
                            objCreateFailure = null;
                        }
                        return (Throwable) objCreateFailure;
                    }
                };
            }
            return null;
        }
        Class<?> cls = parameterTypes[0];
        if (Intrinsics.areEqual(cls, Throwable.class)) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final Throwable invoke(@NotNull Throwable th) {
                    Object objCreateFailure;
                    Object objNewInstance;
                    try {
                        objNewInstance = constructor.newInstance(th);
                    } catch (Throwable th2) {
                        objCreateFailure = ResultKt.createFailure(th2);
                    }
                    if (objNewInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    objCreateFailure = (Throwable) objNewInstance;
                    if (objCreateFailure instanceof Result.Failure) {
                        objCreateFailure = null;
                    }
                    return (Throwable) objCreateFailure;
                }
            };
        }
        if (Intrinsics.areEqual(cls, String.class)) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final Throwable invoke(@NotNull Throwable th) {
                    Object objCreateFailure;
                    Object objNewInstance;
                    try {
                        objNewInstance = constructor.newInstance(th.getMessage());
                    } catch (Throwable th2) {
                        objCreateFailure = ResultKt.createFailure(th2);
                    }
                    if (objNewInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                    Throwable th3 = (Throwable) objNewInstance;
                    th3.initCause(th);
                    objCreateFailure = th3;
                    boolean z = objCreateFailure instanceof Result.Failure;
                    Object obj = objCreateFailure;
                    if (z) {
                        obj = null;
                    }
                    return (Throwable) obj;
                }
            };
        }
        return null;
    }

    public static final int fieldsCount(Class<?> cls, int i) {
        do {
            int i2 = 0;
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    i2++;
                }
            }
            i += i2;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i;
    }

    public static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    public static final int fieldsCountOrDefault(Class<?> cls, int i) {
        Object objCreateFailure;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            objCreateFailure = Integer.valueOf(fieldsCount$default(cls, 0, 1, null));
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        Object objValueOf = Integer.valueOf(i);
        if (objCreateFailure instanceof Result.Failure) {
            objCreateFailure = objValueOf;
        }
        return ((Number) objCreateFailure).intValue();
    }

    public static final Function1<Throwable, Throwable> safeCtor(final Function1<? super Throwable, ? extends Throwable> function1) {
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt.safeCtor.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Throwable invoke(@NotNull Throwable th) {
                Object objCreateFailure;
                try {
                    objCreateFailure = (Throwable) function1.invoke(th);
                } catch (Throwable th2) {
                    objCreateFailure = ResultKt.createFailure(th2);
                }
                if (objCreateFailure instanceof Result.Failure) {
                    objCreateFailure = null;
                }
                return (Throwable) objCreateFailure;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <E extends Throwable> E tryCopyException(@NotNull E e) {
        Object objCreateFailure;
        if (!(e instanceof CopyableThrowable)) {
            return (E) ctorCache.get(e.getClass()).invoke(e);
        }
        try {
            objCreateFailure = ((CopyableThrowable) e).createCopy();
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        if (objCreateFailure instanceof Result.Failure) {
            objCreateFailure = null;
        }
        return (E) objCreateFailure;
    }
}
