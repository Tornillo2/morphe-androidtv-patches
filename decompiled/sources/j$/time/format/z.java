package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class z {
    public static final z ALWAYS;
    public static final z EXCEEDS_PAD;
    public static final z NEVER;
    public static final z NORMAL;
    public static final z NOT_NEGATIVE;
    public static final /* synthetic */ z[] a;

    public static z valueOf(String str) {
        return (z) Enum.valueOf(z.class, str);
    }

    public static z[] values() {
        return (z[]) a.clone();
    }

    static {
        z zVar = new z("NORMAL", 0);
        NORMAL = zVar;
        z zVar2 = new z("ALWAYS", 1);
        ALWAYS = zVar2;
        z zVar3 = new z("NEVER", 2);
        NEVER = zVar3;
        z zVar4 = new z("NOT_NEGATIVE", 3);
        NOT_NEGATIVE = zVar4;
        z zVar5 = new z("EXCEEDS_PAD", 4);
        EXCEEDS_PAD = zVar5;
        a = new z[]{zVar, zVar2, zVar3, zVar4, zVar5};
    }
}
