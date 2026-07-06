package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.EmptyIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EmptySequence implements Sequence, DropTakeSequence {

    @NotNull
    public static final EmptySequence INSTANCE = new EmptySequence();

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public EmptySequence drop(int i) {
        return INSTANCE;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public EmptySequence take(int i) {
        return INSTANCE;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence drop(int i) {
        return INSTANCE;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence take(int i) {
        return INSTANCE;
    }
}
