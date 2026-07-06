package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n8 implements Spliterator {
    public int a;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 17488;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Comparator getComparator() {
        return Spliterator.CC.$default$getComparator(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return Spliterator.CC.$default$getExactSizeIfKnown(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Spliterator.CC.$default$hasCharacteristics(this, i);
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        return null;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return (-this.a) - 1;
    }
}
