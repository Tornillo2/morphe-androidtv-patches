package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Queue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class ConsumingQueueIterator<T> extends AbstractIterator<T> {
    public final Queue<T> queue;

    public ConsumingQueueIterator(Queue<T> queue) {
        queue.getClass();
        this.queue = queue;
    }

    @Override // com.google.common.collect.AbstractIterator
    public T computeNext() {
        if (!this.queue.isEmpty()) {
            return this.queue.remove();
        }
        endOfData();
        return null;
    }
}
