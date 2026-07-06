package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
public final class ULongProgressionIterator implements Iterator<ULong>, KMappedMarker {
    public final long finalElement;
    public boolean hasNext;
    public long next;
    public final long step;

    public /* synthetic */ ULongProgressionIterator(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public /* synthetic */ ULong next() {
        return new ULong(m1823nextsVKNKU());
    }

    /* JADX INFO: renamed from: next-s-VKNKU, reason: not valid java name */
    public long m1823nextsVKNKU() {
        long j = this.next;
        if (j != this.finalElement) {
            this.next = this.step + j;
            return j;
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        this.hasNext = false;
        return j;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public ULongProgressionIterator(long j, long j2, long j3) {
        this.finalElement = j2;
        boolean z = false;
        int iCompare = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
        if (j3 <= 0 ? iCompare >= 0 : iCompare <= 0) {
            z = true;
        }
        this.hasNext = z;
        this.step = j3;
        this.next = z ? j : j2;
    }
}
