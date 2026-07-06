package kotlin.random;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {

    @NotNull
    public final FallbackThreadLocalRandom$implStorage$1 implStorage = new FallbackThreadLocalRandom$implStorage$1();

    @Override // kotlin.random.AbstractPlatformRandom
    @NotNull
    public java.util.Random getImpl() {
        java.util.Random random = this.implStorage.get();
        Intrinsics.checkNotNullExpressionValue(random, "get(...)");
        return random;
    }
}
