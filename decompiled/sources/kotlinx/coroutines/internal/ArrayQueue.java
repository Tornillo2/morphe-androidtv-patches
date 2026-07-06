package kotlinx.coroutines.internal;

import kotlin.collections.ArraysKt___ArraysJvmKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArrayQueue<T> {

    @NotNull
    public Object[] elements = new Object[16];
    public int head;
    public int tail;

    public final void addLast(@NotNull T t) {
        Object[] objArr = this.elements;
        int i = this.tail;
        objArr[i] = t;
        int length = (objArr.length - 1) & (i + 1);
        this.tail = length;
        if (length == this.head) {
            ensureCapacity();
        }
    }

    public final void clear() {
        this.head = 0;
        this.tail = 0;
        this.elements = new Object[this.elements.length];
    }

    public final void ensureCapacity() {
        Object[] objArr = this.elements;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        ArraysKt___ArraysJvmKt.copyInto$default(objArr, objArr2, 0, this.head, 0, 10, (Object) null);
        Object[] objArr3 = this.elements;
        int length2 = objArr3.length;
        int i = this.head;
        ArraysKt___ArraysJvmKt.copyInto$default(objArr3, objArr2, length2 - i, 0, i, 4, (Object) null);
        this.elements = objArr2;
        this.head = 0;
        this.tail = length;
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    @Nullable
    public final T removeFirstOrNull() {
        int i = this.head;
        if (i == this.tail) {
            return null;
        }
        Object[] objArr = this.elements;
        T t = (T) objArr[i];
        objArr[i] = null;
        this.head = (i + 1) & (objArr.length - 1);
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
    }
}
