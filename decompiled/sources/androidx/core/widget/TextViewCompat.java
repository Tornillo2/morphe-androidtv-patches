package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TextViewCompat {
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        public static int getBreakStrategy(TextView textView) {
            return textView.getBreakStrategy();
        }

        public static ColorStateList getCompoundDrawableTintList(TextView textView) {
            return textView.getCompoundDrawableTintList();
        }

        public static PorterDuff.Mode getCompoundDrawableTintMode(TextView textView) {
            return textView.getCompoundDrawableTintMode();
        }

        public static int getHyphenationFrequency(TextView textView) {
            return textView.getHyphenationFrequency();
        }

        public static void setBreakStrategy(TextView textView, int i) {
            textView.setBreakStrategy(i);
        }

        public static void setCompoundDrawableTintList(TextView textView, ColorStateList colorStateList) {
            textView.setCompoundDrawableTintList(colorStateList);
        }

        public static void setCompoundDrawableTintMode(TextView textView, PorterDuff.Mode mode) {
            textView.setCompoundDrawableTintMode(mode);
        }

        public static void setHyphenationFrequency(TextView textView, int i) {
            textView.setHyphenationFrequency(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static DecimalFormatSymbols getInstance(Locale locale) {
            return DecimalFormatSymbols.getInstance(locale);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        public static int getAutoSizeMaxTextSize(TextView textView) {
            return textView.getAutoSizeMaxTextSize();
        }

        public static int getAutoSizeMinTextSize(TextView textView) {
            return textView.getAutoSizeMinTextSize();
        }

        public static int getAutoSizeStepGranularity(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        public static int[] getAutoSizeTextAvailableSizes(TextView textView) {
            return textView.getAutoSizeTextAvailableSizes();
        }

        public static int getAutoSizeTextType(TextView textView) {
            return textView.getAutoSizeTextType();
        }

        public static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i, int i2, int i3, int i4) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }

        public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, int[] iArr, int i) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }

        public static void setAutoSizeTextTypeWithDefaults(TextView textView, int i) {
            textView.setAutoSizeTextTypeWithDefaults(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static CharSequence castToCharSequence(PrecomputedText precomputedText) {
            return precomputedText;
        }

        public static String[] getDigitStrings(DecimalFormatSymbols decimalFormatSymbols) {
            return decimalFormatSymbols.getDigitStrings();
        }

        public static PrecomputedText.Params getTextMetricsParams(TextView textView) {
            return textView.getTextMetricsParams();
        }

        public static void setFirstBaselineToTopHeight(TextView textView, int i) {
            textView.setFirstBaselineToTopHeight(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(34)
    public static class Api34Impl {
        public static void setLineHeight(@NonNull TextView textView, int i, @FloatRange(from = 0.0d) float f) {
            textView.setLineHeight(i, f);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface AutoSizeTextType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class OreoCallback implements ActionMode.Callback {
        public static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
        public final ActionMode.Callback mCallback;
        public boolean mCanUseMenuBuilderReferences;
        public boolean mInitializedMenuBuilderReferences = false;
        public Class<?> mMenuBuilderClass;
        public Method mMenuBuilderRemoveItemAtMethod;
        public final TextView mTextView;

        public OreoCallback(ActionMode.Callback callback, TextView textView) {
            this.mCallback = callback;
            this.mTextView = textView;
        }

        public final Intent createProcessTextIntent() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        public final Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo, TextView textView) {
            Intent intentPutExtra = createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !isEditable(textView));
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            return intentPutExtra.setClassName(activityInfo.packageName, activityInfo.name);
        }

        public final List<ResolveInfo> getSupportedActivities(Context context, PackageManager packageManager) {
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(createProcessTextIntent(), 0)) {
                    if (isSupportedActivity(resolveInfo, context)) {
                        arrayList.add(resolveInfo);
                    }
                }
            }
            return arrayList;
        }

        @NonNull
        public ActionMode.Callback getWrappedCallback() {
            return this.mCallback;
        }

        public final boolean isEditable(TextView textView) {
            return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
        }

        public final boolean isSupportedActivity(ResolveInfo resolveInfo, Context context) {
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!activityInfo.exported) {
                return false;
            }
            String str = activityInfo.permission;
            return str == null || context.checkSelfPermission(str) == 0;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mCallback.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mCallback.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.mCallback.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            recomputeProcessTextMenuItems(menu);
            return this.mCallback.onPrepareActionMode(actionMode, menu);
        }

        public final void recomputeProcessTextMenuItems(Menu menu) {
            Context context = this.mTextView.getContext();
            PackageManager packageManager = context.getPackageManager();
            boolean z = this.mInitializedMenuBuilderReferences;
            Class<?> cls = Integer.TYPE;
            if (!z) {
                this.mInitializedMenuBuilderReferences = true;
                try {
                    Class<?> cls2 = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.mMenuBuilderClass = cls2;
                    this.mMenuBuilderRemoveItemAtMethod = cls2.getDeclaredMethod("removeItemAt", cls);
                    this.mCanUseMenuBuilderReferences = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    this.mMenuBuilderClass = null;
                    this.mMenuBuilderRemoveItemAtMethod = null;
                    this.mCanUseMenuBuilderReferences = false;
                }
            }
            try {
                Method declaredMethod = (this.mCanUseMenuBuilderReferences && this.mMenuBuilderClass.isInstance(menu)) ? this.mMenuBuilderRemoveItemAtMethod : menu.getClass().getDeclaredMethod("removeItemAt", cls);
                for (int size = menu.size() - 1; size >= 0; size--) {
                    MenuItem item = menu.getItem(size);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        declaredMethod.invoke(menu, Integer.valueOf(size));
                    }
                }
                List<ResolveInfo> supportedActivities = getSupportedActivities(context, packageManager);
                int i = 0;
                while (true) {
                    ArrayList arrayList = (ArrayList) supportedActivities;
                    if (i >= arrayList.size()) {
                        return;
                    }
                    ResolveInfo resolveInfo = (ResolveInfo) arrayList.get(i);
                    menu.add(0, 0, i + 100, resolveInfo.loadLabel(packageManager)).setIntent(createProcessTextIntentForResolveInfo(resolveInfo, this.mTextView)).setShowAsAction(1);
                    i++;
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getAutoSizeMaxTextSize(@NonNull TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return Api26Impl.getAutoSizeMaxTextSize(textView);
        }
        if (textView instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView).getAutoSizeMaxTextSize();
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getAutoSizeMinTextSize(@NonNull TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return Api26Impl.getAutoSizeMinTextSize(textView);
        }
        if (textView instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView).getAutoSizeMinTextSize();
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getAutoSizeStepGranularity(@NonNull TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return Api26Impl.getAutoSizeStepGranularity(textView);
        }
        if (textView instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView).getAutoSizeStepGranularity();
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView textView) {
        return Build.VERSION.SDK_INT >= 27 ? Api26Impl.getAutoSizeTextAvailableSizes(textView) : textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeTextAvailableSizes() : new int[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getAutoSizeTextType(@NonNull TextView textView) {
        if (Build.VERSION.SDK_INT >= 27) {
            return Api26Impl.getAutoSizeTextType(textView);
        }
        if (textView instanceof AutoSizeableTextView) {
            return ((AutoSizeableTextView) textView).getAutoSizeTextType();
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static ColorStateList getCompoundDrawableTintList(@NonNull TextView textView) {
        textView.getClass();
        if (Build.VERSION.SDK_INT >= 24) {
            return Api23Impl.getCompoundDrawableTintList(textView);
        }
        if (textView instanceof TintableCompoundDrawablesView) {
            return ((TintableCompoundDrawablesView) textView).getSupportCompoundDrawablesTintList();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static PorterDuff.Mode getCompoundDrawableTintMode(@NonNull TextView textView) {
        textView.getClass();
        if (Build.VERSION.SDK_INT >= 24) {
            return Api23Impl.getCompoundDrawableTintMode(textView);
        }
        if (textView instanceof TintableCompoundDrawablesView) {
            return ((TintableCompoundDrawablesView) textView).getSupportCompoundDrawablesTintMode();
        }
        return null;
    }

    @NonNull
    @ReplaceWith(expression = "textView.getCompoundDrawablesRelative()")
    @Deprecated
    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
        return textView.getCompoundDrawablesRelative();
    }

    public static int getFirstBaselineToTopHeight(@NonNull TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    public static int getLastBaselineToBottomHeight(@NonNull TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    @ReplaceWith(expression = "textView.getMaxLines()")
    @Deprecated
    public static int getMaxLines(@NonNull TextView textView) {
        return textView.getMaxLines();
    }

    @ReplaceWith(expression = "textView.getMinLines()")
    @Deprecated
    public static int getMinLines(@NonNull TextView textView) {
        return textView.getMinLines();
    }

    public static int getTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
        TextDirectionHeuristic textDirectionHeuristic2;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        if (textDirectionHeuristic == textDirectionHeuristic3 || textDirectionHeuristic == (textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == textDirectionHeuristic2) {
            return 6;
        }
        return textDirectionHeuristic == textDirectionHeuristic3 ? 7 : 1;
    }

    public static TextDirectionHeuristic getTextDirectionHeuristic(@NonNull TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        if (Build.VERSION.SDK_INT >= 28 && (textView.getInputType() & 15) == 3) {
            byte directionality = Character.getDirectionality(Api28Impl.getDigitStrings(Api24Impl.getInstance(textView.getTextLocale()))[0].codePointAt(0));
            return (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        boolean z = textView.getLayoutDirection() == 1;
        switch (textView.getTextDirection()) {
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                if (!z) {
                }
                break;
        }
        return TextDirectionHeuristics.LTR;
    }

    @NonNull
    public static PrecomputedTextCompat.Params getTextMetricsParams(@NonNull TextView textView) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return new PrecomputedTextCompat.Params(Api28Impl.getTextMetricsParams(textView));
        }
        PrecomputedTextCompat.Params.Builder builder = new PrecomputedTextCompat.Params.Builder(new TextPaint(textView.getPaint()));
        if (i >= 23) {
            builder.mBreakStrategy = Api23Impl.getBreakStrategy(textView);
            builder.mHyphenationFrequency = Api23Impl.getHyphenationFrequency(textView);
        }
        builder.mTextDir = getTextDirectionHeuristic(textView);
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView textView, int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (Build.VERSION.SDK_INT >= 27) {
            Api26Impl.setAutoSizeTextTypeUniformWithConfiguration(textView, i, i2, i3, i4);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView textView, @NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (Build.VERSION.SDK_INT >= 27) {
            Api26Impl.setAutoSizeTextTypeUniformWithPresetSizes(textView, iArr, i);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView textView, int i) {
        if (Build.VERSION.SDK_INT >= 27) {
            Api26Impl.setAutoSizeTextTypeWithDefaults(textView, i);
        } else if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView) textView).setAutoSizeTextTypeWithDefaults(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setCompoundDrawableTintList(@NonNull TextView textView, @Nullable ColorStateList colorStateList) {
        textView.getClass();
        if (Build.VERSION.SDK_INT >= 24) {
            Api23Impl.setCompoundDrawableTintList(textView, colorStateList);
        } else if (textView instanceof TintableCompoundDrawablesView) {
            ((TintableCompoundDrawablesView) textView).setSupportCompoundDrawablesTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setCompoundDrawableTintMode(@NonNull TextView textView, @Nullable PorterDuff.Mode mode) {
        textView.getClass();
        if (Build.VERSION.SDK_INT >= 24) {
            Api23Impl.setCompoundDrawableTintMode(textView, mode);
        } else if (textView instanceof TintableCompoundDrawablesView) {
            ((TintableCompoundDrawablesView) textView).setSupportCompoundDrawablesTintMode(mode);
        }
    }

    @ReplaceWith(expression = "textView.setCompoundDrawablesRelative(start, top, end, bottom)")
    @Deprecated
    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @ReplaceWith(expression = "textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)")
    @Deprecated
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @ReplaceWith(expression = "textView.setCustomSelectionActionModeCallback(callback)")
    @Deprecated
    public static void setCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull ActionMode.Callback callback) {
        textView.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(textView, callback));
    }

    public static void setFirstBaselineToTopHeight(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        Preconditions.checkArgumentNonnegative(i);
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setFirstBaselineToTopHeight(textView, i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i + i2, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void setLastBaselineToBottomHeight(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        Preconditions.checkArgumentNonnegative(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void setLineHeight(@NonNull TextView textView, @IntRange(from = 0) @Px int i) {
        Preconditions.checkArgumentNonnegative(i);
        if (i != textView.getPaint().getFontMetricsInt(null)) {
            textView.setLineSpacing(i - r0, 1.0f);
        }
    }

    public static void setPrecomputedText(@NonNull TextView textView, @NonNull PrecomputedTextCompat precomputedTextCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            textView.setText(precomputedTextCompat.getPrecomputedText());
        } else {
            if (!getTextMetricsParams(textView).equalsWithoutTextDirection(precomputedTextCompat.getParams())) {
                throw new IllegalArgumentException("Given text can not be applied to TextView.");
            }
            textView.setText(precomputedTextCompat);
        }
    }

    public static void setTextAppearance(@NonNull TextView textView, @StyleRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    public static void setTextMetricsParams(@NonNull TextView textView, @NonNull PrecomputedTextCompat.Params params) {
        textView.setTextDirection(getTextDirection(params.mTextDir));
        if (Build.VERSION.SDK_INT >= 23) {
            textView.getPaint().set(params.mPaint);
            Api23Impl.setBreakStrategy(textView, params.mBreakStrategy);
            Api23Impl.setHyphenationFrequency(textView, params.mHyphenationFrequency);
        } else {
            float textScaleX = params.mPaint.getTextScaleX();
            textView.getPaint().set(params.mPaint);
            if (textScaleX == textView.getTextScaleX()) {
                textView.setTextScaleX((textScaleX / 2.0f) + 1.0f);
            }
            textView.setTextScaleX(textScaleX);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ActionMode.Callback unwrapCustomSelectionActionModeCallback(@Nullable ActionMode.Callback callback) {
        return (!(callback instanceof OreoCallback) || Build.VERSION.SDK_INT < 26) ? callback : ((OreoCallback) callback).getWrappedCallback();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ActionMode.Callback wrapCustomSelectionActionModeCallback(@NonNull TextView textView, @Nullable ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return (i < 26 || i > 27 || (callback instanceof OreoCallback) || callback == null) ? callback : new OreoCallback(callback, textView);
    }

    @ReplaceWith(expression = "textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)")
    @Deprecated
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    public static void setLineHeight(@NonNull TextView textView, int i, @FloatRange(from = 0.0d) float f) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setLineHeight(textView, i, f);
        } else {
            setLineHeight(textView, Math.round(TypedValue.applyDimension(i, f, textView.getResources().getDisplayMetrics())));
        }
    }
}
