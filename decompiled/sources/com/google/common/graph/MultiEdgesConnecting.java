package com.google.common.graph;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MultiEdgesConnecting<E> extends AbstractSet<E> {
    public final Map<E, ?> outEdgeToNode;
    public final Object targetNode;

    public MultiEdgesConnecting(Map<E, ?> outEdgeToNode, Object targetNode) {
        outEdgeToNode.getClass();
        this.outEdgeToNode = outEdgeToNode;
        targetNode.getClass();
        this.targetNode = targetNode;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object edge) {
        return this.targetNode.equals(this.outEdgeToNode.get(edge));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public UnmodifiableIterator<E> iterator() {
        final Iterator<Map.Entry<E, ?>> it = this.outEdgeToNode.entrySet().iterator();
        return new AbstractIterator<E>(this) { // from class: com.google.common.graph.MultiEdgesConnecting.1
            public final /* synthetic */ MultiEdgesConnecting this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (this.this$0.targetNode.equals(entry.getValue())) {
                        return (E) entry.getKey();
                    }
                }
                endOfData();
                return null;
            }
        };
    }
}
