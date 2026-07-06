package j$.time.format;

import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class w {
    public static final w a = new w();

    public final int hashCode() {
        return 182;
    }

    static {
        new ConcurrentHashMap(16, 0.75f, 2);
    }

    public final boolean equals(Object obj) {
        return this == obj || (obj instanceof w);
    }

    public final String toString() {
        return "DecimalStyle[0+-.]";
    }
}
