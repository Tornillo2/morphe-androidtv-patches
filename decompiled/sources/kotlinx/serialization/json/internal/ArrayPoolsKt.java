package kotlinx.serialization.json.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ArrayPoolsKt {
    public static final int MAX_CHARS_IN_POOL;

    static {
        Object objCreateFailure;
        try {
            String property = System.getProperty("kotlinx.serialization.json.pool.size");
            objCreateFailure = property != null ? StringsKt__StringNumberConversionsKt.toIntOrNull(property) : null;
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        Integer num = (Integer) (objCreateFailure instanceof Result.Failure ? null : objCreateFailure);
        MAX_CHARS_IN_POOL = num != null ? num.intValue() : 2097152;
    }
}
