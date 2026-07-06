package com.amazon.livingroom.voice.models;

import androidx.collection.FloatFloatPair$$ExternalSyntheticBackport0;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class SeekCommandPayload {

    @NotNull
    public static final Companion Companion = new Companion();
    public final long position;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<SeekCommandPayload> serializer() {
            return SeekCommandPayload$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ SeekCommandPayload(int i, long j, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 == (i & 1)) {
            this.position = j;
        } else {
            PluginExceptionsKt.throwMissingFieldException(i, 1, SeekCommandPayload$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static SeekCommandPayload copy$default(SeekCommandPayload seekCommandPayload, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = seekCommandPayload.position;
        }
        seekCommandPayload.getClass();
        return new SeekCommandPayload(j);
    }

    public final long component1() {
        return this.position;
    }

    @NotNull
    public final SeekCommandPayload copy(long j) {
        return new SeekCommandPayload(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SeekCommandPayload) && this.position == ((SeekCommandPayload) obj).position;
    }

    public final long getPosition() {
        return this.position;
    }

    public int hashCode() {
        return FloatFloatPair$$ExternalSyntheticBackport0.m(this.position);
    }

    @NotNull
    public String toString() {
        return ChannelLogoUtils$$ExternalSyntheticOutline0.m("SeekCommandPayload(position=", this.position, ")");
    }

    public SeekCommandPayload(long j) {
        this.position = j;
    }
}
