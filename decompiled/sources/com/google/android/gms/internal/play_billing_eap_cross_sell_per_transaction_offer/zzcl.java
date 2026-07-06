package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci;
import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class zzcl implements PrivilegedExceptionAction {
    @Override // java.security.PrivilegedExceptionAction
    public final Object run() throws IllegalAccessException {
        int i = zzci.zzd.zzg;
        for (Field field : Unsafe.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (Unsafe.class.isInstance(obj)) {
                return (Unsafe) Unsafe.class.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }
}
