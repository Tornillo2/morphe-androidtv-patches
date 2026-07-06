package com.amazon.minerva.client.thirdparty.sample;

import android.util.Log;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class LocalSamplingKeyGenerator {
    public static final String TAG = "MetricEventSampler";
    public static int sLocalSamplingKey = -1;
    public static int sResetSamplingKeyInSeconds = 86400;
    public static ScheduledExecutorService sScheduler;

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.sample.LocalSamplingKeyGenerator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            LocalSamplingKeyGenerator.setLocalSamplingKey();
        }
    }

    public static int getLocalSamplingKey() {
        if (sLocalSamplingKey == -1) {
            setLocalSamplingKey();
            scheduleUpdateSamplingKey();
        }
        return sLocalSamplingKey;
    }

    public static void scheduleUpdateSamplingKey() {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        sScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        int i = sResetSamplingKeyInSeconds;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleWithFixedDelay(anonymousClass1, i, i, TimeUnit.SECONDS);
    }

    public static void setLocalSamplingKey() {
        sLocalSamplingKey = new Random().nextInt(100);
        Log.i(TAG, "Set local sampling key to " + sLocalSamplingKey);
    }
}
