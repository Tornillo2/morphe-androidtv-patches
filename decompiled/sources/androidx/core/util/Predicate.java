package androidx.core.util;

import android.annotation.SuppressLint;
import androidx.core.util.Predicate;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public interface Predicate<T> {

    /* JADX INFO: renamed from: androidx.core.util.Predicate$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @SuppressLint({"MissingNullability"})
        public static Predicate $default$and(final Predicate predicate, @SuppressLint({"MissingNullability"}) final Predicate predicate2) {
            Objects.requireNonNull(predicate2);
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda3
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate3) {
                    return Predicate.CC.$default$and(this, predicate3);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate3) {
                    return Predicate.CC.$default$or(this, predicate3);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$and$0(predicate, predicate2, obj);
                }
            };
        }

        @SuppressLint({"MissingNullability"})
        public static Predicate $default$negate(Predicate predicate) {
            return new Predicate$$ExternalSyntheticLambda4(predicate);
        }

        @SuppressLint({"MissingNullability"})
        public static Predicate $default$or(final Predicate predicate, @SuppressLint({"MissingNullability"}) final Predicate predicate2) {
            Objects.requireNonNull(predicate2);
            return new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate3) {
                    return Predicate.CC.$default$and(this, predicate3);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate3) {
                    return Predicate.CC.$default$or(this, predicate3);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return Predicate.CC.$private$lambda$or$2(predicate, predicate2, obj);
                }
            };
        }

        public static /* synthetic */ boolean $private$lambda$and$0(Predicate predicate, Predicate predicate2, Object obj) {
            return predicate.test(obj) && predicate2.test(obj);
        }

        public static /* synthetic */ boolean $private$lambda$negate$1(Predicate predicate, Object obj) {
            return !predicate.test(obj);
        }

        public static /* synthetic */ boolean $private$lambda$or$2(Predicate predicate, Predicate predicate2, Object obj) {
            return predicate.test(obj) || predicate2.test(obj);
        }

        @SuppressLint({"MissingNullability"})
        public static <T> Predicate<T> isEqual(@SuppressLint({"MissingNullability"}) final Object obj) {
            return obj == null ? new Predicate$$ExternalSyntheticLambda1() : new Predicate() { // from class: androidx.core.util.Predicate$$ExternalSyntheticLambda2
                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate and(Predicate predicate) {
                    return Predicate.CC.$default$and(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public Predicate negate() {
                    return new Predicate$$ExternalSyntheticLambda4(this);
                }

                @Override // androidx.core.util.Predicate
                public /* synthetic */ Predicate or(Predicate predicate) {
                    return Predicate.CC.$default$or(this, predicate);
                }

                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj2) {
                    return obj.equals(obj2);
                }
            };
        }

        @SuppressLint({"MissingNullability"})
        public static <T> Predicate<T> not(@SuppressLint({"MissingNullability"}) Predicate<? super T> predicate) {
            Objects.requireNonNull(predicate);
            return predicate.negate();
        }
    }

    @SuppressLint({"MissingNullability"})
    Predicate<T> and(@SuppressLint({"MissingNullability"}) Predicate<? super T> predicate);

    @SuppressLint({"MissingNullability"})
    Predicate<T> negate();

    @SuppressLint({"MissingNullability"})
    Predicate<T> or(@SuppressLint({"MissingNullability"}) Predicate<? super T> predicate);

    boolean test(T t);
}
