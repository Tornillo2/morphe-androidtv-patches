package androidx.work;

import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public enum DirectExecutor implements Executor {
    INSTANCE;

    public static final /* synthetic */ DirectExecutor[] $values() {
        return new DirectExecutor[]{INSTANCE};
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable command) {
        Intrinsics.checkNotNullParameter(command, "command");
        command.run();
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return "DirectExecutor";
    }
}
