package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f5 extends g5 {
    @Override // j$.util.stream.a
    public final boolean G0() {
        return false;
    }

    @Override // j$.util.stream.BaseStream
    public final BaseStream unordered() {
        return !c7.ORDERED.o(this.m) ? this : new a5(this, c7.r);
    }
}
