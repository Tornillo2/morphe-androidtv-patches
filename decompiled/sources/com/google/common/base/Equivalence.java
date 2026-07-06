package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.ForOverride;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class Equivalence<T> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Equals extends Equivalence<Object> implements Serializable {
        public static final Equals INSTANCE = new Equals();

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object a, Object b) {
            return a.equals(b);
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object o) {
            return o.hashCode();
        }

        public final Object readResolve() {
            return INSTANCE;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Equivalence<T> equivalence;
        public final T target;

        public EquivalentToPredicate(Equivalence<T> equivalence, T target) {
            equivalence.getClass();
            this.equivalence = equivalence;
            this.target = target;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T input) {
            return this.equivalence.equivalent(input, this.target);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof EquivalentToPredicate) {
                EquivalentToPredicate equivalentToPredicate = (EquivalentToPredicate) obj;
                if (this.equivalence.equals(equivalentToPredicate.equivalence) && Objects.equal(this.target, equivalentToPredicate.target)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.equivalence, this.target});
        }

        public String toString() {
            return this.equivalence + ".equivalentTo(" + this.target + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Identity extends Equivalence<Object> implements Serializable {
        public static final Identity INSTANCE = new Identity();

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object a, Object b) {
            return false;
        }

        @Override // com.google.common.base.Equivalence
        public int doHash(Object o) {
            return System.identityHashCode(o);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Wrapper<T> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Equivalence<? super T> equivalence;

        @ParametricNullness
        public final T reference;

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Wrapper)) {
                return false;
            }
            Wrapper wrapper = (Wrapper) obj;
            if (this.equivalence.equals(wrapper.equivalence)) {
                return this.equivalence.equivalent(this.reference, wrapper.reference);
            }
            return false;
        }

        @ParametricNullness
        public T get() {
            return this.reference;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            return this.equivalence + ".wrap(" + this.reference + ")";
        }

        public Wrapper(Equivalence<? super T> equivalence, @ParametricNullness T reference) {
            equivalence.getClass();
            this.equivalence = equivalence;
            this.reference = reference;
        }
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    @ForOverride
    public abstract boolean doEquivalent(T a, T b);

    @ForOverride
    public abstract int doHash(T t);

    public final boolean equivalent(T a, T b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return doEquivalent(a, b);
    }

    public final Predicate<T> equivalentTo(T target) {
        return new EquivalentToPredicate(this, target);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public final <F> Equivalence<F> onResultOf(Function<? super F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public final <S extends T> Wrapper<S> wrap(@ParametricNullness S reference) {
        return new Wrapper<>(this, reference);
    }
}
