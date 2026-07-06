package kotlin.text;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.amazon.minerva.identifiers.schemaid.MetricSchemaUUID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.serialization.json.JsonKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nIndent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,129:1\n119#1,2:131\n121#1,4:146\n126#1,2:159\n119#1,2:168\n121#1,4:183\n126#1,2:190\n1#2:130\n1#2:156\n1#2:187\n1#2:211\n1583#3,11:133\n1878#3,2:144\n1880#3:157\n1594#3:158\n774#3:161\n865#3,2:162\n1563#3:164\n1634#3,3:165\n1583#3,11:170\n1878#3,2:181\n1880#3:188\n1594#3:189\n1583#3,11:198\n1878#3,2:209\n1880#3:212\n1594#3:213\n158#4,6:150\n158#4,6:192\n*S KotlinDebug\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n*L\n42#1:131,2\n42#1:146,4\n42#1:159,2\n83#1:168,2\n83#1:183,4\n83#1:190,2\n42#1:156\n83#1:187\n120#1:211\n42#1:133,11\n42#1:144,2\n42#1:157\n42#1:158\n79#1:161\n79#1:162,2\n80#1:164\n80#1:165,3\n83#1:170,11\n83#1:181,2\n83#1:188\n83#1:189\n120#1:198,11\n120#1:209,2\n120#1:212\n120#1:213\n43#1:150,6\n107#1:192,6\n*E\n"})
public class StringsKt__IndentKt extends StringsKt__AppendableKt {
    public static String $r8$lambda$7gQTIx5gKXNh0yIHp2Ioy3cIUtg(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return line;
    }

    public static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(final String str) {
        return str.length() == 0 ? new StringsKt__IndentKt$$ExternalSyntheticLambda1() : new Function1() { // from class: kotlin.text.StringsKt__IndentKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StringsKt__IndentKt.getIndentFunction$lambda$9$StringsKt__IndentKt(str, (String) obj);
            }
        };
    }

    public static final String getIndentFunction$lambda$8$StringsKt__IndentKt(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return line;
    }

    public static final String getIndentFunction$lambda$9$StringsKt__IndentKt(String str, String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return str + line;
    }

    public static final int indentWidth$StringsKt__IndentKt(String str) {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (!CharsKt__CharJVMKt.isWhitespace(str.charAt(i))) {
                break;
            }
            i++;
        }
        return i == -1 ? str.length() : i;
    }

    @NotNull
    public static final String prependIndent(@NotNull String str, @NotNull final String indent) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(indent, "indent");
        return SequencesKt___SequencesKt.joinToString$default(SequencesKt___SequencesKt.map(StringsKt__StringsKt.lineSequence(str), new Function1() { // from class: kotlin.text.StringsKt__IndentKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StringsKt__IndentKt.prependIndent$lambda$5$StringsKt__IndentKt(indent, (String) obj);
            }
        }), StringUtils.LF, null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = JsonKt.defaultIndent;
        }
        return prependIndent(str, str2);
    }

    public static final String prependIndent$lambda$5$StringsKt__IndentKt(String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt__StringsKt.isBlank(it) ? it.length() < str.length() ? str : it : AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, it);
    }

    public static final String reindent$StringsKt__IndentKt(List<String> list, int i, Function1<? super String, String> function1, Function1<? super String, String> function12) throws IOException {
        String strInvoke;
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            String strInvoke2 = null;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String str = (String) obj;
            if (((i2 != 0 && i2 != lastIndex) || !StringsKt__StringsKt.isBlank(str)) && ((strInvoke = function12.invoke(str)) == null || (strInvoke2 = function1.invoke(strInvoke)) == null)) {
                strInvoke2 = str;
            }
            if (strInvoke2 != null) {
                arrayList.add(strInvoke2);
            }
            i2 = i3;
        }
        StringBuilder sb = new StringBuilder(i);
        CollectionsKt___CollectionsKt.joinTo$default(arrayList, sb, StringUtils.LF, null, null, 0, null, null, 124, null);
        return sb.toString();
    }

    @NotNull
    public static final String replaceIndent(@NotNull String str, @NotNull String newIndent) throws IOException {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(newIndent, "newIndent");
        List<String> listLines = StringsKt__StringsKt.lines(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listLines) {
            if (!StringsKt__StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            arrayList2.add(Integer.valueOf(indentWidth$StringsKt__IndentKt((String) obj2)));
        }
        Integer num = (Integer) CollectionsKt___CollectionsKt.minOrNull((Iterable) arrayList2);
        int iIntValue = num != null ? num.intValue() : 0;
        int size2 = (listLines.size() * newIndent.length()) + str.length();
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(newIndent);
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(listLines);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : listLines) {
            int i3 = i + 1;
            String strInvoke = null;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String str2 = (String) obj3;
            if (((i != 0 && i != lastIndex) || !StringsKt__StringsKt.isBlank(str2)) && (strInvoke = indentFunction$StringsKt__IndentKt.invoke(StringsKt___StringsKt.drop(str2, iIntValue))) == null) {
                strInvoke = str2;
            }
            if (strInvoke != null) {
                arrayList3.add(strInvoke);
            }
            i = i3;
        }
        StringBuilder sb = new StringBuilder(size2);
        CollectionsKt___CollectionsKt.joinTo$default(arrayList3, sb, StringUtils.LF, null, null, 0, null, null, 124, null);
        return sb.toString();
    }

    public static /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    @NotNull
    public static final String replaceIndentByMargin(@NotNull String str, @NotNull String newIndent, @NotNull String marginPrefix) throws IOException {
        String str2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(newIndent, "newIndent");
        Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
        if (StringsKt__StringsKt.isBlank(marginPrefix)) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        List<String> listLines = StringsKt__StringsKt.lines(str);
        int size = (listLines.size() * newIndent.length()) + str.length();
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(newIndent);
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(listLines);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : listLines) {
            int i2 = i + 1;
            String strSubstring = null;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            String str3 = (String) obj;
            if ((i != 0 && i != lastIndex) || !StringsKt__StringsKt.isBlank(str3)) {
                int length = str3.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        i3 = -1;
                        break;
                    }
                    if (!CharsKt__CharJVMKt.isWhitespace(str3.charAt(i3))) {
                        break;
                    }
                    i3++;
                }
                if (i3 == -1) {
                    str2 = str3;
                } else {
                    int i4 = i3;
                    str2 = str3;
                    if (StringsKt__StringsJVMKt.startsWith$default(str2, marginPrefix, i4, false, 4, null)) {
                        strSubstring = str2.substring(marginPrefix.length() + i4);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    }
                }
                if (strSubstring == null || (strSubstring = indentFunction$StringsKt__IndentKt.invoke(strSubstring)) == null) {
                    strSubstring = str2;
                }
            }
            if (strSubstring != null) {
                arrayList.add(strSubstring);
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder(size);
        CollectionsKt___CollectionsKt.joinTo$default(arrayList, sb, StringUtils.LF, null, null, 0, null, null, 124, null);
        return sb.toString();
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER;
        }
        return replaceIndentByMargin(str, str2, str3);
    }

    @IntrinsicConstEvaluation
    @NotNull
    public static String trimIndent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return replaceIndent(str, "");
    }

    @IntrinsicConstEvaluation
    @NotNull
    public static final String trimMargin(@NotNull String str, @NotNull String marginPrefix) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
        return replaceIndentByMargin(str, "", marginPrefix);
    }

    public static /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER;
        }
        return trimMargin(str, str2);
    }
}
