package androidx.core.util;

import android.util.SizeF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SizeFCompat {
    public final float mHeight;
    public final float mWidth;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static final class Api21Impl {
        @NonNull
        public static SizeF toSizeF(@NonNull SizeFCompat sizeFCompat) {
            sizeFCompat.getClass();
            return new SizeF(sizeFCompat.mWidth, sizeFCompat.mHeight);
        }

        @NonNull
        public static SizeFCompat toSizeFCompat(@NonNull SizeF sizeF) {
            sizeF.getClass();
            return new SizeFCompat(sizeF.getWidth(), sizeF.getHeight());
        }
    }

    public SizeFCompat(float f, float f2) {
        Preconditions.checkArgumentFinite(f, "width");
        this.mWidth = f;
        Preconditions.checkArgumentFinite(f2, "height");
        this.mHeight = f2;
    }

    @NonNull
    @RequiresApi(21)
    public static SizeFCompat toSizeFCompat(@NonNull SizeF sizeF) {
        return Api21Impl.toSizeFCompat(sizeF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeFCompat)) {
            return false;
        }
        SizeFCompat sizeFCompat = (SizeFCompat) obj;
        return sizeFCompat.mWidth == this.mWidth && sizeFCompat.mHeight == this.mHeight;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.mWidth) ^ Float.floatToIntBits(this.mHeight);
    }

    @NonNull
    @RequiresApi(21)
    public SizeF toSizeF() {
        return Api21Impl.toSizeF(this);
    }

    @NonNull
    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }
}
