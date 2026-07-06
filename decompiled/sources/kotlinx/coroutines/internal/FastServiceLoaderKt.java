package kotlinx.coroutines.internal;

import kotlin.ResultKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class FastServiceLoaderKt {
    public static final boolean ANDROID_DETECTED = false;

    static {
        try {
            Class.forName("android.os.Build");
        } catch (Throwable th) {
            ResultKt.createFailure(th);
        }
    }

    public static final boolean getANDROID_DETECTED() {
        return true;
    }
}
