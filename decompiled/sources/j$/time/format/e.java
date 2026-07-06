package j$.time.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements f {
    public final f[] a;
    public final boolean b;

    /* JADX WARN: Illegal instructions before constructor call */
    public e(List list, boolean z) {
        ArrayList arrayList = (ArrayList) list;
        this((f[]) arrayList.toArray(new f[arrayList.size()]), z);
    }

    public e(f[] fVarArr, boolean z) {
        this.a = fVarArr;
        this.b = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        if (r2 != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002f, code lost:
    
        return true;
     */
    @Override // j$.time.format.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean j(j$.time.format.t r8, java.lang.StringBuilder r9) {
        /*
            r7 = this;
            int r0 = r9.length()
            r1 = 1
            boolean r2 = r7.b
            if (r2 == 0) goto Le
            int r3 = r8.c
            int r3 = r3 + r1
            r8.c = r3
        Le:
            j$.time.format.f[] r3 = r7.a     // Catch: java.lang.Throwable -> L27
            int r4 = r3.length     // Catch: java.lang.Throwable -> L27
            r5 = 0
        L12:
            if (r5 >= r4) goto L2c
            r6 = r3[r5]     // Catch: java.lang.Throwable -> L27
            boolean r6 = r6.j(r8, r9)     // Catch: java.lang.Throwable -> L27
            if (r6 != 0) goto L29
            r9.setLength(r0)     // Catch: java.lang.Throwable -> L27
            if (r2 == 0) goto L2f
        L21:
            int r9 = r8.c
            int r9 = r9 - r1
            r8.c = r9
            return r1
        L27:
            r9 = move-exception
            goto L30
        L29:
            int r5 = r5 + 1
            goto L12
        L2c:
            if (r2 == 0) goto L2f
            goto L21
        L2f:
            return r1
        L30:
            if (r2 == 0) goto L37
            int r0 = r8.c
            int r0 = r0 - r1
            r8.c = r0
        L37:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.e.j(j$.time.format.t, java.lang.StringBuilder):boolean");
    }

    @Override // j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        boolean z = this.b;
        f[] fVarArr = this.a;
        int i2 = 0;
        if (z) {
            ArrayList arrayList = qVar.d;
            x xVarC = qVar.c();
            xVarC.getClass();
            x xVar = new x();
            ((HashMap) xVar.a).putAll(xVarC.a);
            xVar.b = xVarC.b;
            xVar.c = xVarC.c;
            xVar.d = xVarC.d;
            arrayList.add(xVar);
            int length = fVarArr.length;
            int iK = i;
            while (i2 < length) {
                iK = fVarArr[i2].k(qVar, charSequence, iK);
                if (iK < 0) {
                    qVar.d.remove(r8.size() - 1);
                    return i;
                }
                i2++;
            }
            qVar.d.remove(r8.size() - 2);
            return iK;
        }
        int length2 = fVarArr.length;
        while (i2 < length2) {
            i = fVarArr[i2].k(qVar, charSequence, i);
            if (i < 0) {
                return i;
            }
            i2++;
        }
        return i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        f[] fVarArr = this.a;
        if (fVarArr != null) {
            boolean z = this.b;
            sb.append(z ? "[" : "(");
            for (f fVar : fVarArr) {
                sb.append(fVar);
            }
            sb.append(z ? "]" : ")");
        }
        return sb.toString();
    }
}
