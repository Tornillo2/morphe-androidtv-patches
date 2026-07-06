package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class y {
    public static final y LENIENT;
    public static final y SMART;
    public static final y STRICT;
    public static final /* synthetic */ y[] a;

    public static y valueOf(String str) {
        return (y) Enum.valueOf(y.class, str);
    }

    public static y[] values() {
        return (y[]) a.clone();
    }

    static {
        y yVar = new y("STRICT", 0);
        STRICT = yVar;
        y yVar2 = new y("SMART", 1);
        SMART = yVar2;
        y yVar3 = new y("LENIENT", 2);
        LENIENT = yVar3;
        a = new y[]{yVar, yVar2, yVar3};
    }
}
