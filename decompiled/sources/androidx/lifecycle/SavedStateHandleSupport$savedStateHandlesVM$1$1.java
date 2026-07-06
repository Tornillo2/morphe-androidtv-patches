package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleSupport$savedStateHandlesVM$1$1 extends Lambda implements Function1<CreationExtras, SavedStateHandlesVM> {
    public static final SavedStateHandleSupport$savedStateHandlesVM$1$1 INSTANCE = new SavedStateHandleSupport$savedStateHandlesVM$1$1(1);

    public SavedStateHandleSupport$savedStateHandlesVM$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final SavedStateHandlesVM invoke(@NotNull CreationExtras initializer) {
        Intrinsics.checkNotNullParameter(initializer, "$this$initializer");
        return new SavedStateHandlesVM();
    }
}
