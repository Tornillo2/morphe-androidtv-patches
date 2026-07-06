package kotlinx.coroutines.channels;

import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BroadcastChannelKt {
    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> BroadcastChannel<E> BroadcastChannel(int i) {
        if (i == -2) {
            Channel.Factory.getClass();
            return new ArrayBroadcastChannel(Channel.Factory.CHANNEL_DEFAULT_CAPACITY);
        }
        if (i == -1) {
            return new ConflatedBroadcastChannel();
        }
        if (i == 0) {
            throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
        }
        if (i != Integer.MAX_VALUE) {
            return new ArrayBroadcastChannel(i);
        }
        throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
    }
}
