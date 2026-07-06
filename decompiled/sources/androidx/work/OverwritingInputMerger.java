package androidx.work;

import androidx.annotation.NonNull;
import androidx.work.Data;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class OverwritingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    @NonNull
    public Data merge(@NonNull List<Data> inputs) {
        Data.Builder builder = new Data.Builder();
        HashMap map = new HashMap();
        Iterator<Data> it = inputs.iterator();
        while (it.hasNext()) {
            map.putAll(DesugarCollections.unmodifiableMap(it.next().mValues));
        }
        builder.putAll(map);
        return builder.build();
    }
}
