package kotlinx.serialization.internal;

import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class AbstractPolymorphicSerializerKt {
    @JvmName(name = "throwSubtypeNotRegistered")
    @NotNull
    public static final Void throwSubtypeNotRegistered(@Nullable String str, @NotNull KClass<?> baseClass) {
        String string;
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        String str2 = "in the polymorphic scope of '" + baseClass.getSimpleName() + '\'';
        if (str == null) {
            string = "Class discriminator was missing and no default serializers were registered " + str2 + '.';
        } else {
            StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Serializer for subclass '", str, "' is not found ", str2, ".\nCheck if class with serial name '");
            sbM.append(str);
            sbM.append("' exists and serializer is registered in a corresponding SerializersModule.\nTo be registered automatically, class '");
            sbM.append(str);
            sbM.append("' has to be '@Serializable', and the base class '");
            sbM.append(baseClass.getSimpleName());
            sbM.append("' has to be sealed and '@Serializable'.");
            string = sbM.toString();
        }
        throw new SerializationException(string);
    }

    @JvmName(name = "throwSubtypeNotRegistered")
    @NotNull
    public static final Void throwSubtypeNotRegistered(@NotNull KClass<?> subClass, @NotNull KClass<?> baseClass) {
        Intrinsics.checkNotNullParameter(subClass, "subClass");
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        String simpleName = subClass.getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(subClass);
        }
        throwSubtypeNotRegistered(simpleName, baseClass);
        throw null;
    }
}
