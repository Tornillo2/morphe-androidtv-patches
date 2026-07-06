package com.google.android.gms.ads.nativead;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzep;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbbk;
import com.google.android.gms.internal.ads.zzbeu;
import com.google.android.gms.internal.ads.zzbzt;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class NativeAdView extends FrameLayout {

    @NotOnlyInitialized
    public final FrameLayout zza;

    @Nullable
    @NotOnlyInitialized
    public final zzbeu zzb;

    public NativeAdView(@NonNull Context context) {
        super(context);
        this.zza = zzd(context);
        this.zzb = zze();
    }

    @Override // android.view.ViewGroup
    public final void addView(@NonNull View view, int i, @NonNull ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zza);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void bringChildToFront(@NonNull View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zza;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    public void destroy() {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != null) {
            try {
                zzbeuVar.zzc();
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to destroy native ad view", e);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(@NonNull MotionEvent motionEvent) {
        if (this.zzb != null) {
            if (((Boolean) zzba.zzc().zzb(zzbbk.zzjP)).booleanValue()) {
                try {
                    this.zzb.zzd(ObjectWrapper.wrap(motionEvent));
                } catch (RemoteException e) {
                    zzbzt.zzh("Unable to call handleTouchEvent on delegate", e);
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Nullable
    public AdChoicesView getAdChoicesView() {
        View viewZza = zza("3011");
        if (viewZza instanceof AdChoicesView) {
            return (AdChoicesView) viewZza;
        }
        return null;
    }

    @Nullable
    public final View getAdvertiserView() {
        return zza("3005");
    }

    @Nullable
    public final View getBodyView() {
        return zza("3004");
    }

    @Nullable
    public final View getCallToActionView() {
        return zza("3002");
    }

    @Nullable
    public final View getHeadlineView() {
        return zza("3001");
    }

    @Nullable
    public final View getIconView() {
        return zza("3003");
    }

    @Nullable
    public final View getImageView() {
        return zza("3008");
    }

    @Nullable
    public final MediaView getMediaView() {
        View viewZza = zza("3010");
        if (viewZza instanceof MediaView) {
            return (MediaView) viewZza;
        }
        if (viewZza == null) {
            return null;
        }
        zzbzt.zze("View is not an instance of MediaView");
        return null;
    }

    @Nullable
    public final View getPriceView() {
        return zza("3007");
    }

    @Nullable
    public final View getStarRatingView() {
        return zza("3009");
    }

    @Nullable
    public final View getStoreView() {
        return zza("3006");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != null) {
            try {
                zzbeuVar.zze(ObjectWrapper.wrap(view), i);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        super.removeAllViews();
        addView(this.zza);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(@NonNull View view) {
        if (this.zza == view) {
            return;
        }
        super.removeView(view);
    }

    public void setAdChoicesView(@Nullable AdChoicesView adChoicesView) {
        zzf("3011", adChoicesView);
    }

    public final void setAdvertiserView(@Nullable View view) {
        zzf("3005", view);
    }

    public final void setBodyView(@Nullable View view) {
        zzf("3004", view);
    }

    public final void setCallToActionView(@Nullable View view) {
        zzf("3002", view);
    }

    public final void setClickConfirmingView(@Nullable View view) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != null) {
            try {
                zzbeuVar.zzbt(ObjectWrapper.wrap(view));
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call setClickConfirmingView on delegate", e);
            }
        }
    }

    public final void setHeadlineView(@Nullable View view) {
        zzf("3001", view);
    }

    public final void setIconView(@Nullable View view) {
        zzf("3003", view);
    }

    public final void setImageView(@Nullable View view) {
        zzf("3008", view);
    }

    public final void setMediaView(@Nullable MediaView mediaView) {
        zzf("3010", mediaView);
        if (mediaView == null) {
            return;
        }
        mediaView.zza(new zzb(this));
        mediaView.zzb(new zzc(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.google.android.gms.dynamic.IObjectWrapper, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setNativeAd(@NonNull NativeAd nativeAd) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != 0) {
            try {
                zzbeuVar.zzbw(nativeAd.zza());
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void setPriceView(@Nullable View view) {
        zzf("3007", view);
    }

    public final void setStarRatingView(@Nullable View view) {
        zzf("3009", view);
    }

    public final void setStoreView(@Nullable View view) {
        zzf("3006", view);
    }

    @Nullable
    public final View zza(@NonNull String str) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != null) {
            try {
                IObjectWrapper iObjectWrapperZzb = zzbeuVar.zzb(str);
                if (iObjectWrapperZzb != null) {
                    return (View) ObjectWrapper.unwrap(iObjectWrapperZzb);
                }
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call getAssetView on delegate", e);
            }
        }
        return null;
    }

    public final void zzb(MediaContent mediaContent) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar == null) {
            return;
        }
        try {
            if (mediaContent instanceof zzep) {
                zzbeuVar.zzbu(((zzep) mediaContent).zza);
            } else if (mediaContent == null) {
                zzbeuVar.zzbu(null);
            } else {
                zzbzt.zze("Use MediaContent provided by NativeAd.getMediaContent");
            }
        } catch (RemoteException e) {
            zzbzt.zzh("Unable to call setMediaContent on delegate", e);
        }
    }

    public final /* synthetic */ void zzc(ImageView.ScaleType scaleType) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar == null || scaleType == null) {
            return;
        }
        try {
            zzbeuVar.zzbv(ObjectWrapper.wrap(scaleType));
        } catch (RemoteException e) {
            zzbzt.zzh("Unable to call setMediaViewImageScaleType on delegate", e);
        }
    }

    public final FrameLayout zzd(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    @Nullable
    @RequiresNonNull({"overlayFrame"})
    public final zzbeu zze() {
        if (isInEditMode()) {
            return null;
        }
        return zzay.zza().zzh(this.zza.getContext(), this, this.zza);
    }

    public final void zzf(String str, @Nullable View view) {
        zzbeu zzbeuVar = this.zzb;
        if (zzbeuVar != null) {
            try {
                zzbeuVar.zzbs(str, ObjectWrapper.wrap(view));
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call setAssetView on delegate", e);
            }
        }
    }

    public NativeAdView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zza = zzd(context);
        this.zzb = zze();
    }

    public NativeAdView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zza = zzd(context);
        this.zzb = zze();
    }

    @TargetApi(21)
    public NativeAdView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zza = zzd(context);
        this.zzb = zze();
    }
}
