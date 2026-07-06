package androidx.media3.common.text;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.media3.common.Bundleable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Cue implements Bundleable {
    public static final int ANCHOR_TYPE_END = 2;
    public static final int ANCHOR_TYPE_MIDDLE = 1;
    public static final int ANCHOR_TYPE_START = 0;

    @UnstableApi
    @Deprecated
    public static final Bundleable.Creator<Cue> CREATOR;
    public static final float DIMEN_UNSET = -3.4028235E38f;

    @Deprecated
    public static final Cue EMPTY;
    public static final String FIELD_BITMAP_BYTES;
    public static final String FIELD_BITMAP_HEIGHT;
    public static final String FIELD_BITMAP_PARCELABLE;
    public static final String FIELD_CUSTOM_SPANS;
    public static final String FIELD_LINE;
    public static final String FIELD_LINE_ANCHOR;
    public static final String FIELD_LINE_TYPE;
    public static final String FIELD_MULTI_ROW_ALIGNMENT;
    public static final String FIELD_POSITION;
    public static final String FIELD_POSITION_ANCHOR;
    public static final String FIELD_SHEAR_DEGREES;
    public static final String FIELD_SIZE;
    public static final String FIELD_TEXT;
    public static final String FIELD_TEXT_ALIGNMENT;
    public static final String FIELD_TEXT_SIZE;
    public static final String FIELD_TEXT_SIZE_TYPE;
    public static final String FIELD_VERTICAL_TYPE;
    public static final String FIELD_WINDOW_COLOR;
    public static final String FIELD_WINDOW_COLOR_SET;
    public static final int LINE_TYPE_FRACTION = 0;
    public static final int LINE_TYPE_NUMBER = 1;
    public static final int TEXT_SIZE_TYPE_ABSOLUTE = 2;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL = 0;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL_IGNORE_PADDING = 1;
    public static final int TYPE_UNSET = Integer.MIN_VALUE;
    public static final int VERTICAL_TYPE_LR = 2;
    public static final int VERTICAL_TYPE_RL = 1;

    @Nullable
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;

    @Nullable
    public final Layout.Alignment multiRowAlignment;
    public final float position;
    public final int positionAnchor;
    public final float shearDegrees;
    public final float size;

    @Nullable
    public final CharSequence text;

    @Nullable
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final int windowColor;
    public final boolean windowColorSet;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnchorType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @UnstableApi
    public static final class Builder {

        @Nullable
        public Bitmap bitmap;
        public float bitmapHeight;
        public float line;
        public int lineAnchor;
        public int lineType;

        @Nullable
        public Layout.Alignment multiRowAlignment;
        public float position;
        public int positionAnchor;
        public float shearDegrees;
        public float size;

        @Nullable
        public CharSequence text;

        @Nullable
        public Layout.Alignment textAlignment;
        public float textSize;
        public int textSizeType;
        public int verticalType;

        @ColorInt
        public int windowColor;
        public boolean windowColorSet;

        public Cue build() {
            return new Cue(this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.textSizeType, this.textSize, this.size, this.bitmapHeight, this.windowColorSet, this.windowColor, this.verticalType, this.shearDegrees);
        }

        @CanIgnoreReturnValue
        public Builder clearWindowColor() {
            this.windowColorSet = false;
            return this;
        }

        @Nullable
        @Pure
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        @Pure
        public float getBitmapHeight() {
            return this.bitmapHeight;
        }

        @Pure
        public float getLine() {
            return this.line;
        }

        @Pure
        public int getLineAnchor() {
            return this.lineAnchor;
        }

        @Pure
        public int getLineType() {
            return this.lineType;
        }

        @Pure
        public float getPosition() {
            return this.position;
        }

        @Pure
        public int getPositionAnchor() {
            return this.positionAnchor;
        }

        @Pure
        public float getSize() {
            return this.size;
        }

        @Nullable
        @Pure
        public CharSequence getText() {
            return this.text;
        }

        @Nullable
        @Pure
        public Layout.Alignment getTextAlignment() {
            return this.textAlignment;
        }

        @Pure
        public float getTextSize() {
            return this.textSize;
        }

        @Pure
        public int getTextSizeType() {
            return this.textSizeType;
        }

        @Pure
        public int getVerticalType() {
            return this.verticalType;
        }

        @ColorInt
        @Pure
        public int getWindowColor() {
            return this.windowColor;
        }

        public boolean isWindowColorSet() {
            return this.windowColorSet;
        }

        @CanIgnoreReturnValue
        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setBitmapHeight(float f) {
            this.bitmapHeight = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLine(float f, int i) {
            this.line = f;
            this.lineType = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setLineAnchor(int i) {
            this.lineAnchor = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMultiRowAlignment(@Nullable Layout.Alignment alignment) {
            this.multiRowAlignment = alignment;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPosition(float f) {
            this.position = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPositionAnchor(int i) {
            this.positionAnchor = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setShearDegrees(float f) {
            this.shearDegrees = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSize(float f) {
            this.size = f;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setText(CharSequence charSequence) {
            this.text = charSequence;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTextAlignment(@Nullable Layout.Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTextSize(float f, int i) {
            this.textSize = f;
            this.textSizeType = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setVerticalType(int i) {
            this.verticalType = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setWindowColor(@ColorInt int i) {
            this.windowColor = i;
            this.windowColorSet = true;
            return this;
        }

        public Builder() {
            this.text = null;
            this.bitmap = null;
            this.textAlignment = null;
            this.multiRowAlignment = null;
            this.line = -3.4028235E38f;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.textSizeType = Integer.MIN_VALUE;
            this.textSize = -3.4028235E38f;
            this.size = -3.4028235E38f;
            this.bitmapHeight = -3.4028235E38f;
            this.windowColorSet = false;
            this.windowColor = -16777216;
            this.verticalType = Integer.MIN_VALUE;
        }

        public Builder(Cue cue) {
            this.text = cue.text;
            this.bitmap = cue.bitmap;
            this.textAlignment = cue.textAlignment;
            this.multiRowAlignment = cue.multiRowAlignment;
            this.line = cue.line;
            this.lineType = cue.lineType;
            this.lineAnchor = cue.lineAnchor;
            this.position = cue.position;
            this.positionAnchor = cue.positionAnchor;
            this.textSizeType = cue.textSizeType;
            this.textSize = cue.textSize;
            this.size = cue.size;
            this.bitmapHeight = cue.bitmapHeight;
            this.windowColorSet = cue.windowColorSet;
            this.windowColor = cue.windowColor;
            this.verticalType = cue.verticalType;
            this.shearDegrees = cue.shearDegrees;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface LineType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextSizeType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VerticalType {
    }

    static {
        Builder builder = new Builder();
        builder.text = "";
        EMPTY = builder.build();
        FIELD_TEXT = Util.intToStringMaxRadix(0);
        FIELD_CUSTOM_SPANS = Integer.toString(17, 36);
        FIELD_TEXT_ALIGNMENT = Integer.toString(1, 36);
        FIELD_MULTI_ROW_ALIGNMENT = Integer.toString(2, 36);
        FIELD_BITMAP_PARCELABLE = Integer.toString(3, 36);
        FIELD_BITMAP_BYTES = Integer.toString(18, 36);
        FIELD_LINE = Integer.toString(4, 36);
        FIELD_LINE_TYPE = Integer.toString(5, 36);
        FIELD_LINE_ANCHOR = Integer.toString(6, 36);
        FIELD_POSITION = Integer.toString(7, 36);
        FIELD_POSITION_ANCHOR = Integer.toString(8, 36);
        FIELD_TEXT_SIZE_TYPE = Integer.toString(9, 36);
        FIELD_TEXT_SIZE = Integer.toString(10, 36);
        FIELD_SIZE = Integer.toString(11, 36);
        FIELD_BITMAP_HEIGHT = Integer.toString(12, 36);
        FIELD_WINDOW_COLOR = Integer.toString(13, 36);
        FIELD_WINDOW_COLOR_SET = Integer.toString(14, 36);
        FIELD_VERTICAL_TYPE = Integer.toString(15, 36);
        FIELD_SHEAR_DEGREES = Integer.toString(16, 36);
        CREATOR = new Cue$$ExternalSyntheticLambda0();
    }

    @UnstableApi
    public static Cue fromBundle(Bundle bundle) {
        Builder builder = new Builder();
        CharSequence charSequence = bundle.getCharSequence(FIELD_TEXT);
        if (charSequence != null) {
            builder.text = charSequence;
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_CUSTOM_SPANS);
            if (parcelableArrayList != null) {
                SpannableString spannableStringValueOf = SpannableString.valueOf(charSequence);
                int size = parcelableArrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = parcelableArrayList.get(i);
                    i++;
                    CustomSpanBundler.unbundleAndApplyCustomSpan((Bundle) obj, spannableStringValueOf);
                }
                builder.text = spannableStringValueOf;
            }
        }
        Layout.Alignment alignment = (Layout.Alignment) bundle.getSerializable(FIELD_TEXT_ALIGNMENT);
        if (alignment != null) {
            builder.textAlignment = alignment;
        }
        Layout.Alignment alignment2 = (Layout.Alignment) bundle.getSerializable(FIELD_MULTI_ROW_ALIGNMENT);
        if (alignment2 != null) {
            builder.multiRowAlignment = alignment2;
        }
        Bitmap bitmap = (Bitmap) bundle.getParcelable(FIELD_BITMAP_PARCELABLE);
        if (bitmap != null) {
            builder.bitmap = bitmap;
        } else {
            byte[] byteArray = bundle.getByteArray(FIELD_BITMAP_BYTES);
            if (byteArray != null) {
                builder.bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
        }
        String str = FIELD_LINE;
        if (bundle.containsKey(str)) {
            String str2 = FIELD_LINE_TYPE;
            if (bundle.containsKey(str2)) {
                float f = bundle.getFloat(str);
                int i2 = bundle.getInt(str2);
                builder.line = f;
                builder.lineType = i2;
            }
        }
        String str3 = FIELD_LINE_ANCHOR;
        if (bundle.containsKey(str3)) {
            builder.lineAnchor = bundle.getInt(str3);
        }
        String str4 = FIELD_POSITION;
        if (bundle.containsKey(str4)) {
            builder.position = bundle.getFloat(str4);
        }
        String str5 = FIELD_POSITION_ANCHOR;
        if (bundle.containsKey(str5)) {
            builder.positionAnchor = bundle.getInt(str5);
        }
        String str6 = FIELD_TEXT_SIZE;
        if (bundle.containsKey(str6)) {
            String str7 = FIELD_TEXT_SIZE_TYPE;
            if (bundle.containsKey(str7)) {
                float f2 = bundle.getFloat(str6);
                int i3 = bundle.getInt(str7);
                builder.textSize = f2;
                builder.textSizeType = i3;
            }
        }
        String str8 = FIELD_SIZE;
        if (bundle.containsKey(str8)) {
            builder.size = bundle.getFloat(str8);
        }
        String str9 = FIELD_BITMAP_HEIGHT;
        if (bundle.containsKey(str9)) {
            builder.bitmapHeight = bundle.getFloat(str9);
        }
        String str10 = FIELD_WINDOW_COLOR;
        if (bundle.containsKey(str10)) {
            builder.setWindowColor(bundle.getInt(str10));
        }
        if (!bundle.getBoolean(FIELD_WINDOW_COLOR_SET, false)) {
            builder.windowColorSet = false;
        }
        String str11 = FIELD_VERTICAL_TYPE;
        if (bundle.containsKey(str11)) {
            builder.verticalType = bundle.getInt(str11);
        }
        String str12 = FIELD_SHEAR_DEGREES;
        if (bundle.containsKey(str12)) {
            builder.shearDegrees = bundle.getFloat(str12);
        }
        return builder.build();
    }

    @UnstableApi
    public Builder buildUpon() {
        return new Builder(this);
    }

    public boolean equals(@Nullable Object obj) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (this == obj) {
            return true;
        }
        if (obj != null && Cue.class == obj.getClass()) {
            Cue cue = (Cue) obj;
            if (TextUtils.equals(this.text, cue.text) && this.textAlignment == cue.textAlignment && this.multiRowAlignment == cue.multiRowAlignment && ((bitmap = this.bitmap) != null ? !((bitmap2 = cue.bitmap) == null || !bitmap.sameAs(bitmap2)) : cue.bitmap == null) && this.line == cue.line && this.lineType == cue.lineType && this.lineAnchor == cue.lineAnchor && this.position == cue.position && this.positionAnchor == cue.positionAnchor && this.size == cue.size && this.bitmapHeight == cue.bitmapHeight && this.windowColorSet == cue.windowColorSet && this.windowColor == cue.windowColor && this.textSizeType == cue.textSizeType && this.textSize == cue.textSize && this.verticalType == cue.verticalType && this.shearDegrees == cue.shearDegrees) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, Float.valueOf(this.line), Integer.valueOf(this.lineType), Integer.valueOf(this.lineAnchor), Float.valueOf(this.position), Integer.valueOf(this.positionAnchor), Float.valueOf(this.size), Float.valueOf(this.bitmapHeight), Boolean.valueOf(this.windowColorSet), Integer.valueOf(this.windowColor), Integer.valueOf(this.textSizeType), Float.valueOf(this.textSize), Integer.valueOf(this.verticalType), Float.valueOf(this.shearDegrees)});
    }

    @UnstableApi
    public Bundle toBinderBasedBundle() {
        Bundle bundleWithoutBitmap = toBundleWithoutBitmap();
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            bundleWithoutBitmap.putParcelable(FIELD_BITMAP_PARCELABLE, bitmap);
        }
        return bundleWithoutBitmap;
    }

    @Override // androidx.media3.common.Bundleable
    @UnstableApi
    @Deprecated
    public Bundle toBundle() {
        return toBinderBasedBundle();
    }

    public final Bundle toBundleWithoutBitmap() {
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.text;
        if (charSequence != null) {
            bundle.putCharSequence(FIELD_TEXT, charSequence);
            CharSequence charSequence2 = this.text;
            if (charSequence2 instanceof Spanned) {
                ArrayList<Bundle> arrayListBundleCustomSpans = CustomSpanBundler.bundleCustomSpans((Spanned) charSequence2);
                if (!arrayListBundleCustomSpans.isEmpty()) {
                    bundle.putParcelableArrayList(FIELD_CUSTOM_SPANS, arrayListBundleCustomSpans);
                }
            }
        }
        bundle.putSerializable(FIELD_TEXT_ALIGNMENT, this.textAlignment);
        bundle.putSerializable(FIELD_MULTI_ROW_ALIGNMENT, this.multiRowAlignment);
        bundle.putFloat(FIELD_LINE, this.line);
        bundle.putInt(FIELD_LINE_TYPE, this.lineType);
        bundle.putInt(FIELD_LINE_ANCHOR, this.lineAnchor);
        bundle.putFloat(FIELD_POSITION, this.position);
        bundle.putInt(FIELD_POSITION_ANCHOR, this.positionAnchor);
        bundle.putInt(FIELD_TEXT_SIZE_TYPE, this.textSizeType);
        bundle.putFloat(FIELD_TEXT_SIZE, this.textSize);
        bundle.putFloat(FIELD_SIZE, this.size);
        bundle.putFloat(FIELD_BITMAP_HEIGHT, this.bitmapHeight);
        bundle.putBoolean(FIELD_WINDOW_COLOR_SET, this.windowColorSet);
        bundle.putInt(FIELD_WINDOW_COLOR, this.windowColor);
        bundle.putInt(FIELD_VERTICAL_TYPE, this.verticalType);
        bundle.putFloat(FIELD_SHEAR_DEGREES, this.shearDegrees);
        return bundle;
    }

    @UnstableApi
    public Bundle toSerializableBundle() {
        Bundle bundleWithoutBitmap = toBundleWithoutBitmap();
        if (this.bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Assertions.checkState(this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream));
            bundleWithoutBitmap.putByteArray(FIELD_BITMAP_BYTES, byteArrayOutputStream.toByteArray());
        }
        return bundleWithoutBitmap;
    }

    public Cue(@Nullable CharSequence charSequence, @Nullable Layout.Alignment alignment, @Nullable Layout.Alignment alignment2, @Nullable Bitmap bitmap, float f, int i, int i2, float f2, int i3, int i4, float f3, float f4, float f5, boolean z, int i5, int i6, float f6) {
        if (charSequence == null) {
            bitmap.getClass();
        } else {
            Assertions.checkArgument(bitmap == null);
        }
        if (charSequence instanceof Spanned) {
            this.text = SpannedString.valueOf(charSequence);
        } else if (charSequence != null) {
            this.text = charSequence.toString();
        } else {
            this.text = null;
        }
        this.textAlignment = alignment;
        this.multiRowAlignment = alignment2;
        this.bitmap = bitmap;
        this.line = f;
        this.lineType = i;
        this.lineAnchor = i2;
        this.position = f2;
        this.positionAnchor = i3;
        this.size = f4;
        this.bitmapHeight = f5;
        this.windowColorSet = z;
        this.windowColor = i5;
        this.textSizeType = i4;
        this.textSize = f3;
        this.verticalType = i6;
        this.shearDegrees = f6;
    }
}
