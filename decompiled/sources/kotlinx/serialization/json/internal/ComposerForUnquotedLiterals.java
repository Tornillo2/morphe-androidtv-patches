package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SuppressAnimalSniffer
public final class ComposerForUnquotedLiterals extends Composer {
    public final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnquotedLiterals(@NotNull InternalJsonWriter writer, boolean z) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = z;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void printQuoted(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.forceQuoting) {
            super.printQuoted(value);
        } else {
            print(value);
        }
    }
}
