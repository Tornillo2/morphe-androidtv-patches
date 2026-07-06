package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AppCompatCompoundButtonHelper {
    public ColorStateList mButtonTintList = null;
    public PorterDuff.Mode mButtonTintMode = null;
    public boolean mHasButtonTint = false;
    public boolean mHasButtonTintMode = false;
    public boolean mSkipNextApply;

    @NonNull
    public final CompoundButton mView;

    public AppCompatCompoundButtonHelper(@NonNull CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    public void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable != null) {
            if (this.mHasButtonTint || this.mHasButtonTintMode) {
                Drawable drawableMutate = DrawableCompat.wrap(buttonDrawable).mutate();
                if (this.mHasButtonTint) {
                    drawableMutate.setTintList(this.mButtonTintList);
                }
                if (this.mHasButtonTintMode) {
                    drawableMutate.setTintMode(this.mButtonTintMode);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.mView.getDrawableState());
                }
                this.mView.setButtonDrawable(drawableMutate);
            }
        }
    }

    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0042 A[Catch: all -> 0x003f, TRY_ENTER, TryCatch #1 {all -> 0x003f, blocks: (B:3:0x001d, B:5:0x0027, B:7:0x0031, B:16:0x0063, B:18:0x006d, B:19:0x0078, B:21:0x0082, B:11:0x0042, B:13:0x004c, B:15:0x0056), top: B:29:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004c A[Catch: all -> 0x003f, TryCatch #1 {all -> 0x003f, blocks: (B:3:0x001d, B:5:0x0027, B:7:0x0031, B:16:0x0063, B:18:0x006d, B:19:0x0078, B:21:0x0082, B:11:0x0042, B:13:0x004c, B:15:0x0056), top: B:29:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadFromAttributes(@androidx.annotation.Nullable android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CompoundButton r0 = r10.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.R.styleable.CompoundButton
            r2 = 0
            androidx.appcompat.widget.TintTypedArray r1 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r11, r1, r12, r2)
            android.widget.CompoundButton r3 = r10.mView
            android.content.Context r4 = r3.getContext()
            int[] r5 = androidx.appcompat.R.styleable.CompoundButton
            android.content.res.TypedArray r7 = r1.mWrapped
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r3, r4, r5, r6, r7, r8, r9)
            int r11 = androidx.appcompat.R.styleable.CompoundButton_buttonCompat     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            boolean r11 = r12.hasValue(r11)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L42
            int r11 = androidx.appcompat.R.styleable.CompoundButton_buttonCompat     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            int r11 = r12.getResourceId(r11, r2)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L42
            android.widget.CompoundButton r12 = r10.mView     // Catch: java.lang.Throwable -> L3f android.content.res.Resources.NotFoundException -> L42
            android.content.Context r0 = r12.getContext()     // Catch: java.lang.Throwable -> L3f android.content.res.Resources.NotFoundException -> L42
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r11)     // Catch: java.lang.Throwable -> L3f android.content.res.Resources.NotFoundException -> L42
            r12.setButtonDrawable(r11)     // Catch: java.lang.Throwable -> L3f android.content.res.Resources.NotFoundException -> L42
            goto L63
        L3f:
            r0 = move-exception
            r11 = r0
            goto L99
        L42:
            int r11 = androidx.appcompat.R.styleable.CompoundButton_android_button     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            boolean r11 = r12.hasValue(r11)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L63
            int r11 = androidx.appcompat.R.styleable.CompoundButton_android_button     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            int r11 = r12.getResourceId(r11, r2)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L63
            android.widget.CompoundButton r12 = r10.mView     // Catch: java.lang.Throwable -> L3f
            android.content.Context r0 = r12.getContext()     // Catch: java.lang.Throwable -> L3f
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r11)     // Catch: java.lang.Throwable -> L3f
            r12.setButtonDrawable(r11)     // Catch: java.lang.Throwable -> L3f
        L63:
            int r11 = androidx.appcompat.R.styleable.CompoundButton_buttonTint     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            boolean r11 = r12.hasValue(r11)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L78
            android.widget.CompoundButton r11 = r10.mView     // Catch: java.lang.Throwable -> L3f
            int r12 = androidx.appcompat.R.styleable.CompoundButton_buttonTint     // Catch: java.lang.Throwable -> L3f
            android.content.res.ColorStateList r12 = r1.getColorStateList(r12)     // Catch: java.lang.Throwable -> L3f
            r11.setButtonTintList(r12)     // Catch: java.lang.Throwable -> L3f
        L78:
            int r11 = androidx.appcompat.R.styleable.CompoundButton_buttonTintMode     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r12 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            boolean r11 = r12.hasValue(r11)     // Catch: java.lang.Throwable -> L3f
            if (r11 == 0) goto L95
            android.widget.CompoundButton r11 = r10.mView     // Catch: java.lang.Throwable -> L3f
            int r12 = androidx.appcompat.R.styleable.CompoundButton_buttonTintMode     // Catch: java.lang.Throwable -> L3f
            android.content.res.TypedArray r0 = r1.mWrapped     // Catch: java.lang.Throwable -> L3f
            r2 = -1
            int r12 = r0.getInt(r12, r2)     // Catch: java.lang.Throwable -> L3f
            r0 = 0
            android.graphics.PorterDuff$Mode r12 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r12, r0)     // Catch: java.lang.Throwable -> L3f
            r11.setButtonTintMode(r12)     // Catch: java.lang.Throwable -> L3f
        L95:
            r1.recycle()
            return
        L99:
            r1.recycle()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
        } else {
            this.mSkipNextApply = true;
            applyButtonTint();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    public void setSupportButtonTintMode(@Nullable PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }
}
