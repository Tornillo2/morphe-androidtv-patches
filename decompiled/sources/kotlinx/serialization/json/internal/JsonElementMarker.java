package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.ElementMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonElementMarker {
    public boolean isUnmarkedNull;

    @NotNull
    public final ElementMarker origin;

    public JsonElementMarker(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.origin = new ElementMarker(descriptor, new JsonElementMarker$origin$1(this));
    }

    public final boolean isUnmarkedNull$kotlinx_serialization_json() {
        return this.isUnmarkedNull;
    }

    public final void mark$kotlinx_serialization_json(int i) {
        this.origin.mark(i);
    }

    public final int nextUnmarkedIndex$kotlinx_serialization_json() {
        return this.origin.nextUnmarkedIndex();
    }

    public final boolean readIfAbsent(SerialDescriptor serialDescriptor, int i) {
        boolean z = !serialDescriptor.isElementOptional(i) && serialDescriptor.getElementDescriptor(i).isNullable();
        this.isUnmarkedNull = z;
        return z;
    }
}
