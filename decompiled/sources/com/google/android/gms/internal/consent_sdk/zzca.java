package com.google.android.gms.internal.consent_sdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzca {
    @Nullable
    public static zzby zza(Context context, String str) {
        String strConcat;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(SchemaId.METRIC_SCHEMA_ID_DELIMITER, -1);
        int length = strArrSplit.length;
        if (length != 1) {
            if (length == 2) {
                strConcat = strArrSplit[0];
                str2 = strArrSplit[1];
            }
            return null;
        }
        strConcat = String.valueOf(context.getPackageName()).concat("_preferences");
        str2 = strArrSplit[0];
        if (!TextUtils.isEmpty(strConcat) && !TextUtils.isEmpty(str2)) {
            return new zzby(strConcat, str2);
        }
        return null;
    }

    public static void zzb(Context context, Set<String> set) {
        zzbz zzbzVar = new zzbz(context);
        for (String str : set) {
            zzby zzbyVarZza = zza(context, str);
            if (zzbyVarZza == null) {
                String strValueOf = String.valueOf(str);
                Log.d("UserMessagingPlatform", strValueOf.length() != 0 ? "clearKeys: unable to process key: ".concat(strValueOf) : new String("clearKeys: unable to process key: "));
            } else {
                zzbzVar.zzd(zzbyVarZza.zza).remove(zzbyVarZza.zzb);
            }
        }
        zzbzVar.zzb();
    }
}
