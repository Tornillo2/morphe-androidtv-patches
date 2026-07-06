package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public interface d2 {
    d2 a(int i);

    long count();

    void forEach(Consumer consumer);

    d2 i(long j, long j2, IntFunction intFunction);

    void k(Object[] objArr, int i);

    Object[] m(IntFunction intFunction);

    int o();

    Spliterator spliterator();
}
