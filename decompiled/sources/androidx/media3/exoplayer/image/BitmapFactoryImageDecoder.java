package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class BitmapFactoryImageDecoder extends SimpleDecoder<DecoderInputBuffer, ImageOutputBuffer, ImageDecoderException> implements ImageDecoder {
    public final BitmapDecoder bitmapDecoder;

    /* JADX INFO: renamed from: androidx.media3.exoplayer.image.BitmapFactoryImageDecoder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends ImageOutputBuffer {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public void release() {
            BitmapFactoryImageDecoder.this.releaseOutputBuffer(this);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting(otherwise = 2)
    public interface BitmapDecoder {
        Bitmap decode(byte[] bArr, int i) throws ImageDecoderException;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements ImageDecoder.Factory {
        public final BitmapDecoder bitmapDecoder;

        public Factory() {
            this.bitmapDecoder = new BitmapFactoryImageDecoder$Factory$$ExternalSyntheticLambda0();
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public int supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return (str == null || !MimeTypes.isImage(str)) ? RendererCapabilities.CC.create(0, 0, 0, 0) : Util.isBitmapFactorySupportedMimeType(format.sampleMimeType) ? RendererCapabilities.CC.create(4, 0, 0, 0) : RendererCapabilities.CC.create(1, 0, 0, 0);
        }

        @Override // androidx.media3.exoplayer.image.ImageDecoder.Factory
        public BitmapFactoryImageDecoder createImageDecoder() {
            return new BitmapFactoryImageDecoder(this.bitmapDecoder);
        }

        public Factory(BitmapDecoder bitmapDecoder) {
            this.bitmapDecoder = bitmapDecoder;
        }
    }

    public /* synthetic */ BitmapFactoryImageDecoder(BitmapDecoder bitmapDecoder, AnonymousClass1 anonymousClass1) {
        this(bitmapDecoder);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1, 0);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderOutputBuffer createOutputBuffer() {
        return new AnonymousClass1();
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    @Nullable
    public /* bridge */ /* synthetic */ ImageOutputBuffer dequeueOutputBuffer() throws ImageDecoderException {
        return dequeueOutputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "BitmapFactoryImageDecoder";
    }

    public BitmapFactoryImageDecoder(BitmapDecoder bitmapDecoder) {
        super(new DecoderInputBuffer[1], new ImageOutputBuffer[1]);
        this.bitmapDecoder = bitmapDecoder;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageOutputBuffer createOutputBuffer() {
        return new AnonymousClass1();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public ImageDecoderException createUnexpectedDecodeException(Throwable th) {
        return new ImageDecoderException("Unexpected decode error", th);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    @Nullable
    public ImageDecoderException decode(DecoderInputBuffer decoderInputBuffer, ImageOutputBuffer imageOutputBuffer, boolean z) {
        try {
            ByteBuffer byteBuffer = decoderInputBuffer.data;
            byteBuffer.getClass();
            Assertions.checkState(byteBuffer.hasArray());
            Assertions.checkArgument(byteBuffer.arrayOffset() == 0);
            imageOutputBuffer.bitmap = this.bitmapDecoder.decode(byteBuffer.array(), byteBuffer.remaining());
            imageOutputBuffer.timeUs = decoderInputBuffer.timeUs;
            return null;
        } catch (ImageDecoderException e) {
            return e;
        }
    }

    public static Bitmap decode(byte[] bArr, int i) throws ImageDecoderException {
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, i);
        if (bitmapDecodeByteArray != null) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 0, i);
                try {
                    ExifInterface exifInterface = new ExifInterface(byteArrayInputStream, 0);
                    byteArrayInputStream.close();
                    int rotationDegrees = exifInterface.getRotationDegrees();
                    if (rotationDegrees == 0) {
                        return bitmapDecodeByteArray;
                    }
                    Matrix matrix = new Matrix();
                    matrix.postRotate(rotationDegrees);
                    return Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, false);
                } finally {
                }
            } catch (IOException e) {
                throw new ImageDecoderException(e);
            }
        } else {
            throw new ImageDecoderException("Could not decode image data with BitmapFactory. (data.length = " + bArr.length + ", input length = " + i + ")");
        }
    }
}
