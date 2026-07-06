package androidx.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface LayoutInflaterFactory {
    View onCreateView(View view, String str, Context context, AttributeSet attributeSet);
}
