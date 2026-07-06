package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Optional;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use Optional.of(value) or Optional.absent()")
@GwtCompatible(serializable = true)
public abstract class Optional<T> implements Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    /* JADX INFO: renamed from: com.google.common.base.Optional$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AbstractIterator<T> {
        public final Iterator<? extends Optional<? extends T>> iterator;
        public final /* synthetic */ Iterable val$optionals;

        public AnonymousClass1(final Iterable val$optionals) {
            this.val$optionals = val$optionals;
            Iterator<? extends Optional<? extends T>> it = val$optionals.iterator();
            it.getClass();
            this.iterator = it;
        }

        @Override // com.google.common.base.AbstractIterator
        public T computeNext() {
            while (this.iterator.hasNext()) {
                Optional<? extends T> next = this.iterator.next();
                if (next.isPresent()) {
                    return next.get();
                }
            }
            endOfData();
            return null;
        }
    }

    public static /* synthetic */ Iterator $r8$lambda$WrBekaCHgZXf9frPnRGtVx8fOxU(Iterable iterable) {
        return new AnonymousClass1(iterable);
    }

    public static <T> Optional<T> absent() {
        return Absent.INSTANCE;
    }

    @IgnoreJRERequirement
    public static <T> Optional<T> fromJavaUtil(j$.util.Optional<T> javaUtilOptional) {
        if (javaUtilOptional == null) {
            return null;
        }
        return fromNullable(javaUtilOptional.orElse(null));
    }

    public static <T> Optional<T> fromNullable(T nullableReference) {
        return nullableReference == null ? Absent.INSTANCE : new Present(nullableReference);
    }

    public static <T> Optional<T> of(T reference) {
        reference.getClass();
        return new Present(reference);
    }

    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> optionals) {
        optionals.getClass();
        return new Iterable() { // from class: com.google.common.base.Optional$$ExternalSyntheticLambda0
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return new Optional.AnonymousClass1(optionals);
            }
        };
    }

    @IgnoreJRERequirement
    public static <T> j$.util.Optional<T> toJavaUtil(Optional<T> googleOptional) {
        if (googleOptional == null) {
            return null;
        }
        return googleOptional.toJavaUtil();
    }

    public abstract Set<T> asSet();

    public abstract boolean equals(Object object);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> secondChoice);

    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T defaultValue);

    public abstract T orNull();

    public abstract String toString();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    @IgnoreJRERequirement
    public j$.util.Optional<T> toJavaUtil() {
        return j$.util.Optional.ofNullable(orNull());
    }
}
