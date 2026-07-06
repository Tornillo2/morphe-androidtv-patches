package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.SubclassOptInRequired;
import kotlin.collections.EmptyList;
import kotlinx.serialization.SealedSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SubclassOptInRequired(markerClass = {SealedSerializationApi.class})
public interface SerialDescriptor {

    /* JADX INFO: renamed from: kotlinx.serialization.descriptors.SerialDescriptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static boolean $default$isInline(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static boolean $default$isNullable(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static /* synthetic */ boolean access$isInline$jd(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static /* synthetic */ boolean access$isNullable$jd(SerialDescriptor serialDescriptor) {
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static List<Annotation> getAnnotations(@NotNull SerialDescriptor serialDescriptor) {
            return EmptyList.INSTANCE;
        }

        @Deprecated
        public static boolean isInline(@NotNull SerialDescriptor serialDescriptor) {
            return false;
        }

        @Deprecated
        public static boolean isNullable(@NotNull SerialDescriptor serialDescriptor) {
            return false;
        }
    }

    @NotNull
    List<Annotation> getAnnotations();

    @NotNull
    List<Annotation> getElementAnnotations(int i);

    @NotNull
    SerialDescriptor getElementDescriptor(int i);

    int getElementIndex(@NotNull String str);

    @NotNull
    String getElementName(int i);

    int getElementsCount();

    @NotNull
    SerialKind getKind();

    @NotNull
    String getSerialName();

    boolean isElementOptional(int i);

    boolean isInline();

    boolean isNullable();
}
