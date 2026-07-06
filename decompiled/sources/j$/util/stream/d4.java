package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class d4 extends w3 {
    public final /* synthetic */ int h;

    public /* synthetic */ d4(int i) {
        this.h = i;
    }

    @Override // j$.util.stream.w3
    public final r4 u0() {
        switch (this.h) {
            case 0:
                return new v4();
            case 1:
                return new t4();
            case 2:
                return new w4();
            default:
                return new u4();
        }
    }

    @Override // j$.util.stream.w3, j$.util.stream.t8
    public final Object f(a aVar, Spliterator spliterator) {
        switch (this.h) {
            case 0:
                if (!c7.SIZED.o(aVar.m)) {
                }
                break;
            case 1:
                if (!c7.SIZED.o(aVar.m)) {
                }
                break;
            case 2:
                if (!c7.SIZED.o(aVar.m)) {
                }
                break;
            default:
                if (!c7.SIZED.o(aVar.m)) {
                }
                break;
        }
        return (Long) super.f(aVar, spliterator);
    }

    @Override // j$.util.stream.w3, j$.util.stream.t8
    public final Object i(w3 w3Var, Spliterator spliterator) {
        switch (this.h) {
            case 0:
                if (!c7.SIZED.o(((a) w3Var).m)) {
                }
                break;
            case 1:
                if (!c7.SIZED.o(((a) w3Var).m)) {
                }
                break;
            case 2:
                if (!c7.SIZED.o(((a) w3Var).m)) {
                }
                break;
            default:
                if (!c7.SIZED.o(((a) w3Var).m)) {
                }
                break;
        }
        return (Long) super.i(w3Var, spliterator);
    }

    @Override // j$.util.stream.w3, j$.util.stream.t8
    public final int v() {
        switch (this.h) {
        }
        return c7.r;
    }
}
