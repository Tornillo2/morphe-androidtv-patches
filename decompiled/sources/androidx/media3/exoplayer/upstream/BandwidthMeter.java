package androidx.media3.exoplayer.upstream;

import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.TransferListener;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface BandwidthMeter {

    /* JADX INFO: renamed from: androidx.media3.exoplayer.upstream.BandwidthMeter$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static long $default$getTimeToFirstByteEstimateUs(BandwidthMeter bandwidthMeter) {
            return -9223372036854775807L;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface EventListener {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class EventDispatcher {
            public final CopyOnWriteArrayList<HandlerAndListener> listeners = new CopyOnWriteArrayList<>();

            /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
            public static final class HandlerAndListener {
                public final Handler handler;
                public final EventListener listener;
                public boolean released;

                public HandlerAndListener(Handler handler, EventListener eventListener) {
                    this.handler = handler;
                    this.listener = eventListener;
                }

                public void release() {
                    this.released = true;
                }
            }

            public void addListener(Handler handler, EventListener eventListener) {
                handler.getClass();
                eventListener.getClass();
                removeListener(eventListener);
                this.listeners.add(new HandlerAndListener(handler, eventListener));
            }

            public void bandwidthSample(int i, long j, long j2) {
                final int i2;
                final long j3;
                final long j4;
                for (final HandlerAndListener handlerAndListener : this.listeners) {
                    if (handlerAndListener.released) {
                        i2 = i;
                        j3 = j;
                        j4 = j2;
                    } else {
                        i2 = i;
                        j3 = j;
                        j4 = j2;
                        handlerAndListener.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.upstream.BandwidthMeter$EventListener$EventDispatcher$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                handlerAndListener.listener.onBandwidthSample(i2, j3, j4);
                            }
                        });
                    }
                    i = i2;
                    j = j3;
                    j2 = j4;
                }
            }

            public void removeListener(EventListener eventListener) {
                for (HandlerAndListener handlerAndListener : this.listeners) {
                    if (handlerAndListener.listener == eventListener) {
                        handlerAndListener.released = true;
                        this.listeners.remove(handlerAndListener);
                    }
                }
            }
        }

        void onBandwidthSample(int i, long j, long j2);
    }

    void addEventListener(Handler handler, EventListener eventListener);

    long getBitrateEstimate();

    long getTimeToFirstByteEstimateUs();

    @Nullable
    TransferListener getTransferListener();

    void removeEventListener(EventListener eventListener);
}
