package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class w4 extends x4 {
    @Override // j$.util.stream.s4, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.b);
    }

    @Override // j$.util.stream.r4
    public final void j(r4 r4Var) {
        this.b += ((x4) r4Var).b;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b++;
    }
}
