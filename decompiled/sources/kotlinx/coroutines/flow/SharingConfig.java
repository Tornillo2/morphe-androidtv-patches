package kotlinx.coroutines.flow;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.BufferOverflow;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SharingConfig<T> {

    @JvmField
    @NotNull
    public final CoroutineContext context;

    @JvmField
    public final int extraBufferCapacity;

    @JvmField
    @NotNull
    public final BufferOverflow onBufferOverflow;

    @JvmField
    @NotNull
    public final Flow<T> upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public SharingConfig(@NotNull Flow<? extends T> flow, int i, @NotNull BufferOverflow bufferOverflow, @NotNull CoroutineContext coroutineContext) {
        this.upstream = flow;
        this.extraBufferCapacity = i;
        this.onBufferOverflow = bufferOverflow;
        this.context = coroutineContext;
    }
}
