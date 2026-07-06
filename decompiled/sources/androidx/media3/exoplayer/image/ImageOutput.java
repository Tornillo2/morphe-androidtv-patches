package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface ImageOutput {
    public static final ImageOutput NO_OP = new AnonymousClass1();

    void onDisabled();

    void onImageAvailable(long j, Bitmap bitmap);

    /* JADX INFO: renamed from: androidx.media3.exoplayer.image.ImageOutput$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements ImageOutput {
        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onDisabled() {
        }

        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onImageAvailable(long j, Bitmap bitmap) {
        }
    }
}
