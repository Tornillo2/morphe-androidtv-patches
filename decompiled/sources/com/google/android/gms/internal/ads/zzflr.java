package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zzflr {
    ExecutorService zza(int i);

    ExecutorService zzb(ThreadFactory threadFactory, int i);

    ExecutorService zzc(int i, ThreadFactory threadFactory, int i2);
}
