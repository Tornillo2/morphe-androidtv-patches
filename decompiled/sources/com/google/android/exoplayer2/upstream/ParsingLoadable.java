package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ParsingLoadable<T> implements Loader.Loadable {
    public final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long loadTaskId;
    public final Parser<? extends T> parser;

    @Nullable
    public volatile T result;
    public final int type;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Parser<T> {
        T parse(Uri uri, InputStream inputStream) throws IOException;
    }

    public ParsingLoadable(DataSource dataSource, Uri uri, int i, Parser<? extends T> parser) {
        DataSpec.Builder builder = new DataSpec.Builder();
        builder.uri = uri;
        builder.flags = 1;
        this(dataSource, builder.build(), i, parser);
    }

    public static <T> T load(DataSource dataSource, Parser<? extends T> parser, Uri uri, int i) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, i, parser);
        parsingLoadable.load();
        T t = parsingLoadable.result;
        t.getClass();
        return t;
    }

    public long bytesLoaded() {
        return this.dataSource.bytesRead;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.lastResponseHeaders;
    }

    @Nullable
    public final T getResult() {
        return this.result;
    }

    public Uri getUri() {
        return this.dataSource.lastOpenedUri;
    }

    public static <T> T load(DataSource dataSource, Parser<? extends T> parser, DataSpec dataSpec, int i) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, dataSpec, i, parser);
        parsingLoadable.load();
        T t = parsingLoadable.result;
        t.getClass();
        return t;
    }

    public ParsingLoadable(DataSource dataSource, DataSpec dataSpec, int i, Parser<? extends T> parser) {
        this.dataSource = new StatsDataSource(dataSource);
        this.dataSpec = dataSpec;
        this.type = i;
        this.parser = parser;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void load() throws IOException {
        this.dataSource.bytesRead = 0L;
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            dataSourceInputStream.checkOpened();
            Uri uri = this.dataSource.dataSource.getUri();
            uri.getClass();
            this.result = this.parser.parse(uri, dataSourceInputStream);
        } finally {
            Util.closeQuietly(dataSourceInputStream);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void cancelLoad() {
    }
}
