package kotlinx.coroutines.channels;

import java.util.NoSuchElementException;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ClosedReceiveChannelException extends NoSuchElementException {
    public ClosedReceiveChannelException(@Nullable String str) {
        super(str);
    }
}
