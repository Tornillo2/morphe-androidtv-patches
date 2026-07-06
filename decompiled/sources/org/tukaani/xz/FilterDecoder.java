package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public interface FilterDecoder extends FilterCoder {
    InputStream getInputStream(InputStream inputStream);

    int getMemoryUsage();
}
