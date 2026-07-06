package com.bumptech.glide.load.data.mediastore;

import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class FileService {
    public boolean exists(File file) {
        return file.exists();
    }

    public File get(String str) {
        return new File(str);
    }

    public long length(File file) {
        return file.length();
    }
}
