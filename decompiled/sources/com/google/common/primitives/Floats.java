package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.errorprone.annotations.InlineMe;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Floats extends FloatsMethodsForWeb {
    public static final int BYTES = 4;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final float[] array;
        public final int end;
        public final int start;

        public FloatArrayAsList(float[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Float) && Floats.indexOf(this.array, ((Float) target).floatValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof FloatArrayAsList)) {
                return super.equals(object);
            }
            FloatArrayAsList floatArrayAsList = (FloatArrayAsList) object;
            int size = size();
            if (floatArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == floatArrayAsList.array[floatArrayAsList.start + i]) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i = this.start; i < this.end; i++) {
                iHashCode = (iHashCode * 31) + Floats.hashCode(this.array[i]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            int iIndexOf;
            if (!(target instanceof Float) || (iIndexOf = Floats.indexOf(this.array, ((Float) target).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object target) {
            int iLastIndexOf;
            if (!(target instanceof Float) || (iLastIndexOf = Floats.lastIndexOf(this.array, ((Float) target).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Float> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            float[] fArr = this.array;
            int i = this.start;
            return new FloatArrayAsList(fArr, fromIndex + i, i + toIndex);
        }

        public float[] toFloatArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i >= this.end) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append(this.array[i]);
            }
        }

        public FloatArrayAsList(float[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Float get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Float.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Float set(int index, Float element) {
            Preconditions.checkElementIndex(index, size());
            float[] fArr = this.array;
            int i = this.start + index;
            float f = fArr[i];
            element.getClass();
            fArr[i] = element.floatValue();
            return Float.valueOf(f);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<float[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Floats.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(float[] left, float[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Float.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    public static List<Float> asList(float... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new FloatArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    @InlineMe(replacement = "Float.compare(a, b)")
    public static int compare(float a, float b) {
        return Float.compare(a, b);
    }

    public static float[] concat(float[]... arrays) {
        long length = 0;
        for (float[] fArr : arrays) {
            length += (long) fArr.length;
        }
        float[] fArr2 = new float[checkNoOverflow(length)];
        int length2 = 0;
        for (float[] fArr3 : arrays) {
            System.arraycopy(fArr3, 0, fArr2, length2, fArr3.length);
            length2 += fArr3.length;
        }
        return fArr2;
    }

    public static float constrainToRange(float value, float min, float max) {
        if (min <= max) {
            return Math.min(Math.max(value, min), max);
        }
        throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", Float.valueOf(min), Float.valueOf(max)));
    }

    public static boolean contains(float[] array, float target) {
        for (float f : array) {
            if (f == target) {
                return true;
            }
        }
        return false;
    }

    public static float[] ensureCapacity(float[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static int hashCode(float value) {
        return Float.valueOf(value).hashCode();
    }

    public static int indexOf(float[] array, float target) {
        return indexOf(array, target, 0, array.length);
    }

    public static boolean isFinite(float value) {
        return Float.NEGATIVE_INFINITY < value && value < Float.POSITIVE_INFINITY;
    }

    public static String join(String separator, float... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 12);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(float[] array, float target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static Comparator<float[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static float max(float... array) {
        Preconditions.checkArgument(array.length > 0);
        float fMax = array[0];
        for (int i = 1; i < array.length; i++) {
            fMax = Math.max(fMax, array[i]);
        }
        return fMax;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static float min(float... array) {
        Preconditions.checkArgument(array.length > 0);
        float fMin = array[0];
        for (int i = 1; i < array.length; i++) {
            fMin = Math.min(fMin, array[i]);
        }
        return fMin;
    }

    public static void reverse(float[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(float[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static void sortDescending(float[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static Converter<String, Float> stringConverter() {
        return FloatConverter.INSTANCE;
    }

    public static float[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof FloatArrayAsList) {
            return ((FloatArrayAsList) collection).toFloatArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            fArr[i] = ((Number) obj).floatValue();
        }
        return fArr;
    }

    @GwtIncompatible
    public static Float tryParse(String string) {
        if (!Doubles.FLOATING_POINT_PATTERN.matcher(string).matches()) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(string));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FloatConverter extends Converter<String, Float> implements Serializable {
        public static final Converter<String, Float> INSTANCE = new FloatConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        public FloatConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Float value) {
            return value.toString();
        }

        @Override // com.google.common.base.Converter
        public Float doForward(String value) {
            return Float.valueOf(value);
        }

        public String toString() {
            return "Floats.stringConverter()";
        }

        /* JADX INFO: renamed from: doBackward, reason: avoid collision after fix types in other method */
        public String doBackward2(Float value) {
            return value.toString();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public Float doForward2(String value) {
            return Float.valueOf(value);
        }
    }

    public static int indexOf(float[] array, float target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(float[] array, float target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(float[] array, int distance, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        if (array.length <= 1) {
            return;
        }
        int i = toIndex - fromIndex;
        int i2 = (-distance) % i;
        if (i2 < 0) {
            i2 += i;
        }
        int i3 = i2 + fromIndex;
        if (i3 == fromIndex) {
            return;
        }
        reverse(array, fromIndex, i3);
        reverse(array, i3, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(float[] r5, float[] r6) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r5, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r6, r0)
            int r0 = r6.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r5.length
            int r3 = r6.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2c
            r2 = 0
        L18:
            int r3 = r6.length
            if (r2 >= r3) goto L2b
            int r3 = r0 + r2
            r3 = r5[r3]
            r4 = r6[r2]
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L28
            int r0 = r0 + 1
            goto L10
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            return r0
        L2c:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Floats.indexOf(float[], float[]):int");
    }

    public static void reverse(float[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            float f = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = f;
            fromIndex++;
        }
    }

    public static void sortDescending(float[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }
}
