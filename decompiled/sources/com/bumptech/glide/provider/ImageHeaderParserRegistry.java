package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ImageHeaderParserRegistry {
    public final List<ImageHeaderParser> parsers = new ArrayList();

    public synchronized void add(@NonNull ImageHeaderParser imageHeaderParser) {
        this.parsers.add(imageHeaderParser);
    }

    @NonNull
    public synchronized List<ImageHeaderParser> getParsers() {
        return this.parsers;
    }
}
