package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class n3 extends o3 {
    public final Object[] h;

    public n3(Spliterator spliterator, w3 w3Var, Object[] objArr) {
        super(spliterator, w3Var, objArr.length);
        this.h = objArr;
    }

    public n3(n3 n3Var, Spliterator spliterator, long j, long j2) {
        super(n3Var, spliterator, j, j2, n3Var.h.length);
        this.h = n3Var.h;
    }

    @Override // j$.util.stream.o3
    public final o3 a(Spliterator spliterator, long j, long j2) {
        return new n3(this, spliterator, j, j2);
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        Object[] objArr = this.h;
        this.f = i + 1;
        objArr[i] = obj;
    }
}
