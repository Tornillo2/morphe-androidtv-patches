package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/SerializedCollection\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,718:1\n1#2:719\n*E\n"})
public final class SerializedCollection implements Externalizable {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final long serialVersionUID = 0;
    public static final int tagList = 0;
    public static final int tagSet = 1;

    @NotNull
    public Collection<?> collection;
    public final int tag;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public SerializedCollection() {
        this(EmptyList.INSTANCE, 0);
    }

    @Override // java.io.Externalizable
    public void readExternal(@NotNull ObjectInput input) throws IOException {
        Collection<?> collectionBuild;
        Intrinsics.checkNotNullParameter(input, "input");
        byte b = input.readByte();
        int i = b & 1;
        if ((b & (-2)) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) b) + '.');
        }
        int i2 = input.readInt();
        if (i2 < 0) {
            throw new InvalidObjectException("Illegal size value: " + i2 + '.');
        }
        int i3 = 0;
        if (i == 0) {
            ListBuilder listBuilder = new ListBuilder(i2);
            while (i3 < i2) {
                listBuilder.add(input.readObject());
                i3++;
            }
            collectionBuild = listBuilder.build();
        } else {
            if (i != 1) {
                throw new InvalidObjectException("Unsupported collection type tag: " + i + '.');
            }
            SetBuilder setBuilder = new SetBuilder(i2);
            while (i3 < i2) {
                setBuilder.add(input.readObject());
                i3++;
            }
            collectionBuild = setBuilder.build();
        }
        this.collection = collectionBuild;
    }

    public final Object readResolve() {
        return this.collection;
    }

    @Override // java.io.Externalizable
    public void writeExternal(@NotNull ObjectOutput output) throws IOException {
        Intrinsics.checkNotNullParameter(output, "output");
        output.writeByte(this.tag);
        output.writeInt(this.collection.size());
        Iterator<?> it = this.collection.iterator();
        while (it.hasNext()) {
            output.writeObject(it.next());
        }
    }

    public SerializedCollection(@NotNull Collection<?> collection, int i) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        this.collection = collection;
        this.tag = i;
    }
}
