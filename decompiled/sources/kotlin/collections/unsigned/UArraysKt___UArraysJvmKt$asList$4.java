package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$4 extends AbstractList<UShort> implements RandomAccess {
    public final /* synthetic */ short[] $this_asList;

    public UArraysKt___UArraysJvmKt$asList$4(short[] sArr) {
        this.$this_asList = sArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof UShort)) {
            return false;
        }
        return ArraysKt___ArraysKt.contains(this.$this_asList, ((UShort) obj).data);
    }

    /* JADX INFO: renamed from: contains-xj2QHRw, reason: not valid java name */
    public boolean m1104containsxj2QHRw(short s) {
        return ArraysKt___ArraysKt.contains(this.$this_asList, s);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        return new UShort(this.$this_asList[i]);
    }

    /* JADX INFO: renamed from: get-Mh2AYeg, reason: not valid java name */
    public short m1105getMh2AYeg(int i) {
        return this.$this_asList[i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.$this_asList.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof UShort)) {
            return -1;
        }
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((UShort) obj).data);
    }

    /* JADX INFO: renamed from: indexOf-xj2QHRw, reason: not valid java name */
    public int m1106indexOfxj2QHRw(short s) {
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, s);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UShortArray.m922isEmptyimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof UShort)) {
            return -1;
        }
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((UShort) obj).data);
    }

    /* JADX INFO: renamed from: lastIndexOf-xj2QHRw, reason: not valid java name */
    public int m1107lastIndexOfxj2QHRw(short s) {
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, s);
    }
}
