package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseArrayCompat.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n+ 2 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n*L\n1#1,273:1\n275#2,9:274\n288#2,5:283\n296#2,5:288\n304#2,8:293\n320#2,9:301\n353#2,40:310\n396#2,2:350\n353#2,47:352\n403#2,3:399\n353#2,40:402\n407#2:442\n412#2,4:443\n419#2:447\n423#2,4:448\n431#2,8:452\n443#2,5:460\n451#2,4:465\n459#2,9:469\n472#2:478\n477#2:479\n459#2,9:480\n482#2,8:489\n493#2,17:497\n513#2,21:514\n*S KotlinDebug\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n*L\n130#1:274,9\n135#1:283,5\n144#1:288,5\n152#1:293,8\n163#1:301,9\n169#1:310,40\n176#1:350,2\n176#1:352,47\n186#1:399,3\n186#1:402,40\n186#1:442\n191#1:443,4\n205#1:447\n212#1:448,4\n218#1:452,8\n224#1:460,5\n234#1:465,4\n246#1:469,9\n249#1:478\n252#1:479\n252#1:480,9\n257#1:489,8\n263#1:497,17\n271#1:514,21\n*E\n"})
public class SparseArrayCompat<E> implements Cloneable {

    @JvmField
    public /* synthetic */ boolean garbage;

    @JvmField
    public /* synthetic */ int[] keys;

    @JvmField
    public /* synthetic */ int size;

    @JvmField
    public /* synthetic */ Object[] values;

    @JvmOverloads
    public SparseArrayCompat() {
        this(0, 1, null);
    }

    public void append(int i, E e) {
        int i2 = this.size;
        if (i2 != 0 && i <= this.keys[i2 - 1]) {
            put(i, e);
            return;
        }
        if (this.garbage && i2 >= this.keys.length) {
            SparseArrayCompatKt.gc(this);
        }
        int i3 = this.size;
        if (i3 >= this.keys.length) {
            int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
            int[] iArrCopyOf = Arrays.copyOf(this.keys, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.keys = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        this.keys[i3] = i;
        this.values[i3] = e;
        this.size = i3 + 1;
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

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public boolean containsValue(E e) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        int i = this.size;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                i2 = -1;
                break;
            }
            if (this.values[i2] == e) {
                break;
            }
            i2++;
        }
        return i2 >= 0;
    }

    @Deprecated(message = "Alias for remove(int).", replaceWith = @ReplaceWith(expression = "remove(key)", imports = {}))
    public void delete(int i) {
        remove(i);
    }

    @Nullable
    public E get(int i) {
        return (E) SparseArrayCompatKt.commonGet(this, i);
    }

    @JvmName(name = "getIsEmpty")
    public final boolean getIsEmpty() {
        return isEmpty();
    }

    public int indexOfKey(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, i);
    }

    public int indexOfValue(E e) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.values[i2] == e) {
                return i2;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int keyAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.keys[i];
    }

    public void put(int i, E e) {
        int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (iBinarySearch >= 0) {
            this.values[iBinarySearch] = e;
            return;
        }
        int i2 = ~iBinarySearch;
        int i3 = this.size;
        if (i2 < i3) {
            Object[] objArr = this.values;
            if (objArr[i2] == SparseArrayCompatKt.DELETED) {
                this.keys[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.garbage && i3 >= this.keys.length) {
            SparseArrayCompatKt.gc(this);
            i2 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        }
        int i4 = this.size;
        if (i4 >= this.keys.length) {
            int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
            int[] iArrCopyOf = Arrays.copyOf(this.keys, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.keys = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
        }
        int i5 = this.size;
        if (i5 - i2 != 0) {
            int[] iArr = this.keys;
            int i6 = i2 + 1;
            ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i6, i2, i5);
            Object[] objArr2 = this.values;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i6, i2, this.size);
        }
        this.keys[i2] = i;
        this.values[i2] = e;
        this.size++;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void putAll(@org.jetbrains.annotations.NotNull androidx.collection.SparseArrayCompat<? extends E> r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            int r0 = r10.size()
            r1 = 0
        La:
            if (r1 >= r0) goto L97
            int r2 = r10.keyAt(r1)
            java.lang.Object r3 = r10.valueAt(r1)
            int[] r4 = r9.keys
            int r5 = r9.size
            int r4 = androidx.collection.internal.ContainerHelpersKt.binarySearch(r4, r5, r2)
            if (r4 < 0) goto L23
            java.lang.Object[] r2 = r9.values
            r2[r4] = r3
            goto L93
        L23:
            int r4 = ~r4
            int r5 = r9.size
            if (r4 >= r5) goto L37
            java.lang.Object[] r6 = r9.values
            r7 = r6[r4]
            java.lang.Object r8 = androidx.collection.SparseArrayCompatKt.DELETED
            if (r7 != r8) goto L37
            int[] r5 = r9.keys
            r5[r4] = r2
            r6[r4] = r3
            goto L93
        L37:
            boolean r6 = r9.garbage
            if (r6 == 0) goto L4c
            int[] r6 = r9.keys
            int r6 = r6.length
            if (r5 < r6) goto L4c
            androidx.collection.SparseArrayCompatKt.gc(r9)
            int[] r4 = r9.keys
            int r5 = r9.size
            int r4 = androidx.collection.internal.ContainerHelpersKt.binarySearch(r4, r5, r2)
            int r4 = ~r4
        L4c:
            int r5 = r9.size
            int[] r6 = r9.keys
            int r6 = r6.length
            if (r5 < r6) goto L71
            int r5 = r5 + 1
            int r5 = androidx.collection.internal.ContainerHelpersKt.idealIntArraySize(r5)
            int[] r6 = r9.keys
            int[] r6 = java.util.Arrays.copyOf(r6, r5)
            java.lang.String r7 = "copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r9.keys = r6
            java.lang.Object[] r6 = r9.values
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r6, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
            r9.values = r5
        L71:
            int r5 = r9.size
            int r6 = r5 - r4
            if (r6 == 0) goto L85
            int[] r6 = r9.keys
            int r7 = r4 + 1
            kotlin.collections.ArraysKt___ArraysJvmKt.copyInto(r6, r6, r7, r4, r5)
            java.lang.Object[] r5 = r9.values
            int r6 = r9.size
            kotlin.collections.ArraysKt___ArraysJvmKt.copyInto(r5, r5, r7, r4, r6)
        L85:
            int[] r5 = r9.keys
            r5[r4] = r2
            java.lang.Object[] r2 = r9.values
            r2[r4] = r3
            int r2 = r9.size
            int r2 = r2 + 1
            r9.size = r2
        L93:
            int r1 = r1 + 1
            goto La
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.SparseArrayCompat.putAll(androidx.collection.SparseArrayCompat):void");
    }

    @Nullable
    public E putIfAbsent(int i, E e) {
        E e2 = (E) SparseArrayCompatKt.commonGet(this, i);
        if (e2 == null) {
            int iBinarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
            if (iBinarySearch >= 0) {
                this.values[iBinarySearch] = e;
                return e2;
            }
            int i2 = ~iBinarySearch;
            int i3 = this.size;
            if (i2 < i3) {
                Object[] objArr = this.values;
                if (objArr[i2] == SparseArrayCompatKt.DELETED) {
                    this.keys[i2] = i;
                    objArr[i2] = e;
                    return e2;
                }
            }
            if (this.garbage && i3 >= this.keys.length) {
                SparseArrayCompatKt.gc(this);
                i2 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, i);
            }
            int i4 = this.size;
            if (i4 >= this.keys.length) {
                int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
                int[] iArrCopyOf = Arrays.copyOf(this.keys, iIdealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
                this.keys = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(this.values, iIdealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                this.values = objArrCopyOf;
            }
            int i5 = this.size;
            if (i5 - i2 != 0) {
                int[] iArr = this.keys;
                int i6 = i2 + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i6, i2, i5);
                Object[] objArr2 = this.values;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i6, i2, this.size);
            }
            this.keys[i2] = i;
            this.values[i2] = e;
            this.size++;
        }
        return e2;
    }

    public void remove(int i) {
        SparseArrayCompatKt.commonRemove(this, i);
    }

    public void removeAt(int i) {
        Object[] objArr = this.values;
        Object obj = objArr[i];
        Object obj2 = SparseArrayCompatKt.DELETED;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.garbage = true;
        }
    }

    public void removeAtRange(int i, int i2) {
        int iMin = Math.min(i2, i + i2);
        while (i < iMin) {
            removeAt(i);
            i++;
        }
    }

    @Nullable
    public E replace(int i, E e) {
        int iIndexOfKey = indexOfKey(i);
        if (iIndexOfKey < 0) {
            return null;
        }
        Object[] objArr = this.values;
        E e2 = (E) objArr[iIndexOfKey];
        objArr[iIndexOfKey] = e;
        return e2;
    }

    public void setValueAt(int i, E e) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        this.values[i] = e;
    }

    public int size() {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
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
            if (eValueAt != this) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "buffer.toString()");
    }

    public E valueAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return (E) this.values[i];
    }

    @JvmOverloads
    public SparseArrayCompat(int i) {
        if (i == 0) {
            this.keys = ContainerHelpersKt.EMPTY_INTS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i);
            this.keys = new int[iIdealIntArraySize];
            this.values = new Object[iIdealIntArraySize];
        }
    }

    @NotNull
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SparseArrayCompat<E> m29clone() throws CloneNotSupportedException {
        Object objClone = super.clone();
        Intrinsics.checkNotNull(objClone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) objClone;
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }

    public E get(int i, E e) {
        return (E) SparseArrayCompatKt.commonGet(this, i, e);
    }

    public boolean remove(int i, @Nullable Object obj) {
        int iIndexOfKey = indexOfKey(i);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(obj, valueAt(iIndexOfKey))) {
            return false;
        }
        removeAt(iIndexOfKey);
        return true;
    }

    public boolean replace(int i, E e, E e2) {
        int iIndexOfKey = indexOfKey(i);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(this.values[iIndexOfKey], e)) {
            return false;
        }
        this.values[iIndexOfKey] = e2;
        return true;
    }

    public /* synthetic */ SparseArrayCompat(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }
}
