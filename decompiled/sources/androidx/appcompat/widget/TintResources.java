package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TintResources extends ResourcesWrapper {
    public final WeakReference<Context> mContextRef;

    public TintResources(@NonNull Context context, @NonNull Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Drawable drawableCanonical = getDrawableCanonical(i);
        Context context = this.mContextRef.get();
        if (drawableCanonical != null && context != null) {
            ResourceManagerInternal.get().tintDrawableUsingColorFilter(context, i, drawableCanonical);
        }
        return drawableCanonical;
    }
}
