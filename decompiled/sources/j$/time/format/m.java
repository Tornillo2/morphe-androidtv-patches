package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements f {
    public static final m INSENSITIVE;
    public static final m LENIENT;
    public static final m SENSITIVE;
    public static final m STRICT;
    public static final /* synthetic */ m[] a;

    @Override // j$.time.format.f
    public final boolean j(t tVar, StringBuilder sb) {
        return true;
    }

    public static m valueOf(String str) {
        return (m) Enum.valueOf(m.class, str);
    }

    public static m[] values() {
        return (m[]) a.clone();
    }

    static {
        m mVar = new m("SENSITIVE", 0);
        SENSITIVE = mVar;
        m mVar2 = new m("INSENSITIVE", 1);
        INSENSITIVE = mVar2;
        m mVar3 = new m("STRICT", 2);
        STRICT = mVar3;
        m mVar4 = new m("LENIENT", 3);
        LENIENT = mVar4;
        a = new m[]{mVar, mVar2, mVar3, mVar4};
    }

    @Override // j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            qVar.b = true;
            return i;
        }
        if (iOrdinal == 1) {
            qVar.b = false;
            return i;
        }
        if (iOrdinal == 2) {
            qVar.c = true;
            return i;
        }
        if (iOrdinal != 3) {
            return i;
        }
        qVar.c = false;
        return i;
    }

    @Override // java.lang.Enum
    public final String toString() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return "ParseCaseSensitive(true)";
        }
        if (iOrdinal == 1) {
            return "ParseCaseSensitive(false)";
        }
        if (iOrdinal == 2) {
            return "ParseStrict(true)";
        }
        if (iOrdinal == 3) {
            return "ParseStrict(false)";
        }
        throw new IllegalStateException("Unreachable");
    }
}
