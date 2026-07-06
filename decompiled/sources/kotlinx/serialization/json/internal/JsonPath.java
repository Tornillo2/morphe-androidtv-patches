package kotlinx.serialization.json.internal;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonPath {
    public int currentDepth;

    @NotNull
    public Object[] currentObjectPath = new Object[8];

    @NotNull
    public int[] indicies;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Tombstone {

        @NotNull
        public static final Tombstone INSTANCE = new Tombstone();
    }

    public JsonPath() {
        int[] iArr = new int[8];
        for (int i = 0; i < 8; i++) {
            iArr[i] = -1;
        }
        this.indicies = iArr;
        this.currentDepth = -1;
    }

    @NotNull
    public final String getPath() {
        StringBuilder sb = new StringBuilder("$");
        int i = this.currentDepth + 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.currentObjectPath[i2];
            if (obj instanceof SerialDescriptor) {
                SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
                if (!Intrinsics.areEqual(serialDescriptor.getKind(), StructureKind.LIST.INSTANCE)) {
                    int i3 = this.indicies[i2];
                    if (i3 >= 0) {
                        sb.append(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
                        sb.append(serialDescriptor.getElementName(i3));
                    }
                } else if (this.indicies[i2] != -1) {
                    sb.append("[");
                    sb.append(this.indicies[i2]);
                    sb.append("]");
                }
            } else if (obj != Tombstone.INSTANCE) {
                sb.append("['");
                sb.append(obj);
                sb.append("']");
            }
        }
        return sb.toString();
    }

    public final void popDescriptor() {
        int i = this.currentDepth;
        int[] iArr = this.indicies;
        if (iArr[i] == -2) {
            iArr[i] = -1;
            this.currentDepth = i - 1;
        }
        int i2 = this.currentDepth;
        if (i2 != -1) {
            this.currentDepth = i2 - 1;
        }
    }

    public final String prettyString(Object obj) {
        String serialName;
        SerialDescriptor serialDescriptor = obj instanceof SerialDescriptor ? (SerialDescriptor) obj : null;
        return (serialDescriptor == null || (serialName = serialDescriptor.getSerialName()) == null) ? String.valueOf(obj) : serialName;
    }

    public final void pushDescriptor(@NotNull SerialDescriptor sd) {
        Intrinsics.checkNotNullParameter(sd, "sd");
        int i = this.currentDepth + 1;
        this.currentDepth = i;
        if (i == this.currentObjectPath.length) {
            resize();
        }
        this.currentObjectPath[i] = sd;
    }

    public final void resetCurrentMapKey() {
        int[] iArr = this.indicies;
        int i = this.currentDepth;
        if (iArr[i] == -2) {
            this.currentObjectPath[i] = Tombstone.INSTANCE;
        }
    }

    public final void resize() {
        int i = this.currentDepth * 2;
        Object[] objArrCopyOf = Arrays.copyOf(this.currentObjectPath, i);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        this.currentObjectPath = objArrCopyOf;
        int[] iArrCopyOf = Arrays.copyOf(this.indicies, i);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        this.indicies = iArrCopyOf;
    }

    @NotNull
    public String toString() {
        return getPath();
    }

    public final void updateCurrentMapKey(@Nullable Object obj) {
        int[] iArr = this.indicies;
        int i = this.currentDepth;
        if (iArr[i] != -2) {
            int i2 = i + 1;
            this.currentDepth = i2;
            if (i2 == this.currentObjectPath.length) {
                resize();
            }
        }
        Object[] objArr = this.currentObjectPath;
        int i3 = this.currentDepth;
        objArr[i3] = obj;
        this.indicies[i3] = -2;
    }

    public final void updateDescriptorIndex(int i) {
        this.indicies[this.currentDepth] = i;
    }
}
