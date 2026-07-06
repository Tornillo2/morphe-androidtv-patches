package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultContract<I, O> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SynchronousResult<T> {
        public final T value;

        public SynchronousResult(T t) {
            this.value = t;
        }

        public final T getValue() {
            return this.value;
        }
    }

    @NotNull
    public abstract Intent createIntent(@NotNull Context context, I i);

    @Nullable
    public SynchronousResult<O> getSynchronousResult(@NotNull Context context, I i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return null;
    }

    public abstract O parseResult(int i, @Nullable Intent intent);
}
