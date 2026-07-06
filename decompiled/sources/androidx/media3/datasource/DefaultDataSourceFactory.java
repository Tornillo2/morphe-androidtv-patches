package androidx.media3.datasource;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
@Deprecated
public final class DefaultDataSourceFactory implements DataSource.Factory {
    public final DataSource.Factory baseDataSourceFactory;
    public final Context context;

    @Nullable
    public final TransferListener listener;

    public DefaultDataSourceFactory(Context context) {
        this(context, (String) null, (TransferListener) null);
    }

    public DefaultDataSourceFactory(Context context, @Nullable String str) {
        this(context, str, (TransferListener) null);
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public DefaultDataSource createDataSource() {
        DefaultDataSource defaultDataSource = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
        TransferListener transferListener = this.listener;
        if (transferListener != null) {
            defaultDataSource.addTransferListener(transferListener);
        }
        return defaultDataSource;
    }

    public DefaultDataSourceFactory(Context context, @Nullable String str, @Nullable TransferListener transferListener) {
        DefaultHttpDataSource.Factory factory = new DefaultHttpDataSource.Factory();
        factory.userAgent = str;
        this(context, transferListener, factory);
    }

    public DefaultDataSourceFactory(Context context, DataSource.Factory factory) {
        this(context, (TransferListener) null, factory);
    }

    public DefaultDataSourceFactory(Context context, @Nullable TransferListener transferListener, DataSource.Factory factory) {
        this.context = context.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
    }
}
