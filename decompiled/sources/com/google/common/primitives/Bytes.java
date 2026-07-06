package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Bytes {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @GwtCompatible
    public static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {

        @J2ktIncompatible
        @GwtIncompatible
        public static final long serialVersionUID = 0;
        public final byte[] array;
        public final int end;
        public final int start;

        public ByteArrayAsList(byte[] array) {
            this(array, 0, array.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object target) {
            return (target instanceof Byte) && Bytes.indexOf(this.array, ((Byte) target).byteValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof ByteArrayAsList)) {
                return super.equals(object);
            }
            ByteArrayAsList byteArrayAsList = (ByteArrayAsList) object;
            int size = size();
            if (byteArrayAsList.size() == size) {
                for (int i = 0; i < size; i++) {
                    if (this.array[this.start + i] == byteArrayAsList.array[byteArrayAsList.start + i]) {
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
            if (!(target instanceof Byte) || (iIndexOf = Bytes.indexOf(this.array, ((Byte) target).byteValue(), this.start, this.end)) < 0) {
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
            if (!(target instanceof Byte) || (iLastIndexOf = Bytes.lastIndexOf(this.array, ((Byte) target).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Byte> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.EMPTY_LIST;
            }
            byte[] bArr = this.array;
            int i = this.start;
            return new ByteArrayAsList(bArr, fromIndex + i, i + toIndex);
        }

        public byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        public ByteArrayAsList(byte[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Byte.valueOf(this.array[this.start + index]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte set(int index, Byte element) {
            Preconditions.checkElementIndex(index, size());
            byte[] bArr = this.array;
            int i = this.start + index;
            byte b = bArr[i];
            element.getClass();
            bArr[i] = element.byteValue();
            return Byte.valueOf(b);
        }
    }

    public static List<Byte> asList(byte... backingArray) {
        return backingArray.length == 0 ? Collections.EMPTY_LIST : new ByteArrayAsList(backingArray, 0, backingArray.length);
    }

    public static int checkNoOverflow(long result) {
        int i = (int) result;
        Preconditions.checkArgument(result == ((long) i), "the total number of elements (%s) in the arrays must fit in an int", result);
        return i;
    }

    public static byte[] concat(byte[]... arrays) {
        long length = 0;
        for (byte[] bArr : arrays) {
            length += (long) bArr.length;
        }
        byte[] bArr2 = new byte[checkNoOverflow(length)];
        int length2 = 0;
        for (byte[] bArr3 : arrays) {
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr3.length;
        }
        return bArr2;
    }

    public static boolean contains(byte[] array, byte target) {
        for (byte b : array) {
            if (b == target) {
                return true;
            }
        }
        return false;
    }

    public static byte[] ensureCapacity(byte[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static int indexOf(byte[] array, byte target) {
        return indexOf(array, target, 0, array.length);
    }

    public static int lastIndexOf(byte[] array, byte target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    public static void reverse(byte[] array) {
        array.getClass();
        reverse(array, 0, array.length);
    }

    public static void rotate(byte[] array, int distance) {
        rotate(array, distance, 0, array.length);
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).toByteArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            Object obj = array[i];
            obj.getClass();
            bArr[i] = ((Number) obj).byteValue();
        }
        return bArr;
    }

    public static int indexOf(byte[] array, byte target, int start, int end) {
        while (start < end) {
            if (array[start] == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void rotate(byte[] array, int distance, int fromIndex, int toIndex) {
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
    public static int indexOf(byte[] r5, byte[] r6) {
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
            r3 = r5[r3]
            r4 = r6[r2]
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Bytes.indexOf(byte[], byte[]):int");
    }

    public static void reverse(byte[] array, int fromIndex, int toIndex) {
        array.getClass();
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = toIndex - 1; fromIndex < i; i--) {
            byte b = array[fromIndex];
            array[fromIndex] = array[i];
            array[i] = b;
            fromIndex++;
        }
    }

    public static int hashCode(byte value) {
        return value;
    }
}
