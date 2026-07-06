package com.google.android.exoplayer2.offline;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.offline.FilterableManifest;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FilteringManifestParser<T extends FilterableManifest<T>> implements ParsingLoadable.Parser<T> {
    public final ParsingLoadable.Parser<? extends T> parser;

    @Nullable
    public final List<StreamKey> streamKeys;

    public FilteringManifestParser(ParsingLoadable.Parser<? extends T> parser, @Nullable List<StreamKey> list) {
        this.parser = parser;
        this.streamKeys = list;
    }

    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public T parse(Uri uri, InputStream inputStream) throws IOException {
        T t = this.parser.parse(uri, inputStream);
        List<StreamKey> list = this.streamKeys;
        return (list == null || list.isEmpty()) ? t : (T) t.copy(this.streamKeys);
    }
}
