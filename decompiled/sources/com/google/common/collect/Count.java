package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class Count implements Serializable {
    public int value;

    public Count(int value) {
        this.value = value;
    }

    public void add(int delta) {
        this.value += delta;
    }

    public int addAndGet(int delta) {
        int i = this.value + delta;
        this.value = i;
        return i;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Count) && ((Count) obj).value == this.value;
    }

    public int get() {
        return this.value;
    }

    public int getAndSet(int newValue) {
        int i = this.value;
        this.value = newValue;
        return i;
    }

    public int hashCode() {
        return this.value;
    }

    public void set(int newValue) {
        this.value = newValue;
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}
