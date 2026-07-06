package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPersistableBundle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PersistableBundle.kt\nandroidx/core/os/PersistableBundleKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,152:1\n13579#2,2:153\n*S KotlinDebug\n*F\n+ 1 PersistableBundle.kt\nandroidx/core/os/PersistableBundleKt\n*L\n34#1:153,2\n*E\n"})
public final class PersistableBundleKt {
    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle persistableBundleOf(@NotNull Pair<String, ? extends Object>... pairArr) {
        PersistableBundle persistableBundle = new PersistableBundle(pairArr.length);
        for (Pair<String, ? extends Object> pair : pairArr) {
            PersistableBundleApi21ImplKt.putValue(persistableBundle, pair.first, pair.second);
        }
        return persistableBundle;
    }

    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle toPersistableBundle(@NotNull Map<String, ? extends Object> map) {
        PersistableBundle persistableBundle = new PersistableBundle(map.size());
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            PersistableBundleApi21ImplKt.putValue(persistableBundle, entry.getKey(), entry.getValue());
        }
        return persistableBundle;
    }

    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle persistableBundleOf() {
        return new PersistableBundle(0);
    }
}
