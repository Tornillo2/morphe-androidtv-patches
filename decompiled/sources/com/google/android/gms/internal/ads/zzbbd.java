package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzba;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbbd {
    public final List zza = new ArrayList();
    public final List zzb = new ArrayList();
    public final List zzc = new ArrayList();

    public final List zza() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.zzb.iterator();
        while (it.hasNext()) {
            String str = (String) zzba.zzc().zzb((zzbbc) it.next());
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        arrayList.addAll(zzbbo.zza());
        return arrayList;
    }

    public final List zzb() {
        List listZza = zza();
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            String str = (String) zzba.zzc().zzb((zzbbc) it.next());
            if (!TextUtils.isEmpty(str)) {
                ((ArrayList) listZza).add(str);
            }
        }
        ((ArrayList) listZza).addAll(zzbbo.zzb());
        return listZza;
    }

    public final void zzc(zzbbc zzbbcVar) {
        this.zzb.add(zzbbcVar);
    }

    public final void zzd(zzbbc zzbbcVar) {
        this.zza.add(zzbbcVar);
    }

    public final void zze(SharedPreferences.Editor editor, int i, JSONObject jSONObject) {
        for (zzbbc zzbbcVar : this.zza) {
            if (zzbbcVar.zza == 1) {
                zzbbcVar.zzd(editor, zzbbcVar.zza(jSONObject));
            }
        }
        if (jSONObject != null) {
            editor.putString("flag_configuration", jSONObject.toString());
        } else {
            zzbzt.zzg("Flag Json is null.");
        }
    }
}
