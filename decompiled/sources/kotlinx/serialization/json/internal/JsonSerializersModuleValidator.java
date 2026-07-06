package kotlinx.serialization.json.internal;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.modules.SerializersModuleCollector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonSerializersModuleValidator implements SerializersModuleCollector {

    @NotNull
    public final String discriminator;
    public final boolean isDiscriminatorRequired;
    public final boolean useArrayPolymorphism;

    public JsonSerializersModuleValidator(@NotNull JsonConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.discriminator = configuration.classDiscriminator;
        this.useArrayPolymorphism = configuration.useArrayPolymorphism;
        this.isDiscriminatorRequired = configuration.classDiscriminatorMode != ClassDiscriminatorMode.NONE;
    }

    public final void checkDiscriminatorCollisions(SerialDescriptor serialDescriptor, KClass<?> kClass) {
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String elementName = serialDescriptor.getElementName(i);
            if (Intrinsics.areEqual(elementName, this.discriminator)) {
                throw new IllegalArgumentException("Polymorphic serializer for " + kClass + " has property '" + elementName + "' that conflicts with JSON class discriminator. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            }
        }
    }

    public final void checkKind(SerialDescriptor serialDescriptor, KClass<?> kClass) {
        SerialKind kind = serialDescriptor.getKind();
        if ((kind instanceof PolymorphicKind) || Intrinsics.areEqual(kind, SerialKind.CONTEXTUAL.INSTANCE)) {
            throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " can't be registered as a subclass for polymorphic serialization because its kind " + kind + " is not concrete. To work with multiple hierarchies, register it as a base class.");
        }
        if (!this.useArrayPolymorphism && this.isDiscriminatorRequired) {
            if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE) || (kind instanceof PrimitiveKind) || (kind instanceof SerialKind.ENUM)) {
                throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " of kind " + kind + " cannot be serialized polymorphically with class discriminator.");
            }
        }
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <T> void contextual(@NotNull KClass<T> kClass, @NotNull Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(provider, "provider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> actualClass, @NotNull KSerializer<Sub> actualSerializer) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(actualClass, "actualClass");
        Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
        SerialDescriptor descriptor = actualSerializer.getDescriptor();
        checkKind(descriptor, actualClass);
        if (this.useArrayPolymorphism || !this.isDiscriminatorRequired) {
            return;
        }
        checkDiscriminatorCollisions(descriptor, actualClass);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public /* synthetic */ void polymorphicDefault(KClass kClass, Function1 function1) {
        SerializersModuleCollector.CC.$default$polymorphicDefault(this, kClass, function1);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultDeserializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultSerializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public /* synthetic */ void contextual(KClass kClass, KSerializer kSerializer) {
        SerializersModuleCollector.CC.$default$contextual(this, kClass, kSerializer);
    }
}
