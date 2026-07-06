package j$.time.zone;

import j$.time.j;
import j$.time.z;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements Comparable, Serializable {
    public static final /* synthetic */ int e = 0;
    private static final long serialVersionUID = -6946044323557704546L;
    public final long a;
    public final j b;
    public final z c;
    public final z d;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.a, ((b) obj).a);
    }

    public b(j jVar, z zVar, z zVar2) {
        jVar.getClass();
        this.a = j$.com.android.tools.r8.a.q(jVar, zVar);
        this.b = jVar;
        this.c = zVar;
        this.d = zVar2;
    }

    public b(long j, z zVar, z zVar2) {
        this.a = j;
        this.b = j.R(j, 0, zVar);
        this.c = zVar;
        this.d = zVar2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 2, this);
    }

    public final boolean j() {
        return this.d.b > this.c.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this.a == bVar.a && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.b.hashCode() ^ this.c.b) ^ Integer.rotateLeft(this.d.b, 16);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Transition[");
        sb.append(j() ? "Gap" : "Overlap");
        sb.append(" at ");
        sb.append(this.b);
        sb.append(this.c);
        sb.append(" to ");
        sb.append(this.d);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
