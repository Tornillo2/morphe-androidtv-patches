package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Functions {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConstantFunction<E> implements Function<Object, E>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final E value;

        public ConstantFunction(@ParametricNullness E value) {
            this.value = value;
        }

        @Override // com.google.common.base.Function
        @ParametricNullness
        public E apply(Object from) {
            return this.value;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof ConstantFunction) {
                return Objects.equal(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        public int hashCode() {
            E e = this.value;
            if (e == null) {
                return 0;
            }
            return e.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        @ParametricNullness
        public final V defaultValue;
        public final Map<K, ? extends V> map;

        public ForMapWithDefault(Map<K, ? extends V> map, @ParametricNullness V defaultValue) {
            map.getClass();
            this.map = map;
            this.defaultValue = defaultValue;
        }

        @Override // com.google.common.base.Function
        @ParametricNullness
        public V apply(@ParametricNullness K key) {
            V v = this.map.get(key);
            return (v != null || this.map.containsKey(key)) ? v : this.defaultValue;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object o) {
            if (o instanceof ForMapWithDefault) {
                ForMapWithDefault forMapWithDefault = (ForMapWithDefault) o;
                if (this.map.equals(forMapWithDefault.map) && Objects.equal(this.defaultValue, forMapWithDefault.defaultValue)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.map, this.defaultValue});
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ", defaultValue=" + this.defaultValue + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Function<A, ? extends B> f;
        public final Function<B, C> g;

        public FunctionComposition(Function<B, C> g, Function<A, ? extends B> f) {
            g.getClass();
            this.g = g;
            f.getClass();
            this.f = f;
        }

        @Override // com.google.common.base.Function
        @ParametricNullness
        public C apply(@ParametricNullness A a) {
            return (C) this.g.apply(this.f.apply(a));
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof FunctionComposition) {
                FunctionComposition functionComposition = (FunctionComposition) obj;
                if (this.f.equals(functionComposition.f) && this.g.equals(functionComposition.g)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.g.hashCode();
        }

        public String toString() {
            return this.g + "(" + this.f + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Map<K, V> map;

        public FunctionForMapNoDefault(Map<K, V> map) {
            map.getClass();
            this.map = map;
        }

        @Override // com.google.common.base.Function
        @ParametricNullness
        public V apply(@ParametricNullness K key) {
            V v = this.map.get(key);
            Preconditions.checkArgument(v != null || this.map.containsKey(key), "Key '%s' not present in map", key);
            return v;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object o) {
            if (o instanceof FunctionForMapNoDefault) {
                return this.map.equals(((FunctionForMapNoDefault) o).map);
            }
            return false;
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Predicate<T> predicate;

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            return "Functions.forPredicate(" + this.predicate + ")";
        }

        public PredicateFunction(Predicate<T> predicate) {
            predicate.getClass();
            this.predicate = predicate;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Function
        public Boolean apply(@ParametricNullness T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SupplierFunction<F, T> implements Function<F, T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Supplier<T> supplier;

        @Override // com.google.common.base.Function
        @ParametricNullness
        public T apply(@ParametricNullness F input) {
            return this.supplier.get();
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            return "Functions.forSupplier(" + this.supplier + ")";
        }

        public SupplierFunction(Supplier<T> supplier) {
            supplier.getClass();
            this.supplier = supplier;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        public static /* synthetic */ ToStringFunction[] $values() {
            return new ToStringFunction[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }

        @Override // com.google.common.base.Function
        public String apply(Object o) {
            o.getClass();
            return o.toString();
        }
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f) {
        return new FunctionComposition(g, f);
    }

    public static <E> Function<Object, E> constant(@ParametricNullness E value) {
        return new ConstantFunction(value);
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    public static <F, T> Function<F, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, @ParametricNullness V defaultValue) {
        return new ForMapWithDefault(map, defaultValue);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        public static /* synthetic */ IdentityFunction[] $values() {
            return new IdentityFunction[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }

        @Override // com.google.common.base.Function
        public Object apply(Object o) {
            return o;
        }
    }
}
