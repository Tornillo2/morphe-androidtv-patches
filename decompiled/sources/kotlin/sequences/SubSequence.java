package kotlin.sequences;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SubSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,698:1\n1#2:699\n*E\n"})
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    public final int endIndex;

    @NotNull
    public final Sequence<T> sequence;
    public final int startIndex;

    /* JADX INFO: renamed from: kotlin.sequences.SubSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        public final Iterator<T> iterator;
        public int position;
        public final /* synthetic */ SubSequence<T> this$0;

        public AnonymousClass1(SubSequence<T> subSequence) {
            this.this$0 = subSequence;
            this.iterator = subSequence.sequence.iterator();
        }

        private final void drop() {
            while (this.position < this.this$0.startIndex && this.iterator.hasNext()) {
                this.iterator.next();
                this.position++;
            }
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getPosition() {
            return this.position;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            drop();
            return this.position < this.this$0.endIndex && this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            drop();
            int i = this.position;
            if (i >= this.this$0.endIndex) {
                throw new NoSuchElementException();
            }
            this.position = i + 1;
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setPosition(int i) {
            this.position = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubSequence(@NotNull Sequence<? extends T> sequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.startIndex = i;
        this.endIndex = i2;
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("startIndex should be non-negative, but is ", i).toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("endIndex should be non-negative, but is ", i2).toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("endIndex should be not less than startIndex, but was ", i2, " < ", i).toString());
        }
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> drop(int i) {
        return i >= getCount() ? EmptySequence.INSTANCE : new SubSequence(this.sequence, this.startIndex + i, this.endIndex);
    }

    public final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> take(int i) {
        if (i >= getCount()) {
            return this;
        }
        Sequence<T> sequence = this.sequence;
        int i2 = this.startIndex;
        return new SubSequence(sequence, i2, i + i2);
    }
}
