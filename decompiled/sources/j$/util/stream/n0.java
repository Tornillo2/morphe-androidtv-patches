package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class n0 extends o0 {
    public final Consumer b;

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.t8
    public final Object f(a aVar, Spliterator spliterator) {
        aVar.v0(spliterator, this);
        return null;
    }

    @Override // j$.util.stream.t8
    public final /* bridge */ /* synthetic */ Object i(w3 w3Var, Spliterator spliterator) {
        a(w3Var, spliterator);
        return null;
    }

    public n0(Consumer consumer, boolean z) {
        super(z);
        this.b = consumer;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.b.n(obj);
    }
}
