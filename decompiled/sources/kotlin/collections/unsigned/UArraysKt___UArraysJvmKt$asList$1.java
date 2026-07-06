package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$1 extends AbstractList<UInt> implements RandomAccess {
    public final /* synthetic */ int[] $this_asList;

    public UArraysKt___UArraysJvmKt$asList$1(int[] iArr) {
        this.$this_asList = iArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof UInt)) {
            return false;
        }
        return ArraysKt___ArraysKt.contains(this.$this_asList, ((UInt) obj).data);
    }

    /* JADX INFO: renamed from: contains-WZ4Q5Ns, reason: not valid java name */
    public boolean m1092containsWZ4Q5Ns(int i) {
        return ArraysKt___ArraysKt.contains(this.$this_asList, i);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        return new UInt(this.$this_asList[i]);
    }

    /* JADX INFO: renamed from: get-pVg5ArA, reason: not valid java name */
    public int m1093getpVg5ArA(int i) {
        return this.$this_asList[i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.$this_asList.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof UInt)) {
            return -1;
        }
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((UInt) obj).data);
    }

    /* JADX INFO: renamed from: indexOf-WZ4Q5Ns, reason: not valid java name */
    public int m1094indexOfWZ4Q5Ns(int i) {
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, i);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UIntArray.m738isEmptyimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof UInt)) {
            return -1;
        }
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((UInt) obj).data);
    }

    /* JADX INFO: renamed from: lastIndexOf-WZ4Q5Ns, reason: not valid java name */
    public int m1095lastIndexOfWZ4Q5Ns(int i) {
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, i);
    }
}
