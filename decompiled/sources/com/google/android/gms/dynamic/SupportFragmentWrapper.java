package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IFragmentWrapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class SupportFragmentWrapper extends IFragmentWrapper.Stub {
    public final Fragment zza;

    public SupportFragmentWrapper(Fragment fragment) {
        this.zza = fragment;
    }

    @Nullable
    @KeepForSdk
    public static SupportFragmentWrapper wrap(@Nullable Fragment fragment) {
        if (fragment != null) {
            return new SupportFragmentWrapper(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzA() {
        return this.zza.isVisible();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzb() {
        return this.zza.mFragmentId;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzc() {
        Fragment fragment = this.zza;
        fragment.getClass();
        FragmentStrictMode.onGetTargetFragmentRequestCodeUsage(fragment);
        return fragment.mTargetRequestCode;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final Bundle zzd() {
        return this.zza.mArguments;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final IFragmentWrapper zze() {
        return wrap(this.zza.mParentFragment);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final IFragmentWrapper zzf() {
        return wrap(this.zza.getTargetFragment(true));
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzg() {
        return new ObjectWrapper(this.zza.getActivity());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzh() {
        return new ObjectWrapper(this.zza.getResources());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzi() {
        return new ObjectWrapper(this.zza.getView());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final String zzj() {
        return this.zza.mTag;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzk(@NonNull IObjectWrapper iObjectWrapper) {
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.checkNotNull(view);
        this.zza.registerForContextMenu(view);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzl(boolean z) {
        this.zza.setHasOptionsMenu(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzm(boolean z) {
        this.zza.setMenuVisibility(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzn(boolean z) {
        this.zza.setRetainInstance(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzo(boolean z) {
        this.zza.setUserVisibleHint(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzp(@NonNull Intent intent) {
        this.zza.startActivity(intent);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzq(@NonNull Intent intent, int i) {
        this.zza.startActivityForResult(intent, i);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzr(@NonNull IObjectWrapper iObjectWrapper) {
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.checkNotNull(view);
        this.zza.unregisterForContextMenu(view);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzs() {
        Fragment fragment = this.zza;
        fragment.getClass();
        FragmentStrictMode.onGetRetainInstanceUsage(fragment);
        return fragment.mRetainInstance;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzt() {
        return this.zza.getUserVisibleHint();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzu() {
        return this.zza.isAdded();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzv() {
        return this.zza.mDetached;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzw() {
        return this.zza.isHidden();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzx() {
        return this.zza.mInLayout;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzy() {
        return this.zza.mRemoving;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzz() {
        return this.zza.isResumed();
    }
}
