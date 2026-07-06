package androidx.core.util;

import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidXConsumerKt {
    @NotNull
    public static final <T> Consumer<T> asAndroidXConsumer(@NotNull Continuation<? super T> continuation) {
        return new AndroidXContinuationConsumer(continuation);
    }
}
