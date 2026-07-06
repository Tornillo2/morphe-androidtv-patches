package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonSerializersModuleValidator;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonImpl extends Json {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonImpl(@NotNull JsonConfiguration configuration, @NotNull SerializersModule module) {
        super(configuration, module);
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(module, "module");
        validateConfiguration();
    }

    public final void validateConfiguration() {
        if (Intrinsics.areEqual(this.serializersModule, SerializersModuleKt.EmptySerializersModule)) {
            return;
        }
        this.serializersModule.dumpTo(new JsonSerializersModuleValidator(this.configuration));
    }
}
