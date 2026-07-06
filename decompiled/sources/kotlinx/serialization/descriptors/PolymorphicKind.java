package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public abstract class PolymorphicKind extends SerialKind {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OPEN extends PolymorphicKind {

        @NotNull
        public static final OPEN INSTANCE = new OPEN();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SEALED extends PolymorphicKind {

        @NotNull
        public static final SEALED INSTANCE = new SEALED();
    }

    public PolymorphicKind() {
    }

    public PolymorphicKind(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
