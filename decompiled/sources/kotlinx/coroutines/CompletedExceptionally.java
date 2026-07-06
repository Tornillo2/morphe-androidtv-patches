package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CompletedExceptionally {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _handled$FU = AtomicIntegerFieldUpdater.newUpdater(CompletedExceptionally.class, "_handled");

    @NotNull
    private volatile /* synthetic */ int _handled;

    @JvmField
    @NotNull
    public final Throwable cause;

    public CompletedExceptionally(@NotNull Throwable th, boolean z) {
        this.cause = th;
        this._handled = z ? 1 : 0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean getHandled() {
        return this._handled;
    }

    public final boolean makeHandled() {
        return _handled$FU.compareAndSet(this, 0, 1);
    }

    @NotNull
    public String toString() {
        return getClass().getSimpleName() + AbstractJsonLexerKt.BEGIN_LIST + this.cause + AbstractJsonLexerKt.END_LIST;
    }

    public /* synthetic */ CompletedExceptionally(Throwable th, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i & 2) != 0 ? false : z);
    }
}
