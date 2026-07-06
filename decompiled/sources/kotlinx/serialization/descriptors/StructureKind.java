package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class StructureKind extends SerialKind {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CLASS extends StructureKind {

        @NotNull
        public static final CLASS INSTANCE = new CLASS();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LIST extends StructureKind {

        @NotNull
        public static final LIST INSTANCE = new LIST();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MAP extends StructureKind {

        @NotNull
        public static final MAP INSTANCE = new MAP();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OBJECT extends StructureKind {

        @NotNull
        public static final OBJECT INSTANCE = new OBJECT();
    }

    public StructureKind() {
    }

    public StructureKind(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
