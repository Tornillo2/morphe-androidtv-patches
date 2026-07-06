package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class StandardGifDecoder implements GifDecoder {
    public static final int BYTES_PER_INTEGER = 4;

    @ColorInt
    public static final int COLOR_TRANSPARENT_BLACK = 0;
    public static final int INITIAL_FRAME_POINTER = -1;
    public static final int MASK_INT_LOWEST_BYTE = 255;
    public static final int MAX_STACK_SIZE = 4096;
    public static final int NULL_CODE = -1;
    public static final String TAG = "StandardGifDecoder";

    @ColorInt
    public int[] act;

    @NonNull
    public Bitmap.Config bitmapConfig;
    public final GifDecoder.BitmapProvider bitmapProvider;
    public byte[] block;
    public int downsampledHeight;
    public int downsampledWidth;
    public int framePointer;
    public GifHeader header;

    @Nullable
    public Boolean isFirstFrameTransparent;
    public byte[] mainPixels;

    @ColorInt
    public int[] mainScratch;
    public GifHeaderParser parser;

    @ColorInt
    public final int[] pct;
    public byte[] pixelStack;
    public short[] prefix;
    public Bitmap previousImage;
    public ByteBuffer rawData;
    public int sampleSize;
    public boolean savePrevious;
    public int status;
    public byte[] suffix;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    @ColorInt
    public final int averageColorsNear(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.sampleSize + i; i9++) {
            byte[] bArr = this.mainPixels;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.act[bArr[i9] & 255];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.sampleSize + i11; i12++) {
            byte[] bArr2 = this.mainPixels;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.act[bArr2[i12] & 255];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    public final void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = this.mainScratch;
        int i5 = gifFrame.ih;
        int i6 = this.sampleSize;
        int i7 = i5 / i6;
        int i8 = gifFrame.iy / i6;
        int i9 = gifFrame.iw / i6;
        int i10 = gifFrame.ix / i6;
        boolean z = this.framePointer == 0;
        int i11 = this.downsampledWidth;
        int i12 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int i13 = 8;
        int i14 = 0;
        int i15 = 0;
        int i16 = 1;
        while (i15 < i7) {
            int[] iArr3 = iArr;
            if (gifFrame.interlace) {
                if (i14 >= i7) {
                    int i17 = i16 + 1;
                    i = i7;
                    if (i17 == 2) {
                        i16 = i17;
                        i14 = 4;
                    } else if (i17 == 3) {
                        i16 = i17;
                        i14 = 2;
                        i13 = 4;
                    } else if (i17 != 4) {
                        i16 = i17;
                    } else {
                        i16 = i17;
                        i14 = 1;
                        i13 = 2;
                    }
                } else {
                    i = i7;
                }
                i2 = i14 + i13;
            } else {
                i = i7;
                i2 = i14;
                i14 = i15;
            }
            int i18 = i14 + i8;
            boolean z2 = i6 == 1;
            if (i18 < i12) {
                int i19 = i18 * i11;
                int i20 = i19 + i10;
                i3 = i2;
                int i21 = i20 + i9;
                int i22 = i19 + i11;
                if (i22 < i21) {
                    i21 = i22;
                }
                i4 = i8;
                int i23 = i15 * i6 * gifFrame.iw;
                if (z2) {
                    while (i20 < i21) {
                        int i24 = iArr2[bArr[i23] & 255];
                        if (i24 != 0) {
                            iArr3[i20] = i24;
                        } else if (z && bool == null) {
                            bool = Boolean.TRUE;
                        }
                        i23 += i6;
                        i20++;
                    }
                } else {
                    int iM = ColorUtils$$ExternalSyntheticOutline0.m(i21, i20, i6, i23);
                    while (i20 < i21) {
                        int i25 = i21;
                        int iAverageColorsNear = averageColorsNear(i23, iM, gifFrame.iw);
                        if (iAverageColorsNear != 0) {
                            iArr3[i20] = iAverageColorsNear;
                        } else if (z && bool == null) {
                            bool = Boolean.TRUE;
                        }
                        i23 += i6;
                        i20++;
                        i21 = i25;
                    }
                }
            } else {
                i3 = i2;
                i4 = i8;
            }
            i15++;
            iArr = iArr3;
            i7 = i;
            i14 = i3;
            i8 = i4;
        }
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        }
    }

    public final void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i = gifFrame2.ih;
        int i2 = gifFrame2.iy;
        int i3 = gifFrame2.iw;
        int i4 = gifFrame2.ix;
        boolean z = this.framePointer == 0;
        int i5 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        int i6 = 0;
        byte b = -1;
        while (i6 < i) {
            int i7 = (i6 + i2) * i5;
            int i8 = i7 + i4;
            int i9 = i8 + i3;
            int i10 = i7 + i5;
            if (i10 < i9) {
                i9 = i10;
            }
            int i11 = gifFrame2.iw * i6;
            int i12 = i8;
            while (i12 < i9) {
                byte b2 = bArr[i11];
                int[] iArr3 = iArr;
                int i13 = b2 & 255;
                if (i13 != b) {
                    int i14 = iArr2[i13];
                    if (i14 != 0) {
                        iArr3[i12] = i14;
                    } else {
                        b = b2;
                    }
                }
                i11++;
                i12++;
                iArr = iArr3;
            }
            i6++;
            gifFrame2 = gifFrame;
        }
        Boolean bool = this.isFirstFrameTransparent;
        this.isFirstFrameTransparent = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.isFirstFrameTransparent == null && z && b != -1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v15, types: [short] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final void decodeBitmapData(GifFrame gifFrame) {
        int i;
        int i2;
        byte[] bArr;
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (gifFrame != null) {
            standardGifDecoder.rawData.position(gifFrame.bufferFrameStart);
        }
        if (gifFrame == null) {
            GifHeader gifHeader = standardGifDecoder.header;
            i = gifHeader.width;
            i2 = gifHeader.height;
        } else {
            i = gifFrame.iw;
            i2 = gifFrame.ih;
        }
        int i3 = i * i2;
        byte[] bArr2 = standardGifDecoder.mainPixels;
        if (bArr2 == null || bArr2.length < i3) {
            standardGifDecoder.mainPixels = standardGifDecoder.bitmapProvider.obtainByteArray(i3);
        }
        byte[] bArr3 = standardGifDecoder.mainPixels;
        if (standardGifDecoder.prefix == null) {
            standardGifDecoder.prefix = new short[4096];
        }
        short[] sArr = standardGifDecoder.prefix;
        if (standardGifDecoder.suffix == null) {
            standardGifDecoder.suffix = new byte[4096];
        }
        byte[] bArr4 = standardGifDecoder.suffix;
        if (standardGifDecoder.pixelStack == null) {
            standardGifDecoder.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        byte[] bArr5 = standardGifDecoder.pixelStack;
        int i4 = standardGifDecoder.readByte();
        int i5 = 1 << i4;
        int i6 = i5 + 1;
        int i7 = i5 + 2;
        int i8 = i4 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i5; i10++) {
            sArr[i10] = 0;
            bArr4[i10] = (byte) i10;
        }
        byte[] bArr6 = standardGifDecoder.block;
        int i11 = i8;
        int i12 = i7;
        int i13 = i9;
        int i14 = 0;
        int block = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = -1;
        int i20 = 0;
        int i21 = 0;
        while (true) {
            if (i14 >= i3) {
                break;
            }
            if (block == 0) {
                block = standardGifDecoder.readBlock();
                if (block <= 0) {
                    standardGifDecoder.status = 3;
                    break;
                }
                i15 = 0;
            }
            i17 += (bArr6[i15] & 255) << i16;
            i15++;
            block--;
            int i22 = i16 + 8;
            int i23 = i12;
            int i24 = i11;
            int i25 = i19;
            short[] sArr2 = sArr;
            int i26 = i20;
            while (true) {
                bArr = bArr4;
                if (i22 < i24) {
                    i12 = i23;
                    i20 = i26;
                    break;
                }
                int i27 = i17 & i13;
                i17 >>= i24;
                i22 -= i24;
                if (i27 == i5) {
                    i24 = i8;
                    i23 = i7;
                    i13 = i9;
                    bArr4 = bArr;
                    i25 = -1;
                } else {
                    if (i27 == i6) {
                        i20 = i26;
                        i12 = i23;
                        break;
                    }
                    byte[] bArr7 = bArr5;
                    if (i25 == -1) {
                        bArr3[i18] = bArr[i27];
                        i18++;
                        i14++;
                        i25 = i27;
                        i26 = i25;
                        bArr4 = bArr;
                        bArr5 = bArr7;
                    } else {
                        if (i27 >= i23) {
                            bArr7[i21] = (byte) i26;
                            i21++;
                            s = i25;
                        } else {
                            s = i27;
                        }
                        while (s >= i5) {
                            bArr7[i21] = bArr[s];
                            i21++;
                            s = sArr2[s];
                        }
                        int i28 = bArr[s] & 255;
                        byte b = (byte) i28;
                        bArr3[i18] = b;
                        while (true) {
                            i18++;
                            i14++;
                            if (i21 <= 0) {
                                break;
                            }
                            i21--;
                            bArr3[i18] = bArr7[i21];
                        }
                        if (i23 < 4096) {
                            sArr2[i23] = (short) i25;
                            bArr[i23] = b;
                            i23++;
                            if ((i23 & i13) == 0 && i23 < 4096) {
                                i24++;
                                i13 += i23;
                            }
                        }
                        i25 = i27;
                        bArr4 = bArr;
                        bArr5 = bArr7;
                        i26 = i28;
                    }
                }
            }
            i16 = i22;
            sArr = sArr2;
            bArr4 = bArr;
            i19 = i25;
            i11 = i24;
            standardGifDecoder = this;
        }
        Arrays.fill(bArr3, i18, i3, (byte) 0);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return (this.mainScratch.length * 4) + this.rawData.limit() + this.mainPixels.length;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @NonNull
    public ByteBuffer getData() {
        return this.rawData;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int i) {
        if (i < 0) {
            return -1;
        }
        GifHeader gifHeader = this.header;
        if (i < gifHeader.frameCount) {
            return gifHeader.frames.get(i).delay;
        }
        return -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.header.frameCount;
    }

    @NonNull
    public final GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.header.height;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        int i = this.header.loopCount;
        if (i == -1) {
            return 1;
        }
        return i;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public final Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap bitmapObtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        bitmapObtain.setHasAlpha(true);
        return bitmapObtain;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Nullable
    public synchronized Bitmap getNextFrame() {
        try {
            if (this.header.frameCount <= 0 || this.framePointer < 0) {
                String str = TAG;
                if (Log.isLoggable(str, 3)) {
                    Log.d(str, "Unable to decode frame, frameCount=" + this.header.frameCount + ", framePointer=" + this.framePointer);
                }
                this.status = 1;
            }
            int i = this.status;
            if (i != 1 && i != 2) {
                this.status = 0;
                if (this.block == null) {
                    this.block = this.bitmapProvider.obtainByteArray(255);
                }
                GifFrame gifFrame = this.header.frames.get(this.framePointer);
                int i2 = this.framePointer - 1;
                GifFrame gifFrame2 = i2 >= 0 ? this.header.frames.get(i2) : null;
                int[] iArr = gifFrame.lct;
                if (iArr == null) {
                    iArr = this.header.gct;
                }
                this.act = iArr;
                if (iArr == null) {
                    String str2 = TAG;
                    if (Log.isLoggable(str2, 3)) {
                        Log.d(str2, "No valid color table found for frame #" + this.framePointer);
                    }
                    this.status = 1;
                    return null;
                }
                if (gifFrame.transparency) {
                    System.arraycopy(iArr, 0, this.pct, 0, iArr.length);
                    int[] iArr2 = this.pct;
                    this.act = iArr2;
                    iArr2[gifFrame.transIndex] = 0;
                    if (gifFrame.dispose == 2 && this.framePointer == 0) {
                        this.isFirstFrameTransparent = Boolean.TRUE;
                    }
                }
                return setPixels(gifFrame, gifFrame2);
            }
            String str3 = TAG;
            if (Log.isLoggable(str3, 3)) {
                Log.d(str3, "Unable to decode frame, status=" + this.status);
            }
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        int i = this.header.loopCount;
        if (i == -1) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        return i + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.header.width;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(@Nullable InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, 16384);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public final int readBlock() {
        int i = readByte();
        if (i <= 0) {
            return i;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(i, byteBuffer.remaining()));
        return i;
    }

    public final int readByte() {
        return this.rawData.get() & 255;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(@NonNull Bitmap.Config config) {
        Bitmap.Config config2;
        Bitmap.Config config3 = Bitmap.Config.ARGB_8888;
        if (config == config3 || config == (config2 = Bitmap.Config.RGB_565)) {
            this.bitmapConfig = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + config3 + " or " + config2);
    }

    public final Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i;
        int i2;
        Bitmap bitmap;
        int[] iArr = this.mainScratch;
        int i3 = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && (i2 = gifFrame2.dispose) > 0) {
            if (i2 == 2) {
                if (!gifFrame.transparency) {
                    GifHeader gifHeader = this.header;
                    int i4 = gifHeader.bgColor;
                    if (gifFrame.lct == null || gifHeader.bgIndex != gifFrame.transIndex) {
                        i3 = i4;
                    }
                }
                int i5 = gifFrame2.ih;
                int i6 = this.sampleSize;
                int i7 = i5 / i6;
                int i8 = gifFrame2.iy / i6;
                int i9 = gifFrame2.iw / i6;
                int i10 = gifFrame2.ix / i6;
                int i11 = this.downsampledWidth;
                int i12 = (i8 * i11) + i10;
                int i13 = (i7 * i11) + i12;
                while (i12 < i13) {
                    int i14 = i12 + i9;
                    for (int i15 = i12; i15 < i14; i15++) {
                        iArr[i15] = i3;
                    }
                    i12 += this.downsampledWidth;
                }
            } else if (i2 == 3 && (bitmap = this.previousImage) != null) {
                int i16 = this.downsampledWidth;
                bitmap.getPixels(iArr, 0, i16, 0, 0, i16, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && ((i = gifFrame.dispose) == 0 || i == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i17 = this.downsampledWidth;
            bitmap3.setPixels(iArr, 0, i17, 0, 0, i17, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i18 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i18, 0, 0, i18, this.downsampledHeight);
        return nextBitmap;
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, i);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider;
        this.header = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i) {
        try {
            if (i > 0) {
                int iHighestOneBit = Integer.highestOneBit(i);
                this.status = 0;
                this.header = gifHeader;
                this.framePointer = -1;
                ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                this.rawData = byteBufferAsReadOnlyBuffer;
                byteBufferAsReadOnlyBuffer.position(0);
                this.rawData.order(ByteOrder.LITTLE_ENDIAN);
                this.savePrevious = false;
                Iterator<GifFrame> it = gifHeader.frames.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                }
                this.sampleSize = iHighestOneBit;
                int i2 = gifHeader.width;
                this.downsampledWidth = i2 / iHighestOneBit;
                int i3 = gifHeader.height;
                this.downsampledHeight = i3 / iHighestOneBit;
                this.mainPixels = this.bitmapProvider.obtainByteArray(i2 * i3);
                this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
            } else {
                throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(@Nullable byte[] bArr) {
        try {
            GifHeader header = getHeaderParser().setData(bArr).parseHeader();
            this.header = header;
            if (bArr != null) {
                setData(header, bArr);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.status;
    }
}
