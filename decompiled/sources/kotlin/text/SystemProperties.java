package kotlin.text;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SystemProperties {

    @NotNull
    public static final SystemProperties INSTANCE = new SystemProperties();

    @JvmField
    @NotNull
    public static final String LINE_SEPARATOR;

    static {
        String property = System.getProperty("line.separator");
        Intrinsics.checkNotNull(property);
        LINE_SEPARATOR = property;
    }
}
