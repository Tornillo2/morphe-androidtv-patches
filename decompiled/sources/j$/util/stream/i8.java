package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class i8 {
    public static final i8 MAYBE_MORE;
    public static final i8 NO_MORE;
    public static final i8 UNLIMITED;
    public static final /* synthetic */ i8[] a;

    static {
        i8 i8Var = new i8("NO_MORE", 0);
        NO_MORE = i8Var;
        i8 i8Var2 = new i8("MAYBE_MORE", 1);
        MAYBE_MORE = i8Var2;
        i8 i8Var3 = new i8("UNLIMITED", 2);
        UNLIMITED = i8Var3;
        a = new i8[]{i8Var, i8Var2, i8Var3};
    }

    public static i8 valueOf(String str) {
        return (i8) Enum.valueOf(i8.class, str);
    }

    public static i8[] values() {
        return (i8[]) a.clone();
    }
}
