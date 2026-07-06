package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {
    public static final GifDecoderFactory GIF_DECODER_FACTORY = new GifDecoderFactory();
    public static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
    public static final String TAG = "BufferGifDecoder";
    public final Context context;
    public final GifDecoderFactory gifDecoderFactory;
    public final GifHeaderParserPool parserPool;
    public final List<ImageHeaderParser> parsers;
    public final GifBitmapProvider provider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class GifDecoderFactory {
        public GifDecoder build(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
            return new StandardGifDecoder(bitmapProvider, gifHeader, byteBuffer, i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class GifHeaderParserPool {
        public final Queue<GifHeaderParser> pool = Util.createQueue(0);

        public synchronized GifHeaderParser obtain(ByteBuffer byteBuffer) {
            GifHeaderParser gifHeaderParserPoll;
            try {
                gifHeaderParserPoll = this.pool.poll();
                if (gifHeaderParserPoll == null) {
                    gifHeaderParserPoll = new GifHeaderParser();
                }
            } catch (Throwable th) {
                throw th;
            }
            return gifHeaderParserPoll.setData(byteBuffer);
        }

        public synchronized void release(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.clear();
            this.pool.offer(gifHeaderParser);
        }
    }

    public ByteBufferGifDecoder(Context context) {
        this(context, Glide.get(context).getRegistry().getImageHeaderParsers(), Glide.get(context).getBitmapPool(), Glide.get(context).getArrayPool());
    }

    public static int getSampleSize(GifHeader gifHeader, int i, int i2) {
        int iMin = Math.min(gifHeader.getHeight() / i2, gifHeader.getWidth() / i);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable(TAG, 2) && iMax > 1) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Downsampling GIF, sampleSize: ", iMax, ", target dimens: [", i, "x");
            sbM.append(i2);
            sbM.append("], actual dimens: [");
            sbM.append(gifHeader.getWidth());
            sbM.append("x");
            sbM.append(gifHeader.getHeight());
            sbM.append("]");
            Log.v(TAG, sbM.toString());
        }
        return iMax;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public GifDrawableResource decode(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) {
        GifHeaderParser gifHeaderParserObtain = this.parserPool.obtain(byteBuffer);
        try {
            return decode(byteBuffer, i, i2, gifHeaderParserObtain, options);
        } finally {
            this.parserPool.release(gifHeaderParserObtain);
        }
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull ByteBuffer byteBuffer, @NonNull Options options) throws IOException {
        return !((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue() && ImageHeaderParserUtils.getType(this.parsers, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    public ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context, list, bitmapPool, arrayPool, PARSER_POOL, GIF_DECODER_FACTORY);
    }

    @Nullable
    public final GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, GifHeaderParser gifHeaderParser, Options options) {
        StringBuilder sb;
        Bitmap.Config config;
        long logTime = LogTime.getLogTime();
        try {
            GifHeader header = gifHeaderParser.parseHeader();
            if (header.getNumFrames() > 0 && header.getStatus() == 0) {
                if (options.get(GifOptions.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565) {
                    config = Bitmap.Config.RGB_565;
                } else {
                    config = Bitmap.Config.ARGB_8888;
                }
                GifDecoder gifDecoderBuild = this.gifDecoderFactory.build(this.provider, header, byteBuffer, getSampleSize(header, i, i2));
                StandardGifDecoder standardGifDecoder = (StandardGifDecoder) gifDecoderBuild;
                standardGifDecoder.setDefaultBitmapConfig(config);
                standardGifDecoder.advance();
                Bitmap nextFrame = standardGifDecoder.getNextFrame();
                if (nextFrame == null) {
                    if (Log.isLoggable(TAG, 2)) {
                        sb = new StringBuilder("Decoded GIF from stream in ");
                        sb.append(LogTime.getElapsedMillis(logTime));
                        Log.v(TAG, sb.toString());
                        return null;
                    }
                    return null;
                }
                GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(this.context, gifDecoderBuild, (UnitTransformation) UnitTransformation.TRANSFORMATION, i, i2, nextFrame));
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
                }
                return gifDrawableResource;
            }
            if (Log.isLoggable(TAG, 2)) {
                sb = new StringBuilder("Decoded GIF from stream in ");
                sb.append(LogTime.getElapsedMillis(logTime));
                Log.v(TAG, sb.toString());
                return null;
            }
            return null;
        } catch (Throwable th) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Decoded GIF from stream in " + LogTime.getElapsedMillis(logTime));
            }
            throw th;
        }
    }

    @VisibleForTesting
    public ByteBufferGifDecoder(Context context, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool gifHeaderParserPool, GifDecoderFactory gifDecoderFactory) {
        this.context = context.getApplicationContext();
        this.parsers = list;
        this.gifDecoderFactory = gifDecoderFactory;
        this.provider = new GifBitmapProvider(bitmapPool, arrayPool);
        this.parserPool = gifHeaderParserPool;
    }
}
