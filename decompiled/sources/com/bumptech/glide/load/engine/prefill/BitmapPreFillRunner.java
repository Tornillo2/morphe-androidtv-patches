package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BitmapPreFillRunner implements Runnable {
    public static final int BACKOFF_RATIO = 4;
    public static final long INITIAL_BACKOFF_MS = 40;
    public static final long MAX_DURATION_MS = 32;

    @VisibleForTesting
    public static final String TAG = "PreFillRunner";
    public final BitmapPool bitmapPool;
    public final Clock clock;
    public long currentDelay;
    public final Handler handler;
    public boolean isCancelled;
    public final MemoryCache memoryCache;
    public final Set<PreFillType> seenTypes;
    public final PreFillQueue toPrefill;
    public static final Clock DEFAULT_CLOCK = new Clock();
    public static final long MAX_BACKOFF_MS = TimeUnit.SECONDS.toMillis(1);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class Clock {
        public long now() {
            return SystemClock.currentThreadTimeMillis();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class UniqueKey implements Key {
        @Override // com.bumptech.glide.load.Key
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
            throw new UnsupportedOperationException();
        }
    }

    public BitmapPreFillRunner(BitmapPool bitmapPool, MemoryCache memoryCache, PreFillQueue preFillQueue) {
        this(bitmapPool, memoryCache, preFillQueue, DEFAULT_CLOCK, new Handler(Looper.getMainLooper()));
    }

    @VisibleForTesting
    public boolean allocate() {
        Bitmap bitmapCreateBitmap;
        long jNow = this.clock.now();
        while (!this.toPrefill.isEmpty() && !isGcDetected(jNow)) {
            PreFillType preFillTypeRemove = this.toPrefill.remove();
            if (this.seenTypes.contains(preFillTypeRemove)) {
                bitmapCreateBitmap = Bitmap.createBitmap(preFillTypeRemove.width, preFillTypeRemove.height, preFillTypeRemove.config);
            } else {
                this.seenTypes.add(preFillTypeRemove);
                bitmapCreateBitmap = this.bitmapPool.getDirty(preFillTypeRemove.width, preFillTypeRemove.height, preFillTypeRemove.config);
            }
            int bitmapByteSize = Util.getBitmapByteSize(bitmapCreateBitmap);
            if (getFreeMemoryCacheBytes() >= bitmapByteSize) {
                this.memoryCache.put(new UniqueKey(), BitmapResource.obtain(bitmapCreateBitmap, this.bitmapPool));
            } else {
                this.bitmapPool.put(bitmapCreateBitmap);
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "allocated [" + preFillTypeRemove.width + "x" + preFillTypeRemove.height + "] " + preFillTypeRemove.config + " size: " + bitmapByteSize);
            }
        }
        return (this.isCancelled || this.toPrefill.isEmpty()) ? false : true;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public final long getFreeMemoryCacheBytes() {
        return this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize();
    }

    public final long getNextDelay() {
        long j = this.currentDelay;
        this.currentDelay = Math.min(4 * j, MAX_BACKOFF_MS);
        return j;
    }

    public final boolean isGcDetected(long j) {
        return this.clock.now() - j >= 32;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (allocate()) {
            this.handler.postDelayed(this, getNextDelay());
        }
    }

    @VisibleForTesting
    public BitmapPreFillRunner(BitmapPool bitmapPool, MemoryCache memoryCache, PreFillQueue preFillQueue, Clock clock, Handler handler) {
        this.seenTypes = new HashSet();
        this.currentDelay = 40L;
        this.bitmapPool = bitmapPool;
        this.memoryCache = memoryCache;
        this.toPrefill = preFillQueue;
        this.clock = clock;
        this.handler = handler;
    }
}
