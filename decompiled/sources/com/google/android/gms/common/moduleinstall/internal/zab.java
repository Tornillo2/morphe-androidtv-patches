package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zab implements Comparator {
    public static final /* synthetic */ zab zaa = new zab();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Feature feature = (Feature) obj;
        Feature feature2 = (Feature) obj2;
        Parcelable.Creator<ApiFeatureRequest> creator = ApiFeatureRequest.CREATOR;
        return !feature.getName().equals(feature2.getName()) ? feature.getName().compareTo(feature2.getName()) : (feature.getVersion() > feature2.getVersion() ? 1 : (feature.getVersion() == feature2.getVersion() ? 0 : -1));
    }
}
