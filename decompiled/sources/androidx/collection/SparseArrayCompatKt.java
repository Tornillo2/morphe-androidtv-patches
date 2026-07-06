package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSparseArrayCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n*L\n1#1,535:1\n244#1,6:536\n244#1,6:542\n353#1,40:548\n353#1,40:588\n459#1,9:628\n*S KotlinDebug\n*F\n+ 1 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n*L\n255#1:536,6\n260#1:542,6\n397#1:548,40\n405#1:588,40\n477#1:628,9\n*E\n"})
public final class SparseArrayCompatKt {

    @NotNull
    public static final Object DELETED = new Object();

    public static final <E> void commonAppend(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int i2 = sparseArrayCompat.size;
        if (i2 != 0 && i <= sparseArrayCompat.keys[i2 - 1]) {
            sparseArrayCompat.put(i, e);
            return;
        }
        if (sparseArrayCompat.garbage && i2 >= sparseArrayCompat.keys.length) {
            gc(sparseArrayCompat);
        }
        int i3 = sparseArrayCompat.size;
        if (i3 >= sparseArrayCompat.keys.length) {
            int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
            int[] iArrCopyOf = Arrays.copyOf(sparseArrayCompat.keys, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            sparseArrayCompat.keys = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(sparseArrayCompat.values, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            sparseArrayCompat.values = objArrCopyOf;
        }
        sparseArrayCompat.keys[i3] = i;
        sparseArrayCompat.values[i3] = e;
        sparseArrayCompat.size = i3 + 1;
    }

    public static final <E> void commonClear(@NotNull SparseArrayCompat<E> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int i = sparseArrayCompat.size;
        Object[] objArr = sparseArrayCompat.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        sparseArrayCompat.size = 0;
        sparseArrayCompat.garbage = false;
    }

    public static final <E> boolean commonContainsKey(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.indexOfKey(i) >= 0;
    }

    public static final <E> boolean commonContainsValue(@NotNull SparseArrayCompat<E> sparseArrayCompat, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        int i = sparseArrayCompat.size;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                i2 = -1;
                break;
            }
            if (sparseArrayCompat.values[i2] == e) {
                break;
            }
            i2++;
        }
        return i2 >= 0;
    }

    @Nullable
    public static final <E> E commonGet(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        E e;
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        if (iBinarySearch < 0 || (e = (E) sparseArrayCompat.values[iBinarySearch]) == DELETED) {
            return null;
        }
        return e;
    }

    public static final <E> int commonIndexOfKey(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        return ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
    }

    public static final <E> int commonIndexOfValue(@NotNull SparseArrayCompat<E> sparseArrayCompat, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        int i = sparseArrayCompat.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (sparseArrayCompat.values[i2] == e) {
                return i2;
            }
        }
        return -1;
    }

    public static final <E> boolean commonIsEmpty(@NotNull SparseArrayCompat<E> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        return sparseArrayCompat.size() == 0;
    }

    public static final <E> int commonKeyAt(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        return sparseArrayCompat.keys[i];
    }

    public static final <E> void commonPut(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        if (iBinarySearch >= 0) {
            sparseArrayCompat.values[iBinarySearch] = e;
            return;
        }
        int i2 = ~iBinarySearch;
        int i3 = sparseArrayCompat.size;
        if (i2 < i3) {
            Object[] objArr = sparseArrayCompat.values;
            if (objArr[i2] == DELETED) {
                sparseArrayCompat.keys[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (sparseArrayCompat.garbage && i3 >= sparseArrayCompat.keys.length) {
            gc(sparseArrayCompat);
            i2 = ~ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        }
        int i4 = sparseArrayCompat.size;
        if (i4 >= sparseArrayCompat.keys.length) {
            int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
            int[] iArrCopyOf = Arrays.copyOf(sparseArrayCompat.keys, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            sparseArrayCompat.keys = iArrCopyOf;
            Object[] objArrCopyOf = Arrays.copyOf(sparseArrayCompat.values, iIdealIntArraySize);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            sparseArrayCompat.values = objArrCopyOf;
        }
        int i5 = sparseArrayCompat.size;
        if (i5 - i2 != 0) {
            int[] iArr = sparseArrayCompat.keys;
            int i6 = i2 + 1;
            ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i6, i2, i5);
            Object[] objArr2 = sparseArrayCompat.values;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i6, i2, sparseArrayCompat.size);
        }
        sparseArrayCompat.keys[i2] = i;
        sparseArrayCompat.values[i2] = e;
        sparseArrayCompat.size++;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <E> void commonPutAll(@org.jetbrains.annotations.NotNull androidx.collection.SparseArrayCompat<E> r9, @org.jetbrains.annotations.NotNull androidx.collection.SparseArrayCompat<? extends E> r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            int r0 = r10.size()
            r1 = 0
        Lf:
            if (r1 >= r0) goto L9c
            int r2 = r10.keyAt(r1)
            java.lang.Object r3 = r10.valueAt(r1)
            int[] r4 = r9.keys
            int r5 = r9.size
            int r4 = androidx.collection.internal.ContainerHelpersKt.binarySearch(r4, r5, r2)
            if (r4 < 0) goto L28
            java.lang.Object[] r2 = r9.values
            r2[r4] = r3
            goto L98
        L28:
            int r4 = ~r4
            int r5 = r9.size
            if (r4 >= r5) goto L3c
            java.lang.Object[] r6 = r9.values
            r7 = r6[r4]
            java.lang.Object r8 = androidx.collection.SparseArrayCompatKt.DELETED
            if (r7 != r8) goto L3c
            int[] r5 = r9.keys
            r5[r4] = r2
            r6[r4] = r3
            goto L98
        L3c:
            boolean r6 = r9.garbage
            if (r6 == 0) goto L51
            int[] r6 = r9.keys
            int r6 = r6.length
            if (r5 < r6) goto L51
            gc(r9)
            int[] r4 = r9.keys
            int r5 = r9.size
            int r4 = androidx.collection.internal.ContainerHelpersKt.binarySearch(r4, r5, r2)
            int r4 = ~r4
        L51:
            int r5 = r9.size
            int[] r6 = r9.keys
            int r6 = r6.length
            if (r5 < r6) goto L76
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
        L76:
            int r5 = r9.size
            int r6 = r5 - r4
            if (r6 == 0) goto L8a
            int[] r6 = r9.keys
            int r7 = r4 + 1
            kotlin.collections.ArraysKt___ArraysJvmKt.copyInto(r6, r6, r7, r4, r5)
            java.lang.Object[] r5 = r9.values
            int r6 = r9.size
            kotlin.collections.ArraysKt___ArraysJvmKt.copyInto(r5, r5, r7, r4, r6)
        L8a:
            int[] r5 = r9.keys
            r5[r4] = r2
            java.lang.Object[] r2 = r9.values
            r2[r4] = r3
            int r2 = r9.size
            int r2 = r2 + 1
            r9.size = r2
        L98:
            int r1 = r1 + 1
            goto Lf
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.SparseArrayCompatKt.commonPutAll(androidx.collection.SparseArrayCompat, androidx.collection.SparseArrayCompat):void");
    }

    @Nullable
    public static final <E> E commonPutIfAbsent(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        E e2 = (E) commonGet(sparseArrayCompat, i);
        if (e2 == null) {
            int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
            if (iBinarySearch >= 0) {
                sparseArrayCompat.values[iBinarySearch] = e;
                return e2;
            }
            int i2 = ~iBinarySearch;
            int i3 = sparseArrayCompat.size;
            if (i2 < i3) {
                Object[] objArr = sparseArrayCompat.values;
                if (objArr[i2] == DELETED) {
                    sparseArrayCompat.keys[i2] = i;
                    objArr[i2] = e;
                    return e2;
                }
            }
            if (sparseArrayCompat.garbage && i3 >= sparseArrayCompat.keys.length) {
                gc(sparseArrayCompat);
                i2 = ~ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
            }
            int i4 = sparseArrayCompat.size;
            if (i4 >= sparseArrayCompat.keys.length) {
                int iIdealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
                int[] iArrCopyOf = Arrays.copyOf(sparseArrayCompat.keys, iIdealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
                sparseArrayCompat.keys = iArrCopyOf;
                Object[] objArrCopyOf = Arrays.copyOf(sparseArrayCompat.values, iIdealIntArraySize);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                sparseArrayCompat.values = objArrCopyOf;
            }
            int i5 = sparseArrayCompat.size;
            if (i5 - i2 != 0) {
                int[] iArr = sparseArrayCompat.keys;
                int i6 = i2 + 1;
                ArraysKt___ArraysJvmKt.copyInto(iArr, iArr, i6, i2, i5);
                Object[] objArr2 = sparseArrayCompat.values;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i6, i2, sparseArrayCompat.size);
            }
            sparseArrayCompat.keys[i2] = i;
            sparseArrayCompat.values[i2] = e;
            sparseArrayCompat.size++;
        }
        return e2;
    }

    public static final <E> void commonRemove(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        if (iBinarySearch >= 0) {
            Object[] objArr = sparseArrayCompat.values;
            Object obj = objArr[iBinarySearch];
            Object obj2 = DELETED;
            if (obj != obj2) {
                objArr[iBinarySearch] = obj2;
                sparseArrayCompat.garbage = true;
            }
        }
    }

    public static final <E> void commonRemoveAt(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        Object[] objArr = sparseArrayCompat.values;
        Object obj = objArr[i];
        Object obj2 = DELETED;
        if (obj != obj2) {
            objArr[i] = obj2;
            sparseArrayCompat.garbage = true;
        }
    }

    public static final <E> void commonRemoveAtRange(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, int i2) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iMin = Math.min(i2, i + i2);
        while (i < iMin) {
            sparseArrayCompat.removeAt(i);
            i++;
        }
    }

    @Nullable
    public static final <E> E commonReplace(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iIndexOfKey = sparseArrayCompat.indexOfKey(i);
        if (iIndexOfKey < 0) {
            return null;
        }
        Object[] objArr = sparseArrayCompat.values;
        E e2 = (E) objArr[iIndexOfKey];
        objArr[iIndexOfKey] = e;
        return e2;
    }

    public static final <E> void commonSetValueAt(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        sparseArrayCompat.values[i] = e;
    }

    public static final <E> int commonSize(@NotNull SparseArrayCompat<E> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        return sparseArrayCompat.size;
    }

    @NotNull
    public static final <E> String commonToString(@NotNull SparseArrayCompat<E> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(sparseArrayCompat.size * 28);
        sb.append('{');
        int i = sparseArrayCompat.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(sparseArrayCompat.keyAt(i2));
            sb.append('=');
            E eValueAt = sparseArrayCompat.valueAt(i2);
            if (eValueAt != sparseArrayCompat) {
                sb.append(eValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        return ArraySet$$ExternalSyntheticOutline0.m(sb, '}', "buffer.toString()");
    }

    public static final <E> E commonValueAt(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        if (sparseArrayCompat.garbage) {
            gc(sparseArrayCompat);
        }
        return (E) sparseArrayCompat.values[i];
    }

    public static final <E> void gc(SparseArrayCompat<E> sparseArrayCompat) {
        int i = sparseArrayCompat.size;
        int[] iArr = sparseArrayCompat.keys;
        Object[] objArr = sparseArrayCompat.values;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        sparseArrayCompat.garbage = false;
        sparseArrayCompat.size = i2;
    }

    public static final <E, T extends E> T internalGet(SparseArrayCompat<E> sparseArrayCompat, int i, T t) {
        T t2;
        int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        return (iBinarySearch < 0 || (t2 = (T) sparseArrayCompat.values[iBinarySearch]) == DELETED) ? t : t2;
    }

    public static final <E> E commonGet(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e) {
        E e2;
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iBinarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        return (iBinarySearch < 0 || (e2 = (E) sparseArrayCompat.values[iBinarySearch]) == DELETED) ? e : e2;
    }

    public static final <E> boolean commonReplace(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, E e, E e2) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iIndexOfKey = sparseArrayCompat.indexOfKey(i);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(sparseArrayCompat.values[iIndexOfKey], e)) {
            return false;
        }
        sparseArrayCompat.values[iIndexOfKey] = e2;
        return true;
    }

    public static final <E> boolean commonRemove(@NotNull SparseArrayCompat<E> sparseArrayCompat, int i, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<this>");
        int iIndexOfKey = sparseArrayCompat.indexOfKey(i);
        if (iIndexOfKey < 0 || !Intrinsics.areEqual(obj, sparseArrayCompat.valueAt(iIndexOfKey))) {
            return false;
        }
        sparseArrayCompat.removeAt(iIndexOfKey);
        return true;
    }
}
