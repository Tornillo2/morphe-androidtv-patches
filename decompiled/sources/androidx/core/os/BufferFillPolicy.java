package androidx.core.os;

import androidx.annotation.RequiresApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public abstract class BufferFillPolicy {

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final BufferFillPolicy DISCARD = new Discard(1);

    @JvmField
    @NotNull
    public static final BufferFillPolicy RING_BUFFER = new RingBuffer(2);
    public final int value;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Discard extends BufferFillPolicy {
        public Discard() {
            super(1);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class RingBuffer extends BufferFillPolicy {
        public RingBuffer() {
            super(2);
        }
    }

    public /* synthetic */ BufferFillPolicy(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getValue$core_release() {
        return this.value;
    }

    public BufferFillPolicy(int i) {
        this.value = i;
    }
}
