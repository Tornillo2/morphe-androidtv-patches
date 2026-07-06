package com.google.common.base;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import j$.time.Duration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Suppliers {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;
        public final long durationNanos;
        public volatile transient long expirationNanos;
        public transient Object lock = new Object();
        public volatile transient T value;

        public ExpiringMemoizingSupplier(Supplier<T> delegate, long durationNanos) {
            this.delegate = delegate;
            this.durationNanos = durationNanos;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            long j = this.expirationNanos;
            long jNanoTime = System.nanoTime();
            if (j == 0 || jNanoTime - j >= 0) {
                synchronized (this.lock) {
                    try {
                        if (j == this.expirationNanos) {
                            T t = this.delegate.get();
                            this.value = t;
                            long j2 = jNanoTime + this.durationNanos;
                            if (j2 == 0) {
                                j2 = 1;
                            }
                            this.expirationNanos = j2;
                            return t;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return this.value;
        }

        @J2ktIncompatible
        @GwtIncompatible
        public final void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
            in.defaultReadObject();
            this.lock = new Object();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Suppliers.memoizeWithExpiration(");
            sb.append(this.delegate);
            sb.append(", ");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(sb, this.durationNanos, ", NANOS)");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class MemoizingSupplier<T> implements Supplier<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;
        public volatile transient boolean initialized;
        public transient Object lock = new Object();
        public transient T value;

        public MemoizingSupplier(Supplier<T> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @J2ktIncompatible
        @GwtIncompatible
        private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
            in.defaultReadObject();
            this.lock = new Object();
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            if (!this.initialized) {
                synchronized (this.lock) {
                    try {
                        if (!this.initialized) {
                            T t = this.delegate.get();
                            this.value = t;
                            this.initialized = true;
                            return t;
                        }
                    } finally {
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object obj;
            StringBuilder sb = new StringBuilder("Suppliers.memoize(");
            if (this.initialized) {
                obj = "<supplier that returned " + this.value + ">";
            } else {
                obj = this.delegate;
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        public static final Supplier<Void> SUCCESSFULLY_COMPUTED = new Suppliers$NonSerializableMemoizingSupplier$$ExternalSyntheticLambda0();
        public volatile Supplier<T> delegate;
        public final Object lock = new Object();
        public T value;

        public static /* synthetic */ Void $r8$lambda$z7NQSm4H_wzbZnaYNFygi6b6f7I() {
            throw new IllegalStateException();
        }

        public NonSerializableMemoizingSupplier(Supplier<T> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            Supplier<T> supplier = this.delegate;
            Supplier<T> supplier2 = (Supplier<T>) SUCCESSFULLY_COMPUTED;
            if (supplier != supplier2) {
                synchronized (this.lock) {
                    try {
                        if (this.delegate != supplier2) {
                            T t = this.delegate.get();
                            this.value = t;
                            this.delegate = supplier2;
                            return t;
                        }
                    } finally {
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            Object obj = this.delegate;
            StringBuilder sb = new StringBuilder("Suppliers.memoize(");
            if (obj == SUCCESSFULLY_COMPUTED) {
                obj = "<supplier that returned " + this.value + ">";
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SupplierComposition<F, T> implements Supplier<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Function<? super F, T> function;
        public final Supplier<F> supplier;

        public SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            function.getClass();
            this.function = function;
            supplier.getClass();
            this.supplier = supplier;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierComposition) {
                SupplierComposition supplierComposition = (SupplierComposition) obj;
                if (this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.function, this.supplier});
        }

        public String toString() {
            return "Suppliers.compose(" + this.function + ", " + this.supplier + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SupplierOfInstance<T> implements Supplier<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final T instance;

        public SupplierOfInstance(@ParametricNullness T instance) {
            this.instance = instance;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            return this.instance;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.instance});
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.instance + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @J2ktIncompatible
    public static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Supplier<T> delegate;

        public ThreadSafeSupplier(Supplier<T> delegate) {
            delegate.getClass();
            this.delegate = delegate;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            T t;
            synchronized (this.delegate) {
                t = this.delegate.get();
            }
            return t;
        }

        public String toString() {
            return "Suppliers.synchronizedSupplier(" + this.delegate + ")";
        }
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> delegate) {
        return ((delegate instanceof NonSerializableMemoizingSupplier) || (delegate instanceof MemoizingSupplier)) ? delegate : delegate instanceof Serializable ? new MemoizingSupplier(delegate) : new NonSerializableMemoizingSupplier(delegate);
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> delegate, long duration, TimeUnit unit) {
        delegate.getClass();
        Preconditions.checkArgument(duration > 0, "duration (%s %s) must be > 0", duration, unit);
        return new ExpiringMemoizingSupplier(delegate, unit.toNanos(duration));
    }

    public static <T> Supplier<T> ofInstance(@ParametricNullness T instance) {
        return new SupplierOfInstance(instance);
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    @J2ktIncompatible
    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> delegate) {
        return new ThreadSafeSupplier(delegate);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        public static /* synthetic */ SupplierFunctionImpl[] $values() {
            return new SupplierFunctionImpl[]{INSTANCE};
        }

        @Override // com.google.common.base.Function
        public Object apply(Object input) {
            return ((Supplier) input).get();
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Suppliers.supplierFunction()";
        }

        public Object apply(Supplier<Object> input) {
            return input.get();
        }
    }

    @GwtIncompatible
    @IgnoreJRERequirement
    @J2ktIncompatible
    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> delegate, Duration duration) {
        delegate.getClass();
        Preconditions.checkArgument((duration.isNegative() || duration.isZero()) ? false : true, "duration (%s) must be > 0", duration);
        return new ExpiringMemoizingSupplier(delegate, Internal.toNanosSaturated(duration));
    }
}
