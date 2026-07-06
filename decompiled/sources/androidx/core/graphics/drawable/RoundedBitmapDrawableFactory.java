package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RoundedBitmapDrawableFactory {
    public static final String TAG = "RoundedBitmapDrawableFa";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        public DefaultRoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
            super(resources, bitmap);
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public void gravityCompatApply(int i, int i2, int i3, Rect rect, Rect rect2) {
            Gravity.apply(i, i2, i3, rect, rect2, 0);
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public boolean hasMipMap() {
            Bitmap bitmap = this.mBitmap;
            return bitmap != null && bitmap.hasMipMap();
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public void setMipMap(boolean z) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                bitmap.setHasMipMap(z);
                invalidateSelf();
            }
        }
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @Nullable Bitmap bitmap) {
        return new RoundedBitmapDrawable21(resources, bitmap);
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @NonNull String str) {
        RoundedBitmapDrawable21 roundedBitmapDrawable21 = new RoundedBitmapDrawable21(resources, BitmapFactory.decodeFile(str));
        if (roundedBitmapDrawable21.mBitmap == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + str);
        }
        return roundedBitmapDrawable21;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources, @NonNull InputStream inputStream) {
        RoundedBitmapDrawable21 roundedBitmapDrawable21 = new RoundedBitmapDrawable21(resources, BitmapFactory.decodeStream(inputStream));
        if (roundedBitmapDrawable21.mBitmap == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + inputStream);
        }
        return roundedBitmapDrawable21;
    }
}
