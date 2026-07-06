package j$.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class z implements n, Serializable {
    public static final z d;
    public static final z[] e;
    private static final long serialVersionUID = 1466499369062886794L;
    public final transient int a;
    public final transient j$.time.h b;
    public final transient String c;

    @Override // j$.time.temporal.n
    public final /* synthetic */ Object B(j$.time.e eVar) {
        return j$.com.android.tools.r8.a.p(this, eVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ long C(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.j(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ boolean e(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.l(this, rVar);
    }

    @Override // j$.time.temporal.n
    public final /* synthetic */ int j(j$.time.temporal.r rVar) {
        return j$.com.android.tools.r8.a.i(this, rVar);
    }

    static {
        z zVar = new z(-1, j$.time.h.X(1868, 1, 1), "Meiji");
        d = zVar;
        e = new z[]{zVar, new z(0, j$.time.h.X(1912, 7, 30), "Taisho"), new z(1, j$.time.h.X(1926, 12, 25), "Showa"), new z(2, j$.time.h.X(1989, 1, 8), "Heisei"), new z(3, j$.time.h.X(2019, 5, 1), "Reiwa")};
    }

    public final z i() {
        if (this == e[r0.length - 1]) {
            return null;
        }
        return m(this.a + 1);
    }

    public z(int i, j$.time.h hVar, String str) {
        this.a = i;
        this.b = hVar;
        this.c = str;
    }

    public static z m(int i) {
        int i2 = i + 1;
        if (i2 >= 0) {
            z[] zVarArr = e;
            if (i2 < zVarArr.length) {
                return zVarArr[i2];
            }
        }
        throw new j$.time.b("Invalid era: " + i);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.m o(j$.time.temporal.m mVar) {
        return mVar.c(getValue(), j$.time.temporal.a.ERA);
    }

    public static z h(j$.time.h hVar) {
        if (hVar.T(y.d)) {
            throw new j$.time.b("JapaneseDate before Meiji 6 are not supported");
        }
        for (int length = e.length - 1; length >= 0; length--) {
            z zVar = e[length];
            if (hVar.compareTo(zVar.b) >= 0) {
                return zVar;
            }
        }
        return null;
    }

    @Override // j$.time.chrono.n
    public final int getValue() {
        return this.a;
    }

    @Override // j$.time.temporal.n
    public final j$.time.temporal.v l(j$.time.temporal.r rVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        if (rVar != aVar) {
            return j$.time.temporal.s.d(this, rVar);
        }
        return w.c.q(aVar);
    }

    public final String toString() {
        return this.c;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new f0((byte) 5, this);
    }
}
