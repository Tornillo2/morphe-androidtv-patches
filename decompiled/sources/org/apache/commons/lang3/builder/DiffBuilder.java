package org.apache.commons.lang3.builder;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class DiffBuilder implements Builder<DiffResult> {
    public final List<Diff<?>> diffs;
    public final Object left;
    public final boolean objectsTriviallyEqual;
    public final Object right;
    public final ToStringStyle style;

    public DiffBuilder(Object obj, Object obj2, ToStringStyle toStringStyle, boolean z) {
        boolean z2 = true;
        Validate.isTrue(obj != null, "lhs cannot be null", new Object[0]);
        Validate.isTrue(obj2 != null, "rhs cannot be null", new Object[0]);
        this.diffs = new ArrayList();
        this.left = obj;
        this.right = obj2;
        this.style = toStringStyle;
        if (!z || (obj != obj2 && !obj.equals(obj2))) {
            z2 = false;
        }
        this.objectsTriviallyEqual = z2;
    }

    public DiffBuilder append(String str, final boolean z, final boolean z2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && z != z2) {
            this.diffs.add(new Diff<Boolean>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.1
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Boolean getLeft() {
                    return Boolean.valueOf(z);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Boolean getRight() {
                    return Boolean.valueOf(z2);
                }
            });
        }
        return this;
    }

    public final void validateFieldNameNotNull(String str) {
        Validate.isTrue(str != null, "Field name cannot be null", new Object[0]);
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public DiffResult build() {
        return new DiffResult(this.left, this.right, this.diffs, this.style);
    }

    public DiffBuilder append(String str, final boolean[] zArr, final boolean[] zArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(zArr, zArr2)) {
            this.diffs.add((Diff<?>) new Diff<Boolean[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.2
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Boolean[] getLeft() {
                    return ArrayUtils.toObject(zArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Boolean[] getRight() {
                    return ArrayUtils.toObject(zArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final byte b, final byte b2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && b != b2) {
            this.diffs.add(new Diff<Byte>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.3
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Byte getLeft() {
                    return Byte.valueOf(b);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Byte getRight() {
                    return Byte.valueOf(b2);
                }
            });
        }
        return this;
    }

    public DiffBuilder(Object obj, Object obj2, ToStringStyle toStringStyle) {
        this(obj, obj2, toStringStyle, true);
    }

    public DiffBuilder append(String str, final byte[] bArr, final byte[] bArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(bArr, bArr2)) {
            this.diffs.add((Diff<?>) new Diff<Byte[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.4
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Byte[] getLeft() {
                    return ArrayUtils.toObject(bArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Byte[] getRight() {
                    return ArrayUtils.toObject(bArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final char c, final char c2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && c != c2) {
            this.diffs.add(new Diff<Character>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.5
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Character getLeft() {
                    return Character.valueOf(c);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Character getRight() {
                    return Character.valueOf(c2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final char[] cArr, final char[] cArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(cArr, cArr2)) {
            this.diffs.add((Diff<?>) new Diff<Character[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.6
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Character[] getLeft() {
                    return ArrayUtils.toObject(cArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Character[] getRight() {
                    return ArrayUtils.toObject(cArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final double d, final double d2) {
        validateFieldNameNotNull(str);
        if (this.objectsTriviallyEqual || Double.doubleToLongBits(d) == Double.doubleToLongBits(d2)) {
            return this;
        }
        this.diffs.add(new Diff<Double>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.7
            public static final long serialVersionUID = 1;

            @Override // org.apache.commons.lang3.tuple.Pair
            public Double getLeft() {
                return Double.valueOf(d);
            }

            @Override // org.apache.commons.lang3.tuple.Pair
            public Double getRight() {
                return Double.valueOf(d2);
            }
        });
        return this;
    }

    public DiffBuilder append(String str, final double[] dArr, final double[] dArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(dArr, dArr2)) {
            this.diffs.add((Diff<?>) new Diff<Double[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.8
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Double[] getLeft() {
                    return ArrayUtils.toObject(dArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Double[] getRight() {
                    return ArrayUtils.toObject(dArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final float f, final float f2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && Float.floatToIntBits(f) != Float.floatToIntBits(f2)) {
            this.diffs.add(new Diff<Float>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.9
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Float getLeft() {
                    return Float.valueOf(f);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Float getRight() {
                    return Float.valueOf(f2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final float[] fArr, final float[] fArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(fArr, fArr2)) {
            this.diffs.add((Diff<?>) new Diff<Float[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.10
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Float[] getLeft() {
                    return ArrayUtils.toObject(fArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Float[] getRight() {
                    return ArrayUtils.toObject(fArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final int i, final int i2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && i != i2) {
            this.diffs.add(new Diff<Integer>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.11
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Integer getLeft() {
                    return Integer.valueOf(i);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Integer getRight() {
                    return Integer.valueOf(i2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final int[] iArr, final int[] iArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(iArr, iArr2)) {
            this.diffs.add((Diff<?>) new Diff<Integer[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.12
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Integer[] getLeft() {
                    return ArrayUtils.toObject(iArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Integer[] getRight() {
                    return ArrayUtils.toObject(iArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final long j, final long j2) {
        validateFieldNameNotNull(str);
        if (this.objectsTriviallyEqual || j == j2) {
            return this;
        }
        this.diffs.add(new Diff<Long>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.13
            public static final long serialVersionUID = 1;

            @Override // org.apache.commons.lang3.tuple.Pair
            public Long getLeft() {
                return Long.valueOf(j);
            }

            @Override // org.apache.commons.lang3.tuple.Pair
            public Long getRight() {
                return Long.valueOf(j2);
            }
        });
        return this;
    }

    public DiffBuilder append(String str, final long[] jArr, final long[] jArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(jArr, jArr2)) {
            this.diffs.add((Diff<?>) new Diff<Long[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.14
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Long[] getLeft() {
                    return ArrayUtils.toObject(jArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Long[] getRight() {
                    return ArrayUtils.toObject(jArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final short s, final short s2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && s != s2) {
            this.diffs.add(new Diff<Short>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.15
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Short getLeft() {
                    return Short.valueOf(s);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Short getRight() {
                    return Short.valueOf(s2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final short[] sArr, final short[] sArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(sArr, sArr2)) {
            this.diffs.add((Diff<?>) new Diff<Short[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.16
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Short[] getLeft() {
                    return ArrayUtils.toObject(sArr);
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Short[] getRight() {
                    return ArrayUtils.toObject(sArr2);
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, final Object obj, final Object obj2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && obj != obj2) {
            Object obj3 = obj != null ? obj : obj2;
            if (obj3.getClass().isArray()) {
                if (obj3 instanceof boolean[]) {
                    return append(str, (boolean[]) obj, (boolean[]) obj2);
                }
                if (obj3 instanceof byte[]) {
                    return append(str, (byte[]) obj, (byte[]) obj2);
                }
                if (obj3 instanceof char[]) {
                    return append(str, (char[]) obj, (char[]) obj2);
                }
                if (obj3 instanceof double[]) {
                    return append(str, (double[]) obj, (double[]) obj2);
                }
                if (obj3 instanceof float[]) {
                    return append(str, (float[]) obj, (float[]) obj2);
                }
                if (obj3 instanceof int[]) {
                    return append(str, (int[]) obj, (int[]) obj2);
                }
                if (obj3 instanceof long[]) {
                    return append(str, (long[]) obj, (long[]) obj2);
                }
                if (obj3 instanceof short[]) {
                    return append(str, (short[]) obj, (short[]) obj2);
                }
                return append(str, (Object[]) obj, (Object[]) obj2);
            }
            if (obj == null || !obj.equals(obj2)) {
                this.diffs.add(new Diff<Object>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.17
                    public static final long serialVersionUID = 1;

                    @Override // org.apache.commons.lang3.tuple.Pair
                    public Object getLeft() {
                        return obj;
                    }

                    @Override // org.apache.commons.lang3.tuple.Pair
                    public Object getRight() {
                        return obj2;
                    }
                });
                return this;
            }
        }
        return this;
    }

    public DiffBuilder append(String str, final Object[] objArr, final Object[] objArr2) {
        validateFieldNameNotNull(str);
        if (!this.objectsTriviallyEqual && !Arrays.equals(objArr, objArr2)) {
            this.diffs.add((Diff<?>) new Diff<Object[]>(str) { // from class: org.apache.commons.lang3.builder.DiffBuilder.18
                public static final long serialVersionUID = 1;

                @Override // org.apache.commons.lang3.tuple.Pair
                public Object[] getLeft() {
                    return objArr;
                }

                @Override // org.apache.commons.lang3.tuple.Pair
                public Object[] getRight() {
                    return objArr2;
                }
            });
        }
        return this;
    }

    public DiffBuilder append(String str, DiffResult diffResult) {
        validateFieldNameNotNull(str);
        Validate.isTrue(diffResult != null, "Diff result cannot be null", new Object[0]);
        if (!this.objectsTriviallyEqual) {
            for (Diff<?> diff : diffResult.getDiffs()) {
                StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(str, ExternalFourCCMapper.CODEC_NAME_SPLITTER);
                sbM.append(diff.fieldName);
                append(sbM.toString(), diff.getLeft(), diff.getRight());
            }
        }
        return this;
    }
}
