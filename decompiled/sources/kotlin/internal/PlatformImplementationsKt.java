package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.jdk8.JDK8PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PlatformImplementationsKt {

    @JvmField
    @NotNull
    public static final PlatformImplementations IMPLEMENTATIONS = new JDK8PlatformImplementations();

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int i, int i2, int i3) {
        return KotlinVersion.CURRENT.isAtLeast(i, i2, i3);
    }

    @InlineOnly
    public static final <T> T castToBaseType(Object obj) {
        try {
            Intrinsics.throwUndefinedForReified();
            throw null;
        } catch (ClassCastException unused) {
            obj.getClass().getClassLoader();
            Intrinsics.throwUndefinedForReified();
            throw null;
        }
    }
}
