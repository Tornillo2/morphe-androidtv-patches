package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class JobNode extends CompletionHandlerBase implements DisposableHandle, Incomplete {
    public JobSupport job;

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        getJob().removeNode$kotlinx_coroutines_core(this);
    }

    @NotNull
    public final JobSupport getJob() {
        JobSupport jobSupport = this.job;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        throw null;
    }

    @Override // kotlinx.coroutines.Incomplete
    @Nullable
    public NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    public final void setJob(@NotNull JobSupport jobSupport) {
        this.job = jobSupport;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return getClass().getSimpleName() + ObjectUtils.AT_SIGN + DebugStringsKt.getHexAddress(this) + "[job@" + DebugStringsKt.getHexAddress(getJob()) + AbstractJsonLexerKt.END_LIST;
    }
}
