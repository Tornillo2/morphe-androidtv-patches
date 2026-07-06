package androidx.media3.datasource;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class DataSourceBitmapLoader$$ExternalSyntheticLambda0 implements Supplier {
    @Override // com.google.common.base.Supplier
    public final Object get() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }
}
