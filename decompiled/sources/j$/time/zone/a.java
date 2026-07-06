package j$.time.zone;

import j$.time.z;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;
    public byte a;
    public Object b;

    public a() {
    }

    public a(byte b, Object obj) {
        this.a = b;
        this.b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b = this.a;
        Object obj = this.b;
        objectOutput.writeByte(b);
        if (b != 1) {
            if (b == 2) {
                b bVar = (b) obj;
                c(bVar.a, objectOutput);
                d(bVar.c, objectOutput);
                d(bVar.d, objectOutput);
                return;
            }
            if (b == 3) {
                ((e) obj).b(objectOutput);
                return;
            } else {
                if (b != 100) {
                    throw new InvalidClassException("Unknown serialized type");
                }
                objectOutput.writeUTF(((f) obj).g.getID());
                return;
            }
        }
        f fVar = (f) obj;
        objectOutput.writeInt(fVar.a.length);
        for (long j : fVar.a) {
            c(j, objectOutput);
        }
        for (z zVar : fVar.b) {
            d(zVar, objectOutput);
        }
        objectOutput.writeInt(fVar.c.length);
        for (long j2 : fVar.c) {
            c(j2, objectOutput);
        }
        for (z zVar2 : fVar.e) {
            d(zVar2, objectOutput);
        }
        objectOutput.writeByte(fVar.f.length);
        for (e eVar : fVar.f) {
            eVar.b(objectOutput);
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Object fVar;
        byte b = objectInput.readByte();
        this.a = b;
        if (b == 1) {
            long[] jArr = f.i;
            int i = objectInput.readInt();
            long[] jArr2 = i == 0 ? jArr : new long[i];
            for (int i2 = 0; i2 < i; i2++) {
                jArr2[i2] = a(objectInput);
            }
            int i3 = i + 1;
            z[] zVarArr = new z[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                zVarArr[i4] = b(objectInput);
            }
            int i5 = objectInput.readInt();
            if (i5 != 0) {
                jArr = new long[i5];
            }
            long[] jArr3 = jArr;
            for (int i6 = 0; i6 < i5; i6++) {
                jArr3[i6] = a(objectInput);
            }
            int i7 = i5 + 1;
            z[] zVarArr2 = new z[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                zVarArr2[i8] = b(objectInput);
            }
            int i9 = objectInput.readByte();
            e[] eVarArr = i9 == 0 ? f.j : new e[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                eVarArr[i10] = e.a(objectInput);
            }
            fVar = new f(jArr2, zVarArr, jArr3, zVarArr2, eVarArr);
        } else if (b == 2) {
            int i11 = b.e;
            long jA = a(objectInput);
            z zVarB = b(objectInput);
            z zVarB2 = b(objectInput);
            if (zVarB.equals(zVarB2)) {
                throw new IllegalArgumentException("Offsets must not be equal");
            }
            fVar = new b(jA, zVarB, zVarB2);
        } else if (b == 3) {
            fVar = e.a(objectInput);
        } else {
            if (b != 100) {
                throw new StreamCorruptedException("Unknown serialized type");
            }
            fVar = new f(TimeZone.getTimeZone(objectInput.readUTF()));
        }
        this.b = fVar;
    }

    private Object readResolve() {
        return this.b;
    }

    public static z b(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        return b == 127 ? z.U(dataInput.readInt()) : z.U(b * 900);
    }

    public static void c(long j, DataOutput dataOutput) throws IOException {
        if (j >= -4575744000L && j < 10413792000L && j % 900 == 0) {
            int i = (int) ((j + 4575744000L) / 900);
            dataOutput.writeByte((i >>> 16) & 255);
            dataOutput.writeByte((i >>> 8) & 255);
            dataOutput.writeByte(i & 255);
            return;
        }
        dataOutput.writeByte(255);
        dataOutput.writeLong(j);
    }

    public static long a(DataInput dataInput) {
        int i = dataInput.readByte() & 255;
        if (i == 255) {
            return dataInput.readLong();
        }
        return (((long) (((i << 16) + ((dataInput.readByte() & 255) << 8)) + (dataInput.readByte() & 255))) * 900) - 4575744000L;
    }

    public static void d(z zVar, DataOutput dataOutput) throws IOException {
        int i = zVar.b;
        int i2 = i % 900 == 0 ? i / 900 : 127;
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i);
        }
    }
}
