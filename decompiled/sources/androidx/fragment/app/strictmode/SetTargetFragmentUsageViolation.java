package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SetTargetFragmentUsageViolation extends TargetFragmentUsageViolation {
    public final int requestCode;

    @NotNull
    public final Fragment targetFragment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetTargetFragmentUsageViolation(@NotNull Fragment fragment, @NotNull Fragment targetFragment, int i) {
        super(fragment, "Attempting to set target fragment " + targetFragment + " with request code " + i + " for fragment " + fragment);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(targetFragment, "targetFragment");
        this.targetFragment = targetFragment;
        this.requestCode = i;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    @NotNull
    public final Fragment getTargetFragment() {
        return this.targetFragment;
    }
}
