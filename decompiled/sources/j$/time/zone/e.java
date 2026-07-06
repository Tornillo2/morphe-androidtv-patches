package j$.time.zone;

import com.google.common.base.Ascii;
import j$.time.l;
import j$.time.n;
import j$.time.z;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;
    public final n a;
    public final byte b;
    public final j$.time.d c;
    public final l d;
    public final boolean e;
    public final d f;
    public final z g;
    public final z h;
    public final z i;

    public e(n nVar, int i, j$.time.d dVar, l lVar, boolean z, d dVar2, z zVar, z zVar2, z zVar3) {
        this.a = nVar;
        this.b = (byte) i;
        this.c = dVar;
        this.d = lVar;
        this.e = z;
        this.f = dVar2;
        this.g = zVar;
        this.h = zVar2;
        this.i = zVar3;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 3, this);
    }

    public final void b(DataOutput dataOutput) {
        int iA0 = this.e ? 86400 : this.d.a0();
        int i = this.g.b;
        int i2 = this.h.b - i;
        int i3 = this.i.b - i;
        byte b = iA0 % 3600 == 0 ? this.e ? Ascii.CAN : this.d.a : (byte) 31;
        int i4 = i % 900 == 0 ? (i / 900) + 128 : 255;
        int i5 = (i2 == 0 || i2 == 1800 || i2 == 3600) ? i2 / 1800 : 3;
        int i6 = (i3 == 0 || i3 == 1800 || i3 == 3600) ? i3 / 1800 : 3;
        j$.time.d dVar = this.c;
        dataOutput.writeInt((this.a.getValue() << 28) + ((this.b + 32) << 22) + ((dVar == null ? 0 : dVar.getValue()) << 19) + (b << Ascii.SO) + (this.f.ordinal() << 12) + (i4 << 4) + (i5 << 2) + i6);
        if (b == 31) {
            dataOutput.writeInt(iA0);
        }
        if (i4 == 255) {
            dataOutput.writeInt(i);
        }
        if (i5 == 3) {
            dataOutput.writeInt(this.h.b);
        }
        if (i6 == 3) {
            dataOutput.writeInt(this.i.b);
        }
    }

    public static e a(DataInput dataInput) {
        d dVar;
        l lVarO;
        int i;
        int i2;
        int i3 = dataInput.readInt();
        n nVarQ = n.Q(i3 >>> 28);
        int i4 = ((264241152 & i3) >>> 22) - 32;
        int i5 = (3670016 & i3) >>> 19;
        j$.time.d dVarN = i5 == 0 ? null : j$.time.d.N(i5);
        int i6 = (507904 & i3) >>> 14;
        d dVar2 = d.values()[(i3 & 12288) >>> 12];
        int i7 = (i3 & 4080) >>> 4;
        int i8 = (i3 & 12) >>> 2;
        int i9 = i3 & 3;
        if (i6 == 31) {
            long j = dataInput.readInt();
            l lVar = l.e;
            j$.time.temporal.a.SECOND_OF_DAY.C(j);
            int i10 = (int) (j / 3600);
            dVar = dVar2;
            long j2 = j - ((long) (i10 * 3600));
            int i11 = (int) (j2 / 60);
            lVarO = l.O(i10, i11, (int) (j2 - ((long) (i11 * 60))), 0);
        } else {
            dVar = dVar2;
            int i12 = i6 % 24;
            l lVar2 = l.e;
            j$.time.temporal.a.HOUR_OF_DAY.C(i12);
            lVarO = l.h[i12];
        }
        z zVarU = z.U(i7 == 255 ? dataInput.readInt() : (i7 - 128) * 900);
        if (i8 == 3) {
            i = dataInput.readInt();
        } else {
            i = (i8 * 1800) + zVarU.b;
        }
        z zVarU2 = z.U(i);
        if (i9 == 3) {
            i2 = dataInput.readInt();
        } else {
            i2 = (i9 * 1800) + zVarU.b;
        }
        z zVarU3 = z.U(i2);
        boolean z = i6 == 24;
        Objects.requireNonNull(nVarQ, "month");
        Objects.requireNonNull(lVarO, "time");
        Objects.requireNonNull(dVar, "timeDefnition");
        Objects.requireNonNull(zVarU, "standardOffset");
        Objects.requireNonNull(zVarU2, "offsetBefore");
        Objects.requireNonNull(zVarU3, "offsetAfter");
        if (i4 < -28 || i4 > 31 || i4 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !lVarO.equals(l.g)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (lVarO.d != 0) {
            throw new IllegalArgumentException("Time's nano-of-second must be zero");
        }
        return new e(nVarQ, i4, dVarN, lVarO, z, dVar, zVarU, zVarU2, zVarU3);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (this.a == eVar.a && this.b == eVar.b && this.c == eVar.c && this.f == eVar.f && this.d.equals(eVar.d) && this.e == eVar.e && this.g.equals(eVar.g) && this.h.equals(eVar.h) && this.i.equals(eVar.i)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iA0 = ((this.d.a0() + (this.e ? 1 : 0)) << 15) + (this.a.ordinal() << 11) + ((this.b + 32) << 5);
        j$.time.d dVar = this.c;
        return ((this.g.b ^ (this.f.ordinal() + (iA0 + ((dVar == null ? 7 : dVar.ordinal()) << 2)))) ^ this.h.b) ^ this.i.b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TransitionRule[");
        sb.append(this.i.b - this.h.b > 0 ? "Gap " : "Overlap ");
        sb.append(this.h);
        sb.append(" to ");
        sb.append(this.i);
        sb.append(", ");
        j$.time.d dVar = this.c;
        if (dVar != null) {
            byte b = this.b;
            if (b == -1) {
                sb.append(dVar.name());
                sb.append(" on or before last day of ");
                sb.append(this.a.name());
            } else if (b < 0) {
                sb.append(dVar.name());
                sb.append(" on or before last day minus ");
                sb.append((-this.b) - 1);
                sb.append(" of ");
                sb.append(this.a.name());
            } else {
                sb.append(dVar.name());
                sb.append(" on or after ");
                sb.append(this.a.name());
                sb.append(' ');
                sb.append((int) this.b);
            }
        } else {
            sb.append(this.a.name());
            sb.append(' ');
            sb.append((int) this.b);
        }
        sb.append(" at ");
        sb.append(this.e ? "24:00" : this.d.toString());
        sb.append(StringUtils.SPACE);
        sb.append(this.f);
        sb.append(", standard offset ");
        sb.append(this.g);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
