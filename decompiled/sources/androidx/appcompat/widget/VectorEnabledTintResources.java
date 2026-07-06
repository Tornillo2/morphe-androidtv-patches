package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.ResourcesCompat;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class VectorEnabledTintResources extends ResourcesWrapper {
    public static final int MAX_SDK_WHERE_REQUIRED = 20;
    public static boolean sCompatVectorFromResourcesEnabled = false;
    public final WeakReference<Context> mContextRef;

    public VectorEnabledTintResources(@NonNull Context context, @NonNull Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference<>(context);
    }

    public static boolean isCompatVectorFromResourcesEnabled() {
        return sCompatVectorFromResourcesEnabled;
    }

    public static void setCompatVectorFromResourcesEnabled(boolean z) {
        sCompatVectorFromResourcesEnabled = z;
    }

    public static boolean shouldBeUsed() {
        return false;
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getAnimation(int i) throws Resources.NotFoundException {
        return this.mResources.getAnimation(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public boolean getBoolean(int i) throws Resources.NotFoundException {
        return this.mResources.getBoolean(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int getColor(int i) throws Resources.NotFoundException {
        return this.mResources.getColor(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public ColorStateList getColorStateList(int i) throws Resources.NotFoundException {
        return this.mResources.getColorStateList(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public float getDimension(int i) throws Resources.NotFoundException {
        return this.mResources.getDimension(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int getDimensionPixelOffset(int i) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelOffset(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int getDimensionPixelSize(int i) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelSize(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Context context = this.mContextRef.get();
        return context != null ? ResourceManagerInternal.get().onDrawableLoadedFromResources(context, this, i) : getDrawableCanonical(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ Drawable getDrawableForDensity(int i, int i2) throws Resources.NotFoundException {
        return super.getDrawableForDensity(i, i2);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public float getFraction(int i, int i2, int i3) {
        return this.mResources.getFraction(i, i2, i3);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.mResources.getIdentifier(str, str2, str3);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int[] getIntArray(int i) throws Resources.NotFoundException {
        return this.mResources.getIntArray(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public int getInteger(int i) throws Resources.NotFoundException {
        return this.mResources.getInteger(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getLayout(int i) throws Resources.NotFoundException {
        return this.mResources.getLayout(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public Movie getMovie(int i) throws Resources.NotFoundException {
        return this.mResources.getMovie(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getQuantityString(int i, int i2, Object[] objArr) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(i, i2, objArr);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public CharSequence getQuantityText(int i, int i2) throws Resources.NotFoundException {
        return this.mResources.getQuantityText(i, i2);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getResourceEntryName(int i) throws Resources.NotFoundException {
        return this.mResources.getResourceEntryName(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getResourceName(int i) throws Resources.NotFoundException {
        return this.mResources.getResourceName(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getResourcePackageName(int i) throws Resources.NotFoundException {
        return this.mResources.getResourcePackageName(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getResourceTypeName(int i) throws Resources.NotFoundException {
        return this.mResources.getResourceTypeName(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getString(int i) throws Resources.NotFoundException {
        return this.mResources.getString(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String[] getStringArray(int i) throws Resources.NotFoundException {
        return this.mResources.getStringArray(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public CharSequence getText(int i) throws Resources.NotFoundException {
        return this.mResources.getText(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public CharSequence[] getTextArray(int i) throws Resources.NotFoundException {
        return this.mResources.getTextArray(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void getValue(int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        super.getValue(i, typedValue, z);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        super.getValueForDensity(i, i2, typedValue, z);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getXml(int i) throws Resources.NotFoundException {
        return this.mResources.getXml(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.mResources.obtainAttributes(attributeSet, iArr);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public TypedArray obtainTypedArray(int i) throws Resources.NotFoundException {
        return this.mResources.obtainTypedArray(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public InputStream openRawResource(int i) throws Resources.NotFoundException {
        return this.mResources.openRawResource(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i) throws Resources.NotFoundException {
        return this.mResources.openRawResourceFd(i);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        super.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        super.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    @RequiresApi(21)
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, i, i2, theme);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getQuantityString(int i, int i2) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(i, i2);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public String getString(int i, Object[] objArr) throws Resources.NotFoundException {
        return this.mResources.getString(i, objArr);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public CharSequence getText(int i, CharSequence charSequence) {
        return this.mResources.getText(i, charSequence);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public /* bridge */ /* synthetic */ void getValue(String str, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        super.getValue(str, typedValue, z);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    public InputStream openRawResource(int i, TypedValue typedValue) throws Resources.NotFoundException {
        return this.mResources.openRawResource(i, typedValue);
    }

    @Override // androidx.appcompat.widget.ResourcesWrapper, android.content.res.Resources
    @RequiresApi(21)
    public Drawable getDrawable(int i, Resources.Theme theme) throws Resources.NotFoundException {
        return ResourcesCompat.getDrawable(this.mResources, i, theme);
    }
}
