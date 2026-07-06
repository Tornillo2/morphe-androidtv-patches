package kotlinx.serialization.json;

import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public interface JsonNamingStrategy {

    @NotNull
    public static final Builtins Builtins = Builtins.$$INSTANCE;

    @NotNull
    String serialNameForJson(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull String str);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ExperimentalSerializationApi
    @SourceDebugExtension({"SMAP\nJsonNamingStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonNamingStrategy.kt\nkotlinx/serialization/json/JsonNamingStrategy$Builtins\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,178:1\n1179#2:179\n1180#2:181\n1#3:180\n*S KotlinDebug\n*F\n+ 1 JsonNamingStrategy.kt\nkotlinx/serialization/json/JsonNamingStrategy$Builtins\n*L\n149#1:179\n149#1:181\n*E\n"})
    public static final class Builtins {
        public static final /* synthetic */ Builtins $$INSTANCE = new Builtins();

        @NotNull
        public static final JsonNamingStrategy SnakeCase = new JsonNamingStrategy$Builtins$SnakeCase$1();

        @NotNull
        public static final JsonNamingStrategy KebabCase = new JsonNamingStrategy$Builtins$KebabCase$1();

        public final String convertCamelCase(String str, char c) {
            StringBuilder sb = new StringBuilder(str.length() * 2);
            Character chValueOf = null;
            int i = 0;
            for (int i2 = 0; i2 < str.length(); i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isUpperCase(cCharAt)) {
                    if (i == 0 && sb.length() > 0 && StringsKt___StringsKt.last(sb) != c) {
                        sb.append(c);
                    }
                    if (chValueOf != null) {
                        sb.append(chValueOf.charValue());
                    }
                    i++;
                    chValueOf = Character.valueOf(Character.toLowerCase(cCharAt));
                } else {
                    if (chValueOf != null) {
                        if (i > 1 && Character.isLetter(cCharAt)) {
                            sb.append(c);
                        }
                        sb.append(chValueOf.charValue());
                        chValueOf = null;
                        i = 0;
                    }
                    sb.append(cCharAt);
                }
            }
            if (chValueOf != null) {
                sb.append(chValueOf.charValue());
            }
            return sb.toString();
        }

        @NotNull
        public final JsonNamingStrategy getKebabCase() {
            return KebabCase;
        }

        @NotNull
        public final JsonNamingStrategy getSnakeCase() {
            return SnakeCase;
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getKebabCase$annotations() {
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getSnakeCase$annotations() {
        }
    }
}
