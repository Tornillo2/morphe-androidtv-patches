package com.amazon.ignitionshared.pear;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class PearResponseMessage {

    @NotNull
    public final List<AppRecommendations> byapp;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new PearResponseMessage$$ExternalSyntheticLambda0())};

    @NotNull
    public static final Json json = JsonKt.Json$default(null, new PearResponseMessage$$ExternalSyntheticLambda1(), 1, null);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nPearResponseMessage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearResponseMessage.kt\ncom/amazon/ignitionshared/pear/PearResponseMessage$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/JsonKt\n*L\n1#1,156:1\n335#2:157\n*S KotlinDebug\n*F\n+ 1 PearResponseMessage.kt\ncom/amazon/ignitionshared/pear/PearResponseMessage$Companion\n*L\n16#1:157\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final PearResponseMessage of(@NotNull JsonElement message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Json json = PearResponseMessage.json;
            json.getClass();
            return (PearResponseMessage) json.decodeFromJsonElement(PearResponseMessage.Companion.serializer(), message);
        }

        @NotNull
        public final KSerializer<PearResponseMessage> serializer() {
            return PearResponseMessage$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ PearResponseMessage(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.byapp = list;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, PearResponseMessage$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(AppRecommendations$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PearResponseMessage copy$default(PearResponseMessage pearResponseMessage, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = pearResponseMessage.byapp;
        }
        return pearResponseMessage.copy(list);
    }

    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }

    @NotNull
    public final List<AppRecommendations> component1() {
        return this.byapp;
    }

    @NotNull
    public final PearResponseMessage copy(@NotNull List<AppRecommendations> byapp) {
        Intrinsics.checkNotNullParameter(byapp, "byapp");
        return new PearResponseMessage(byapp);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PearResponseMessage) && Intrinsics.areEqual(this.byapp, ((PearResponseMessage) obj).byapp);
    }

    @NotNull
    public final List<AppRecommendations> getByapp() {
        return this.byapp;
    }

    public int hashCode() {
        return this.byapp.hashCode();
    }

    @NotNull
    public String toString() {
        return "PearResponseMessage(byapp=" + this.byapp + ")";
    }

    public PearResponseMessage(@NotNull List<AppRecommendations> byapp) {
        Intrinsics.checkNotNullParameter(byapp, "byapp");
        this.byapp = byapp;
    }
}
