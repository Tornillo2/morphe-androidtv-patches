package kotlin.collections;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_ArraysJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3051:1\n14151#2,14:3052\n14181#2,14:3066\n14211#2,14:3080\n14241#2,14:3094\n14271#2,14:3108\n14301#2,14:3122\n14331#2,14:3136\n14361#2,14:3150\n14391#2,14:3164\n17123#2,14:3178\n17153#2,14:3192\n17183#2,14:3206\n17213#2,14:3220\n17243#2,14:3234\n17273#2,14:3248\n17303#2,14:3262\n17333#2,14:3276\n17363#2,14:3290\n*S KotlinDebug\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt\n*L\n2443#1:3052,14\n2450#1:3066,14\n2457#1:3080,14\n2464#1:3094,14\n2471#1:3108,14\n2478#1:3122,14\n2485#1:3136,14\n2492#1:3150,14\n2499#1:3164,14\n2641#1:3178,14\n2648#1:3192,14\n2655#1:3206,14\n2662#1:3220,14\n2669#1:3234,14\n2676#1:3248,14\n2683#1:3262,14\n2690#1:3276,14\n2697#1:3290,14\n*E\n"})
public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 extends AbstractList<Byte> implements RandomAccess {
        public final /* synthetic */ byte[] $this_asList;

        public AnonymousClass1(byte[] bArr) {
            this.$this_asList = bArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Byte)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Number) obj).byteValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Byte)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Number) obj).byteValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Byte)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Number) obj).byteValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Byte get(int i) {
            return Byte.valueOf(this.$this_asList[i]);
        }

        public boolean contains(byte b) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, b);
        }

        public int indexOf(byte b) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, b);
        }

        public int lastIndexOf(byte b) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, b);
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass2 extends AbstractList<Short> implements RandomAccess {
        public final /* synthetic */ short[] $this_asList;

        public AnonymousClass2(short[] sArr) {
            this.$this_asList = sArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Short)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Number) obj).shortValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Short)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Number) obj).shortValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Short)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Number) obj).shortValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Short get(int i) {
            return Short.valueOf(this.$this_asList[i]);
        }

        public boolean contains(short s) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, s);
        }

        public int indexOf(short s) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, s);
        }

        public int lastIndexOf(short s) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, s);
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3 extends AbstractList<Integer> implements RandomAccess {
        public final /* synthetic */ int[] $this_asList;

        public AnonymousClass3(int[] iArr) {
            this.$this_asList = iArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Integer)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Number) obj).intValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Number) obj).intValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Number) obj).intValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Integer get(int i) {
            return Integer.valueOf(this.$this_asList[i]);
        }

        public boolean contains(int i) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, i);
        }

        public int indexOf(int i) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, i);
        }

        public int lastIndexOf(int i) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, i);
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass4 extends AbstractList<Long> implements RandomAccess {
        public final /* synthetic */ long[] $this_asList;

        public AnonymousClass4(long[] jArr) {
            this.$this_asList = jArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Long)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Number) obj).longValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Long)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Number) obj).longValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Long)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Number) obj).longValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Long get(int i) {
            return Long.valueOf(this.$this_asList[i]);
        }

        public boolean contains(long j) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, j);
        }

        public int indexOf(long j) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, j);
        }

        public int lastIndexOf(long j) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, j);
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\n_ArraysJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$5\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3051:1\n12687#2,2:3052\n1742#2,6:3054\n1850#2,6:3060\n*S KotlinDebug\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$5\n*L\n199#1:3052,2\n201#1:3054,6\n202#1:3060,6\n*E\n"})
    public static final class AnonymousClass5 extends AbstractList<Float> implements RandomAccess {
        public final /* synthetic */ float[] $this_asList;

        public AnonymousClass5(float[] fArr) {
            this.$this_asList = fArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Float) {
                return contains(((Number) obj).floatValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Float) {
                return indexOf(((Number) obj).floatValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Float) {
                return lastIndexOf(((Number) obj).floatValue());
            }
            return -1;
        }

        public boolean contains(float f) {
            for (float f2 : this.$this_asList) {
                if (Float.floatToIntBits(f2) == Float.floatToIntBits(f)) {
                    return true;
                }
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Float get(int i) {
            return Float.valueOf(this.$this_asList[i]);
        }

        public int indexOf(float f) {
            float[] fArr = this.$this_asList;
            int length = fArr.length;
            for (int i = 0; i < length; i++) {
                if (Float.floatToIntBits(fArr[i]) == Float.floatToIntBits(f)) {
                    return i;
                }
            }
            return -1;
        }

        public int lastIndexOf(float f) {
            float[] fArr = this.$this_asList;
            int length = fArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i = length - 1;
                    if (Float.floatToIntBits(fArr[length]) == Float.floatToIntBits(f)) {
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
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\n_ArraysJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$6\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3051:1\n12697#2,2:3052\n1754#2,6:3054\n1862#2,6:3060\n*S KotlinDebug\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt$asList$6\n*L\n213#1:3052,2\n215#1:3054,6\n216#1:3060,6\n*E\n"})
    public static final class AnonymousClass6 extends AbstractList<Double> implements RandomAccess {
        public final /* synthetic */ double[] $this_asList;

        public AnonymousClass6(double[] dArr) {
            this.$this_asList = dArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Double) {
                return contains(((Number) obj).doubleValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Double) {
                return indexOf(((Number) obj).doubleValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Double) {
                return lastIndexOf(((Number) obj).doubleValue());
            }
            return -1;
        }

        public boolean contains(double d) {
            for (double d2 : this.$this_asList) {
                if (Double.doubleToLongBits(d2) == Double.doubleToLongBits(d)) {
                    return true;
                }
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Double get(int i) {
            return Double.valueOf(this.$this_asList[i]);
        }

        public int indexOf(double d) {
            double[] dArr = this.$this_asList;
            int length = dArr.length;
            for (int i = 0; i < length; i++) {
                if (Double.doubleToLongBits(dArr[i]) == Double.doubleToLongBits(d)) {
                    return i;
                }
            }
            return -1;
        }

        public int lastIndexOf(double d) {
            double[] dArr = this.$this_asList;
            int length = dArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i = length - 1;
                    if (Double.doubleToLongBits(dArr[length]) == Double.doubleToLongBits(d)) {
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
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass7 extends AbstractList<Boolean> implements RandomAccess {
        public final /* synthetic */ boolean[] $this_asList;

        public AnonymousClass7(boolean[] zArr) {
            this.$this_asList = zArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Boolean)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Boolean) obj).booleanValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Boolean)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Boolean) obj).booleanValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Boolean)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Boolean) obj).booleanValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Boolean get(int i) {
            return Boolean.valueOf(this.$this_asList[i]);
        }

        public boolean contains(boolean z) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, z);
        }

        public int indexOf(boolean z) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, z);
        }

        public int lastIndexOf(boolean z) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, z);
        }
    }

    /* JADX INFO: renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$8, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass8 extends AbstractList<Character> implements RandomAccess {
        public final /* synthetic */ char[] $this_asList;

        public AnonymousClass8(char[] cArr) {
            this.$this_asList = cArr;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Character)) {
                return false;
            }
            return ArraysKt___ArraysKt.contains(this.$this_asList, ((Character) obj).charValue());
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (!(obj instanceof Character)) {
                return -1;
            }
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, ((Character) obj).charValue());
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (!(obj instanceof Character)) {
                return -1;
            }
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, ((Character) obj).charValue());
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Character get(int i) {
            return Character.valueOf(this.$this_asList[i]);
        }

        public boolean contains(char c) {
            return ArraysKt___ArraysKt.contains(this.$this_asList, c);
        }

        public int indexOf(char c) {
            return ArraysKt___ArraysKt.indexOf(this.$this_asList, c);
        }

        public int lastIndexOf(char c) {
            return ArraysKt___ArraysKt.lastIndexOf(this.$this_asList, c);
        }
    }

    @NotNull
    public static <T> List<T> asList(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        List<T> listAsList = Arrays.asList(tArr);
        Intrinsics.checkNotNullExpressionValue(listAsList, "asList(...)");
        return listAsList;
    }

    public static final <T> int binarySearch(@NotNull T[] tArr, T t, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return Arrays.binarySearch(tArr, i, i2, t, comparator);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return binarySearch(objArr, obj, comparator, i, i2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepEqualsInline")
    @LowPriorityInOverloadResolution
    public static final <T> boolean contentDeepEqualsInline(T[] tArr, T[] other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return ArraysKt__ArraysKt.contentDeepEquals(tArr, other);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "contentDeepEqualsNullable")
    public static final <T> boolean contentDeepEqualsNullable(T[] tArr, T[] tArr2) {
        return ArraysKt__ArraysKt.contentDeepEquals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepHashCodeInline")
    @LowPriorityInOverloadResolution
    public static final <T> int contentDeepHashCodeInline(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "contentDeepHashCodeNullable")
    public static final <T> int contentDeepHashCodeNullable(T[] tArr) {
        return Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepToStringInline")
    @LowPriorityInOverloadResolution
    public static final <T> String contentDeepToStringInline(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return ArraysKt__ArraysKt.contentDeepToString(tArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "contentDeepToStringNullable")
    public static final <T> String contentDeepToStringNullable(T[] tArr) {
        return ArraysKt__ArraysKt.contentDeepToString(tArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> boolean contentEquals(T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> int contentHashCode(T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> String contentToString(T[] tArr) {
        String string = Arrays.toString(tArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static <T> T[] copyInto(@NotNull T[] tArr, @NotNull T[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(tArr, i2, destination, i, i3 - i2);
        return destination;
    }

    public static /* synthetic */ Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        copyInto(objArr, objArr2, i, i2, i3);
        return objArr2;
    }

    @InlineOnly
    public static final <T> T[] copyOf(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(...)");
        return tArr2;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static <T> T[] copyOfRange(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, tArr.length);
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOfRange(...)");
        return tArr2;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final <T> T[] copyOfRangeInline(T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T[]) copyOfRange(tArr, i, i2);
    }

    @InlineOnly
    public static final <T> T elementAt(T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[i];
    }

    public static <T> void fill(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Arrays.fill(tArr, i, i2, t);
    }

    public static /* synthetic */ void fill$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        fill(objArr, obj, i, i2);
    }

    @NotNull
    public static final <R> List<R> filterIsInstance(@NotNull Object[] objArr, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(klass, "klass");
        ArrayList arrayList = new ArrayList();
        filterIsInstanceTo(objArr, arrayList, klass);
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(@NotNull Object[] objArr, @NotNull C destination, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(klass, "klass");
        for (Object obj : objArr) {
            if (klass.isInstance(obj)) {
                destination.add(obj);
            }
        }
        return destination;
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double max(Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(dArr);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <T, R extends Comparable<? super R>> T maxBy(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (length != 0) {
            R rInvoke = selector.invoke(t);
            if (1 <= length) {
                while (true) {
                    T t2 = tArr[i];
                    R rInvoke2 = selector.invoke(t2);
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        t = t2;
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return t;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object maxWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(objArr, comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double min(Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(dArr);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <T, R extends Comparable<? super R>> T minBy(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (length != 0) {
            R rInvoke = selector.invoke(t);
            if (1 <= length) {
                while (true) {
                    T t2 = tArr[i];
                    R rInvoke2 = selector.invoke(t2);
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        t = t2;
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return t;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object minWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(objArr, comparator);
    }

    @NotNull
    public static <T> T[] plus(@NotNull T[] tArr, T t) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, length + 1);
        tArr2[length] = t;
        return tArr2;
    }

    @InlineOnly
    public static final <T> T[] plusElement(T[] tArr, T t) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T[]) plus(tArr, t);
    }

    public static final void sort(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length > 1) {
            Arrays.sort(iArr);
        }
    }

    public static /* synthetic */ void sort$default(Comparable[] comparableArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = comparableArr.length;
        }
        sort(comparableArr, i, i2);
    }

    public static final <T> void sortWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }

    public static /* synthetic */ void sortWith$default(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        sortWith(objArr, comparator, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final <T> BigDecimal sumOfBigDecimal(T[] tArr, Function1<? super T, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (T t : tArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(t));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final <T> BigInteger sumOfBigInteger(T[] tArr, Function1<? super T, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (T t : tArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(t));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(tArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final Byte[] toTypedArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Byte[] bArr2 = new Byte[bArr.length];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    public static final <T> int binarySearch(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return Arrays.binarySearch(tArr, i, i2, t);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return binarySearch(objArr, obj, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(byte[] bArr) {
        String string = Arrays.toString(bArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static byte[] copyInto(@NotNull byte[] bArr, @NotNull byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(bArr, i2, destination, i, i3 - i2);
        return destination;
    }

    @InlineOnly
    public static final byte[] copyOf(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final byte[] copyOfRangeInline(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return copyOfRange(bArr, i, i2);
    }

    @InlineOnly
    public static final byte elementAt(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[i];
    }

    public static void fill(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Arrays.fill(bArr, i, i2, b);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float max(Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(fArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte maxWith(byte[] bArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(bArr, (Comparator<? super Byte>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @SinceKotlin(version = "1.1")
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float min(Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(fArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte minWith(byte[] bArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(bArr, (Comparator<? super Byte>) comparator);
    }

    public static final void sort(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length > 1) {
            Arrays.sort(jArr);
        }
    }

    public static final <T> void sortWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Arrays.sort(tArr, i, i2, comparator);
    }

    @NotNull
    public static final SortedSet<Byte> toSortedSet(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(bArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final List<Byte> asList(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new AnonymousClass1(bArr);
    }

    public static final int binarySearch(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return Arrays.binarySearch(bArr, i, i2, b);
    }

    public static /* synthetic */ int binarySearch$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return binarySearch(bArr, b, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(short[] sArr) {
        String string = Arrays.toString(sArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static short[] copyInto(@NotNull short[] sArr, @NotNull short[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(sArr, i2, destination, i, i3 - i2);
        return destination;
    }

    public static /* synthetic */ byte[] copyInto$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        copyInto(bArr, bArr2, i, i2, i3);
        return bArr2;
    }

    @InlineOnly
    public static final short[] copyOf(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] sArrCopyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static byte[] copyOfRange(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final short[] copyOfRangeInline(short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return copyOfRange(sArr, i, i2);
    }

    @InlineOnly
    public static final short elementAt(short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[i];
    }

    public static void fill(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Arrays.fill(sArr, i, i2, s);
    }

    public static /* synthetic */ void fill$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        fill(bArr, b, i, i2);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Comparable max(Comparable[] comparableArr) {
        Intrinsics.checkNotNullParameter(comparableArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(comparableArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short maxWith(short[] sArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(sArr, (Comparator<? super Short>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Comparable min(Comparable[] comparableArr) {
        Intrinsics.checkNotNullParameter(comparableArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(comparableArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short minWith(short[] sArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(sArr, (Comparator<? super Short>) comparator);
    }

    public static final void sort(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length > 1) {
            Arrays.sort(bArr);
        }
    }

    public static /* synthetic */ void sort$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        sort(bArr, i, i2);
    }

    @NotNull
    public static final SortedSet<Short> toSortedSet(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(sArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final List<Short> asList(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return new AnonymousClass2(sArr);
    }

    public static final int binarySearch(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return Arrays.binarySearch(sArr, i, i2, s);
    }

    public static /* synthetic */ int binarySearch$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return binarySearch(sArr, s, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(int[] iArr) {
        String string = Arrays.toString(iArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static int[] copyInto(@NotNull int[] iArr, @NotNull int[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(iArr, i2, destination, i, i3 - i2);
        return destination;
    }

    @InlineOnly
    public static final int[] copyOf(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final int[] copyOfRangeInline(int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return copyOfRange(iArr, i, i2);
    }

    @InlineOnly
    public static final int elementAt(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[i];
    }

    public static void fill(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Arrays.fill(iArr, i2, i3, i);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte max(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(bArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer maxWith(int[] iArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(iArr, (Comparator<? super Integer>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte min(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(bArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer minWith(int[] iArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(iArr, (Comparator<? super Integer>) comparator);
    }

    @NotNull
    public static byte[] plus(@NotNull byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, length + 1);
        bArrCopyOf[length] = b;
        return bArrCopyOf;
    }

    public static final void sort(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length > 1) {
            Arrays.sort(sArr);
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(byte[] bArr, Function1<? super Byte, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (byte b : bArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Byte.valueOf(b)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(byte[] bArr, Function1<? super Byte, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (byte b : bArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Byte.valueOf(b)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final SortedSet<Integer> toSortedSet(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(iArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final Short[] toTypedArray(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Short[] shArr = new Short[sArr.length];
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return shArr;
    }

    @NotNull
    public static List<Integer> asList(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return new AnonymousClass3(iArr);
    }

    public static final int binarySearch(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return Arrays.binarySearch(iArr, i2, i3, i);
    }

    public static /* synthetic */ int binarySearch$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return binarySearch(iArr, i, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(long[] jArr, long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(long[] jArr) {
        String string = Arrays.toString(jArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static long[] copyInto(@NotNull long[] jArr, @NotNull long[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(jArr, i2, destination, i, i3 - i2);
        return destination;
    }

    public static /* synthetic */ short[] copyInto$default(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length;
        }
        copyInto(sArr, sArr2, i, i2, i3);
        return sArr2;
    }

    @InlineOnly
    public static final long[] copyOf(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static short[] copyOfRange(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, sArr.length);
        short[] sArrCopyOfRange = Arrays.copyOfRange(sArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOfRange, "copyOfRange(...)");
        return sArrCopyOfRange;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final long[] copyOfRangeInline(long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return copyOfRange(jArr, i, i2);
    }

    @InlineOnly
    public static final long elementAt(long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[i];
    }

    public static void fill(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Arrays.fill(jArr, i, i2, j);
    }

    public static /* synthetic */ void fill$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        fill(sArr, s, i, i2);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short max(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(sArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long maxWith(long[] jArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(jArr, (Comparator<? super Long>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short min(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(sArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long minWith(long[] jArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(jArr, (Comparator<? super Long>) comparator);
    }

    public static final void sort(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length > 1) {
            Arrays.sort(dArr);
        }
    }

    public static /* synthetic */ void sort$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        sort(sArr, i, i2);
    }

    @NotNull
    public static final SortedSet<Long> toSortedSet(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(jArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static List<Long> asList(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return new AnonymousClass4(jArr);
    }

    public static final int binarySearch(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return Arrays.binarySearch(jArr, i, i2, j);
    }

    public static /* synthetic */ int binarySearch$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return binarySearch(jArr, j, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(float[] fArr) {
        String string = Arrays.toString(fArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static float[] copyInto(@NotNull float[] fArr, @NotNull float[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(fArr, i2, destination, i, i3 - i2);
        return destination;
    }

    @InlineOnly
    public static final float[] copyOf(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        return fArrCopyOf;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final float[] copyOfRangeInline(float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return copyOfRange(fArr, i, i2);
    }

    @InlineOnly
    public static final float elementAt(float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[i];
    }

    public static final void fill(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Arrays.fill(fArr, i, i2, f);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer max(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(iArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float maxWith(float[] fArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(fArr, (Comparator<? super Float>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer min(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(iArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float minWith(float[] fArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(fArr, (Comparator<? super Float>) comparator);
    }

    public static final void sort(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length > 1) {
            Arrays.sort(fArr);
        }
    }

    @NotNull
    public static final SortedSet<Float> toSortedSet(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(fArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final List<Float> asList(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new AnonymousClass5(fArr);
    }

    public static final int binarySearch(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return Arrays.binarySearch(fArr, i, i2, f);
    }

    public static /* synthetic */ int binarySearch$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return binarySearch(fArr, f, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(double[] dArr) {
        String string = Arrays.toString(dArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final double[] copyInto(@NotNull double[] dArr, @NotNull double[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(dArr, i2, destination, i, i3 - i2);
        return destination;
    }

    public static /* synthetic */ int[] copyInto$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        copyInto(iArr, iArr2, i, i2, i3);
        return iArr2;
    }

    @InlineOnly
    public static final double[] copyOf(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double[] dArrCopyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(...)");
        return dArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static int[] copyOfRange(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, iArr.length);
        int[] iArrCopyOfRange = Arrays.copyOfRange(iArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOfRange, "copyOfRange(...)");
        return iArrCopyOfRange;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final double[] copyOfRangeInline(double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return copyOfRange(dArr, i, i2);
    }

    @InlineOnly
    public static final double elementAt(double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[i];
    }

    public static final void fill(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Arrays.fill(dArr, i, i2, d);
    }

    public static /* synthetic */ void fill$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        fill(iArr, i, i2, i3);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long max(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(jArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double maxWith(double[] dArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(dArr, (Comparator<? super Double>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long min(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(jArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double minWith(double[] dArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(dArr, (Comparator<? super Double>) comparator);
    }

    @NotNull
    public static short[] plus(@NotNull short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        short[] sArrCopyOf = Arrays.copyOf(sArr, length + 1);
        sArrCopyOf[length] = s;
        return sArrCopyOf;
    }

    public static final void sort(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length > 1) {
            Arrays.sort(cArr);
        }
    }

    public static /* synthetic */ void sort$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        sort(iArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(short[] sArr, Function1<? super Short, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (short s : sArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Short.valueOf(s)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(short[] sArr, Function1<? super Short, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (short s : sArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Short.valueOf(s)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final SortedSet<Double> toSortedSet(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(dArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final Integer[] toTypedArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Integer[] numArr = new Integer[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    @NotNull
    public static List<Double> asList(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return new AnonymousClass6(dArr);
    }

    public static final int binarySearch(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return Arrays.binarySearch(dArr, i, i2, d);
    }

    public static /* synthetic */ int binarySearch$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return binarySearch(dArr, d, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(boolean[] zArr) {
        return Arrays.hashCode(zArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(boolean[] zArr) {
        String string = Arrays.toString(zArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final boolean[] copyInto(@NotNull boolean[] zArr, @NotNull boolean[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(zArr, i2, destination, i, i3 - i2);
        return destination;
    }

    @InlineOnly
    public static final boolean[] copyOf(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        boolean[] zArrCopyOf = Arrays.copyOf(zArr, zArr.length);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "copyOf(...)");
        return zArrCopyOf;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final boolean[] copyOfRangeInline(boolean[] zArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return copyOfRange(zArr, i, i2);
    }

    @InlineOnly
    public static final boolean elementAt(boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[i];
    }

    public static final void fill(@NotNull boolean[] zArr, boolean z, int i, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Arrays.fill(zArr, i, i2, z);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float max(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(fArr);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Byte maxBy(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (length == 0) {
            return Byte.valueOf(b);
        }
        R rInvoke = selector.invoke(Byte.valueOf(b));
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                R rInvoke2 = selector.invoke(Byte.valueOf(b2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    b = b2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Boolean maxWith(boolean[] zArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(zArr, (Comparator<? super Boolean>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float min(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(fArr);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Byte minBy(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (length == 0) {
            return Byte.valueOf(b);
        }
        R rInvoke = selector.invoke(Byte.valueOf(b));
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                R rInvoke2 = selector.invoke(Byte.valueOf(b2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    b = b2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Boolean minWith(boolean[] zArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(zArr, (Comparator<? super Boolean>) comparator);
    }

    @InlineOnly
    public static final <T extends Comparable<? super T>> void sort(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        sort((Object[]) tArr);
    }

    @NotNull
    public static final SortedSet<Boolean> toSortedSet(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(zArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final List<Boolean> asList(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return new AnonymousClass7(zArr);
    }

    public static final int binarySearch(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return Arrays.binarySearch(cArr, i, i2, c);
    }

    public static /* synthetic */ int binarySearch$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return binarySearch(cArr, c, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean contentEquals(char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int contentHashCode(char[] cArr) {
        return Arrays.hashCode(cArr);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final String contentToString(char[] cArr) {
        String string = Arrays.toString(cArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static char[] copyInto(@NotNull char[] cArr, @NotNull char[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(cArr, i2, destination, i, i3 - i2);
        return destination;
    }

    public static /* synthetic */ long[] copyInto$default(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length;
        }
        copyInto(jArr, jArr2, i, i2, i3);
        return jArr2;
    }

    @InlineOnly
    public static final char[] copyOf(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        char[] cArrCopyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
        return cArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static long[] copyOfRange(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, jArr.length);
        long[] jArrCopyOfRange = Arrays.copyOfRange(jArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOfRange, "copyOfRange(...)");
        return jArrCopyOfRange;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    public static final char[] copyOfRangeInline(char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return copyOfRange(cArr, i, i2);
    }

    @InlineOnly
    public static final char elementAt(char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[i];
    }

    public static void fill(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Arrays.fill(cArr, i, i2, c);
    }

    public static /* synthetic */ void fill$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        fill(jArr, j, i, i2);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double max(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(dArr);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character maxWith(char[] cArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.maxWithOrNull(cArr, (Comparator<? super Character>) comparator);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double min(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(dArr);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character minWith(char[] cArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysKt.minWithOrNull(cArr, (Comparator<? super Character>) comparator);
    }

    public static final <T> void sort(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length > 1) {
            Arrays.sort(tArr);
        }
    }

    public static /* synthetic */ void sort$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        sort(jArr, i, i2);
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        TreeSet treeSet = new TreeSet();
        ArraysKt___ArraysKt.toCollection(cArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final List<Character> asList(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new AnonymousClass8(cArr);
    }

    @InlineOnly
    public static final byte[] copyOf(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, i);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character max(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return ArraysKt___ArraysKt.maxOrNull(cArr);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character min(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return ArraysKt___ArraysKt.minOrNull(cArr);
    }

    @NotNull
    public static int[] plus(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        int[] iArrCopyOf = Arrays.copyOf(iArr, length + 1);
        iArrCopyOf[length] = i;
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> void sort(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Arrays.sort(tArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(int[] iArr, Function1<? super Integer, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (int i : iArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Integer.valueOf(i)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(int[] iArr, Function1<? super Integer, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (int i : iArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Integer.valueOf(i)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final <T> SortedSet<T> toSortedSet(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        TreeSet treeSet = new TreeSet(comparator);
        ArraysKt___ArraysKt.toCollection(tArr, treeSet);
        return treeSet;
    }

    @NotNull
    public static final Long[] toTypedArray(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public static /* synthetic */ float[] copyInto$default(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length;
        }
        copyInto(fArr, fArr2, i, i2, i3);
        return fArr2;
    }

    @InlineOnly
    public static final short[] copyOf(short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] sArrCopyOf = Arrays.copyOf(sArr, i);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final float[] copyOfRange(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, fArr.length);
        float[] fArrCopyOfRange = Arrays.copyOfRange(fArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOfRange, "copyOfRange(...)");
        return fArrCopyOfRange;
    }

    public static /* synthetic */ void fill$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        fill(fArr, f, i, i2);
    }

    public static final void sort(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Arrays.sort(bArr, i, i2);
    }

    public static /* synthetic */ void sort$default(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        sort(fArr, i, i2);
    }

    @InlineOnly
    public static final int[] copyOf(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] iArrCopyOf = Arrays.copyOf(iArr, i);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    public static final void sort(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Arrays.sort(sArr, i, i2);
    }

    public static /* synthetic */ double[] copyInto$default(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length;
        }
        copyInto(dArr, dArr2, i, i2, i3);
        return dArr2;
    }

    @InlineOnly
    public static final long[] copyOf(long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] jArrCopyOf = Arrays.copyOf(jArr, i);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final double[] copyOfRange(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, dArr.length);
        double[] dArrCopyOfRange = Arrays.copyOfRange(dArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOfRange, "copyOfRange(...)");
        return dArrCopyOfRange;
    }

    public static /* synthetic */ void fill$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        fill(dArr, d, i, i2);
    }

    @NotNull
    public static long[] plus(@NotNull long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, length + 1);
        jArrCopyOf[length] = j;
        return jArrCopyOf;
    }

    public static void sort(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Arrays.sort(iArr, i, i2);
    }

    public static /* synthetic */ void sort$default(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        sort(dArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(long[] jArr, Function1<? super Long, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (long j : jArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Long.valueOf(j)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(long[] jArr, Function1<? super Long, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (long j : jArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Long.valueOf(j)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final Float[] toTypedArray(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Float[] fArr2 = new Float[fArr.length];
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    @InlineOnly
    public static final float[] copyOf(float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float[] fArrCopyOf = Arrays.copyOf(fArr, i);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        return fArrCopyOf;
    }

    public static void sort(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Arrays.sort(jArr, i, i2);
    }

    public static /* synthetic */ boolean[] copyInto$default(boolean[] zArr, boolean[] zArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = zArr.length;
        }
        copyInto(zArr, zArr2, i, i2, i3);
        return zArr2;
    }

    @InlineOnly
    public static final double[] copyOf(double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double[] dArrCopyOf = Arrays.copyOf(dArr, i);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(...)");
        return dArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final boolean[] copyOfRange(@NotNull boolean[] zArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, zArr.length);
        boolean[] zArrCopyOfRange = Arrays.copyOfRange(zArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOfRange, "copyOfRange(...)");
        return zArrCopyOfRange;
    }

    public static /* synthetic */ void fill$default(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        fill(zArr, z, i, i2);
    }

    public static void sort(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Arrays.sort(fArr, i, i2);
    }

    public static /* synthetic */ void sort$default(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        sort(cArr, i, i2);
    }

    @InlineOnly
    public static final boolean[] copyOf(boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        boolean[] zArrCopyOf = Arrays.copyOf(zArr, i);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "copyOf(...)");
        return zArrCopyOf;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        float[] fArrCopyOf = Arrays.copyOf(fArr, length + 1);
        fArrCopyOf[length] = f;
        return fArrCopyOf;
    }

    public static final void sort(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Arrays.sort(dArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(float[] fArr, Function1<? super Float, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (float f : fArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Float.valueOf(f)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(float[] fArr, Function1<? super Float, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (float f : fArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Float.valueOf(f)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final Double[] toTypedArray(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Double[] dArr2 = new Double[dArr.length];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    public static /* synthetic */ char[] copyInto$default(char[] cArr, char[] cArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = cArr.length;
        }
        copyInto(cArr, cArr2, i, i2, i3);
        return cArr2;
    }

    @InlineOnly
    public static final char[] copyOf(char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        char[] cArrCopyOf = Arrays.copyOf(cArr, i);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
        return cArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final char[] copyOfRange(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, cArr.length);
        char[] cArrCopyOfRange = Arrays.copyOfRange(cArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOfRange, "copyOfRange(...)");
        return cArrCopyOfRange;
    }

    public static /* synthetic */ void fill$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        fill(cArr, c, i, i2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Short maxBy(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (length == 0) {
            return Short.valueOf(s);
        }
        R rInvoke = selector.invoke(Short.valueOf(s));
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                R rInvoke2 = selector.invoke(Short.valueOf(s2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    s = s2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Short minBy(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (length == 0) {
            return Short.valueOf(s);
        }
        R rInvoke = selector.invoke(Short.valueOf(s));
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                R rInvoke2 = selector.invoke(Short.valueOf(s2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    s = s2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    public static final void sort(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Arrays.sort(cArr, i, i2);
    }

    public static /* synthetic */ void sort$default(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        sort(objArr, i, i2);
    }

    @InlineOnly
    public static final <T> T[] copyOf(T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(...)");
        return tArr2;
    }

    public static final <T> void sort(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Arrays.sort(tArr, i, i2);
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        double[] dArrCopyOf = Arrays.copyOf(dArr, length + 1);
        dArrCopyOf[length] = d;
        return dArrCopyOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(double[] dArr, Function1<? super Double, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (double d : dArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Double.valueOf(d)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(double[] dArr, Function1<? super Double, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (double d : dArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Double.valueOf(d)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final Boolean[] toTypedArray(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Boolean[] boolArr = new Boolean[zArr.length];
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        boolean[] zArrCopyOf = Arrays.copyOf(zArr, length + 1);
        zArrCopyOf[length] = z;
        return zArrCopyOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(boolean[] zArr, Function1<? super Boolean, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (boolean z : zArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Boolean.valueOf(z)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(boolean[] zArr, Function1<? super Boolean, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (boolean z : zArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Boolean.valueOf(z)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @NotNull
    public static final Character[] toTypedArray(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Character[] chArr = new Character[cArr.length];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return chArr;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        char[] cArrCopyOf = Arrays.copyOf(cArr, length + 1);
        cArrCopyOf[length] = c;
        return cArrCopyOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigDecimal")
    @OverloadResolutionByLambdaReturnType
    public static final BigDecimal sumOfBigDecimal(char[] cArr, Function1<? super Character, ? extends BigDecimal> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
        for (char c : cArr) {
            bigDecimalValueOf = bigDecimalValueOf.add(selector.invoke(Character.valueOf(c)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "add(...)");
        }
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfBigInteger")
    @OverloadResolutionByLambdaReturnType
    public static final BigInteger sumOfBigInteger(char[] cArr, Function1<? super Character, ? extends BigInteger> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(...)");
        for (char c : cArr) {
            bigIntegerValueOf = bigIntegerValueOf.add(selector.invoke(Character.valueOf(c)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "add(...)");
        }
        return bigIntegerValueOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Integer maxBy(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (length == 0) {
            return Integer.valueOf(i);
        }
        R rInvoke = selector.invoke(Integer.valueOf(i));
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                R rInvoke2 = selector.invoke(Integer.valueOf(i3));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    i = i3;
                    rInvoke = rInvoke2;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Integer minBy(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (length == 0) {
            return Integer.valueOf(i);
        }
        R rInvoke = selector.invoke(Integer.valueOf(i));
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                R rInvoke2 = selector.invoke(Integer.valueOf(i3));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    i = i3;
                    rInvoke = rInvoke2;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] tArr, @NotNull Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, elements.size() + length);
        Iterator<? extends T> it = elements.iterator();
        while (it.hasNext()) {
            tArr2[length] = it.next();
            length++;
        }
        Intrinsics.checkNotNull(tArr2);
        return tArr2;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] bArr, @NotNull Collection<Byte> elements) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = bArr.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, elements.size() + length);
        Iterator<Byte> it = elements.iterator();
        while (it.hasNext()) {
            bArrCopyOf[length] = it.next().byteValue();
            length++;
        }
        Intrinsics.checkNotNull(bArrCopyOf);
        return bArrCopyOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Long maxBy(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (length == 0) {
            return Long.valueOf(j);
        }
        R rInvoke = selector.invoke(Long.valueOf(j));
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                R rInvoke2 = selector.invoke(Long.valueOf(j2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    j = j2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Long minBy(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (length == 0) {
            return Long.valueOf(j);
        }
        R rInvoke = selector.invoke(Long.valueOf(j));
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                R rInvoke2 = selector.invoke(Long.valueOf(j2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    j = j2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    @NotNull
    public static final short[] plus(@NotNull short[] sArr, @NotNull Collection<Short> elements) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = sArr.length;
        short[] sArrCopyOf = Arrays.copyOf(sArr, elements.size() + length);
        Iterator<Short> it = elements.iterator();
        while (it.hasNext()) {
            sArrCopyOf[length] = it.next().shortValue();
            length++;
        }
        Intrinsics.checkNotNull(sArrCopyOf);
        return sArrCopyOf;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] iArr, @NotNull Collection<Integer> elements) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = iArr.length;
        int[] iArrCopyOf = Arrays.copyOf(iArr, elements.size() + length);
        Iterator<Integer> it = elements.iterator();
        while (it.hasNext()) {
            iArrCopyOf[length] = it.next().intValue();
            length++;
        }
        Intrinsics.checkNotNull(iArrCopyOf);
        return iArrCopyOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Float maxBy(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        float f = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (length == 0) {
            return Float.valueOf(f);
        }
        R rInvoke = selector.invoke(Float.valueOf(f));
        if (1 <= length) {
            while (true) {
                float f2 = fArr[i];
                R rInvoke2 = selector.invoke(Float.valueOf(f2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    f = f2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(f);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Float minBy(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        float f = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (length == 0) {
            return Float.valueOf(f);
        }
        R rInvoke = selector.invoke(Float.valueOf(f));
        if (1 <= length) {
            while (true) {
                float f2 = fArr[i];
                R rInvoke2 = selector.invoke(Float.valueOf(f2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    f = f2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(f);
    }

    @NotNull
    public static final long[] plus(@NotNull long[] jArr, @NotNull Collection<Long> elements) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, elements.size() + length);
        Iterator<Long> it = elements.iterator();
        while (it.hasNext()) {
            jArrCopyOf[length] = it.next().longValue();
            length++;
        }
        Intrinsics.checkNotNull(jArrCopyOf);
        return jArrCopyOf;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, @NotNull Collection<Float> elements) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = fArr.length;
        float[] fArrCopyOf = Arrays.copyOf(fArr, elements.size() + length);
        Iterator<Float> it = elements.iterator();
        while (it.hasNext()) {
            fArrCopyOf[length] = it.next().floatValue();
            length++;
        }
        Intrinsics.checkNotNull(fArrCopyOf);
        return fArrCopyOf;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, @NotNull Collection<Double> elements) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = dArr.length;
        double[] dArrCopyOf = Arrays.copyOf(dArr, elements.size() + length);
        Iterator<Double> it = elements.iterator();
        while (it.hasNext()) {
            dArrCopyOf[length] = it.next().doubleValue();
            length++;
        }
        Intrinsics.checkNotNull(dArrCopyOf);
        return dArrCopyOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Double maxBy(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        double d = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (length == 0) {
            return Double.valueOf(d);
        }
        R rInvoke = selector.invoke(Double.valueOf(d));
        if (1 <= length) {
            while (true) {
                double d2 = dArr[i];
                R rInvoke2 = selector.invoke(Double.valueOf(d2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    d = d2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(d);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Double minBy(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        double d = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (length == 0) {
            return Double.valueOf(d);
        }
        R rInvoke = selector.invoke(Double.valueOf(d));
        if (1 <= length) {
            while (true) {
                double d2 = dArr[i];
                R rInvoke2 = selector.invoke(Double.valueOf(d2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    d = d2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(d);
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, @NotNull Collection<Boolean> elements) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = zArr.length;
        boolean[] zArrCopyOf = Arrays.copyOf(zArr, elements.size() + length);
        Iterator<Boolean> it = elements.iterator();
        while (it.hasNext()) {
            zArrCopyOf[length] = it.next().booleanValue();
            length++;
        }
        Intrinsics.checkNotNull(zArrCopyOf);
        return zArrCopyOf;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, @NotNull Collection<Character> elements) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = cArr.length;
        char[] cArrCopyOf = Arrays.copyOf(cArr, elements.size() + length);
        Iterator<Character> it = elements.iterator();
        while (it.hasNext()) {
            cArrCopyOf[length] = it.next().charValue();
            length++;
        }
        Intrinsics.checkNotNull(cArrCopyOf);
        return cArrCopyOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Boolean maxBy(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (length == 0) {
            return Boolean.valueOf(z);
        }
        R rInvoke = selector.invoke(Boolean.valueOf(z));
        if (1 <= length) {
            while (true) {
                boolean z2 = zArr[i];
                R rInvoke2 = selector.invoke(Boolean.valueOf(z2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    z = z2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Boolean.valueOf(z);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Boolean minBy(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (length == 0) {
            return Boolean.valueOf(z);
        }
        R rInvoke = selector.invoke(Boolean.valueOf(z));
        if (1 <= length) {
            while (true) {
                boolean z2 = zArr[i];
                R rInvoke2 = selector.invoke(Boolean.valueOf(z2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    z = z2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Boolean.valueOf(z);
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] tArr, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = tArr.length;
        int length2 = elements.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, length + length2);
        System.arraycopy(elements, 0, tArr2, length, length2);
        Intrinsics.checkNotNull(tArr2);
        return tArr2;
    }

    @NotNull
    public static byte[] plus(@NotNull byte[] bArr, @NotNull byte[] elements) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = bArr.length;
        int length2 = elements.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, length + length2);
        System.arraycopy(elements, 0, bArrCopyOf, length, length2);
        Intrinsics.checkNotNull(bArrCopyOf);
        return bArrCopyOf;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Character maxBy(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (length == 0) {
            return Character.valueOf(c);
        }
        R rInvoke = selector.invoke(Character.valueOf(c));
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                R rInvoke2 = selector.invoke(Character.valueOf(c2));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    c = c2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(c);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final <R extends Comparable<? super R>> Character minBy(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (length == 0) {
            return Character.valueOf(c);
        }
        R rInvoke = selector.invoke(Character.valueOf(c));
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                R rInvoke2 = selector.invoke(Character.valueOf(c2));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    c = c2;
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(c);
    }

    @NotNull
    public static short[] plus(@NotNull short[] sArr, @NotNull short[] elements) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = sArr.length;
        int length2 = elements.length;
        short[] sArrCopyOf = Arrays.copyOf(sArr, length + length2);
        System.arraycopy(elements, 0, sArrCopyOf, length, length2);
        Intrinsics.checkNotNull(sArrCopyOf);
        return sArrCopyOf;
    }

    @NotNull
    public static int[] plus(@NotNull int[] iArr, @NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = iArr.length;
        int length2 = elements.length;
        int[] iArrCopyOf = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(elements, 0, iArrCopyOf, length, length2);
        Intrinsics.checkNotNull(iArrCopyOf);
        return iArrCopyOf;
    }

    @NotNull
    public static long[] plus(@NotNull long[] jArr, @NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = jArr.length;
        int length2 = elements.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, length + length2);
        System.arraycopy(elements, 0, jArrCopyOf, length, length2);
        Intrinsics.checkNotNull(jArrCopyOf);
        return jArrCopyOf;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] fArr, @NotNull float[] elements) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = fArr.length;
        int length2 = elements.length;
        float[] fArrCopyOf = Arrays.copyOf(fArr, length + length2);
        System.arraycopy(elements, 0, fArrCopyOf, length, length2);
        Intrinsics.checkNotNull(fArrCopyOf);
        return fArrCopyOf;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] dArr, @NotNull double[] elements) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = dArr.length;
        int length2 = elements.length;
        double[] dArrCopyOf = Arrays.copyOf(dArr, length + length2);
        System.arraycopy(elements, 0, dArrCopyOf, length, length2);
        Intrinsics.checkNotNull(dArrCopyOf);
        return dArrCopyOf;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] zArr, @NotNull boolean[] elements) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = zArr.length;
        int length2 = elements.length;
        boolean[] zArrCopyOf = Arrays.copyOf(zArr, length + length2);
        System.arraycopy(elements, 0, zArrCopyOf, length, length2);
        Intrinsics.checkNotNull(zArrCopyOf);
        return zArrCopyOf;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] cArr, @NotNull char[] elements) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = cArr.length;
        int length2 = elements.length;
        char[] cArrCopyOf = Arrays.copyOf(cArr, length + length2);
        System.arraycopy(elements, 0, cArrCopyOf, length, length2);
        Intrinsics.checkNotNull(cArrCopyOf);
        return cArrCopyOf;
    }
}
