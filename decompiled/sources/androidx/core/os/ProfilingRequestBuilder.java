package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.os.ProfilingRequestBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public abstract class ProfilingRequestBuilder<T extends ProfilingRequestBuilder<T>> {

    @Nullable
    public android.os.CancellationSignal mCancellationSignal;

    @Nullable
    public String mTag;

    @NotNull
    public final ProfilingRequest build() {
        return new ProfilingRequest(getProfilingType(), getParams(), this.mTag, this.mCancellationSignal);
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @NotNull
    public abstract Bundle getParams();

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public abstract int getProfilingType();

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @NotNull
    public abstract T getThis();

    @NotNull
    public final T setCancellationSignal(@NotNull android.os.CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        this.mCancellationSignal = cancellationSignal;
        return (T) getThis();
    }

    @NotNull
    public final T setTag(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.mTag = tag;
        return (T) getThis();
    }
}
