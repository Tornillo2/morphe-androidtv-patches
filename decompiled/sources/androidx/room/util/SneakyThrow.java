package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SneakyThrow {
    public static void reThrow(@NonNull Exception exc) throws Exception {
        throw exc;
    }

    public static <E extends Throwable> void sneakyThrow(@NonNull Throwable th) throws Throwable {
        throw th;
    }
}
