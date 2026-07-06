package kotlinx.coroutines;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CancellableContinuationImplKt {
    public static final int RESUMED = 2;

    @JvmField
    @NotNull
    public static final Symbol RESUME_TOKEN = new Symbol("RESUME_TOKEN");
    public static final int SUSPENDED = 1;
    public static final int UNDECIDED = 0;

    public static /* synthetic */ void getRESUME_TOKEN$annotations() {
    }
}
