package kotlin.collections.builders;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SerializedMap implements Externalizable {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long serialVersionUID = 0;

    @NotNull
    public Map<?, ?> map;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public SerializedMap(@NotNull Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.map = map;
    }

    @Override // java.io.Externalizable
    public void readExternal(@NotNull ObjectInput input) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        byte b = input.readByte();
        if (b != 0) {
            throw new InvalidObjectException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported flags value: ", b));
        }
        int i = input.readInt();
        if (i < 0) {
            throw new InvalidObjectException("Illegal size value: " + i + '.');
        }
        MapBuilder mapBuilder = new MapBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            mapBuilder.put(input.readObject(), input.readObject());
        }
        this.map = mapBuilder.build();
    }

    public final Object readResolve() {
        return this.map;
    }

    @Override // java.io.Externalizable
    public void writeExternal(@NotNull ObjectOutput output) throws IOException {
        Intrinsics.checkNotNullParameter(output, "output");
        output.writeByte(0);
        output.writeInt(this.map.size());
        for (Map.Entry<?, ?> entry : this.map.entrySet()) {
            output.writeObject(entry.getKey());
            output.writeObject(entry.getValue());
        }
    }

    public SerializedMap() {
        this(MapsKt__MapsKt.emptyMap());
    }
}
