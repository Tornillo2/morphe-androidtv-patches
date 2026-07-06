package com.google.android.gms.ads.mediation;

import android.location.Location;
import androidx.annotation.NonNull;
import java.util.Date;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface MediationAdRequest {
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;

    @NonNull
    @Deprecated
    Date getBirthday();

    @Deprecated
    int getGender();

    @NonNull
    Set<String> getKeywords();

    @NonNull
    Location getLocation();

    @Deprecated
    boolean isDesignedForFamilies();

    boolean isTesting();

    int taggedForChildDirectedTreatment();
}
