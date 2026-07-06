package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.ImageReader;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Downsampler {
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG;
    public static final DecodeCallbacks EMPTY_CALLBACKS;
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS;
    public static final String ICO_MIME_TYPE = "image/x-ico";
    public static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES;
    public static final Queue<BitmapFactory.Options> OPTIONS_QUEUE;
    public static final String TAG = "Downsampler";
    public static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT;
    public static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    public final BitmapPool bitmapPool;
    public final ArrayPool byteArrayPool;
    public final DisplayMetrics displayMetrics;
    public final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    public final List<ImageHeaderParser> parsers;
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final Option<PreferredColorSpace> PREFERRED_COLOR_SPACE = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", PreferredColorSpace.SRGB);

    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    static {
        Boolean bool = Boolean.FALSE;
        FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        ALLOW_HARDWARE_CONFIG = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
        NO_DOWNSAMPLE_PRE_N_MIME_TYPES = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(WBMP_MIME_TYPE, ICO_MIME_TYPE)));
        EMPTY_CALLBACKS = new AnonymousClass1();
        TYPES_THAT_USE_POOL_PRE_KITKAT = DesugarCollections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
        OPTIONS_QUEUE = Util.createQueue(0);
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.parsers = list;
        Preconditions.checkNotNull(displayMetrics, "Argument must not be null");
        this.displayMetrics = displayMetrics;
        Preconditions.checkNotNull(bitmapPool, "Argument must not be null");
        this.bitmapPool = bitmapPool;
        Preconditions.checkNotNull(arrayPool, "Argument must not be null");
        this.byteArrayPool = arrayPool;
    }

    public static int adjustTargetDensityForError(double d) {
        int densityMultiplier = getDensityMultiplier(d);
        int i = (int) ((((double) densityMultiplier) * d) + 0.5d);
        return (int) (((d / ((double) (i / densityMultiplier))) * ((double) i)) + 0.5d);
    }

    public static void calculateScaling(ImageHeaderParser.ImageType imageType, ImageReader imageReader, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int i, int i2, int i3, int i4, int i5, BitmapFactory.Options options) throws IOException {
        int i6;
        int i7;
        int i8;
        int i9;
        int iFloor;
        double dFloor;
        int iRound;
        if (i2 <= 0 || i3 <= 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to determine dimensions for: " + imageType + " with target [" + i4 + "x" + i5 + "]");
                return;
            }
            return;
        }
        if (isRotationRequired(i)) {
            i7 = i2;
            i6 = i3;
        } else {
            i6 = i2;
            i7 = i3;
        }
        float scaleFactor = downsampleStrategy.getScaleFactor(i6, i7, i4, i5);
        if (scaleFactor <= 0.0f) {
            StringBuilder sb = new StringBuilder("Cannot scale with factor: ");
            sb.append(scaleFactor);
            sb.append(" from: ");
            sb.append(downsampleStrategy);
            sb.append(", source: [");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb, i2, "x", i3, "], target: [");
            sb.append(i4);
            sb.append("x");
            sb.append(i5);
            sb.append("]");
            throw new IllegalArgumentException(sb.toString());
        }
        DownsampleStrategy.SampleSizeRounding sampleSizeRounding = downsampleStrategy.getSampleSizeRounding(i6, i7, i4, i5);
        if (sampleSizeRounding == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        float f = i6;
        float f2 = i7;
        int i10 = i6;
        int i11 = i7;
        int i12 = i10 / ((int) (((double) (scaleFactor * f)) + 0.5d));
        int i13 = i11 / ((int) (((double) (scaleFactor * f2)) + 0.5d));
        DownsampleStrategy.SampleSizeRounding sampleSizeRounding2 = DownsampleStrategy.SampleSizeRounding.MEMORY;
        int iMax = sampleSizeRounding == sampleSizeRounding2 ? Math.max(i12, i13) : Math.min(i12, i13);
        int i14 = Build.VERSION.SDK_INT;
        int i15 = iMax;
        if (i14 > 23 || !NO_DOWNSAMPLE_PRE_N_MIME_TYPES.contains(options.outMimeType)) {
            int iMax2 = Math.max(1, Integer.highestOneBit(i15));
            i8 = (sampleSizeRounding != sampleSizeRounding2 || ((float) iMax2) >= 1.0f / scaleFactor) ? iMax2 : iMax2 << 1;
        } else {
            i8 = 1;
        }
        options.inSampleSize = i8;
        if (imageType == ImageHeaderParser.ImageType.JPEG) {
            float fMin = Math.min(i8, 8);
            i9 = 0;
            iFloor = (int) Math.ceil(f / fMin);
            iRound = (int) Math.ceil(f2 / fMin);
            int i16 = i8 / 8;
            if (i16 > 0) {
                iFloor /= i16;
                iRound /= i16;
            }
        } else {
            i9 = 0;
            if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
                float f3 = i8;
                iFloor = (int) Math.floor(f / f3);
                dFloor = Math.floor(f2 / f3);
            } else if (imageType == ImageHeaderParser.ImageType.WEBP || imageType == ImageHeaderParser.ImageType.WEBP_A) {
                if (i14 >= 24) {
                    float f4 = i8;
                    iFloor = Math.round(f / f4);
                    iRound = Math.round(f2 / f4);
                } else {
                    float f5 = i8;
                    iFloor = (int) Math.floor(f / f5);
                    dFloor = Math.floor(f2 / f5);
                }
            } else if (i10 % i8 == 0 && i11 % i8 == 0) {
                iFloor = i10 / i8;
                iRound = i11 / i8;
            } else {
                int[] dimensions = getDimensions(imageReader, options, decodeCallbacks, bitmapPool);
                iFloor = dimensions[0];
                iRound = dimensions[1];
            }
            iRound = (int) dFloor;
        }
        double scaleFactor2 = downsampleStrategy.getScaleFactor(iFloor, iRound, i4, i5);
        options.inTargetDensity = adjustTargetDensityForError(scaleFactor2);
        options.inDensity = getDensityMultiplier(scaleFactor2);
        if (isScaling(options)) {
            options.inScaled = true;
        } else {
            options.inTargetDensity = i9;
            options.inDensity = i9;
        }
        if (Log.isLoggable(TAG, 2)) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Calculate scaling, source: [", i2, "x", i3, "], degreesToRotate: ");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM, i, ", target: [", i4, "x");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM, i5, "], power of two scaled: [", iFloor, "x");
            sbM.append(iRound);
            sbM.append("], exact scale factor: ");
            sbM.append(scaleFactor);
            sbM.append(", power of 2 sample size: ");
            sbM.append(i8);
            sbM.append(", adjusted scale factor: ");
            sbM.append(scaleFactor2);
            sbM.append(", target density: ");
            sbM.append(options.inTargetDensity);
            sbM.append(", density: ");
            sbM.append(options.inDensity);
            Log.v(TAG, sbM.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap decodeStream(com.bumptech.glide.load.resource.bitmap.ImageReader r5, android.graphics.BitmapFactory.Options r6, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r7, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r8) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r6.inJustDecodeBounds
            if (r1 != 0) goto Lc
            r7.onObtainBounds()
            r5.stopGrowingBuffers()
        Lc:
            int r1 = r6.outWidth
            int r2 = r6.outHeight
            java.lang.String r3 = r6.outMimeType
            java.util.concurrent.locks.Lock r4 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r4.lock()
            android.graphics.Bitmap r5 = r5.decodeBitmap(r6)     // Catch: java.lang.IllegalArgumentException -> L23 java.lang.Throwable -> L45
        L1d:
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.BITMAP_DRAWABLE_LOCK
            r6.unlock()
            return r5
        L23:
            r4 = move-exception
            java.io.IOException r1 = newIoExceptionForInBitmapAssertion(r4, r1, r2, r3, r6)     // Catch: java.lang.Throwable -> L45
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.Throwable -> L45
            if (r2 == 0) goto L34
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch: java.lang.Throwable -> L45
        L34:
            android.graphics.Bitmap r0 = r6.inBitmap     // Catch: java.lang.Throwable -> L45
            if (r0 == 0) goto L44
            r8.put(r0)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L45
            r0 = 0
            r6.inBitmap = r0     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L45
            android.graphics.Bitmap r5 = decodeStream(r5, r6, r7, r8)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L45
            goto L1d
        L43:
            throw r1     // Catch: java.lang.Throwable -> L45
        L44:
            throw r1     // Catch: java.lang.Throwable -> L45
        L45:
            r5 = move-exception
            java.util.concurrent.locks.Lock r6 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.BITMAP_DRAWABLE_LOCK
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeStream(com.bumptech.glide.load.resource.bitmap.ImageReader, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    @Nullable
    @TargetApi(19)
    public static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    public static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options optionsPoll;
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        synchronized (queue) {
            optionsPoll = queue.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            resetOptions(optionsPoll);
        }
        return optionsPoll;
    }

    public static int getDensityMultiplier(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    public static int[] getDimensions(ImageReader imageReader, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(imageReader, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    public static String getInBitmapString(BitmapFactory.Options options) {
        return getBitmapString(options.inBitmap);
    }

    public static boolean isRotationRequired(int i) {
        return i == 90 || i == 270;
    }

    public static boolean isScaling(BitmapFactory.Options options) {
        int i;
        int i2 = options.inTargetDensity;
        return i2 > 0 && (i = options.inDensity) > 0 && i2 != i;
    }

    public static void logDecode(int i, int i2, String str, BitmapFactory.Options options, Bitmap bitmap, int i3, int i4, long j) {
        Log.v(TAG, "Decoded " + getBitmapString(bitmap) + " from [" + i + "x" + i2 + "] " + str + " with inBitmap " + getBitmapString(options.inBitmap) + " for [" + i3 + "x" + i4 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.getElapsedMillis(j));
    }

    public static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException illegalArgumentException, int i, int i2, String str, BitmapFactory.Options options) {
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Exception decoding bitmap, outWidth: ", i, ", outHeight: ", i2, ", outMimeType: ");
        sbM.append(str);
        sbM.append(", inBitmap: ");
        sbM.append(getBitmapString(options.inBitmap));
        return new IOException(sbM.toString(), illegalArgumentException);
    }

    public static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    public static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = null;
            options.outColorSpace = null;
            options.outConfig = null;
        }
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    public static int round(double d) {
        return (int) (d + 0.5d);
    }

    @TargetApi(26)
    public static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2) {
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT < 26) {
            config = null;
        } else if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
            return;
        } else {
            config = options.outConfig;
        }
        if (config == null) {
            config = options.inPreferredConfig;
        }
        options.inBitmap = bitmapPool.getDirty(i, i2, config);
    }

    public final void calculateConfig(ImageReader imageReader, DecodeFormat decodeFormat, boolean z, boolean z2, BitmapFactory.Options options, int i, int i2) {
        boolean zHasAlpha;
        if (this.hardwareConfigState.setHardwareConfigIfAllowed(i, i2, options, z, z2)) {
            return;
        }
        if (decodeFormat == DecodeFormat.PREFER_ARGB_8888) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        try {
            zHasAlpha = imageReader.getImageType().hasAlpha();
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e);
            }
            zHasAlpha = false;
        }
        Bitmap.Config config = zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        options.inPreferredConfig = config;
        if (config == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        return decode(inputStream, i, i2, options, EMPTY_CALLBACKS);
    }

    public final Bitmap decodeFromWrappedStreams(ImageReader imageReader, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, PreferredColorSpace preferredColorSpace, boolean z, int i, int i2, boolean z2, DecodeCallbacks decodeCallbacks) throws IOException {
        boolean z3;
        int i3;
        String str;
        String str2;
        ColorSpace colorSpace;
        long logTime = LogTime.getLogTime();
        int[] dimensions = getDimensions(imageReader, options, decodeCallbacks, this.bitmapPool);
        int i4 = dimensions[0];
        int i5 = dimensions[1];
        String str3 = options.outMimeType;
        boolean z4 = (i4 == -1 || i5 == -1) ? false : z;
        int imageOrientation = imageReader.getImageOrientation();
        int exifOrientationDegrees = TransformationUtils.getExifOrientationDegrees(imageOrientation);
        switch (imageOrientation) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                z3 = true;
                break;
            default:
                z3 = false;
                break;
        }
        int i6 = i;
        if (i6 != Integer.MIN_VALUE) {
            i3 = i2;
        } else if (isRotationRequired(exifOrientationDegrees)) {
            i3 = i2;
            i6 = i5;
        } else {
            i3 = i2;
            i6 = i4;
        }
        if (i3 == Integer.MIN_VALUE) {
            i3 = isRotationRequired(exifOrientationDegrees) ? i4 : i5;
        }
        calculateScaling(imageReader.getImageType(), imageReader, decodeCallbacks, this.bitmapPool, downsampleStrategy, exifOrientationDegrees, i4, i5, i6, i3, options);
        int i7 = i6;
        int i8 = i3;
        calculateConfig(imageReader, decodeFormat, z4, z3, options, i7, i8);
        int i9 = Build.VERSION.SDK_INT;
        if (i4 < 0 || i5 < 0 || !z2) {
            float f = isScaling(options) ? options.inTargetDensity / options.inDensity : 1.0f;
            int i10 = options.inSampleSize;
            float f2 = i10;
            int iCeil = (int) Math.ceil(i4 / f2);
            int iCeil2 = (int) Math.ceil(i5 / f2);
            int iRound = Math.round(iCeil * f);
            int iRound2 = Math.round(iCeil2 * f);
            str = TAG;
            if (Log.isLoggable(str, 2)) {
                str2 = str3;
                StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Calculated target [", iRound, "x", iRound2, "] for source [");
                AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM, i4, "x", i5, "], sampleSize: ");
                sbM.append(i10);
                sbM.append(", targetDensity: ");
                sbM.append(options.inTargetDensity);
                sbM.append(", density: ");
                sbM.append(options.inDensity);
                sbM.append(", density multiplier: ");
                sbM.append(f);
                Log.v(str, sbM.toString());
            } else {
                str2 = str3;
            }
            i7 = iRound;
            i8 = iRound2;
        } else {
            str2 = str3;
            str = TAG;
        }
        if (i7 > 0 && i8 > 0) {
            setInBitmap(options, this.bitmapPool, i7, i8);
        }
        if (i9 >= 28) {
            options.inPreferredColorSpace = ColorSpace.get((preferredColorSpace == PreferredColorSpace.DISPLAY_P3 && (colorSpace = options.outColorSpace) != null && colorSpace.isWideGamut()) ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB);
        } else if (i9 >= 26) {
            options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        Bitmap bitmapDecodeStream = decodeStream(imageReader, options, decodeCallbacks, this.bitmapPool);
        decodeCallbacks.onDecodeComplete(this.bitmapPool, bitmapDecodeStream);
        if (Log.isLoggable(str, 2)) {
            logDecode(i4, i5, str2, options, bitmapDecodeStream, i, i2, logTime);
        }
        if (bitmapDecodeStream == null) {
            return null;
        }
        bitmapDecodeStream.setDensity(this.displayMetrics.densityDpi);
        Bitmap bitmapRotateImageExif = TransformationUtils.rotateImageExif(this.bitmapPool, bitmapDecodeStream, imageOrientation);
        if (!bitmapDecodeStream.equals(bitmapRotateImageExif)) {
            this.bitmapPool.put(bitmapDecodeStream);
        }
        return bitmapRotateImageExif;
    }

    public boolean handles(ParcelFileDescriptor parcelFileDescriptor) {
        return true;
    }

    public final boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        return decode(new ImageReader.InputStreamImageReader(inputStream, this.parsers, this.byteArrayPool), i, i2, options, decodeCallbacks);
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    @RequiresApi(21)
    public Resource<Bitmap> decode(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, Options options) throws IOException {
        return decode(new ImageReader.ParcelFileDescriptorImageReader(parcelFileDescriptor, this.parsers, this.byteArrayPool), i, i2, options, EMPTY_CALLBACKS);
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public final Resource<Bitmap> decode(ImageReader imageReader, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        byte[] bArr = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        defaultOptions.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options.get(DECODE_FORMAT);
        PreferredColorSpace preferredColorSpace = (PreferredColorSpace) options.get(PREFERRED_COLOR_SPACE);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        boolean zBooleanValue = ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
        Option<Boolean> option = ALLOW_HARDWARE_CONFIG;
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(imageReader, defaultOptions, downsampleStrategy, decodeFormat, preferredColorSpace, options.get(option) != null && ((Boolean) options.get(option)).booleanValue(), i, i2, zBooleanValue, decodeCallbacks), this.bitmapPool);
        } finally {
            releaseOptions(defaultOptions);
            this.byteArrayPool.put(bArr);
        }
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.resource.bitmap.Downsampler$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements DecodeCallbacks {
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }
    }
}
