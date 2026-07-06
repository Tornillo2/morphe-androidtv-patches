package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public abstract class Converter<A, B> implements Function<A, B> {
    public final boolean handleNullAutomatically;

    @RetainedWith
    @LazyInit
    public transient Converter<B, A> reverse;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Converter<A, B> first;
        public final Converter<B, C> second;

        public ConverterComposition(Converter<A, B> first, Converter<B, C> second) {
            super(true);
            this.first = first;
            this.second = second;
        }

        @Override // com.google.common.base.Converter
        public A correctedDoBackward(C c) {
            return (A) this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        @Override // com.google.common.base.Converter
        public C correctedDoForward(A a) {
            return (C) this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        @Override // com.google.common.base.Converter
        public A doBackward(C c) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        public C doForward(A a) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof ConverterComposition) {
                ConverterComposition converterComposition = (ConverterComposition) object;
                if (this.first.equals(converterComposition.first) && this.second.equals(converterComposition.second)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            return this.first + ".andThen(" + this.second + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        public final Function<? super B, ? extends A> backwardFunction;
        public final Function<? super A, ? extends B> forwardFunction;

        public FunctionBasedConverter(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
            super(true);
            forwardFunction.getClass();
            this.forwardFunction = forwardFunction;
            backwardFunction.getClass();
            this.backwardFunction = backwardFunction;
        }

        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return this.backwardFunction.apply(b);
        }

        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return this.forwardFunction.apply(a);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof FunctionBasedConverter) {
                FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) object;
                if (this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.backwardFunction.hashCode() + (this.forwardFunction.hashCode() * 31);
        }

        public String toString() {
            return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        public static final Converter<?, ?> INSTANCE = new IdentityConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;

        public IdentityConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public <S> Converter<T, S> doAndThen(Converter<T, S> otherConverter) {
            Preconditions.checkNotNull(otherConverter, "otherConverter");
            return otherConverter;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }

        @Override // com.google.common.base.Converter
        public Converter reverse() {
            return this;
        }

        @Override // com.google.common.base.Converter
        public T doBackward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public T doForward(T t) {
            return t;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final Converter<A, B> original;

        public ReverseConverter(Converter<A, B> original) {
            super(true);
            this.original = original;
        }

        @Override // com.google.common.base.Converter
        public B correctedDoBackward(A a) {
            return this.original.correctedDoForward(a);
        }

        @Override // com.google.common.base.Converter
        public A correctedDoForward(B b) {
            return this.original.correctedDoBackward(b);
        }

        @Override // com.google.common.base.Converter
        public B doBackward(A a) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        public A doForward(B b) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(Object object) {
            if (object instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) object).original);
            }
            return false;
        }

        public int hashCode() {
            return ~this.original.hashCode();
        }

        @Override // com.google.common.base.Converter
        public Converter<A, B> reverse() {
            return this.original;
        }

        public String toString() {
            return this.original + ".reverse()";
        }
    }

    public static /* synthetic */ Iterator $r8$lambda$9wmNQyD5bzY9Puwafq28xUluA4c(Converter converter, Iterable iterable) {
        converter.getClass();
        return new Iterator<B>(converter, iterable) { // from class: com.google.common.base.Converter.1
            public final Iterator<? extends A> fromIterator;
            public final /* synthetic */ Converter this$0;
            public final /* synthetic */ Iterable val$fromIterable;

            {
                this.val$fromIterable = iterable;
                this.this$0 = converter;
                this.fromIterator = iterable.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.fromIterator.hasNext();
            }

            @Override // java.util.Iterator
            public B next() {
                return (B) this.this$0.correctedDoForward(this.fromIterator.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.fromIterator.remove();
            }
        };
    }

    public Converter() {
        this(true);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
        return new FunctionBasedConverter(forwardFunction, backwardFunction);
    }

    public static <T> Converter<T, T> identity() {
        return (IdentityConverter) IdentityConverter.INSTANCE;
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> secondConverter) {
        return doAndThen(secondConverter);
    }

    @Override // com.google.common.base.Function
    @InlineMe(replacement = "this.convert(a)")
    @Deprecated
    public final B apply(A a) {
        return correctedDoForward(a);
    }

    public final B convert(A a) {
        return correctedDoForward(a);
    }

    public Iterable<B> convertAll(final Iterable<? extends A> fromIterable) {
        Preconditions.checkNotNull(fromIterable, "fromIterable");
        return new Iterable() { // from class: com.google.common.base.Converter$$ExternalSyntheticLambda0
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Converter.$r8$lambda$9wmNQyD5bzY9Puwafq28xUluA4c(this.f$0, fromIterable);
            }
        };
    }

    public A correctedDoBackward(B b) {
        if (!this.handleNullAutomatically) {
            return doBackward(b);
        }
        if (b == null) {
            return null;
        }
        A aDoBackward = doBackward(b);
        aDoBackward.getClass();
        return aDoBackward;
    }

    public B correctedDoForward(A a) {
        if (!this.handleNullAutomatically) {
            return doForward(a);
        }
        if (a == null) {
            return null;
        }
        B bDoForward = doForward(a);
        bDoForward.getClass();
        return bDoForward;
    }

    public <C> Converter<A, C> doAndThen(Converter<B, C> secondConverter) {
        secondConverter.getClass();
        return new ConverterComposition(this, secondConverter);
    }

    @ForOverride
    public abstract A doBackward(B b);

    @ForOverride
    public abstract B doForward(A a);

    @Override // com.google.common.base.Function
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @CheckReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    public final A unsafeDoBackward(B b) {
        return doBackward(b);
    }

    public final B unsafeDoForward(A a) {
        return doForward(a);
    }

    public Converter(boolean handleNullAutomatically) {
        this.handleNullAutomatically = handleNullAutomatically;
    }
}
