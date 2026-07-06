package kotlinx.serialization.json.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JsonFriendModuleApi
public interface InternalJsonWriter {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final void doWriteEscaping(@NotNull String text, @NotNull Function3<? super String, ? super Integer, ? super Integer, Unit> writeImpl) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(writeImpl, "writeImpl");
            int length = text.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = text.charAt(i2);
                if (cCharAt < StringOpsKt.getESCAPE_STRINGS().length) {
                    String[] strArr = StringOpsKt.ESCAPE_STRINGS;
                    if (strArr[cCharAt] != null) {
                        writeImpl.invoke(text, Integer.valueOf(i), Integer.valueOf(i2));
                        String str = strArr[cCharAt];
                        Intrinsics.checkNotNull(str);
                        writeImpl.invoke(str, 0, Integer.valueOf(str.length()));
                        i = i2 + 1;
                    }
                }
            }
            writeImpl.invoke(text, Integer.valueOf(i), Integer.valueOf(text.length()));
        }
    }

    void release();

    void write(@NotNull String str);

    void writeChar(char c);

    void writeLong(long j);

    void writeQuoted(@NotNull String str);
}
