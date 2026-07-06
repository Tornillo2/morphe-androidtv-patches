package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CharProgressionIterator extends CharIterator {
    public final int finalElement;
    public boolean hasNext;
    public int next;
    public final int step;

    public CharProgressionIterator(char c, char c2, int i) {
        this.step = i;
        this.finalElement = c2;
        boolean z = false;
        if (i <= 0 ? Intrinsics.compare((int) c, (int) c2) >= 0 : Intrinsics.compare((int) c, (int) c2) <= 0) {
            z = true;
        }
        this.hasNext = z;
        this.next = z ? c : c2;
    }

    public final int getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        int i = this.next;
        if (i != this.finalElement) {
            this.next = this.step + i;
        } else {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            this.hasNext = false;
        }
        return (char) i;
    }
}
