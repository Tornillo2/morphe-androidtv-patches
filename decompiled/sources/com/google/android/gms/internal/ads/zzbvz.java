package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.rewarded.RewardItem;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbvz implements RewardItem {
    public final zzbvm zza;

    public zzbvz(zzbvm zzbvmVar) {
        this.zza = zzbvmVar;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public final int getAmount() {
        zzbvm zzbvmVar = this.zza;
        if (zzbvmVar != null) {
            try {
                return zzbvmVar.zze();
            } catch (RemoteException e) {
                zzbzt.zzk("Could not forward getAmount to RewardItem", e);
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    @Nullable
    public final String getType() {
        zzbvm zzbvmVar = this.zza;
        if (zzbvmVar != null) {
            try {
                return zzbvmVar.zzf();
            } catch (RemoteException e) {
                zzbzt.zzk("Could not forward getType to RewardItem", e);
            }
        }
        return null;
    }
}
