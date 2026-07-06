package kotlinx.coroutines.internal;

import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final int systemProp(@NotNull String str, int i, int i2, int i3) {
        return (int) systemProp(str, i, i2, i3);
    }

    public static int systemProp$default(String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return systemProp(str, i, i2, i3);
    }

    public static final long systemProp(@NotNull String str, long j, long j2, long j3) {
        String strSystemProp = SystemPropsKt__SystemPropsKt.systemProp(str);
        if (strSystemProp == null) {
            return j;
        }
        Long longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(strSystemProp);
        if (longOrNull == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + strSystemProp + '\'').toString());
        }
        long jLongValue = longOrNull.longValue();
        if (j2 <= jLongValue && jLongValue <= j3) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j2 + ".." + j3 + ", but is '" + jLongValue + '\'').toString());
    }

    public static long systemProp$default(String str, long j, long j2, long j3, int i, Object obj) {
        if ((i & 4) != 0) {
            j2 = 1;
        }
        long j4 = j2;
        if ((i & 8) != 0) {
            j3 = Long.MAX_VALUE;
        }
        return systemProp(str, j, j4, j3);
    }

    public static final boolean systemProp(@NotNull String str, boolean z) {
        String strSystemProp = SystemPropsKt__SystemPropsKt.systemProp(str);
        return strSystemProp != null ? Boolean.parseBoolean(strSystemProp) : z;
    }
}
