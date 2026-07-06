package androidx.core.os;

import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public final class StackSamplingRequestBuilder extends ProfilingRequestBuilder<StackSamplingRequestBuilder> {

    @NotNull
    public final Bundle mParams = new Bundle();

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @NotNull
    public Bundle getParams() {
        return this.mParams;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public int getProfilingType() {
        return 3;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    public ProfilingRequestBuilder getThis() {
        return this;
    }

    @NotNull
    public final StackSamplingRequestBuilder setBufferSizeKb(int i) {
        this.mParams.putInt(Profiling.KEY_SIZE_KB, i);
        return this;
    }

    @NotNull
    public final StackSamplingRequestBuilder setDurationMs(int i) {
        this.mParams.putInt(Profiling.KEY_DURATION_MS, i);
        return this;
    }

    @NotNull
    public final StackSamplingRequestBuilder setSamplingFrequencyHz(int i) {
        this.mParams.putInt(Profiling.KEY_FREQUENCY_HZ, i);
        return this;
    }

    @Override // androidx.core.os.ProfilingRequestBuilder
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @NotNull
    public StackSamplingRequestBuilder getThis() {
        return this;
    }
}
