package kotlin.ranges;

import java.lang.Comparable;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.9")
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
public interface OpenEndRange<T extends Comparable<? super T>> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(@NotNull OpenEndRange<T> openEndRange, @NotNull T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return value.compareTo(openEndRange.getStart()) >= 0 && value.compareTo(openEndRange.getEndExclusive()) < 0;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(@NotNull OpenEndRange<T> openEndRange) {
            return openEndRange.getStart().compareTo(openEndRange.getEndExclusive()) >= 0;
        }
    }

    boolean contains(@NotNull T t);

    @NotNull
    T getEndExclusive();

    @NotNull
    T getStart();

    boolean isEmpty();
}
