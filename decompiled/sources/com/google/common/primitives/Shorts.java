package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.InlineMe;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class Shorts extends ShortsMethodsForWeb {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        public static /* synthetic */ LexicographicalComparator[] $values() {
            return new LexicographicalComparator[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(short[] left, short[] right) {
            int iMin = Math.min(left.length, right.length);
            for (int i = 0; i < iMin; i++) {
                int iCompare = Short.compare(left[i], right[i]);
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            return left.length - right.length;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final short[] array;
        public final int end;
        public final int start;

        public ShortArrayAsList(short[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Short) && Shorts.indexOf(this.array, ((Short) target).shortValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof ShortArrayAsList)) {
                return super.equals(object);
            }
            ShortArrayAsList shortArrayAsList = (ShortArrayAsList) object;
            int size = size();
            if (shortArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == shortArrayAsList.array[shortArrayAsList.start + i]) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i = 1;
            for (int i2 = this.start; i2 < this.end; i2++) {
                i = (i * 31) + this.array[i2];
            }
            return i;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object target) {
            int iIndexOf;
            if (!(target instanceof Short) || (iIndexOf = Shorts.indexOf(this.array, ((Short) target).shortValue(), this.start, this.end)) < 0) {
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
            if (!(target instanceof Short) || (iLastIndexOf = Shorts.lastIndexOf(this.array, ((Short) target).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Short> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            short[] sArr = this.array;
            int i = this.start;
            return new ShortArrayAsList(sArr, fromIndex + i, i + toIndex);
        }

        public short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 6);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append((int) this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i >= this.end) {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.array[i]);
            }
        }

        public ShortArrayAsList(short[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Short get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Short.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Short set(int index, Short element) {
            Preconditions.checkElementIndex(index, size());
            short[] sArr = this.array;
            int i = this.start + index;
            short s = sArr[i];
            element.getClass();
            sArr[i] = element.shortValue();
            return Short.valueOf(s);
        }
    }

    public static List<Short> asList(short... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new ShortArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    public static short checkedCast(long value) {
        short s = (short) value;
        Preconditions.checkArgument(((long) s) == value, "Out of range: %s", value);
        return s;
    }

    @InlineMe(replacement = "Short.compare(a, b)")
    public static int compare(short a, short b) {
        return Short.compare(a, b);
    }

    public static short[] concat(short[]... arrays) {
        long length = 0;
        for (short[] sArr : arrays) {
            length += (long) sArr.length;
        }
        short[] sArr2 = new short[checkNoOverflow(length)];
        int length2 = 0;
        for (short[] sArr3 : arrays) {
            System.arraycopy(sArr3, 0, sArr2, length2, sArr3.length);
            length2 += sArr3.length;
        }
        return sArr2;
    }

    public static short constrainToRange(short value, short min, short max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", (int) min, (int) max);
        return value < min ? min : value < max ? value : max;
    }

    public static boolean contains(short[] array, short target) {
        for (short s : array) {
            if (s == target) {
                return true;
            }
        }
        return false;
    }

    public static short[] ensureCapacity(short[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 2, "array too small: %s < %s", bytes.length, 2);
        return fromBytes(bytes[0], bytes[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b1, byte b2) {
        return (short) ((b1 << 8) | (b2 & 255));
    }

    public static int indexOf(short[] array, short target) {
        return indexOf(array, target, 0, array.length);
    }

    public static String join(String separator, short... array) {
        separator.getClass();
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 6);
        sb.append((int) array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(separator);
            sb.append((int) array[i]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(short[] array, short target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short max(short... array) {
        Preconditions.checkArgument(array.length > 0);
        short s = array[0];
        for (int i = 1; i < array.length; i++) {
            short s2 = array[i];
            if (s2 > s) {
                s = s2;
            }
        }
        return s;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static short min(short... array) {
        Preconditions.checkArgument(array.length > 0);
        short s = array[0];
        for (int i = 1; i < array.length; i++) {
            short s2 = array[i];
            if (s2 < s) {
                s = s2;
            }
        }
        return s;
    }

    public static void reverse(short[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(short[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static short saturatedCast(long value) {
        return value > 32767 ? ShortCompanionObject.MAX_VALUE : value < -32768 ? ShortCompanionObject.MIN_VALUE : (short) value;
    }

    public static void sortDescending(short[] array) {
        array.getClass();
        sortDescending(array, 0, array.length);
    }

    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).toShortArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            sArr[i] = ((Number) obj).shortValue();
        }
        return sArr;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short value) {
        return new byte[]{(byte) (value >> 8), (byte) value};
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ShortConverter extends Converter<String, Short> implements Serializable {
        public static final Converter<String, Short> INSTANCE = new ShortConverter(true);

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 1;

        public ShortConverter() {
            super(true);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.base.Converter
        public String doBackward(Short value) {
            return value.toString();
        }

        @Override // com.google.common.base.Converter
        public Short doForward(String value) {
            return Short.decode(value);
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }

        /* JADX INFO: renamed from: doBackward, reason: avoid collision after fix types in other method */
        public String doBackward2(Short value) {
            return value.toString();
        }

        /* JADX INFO: renamed from: doForward, reason: avoid collision after fix types in other method */
        public Short doForward2(String value) {
            return Short.decode(value);
        }
    }

    public static int indexOf(short[] array, short target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(short[] array, int distance, int fromIndex, int toIndex) {
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

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(short[] r5, short[] r6) {
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
            if (r0 >= r2) goto L2a
            r2 = 0
        L18:
            int r3 = r6.length
            if (r2 >= r3) goto L29
            int r3 = r0 + r2
            short r3 = r5[r3]
            short r4 = r6[r2]
            if (r3 == r4) goto L26
            int r0 = r0 + 1
            goto L10
        L26:
            int r2 = r2 + 1
            goto L18
        L29:
            return r0
        L2a:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Shorts.indexOf(short[], short[]):int");
    }

    public static void reverse(short[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            short s = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = s;
            fromIndex++;
        }
    }

    public static void sortDescending(short[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static int hashCode(short value) {
        return value;
    }
}
