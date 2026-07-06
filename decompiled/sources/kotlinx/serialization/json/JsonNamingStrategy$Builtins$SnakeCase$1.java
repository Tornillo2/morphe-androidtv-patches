package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonNamingStrategy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonNamingStrategy$Builtins$SnakeCase$1 implements JsonNamingStrategy {
    @Override // kotlinx.serialization.json.JsonNamingStrategy
    public String serialNameForJson(SerialDescriptor descriptor, int i, String serialName) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        return JsonNamingStrategy.Builtins.$$INSTANCE.convertCamelCase(serialName, '_');
    }

    public String toString() {
        return "kotlinx.serialization.json.JsonNamingStrategy.SnakeCase";
    }
}
