package com.google.android.gms.internal.ads;

import com.google.common.base.Throwables;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpq {

    @CheckForNull
    public static final Object zza;

    @CheckForNull
    public static final Method zzb;

    @CheckForNull
    public static final Method zzc;

    static {
        Object objZza = zza();
        zza = objZza;
        zzb = objZza == null ? null : zzb("getStackTraceElement", Throwable.class, Integer.TYPE);
        zzc = objZza != null ? zzc(objZza) : null;
    }

    @CheckForNull
    public static Object zza() {
        try {
            return Class.forName(Throwables.SHARED_SECRETS_CLASSNAME, false, null).getMethod("getJavaLangAccess", null).invoke(null, null);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    public static Method zzb(String str, Class... clsArr) throws ThreadDeath {
        try {
            return Class.forName(Throwables.JAVA_LANG_ACCESS_CLASSNAME, false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    public static Method zzc(Object obj) {
        try {
            Method methodZzb = zzb("getStackTraceDepth", Throwable.class);
            if (methodZzb == null) {
                return null;
            }
            methodZzb.invoke(obj, new Throwable());
            return methodZzb;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }
}
