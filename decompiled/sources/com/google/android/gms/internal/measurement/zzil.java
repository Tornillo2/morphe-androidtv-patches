package com.google.android.gms.internal.measurement;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.android.gms.internal.measurement.zzik;
import com.google.android.gms.internal.measurement.zzil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzil<MessageType extends zzil<MessageType, BuilderType>, BuilderType extends zzik<MessageType, BuilderType>> implements zzlj {
    protected int zzb = 0;

    public static void zzbw(Iterable iterable, List list) {
        zzkk.zze(iterable);
        if (iterable instanceof zzkr) {
            List listZzh = ((zzkr) iterable).zzh();
            zzkr zzkrVar = (zzkr) list;
            int size = list.size();
            for (Object obj : listZzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzkrVar.size() - size) + " is null.";
                    int size2 = zzkrVar.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            throw new NullPointerException(str);
                        }
                        zzkrVar.remove(size2);
                    }
                } else if (obj instanceof zzjb) {
                    zzkrVar.zzi((zzjb) obj);
                } else {
                    zzkrVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzlq) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size3 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                int size4 = list.size();
                while (true) {
                    size4--;
                    if (size4 < size3) {
                        throw new NullPointerException(str2);
                    }
                    list.remove(size4);
                }
            } else {
                list.add(obj2);
            }
        }
    }

    public int zzbu() {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final zzjb zzbv() {
        try {
            int iZzbz = zzbz();
            zzjb zzjbVar = zzjb.zzb;
            byte[] bArr = new byte[iZzbz];
            zzjj zzjjVarZzC = zzjj.zzC(bArr);
            zzbN(zzjjVarZzC);
            zzjjVarZzC.zzD();
            return new zziy(bArr);
        } catch (IOException e) {
            throw new RuntimeException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e);
        }
    }

    public void zzbx(int i) {
        throw null;
    }

    public final byte[] zzby() {
        try {
            byte[] bArr = new byte[zzbz()];
            zzjj zzjjVarZzC = zzjj.zzC(bArr);
            zzbN(zzjjVarZzC);
            zzjjVarZzC.zzD();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e);
        }
    }
}
