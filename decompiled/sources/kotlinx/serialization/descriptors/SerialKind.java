package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class SerialKind {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CONTEXTUAL extends SerialKind {

        @NotNull
        public static final CONTEXTUAL INSTANCE = new CONTEXTUAL();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ENUM extends SerialKind {

        @NotNull
        public static final ENUM INSTANCE = new ENUM();
    }

    public SerialKind() {
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @NotNull
    public String toString() {
        String simpleName = ((ClassReference) Reflection.getOrCreateKotlinClass(getClass())).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        return simpleName;
    }

    public SerialKind(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
