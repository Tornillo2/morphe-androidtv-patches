package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.GlideException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zao();

    @SafeParcelable.VersionField(id = 1)
    public final int zaa;
    public final HashMap zab;

    @SafeParcelable.Field(getter = "getRootClassName", id = 3)
    public final String zac;

    @SafeParcelable.Constructor
    public zan(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList arrayList, @SafeParcelable.Param(id = 3) String str) {
        this.zaa = i;
        HashMap map = new HashMap();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zal zalVar = (zal) arrayList.get(i2);
            String str2 = zalVar.zab;
            HashMap map2 = new HashMap();
            ArrayList arrayList2 = zalVar.zac;
            Preconditions.checkNotNull(arrayList2);
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                zam zamVar = (zam) zalVar.zac.get(i3);
                map2.put(zamVar.zab, zamVar.zac);
            }
            map.put(str2, map2);
        }
        this.zab = map;
        Preconditions.checkNotNull(str);
        this.zac = str;
        zad();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zab.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map map = (Map) this.zab.get(str);
            for (String str2 : map.keySet()) {
                sb.append(GlideException.IndentedAppendable.INDENT);
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        int i2 = this.zaa;
        SafeParcelWriter.zzc(parcel, 1, 4);
        parcel.writeInt(i2);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zal(str, (Map) this.zab.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zac, false);
        SafeParcelWriter.zzb(parcel, iZza);
    }

    public final String zaa() {
        return this.zac;
    }

    @Nullable
    public final Map zab(String str) {
        return (Map) this.zab.get(str);
    }

    public final void zac() {
        for (String str : this.zab.keySet()) {
            Map map = (Map) this.zab.get(str);
            HashMap map2 = new HashMap();
            for (String str2 : map.keySet()) {
                map2.put(str2, ((FastJsonResponse.Field) map.get(str2)).zab());
            }
            this.zab.put(str, map2);
        }
    }

    public final void zad() {
        Iterator it = this.zab.keySet().iterator();
        while (it.hasNext()) {
            Map map = (Map) this.zab.get((String) it.next());
            Iterator it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                ((FastJsonResponse.Field) map.get((String) it2.next())).zaj = this;
            }
        }
    }

    public final void zae(Class cls, Map map) {
        String canonicalName = cls.getCanonicalName();
        Preconditions.checkNotNull(canonicalName);
        this.zab.put(canonicalName, map);
    }

    public final boolean zaf(Class cls) {
        String canonicalName = cls.getCanonicalName();
        Preconditions.checkNotNull(canonicalName);
        return this.zab.containsKey(canonicalName);
    }

    public zan(Class cls) {
        this.zaa = 1;
        this.zab = new HashMap();
        String canonicalName = cls.getCanonicalName();
        Preconditions.checkNotNull(canonicalName);
        this.zac = canonicalName;
    }
}
