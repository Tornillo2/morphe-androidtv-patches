package com.google.android.gms.ads.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzea;
import com.google.android.gms.internal.ads.zzbzt;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SearchAdView extends ViewGroup {

    @NotOnlyInitialized
    public final zzea zza;

    public SearchAdView(@NonNull Context context) {
        super(context);
        this.zza = new zzea(this);
    }

    public void destroy() {
        this.zza.zzk();
    }

    @NonNull
    public AdListener getAdListener() {
        return this.zza.zzg;
    }

    @Nullable
    public AdSize getAdSize() {
        return this.zza.zzb();
    }

    @NonNull
    public String getAdUnitId() {
        return this.zza.zzj();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(@NonNull DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (!AdSize.SEARCH.equals(getAdSize())) {
            throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
        }
        this.zza.zzm(dynamicHeightSearchAdRequest.zza.zza);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int i5 = ((i3 - i) - measuredWidth) / 2;
        int i6 = ((i4 - i2) - measuredHeight) / 2;
        childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        AdSize adSize;
        int heightInPixels;
        int measuredWidth = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzbzt.zzh("Unable to retrieve ad size.", e);
                adSize = null;
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                heightInPixels = adSize.getHeightInPixels(context);
                measuredWidth = widthInPixels;
            } else {
                heightInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            measuredWidth = childAt.getMeasuredWidth();
            heightInPixels = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(measuredWidth, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(heightInPixels, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        this.zza.zzn();
    }

    public void resume() {
        this.zza.zzp();
    }

    public void setAdListener(@NonNull AdListener adListener) {
        this.zza.zzr(adListener);
    }

    public void setAdSize(@NonNull AdSize adSize) {
        this.zza.zzs(adSize);
    }

    public void setAdUnitId(@NonNull String str) {
        this.zza.zzu(str);
    }

    public SearchAdView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zza = new zzea(this, attributeSet, false);
    }

    public SearchAdView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zza = new zzea(this, attributeSet, false);
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(@NonNull SearchAdRequest searchAdRequest) {
        this.zza.zzm(searchAdRequest.zza);
    }
}
