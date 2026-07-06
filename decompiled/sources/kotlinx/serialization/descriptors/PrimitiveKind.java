package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class PrimitiveKind extends SerialKind {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BOOLEAN extends PrimitiveKind {

        @NotNull
        public static final BOOLEAN INSTANCE = new BOOLEAN();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BYTE extends PrimitiveKind {

        @NotNull
        public static final BYTE INSTANCE = new BYTE();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CHAR extends PrimitiveKind {

        @NotNull
        public static final CHAR INSTANCE = new CHAR();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DOUBLE extends PrimitiveKind {

        @NotNull
        public static final DOUBLE INSTANCE = new DOUBLE();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FLOAT extends PrimitiveKind {

        @NotNull
        public static final FLOAT INSTANCE = new FLOAT();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class INT extends PrimitiveKind {

        @NotNull
        public static final INT INSTANCE = new INT();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LONG extends PrimitiveKind {

        @NotNull
        public static final LONG INSTANCE = new LONG();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SHORT extends PrimitiveKind {

        @NotNull
        public static final SHORT INSTANCE = new SHORT();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class STRING extends PrimitiveKind {

        @NotNull
        public static final STRING INSTANCE = new STRING();
    }

    public PrimitiveKind() {
    }

    public PrimitiveKind(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
