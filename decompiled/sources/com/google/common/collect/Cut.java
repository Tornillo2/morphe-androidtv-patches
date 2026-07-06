package com.google.common.collect;

import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class Cut<C extends Comparable> implements Comparable<Cut<C>>, Serializable {

    @J2ktIncompatible
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final C endpoint;

    /* JADX INFO: renamed from: com.google.common.collect.Cut$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AboveAll extends Cut<Comparable<?>> {
        public static final AboveAll INSTANCE = new AboveAll();

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public AboveAll() {
            super("");
        }

        @Override // com.google.common.collect.Cut
        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : 1;
        }

        @Override // com.google.common.collect.Cut
        public void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append("+∞)");
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> domain) {
            return domain.maxValue();
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        public boolean isLessThan(Comparable<?> value) {
            return false;
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError();
        }

        public final Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "+∞";
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsLowerBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsUpperBound() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        public Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        public Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public int compareTo(Object o) {
            return ((Cut) o) == this ? 0 : 1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AboveValue<C extends Comparable> extends Cut<C> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AboveValue(C endpoint) {
            super(endpoint);
            endpoint.getClass();
        }

        @Override // com.google.common.collect.Cut
        public Cut<C> canonical(DiscreteDomain<C> domain) {
            Comparable next = domain.next(this.endpoint);
            return next != null ? new BelowValue(next) : AboveAll.INSTANCE;
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object that) {
            return compareTo((Cut) that);
        }

        @Override // com.google.common.collect.Cut
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append('(');
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.Cut
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(AbstractJsonLexerKt.END_LIST);
        }

        @Override // com.google.common.collect.Cut
        public C greatestValueBelow(DiscreteDomain<C> domain) {
            return this.endpoint;
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return ~this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.Cut
        public boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) < 0;
        }

        @Override // com.google.common.collect.Cut
        public C leastValueAbove(DiscreteDomain<C> discreteDomain) {
            return (C) discreteDomain.next(this.endpoint);
        }

        public String toString() {
            return SchemaId.METRIC_SCHEMA_ID_DELIMITER + this.endpoint + CCTDestination.EXTRAS_DELIMITER;
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsLowerBound() {
            return BoundType.OPEN;
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsUpperBound() {
            return BoundType.CLOSED;
        }

        @Override // com.google.common.collect.Cut
        public Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
            if (i == 1) {
                Comparable next = domain.next(this.endpoint);
                return next == null ? BelowAll.INSTANCE : new BelowValue(next);
            }
            if (i == 2) {
                return this;
            }
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
            if (i == 1) {
                return this;
            }
            if (i != 2) {
                throw new AssertionError();
            }
            Comparable next = domain.next(this.endpoint);
            return next == null ? AboveAll.INSTANCE : new BelowValue(next);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BelowAll extends Cut<Comparable<?>> {
        public static final BelowAll INSTANCE = new BelowAll();

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public BelowAll() {
            super("");
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.Cut
        public Cut<Comparable<?>> canonical(DiscreteDomain<Comparable<?>> domain) {
            try {
                return new BelowValue(domain.minValue());
            } catch (NoSuchElementException unused) {
                return this;
            }
        }

        @Override // com.google.common.collect.Cut
        public int compareTo(Cut<Comparable<?>> o) {
            return o == this ? 0 : -1;
        }

        @Override // com.google.common.collect.Cut
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-∞");
        }

        @Override // com.google.common.collect.Cut
        public void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> endpoint() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> greatestValueBelow(DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        public boolean isLessThan(Comparable<?> value) {
            return true;
        }

        @Override // com.google.common.collect.Cut
        public Comparable<?> leastValueAbove(DiscreteDomain<Comparable<?>> domain) {
            return domain.minValue();
        }

        public String toString() {
            return "-∞";
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsLowerBound() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsUpperBound() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        public Cut<Comparable<?>> withLowerBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        public Cut<Comparable<?>> withUpperBoundType(BoundType boundType, DiscreteDomain<Comparable<?>> domain) {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public int compareTo(Object o) {
            return ((Cut) o) == this ? 0 : -1;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BelowValue<C extends Comparable> extends Cut<C> {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BelowValue(C endpoint) {
            super(endpoint);
            endpoint.getClass();
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object that) {
            return compareTo((Cut) that);
        }

        @Override // com.google.common.collect.Cut
        public void describeAsLowerBound(StringBuilder sb) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.Cut
        public void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(')');
        }

        @Override // com.google.common.collect.Cut
        public C greatestValueBelow(DiscreteDomain<C> discreteDomain) {
            return (C) discreteDomain.previous(this.endpoint);
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.Cut
        public boolean isLessThan(C value) {
            return Range.compareOrThrow(this.endpoint, value) <= 0;
        }

        @Override // com.google.common.collect.Cut
        public C leastValueAbove(DiscreteDomain<C> domain) {
            return this.endpoint;
        }

        public String toString() {
            return CCTDestination.EXTRAS_DELIMITER + this.endpoint + SchemaId.METRIC_SCHEMA_ID_DELIMITER;
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsLowerBound() {
            return BoundType.CLOSED;
        }

        @Override // com.google.common.collect.Cut
        public BoundType typeAsUpperBound() {
            return BoundType.OPEN;
        }

        @Override // com.google.common.collect.Cut
        public Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
            if (i == 1) {
                return this;
            }
            if (i != 2) {
                throw new AssertionError();
            }
            Comparable comparablePrevious = domain.previous(this.endpoint);
            return comparablePrevious == null ? BelowAll.INSTANCE : new AboveValue(comparablePrevious);
        }

        @Override // com.google.common.collect.Cut
        public Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$collect$BoundType[boundType.ordinal()];
            if (i == 1) {
                Comparable comparablePrevious = domain.previous(this.endpoint);
                return comparablePrevious == null ? AboveAll.INSTANCE : new AboveValue(comparablePrevious);
            }
            if (i == 2) {
                return this;
            }
            throw new AssertionError();
        }
    }

    public Cut(C endpoint) {
        this.endpoint = endpoint;
    }

    public static <C extends Comparable> Cut<C> aboveAll() {
        return AboveAll.INSTANCE;
    }

    public static <C extends Comparable> Cut<C> aboveValue(C endpoint) {
        return new AboveValue(endpoint);
    }

    public static <C extends Comparable> Cut<C> belowAll() {
        return BelowAll.INSTANCE;
    }

    public static <C extends Comparable> Cut<C> belowValue(C endpoint) {
        return new BelowValue(endpoint);
    }

    public abstract void describeAsLowerBound(StringBuilder sb);

    public abstract void describeAsUpperBound(StringBuilder sb);

    public C endpoint() {
        return this.endpoint;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cut) {
            try {
                if (compareTo((Cut) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract C greatestValueBelow(DiscreteDomain<C> domain);

    public abstract int hashCode();

    public abstract boolean isLessThan(C value);

    public abstract C leastValueAbove(DiscreteDomain<C> domain);

    public abstract BoundType typeAsLowerBound();

    public abstract BoundType typeAsUpperBound();

    public abstract Cut<C> withLowerBoundType(BoundType boundType, DiscreteDomain<C> domain);

    public abstract Cut<C> withUpperBoundType(BoundType boundType, DiscreteDomain<C> domain);

    @Override // java.lang.Comparable
    public int compareTo(Cut<C> that) {
        if (that == BelowAll.INSTANCE) {
            return 1;
        }
        if (that == AboveAll.INSTANCE) {
            return -1;
        }
        int iCompareOrThrow = Range.compareOrThrow(this.endpoint, that.endpoint);
        return iCompareOrThrow != 0 ? iCompareOrThrow : Boolean.compare(this instanceof AboveValue, that instanceof AboveValue);
    }

    public Cut<C> canonical(DiscreteDomain<C> domain) {
        return this;
    }
}
