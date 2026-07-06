package kotlin.text;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline0;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1579:1\n80#1,22:1580\n114#1,5:1602\n131#1,5:1607\n80#1,22:1612\n108#1:1634\n80#1,22:1635\n114#1,5:1657\n125#1:1662\n114#1,5:1663\n131#1,5:1668\n142#1:1673\n131#1,5:1674\n80#1,22:1679\n114#1,5:1701\n131#1,5:1706\n1069#2,2:1711\n12717#3,2:1713\n12717#3,2:1715\n295#4,2:1717\n295#4,2:1719\n1563#4:1722\n1634#4,3:1723\n1563#4:1726\n1634#4,3:1727\n1#5:1721\n*S KotlinDebug\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n108#1:1580,22\n125#1:1602,5\n142#1:1607,5\n147#1:1612,22\n152#1:1634\n152#1:1635,22\n157#1:1657,5\n162#1:1662\n162#1:1663,5\n167#1:1668,5\n172#1:1673\n172#1:1674,5\n177#1:1679,22\n188#1:1701,5\n199#1:1706,5\n312#1:1711,2\n971#1:1713,2\n995#1:1715,2\n1034#1:1717,2\n1040#1:1719,2\n1401#1:1722\n1401#1:1723,3\n1426#1:1726\n1426#1:1727,3\n*E\n"})
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence charSequence, @NotNull CharSequence other, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(charSequence.length(), other.length());
        int i = 0;
        while (i < iMin && CharsKt__CharKt.equals(charSequence.charAt(i), other.charAt(i), z)) {
            i++;
        }
        int i2 = i - 1;
        if (hasSurrogatePairAt(charSequence, i2) || hasSurrogatePairAt(other, i2)) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence charSequence, @NotNull CharSequence other, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = charSequence.length();
        int iMin = Math.min(length, other.length());
        int i = 0;
        while (i < iMin && CharsKt__CharKt.equals(charSequence.charAt((length - i) - 1), other.charAt((r1 - i) - 1), z)) {
            i++;
        }
        if (hasSurrogatePairAt(charSequence, (length - i) - 1) || hasSurrogatePairAt(other, (r1 - i) - 1)) {
            i--;
        }
        return charSequence.subSequence(length - i, length).toString();
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return commonSuffixWith(charSequence, charSequence2, z);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, @NotNull CharSequence other, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return other instanceof String ? indexOf$default(charSequence, (String) other, 0, z, 2, (Object) null) >= 0 : indexOf$StringsKt__StringsKt$default(charSequence, other, 0, charSequence.length(), z, false, 16, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return contains(charSequence, charSequence2, z);
    }

    public static final boolean contentEqualsIgnoreCaseImpl(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).equalsIgnoreCase((String) charSequence2);
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || charSequence.length() != charSequence2.length()) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt__CharKt.equals(charSequence.charAt(i), charSequence2.charAt(i), true)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean contentEqualsImpl(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || charSequence.length() != charSequence2.length()) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() > 0 && CharsKt__CharKt.equals(charSequence.charAt(getLastIndex(charSequence)), c, z);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, c, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> strings, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(charSequence, strings, i, z, false);
    }

    public static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        IntProgression intProgressionDownTo;
        Object next;
        boolean z3;
        Object next2;
        if (!z && collection.size() == 1) {
            String str = (String) CollectionsKt___CollectionsKt.single(collection);
            int iIndexOf$default = !z2 ? indexOf$default(charSequence, str, i, false, 4, (Object) null) : lastIndexOf$default(charSequence, str, i, false, 4, (Object) null);
            if (iIndexOf$default < 0) {
                return null;
            }
            return new Pair<>(Integer.valueOf(iIndexOf$default), str);
        }
        if (z2) {
            int lastIndex = getLastIndex(charSequence);
            if (i <= lastIndex) {
                lastIndex = i;
            }
            intProgressionDownTo = RangesKt___RangesKt.downTo(lastIndex, 0);
        } else {
            intProgressionDownTo = new IntRange(i >= 0 ? i : 0, charSequence.length(), 1);
        }
        if (charSequence instanceof String) {
            int i2 = intProgressionDownTo.first;
            int i3 = intProgressionDownTo.last;
            int i4 = intProgressionDownTo.step;
            if ((i4 > 0 && i2 <= i3) || (i4 < 0 && i3 <= i2)) {
                int i5 = i2;
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z3 = z;
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        z3 = z;
                        if (StringsKt__StringsJVMKt.regionMatches(str2, 0, (String) charSequence, i5, str2.length(), z3)) {
                            break;
                        }
                        z = z3;
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (i5 == i3) {
                            break;
                        }
                        i5 += i4;
                        z = z3;
                    } else {
                        return new Pair<>(Integer.valueOf(i5), str3);
                    }
                }
            }
        } else {
            boolean z4 = z;
            int i6 = intProgressionDownTo.first;
            int i7 = intProgressionDownTo.last;
            int i8 = intProgressionDownTo.step;
            if ((i8 > 0 && i6 <= i7) || (i8 < 0 && i7 <= i6)) {
                int i9 = i6;
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        boolean z5 = z4;
                        z4 = z5;
                        if (regionMatchesImpl(str4, 0, charSequence, i9, str4.length(), z5)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (i9 == i7) {
                            break;
                        }
                        i9 += i8;
                    } else {
                        return new Pair<>(Integer.valueOf(i9), str5);
                    }
                }
            }
        }
        return null;
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> strings, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(charSequence, strings, i, z, true);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return findLastAnyOf(charSequence, collection, i, z);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return new IntRange(0, charSequence.length() - 1, 1);
    }

    public static final int getLastIndex(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return i >= 0 && i <= charSequence.length() + (-2) && Character.isHighSurrogate(charSequence.charAt(i)) && Character.isLowSurrogate(charSequence.charAt(i + 1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <C extends CharSequence & R, R> R ifBlank(C c, Function0<? extends R> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return isBlank(c) ? defaultValue.invoke() : c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <C extends CharSequence & R, R> R ifEmpty(C c, Function0<? extends R> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return c.length() == 0 ? defaultValue.invoke() : c;
    }

    public static final int indexOf(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? indexOfAny(charSequence, new char[]{c}, i, z) : ((String) charSequence).indexOf(c, i);
    }

    public static final int indexOf$StringsKt__StringsKt(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        IntProgression intProgressionDownTo;
        if (z2) {
            int lastIndex = getLastIndex(charSequence);
            if (i > lastIndex) {
                i = lastIndex;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            intProgressionDownTo = RangesKt___RangesKt.downTo(i, i2);
        } else {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence.length();
            if (i2 > length) {
                i2 = length;
            }
            intProgressionDownTo = new IntRange(i, i2, 1);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int i3 = intProgressionDownTo.first;
            int i4 = intProgressionDownTo.last;
            int i5 = intProgressionDownTo.step;
            if ((i5 <= 0 || i3 > i4) && (i5 >= 0 || i4 > i3)) {
                return -1;
            }
            int i6 = i3;
            while (true) {
                String str = (String) charSequence2;
                boolean z3 = z;
                if (StringsKt__StringsJVMKt.regionMatches(str, 0, (String) charSequence, i6, str.length(), z3)) {
                    return i6;
                }
                if (i6 == i4) {
                    return -1;
                }
                i6 += i5;
                z = z3;
            }
        } else {
            boolean z4 = z;
            int i7 = intProgressionDownTo.first;
            int i8 = intProgressionDownTo.last;
            int i9 = intProgressionDownTo.step;
            if ((i9 <= 0 || i7 > i8) && (i9 >= 0 || i8 > i7)) {
                return -1;
            }
            int i10 = i7;
            while (true) {
                boolean z5 = z4;
                CharSequence charSequence3 = charSequence;
                CharSequence charSequence4 = charSequence2;
                z4 = z5;
                if (regionMatchesImpl(charSequence4, 0, charSequence3, i10, charSequence2.length(), z5)) {
                    return i10;
                }
                if (i10 == i8) {
                    return -1;
                }
                i10 += i9;
                charSequence2 = charSequence4;
                charSequence = charSequence3;
            }
        }
    }

    public static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, c, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull char[] chars, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!z && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(ArraysKt___ArraysKt.single(chars), i);
        }
        if (i < 0) {
            i = 0;
        }
        int lastIndex = getLastIndex(charSequence);
        if (i > lastIndex) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(i);
            for (char c : chars) {
                if (CharsKt__CharKt.equals(c, cCharAt, z)) {
                    return i;
                }
            }
            if (i == lastIndex) {
                return -1;
            }
            i++;
        }
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, cArr, i, z);
    }

    public static boolean isBlank(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        for (int i = 0; i < charSequence.length(); i++) {
            if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @InlineOnly
    public static final boolean isEmpty(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() == 0;
    }

    @InlineOnly
    public static final boolean isNotBlank(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return !isBlank(charSequence);
    }

    @InlineOnly
    public static final boolean isNotEmpty(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() > 0;
    }

    @InlineOnly
    public static final boolean isNullOrBlank(CharSequence charSequence) {
        return charSequence == null || isBlank(charSequence);
    }

    @InlineOnly
    public static final boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    @NotNull
    public static final CharIterator iterator(@NotNull final CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return new CharIterator() { // from class: kotlin.text.StringsKt__StringsKt.iterator.1
            public int index;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < charSequence.length();
            }

            @Override // kotlin.collections.CharIterator
            public char nextChar() {
                CharSequence charSequence2 = charSequence;
                int i = this.index;
                this.index = i + 1;
                return charSequence2.charAt(i);
            }
        };
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? lastIndexOfAny(charSequence, new char[]{c}, i, z) : ((String) charSequence).lastIndexOf(c, i);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, c, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull char[] chars, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!z && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(ArraysKt___ArraysKt.single(chars), i);
        }
        int lastIndex = getLastIndex(charSequence);
        if (i > lastIndex) {
            i = lastIndex;
        }
        while (-1 < i) {
            char cCharAt = charSequence.charAt(i);
            for (char c : chars) {
                if (CharsKt__CharKt.equals(c, cCharAt, z)) {
                    return i;
                }
            }
            i--;
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, cArr, i, z);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull final CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return new Sequence<String>() { // from class: kotlin.text.StringsKt__StringsKt$lineSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<String> iterator() {
                return new LinesIterator(charSequence);
            }
        };
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return SequencesKt___SequencesKt.toList(lineSequence(charSequence));
    }

    @InlineOnly
    public static final boolean matches(CharSequence charSequence, Regex regex) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.matches(charSequence);
    }

    @InlineOnly
    public static final String orEmpty(String str) {
        return str == null ? "" : str;
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Desired length ", i, " is less than zero."));
        }
        if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(i);
        sb.append(charSequence);
        int length = i - charSequence.length();
        int i2 = 1;
        if (1 <= length) {
            while (true) {
                sb.append(c);
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return sb;
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padEnd(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Desired length ", i, " is less than zero."));
        }
        if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(i);
        int length = i - charSequence.length();
        int i2 = 1;
        if (1 <= length) {
            while (true) {
                sb.append(c);
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        sb.append(charSequence);
        return sb;
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padStart(charSequence, i, c);
    }

    public static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence charSequence, final char[] cArr, int i, final boolean z, int i2) {
        requireNonNegativeLimit(i2);
        return new DelimitedRangesSequence(charSequence, i, i2, new Function2() { // from class: kotlin.text.StringsKt__StringsKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return StringsKt__StringsKt.rangesDelimitedBy$lambda$14$StringsKt__StringsKt(cArr, z, (CharSequence) obj, ((Integer) obj2).intValue());
            }
        });
    }

    public static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArr, i, z, i2);
    }

    public static final Pair rangesDelimitedBy$lambda$14$StringsKt__StringsKt(char[] cArr, boolean z, CharSequence DelimitedRangesSequence, int i) {
        Intrinsics.checkNotNullParameter(DelimitedRangesSequence, "$this$DelimitedRangesSequence");
        int iIndexOfAny = indexOfAny(DelimitedRangesSequence, cArr, i, z);
        if (iIndexOfAny < 0) {
            return null;
        }
        return new Pair(Integer.valueOf(iIndexOfAny), 1);
    }

    public static final Pair rangesDelimitedBy$lambda$16$StringsKt__StringsKt(List list, boolean z, CharSequence DelimitedRangesSequence, int i) {
        Intrinsics.checkNotNullParameter(DelimitedRangesSequence, "$this$DelimitedRangesSequence");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(DelimitedRangesSequence, list, i, z, false);
        if (pairFindAnyOf$StringsKt__StringsKt != null) {
            return new Pair(pairFindAnyOf$StringsKt__StringsKt.first, Integer.valueOf(pairFindAnyOf$StringsKt__StringsKt.second.length()));
        }
        return null;
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence charSequence, int i, @NotNull CharSequence other, int i2, int i3, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > other.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!CharsKt__CharKt.equals(charSequence.charAt(i + i4), other.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence charSequence, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return startsWith$default(charSequence, prefix, false, 2, (Object) null) ? charSequence.subSequence(prefix.length(), charSequence.length()) : charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        return removeRange(charSequence, range.first, range.last + 1);
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence charSequence, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return endsWith$default(charSequence, suffix, false, 2, (Object) null) ? charSequence.subSequence(0, charSequence.length() - suffix.length()) : charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return (charSequence.length() >= suffix.length() + prefix.length() && startsWith$default(charSequence, prefix, false, 2, (Object) null) && endsWith$default(charSequence, suffix, false, 2, (Object) null)) ? charSequence.subSequence(prefix.length(), charSequence.length() - suffix.length()) : charSequence.subSequence(0, charSequence.length());
    }

    @InlineOnly
    public static final String replace(CharSequence charSequence, Regex regex, String replacement) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return regex.replace(charSequence, replacement);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String str, char c, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        return iIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, iIndexOf$default + 1, str.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfter$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceAfter(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String str, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        return iLastIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, delimiter.length() + iLastIndexOf$default, str.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceAfterLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String str, char c, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        return iIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, 0, iIndexOf$default, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceBefore$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceBefore(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String str, char c, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        return iLastIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, 0, iLastIndexOf$default, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceBeforeLast(str, c, str2, str3);
    }

    @InlineOnly
    public static final String replaceFirst(CharSequence charSequence, Regex regex, String replacement) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return regex.replaceFirst(charSequence, replacement);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "replaceFirstCharWithChar")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String replaceFirstCharWithChar(String str, Function1<? super Character, Character> transform) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (str.length() <= 0) {
            return str;
        }
        char cCharValue = transform.invoke(Character.valueOf(str.charAt(0))).charValue();
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return cCharValue + strSubstring;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "replaceFirstCharWithCharSequence")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String replaceFirstCharWithCharSequence(String str, Function1<? super Character, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (str.length() <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((Object) transform.invoke(Character.valueOf(str.charAt(0))));
        String strSubstring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        sb.append(strSubstring);
        return sb.toString();
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, @NotNull IntRange range, @NotNull CharSequence replacement) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return replaceRange(charSequence, range.first, range.last + 1, replacement);
    }

    public static final void requireNonNegativeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Limit must be non-negative, but was ", i).toString());
        }
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull String[] delimiters, boolean z, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            String str = delimiters[0];
            if (str.length() != 0) {
                return split$StringsKt__StringsKt(charSequence, str, z, i);
            }
        }
        Iterable iterableAsIterable = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, delimiters, 0, z, i, 2, (Object) null));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterableAsIterable, 10));
        Iterator it = ((SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1) iterableAsIterable).iterator();
        while (it.hasNext()) {
            arrayList.add(substring(charSequence, (IntRange) it.next()));
        }
        return arrayList;
    }

    public static final List<String> split$StringsKt__StringsKt(CharSequence charSequence, String str, boolean z, int i) {
        requireNonNegativeLimit(i);
        int length = 0;
        int iIndexOf = indexOf(charSequence, str, 0, z);
        if (iIndexOf == -1 || i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(charSequence.toString());
        }
        boolean z2 = i > 0;
        int i2 = 10;
        if (z2 && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        do {
            arrayList.add(charSequence.subSequence(length, iIndexOf).toString());
            length = str.length() + iIndexOf;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            iIndexOf = indexOf(charSequence, str, length, z);
        } while (iIndexOf != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return split(charSequence, strArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence charSequence, @NotNull String[] delimiters, boolean z, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, delimiters, 0, z, i, 2, (Object) null), new Function1() { // from class: kotlin.text.StringsKt__StringsKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StringsKt__StringsKt.splitToSequence$lambda$18$StringsKt__StringsKt(charSequence, (IntRange) obj);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return splitToSequence(charSequence, strArr, z, i);
    }

    public static final String splitToSequence$lambda$18$StringsKt__StringsKt(CharSequence charSequence, IntRange it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return substring(charSequence, it);
    }

    public static final String splitToSequence$lambda$20$StringsKt__StringsKt(CharSequence charSequence, IntRange it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return substring(charSequence, it);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() > 0 && CharsKt__CharKt.equals(charSequence.charAt(0), c, z);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, c, z);
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence charSequence, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        return charSequence.subSequence(range.first, range.last + 1);
    }

    @NotNull
    public static final String substring(@NotNull CharSequence charSequence, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        return charSequence.subSequence(range.first, range.last + 1).toString();
    }

    public static /* synthetic */ String substring$default(CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.subSequence(i, i2).toString();
    }

    @NotNull
    public static String substringAfter(@NotNull String str, char c, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iIndexOf$default + 1, str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfter$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringAfter(str, c, str2);
    }

    @NotNull
    public static String substringAfterLast(@NotNull String str, char c, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iLastIndexOf$default + 1, str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfterLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringAfterLast(str, c, str2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String str, char c, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String substringBefore$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringBefore(str, c, str2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String str, char c, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iLastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return substringBeforeLast(str, c, str2);
    }

    @SinceKotlin(version = "1.5")
    public static boolean toBooleanStrict(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.equals("true")) {
            return true;
        }
        if (str.equals("false")) {
            return false;
        }
        throw new IllegalArgumentException("The string doesn't represent a boolean value: ".concat(str));
    }

    @SinceKotlin(version = "1.5")
    @Nullable
    public static Boolean toBooleanStrictOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.equals("true")) {
            return Boolean.TRUE;
        }
        if (str.equals("false")) {
            return Boolean.FALSE;
        }
        return null;
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zBooleanValue = ((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, !z ? i : length, predicate)).booleanValue();
            if (z) {
                if (!zBooleanValue) {
                    break;
                }
                length--;
            } else if (zBooleanValue) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = charSequence.length() - 1;
        if (length < 0) {
            return "";
        }
        while (true) {
            int i = length - 1;
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, length, predicate)).booleanValue()) {
                return charSequence.subSequence(0, length + 1);
            }
            if (i < 0) {
                return "";
            }
            length = i;
        }
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int iM = StringsKt__StringsKt$$ExternalSyntheticOutline0.m(charSequence, "<this>", function1, "predicate");
        for (int i = 0; i < iM; i++) {
            if (!((Boolean) StringsKt__StringsKt$$ExternalSyntheticOutline1.m(charSequence, i, function1)).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return contains(charSequence, c, z);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, @NotNull CharSequence suffix, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return (!z && (charSequence instanceof String) && (suffix instanceof String)) ? StringsKt__StringsJVMKt.endsWith$default((String) charSequence, (String) suffix, false, 2, null) : regionMatchesImpl(charSequence, charSequence.length() - suffix.length(), suffix, 0, suffix.length(), z);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return endsWith(charSequence, charSequence2, z);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOf(charSequence, str, i, z);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return indexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOf(charSequence, str, i, z);
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lastIndexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static /* synthetic */ String padEnd$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padEnd(str, i, c);
    }

    public static /* synthetic */ String padStart$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return padStart(str, i, c);
    }

    public static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, strArr, i, z, i2);
    }

    @InlineOnly
    public static final String replace(CharSequence charSequence, Regex regex, Function1<? super MatchResult, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return regex.replace(charSequence, transform);
    }

    public static /* synthetic */ String replaceAfter$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceAfter(str, str2, str3, str4);
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return replaceAfterLast(str, c, str2, str3);
    }

    public static /* synthetic */ String replaceBefore$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceBefore(str, str2, str3, str4);
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return replaceBeforeLast(str, str2, str3, str4);
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return split(charSequence, cArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence charSequence, @NotNull char[] delimiters, boolean z, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt___SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, delimiters, 0, z, i, 2, (Object) null), new Function1() { // from class: kotlin.text.StringsKt__StringsKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return StringsKt__StringsKt.splitToSequence$lambda$20$StringsKt__StringsKt(charSequence, (IntRange) obj);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return splitToSequence(charSequence, cArr, z, i);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence prefix, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return (!z && (charSequence instanceof String) && (prefix instanceof String)) ? StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) prefix, false, 2, null) : regionMatchesImpl(charSequence, 0, prefix, 0, prefix.length(), z);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, z);
    }

    public static /* synthetic */ String substringAfter$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringAfter(str, str2, str3);
    }

    public static /* synthetic */ String substringAfterLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringAfterLast(str, str2, str3);
    }

    public static /* synthetic */ String substringBefore$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringBefore(str, str2, str3);
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return substringBeforeLast(str, str2, str3);
    }

    public static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence charSequence, String[] strArr, int i, final boolean z, int i2) {
        requireNonNegativeLimit(i2);
        final List listAsList = ArraysKt___ArraysJvmKt.asList(strArr);
        return new DelimitedRangesSequence(charSequence, i, i2, new Function2() { // from class: kotlin.text.StringsKt__StringsKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return StringsKt__StringsKt.rangesDelimitedBy$lambda$16$StringsKt__StringsKt(listAsList, z, (CharSequence) obj, ((Integer) obj2).intValue());
            }
        });
    }

    @NotNull
    public static final String replaceAfter(@NotNull String str, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        return iIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, delimiter.length() + iIndexOf$default, str.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String str, char c, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        return iLastIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, iLastIndexOf$default + 1, str.length(), (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceBefore(@NotNull String str, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        return iIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, 0, iIndexOf$default, (CharSequence) replacement).toString();
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String str, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        return iLastIndexOf$default == -1 ? missingDelimiterValue : replaceRange((CharSequence) str, 0, iLastIndexOf$default, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, Regex regex, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.split(charSequence, i);
    }

    @SinceKotlin(version = "1.6")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    public static final Sequence<String> splitToSequence(CharSequence charSequence, Regex regex, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.splitToSequence(charSequence, i);
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, Regex regex, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.splitToSequence(charSequence, i);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return startsWith(charSequence, charSequence2, i, z);
    }

    @NotNull
    public static final String substringAfter(@NotNull String str, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(delimiter.length() + iIndexOf$default, str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String str, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(delimiter.length() + iLastIndexOf$default, str.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final String substringBefore(@NotNull String str, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iIndexOf$default = indexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String str, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int iLastIndexOf$default = lastIndexOf$default((CharSequence) str, delimiter, 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(0, iLastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final boolean contains(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return indexOf$default(charSequence, c, 0, z, 2, (Object) null) >= 0;
    }

    public static final int indexOf(@NotNull CharSequence charSequence, @NotNull String string, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(string, i);
        }
        return indexOf$StringsKt__StringsKt$default(charSequence, string, i, charSequence.length(), z, false, 16, null);
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, @NotNull String string, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(string, i);
        }
        return indexOf$StringsKt__StringsKt(charSequence, string, i, 0, z, true);
    }

    @NotNull
    public static String removePrefix(@NotNull String str, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!startsWith$default((CharSequence) str, prefix, false, 2, (Object) null)) {
            return str;
        }
        String strSubstring = str.substring(prefix.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i2 < i) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline0.m("End index (", i2, ") is less than start index (", i, ")."));
        }
        if (i2 == i) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(charSequence.length() - (i2 - i));
        sb.append(charSequence, 0, i);
        sb.append(charSequence, i2, charSequence.length());
        return sb;
    }

    @NotNull
    public static String removeSuffix(@NotNull String str, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!endsWith$default((CharSequence) str, suffix, false, 2, (Object) null)) {
            return str;
        }
        String strSubstring = str.substring(0, str.length() - suffix.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (str.length() < suffix.length() + prefix.length() || !startsWith$default((CharSequence) str, prefix, false, 2, (Object) null) || !endsWith$default((CharSequence) str, suffix, false, 2, (Object) null)) {
            return str;
        }
        String strSubstring = str.substring(prefix.length(), str.length() - suffix.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, int i, int i2, @NotNull CharSequence replacement) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        if (i2 >= i) {
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence, 0, i);
            sb.append(replacement);
            sb.append(charSequence, i2, charSequence.length());
            return sb;
        }
        throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline0.m("End index (", i2, ") is less than start index (", i, ")."));
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    public static final CharSequence subSequence(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return str.subSequence(i, i2);
    }

    @NotNull
    public static final String substring(@NotNull String str, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        String strSubstring = str.substring(range.first, range.last + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @InlineOnly
    public static final boolean contains(CharSequence charSequence, Regex regex) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.containsMatchIn(charSequence);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence prefix, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!z && (charSequence instanceof String) && (prefix instanceof String)) {
            return StringsKt__StringsJVMKt.startsWith$default((String) charSequence, (String) prefix, i, false, 4, null);
        }
        return regionMatchesImpl(charSequence, i, prefix, 0, prefix.length(), z);
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return removeSurrounding(charSequence, delimiter, delimiter);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String str, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return removeSurrounding(str, delimiter, delimiter);
    }

    @InlineOnly
    public static final String substring(CharSequence charSequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return charSequence.subSequence(i, i2).toString();
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> strings, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(charSequence, strings, i, z, false);
        if (pairFindAnyOf$StringsKt__StringsKt != null) {
            return pairFindAnyOf$StringsKt__StringsKt.first.intValue();
        }
        return -1;
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> strings, int i, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(charSequence, strings, i, z, true);
        if (pairFindAnyOf$StringsKt__StringsKt != null) {
            return pairFindAnyOf$StringsKt__StringsKt.first.intValue();
        }
        return -1;
    }

    @InlineOnly
    public static final String trim(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return trim((CharSequence) str).toString();
    }

    @NotNull
    public static final String trim(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zBooleanValue = predicate.invoke(Character.valueOf(str.charAt(!z ? i : length))).booleanValue();
            if (z) {
                if (!zBooleanValue) {
                    break;
                }
                length--;
            } else if (zBooleanValue) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1).toString();
    }

    @InlineOnly
    public static final String trimEnd(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return trimEnd((CharSequence) str).toString();
    }

    @NotNull
    public static final String trimEnd(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        CharSequence charSequenceSubSequence;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = str.length() - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (!predicate.invoke(Character.valueOf(str.charAt(length))).booleanValue()) {
                    charSequenceSubSequence = str.subSequence(0, length + 1);
                    break;
                }
                if (i < 0) {
                    break;
                }
                length = i;
            }
            charSequenceSubSequence = "";
        } else {
            charSequenceSubSequence = "";
        }
        return charSequenceSubSequence.toString();
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull char[] delimiters, boolean z, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            return split$StringsKt__StringsKt(charSequence, String.valueOf(delimiters[0]), z, i);
        }
        Iterable iterableAsIterable = SequencesKt___SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, delimiters, 0, z, i, 2, (Object) null));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterableAsIterable, 10));
        Iterator it = ((SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1) iterableAsIterable).iterator();
        while (it.hasNext()) {
            arrayList.add(substring(charSequence, (IntRange) it.next()));
        }
        return arrayList;
    }

    @InlineOnly
    public static final String trimStart(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return trimStart((CharSequence) str).toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zContains = ArraysKt___ArraysKt.contains(chars, charSequence.charAt(!z ? i : length));
            if (z) {
                if (!zContains) {
                    break;
                }
                length--;
            } else if (zContains) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final String trimStart(@NotNull String str, @NotNull Function1<? super Character, Boolean> predicate) {
        CharSequence charSequenceSubSequence;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length) {
                if (!predicate.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                    charSequenceSubSequence = str.subSequence(i, str.length());
                    break;
                }
                i++;
            } else {
                charSequenceSubSequence = "";
                break;
            }
        }
        return charSequenceSubSequence.toString();
    }

    @NotNull
    public static final String padEnd(@NotNull String str, int i, char c) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return padEnd((CharSequence) str, i, c).toString();
    }

    @NotNull
    public static String padStart(@NotNull String str, int i, char c) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return padStart((CharSequence) str, i, c).toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = charSequence.length() - 1;
        if (length < 0) {
            return "";
        }
        while (true) {
            int i = length - 1;
            if (!ArraysKt___ArraysKt.contains(chars, charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1);
            }
            if (i < 0) {
                return "";
            }
            length = i;
        }
    }

    @NotNull
    public static final String trim(@NotNull String str, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zContains = ArraysKt___ArraysKt.contains(chars, str.charAt(!z ? i : length));
            if (z) {
                if (!zContains) {
                    break;
                }
                length--;
            } else if (zContains) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1).toString();
    }

    @InlineOnly
    public static final String removeRange(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return removeRange((CharSequence) str, i, i2).toString();
    }

    @InlineOnly
    public static final String replaceRange(String str, int i, int i2, CharSequence replacement) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return replaceRange((CharSequence) str, i, i2, replacement).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!ArraysKt___ArraysKt.contains(chars, charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @InlineOnly
    public static final String removeRange(String str, IntRange range) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        return removeRange((CharSequence) str, range).toString();
    }

    @InlineOnly
    public static final String replaceRange(String str, IntRange range, CharSequence replacement) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(range, "range");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return replaceRange((CharSequence) str, range, replacement).toString();
    }

    @InlineOnly
    public static final List<String> split(CharSequence charSequence, Regex regex, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        return regex.split(charSequence, i);
    }

    @NotNull
    public static final String trimEnd(@NotNull String str, @NotNull char... chars) {
        CharSequence charSequenceSubSequence;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = str.length() - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (!ArraysKt___ArraysKt.contains(chars, str.charAt(length))) {
                    charSequenceSubSequence = str.subSequence(0, length + 1);
                    break;
                }
                if (i < 0) {
                    break;
                }
                length = i;
            }
            charSequenceSubSequence = "";
        } else {
            charSequenceSubSequence = "";
        }
        return charSequenceSubSequence.toString();
    }

    @NotNull
    public static CharSequence trim(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zIsWhitespace = CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(!z ? i : length));
            if (z) {
                if (!zIsWhitespace) {
                    break;
                }
                length--;
            } else if (zIsWhitespace) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static String trimStart(@NotNull String str, @NotNull char... chars) {
        CharSequence charSequenceSubSequence;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length) {
                if (!ArraysKt___ArraysKt.contains(chars, str.charAt(i))) {
                    charSequenceSubSequence = str.subSequence(i, str.length());
                    break;
                }
                i++;
            } else {
                charSequenceSubSequence = "";
                break;
            }
        }
        return charSequenceSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length() - 1;
        if (length < 0) {
            return "";
        }
        while (true) {
            int i = length - 1;
            if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1);
            }
            if (i < 0) {
                return "";
            }
            length = i;
        }
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }
}
