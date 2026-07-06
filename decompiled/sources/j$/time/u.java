package j$.time;

import j$.util.Objects;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* JADX INFO: loaded from: classes2.dex */
public final class u implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;
    public byte a;
    public Object b;

    public u() {
    }

    public u(byte b, Object obj) {
        this.a = b;
        this.b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b = this.a;
        Object obj = this.b;
        objectOutput.writeByte(b);
        switch (b) {
            case 1:
                Duration duration = (Duration) obj;
                objectOutput.writeLong(duration.a);
                objectOutput.writeInt(duration.b);
                return;
            case 2:
                Instant instant = (Instant) obj;
                objectOutput.writeLong(instant.a);
                objectOutput.writeInt(instant.b);
                return;
            case 3:
                h hVar = (h) obj;
                objectOutput.writeInt(hVar.a);
                objectOutput.writeByte(hVar.b);
                objectOutput.writeByte(hVar.c);
                return;
            case 4:
                ((l) obj).d0(objectOutput);
                return;
            case 5:
                j jVar = (j) obj;
                h hVar2 = jVar.a;
                objectOutput.writeInt(hVar2.a);
                objectOutput.writeByte(hVar2.b);
                objectOutput.writeByte(hVar2.c);
                jVar.b.d0(objectOutput);
                return;
            case 6:
                c0 c0Var = (c0) obj;
                j jVar2 = c0Var.a;
                h hVar3 = jVar2.a;
                objectOutput.writeInt(hVar3.a);
                objectOutput.writeByte(hVar3.b);
                objectOutput.writeByte(hVar3.c);
                jVar2.b.d0(objectOutput);
                c0Var.b.X(objectOutput);
                c0Var.c.R(objectOutput);
                return;
            case 7:
                objectOutput.writeUTF(((a0) obj).b);
                return;
            case 8:
                ((z) obj).X(objectOutput);
                return;
            case 9:
                s sVar = (s) obj;
                sVar.a.d0(objectOutput);
                sVar.b.X(objectOutput);
                return;
            case 10:
                r rVar = (r) obj;
                j jVar3 = rVar.a;
                h hVar4 = jVar3.a;
                objectOutput.writeInt(hVar4.a);
                objectOutput.writeByte(hVar4.b);
                objectOutput.writeByte(hVar4.c);
                jVar3.b.d0(objectOutput);
                rVar.b.X(objectOutput);
                return;
            case 11:
                objectOutput.writeInt(((w) obj).a);
                return;
            case 12:
                y yVar = (y) obj;
                objectOutput.writeInt(yVar.a);
                objectOutput.writeByte(yVar.b);
                return;
            case 13:
                p pVar = (p) obj;
                objectOutput.writeByte(pVar.a);
                objectOutput.writeByte(pVar.b);
                return;
            case 14:
                t tVar = (t) obj;
                objectOutput.writeInt(tVar.a);
                objectOutput.writeInt(tVar.b);
                objectOutput.writeInt(tVar.c);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        byte b = objectInput.readByte();
        this.a = b;
        this.b = a(b, objectInput);
    }

    public static Object a(byte b, ObjectInput objectInput) throws IOException {
        switch (b) {
            case 1:
                Duration duration = Duration.c;
                return Duration.ofSeconds(objectInput.readLong(), objectInput.readInt());
            case 2:
                Instant instant = Instant.c;
                return Instant.ofEpochSecond(objectInput.readLong(), objectInput.readInt());
            case 3:
                h hVar = h.d;
                return h.X(objectInput.readInt(), objectInput.readByte(), objectInput.readByte());
            case 4:
                return l.Y(objectInput);
            case 5:
                j jVar = j.c;
                h hVar2 = h.d;
                return j.Q(h.X(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), l.Y(objectInput));
            case 6:
                j jVar2 = j.c;
                h hVar3 = h.d;
                j jVarQ = j.Q(h.X(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), l.Y(objectInput));
                z zVarW = z.W(objectInput);
                ZoneId zoneId = (ZoneId) a(objectInput.readByte(), objectInput);
                Objects.requireNonNull(jVarQ, "localDateTime");
                Objects.requireNonNull(zVarW, "offset");
                Objects.requireNonNull(zoneId, "zone");
                if (!(zoneId instanceof z) || zVarW.equals(zoneId)) {
                    return new c0(jVarQ, zoneId, zVarW);
                }
                throw new IllegalArgumentException("ZoneId must match ZoneOffset");
            case 7:
                int i = a0.d;
                return ZoneId.O(objectInput.readUTF(), false);
            case 8:
                return z.W(objectInput);
            case 9:
                int i2 = s.c;
                return new s(l.Y(objectInput), z.W(objectInput));
            case 10:
                int i3 = r.c;
                h hVar4 = h.d;
                return new r(j.Q(h.X(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), l.Y(objectInput)), z.W(objectInput));
            case 11:
                int i4 = w.b;
                return w.N(objectInput.readInt());
            case 12:
                int i5 = y.c;
                int i6 = objectInput.readInt();
                byte b2 = objectInput.readByte();
                j$.time.temporal.a.YEAR.C(i6);
                j$.time.temporal.a.MONTH_OF_YEAR.C(b2);
                return new y(i6, b2);
            case 13:
                int i7 = p.c;
                byte b3 = objectInput.readByte();
                byte b4 = objectInput.readByte();
                n nVarQ = n.Q(b3);
                Objects.requireNonNull(nVarQ, "month");
                j$.time.temporal.a.DAY_OF_MONTH.C(b4);
                if (b4 <= nVarQ.P()) {
                    return new p(nVarQ.getValue(), b4);
                }
                throw new b("Illegal value for DayOfMonth field, value " + ((int) b4) + " is not valid for month " + nVarQ.name());
            case 14:
                t tVar = t.d;
                return t.a(objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
    }

    private Object readResolve() {
        return this.b;
    }
}
