package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ViewGroupKt$descendants$1$1 extends Lambda implements Function1<View, Iterator<? extends View>> {
    public static final ViewGroupKt$descendants$1$1 INSTANCE = new ViewGroupKt$descendants$1$1(1);

    public ViewGroupKt$descendants$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Iterator<View> invoke(View view) {
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            return new ViewGroupKt$children$1(viewGroup).iterator();
        }
        return null;
    }
}
