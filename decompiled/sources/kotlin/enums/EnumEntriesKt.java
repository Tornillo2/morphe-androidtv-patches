package kotlin.enums;

import kotlin.ExperimentalStdlibApi;
import kotlin.NotImplementedError;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class EnumEntriesKt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "2.0")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final /* synthetic */ <T extends Enum<T>> EnumEntries<T> enumEntries() {
        throw new NotImplementedError(null, 1, 0 == true ? 1 : 0);
    }

    @SinceKotlin(version = "1.8")
    @PublishedApi
    @NotNull
    public static final <E extends Enum<E>> EnumEntries<E> enumEntries(@NotNull Function0<E[]> entriesProvider) {
        Intrinsics.checkNotNullParameter(entriesProvider, "entriesProvider");
        return new EnumEntriesList(entriesProvider.invoke());
    }

    @SinceKotlin(version = "1.8")
    @PublishedApi
    @NotNull
    public static final <E extends Enum<E>> EnumEntries<E> enumEntries(@NotNull E[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        return new EnumEntriesList(entries);
    }
}
