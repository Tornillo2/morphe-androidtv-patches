package com.google.android.gms.common.internal;

import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzb extends com.google.android.gms.internal.common.zzi {
    public final /* synthetic */ BaseGmsClient zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzb(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.zza = baseGmsClient;
    }

    public static final void zza(Message message) {
        zzc zzcVar = (zzc) message.obj;
        zzcVar.getClass();
        zzcVar.zzg();
    }

    public static final boolean zzb(Message message) {
        int i = message.what;
        return i == 2 || i == 1 || i == 7;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00f6  */
    @Override // android.os.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleMessage(android.os.Message r8) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzb.handleMessage(android.os.Message):void");
    }
}
