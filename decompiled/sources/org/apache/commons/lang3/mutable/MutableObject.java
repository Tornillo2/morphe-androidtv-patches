package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MutableObject<T> implements Mutable<T>, Serializable {
    public static final long serialVersionUID = 86241875189L;
    public T value;

    public MutableObject() {
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() == obj.getClass()) {
            return this.value.equals(((MutableObject) obj).value);
        }
        return false;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* JADX INFO: renamed from: getValue */
    public T getValue2() {
        return this.value;
    }

    public int hashCode() {
        T t = this.value;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(T t) {
        this.value = t;
    }

    public String toString() {
        T t = this.value;
        return t == null ? AbstractJsonLexerKt.NULL : t.toString();
    }

    public MutableObject(T t) {
        this.value = t;
    }
}
