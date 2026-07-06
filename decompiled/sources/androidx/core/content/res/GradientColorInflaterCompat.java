package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class GradientColorInflaterCompat {
    public static final int TILE_MODE_CLAMP = 0;
    public static final int TILE_MODE_MIRROR = 2;
    public static final int TILE_MODE_REPEAT = 1;

    public static ColorStops checkColors(@Nullable ColorStops colorStops, @ColorInt int i, @ColorInt int i2, boolean z, @ColorInt int i3) {
        return colorStops != null ? colorStops : z ? new ColorStops(i, i3, i2) : new ColorStops(i, i2);
    }

    public static Shader createFromXml(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return createFromXmlInner(resources, xmlPullParser, attributeSetAsAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayObtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R.styleable.GradientColor);
        float f = !TypedArrayUtils.hasAttribute(xmlPullParser, "startX") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_startX, 0.0f);
        float f2 = !TypedArrayUtils.hasAttribute(xmlPullParser, "startY") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_startY, 0.0f);
        float f3 = !TypedArrayUtils.hasAttribute(xmlPullParser, "endX") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_endX, 0.0f);
        float f4 = !TypedArrayUtils.hasAttribute(xmlPullParser, "endY") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_endY, 0.0f);
        float f5 = !TypedArrayUtils.hasAttribute(xmlPullParser, "centerX") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_centerX, 0.0f);
        float f6 = !TypedArrayUtils.hasAttribute(xmlPullParser, "centerY") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_centerY, 0.0f);
        int i = !TypedArrayUtils.hasAttribute(xmlPullParser, "type") ? 0 : typedArrayObtainAttributes.getInt(R.styleable.GradientColor_android_type, 0);
        int color = !TypedArrayUtils.hasAttribute(xmlPullParser, "startColor") ? 0 : typedArrayObtainAttributes.getColor(R.styleable.GradientColor_android_startColor, 0);
        boolean z = xmlPullParser.getAttributeValue(TypedArrayUtils.NAMESPACE, "centerColor") != null;
        int color2 = !TypedArrayUtils.hasAttribute(xmlPullParser, "centerColor") ? 0 : typedArrayObtainAttributes.getColor(R.styleable.GradientColor_android_centerColor, 0);
        int color3 = !TypedArrayUtils.hasAttribute(xmlPullParser, "endColor") ? 0 : typedArrayObtainAttributes.getColor(R.styleable.GradientColor_android_endColor, 0);
        int i2 = !TypedArrayUtils.hasAttribute(xmlPullParser, "tileMode") ? 0 : typedArrayObtainAttributes.getInt(R.styleable.GradientColor_android_tileMode, 0);
        float f7 = !TypedArrayUtils.hasAttribute(xmlPullParser, "gradientRadius") ? 0.0f : typedArrayObtainAttributes.getFloat(R.styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayObtainAttributes.recycle();
        ColorStops colorStopsCheckColors = checkColors(inflateChildElements(resources, xmlPullParser, attributeSet, theme), color, color3, z, color2);
        if (i != 1) {
            return i != 2 ? new LinearGradient(f, f2, f3, f4, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(i2)) : new SweepGradient(f5, f6, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets);
        }
        if (f7 > 0.0f) {
            return new RadialGradient(f5, f6, f7, colorStopsCheckColors.mColors, colorStopsCheckColors.mOffsets, parseTileMode(i2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        return new androidx.core.content.res.GradientColorInflaterCompat.ColorStops(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.core.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(@androidx.annotation.NonNull android.content.res.Resources r8, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r9, @androidx.annotation.NonNull android.util.AttributeSet r10, @androidx.annotation.Nullable android.content.res.Resources.Theme r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r9.next()
            if (r3 == r1) goto L85
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L85
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = androidx.core.R.styleable.GradientColorItem
            android.content.res.TypedArray r3 = androidx.core.content.res.TypedArrayUtils.obtainAttributes(r8, r11, r10, r3)
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L6a
            if (r6 == 0) goto L6a
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L6a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L85:
            int r8 = r4.size()
            if (r8 <= 0) goto L91
            androidx.core.content.res.GradientColorInflaterCompat$ColorStops r8 = new androidx.core.content.res.GradientColorInflaterCompat$ColorStops
            r8.<init>(r4, r2)
            return r8
        L91:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.core.content.res.GradientColorInflaterCompat$ColorStops");
    }

    public static Shader.TileMode parseTileMode(int i) {
        return i != 1 ? i != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR : Shader.TileMode.REPEAT;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ColorStops {
        public final int[] mColors;
        public final float[] mOffsets;

        public ColorStops(@NonNull List<Integer> list, @NonNull List<Float> list2) {
            int size = list.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = list.get(i).intValue();
                this.mOffsets[i] = list2.get(i).floatValue();
            }
        }

        public ColorStops(@ColorInt int i, @ColorInt int i2) {
            this.mColors = new int[]{i, i2};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        public ColorStops(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
            this.mColors = new int[]{i, i2, i3};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
