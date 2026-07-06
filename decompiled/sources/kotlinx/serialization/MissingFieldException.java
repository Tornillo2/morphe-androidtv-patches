package kotlinx.serialization;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public final class MissingFieldException extends SerializationException {

    @NotNull
    public final List<String> missingFields;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(@NotNull List<String> missingFields, @Nullable String str, @Nullable Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(missingFields, "missingFields");
        this.missingFields = missingFields;
    }

    @NotNull
    public final List<String> getMissingFields() {
        return this.missingFields;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MissingFieldException(@NotNull List<String> missingFields, @NotNull String serialName) {
        String str;
        Intrinsics.checkNotNullParameter(missingFields, "missingFields");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        if (missingFields.size() == 1) {
            str = "Field '" + missingFields.get(0) + "' is required for type with serial name '" + serialName + "', but it was missing";
        } else {
            str = "Fields " + missingFields + " are required for type with serial name '" + serialName + "', but they were missing";
        }
        this(missingFields, str, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(@NotNull String missingField, @NotNull String serialName) {
        this(CollectionsKt__CollectionsJVMKt.listOf(missingField), "Field '" + missingField + "' is required for type with serial name '" + serialName + "', but it was missing", null);
        Intrinsics.checkNotNullParameter(missingField, "missingField");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @PublishedApi
    public MissingFieldException(@NotNull String missingField) {
        this(CollectionsKt__CollectionsJVMKt.listOf(missingField), MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Field '", missingField, "' is required, but it was missing"), null);
        Intrinsics.checkNotNullParameter(missingField, "missingField");
    }
}
