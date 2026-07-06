package androidx.collection;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSimpleArrayMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,769:1\n297#1,5:770\n297#1,5:775\n1#2:780\n*S KotlinDebug\n*F\n+ 1 SimpleArrayMap.kt\nandroidx/collection/SimpleArrayMap\n*L\n276#1:770,5\n291#1:775,5\n*E\n"})
public class SimpleArrayMap<K, V> {

    @NotNull
    public Object[] array;

    @NotNull
    public int[] hashes;
    public int size;

    @JvmOverloads
    public SimpleArrayMap() {
        this(0, 1, null);
    }

    @JvmName(name = "__restricted$indexOfValue")
    public final int __restricted$indexOfValue(V v) {
        int i = this.size * 2;
        Object[] objArr = this.array;
        if (v == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (v.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public void clear() {
        if (this.size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this.size = 0;
        }
        if (this.size > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(K k) {
        return indexOfKey(k) >= 0;
    }

    public boolean containsValue(V v) {
        return __restricted$indexOfValue(v) >= 0;
    }

    public void ensureCapacity(int i) {
        int i2 = this.size;
        int[] iArr = this.hashes;
        if (iArr.length < i) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, i);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.hashes = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.array, i * 2);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.array = objArrCopyOf;
        }
        if (this.size != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(@Nullable Object obj) {
        int i;
        int i2;
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                if (size() == ((SimpleArrayMap) obj).size()) {
                    SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                    int i3 = this.size;
                    while (i2 < i3) {
                        K kKeyAt = keyAt(i2);
                        V vValueAt = valueAt(i2);
                        Object obj2 = simpleArrayMap.get(kKeyAt);
                        if (vValueAt == null) {
                            i2 = (obj2 == null && simpleArrayMap.containsKey(kKeyAt)) ? i2 + 1 : 0;
                        } else if (!vValueAt.equals(obj2)) {
                        }
                    }
                    return true;
                }
            } else if ((obj instanceof Map) && size() == ((Map) obj).size()) {
                int i4 = this.size;
                while (i < i4) {
                    K kKeyAt2 = keyAt(i);
                    V vValueAt2 = valueAt(i);
                    Object obj3 = ((Map) obj).get(kKeyAt2);
                    if (vValueAt2 == null) {
                        i = (obj3 == null && ((Map) obj).containsKey(kKeyAt2)) ? i + 1 : 0;
                    } else if (!vValueAt2.equals(obj3)) {
                    }
                }
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    @Nullable
    public V get(K k) {
        int iIndexOfKey = indexOfKey(k);
        if (iIndexOfKey >= 0) {
            return (V) this.array[(iIndexOfKey << 1) + 1];
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V getOrDefault(@Nullable Object obj, V v) {
        int iIndexOfKey = indexOfKey(obj);
        return iIndexOfKey >= 0 ? (V) this.array[(iIndexOfKey << 1) + 1] : v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends V> T getOrDefaultInternal(Object obj, T t) {
        int iIndexOfKey = indexOfKey(obj);
        return iIndexOfKey >= 0 ? (T) this.array[(iIndexOfKey << 1) + 1] : t;
    }

    public int hashCode() {
        int[] iArr = this.hashes;
        Object[] objArr = this.array;
        int i = this.size;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public final int indexOf(K k, int i) {
        int i2 = this.size;
        if (i2 == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.hashes, i2, i);
        if (iBinarySearch < 0 || Intrinsics.areEqual(k, this.array[iBinarySearch << 1])) {
            return iBinarySearch;
        }
        int i3 = iBinarySearch + 1;
        while (i3 < i2 && this.hashes[i3] == i) {
            if (Intrinsics.areEqual(k, this.array[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iBinarySearch - 1; i4 >= 0 && this.hashes[i4] == i; i4--) {
            if (Intrinsics.areEqual(k, this.array[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    public int indexOfKey(K k) {
        return k == null ? indexOfNull() : indexOf(k, k.hashCode());
    }

    public final int indexOfNull() {
        int i = this.size;
        if (i == 0) {
            return -1;
        }
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.hashes, i, 0);
        if (iBinarySearch < 0 || this.array[iBinarySearch << 1] == null) {
            return iBinarySearch;
        }
        int i2 = iBinarySearch + 1;
        while (i2 < i && this.hashes[i2] == 0) {
            if (this.array[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iBinarySearch - 1; i3 >= 0 && this.hashes[i3] == 0; i3--) {
            if (this.array[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public K keyAt(int i) {
        if (i < 0 || i >= this.size) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        return (K) this.array[i << 1];
    }

    @Nullable
    public V put(K k, V v) {
        int i = this.size;
        int iHashCode = k != null ? k.hashCode() : 0;
        int iIndexOf = k != null ? indexOf(k, iHashCode) : indexOfNull();
        if (iIndexOf >= 0) {
            int i2 = (iIndexOf << 1) + 1;
            Object[] objArr = this.array;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
        int i3 = ~iIndexOf;
        int[] iArr = this.hashes;
        if (i >= iArr.length) {
            int i4 = 8;
            if (i >= 8) {
                i4 = (i >> 1) + i;
            } else if (i < 4) {
                i4 = 4;
            }
            int[] iArrCopyOf = Arrays.copyOf(iArr, i4);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.hashes = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.array, i4 << 1);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.array = objArrCopyOf;
            if (i != this.size) {
                throw new ConcurrentModificationException();
            }
        }
        if (i3 < i) {
            int[] iArr2 = this.hashes;
            int i5 = i3 + 1;
            ArraysKt___ArraysJvmKt.copyInto(iArr2, iArr2, i5, i3, i);
            Object[] objArr2 = this.array;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i5 << 1, i3 << 1, this.size << 1);
        }
        int i6 = this.size;
        if (i == i6) {
            int[] iArr3 = this.hashes;
            if (i3 < iArr3.length) {
                iArr3[i3] = iHashCode;
                Object[] objArr3 = this.array;
                int i7 = i3 << 1;
                objArr3[i7] = k;
                objArr3[i7 + 1] = v;
                this.size = i6 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(@NotNull SimpleArrayMap<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        int i = map.size;
        ensureCapacity(this.size + i);
        if (this.size != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(map.keyAt(i2), map.valueAt(i2));
            }
        } else if (i > 0) {
            ArraysKt___ArraysJvmKt.copyInto(map.hashes, this.hashes, 0, 0, i);
            ArraysKt___ArraysJvmKt.copyInto(map.array, this.array, 0, 0, i << 1);
            this.size = i;
        }
    }

    @Nullable
    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    @Nullable
    public V remove(K k) {
        int iIndexOfKey = indexOfKey(k);
        if (iIndexOfKey >= 0) {
            return removeAt(iIndexOfKey);
        }
        return null;
    }

    public V removeAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.size)) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        Object[] objArr = this.array;
        int i3 = i << 1;
        V v = (V) objArr[i3 + 1];
        if (i2 <= 1) {
            clear();
            return v;
        }
        int i4 = i2 - 1;
        int[] iArr = this.hashes;
        if (iArr.length <= 8 || i2 >= iArr.length / 3) {
            if (i < i4) {
                int i5 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i, i5, i2);
                Object[] objArr2 = this.array;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i3, i5 << 1, i2 << 1);
            }
            Object[] objArr3 = this.array;
            int i6 = i4 << 1;
            objArr3[i6] = null;
            objArr3[i6 + 1] = null;
        } else {
            int i7 = i2 > 8 ? i2 + (i2 >> 1) : 8;
            int[] iArrCopyOf = Arrays.copyOf(iArr, i7);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.hashes = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.array, i7 << 1);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.array = objArrCopyOf;
            if (i2 != this.size) {
                throw new ConcurrentModificationException();
            }
            if (i > 0) {
                ArraysKt___ArraysJvmKt.copyInto(iArr, this.hashes, 0, 0, i);
                ArraysKt___ArraysJvmKt.copyInto(objArr, this.array, 0, 0, i3);
            }
            if (i < i4) {
                int i8 = i + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, this.hashes, i, i8, i2);
                ArraysKt___ArraysJvmKt.copyInto(objArr, this.array, i3, i8 << 1, i2 << 1);
            }
        }
        if (i2 != this.size) {
            throw new ConcurrentModificationException();
        }
        this.size = i4;
        return v;
    }

    @Nullable
    public V replace(K k, V v) {
        int iIndexOfKey = indexOfKey(k);
        if (iIndexOfKey >= 0) {
            return setValueAt(iIndexOfKey, v);
        }
        return null;
    }

    public V setValueAt(int i, V v) {
        if (i < 0 || i >= this.size) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        int i2 = (i << 1) + 1;
        Object[] objArr = this.array;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public int size() {
        return this.size;
    }

    @NotNull
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K kKeyAt = keyAt(i2);
            if (kKeyAt != sb) {
                sb.append(kKeyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V vValueAt = valueAt(i2);
            if (vValueAt != sb) {
                sb.append(vValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "StringBuilder(capacity).…builderAction).toString()");
    }

    public V valueAt(int i) {
        if (i < 0 || i >= this.size) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        return (V) this.array[(i << 1) + 1];
    }

    @JvmOverloads
    public SimpleArrayMap(int i) {
        this.hashes = i == 0 ? ContainerHelpersKt.EMPTY_INTS : new int[i];
        this.array = i == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[i << 1];
    }

    public boolean remove(K k, V v) {
        int iIndexOfKey = indexOfKey(k);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(v, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public boolean replace(K k, V v, V v2) {
        int iIndexOfKey = indexOfKey(k);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(v, valueAt(iIndexOfKey))) {
            return false;
        }
        setValueAt(iIndexOfKey, v2);
        return true;
    }

    public /* synthetic */ SimpleArrayMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public SimpleArrayMap(@Nullable SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this(0, 1, null);
        if (simpleArrayMap != null) {
            putAll(simpleArrayMap);
        }
    }
}
