package j$.time.chrono;

import j$.time.ZoneId;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* JADX INFO: loaded from: classes2.dex */
public final class f0 implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;
    public byte a;
    public Object b;

    public f0() {
    }

    public f0(byte b, Object obj) {
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
                objectOutput.writeUTF(((a) obj).i());
                return;
            case 2:
                g gVar = (g) obj;
                objectOutput.writeObject(gVar.a);
                objectOutput.writeObject(gVar.b);
                return;
            case 3:
                l lVar = (l) obj;
                objectOutput.writeObject(lVar.a);
                objectOutput.writeObject(lVar.b);
                objectOutput.writeObject(lVar.c);
                return;
            case 4:
                y yVar = (y) obj;
                yVar.getClass();
                objectOutput.writeInt(j$.time.temporal.s.a(yVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(yVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(yVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 5:
                objectOutput.writeByte(((z) obj).a);
                return;
            case 6:
                r rVar = (r) obj;
                objectOutput.writeObject(rVar.a);
                objectOutput.writeInt(j$.time.temporal.s.a(rVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(rVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(rVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 7:
                d0 d0Var = (d0) obj;
                d0Var.getClass();
                objectOutput.writeInt(j$.time.temporal.s.a(d0Var, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(d0Var, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(d0Var, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 8:
                j0 j0Var = (j0) obj;
                j0Var.getClass();
                objectOutput.writeInt(j$.time.temporal.s.a(j0Var, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(j0Var, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.s.a(j0Var, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 9:
                h hVar = (h) obj;
                objectOutput.writeUTF(hVar.a.i());
                objectOutput.writeInt(hVar.b);
                objectOutput.writeInt(hVar.c);
                objectOutput.writeInt(hVar.d);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Object objO;
        byte b = objectInput.readByte();
        this.a = b;
        switch (b) {
            case 1:
                ConcurrentHashMap concurrentHashMap = a.a;
                objO = j$.com.android.tools.r8.a.O(objectInput.readUTF());
                break;
            case 2:
                objO = ((b) objectInput.readObject()).E((j$.time.l) objectInput.readObject());
                break;
            case 3:
                objO = ((e) objectInput.readObject()).x((j$.time.z) objectInput.readObject()).u((ZoneId) objectInput.readObject());
                break;
            case 4:
                j$.time.h hVar = y.d;
                int i = objectInput.readInt();
                byte b2 = objectInput.readByte();
                byte b3 = objectInput.readByte();
                w.c.getClass();
                objO = new y(j$.time.h.X(i, b2, b3));
                break;
            case 5:
                z zVar = z.d;
                objO = z.m(objectInput.readByte());
                break;
            case 6:
                p pVar = (p) objectInput.readObject();
                int i2 = objectInput.readInt();
                byte b4 = objectInput.readByte();
                byte b5 = objectInput.readByte();
                pVar.getClass();
                objO = new r(pVar, i2, b4, b5);
                break;
            case 7:
                int i3 = objectInput.readInt();
                byte b6 = objectInput.readByte();
                byte b7 = objectInput.readByte();
                b0.c.getClass();
                objO = new d0(j$.time.h.X(i3 + 1911, b6, b7));
                break;
            case 8:
                int i4 = objectInput.readInt();
                byte b8 = objectInput.readByte();
                byte b9 = objectInput.readByte();
                h0.c.getClass();
                objO = new j0(j$.time.h.X(i4 - 543, b8, b9));
                break;
            case 9:
                int i5 = h.e;
                objO = new h(j$.com.android.tools.r8.a.O(objectInput.readUTF()), objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
                break;
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
        this.b = objO;
    }

    private Object readResolve() {
        return this.b;
    }
}
