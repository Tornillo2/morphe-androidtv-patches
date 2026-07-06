package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BitmapPreFiller {
    public final BitmapPool bitmapPool;
    public BitmapPreFillRunner current;
    public final DecodeFormat defaultFormat;
    public final Handler handler = new Handler(Looper.getMainLooper());
    public final MemoryCache memoryCache;

    public BitmapPreFiller(MemoryCache memoryCache, BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this.memoryCache = memoryCache;
        this.bitmapPool = bitmapPool;
        this.defaultFormat = decodeFormat;
    }

    public static int getSizeInBytes(PreFillType preFillType) {
        return Util.getBitmapByteSize(preFillType.width, preFillType.height, preFillType.config);
    }

    @VisibleForTesting
    public PreFillQueue generateAllocationOrder(PreFillType... preFillTypeArr) {
        long maxSize = this.bitmapPool.getMaxSize() + (this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize());
        int i = 0;
        for (PreFillType preFillType : preFillTypeArr) {
            i += preFillType.weight;
        }
        float f = maxSize / i;
        HashMap map = new HashMap();
        for (PreFillType preFillType2 : preFillTypeArr) {
            map.put(preFillType2, Integer.valueOf(Math.round(preFillType2.weight * f) / Util.getBitmapByteSize(preFillType2.width, preFillType2.height, preFillType2.config)));
        }
        return new PreFillQueue(map);
    }

    public void preFill(PreFillType.Builder... builderArr) {
        BitmapPreFillRunner bitmapPreFillRunner = this.current;
        if (bitmapPreFillRunner != null) {
            bitmapPreFillRunner.isCancelled = true;
        }
        PreFillType[] preFillTypeArr = new PreFillType[builderArr.length];
        for (int i = 0; i < builderArr.length; i++) {
            PreFillType.Builder builder = builderArr[i];
            if (builder.getConfig() == null) {
                builder.setConfig(this.defaultFormat == DecodeFormat.PREFER_ARGB_8888 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            preFillTypeArr[i] = builder.build();
        }
        BitmapPreFillRunner bitmapPreFillRunner2 = new BitmapPreFillRunner(this.bitmapPool, this.memoryCache, generateAllocationOrder(preFillTypeArr));
        this.current = bitmapPreFillRunner2;
        this.handler.post(bitmapPreFillRunner2);
    }
}
