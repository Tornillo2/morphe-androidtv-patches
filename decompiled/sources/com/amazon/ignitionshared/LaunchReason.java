package com.amazon.ignitionshared;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum LaunchReason {
    LAUNCHER(0),
    HOT_KEY(1),
    PRELOAD(2),
    DEEP_LINKING(3);

    public final int code;

    LaunchReason(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
