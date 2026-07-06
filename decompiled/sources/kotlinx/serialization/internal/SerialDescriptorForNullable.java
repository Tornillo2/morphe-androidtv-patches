package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SerialDescriptorForNullable implements SerialDescriptor, CachedNames {

    @NotNull
    public final SerialDescriptor original;

    @NotNull
    public final String serialName;

    @NotNull
    public final Set<String> serialNames;

    public SerialDescriptorForNullable(@NotNull SerialDescriptor original) {
        Intrinsics.checkNotNullParameter(original, "original");
        this.original = original;
        this.serialName = original.getSerialName() + '?';
        this.serialNames = Platform_commonKt.cachedSerialNames(original);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SerialDescriptorForNullable) && Intrinsics.areEqual(this.original, ((SerialDescriptorForNullable) obj).original);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return this.original.getAnnotations();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        return this.original.getElementAnnotations(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        return this.original.getElementDescriptor(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.original.getElementIndex(name);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return this.original.getElementName(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.original.getElementsCount();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialKind getKind() {
        return this.original.getKind();
    }

    @NotNull
    public final SerialDescriptor getOriginal$kotlinx_serialization_core() {
        return this.original;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    @NotNull
    public Set<String> getSerialNames() {
        return this.serialNames;
    }

    public int hashCode() {
        return this.original.hashCode() * 31;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.original.isElementOptional(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isInline() {
        return this.original.isInline();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isNullable() {
        return true;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.original);
        sb.append('?');
        return sb.toString();
    }
}
