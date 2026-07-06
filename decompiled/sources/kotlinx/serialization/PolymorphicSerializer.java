package kotlinx.serialization;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class PolymorphicSerializer<T> extends AbstractPolymorphicSerializer<T> {

    @NotNull
    public List<? extends Annotation> _annotations;

    @NotNull
    public final KClass<T> baseClass;

    @NotNull
    public final Lazy descriptor$delegate;

    public PolymorphicSerializer(@NotNull KClass<T> baseClass) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        this.baseClass = baseClass;
        this._annotations = EmptyList.INSTANCE;
        this.descriptor$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.serialization.PolymorphicSerializer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PolymorphicSerializer.descriptor_delegate$lambda$1(this.f$0);
            }
        });
    }

    public static final SerialDescriptor descriptor_delegate$lambda$1(final PolymorphicSerializer polymorphicSerializer) {
        return ContextAwareKt.withContext(SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.Polymorphic", PolymorphicKind.OPEN.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.PolymorphicSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolymorphicSerializer.descriptor_delegate$lambda$1$lambda$0(this.f$0, (ClassSerialDescriptorBuilder) obj);
            }
        }), polymorphicSerializer.baseClass);
    }

    public static final Unit descriptor_delegate$lambda$1$lambda$0(PolymorphicSerializer polymorphicSerializer, ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        ((StringSerializer) BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)).getClass();
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "type", StringSerializer.descriptor, null, false, 12, null);
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "value", SerialDescriptorsKt.buildSerialDescriptor$default("kotlinx.serialization.Polymorphic<" + polymorphicSerializer.baseClass.getSimpleName() + '>', SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], null, 8, null), null, false, 12, null);
        buildSerialDescriptor.setAnnotations(polymorphicSerializer._annotations);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    @NotNull
    public KClass<T> getBaseClass() {
        return this.baseClass;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor$delegate.getValue();
    }

    @NotNull
    public String toString() {
        return "kotlinx.serialization.PolymorphicSerializer(baseClass: " + this.baseClass + ')';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @PublishedApi
    public PolymorphicSerializer(@NotNull KClass<T> baseClass, @NotNull Annotation[] classAnnotations) {
        this(baseClass);
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(classAnnotations, "classAnnotations");
        this._annotations = ArraysKt___ArraysJvmKt.asList(classAnnotations);
    }
}
