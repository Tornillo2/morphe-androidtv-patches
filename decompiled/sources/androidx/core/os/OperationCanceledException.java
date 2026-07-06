package androidx.core.os;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        super("The operation has been canceled.");
    }

    public OperationCanceledException(@Nullable String str) {
        super(str != null ? str.toString() : "The operation has been canceled.");
    }
}
