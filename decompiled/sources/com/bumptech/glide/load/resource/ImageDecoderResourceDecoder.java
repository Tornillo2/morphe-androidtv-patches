package com.bumptech.glide.load.resource;

import android.annotation.SuppressLint;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 28)
public abstract class ImageDecoderResourceDecoder<T> implements ResourceDecoder<ImageDecoder.Source, T> {
    public static final String TAG = "ImageDecoder";
    public final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();

    public abstract Resource<T> decode(ImageDecoder.Source source, int i, int i2, ImageDecoder.OnHeaderDecodedListener onHeaderDecodedListener) throws IOException;

    @Override // com.bumptech.glide.load.ResourceDecoder
    @Nullable
    public /* bridge */ /* synthetic */ Resource decode(@NonNull ImageDecoder.Source source, int i, int i2, @NonNull Options options) throws IOException {
        return decode2(ImageDecoderResourceDecoder$$ExternalSyntheticApiModelOutline0.m(source), i, i2, options);
    }

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public final boolean handles2(@NonNull ImageDecoder.Source source, @NonNull Options options) {
        return true;
    }

    @Nullable
    /* JADX INFO: renamed from: decode, reason: avoid collision after fix types in other method */
    public final Resource<T> decode2(@NonNull ImageDecoder.Source source, final int i, final int i2, @NonNull Options options) throws IOException {
        final DecodeFormat decodeFormat = (DecodeFormat) options.get(Downsampler.DECODE_FORMAT);
        final DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        Option<Boolean> option = Downsampler.ALLOW_HARDWARE_CONFIG;
        final boolean z = options.get(option) != null && ((Boolean) options.get(option)).booleanValue();
        final PreferredColorSpace preferredColorSpace = (PreferredColorSpace) options.get(Downsampler.PREFERRED_COLOR_SPACE);
        return decode(source, i, i2, new ImageDecoder.OnHeaderDecodedListener() { // from class: com.bumptech.glide.load.resource.ImageDecoderResourceDecoder.1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            @SuppressLint({"Override"})
            public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source2) {
                if (ImageDecoderResourceDecoder.this.hardwareConfigState.isHardwareConfigAllowed(i, i2, z, false)) {
                    imageDecoder.setAllocator(3);
                } else {
                    imageDecoder.setAllocator(1);
                }
                if (decodeFormat == DecodeFormat.PREFER_RGB_565) {
                    imageDecoder.setMemorySizePolicy(0);
                }
                imageDecoder.setOnPartialImageListener(new ImageDecoder.OnPartialImageListener() { // from class: com.bumptech.glide.load.resource.ImageDecoderResourceDecoder.1.1
                    @Override // android.graphics.ImageDecoder.OnPartialImageListener
                    public boolean onPartialImage(@NonNull ImageDecoder.DecodeException decodeException) {
                        return false;
                    }
                });
                Size size = imageInfo.getSize();
                int width = i;
                if (width == Integer.MIN_VALUE) {
                    width = size.getWidth();
                }
                int height = i2;
                if (height == Integer.MIN_VALUE) {
                    height = size.getHeight();
                }
                float scaleFactor = downsampleStrategy.getScaleFactor(size.getWidth(), size.getHeight(), width, height);
                int iRound = Math.round(size.getWidth() * scaleFactor);
                int iRound2 = Math.round(size.getHeight() * scaleFactor);
                if (Log.isLoggable(ImageDecoderResourceDecoder.TAG, 2)) {
                    Log.v(ImageDecoderResourceDecoder.TAG, "Resizing from [" + size.getWidth() + "x" + size.getHeight() + "] to [" + iRound + "x" + iRound2 + "] scaleFactor: " + scaleFactor);
                }
                imageDecoder.setTargetSize(iRound, iRound2);
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 28) {
                    imageDecoder.setTargetColorSpace(ColorSpace.get((preferredColorSpace == PreferredColorSpace.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
                } else if (i3 >= 26) {
                    imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
                }
            }
        });
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(@NonNull ImageDecoder.Source source, @NonNull Options options) throws IOException {
        ImageDecoderResourceDecoder$$ExternalSyntheticApiModelOutline0.m(source);
        return true;
    }
}
