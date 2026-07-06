package kotlinx.serialization.descriptors;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KType;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializersKt__SerializersKt;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.internal.ArrayListClassDesc;
import kotlinx.serialization.internal.HashMapClassDesc;
import kotlinx.serialization.internal.HashSetClassDesc;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.internal.SerialDescriptorForNullable;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerialDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,380:1\n1#2:381\n*E\n"})
public final class SerialDescriptorsKt {
    @NotNull
    public static final SerialDescriptor PrimitiveSerialDescriptor(@NotNull String serialName, @NotNull PrimitiveKind kind) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (StringsKt__StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        return PrimitivesKt.PrimitiveDescriptorSafe(serialName, kind);
    }

    @NotNull
    public static final SerialDescriptor SerialDescriptor(@NotNull String serialName, @NotNull SerialDescriptor original) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(original, "original");
        if (StringsKt__StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        if (!serialName.equals(original.getSerialName())) {
            if (original.getKind() instanceof PrimitiveKind) {
                PrimitivesKt.checkNameIsNotAPrimitive(serialName);
            }
            return new WrappedSerialDescriptor(serialName, original);
        }
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("The name of the wrapped descriptor (", serialName, ") cannot be the same as the name of the original descriptor (");
        sbM.append(original.getSerialName());
        sbM.append(')');
        throw new IllegalArgumentException(sbM.toString().toString());
    }

    @NotNull
    public static final SerialDescriptor buildClassSerialDescriptor(@NotNull String serialName, @NotNull SerialDescriptor[] typeParameters, @NotNull Function1<? super ClassSerialDescriptorBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        if (StringsKt__StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
        builderAction.invoke(classSerialDescriptorBuilder);
        return new SerialDescriptorImpl(serialName, StructureKind.CLASS.INSTANCE, classSerialDescriptorBuilder.elementNames.size(), ArraysKt___ArraysKt.toList(typeParameters), classSerialDescriptorBuilder);
    }

    public static /* synthetic */ SerialDescriptor buildClassSerialDescriptor$default(String str, SerialDescriptor[] serialDescriptorArr, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new SerialDescriptorsKt$$ExternalSyntheticLambda0();
        }
        return buildClassSerialDescriptor(str, serialDescriptorArr, function1);
    }

    public static final Unit buildClassSerialDescriptor$lambda$0(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "<this>");
        return Unit.INSTANCE;
    }

    @InternalSerializationApi
    @NotNull
    public static final SerialDescriptor buildSerialDescriptor(@NotNull String serialName, @NotNull SerialKind kind, @NotNull SerialDescriptor[] typeParameters, @NotNull Function1<? super ClassSerialDescriptorBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (StringsKt__StringsKt.isBlank(serialName)) {
            throw new IllegalArgumentException("Blank serial names are prohibited");
        }
        if (kind.equals(StructureKind.CLASS.INSTANCE)) {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead");
        }
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(serialName);
        builder.invoke(classSerialDescriptorBuilder);
        return new SerialDescriptorImpl(serialName, kind, classSerialDescriptorBuilder.elementNames.size(), ArraysKt___ArraysKt.toList(typeParameters), classSerialDescriptorBuilder);
    }

    public static /* synthetic */ SerialDescriptor buildSerialDescriptor$default(String str, SerialKind serialKind, SerialDescriptor[] serialDescriptorArr, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new SerialDescriptorsKt$$ExternalSyntheticLambda1();
        }
        return buildSerialDescriptor(str, serialKind, serialDescriptorArr, function1);
    }

    public static final Unit buildSerialDescriptor$lambda$5(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "<this>");
        return Unit.INSTANCE;
    }

    public static final <T> void element(ClassSerialDescriptorBuilder classSerialDescriptorBuilder, String elementName, List<? extends Annotation> annotations, boolean z) {
        Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "<this>");
        Intrinsics.checkNotNullParameter(elementName, "elementName");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static void element$default(ClassSerialDescriptorBuilder classSerialDescriptorBuilder, String elementName, List annotations, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = EmptyList.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(classSerialDescriptorBuilder, "<this>");
        Intrinsics.checkNotNullParameter(elementName, "elementName");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final SerialDescriptor getNonNullOriginal(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return serialDescriptor instanceof SerialDescriptorForNullable ? ((SerialDescriptorForNullable) serialDescriptor).original : serialDescriptor;
    }

    @NotNull
    public static final SerialDescriptor getNullable(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return serialDescriptor.isNullable() ? serialDescriptor : new SerialDescriptorForNullable(serialDescriptor);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final SerialDescriptor listSerialDescriptor(@NotNull SerialDescriptor elementDescriptor) {
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        return new ArrayListClassDesc(elementDescriptor);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final SerialDescriptor mapSerialDescriptor(@NotNull SerialDescriptor keyDescriptor, @NotNull SerialDescriptor valueDescriptor) {
        Intrinsics.checkNotNullParameter(keyDescriptor, "keyDescriptor");
        Intrinsics.checkNotNullParameter(valueDescriptor, "valueDescriptor");
        return new HashMapClassDesc(keyDescriptor, valueDescriptor);
    }

    @NotNull
    public static final SerialDescriptor serialDescriptor(@NotNull KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt__SerializersKt.serializer(type).getDescriptor();
    }

    @ExperimentalSerializationApi
    public static final <T> SerialDescriptor setSerialDescriptor() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalSerializationApi
    public static final <K, V> SerialDescriptor mapSerialDescriptor() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final SerialDescriptor setSerialDescriptor(@NotNull SerialDescriptor elementDescriptor) {
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        return new HashSetClassDesc(elementDescriptor);
    }

    @ExperimentalSerializationApi
    public static final <T> SerialDescriptor listSerialDescriptor() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> SerialDescriptor serialDescriptor() {
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getNonNullOriginal$annotations(SerialDescriptor serialDescriptor) {
    }

    public static /* synthetic */ void getNullable$annotations(SerialDescriptor serialDescriptor) {
    }
}
