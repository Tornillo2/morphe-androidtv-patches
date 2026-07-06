package com.google.android.gms.ads.nativead;

import android.os.RemoteException;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbfa;
import com.google.android.gms.internal.ads.zzbzt;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class NativeAdViewHolder {

    @NonNull
    public static final WeakHashMap zza = new WeakHashMap();

    @NotOnlyInitialized
    public zzbfa zzb;
    public WeakReference zzc;

    public NativeAdViewHolder(@NonNull View view, @NonNull Map<String, View> map, @NonNull Map<String, View> map2) {
        Preconditions.checkNotNull(view, "ContainerView must not be null");
        if (view instanceof NativeAdView) {
            zzbzt.zzg("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
            return;
        }
        WeakHashMap weakHashMap = zza;
        if (weakHashMap.get(view) != null) {
            zzbzt.zzg("The provided containerView is already in use with another NativeAdViewHolder.");
            return;
        }
        weakHashMap.put(view, this);
        this.zzc = new WeakReference(view);
        this.zzb = zzay.zza().zzi(view, zza(map), zza(map2));
    }

    public static final HashMap zza(Map map) {
        return map == null ? new HashMap() : new HashMap(map);
    }

    public final void setClickConfirmingView(@NonNull View view) {
        try {
            this.zzb.zzb(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzbzt.zzh("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.gms.dynamic.IObjectWrapper, java.lang.Object] */
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
        ?? Zza = nativeAd.zza();
        WeakReference weakReference = this.zzc;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null) {
            zzbzt.zzj("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        WeakHashMap weakHashMap = zza;
        if (!weakHashMap.containsKey(view)) {
            weakHashMap.put(view, this);
        }
        zzbfa zzbfaVar = this.zzb;
        if (zzbfaVar != 0) {
            try {
                zzbfaVar.zzc(Zza);
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public void unregisterNativeAd() {
        zzbfa zzbfaVar = this.zzb;
        if (zzbfaVar != null) {
            try {
                zzbfaVar.zzd();
            } catch (RemoteException e) {
                zzbzt.zzh("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        WeakReference weakReference = this.zzc;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null) {
            zza.remove(view);
        }
    }
}
