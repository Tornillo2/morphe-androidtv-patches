package com.amazon.ignitionshared;

import android.annotation.SuppressLint;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import com.amazon.reporting.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class GMBMessageProcessor {
    public static final String LOG_TAG = "GMBMessageProcessor";
    public final AtomicInteger handleCounter = new AtomicInteger(1);
    public final Map<String, Map<Integer, GMBMessageHandler>> EVENT_HANDLER_MAP = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface GMBMessageHandler {
        void process(String str);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NativeProcessMessageResult {
        SUCCESS(0),
        FAILURE(1);

        public final int code;

        NativeProcessMessageResult(int i) {
            this.code = i;
        }
    }

    @Inject
    public GMBMessageProcessor() {
    }

    @CalledFromNative
    public int processMessage(String str, String str2) {
        try {
            Log.d(LOG_TAG, "Message received on GMB with event type : " + str);
            if (this.EVENT_HANDLER_MAP.containsKey(str)) {
                Iterator<GMBMessageHandler> it = this.EVENT_HANDLER_MAP.get(str).values().iterator();
                while (it.hasNext()) {
                    it.next().process(str2);
                }
            }
            return NativeProcessMessageResult.SUCCESS.code;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed in processing message", e);
            return NativeProcessMessageResult.FAILURE.code;
        }
    }

    public int subscribeMessageHandler(String str, GMBMessageHandler gMBMessageHandler) {
        if (!this.EVENT_HANDLER_MAP.containsKey(str)) {
            this.EVENT_HANDLER_MAP.put(str, new HashMap());
        }
        int andIncrement = this.handleCounter.getAndIncrement();
        this.EVENT_HANDLER_MAP.get(str).put(Integer.valueOf(andIncrement), gMBMessageHandler);
        return andIncrement;
    }

    @SuppressLint({"DefaultLocale"})
    public boolean unsubscribeMessageHandler(String str, int i) {
        Map<Integer, GMBMessageHandler> map = this.EVENT_HANDLER_MAP.get(str);
        if (map == null) {
            Log.w(LOG_TAG, String.format("No handlers registered for event type %s", str));
            return false;
        }
        if (map.remove(Integer.valueOf(i)) != null) {
            return true;
        }
        Log.w(LOG_TAG, String.format("No handler found for event type %s with handle %d", str, Integer.valueOf(i)));
        return false;
    }
}
