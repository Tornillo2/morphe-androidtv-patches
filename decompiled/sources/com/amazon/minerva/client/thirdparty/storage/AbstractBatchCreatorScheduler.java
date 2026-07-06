package com.amazon.minerva.client.thirdparty.storage;

import android.util.Log;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import j$.util.Objects;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBatchCreatorScheduler {
    public static final String TAG = "AbstractBatchCreatorScheduler";
    public static final String THREAD_NAME_BATCH_OPEN_TIME_WATCHER = "MnvBchOpnWch_";
    public static final int THREAD_POOL_TERMINATION_WAIT_MS = 5000;
    public final BatchOpenTimeWatcher mBatchOpenTimeWatcher = new BatchOpenTimeWatcher();
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;
    public final AtomicLong mTimeSinceLastPublish;
    public final ScheduledExecutorService threadPool;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class BatchOpenTimeWatcher implements Callable<Void> {
        public BatchOpenTimeWatcher() {
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() throws Exception {
            call2();
            return null;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() throws Exception {
            AbstractBatchCreatorScheduler.this.checkBatchOpenTimeAndEnqueueIfReady();
            AbstractBatchCreatorScheduler.this.scheduleBatchOpenTimeWatcher();
            return null;
        }
    }

    public AbstractBatchCreatorScheduler(MetricsConfigurationHelper metricsConfigurationHelper, String str) {
        Objects.requireNonNull(metricsConfigurationHelper, "metricsConfigurationHelper cannot be null.");
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        AtomicLong atomicLong = new AtomicLong();
        this.mTimeSinceLastPublish = atomicLong;
        atomicLong.set(System.currentTimeMillis());
        this.threadPool = Executors.newSingleThreadScheduledExecutor(new BackgroundThreadFactory(RemoteInput$$ExternalSyntheticOutline0.m(THREAD_NAME_BATCH_OPEN_TIME_WATCHER, str)));
    }

    public abstract void checkBatchOpenTimeAndEnqueueIfReady();

    public boolean maxBatchOpenTimeReached() {
        return System.currentTimeMillis() - this.mTimeSinceLastPublish.get() >= this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchOpenTimeMillis();
    }

    public void scheduleBatchOpenTimeWatcher() {
        try {
            this.threadPool.schedule(this.mBatchOpenTimeWatcher, this.mMetricsConfigurationHelper.getStorageConfiguration().getCheckBatchOpenTimeMillis(), TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException unused) {
            Log.e(TAG, "Unexpected rejected execution exception while scheduling LastPublishTimeWatcher");
        }
    }

    public void shutdown() {
        List<Runnable> listShutdownNow = this.threadPool.shutdownNow();
        if (listShutdownNow == null || listShutdownNow.size() <= 0) {
            return;
        }
        Log.i(TAG, String.format("shutdown called with %d pending tasks", Integer.valueOf(listShutdownNow.size())));
    }
}
