package com.google.android.gms.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MediationUtils {
    public static final double MIN_HEIGHT_RATIO = 0.7d;
    public static final double MIN_WIDTH_RATIO = 0.5d;

    /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00af A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.ads.AdSize findClosestSize(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.NonNull com.google.android.gms.ads.AdSize r11, @androidx.annotation.NonNull java.util.List<com.google.android.gms.ads.AdSize> r12) {
        /*
            r0 = 0
            if (r12 == 0) goto Lb1
            if (r11 != 0) goto L7
            goto Lb1
        L7:
            boolean r1 = r11.zzf
            if (r1 != 0) goto L32
            boolean r1 = r11.zzh
            if (r1 != 0) goto L32
            android.content.res.Resources r1 = r10.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.density
            int r2 = r11.getWidthInPixels(r10)
            float r2 = (float) r2
            float r2 = r2 / r1
            int r2 = java.lang.Math.round(r2)
            int r10 = r11.getHeightInPixels(r10)
            float r10 = (float) r10
            float r10 = r10 / r1
            int r10 = java.lang.Math.round(r10)
            com.google.android.gms.ads.AdSize r11 = new com.google.android.gms.ads.AdSize
            r11.<init>(r2, r10)
        L32:
            java.util.Iterator r10 = r12.iterator()
        L36:
            boolean r12 = r10.hasNext()
            if (r12 == 0) goto Lb1
            java.lang.Object r12 = r10.next()
            com.google.android.gms.ads.AdSize r12 = (com.google.android.gms.ads.AdSize) r12
            if (r12 == 0) goto L36
            int r1 = r11.zzb
            int r2 = r12.zzb
            int r3 = r11.zzc
            double r4 = (double) r1
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r4 = r4 * r6
            double r6 = (double) r2
            int r8 = r12.zzc
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 > 0) goto L36
            if (r1 < r2) goto L36
            boolean r1 = r11.zzh
            if (r1 == 0) goto L85
            int r1 = r11.zzi
            com.google.android.gms.internal.ads.zzbbc r3 = com.google.android.gms.internal.ads.zzbbk.zzhs
            com.google.android.gms.internal.ads.zzbbi r4 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r3 = r4.zzb(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 > r2) goto L36
            com.google.android.gms.internal.ads.zzbbc r2 = com.google.android.gms.internal.ads.zzbbk.zzht
            com.google.android.gms.ads.internal.client.zzba r3 = com.google.android.gms.ads.internal.client.zzba.zza
            com.google.android.gms.internal.ads.zzbbi r3 = r3.zzd
            java.lang.Object r2 = r3.zzb(r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r2 > r8) goto L36
            if (r1 < r8) goto L36
            goto L9e
        L85:
            boolean r1 = r11.zzf
            if (r1 == 0) goto L8e
            int r1 = r11.zzg
            if (r1 < r8) goto L36
            goto L9e
        L8e:
            double r1 = (double) r3
            r4 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r1 = r1 * r4
            double r4 = (double) r8
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 > 0) goto L36
            if (r3 >= r8) goto L9e
            goto L36
        L9e:
            if (r0 != 0) goto La1
            goto Laf
        La1:
            int r1 = r0.zzb
            int r2 = r0.zzc
            int r1 = r1 * r2
            int r2 = r12.zzb
            int r3 = r12.zzc
            int r2 = r2 * r3
            if (r1 > r2) goto L36
        Laf:
            r0 = r12
            goto L36
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.MediationUtils.findClosestSize(android.content.Context, com.google.android.gms.ads.AdSize, java.util.List):com.google.android.gms.ads.AdSize");
    }
}
