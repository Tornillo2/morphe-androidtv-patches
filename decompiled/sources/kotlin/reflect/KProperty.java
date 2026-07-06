package kotlin.reflect;

import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface KProperty<V> extends KCallable<V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Accessor<V> {
        @NotNull
        KProperty<V> getProperty();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Getter<V> extends Accessor<V>, KFunction<V> {
    }

    @NotNull
    Getter<V> getGetter();

    boolean isConst();

    boolean isLateinit();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isConst$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isLateinit$annotations() {
        }
    }
}
