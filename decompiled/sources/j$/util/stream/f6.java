package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class f6 extends b6 {
    public u6 c;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.u6, j$.util.stream.y6] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.y6] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.y6] */
    @Override // j$.util.stream.i5, j$.util.stream.o5
    public final void c(long j) {
        ?? y6Var;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            y6Var = new y6();
        } else {
            y6Var = new u6((int) j);
        }
        this.c = y6Var;
    }

    @Override // j$.util.stream.i5, j$.util.stream.o5
    public final void end() {
        int[] iArr = (int[]) this.c.b();
        Arrays.sort(iArr);
        long length = iArr.length;
        o5 o5Var = this.a;
        o5Var.c(length);
        int i = 0;
        if (!this.b) {
            int length2 = iArr.length;
            while (i < length2) {
                o5Var.accept(iArr[i]);
                i++;
            }
        } else {
            int length3 = iArr.length;
            while (i < length3) {
                int i2 = iArr[i];
                if (o5Var.e()) {
                    break;
                }
                o5Var.accept(i2);
                i++;
            }
        }
        o5Var.end();
    }

    @Override // j$.util.stream.m5, j$.util.stream.o5
    public final void accept(int i) {
        this.c.accept(i);
    }
}
