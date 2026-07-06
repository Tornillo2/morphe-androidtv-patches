package kotlin.jdk7;

import androidx.core.provider.FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "AutoCloseableKt")
public final class AutoCloseableKt {
    @SinceKotlin(version = "2.0")
    @InlineOnly
    public static final AutoCloseable AutoCloseable(final Function0<Unit> closeAction) {
        Intrinsics.checkNotNullParameter(closeAction, "closeAction");
        return new AutoCloseable() { // from class: kotlin.jdk7.AutoCloseableKt.AutoCloseable.1
            @Override // java.lang.AutoCloseable
            public final void close() {
                closeAction.invoke();
            }
        };
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable autoCloseable, @Nullable Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0.m(autoCloseable);
                return;
            }
            try {
                FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0.m(autoCloseable);
            } catch (Throwable th2) {
                ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
            }
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            R rInvoke = block.invoke(t);
            closeFinally(t, null);
            return rInvoke;
        } finally {
        }
    }

    @SinceKotlin(version = "2.0")
    public static /* synthetic */ void AutoCloseable$annotations() {
    }
}
