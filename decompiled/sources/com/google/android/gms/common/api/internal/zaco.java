package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaco {
    public static final ExecutorService zaa = com.google.android.gms.internal.base.zat.zab.zaa(new NumberedThreadFactory("GAC_Transform"), 1);

    public static ExecutorService zaa() {
        return zaa;
    }
}
