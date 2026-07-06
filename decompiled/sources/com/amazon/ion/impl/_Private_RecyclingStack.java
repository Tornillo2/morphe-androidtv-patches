package com.amazon.ion.impl;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_RecyclingStack<T> {
    public final ElementFactory<T> elementFactory;
    public final List<T> elements;
    public int currentIndex = -1;
    public T top = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ElementFactory<T> {
        T newElement();
    }

    public _Private_RecyclingStack(int i, ElementFactory<T> elementFactory) {
        this.elements = new ArrayList(i);
        this.elementFactory = elementFactory;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public T peek() {
        return this.top;
    }

    public T pop() {
        T t = this.top;
        int i = this.currentIndex - 1;
        this.currentIndex = i;
        if (i >= 0) {
            this.top = this.elements.get(i);
            return t;
        }
        this.top = null;
        this.currentIndex = -1;
        return t;
    }

    public T push() {
        int i = this.currentIndex + 1;
        this.currentIndex = i;
        if (i >= this.elements.size()) {
            T tNewElement = this.elementFactory.newElement();
            this.top = tNewElement;
            this.elements.add(tNewElement);
        } else {
            this.top = this.elements.get(this.currentIndex);
        }
        return this.top;
    }

    public int size() {
        return this.currentIndex + 1;
    }
}
