package kotlin.collections.unsigned;

import java.util.RandomAccess;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UArraysKt___UArraysJvmKt$asList$3 extends AbstractList<UByte> implements RandomAccess {
    public final /* synthetic */ byte[] $this_asList;

    public UArraysKt___UArraysJvmKt$asList$3(byte[] bArr) {
        this.$this_asList = bArr;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof UByte)) {
            return false;
        }
        return ArraysKt___ArraysKt.contains(this.$this_asList, ((UByte) obj).data);
    }

    /* JADX INFO: renamed from: contains-7apg3OU, reason: not valid java name */
    public boolean m1100contains7apg3OU(byte b) {
        return ArraysKt___ArraysKt.contains(this.$this_asList, b);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        return new UByte(this.$this_asList[i]);
    }

    /* JADX INFO: renamed from: get-w2LRezQ, reason: not valid java name */
    public byte m1101getw2LRezQ(int i) {
        return this.$this_asList[i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.$this_asList.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof UByte)) {
            return -1;
        }
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((UByte) obj).data);
    }

    /* JADX INFO: renamed from: indexOf-7apg3OU, reason: not valid java name */
    public int m1102indexOf7apg3OU(byte b) {
        return ArraysKt___ArraysKt.indexOf(this.$this_asList, b);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return UByteArray.m659isEmptyimpl(this.$this_asList);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof UByte)) {
            return -1;
        }
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((UByte) obj).data);
    }

    /* JADX INFO: renamed from: lastIndexOf-7apg3OU, reason: not valid java name */
    public int m1103lastIndexOf7apg3OU(byte b) {
        return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, b);
    }
}
