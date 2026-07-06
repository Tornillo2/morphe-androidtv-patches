package j$.time;

import com.google.common.base.Ascii;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class t implements j$.time.temporal.q, Serializable {
    public static final t d = new t(0, 0, 0);
    private static final long serialVersionUID = -3587258372562876L;
    public final int a;
    public final int b;
    public final int c;

    static {
        Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
        j$.com.android.tools.r8.a.G(new Object[]{j$.time.temporal.b.YEARS, j$.time.temporal.b.MONTHS, j$.time.temporal.b.DAYS});
    }

    public static t a(int i, int i2, int i3) {
        if ((i | i2 | i3) == 0) {
            return d;
        }
        return new t(i, i2, i3);
    }

    public t(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // j$.time.temporal.q
    public final j$.time.temporal.m j(j$.time.temporal.m mVar) {
        Objects.requireNonNull(mVar, "temporal");
        j$.time.chrono.m mVar2 = (j$.time.chrono.m) mVar.B(j$.time.temporal.s.b);
        if (mVar2 == null || j$.time.chrono.t.c.equals(mVar2)) {
            int i = this.b;
            if (i != 0) {
                long j = (((long) this.a) * 12) + ((long) i);
                if (j != 0) {
                    mVar = mVar.d(j, j$.time.temporal.b.MONTHS);
                }
            } else {
                int i2 = this.a;
                if (i2 != 0) {
                    mVar = mVar.d(i2, j$.time.temporal.b.YEARS);
                }
            }
            int i3 = this.c;
            return i3 != 0 ? mVar.d(i3, j$.time.temporal.b.DAYS) : mVar;
        }
        throw new b("Chronology mismatch, expected: ISO, actual: " + mVar2.i());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof t) {
            t tVar = (t) obj;
            if (this.a == tVar.a && this.b == tVar.b && this.c == tVar.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Integer.rotateLeft(this.c, 16) + Integer.rotateLeft(this.b, 8) + this.a;
    }

    public final String toString() {
        if (this == d) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder("P");
        int i = this.a;
        if (i != 0) {
            sb.append(i);
            sb.append('Y');
        }
        int i2 = this.b;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        int i3 = this.c;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }

    private Object writeReplace() {
        return new u(Ascii.SO, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
