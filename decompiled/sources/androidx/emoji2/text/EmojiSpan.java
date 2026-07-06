package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
public abstract class EmojiSpan extends ReplacementSpan {

    @NonNull
    public final TypefaceEmojiRasterizer mRasterizer;
    public final Paint.FontMetricsInt mTmpFontMetrics = new Paint.FontMetricsInt();
    public short mWidth = -1;
    public short mHeight = -1;
    public float mRatio = 1.0f;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public EmojiSpan(@NonNull TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        Preconditions.checkNotNull(typefaceEmojiRasterizer, "rasterizer cannot be null");
        this.mRasterizer = typefaceEmojiRasterizer;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public final int getHeight() {
        return this.mHeight;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public final int getId() {
        return this.mRasterizer.getId();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final float getRatio() {
        return this.mRatio;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, @SuppressLint({"UnknownNullness"}) CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        paint.getFontMetricsInt(this.mTmpFontMetrics);
        Paint.FontMetricsInt fontMetricsInt2 = this.mTmpFontMetrics;
        this.mRatio = (Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f) / this.mRasterizer.getHeight();
        this.mHeight = (short) (this.mRasterizer.getHeight() * this.mRatio);
        short width = (short) (this.mRasterizer.getWidth() * this.mRatio);
        this.mWidth = width;
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt3 = this.mTmpFontMetrics;
            fontMetricsInt.ascent = fontMetricsInt3.ascent;
            fontMetricsInt.descent = fontMetricsInt3.descent;
            fontMetricsInt.top = fontMetricsInt3.top;
            fontMetricsInt.bottom = fontMetricsInt3.bottom;
        }
        return width;
    }

    @NonNull
    public final TypefaceEmojiRasterizer getTypefaceRasterizer() {
        return this.mRasterizer;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final int getWidth() {
        return this.mWidth;
    }
}
