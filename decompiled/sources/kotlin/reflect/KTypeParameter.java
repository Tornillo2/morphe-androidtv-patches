package kotlin.reflect;

import java.util.List;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
public interface KTypeParameter extends KClassifier {
    @NotNull
    String getName();

    @NotNull
    List<KType> getUpperBounds();

    @NotNull
    KVariance getVariance();

    boolean isReified();
}
