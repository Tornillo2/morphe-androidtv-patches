package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
public final class UIntProgressionIterator implements Iterator<UInt>, KMappedMarker {
    public final int finalElement;
    public boolean hasNext;
    public int next;
    public final int step;

    public /* synthetic */ UIntProgressionIterator(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public /* synthetic */ UInt next() {
        return new UInt(m1814nextpVg5ArA());
    }

    /* JADX INFO: renamed from: next-pVg5ArA, reason: not valid java name */
    public int m1814nextpVg5ArA() {
        int i = this.next;
        if (i != this.finalElement) {
            this.next = this.step + i;
            return i;
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        this.hasNext = false;
        return i;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public UIntProgressionIterator(int i, int i2, int i3) {
        this.finalElement = i2;
        boolean z = false;
        int iCompare = Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
        if (i3 <= 0 ? iCompare >= 0 : iCompare <= 0) {
            z = true;
        }
        this.hasNext = z;
        this.step = i3;
        this.next = z ? i : i2;
    }
}
