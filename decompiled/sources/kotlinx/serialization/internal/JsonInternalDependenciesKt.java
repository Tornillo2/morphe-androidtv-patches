package kotlinx.serialization.internal;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonInternalDependenciesKt {
    @CoreFriendModuleApi
    @NotNull
    public static final Set<String> jsonCachedSerialNames(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return Platform_commonKt.cachedSerialNames(serialDescriptor);
    }
}
