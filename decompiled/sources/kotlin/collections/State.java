package kotlin.collections;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class State {
    public static final int DONE = 2;
    public static final int FAILED = 3;

    @NotNull
    public static final State INSTANCE = new State();
    public static final int NOT_READY = 0;
    public static final int READY = 1;
}
