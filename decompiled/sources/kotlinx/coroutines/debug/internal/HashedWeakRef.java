package kotlinx.coroutines.debug.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class HashedWeakRef<T> extends WeakReference<T> {

    @JvmField
    public final int hash;

    public HashedWeakRef(T t, @Nullable ReferenceQueue<T> referenceQueue) {
        super(t, referenceQueue);
        this.hash = t != null ? t.hashCode() : 0;
    }
}
