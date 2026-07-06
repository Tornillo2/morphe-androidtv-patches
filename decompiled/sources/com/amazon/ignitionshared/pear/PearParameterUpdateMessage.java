package com.amazon.ignitionshared.pear;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class PearParameterUpdateMessage {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Json json = JsonKt.Json$default(null, new PearParameterUpdateMessage$$ExternalSyntheticLambda0(), 1, null);

    @NotNull
    public final String app;

    @NotNull
    public final Update update;

    @NotNull
    public final String version;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nPearParameterUpdateMessage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PearParameterUpdateMessage.kt\ncom/amazon/ignitionshared/pear/PearParameterUpdateMessage$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,87:1\n222#2:88\n*S KotlinDebug\n*F\n+ 1 PearParameterUpdateMessage.kt\ncom/amazon/ignitionshared/pear/PearParameterUpdateMessage$Companion\n*L\n15#1:88\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final PearParameterUpdateMessage of(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Json json = PearParameterUpdateMessage.json;
            json.getClass();
            return (PearParameterUpdateMessage) json.decodeFromString(PearParameterUpdateMessage.Companion.serializer(), message);
        }

        @NotNull
        public final KSerializer<PearParameterUpdateMessage> serializer() {
            return PearParameterUpdateMessage$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ PearParameterUpdateMessage(int i, String str, Update update, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, PearParameterUpdateMessage$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.app = str;
        this.update = update;
        this.version = str2;
    }

    public static /* synthetic */ PearParameterUpdateMessage copy$default(PearParameterUpdateMessage pearParameterUpdateMessage, String str, Update update, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pearParameterUpdateMessage.app;
        }
        if ((i & 2) != 0) {
            update = pearParameterUpdateMessage.update;
        }
        if ((i & 4) != 0) {
            str2 = pearParameterUpdateMessage.version;
        }
        return pearParameterUpdateMessage.copy(str, update, str2);
    }

    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(PearParameterUpdateMessage pearParameterUpdateMessage, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, pearParameterUpdateMessage.app);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Update$$serializer.INSTANCE, pearParameterUpdateMessage.update);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, pearParameterUpdateMessage.version);
    }

    @NotNull
    public final String component1() {
        return this.app;
    }

    @NotNull
    public final Update component2() {
        return this.update;
    }

    @NotNull
    public final String component3() {
        return this.version;
    }

    @NotNull
    public final PearParameterUpdateMessage copy(@NotNull String app, @NotNull Update update, @NotNull String version) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(version, "version");
        return new PearParameterUpdateMessage(app, update, version);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PearParameterUpdateMessage)) {
            return false;
        }
        PearParameterUpdateMessage pearParameterUpdateMessage = (PearParameterUpdateMessage) obj;
        return Intrinsics.areEqual(this.app, pearParameterUpdateMessage.app) && Intrinsics.areEqual(this.update, pearParameterUpdateMessage.update) && Intrinsics.areEqual(this.version, pearParameterUpdateMessage.version);
    }

    @NotNull
    public final String getApp() {
        return this.app;
    }

    @NotNull
    public final Update getUpdate() {
        return this.update;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return this.version.hashCode() + ((this.update.recsV1.hashCode() + (this.app.hashCode() * 31)) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.app;
        Update update = this.update;
        String str2 = this.version;
        StringBuilder sb = new StringBuilder("PearParameterUpdateMessage(app=");
        sb.append(str);
        sb.append(", update=");
        sb.append(update);
        sb.append(", version=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str2, ")");
    }

    public PearParameterUpdateMessage(@NotNull String app, @NotNull Update update, @NotNull String version) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(version, "version");
        this.app = app;
        this.update = update;
        this.version = version;
    }
}
