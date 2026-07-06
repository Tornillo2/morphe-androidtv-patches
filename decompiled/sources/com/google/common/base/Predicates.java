package com.google.common.base;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.JdkPattern;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Predicates {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AndPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final List<? extends Predicate<? super T>> components;

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (!this.components.get(i).apply(t)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof AndPredicate) {
                return this.components.equals(((AndPredicate) obj).components);
            }
            return false;
        }

        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        public String toString() {
            return Predicates.toStringHelper("and", this.components);
        }

        public AndPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CompositionPredicate<A, B> implements Predicate<A>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Function<A, ? extends B> f;
        public final Predicate<B> p;

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness A a) {
            return this.p.apply(this.f.apply(a));
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof CompositionPredicate) {
                CompositionPredicate compositionPredicate = (CompositionPredicate) obj;
                if (this.f.equals(compositionPredicate.f) && this.p.equals(compositionPredicate.p)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.p.hashCode();
        }

        public String toString() {
            return this.p + "(" + this.f + ")";
        }

        public CompositionPredicate(Predicate<B> p, Function<A, ? extends B> f) {
            p.getClass();
            this.p = p;
            f.getClass();
            this.f = f;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public ContainsPatternFromStringPredicate(String string) {
            super(Platform.compilePattern(string));
        }

        @Override // com.google.common.base.Predicates.ContainsPatternPredicate
        public String toString() {
            return "Predicates.containsPattern(" + this.pattern.pattern() + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class ContainsPatternPredicate implements Predicate<CharSequence>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final CommonPattern pattern;

        public ContainsPatternPredicate(CommonPattern pattern) {
            pattern.getClass();
            this.pattern = pattern;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof ContainsPatternPredicate) {
                ContainsPatternPredicate containsPatternPredicate = (ContainsPatternPredicate) obj;
                if (Objects.equal(this.pattern.pattern(), containsPatternPredicate.pattern.pattern()) && this.pattern.flags() == containsPatternPredicate.pattern.flags()) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.pattern.pattern(), Integer.valueOf(this.pattern.flags())});
        }

        public String toString() {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this.pattern);
            stringHelper.addHolder("pattern", this.pattern.pattern());
            stringHelper.add("pattern.flags", this.pattern.flags());
            return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Predicates.contains(", stringHelper.toString(), ")");
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(CharSequence t) {
            return ((JdkPattern.JdkMatcher) this.pattern.matcher(t)).matcher.find();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Collection<?> target;

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness T t) {
            try {
                return this.target.contains(t);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof InPredicate) {
                return this.target.equals(((InPredicate) obj).target);
            }
            return false;
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            return "Predicates.in(" + this.target + ")";
        }

        public InPredicate(Collection<?> target) {
            target.getClass();
            this.target = target;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    public static class InstanceOfPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Class<?> clazz;

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness T o) {
            return this.clazz.isInstance(o);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            return (obj instanceof InstanceOfPredicate) && this.clazz == ((InstanceOfPredicate) obj).clazz;
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        public String toString() {
            return "Predicates.instanceOf(" + this.clazz.getName() + ")";
        }

        public InstanceOfPredicate(Class<?> clazz) {
            clazz.getClass();
            this.clazz = clazz;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IsEqualToPredicate implements Predicate<Object>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Object target;

        @Override // com.google.common.base.Predicate
        public boolean apply(Object o) {
            return this.target.equals(o);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof IsEqualToPredicate) {
                return this.target.equals(((IsEqualToPredicate) obj).target);
            }
            return false;
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            return "Predicates.equalTo(" + this.target + ")";
        }

        public IsEqualToPredicate(Object target) {
            this.target = target;
        }

        public <T> Predicate<T> withNarrowedType() {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NotPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Predicate<T> predicate;

        public NotPredicate(Predicate<T> predicate) {
            predicate.getClass();
            this.predicate = predicate;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness T t) {
            return !this.predicate.apply(t);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof NotPredicate) {
                return this.predicate.equals(((NotPredicate) obj).predicate);
            }
            return false;
        }

        public int hashCode() {
            return ~this.predicate.hashCode();
        }

        public String toString() {
            return "Predicates.not(" + this.predicate + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ObjectPredicate implements Predicate<Object> {
        ALWAYS_TRUE { // from class: com.google.common.base.Predicates.ObjectPredicate.1
            @Override // com.google.common.base.Predicate
            public boolean apply(Object o) {
                return true;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysTrue()";
            }
        },
        ALWAYS_FALSE { // from class: com.google.common.base.Predicates.ObjectPredicate.2
            @Override // com.google.common.base.Predicate
            public boolean apply(Object o) {
                return false;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.alwaysFalse()";
            }
        },
        IS_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.3
            @Override // com.google.common.base.Predicate
            public boolean apply(Object o) {
                return o == null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.isNull()";
            }
        },
        NOT_NULL { // from class: com.google.common.base.Predicates.ObjectPredicate.4
            @Override // com.google.common.base.Predicate
            public boolean apply(Object o) {
                return o != null;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Predicates.notNull()";
            }
        };

        ObjectPredicate(AnonymousClass1 anonymousClass1) {
        }

        public <T> Predicate<T> withNarrowedType() {
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class OrPredicate<T> implements Predicate<T>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final List<? extends Predicate<? super T>> components;

        @Override // com.google.common.base.Predicate
        public boolean apply(@ParametricNullness T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (this.components.get(i).apply(t)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (obj instanceof OrPredicate) {
                return this.components.equals(((OrPredicate) obj).components);
            }
            return false;
        }

        public int hashCode() {
            return this.components.hashCode() + 87855567;
        }

        public String toString() {
            return Predicates.toStringHelper("or", this.components);
        }

        public OrPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtIncompatible
    @J2ktIncompatible
    public static class SubtypeOfPredicate implements Predicate<Class<?>>, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Class<?> clazz;

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            return (obj instanceof SubtypeOfPredicate) && this.clazz == ((SubtypeOfPredicate) obj).clazz;
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        public String toString() {
            return "Predicates.subtypeOf(" + this.clazz.getName() + ")";
        }

        public SubtypeOfPredicate(Class<?> clazz) {
            clazz.getClass();
            this.clazz = clazz;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(Class<?> input) {
            return this.clazz.isAssignableFrom(input);
        }
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> alwaysFalse() {
        return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> alwaysTrue() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    public static <T> Predicate<T> and(Iterable<? extends Predicate<? super T>> components) {
        return new AndPredicate(defensiveCopy(components));
    }

    public static <T> List<Predicate<? super T>> asList(Predicate<? super T> first, Predicate<? super T> second) {
        return Arrays.asList(first, second);
    }

    public static <A, B> Predicate<A> compose(Predicate<B> predicate, Function<A, ? extends B> function) {
        return new CompositionPredicate(predicate, function);
    }

    @GwtIncompatible("java.util.regex.Pattern")
    public static Predicate<CharSequence> contains(Pattern pattern) {
        return new ContainsPatternPredicate(new JdkPattern(pattern));
    }

    @GwtIncompatible
    public static Predicate<CharSequence> containsPattern(String pattern) {
        return new ContainsPatternFromStringPredicate(pattern);
    }

    public static <T> List<T> defensiveCopy(T... array) {
        return defensiveCopy(Arrays.asList(array));
    }

    public static <T> Predicate<T> equalTo(@ParametricNullness T target) {
        return target == null ? ObjectPredicate.IS_NULL.withNarrowedType() : new IsEqualToPredicate(target);
    }

    public static <T> Predicate<T> in(Collection<? extends T> target) {
        return new InPredicate(target);
    }

    @GwtIncompatible
    public static <T> Predicate<T> instanceOf(Class<?> clazz) {
        return new InstanceOfPredicate(clazz);
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> isNull() {
        return ObjectPredicate.IS_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return new NotPredicate(predicate);
    }

    @GwtCompatible(serializable = true)
    public static <T> Predicate<T> notNull() {
        return ObjectPredicate.NOT_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> components) {
        return new OrPredicate(defensiveCopy(components));
    }

    @J2ktIncompatible
    @GwtIncompatible
    public static Predicate<Class<?>> subtypeOf(Class<?> clazz) {
        return new SubtypeOfPredicate(clazz);
    }

    public static String toStringHelper(String methodName, Iterable<?> components) {
        StringBuilder sb = new StringBuilder("Predicates.");
        sb.append(methodName);
        sb.append('(');
        boolean z = true;
        for (Object obj : components) {
            if (!z) {
                sb.append(',');
            }
            sb.append(obj);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    public static <T> List<T> defensiveCopy(Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            t.getClass();
            arrayList.add(t);
        }
        return arrayList;
    }

    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<? super T>... components) {
        return new AndPredicate(defensiveCopy(components));
    }

    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<? super T>... components) {
        return new OrPredicate(defensiveCopy(components));
    }

    public static <T> Predicate<T> and(Predicate<? super T> first, Predicate<? super T> second) {
        first.getClass();
        second.getClass();
        return new AndPredicate(asList(first, second));
    }

    public static <T> Predicate<T> or(Predicate<? super T> first, Predicate<? super T> second) {
        first.getClass();
        second.getClass();
        return new OrPredicate(asList(first, second));
    }
}
