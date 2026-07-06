package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzdp;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzdq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzdq<MessageType extends zzdq<MessageType, BuilderType>, BuilderType extends zzdp<MessageType, BuilderType>> implements zzgj {
    protected int zza = 0;

    public static void zzg(Iterable iterable, List list) {
        byte[] bArr = zzfm.zzb;
        int size = ((Collection) iterable).size();
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + size);
        } else if (list instanceof zzgr) {
            zzgr zzgrVar = (zzgr) list;
            zzgrVar.zzf(zzgrVar.zzd + size);
        }
        int size2 = list.size();
        List list2 = (List) iterable;
        int size3 = list2.size();
        for (int i = 0; i < size3; i++) {
            Object obj = list2.get(i);
            if (obj == null) {
                String strM = ObjectListKt$$ExternalSyntheticOutline1.m("Element at index ", list.size() - size2, " is null.");
                int size4 = list.size();
                while (true) {
                    size4--;
                    if (size4 < size2) {
                        throw new NullPointerException(strM);
                    }
                    list.remove(size4);
                }
            } else {
                list.add(obj);
            }
        }
    }

    public final byte[] zzM() {
        try {
            int iZzj = zzj();
            byte[] bArr = new byte[iZzj];
            int i = zzen.zzb;
            zzek zzekVar = new zzek(bArr, 0, iZzj);
            zzL(zzekVar);
            zzekVar.zzE();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e);
        }
    }

    public int zze(zzgt zzgtVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgj
    public final zzeg zzf() {
        try {
            int iZzj = zzj();
            zzeg zzegVar = zzeg.zzb;
            byte[] bArr = new byte[iZzj];
            int i = zzen.zzb;
            zzek zzekVar = new zzek(bArr, 0, iZzj);
            zzL(zzekVar);
            zzekVar.zzE();
            return new zzee(bArr);
        } catch (IOException e) {
            throw new RuntimeException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e);
        }
    }
}
