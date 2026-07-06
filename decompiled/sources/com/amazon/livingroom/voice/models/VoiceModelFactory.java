package com.amazon.livingroom.voice.models;

import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VoiceModelFactory {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final Json mapper = JsonKt.Json$default(null, new VoiceModelFactory$$ExternalSyntheticLambda0(), 1, null);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nVoiceModelFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VoiceModelFactory.kt\ncom/amazon/livingroom/voice/models/VoiceModelFactory$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,48:1\n222#2:49\n222#2:50\n*S KotlinDebug\n*F\n+ 1 VoiceModelFactory.kt\ncom/amazon/livingroom/voice/models/VoiceModelFactory$Companion\n*L\n43#1:49\n45#1:50\n*E\n"})
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createDisableCaptionsCommand() {
            return new DisableCaptionsCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createEnableCaptionsCommand() {
            return new EnableCaptionsCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createFastForwardCommand() {
            return new FastForwardVoiceCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createPauseCommand() {
            return new PauseCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createPlayCommand() {
            return new PlayCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createRewindCommand() {
            return new RewindVoiceCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createSeekCommand(long j) {
            return new SeekVoiceCommand(new SeekCommandPayload(j));
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createSkipToNextCommand() {
            return new SkipToNextCommand();
        }

        @JvmStatic
        @NotNull
        public final VoiceCommand createStopCommand() {
            return new StopCommand();
        }

        @NotNull
        public final VoiceMessage createVoiceMessage(@NotNull VoiceCommand command, @NotNull String messageTrackerId) {
            Intrinsics.checkNotNullParameter(command, "command");
            Intrinsics.checkNotNullParameter(messageTrackerId, "messageTrackerId");
            return new VoiceMessage(VoiceCommandKt.GOOGLE_ASSISTANT, messageTrackerId, command);
        }

        @NotNull
        public final VoiceResult createVoiceResult(@NotNull String jsonVoiceMessage) {
            Intrinsics.checkNotNullParameter(jsonVoiceMessage, "jsonVoiceMessage");
            Json json = VoiceModelFactory.mapper;
            json.getClass();
            return (VoiceResult) json.decodeFromString(VoiceResult.Companion.serializer(), jsonVoiceMessage);
        }

        @NotNull
        public final VoiceStartup createVoiceStartup(@NotNull String jsonVoiceMessage) {
            Intrinsics.checkNotNullParameter(jsonVoiceMessage, "jsonVoiceMessage");
            Json json = VoiceModelFactory.mapper;
            json.getClass();
            return (VoiceStartup) json.decodeFromString(VoiceStartup.Companion.serializer(), jsonVoiceMessage);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createDisableCaptionsCommand() {
        Companion.getClass();
        return new DisableCaptionsCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createEnableCaptionsCommand() {
        Companion.getClass();
        return new EnableCaptionsCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createFastForwardCommand() {
        Companion.getClass();
        return new FastForwardVoiceCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createPauseCommand() {
        Companion.getClass();
        return new PauseCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createPlayCommand() {
        Companion.getClass();
        return new PlayCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createRewindCommand() {
        Companion.getClass();
        return new RewindVoiceCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createSeekCommand(long j) {
        return Companion.createSeekCommand(j);
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createSkipToNextCommand() {
        Companion.getClass();
        return new SkipToNextCommand();
    }

    @JvmStatic
    @NotNull
    public static final VoiceCommand createStopCommand() {
        Companion.getClass();
        return new StopCommand();
    }

    public static final Unit mapper$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.ignoreUnknownKeys = true;
        return Unit.INSTANCE;
    }
}
