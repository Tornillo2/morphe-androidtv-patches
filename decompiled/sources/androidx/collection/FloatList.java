package androidx.collection;

import android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0;
import java.util.NoSuchElementException;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFloatList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatList\n*L\n1#1,969:1\n253#1,6:970\n279#1,6:976\n253#1,6:982\n75#1:988\n253#1,6:989\n253#1,6:995\n253#1,6:1001\n266#1,6:1007\n279#1,6:1013\n293#1,6:1019\n70#1:1025\n70#1:1026\n266#1,6:1027\n266#1,6:1033\n293#1,6:1039\n70#1:1045\n279#1,6:1046\n293#1,6:1052\n266#1,6:1058\n266#1,6:1064\n253#1,6:1070\n75#1:1076\n467#1,10:1077\n266#1,4:1087\n477#1,9:1091\n271#1:1100\n486#1,2:1101\n467#1,10:1103\n266#1,4:1113\n477#1,9:1117\n271#1:1126\n486#1,2:1127\n467#1,10:1129\n266#1,4:1139\n477#1,9:1143\n271#1:1152\n486#1,2:1153\n467#1,10:1155\n266#1,4:1165\n477#1,9:1169\n271#1:1178\n486#1,2:1179\n467#1,10:1181\n266#1,4:1191\n477#1,9:1195\n271#1:1204\n486#1,2:1205\n*S KotlinDebug\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatList\n*L\n96#1:970,6\n110#1:976,6\n122#1:982,6\n135#1:988\n153#1:989,6\n175#1:995,6\n192#1:1001,6\n208#1:1007,6\n225#1:1013,6\n241#1:1019,6\n306#1:1025\n317#1:1026\n343#1:1027,6\n357#1:1033,6\n371#1:1039,6\n397#1:1045\n407#1:1046,6\n420#1:1052,6\n445#1:1058,6\n476#1:1064,6\n494#1:1070,6\n510#1:1076\n-1#1:1077,10\n-1#1:1087,4\n-1#1:1091,9\n-1#1:1100\n-1#1:1101,2\n-1#1:1103,10\n-1#1:1113,4\n-1#1:1117,9\n-1#1:1126\n-1#1:1127,2\n-1#1:1129,10\n-1#1:1139,4\n-1#1:1143,9\n-1#1:1152\n-1#1:1153,2\n-1#1:1155,10\n-1#1:1165,4\n-1#1:1169,9\n-1#1:1178\n-1#1:1179,2\n-1#1:1181,10\n-1#1:1191,4\n-1#1:1195,9\n-1#1:1204\n-1#1:1205,2\n*E\n"})
public abstract class FloatList {

    @JvmField
    public int _size;

    @JvmField
    @NotNull
    public float[] content;

    public /* synthetic */ FloatList(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public static /* synthetic */ String joinToString$default(FloatList floatList, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        CharSequence charSequence6 = charSequence3;
        return floatList.joinToString(charSequence, charSequence2, charSequence6, i, charSequence5);
    }

    public final boolean any() {
        return isNotEmpty();
    }

    public final boolean contains(float f) {
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (fArr[i2] == f) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsAll(@NotNull FloatList elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, elements._size);
        int i = intRangeUntil.first;
        int i2 = intRangeUntil.last;
        if (i > i2) {
            return true;
        }
        while (contains(elements.get(i))) {
            if (i == i2) {
                return true;
            }
            i++;
        }
        return false;
    }

    public final int count() {
        return this._size;
    }

    public final float elementAt(@androidx.annotation.IntRange(from = 0) int i) {
        if (i >= 0 && i < this._size) {
            return this.content[i];
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size - 1);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    public final float elementAtOrElse(@androidx.annotation.IntRange(from = 0) int i, @NotNull Function1<? super Integer, Float> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= this._size) ? defaultValue.invoke(Integer.valueOf(i)).floatValue() : this.content[i];
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof FloatList) {
            FloatList floatList = (FloatList) obj;
            int i = floatList._size;
            int i2 = this._size;
            if (i == i2) {
                float[] fArr = this.content;
                float[] fArr2 = floatList.content;
                IntRange intRangeUntil = RangesKt___RangesKt.until(0, i2);
                int i3 = intRangeUntil.first;
                int i4 = intRangeUntil.last;
                if (i3 > i4) {
                    return true;
                }
                while (fArr[i3] == fArr2[i3]) {
                    if (i3 == i4) {
                        return true;
                    }
                    i3++;
                }
                return false;
            }
        }
        return false;
    }

    public final float first() {
        if (isEmpty()) {
            throw new NoSuchElementException("FloatList is empty.");
        }
        return this.content[0];
    }

    public final <R> R fold(R r, @NotNull Function2<? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            r = operation.invoke(r, Float.valueOf(fArr[i2]));
        }
        return r;
    }

    public final <R> R foldIndexed(R r, @NotNull Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Float.valueOf(fArr[i2]));
        }
        return r;
    }

    public final <R> R foldRight(R r, @NotNull Function2<? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        float[] fArr = this.content;
        int i = this._size;
        while (true) {
            i--;
            if (-1 >= i) {
                return r;
            }
            r = operation.invoke(Float.valueOf(fArr[i]), r);
        }
    }

    public final <R> R foldRightIndexed(R r, @NotNull Function3<? super Integer, ? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        float[] fArr = this.content;
        int i = this._size;
        while (true) {
            i--;
            if (-1 >= i) {
                return r;
            }
            r = operation.invoke(Integer.valueOf(i), Float.valueOf(fArr[i]), r);
        }
    }

    public final void forEach(@NotNull Function1<? super Float, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            block.invoke(Float.valueOf(fArr[i2]));
        }
    }

    public final void forEachIndexed(@NotNull Function2<? super Integer, ? super Float, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            block.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i2]));
        }
    }

    public final void forEachReversed(@NotNull Function1<? super Float, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.content;
        int i = this._size;
        while (true) {
            i--;
            if (-1 >= i) {
                return;
            } else {
                block.invoke(Float.valueOf(fArr[i]));
            }
        }
    }

    public final void forEachReversedIndexed(@NotNull Function2<? super Integer, ? super Float, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.content;
        int i = this._size;
        while (true) {
            i--;
            if (-1 >= i) {
                return;
            } else {
                block.invoke(Integer.valueOf(i), Float.valueOf(fArr[i]));
            }
        }
    }

    public final float get(@androidx.annotation.IntRange(from = 0) int i) {
        if (i >= 0 && i < this._size) {
            return this.content[i];
        }
        StringBuilder sbM = MediaBrowserCompat$CustomActionResultReceiver$$ExternalSyntheticOutline0.m("Index ", i, " must be in 0..");
        sbM.append(this._size - 1);
        throw new IndexOutOfBoundsException(sbM.toString());
    }

    @NotNull
    public final IntRange getIndices() {
        return RangesKt___RangesKt.until(0, this._size);
    }

    @androidx.annotation.IntRange(from = -1)
    public final int getLastIndex() {
        return this._size - 1;
    }

    @androidx.annotation.IntRange(from = 0)
    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        float[] fArr = this.content;
        int i = this._size;
        int iFloatToIntBits = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iFloatToIntBits += Float.floatToIntBits(fArr[i2]) * 31;
        }
        return iFloatToIntBits;
    }

    public final int indexOf(float f) {
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (f == fArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public final int indexOfFirst(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (predicate.invoke(Float.valueOf(fArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public final int indexOfLast(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        do {
            i--;
            if (-1 >= i) {
                return -1;
            }
        } while (!predicate.invoke(Float.valueOf(fArr[i])).booleanValue());
        return i;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString() {
        return joinToString$default(this, null, null, null, 0, null, 31, null);
    }

    public final float last() {
        if (isEmpty()) {
            throw new NoSuchElementException("FloatList is empty.");
        }
        return this.content[this._size - 1];
    }

    public final int lastIndexOf(float f) {
        float[] fArr = this.content;
        int i = this._size;
        do {
            i--;
            if (-1 >= i) {
                return -1;
            }
        } while (fArr[i] != f);
        return i;
    }

    public final boolean none() {
        return isEmpty();
    }

    public final boolean reversedAny(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        for (int i = this._size - 1; -1 < i; i--) {
            if (predicate.invoke(Float.valueOf(fArr[i])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }

    public FloatList(int i) {
        this.content = i == 0 ? FloatSetKt.getEmptyFloatArray() : new float[i];
    }

    public final boolean any(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (predicate.invoke(Float.valueOf(fArr[i2])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final int count(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (predicate.invoke(Float.valueOf(fArr[i3])).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        return joinToString$default(this, separator, null, null, 0, null, 30, null);
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return joinToString$default(this, separator, prefix, null, 0, null, 28, null);
    }

    public final float first(@NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            float f = fArr[i2];
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return f;
            }
        }
        throw new NoSuchElementException("FloatList contains no element matching the predicate.");
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, 24, null);
    }

    public static /* synthetic */ String joinToString$default(FloatList floatList, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence charSequence, Function1 function1, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                separator = ", ";
            }
            if ((i2 & 2) != 0) {
                prefix = "";
            }
            if ((i2 & 4) != 0) {
                postfix = "";
            }
            if ((i2 & 8) != 0) {
                i = -1;
            }
            if ((i2 & 16) != 0) {
                charSequence = "...";
            }
            Intrinsics.checkNotNullParameter(separator, "separator");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(postfix, "postfix");
            StringBuilder sbM = FloatList$$ExternalSyntheticOutline0.m(charSequence, "truncated", function1, "transform", prefix);
            float[] fArr = floatList.content;
            int i3 = floatList._size;
            int i4 = 0;
            while (true) {
                if (i4 < i3) {
                    float f = fArr[i4];
                    if (i4 == i) {
                        sbM.append(charSequence);
                        break;
                    }
                    if (i4 != 0) {
                        sbM.append(separator);
                    }
                    sbM.append((CharSequence) function1.invoke(Float.valueOf(f)));
                    i4++;
                } else {
                    sbM.append(postfix);
                    break;
                }
            }
            String string = sbM.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, i, null, 16, null);
    }

    public final float last(@NotNull Function1<? super Float, Boolean> predicate) {
        float f;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.content;
        int i = this._size;
        do {
            i--;
            if (-1 < i) {
                f = fArr[i];
            } else {
                throw new NoSuchElementException("FloatList contains no element matching the predicate.");
            }
        } while (!predicate.invoke(Float.valueOf(f)).booleanValue());
        return f;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        StringBuilder sbM = FloatFloatMap$$ExternalSyntheticOutline2.m(charSequence, "postfix", charSequence2, "truncated", prefix);
        float[] fArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                float f = fArr[i3];
                if (i3 == i) {
                    sbM.append(charSequence2);
                    break;
                }
                if (i3 != 0) {
                    sbM.append(separator);
                }
                sbM.append(f);
                i3++;
            } else {
                sbM.append(charSequence);
                break;
            }
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @PublishedApi
    public static /* synthetic */ void getContent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void get_size$annotations() {
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence charSequence, @NotNull Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        StringBuilder sbM = FloatList$$ExternalSyntheticOutline0.m(charSequence, "truncated", function1, "transform", prefix);
        float[] fArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                float f = fArr[i3];
                if (i3 == i) {
                    sbM.append(charSequence);
                    break;
                }
                if (i3 != 0) {
                    sbM.append(separator);
                }
                sbM.append(function1.invoke(Float.valueOf(f)));
                i3++;
            } else {
                sbM.append(postfix);
                break;
            }
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence charSequence, int i, @NotNull Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        StringBuilder sbM = FloatList$$ExternalSyntheticOutline0.m(charSequence, "postfix", function1, "transform", prefix);
        float[] fArr = this.content;
        int i2 = this._size;
        int i3 = 0;
        while (true) {
            if (i3 < i2) {
                float f = fArr[i3];
                if (i3 == i) {
                    sbM.append((CharSequence) "...");
                    break;
                }
                if (i3 != 0) {
                    sbM.append(separator);
                }
                sbM.append(function1.invoke(Float.valueOf(f)));
                i3++;
            } else {
                sbM.append(charSequence);
                break;
            }
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence charSequence, @NotNull Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        StringBuilder sbM = FloatList$$ExternalSyntheticOutline0.m(charSequence, "postfix", function1, "transform", prefix);
        float[] fArr = this.content;
        int i = this._size;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                float f = fArr[i2];
                if (i2 == -1) {
                    sbM.append((CharSequence) "...");
                    break;
                }
                if (i2 != 0) {
                    sbM.append(separator);
                }
                sbM.append(function1.invoke(Float.valueOf(f)));
                i2++;
            } else {
                sbM.append(charSequence);
                break;
            }
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence charSequence, @NotNull Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        StringBuilder sbM = FloatList$$ExternalSyntheticOutline0.m(charSequence, "prefix", function1, "transform", charSequence);
        float[] fArr = this.content;
        int i = this._size;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                float f = fArr[i2];
                if (i2 == -1) {
                    sbM.append((CharSequence) "...");
                    break;
                }
                if (i2 != 0) {
                    sbM.append(separator);
                }
                sbM.append(function1.invoke(Float.valueOf(f)));
                i2++;
            } else {
                sbM.append((CharSequence) "");
                break;
            }
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull Function1<? super Float, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(transform, "transform");
        StringBuilder sb = new StringBuilder("");
        float[] fArr = this.content;
        int i = this._size;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                float f = fArr[i2];
                if (i2 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i2 != 0) {
                    sb.append(separator);
                }
                sb.append(transform.invoke(Float.valueOf(f)));
                i2++;
            } else {
                sb.append((CharSequence) "");
                break;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull Function1<? super Float, ? extends CharSequence> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        StringBuilder sb = new StringBuilder("");
        float[] fArr = this.content;
        int i = this._size;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                float f = fArr[i2];
                if (i2 == -1) {
                    sb.append((CharSequence) "...");
                    break;
                }
                if (i2 != 0) {
                    sb.append((CharSequence) ", ");
                }
                sb.append(transform.invoke(Float.valueOf(f)));
                i2++;
            } else {
                sb.append((CharSequence) "");
                break;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
