package j$.util;

import j$.util.Spliterator;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class Spliterators {
    public static final h1 a = new h1();
    public static final f1 b = new f1();
    public static final g1 c = new g1();
    public static final e1 d = new e1();

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2, int i3) {
        a(((int[]) Objects.requireNonNull(iArr)).length, i, i2);
        return new i1(iArr, i, i2, i3);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2, int i3) {
        a(((long[]) Objects.requireNonNull(jArr)).length, i, i2);
        return new k1(jArr, i, i2, i3);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2, int i3) {
        a(((double[]) Objects.requireNonNull(dArr)).length, i, i2);
        return new d1(dArr, i, i2, i3);
    }

    public static void a(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException(i2);
            }
            if (i3 > i) {
                throw new ArrayIndexOutOfBoundsException(i3);
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
    }

    public static <T> Spliterator<T> spliterator(java.util.Collection<? extends T> collection, int i) {
        return new j1((java.util.Collection) Objects.requireNonNull(collection), i);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> it, int i) {
        return new j1((Iterator) Objects.requireNonNull(it), i);
    }

    public static <T> Iterator<T> iterator(Spliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        return new x0(spliterator);
    }

    public static PrimitiveIterator$OfInt iterator(Spliterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new y0(ofInt);
    }

    public static PrimitiveIterator$OfLong iterator(Spliterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new z0(ofLong);
    }

    public static PrimitiveIterator$OfDouble iterator(Spliterator.OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new a1(ofDouble);
    }

    public static abstract class AbstractSpliterator<T> implements Spliterator<T> {
        public final int a;
        public long b;
        public int c;

        @Override // j$.util.Spliterator
        public /* synthetic */ void forEachRemaining(Consumer consumer) {
            Spliterator.CC.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ java.util.Comparator getComparator() {
            return Spliterator.CC.$default$getComparator(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ long getExactSizeIfKnown() {
            return Spliterator.CC.$default$getExactSizeIfKnown(this);
        }

        @Override // j$.util.Spliterator
        public /* synthetic */ boolean hasCharacteristics(int i) {
            return Spliterator.CC.$default$hasCharacteristics(this, i);
        }

        public AbstractSpliterator(long j, int i) {
            this.b = j;
            this.a = (i & 64) != 0 ? i | 16384 : i;
        }

        @Override // j$.util.Spliterator
        public final Spliterator trySplit() {
            b1 b1Var = new b1();
            long j = this.b;
            if (j <= 1 || !tryAdvance(b1Var)) {
                return null;
            }
            int i = this.c + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = b1Var.a;
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (tryAdvance(b1Var));
            this.c = i2;
            long j2 = this.b;
            if (j2 != Long.MAX_VALUE) {
                this.b = j2 - ((long) i2);
            }
            return new c1(objArr, 0, i2, this.a);
        }

        @Override // j$.util.Spliterator
        public final long estimateSize() {
            return this.b;
        }

        @Override // j$.util.Spliterator
        public final int characteristics() {
            return this.a;
        }
    }
}
