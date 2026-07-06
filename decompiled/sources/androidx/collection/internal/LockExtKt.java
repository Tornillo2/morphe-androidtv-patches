package androidx.collection.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nLockExt.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockExt.kt\nandroidx/collection/internal/LockExtKt\n+ 2 Lock.jvm.kt\nandroidx/collection/internal/Lock\n*L\n1#1,27:1\n26#2:28\n*S KotlinDebug\n*F\n+ 1 LockExt.kt\nandroidx/collection/internal/LockExtKt\n*L\n25#1:28\n*E\n"})
public final class LockExtKt {
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <T> T m30synchronized(@NotNull Lock lock, @NotNull Function0<? extends T> block) {
        T tInvoke;
        Intrinsics.checkNotNullParameter(lock, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (lock) {
            tInvoke = block.invoke();
        }
        return tInvoke;
    }
}
