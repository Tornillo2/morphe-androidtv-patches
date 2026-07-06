package kotlin.uuid;

import java.security.SecureRandom;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SecureRandomHolder {

    @NotNull
    public static final SecureRandomHolder INSTANCE = new SecureRandomHolder();

    @NotNull
    public static final SecureRandom instance = new SecureRandom();

    @NotNull
    public final SecureRandom getInstance() {
        return instance;
    }
}
