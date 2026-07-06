package androidx.work;

import androidx.work.Data;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DataKt {
    public static final <T> boolean hasKeyWithValueOfType(Data data, String key) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final Data workDataOf(@NotNull Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Data.Builder builder = new Data.Builder();
        int length = pairs.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair = pairs[i];
            i++;
            builder.put(pair.first, pair.second);
        }
        return builder.build();
    }
}
