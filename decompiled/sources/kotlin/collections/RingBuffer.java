package kotlin.collections;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nSlidingWindow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,206:1\n204#1:208\n204#1:209\n204#1:210\n1#2:207\n*S KotlinDebug\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n*L\n106#1:208\n175#1:209\n188#1:210\n*E\n"})
public final class RingBuffer<T> extends AbstractList<T> implements RandomAccess {

    @NotNull
    public final Object[] buffer;
    public final int capacity;
    public int size;
    public int startIndex;

    public RingBuffer(@NotNull Object[] buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.buffer = buffer;
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("ring buffer filled size should not be negative but it is ", i).toString());
        }
        if (i <= buffer.length) {
            this.capacity = buffer.length;
            this.size = i;
        } else {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("ring buffer filled size: ", i, " cannot be larger than the buffer size: ");
            sbM.append(buffer.length);
            throw new IllegalArgumentException(sbM.toString().toString());
        }
    }

    @Override // java.util.Collection, java.util.List
    public final void add(T t) {
        if (isFull()) {
            throw new IllegalStateException("ring buffer is full");
        }
        this.buffer[(getSize() + this.startIndex) % this.capacity] = t;
        this.size = getSize() + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final RingBuffer<T> expanded(int i) {
        Object[] array;
        int i2 = this.capacity;
        int i3 = i2 + (i2 >> 1) + 1;
        if (i3 <= i) {
            i = i3;
        }
        if (this.startIndex == 0) {
            array = Arrays.copyOf(this.buffer, i);
            Intrinsics.checkNotNullExpressionValue(array, "copyOf(...)");
        } else {
            array = toArray(new Object[i]);
        }
        return new RingBuffer<>(array, getSize());
    }

    public final int forward(int i, int i2) {
        return (i + i2) % this.capacity;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, getSize());
        return (T) this.buffer[(this.startIndex + i) % this.capacity];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.size;
    }

    public final boolean isFull() {
        return getSize() == this.capacity;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<T> iterator() {
        return new AbstractIterator<T>(this) { // from class: kotlin.collections.RingBuffer.iterator.1
            public int count;
            public int index;
            public final /* synthetic */ RingBuffer<T> this$0;

            {
                this.this$0 = this;
                this.count = this.getSize();
                this.index = this.startIndex;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.collections.AbstractIterator
            public void computeNext() {
                if (this.count == 0) {
                    this.state = 2;
                    return;
                }
                setNext(this.this$0.buffer[this.index]);
                this.index = (this.index + 1) % this.this$0.capacity;
                this.count--;
            }
        };
    }

    public final void removeFirst(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("n shouldn't be negative but it is ", i).toString());
        }
        if (i > getSize()) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("n shouldn't be greater than the buffer size: n = ", i, ", size = ");
            sbM.append(getSize());
            throw new IllegalArgumentException(sbM.toString().toString());
        }
        if (i > 0) {
            int i2 = this.startIndex;
            int i3 = this.capacity;
            int i4 = (i2 + i) % i3;
            if (i2 > i4) {
                ArraysKt___ArraysJvmKt.fill(this.buffer, (Object) null, i2, i3);
                ArraysKt___ArraysJvmKt.fill(this.buffer, (Object) null, 0, i4);
            } else {
                ArraysKt___ArraysJvmKt.fill(this.buffer, (Object) null, i2, i4);
            }
            this.startIndex = i4;
            this.size = getSize() - i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[getSize()]);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < getSize()) {
            array = (T[]) Arrays.copyOf(array, getSize());
            Intrinsics.checkNotNullExpressionValue(array, "copyOf(...)");
        }
        int size = getSize();
        int i = 0;
        int i2 = 0;
        for (int i3 = this.startIndex; i2 < size && i3 < this.capacity; i3++) {
            array[i2] = this.buffer[i3];
            i2++;
        }
        while (i2 < size) {
            array[i2] = this.buffer[i];
            i2++;
            i++;
        }
        CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(size, array);
        return array;
    }

    public RingBuffer(int i) {
        this(new Object[i], 0);
    }
}
