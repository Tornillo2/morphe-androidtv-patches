package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class LruBitmapPool implements BitmapPool {
    public static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    public static final String TAG = "LruBitmapPool";
    public final Set<Bitmap.Config> allowedConfigs;
    public long currentSize;
    public int evictions;
    public int hits;
    public final long initialMaxSize;
    public long maxSize;
    public int misses;
    public int puts;
    public final LruPoolStrategy strategy;
    public final BitmapTracker tracker;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ThrowingBitmapTracker implements BitmapTracker {
        public final Set<Bitmap> bitmaps = DesugarCollections.synchronizedSet(new HashSet());

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                this.bitmaps.add(bitmap);
                return;
            }
            throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + "x" + bitmap.getHeight() + "]");
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                throw new IllegalStateException("Cannot remove bitmap not in tracker");
            }
            this.bitmaps.remove(bitmap);
        }
    }

    public LruBitmapPool(long j, LruPoolStrategy lruPoolStrategy, Set<Bitmap.Config> set) {
        this.initialMaxSize = j;
        this.maxSize = j;
        this.strategy = lruPoolStrategy;
        this.allowedConfigs = set;
        this.tracker = new NullBitmapTracker();
    }

    @TargetApi(26)
    public static void assertNotHardwareConfig(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    @NonNull
    public static Bitmap createBitmap(int i, int i2, @Nullable Bitmap.Config config) {
        if (config == null) {
            config = DEFAULT_CONFIG;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    @TargetApi(26)
    public static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        int i = Build.VERSION.SDK_INT;
        hashSet.add(null);
        if (i >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        return DesugarCollections.unmodifiableSet(hashSet);
    }

    public static LruPoolStrategy getDefaultStrategy() {
        return new SizeConfigStrategy();
    }

    @TargetApi(19)
    public static void maybeSetPreMultiplied(Bitmap bitmap) {
        bitmap.setPremultiplied(true);
    }

    public static void normalize(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        bitmap.setPremultiplied(true);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "clearMemory");
        }
        trimToSize(0L);
    }

    public final void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    public final void dumpUnchecked() {
        Log.v(TAG, "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
    }

    public final void evict() {
        trimToSize(this.maxSize);
    }

    public long evictionCount() {
        return this.evictions;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        if (dirtyOrNull == null) {
            return createBitmap(i, i2, config);
        }
        dirtyOrNull.eraseColor(0);
        return dirtyOrNull;
    }

    public long getCurrentSize() {
        return this.currentSize;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @NonNull
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        return dirtyOrNull == null ? createBitmap(i, i2, config) : dirtyOrNull;
    }

    @Nullable
    public final synchronized Bitmap getDirtyOrNull(int i, int i2, @Nullable Bitmap.Config config) {
        Bitmap bitmap;
        try {
            assertNotHardwareConfig(config);
            bitmap = this.strategy.get(i, i2, config != null ? config : DEFAULT_CONFIG);
            if (bitmap == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Missing bitmap=" + this.strategy.logBitmap(i, i2, config));
                }
                this.misses++;
            } else {
                this.hits++;
                this.currentSize -= (long) this.strategy.getSize(bitmap);
                this.tracker.remove(bitmap);
                bitmap.setHasAlpha(true);
                bitmap.setPremultiplied(true);
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Get bitmap=" + this.strategy.logBitmap(i, i2, config));
            }
            dump();
        } catch (Throwable th) {
            throw th;
        }
        return bitmap;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return this.maxSize;
    }

    public long hitCount() {
        return this.hits;
    }

    public long missCount() {
        return this.misses;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void put(Bitmap bitmap) {
        try {
            if (bitmap == null) {
                throw new NullPointerException("Bitmap must not be null");
            }
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Cannot pool recycled bitmap");
            }
            if (bitmap.isMutable() && this.strategy.getSize(bitmap) <= this.maxSize && this.allowedConfigs.contains(bitmap.getConfig())) {
                int size = this.strategy.getSize(bitmap);
                this.strategy.put(bitmap);
                this.tracker.add(bitmap);
                this.puts++;
                this.currentSize += (long) size;
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Put bitmap in pool=" + this.strategy.logBitmap(bitmap));
                }
                dump();
                evict();
                return;
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(bitmap.getConfig()));
            }
            bitmap.recycle();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void setSizeMultiplier(float f) {
        this.maxSize = Math.round(this.initialMaxSize * f);
        evict();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "trimMemory, level=" + i);
        }
        if (i >= 40 || (Build.VERSION.SDK_INT >= 23 && i >= 20)) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }

    public final synchronized void trimToSize(long j) {
        while (this.currentSize > j) {
            try {
                Bitmap bitmapRemoveLast = this.strategy.removeLast();
                if (bitmapRemoveLast == null) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Size mismatch, resetting");
                        dumpUnchecked();
                    }
                    this.currentSize = 0L;
                    return;
                }
                this.tracker.remove(bitmapRemoveLast);
                this.currentSize -= (long) this.strategy.getSize(bitmapRemoveLast);
                this.evictions++;
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Evicting bitmap=" + this.strategy.logBitmap(bitmapRemoveLast));
                }
                dump();
                bitmapRemoveLast.recycle();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public LruBitmapPool(long j) {
        this(j, new SizeConfigStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(long j, Set<Bitmap.Config> set) {
        this(j, new SizeConfigStrategy(), set);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NullBitmapTracker implements BitmapTracker {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void add(Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool.BitmapTracker
        public void remove(Bitmap bitmap) {
        }
    }
}
