package kotlin.reflect;

import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface KParameter extends KAnnotatedElement {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Kind {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Kind[] $VALUES;
        public static final Kind INSTANCE = new Kind("INSTANCE", 0);
        public static final Kind EXTENSION_RECEIVER = new Kind("EXTENSION_RECEIVER", 1);
        public static final Kind VALUE = new Kind("VALUE", 2);

        public static final /* synthetic */ Kind[] $values() {
            return new Kind[]{INSTANCE, EXTENSION_RECEIVER, VALUE};
        }

        static {
            Kind[] kindArr$values = $values();
            $VALUES = kindArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(kindArr$values);
        }

        public Kind(String str, int i) {
        }

        @NotNull
        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }
    }

    int getIndex();

    @NotNull
    Kind getKind();

    @Nullable
    String getName();

    @NotNull
    KType getType();

    boolean isOptional();

    boolean isVararg();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isVararg$annotations() {
        }
    }
}
