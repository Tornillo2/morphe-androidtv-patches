package androidx.media3.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new DataSourceBitmapLoader$$ExternalSyntheticLambda0());
    public final DataSource.Factory dataSourceFactory;
    public final ListeningExecutorService listeningExecutorService;

    @Nullable
    public final BitmapFactory.Options options;

    /* JADX WARN: Illegal instructions before constructor call */
    public DataSourceBitmapLoader(Context context) {
        ListeningExecutorService listeningExecutorService = DEFAULT_EXECUTOR_SERVICE.get();
        Assertions.checkStateNotNull(listeningExecutorService);
        this(listeningExecutorService, new DefaultDataSource.Factory(context), null);
    }

    public static Bitmap decode(byte[] bArr, @Nullable BitmapFactory.Options options) throws IOException {
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Assertions.checkArgument(bitmapDecodeByteArray != null, "Could not decode image data");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
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
    }

    public static Bitmap load(DataSource dataSource, Uri uri, @Nullable BitmapFactory.Options options) throws IOException {
        try {
            dataSource.open(new DataSpec(uri));
            return decode(DataSourceUtil.readToEnd(dataSource), options);
        } finally {
            dataSource.close();
        }
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(final byte[] bArr) {
        return this.listeningExecutorService.submit(new Callable() { // from class: androidx.media3.datasource.DataSourceBitmapLoader$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DataSourceBitmapLoader.decode(bArr, this.f$0.options);
            }
        });
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(final Uri uri) {
        return this.listeningExecutorService.submit(new Callable() { // from class: androidx.media3.datasource.DataSourceBitmapLoader$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DataSourceBitmapLoader dataSourceBitmapLoader = this.f$0;
                return DataSourceBitmapLoader.load(dataSourceBitmapLoader.dataSourceFactory.createDataSource(), uri, dataSourceBitmapLoader.options);
            }
        });
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public /* synthetic */ ListenableFuture loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        return BitmapLoader.CC.$default$loadBitmapFromMetadata(this, mediaMetadata);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService, DataSource.Factory factory) {
        this(listeningExecutorService, factory, null);
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService, DataSource.Factory factory, @Nullable BitmapFactory.Options options) {
        this.listeningExecutorService = listeningExecutorService;
        this.dataSourceFactory = factory;
        this.options = options;
    }
}
