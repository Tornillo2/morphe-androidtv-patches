package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzjt {
    public static final zzjt zzb = new zzjt(true);
    public final zzmh zza = new zzlx(16, null);
    public boolean zzc;
    public boolean zzd;

    public zzjt() {
    }

    public static zzjt zza() {
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0043 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void zzd(com.google.android.gms.internal.measurement.zzjs r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.measurement.zznb r0 = r4.zzb()
            com.google.android.gms.internal.measurement.zzkk.zze(r5)
            com.google.android.gms.internal.measurement.zznb r1 = com.google.android.gms.internal.measurement.zznb.zza
            com.google.android.gms.internal.measurement.zznc r1 = com.google.android.gms.internal.measurement.zznc.INT
            com.google.android.gms.internal.measurement.zznc r0 = r0.zzt
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L3f;
                case 1: goto L3c;
                case 2: goto L39;
                case 3: goto L36;
                case 4: goto L33;
                case 5: goto L30;
                case 6: goto L27;
                case 7: goto L1e;
                case 8: goto L15;
                default: goto L14;
            }
        L14:
            goto L44
        L15:
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzlj
            if (r0 != 0) goto L1d
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzko
            if (r0 == 0) goto L44
        L1d:
            return
        L1e:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L26
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzke
            if (r0 == 0) goto L44
        L26:
            return
        L27:
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzjb
            if (r0 != 0) goto L2f
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L44
        L2f:
            return
        L30:
            boolean r0 = r5 instanceof java.lang.String
            goto L41
        L33:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L41
        L36:
            boolean r0 = r5 instanceof java.lang.Double
            goto L41
        L39:
            boolean r0 = r5 instanceof java.lang.Float
            goto L41
        L3c:
            boolean r0 = r5 instanceof java.lang.Long
            goto L41
        L3f:
            boolean r0 = r5 instanceof java.lang.Integer
        L41:
            if (r0 == 0) goto L44
            return
        L44:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r4.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.gms.internal.measurement.zznb r4 = r4.zzb()
            com.google.android.gms.internal.measurement.zznc r4 = r4.zzt
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r1
            r1 = 1
            r2[r1] = r4
            r4 = 2
            r2[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r2)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzd(com.google.android.gms.internal.measurement.zzjs, java.lang.Object):void");
    }

    public final Object clone() throws CloneNotSupportedException {
        zzjt zzjtVar = new zzjt();
        for (int i = 0; i < this.zza.zzb.size(); i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            zzjtVar.zzc((zzjs) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzjtVar.zzc((zzjs) entry.getKey(), entry.getValue());
        }
        zzjtVar.zzd = this.zzd;
        return zzjtVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzjt) {
            return this.zza.equals(((zzjt) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzjs zzjsVar, Object obj) {
        if (!zzjsVar.zzc()) {
            zzd(zzjsVar, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzjsVar, arrayList.get(i));
            }
            obj = arrayList;
        }
        if (obj instanceof zzko) {
            this.zzd = true;
        }
        this.zza.put(zzjsVar, obj);
    }

    public zzjt(boolean z) {
        zzb();
        zzb();
    }
}
