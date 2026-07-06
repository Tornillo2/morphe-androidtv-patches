package kotlinx.serialization.internal;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
public abstract class NamedValueDecoder extends TaggedDecoder<String> {
    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        if (parentName.length() == 0) {
            return childName;
        }
        return parentName + '.' + childName;
    }

    @NotNull
    public String elementName(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return descriptor.getElementName(i);
    }

    @NotNull
    public final String nested(@NotNull String nestedName) {
        Intrinsics.checkNotNullParameter(nestedName, "nestedName");
        String str = (String) CollectionsKt___CollectionsKt.lastOrNull(this.tagStack);
        if (str == null) {
            str = "";
        }
        return composeName(str, nestedName);
    }

    @NotNull
    public final String renderTagStack() {
        return this.tagStack.isEmpty() ? "$" : CollectionsKt___CollectionsKt.joinToString$default(this.tagStack, ExternalFourCCMapper.CODEC_NAME_SPLITTER, "$.", null, 0, null, null, 60, null);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    @NotNull
    public final String getTag(@NotNull SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return nested(elementName(serialDescriptor, i));
    }
}
