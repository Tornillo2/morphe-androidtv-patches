package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$2 extends AbstractList<ULong> implements RandomAccess {
    public final /* synthetic */ long[] $this_asList;

    public UArraysKt___UArraysJvmKt$asList$2(long[] jArr) {
        this.$this_asList = jArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof ULong)) {
            return false;
        }
        return ArraysKt___ArraysKt.contains(this.$this_asList, ((ULong) obj).data);
    }

    /* JADX INFO: renamed from: contains-VKZWuLQ, reason: not valid java name */
    public boolean m1096containsVKZWuLQ(long j) {
        return ArraysKt___ArraysKt.contains(this.$this_asList, j);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        return new ULong(this.$this_asList[i]);
    }

    /* JADX INFO: renamed from: get-s-VKNKU, reason: not valid java name */
    public long m1097getsVKNKU(int i) {
        return this.$this_asList[i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.$this_asList.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof ULong)) {
            return -1;
        }
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((ULong) obj).data);
    }

    /* JADX INFO: renamed from: indexOf-VKZWuLQ, reason: not valid java name */
    public int m1098indexOfVKZWuLQ(long j) {
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, j);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return ULongArray.m817isEmptyimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof ULong)) {
            return -1;
        }
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((ULong) obj).data);
    }

    /* JADX INFO: renamed from: lastIndexOf-VKZWuLQ, reason: not valid java name */
    public int m1099lastIndexOfVKZWuLQ(long j) {
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, j);
    }
}
