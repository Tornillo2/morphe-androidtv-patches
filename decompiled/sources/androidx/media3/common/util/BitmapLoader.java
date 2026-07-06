package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface BitmapLoader {

    /* JADX INFO: renamed from: androidx.media3.common.util.BitmapLoader$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @Nullable
        public static ListenableFuture $default$loadBitmapFromMetadata(BitmapLoader bitmapLoader, MediaMetadata mediaMetadata) {
            byte[] bArr = mediaMetadata.artworkData;
            if (bArr != null) {
                return bitmapLoader.decodeBitmap(bArr);
            }
            Uri uri = mediaMetadata.artworkUri;
            if (uri != null) {
                return bitmapLoader.loadBitmap(uri);
            }
            return null;
        }
    }

    ListenableFuture<Bitmap> decodeBitmap(byte[] bArr);

    ListenableFuture<Bitmap> loadBitmap(Uri uri);

    @Nullable
    ListenableFuture<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata);

    boolean supportsMimeType(String str);
}
