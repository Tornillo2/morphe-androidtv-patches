package kotlinx.serialization.internal;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCollectionDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionDescriptors.kt\nkotlinx/serialization/internal/MapLikeDescriptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,138:1\n1#2:139\n*E\n"})
public abstract class MapLikeDescriptor implements SerialDescriptor {
    public final int elementsCount;

    @NotNull
    public final SerialDescriptor keyDescriptor;

    @NotNull
    public final String serialName;

    @NotNull
    public final SerialDescriptor valueDescriptor;

    public /* synthetic */ MapLikeDescriptor(String str, SerialDescriptor serialDescriptor, SerialDescriptor serialDescriptor2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, serialDescriptor, serialDescriptor2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MapLikeDescriptor)) {
            return false;
        }
        MapLikeDescriptor mapLikeDescriptor = (MapLikeDescriptor) obj;
        return Intrinsics.areEqual(getSerialName(), mapLikeDescriptor.getSerialName()) && Intrinsics.areEqual(this.keyDescriptor, mapLikeDescriptor.keyDescriptor) && Intrinsics.areEqual(this.valueDescriptor, mapLikeDescriptor.valueDescriptor);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List getAnnotations() {
        return EmptyList.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        if (i >= 0) {
            return EmptyList.INSTANCE;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Illegal index ", i, ", ");
        sbM.append(getSerialName());
        sbM.append(" expects only non-negative indices");
        throw new IllegalArgumentException(sbM.toString().toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        if (i < 0) {
            StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Illegal index ", i, ", ");
            sbM.append(getSerialName());
            sbM.append(" expects only non-negative indices");
            throw new IllegalArgumentException(sbM.toString().toString());
        }
        int i2 = i % 2;
        if (i2 == 0) {
            return this.keyDescriptor;
        }
        if (i2 == 1) {
            return this.valueDescriptor;
        }
        throw new IllegalStateException("Unreached");
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(name);
        if (intOrNull != null) {
            return intOrNull.intValue();
        }
        throw new IllegalArgumentException(name.concat(" is not a valid map index"));
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return String.valueOf(i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.elementsCount;
    }

    @NotNull
    public final SerialDescriptor getKeyDescriptor() {
        return this.keyDescriptor;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialKind getKind() {
        return StructureKind.MAP.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    @NotNull
    public final SerialDescriptor getValueDescriptor() {
        return this.valueDescriptor;
    }

    public int hashCode() {
        return this.valueDescriptor.hashCode() + ((this.keyDescriptor.hashCode() + (getSerialName().hashCode() * 31)) * 31);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int i) {
        if (i >= 0) {
            return false;
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Illegal index ", i, ", ");
        sbM.append(getSerialName());
        sbM.append(" expects only non-negative indices");
        throw new IllegalArgumentException(sbM.toString().toString());
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isInline() {
        return false;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isNullable() {
        return false;
    }

    @NotNull
    public String toString() {
        return getSerialName() + '(' + this.keyDescriptor + ", " + this.valueDescriptor + ')';
    }

    public MapLikeDescriptor(String str, SerialDescriptor serialDescriptor, SerialDescriptor serialDescriptor2) {
        this.serialName = str;
        this.keyDescriptor = serialDescriptor;
        this.valueDescriptor = serialDescriptor2;
        this.elementsCount = 2;
    }
}
