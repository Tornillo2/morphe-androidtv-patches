package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public final class ProfilingRequest {

    @Nullable
    public final android.os.CancellationSignal cancellationSignal;

    @NotNull
    public final Bundle params;
    public final int profilingType;

    @Nullable
    public final String tag;

    public ProfilingRequest(int i, @NotNull Bundle params, @Nullable String str, @Nullable android.os.CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.profilingType = i;
        this.params = params;
        this.tag = str;
        this.cancellationSignal = cancellationSignal;
    }

    @Nullable
    public final android.os.CancellationSignal getCancellationSignal() {
        return this.cancellationSignal;
    }

    @NotNull
    public final Bundle getParams() {
        return this.params;
    }

    public final int getProfilingType() {
        return this.profilingType;
    }

    @Nullable
    public final String getTag() {
        return this.tag;
    }
}
