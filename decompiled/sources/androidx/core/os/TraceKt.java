package androidx.core.os;

import android.os.Trace;
import kotlin.Deprecated;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TraceKt {
    @Deprecated(message = "Use androidx.tracing.Trace instead", replaceWith = @ReplaceWith(expression = "trace(sectionName, block)", imports = {"androidx.tracing.trace"}))
    public static final <T> T trace(@NotNull String str, @NotNull Function0<? extends T> function0) {
        Trace.beginSection(str);
        try {
            return function0.invoke();
        } finally {
            Trace.endSection();
        }
    }
}
