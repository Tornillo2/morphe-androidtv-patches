package j$.util.stream;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class g6 extends c6 {
    public w6 c;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.w6, j$.util.stream.y6] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.y6] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.y6] */
    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final void c(long j) {
        ?? y6Var;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            y6Var = new y6();
        } else {
            y6Var = new w6((int) j);
        }
        this.c = y6Var;
    }

    @Override // j$.util.stream.j5, j$.util.stream.o5
    public final void end() {
        long[] jArr = (long[]) this.c.b();
        Arrays.sort(jArr);
        long length = jArr.length;
        o5 o5Var = this.a;
        o5Var.c(length);
        int i = 0;
        if (!this.b) {
            int length2 = jArr.length;
            while (i < length2) {
                o5Var.accept(jArr[i]);
                i++;
            }
        } else {
            int length3 = jArr.length;
            while (i < length3) {
                long j = jArr[i];
                if (o5Var.e()) {
                    break;
                }
                o5Var.accept(j);
                i++;
            }
        }
        o5Var.end();
    }

    @Override // j$.util.stream.n5, j$.util.stream.o5, java.util.function.LongConsumer
    public final void accept(long j) {
        this.c.accept(j);
    }
}
