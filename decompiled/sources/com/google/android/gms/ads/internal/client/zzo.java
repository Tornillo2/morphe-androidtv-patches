package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.RequestConfiguration;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zzo implements Comparator {
    public static final /* synthetic */ zzo zza = new zzo();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        List list = RequestConfiguration.zza;
        return list.indexOf((String) obj) - list.indexOf((String) obj2);
    }
}
