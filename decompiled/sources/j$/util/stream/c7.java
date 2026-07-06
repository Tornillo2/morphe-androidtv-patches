package j$.util.stream;

import j$.util.Map;
import j$.util.Spliterator;
import java.util.EnumMap;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DISTINCT' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class c7 {
    public static final c7 DISTINCT;
    public static final c7 ORDERED;
    public static final c7 SHORT_CIRCUIT;
    public static final c7 SIZED;
    public static final c7 SORTED;
    public static final int f;
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final int l;
    public static final int m;
    public static final int n;
    public static final int o;
    public static final int p;
    public static final int q;
    public static final int r;
    public static final int s;
    public static final int t;
    public static final int u;
    public static final /* synthetic */ c7[] v;
    public final Map a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public static c7 valueOf(String str) {
        return (c7) Enum.valueOf(c7.class, str);
    }

    public static c7[] values() {
        return (c7[]) v.clone();
    }

    static {
        b7 b7Var = b7.SPLITERATOR;
        j$.util.q qVarW = w(b7Var);
        b7 b7Var2 = b7.STREAM;
        qVarW.a(b7Var2);
        b7 b7Var3 = b7.OP;
        ((EnumMap) ((Map) qVarW.b)).put(b7Var3, 3);
        c7 c7Var = new c7("DISTINCT", 0, 0, qVarW);
        DISTINCT = c7Var;
        j$.util.q qVarW2 = w(b7Var);
        qVarW2.a(b7Var2);
        ((EnumMap) ((Map) qVarW2.b)).put(b7Var3, 3);
        c7 c7Var2 = new c7("SORTED", 1, 1, qVarW2);
        SORTED = c7Var2;
        j$.util.q qVarW3 = w(b7Var);
        qVarW3.a(b7Var2);
        ((EnumMap) ((Map) qVarW3.b)).put(b7Var3, 3);
        b7 b7Var4 = b7.TERMINAL_OP;
        ((EnumMap) ((Map) qVarW3.b)).put(b7Var4, 2);
        b7 b7Var5 = b7.UPSTREAM_TERMINAL_OP;
        ((EnumMap) ((Map) qVarW3.b)).put(b7Var5, 2);
        c7 c7Var3 = new c7("ORDERED", 2, 2, qVarW3);
        ORDERED = c7Var3;
        j$.util.q qVarW4 = w(b7Var);
        qVarW4.a(b7Var2);
        ((EnumMap) ((Map) qVarW4.b)).put(b7Var3, 2);
        c7 c7Var4 = new c7("SIZED", 3, 3, qVarW4);
        SIZED = c7Var4;
        j$.util.q qVarW5 = w(b7Var3);
        qVarW5.a(b7Var4);
        int i2 = 0;
        c7 c7Var5 = new c7("SHORT_CIRCUIT", 4, 12, qVarW5);
        SHORT_CIRCUIT = c7Var5;
        v = new c7[]{c7Var, c7Var2, c7Var3, c7Var4, c7Var5};
        f = k(b7Var);
        g = k(b7Var2);
        h = k(b7Var3);
        k(b7Var4);
        k(b7Var5);
        for (c7 c7Var6 : values()) {
            i2 |= c7Var6.e;
        }
        i = i2;
        int i3 = g;
        j = i3;
        int i4 = i3 << 1;
        k = i4;
        l = i3 | i4;
        c7 c7Var7 = DISTINCT;
        m = c7Var7.c;
        n = c7Var7.d;
        c7 c7Var8 = SORTED;
        o = c7Var8.c;
        p = c7Var8.d;
        c7 c7Var9 = ORDERED;
        q = c7Var9.c;
        r = c7Var9.d;
        c7 c7Var10 = SIZED;
        s = c7Var10.c;
        t = c7Var10.d;
        u = SHORT_CIRCUIT.c;
    }

    public static j$.util.q w(b7 b7Var) {
        j$.util.q qVar = new j$.util.q(10, new EnumMap(b7.class));
        qVar.a(b7Var);
        return qVar;
    }

    public c7(String str, int i2, int i3, j$.util.q qVar) {
        for (b7 b7Var : b7.values()) {
            Map.EL.a((java.util.Map) qVar.b, b7Var, 0);
        }
        this.a = (java.util.Map) qVar.b;
        int i4 = i3 * 2;
        this.b = i4;
        this.c = 1 << i4;
        this.d = 2 << i4;
        this.e = 3 << i4;
    }

    public final boolean o(int i2) {
        return (i2 & this.e) == this.c;
    }

    public static int k(b7 b7Var) {
        int iIntValue = 0;
        for (c7 c7Var : values()) {
            iIntValue |= ((Integer) c7Var.a.get(b7Var)).intValue() << c7Var.b;
        }
        return iIntValue;
    }

    public static int j(int i2, int i3) {
        int i4;
        if (i2 == 0) {
            i4 = i;
        } else {
            i4 = ~(((j & i2) << 1) | i2 | ((k & i2) >> 1));
        }
        return i2 | (i3 & i4);
    }

    public static int l(Spliterator spliterator) {
        int iCharacteristics = spliterator.characteristics();
        int i2 = iCharacteristics & 4;
        int i3 = f;
        return (i2 == 0 || spliterator.getComparator() == null) ? iCharacteristics & i3 : iCharacteristics & i3 & (-5);
    }
}
