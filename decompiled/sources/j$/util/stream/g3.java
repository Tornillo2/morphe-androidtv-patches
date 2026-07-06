package j$.util.stream;

import j$.util.Spliterator;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g3 implements Spliterator {
    public d2 a;
    public int b;
    public Spliterator c;
    public Spliterator d;
    public Deque e;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 64;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Comparator getComparator() {
        return Spliterator.CC.$default$getComparator(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return Spliterator.CC.$default$getExactSizeIfKnown(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    public g3(d2 d2Var) {
        this.a = d2Var;
    }

    public final Deque b() {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        int iO = this.a.o();
        while (true) {
            iO--;
            if (iO < this.b) {
                return arrayDeque;
            }
            arrayDeque.addFirst(this.a.a(iO));
        }
    }

    public static d2 a(Deque deque) {
        while (true) {
            ArrayDeque arrayDeque = (ArrayDeque) deque;
            d2 d2Var = (d2) arrayDeque.pollFirst();
            if (d2Var == null) {
                return null;
            }
            if (d2Var.o() != 0) {
                for (int iO = d2Var.o() - 1; iO >= 0; iO--) {
                    arrayDeque.addFirst(d2Var.a(iO));
                }
            } else if (d2Var.count() > 0) {
                return d2Var;
            }
        }
    }

    public final boolean c() {
        if (this.a == null) {
            return false;
        }
        if (this.d != null) {
            return true;
        }
        Spliterator spliterator = this.c;
        if (spliterator == null) {
            Deque dequeB = b();
            this.e = dequeB;
            d2 d2VarA = a(dequeB);
            if (d2VarA != null) {
                this.d = d2VarA.spliterator();
                return true;
            }
            this.a = null;
            return false;
        }
        this.d = spliterator;
        return true;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        d2 d2Var = this.a;
        if (d2Var == null || this.d != null) {
            return null;
        }
        Spliterator spliterator = this.c;
        if (spliterator != null) {
            return spliterator.trySplit();
        }
        if (this.b < d2Var.o() - 1) {
            d2 d2Var2 = this.a;
            int i = this.b;
            this.b = i + 1;
            return d2Var2.a(i).spliterator();
        }
        d2 d2VarA = this.a.a(this.b);
        this.a = d2VarA;
        if (d2VarA.o() == 0) {
            Spliterator spliterator2 = this.a.spliterator();
            this.c = spliterator2;
            return spliterator2.trySplit();
        }
        d2 d2Var3 = this.a;
        this.b = 1;
        return d2Var3.a(0).spliterator();
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        long jCount = 0;
        if (this.a == null) {
            return 0L;
        }
        Spliterator spliterator = this.c;
        if (spliterator != null) {
            return spliterator.estimateSize();
        }
        for (int i = this.b; i < this.a.o(); i++) {
            jCount += this.a.a(i).count();
        }
        return jCount;
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfPrimitive) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) trySplit();
    }
}
