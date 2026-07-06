package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WrappedDrawableState extends Drawable.ConstantState {
    public int mChangingConfigurations;
    public Drawable.ConstantState mDrawableState;
    public ColorStateList mTint;
    public PorterDuff.Mode mTintMode;

    public WrappedDrawableState(@Nullable WrappedDrawableState wrappedDrawableState) {
        this.mTint = null;
        this.mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;
        if (wrappedDrawableState != null) {
            this.mChangingConfigurations = wrappedDrawableState.mChangingConfigurations;
            this.mDrawableState = wrappedDrawableState.mDrawableState;
            this.mTint = wrappedDrawableState.mTint;
            this.mTintMode = wrappedDrawableState.mTintMode;
        }
    }

    public boolean canConstantState() {
        return this.mDrawableState != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.mChangingConfigurations;
        Drawable.ConstantState constantState = this.mDrawableState;
        return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable() {
        return new WrappedDrawableApi21(this, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable(@Nullable Resources resources) {
        return new WrappedDrawableApi21(this, resources);
    }
}
