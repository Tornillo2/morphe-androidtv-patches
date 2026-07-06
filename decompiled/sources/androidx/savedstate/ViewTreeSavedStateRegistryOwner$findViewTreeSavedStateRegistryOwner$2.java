package androidx.savedstate;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2 extends Lambda implements Function1<View, SavedStateRegistryOwner> {
    public static final ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2 INSTANCE = new ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2(1);

    public ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final SavedStateRegistryOwner invoke(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Object tag = view.getTag(R.id.view_tree_saved_state_registry_owner);
        if (tag instanceof SavedStateRegistryOwner) {
            return (SavedStateRegistryOwner) tag;
        }
        return null;
    }
}
