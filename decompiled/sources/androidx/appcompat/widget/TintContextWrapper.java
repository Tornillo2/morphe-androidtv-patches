package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class TintContextWrapper extends ContextWrapper {
    public static final Object CACHE_LOCK = new Object();
    public static ArrayList<WeakReference<TintContextWrapper>> sCache;
    public final Resources mResources;
    public final Resources.Theme mTheme;

    public TintContextWrapper(@NonNull Context context) {
        super(context);
        VectorEnabledTintResources.shouldBeUsed();
        this.mResources = new TintResources(this, context.getResources());
        this.mTheme = null;
    }

    public static boolean shouldWrap(@NonNull Context context) {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources) && !(context.getResources() instanceof VectorEnabledTintResources)) {
            VectorEnabledTintResources.shouldBeUsed();
        }
        return false;
    }

    public static Context wrap(@NonNull Context context) {
        shouldWrap(context);
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mResources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.mTheme;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.mTheme;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
