package kotlin.text;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline0;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline1;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_Strings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,2571:1\n130#1,2:2572\n221#1,5:2574\n507#1,5:2580\n507#1,5:2585\n467#1:2590\n1188#1,2:2591\n468#1,2:2593\n1190#1:2595\n470#1:2596\n467#1:2597\n1188#1,2:2598\n468#1,2:2600\n1190#1:2602\n470#1:2603\n1188#1,3:2604\n497#1,2:2607\n497#1,2:2609\n755#1,4:2611\n724#1,4:2615\n740#1,4:2619\n787#1,4:2623\n887#1,5:2627\n928#1,3:2632\n931#1,3:2642\n946#1,3:2645\n949#1,3:2655\n1046#1,3:2672\n1016#1,4:2675\n1005#1:2679\n1188#1,2:2680\n1190#1:2683\n1006#1:2684\n1188#1,3:2685\n1037#1:2688\n1179#1:2689\n1180#1:2691\n1038#1:2692\n1179#1,2:2693\n1188#1,3:2695\n2069#1,2:2698\n2071#1,6:2701\n2093#1,2:2707\n2095#1,6:2710\n2516#1,6:2716\n2546#1,7:2722\n1#2:2579\n1#2:2682\n1#2:2690\n1#2:2700\n1#2:2709\n384#3,7:2635\n384#3,7:2648\n384#3,7:2658\n384#3,7:2665\n*S KotlinDebug\n*F\n+ 1 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n56#1:2572,2\n66#1:2574,5\n425#1:2580,5\n434#1:2585,5\n445#1:2590\n445#1:2591,2\n445#1:2593,2\n445#1:2595\n445#1:2596\n456#1:2597\n456#1:2598,2\n456#1:2600,2\n456#1:2602\n456#1:2603\n467#1:2604,3\n479#1:2607,2\n488#1:2609,2\n682#1:2611,4\n697#1:2615,4\n711#1:2619,4\n774#1:2623,4\n847#1:2627,5\n903#1:2632,3\n903#1:2642,3\n916#1:2645,3\n916#1:2655,3\n975#1:2672,3\n985#1:2675,4\n995#1:2679\n995#1:2680,2\n995#1:2683\n995#1:2684\n1005#1:2685,3\n1029#1:2688\n1029#1:2689\n1029#1:2691\n1029#1:2692\n1037#1:2693,2\n1875#1:2695,3\n2163#1:2698,2\n2163#1:2701,6\n2180#1:2707,2\n2180#1:2710,6\n2505#1:2716,6\n2533#1:2722,7\n995#1:2682\n1029#1:2690\n2163#1:2700\n2180#1:2709\n903#1:2635,7\n916#1:2648,7\n930#1:2658,7\n948#1:2665,7\n*E\n"})
public class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static final boolean all(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return !(charSequence.length() == 0);
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return ((charSequence instanceof String) && charSequence.length() == 0) ? EmptyList.INSTANCE : new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(charSequence);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull final CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return ((charSequence instanceof String) && charSequence.length() == 0) ? EmptySequence.INSTANCE : new Sequence<Character>() { // from class: kotlin.text.StringsKt___StringsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<Character> iterator() {
                return StringsKt__StringsKt.iterator(charSequence);
            }
        };
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(charSequence.length());
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            linkedHashMap.put(pair.first, pair.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(charSequence.length());
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            linkedHashMap.put(keySelector.invoke(Character.valueOf(cCharAt)), Character.valueOf(cCharAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            destination.put(keySelector.invoke(Character.valueOf(cCharAt)), Character.valueOf(cCharAt));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            destination.put(pair.first, pair.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <V> Map<Character, V> associateWith(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int length = charSequence.length();
        if (length > 128) {
            length = 128;
        }
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            linkedHashMap.put(Character.valueOf(cCharAt), valueSelector.invoke(Character.valueOf(cCharAt)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <V, M extends Map<? super Character, ? super V>> M associateWithTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            destination.put(Character.valueOf(cCharAt), valueSelector.invoke(Character.valueOf(cCharAt)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static List<String> chunked(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return windowed(charSequence, i, i, true);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<String> chunkedSequence(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return chunkedSequence(charSequence, i, new StringsKt___StringsKt$$ExternalSyntheticLambda2());
    }

    public static final String chunkedSequence$lambda$22$StringsKt___StringsKt(CharSequence it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toString();
    }

    @InlineOnly
    public static final int count(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length();
    }

    @NotNull
    public static final CharSequence drop(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
        }
        int length = charSequence.length();
        if (i > length) {
            i = length;
        }
        return charSequence.subSequence(i, charSequence.length());
    }

    @NotNull
    public static final CharSequence dropLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
        }
        int length = charSequence.length() - i;
        if (length < 0) {
            length = 0;
        }
        return take(charSequence, length);
    }

    @NotNull
    public static final CharSequence dropLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); -1 < lastIndex; lastIndex--) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, lastIndex, predicate)).booleanValue()) {
                return charSequence.subSequence(0, lastIndex + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence dropWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int iM = StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "predicate");
        for (int i = 0; i < iM; i++) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @InlineOnly
    public static final char elementAtOrElse(CharSequence charSequence, int i, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= charSequence.length()) ? defaultValue.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @InlineOnly
    public static final Character elementAtOrNull(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return getOrNull(charSequence, i);
    }

    @NotNull
    public static final CharSequence filter(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
        }
        return sb;
    }

    @NotNull
    public static final CharSequence filterIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char cCharAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
            i++;
            i2 = i3;
        }
        return sb;
    }

    @NotNull
    public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char cCharAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(cCharAt)).booleanValue()) {
                destination.append(cCharAt);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final CharSequence filterNot(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (!predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
        }
        return sb;
    }

    @NotNull
    public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (!predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                destination.append(cCharAt);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Appendable> C filterTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                destination.append(cCharAt);
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Character find(CharSequence charSequence, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                return Character.valueOf(cCharAt);
            }
        }
        return null;
    }

    @InlineOnly
    public static final Character findLast(CharSequence charSequence, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            char cCharAt = charSequence.charAt(length);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                return Character.valueOf(cCharAt);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final char first(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() != 0) {
            return charSequence.charAt(0);
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <R> R firstNotNullOf(CharSequence charSequence, Function1<? super Character, ? extends R> transform) {
        R r;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        while (true) {
            if (i >= charSequence.length()) {
                r = null;
                break;
            }
            r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            if (r != null) {
                break;
            }
            i++;
        }
        if (r != null) {
            return r;
        }
        throw new NoSuchElementException("No element of the char sequence was transformed to a non-null value.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <R> R firstNotNullOfOrNull(CharSequence charSequence, Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            R r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(0));
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(CharSequence charSequence, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i))));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(CharSequence charSequence, C destination, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i))));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform));
        }
        return destination;
    }

    public static final <R> R fold(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int i = 0; i < charSequence.length(); i++) {
            r = operation.invoke(r, Character.valueOf(charSequence.charAt(i)));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Character.valueOf(charSequence.charAt(i)));
            i++;
            i2++;
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            r = operation.invoke(Character.valueOf(charSequence.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            r = operation.invoke(Integer.valueOf(lastIndex), Character.valueOf(charSequence.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final void forEach(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i = 0; i < charSequence.length(); i++) {
            action.invoke(Character.valueOf(charSequence.charAt(i)));
        }
    }

    public static final void forEachIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            action.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            i++;
            i2++;
        }
    }

    @InlineOnly
    public static final char getOrElse(CharSequence charSequence, int i, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= charSequence.length()) ? defaultValue.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @Nullable
    public static final Character getOrNull(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0 || i >= charSequence.length()) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(i));
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            K kInvoke = keySelector.invoke(Character.valueOf(cCharAt));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Character.valueOf(cCharAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            K kInvoke = keySelector.invoke(Character.valueOf(cCharAt));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Character.valueOf(cCharAt));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K> Grouping<Character, K> groupingBy(@NotNull final CharSequence charSequence, @NotNull final Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        return new Grouping<Character, K>() { // from class: kotlin.text.StringsKt___StringsKt.groupingBy.1
            @Override // kotlin.collections.Grouping
            public Object keyOf(Character ch) {
                Character ch2 = ch;
                ch2.charValue();
                return keySelector.invoke(ch2);
            }

            @Override // kotlin.collections.Grouping
            public Iterator<Character> sourceIterator() {
                return StringsKt__StringsKt.iterator(charSequence);
            }

            public K keyOf(char c) {
                return keySelector.invoke(Character.valueOf(c));
            }
        };
    }

    public static final int indexOfFirst(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int iM = StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "predicate");
        for (int i = 0; i < iM; i++) {
            if (((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, length, predicate)).booleanValue()) {
                    return length;
                }
                if (i < 0) {
                    break;
                }
                length = i;
            }
        }
        return -1;
    }

    public static char last(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() != 0) {
            return charSequence.charAt(StringsKt__StringsKt.getLastIndex(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(charSequence.length() - 1));
    }

    @NotNull
    public static final <R> List<R> map(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            arrayList.add(transform.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(charSequence.length());
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i))));
            i++;
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            R rInvoke = transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            R rInvoke = transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            destination.add(transform.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i))));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R> List<R> mapNotNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            Object objM = StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            if (objM != null) {
                arrayList.add(objM);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Object objM = StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, transform);
            if (objM != null) {
                destination.add(objM);
            }
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence charSequence, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            destination.add(transform.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character maxByOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex == 0) {
            return Character.valueOf(cCharAt);
        }
        R rInvoke = function1.invoke(Character.valueOf(cCharAt));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                R rInvoke2 = function1.invoke(Character.valueOf(cCharAt2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    cCharAt = cCharAt2;
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> char maxByOrThrow(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex != 0) {
            R rInvoke = function1.invoke(Character.valueOf(cCharAt));
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    char cCharAt2 = charSequence.charAt(i);
                    R rInvoke2 = function1.invoke(Character.valueOf(cCharAt2));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        cCharAt = cCharAt2;
                        rInvoke = rInvoke2;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(CharSequence charSequence, Function1<? super Character, Double> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).doubleValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).doubleValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(CharSequence charSequence, Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        R r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Comparable comparable = (Comparable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1);
                if (r.compareTo(comparable) < 0) {
                    r = (R) comparable;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWith(CharSequence charSequence, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, selector);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Object obj = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(CharSequence charSequence, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (charSequence.length() == 0) {
            return null;
        }
        R r = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, selector);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Object obj = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (Intrinsics.compare((int) cCharAt, (int) cCharAt2) < 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final char maxOrThrow(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (Intrinsics.compare((int) cCharAt, (int) cCharAt2) < 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxWithOrNull(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(cCharAt), Character.valueOf(cCharAt2)) < 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final char maxWithOrThrow(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(cCharAt), Character.valueOf(cCharAt2)) < 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character minByOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex == 0) {
            return Character.valueOf(cCharAt);
        }
        R rInvoke = function1.invoke(Character.valueOf(cCharAt));
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                R rInvoke2 = function1.invoke(Character.valueOf(cCharAt2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    cCharAt = cCharAt2;
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> char minByOrThrow(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex != 0) {
            R rInvoke = function1.invoke(Character.valueOf(cCharAt));
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    char cCharAt2 = charSequence.charAt(i);
                    R rInvoke2 = function1.invoke(Character.valueOf(cCharAt2));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        cCharAt = cCharAt2;
                        rInvoke = rInvoke2;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(CharSequence charSequence, Function1<? super Character, Double> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).doubleValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).doubleValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(CharSequence charSequence, Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        R r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Comparable comparable = (Comparable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1);
                if (r.compareTo(comparable) > 0) {
                    r = (R) comparable;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWith(CharSequence charSequence, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, selector);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Object obj = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(CharSequence charSequence, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (charSequence.length() == 0) {
            return null;
        }
        R r = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, selector);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                Object obj = (Object) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (Intrinsics.compare((int) cCharAt, (int) cCharAt2) > 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final char minOrThrow(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (Intrinsics.compare((int) cCharAt, (int) cCharAt2) > 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minWithOrNull(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(cCharAt), Character.valueOf(cCharAt2)) > 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final char minWithOrThrow(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (charSequence.length() == 0) {
            throw new NoSuchElementException();
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                char cCharAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(cCharAt), Character.valueOf(cCharAt2)) > 0) {
                    cCharAt = cCharAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    public static final boolean none(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() == 0;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S extends CharSequence> S onEach(@NotNull S s, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i = 0; i < s.length(); i++) {
            action.invoke(Character.valueOf(s.charAt(i)));
        }
        return s;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S extends CharSequence> S onEachIndexed(@NotNull S s, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int i = 0;
        int i2 = 0;
        while (i < s.length()) {
            action.invoke(Integer.valueOf(i2), Character.valueOf(s.charAt(i)));
            i++;
            i2++;
        }
        return s;
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            } else {
                sb2.append(cCharAt);
            }
        }
        return new Pair<>(sb, sb2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final char random(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return random(charSequence, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Character randomOrNull(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return randomOrNull(charSequence, Random.Default);
    }

    public static final char reduce(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                cCharAt = operation.invoke(Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    public static final char reduceIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                cCharAt = operation.invoke(Integer.valueOf(i), Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceIndexedOrNull(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                cCharAt = operation.invoke(Integer.valueOf(i), Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceOrNull(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(0);
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                cCharAt = operation.invoke(Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharAt);
    }

    public static final char reduceRight(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char cCharAt = charSequence.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            cCharAt = operation.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(cCharAt)).charValue();
        }
        return cCharAt;
    }

    public static final char reduceRightIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char cCharAt = charSequence.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            cCharAt = operation.invoke(Integer.valueOf(i), Character.valueOf(charSequence.charAt(i)), Character.valueOf(cCharAt)).charValue();
        }
        return cCharAt;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceRightIndexedOrNull(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex < 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            cCharAt = operation.invoke(Integer.valueOf(i), Character.valueOf(charSequence.charAt(i)), Character.valueOf(cCharAt)).charValue();
        }
        return Character.valueOf(cCharAt);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceRightOrNull(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        if (lastIndex < 0) {
            return null;
        }
        char cCharAt = charSequence.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            cCharAt = operation.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(cCharAt)).charValue();
        }
        return Character.valueOf(cCharAt);
    }

    @NotNull
    public static final CharSequence reversed(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return new StringBuilder(charSequence).reverse();
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <R> List<R> runningFold(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(charSequence.length() + 1);
        arrayList.add(r);
        for (int i = 0; i < charSequence.length(); i++) {
            r = operation.invoke(r, Character.valueOf(charSequence.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <R> List<R> runningFoldIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(charSequence.length() + 1);
        arrayList.add(r);
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Character.valueOf(charSequence.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final List<Character> runningReduce(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return EmptyList.INSTANCE;
        }
        char cCharAt = charSequence.charAt(0);
        ArrayList arrayList = new ArrayList(charSequence.length());
        arrayList.add(Character.valueOf(cCharAt));
        int length = charSequence.length();
        int i = 1;
        while (i < length) {
            Character chInvoke = operation.invoke(Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i)));
            char cCharValue = chInvoke.charValue();
            arrayList.add(chInvoke);
            i++;
            cCharAt = cCharValue;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final List<Character> runningReduceIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return EmptyList.INSTANCE;
        }
        char cCharAt = charSequence.charAt(0);
        ArrayList arrayList = new ArrayList(charSequence.length());
        arrayList.add(Character.valueOf(cCharAt));
        int length = charSequence.length();
        int i = 1;
        while (i < length) {
            Character chInvoke = operation.invoke(Integer.valueOf(i), Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i)));
            char cCharValue = chInvoke.charValue();
            arrayList.add(chInvoke);
            i++;
            cCharAt = cCharValue;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <R> List<R> scan(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(charSequence.length() + 1);
        arrayList.add(r);
        for (int i = 0; i < charSequence.length(); i++) {
            r = operation.invoke(r, Character.valueOf(charSequence.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <R> List<R> scanIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (charSequence.length() == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(charSequence.length() + 1);
        arrayList.add(r);
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Character.valueOf(charSequence.charAt(i)));
            arrayList.add(r);
        }
        return arrayList;
    }

    public static char single(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        if (length == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (length == 1) {
            return charSequence.charAt(0);
        }
        throw new IllegalArgumentException("Char sequence has more than one element.");
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 1) {
            return Character.valueOf(charSequence.charAt(0));
        }
        return null;
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt__StringsKt.subSequence(charSequence, indices);
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            iIntValue += ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            dDoubleValue += ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(CharSequence charSequence, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            dDoubleValue += ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(CharSequence charSequence, Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            iIntValue += ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(CharSequence charSequence, Function1<? super Character, Long> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            jLongValue += ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(CharSequence charSequence, Function1<? super Character, UInt> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i += ((UInt) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i2, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(CharSequence charSequence, Function1<? super Character, ULong> selector) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            j += ((ULong) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, selector)).data;
        }
        return j;
    }

    @NotNull
    public static final CharSequence take(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
        }
        int length = charSequence.length();
        if (i > length) {
            i = length;
        }
        return charSequence.subSequence(0, i);
    }

    @NotNull
    public static final CharSequence takeLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
        }
        int length = charSequence.length();
        if (i > length) {
            i = length;
        }
        return charSequence.subSequence(length - i, length);
    }

    @NotNull
    public static final CharSequence takeLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence); -1 < lastIndex; lastIndex--) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, lastIndex, predicate)).booleanValue()) {
                return charSequence.subSequence(lastIndex + 1, charSequence.length());
            }
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final CharSequence takeWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int iM = StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "predicate");
        for (int i = 0; i < iM; i++) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).booleanValue()) {
                return charSequence.subSequence(0, i);
            }
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence charSequence, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (int i = 0; i < charSequence.length(); i++) {
            destination.add(Character.valueOf(charSequence.charAt(i)));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        if (length > 128) {
            length = 128;
        }
        HashSet<Character> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(length));
        toCollection(charSequence, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Character> toList(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        return length != 0 ? length != 1 ? toMutableList(charSequence) : CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(charSequence.charAt(0))) : EmptyList.INSTANCE;
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        ArrayList arrayList = new ArrayList(charSequence.length());
        toCollection(charSequence, arrayList);
        return arrayList;
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length == 1) {
            return SetsKt__SetsJVMKt.setOf(Character.valueOf(charSequence.charAt(0)));
        }
        int length2 = charSequence.length();
        if (length2 > 128) {
            length2 = 128;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(length2));
        toCollection(charSequence, linkedHashSet);
        return linkedHashSet;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<String> windowed(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return windowed(charSequence, i, i2, z, new StringsKt___StringsKt$$ExternalSyntheticLambda0());
    }

    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z);
    }

    public static final String windowed$lambda$23$StringsKt___StringsKt(CharSequence it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toString();
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final Sequence<String> windowedSequence(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return windowedSequence(charSequence, i, i2, z, new StringsKt___StringsKt$$ExternalSyntheticLambda1());
    }

    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z);
    }

    public static final String windowedSequence$lambda$24$StringsKt___StringsKt(CharSequence it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toString();
    }

    public static final Object windowedSequence$lambda$25$StringsKt___StringsKt(int i, CharSequence charSequence, Function1 function1, int i2) {
        int length = i + i2;
        if (length < 0 || length > charSequence.length()) {
            length = charSequence.length();
        }
        return function1.invoke(charSequence.subSequence(i2, length));
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull final CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.text.StringsKt___StringsKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return StringsKt__StringsKt.iterator(charSequence);
            }
        });
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull CharSequence charSequence, @NotNull CharSequence other, @NotNull Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(charSequence.length(), other.length());
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> zipWithNext(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            Character chValueOf = Character.valueOf(charSequence.charAt(i));
            i++;
            arrayList.add(transform.invoke(chValueOf, Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> chunked(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return windowed(charSequence, i, i, true, transform);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> Sequence<R> chunkedSequence(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return windowedSequence(charSequence, i, i, true, transform);
    }

    public static final int count(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i2, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                return Character.valueOf(cCharAt);
            }
        }
        return null;
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            char cCharAt = charSequence.charAt(length);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                return Character.valueOf(cCharAt);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final boolean none(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull CharSequence charSequence, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (charSequence.length() != 0) {
            return charSequence.charAt(random.nextInt(charSequence.length()));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character randomOrNull(@NotNull CharSequence charSequence, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(random.nextInt(charSequence.length())));
    }

    @InlineOnly
    public static final String reversed(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return reversed((CharSequence) str).toString();
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Character chValueOf = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                if (z) {
                    return null;
                }
                chValueOf = Character.valueOf(cCharAt);
                z = true;
            }
        }
        if (z) {
            return chValueOf;
        }
        return null;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> List<R> windowed(@NotNull CharSequence charSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        int length = charSequence.length();
        int i3 = 0;
        ArrayList arrayList = new ArrayList((length / i2) + (length % i2 == 0 ? 0 : 1));
        while (i3 >= 0 && i3 < length) {
            int i4 = i3 + i;
            if (i4 < 0 || i4 > length) {
                if (!z) {
                    break;
                }
                i4 = length;
            }
            arrayList.add(transform.invoke(charSequence.subSequence(i3, i4)));
            i3 += i2;
        }
        return arrayList;
    }

    public static /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowed(charSequence, i, i2, z, function1);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <R> Sequence<R> windowedSequence(@NotNull final CharSequence charSequence, final int i, int i2, boolean z, @NotNull final Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        return SequencesKt___SequencesKt.map(CollectionsKt___CollectionsKt.asSequence(RangesKt___RangesKt.step(z ? StringsKt__StringsKt.getIndices(charSequence) : RangesKt___RangesKt.until(0, (charSequence.length() - i) + 1), i2)), new Function1() { // from class: kotlin.text.StringsKt___StringsKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StringsKt___StringsKt.windowedSequence$lambda$25$StringsKt___StringsKt(i, charSequence, transform, ((Integer) obj).intValue());
            }
        });
    }

    public static /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return windowedSequence(charSequence, i, i2, z, function1);
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            destination.put(keySelector.invoke(Character.valueOf(cCharAt)), valueTransform.invoke(Character.valueOf(cCharAt)));
        }
        return destination;
    }

    @NotNull
    public static final String filterNot(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    @NotNull
    public static final String slice(@NotNull String str, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt__StringsKt.substring(str, indices);
    }

    @NotNull
    public static final String filterIndexed(@NotNull String str, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
            i++;
            i2 = i3;
        }
        return sb.toString();
    }

    public static final char first(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                return cCharAt;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    public static final char last(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                char cCharAt = charSequence.charAt(length);
                if (!predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return cCharAt;
                }
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull CharSequence charSequence, @NotNull CharSequence other) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(charSequence.length(), other.length());
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Character.valueOf(charSequence.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(charSequence.length());
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            linkedHashMap.put(keySelector.invoke(Character.valueOf(cCharAt)), valueTransform.invoke(Character.valueOf(cCharAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final String filter(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static final char single(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Character chValueOf = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                if (!z) {
                    chValueOf = Character.valueOf(cCharAt);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(chValueOf, "null cannot be cast to non-null type kotlin.Char");
            return chValueOf.charValue();
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            sb.append(charSequence.charAt(it.next().intValue()));
        }
        return sb;
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final List<Pair<Character, Character>> zipWithNext(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            i++;
            arrayList.add(new Pair(Character.valueOf(cCharAt), Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final Pair<String, String> partition(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (predicate.invoke(Character.valueOf(cCharAt)).booleanValue()) {
                sb.append(cCharAt);
            } else {
                sb2.append(cCharAt);
            }
        }
        return new Pair<>(sb.toString(), sb2.toString());
    }

    @NotNull
    public static final String dropLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(str); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(str.charAt(lastIndex))).booleanValue()) {
                String strSubstring = str.substring(0, lastIndex + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return strSubstring;
            }
        }
        return "";
    }

    @NotNull
    public static final String takeLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = StringsKt__StringsKt.getLastIndex(str); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(str.charAt(lastIndex))).booleanValue()) {
                String strSubstring = str.substring(lastIndex + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return strSubstring;
            }
        }
        return str;
    }

    @NotNull
    public static String drop(@NotNull String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            int length = str.length();
            if (i > length) {
                i = length;
            }
            String strSubstring = str.substring(i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static String dropLast(@NotNull String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            int length = str.length() - i;
            if (length < 0) {
                length = 0;
            }
            return take(str, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            K kInvoke = keySelector.invoke(Character.valueOf(cCharAt));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Character.valueOf(cCharAt)));
        }
        return destination;
    }

    @InlineOnly
    public static final String slice(String str, Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return slice((CharSequence) str, indices).toString();
    }

    @NotNull
    public static final String take(@NotNull String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            int length = str.length();
            if (i > length) {
                i = length;
            }
            String strSubstring = str.substring(0, i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            K kInvoke = keySelector.invoke(Character.valueOf(cCharAt));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Character.valueOf(cCharAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final String takeLast(@NotNull String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i >= 0) {
            int length = str.length();
            if (i > length) {
                i = length;
            }
            String strSubstring = str.substring(length - i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested character count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final String dropWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                String strSubstring = str.substring(i);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return strSubstring;
            }
        }
        return "";
    }

    @NotNull
    public static final String takeWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                String strSubstring = str.substring(0, i);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return strSubstring;
            }
        }
        return str;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m1893maxOfOrNull(CharSequence charSequence, Function1<? super Character, Double> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        double dDoubleValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).doubleValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).doubleValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m1897minOfOrNull(CharSequence charSequence, Function1<? super Character, Double> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        double dDoubleValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).doubleValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).doubleValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m1891maxOf(CharSequence charSequence, Function1<? super Character, Float> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") != 0) {
            float fFloatValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).floatValue();
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).floatValue());
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m1895minOf(CharSequence charSequence, Function1<? super Character, Float> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") != 0) {
            float fFloatValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).floatValue();
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).floatValue());
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m1894maxOfOrNull(CharSequence charSequence, Function1<? super Character, Float> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        float fFloatValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).floatValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).floatValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1898minOfOrNull(CharSequence charSequence, Function1<? super Character, Float> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") == 0) {
            return null;
        }
        float fFloatValue = ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1)).floatValue();
        int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, ((Number) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).floatValue());
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m1892maxOf(CharSequence charSequence, Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") != 0) {
            R r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1);
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Comparable comparable = (Comparable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1);
                    if (r.compareTo(comparable) < 0) {
                        r = (R) comparable;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m1896minOf(CharSequence charSequence, Function1<? super Character, ? extends R> function1) {
        if (StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "selector") != 0) {
            R r = (R) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, 0, function1);
            int lastIndex = StringsKt__StringsKt.getLastIndex(charSequence);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Comparable comparable = (Comparable) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1);
                    if (r.compareTo(comparable) > 0) {
                        r = (R) comparable;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }
}
