package kotlinx.coroutines;

import kotlin.Unit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DisposeOnCancel extends CancelHandler {

    @NotNull
    public final DisposableHandle handle;

    public DisposeOnCancel(@NotNull DisposableHandle disposableHandle) {
        this.handle = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "DisposeOnCancel[" + this.handle + AbstractJsonLexerKt.END_LIST;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.handle.dispose();
    }
}
