package kotlin.collections.unsigned;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline0;
import kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticOutline1;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.UArraySortingKt;
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
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_UArrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _UArrays.kt\nkotlin/collections/unsigned/UArraysKt___UArraysKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,11102:1\n3824#1:11152\n3832#1:11153\n3840#1:11154\n3848#1:11155\n3824#1:11156\n3832#1:11157\n3840#1:11158\n3848#1:11159\n3824#1:11160\n3832#1:11161\n3840#1:11162\n3848#1:11163\n3824#1:11220\n3832#1:11221\n3840#1:11222\n3848#1:11223\n3824#1:11224\n3832#1:11225\n3840#1:11226\n3848#1:11227\n3824#1:11228\n3832#1:11229\n3840#1:11230\n3848#1:11231\n3824#1:11232\n3832#1:11233\n3840#1:11234\n3848#1:11235\n3824#1:11236\n3832#1:11237\n3840#1:11238\n3848#1:11239\n3824#1:11240\n3832#1:11241\n3840#1:11242\n3848#1:11243\n3824#1:11244\n3832#1:11245\n3840#1:11246\n3848#1:11247\n3824#1:11248\n3832#1:11249\n3840#1:11250\n3848#1:11251\n3824#1:11252\n3832#1:11253\n3840#1:11254\n3848#1:11255\n3824#1:11256\n3832#1:11257\n3840#1:11258\n3848#1:11259\n3824#1:11260\n3832#1:11261\n3840#1:11262\n3848#1:11263\n3824#1:11264\n3832#1:11265\n3840#1:11266\n3848#1:11267\n3824#1:11268\n3832#1:11269\n3840#1:11270\n3848#1:11271\n3824#1:11272\n3832#1:11273\n3840#1:11274\n3848#1:11275\n3824#1:11276\n3832#1:11277\n3840#1:11278\n3848#1:11279\n3824#1:11280\n3832#1:11281\n3840#1:11282\n3848#1:11283\n3824#1:11284\n3832#1:11285\n3840#1:11286\n3848#1:11287\n3824#1:11288\n3832#1:11289\n3840#1:11290\n3848#1:11291\n3824#1:11292\n3832#1:11293\n3840#1:11294\n3848#1:11295\n3824#1:11296\n3832#1:11297\n3840#1:11298\n3848#1:11299\n3824#1:11300\n3832#1:11301\n3840#1:11302\n3848#1:11303\n3824#1:11304\n3832#1:11305\n3840#1:11306\n3848#1:11307\n3824#1:11308\n3832#1:11309\n3840#1:11310\n3848#1:11311\n3824#1:11312\n3832#1:11313\n3840#1:11314\n3848#1:11315\n3824#1:11316\n3832#1:11317\n3840#1:11318\n3848#1:11319\n3824#1:11320\n3832#1:11321\n3840#1:11322\n3848#1:11323\n3824#1:11324\n3832#1:11325\n3840#1:11326\n3848#1:11327\n3824#1:11328\n3832#1:11329\n3840#1:11330\n3848#1:11331\n3824#1:11332\n3832#1:11333\n3840#1:11334\n3848#1:11335\n3824#1:11336\n3832#1:11337\n3840#1:11338\n3848#1:11339\n3824#1:11340\n3832#1:11341\n3840#1:11342\n3848#1:11343\n3824#1:11344\n3832#1:11345\n3840#1:11346\n3848#1:11347\n3824#1:11348\n3832#1:11349\n3840#1:11350\n3848#1:11351\n3824#1:11352\n3832#1:11353\n3840#1:11354\n3848#1:11355\n3824#1:11356\n3832#1:11357\n3840#1:11358\n3848#1:11359\n3824#1:11360\n3832#1:11361\n3840#1:11362\n3848#1:11363\n3824#1:11364\n3832#1:11365\n3840#1:11366\n3848#1:11367\n3824#1:11368\n3832#1:11369\n3840#1:11370\n3848#1:11371\n1718#2,6:11103\n1730#2,6:11109\n1694#2,6:11115\n1706#2,6:11121\n1826#2,6:11127\n1838#2,6:11133\n1802#2,6:11139\n1814#2,6:11145\n1#3:11151\n384#4,7:11164\n384#4,7:11171\n384#4,7:11178\n384#4,7:11185\n384#4,7:11192\n384#4,7:11199\n384#4,7:11206\n384#4,7:11213\n*S KotlinDebug\n*F\n+ 1 _UArrays.kt\nkotlin/collections/unsigned/UArraysKt___UArraysKt\n*L\n1735#1:11152\n1752#1:11153\n1769#1:11154\n1786#1:11155\n2563#1:11156\n2580#1:11157\n2597#1:11158\n2614#1:11159\n2930#1:11160\n2946#1:11161\n2962#1:11162\n2978#1:11163\n5622#1:11220\n5642#1:11221\n5662#1:11222\n5682#1:11223\n5703#1:11224\n5725#1:11225\n5747#1:11226\n5769#1:11227\n5884#1:11228\n5905#1:11229\n5926#1:11230\n5947#1:11231\n5976#1:11232\n6012#1:11233\n6048#1:11234\n6084#1:11235\n6116#1:11236\n6148#1:11237\n6180#1:11238\n6212#1:11239\n6244#1:11240\n6269#1:11241\n6294#1:11242\n6319#1:11243\n6344#1:11244\n6369#1:11245\n6394#1:11246\n6419#1:11247\n6444#1:11248\n6471#1:11249\n6498#1:11250\n6525#1:11251\n6550#1:11252\n6573#1:11253\n6596#1:11254\n6619#1:11255\n6642#1:11256\n6665#1:11257\n6688#1:11258\n6711#1:11259\n6734#1:11260\n6759#1:11261\n6784#1:11262\n6809#1:11263\n6836#1:11264\n6863#1:11265\n6890#1:11266\n6917#1:11267\n6942#1:11268\n6967#1:11269\n6992#1:11270\n7017#1:11271\n7036#1:11272\n7053#1:11273\n7070#1:11274\n7087#1:11275\n7106#1:11276\n7125#1:11277\n7144#1:11278\n7163#1:11279\n7178#1:11280\n7193#1:11281\n7208#1:11282\n7223#1:11283\n7244#1:11284\n7265#1:11285\n7286#1:11286\n7307#1:11287\n7336#1:11288\n7372#1:11289\n7408#1:11290\n7444#1:11291\n7476#1:11292\n7508#1:11293\n7540#1:11294\n7572#1:11295\n7604#1:11296\n7629#1:11297\n7654#1:11298\n7679#1:11299\n7704#1:11300\n7729#1:11301\n7754#1:11302\n7779#1:11303\n7804#1:11304\n7831#1:11305\n7858#1:11306\n7885#1:11307\n7910#1:11308\n7933#1:11309\n7956#1:11310\n7979#1:11311\n8002#1:11312\n8025#1:11313\n8048#1:11314\n8071#1:11315\n8094#1:11316\n8119#1:11317\n8144#1:11318\n8169#1:11319\n8196#1:11320\n8223#1:11321\n8250#1:11322\n8277#1:11323\n8302#1:11324\n8327#1:11325\n8352#1:11326\n8377#1:11327\n8396#1:11328\n8413#1:11329\n8430#1:11330\n8447#1:11331\n8466#1:11332\n8485#1:11333\n8504#1:11334\n8523#1:11335\n8538#1:11336\n8553#1:11337\n8568#1:11338\n8583#1:11339\n8801#1:11340\n8826#1:11341\n8851#1:11342\n8876#1:11343\n8901#1:11344\n8926#1:11345\n8951#1:11346\n8976#1:11347\n9000#1:11348\n9024#1:11349\n9048#1:11350\n9072#1:11351\n9096#1:11352\n9120#1:11353\n9144#1:11354\n9168#1:11355\n9190#1:11356\n9215#1:11357\n9240#1:11358\n9265#1:11359\n9290#1:11360\n9316#1:11361\n9342#1:11362\n9368#1:11363\n9393#1:11364\n9418#1:11365\n9443#1:11366\n9468#1:11367\n9493#1:11368\n9517#1:11369\n9541#1:11370\n9565#1:11371\n841#1:11103,6\n851#1:11109,6\n861#1:11115,6\n871#1:11121,6\n881#1:11127,6\n891#1:11133,6\n901#1:11139,6\n911#1:11145,6\n4840#1:11164,7\n4860#1:11171,7\n4880#1:11178,7\n4900#1:11185,7\n4921#1:11192,7\n4942#1:11199,7\n4963#1:11206,7\n4984#1:11213,7\n*E\n"})
public class UArraysKt___UArraysKt extends UArraysKt___UArraysJvmKt {
    /* JADX INFO: renamed from: $r8$lambda$J5SDvJOHM5CRm-HrCI8Cg89yMQs, reason: not valid java name */
    public static Iterator m1108$r8$lambda$J5SDvJOHM5CRmHrCI8Cg89yMQs(int[] iArr) {
        return new UIntArray.Iterator(iArr);
    }

    public static Iterator $r8$lambda$jZPdNkI46wCiLyNfAHggBQiAMcU(short[] sArr) {
        return new UShortArray.Iterator(sArr);
    }

    public static Iterator $r8$lambda$mBmt3ZSYhoqMn71ZbyMauS5VhEo(byte[] bArr) {
        return new UByteArray.Iterator(bArr);
    }

    public static Iterator $r8$lambda$yrlcmyE_6urw4fu2O7WTz4NDrMc(long[] jArr) {
        return new ULongArray.Iterator(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: all-JOV_ifY, reason: not valid java name */
    public static final boolean m1109allJOV_ifY(byte[] all, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : all) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: all-MShoTSo, reason: not valid java name */
    public static final boolean m1110allMShoTSo(long[] all, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : all) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: all-jgv0xPQ, reason: not valid java name */
    public static final boolean m1111alljgv0xPQ(int[] all, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : all) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: all-xTcfx_M, reason: not valid java name */
    public static final boolean m1112allxTcfx_M(short[] all, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : all) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any--ajY-9A, reason: not valid java name */
    public static final boolean m1113anyajY9A(int[] any) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        return ArraysKt___ArraysKt.any(any);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-GBYM_sE, reason: not valid java name */
    public static final boolean m1114anyGBYM_sE(byte[] any) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        return ArraysKt___ArraysKt.any(any);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-JOV_ifY, reason: not valid java name */
    public static final boolean m1115anyJOV_ifY(byte[] any, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : any) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-MShoTSo, reason: not valid java name */
    public static final boolean m1116anyMShoTSo(long[] any, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : any) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-QwZRm1k, reason: not valid java name */
    public static final boolean m1117anyQwZRm1k(long[] any) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        return ArraysKt___ArraysKt.any(any);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-jgv0xPQ, reason: not valid java name */
    public static final boolean m1118anyjgv0xPQ(int[] any, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : any) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-rL5Bavg, reason: not valid java name */
    public static final boolean m1119anyrL5Bavg(short[] any) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        return ArraysKt___ArraysKt.any(any);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: any-xTcfx_M, reason: not valid java name */
    public static final boolean m1120anyxTcfx_M(short[] any, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : any) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: asByteArray-GBYM_sE, reason: not valid java name */
    public static final byte[] m1121asByteArrayGBYM_sE(byte[] asByteArray) {
        Intrinsics.checkNotNullParameter(asByteArray, "$this$asByteArray");
        return asByteArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: asIntArray--ajY-9A, reason: not valid java name */
    public static final int[] m1122asIntArrayajY9A(int[] asIntArray) {
        Intrinsics.checkNotNullParameter(asIntArray, "$this$asIntArray");
        return asIntArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: asLongArray-QwZRm1k, reason: not valid java name */
    public static final long[] m1123asLongArrayQwZRm1k(long[] asLongArray) {
        Intrinsics.checkNotNullParameter(asLongArray, "$this$asLongArray");
        return asLongArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: asShortArray-rL5Bavg, reason: not valid java name */
    public static final short[] m1124asShortArrayrL5Bavg(short[] asShortArray) {
        Intrinsics.checkNotNullParameter(asShortArray, "$this$asShortArray");
        return asShortArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final byte[] asUByteArray(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final int[] asUIntArray(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final long[] asULongArray(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final short[] asUShortArray(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWith-JOV_ifY, reason: not valid java name */
    public static final <V> Map<UByte, V> m1125associateWithJOV_ifY(byte[] associateWith, Function1<? super UByte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateWith.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (byte b : associateWith) {
            linkedHashMap.put(new UByte(b), valueSelector.invoke(new UByte(b)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWith-MShoTSo, reason: not valid java name */
    public static final <V> Map<ULong, V> m1126associateWithMShoTSo(long[] associateWith, Function1<? super ULong, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateWith.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (long j : associateWith) {
            linkedHashMap.put(new ULong(j), valueSelector.invoke(new ULong(j)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWith-jgv0xPQ, reason: not valid java name */
    public static final <V> Map<UInt, V> m1127associateWithjgv0xPQ(int[] associateWith, Function1<? super UInt, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateWith.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i : associateWith) {
            linkedHashMap.put(new UInt(i), valueSelector.invoke(new UInt(i)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWith-xTcfx_M, reason: not valid java name */
    public static final <V> Map<UShort, V> m1128associateWithxTcfx_M(short[] associateWith, Function1<? super UShort, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(associateWith.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (short s : associateWith) {
            linkedHashMap.put(new UShort(s), valueSelector.invoke(new UShort(s)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWithTo-4D70W2E, reason: not valid java name */
    public static final <V, M extends Map<? super UInt, ? super V>> M m1129associateWithTo4D70W2E(int[] associateWithTo, M destination, Function1<? super UInt, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (int i : associateWithTo) {
            destination.put(new UInt(i), valueSelector.invoke(new UInt(i)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWithTo-H21X9dk, reason: not valid java name */
    public static final <V, M extends Map<? super UByte, ? super V>> M m1130associateWithToH21X9dk(byte[] associateWithTo, M destination, Function1<? super UByte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (byte b : associateWithTo) {
            destination.put(new UByte(b), valueSelector.invoke(new UByte(b)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWithTo-X6OPwNk, reason: not valid java name */
    public static final <V, M extends Map<? super ULong, ? super V>> M m1131associateWithToX6OPwNk(long[] associateWithTo, M destination, Function1<? super ULong, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (long j : associateWithTo) {
            destination.put(new ULong(j), valueSelector.invoke(new ULong(j)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: associateWithTo-ciTST-8, reason: not valid java name */
    public static final <V, M extends Map<? super UShort, ? super V>> M m1132associateWithTociTST8(short[] associateWithTo, M destination, Function1<? super UShort, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (short s : associateWithTo) {
            destination.put(new UShort(s), valueSelector.invoke(new UShort(s)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component1--ajY-9A, reason: not valid java name */
    public static final int m1133component1ajY9A(int[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1[0];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component1-GBYM_sE, reason: not valid java name */
    public static final byte m1134component1GBYM_sE(byte[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1[0];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component1-QwZRm1k, reason: not valid java name */
    public static final long m1135component1QwZRm1k(long[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1[0];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component1-rL5Bavg, reason: not valid java name */
    public static final short m1136component1rL5Bavg(short[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1[0];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component2--ajY-9A, reason: not valid java name */
    public static final int m1137component2ajY9A(int[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2[1];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component2-GBYM_sE, reason: not valid java name */
    public static final byte m1138component2GBYM_sE(byte[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2[1];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component2-QwZRm1k, reason: not valid java name */
    public static final long m1139component2QwZRm1k(long[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2[1];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component2-rL5Bavg, reason: not valid java name */
    public static final short m1140component2rL5Bavg(short[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2[1];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component3--ajY-9A, reason: not valid java name */
    public static final int m1141component3ajY9A(int[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3[2];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component3-GBYM_sE, reason: not valid java name */
    public static final byte m1142component3GBYM_sE(byte[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3[2];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component3-QwZRm1k, reason: not valid java name */
    public static final long m1143component3QwZRm1k(long[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3[2];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component3-rL5Bavg, reason: not valid java name */
    public static final short m1144component3rL5Bavg(short[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3[2];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component4--ajY-9A, reason: not valid java name */
    public static final int m1145component4ajY9A(int[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4[3];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component4-GBYM_sE, reason: not valid java name */
    public static final byte m1146component4GBYM_sE(byte[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4[3];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component4-QwZRm1k, reason: not valid java name */
    public static final long m1147component4QwZRm1k(long[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4[3];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component4-rL5Bavg, reason: not valid java name */
    public static final short m1148component4rL5Bavg(short[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4[3];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component5--ajY-9A, reason: not valid java name */
    public static final int m1149component5ajY9A(int[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return component5[4];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component5-GBYM_sE, reason: not valid java name */
    public static final byte m1150component5GBYM_sE(byte[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return component5[4];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component5-QwZRm1k, reason: not valid java name */
    public static final long m1151component5QwZRm1k(long[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return component5[4];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: component5-rL5Bavg, reason: not valid java name */
    public static final short m1152component5rL5Bavg(short[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return component5[4];
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentEquals-FGO6Aew, reason: not valid java name */
    public static boolean m1153contentEqualsFGO6Aew(@Nullable short[] sArr, @Nullable short[] sArr2) {
        if (sArr == null) {
            sArr = null;
        }
        if (sArr2 == null) {
            sArr2 = null;
        }
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentEquals-KJPZfPQ, reason: not valid java name */
    public static boolean m1154contentEqualsKJPZfPQ(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (iArr == null) {
            iArr = null;
        }
        if (iArr2 == null) {
            iArr2 = null;
        }
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentEquals-kV0jMPg, reason: not valid java name */
    public static boolean m1155contentEqualskV0jMPg(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            bArr = null;
        }
        if (bArr2 == null) {
            bArr2 = null;
        }
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentEquals-lec5QzE, reason: not valid java name */
    public static boolean m1156contentEqualslec5QzE(@Nullable long[] jArr, @Nullable long[] jArr2) {
        if (jArr == null) {
            jArr = null;
        }
        if (jArr2 == null) {
            jArr2 = null;
        }
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentHashCode-2csIQuQ, reason: not valid java name */
    public static final int m1157contentHashCode2csIQuQ(@Nullable byte[] bArr) {
        if (bArr == null) {
            bArr = null;
        }
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentHashCode-XUkPCBk, reason: not valid java name */
    public static final int m1158contentHashCodeXUkPCBk(@Nullable int[] iArr) {
        if (iArr == null) {
            iArr = null;
        }
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentHashCode-d-6D3K8, reason: not valid java name */
    public static final int m1159contentHashCoded6D3K8(@Nullable short[] sArr) {
        if (sArr == null) {
            sArr = null;
        }
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: contentHashCode-uLth9ew, reason: not valid java name */
    public static final int m1160contentHashCodeuLth9ew(@Nullable long[] jArr) {
        if (jArr == null) {
            jArr = null;
        }
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: contentToString-2csIQuQ, reason: not valid java name */
    public static String m1161contentToString2csIQuQ(@Nullable byte[] bArr) {
        String strJoinToString$default;
        return (bArr == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(new UByteArray(bArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? AbstractJsonLexerKt.NULL : strJoinToString$default;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: contentToString-XUkPCBk, reason: not valid java name */
    public static String m1162contentToStringXUkPCBk(@Nullable int[] iArr) {
        String strJoinToString$default;
        return (iArr == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(new UIntArray(iArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? AbstractJsonLexerKt.NULL : strJoinToString$default;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: contentToString-d-6D3K8, reason: not valid java name */
    public static String m1163contentToStringd6D3K8(@Nullable short[] sArr) {
        String strJoinToString$default;
        return (sArr == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(new UShortArray(sArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? AbstractJsonLexerKt.NULL : strJoinToString$default;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: contentToString-uLth9ew, reason: not valid java name */
    public static String m1164contentToStringuLth9ew(@Nullable long[] jArr) {
        String strJoinToString$default;
        return (jArr == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(new ULongArray(jArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? AbstractJsonLexerKt.NULL : strJoinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyInto--B0-L2c, reason: not valid java name */
    public static final long[] m1165copyIntoB0L2c(long[] copyInto, long[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    /* JADX INFO: renamed from: copyInto--B0-L2c$default, reason: not valid java name */
    public static long[] m1166copyIntoB0L2c$default(long[] copyInto, long[] destination, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = copyInto.length;
        }
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyInto-9-ak10g, reason: not valid java name */
    public static final short[] m1167copyInto9ak10g(short[] copyInto, short[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    /* JADX INFO: renamed from: copyInto-9-ak10g$default, reason: not valid java name */
    public static short[] m1168copyInto9ak10g$default(short[] copyInto, short[] destination, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = copyInto.length;
        }
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyInto-FUQE5sA, reason: not valid java name */
    public static final byte[] m1169copyIntoFUQE5sA(byte[] copyInto, byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    /* JADX INFO: renamed from: copyInto-FUQE5sA$default, reason: not valid java name */
    public static byte[] m1170copyIntoFUQE5sA$default(byte[] copyInto, byte[] destination, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = copyInto.length;
        }
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyInto-sIZ3KeM, reason: not valid java name */
    public static final int[] m1171copyIntosIZ3KeM(int[] copyInto, int[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    /* JADX INFO: renamed from: copyInto-sIZ3KeM$default, reason: not valid java name */
    public static int[] m1172copyIntosIZ3KeM$default(int[] copyInto, int[] destination, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = copyInto.length;
        }
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i, i2, i3);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf--ajY-9A, reason: not valid java name */
    public static final int[] m1173copyOfajY9A(int[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        int[] iArrCopyOf = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-GBYM_sE, reason: not valid java name */
    public static final byte[] m1174copyOfGBYM_sE(byte[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        byte[] bArrCopyOf = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-PpDY95g, reason: not valid java name */
    public static final byte[] m1175copyOfPpDY95g(byte[] copyOf, int i) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        byte[] bArrCopyOf = Arrays.copyOf(copyOf, i);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-QwZRm1k, reason: not valid java name */
    public static final long[] m1176copyOfQwZRm1k(long[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        long[] jArrCopyOf = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-nggk6HY, reason: not valid java name */
    public static final short[] m1177copyOfnggk6HY(short[] copyOf, int i) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        short[] sArrCopyOf = Arrays.copyOf(copyOf, i);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-qFRl0hI, reason: not valid java name */
    public static final int[] m1178copyOfqFRl0hI(int[] copyOf, int i) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        int[] iArrCopyOf = Arrays.copyOf(copyOf, i);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-r7IrZao, reason: not valid java name */
    public static final long[] m1179copyOfr7IrZao(long[] copyOf, int i) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        long[] jArrCopyOf = Arrays.copyOf(copyOf, i);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOf-rL5Bavg, reason: not valid java name */
    public static final short[] m1180copyOfrL5Bavg(short[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        short[] sArrCopyOf = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOfRange--nroSd4, reason: not valid java name */
    public static final long[] m1181copyOfRangenroSd4(long[] copyOfRange, int i, int i2) {
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        return ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOfRange-4UcCI2c, reason: not valid java name */
    public static final byte[] m1182copyOfRange4UcCI2c(byte[] copyOfRange, int i, int i2) {
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        return ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOfRange-Aa5vz7o, reason: not valid java name */
    public static final short[] m1183copyOfRangeAa5vz7o(short[] copyOfRange, int i, int i2) {
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        return ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: copyOfRange-oBK06Vg, reason: not valid java name */
    public static final int[] m1184copyOfRangeoBK06Vg(int[] copyOfRange, int i, int i2) {
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        return ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: count-JOV_ifY, reason: not valid java name */
    public static final int m1185countJOV_ifY(byte[] count, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (byte b : count) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: count-MShoTSo, reason: not valid java name */
    public static final int m1186countMShoTSo(long[] count, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (long j : count) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: count-jgv0xPQ, reason: not valid java name */
    public static final int m1187countjgv0xPQ(int[] count, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (int i2 : count) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: count-xTcfx_M, reason: not valid java name */
    public static final int m1188countxTcfx_M(short[] count, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (short s : count) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: drop-PpDY95g, reason: not valid java name */
    public static final List<UByte> m1189dropPpDY95g(@NotNull byte[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = drop.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1717takeLastPpDY95g(drop, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: drop-nggk6HY, reason: not valid java name */
    public static final List<UShort> m1190dropnggk6HY(@NotNull short[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = drop.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1718takeLastnggk6HY(drop, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: drop-qFRl0hI, reason: not valid java name */
    public static final List<UInt> m1191dropqFRl0hI(@NotNull int[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = drop.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1719takeLastqFRl0hI(drop, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: drop-r7IrZao, reason: not valid java name */
    public static final List<ULong> m1192dropr7IrZao(@NotNull long[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = drop.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1720takeLastr7IrZao(drop, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: dropLast-PpDY95g, reason: not valid java name */
    public static final List<UByte> m1193dropLastPpDY95g(@NotNull byte[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = dropLast.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1713takePpDY95g(dropLast, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: dropLast-nggk6HY, reason: not valid java name */
    public static final List<UShort> m1194dropLastnggk6HY(@NotNull short[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = dropLast.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1714takenggk6HY(dropLast, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: dropLast-qFRl0hI, reason: not valid java name */
    public static final List<UInt> m1195dropLastqFRl0hI(@NotNull int[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = dropLast.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1715takeqFRl0hI(dropLast, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: dropLast-r7IrZao, reason: not valid java name */
    public static final List<ULong> m1196dropLastr7IrZao(@NotNull long[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = dropLast.length - i;
        if (length < 0) {
            length = 0;
        }
        return m1716taker7IrZao(dropLast, length);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropLastWhile-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1197dropLastWhileJOV_ifY(byte[] dropLastWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dropLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(dropLastWhile[length], predicate)).booleanValue());
        return m1713takePpDY95g(dropLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropLastWhile-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1198dropLastWhileMShoTSo(long[] dropLastWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dropLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(dropLastWhile[length], predicate)).booleanValue());
        return m1716taker7IrZao(dropLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropLastWhile-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1199dropLastWhilejgv0xPQ(int[] dropLastWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dropLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(dropLastWhile[length], predicate)).booleanValue());
        return m1715takeqFRl0hI(dropLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropLastWhile-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1200dropLastWhilexTcfx_M(short[] dropLastWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dropLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(dropLastWhile[length], predicate)).booleanValue());
        return m1714takenggk6HY(dropLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropWhile-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1201dropWhileJOV_ifY(byte[] dropWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (byte b : dropWhile) {
            if (z) {
                arrayList.add(new UByte(b));
            } else if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                arrayList.add(new UByte(b));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropWhile-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1202dropWhileMShoTSo(long[] dropWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (long j : dropWhile) {
            if (z) {
                arrayList.add(new ULong(j));
            } else if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                arrayList.add(new ULong(j));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropWhile-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1203dropWhilejgv0xPQ(int[] dropWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i : dropWhile) {
            if (z) {
                arrayList.add(new UInt(i));
            } else if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                arrayList.add(new UInt(i));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: dropWhile-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1204dropWhilexTcfx_M(short[] dropWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (short s : dropWhile) {
            if (z) {
                arrayList.add(new UShort(s));
            } else if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                arrayList.add(new UShort(s));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrElse-CVVdw08, reason: not valid java name */
    public static final short m1205elementAtOrElseCVVdw08(short[] elementAtOrElse, int i, Function1<? super Integer, UShort> defaultValue) {
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= elementAtOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : elementAtOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrElse-QxvSvLU, reason: not valid java name */
    public static final int m1206elementAtOrElseQxvSvLU(int[] elementAtOrElse, int i, Function1<? super Integer, UInt> defaultValue) {
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= elementAtOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : elementAtOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrElse-Xw8i6dc, reason: not valid java name */
    public static final long m1207elementAtOrElseXw8i6dc(long[] elementAtOrElse, int i, Function1<? super Integer, ULong> defaultValue) {
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= elementAtOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : elementAtOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrElse-cO-VybQ, reason: not valid java name */
    public static final byte m1208elementAtOrElsecOVybQ(byte[] elementAtOrElse, int i, Function1<? super Integer, UByte> defaultValue) {
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= elementAtOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : elementAtOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrNull-PpDY95g, reason: not valid java name */
    public static final UByte m1209elementAtOrNullPpDY95g(byte[] elementAtOrNull, int i) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m1329getOrNullPpDY95g(elementAtOrNull, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrNull-nggk6HY, reason: not valid java name */
    public static final UShort m1210elementAtOrNullnggk6HY(short[] elementAtOrNull, int i) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m1330getOrNullnggk6HY(elementAtOrNull, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrNull-qFRl0hI, reason: not valid java name */
    public static final UInt m1211elementAtOrNullqFRl0hI(int[] elementAtOrNull, int i) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m1331getOrNullqFRl0hI(elementAtOrNull, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: elementAtOrNull-r7IrZao, reason: not valid java name */
    public static final ULong m1212elementAtOrNullr7IrZao(long[] elementAtOrNull, int i) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m1332getOrNullr7IrZao(elementAtOrNull, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: fill-2fe2U9s, reason: not valid java name */
    public static final void m1213fill2fe2U9s(@NotNull int[] fill, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, i2, i3, i);
    }

    /* JADX INFO: renamed from: fill-2fe2U9s$default, reason: not valid java name */
    public static void m1214fill2fe2U9s$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        m1213fill2fe2U9s(iArr, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: fill-EtDCXyQ, reason: not valid java name */
    public static final void m1215fillEtDCXyQ(@NotNull short[] fill, short s, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, i, i2, s);
    }

    /* JADX INFO: renamed from: fill-EtDCXyQ$default, reason: not valid java name */
    public static void m1216fillEtDCXyQ$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        m1215fillEtDCXyQ(sArr, s, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: fill-K6DWlUc, reason: not valid java name */
    public static final void m1217fillK6DWlUc(@NotNull long[] fill, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, i, i2, j);
    }

    /* JADX INFO: renamed from: fill-K6DWlUc$default, reason: not valid java name */
    public static void m1218fillK6DWlUc$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        m1217fillK6DWlUc(jArr, j, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: fill-WpHrYlw, reason: not valid java name */
    public static final void m1219fillWpHrYlw(@NotNull byte[] fill, byte b, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, i, i2, b);
    }

    /* JADX INFO: renamed from: fill-WpHrYlw$default, reason: not valid java name */
    public static void m1220fillWpHrYlw$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        m1219fillWpHrYlw(bArr, b, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filter-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1221filterJOV_ifY(byte[] filter, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : filter) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                arrayList.add(new UByte(b));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filter-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1222filterMShoTSo(long[] filter, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : filter) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                arrayList.add(new ULong(j));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filter-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1223filterjgv0xPQ(int[] filter, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : filter) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                arrayList.add(new UInt(i));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filter-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1224filterxTcfx_M(short[] filter, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : filter) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                arrayList.add(new UShort(s));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexed-ELGow60, reason: not valid java name */
    public static final List<UByte> m1225filterIndexedELGow60(byte[] filterIndexed, Function2<? super Integer, ? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = filterIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = filterIndexed[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UByte(b)).booleanValue()) {
                arrayList.add(new UByte(b));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexed-WyvcNBI, reason: not valid java name */
    public static final List<UInt> m1226filterIndexedWyvcNBI(int[] filterIndexed, Function2<? super Integer, ? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = filterIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = filterIndexed[i];
            int i4 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UInt(i3)).booleanValue()) {
                arrayList.add(new UInt(i3));
            }
            i++;
            i2 = i4;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexed-s8dVfGU, reason: not valid java name */
    public static final List<ULong> m1227filterIndexeds8dVfGU(long[] filterIndexed, Function2<? super Integer, ? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = filterIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            long j = filterIndexed[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new ULong(j)).booleanValue()) {
                arrayList.add(new ULong(j));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexed-xzaTVY8, reason: not valid java name */
    public static final List<UShort> m1228filterIndexedxzaTVY8(short[] filterIndexed, Function2<? super Integer, ? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = filterIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            short s = filterIndexed[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UShort(s)).booleanValue()) {
                arrayList.add(new UShort(s));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexedTo--6EtJGI, reason: not valid java name */
    public static final <C extends Collection<? super UInt>> C m1229filterIndexedTo6EtJGI(int[] filterIndexedTo, C destination, Function2<? super Integer, ? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = filterIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = filterIndexedTo[i];
            int i4 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UInt(i3)).booleanValue()) {
                destination.add(new UInt(i3));
            }
            i++;
            i2 = i4;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexedTo-QqktQ3k, reason: not valid java name */
    public static final <C extends Collection<? super UShort>> C m1230filterIndexedToQqktQ3k(short[] filterIndexedTo, C destination, Function2<? super Integer, ? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = filterIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            short s = filterIndexedTo[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UShort(s)).booleanValue()) {
                destination.add(new UShort(s));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexedTo-eNpIKz8, reason: not valid java name */
    public static final <C extends Collection<? super UByte>> C m1231filterIndexedToeNpIKz8(byte[] filterIndexedTo, C destination, Function2<? super Integer, ? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = filterIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = filterIndexedTo[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new UByte(b)).booleanValue()) {
                destination.add(new UByte(b));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterIndexedTo-pe2Q0Dw, reason: not valid java name */
    public static final <C extends Collection<? super ULong>> C m1232filterIndexedTope2Q0Dw(long[] filterIndexedTo, C destination, Function2<? super Integer, ? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = filterIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            long j = filterIndexedTo[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), new ULong(j)).booleanValue()) {
                destination.add(new ULong(j));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNot-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1233filterNotJOV_ifY(byte[] filterNot, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : filterNot) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                arrayList.add(new UByte(b));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNot-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1234filterNotMShoTSo(long[] filterNot, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : filterNot) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                arrayList.add(new ULong(j));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNot-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1235filterNotjgv0xPQ(int[] filterNot, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : filterNot) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                arrayList.add(new UInt(i));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNot-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1236filterNotxTcfx_M(short[] filterNot, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : filterNot) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                arrayList.add(new UShort(s));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNotTo-HqK1JgA, reason: not valid java name */
    public static final <C extends Collection<? super ULong>> C m1237filterNotToHqK1JgA(long[] filterNotTo, C destination, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : filterNotTo) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                destination.add(new ULong(j));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNotTo-oEOeDjA, reason: not valid java name */
    public static final <C extends Collection<? super UShort>> C m1238filterNotTooEOeDjA(short[] filterNotTo, C destination, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : filterNotTo) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                destination.add(new UShort(s));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNotTo-wU5IKMo, reason: not valid java name */
    public static final <C extends Collection<? super UInt>> C m1239filterNotTowU5IKMo(int[] filterNotTo, C destination, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : filterNotTo) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                destination.add(new UInt(i));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterNotTo-wzUQCXU, reason: not valid java name */
    public static final <C extends Collection<? super UByte>> C m1240filterNotTowzUQCXU(byte[] filterNotTo, C destination, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : filterNotTo) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                destination.add(new UByte(b));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterTo-HqK1JgA, reason: not valid java name */
    public static final <C extends Collection<? super ULong>> C m1241filterToHqK1JgA(long[] filterTo, C destination, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : filterTo) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                destination.add(new ULong(j));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterTo-oEOeDjA, reason: not valid java name */
    public static final <C extends Collection<? super UShort>> C m1242filterTooEOeDjA(short[] filterTo, C destination, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : filterTo) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                destination.add(new UShort(s));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterTo-wU5IKMo, reason: not valid java name */
    public static final <C extends Collection<? super UInt>> C m1243filterTowU5IKMo(int[] filterTo, C destination, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : filterTo) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                destination.add(new UInt(i));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: filterTo-wzUQCXU, reason: not valid java name */
    public static final <C extends Collection<? super UByte>> C m1244filterTowzUQCXU(byte[] filterTo, C destination, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : filterTo) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                destination.add(new UByte(b));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: find-JOV_ifY, reason: not valid java name */
    public static final UByte m1245findJOV_ifY(byte[] find, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : find) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return new UByte(b);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: find-MShoTSo, reason: not valid java name */
    public static final ULong m1246findMShoTSo(long[] find, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : find) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return new ULong(j);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: find-jgv0xPQ, reason: not valid java name */
    public static final UInt m1247findjgv0xPQ(int[] find, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : find) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return new UInt(i);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: find-xTcfx_M, reason: not valid java name */
    public static final UShort m1248findxTcfx_M(short[] find, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : find) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return new UShort(s);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: findLast-JOV_ifY, reason: not valid java name */
    public static final UByte m1249findLastJOV_ifY(byte[] findLast, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = findLast.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            byte b = findLast[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return new UByte(b);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: findLast-MShoTSo, reason: not valid java name */
    public static final ULong m1250findLastMShoTSo(long[] findLast, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = findLast.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            long j = findLast[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return new ULong(j);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: findLast-jgv0xPQ, reason: not valid java name */
    public static final UInt m1251findLastjgv0xPQ(int[] findLast, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = findLast.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            int i2 = findLast[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, predicate)).booleanValue()) {
                return new UInt(i2);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: findLast-xTcfx_M, reason: not valid java name */
    public static final UShort m1252findLastxTcfx_M(short[] findLast, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = findLast.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            short s = findLast[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return new UShort(s);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first--ajY-9A, reason: not valid java name */
    public static final int m1253firstajY9A(int[] first) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        return ArraysKt___ArraysKt.first(first);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-GBYM_sE, reason: not valid java name */
    public static final byte m1254firstGBYM_sE(byte[] first) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        return ArraysKt___ArraysKt.first(first);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-JOV_ifY, reason: not valid java name */
    public static final byte m1255firstJOV_ifY(byte[] first, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : first) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return b;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-MShoTSo, reason: not valid java name */
    public static final long m1256firstMShoTSo(long[] first, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : first) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return j;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-QwZRm1k, reason: not valid java name */
    public static final long m1257firstQwZRm1k(long[] first) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        return ArraysKt___ArraysKt.first(first);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-jgv0xPQ, reason: not valid java name */
    public static final int m1258firstjgv0xPQ(int[] first, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : first) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return i;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-rL5Bavg, reason: not valid java name */
    public static final short m1259firstrL5Bavg(short[] first) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        return ArraysKt___ArraysKt.first(first);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: first-xTcfx_M, reason: not valid java name */
    public static final short m1260firstxTcfx_M(short[] first, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : first) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return s;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: firstOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1261firstOrNullajY9A(@NotNull int[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (firstOrNull.length == 0) {
            return null;
        }
        return new UInt(firstOrNull[0]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: firstOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1262firstOrNullGBYM_sE(@NotNull byte[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (firstOrNull.length == 0) {
            return null;
        }
        return new UByte(firstOrNull[0]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: firstOrNull-JOV_ifY, reason: not valid java name */
    public static final UByte m1263firstOrNullJOV_ifY(byte[] firstOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : firstOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return new UByte(b);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: firstOrNull-MShoTSo, reason: not valid java name */
    public static final ULong m1264firstOrNullMShoTSo(long[] firstOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : firstOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return new ULong(j);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: firstOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1265firstOrNullQwZRm1k(@NotNull long[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (firstOrNull.length == 0) {
            return null;
        }
        return new ULong(firstOrNull[0]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: firstOrNull-jgv0xPQ, reason: not valid java name */
    public static final UInt m1266firstOrNulljgv0xPQ(int[] firstOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : firstOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return new UInt(i);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: firstOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1267firstOrNullrL5Bavg(@NotNull short[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (firstOrNull.length == 0) {
            return null;
        }
        return new UShort(firstOrNull[0]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: firstOrNull-xTcfx_M, reason: not valid java name */
    public static final UShort m1268firstOrNullxTcfx_M(short[] firstOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : firstOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return new UShort(s);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMap-JOV_ifY, reason: not valid java name */
    public static final <R> List<R> m1269flatMapJOV_ifY(byte[] flatMap, Function1<? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (byte b : flatMap) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, transform));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMap-MShoTSo, reason: not valid java name */
    public static final <R> List<R> m1270flatMapMShoTSo(long[] flatMap, Function1<? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (long j : flatMap) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, transform));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMap-jgv0xPQ, reason: not valid java name */
    public static final <R> List<R> m1271flatMapjgv0xPQ(int[] flatMap, Function1<? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i : flatMap) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, transform));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMap-xTcfx_M, reason: not valid java name */
    public static final <R> List<R> m1272flatMapxTcfx_M(short[] flatMap, Function1<? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (short s : flatMap) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, transform));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexed-ELGow60, reason: not valid java name */
    public static final <R> List<R> m1273flatMapIndexedELGow60(byte[] flatMapIndexed, Function2<? super Integer, ? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = flatMapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), new UByte(flatMapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexed-WyvcNBI, reason: not valid java name */
    public static final <R> List<R> m1274flatMapIndexedWyvcNBI(int[] flatMapIndexed, Function2<? super Integer, ? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = flatMapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), new UInt(flatMapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexed-s8dVfGU, reason: not valid java name */
    public static final <R> List<R> m1275flatMapIndexeds8dVfGU(long[] flatMapIndexed, Function2<? super Integer, ? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = flatMapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), new ULong(flatMapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexed-xzaTVY8, reason: not valid java name */
    public static final <R> List<R> m1276flatMapIndexedxzaTVY8(short[] flatMapIndexed, Function2<? super Integer, ? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = flatMapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), new UShort(flatMapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexedTo--6EtJGI, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1277flatMapIndexedTo6EtJGI(int[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = flatMapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), new UInt(flatMapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexedTo-QqktQ3k, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1278flatMapIndexedToQqktQ3k(short[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = flatMapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), new UShort(flatMapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexedTo-eNpIKz8, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1279flatMapIndexedToeNpIKz8(byte[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = flatMapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), new UByte(flatMapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: flatMapIndexedTo-pe2Q0Dw, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1280flatMapIndexedTope2Q0Dw(long[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = flatMapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), new ULong(flatMapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMapTo-HqK1JgA, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1281flatMapToHqK1JgA(long[] flatMapTo, C destination, Function1<? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j : flatMapTo) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, transform));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMapTo-oEOeDjA, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1282flatMapTooEOeDjA(short[] flatMapTo, C destination, Function1<? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s : flatMapTo) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, transform));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMapTo-wU5IKMo, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1283flatMapTowU5IKMo(int[] flatMapTo, C destination, Function1<? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i : flatMapTo) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, transform));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: flatMapTo-wzUQCXU, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1284flatMapTowzUQCXU(byte[] flatMapTo, C destination, Function1<? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b : flatMapTo) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, (Iterable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, transform));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: fold-A8wKCXQ, reason: not valid java name */
    public static final <R> R m1285foldA8wKCXQ(long[] fold, R r, Function2<? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (long j : fold) {
            r = operation.invoke(r, new ULong(j));
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: fold-yXmHNn8, reason: not valid java name */
    public static final <R> R m1286foldyXmHNn8(byte[] fold, R r, Function2<? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (byte b : fold) {
            r = operation.invoke(r, new UByte(b));
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: fold-zi1B2BA, reason: not valid java name */
    public static final <R> R m1287foldzi1B2BA(int[] fold, R r, Function2<? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int i : fold) {
            r = operation.invoke(r, new UInt(i));
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: fold-zww5nb8, reason: not valid java name */
    public static final <R> R m1288foldzww5nb8(short[] fold, R r, Function2<? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (short s : fold) {
            r = operation.invoke(r, new UShort(s));
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldIndexed-3iWJZGE, reason: not valid java name */
    public static final <R> R m1289foldIndexed3iWJZGE(byte[] foldIndexed, R r, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = foldIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, new UByte(foldIndexed[i]));
            i++;
            i2++;
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldIndexed-bzxtMww, reason: not valid java name */
    public static final <R> R m1290foldIndexedbzxtMww(short[] foldIndexed, R r, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = foldIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, new UShort(foldIndexed[i]));
            i++;
            i2++;
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldIndexed-mwnnOCs, reason: not valid java name */
    public static final <R> R m1291foldIndexedmwnnOCs(long[] foldIndexed, R r, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = foldIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, new ULong(foldIndexed[i]));
            i++;
            i2++;
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldIndexed-yVwIW0Q, reason: not valid java name */
    public static final <R> R m1292foldIndexedyVwIW0Q(int[] foldIndexed, R r, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = foldIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, new UInt(foldIndexed[i]));
            i++;
            i2++;
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRight-A8wKCXQ, reason: not valid java name */
    public static final <R> R m1293foldRightA8wKCXQ(long[] foldRight, R r, Function2<? super ULong, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRight.length - 1; length >= 0; length--) {
            r = operation.invoke(new ULong(foldRight[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRight-yXmHNn8, reason: not valid java name */
    public static final <R> R m1294foldRightyXmHNn8(byte[] foldRight, R r, Function2<? super UByte, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRight.length - 1; length >= 0; length--) {
            r = operation.invoke(new UByte(foldRight[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRight-zi1B2BA, reason: not valid java name */
    public static final <R> R m1295foldRightzi1B2BA(int[] foldRight, R r, Function2<? super UInt, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRight.length - 1; length >= 0; length--) {
            r = operation.invoke(new UInt(foldRight[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRight-zww5nb8, reason: not valid java name */
    public static final <R> R m1296foldRightzww5nb8(short[] foldRight, R r, Function2<? super UShort, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRight.length - 1; length >= 0; length--) {
            r = operation.invoke(new UShort(foldRight[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRightIndexed-3iWJZGE, reason: not valid java name */
    public static final <R> R m1297foldRightIndexed3iWJZGE(byte[] foldRightIndexed, R r, Function3<? super Integer, ? super UByte, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRightIndexed.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), new UByte(foldRightIndexed[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRightIndexed-bzxtMww, reason: not valid java name */
    public static final <R> R m1298foldRightIndexedbzxtMww(short[] foldRightIndexed, R r, Function3<? super Integer, ? super UShort, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRightIndexed.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), new UShort(foldRightIndexed[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRightIndexed-mwnnOCs, reason: not valid java name */
    public static final <R> R m1299foldRightIndexedmwnnOCs(long[] foldRightIndexed, R r, Function3<? super Integer, ? super ULong, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRightIndexed.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), new ULong(foldRightIndexed[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: foldRightIndexed-yVwIW0Q, reason: not valid java name */
    public static final <R> R m1300foldRightIndexedyVwIW0Q(int[] foldRightIndexed, R r, Function3<? super Integer, ? super UInt, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = foldRightIndexed.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), new UInt(foldRightIndexed[length]), r);
        }
        return r;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEach-JOV_ifY, reason: not valid java name */
    public static final void m1301forEachJOV_ifY(byte[] forEach, Function1<? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b : forEach) {
            action.invoke(new UByte(b));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEach-MShoTSo, reason: not valid java name */
    public static final void m1302forEachMShoTSo(long[] forEach, Function1<? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j : forEach) {
            action.invoke(new ULong(j));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEach-jgv0xPQ, reason: not valid java name */
    public static final void m1303forEachjgv0xPQ(int[] forEach, Function1<? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i : forEach) {
            action.invoke(new UInt(i));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEach-xTcfx_M, reason: not valid java name */
    public static final void m1304forEachxTcfx_M(short[] forEach, Function1<? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s : forEach) {
            action.invoke(new UShort(s));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEachIndexed-ELGow60, reason: not valid java name */
    public static final void m1305forEachIndexedELGow60(byte[] forEachIndexed, Function2<? super Integer, ? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = forEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UByte(forEachIndexed[i]));
            i++;
            i2++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEachIndexed-WyvcNBI, reason: not valid java name */
    public static final void m1306forEachIndexedWyvcNBI(int[] forEachIndexed, Function2<? super Integer, ? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = forEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UInt(forEachIndexed[i]));
            i++;
            i2++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEachIndexed-s8dVfGU, reason: not valid java name */
    public static final void m1307forEachIndexeds8dVfGU(long[] forEachIndexed, Function2<? super Integer, ? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = forEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new ULong(forEachIndexed[i]));
            i++;
            i2++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: forEachIndexed-xzaTVY8, reason: not valid java name */
    public static final void m1308forEachIndexedxzaTVY8(short[] forEachIndexed, Function2<? super Integer, ? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = forEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UShort(forEachIndexed[i]));
            i++;
            i2++;
        }
    }

    @NotNull
    /* JADX INFO: renamed from: getIndices--ajY-9A, reason: not valid java name */
    public static final IntRange m1309getIndicesajY9A(@NotNull int[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @NotNull
    /* JADX INFO: renamed from: getIndices-GBYM_sE, reason: not valid java name */
    public static final IntRange m1311getIndicesGBYM_sE(@NotNull byte[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @NotNull
    /* JADX INFO: renamed from: getIndices-QwZRm1k, reason: not valid java name */
    public static final IntRange m1313getIndicesQwZRm1k(@NotNull long[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @NotNull
    /* JADX INFO: renamed from: getIndices-rL5Bavg, reason: not valid java name */
    public static final IntRange m1315getIndicesrL5Bavg(@NotNull short[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    /* JADX INFO: renamed from: getLastIndex--ajY-9A, reason: not valid java name */
    public static final int m1317getLastIndexajY9A(@NotNull int[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return lastIndex.length - 1;
    }

    /* JADX INFO: renamed from: getLastIndex-GBYM_sE, reason: not valid java name */
    public static final int m1319getLastIndexGBYM_sE(@NotNull byte[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return lastIndex.length - 1;
    }

    /* JADX INFO: renamed from: getLastIndex-QwZRm1k, reason: not valid java name */
    public static final int m1321getLastIndexQwZRm1k(@NotNull long[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return lastIndex.length - 1;
    }

    /* JADX INFO: renamed from: getLastIndex-rL5Bavg, reason: not valid java name */
    public static final int m1323getLastIndexrL5Bavg(@NotNull short[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return lastIndex.length - 1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: getOrElse-CVVdw08, reason: not valid java name */
    public static final short m1325getOrElseCVVdw08(short[] getOrElse, int i, Function1<? super Integer, UShort> defaultValue) {
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= getOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : getOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: getOrElse-QxvSvLU, reason: not valid java name */
    public static final int m1326getOrElseQxvSvLU(int[] getOrElse, int i, Function1<? super Integer, UInt> defaultValue) {
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= getOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : getOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: getOrElse-Xw8i6dc, reason: not valid java name */
    public static final long m1327getOrElseXw8i6dc(long[] getOrElse, int i, Function1<? super Integer, ULong> defaultValue) {
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= getOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : getOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: getOrElse-cO-VybQ, reason: not valid java name */
    public static final byte m1328getOrElsecOVybQ(byte[] getOrElse, int i, Function1<? super Integer, UByte> defaultValue) {
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= getOrElse.length) ? defaultValue.invoke(Integer.valueOf(i)).data : getOrElse[i];
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: getOrNull-PpDY95g, reason: not valid java name */
    public static final UByte m1329getOrNullPpDY95g(@NotNull byte[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i >= getOrNull.length) {
            return null;
        }
        return new UByte(getOrNull[i]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: getOrNull-nggk6HY, reason: not valid java name */
    public static final UShort m1330getOrNullnggk6HY(@NotNull short[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i >= getOrNull.length) {
            return null;
        }
        return new UShort(getOrNull[i]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: getOrNull-qFRl0hI, reason: not valid java name */
    public static final UInt m1331getOrNullqFRl0hI(@NotNull int[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i >= getOrNull.length) {
            return null;
        }
        return new UInt(getOrNull[i]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: getOrNull-r7IrZao, reason: not valid java name */
    public static final ULong m1332getOrNullr7IrZao(@NotNull long[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i >= getOrNull.length) {
            return null;
        }
        return new ULong(getOrNull[i]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy--_j2Y-Q, reason: not valid java name */
    public static final <K, V> Map<K, List<V>> m1333groupBy_j2YQ(long[] groupBy, Function1<? super ULong, ? extends K> keySelector, Function1<? super ULong, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new ULong(j)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-3bBvP4M, reason: not valid java name */
    public static final <K, V> Map<K, List<V>> m1334groupBy3bBvP4M(short[] groupBy, Function1<? super UShort, ? extends K> keySelector, Function1<? super UShort, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UShort(s)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-JOV_ifY, reason: not valid java name */
    public static final <K> Map<K, List<UByte>> m1335groupByJOV_ifY(byte[] groupBy, Function1<? super UByte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(new UByte(b));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-L4rlFek, reason: not valid java name */
    public static final <K, V> Map<K, List<V>> m1336groupByL4rlFek(int[] groupBy, Function1<? super UInt, ? extends K> keySelector, Function1<? super UInt, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UInt(i)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-MShoTSo, reason: not valid java name */
    public static final <K> Map<K, List<ULong>> m1337groupByMShoTSo(long[] groupBy, Function1<? super ULong, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(new ULong(j));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-bBsjw1Y, reason: not valid java name */
    public static final <K, V> Map<K, List<V>> m1338groupBybBsjw1Y(byte[] groupBy, Function1<? super UByte, ? extends K> keySelector, Function1<? super UByte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UByte(b)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-jgv0xPQ, reason: not valid java name */
    public static final <K> Map<K, List<UInt>> m1339groupByjgv0xPQ(int[] groupBy, Function1<? super UInt, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(new UInt(i));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupBy-xTcfx_M, reason: not valid java name */
    public static final <K> Map<K, List<UShort>> m1340groupByxTcfx_M(short[] groupBy, Function1<? super UShort, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s : groupBy) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, keySelector);
            Object objM2 = linkedHashMap.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, objM);
            }
            ((List) objM2).add(new UShort(s));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-4D70W2E, reason: not valid java name */
    public static final <K, M extends Map<? super K, List<UInt>>> M m1341groupByTo4D70W2E(int[] groupByTo, M destination, Function1<? super UInt, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(new UInt(i));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-H21X9dk, reason: not valid java name */
    public static final <K, M extends Map<? super K, List<UByte>>> M m1342groupByToH21X9dk(byte[] groupByTo, M destination, Function1<? super UByte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (byte b : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(new UByte(b));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-JM6gNCM, reason: not valid java name */
    public static final <K, V, M extends Map<? super K, List<V>>> M m1343groupByToJM6gNCM(int[] groupByTo, M destination, Function1<? super UInt, ? extends K> keySelector, Function1<? super UInt, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UInt(i)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-QxgOkWg, reason: not valid java name */
    public static final <K, V, M extends Map<? super K, List<V>>> M m1344groupByToQxgOkWg(long[] groupByTo, M destination, Function1<? super ULong, ? extends K> keySelector, Function1<? super ULong, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (long j : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new ULong(j)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-X6OPwNk, reason: not valid java name */
    public static final <K, M extends Map<? super K, List<ULong>>> M m1345groupByToX6OPwNk(long[] groupByTo, M destination, Function1<? super ULong, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (long j : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(new ULong(j));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-ciTST-8, reason: not valid java name */
    public static final <K, M extends Map<? super K, List<UShort>>> M m1346groupByTociTST8(short[] groupByTo, M destination, Function1<? super UShort, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (short s : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(new UShort(s));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-q8RuPII, reason: not valid java name */
    public static final <K, V, M extends Map<? super K, List<V>>> M m1347groupByToq8RuPII(short[] groupByTo, M destination, Function1<? super UShort, ? extends K> keySelector, Function1<? super UShort, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (short s : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UShort(s)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: groupByTo-qOZmbk8, reason: not valid java name */
    public static final <K, V, M extends Map<? super K, List<V>>> M m1348groupByToqOZmbk8(byte[] groupByTo, M destination, Function1<? super UByte, ? extends K> keySelector, Function1<? super UByte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (byte b : groupByTo) {
            Object objM = UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, keySelector);
            Object objM2 = destination.get(objM);
            if (objM2 == null) {
                objM2 = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, objM);
            }
            ((List) objM2).add(valueTransform.invoke(new UByte(b)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOf-3uqUaXg, reason: not valid java name */
    public static final int m1349indexOf3uqUaXg(long[] indexOf, long j) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        return ArraysKt___ArraysKt.indexOf(indexOf, j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOf-XzdR7RA, reason: not valid java name */
    public static final int m1350indexOfXzdR7RA(short[] indexOf, short s) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        return ArraysKt___ArraysKt.indexOf(indexOf, s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOf-gMuBH34, reason: not valid java name */
    public static final int m1351indexOfgMuBH34(byte[] indexOf, byte b) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        return ArraysKt___ArraysKt.indexOf(indexOf, b);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOf-uWY9BYg, reason: not valid java name */
    public static final int m1352indexOfuWY9BYg(int[] indexOf, int i) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        return ArraysKt___ArraysKt.indexOf(indexOf, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfFirst-JOV_ifY, reason: not valid java name */
    public static final int m1353indexOfFirstJOV_ifY(byte[] indexOfFirst, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i = 0; i < length; i++) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(indexOfFirst[i], predicate)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfFirst-MShoTSo, reason: not valid java name */
    public static final int m1354indexOfFirstMShoTSo(long[] indexOfFirst, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i = 0; i < length; i++) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(indexOfFirst[i], predicate)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfFirst-jgv0xPQ, reason: not valid java name */
    public static final int m1355indexOfFirstjgv0xPQ(int[] indexOfFirst, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i = 0; i < length; i++) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(indexOfFirst[i], predicate)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfFirst-xTcfx_M, reason: not valid java name */
    public static final int m1356indexOfFirstxTcfx_M(short[] indexOfFirst, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i = 0; i < length; i++) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(indexOfFirst[i], predicate)).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfLast-JOV_ifY, reason: not valid java name */
    public static final int m1357indexOfLastJOV_ifY(byte[] indexOfLast, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(indexOfLast[length], predicate)).booleanValue()) {
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

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfLast-MShoTSo, reason: not valid java name */
    public static final int m1358indexOfLastMShoTSo(long[] indexOfLast, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(indexOfLast[length], predicate)).booleanValue()) {
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

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfLast-jgv0xPQ, reason: not valid java name */
    public static final int m1359indexOfLastjgv0xPQ(int[] indexOfLast, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(indexOfLast[length], predicate)).booleanValue()) {
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

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: indexOfLast-xTcfx_M, reason: not valid java name */
    public static final int m1360indexOfLastxTcfx_M(short[] indexOfLast, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(indexOfLast[length], predicate)).booleanValue()) {
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

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last--ajY-9A, reason: not valid java name */
    public static final int m1361lastajY9A(int[] last) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        return ArraysKt___ArraysKt.last(last);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-GBYM_sE, reason: not valid java name */
    public static final byte m1362lastGBYM_sE(byte[] last) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        return ArraysKt___ArraysKt.last(last);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-JOV_ifY, reason: not valid java name */
    public static final byte m1363lastJOV_ifY(byte[] last, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = last.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                byte b = last[length];
                if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return b;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-MShoTSo, reason: not valid java name */
    public static final long m1364lastMShoTSo(long[] last, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = last.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                long j = last[length];
                if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return j;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-QwZRm1k, reason: not valid java name */
    public static final long m1365lastQwZRm1k(long[] last) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        return ArraysKt___ArraysKt.last(last);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-jgv0xPQ, reason: not valid java name */
    public static final int m1366lastjgv0xPQ(int[] last, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = last.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                int i2 = last[length];
                if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, predicate)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return i2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-rL5Bavg, reason: not valid java name */
    public static final short m1367lastrL5Bavg(short[] last) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        return ArraysKt___ArraysKt.last(last);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: last-xTcfx_M, reason: not valid java name */
    public static final short m1368lastxTcfx_M(short[] last, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = last.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                short s = last[length];
                if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return s;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastIndexOf-3uqUaXg, reason: not valid java name */
    public static final int m1369lastIndexOf3uqUaXg(long[] lastIndexOf, long j) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        return ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastIndexOf-XzdR7RA, reason: not valid java name */
    public static final int m1370lastIndexOfXzdR7RA(short[] lastIndexOf, short s) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        return ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastIndexOf-gMuBH34, reason: not valid java name */
    public static final int m1371lastIndexOfgMuBH34(byte[] lastIndexOf, byte b) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        return ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, b);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastIndexOf-uWY9BYg, reason: not valid java name */
    public static final int m1372lastIndexOfuWY9BYg(int[] lastIndexOf, int i) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        return ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: lastOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1373lastOrNullajY9A(@NotNull int[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (lastOrNull.length == 0) {
            return null;
        }
        return new UInt(lastOrNull[lastOrNull.length - 1]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: lastOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1374lastOrNullGBYM_sE(@NotNull byte[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (lastOrNull.length == 0) {
            return null;
        }
        return new UByte(lastOrNull[lastOrNull.length - 1]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastOrNull-JOV_ifY, reason: not valid java name */
    public static final UByte m1375lastOrNullJOV_ifY(byte[] lastOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = lastOrNull.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            byte b = lastOrNull[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return new UByte(b);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastOrNull-MShoTSo, reason: not valid java name */
    public static final ULong m1376lastOrNullMShoTSo(long[] lastOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = lastOrNull.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            long j = lastOrNull[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return new ULong(j);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: lastOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1377lastOrNullQwZRm1k(@NotNull long[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (lastOrNull.length == 0) {
            return null;
        }
        return new ULong(lastOrNull[lastOrNull.length - 1]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastOrNull-jgv0xPQ, reason: not valid java name */
    public static final UInt m1378lastOrNulljgv0xPQ(int[] lastOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = lastOrNull.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            int i2 = lastOrNull[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, predicate)).booleanValue()) {
                return new UInt(i2);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: lastOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1379lastOrNullrL5Bavg(@NotNull short[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (lastOrNull.length == 0) {
            return null;
        }
        return new UShort(lastOrNull[lastOrNull.length - 1]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: lastOrNull-xTcfx_M, reason: not valid java name */
    public static final UShort m1380lastOrNullxTcfx_M(short[] lastOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = lastOrNull.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            short s = lastOrNull[length];
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return new UShort(s);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: map-JOV_ifY, reason: not valid java name */
    public static final <R> List<R> m1381mapJOV_ifY(byte[] map, Function1<? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(map.length);
        for (byte b : map) {
            arrayList.add(transform.invoke(new UByte(b)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: map-MShoTSo, reason: not valid java name */
    public static final <R> List<R> m1382mapMShoTSo(long[] map, Function1<? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(map.length);
        for (long j : map) {
            arrayList.add(transform.invoke(new ULong(j)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: map-jgv0xPQ, reason: not valid java name */
    public static final <R> List<R> m1383mapjgv0xPQ(int[] map, Function1<? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(map.length);
        for (int i : map) {
            arrayList.add(transform.invoke(new UInt(i)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: map-xTcfx_M, reason: not valid java name */
    public static final <R> List<R> m1384mapxTcfx_M(short[] map, Function1<? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(map.length);
        for (short s : map) {
            arrayList.add(transform.invoke(new UShort(s)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexed-ELGow60, reason: not valid java name */
    public static final <R> List<R> m1385mapIndexedELGow60(byte[] mapIndexed, Function2<? super Integer, ? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(mapIndexed.length);
        int length = mapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), new UByte(mapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexed-WyvcNBI, reason: not valid java name */
    public static final <R> List<R> m1386mapIndexedWyvcNBI(int[] mapIndexed, Function2<? super Integer, ? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(mapIndexed.length);
        int length = mapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), new UInt(mapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexed-s8dVfGU, reason: not valid java name */
    public static final <R> List<R> m1387mapIndexeds8dVfGU(long[] mapIndexed, Function2<? super Integer, ? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(mapIndexed.length);
        int length = mapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), new ULong(mapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexed-xzaTVY8, reason: not valid java name */
    public static final <R> List<R> m1388mapIndexedxzaTVY8(short[] mapIndexed, Function2<? super Integer, ? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(mapIndexed.length);
        int length = mapIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), new UShort(mapIndexed[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexedTo--6EtJGI, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1389mapIndexedTo6EtJGI(int[] mapIndexedTo, C destination, Function2<? super Integer, ? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = mapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), new UInt(mapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexedTo-QqktQ3k, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1390mapIndexedToQqktQ3k(short[] mapIndexedTo, C destination, Function2<? super Integer, ? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = mapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), new UShort(mapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexedTo-eNpIKz8, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1391mapIndexedToeNpIKz8(byte[] mapIndexedTo, C destination, Function2<? super Integer, ? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = mapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), new UByte(mapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapIndexedTo-pe2Q0Dw, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1392mapIndexedTope2Q0Dw(long[] mapIndexedTo, C destination, Function2<? super Integer, ? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = mapIndexedTo.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), new ULong(mapIndexedTo[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapTo-HqK1JgA, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1393mapToHqK1JgA(long[] mapTo, C destination, Function1<? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j : mapTo) {
            destination.add(transform.invoke(new ULong(j)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapTo-oEOeDjA, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1394mapTooEOeDjA(short[] mapTo, C destination, Function1<? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s : mapTo) {
            destination.add(transform.invoke(new UShort(s)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapTo-wU5IKMo, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1395mapTowU5IKMo(int[] mapTo, C destination, Function1<? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i : mapTo) {
            destination.add(transform.invoke(new UInt(i)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: mapTo-wzUQCXU, reason: not valid java name */
    public static final <R, C extends Collection<? super R>> C m1396mapTowzUQCXU(byte[] mapTo, C destination, Function1<? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b : mapTo) {
            destination.add(transform.invoke(new UByte(b)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: maxByOrNull-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UByte m1397maxByOrNullJOV_ifY(byte[] maxByOrNull, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxByOrNull.length == 0) {
            return null;
        }
        byte b = maxByOrNull[0];
        int i = 1;
        int length = maxByOrNull.length - 1;
        if (length == 0) {
            return new UByte(b);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector);
        if (1 <= length) {
            while (true) {
                byte b2 = maxByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b2, selector);
                if (comparable.compareTo(comparable2) < 0) {
                    b = b2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: maxByOrNull-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> ULong m1398maxByOrNullMShoTSo(long[] maxByOrNull, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxByOrNull.length == 0) {
            return null;
        }
        long j = maxByOrNull[0];
        int i = 1;
        int length = maxByOrNull.length - 1;
        if (length == 0) {
            return new ULong(j);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector);
        if (1 <= length) {
            while (true) {
                long j2 = maxByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j2, selector);
                if (comparable.compareTo(comparable2) < 0) {
                    j = j2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: maxByOrNull-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UInt m1399maxByOrNulljgv0xPQ(int[] maxByOrNull, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxByOrNull.length == 0) {
            return null;
        }
        int i = maxByOrNull[0];
        int i2 = 1;
        int length = maxByOrNull.length - 1;
        if (length == 0) {
            return new UInt(i);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector);
        if (1 <= length) {
            while (true) {
                int i3 = maxByOrNull[i2];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i3, selector);
                if (comparable.compareTo(comparable2) < 0) {
                    i = i3;
                    comparable = comparable2;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: maxByOrNull-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UShort m1400maxByOrNullxTcfx_M(short[] maxByOrNull, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxByOrNull.length == 0) {
            return null;
        }
        short s = maxByOrNull[0];
        int i = 1;
        int length = maxByOrNull.length - 1;
        if (length == 0) {
            return new UShort(s);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector);
        if (1 <= length) {
            while (true) {
                short s2 = maxByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s2, selector);
                if (comparable.compareTo(comparable2) < 0) {
                    s = s2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "maxByOrThrow-U")
    /* JADX INFO: renamed from: maxByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> byte m1401maxByOrThrowU(byte[] maxBy, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxBy.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = maxBy[0];
        int i = 1;
        int length = maxBy.length - 1;
        if (length != 0) {
            Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector);
            if (1 <= length) {
                while (true) {
                    byte b2 = maxBy[i];
                    Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b2, selector);
                    if (comparable.compareTo(comparable2) < 0) {
                        b = b2;
                        comparable = comparable2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-JOV_ifY, reason: not valid java name */
    public static final double m1405maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[0], selector)).doubleValue();
        int i = 1;
        int length = maxOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-MShoTSo, reason: not valid java name */
    public static final double m1408maxOfMShoTSo(long[] maxOf, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[0], selector)).doubleValue();
        int i = 1;
        int length = maxOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-jgv0xPQ, reason: not valid java name */
    public static final double m1411maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[0], selector)).doubleValue();
        int i = 1;
        int length = maxOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-xTcfx_M, reason: not valid java name */
    public static final double m1414maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[0], selector)).doubleValue();
        int i = 1;
        int length = maxOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1417maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[0], selector);
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[i], selector);
                if (r.compareTo(comparable) < 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1420maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[0], selector);
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[i], selector);
                if (r.compareTo(comparable) < 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1423maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[0], selector);
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[i], selector);
                if (r.compareTo(comparable) < 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1426maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[0], selector);
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[i], selector);
                if (r.compareTo(comparable) < 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWith-5NtCtWE, reason: not valid java name */
    public static final <R> R m1429maxOfWith5NtCtWE(long[] maxOfWith, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfWith[0], selector);
        int i = 1;
        int length = maxOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfWith[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWith-LTi4i_s, reason: not valid java name */
    public static final <R> R m1430maxOfWithLTi4i_s(byte[] maxOfWith, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfWith[0], selector);
        int i = 1;
        int length = maxOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfWith[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWith-l8EHGbQ, reason: not valid java name */
    public static final <R> R m1431maxOfWithl8EHGbQ(short[] maxOfWith, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfWith[0], selector);
        int i = 1;
        int length = maxOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfWith[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWith-myNOsp4, reason: not valid java name */
    public static final <R> R m1432maxOfWithmyNOsp4(int[] maxOfWith, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfWith[0], selector);
        int i = 1;
        int length = maxOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfWith[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWithOrNull-5NtCtWE, reason: not valid java name */
    public static final <R> R m1433maxOfWithOrNull5NtCtWE(long[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfWithOrNull[0], selector);
        int i = 1;
        int length = maxOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWithOrNull-LTi4i_s, reason: not valid java name */
    public static final <R> R m1434maxOfWithOrNullLTi4i_s(byte[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfWithOrNull[0], selector);
        int i = 1;
        int length = maxOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWithOrNull-l8EHGbQ, reason: not valid java name */
    public static final <R> R m1435maxOfWithOrNulll8EHGbQ(short[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfWithOrNull[0], selector);
        int i = 1;
        int length = maxOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfWithOrNull-myNOsp4, reason: not valid java name */
    public static final <R> R m1436maxOfWithOrNullmyNOsp4(int[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfWithOrNull[0], selector);
        int i = 1;
        int length = maxOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) < 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1437maxOrNullajY9A(@NotNull int[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (maxOrNull.length == 0) {
            return null;
        }
        int i = maxOrNull[0];
        int i2 = 1;
        int length = maxOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = maxOrNull[i2];
                if (Integer.compare(i ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) < 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1438maxOrNullGBYM_sE(@NotNull byte[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (maxOrNull.length == 0) {
            return null;
        }
        byte b = maxOrNull[0];
        int i = 1;
        int length = maxOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = maxOrNull[i];
                if (Intrinsics.compare(b & 255, b2 & 255) < 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1439maxOrNullQwZRm1k(@NotNull long[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (maxOrNull.length == 0) {
            return null;
        }
        long j = maxOrNull[0];
        int i = 1;
        int length = maxOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = maxOrNull[i];
                if (Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1440maxOrNullrL5Bavg(@NotNull short[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (maxOrNull.length == 0) {
            return null;
        }
        short s = maxOrNull[0];
        int i = 1;
        int length = maxOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = maxOrNull[i];
                if (Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) < 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* JADX INFO: renamed from: maxOrThrow-U, reason: not valid java name */
    public static final byte m1441maxOrThrowU(@NotNull byte[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (max.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = max[0];
        int i = 1;
        int length = max.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = max[i];
                if (Intrinsics.compare(b & 255, b2 & 255) < 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxWithOrNull-XMRcp5o, reason: not valid java name */
    public static final UByte m1445maxWithOrNullXMRcp5o(@NotNull byte[] maxWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWithOrNull.length == 0) {
            return null;
        }
        byte b = maxWithOrNull[0];
        int i = 1;
        int length = maxWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = maxWithOrNull[i];
                if (comparator.compare(new UByte(b), new UByte(b2)) < 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxWithOrNull-YmdZ_VM, reason: not valid java name */
    public static final UInt m1446maxWithOrNullYmdZ_VM(@NotNull int[] maxWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWithOrNull.length == 0) {
            return null;
        }
        int i = maxWithOrNull[0];
        int i2 = 1;
        int length = maxWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = maxWithOrNull[i2];
                if (comparator.compare(new UInt(i), new UInt(i3)) < 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxWithOrNull-eOHTfZs, reason: not valid java name */
    public static final UShort m1447maxWithOrNulleOHTfZs(@NotNull short[] maxWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWithOrNull.length == 0) {
            return null;
        }
        short s = maxWithOrNull[0];
        int i = 1;
        int length = maxWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = maxWithOrNull[i];
                if (comparator.compare(new UShort(s), new UShort(s2)) < 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: maxWithOrNull-zrEWJaI, reason: not valid java name */
    public static final ULong m1448maxWithOrNullzrEWJaI(@NotNull long[] maxWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWithOrNull.length == 0) {
            return null;
        }
        long j = maxWithOrNull[0];
        int i = 1;
        int length = maxWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = maxWithOrNull[i];
                if (comparator.compare(new ULong(j), new ULong(j2)) < 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* JADX INFO: renamed from: maxWithOrThrow-U, reason: not valid java name */
    public static final byte m1449maxWithOrThrowU(@NotNull byte[] maxWith, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWith.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = maxWith[0];
        int i = 1;
        int length = maxWith.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = maxWith[i];
                if (comparator.compare(new UByte(b), new UByte(b2)) < 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: minByOrNull-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UByte m1453minByOrNullJOV_ifY(byte[] minByOrNull, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minByOrNull.length == 0) {
            return null;
        }
        byte b = minByOrNull[0];
        int i = 1;
        int length = minByOrNull.length - 1;
        if (length == 0) {
            return new UByte(b);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector);
        if (1 <= length) {
            while (true) {
                byte b2 = minByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b2, selector);
                if (comparable.compareTo(comparable2) > 0) {
                    b = b2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: minByOrNull-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> ULong m1454minByOrNullMShoTSo(long[] minByOrNull, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minByOrNull.length == 0) {
            return null;
        }
        long j = minByOrNull[0];
        int i = 1;
        int length = minByOrNull.length - 1;
        if (length == 0) {
            return new ULong(j);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector);
        if (1 <= length) {
            while (true) {
                long j2 = minByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j2, selector);
                if (comparable.compareTo(comparable2) > 0) {
                    j = j2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: minByOrNull-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UInt m1455minByOrNulljgv0xPQ(int[] minByOrNull, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minByOrNull.length == 0) {
            return null;
        }
        int i = minByOrNull[0];
        int i2 = 1;
        int length = minByOrNull.length - 1;
        if (length == 0) {
            return new UInt(i);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector);
        if (1 <= length) {
            while (true) {
                int i3 = minByOrNull[i2];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i3, selector);
                if (comparable.compareTo(comparable2) > 0) {
                    i = i3;
                    comparable = comparable2;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: minByOrNull-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> UShort m1456minByOrNullxTcfx_M(short[] minByOrNull, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minByOrNull.length == 0) {
            return null;
        }
        short s = minByOrNull[0];
        int i = 1;
        int length = minByOrNull.length - 1;
        if (length == 0) {
            return new UShort(s);
        }
        Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector);
        if (1 <= length) {
            while (true) {
                short s2 = minByOrNull[i];
                Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s2, selector);
                if (comparable.compareTo(comparable2) > 0) {
                    s = s2;
                    comparable = comparable2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "minByOrThrow-U")
    /* JADX INFO: renamed from: minByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> byte m1457minByOrThrowU(byte[] minBy, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minBy.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = minBy[0];
        int i = 1;
        int length = minBy.length - 1;
        if (length != 0) {
            Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector);
            if (1 <= length) {
                while (true) {
                    byte b2 = minBy[i];
                    Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b2, selector);
                    if (comparable.compareTo(comparable2) > 0) {
                        b = b2;
                        comparable = comparable2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-JOV_ifY, reason: not valid java name */
    public static final double m1461minOfJOV_ifY(byte[] minOf, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[0], selector)).doubleValue();
        int i = 1;
        int length = minOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-MShoTSo, reason: not valid java name */
    public static final double m1464minOfMShoTSo(long[] minOf, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[0], selector)).doubleValue();
        int i = 1;
        int length = minOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-jgv0xPQ, reason: not valid java name */
    public static final double m1467minOfjgv0xPQ(int[] minOf, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[0], selector)).doubleValue();
        int i = 1;
        int length = minOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-xTcfx_M, reason: not valid java name */
    public static final double m1470minOfxTcfx_M(short[] minOf, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[0], selector)).doubleValue();
        int i = 1;
        int length = minOf.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1473minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[0], selector);
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[i], selector);
                if (r.compareTo(comparable) > 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1476minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[0], selector);
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[i], selector);
                if (r.compareTo(comparable) > 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1479minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[0], selector);
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[i], selector);
                if (r.compareTo(comparable) > 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1482minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[0], selector);
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[i], selector);
                if (r.compareTo(comparable) > 0) {
                    r = (R) comparable;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWith-5NtCtWE, reason: not valid java name */
    public static final <R> R m1485minOfWith5NtCtWE(long[] minOfWith, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfWith[0], selector);
        int i = 1;
        int length = minOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfWith[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWith-LTi4i_s, reason: not valid java name */
    public static final <R> R m1486minOfWithLTi4i_s(byte[] minOfWith, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfWith[0], selector);
        int i = 1;
        int length = minOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfWith[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWith-l8EHGbQ, reason: not valid java name */
    public static final <R> R m1487minOfWithl8EHGbQ(short[] minOfWith, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfWith[0], selector);
        int i = 1;
        int length = minOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfWith[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWith-myNOsp4, reason: not valid java name */
    public static final <R> R m1488minOfWithmyNOsp4(int[] minOfWith, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWith.length == 0) {
            throw new NoSuchElementException();
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfWith[0], selector);
        int i = 1;
        int length = minOfWith.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfWith[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWithOrNull-5NtCtWE, reason: not valid java name */
    public static final <R> R m1489minOfWithOrNull5NtCtWE(long[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfWithOrNull[0], selector);
        int i = 1;
        int length = minOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWithOrNull-LTi4i_s, reason: not valid java name */
    public static final <R> R m1490minOfWithOrNullLTi4i_s(byte[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfWithOrNull[0], selector);
        int i = 1;
        int length = minOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWithOrNull-l8EHGbQ, reason: not valid java name */
    public static final <R> R m1491minOfWithOrNulll8EHGbQ(short[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfWithOrNull[0], selector);
        int i = 1;
        int length = minOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfWithOrNull-myNOsp4, reason: not valid java name */
    public static final <R> R m1492minOfWithOrNullmyNOsp4(int[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfWithOrNull.length == 0) {
            return null;
        }
        R r = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfWithOrNull[0], selector);
        int i = 1;
        int length = minOfWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                Object obj = (Object) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfWithOrNull[i], selector);
                if (comparator.compare(r, obj) > 0) {
                    r = (R) obj;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return r;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1493minOrNullajY9A(@NotNull int[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (minOrNull.length == 0) {
            return null;
        }
        int i = minOrNull[0];
        int i2 = 1;
        int length = minOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = minOrNull[i2];
                if (Integer.compare(i ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) > 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1494minOrNullGBYM_sE(@NotNull byte[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (minOrNull.length == 0) {
            return null;
        }
        byte b = minOrNull[0];
        int i = 1;
        int length = minOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = minOrNull[i];
                if (Intrinsics.compare(b & 255, b2 & 255) > 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1495minOrNullQwZRm1k(@NotNull long[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (minOrNull.length == 0) {
            return null;
        }
        long j = minOrNull[0];
        int i = 1;
        int length = minOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = minOrNull[i];
                if (Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) > 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1496minOrNullrL5Bavg(@NotNull short[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (minOrNull.length == 0) {
            return null;
        }
        short s = minOrNull[0];
        int i = 1;
        int length = minOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = minOrNull[i];
                if (Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) > 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* JADX INFO: renamed from: minOrThrow-U, reason: not valid java name */
    public static final byte m1497minOrThrowU(@NotNull byte[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (min.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = min[0];
        int i = 1;
        int length = min.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = min[i];
                if (Intrinsics.compare(b & 255, b2 & 255) > 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minWithOrNull-XMRcp5o, reason: not valid java name */
    public static final UByte m1501minWithOrNullXMRcp5o(@NotNull byte[] minWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWithOrNull.length == 0) {
            return null;
        }
        byte b = minWithOrNull[0];
        int i = 1;
        int length = minWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = minWithOrNull[i];
                if (comparator.compare(new UByte(b), new UByte(b2)) > 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minWithOrNull-YmdZ_VM, reason: not valid java name */
    public static final UInt m1502minWithOrNullYmdZ_VM(@NotNull int[] minWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWithOrNull.length == 0) {
            return null;
        }
        int i = minWithOrNull[0];
        int i2 = 1;
        int length = minWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = minWithOrNull[i2];
                if (comparator.compare(new UInt(i), new UInt(i3)) > 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minWithOrNull-eOHTfZs, reason: not valid java name */
    public static final UShort m1503minWithOrNulleOHTfZs(@NotNull short[] minWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWithOrNull.length == 0) {
            return null;
        }
        short s = minWithOrNull[0];
        int i = 1;
        int length = minWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = minWithOrNull[i];
                if (comparator.compare(new UShort(s), new UShort(s2)) > 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: minWithOrNull-zrEWJaI, reason: not valid java name */
    public static final ULong m1504minWithOrNullzrEWJaI(@NotNull long[] minWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWithOrNull.length == 0) {
            return null;
        }
        long j = minWithOrNull[0];
        int i = 1;
        int length = minWithOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = minWithOrNull[i];
                if (comparator.compare(new ULong(j), new ULong(j2)) > 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* JADX INFO: renamed from: minWithOrThrow-U, reason: not valid java name */
    public static final byte m1505minWithOrThrowU(@NotNull byte[] minWith, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWith.length == 0) {
            throw new NoSuchElementException();
        }
        byte b = minWith[0];
        int i = 1;
        int length = minWith.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = minWith[i];
                if (comparator.compare(new UByte(b), new UByte(b2)) > 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none--ajY-9A, reason: not valid java name */
    public static final boolean m1509noneajY9A(int[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return none.length == 0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-GBYM_sE, reason: not valid java name */
    public static final boolean m1510noneGBYM_sE(byte[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return none.length == 0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-JOV_ifY, reason: not valid java name */
    public static final boolean m1511noneJOV_ifY(byte[] none, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : none) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-MShoTSo, reason: not valid java name */
    public static final boolean m1512noneMShoTSo(long[] none, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : none) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-QwZRm1k, reason: not valid java name */
    public static final boolean m1513noneQwZRm1k(long[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return none.length == 0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-jgv0xPQ, reason: not valid java name */
    public static final boolean m1514nonejgv0xPQ(int[] none, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : none) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-rL5Bavg, reason: not valid java name */
    public static final boolean m1515nonerL5Bavg(short[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return none.length == 0;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: none-xTcfx_M, reason: not valid java name */
    public static final boolean m1516nonexTcfx_M(short[] none, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : none) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEach-JOV_ifY, reason: not valid java name */
    public static final byte[] m1517onEachJOV_ifY(byte[] onEach, Function1<? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b : onEach) {
            action.invoke(new UByte(b));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEach-MShoTSo, reason: not valid java name */
    public static final long[] m1518onEachMShoTSo(long[] onEach, Function1<? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j : onEach) {
            action.invoke(new ULong(j));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEach-jgv0xPQ, reason: not valid java name */
    public static final int[] m1519onEachjgv0xPQ(int[] onEach, Function1<? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i : onEach) {
            action.invoke(new UInt(i));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEach-xTcfx_M, reason: not valid java name */
    public static final short[] m1520onEachxTcfx_M(short[] onEach, Function1<? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s : onEach) {
            action.invoke(new UShort(s));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEachIndexed-ELGow60, reason: not valid java name */
    public static final byte[] m1521onEachIndexedELGow60(byte[] onEachIndexed, Function2<? super Integer, ? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = onEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UByte(onEachIndexed[i]));
            i++;
            i2++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEachIndexed-WyvcNBI, reason: not valid java name */
    public static final int[] m1522onEachIndexedWyvcNBI(int[] onEachIndexed, Function2<? super Integer, ? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = onEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UInt(onEachIndexed[i]));
            i++;
            i2++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEachIndexed-s8dVfGU, reason: not valid java name */
    public static final long[] m1523onEachIndexeds8dVfGU(long[] onEachIndexed, Function2<? super Integer, ? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = onEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new ULong(onEachIndexed[i]));
            i++;
            i2++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: onEachIndexed-xzaTVY8, reason: not valid java name */
    public static final short[] m1524onEachIndexedxzaTVY8(short[] onEachIndexed, Function2<? super Integer, ? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = onEachIndexed.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), new UShort(onEachIndexed[i]));
            i++;
            i2++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-3uqUaXg, reason: not valid java name */
    public static final long[] m1525plus3uqUaXg(long[] plus, long j) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return ArraysKt___ArraysJvmKt.plus(plus, j);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: plus-CFIt9YE, reason: not valid java name */
    public static final int[] m1526plusCFIt9YE(@NotNull int[] plus, @NotNull Collection<UInt> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        int[] iArrCopyOf = Arrays.copyOf(plus, elements.size() + plus.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        Iterator<UInt> it = elements.iterator();
        while (it.hasNext()) {
            iArrCopyOf[length] = it.next().data;
            length++;
        }
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-XzdR7RA, reason: not valid java name */
    public static final short[] m1527plusXzdR7RA(short[] plus, short s) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return ArraysKt___ArraysJvmKt.plus(plus, s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-ctEhBpI, reason: not valid java name */
    public static final int[] m1528plusctEhBpI(int[] plus, int[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt___ArraysJvmKt.plus(plus, elements);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-gMuBH34, reason: not valid java name */
    public static final byte[] m1529plusgMuBH34(byte[] plus, byte b) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return ArraysKt___ArraysJvmKt.plus(plus, b);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-kdPth3s, reason: not valid java name */
    public static final byte[] m1530pluskdPth3s(byte[] plus, byte[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt___ArraysJvmKt.plus(plus, elements);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: plus-kzHmqpY, reason: not valid java name */
    public static final long[] m1531pluskzHmqpY(@NotNull long[] plus, @NotNull Collection<ULong> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        long[] jArrCopyOf = Arrays.copyOf(plus, elements.size() + plus.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        Iterator<ULong> it = elements.iterator();
        while (it.hasNext()) {
            jArrCopyOf[length] = it.next().data;
            length++;
        }
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-mazbYpA, reason: not valid java name */
    public static final short[] m1532plusmazbYpA(short[] plus, short[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt___ArraysJvmKt.plus(plus, elements);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: plus-ojwP5H8, reason: not valid java name */
    public static final short[] m1533plusojwP5H8(@NotNull short[] plus, @NotNull Collection<UShort> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        short[] sArrCopyOf = Arrays.copyOf(plus, elements.size() + plus.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        Iterator<UShort> it = elements.iterator();
        while (it.hasNext()) {
            sArrCopyOf[length] = it.next().data;
            length++;
        }
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-uWY9BYg, reason: not valid java name */
    public static final int[] m1534plusuWY9BYg(int[] plus, int i) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return ArraysKt___ArraysJvmKt.plus(plus, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: plus-us8wMrg, reason: not valid java name */
    public static final long[] m1535plusus8wMrg(long[] plus, long[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt___ArraysJvmKt.plus(plus, elements);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: plus-xo_DsdI, reason: not valid java name */
    public static final byte[] m1536plusxo_DsdI(@NotNull byte[] plus, @NotNull Collection<UByte> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        byte[] bArrCopyOf = Arrays.copyOf(plus, elements.size() + plus.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        Iterator<UByte> it = elements.iterator();
        while (it.hasNext()) {
            bArrCopyOf[length] = it.next().data;
            length++;
        }
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: random--ajY-9A, reason: not valid java name */
    public static final int m1537randomajY9A(int[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1538random2D5oskM(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: random-2D5oskM, reason: not valid java name */
    public static final int m1538random2D5oskM(@NotNull int[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (random.length != 0) {
            return random[random2.nextInt(random.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: random-GBYM_sE, reason: not valid java name */
    public static final byte m1539randomGBYM_sE(byte[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1542randomoSF2wD8(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: random-JzugnMA, reason: not valid java name */
    public static final long m1540randomJzugnMA(@NotNull long[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (random.length != 0) {
            return random[random2.nextInt(random.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: random-QwZRm1k, reason: not valid java name */
    public static final long m1541randomQwZRm1k(long[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1540randomJzugnMA(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: random-oSF2wD8, reason: not valid java name */
    public static final byte m1542randomoSF2wD8(@NotNull byte[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (random.length != 0) {
            return random[random2.nextInt(random.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: random-rL5Bavg, reason: not valid java name */
    public static final short m1543randomrL5Bavg(short[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1544randoms5X_as8(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: random-s5X_as8, reason: not valid java name */
    public static final short m1544randoms5X_as8(@NotNull short[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (random.length != 0) {
            return random[random2.nextInt(random.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: randomOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1545randomOrNullajY9A(int[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1546randomOrNull2D5oskM(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: randomOrNull-2D5oskM, reason: not valid java name */
    public static final UInt m1546randomOrNull2D5oskM(@NotNull int[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (randomOrNull.length == 0) {
            return null;
        }
        return new UInt(randomOrNull[random.nextInt(randomOrNull.length)]);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: randomOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1547randomOrNullGBYM_sE(byte[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1550randomOrNulloSF2wD8(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: randomOrNull-JzugnMA, reason: not valid java name */
    public static final ULong m1548randomOrNullJzugnMA(@NotNull long[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (randomOrNull.length == 0) {
            return null;
        }
        return new ULong(randomOrNull[random.nextInt(randomOrNull.length)]);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: randomOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1549randomOrNullQwZRm1k(long[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1548randomOrNullJzugnMA(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: randomOrNull-oSF2wD8, reason: not valid java name */
    public static final UByte m1550randomOrNulloSF2wD8(@NotNull byte[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (randomOrNull.length == 0) {
            return null;
        }
        return new UByte(randomOrNull[random.nextInt(randomOrNull.length)]);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: randomOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1551randomOrNullrL5Bavg(short[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1552randomOrNulls5X_as8(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: randomOrNull-s5X_as8, reason: not valid java name */
    public static final UShort m1552randomOrNulls5X_as8(@NotNull short[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (randomOrNull.length == 0) {
            return null;
        }
        return new UShort(randomOrNull[random.nextInt(randomOrNull.length)]);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduce-ELGow60, reason: not valid java name */
    public static final byte m1553reduceELGow60(byte[] reduce, Function2<? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduce.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b = reduce[0];
        int i = 1;
        int length = reduce.length - 1;
        if (1 <= length) {
            while (true) {
                b = operation.invoke(new UByte(b), new UByte(reduce[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduce-WyvcNBI, reason: not valid java name */
    public static final int m1554reduceWyvcNBI(int[] reduce, Function2<? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduce.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i = reduce[0];
        int i2 = 1;
        int length = reduce.length - 1;
        if (1 <= length) {
            while (true) {
                i = operation.invoke(new UInt(i), new UInt(reduce[i2])).data;
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduce-s8dVfGU, reason: not valid java name */
    public static final long m1555reduces8dVfGU(long[] reduce, Function2<? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduce.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j = reduce[0];
        int i = 1;
        int length = reduce.length - 1;
        if (1 <= length) {
            while (true) {
                j = operation.invoke(new ULong(j), new ULong(reduce[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return j;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduce-xzaTVY8, reason: not valid java name */
    public static final short m1556reducexzaTVY8(short[] reduce, Function2<? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduce.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s = reduce[0];
        int i = 1;
        int length = reduce.length - 1;
        if (1 <= length) {
            while (true) {
                s = operation.invoke(new UShort(s), new UShort(reduce[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexed-D40WMg8, reason: not valid java name */
    public static final int m1557reduceIndexedD40WMg8(int[] reduceIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexed.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i = reduceIndexed[0];
        int i2 = 1;
        int length = reduceIndexed.length - 1;
        if (1 <= length) {
            while (true) {
                i = operation.invoke(Integer.valueOf(i2), new UInt(i), new UInt(reduceIndexed[i2])).data;
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexed-EOyYB1Y, reason: not valid java name */
    public static final byte m1558reduceIndexedEOyYB1Y(byte[] reduceIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexed.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b = reduceIndexed[0];
        int i = 1;
        int length = reduceIndexed.length - 1;
        if (1 <= length) {
            while (true) {
                b = operation.invoke(Integer.valueOf(i), new UByte(b), new UByte(reduceIndexed[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexed-aLgx1Fo, reason: not valid java name */
    public static final short m1559reduceIndexedaLgx1Fo(short[] reduceIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexed.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s = reduceIndexed[0];
        int i = 1;
        int length = reduceIndexed.length - 1;
        if (1 <= length) {
            while (true) {
                s = operation.invoke(Integer.valueOf(i), new UShort(s), new UShort(reduceIndexed[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexed-z1zDJgo, reason: not valid java name */
    public static final long m1560reduceIndexedz1zDJgo(long[] reduceIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexed.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j = reduceIndexed[0];
        int i = 1;
        int length = reduceIndexed.length - 1;
        if (1 <= length) {
            while (true) {
                j = operation.invoke(Integer.valueOf(i), new ULong(j), new ULong(reduceIndexed[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return j;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexedOrNull-D40WMg8, reason: not valid java name */
    public static final UInt m1561reduceIndexedOrNullD40WMg8(int[] reduceIndexedOrNull, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexedOrNull.length == 0) {
            return null;
        }
        int i = reduceIndexedOrNull[0];
        int i2 = 1;
        int length = reduceIndexedOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                i = operation.invoke(Integer.valueOf(i2), new UInt(i), new UInt(reduceIndexedOrNull[i2])).data;
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexedOrNull-EOyYB1Y, reason: not valid java name */
    public static final UByte m1562reduceIndexedOrNullEOyYB1Y(byte[] reduceIndexedOrNull, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexedOrNull.length == 0) {
            return null;
        }
        byte b = reduceIndexedOrNull[0];
        int i = 1;
        int length = reduceIndexedOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                b = operation.invoke(Integer.valueOf(i), new UByte(b), new UByte(reduceIndexedOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexedOrNull-aLgx1Fo, reason: not valid java name */
    public static final UShort m1563reduceIndexedOrNullaLgx1Fo(short[] reduceIndexedOrNull, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexedOrNull.length == 0) {
            return null;
        }
        short s = reduceIndexedOrNull[0];
        int i = 1;
        int length = reduceIndexedOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                s = operation.invoke(Integer.valueOf(i), new UShort(s), new UShort(reduceIndexedOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceIndexedOrNull-z1zDJgo, reason: not valid java name */
    public static final ULong m1564reduceIndexedOrNullz1zDJgo(long[] reduceIndexedOrNull, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceIndexedOrNull.length == 0) {
            return null;
        }
        long j = reduceIndexedOrNull[0];
        int i = 1;
        int length = reduceIndexedOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                j = operation.invoke(Integer.valueOf(i), new ULong(j), new ULong(reduceIndexedOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceOrNull-ELGow60, reason: not valid java name */
    public static final UByte m1565reduceOrNullELGow60(byte[] reduceOrNull, Function2<? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceOrNull.length == 0) {
            return null;
        }
        byte b = reduceOrNull[0];
        int i = 1;
        int length = reduceOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                b = operation.invoke(new UByte(b), new UByte(reduceOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceOrNull-WyvcNBI, reason: not valid java name */
    public static final UInt m1566reduceOrNullWyvcNBI(int[] reduceOrNull, Function2<? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceOrNull.length == 0) {
            return null;
        }
        int i = reduceOrNull[0];
        int i2 = 1;
        int length = reduceOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                i = operation.invoke(new UInt(i), new UInt(reduceOrNull[i2])).data;
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return new UInt(i);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceOrNull-s8dVfGU, reason: not valid java name */
    public static final ULong m1567reduceOrNulls8dVfGU(long[] reduceOrNull, Function2<? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceOrNull.length == 0) {
            return null;
        }
        long j = reduceOrNull[0];
        int i = 1;
        int length = reduceOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                j = operation.invoke(new ULong(j), new ULong(reduceOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceOrNull-xzaTVY8, reason: not valid java name */
    public static final UShort m1568reduceOrNullxzaTVY8(short[] reduceOrNull, Function2<? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (reduceOrNull.length == 0) {
            return null;
        }
        short s = reduceOrNull[0];
        int i = 1;
        int length = reduceOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                s = operation.invoke(new UShort(s), new UShort(reduceOrNull[i])).data;
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRight-ELGow60, reason: not valid java name */
    public static final byte m1569reduceRightELGow60(byte[] reduceRight, Function2<? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRight.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b = reduceRight[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            b = operation.invoke(new UByte(reduceRight[i2]), new UByte(b)).data;
        }
        return b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRight-WyvcNBI, reason: not valid java name */
    public static final int m1570reduceRightWyvcNBI(int[] reduceRight, Function2<? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRight.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i2 = reduceRight[i];
        for (int i3 = length - 2; i3 >= 0; i3--) {
            i2 = operation.invoke(new UInt(reduceRight[i3]), new UInt(i2)).data;
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRight-s8dVfGU, reason: not valid java name */
    public static final long m1571reduceRights8dVfGU(long[] reduceRight, Function2<? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRight.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j = reduceRight[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            j = operation.invoke(new ULong(reduceRight[i2]), new ULong(j)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRight-xzaTVY8, reason: not valid java name */
    public static final short m1572reduceRightxzaTVY8(short[] reduceRight, Function2<? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRight.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s = reduceRight[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            s = operation.invoke(new UShort(reduceRight[i2]), new UShort(s)).data;
        }
        return s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexed-D40WMg8, reason: not valid java name */
    public static final int m1573reduceRightIndexedD40WMg8(int[] reduceRightIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexed.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i2 = reduceRightIndexed[i];
        for (int i3 = length - 2; i3 >= 0; i3--) {
            i2 = operation.invoke(Integer.valueOf(i3), new UInt(reduceRightIndexed[i3]), new UInt(i2)).data;
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexed-EOyYB1Y, reason: not valid java name */
    public static final byte m1574reduceRightIndexedEOyYB1Y(byte[] reduceRightIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexed.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b = reduceRightIndexed[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            b = operation.invoke(Integer.valueOf(i2), new UByte(reduceRightIndexed[i2]), new UByte(b)).data;
        }
        return b;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexed-aLgx1Fo, reason: not valid java name */
    public static final short m1575reduceRightIndexedaLgx1Fo(short[] reduceRightIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexed.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s = reduceRightIndexed[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            s = operation.invoke(Integer.valueOf(i2), new UShort(reduceRightIndexed[i2]), new UShort(s)).data;
        }
        return s;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexed-z1zDJgo, reason: not valid java name */
    public static final long m1576reduceRightIndexedz1zDJgo(long[] reduceRightIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexed.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j = reduceRightIndexed[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            j = operation.invoke(Integer.valueOf(i2), new ULong(reduceRightIndexed[i2]), new ULong(j)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexedOrNull-D40WMg8, reason: not valid java name */
    public static final UInt m1577reduceRightIndexedOrNullD40WMg8(int[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexedOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        int i2 = reduceRightIndexedOrNull[i];
        for (int i3 = length - 2; i3 >= 0; i3--) {
            i2 = operation.invoke(Integer.valueOf(i3), new UInt(reduceRightIndexedOrNull[i3]), new UInt(i2)).data;
        }
        return new UInt(i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexedOrNull-EOyYB1Y, reason: not valid java name */
    public static final UByte m1578reduceRightIndexedOrNullEOyYB1Y(byte[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexedOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        byte b = reduceRightIndexedOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            b = operation.invoke(Integer.valueOf(i2), new UByte(reduceRightIndexedOrNull[i2]), new UByte(b)).data;
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexedOrNull-aLgx1Fo, reason: not valid java name */
    public static final UShort m1579reduceRightIndexedOrNullaLgx1Fo(short[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexedOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        short s = reduceRightIndexedOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            s = operation.invoke(Integer.valueOf(i2), new UShort(reduceRightIndexedOrNull[i2]), new UShort(s)).data;
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightIndexedOrNull-z1zDJgo, reason: not valid java name */
    public static final ULong m1580reduceRightIndexedOrNullz1zDJgo(long[] reduceRightIndexedOrNull, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightIndexedOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        long j = reduceRightIndexedOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            j = operation.invoke(Integer.valueOf(i2), new ULong(reduceRightIndexedOrNull[i2]), new ULong(j)).data;
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightOrNull-ELGow60, reason: not valid java name */
    public static final UByte m1581reduceRightOrNullELGow60(byte[] reduceRightOrNull, Function2<? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        byte b = reduceRightOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            b = operation.invoke(new UByte(reduceRightOrNull[i2]), new UByte(b)).data;
        }
        return new UByte(b);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightOrNull-WyvcNBI, reason: not valid java name */
    public static final UInt m1582reduceRightOrNullWyvcNBI(int[] reduceRightOrNull, Function2<? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        int i2 = reduceRightOrNull[i];
        for (int i3 = length - 2; i3 >= 0; i3--) {
            i2 = operation.invoke(new UInt(reduceRightOrNull[i3]), new UInt(i2)).data;
        }
        return new UInt(i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightOrNull-s8dVfGU, reason: not valid java name */
    public static final ULong m1583reduceRightOrNulls8dVfGU(long[] reduceRightOrNull, Function2<? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        long j = reduceRightOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            j = operation.invoke(new ULong(reduceRightOrNull[i2]), new ULong(j)).data;
        }
        return new ULong(j);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reduceRightOrNull-xzaTVY8, reason: not valid java name */
    public static final UShort m1584reduceRightOrNullxzaTVY8(short[] reduceRightOrNull, Function2<? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = reduceRightOrNull.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        short s = reduceRightOrNull[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            s = operation.invoke(new UShort(reduceRightOrNull[i2]), new UShort(s)).data;
        }
        return new UShort(s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse--ajY-9A, reason: not valid java name */
    public static final void m1585reverseajY9A(int[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse--nroSd4, reason: not valid java name */
    public static final void m1586reversenroSd4(long[] reverse, int i, int i2) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-4UcCI2c, reason: not valid java name */
    public static final void m1587reverse4UcCI2c(byte[] reverse, int i, int i2) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-Aa5vz7o, reason: not valid java name */
    public static final void m1588reverseAa5vz7o(short[] reverse, int i, int i2) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-GBYM_sE, reason: not valid java name */
    public static final void m1589reverseGBYM_sE(byte[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-QwZRm1k, reason: not valid java name */
    public static final void m1590reverseQwZRm1k(long[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-oBK06Vg, reason: not valid java name */
    public static final void m1591reverseoBK06Vg(int[] reverse, int i, int i2) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reverse-rL5Bavg, reason: not valid java name */
    public static final void m1592reverserL5Bavg(short[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: reversed--ajY-9A, reason: not valid java name */
    public static final List<UInt> m1593reversedajY9A(@NotNull int[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (reversed.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<UInt> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new UIntArray(reversed));
        Collections.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: reversed-GBYM_sE, reason: not valid java name */
    public static final List<UByte> m1594reversedGBYM_sE(@NotNull byte[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (reversed.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<UByte> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new UByteArray(reversed));
        Collections.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: reversed-QwZRm1k, reason: not valid java name */
    public static final List<ULong> m1595reversedQwZRm1k(@NotNull long[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (reversed.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<ULong> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new ULongArray(reversed));
        Collections.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: reversed-rL5Bavg, reason: not valid java name */
    public static final List<UShort> m1596reversedrL5Bavg(@NotNull short[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (reversed.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<UShort> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new UShortArray(reversed));
        Collections.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reversedArray--ajY-9A, reason: not valid java name */
    public static final int[] m1597reversedArrayajY9A(int[] reversedArray) {
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        return ArraysKt___ArraysKt.reversedArray(reversedArray);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reversedArray-GBYM_sE, reason: not valid java name */
    public static final byte[] m1598reversedArrayGBYM_sE(byte[] reversedArray) {
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        return ArraysKt___ArraysKt.reversedArray(reversedArray);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reversedArray-QwZRm1k, reason: not valid java name */
    public static final long[] m1599reversedArrayQwZRm1k(long[] reversedArray) {
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        return ArraysKt___ArraysKt.reversedArray(reversedArray);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: reversedArray-rL5Bavg, reason: not valid java name */
    public static final short[] m1600reversedArrayrL5Bavg(short[] reversedArray) {
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        return ArraysKt___ArraysKt.reversedArray(reversedArray);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFold-A8wKCXQ, reason: not valid java name */
    public static final <R> List<R> m1601runningFoldA8wKCXQ(long[] runningFold, R r, Function2<? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFold.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFold.length + 1);
        arrayList.add(r);
        for (long j : runningFold) {
            r = operation.invoke(r, new ULong(j));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFold-yXmHNn8, reason: not valid java name */
    public static final <R> List<R> m1602runningFoldyXmHNn8(byte[] runningFold, R r, Function2<? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFold.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFold.length + 1);
        arrayList.add(r);
        for (byte b : runningFold) {
            r = operation.invoke(r, new UByte(b));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFold-zi1B2BA, reason: not valid java name */
    public static final <R> List<R> m1603runningFoldzi1B2BA(int[] runningFold, R r, Function2<? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFold.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFold.length + 1);
        arrayList.add(r);
        for (int i : runningFold) {
            r = operation.invoke(r, new UInt(i));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFold-zww5nb8, reason: not valid java name */
    public static final <R> List<R> m1604runningFoldzww5nb8(short[] runningFold, R r, Function2<? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFold.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFold.length + 1);
        arrayList.add(r);
        for (short s : runningFold) {
            r = operation.invoke(r, new UShort(s));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFoldIndexed-3iWJZGE, reason: not valid java name */
    public static final <R> List<R> m1605runningFoldIndexed3iWJZGE(byte[] runningFoldIndexed, R r, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFoldIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFoldIndexed.length + 1);
        arrayList.add(r);
        int length = runningFoldIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UByte(runningFoldIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFoldIndexed-bzxtMww, reason: not valid java name */
    public static final <R> List<R> m1606runningFoldIndexedbzxtMww(short[] runningFoldIndexed, R r, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFoldIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFoldIndexed.length + 1);
        arrayList.add(r);
        int length = runningFoldIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UShort(runningFoldIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFoldIndexed-mwnnOCs, reason: not valid java name */
    public static final <R> List<R> m1607runningFoldIndexedmwnnOCs(long[] runningFoldIndexed, R r, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFoldIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFoldIndexed.length + 1);
        arrayList.add(r);
        int length = runningFoldIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new ULong(runningFoldIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningFoldIndexed-yVwIW0Q, reason: not valid java name */
    public static final <R> List<R> m1608runningFoldIndexedyVwIW0Q(int[] runningFoldIndexed, R r, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningFoldIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(runningFoldIndexed.length + 1);
        arrayList.add(r);
        int length = runningFoldIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UInt(runningFoldIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduce-ELGow60, reason: not valid java name */
    public static final List<UByte> m1609runningReduceELGow60(byte[] runningReduce, Function2<? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduce.length == 0) {
            return EmptyList.INSTANCE;
        }
        byte b = runningReduce[0];
        ArrayList arrayList = new ArrayList(runningReduce.length);
        arrayList.add(new UByte(b));
        int length = runningReduce.length;
        for (int i = 1; i < length; i++) {
            b = operation.invoke(new UByte(b), new UByte(runningReduce[i])).data;
            arrayList.add(new UByte(b));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduce-WyvcNBI, reason: not valid java name */
    public static final List<UInt> m1610runningReduceWyvcNBI(int[] runningReduce, Function2<? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduce.length == 0) {
            return EmptyList.INSTANCE;
        }
        int i = runningReduce[0];
        ArrayList arrayList = new ArrayList(runningReduce.length);
        arrayList.add(new UInt(i));
        int length = runningReduce.length;
        for (int i2 = 1; i2 < length; i2++) {
            i = operation.invoke(new UInt(i), new UInt(runningReduce[i2])).data;
            arrayList.add(new UInt(i));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduce-s8dVfGU, reason: not valid java name */
    public static final List<ULong> m1611runningReduces8dVfGU(long[] runningReduce, Function2<? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduce.length == 0) {
            return EmptyList.INSTANCE;
        }
        long j = runningReduce[0];
        ArrayList arrayList = new ArrayList(runningReduce.length);
        arrayList.add(new ULong(j));
        int length = runningReduce.length;
        for (int i = 1; i < length; i++) {
            j = operation.invoke(new ULong(j), new ULong(runningReduce[i])).data;
            arrayList.add(new ULong(j));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduce-xzaTVY8, reason: not valid java name */
    public static final List<UShort> m1612runningReducexzaTVY8(short[] runningReduce, Function2<? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduce.length == 0) {
            return EmptyList.INSTANCE;
        }
        short s = runningReduce[0];
        ArrayList arrayList = new ArrayList(runningReduce.length);
        arrayList.add(new UShort(s));
        int length = runningReduce.length;
        for (int i = 1; i < length; i++) {
            s = operation.invoke(new UShort(s), new UShort(runningReduce[i])).data;
            arrayList.add(new UShort(s));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduceIndexed-D40WMg8, reason: not valid java name */
    public static final List<UInt> m1613runningReduceIndexedD40WMg8(int[] runningReduceIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduceIndexed.length == 0) {
            return EmptyList.INSTANCE;
        }
        int i = runningReduceIndexed[0];
        ArrayList arrayList = new ArrayList(runningReduceIndexed.length);
        arrayList.add(new UInt(i));
        int length = runningReduceIndexed.length;
        for (int i2 = 1; i2 < length; i2++) {
            i = operation.invoke(Integer.valueOf(i2), new UInt(i), new UInt(runningReduceIndexed[i2])).data;
            arrayList.add(new UInt(i));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduceIndexed-EOyYB1Y, reason: not valid java name */
    public static final List<UByte> m1614runningReduceIndexedEOyYB1Y(byte[] runningReduceIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduceIndexed.length == 0) {
            return EmptyList.INSTANCE;
        }
        byte b = runningReduceIndexed[0];
        ArrayList arrayList = new ArrayList(runningReduceIndexed.length);
        arrayList.add(new UByte(b));
        int length = runningReduceIndexed.length;
        for (int i = 1; i < length; i++) {
            b = operation.invoke(Integer.valueOf(i), new UByte(b), new UByte(runningReduceIndexed[i])).data;
            arrayList.add(new UByte(b));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduceIndexed-aLgx1Fo, reason: not valid java name */
    public static final List<UShort> m1615runningReduceIndexedaLgx1Fo(short[] runningReduceIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduceIndexed.length == 0) {
            return EmptyList.INSTANCE;
        }
        short s = runningReduceIndexed[0];
        ArrayList arrayList = new ArrayList(runningReduceIndexed.length);
        arrayList.add(new UShort(s));
        int length = runningReduceIndexed.length;
        for (int i = 1; i < length; i++) {
            s = operation.invoke(Integer.valueOf(i), new UShort(s), new UShort(runningReduceIndexed[i])).data;
            arrayList.add(new UShort(s));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: runningReduceIndexed-z1zDJgo, reason: not valid java name */
    public static final List<ULong> m1616runningReduceIndexedz1zDJgo(long[] runningReduceIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (runningReduceIndexed.length == 0) {
            return EmptyList.INSTANCE;
        }
        long j = runningReduceIndexed[0];
        ArrayList arrayList = new ArrayList(runningReduceIndexed.length);
        arrayList.add(new ULong(j));
        int length = runningReduceIndexed.length;
        for (int i = 1; i < length; i++) {
            j = operation.invoke(Integer.valueOf(i), new ULong(j), new ULong(runningReduceIndexed[i])).data;
            arrayList.add(new ULong(j));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scan-A8wKCXQ, reason: not valid java name */
    public static final <R> List<R> m1617scanA8wKCXQ(long[] scan, R r, Function2<? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scan.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scan.length + 1);
        arrayList.add(r);
        for (long j : scan) {
            r = operation.invoke(r, new ULong(j));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scan-yXmHNn8, reason: not valid java name */
    public static final <R> List<R> m1618scanyXmHNn8(byte[] scan, R r, Function2<? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scan.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scan.length + 1);
        arrayList.add(r);
        for (byte b : scan) {
            r = operation.invoke(r, new UByte(b));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scan-zi1B2BA, reason: not valid java name */
    public static final <R> List<R> m1619scanzi1B2BA(int[] scan, R r, Function2<? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scan.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scan.length + 1);
        arrayList.add(r);
        for (int i : scan) {
            r = operation.invoke(r, new UInt(i));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scan-zww5nb8, reason: not valid java name */
    public static final <R> List<R> m1620scanzww5nb8(short[] scan, R r, Function2<? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scan.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scan.length + 1);
        arrayList.add(r);
        for (short s : scan) {
            r = operation.invoke(r, new UShort(s));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scanIndexed-3iWJZGE, reason: not valid java name */
    public static final <R> List<R> m1621scanIndexed3iWJZGE(byte[] scanIndexed, R r, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scanIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scanIndexed.length + 1);
        arrayList.add(r);
        int length = scanIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UByte(scanIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scanIndexed-bzxtMww, reason: not valid java name */
    public static final <R> List<R> m1622scanIndexedbzxtMww(short[] scanIndexed, R r, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scanIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scanIndexed.length + 1);
        arrayList.add(r);
        int length = scanIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UShort(scanIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scanIndexed-mwnnOCs, reason: not valid java name */
    public static final <R> List<R> m1623scanIndexedmwnnOCs(long[] scanIndexed, R r, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scanIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scanIndexed.length + 1);
        arrayList.add(r);
        int length = scanIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new ULong(scanIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: scanIndexed-yVwIW0Q, reason: not valid java name */
    public static final <R> List<R> m1624scanIndexedyVwIW0Q(int[] scanIndexed, R r, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (scanIndexed.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(scanIndexed.length + 1);
        arrayList.add(r);
        int length = scanIndexed.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, new UInt(scanIndexed[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle--ajY-9A, reason: not valid java name */
    public static final void m1625shuffleajY9A(@NotNull int[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1626shuffle2D5oskM(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-2D5oskM, reason: not valid java name */
    public static final void m1626shuffle2D5oskM(@NotNull int[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = shuffle.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            int i = shuffle[length];
            shuffle[length] = shuffle[iNextInt];
            shuffle[iNextInt] = i;
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-GBYM_sE, reason: not valid java name */
    public static final void m1627shuffleGBYM_sE(@NotNull byte[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1630shuffleoSF2wD8(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-JzugnMA, reason: not valid java name */
    public static final void m1628shuffleJzugnMA(@NotNull long[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = shuffle.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            long j = shuffle[length];
            shuffle[length] = shuffle[iNextInt];
            shuffle[iNextInt] = j;
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-QwZRm1k, reason: not valid java name */
    public static final void m1629shuffleQwZRm1k(@NotNull long[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1628shuffleJzugnMA(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-oSF2wD8, reason: not valid java name */
    public static final void m1630shuffleoSF2wD8(@NotNull byte[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = shuffle.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            byte b = shuffle[length];
            shuffle[length] = shuffle[iNextInt];
            shuffle[iNextInt] = b;
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-rL5Bavg, reason: not valid java name */
    public static final void m1631shufflerL5Bavg(@NotNull short[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1632shuffles5X_as8(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: shuffle-s5X_as8, reason: not valid java name */
    public static final void m1632shuffles5X_as8(@NotNull short[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = shuffle.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            short s = shuffle[length];
            shuffle[length] = shuffle[iNextInt];
            shuffle[iNextInt] = s;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single--ajY-9A, reason: not valid java name */
    public static final int m1633singleajY9A(int[] single) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        return ArraysKt___ArraysKt.single(single);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-GBYM_sE, reason: not valid java name */
    public static final byte m1634singleGBYM_sE(byte[] single) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        return ArraysKt___ArraysKt.single(single);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-JOV_ifY, reason: not valid java name */
    public static final byte m1635singleJOV_ifY(byte[] single, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UByte uByte = null;
        boolean z = false;
        for (byte b : single) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uByte = new UByte(b);
                z = true;
            }
        }
        if (z) {
            return uByte.data;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-MShoTSo, reason: not valid java name */
    public static final long m1636singleMShoTSo(long[] single, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ULong uLong = null;
        boolean z = false;
        for (long j : single) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uLong = new ULong(j);
                z = true;
            }
        }
        if (z) {
            return uLong.data;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-QwZRm1k, reason: not valid java name */
    public static final long m1637singleQwZRm1k(long[] single) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        return ArraysKt___ArraysKt.single(single);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-jgv0xPQ, reason: not valid java name */
    public static final int m1638singlejgv0xPQ(int[] single, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UInt uInt = null;
        boolean z = false;
        for (int i : single) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uInt = new UInt(i);
                z = true;
            }
        }
        if (z) {
            return uInt.data;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-rL5Bavg, reason: not valid java name */
    public static final short m1639singlerL5Bavg(short[] single) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        return ArraysKt___ArraysKt.single(single);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: single-xTcfx_M, reason: not valid java name */
    public static final short m1640singlexTcfx_M(short[] single, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UShort uShort = null;
        boolean z = false;
        for (short s : single) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uShort = new UShort(s);
                z = true;
            }
        }
        if (z) {
            return uShort.data;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: singleOrNull--ajY-9A, reason: not valid java name */
    public static final UInt m1641singleOrNullajY9A(@NotNull int[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (singleOrNull.length == 1) {
            return new UInt(singleOrNull[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: singleOrNull-GBYM_sE, reason: not valid java name */
    public static final UByte m1642singleOrNullGBYM_sE(@NotNull byte[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (singleOrNull.length == 1) {
            return new UByte(singleOrNull[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: singleOrNull-JOV_ifY, reason: not valid java name */
    public static final UByte m1643singleOrNullJOV_ifY(byte[] singleOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UByte uByte = null;
        boolean z = false;
        for (byte b : singleOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                if (z) {
                    return null;
                }
                uByte = new UByte(b);
                z = true;
            }
        }
        if (z) {
            return uByte;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: singleOrNull-MShoTSo, reason: not valid java name */
    public static final ULong m1644singleOrNullMShoTSo(long[] singleOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ULong uLong = null;
        boolean z = false;
        for (long j : singleOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                if (z) {
                    return null;
                }
                uLong = new ULong(j);
                z = true;
            }
        }
        if (z) {
            return uLong;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: singleOrNull-QwZRm1k, reason: not valid java name */
    public static final ULong m1645singleOrNullQwZRm1k(@NotNull long[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (singleOrNull.length == 1) {
            return new ULong(singleOrNull[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: singleOrNull-jgv0xPQ, reason: not valid java name */
    public static final UInt m1646singleOrNulljgv0xPQ(int[] singleOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UInt uInt = null;
        boolean z = false;
        for (int i : singleOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                if (z) {
                    return null;
                }
                uInt = new UInt(i);
                z = true;
            }
        }
        if (z) {
            return uInt;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* JADX INFO: renamed from: singleOrNull-rL5Bavg, reason: not valid java name */
    public static final UShort m1647singleOrNullrL5Bavg(@NotNull short[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (singleOrNull.length == 1) {
            return new UShort(singleOrNull[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: singleOrNull-xTcfx_M, reason: not valid java name */
    public static final UShort m1648singleOrNullxTcfx_M(short[] singleOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UShort uShort = null;
        boolean z = false;
        for (short s : singleOrNull) {
            if (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                if (z) {
                    return null;
                }
                uShort = new UShort(s);
                z = true;
            }
        }
        if (z) {
            return uShort;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-F7u83W8, reason: not valid java name */
    public static final List<ULong> m1649sliceF7u83W8(@NotNull long[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(new ULong(slice[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-HwE9HBo, reason: not valid java name */
    public static final List<UInt> m1650sliceHwE9HBo(@NotNull int[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(new UInt(slice[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-JGPC0-M, reason: not valid java name */
    public static final List<UShort> m1651sliceJGPC0M(@NotNull short[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(new UShort(slice[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-JQknh5Q, reason: not valid java name */
    public static final List<UByte> m1652sliceJQknh5Q(@NotNull byte[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(new UByte(slice[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-Q6IL4kU, reason: not valid java name */
    public static final List<UShort> m1653sliceQ6IL4kU(@NotNull short[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : UArraysKt___UArraysJvmKt.m1055asListrL5Bavg(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.first, indices.last + 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-ZRhS8yI, reason: not valid java name */
    public static final List<ULong> m1654sliceZRhS8yI(@NotNull long[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : UArraysKt___UArraysJvmKt.m1054asListQwZRm1k(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.first, indices.last + 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-c0bezYM, reason: not valid java name */
    public static final List<UByte> m1655slicec0bezYM(@NotNull byte[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : UArraysKt___UArraysJvmKt.m1053asListGBYM_sE(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.first, indices.last + 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: slice-tAntMlw, reason: not valid java name */
    public static final List<UInt> m1656slicetAntMlw(@NotNull int[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : UArraysKt___UArraysJvmKt.m1052asListajY9A(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.first, indices.last + 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-CFIt9YE, reason: not valid java name */
    public static final int[] m1657sliceArrayCFIt9YE(@NotNull int[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-Q6IL4kU, reason: not valid java name */
    public static final short[] m1658sliceArrayQ6IL4kU(@NotNull short[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-ZRhS8yI, reason: not valid java name */
    public static final long[] m1659sliceArrayZRhS8yI(@NotNull long[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-c0bezYM, reason: not valid java name */
    public static final byte[] m1660sliceArrayc0bezYM(@NotNull byte[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-kzHmqpY, reason: not valid java name */
    public static final long[] m1661sliceArraykzHmqpY(@NotNull long[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-ojwP5H8, reason: not valid java name */
    public static final short[] m1662sliceArrayojwP5H8(@NotNull short[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-tAntMlw, reason: not valid java name */
    public static final int[] m1663sliceArraytAntMlw(@NotNull int[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sliceArray-xo_DsdI, reason: not valid java name */
    public static final byte[] m1664sliceArrayxo_DsdI(@NotNull byte[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort--ajY-9A, reason: not valid java name */
    public static final void m1665sortajY9A(@NotNull int[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            UArraySortingKt.m1050sortArrayoBK06Vg(sort, 0, sort.length);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort--nroSd4, reason: not valid java name */
    public static final void m1666sortnroSd4(@NotNull long[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, sort.length);
        if (i < i2 - 1) {
            UArraySortingKt.m1047sortArraynroSd4(sort, i, i2);
        }
    }

    /* JADX INFO: renamed from: sort--nroSd4$default, reason: not valid java name */
    public static void m1667sortnroSd4$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        m1666sortnroSd4(jArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-4UcCI2c, reason: not valid java name */
    public static final void m1668sort4UcCI2c(@NotNull byte[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, sort.length);
        if (i < i2 - 1) {
            UArraySortingKt.m1048sortArray4UcCI2c(sort, i, i2);
        }
    }

    /* JADX INFO: renamed from: sort-4UcCI2c$default, reason: not valid java name */
    public static void m1669sort4UcCI2c$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        m1668sort4UcCI2c(bArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-Aa5vz7o, reason: not valid java name */
    public static final void m1670sortAa5vz7o(@NotNull short[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, sort.length);
        if (i < i2 - 1) {
            UArraySortingKt.m1049sortArrayAa5vz7o(sort, i, i2);
        }
    }

    /* JADX INFO: renamed from: sort-Aa5vz7o$default, reason: not valid java name */
    public static void m1671sortAa5vz7o$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        m1670sortAa5vz7o(sArr, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-GBYM_sE, reason: not valid java name */
    public static final void m1672sortGBYM_sE(@NotNull byte[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            UArraySortingKt.m1048sortArray4UcCI2c(sort, 0, sort.length);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-QwZRm1k, reason: not valid java name */
    public static final void m1673sortQwZRm1k(@NotNull long[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            UArraySortingKt.m1047sortArraynroSd4(sort, 0, sort.length);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-oBK06Vg, reason: not valid java name */
    public static final void m1674sortoBK06Vg(@NotNull int[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, sort.length);
        if (i < i2 - 1) {
            UArraySortingKt.m1050sortArrayoBK06Vg(sort, i, i2);
        }
    }

    /* JADX INFO: renamed from: sort-oBK06Vg$default, reason: not valid java name */
    public static void m1675sortoBK06Vg$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        m1674sortoBK06Vg(iArr, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sort-rL5Bavg, reason: not valid java name */
    public static final void m1676sortrL5Bavg(@NotNull short[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            UArraySortingKt.m1049sortArrayAa5vz7o(sort, 0, sort.length);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending--ajY-9A, reason: not valid java name */
    public static final void m1677sortDescendingajY9A(@NotNull int[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (sortDescending.length > 1) {
            m1665sortajY9A(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending--nroSd4, reason: not valid java name */
    public static final void m1678sortDescendingnroSd4(@NotNull long[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1666sortnroSd4(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-4UcCI2c, reason: not valid java name */
    public static final void m1679sortDescending4UcCI2c(@NotNull byte[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1668sort4UcCI2c(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-Aa5vz7o, reason: not valid java name */
    public static final void m1680sortDescendingAa5vz7o(@NotNull short[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1670sortAa5vz7o(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-GBYM_sE, reason: not valid java name */
    public static final void m1681sortDescendingGBYM_sE(@NotNull byte[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (sortDescending.length > 1) {
            m1672sortGBYM_sE(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-QwZRm1k, reason: not valid java name */
    public static final void m1682sortDescendingQwZRm1k(@NotNull long[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (sortDescending.length > 1) {
            m1673sortQwZRm1k(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-oBK06Vg, reason: not valid java name */
    public static final void m1683sortDescendingoBK06Vg(@NotNull int[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1674sortoBK06Vg(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortDescending-rL5Bavg, reason: not valid java name */
    public static final void m1684sortDescendingrL5Bavg(@NotNull short[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (sortDescending.length > 1) {
            m1676sortrL5Bavg(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sorted--ajY-9A, reason: not valid java name */
    public static final List<UInt> m1685sortedajY9A(@NotNull int[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        int[] iArrCopyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        m1665sortajY9A(iArrCopyOf);
        return UArraysKt___UArraysJvmKt.m1052asListajY9A(iArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sorted-GBYM_sE, reason: not valid java name */
    public static final List<UByte> m1686sortedGBYM_sE(@NotNull byte[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        byte[] bArrCopyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        m1672sortGBYM_sE(bArrCopyOf);
        return UArraysKt___UArraysJvmKt.m1053asListGBYM_sE(bArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sorted-QwZRm1k, reason: not valid java name */
    public static final List<ULong> m1687sortedQwZRm1k(@NotNull long[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        long[] jArrCopyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        m1673sortQwZRm1k(jArrCopyOf);
        return UArraysKt___UArraysJvmKt.m1054asListQwZRm1k(jArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sorted-rL5Bavg, reason: not valid java name */
    public static final List<UShort> m1688sortedrL5Bavg(@NotNull short[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        short[] sArrCopyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        m1676sortrL5Bavg(sArrCopyOf);
        return UArraysKt___UArraysJvmKt.m1055asListrL5Bavg(sArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArray--ajY-9A, reason: not valid java name */
    public static final int[] m1689sortedArrayajY9A(@NotNull int[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (sortedArray.length == 0) {
            return sortedArray;
        }
        int[] iArrCopyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        m1665sortajY9A(iArrCopyOf);
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArray-GBYM_sE, reason: not valid java name */
    public static final byte[] m1690sortedArrayGBYM_sE(@NotNull byte[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (sortedArray.length == 0) {
            return sortedArray;
        }
        byte[] bArrCopyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        m1672sortGBYM_sE(bArrCopyOf);
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArray-QwZRm1k, reason: not valid java name */
    public static final long[] m1691sortedArrayQwZRm1k(@NotNull long[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (sortedArray.length == 0) {
            return sortedArray;
        }
        long[] jArrCopyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        m1673sortQwZRm1k(jArrCopyOf);
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArray-rL5Bavg, reason: not valid java name */
    public static final short[] m1692sortedArrayrL5Bavg(@NotNull short[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (sortedArray.length == 0) {
            return sortedArray;
        }
        short[] sArrCopyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        m1676sortrL5Bavg(sArrCopyOf);
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArrayDescending--ajY-9A, reason: not valid java name */
    public static final int[] m1693sortedArrayDescendingajY9A(@NotNull int[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (sortedArrayDescending.length == 0) {
            return sortedArrayDescending;
        }
        int[] iArrCopyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        m1677sortDescendingajY9A(iArrCopyOf);
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArrayDescending-GBYM_sE, reason: not valid java name */
    public static final byte[] m1694sortedArrayDescendingGBYM_sE(@NotNull byte[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (sortedArrayDescending.length == 0) {
            return sortedArrayDescending;
        }
        byte[] bArrCopyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        m1681sortDescendingGBYM_sE(bArrCopyOf);
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArrayDescending-QwZRm1k, reason: not valid java name */
    public static final long[] m1695sortedArrayDescendingQwZRm1k(@NotNull long[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (sortedArrayDescending.length == 0) {
            return sortedArrayDescending;
        }
        long[] jArrCopyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        m1682sortDescendingQwZRm1k(jArrCopyOf);
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedArrayDescending-rL5Bavg, reason: not valid java name */
    public static final short[] m1696sortedArrayDescendingrL5Bavg(@NotNull short[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (sortedArrayDescending.length == 0) {
            return sortedArrayDescending;
        }
        short[] sArrCopyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        m1684sortDescendingrL5Bavg(sArrCopyOf);
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedDescending--ajY-9A, reason: not valid java name */
    public static final List<UInt> m1697sortedDescendingajY9A(@NotNull int[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        int[] iArrCopyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        m1665sortajY9A(iArrCopyOf);
        return m1593reversedajY9A(iArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedDescending-GBYM_sE, reason: not valid java name */
    public static final List<UByte> m1698sortedDescendingGBYM_sE(@NotNull byte[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        byte[] bArrCopyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        m1672sortGBYM_sE(bArrCopyOf);
        return m1594reversedGBYM_sE(bArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedDescending-QwZRm1k, reason: not valid java name */
    public static final List<ULong> m1699sortedDescendingQwZRm1k(@NotNull long[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        long[] jArrCopyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        m1673sortQwZRm1k(jArrCopyOf);
        return m1595reversedQwZRm1k(jArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: sortedDescending-rL5Bavg, reason: not valid java name */
    public static final List<UShort> m1700sortedDescendingrL5Bavg(@NotNull short[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        short[] sArrCopyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        m1676sortrL5Bavg(sArrCopyOf);
        return m1596reversedrL5Bavg(sArrCopyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: sum--ajY-9A, reason: not valid java name */
    public static final int m1701sumajY9A(int[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        return ArraysKt___ArraysKt.sum(sum);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: sum-GBYM_sE, reason: not valid java name */
    public static final int m1702sumGBYM_sE(byte[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int i = 0;
        for (byte b : sum) {
            i += b & 255;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: sum-QwZRm1k, reason: not valid java name */
    public static final long m1703sumQwZRm1k(long[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        return ArraysKt___ArraysKt.sum(sum);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: sum-rL5Bavg, reason: not valid java name */
    public static final int m1704sumrL5Bavg(short[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int i = 0;
        for (short s : sum) {
            i += s & UShort.MAX_VALUE;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumBy-JOV_ifY, reason: not valid java name */
    public static final int m1705sumByJOV_ifY(byte[] sumBy, Function1<? super UByte, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (byte b : sumBy) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumBy-MShoTSo, reason: not valid java name */
    public static final int m1706sumByMShoTSo(long[] sumBy, Function1<? super ULong, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (long j : sumBy) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumBy-jgv0xPQ, reason: not valid java name */
    public static final int m1707sumByjgv0xPQ(int[] sumBy, Function1<? super UInt, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (int i2 : sumBy) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumBy-xTcfx_M, reason: not valid java name */
    public static final int m1708sumByxTcfx_M(short[] sumBy, Function1<? super UShort, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (short s : sumBy) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumByDouble-JOV_ifY, reason: not valid java name */
    public static final double m1709sumByDoubleJOV_ifY(byte[] sumByDouble, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (byte b : sumByDouble) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumByDouble-MShoTSo, reason: not valid java name */
    public static final double m1710sumByDoubleMShoTSo(long[] sumByDouble, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (long j : sumByDouble) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumByDouble-jgv0xPQ, reason: not valid java name */
    public static final double m1711sumByDoublejgv0xPQ(int[] sumByDouble, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i : sumByDouble) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* JADX INFO: renamed from: sumByDouble-xTcfx_M, reason: not valid java name */
    public static final double m1712sumByDoublexTcfx_M(short[] sumByDouble, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (short s : sumByDouble) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(byte[] sumOf, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (byte b : sumOf) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(byte[] sumOf, Function1<? super UByte, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (byte b : sumOf) {
            iIntValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(byte[] sumOf, Function1<? super UByte, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (byte b : sumOf) {
            jLongValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int i = 0;
        for (UByte uByte : uByteArr) {
            i += uByte.data & 255;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(byte[] sumOf, Function1<? super UByte, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (byte b : sumOf) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(byte[] sumOf, Function1<? super UByte, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (byte b : sumOf) {
            j += ((ULong) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, selector)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int i = 0;
        for (UShort uShort : uShortArr) {
            i += uShort.data & UShort.MAX_VALUE;
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: take-PpDY95g, reason: not valid java name */
    public static final List<UByte> m1713takePpDY95g(@NotNull byte[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= take.length) {
            return CollectionsKt___CollectionsKt.toList(new UByteArray(take));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UByte(take[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (byte b : take) {
            arrayList.add(new UByte(b));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: take-nggk6HY, reason: not valid java name */
    public static final List<UShort> m1714takenggk6HY(@NotNull short[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= take.length) {
            return CollectionsKt___CollectionsKt.toList(new UShortArray(take));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UShort(take[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (short s : take) {
            arrayList.add(new UShort(s));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: take-qFRl0hI, reason: not valid java name */
    public static final List<UInt> m1715takeqFRl0hI(@NotNull int[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= take.length) {
            return CollectionsKt___CollectionsKt.toList(new UIntArray(take));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UInt(take[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (int i3 : take) {
            arrayList.add(new UInt(i3));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: take-r7IrZao, reason: not valid java name */
    public static final List<ULong> m1716taker7IrZao(@NotNull long[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= take.length) {
            return CollectionsKt___CollectionsKt.toList(new ULongArray(take));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new ULong(take[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (long j : take) {
            arrayList.add(new ULong(j));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: takeLast-PpDY95g, reason: not valid java name */
    public static final List<UByte> m1717takeLastPpDY95g(@NotNull byte[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = takeLast.length;
        if (i >= length) {
            return CollectionsKt___CollectionsKt.toList(new UByteArray(takeLast));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UByte(takeLast[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(new UByte(takeLast[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: takeLast-nggk6HY, reason: not valid java name */
    public static final List<UShort> m1718takeLastnggk6HY(@NotNull short[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = takeLast.length;
        if (i >= length) {
            return CollectionsKt___CollectionsKt.toList(new UShortArray(takeLast));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UShort(takeLast[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(new UShort(takeLast[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: takeLast-qFRl0hI, reason: not valid java name */
    public static final List<UInt> m1719takeLastqFRl0hI(@NotNull int[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = takeLast.length;
        if (i >= length) {
            return CollectionsKt___CollectionsKt.toList(new UIntArray(takeLast));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new UInt(takeLast[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(new UInt(takeLast[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: takeLast-r7IrZao, reason: not valid java name */
    public static final List<ULong> m1720takeLastr7IrZao(@NotNull long[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = takeLast.length;
        if (i >= length) {
            return CollectionsKt___CollectionsKt.toList(new ULongArray(takeLast));
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(new ULong(takeLast[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(new ULong(takeLast[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeLastWhile-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1721takeLastWhileJOV_ifY(byte[] takeLastWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = takeLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return CollectionsKt___CollectionsKt.toList(new UByteArray(takeLastWhile));
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(takeLastWhile[length], predicate)).booleanValue());
        return m1189dropPpDY95g(takeLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeLastWhile-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1722takeLastWhileMShoTSo(long[] takeLastWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = takeLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return CollectionsKt___CollectionsKt.toList(new ULongArray(takeLastWhile));
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(takeLastWhile[length], predicate)).booleanValue());
        return m1192dropr7IrZao(takeLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeLastWhile-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1723takeLastWhilejgv0xPQ(int[] takeLastWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = takeLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return CollectionsKt___CollectionsKt.toList(new UIntArray(takeLastWhile));
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(takeLastWhile[length], predicate)).booleanValue());
        return m1191dropqFRl0hI(takeLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeLastWhile-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1724takeLastWhilexTcfx_M(short[] takeLastWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = takeLastWhile.length;
        do {
            length--;
            if (-1 >= length) {
                return CollectionsKt___CollectionsKt.toList(new UShortArray(takeLastWhile));
            }
        } while (((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(takeLastWhile[length], predicate)).booleanValue());
        return m1190dropnggk6HY(takeLastWhile, length + 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeWhile-JOV_ifY, reason: not valid java name */
    public static final List<UByte> m1725takeWhileJOV_ifY(byte[] takeWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : takeWhile) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(b, predicate)).booleanValue()) {
                break;
            }
            arrayList.add(new UByte(b));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeWhile-MShoTSo, reason: not valid java name */
    public static final List<ULong> m1726takeWhileMShoTSo(long[] takeWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : takeWhile) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, predicate)).booleanValue()) {
                break;
            }
            arrayList.add(new ULong(j));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeWhile-jgv0xPQ, reason: not valid java name */
    public static final List<UInt> m1727takeWhilejgv0xPQ(int[] takeWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : takeWhile) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, predicate)).booleanValue()) {
                break;
            }
            arrayList.add(new UInt(i));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: takeWhile-xTcfx_M, reason: not valid java name */
    public static final List<UShort> m1728takeWhilexTcfx_M(short[] takeWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : takeWhile) {
            if (!((Boolean) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, predicate)).booleanValue()) {
                break;
            }
            arrayList.add(new UShort(s));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toByteArray-GBYM_sE, reason: not valid java name */
    public static final byte[] m1729toByteArrayGBYM_sE(byte[] toByteArray) {
        Intrinsics.checkNotNullParameter(toByteArray, "$this$toByteArray");
        byte[] bArrCopyOf = Arrays.copyOf(toByteArray, toByteArray.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toIntArray--ajY-9A, reason: not valid java name */
    public static final int[] m1730toIntArrayajY9A(int[] toIntArray) {
        Intrinsics.checkNotNullParameter(toIntArray, "$this$toIntArray");
        int[] iArrCopyOf = Arrays.copyOf(toIntArray, toIntArray.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toLongArray-QwZRm1k, reason: not valid java name */
    public static final long[] m1731toLongArrayQwZRm1k(long[] toLongArray) {
        Intrinsics.checkNotNullParameter(toLongArray, "$this$toLongArray");
        long[] jArrCopyOf = Arrays.copyOf(toLongArray, toLongArray.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: toShortArray-rL5Bavg, reason: not valid java name */
    public static final short[] m1732toShortArrayrL5Bavg(short[] toShortArray) {
        Intrinsics.checkNotNullParameter(toShortArray, "$this$toShortArray");
        short[] sArrCopyOf = Arrays.copyOf(toShortArray, toShortArray.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toTypedArray--ajY-9A, reason: not valid java name */
    public static final UInt[] m1733toTypedArrayajY9A(@NotNull int[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int length = toTypedArray.length;
        UInt[] uIntArr = new UInt[length];
        for (int i = 0; i < length; i++) {
            uIntArr[i] = new UInt(toTypedArray[i]);
        }
        return uIntArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toTypedArray-GBYM_sE, reason: not valid java name */
    public static final UByte[] m1734toTypedArrayGBYM_sE(@NotNull byte[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int length = toTypedArray.length;
        UByte[] uByteArr = new UByte[length];
        for (int i = 0; i < length; i++) {
            uByteArr[i] = new UByte(toTypedArray[i]);
        }
        return uByteArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toTypedArray-QwZRm1k, reason: not valid java name */
    public static final ULong[] m1735toTypedArrayQwZRm1k(@NotNull long[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int length = toTypedArray.length;
        ULong[] uLongArr = new ULong[length];
        for (int i = 0; i < length; i++) {
            uLongArr[i] = new ULong(toTypedArray[i]);
        }
        return uLongArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toTypedArray-rL5Bavg, reason: not valid java name */
    public static final UShort[] m1736toTypedArrayrL5Bavg(@NotNull short[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int length = toTypedArray.length;
        UShort[] uShortArr = new UShort[length];
        for (int i = 0; i < length; i++) {
            uShortArr[i] = new UShort(toTypedArray[i]);
        }
        return uShortArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int length = uByteArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = uByteArr[i].data;
        }
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int length = uIntArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = uIntArr[i].data;
        }
        return iArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        int length = uLongArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = uLongArr[i].data;
        }
        return jArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int length = uShortArr.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = uShortArr[i].data;
        }
        return sArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: withIndex--ajY-9A, reason: not valid java name */
    public static final Iterable<IndexedValue<UInt>> m1737withIndexajY9A(@NotNull final int[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.unsigned.UArraysKt___UArraysKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new UIntArray.Iterator(withIndex);
            }
        });
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: withIndex-GBYM_sE, reason: not valid java name */
    public static final Iterable<IndexedValue<UByte>> m1738withIndexGBYM_sE(@NotNull final byte[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.unsigned.UArraysKt___UArraysKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new UByteArray.Iterator(withIndex);
            }
        });
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: withIndex-QwZRm1k, reason: not valid java name */
    public static final Iterable<IndexedValue<ULong>> m1739withIndexQwZRm1k(@NotNull final long[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.unsigned.UArraysKt___UArraysKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new ULongArray.Iterator(withIndex);
            }
        });
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: withIndex-rL5Bavg, reason: not valid java name */
    public static final Iterable<IndexedValue<UShort>> m1740withIndexrL5Bavg(@NotNull final short[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.unsigned.UArraysKt___UArraysKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new UShortArray.Iterator(withIndex);
            }
        });
    }

    public static final Iterator withIndex_GBYM_sE$lambda$58$UArraysKt___UArraysKt(byte[] bArr) {
        return new UByteArray.Iterator(bArr);
    }

    public static final Iterator withIndex_QwZRm1k$lambda$57$UArraysKt___UArraysKt(long[] jArr) {
        return new ULongArray.Iterator(jArr);
    }

    public static final Iterator withIndex__ajY_9A$lambda$56$UArraysKt___UArraysKt(int[] iArr) {
        return new UIntArray.Iterator(iArr);
    }

    public static final Iterator withIndex_rL5Bavg$lambda$59$UArraysKt___UArraysKt(short[] sArr) {
        return new UShortArray.Iterator(sArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-7znnbtw, reason: not valid java name */
    public static final <R, V> List<V> m1741zip7znnbtw(int[] zip, Iterable<? extends R> other, Function2<? super UInt, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(new UInt(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-8LME4QE, reason: not valid java name */
    public static final <R, V> List<V> m1742zip8LME4QE(long[] zip, R[] other, Function2<? super ULong, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new ULong(zip[i]), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-C-E_24M, reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m1743zipCE_24M(@NotNull int[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            int i2 = zip[i];
            arrayList.add(new Pair(new UInt(i2), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-F7u83W8, reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m1744zipF7u83W8(@NotNull long[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(new ULong(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-HwE9HBo, reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m1745zipHwE9HBo(@NotNull int[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(new UInt(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-JAKpvQM, reason: not valid java name */
    public static final <V> List<V> m1746zipJAKpvQM(byte[] zip, byte[] other, Function2<? super UByte, ? super UByte, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UByte(zip[i]), new UByte(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-JGPC0-M, reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m1747zipJGPC0M(@NotNull short[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(new UShort(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-JQknh5Q, reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m1748zipJQknh5Q(@NotNull byte[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(new UByte(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-L83TJbI, reason: not valid java name */
    public static final <V> List<V> m1749zipL83TJbI(int[] zip, int[] other, Function2<? super UInt, ? super UInt, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UInt(zip[i]), new UInt(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-LuipOMY, reason: not valid java name */
    public static final <R, V> List<V> m1750zipLuipOMY(byte[] zip, R[] other, Function2<? super UByte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UByte(zip[i]), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-PabeH-Q, reason: not valid java name */
    public static final <V> List<V> m1751zipPabeHQ(long[] zip, long[] other, Function2<? super ULong, ? super ULong, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new ULong(zip[i]), new ULong(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-TUPTUsU, reason: not valid java name */
    public static final <R, V> List<V> m1752zipTUPTUsU(long[] zip, Iterable<? extends R> other, Function2<? super ULong, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(new ULong(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-UCnP4_w, reason: not valid java name */
    public static final <R, V> List<V> m1753zipUCnP4_w(byte[] zip, Iterable<? extends R> other, Function2<? super UByte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(new UByte(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-ZjwqOic, reason: not valid java name */
    public static final <R, V> List<V> m1754zipZjwqOic(int[] zip, R[] other, Function2<? super UInt, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UInt(zip[i]), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-ctEhBpI, reason: not valid java name */
    public static final List<Pair<UInt, UInt>> m1755zipctEhBpI(@NotNull int[] zip, @NotNull int[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(new UInt(zip[i]), new UInt(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-ePBmRWY, reason: not valid java name */
    public static final <R, V> List<V> m1756zipePBmRWY(short[] zip, R[] other, Function2<? super UShort, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UShort(zip[i]), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-f7H3mmw, reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m1757zipf7H3mmw(@NotNull long[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            long j = zip[i];
            arrayList.add(new Pair(new ULong(j), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-gVVukQo, reason: not valid java name */
    public static final <V> List<V> m1758zipgVVukQo(short[] zip, short[] other, Function2<? super UShort, ? super UShort, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(new UShort(zip[i]), new UShort(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* JADX INFO: renamed from: zip-kBb4a-s, reason: not valid java name */
    public static final <R, V> List<V> m1759zipkBb4as(short[] zip, Iterable<? extends R> other, Function2<? super UShort, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zip.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(new UShort(zip[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-kdPth3s, reason: not valid java name */
    public static final List<Pair<UByte, UByte>> m1760zipkdPth3s(@NotNull byte[] zip, @NotNull byte[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(new UByte(zip[i]), new UByte(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-mazbYpA, reason: not valid java name */
    public static final List<Pair<UShort, UShort>> m1761zipmazbYpA(@NotNull short[] zip, @NotNull short[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(new UShort(zip[i]), new UShort(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-nl983wc, reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m1762zipnl983wc(@NotNull byte[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            byte b = zip[i];
            arrayList.add(new Pair(new UByte(b), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-uaTIQ5s, reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m1763zipuaTIQ5s(@NotNull short[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            short s = zip[i];
            arrayList.add(new Pair(new UShort(s), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: zip-us8wMrg, reason: not valid java name */
    public static final List<Pair<ULong, ULong>> m1764zipus8wMrg(@NotNull long[] zip, @NotNull long[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zip.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(new ULong(zip[i]), new ULong(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final byte[] toUByteArray(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final int[] toUIntArray(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        return iArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final long[] toULongArray(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        return jArrCopyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    public static final short[] toUShortArray(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] sArrCopyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        return sArrCopyOf;
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* JADX INFO: renamed from: maxOrThrow-U, reason: not valid java name */
    public static final int m1442maxOrThrowU(@NotNull int[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (max.length != 0) {
            int i = max[0];
            int i2 = 1;
            int length = max.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = max[i2];
                    if (Integer.compare(i ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) < 0) {
                        i = i3;
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* JADX INFO: renamed from: minOrThrow-U, reason: not valid java name */
    public static final int m1498minOrThrowU(@NotNull int[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (min.length != 0) {
            int i = min[0];
            int i2 = 1;
            int length = min.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = min[i2];
                    if (Integer.compare(i ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) > 0) {
                        i = i3;
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* JADX INFO: renamed from: maxWithOrThrow-U, reason: not valid java name */
    public static final int m1450maxWithOrThrowU(@NotNull int[] maxWith, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWith.length != 0) {
            int i = maxWith[0];
            int i2 = 1;
            int length = maxWith.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = maxWith[i2];
                    if (comparator.compare(new UInt(i), new UInt(i3)) < 0) {
                        i = i3;
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* JADX INFO: renamed from: minWithOrThrow-U, reason: not valid java name */
    public static final int m1506minWithOrThrowU(@NotNull int[] minWith, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWith.length != 0) {
            int i = minWith[0];
            int i2 = 1;
            int length = minWith.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = minWith[i2];
                    if (comparator.compare(new UInt(i), new UInt(i3)) > 0) {
                        i = i3;
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(int[] sumOf, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i : sumOf) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(int[] sumOf, Function1<? super UInt, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (int i : sumOf) {
            iIntValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(int[] sumOf, Function1<? super UInt, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (int i : sumOf) {
            jLongValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(int[] sumOf, Function1<? super UInt, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (int i2 : sumOf) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i2, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(int[] sumOf, Function1<? super UInt, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (int i : sumOf) {
            j += ((ULong) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* JADX INFO: renamed from: maxOrThrow-U, reason: not valid java name */
    public static final long m1443maxOrThrowU(@NotNull long[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (max.length != 0) {
            long j = max[0];
            int i = 1;
            int length = max.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = max[i];
                    if (Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) < 0) {
                        j = j2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* JADX INFO: renamed from: minOrThrow-U, reason: not valid java name */
    public static final long m1499minOrThrowU(@NotNull long[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (min.length != 0) {
            long j = min[0];
            int i = 1;
            int length = min.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = min[i];
                    if (Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) > 0) {
                        j = j2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* JADX INFO: renamed from: maxWithOrThrow-U, reason: not valid java name */
    public static final long m1451maxWithOrThrowU(@NotNull long[] maxWith, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWith.length != 0) {
            long j = maxWith[0];
            int i = 1;
            int length = maxWith.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = maxWith[i];
                    if (comparator.compare(new ULong(j), new ULong(j2)) < 0) {
                        j = j2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* JADX INFO: renamed from: minWithOrThrow-U, reason: not valid java name */
    public static final long m1507minWithOrThrowU(@NotNull long[] minWith, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWith.length != 0) {
            long j = minWith[0];
            int i = 1;
            int length = minWith.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = minWith[i];
                    if (comparator.compare(new ULong(j), new ULong(j2)) > 0) {
                        j = j2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(long[] sumOf, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (long j : sumOf) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(long[] sumOf, Function1<? super ULong, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (long j : sumOf) {
            iIntValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(long[] sumOf, Function1<? super ULong, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (long j : sumOf) {
            jLongValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-JOV_ifY, reason: not valid java name */
    public static final Double m1418maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-MShoTSo, reason: not valid java name */
    public static final Double m1421maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final Double m1424maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-xTcfx_M, reason: not valid java name */
    public static final Double m1427maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-JOV_ifY, reason: not valid java name */
    public static final Double m1474minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-MShoTSo, reason: not valid java name */
    public static final Double m1477minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final Double m1480minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-xTcfx_M, reason: not valid java name */
    public static final Double m1483minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        double dDoubleValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[0], selector)).doubleValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[i], selector)).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "maxByOrThrow-U")
    /* JADX INFO: renamed from: maxByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> int m1402maxByOrThrowU(int[] maxBy, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxBy.length != 0) {
            int i = maxBy[0];
            int i2 = 1;
            int length = maxBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector);
                if (1 <= length) {
                    while (true) {
                        int i3 = maxBy[i2];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i3, selector);
                        if (comparable.compareTo(comparable2) < 0) {
                            i = i3;
                            comparable = comparable2;
                        }
                        if (i2 == length) {
                            break;
                        }
                        i2++;
                    }
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-JOV_ifY, reason: not valid java name */
    public static final float m1406maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[0], selector)).floatValue();
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-MShoTSo, reason: not valid java name */
    public static final float m1409maxOfMShoTSo(long[] maxOf, Function1<? super ULong, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[0], selector)).floatValue();
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-jgv0xPQ, reason: not valid java name */
    public static final float m1412maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[0], selector)).floatValue();
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-xTcfx_M, reason: not valid java name */
    public static final float m1415maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[0], selector)).floatValue();
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[i], selector)).floatValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "minByOrThrow-U")
    /* JADX INFO: renamed from: minByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> int m1458minByOrThrowU(int[] minBy, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minBy.length != 0) {
            int i = minBy[0];
            int i2 = 1;
            int length = minBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i, selector);
                if (1 <= length) {
                    while (true) {
                        int i3 = minBy[i2];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(i3, selector);
                        if (comparable.compareTo(comparable2) > 0) {
                            i = i3;
                            comparable = comparable2;
                        }
                        if (i2 == length) {
                            break;
                        }
                        i2++;
                    }
                }
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-JOV_ifY, reason: not valid java name */
    public static final float m1462minOfJOV_ifY(byte[] minOf, Function1<? super UByte, Float> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[0], selector)).floatValue();
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-MShoTSo, reason: not valid java name */
    public static final float m1465minOfMShoTSo(long[] minOf, Function1<? super ULong, Float> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[0], selector)).floatValue();
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-jgv0xPQ, reason: not valid java name */
    public static final float m1468minOfjgv0xPQ(int[] minOf, Function1<? super UInt, Float> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[0], selector)).floatValue();
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[i], selector)).floatValue());
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-xTcfx_M, reason: not valid java name */
    public static final float m1471minOfxTcfx_M(short[] minOf, Function1<? super UShort, Float> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[0], selector)).floatValue();
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[i], selector)).floatValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(long[] sumOf, Function1<? super ULong, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (long j : sumOf) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(long[] sumOf, Function1<? super ULong, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (long j2 : sumOf) {
            j += ((ULong) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j2, selector)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* JADX INFO: renamed from: maxOrThrow-U, reason: not valid java name */
    public static final short m1444maxOrThrowU(@NotNull short[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (max.length != 0) {
            short s = max[0];
            int i = 1;
            int length = max.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = max[i];
                    if (Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) < 0) {
                        s = s2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* JADX INFO: renamed from: minOrThrow-U, reason: not valid java name */
    public static final short m1500minOrThrowU(@NotNull short[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (min.length != 0) {
            short s = min[0];
            int i = 1;
            int length = min.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = min[i];
                    if (Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) > 0) {
                        s = s2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* JADX INFO: renamed from: maxWithOrThrow-U, reason: not valid java name */
    public static final short m1452maxWithOrThrowU(@NotNull short[] maxWith, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (maxWith.length != 0) {
            short s = maxWith[0];
            int i = 1;
            int length = maxWith.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = maxWith[i];
                    if (comparator.compare(new UShort(s), new UShort(s2)) < 0) {
                        s = s2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* JADX INFO: renamed from: minWithOrThrow-U, reason: not valid java name */
    public static final short m1508minWithOrThrowU(@NotNull short[] minWith, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (minWith.length != 0) {
            short s = minWith[0];
            int i = 1;
            int length = minWith.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = minWith[i];
                    if (comparator.compare(new UShort(s), new UShort(s2)) > 0) {
                        s = s2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(short[] sumOf, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (short s : sumOf) {
            dDoubleValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(short[] sumOf, Function1<? super UShort, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (short s : sumOf) {
            iIntValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(short[] sumOf, Function1<? super UShort, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (short s : sumOf) {
            jLongValue += ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getIndices--ajY-9A$annotations, reason: not valid java name */
    public static /* synthetic */ void m1310getIndicesajY9A$annotations(int[] iArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getIndices-GBYM_sE$annotations, reason: not valid java name */
    public static /* synthetic */ void m1312getIndicesGBYM_sE$annotations(byte[] bArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getIndices-QwZRm1k$annotations, reason: not valid java name */
    public static /* synthetic */ void m1314getIndicesQwZRm1k$annotations(long[] jArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getIndices-rL5Bavg$annotations, reason: not valid java name */
    public static /* synthetic */ void m1316getIndicesrL5Bavg$annotations(short[] sArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getLastIndex--ajY-9A$annotations, reason: not valid java name */
    public static /* synthetic */ void m1318getLastIndexajY9A$annotations(int[] iArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getLastIndex-GBYM_sE$annotations, reason: not valid java name */
    public static /* synthetic */ void m1320getLastIndexGBYM_sE$annotations(byte[] bArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getLastIndex-QwZRm1k$annotations, reason: not valid java name */
    public static /* synthetic */ void m1322getLastIndexQwZRm1k$annotations(long[] jArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: getLastIndex-rL5Bavg$annotations, reason: not valid java name */
    public static /* synthetic */ void m1324getLastIndexrL5Bavg$annotations(short[] sArr) {
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(short[] sumOf, Function1<? super UShort, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (short s : sumOf) {
            i += ((UInt) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(short[] sumOf, Function1<? super UShort, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (short s : sumOf) {
            j += ((ULong) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector)).data;
        }
        return j;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-JOV_ifY, reason: not valid java name */
    public static final Float m1419maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-MShoTSo, reason: not valid java name */
    public static final Float m1422maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final Float m1425maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOfOrNull-xTcfx_M, reason: not valid java name */
    public static final Float m1428maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, Float> selector) {
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = maxOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-JOV_ifY, reason: not valid java name */
    public static final Float m1475minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, Float> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-MShoTSo, reason: not valid java name */
    public static final Float m1478minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, Float> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-jgv0xPQ, reason: not valid java name */
    public static final Float m1481minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, Float> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOfOrNull-xTcfx_M, reason: not valid java name */
    public static final Float m1484minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, Float> selector) {
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOfOrNull.length == 0) {
            return null;
        }
        float fFloatValue = ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[0], selector)).floatValue();
        int i = 1;
        int length = minOfOrNull.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, ((Number) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOfOrNull[i], selector)).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "maxByOrThrow-U")
    /* JADX INFO: renamed from: maxByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> long m1403maxByOrThrowU(long[] maxBy, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxBy.length != 0) {
            long j = maxBy[0];
            int i = 1;
            int length = maxBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector);
                if (1 <= length) {
                    while (true) {
                        long j2 = maxBy[i];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j2, selector);
                        if (comparable.compareTo(comparable2) < 0) {
                            j = j2;
                            comparable = comparable2;
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                    }
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1407maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[0], selector);
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(maxOf[i], selector);
                    if (r.compareTo(comparable) < 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1410maxOfMShoTSo(long[] maxOf, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[0], selector);
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(maxOf[i], selector);
                    if (r.compareTo(comparable) < 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1413maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[0], selector);
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(maxOf[i], selector);
                    if (r.compareTo(comparable) < 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: maxOf-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1416maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[0], selector);
            int i = 1;
            int length = maxOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(maxOf[i], selector);
                    if (r.compareTo(comparable) < 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "minByOrThrow-U")
    /* JADX INFO: renamed from: minByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> long m1459minByOrThrowU(long[] minBy, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minBy.length != 0) {
            long j = minBy[0];
            int i = 1;
            int length = minBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j, selector);
                if (1 <= length) {
                    while (true) {
                        long j2 = minBy[i];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(j2, selector);
                        if (comparable.compareTo(comparable2) > 0) {
                            j = j2;
                            comparable = comparable2;
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                    }
                }
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-JOV_ifY, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1463minOfJOV_ifY(byte[] minOf, Function1<? super UByte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[0], selector);
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline2.m(minOf[i], selector);
                    if (r.compareTo(comparable) > 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-MShoTSo, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1466minOfMShoTSo(long[] minOf, Function1<? super ULong, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[0], selector);
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline1.m(minOf[i], selector);
                    if (r.compareTo(comparable) > 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-jgv0xPQ, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1469minOfjgv0xPQ(int[] minOf, Function1<? super UInt, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[0], selector);
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline3.m(minOf[i], selector);
                    if (r.compareTo(comparable) > 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
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
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* JADX INFO: renamed from: minOf-xTcfx_M, reason: not valid java name */
    public static final <R extends Comparable<? super R>> R m1472minOfxTcfx_M(short[] minOf, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minOf.length != 0) {
            R r = (R) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[0], selector);
            int i = 1;
            int length = minOf.length - 1;
            if (1 <= length) {
                while (true) {
                    Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(minOf[i], selector);
                    if (r.compareTo(comparable) > 0) {
                        r = (R) comparable;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return r;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int i = 0;
        for (UInt uInt : uIntArr) {
            i += uInt.data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        long j = 0;
        for (ULong uLong : uLongArr) {
            j += uLong.data;
        }
        return j;
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "maxByOrThrow-U")
    /* JADX INFO: renamed from: maxByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> short m1404maxByOrThrowU(short[] maxBy, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (maxBy.length != 0) {
            short s = maxBy[0];
            int i = 1;
            int length = maxBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector);
                if (1 <= length) {
                    while (true) {
                        short s2 = maxBy[i];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s2, selector);
                        if (comparable.compareTo(comparable2) < 0) {
                            s = s2;
                            comparable = comparable2;
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                    }
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "minByOrThrow-U")
    /* JADX INFO: renamed from: minByOrThrow-U, reason: not valid java name */
    public static final <R extends Comparable<? super R>> short m1460minByOrThrowU(short[] minBy, Function1<? super UShort, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (minBy.length != 0) {
            short s = minBy[0];
            int i = 1;
            int length = minBy.length - 1;
            if (length != 0) {
                Comparable comparable = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s, selector);
                if (1 <= length) {
                    while (true) {
                        short s2 = minBy[i];
                        Comparable comparable2 = (Comparable) UArraysKt___UArraysJvmKt$$ExternalSyntheticOutline0.m(s2, selector);
                        if (comparable.compareTo(comparable2) > 0) {
                            s = s2;
                            comparable = comparable2;
                        }
                        if (i == length) {
                            break;
                        }
                        i++;
                    }
                }
            }
            return s;
        }
        throw new NoSuchElementException();
    }
}
