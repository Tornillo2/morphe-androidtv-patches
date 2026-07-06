package androidx.collection;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLongSparseArray.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n+ 2 LongSparseArray.kt\nandroidx/collection/LongSparseArrayKt\n*L\n1#1,255:1\n243#2:256\n256#2,6:257\n248#2,14:263\n267#2,8:277\n267#2,8:285\n278#2,9:293\n291#2,5:302\n299#2,8:307\n315#2,9:315\n349#2,12:324\n328#2,18:336\n363#2,26:354\n392#2,5:380\n400#2,5:385\n409#2,2:390\n328#2,18:392\n412#2:410\n416#2:411\n420#2,6:412\n328#2,18:418\n427#2:436\n432#2,6:437\n328#2,18:443\n441#2:461\n446#2,6:462\n328#2,18:468\n453#2,2:486\n458#2,2:488\n328#2,18:490\n461#2:508\n466#2,2:509\n328#2,18:511\n469#2,6:529\n479#2:535\n484#2:536\n489#2,8:537\n500#2,6:545\n328#2,18:551\n507#2,10:569\n520#2,21:579\n*S KotlinDebug\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n*L\n93#1:256\n93#1:257,6\n100#1:263,14\n106#1:277,8\n111#1:285,8\n120#1:293,9\n125#1:302,5\n134#1:307,8\n145#1:315,9\n151#1:324,12\n151#1:336,18\n151#1:354,26\n157#1:380,5\n168#1:385,5\n173#1:390,2\n173#1:392,18\n173#1:410\n180#1:411\n192#1:412,6\n192#1:418,18\n192#1:436\n204#1:437,6\n204#1:443,18\n204#1:461\n212#1:462,6\n212#1:468,18\n212#1:486,2\n219#1:488,2\n219#1:490,18\n219#1:508\n228#1:509,2\n228#1:511,18\n228#1:529,6\n231#1:535\n234#1:536\n239#1:537,8\n245#1:545,6\n245#1:551,18\n245#1:569,10\n253#1:579,21\n*E\n"})
public class LongSparseArray<E> implements Cloneable {

    @JvmField
    public /* synthetic */ boolean garbage;

    @JvmField
    public /* synthetic */ long[] keys;

    @JvmField
    public /* synthetic */ int size;

    @JvmField
    public /* synthetic */ Object[] values;

    @JvmOverloads
    public LongSparseArray() {
        this(0, 1, null);
    }

    public void append(long j, E e) {
        int i = this.size;
        if (i != 0 && j <= this.keys[i - 1]) {
            put(j, e);
            return;
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            if (i >= jArr.length) {
                Object[] objArr = this.values;
                int i2 = 0;
                for (int i3 = 0; i3 < i; i3++) {
                    Object obj = objArr[i3];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i3 != i2) {
                            jArr[i2] = jArr[i3];
                            objArr[i2] = obj;
                            objArr[i3] = null;
                        }
                        i2++;
                    }
                }
                this.garbage = false;
                this.size = i2;
            }
        }
        int i4 = this.size;
        if (i4 >= this.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i4 + 1);
            long[] jArrCopyOf = Arrays.copyOf(this.keys, iIdealLongArraySize);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealLongArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        this.keys[i4] = j;
        this.values[i4] = e;
        this.size = i4 + 1;
    }

    public void clear() {
        int i = this.size;
        Object[] objArr = this.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public boolean containsKey(long j) {
        return indexOfKey(j) >= 0;
    }

    public boolean containsValue(E e) {
        return indexOfValue(e) >= 0;
    }

    @Deprecated(message = "Alias for `remove(key)`.", replaceWith = @ReplaceWith(expression = "remove(key)", imports = {}))
    public void delete(long j) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (iBinarySearch >= 0) {
            Object[] objArr = this.values;
            Object obj = objArr[iBinarySearch];
            Object obj2 = LongSparseArrayKt.DELETED;
            if (obj != obj2) {
                objArr[iBinarySearch] = obj2;
                this.garbage = true;
            }
        }
    }

    @Nullable
    public E get(long j) {
        E e;
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (iBinarySearch < 0 || (e = (E) this.values[iBinarySearch]) == LongSparseArrayKt.DELETED) {
            return null;
        }
        return e;
    }

    public int indexOfKey(long j) {
        if (this.garbage) {
            int i = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.garbage = false;
            this.size = i2;
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, j);
    }

    public int indexOfValue(E e) {
        if (this.garbage) {
            int i = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.garbage = false;
            this.size = i2;
        }
        int i4 = this.size;
        for (int i5 = 0; i5 < i4; i5++) {
            if (this.values[i5] == e) {
                return i5;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.size)) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        return this.keys[i];
    }

    public void put(long j, E e) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (iBinarySearch >= 0) {
            this.values[iBinarySearch] = e;
            return;
        }
        int i = ~iBinarySearch;
        int i2 = this.size;
        if (i < i2) {
            Object[] objArr = this.values;
            if (objArr[i] == LongSparseArrayKt.DELETED) {
                this.keys[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            if (i2 >= jArr.length) {
                Object[] objArr2 = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr2[i4];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr2[i3] = obj;
                            objArr2[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
                i = ~ContainerHelpersKt.binarySearch(this.keys, i3, j);
            }
        }
        int i5 = this.size;
        if (i5 >= this.keys.length) {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i5 + 1);
            long[] jArrCopyOf = Arrays.copyOf(this.keys, iIdealLongArraySize);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.keys = jArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealLongArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        int i6 = this.size;
        if (i6 - i != 0) {
            long[] jArr2 = this.keys;
            int i7 = i + 1;
            ArraysKt___ArraysJvmKt.copyInto(jArr2, jArr2, i7, i, i6);
            Object[] objArr3 = this.values;
            ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i7, i, this.size);
        }
        this.keys[i] = j;
        this.values[i] = e;
        this.size++;
    }

    public void putAll(@NotNull LongSparseArray<? extends E> other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int size = other.size();
        for (int i = 0; i < size; i++) {
            put(other.keyAt(i), other.valueAt(i));
        }
    }

    @Nullable
    public E putIfAbsent(long j, E e) {
        E e2 = get(j);
        if (e2 == null) {
            put(j, e);
        }
        return e2;
    }

    public void remove(long j) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (iBinarySearch >= 0) {
            Object[] objArr = this.values;
            Object obj = objArr[iBinarySearch];
            Object obj2 = LongSparseArrayKt.DELETED;
            if (obj != obj2) {
                objArr[iBinarySearch] = obj2;
                this.garbage = true;
            }
        }
    }

    public void removeAt(int i) {
        Object[] objArr = this.values;
        Object obj = objArr[i];
        Object obj2 = LongSparseArrayKt.DELETED;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.garbage = true;
        }
    }

    @Nullable
    public E replace(long j, E e) {
        int iIndexOfKey = indexOfKey(j);
        if (iIndexOfKey < 0) {
            return null;
        }
        Object[] objArr = this.values;
        E e2 = (E) objArr[iIndexOfKey];
        objArr[iIndexOfKey] = e;
        return e2;
    }

    public void setValueAt(int i, E e) {
        int i2;
        if (i < 0 || i >= (i2 = this.size)) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        this.values[i] = e;
    }

    public int size() {
        if (this.garbage) {
            int i = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.garbage = false;
            this.size = i2;
        }
        return this.size;
    }

    @NotNull
    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i2));
            sb.append('=');
            E eValueAt = valueAt(i2);
            if (eValueAt != sb) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "StringBuilder(capacity).…builderAction).toString()");
    }

    public E valueAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.size)) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Expected index to be within 0..size()-1, but was ", i).toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj = objArr[i4];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i4 != i3) {
                        jArr[i3] = jArr[i4];
                        objArr[i3] = obj;
                        objArr[i4] = null;
                    }
                    i3++;
                }
            }
            this.garbage = false;
            this.size = i3;
        }
        return (E) this.values[i];
    }

    @JvmOverloads
    public LongSparseArray(int i) {
        if (i == 0) {
            this.keys = ContainerHelpersKt.EMPTY_LONGS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            int iIdealLongArraySize = ContainerHelpersKt.idealLongArraySize(i);
            this.keys = new long[iIdealLongArraySize];
            this.values = new Object[iIdealLongArraySize];
        }
    }

    @NotNull
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongSparseArray<E> m28clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        LongSparseArray<E> longSparseArray = (LongSparseArray) objClone;
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    public E get(long j, E e) {
        E e2;
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        return (iBinarySearch < 0 || (e2 = (E) this.values[iBinarySearch]) == LongSparseArrayKt.DELETED) ? e : e2;
    }

    public boolean replace(long j, E e, E e2) {
        int iIndexOfKey = indexOfKey(j);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(this.values[iIndexOfKey], e)) {
            return false;
        }
        this.values[iIndexOfKey] = e2;
        return true;
    }

    public boolean remove(long j, E e) {
        int iIndexOfKey = indexOfKey(j);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(e, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public /* synthetic */ LongSparseArray(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }
}
