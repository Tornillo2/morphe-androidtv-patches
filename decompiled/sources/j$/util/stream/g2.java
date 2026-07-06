package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class g2 implements d2 {
    public final Object[] a;
    public int b;

    @Override // j$.util.stream.d2
    public final /* synthetic */ d2 i(long j, long j2, IntFunction intFunction) {
        return w3.V(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.d2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.d2
    public final d2 a(int i) {
        throw new IndexOutOfBoundsException();
    }

    public g2(long j, IntFunction intFunction) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = (Object[]) intFunction.apply((int) j);
        this.b = 0;
    }

    public g2(Object[] objArr) {
        this.a = objArr;
        this.b = objArr.length;
    }

    @Override // j$.util.stream.d2
    public final Spliterator spliterator() {
        int i = this.b;
        Object[] objArr = this.a;
        Spliterators.a(((Object[]) Objects.requireNonNull(objArr)).length, 0, i);
        return new j$.util.c1(objArr, 0, i, 1040);
    }

    @Override // j$.util.stream.d2
    public final void k(Object[] objArr, int i) {
        System.arraycopy(this.a, 0, objArr, i, this.b);
    }

    @Override // j$.util.stream.d2
    public final Object[] m(IntFunction intFunction) {
        Object[] objArr = this.a;
        if (objArr.length == this.b) {
            return objArr;
        }
        throw new IllegalStateException();
    }

    @Override // j$.util.stream.d2
    public final long count() {
        return this.b;
    }

    @Override // j$.util.stream.d2
    public final void forEach(Consumer consumer) {
        for (int i = 0; i < this.b; i++) {
            consumer.n(this.a[i]);
        }
    }

    public String toString() {
        Object[] objArr = this.a;
        return String.format("ArrayNode[%d][%s]", Integer.valueOf(objArr.length - this.b), Arrays.toString(objArr));
    }
}
