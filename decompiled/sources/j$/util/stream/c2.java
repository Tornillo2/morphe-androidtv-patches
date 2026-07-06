package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public interface c2 extends d2 {
    @Override // j$.util.stream.d2
    c2 a(int i);

    Object b();

    void f(int i, Object obj);

    void g(Object obj);

    Object newArray(int i);

    @Override // j$.util.stream.d2
    Spliterator.OfPrimitive spliterator();
}
