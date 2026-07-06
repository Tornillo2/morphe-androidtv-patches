package kotlinx.serialization.internal;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import java.util.Map;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text._OneToManyTitlecaseMappingsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nPrimitives.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Primitives.kt\nkotlinx/serialization/internal/PrimitivesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"})
public final class PrimitivesKt {

    @NotNull
    public static final Map<KClass<?>, KSerializer<?>> BUILTIN_SERIALIZERS = PlatformKt.initBuiltins();

    @NotNull
    public static final SerialDescriptor PrimitiveDescriptorSafe(@NotNull String serialName, @NotNull PrimitiveKind kind) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        checkNameIsNotAPrimitive(serialName);
        return new PrimitiveSerialDescriptor(serialName, kind);
    }

    @Nullable
    public static final <T> KSerializer<T> builtinSerializerOrNull(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return (KSerializer) BUILTIN_SERIALIZERS.get(kClass);
    }

    public static final String capitalize(String str) {
        if (str.length() <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char cCharAt = str.charAt(0);
        sb.append((Object) (Character.isLowerCase(cCharAt) ? _OneToManyTitlecaseMappingsKt.titlecaseImpl(cCharAt) : String.valueOf(cCharAt)));
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        sb.append(strSubstring);
        return sb.toString();
    }

    public static final void checkNameIsNotAPrimitive(@NotNull String serialName) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        for (KSerializer<?> kSerializer : BUILTIN_SERIALIZERS.values()) {
            if (serialName.equals(kSerializer.getDescriptor().getSerialName())) {
                StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("\n                The name of serial descriptor should uniquely identify associated serializer.\n                For serial name ", serialName, " there already exists ");
                sbM.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(kSerializer.getClass())).getSimpleName());
                sbM.append(".\n                Please refer to SerialDescriptor documentation for additional information.\n            ");
                throw new IllegalArgumentException(StringsKt__IndentKt.trimIndent(sbM.toString()));
            }
        }
    }
}
