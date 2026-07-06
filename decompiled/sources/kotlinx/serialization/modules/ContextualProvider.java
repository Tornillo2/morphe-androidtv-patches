package kotlinx.serialization.modules;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class ContextualProvider {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Argless extends ContextualProvider {

        @NotNull
        public final KSerializer<?> serializer;

        public Argless(@NotNull KSerializer<?> serializer) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            this.serializer = serializer;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Argless) && Intrinsics.areEqual(((Argless) obj).serializer, this.serializer);
        }

        @NotNull
        public final KSerializer<?> getSerializer() {
            return this.serializer;
        }

        public int hashCode() {
            return this.serializer.hashCode();
        }

        @Override // kotlinx.serialization.modules.ContextualProvider
        @NotNull
        public KSerializer<?> invoke(@NotNull List<? extends KSerializer<?>> typeArgumentsSerializers) {
            Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
            return this.serializer;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WithTypeArguments extends ContextualProvider {

        @NotNull
        public final Function1<List<? extends KSerializer<?>>, KSerializer<?>> provider;

        /* JADX WARN: Multi-variable type inference failed */
        public WithTypeArguments(@NotNull Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            this.provider = provider;
        }

        @NotNull
        public final Function1<List<? extends KSerializer<?>>, KSerializer<?>> getProvider() {
            return this.provider;
        }

        @Override // kotlinx.serialization.modules.ContextualProvider
        @NotNull
        public KSerializer<?> invoke(@NotNull List<? extends KSerializer<?>> typeArgumentsSerializers) {
            Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
            return this.provider.invoke(typeArgumentsSerializers);
        }
    }

    public ContextualProvider() {
    }

    @NotNull
    public abstract KSerializer<?> invoke(@NotNull List<? extends KSerializer<?>> list);

    public ContextualProvider(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
