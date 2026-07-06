package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlin.PublishedApi;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalCoroutinesApi
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {

    @NotNull
    private volatile /* synthetic */ int _size = 0;

    @Nullable
    public T[] a;

    @PublishedApi
    public final void addImpl(@NotNull T t) {
        t.setHeap(this);
        ThreadSafeHeapNode[] threadSafeHeapNodeArrRealloc = realloc();
        int i = this._size;
        this._size = i + 1;
        threadSafeHeapNodeArrRealloc[i] = t;
        t.setIndex(i);
        siftUpFrom(i);
    }

    public final void addLast(@NotNull T t) {
        synchronized (this) {
            addImpl(t);
        }
    }

    public final boolean addLastIf(@NotNull T t, @NotNull Function1<? super T, Boolean> function1) {
        boolean z;
        synchronized (this) {
            if (function1.invoke(firstImpl()).booleanValue()) {
                addImpl(t);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void clear() {
        synchronized (this) {
            try {
                T[] tArr = this.a;
                if (tArr != null) {
                    ArraysKt___ArraysJvmKt.fill$default(tArr, (Object) null, 0, 0, 6, (Object) null);
                }
                this._size = 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Nullable
    public final T find(@NotNull Function1<? super T, Boolean> function1) {
        T t;
        synchronized (this) {
            try {
                int i = this._size;
                int i2 = 0;
                while (true) {
                    t = null;
                    if (i2 >= i) {
                        break;
                    }
                    T[] tArr = this.a;
                    if (tArr != null) {
                        t = (Object) tArr[i2];
                    }
                    Intrinsics.checkNotNull(t);
                    if (function1.invoke(t).booleanValue()) {
                        break;
                    }
                    i2++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    @PublishedApi
    @Nullable
    public final T firstImpl() {
        T[] tArr = this.a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    public final int getSize() {
        return this._size;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    @Nullable
    public final T peek() {
        T t;
        synchronized (this) {
            t = (T) firstImpl();
        }
        return t;
    }

    public final T[] realloc() {
        T[] tArr = this.a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.a = tArr2;
            return tArr2;
        }
        if (this._size < tArr.length) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, this._size * 2);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        T[] tArr3 = (T[]) ((ThreadSafeHeapNode[]) objArrCopyOf);
        this.a = tArr3;
        return tArr3;
    }

    public final boolean remove(@NotNull T t) {
        boolean z;
        synchronized (this) {
            if (t.getHeap() == null) {
                z = false;
            } else {
                removeAtImpl(t.getIndex());
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    @kotlin.PublishedApi
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T removeAtImpl(int r6) {
        /*
            r5 = this;
            T extends kotlinx.coroutines.internal.ThreadSafeHeapNode & java.lang.Comparable<? super T>[] r0 = r5.a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r1 = r5._size
            r2 = -1
            int r1 = r1 + r2
            r5._size = r1
            int r1 = r5._size
            if (r6 >= r1) goto L36
            int r1 = r5._size
            r5.swap(r6, r1)
            int r1 = r6 + (-1)
            int r1 = r1 / 2
            if (r6 <= 0) goto L33
            r3 = r0[r6]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r0[r1]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L33
            r5.swap(r6, r1)
            r5.siftUpFrom(r1)
            goto L36
        L33:
            r5.siftDownFrom(r6)
        L36:
            int r6 = r5._size
            r6 = r0[r6]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r1 = 0
            r6.setHeap(r1)
            r6.setIndex(r2)
            int r2 = r5._size
            r0[r2] = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.removeAtImpl(int):kotlinx.coroutines.internal.ThreadSafeHeapNode");
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> function1) {
        synchronized (this) {
            ThreadSafeHeapNode threadSafeHeapNodeFirstImpl = firstImpl();
            T t = null;
            if (threadSafeHeapNodeFirstImpl == null) {
                return null;
            }
            if (function1.invoke(threadSafeHeapNodeFirstImpl).booleanValue()) {
                t = (T) removeAtImpl(0);
            }
            return t;
        }
    }

    @Nullable
    public final T removeFirstOrNull() {
        T t;
        synchronized (this) {
            t = this._size > 0 ? (T) removeAtImpl(0) : null;
        }
        return t;
    }

    public final void setSize(int i) {
        this._size = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void siftDownFrom(int r6) {
        /*
            r5 = this;
        L0:
            int r0 = r6 * 2
            int r1 = r0 + 1
            int r2 = r5._size
            if (r1 < r2) goto L9
            goto L3a
        L9:
            T extends kotlinx.coroutines.internal.ThreadSafeHeapNode & java.lang.Comparable<? super T>[] r2 = r5.a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r0 = r0 + 2
            int r3 = r5._size
            if (r0 >= r3) goto L27
            r3 = r2[r0]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r2[r1]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L27
            goto L28
        L27:
            r0 = r1
        L28:
            r1 = r2[r6]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            r2 = r2[r0]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r1 = r1.compareTo(r2)
            if (r1 > 0) goto L3b
        L3a:
            return
        L3b:
            r5.swap(r6, r0)
            r6 = r0
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.siftDownFrom(int):void");
    }

    public final void siftUpFrom(int i) {
        while (i > 0) {
            T[] tArr = this.a;
            Intrinsics.checkNotNull(tArr);
            int i2 = (i - 1) / 2;
            T t = tArr[i2];
            Intrinsics.checkNotNull(t);
            T t2 = tArr[i];
            Intrinsics.checkNotNull(t2);
            if (((Comparable) t).compareTo(t2) <= 0) {
                return;
            }
            swap(i, i2);
            i = i2;
        }
    }

    public final void swap(int i, int i2) {
        T[] tArr = this.a;
        Intrinsics.checkNotNull(tArr);
        T t = tArr[i2];
        Intrinsics.checkNotNull(t);
        T t2 = tArr[i];
        Intrinsics.checkNotNull(t2);
        tArr[i] = t;
        tArr[i2] = t2;
        t.setIndex(i);
        t2.setIndex(i2);
    }
}
