package kotlin.collections;

import android.R;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt__AppendableKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\n_Arrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,25321:1\n12687#1,2:25322\n12697#1,2:25324\n1310#1,2:25326\n1318#1,2:25328\n1326#1,2:25330\n1334#1,2:25332\n1342#1,2:25334\n1350#1,2:25336\n1358#1,2:25338\n1366#1,2:25340\n1374#1,2:25342\n2353#1,5:25344\n2366#1,5:25349\n2379#1,5:25354\n2392#1,5:25359\n2405#1,5:25364\n2418#1,5:25369\n2431#1,5:25374\n2444#1,5:25379\n2457#1,5:25384\n4344#1,2:25390\n4354#1,2:25392\n4364#1,2:25394\n4374#1,2:25396\n4384#1,2:25398\n4394#1,2:25400\n4404#1,2:25402\n4414#1,2:25404\n4424#1,2:25406\n4011#1:25408\n13537#1,2:25409\n4012#1,2:25411\n13539#1:25413\n4014#1:25414\n4025#1:25415\n13547#1,2:25416\n4026#1,2:25418\n13549#1:25420\n4028#1:25421\n4039#1:25422\n13557#1,2:25423\n4040#1,2:25425\n13559#1:25427\n4042#1:25428\n4053#1:25429\n13567#1,2:25430\n4054#1,2:25432\n13569#1:25434\n4056#1:25435\n4067#1:25436\n13577#1,2:25437\n4068#1,2:25439\n13579#1:25441\n4070#1:25442\n4081#1:25443\n13587#1,2:25444\n4082#1,2:25446\n13589#1:25448\n4084#1:25449\n4095#1:25450\n13597#1,2:25451\n4096#1,2:25453\n13599#1:25455\n4098#1:25456\n4109#1:25457\n13607#1,2:25458\n4110#1,2:25460\n13609#1:25462\n4112#1:25463\n4123#1:25464\n13617#1,2:25465\n4124#1,2:25467\n13619#1:25469\n4126#1:25470\n13537#1,3:25471\n13547#1,3:25474\n13557#1,3:25477\n13567#1,3:25480\n13577#1,3:25483\n13587#1,3:25486\n13597#1,3:25489\n13607#1,3:25492\n13617#1,3:25495\n4144#1,2:25498\n4254#1,2:25500\n4264#1,2:25502\n4274#1,2:25504\n4284#1,2:25506\n4294#1,2:25508\n4304#1,2:25510\n4314#1,2:25512\n4324#1,2:25514\n4334#1,2:25516\n9251#1,4:25518\n9266#1,4:25522\n9281#1,4:25526\n9296#1,4:25530\n9311#1,4:25534\n9326#1,4:25538\n9341#1,4:25542\n9356#1,4:25546\n9371#1,4:25550\n8964#1,4:25554\n8980#1,4:25558\n8996#1,4:25562\n9012#1,4:25566\n9028#1,4:25570\n9044#1,4:25574\n9060#1,4:25578\n9076#1,4:25582\n9092#1,4:25586\n9108#1,4:25590\n9124#1,4:25594\n9140#1,4:25598\n9156#1,4:25602\n9172#1,4:25606\n9188#1,4:25610\n9204#1,4:25614\n9220#1,4:25618\n9236#1,4:25622\n9539#1,4:25626\n10557#1,5:25630\n10568#1,5:25635\n10579#1,5:25640\n10590#1,5:25645\n10601#1,5:25650\n10612#1,5:25655\n10623#1,5:25660\n10634#1,5:25665\n10645#1,5:25670\n10660#1,5:25675\n10901#1,3:25680\n10904#1,3:25690\n10918#1,3:25693\n10921#1,3:25703\n10935#1,3:25706\n10938#1,3:25716\n10952#1,3:25719\n10955#1,3:25729\n10969#1,3:25732\n10972#1,3:25742\n10986#1,3:25745\n10989#1,3:25755\n11003#1,3:25758\n11006#1,3:25768\n11020#1,3:25771\n11023#1,3:25781\n11037#1,3:25784\n11040#1,3:25794\n11055#1,3:25797\n11058#1,3:25807\n11073#1,3:25810\n11076#1,3:25820\n11091#1,3:25823\n11094#1,3:25833\n11109#1,3:25836\n11112#1,3:25846\n11127#1,3:25849\n11130#1,3:25859\n11145#1,3:25862\n11148#1,3:25872\n11163#1,3:25875\n11166#1,3:25885\n11181#1,3:25888\n11184#1,3:25898\n11199#1,3:25901\n11202#1,3:25911\n11563#1,3:26040\n11573#1,3:26043\n11583#1,3:26046\n11593#1,3:26049\n11603#1,3:26052\n11613#1,3:26055\n11623#1,3:26058\n11633#1,3:26061\n11643#1,3:26064\n11429#1,4:26067\n11442#1,4:26071\n11455#1,4:26075\n11468#1,4:26079\n11481#1,4:26083\n11494#1,4:26087\n11507#1,4:26091\n11520#1,4:26095\n11533#1,4:26099\n11418#1:26103\n13537#1,2:26104\n13539#1:26107\n11419#1:26108\n13537#1,3:26109\n11554#1:26112\n13472#1:26113\n13473#1:26115\n11555#1:26116\n13472#1,2:26117\n13537#1,3:26119\n13547#1,3:26122\n13557#1,3:26125\n13567#1,3:26128\n13577#1,3:26131\n13587#1,3:26134\n13597#1,3:26137\n13607#1,3:26140\n13617#1,3:26143\n21540#1,2:26146\n21542#1,6:26149\n21756#1,2:26155\n21758#1,6:26158\n23933#1,6:26164\n23949#1,6:26170\n23965#1,6:26176\n23981#1,6:26182\n23997#1,6:26188\n24013#1,6:26194\n24029#1,6:26200\n24045#1,6:26206\n24061#1,6:26212\n24167#1,8:26218\n24185#1,8:26226\n24203#1,8:26234\n24221#1,8:26242\n24239#1,8:26250\n24257#1,8:26258\n24275#1,8:26266\n24293#1,8:26274\n24311#1,8:26282\n24409#1,6:26290\n24425#1,6:26296\n24441#1,6:26302\n24457#1,6:26308\n24473#1,6:26314\n24489#1,6:26320\n24505#1,6:26326\n24521#1,6:26332\n1#2:25389\n1#2:26106\n1#2:26114\n1#2:26148\n1#2:26157\n384#3,7:25683\n384#3,7:25696\n384#3,7:25709\n384#3,7:25722\n384#3,7:25735\n384#3,7:25748\n384#3,7:25761\n384#3,7:25774\n384#3,7:25787\n384#3,7:25800\n384#3,7:25813\n384#3,7:25826\n384#3,7:25839\n384#3,7:25852\n384#3,7:25865\n384#3,7:25878\n384#3,7:25891\n384#3,7:25904\n384#3,7:25914\n384#3,7:25921\n384#3,7:25928\n384#3,7:25935\n384#3,7:25942\n384#3,7:25949\n384#3,7:25956\n384#3,7:25963\n384#3,7:25970\n384#3,7:25977\n384#3,7:25984\n384#3,7:25991\n384#3,7:25998\n384#3,7:26005\n384#3,7:26012\n384#3,7:26019\n384#3,7:26026\n384#3,7:26033\n*S KotlinDebug\n*F\n+ 1 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n557#1:25322,2\n566#1:25324,2\n860#1:25326,2\n870#1:25328,2\n880#1:25330,2\n890#1:25332,2\n900#1:25334,2\n910#1:25336,2\n920#1:25338,2\n930#1:25340,2\n940#1:25342,2\n950#1:25344,5\n960#1:25349,5\n970#1:25354,5\n980#1:25359,5\n990#1:25364,5\n1000#1:25369,5\n1010#1:25374,5\n1020#1:25379,5\n1030#1:25384,5\n3829#1:25390,2\n3838#1:25392,2\n3847#1:25394,2\n3856#1:25396,2\n3865#1:25398,2\n3874#1:25400,2\n3883#1:25402,2\n3892#1:25404,2\n3901#1:25406,2\n3912#1:25408\n3912#1:25409,2\n3912#1:25411,2\n3912#1:25413\n3912#1:25414\n3923#1:25415\n3923#1:25416,2\n3923#1:25418,2\n3923#1:25420\n3923#1:25421\n3934#1:25422\n3934#1:25423,2\n3934#1:25425,2\n3934#1:25427\n3934#1:25428\n3945#1:25429\n3945#1:25430,2\n3945#1:25432,2\n3945#1:25434\n3945#1:25435\n3956#1:25436\n3956#1:25437,2\n3956#1:25439,2\n3956#1:25441\n3956#1:25442\n3967#1:25443\n3967#1:25444,2\n3967#1:25446,2\n3967#1:25448\n3967#1:25449\n3978#1:25450\n3978#1:25451,2\n3978#1:25453,2\n3978#1:25455\n3978#1:25456\n3989#1:25457\n3989#1:25458,2\n3989#1:25460,2\n3989#1:25462\n3989#1:25463\n4000#1:25464\n4000#1:25465,2\n4000#1:25467,2\n4000#1:25469\n4000#1:25470\n4011#1:25471,3\n4025#1:25474,3\n4039#1:25477,3\n4053#1:25480,3\n4067#1:25483,3\n4081#1:25486,3\n4095#1:25489,3\n4109#1:25492,3\n4123#1:25495,3\n4135#1:25498,2\n4154#1:25500,2\n4163#1:25502,2\n4172#1:25504,2\n4181#1:25506,2\n4190#1:25508,2\n4199#1:25510,2\n4208#1:25512,2\n4217#1:25514,2\n4226#1:25516,2\n8570#1:25518,4\n8585#1:25522,4\n8600#1:25526,4\n8615#1:25530,4\n8630#1:25534,4\n8645#1:25538,4\n8660#1:25542,4\n8675#1:25546,4\n8690#1:25550,4\n8705#1:25554,4\n8720#1:25558,4\n8735#1:25562,4\n8750#1:25566,4\n8765#1:25570,4\n8780#1:25574,4\n8795#1:25578,4\n8810#1:25582,4\n8825#1:25586,4\n8839#1:25590,4\n8853#1:25594,4\n8867#1:25598,4\n8881#1:25602,4\n8895#1:25606,4\n8909#1:25610,4\n8923#1:25614,4\n8937#1:25618,4\n8951#1:25622,4\n9390#1:25626,4\n10135#1:25630,5\n10144#1:25635,5\n10153#1:25640,5\n10162#1:25645,5\n10171#1:25650,5\n10180#1:25655,5\n10189#1:25660,5\n10198#1:25665,5\n10207#1:25670,5\n10220#1:25675,5\n10676#1:25680,3\n10676#1:25690,3\n10688#1:25693,3\n10688#1:25703,3\n10700#1:25706,3\n10700#1:25716,3\n10712#1:25719,3\n10712#1:25729,3\n10724#1:25732,3\n10724#1:25742,3\n10736#1:25745,3\n10736#1:25755,3\n10748#1:25758,3\n10748#1:25768,3\n10760#1:25771,3\n10760#1:25781,3\n10772#1:25784,3\n10772#1:25794,3\n10785#1:25797,3\n10785#1:25807,3\n10798#1:25810,3\n10798#1:25820,3\n10811#1:25823,3\n10811#1:25833,3\n10824#1:25836,3\n10824#1:25846,3\n10837#1:25849,3\n10837#1:25859,3\n10850#1:25862,3\n10850#1:25872,3\n10863#1:25875,3\n10863#1:25885,3\n10876#1:25888,3\n10876#1:25898,3\n10889#1:25901,3\n10889#1:25911,3\n11228#1:26040,3\n11238#1:26043,3\n11248#1:26046,3\n11258#1:26049,3\n11268#1:26052,3\n11278#1:26055,3\n11288#1:26058,3\n11298#1:26061,3\n11308#1:26064,3\n11318#1:26067,4\n11328#1:26071,4\n11338#1:26075,4\n11348#1:26079,4\n11358#1:26083,4\n11368#1:26087,4\n11378#1:26091,4\n11388#1:26095,4\n11398#1:26099,4\n11408#1:26103\n11408#1:26104,2\n11408#1:26107\n11408#1:26108\n11418#1:26109,3\n11546#1:26112\n11546#1:26113\n11546#1:26115\n11546#1:26116\n11554#1:26117,2\n19826#1:26119,3\n19838#1:26122,3\n19850#1:26125,3\n19862#1:26128,3\n19874#1:26131,3\n19886#1:26134,3\n19898#1:26137,3\n19910#1:26140,3\n19922#1:26143,3\n22370#1:26146,2\n22370#1:26149,6\n22523#1:26155,2\n22523#1:26158,6\n23842#1:26164,6\n23852#1:26170,6\n23862#1:26176,6\n23872#1:26182,6\n23882#1:26188,6\n23892#1:26194,6\n23902#1:26200,6\n23912#1:26206,6\n23922#1:26212,6\n24076#1:26218,8\n24086#1:26226,8\n24096#1:26234,8\n24106#1:26242,8\n24116#1:26250,8\n24126#1:26258,8\n24136#1:26266,8\n24146#1:26274,8\n24156#1:26282,8\n24328#1:26290,6\n24338#1:26296,6\n24348#1:26302,6\n24358#1:26308,6\n24368#1:26314,6\n24378#1:26320,6\n24388#1:26326,6\n24398#1:26332,6\n11408#1:26106\n11546#1:26114\n22370#1:26148\n22523#1:26157\n10676#1:25683,7\n10688#1:25696,7\n10700#1:25709,7\n10712#1:25722,7\n10724#1:25735,7\n10736#1:25748,7\n10748#1:25761,7\n10760#1:25774,7\n10772#1:25787,7\n10785#1:25800,7\n10798#1:25813,7\n10811#1:25826,7\n10824#1:25839,7\n10837#1:25852,7\n10850#1:25865,7\n10863#1:25878,7\n10876#1:25891,7\n10889#1:25904,7\n10903#1:25914,7\n10920#1:25921,7\n10937#1:25928,7\n10954#1:25935,7\n10971#1:25942,7\n10988#1:25949,7\n11005#1:25956,7\n11022#1:25963,7\n11039#1:25970,7\n11057#1:25977,7\n11075#1:25984,7\n11093#1:25991,7\n11111#1:25998,7\n11129#1:26005,7\n11147#1:26012,7\n11165#1:26019,7\n11183#1:26026,7\n11201#1:26033,7\n*E\n"})
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final <T> boolean all(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (!predicate.invoke(t).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean any(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return !(tArr.length == 0);
    }

    @NotNull
    public static <T> Iterable<T> asIterable(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0 ? EmptyList.INSTANCE : new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1(tArr);
    }

    @NotNull
    public static <T> Sequence<T> asSequence(@NotNull final T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0 ? EmptySequence.INSTANCE : new Sequence<T>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                return ArrayIteratorKt.iterator(tArr);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, K, V> Map<K, V> associate(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (R.bool boolVar : tArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(boolVar);
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K> Map<K, T> associateBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (T t : tArr) {
            linkedHashMap.put(keySelector.invoke(t), t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, ? super T>> M associateByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : tArr) {
            destination.put(keySelector.invoke(t), t);
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(t);
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <K, V> Map<K, V> associateWith(@NotNull K[] kArr, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(kArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(kArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (K k : kArr) {
            linkedHashMap.put(k, valueSelector.invoke(k));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateWithTo(@NotNull K[] kArr, @NotNull M destination, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(kArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (K k : kArr) {
            destination.put(k, valueSelector.invoke(k));
        }
        return destination;
    }

    public static final double average(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (byte b : bArr) {
            d += (double) b;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @JvmName(name = "averageOfByte")
    public static final double averageOfByte(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        double dByteValue = 0.0d;
        int i = 0;
        for (Byte b : bArr) {
            dByteValue += (double) b.byteValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dByteValue / ((double) i);
    }

    @JvmName(name = "averageOfDouble")
    public static final double averageOfDouble(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double dDoubleValue = 0.0d;
        int i = 0;
        for (Double d : dArr) {
            dDoubleValue += d.doubleValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dDoubleValue / ((double) i);
    }

    @JvmName(name = "averageOfFloat")
    public static final double averageOfFloat(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        double dFloatValue = 0.0d;
        int i = 0;
        for (Float f : fArr) {
            dFloatValue += (double) f.floatValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dFloatValue / ((double) i);
    }

    @JvmName(name = "averageOfInt")
    public static final double averageOfInt(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        double dIntValue = 0.0d;
        int i = 0;
        for (Integer num : numArr) {
            dIntValue += (double) num.intValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dIntValue / ((double) i);
    }

    @JvmName(name = "averageOfLong")
    public static final double averageOfLong(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        double dLongValue = 0.0d;
        int i = 0;
        for (Long l : lArr) {
            dLongValue += l.longValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dLongValue / ((double) i);
    }

    @JvmName(name = "averageOfShort")
    public static final double averageOfShort(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        double dShortValue = 0.0d;
        int i = 0;
        for (Short sh : shArr) {
            dShortValue += (double) sh.shortValue();
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return dShortValue / ((double) i);
    }

    @InlineOnly
    public static final <T> T component1(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[0];
    }

    @InlineOnly
    public static final <T> T component2(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[1];
    }

    @InlineOnly
    public static final <T> T component3(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[2];
    }

    @InlineOnly
    public static final <T> T component4(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[3];
    }

    @InlineOnly
    public static final <T> T component5(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[4];
    }

    public static <T> boolean contains(@NotNull T[] tArr, T t) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return indexOf(tArr, t) >= 0;
    }

    @InlineOnly
    public static final <T> int count(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length;
    }

    @NotNull
    public static final <T> List<T> distinct(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(tArr));
    }

    @NotNull
    public static final <T, K> List<T> distinctBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (hashSet.add(selector.invoke(t))) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> drop(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = tArr.length - i;
        if (length < 0) {
            length = 0;
        }
        return takeLast(tArr, length);
    }

    @NotNull
    public static final <T> List<T> dropLast(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        int length = tArr.length - i;
        if (length < 0) {
            length = 0;
        }
        return take(tArr, length);
    }

    @NotNull
    public static final <T> List<T> dropLastWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(tArr[length]).booleanValue());
        return take(tArr, length + 1);
    }

    @NotNull
    public static final <T> List<T> dropWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (T t : tArr) {
            if (z) {
                arrayList.add(t);
            } else if (!predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
                z = true;
            }
        }
        return arrayList;
    }

    @InlineOnly
    public static final <T> T elementAtOrElse(T[] tArr, int i, Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= tArr.length) ? defaultValue.invoke(Integer.valueOf(i)) : tArr[i];
    }

    @InlineOnly
    public static final <T> T elementAtOrNull(T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T) getOrNull(tArr, i);
    }

    @NotNull
    public static final <T> List<T> filter(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            T t = tArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), t).booleanValue()) {
                arrayList.add(t);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterIndexedTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            T t = tArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), t).booleanValue()) {
                destination.add(t);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    public static final <R> List<R> filterIsInstance(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        ArrayList arrayList = new ArrayList();
        if (objArr.length <= 0) {
            return arrayList;
        }
        Object obj = objArr[0];
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <R, C extends Collection<? super R>> C filterIsInstanceTo(Object[] objArr, C destination) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (objArr.length <= 0) {
            return destination;
        }
        Object obj = objArr[0];
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final <T> List<T> filterNot(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (!predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static <T> List<T> filterNotNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        ArrayList arrayList = new ArrayList();
        filterNotNullTo(tArr, arrayList);
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super T>, T> C filterNotNullTo(@NotNull T[] tArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t : tArr) {
            if (t != null) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterNotTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (!predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                destination.add(t);
            }
        }
        return destination;
    }

    @InlineOnly
    public static final <T> T find(T[] tArr, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    @InlineOnly
    public static final <T> T findLast(T[] tArr, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            T t = tArr[length];
            if (predicate.invoke(t).booleanValue()) {
                return t;
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static <T> T first(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length != 0) {
            return tArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOf(T[] tArr, Function1<? super T, ? extends R> transform) {
        R rInvoke;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                rInvoke = transform.invoke(tArr[i]);
                if (rInvoke != null) {
                    break;
                }
                i++;
            } else {
                rInvoke = null;
                break;
            }
        }
        if (rInvoke != null) {
            return rInvoke;
        }
        throw new NoSuchElementException("No element of the array was transformed to a non-null value.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static final <T, R> R firstNotNullOfOrNull(T[] tArr, Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            R rInvoke = transform.invoke(t);
            if (rInvoke != null) {
                return rInvoke;
            }
        }
        return null;
    }

    @Nullable
    public static final <T> T firstOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[0];
    }

    @NotNull
    public static final <T, R> List<R> flatMap(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(t));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapIndexedIterable(T[] tArr, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedIterableTo(T[] tArr, C destination, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequence")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapIndexedSequence(T[] tArr, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequenceTo")
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapIndexedSequenceTo(T[] tArr, C destination, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequence")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapSequence(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(t));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequenceTo")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapSequenceTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(t));
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C flatMapTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(t));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R fold(@NotNull T[] tArr, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (R.bool boolVar : tArr) {
            r = operation.invoke(r, boolVar);
        }
        return r;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldIndexed(@NotNull T[] tArr, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            r = operation.invoke(Integer.valueOf(i2), r, tArr[i]);
            i++;
            i2++;
        }
        return r;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldRight(@NotNull T[] tArr, R r, @NotNull Function2<? super T, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = tArr.length - 1; length >= 0; length--) {
            r = operation.invoke(tArr[length], r);
        }
        return r;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldRightIndexed(@NotNull T[] tArr, R r, @NotNull Function3<? super Integer, ? super T, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = tArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), tArr[length], r);
        }
        return r;
    }

    public static final <T> void forEach(@NotNull T[] tArr, @NotNull Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (T t : tArr) {
            action.invoke(t);
        }
    }

    public static final <T> void forEachIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), tArr[i]);
            i++;
            i2++;
        }
    }

    @NotNull
    public static final <T> IntRange getIndices(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return new IntRange(0, tArr.length - 1, 1);
    }

    public static final <T> int getLastIndex(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length - 1;
    }

    @InlineOnly
    public static final <T> T getOrElse(T[] tArr, int i, Function1<? super Integer, ? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= tArr.length) ? defaultValue.invoke(Integer.valueOf(i)) : tArr[i];
    }

    @Nullable
    public static <T> T getOrNull(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i < 0 || i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    @NotNull
    public static final <T, K> Map<K, List<T>> groupBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : tArr) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(t);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, List<T>>> M groupByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t : tArr) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(t);
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Grouping<T, K> groupingBy(@NotNull final T[] tArr, @NotNull final Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        return new Grouping<T, K>() { // from class: kotlin.collections.ArraysKt___ArraysKt.groupingBy.1
            @Override // kotlin.collections.Grouping
            public K keyOf(T t) {
                return keySelector.invoke(t);
            }

            @Override // kotlin.collections.Grouping
            public Iterator<T> sourceIterator() {
                return ArrayIteratorKt.iterator(tArr);
            }
        };
    }

    public static <T> int indexOf(@NotNull T[] tArr, T t) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i = 0;
        if (t == null) {
            int length = tArr.length;
            while (i < length) {
                if (tArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i < length2) {
            if (t.equals(tArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T> int indexOfFirst(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(tArr[i]).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final <T> int indexOfLast(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(tArr[length]).booleanValue()) {
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

    @NotNull
    public static final <T> Set<T> intersect(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    public static final <T> boolean isEmpty(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0;
    }

    @InlineOnly
    public static final <T> boolean isNotEmpty(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return !(tArr.length == 0);
    }

    @NotNull
    public static final <T, A extends Appendable> A joinTo(@NotNull T[] tArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (T t : tArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            StringsKt__AppendableKt.appendElement(buffer, t, function1);
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ Appendable joinTo$default(Object[] objArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(objArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (i2 & 64) != 0 ? null : function1);
        return appendable;
    }

    @NotNull
    public static final <T> String joinToString(@NotNull T[] tArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(tArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(Object[] objArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(objArr, charSequence, charSequence2, charSequence3, i, charSequence5, function12);
    }

    public static final <T> T last(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length != 0) {
            return tArr[tArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final <T> int lastIndexOf(@NotNull T[] tArr, T t) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (t == null) {
            int length = tArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i = length - 1;
                    if (tArr[length] == null) {
                        return length;
                    }
                    if (i < 0) {
                        break;
                    }
                    length = i;
                }
            }
        } else {
            int length2 = tArr.length - 1;
            if (length2 >= 0) {
                while (true) {
                    int i2 = length2 - 1;
                    if (t.equals(tArr[length2])) {
                        return length2;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    length2 = i2;
                }
            }
        }
        return -1;
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[tArr.length - 1];
    }

    @NotNull
    public static final <T, R> List<R> map(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(tArr.length);
        for (T t : tArr) {
            arrayList.add(transform.invoke(t));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(tArr.length);
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexedNotNull(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            R rInvoke = transform.invoke(Integer.valueOf(i2), tArr[i]);
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            R rInvoke = transform.invoke(Integer.valueOf(i2), tArr[i]);
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), tArr[i]));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <T, R> List<R> mapNotNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            R rInvoke = transform.invoke(t);
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            R rInvoke = transform.invoke(t);
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t : tArr) {
            destination.add(transform.invoke(t));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T maxByOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <T, R extends Comparable<? super R>> T maxByOrThrow(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
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

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double maxOf(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = selector.invoke(tArr[0]).doubleValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(tArr[i]).doubleValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Double m963maxOfOrNull(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(tArr[0]).doubleValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(tArr[i]).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWith(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R maxOfWithOrNull(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxOrNull(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = dArr[0].doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, dArr[i].doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final double maxOrThrow(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = dArr[0].doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, dArr[i].doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T maxWithOrNull(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (comparator.compare(t, t2) < 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final <T> T maxWithOrThrow(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (comparator.compare(t, t2) < 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T minByOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <T, R extends Comparable<? super R>> T minByOrThrow(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
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

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T> double minOf(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = selector.invoke(tArr[0]).doubleValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(tArr[i]).doubleValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Double m999minOfOrNull(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(tArr[0]).doubleValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(tArr[i]).doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWith(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R> R minOfWithOrNull(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minOrNull(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = dArr[0].doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, dArr[i].doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final double minOrThrow(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        double dDoubleValue = dArr[0].doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, dArr[i].doubleValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T minWithOrNull(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (comparator.compare(t, t2) > 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final <T> T minWithOrThrow(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (comparator.compare(t, t2) > 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    public static final <T> boolean none(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> T[] onEach(T[] tArr, Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (T t : tArr) {
            action.invoke(t);
        }
        return tArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> T[] onEachIndexed(T[] tArr, Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), tArr[i]);
            i++;
            i2++;
        }
        return tArr;
    }

    @NotNull
    public static final <T> Pair<List<T>, List<T>> partition(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                arrayList.add(t);
            } else {
                arrayList2.add(t);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final <T> T random(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T) random(tArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <T> T randomOrNull(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T) randomOrNull(tArr, Random.Default);
    }

    public static final <S, T extends S> S reduce(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S sInvoke = (Object) tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                sInvoke = operation.invoke(sInvoke, (Object) tArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sInvoke;
    }

    public static final <S, T extends S> S reduceIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S sInvoke = (Object) tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                sInvoke = operation.invoke(Integer.valueOf(i), sInvoke, (Object) tArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceIndexedOrNull(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return null;
        }
        S sInvoke = (Object) tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                sInvoke = operation.invoke(Integer.valueOf(i), sInvoke, (Object) tArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceOrNull(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return null;
        }
        S sInvoke = (Object) tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                sInvoke = operation.invoke(sInvoke, (Object) tArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sInvoke;
    }

    public static final <S, T extends S> S reduceRight(@NotNull T[] tArr, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S sInvoke = (S) tArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sInvoke = operation.invoke((Object) tArr[i2], sInvoke);
        }
        return sInvoke;
    }

    public static final <S, T extends S> S reduceRightIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i = length - 1;
        if (i < 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S sInvoke = (S) tArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sInvoke = operation.invoke(Integer.valueOf(i2), (Object) tArr[i2], sInvoke);
        }
        return sInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceRightIndexedOrNull(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        S sInvoke = (S) tArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sInvoke = operation.invoke(Integer.valueOf(i2), (Object) tArr[i2], sInvoke);
        }
        return sInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceRightOrNull(@NotNull T[] tArr, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        S sInvoke = (S) tArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sInvoke = operation.invoke((Object) tArr[i2], sInvoke);
        }
        return sInvoke;
    }

    @NotNull
    public static final <T> T[] requireNoNulls(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        for (T t : tArr) {
            if (t == null) {
                throw new IllegalArgumentException("null element found in " + tArr + '.');
            }
        }
        return tArr;
    }

    public static final <T> void reverse(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = (tArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = tArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            T t = tArr[i];
            tArr[i] = tArr[length2];
            tArr[length2] = t;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @NotNull
    public static final <T> List<T> reversed(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<T> mutableList = toMutableList(tArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final <T> T[] reversedArray(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return tArr;
        }
        T[] tArr2 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(tArr, tArr.length);
        int length = tArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                tArr2[length - i] = tArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return tArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFold(@NotNull T[] tArr, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r);
        for (R.bool boolVar : tArr) {
            r = operation.invoke(r, boolVar);
            arrayList.add(r);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFoldIndexed(@NotNull T[] tArr, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r);
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, tArr[i]);
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> List<S> runningReduce(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        S sInvoke = (Object) tArr[0];
        ArrayList arrayList = new ArrayList(tArr.length);
        arrayList.add(sInvoke);
        int length = tArr.length;
        for (int i = 1; i < length; i++) {
            sInvoke = operation.invoke(sInvoke, (Object) tArr[i]);
            arrayList.add(sInvoke);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> List<S> runningReduceIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        S sInvoke = (Object) tArr[0];
        ArrayList arrayList = new ArrayList(tArr.length);
        arrayList.add(sInvoke);
        int length = tArr.length;
        for (int i = 1; i < length; i++) {
            sInvoke = operation.invoke(Integer.valueOf(i), sInvoke, (Object) tArr[i]);
            arrayList.add(sInvoke);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(byte[] bArr, R r, Function2<? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r);
        for (byte b : bArr) {
            r = operation.invoke(r, Byte.valueOf(b));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(byte[] bArr, R r, Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Byte.valueOf(bArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void shuffle(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        shuffle(tArr, Random.Default);
    }

    public static final <T> T single(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return tArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @Nullable
    public static <T> T singleOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull T[] tArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? EmptyList.INSTANCE : ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(tArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static final <T> T[] sliceArray(@NotNull T[] tArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        T[] tArr2 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(tArr, indices.size());
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            tArr2[i] = tArr[it.next().intValue()];
            i++;
        }
        return tArr2;
    }

    public static final <T, R extends Comparable<? super R>> void sortBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length > 1) {
            ArraysKt___ArraysJvmKt.sortWith(tArr, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
        }
    }

    public static final <T, R extends Comparable<? super R>> void sortByDescending(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length > 1) {
            ArraysKt___ArraysJvmKt.sortWith(tArr, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Arrays.sort(bArr, i, i2);
        reverse(bArr, i, i2);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sorted(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return ArraysKt___ArraysJvmKt.asList(sortedArray(tArr));
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T[] sortedArray(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        T[] tArr2 = (T[]) ((Comparable[]) objArrCopyOf);
        ArraysKt___ArraysJvmKt.sort((Object[]) tArr2);
        return tArr2;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T[] sortedArrayDescending(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        T[] tArr2 = (T[]) ((Comparable[]) objArrCopyOf);
        ArraysKt___ArraysJvmKt.sortWith(tArr2, ComparisonsKt__ComparisonsKt.reverseOrder());
        return tArr2;
    }

    @NotNull
    public static final <T> T[] sortedArrayWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            return tArr;
        }
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sortWith(tArr2, comparator);
        return tArr2;
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(tArr, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedByDescending(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(tArr, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sortedDescending(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return sortedWith(tArr, ComparisonsKt__ComparisonsKt.reverseOrder());
    }

    @NotNull
    public static <T> List<T> sortedWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt___ArraysJvmKt.asList(sortedArrayWith(tArr, comparator));
    }

    @NotNull
    public static final <T> Set<T> subtract(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    public static final int sum(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i = 0;
        for (byte b : bArr) {
            i += b;
        }
        return i;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> int sumBy(@NotNull T[] tArr, @NotNull Function1<? super T, Integer> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (T t : tArr) {
            iIntValue += selector.invoke(t).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> double sumByDouble(@NotNull T[] tArr, @NotNull Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (T t : tArr) {
            dDoubleValue += selector.invoke(t).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfByte")
    public static final int sumOfByte(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int iByteValue = 0;
        for (Byte b : bArr) {
            iByteValue += b.byteValue();
        }
        return iByteValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final <T> double sumOfDouble(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (T t : tArr) {
            dDoubleValue += selector.invoke(t).doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfFloat")
    public static final float sumOfFloat(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float fFloatValue = 0.0f;
        for (Float f : fArr) {
            fFloatValue += f.floatValue();
        }
        return fFloatValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final <T> int sumOfInt(T[] tArr, Function1<? super T, Integer> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (T t : tArr) {
            iIntValue += selector.invoke(t).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final <T> long sumOfLong(T[] tArr, Function1<? super T, Long> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (T t : tArr) {
            jLongValue += selector.invoke(t).longValue();
        }
        return jLongValue;
    }

    @JvmName(name = "sumOfShort")
    public static final int sumOfShort(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        int iShortValue = 0;
        for (Short sh : shArr) {
            iShortValue += sh.shortValue();
        }
        return iShortValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> int sumOfUInt(T[] tArr, Function1<? super T, UInt> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (T t : tArr) {
            i += selector.invoke(t).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final <T> long sumOfULong(T[] tArr, Function1<? super T, ULong> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (T t : tArr) {
            j += selector.invoke(t).data;
        }
        return j;
    }

    @NotNull
    public static final List<Byte> take(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= bArr.length) {
            return toList(bArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Byte> takeLast(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = bArr.length;
        if (i >= length) {
            return toList(bArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Byte.valueOf(bArr[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> takeLastWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(tArr);
            }
        } while (predicate.invoke(tArr[length]).booleanValue());
        return drop(tArr, length + 1);
    }

    @NotNull
    public static final <T> List<T> takeWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (!predicate.invoke(t).booleanValue()) {
                break;
            }
            arrayList.add(t);
        }
        return arrayList;
    }

    @NotNull
    public static final boolean[] toBooleanArray(@NotNull Boolean[] boolArr) {
        Intrinsics.checkNotNullParameter(boolArr, "<this>");
        int length = boolArr.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = boolArr[i].booleanValue();
        }
        return zArr;
    }

    @NotNull
    public static final byte[] toByteArray(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return bArr2;
    }

    @NotNull
    public static final char[] toCharArray(@NotNull Character[] chArr) {
        Intrinsics.checkNotNullParameter(chArr, "<this>");
        int length = chArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = chArr[i].charValue();
        }
        return cArr;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C toCollection(@NotNull T[] tArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t : tArr) {
            destination.add(t);
        }
        return destination;
    }

    @NotNull
    public static final double[] toDoubleArray(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        double[] dArr2 = new double[length];
        for (int i = 0; i < length; i++) {
            dArr2[i] = dArr[i].doubleValue();
        }
        return dArr2;
    }

    @NotNull
    public static final float[] toFloatArray(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        float[] fArr2 = new float[length];
        for (int i = 0; i < length; i++) {
            fArr2[i] = fArr[i].floatValue();
        }
        return fArr2;
    }

    @NotNull
    public static final <T> HashSet<T> toHashSet(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        HashSet<T> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(tArr.length));
        toCollection(tArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final int[] toIntArray(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    @NotNull
    public static <T> List<T> toList(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        return length != 0 ? length != 1 ? toMutableList(tArr) : CollectionsKt__CollectionsJVMKt.listOf(tArr[0]) : EmptyList.INSTANCE;
    }

    @NotNull
    public static final long[] toLongArray(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        int length = lArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }

    @NotNull
    public static final <T> List<T> toMutableList(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return new ArrayList(CollectionsKt__CollectionsKt.asCollection(tArr));
    }

    @NotNull
    public static final <T> Set<T> toMutableSet(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(tArr.length));
        toCollection(tArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static <T> Set<T> toSet(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length == 1) {
            return SetsKt__SetsJVMKt.setOf(tArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(tArr.length));
        toCollection(tArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final short[] toShortArray(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        int length = shArr.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = shArr[i].shortValue();
        }
        return sArr;
    }

    @NotNull
    public static final <T> Set<T> union(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static <T> Iterable<IndexedValue<T>> withIndex(@NotNull final T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorKt.iterator(tArr);
            }
        });
    }

    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull T[] tArr, @NotNull R[] other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(tArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(tArr[i], other[i]));
        }
        return arrayList;
    }

    public static final boolean all(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return !(bArr.length == 0);
    }

    public static final double average(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (short s : sArr) {
            d += (double) s;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @InlineOnly
    public static final byte component1(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[0];
    }

    @InlineOnly
    public static final byte component2(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[1];
    }

    @InlineOnly
    public static final byte component3(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[2];
    }

    @InlineOnly
    public static final byte component4(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[3];
    }

    @InlineOnly
    public static final byte component5(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[4];
    }

    public static boolean contains(@NotNull byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return indexOf(bArr, b) >= 0;
    }

    @InlineOnly
    public static final int count(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }

    @NotNull
    public static final List<Byte> distinct(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(bArr));
    }

    @InlineOnly
    public static final byte elementAtOrElse(byte[] bArr, int i, Function1<? super Integer, Byte> defaultValue) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= bArr.length) ? defaultValue.invoke(Integer.valueOf(i)).byteValue() : bArr[i];
    }

    @InlineOnly
    public static final Byte elementAtOrNull(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return getOrNull(bArr, i);
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterNotTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                destination.add(Byte.valueOf(b));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                destination.add(Byte.valueOf(b));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Byte find(byte[] bArr, Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return Byte.valueOf(b);
            }
        }
        return null;
    }

    @Nullable
    public static final Byte firstOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(byte[] bArr, Function2<? super Integer, ? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull byte[] bArr, R r, @NotNull Function2<? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (byte b : bArr) {
            r = operation.invoke(r, Byte.valueOf(b));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull byte[] bArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Byte.valueOf(bArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final void forEach(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b : bArr) {
            action.invoke(Byte.valueOf(b));
        }
    }

    public static final void forEachIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i]));
            i++;
            i2++;
        }
    }

    public static int getLastIndex(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length - 1;
    }

    @InlineOnly
    public static final byte getOrElse(byte[] bArr, int i, Function1<? super Integer, Byte> defaultValue) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= bArr.length) ? defaultValue.invoke(Integer.valueOf(i)).byteValue() : bArr[i];
    }

    @Nullable
    public static final Byte getOrNull(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i < 0 || i >= bArr.length) {
            return null;
        }
        return Byte.valueOf(bArr[i]);
    }

    @InlineOnly
    public static final boolean isEmpty(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return !(bArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(byte[] bArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(bArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Byte, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull byte[] bArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Byte, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(bArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(byte[] bArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(bArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Byte, ? extends CharSequence>) function12);
    }

    @Nullable
    public static final Byte lastOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[bArr.length - 1]);
    }

    public static final boolean none(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final byte[] onEach(byte[] bArr, Function1<? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b : bArr) {
            action.invoke(Byte.valueOf(b));
        }
        return bArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final byte[] onEachIndexed(byte[] bArr, Function2<? super Integer, ? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i]));
            i++;
            i2++;
        }
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final byte random(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return random(bArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Byte randomOrNull(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return randomOrNull(bArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(short[] sArr, R r, Function2<? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r);
        for (short s : sArr) {
            r = operation.invoke(r, Short.valueOf(s));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(short[] sArr, R r, Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r);
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Short.valueOf(sArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        shuffle(bArr, (Random) Random.Default);
    }

    @Nullable
    public static final Byte singleOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 1) {
            return Byte.valueOf(bArr[0]);
        }
        return null;
    }

    @NotNull
    public static final List<Byte> sorted(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Byte[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(bArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Byte> sortedBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(bArr, (Comparator<? super Byte>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Byte> sortedByDescending(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(bArr, (Comparator<? super Byte>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Byte> sortedDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(bArrCopyOf);
        return reversed(bArrCopyOf);
    }

    @NotNull
    public static final List<Byte> sortedWith(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Byte[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(bArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    public static final int sum(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int i = 0;
        for (short s : sArr) {
            i += s;
        }
        return i;
    }

    @NotNull
    public static final HashSet<Byte> toHashSet(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        HashSet<Byte> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(bArr.length));
        toCollection(bArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Byte> toMutableList(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    @NotNull
    public static final Set<Byte> toMutableSet(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(bArr.length));
        toCollection(bArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Byte>> withIndex(@NotNull final byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(bArr);
            }
        });
    }

    public static final boolean all(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return !(sArr.length == 0);
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Byte>> M associateByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (byte b : bArr) {
            destination.put(keySelector.invoke(Byte.valueOf(b)), Byte.valueOf(b));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Byte, ? super V>> M associateWithTo(byte[] bArr, M destination, Function1<? super Byte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (byte b : bArr) {
            destination.put(Byte.valueOf(b), valueSelector.invoke(Byte.valueOf(b)));
        }
        return destination;
    }

    public static final double average(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (int i2 : iArr) {
            d += (double) i2;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @InlineOnly
    public static final short component1(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[0];
    }

    @InlineOnly
    public static final short component2(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[1];
    }

    @InlineOnly
    public static final short component3(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[2];
    }

    @InlineOnly
    public static final short component4(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[3];
    }

    @InlineOnly
    public static final short component5(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[4];
    }

    public static boolean contains(@NotNull short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return indexOf(sArr, s) >= 0;
    }

    @InlineOnly
    public static final int count(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length;
    }

    @NotNull
    public static final List<Short> distinct(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(sArr));
    }

    @InlineOnly
    public static final short elementAtOrElse(short[] sArr, int i, Function1<? super Integer, Short> defaultValue) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= sArr.length) ? defaultValue.invoke(Integer.valueOf(i)).shortValue() : sArr[i];
    }

    @InlineOnly
    public static final Short elementAtOrNull(short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return getOrNull(sArr, i);
    }

    @NotNull
    public static final List<Byte> filter(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                arrayList.add(Byte.valueOf(b));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterIndexedTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Byte.valueOf(b)).booleanValue()) {
                destination.add(Byte.valueOf(b));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Byte> filterNot(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                arrayList.add(Byte.valueOf(b));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterNotTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
                destination.add(Short.valueOf(s));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                destination.add(Short.valueOf(s));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Short find(short[] sArr, Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return Short.valueOf(s);
            }
        }
        return null;
    }

    @Nullable
    public static final Short firstOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(short[] sArr, Function2<? super Integer, ? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull short[] sArr, R r, @NotNull Function2<? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (short s : sArr) {
            r = operation.invoke(r, Short.valueOf(s));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull short[] sArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Short.valueOf(sArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull byte[] bArr, R r, @NotNull Function2<? super Byte, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = bArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Byte.valueOf(bArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull byte[] bArr, R r, @NotNull Function3<? super Integer, ? super Byte, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = bArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Byte.valueOf(bArr[length]), r);
        }
        return r;
    }

    public static final void forEach(@NotNull short[] sArr, @NotNull Function1<? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s : sArr) {
            action.invoke(Short.valueOf(s));
        }
    }

    public static final void forEachIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i]));
            i++;
            i2++;
        }
    }

    public static int getLastIndex(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length - 1;
    }

    @InlineOnly
    public static final short getOrElse(short[] sArr, int i, Function1<? super Integer, Short> defaultValue) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= sArr.length) ? defaultValue.invoke(Integer.valueOf(i)).shortValue() : sArr[i];
    }

    @Nullable
    public static final Short getOrNull(@NotNull short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i < 0 || i >= sArr.length) {
            return null;
        }
        return Short.valueOf(sArr[i]);
    }

    public static final int indexOfFirst(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Byte.valueOf(bArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Byte.valueOf(bArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Byte> intersect(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    public static final boolean isEmpty(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return !(sArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(short[] sArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(sArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Short, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull short[] sArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Short, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(sArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(short[] sArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(sArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Short, ? extends CharSequence>) function12);
    }

    @Nullable
    public static final Short lastOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[sArr.length - 1]);
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b : bArr) {
            destination.add(transform.invoke(Byte.valueOf(b)));
        }
        return destination;
    }

    public static final boolean none(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final short[] onEach(short[] sArr, Function1<? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s : sArr) {
            action.invoke(Short.valueOf(s));
        }
        return sArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final short[] onEachIndexed(short[] sArr, Function2<? super Integer, ? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i]));
            i++;
            i2++;
        }
        return sArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final short random(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return random(sArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Short randomOrNull(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return randomOrNull(sArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(int[] iArr, R r, Function2<? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r);
        for (int i : iArr) {
            r = operation.invoke(r, Integer.valueOf(i));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(int[] iArr, R r, Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r);
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Integer.valueOf(iArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        shuffle(sArr, (Random) Random.Default);
    }

    @Nullable
    public static final Short singleOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 1) {
            return Short.valueOf(sArr[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Arrays.sort(sArr, i, i2);
        reverse(sArr, i, i2);
    }

    @NotNull
    public static final List<Short> sorted(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Short[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(sArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final byte[] sortedArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(bArrCopyOf);
        return bArrCopyOf;
    }

    @NotNull
    public static final byte[] sortedArrayDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        sortDescending(bArrCopyOf);
        return bArrCopyOf;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Short> sortedBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sArr, (Comparator<? super Short>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Short> sortedByDescending(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sArr, (Comparator<? super Short>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Short> sortedDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] sArrCopyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(sArrCopyOf);
        return reversed(sArrCopyOf);
    }

    @NotNull
    public static final List<Short> sortedWith(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Short[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(sArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final Set<Byte> subtract(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    public static int sum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        return i;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Integer> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (byte b : bArr) {
            iIntValue += selector.invoke(Byte.valueOf(b)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (byte b : bArr) {
            dDoubleValue += selector.invoke(Byte.valueOf(b)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (byte b : bArr) {
            dDoubleValue += selector.invoke(Byte.valueOf(b)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(byte[] bArr, Function1<? super Byte, Integer> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (byte b : bArr) {
            iIntValue += selector.invoke(Byte.valueOf(b)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(byte[] bArr, Function1<? super Byte, Long> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (byte b : bArr) {
            jLongValue += selector.invoke(Byte.valueOf(b)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C toCollection(@NotNull byte[] bArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (byte b : bArr) {
            destination.add(Byte.valueOf(b));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Short> toHashSet(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        HashSet<Short> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(sArr.length));
        toCollection(sArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final Set<Short> toMutableSet(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(sArr.length));
        toCollection(sArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Set<Byte> union(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Short>> withIndex(@NotNull final short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(sArr);
            }
        });
    }

    public static final boolean all(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (!predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return !(iArr.length == 0);
    }

    @NotNull
    public static final Iterable<Byte> asIterable(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2(bArr);
    }

    @NotNull
    public static final Sequence<Byte> asSequence(@NotNull final byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Byte>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$2
            @Override // kotlin.sequences.Sequence
            public Iterator<Byte> iterator() {
                return ArrayIteratorsKt.iterator(bArr);
            }
        };
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Byte, V> associateWith(byte[] bArr, Function1<? super Byte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (byte b : bArr) {
            linkedHashMap.put(Byte.valueOf(b), valueSelector.invoke(Byte.valueOf(b)));
        }
        return linkedHashMap;
    }

    public static final double average(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (long j : jArr) {
            d += j;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @InlineOnly
    public static final int component1(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[0];
    }

    @InlineOnly
    public static final int component2(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[1];
    }

    @InlineOnly
    public static final int component3(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[2];
    }

    @InlineOnly
    public static final int component4(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[3];
    }

    @InlineOnly
    public static final int component5(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[4];
    }

    public static boolean contains(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return indexOf(iArr, i) >= 0;
    }

    @InlineOnly
    public static final int count(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length;
    }

    @NotNull
    public static final List<Integer> distinct(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(iArr));
    }

    @InlineOnly
    public static final int elementAtOrElse(int[] iArr, int i, Function1<? super Integer, Integer> defaultValue) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= iArr.length) ? defaultValue.invoke(Integer.valueOf(i)).intValue() : iArr[i];
    }

    @InlineOnly
    public static final Integer elementAtOrNull(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return getOrNull(iArr, i);
    }

    @NotNull
    public static final List<Byte> filterIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Byte.valueOf(b)).booleanValue()) {
                arrayList.add(Byte.valueOf(b));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterNotTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (!predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                destination.add(Integer.valueOf(i));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                destination.add(Integer.valueOf(i));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Integer find(int[] iArr, Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    @InlineOnly
    public static final Byte findLast(byte[] bArr, Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            byte b = bArr[length];
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return Byte.valueOf(b);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static byte first(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length != 0) {
            return bArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Integer firstOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(int[] iArr, Function2<? super Integer, ? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(byte[] bArr, C destination, Function2<? super Integer, ? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b : bArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Byte.valueOf(b)));
        }
        return destination;
    }

    public static final <R> R fold(@NotNull int[] iArr, R r, @NotNull Function2<? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int i : iArr) {
            r = operation.invoke(r, Integer.valueOf(i));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull int[] iArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Integer.valueOf(iArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final void forEach(@NotNull int[] iArr, @NotNull Function1<? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i : iArr) {
            action.invoke(Integer.valueOf(i));
        }
    }

    public static final void forEachIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i]));
            i++;
            i2++;
        }
    }

    @NotNull
    public static IntRange getIndices(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new IntRange(0, bArr.length - 1, 1);
    }

    public static int getLastIndex(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length - 1;
    }

    @InlineOnly
    public static final int getOrElse(int[] iArr, int i, Function1<? super Integer, Integer> defaultValue) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= iArr.length) ? defaultValue.invoke(Integer.valueOf(i)).intValue() : iArr[i];
    }

    @Nullable
    public static final Integer getOrNull(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i < 0 || i >= iArr.length) {
            return null;
        }
        return Integer.valueOf(iArr[i]);
    }

    @InlineOnly
    public static final boolean isEmpty(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return !(iArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(int[] iArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(iArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Integer, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull int[] iArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Integer, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(iArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(int[] iArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(iArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Integer, ? extends CharSequence>) function12);
    }

    @Nullable
    public static final Integer lastOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[iArr.length - 1]);
    }

    @NotNull
    public static final <R> List<R> map(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b : bArr) {
            arrayList.add(transform.invoke(Byte.valueOf(b)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(bArr.length);
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final boolean none(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int[] onEach(int[] iArr, Function1<? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i : iArr) {
            action.invoke(Integer.valueOf(i));
        }
        return iArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final int[] onEachIndexed(int[] iArr, Function2<? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i]));
            i++;
            i2++;
        }
        return iArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final int random(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return random(iArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Integer randomOrNull(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return randomOrNull(iArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceRightIndexedOrNull(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        byte bByteValue = bArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            bByteValue = operation.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i2]), Byte.valueOf(bByteValue)).byteValue();
        }
        return Byte.valueOf(bByteValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceRightOrNull(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        byte bByteValue = bArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            bByteValue = operation.invoke(Byte.valueOf(bArr[i2]), Byte.valueOf(bByteValue)).byteValue();
        }
        return Byte.valueOf(bByteValue);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(long[] jArr, R r, Function2<? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r);
        for (long j : jArr) {
            r = operation.invoke(r, Long.valueOf(j));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(long[] jArr, R r, Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r);
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Long.valueOf(jArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        shuffle(iArr, (Random) Random.Default);
    }

    @Nullable
    public static final Integer singleOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 1) {
            return Integer.valueOf(iArr[0]);
        }
        return null;
    }

    @NotNull
    public static byte[] sliceArray(@NotNull byte[] bArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        byte[] bArr2 = new byte[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            bArr2[i] = bArr[it.next().intValue()];
            i++;
        }
        return bArr2;
    }

    @NotNull
    public static final List<Integer> sorted(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Integer[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(iArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Integer> sortedBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iArr, (Comparator<? super Integer>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Integer> sortedByDescending(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iArr, (Comparator<? super Integer>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Integer> sortedDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(iArrCopyOf);
        return reversed(iArrCopyOf);
    }

    @NotNull
    public static final List<Integer> sortedWith(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Integer[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(iArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    public static long sum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long j = 0;
        for (long j2 : jArr) {
            j += j2;
        }
        return j;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(byte[] bArr, Function1<? super Byte, UInt> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (byte b : bArr) {
            i += selector.invoke(Byte.valueOf(b)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(byte[] bArr, Function1<? super Byte, ULong> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (byte b : bArr) {
            j += selector.invoke(Byte.valueOf(b)).data;
        }
        return j;
    }

    @NotNull
    public static final HashSet<Integer> toHashSet(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        HashSet<Integer> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(iArr.length));
        toCollection(iArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Short> toMutableList(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s : sArr) {
            arrayList.add(Short.valueOf(s));
        }
        return arrayList;
    }

    @NotNull
    public static final Set<Integer> toMutableSet(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(iArr.length));
        toCollection(iArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Integer>> withIndex(@NotNull final int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(iArr);
            }
        });
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull byte[] bArr, @NotNull R[] other, @NotNull Function2<? super Byte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final boolean all(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return !(jArr.length == 0);
    }

    @NotNull
    public static final <K> Map<K, Byte> associateBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (byte b : bArr) {
            linkedHashMap.put(keySelector.invoke(Byte.valueOf(b)), Byte.valueOf(b));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Short>> M associateByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (short s : sArr) {
            destination.put(keySelector.invoke(Short.valueOf(s)), Short.valueOf(s));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Short, ? super V>> M associateWithTo(short[] sArr, M destination, Function1<? super Short, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (short s : sArr) {
            destination.put(Short.valueOf(s), valueSelector.invoke(Short.valueOf(s)));
        }
        return destination;
    }

    public static final double average(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (float f : fArr) {
            d += (double) f;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @InlineOnly
    public static final long component1(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[0];
    }

    @InlineOnly
    public static final long component2(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[1];
    }

    @InlineOnly
    public static final long component3(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[2];
    }

    @InlineOnly
    public static final long component4(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[3];
    }

    @InlineOnly
    public static final long component5(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[4];
    }

    public static boolean contains(@NotNull long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return indexOf(jArr, j) >= 0;
    }

    @InlineOnly
    public static final int count(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }

    @NotNull
    public static final List<Long> distinct(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(jArr));
    }

    @NotNull
    public static final List<Byte> dropLastWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Byte.valueOf(bArr[length])).booleanValue());
        return take(bArr, length + 1);
    }

    @InlineOnly
    public static final long elementAtOrElse(long[] jArr, int i, Function1<? super Integer, Long> defaultValue) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= jArr.length) ? defaultValue.invoke(Integer.valueOf(i)).longValue() : jArr[i];
    }

    @InlineOnly
    public static final Long elementAtOrNull(long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return getOrNull(jArr, i);
    }

    @NotNull
    public static final List<Short> filter(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                arrayList.add(Short.valueOf(s));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterIndexedTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            short s = sArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Short.valueOf(s)).booleanValue()) {
                destination.add(Short.valueOf(s));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Short> filterNot(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : sArr) {
            if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
                arrayList.add(Short.valueOf(s));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterNotTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
                destination.add(Long.valueOf(j));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                destination.add(Long.valueOf(j));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Long find(long[] jArr, Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return Long.valueOf(j);
            }
        }
        return null;
    }

    @Nullable
    public static final Long firstOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[0]);
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Byte.valueOf(b)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(long[] jArr, Function2<? super Integer, ? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull long[] jArr, R r, @NotNull Function2<? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (long j : jArr) {
            r = operation.invoke(r, Long.valueOf(j));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull long[] jArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Long.valueOf(jArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull short[] sArr, R r, @NotNull Function2<? super Short, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = sArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Short.valueOf(sArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull short[] sArr, R r, @NotNull Function3<? super Integer, ? super Short, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = sArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Short.valueOf(sArr[length]), r);
        }
        return r;
    }

    public static final void forEach(@NotNull long[] jArr, @NotNull Function1<? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j : jArr) {
            action.invoke(Long.valueOf(j));
        }
    }

    public static final void forEachIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i]));
            i++;
            i2++;
        }
    }

    public static int getLastIndex(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length - 1;
    }

    @InlineOnly
    public static final long getOrElse(long[] jArr, int i, Function1<? super Integer, Long> defaultValue) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= jArr.length) ? defaultValue.invoke(Integer.valueOf(i)).longValue() : jArr[i];
    }

    @Nullable
    public static final Long getOrNull(@NotNull long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i < 0 || i >= jArr.length) {
            return null;
        }
        return Long.valueOf(jArr[i]);
    }

    public static final int indexOfFirst(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Short.valueOf(sArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Short.valueOf(sArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Short> intersect(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    public static final boolean isEmpty(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return !(jArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(long[] jArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(jArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Long, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull long[] jArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Long, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(jArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(long[] jArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(jArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Long, ? extends CharSequence>) function12);
    }

    public static byte last(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length != 0) {
            return bArr[bArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Long lastOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[jArr.length - 1]);
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s : sArr) {
            destination.add(transform.invoke(Short.valueOf(s)));
        }
        return destination;
    }

    public static final boolean none(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final long[] onEach(long[] jArr, Function1<? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j : jArr) {
            action.invoke(Long.valueOf(j));
        }
        return jArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final long[] onEachIndexed(long[] jArr, Function2<? super Integer, ? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i]));
            i++;
            i2++;
        }
        return jArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final long random(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return random(jArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Long randomOrNull(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return randomOrNull(jArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceIndexedOrNull(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return null;
        }
        byte bByteValue = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                bByteValue = operation.invoke(Integer.valueOf(i), Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(bByteValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceOrNull(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return null;
        }
        byte bByteValue = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                bByteValue = operation.invoke(Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(bByteValue);
    }

    public static final byte reduceRight(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i = length - 1;
        if (i >= 0) {
            byte bByteValue = bArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                bByteValue = operation.invoke(Byte.valueOf(bArr[i2]), Byte.valueOf(bByteValue)).byteValue();
            }
            return bByteValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final byte reduceRightIndexed(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i = length - 1;
        if (i >= 0) {
            byte bByteValue = bArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                bByteValue = operation.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i2]), Byte.valueOf(bByteValue)).byteValue();
            }
            return bByteValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Byte> reversed(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Byte> mutableList = toMutableList(bArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static byte[] reversedArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length];
        int length = bArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                bArr2[length - i] = bArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return bArr2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(float[] fArr, R r, Function2<? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r);
        for (float f : fArr) {
            r = operation.invoke(r, Float.valueOf(f));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(float[] fArr, R r, Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r);
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Float.valueOf(fArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        shuffle(jArr, (Random) Random.Default);
    }

    public static byte single(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return bArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @Nullable
    public static final Long singleOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 1) {
            return Long.valueOf(jArr[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.4")
    public static void sortDescending(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Arrays.sort(iArr, i, i2);
        reverse(iArr, i, i2);
    }

    @NotNull
    public static final List<Long> sorted(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Long[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(jArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final short[] sortedArray(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return sArr;
        }
        short[] sArrCopyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(sArrCopyOf);
        return sArrCopyOf;
    }

    @NotNull
    public static final short[] sortedArrayDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return sArr;
        }
        short[] sArrCopyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(...)");
        sortDescending(sArrCopyOf);
        return sArrCopyOf;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Long> sortedBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(jArr, (Comparator<? super Long>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Long> sortedByDescending(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(jArr, (Comparator<? super Long>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Long> sortedDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(jArrCopyOf);
        return reversed(jArrCopyOf);
    }

    @NotNull
    public static final List<Long> sortedWith(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Long[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(jArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final Set<Short> subtract(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    public static final float sum(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float f = 0.0f;
        for (float f2 : fArr) {
            f += f2;
        }
        return f;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull short[] sArr, @NotNull Function1<? super Short, Integer> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (short s : sArr) {
            iIntValue += selector.invoke(Short.valueOf(s)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull short[] sArr, @NotNull Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (short s : sArr) {
            dDoubleValue += selector.invoke(Short.valueOf(s)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (short s : sArr) {
            dDoubleValue += selector.invoke(Short.valueOf(s)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(short[] sArr, Function1<? super Short, Integer> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (short s : sArr) {
            iIntValue += selector.invoke(Short.valueOf(s)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(short[] sArr, Function1<? super Short, Long> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (short s : sArr) {
            jLongValue += selector.invoke(Short.valueOf(s)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final List<Byte> takeLastWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(bArr);
            }
        } while (predicate.invoke(Byte.valueOf(bArr[length])).booleanValue());
        return drop(bArr, length + 1);
    }

    @NotNull
    public static final List<Byte> takeWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                break;
            }
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C toCollection(@NotNull short[] sArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (short s : sArr) {
            destination.add(Short.valueOf(s));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Long> toHashSet(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        HashSet<Long> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(jArr.length));
        toCollection(jArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Byte> toList(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(bArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[0]));
    }

    @NotNull
    public static final Set<Long> toMutableSet(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(jArr.length));
        toCollection(jArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Set<Byte> toSet(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(bArr.length));
            toCollection(bArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Byte.valueOf(bArr[0]));
    }

    @NotNull
    public static final Set<Short> union(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Long>> withIndex(@NotNull final long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(jArr);
            }
        });
    }

    public static final boolean all(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return !(fArr.length == 0);
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b : bArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Byte.valueOf(b));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Short, V> associateWith(short[] sArr, Function1<? super Short, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (short s : sArr) {
            linkedHashMap.put(Short.valueOf(s), valueSelector.invoke(Short.valueOf(s)));
        }
        return linkedHashMap;
    }

    public static final double average(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d = 0.0d;
        int i = 0;
        for (double d2 : dArr) {
            d += d2;
            i++;
        }
        if (i == 0) {
            return Double.NaN;
        }
        return d / ((double) i);
    }

    @InlineOnly
    public static final float component1(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[0];
    }

    @InlineOnly
    public static final float component2(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[1];
    }

    @InlineOnly
    public static final float component3(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[2];
    }

    @InlineOnly
    public static final float component4(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[3];
    }

    @InlineOnly
    public static final float component5(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[4];
    }

    public static final boolean contains(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return indexOf(zArr, z) >= 0;
    }

    @InlineOnly
    public static final int count(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }

    @NotNull
    public static final List<Float> distinct(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(fArr));
    }

    @NotNull
    public static final List<Byte> dropWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (byte b : bArr) {
            if (z) {
                arrayList.add(Byte.valueOf(b));
            } else if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                arrayList.add(Byte.valueOf(b));
                z = true;
            }
        }
        return arrayList;
    }

    @InlineOnly
    public static final float elementAtOrElse(float[] fArr, int i, Function1<? super Integer, Float> defaultValue) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= fArr.length) ? defaultValue.invoke(Integer.valueOf(i)).floatValue() : fArr[i];
    }

    @InlineOnly
    public static final Float elementAtOrNull(float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return getOrNull(fArr, i);
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterNotTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                destination.add(Float.valueOf(f));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                destination.add(Float.valueOf(f));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Float find(float[] fArr, Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return Float.valueOf(f);
            }
        }
        return null;
    }

    @Nullable
    public static final Float firstOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(float[] fArr, Function2<? super Integer, ? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull float[] fArr, R r, @NotNull Function2<? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (float f : fArr) {
            r = operation.invoke(r, Float.valueOf(f));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull float[] fArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Float.valueOf(fArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final void forEach(@NotNull float[] fArr, @NotNull Function1<? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (float f : fArr) {
            action.invoke(Float.valueOf(f));
        }
    }

    public static final void forEachIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i]));
            i++;
            i2++;
        }
    }

    public static final int getLastIndex(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length - 1;
    }

    @InlineOnly
    public static final float getOrElse(float[] fArr, int i, Function1<? super Integer, Float> defaultValue) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= fArr.length) ? defaultValue.invoke(Integer.valueOf(i)).floatValue() : fArr[i];
    }

    @Nullable
    public static final Float getOrNull(@NotNull float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i < 0 || i >= fArr.length) {
            return null;
        }
        return Float.valueOf(fArr[i]);
    }

    public static int indexOf(@NotNull byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (b == bArr[i]) {
                return i;
            }
        }
        return -1;
    }

    @InlineOnly
    public static final boolean isEmpty(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return !(fArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(float[] fArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(fArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Float, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull float[] fArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Float, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(fArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(float[] fArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(fArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Float, ? extends CharSequence>) function12);
    }

    public static int lastIndexOf(@NotNull byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (b == bArr[length]) {
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

    @Nullable
    public static final Float lastOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[fArr.length - 1]);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte maxWithOrNull(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                if (comparator.compare(Byte.valueOf(b), Byte.valueOf(b2)) < 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte minWithOrNull(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                if (comparator.compare(Byte.valueOf(b), Byte.valueOf(b2)) > 0) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    public static final boolean none(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final float[] onEach(float[] fArr, Function1<? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (float f : fArr) {
            action.invoke(Float.valueOf(f));
        }
        return fArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final float[] onEachIndexed(float[] fArr, Function2<? super Integer, ? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i]));
            i++;
            i2++;
        }
        return fArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final float random(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return random(fArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Float randomOrNull(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return randomOrNull(fArr, (Random) Random.Default);
    }

    public static final byte reduce(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length != 0) {
            byte bByteValue = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    bByteValue = operation.invoke(Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return bByteValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final byte reduceIndexed(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length != 0) {
            byte bByteValue = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    bByteValue = operation.invoke(Integer.valueOf(i), Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return bByteValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static void reverse(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = (bArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = bArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            byte b = bArr[i];
            bArr[i] = bArr[length2];
            bArr[length2] = b;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(byte[] bArr, R r, Function2<? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r);
        for (byte b : bArr) {
            r = operation.invoke(r, Byte.valueOf(b));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(byte[] bArr, R r, Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Byte.valueOf(bArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(double[] dArr, R r, Function2<? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r);
        for (double d : dArr) {
            r = operation.invoke(r, Double.valueOf(d));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(double[] dArr, R r, Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r);
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Double.valueOf(dArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        shuffle(fArr, (Random) Random.Default);
    }

    @Nullable
    public static final Float singleOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 1) {
            return Float.valueOf(fArr[0]);
        }
        return null;
    }

    @NotNull
    public static final List<Byte> slice(@NotNull byte[] bArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(bArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static final List<Float> sorted(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Float[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(fArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Float> sortedBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(fArr, (Comparator<? super Float>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Float> sortedByDescending(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(fArr, (Comparator<? super Float>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Float> sortedDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(fArrCopyOf);
        return reversed(fArrCopyOf);
    }

    @NotNull
    public static final List<Float> sortedWith(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Float[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(fArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    public static final double sum(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2;
        }
        return d;
    }

    @NotNull
    public static final HashSet<Float> toHashSet(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        HashSet<Float> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(fArr.length));
        toCollection(fArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Integer> toMutableList(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    @NotNull
    public static final Set<Float> toMutableSet(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(fArr.length));
        toCollection(fArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Float>> withIndex(@NotNull final float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(fArr);
            }
        });
    }

    public static final boolean all(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return !(dArr.length == 0);
    }

    @NotNull
    public static final Iterable<Short> asIterable(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3(sArr);
    }

    @NotNull
    public static final Sequence<Short> asSequence(@NotNull final short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Short>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$3
            @Override // kotlin.sequences.Sequence
            public Iterator<Short> iterator() {
                return ArrayIteratorsKt.iterator(sArr);
            }
        };
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Integer>> M associateByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i : iArr) {
            destination.put(keySelector.invoke(Integer.valueOf(i)), Integer.valueOf(i));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Integer, ? super V>> M associateWithTo(int[] iArr, M destination, Function1<? super Integer, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (int i : iArr) {
            destination.put(Integer.valueOf(i), valueSelector.invoke(Integer.valueOf(i)));
        }
        return destination;
    }

    @InlineOnly
    public static final double component1(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[0];
    }

    @InlineOnly
    public static final double component2(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[1];
    }

    @InlineOnly
    public static final double component3(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[2];
    }

    @InlineOnly
    public static final double component4(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[3];
    }

    @InlineOnly
    public static final double component5(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[4];
    }

    public static boolean contains(@NotNull char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return indexOf(cArr, c) >= 0;
    }

    @InlineOnly
    public static final int count(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length;
    }

    @NotNull
    public static final List<Double> distinct(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(dArr));
    }

    @NotNull
    public static final <K> List<Byte> distinctBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            if (hashSet.add(selector.invoke(Byte.valueOf(b)))) {
                arrayList.add(Byte.valueOf(b));
            }
        }
        return arrayList;
    }

    @InlineOnly
    public static final double elementAtOrElse(double[] dArr, int i, Function1<? super Integer, Double> defaultValue) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= dArr.length) ? defaultValue.invoke(Integer.valueOf(i)).doubleValue() : dArr[i];
    }

    @InlineOnly
    public static final Double elementAtOrNull(double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return getOrNull(dArr, i);
    }

    @NotNull
    public static final List<Integer> filter(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> filterIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            short s = sArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Short.valueOf(s)).booleanValue()) {
                arrayList.add(Short.valueOf(s));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterIndexedTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Integer.valueOf(i3)).booleanValue()) {
                destination.add(Integer.valueOf(i3));
            }
            i++;
            i2 = i4;
        }
        return destination;
    }

    @NotNull
    public static final List<Integer> filterNot(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (!predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterNotTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                destination.add(Double.valueOf(d));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                destination.add(Double.valueOf(d));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Double find(double[] dArr, Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return Double.valueOf(d);
            }
        }
        return null;
    }

    @InlineOnly
    public static final Short findLast(short[] sArr, Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            short s = sArr[length];
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return Short.valueOf(s);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static short first(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length != 0) {
            return sArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Double firstOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(double[] dArr, Function2<? super Integer, ? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(short[] sArr, C destination, Function2<? super Integer, ? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s : sArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Short.valueOf(s)));
        }
        return destination;
    }

    public static final <R> R fold(@NotNull double[] dArr, R r, @NotNull Function2<? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (double d : dArr) {
            r = operation.invoke(r, Double.valueOf(d));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull double[] dArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Double.valueOf(dArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull int[] iArr, R r, @NotNull Function2<? super Integer, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = iArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(iArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull int[] iArr, R r, @NotNull Function3<? super Integer, ? super Integer, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = iArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Integer.valueOf(iArr[length]), r);
        }
        return r;
    }

    public static final void forEach(@NotNull double[] dArr, @NotNull Function1<? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (double d : dArr) {
            action.invoke(Double.valueOf(d));
        }
    }

    public static final void forEachIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i]));
            i++;
            i2++;
        }
    }

    @NotNull
    public static IntRange getIndices(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return new IntRange(0, sArr.length - 1, 1);
    }

    public static final int getLastIndex(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length - 1;
    }

    @InlineOnly
    public static final double getOrElse(double[] dArr, int i, Function1<? super Integer, Double> defaultValue) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= dArr.length) ? defaultValue.invoke(Integer.valueOf(i)).doubleValue() : dArr[i];
    }

    @Nullable
    public static final Double getOrNull(@NotNull double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i < 0 || i >= dArr.length) {
            return null;
        }
        return Double.valueOf(dArr[i]);
    }

    public static final int indexOfFirst(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Integer.valueOf(iArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Integer.valueOf(iArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Integer> intersect(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    public static final boolean isEmpty(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return !(dArr.length == 0);
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull byte[] bArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Byte, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (byte b : bArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Byte.valueOf(b)));
            } else {
                buffer.append(String.valueOf((int) b));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ Appendable joinTo$default(double[] dArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(dArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Double, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull double[] dArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Double, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(dArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(double[] dArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(dArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Double, ? extends CharSequence>) function12);
    }

    @Nullable
    public static final Double lastOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[dArr.length - 1]);
    }

    @NotNull
    public static final <R> List<R> map(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s : sArr) {
            arrayList.add(transform.invoke(Short.valueOf(s)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(sArr.length);
        int length = sArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i : iArr) {
            destination.add(transform.invoke(Integer.valueOf(i)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            double dDoubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Byte.valueOf(bArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m957maxOfOrNull(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Byte.valueOf(bArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxOrNull(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = fArr[0].floatValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, fArr[i].floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final float maxOrThrow(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            float fFloatValue = fArr[0].floatValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, fArr[i].floatValue());
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
    @JvmName(name = "maxWithOrThrow")
    public static final byte maxWithOrThrow(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    byte b2 = bArr[i];
                    if (comparator.compare(Byte.valueOf(b), Byte.valueOf(b2)) < 0) {
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
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            double dDoubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Byte.valueOf(bArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m993minOfOrNull(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Byte.valueOf(bArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minOrNull(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = fArr[0].floatValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, fArr[i].floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final float minOrThrow(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            float fFloatValue = fArr[0].floatValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, fArr[i].floatValue());
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
    @JvmName(name = "minWithOrThrow")
    public static final byte minWithOrThrow(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    byte b2 = bArr[i];
                    if (comparator.compare(Byte.valueOf(b), Byte.valueOf(b2)) > 0) {
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
        throw new NoSuchElementException();
    }

    public static final boolean none(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final double[] onEach(double[] dArr, Function1<? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (double d : dArr) {
            action.invoke(Double.valueOf(d));
        }
        return dArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final double[] onEachIndexed(double[] dArr, Function2<? super Integer, ? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i]));
            i++;
            i2++;
        }
        return dArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final double random(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return random(dArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Double randomOrNull(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return randomOrNull(dArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(boolean[] zArr, R r, Function2<? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r);
        for (boolean z : zArr) {
            r = operation.invoke(r, Boolean.valueOf(z));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(boolean[] zArr, R r, Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r);
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Boolean.valueOf(zArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        shuffle(dArr, Random.Default);
    }

    @Nullable
    public static final Double singleOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 1) {
            return Double.valueOf(dArr[0]);
        }
        return null;
    }

    @NotNull
    public static short[] sliceArray(@NotNull short[] sArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        short[] sArr2 = new short[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            sArr2[i] = sArr[it.next().intValue()];
            i++;
        }
        return sArr2;
    }

    @SinceKotlin(version = "1.4")
    public static void sortDescending(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Arrays.sort(jArr, i, i2);
        reverse(jArr, i, i2);
    }

    @NotNull
    public static final List<Double> sorted(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Double[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(dArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final int[] sortedArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return iArr;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(iArrCopyOf);
        return iArrCopyOf;
    }

    @NotNull
    public static final int[] sortedArrayDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return iArr;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        sortDescending(iArrCopyOf);
        return iArrCopyOf;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Double> sortedBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(dArr, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Double> sortedByDescending(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(dArr, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Double> sortedDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double[] dArrCopyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(dArrCopyOf);
        return reversed(dArrCopyOf);
    }

    @NotNull
    public static final List<Double> sortedWith(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Double[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(dArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final Set<Integer> subtract(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, Integer> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (int i : iArr) {
            iIntValue += selector.invoke(Integer.valueOf(i)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull int[] iArr, @NotNull Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i : iArr) {
            dDoubleValue += selector.invoke(Integer.valueOf(i)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (int i : iArr) {
            dDoubleValue += selector.invoke(Integer.valueOf(i)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(int[] iArr, Function1<? super Integer, Integer> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (int i : iArr) {
            iIntValue += selector.invoke(Integer.valueOf(i)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(int[] iArr, Function1<? super Integer, Long> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (int i : iArr) {
            jLongValue += selector.invoke(Integer.valueOf(i)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(short[] sArr, Function1<? super Short, UInt> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (short s : sArr) {
            i += selector.invoke(Short.valueOf(s)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(short[] sArr, Function1<? super Short, ULong> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (short s : sArr) {
            j += selector.invoke(Short.valueOf(s)).data;
        }
        return j;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C toCollection(@NotNull int[] iArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (int i : iArr) {
            destination.add(Integer.valueOf(i));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Double> toHashSet(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        HashSet<Double> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(dArr.length));
        toCollection(dArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final Set<Double> toMutableSet(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(dArr.length));
        toCollection(dArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Set<Integer> union(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Double>> withIndex(@NotNull final double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(dArr);
            }
        });
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull short[] sArr, @NotNull R[] other, @NotNull Function2<? super Short, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Short.valueOf(sArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final boolean all(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return !(zArr.length == 0);
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (byte b : bArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Byte.valueOf(b));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Integer, V> associateWith(int[] iArr, Function1<? super Integer, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i : iArr) {
            linkedHashMap.put(Integer.valueOf(i), valueSelector.invoke(Integer.valueOf(i)));
        }
        return linkedHashMap;
    }

    @InlineOnly
    public static final boolean component1(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[0];
    }

    @InlineOnly
    public static final boolean component2(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[1];
    }

    @InlineOnly
    public static final boolean component3(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[2];
    }

    @InlineOnly
    public static final boolean component4(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[3];
    }

    @InlineOnly
    public static final boolean component5(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[4];
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'any { it == element }' instead to continue using this behavior, or '.asList().contains(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "any { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ boolean contains(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        for (float f2 : fArr) {
            if (f2 == f) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    public static final int count(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length;
    }

    @NotNull
    public static final List<Boolean> distinct(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(zArr));
    }

    @InlineOnly
    public static final boolean elementAtOrElse(boolean[] zArr, int i, Function1<? super Integer, Boolean> defaultValue) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= zArr.length) ? defaultValue.invoke(Integer.valueOf(i)).booleanValue() : zArr[i];
    }

    @InlineOnly
    public static final Boolean elementAtOrNull(boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return getOrNull(zArr, i);
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterNotTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Boolean find(boolean[] zArr, Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
        }
        return null;
    }

    @Nullable
    public static final Boolean firstOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[0]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(boolean[] zArr, Function2<? super Integer, ? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull boolean[] zArr, R r, @NotNull Function2<? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (boolean z : zArr) {
            r = operation.invoke(r, Boolean.valueOf(z));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull boolean[] zArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Boolean.valueOf(zArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final void forEach(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (boolean z : zArr) {
            action.invoke(Boolean.valueOf(z));
        }
    }

    public static final void forEachIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i]));
            i++;
            i2++;
        }
    }

    public static final int getLastIndex(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length - 1;
    }

    @InlineOnly
    public static final boolean getOrElse(boolean[] zArr, int i, Function1<? super Integer, Boolean> defaultValue) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= zArr.length) ? defaultValue.invoke(Integer.valueOf(i)).booleanValue() : zArr[i];
    }

    @Nullable
    public static final Boolean getOrNull(@NotNull boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i < 0 || i >= zArr.length) {
            return null;
        }
        return Boolean.valueOf(zArr[i]);
    }

    public static int indexOf(@NotNull short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            if (s == sArr[i]) {
                return i;
            }
        }
        return -1;
    }

    @InlineOnly
    public static final boolean isEmpty(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return !(zArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(boolean[] zArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(zArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Boolean, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull boolean[] zArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Boolean, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(zArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(boolean[] zArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(zArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Boolean, ? extends CharSequence>) function12);
    }

    public static int lastIndexOf(@NotNull short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (s == sArr[length]) {
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

    @Nullable
    public static final Boolean lastOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[zArr.length - 1]);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Byte maxByOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Byte minByOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
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

    public static final boolean none(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean[] onEach(boolean[] zArr, Function1<? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (boolean z : zArr) {
            action.invoke(Boolean.valueOf(z));
        }
        return zArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final boolean[] onEachIndexed(boolean[] zArr, Function2<? super Integer, ? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i]));
            i++;
            i2++;
        }
        return zArr;
    }

    @NotNull
    public static final Pair<List<Byte>, List<Byte>> partition(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                arrayList.add(Byte.valueOf(b));
            } else {
                arrayList2.add(Byte.valueOf(b));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final boolean random(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return random(zArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Boolean randomOrNull(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return randomOrNull(zArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceRightIndexedOrNull(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        short sShortValue = sArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sShortValue = operation.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i2]), Short.valueOf(sShortValue)).shortValue();
        }
        return Short.valueOf(sShortValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceRightOrNull(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        short sShortValue = sArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            sShortValue = operation.invoke(Short.valueOf(sArr[i2]), Short.valueOf(sShortValue)).shortValue();
        }
        return Short.valueOf(sShortValue);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Byte> runningReduce(byte[] bArr, Function2<? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        byte bByteValue = bArr[0];
        ArrayList arrayList = new ArrayList(bArr.length);
        arrayList.add(Byte.valueOf(bByteValue));
        int length = bArr.length;
        for (int i = 1; i < length; i++) {
            bByteValue = operation.invoke(Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
            arrayList.add(Byte.valueOf(bByteValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Byte> runningReduceIndexed(byte[] bArr, Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        byte bByteValue = bArr[0];
        ArrayList arrayList = new ArrayList(bArr.length);
        arrayList.add(Byte.valueOf(bByteValue));
        int length = bArr.length;
        for (int i = 1; i < length; i++) {
            bByteValue = operation.invoke(Integer.valueOf(i), Byte.valueOf(bByteValue), Byte.valueOf(bArr[i])).byteValue();
            arrayList.add(Byte.valueOf(bByteValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scan(char[] cArr, R r, Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r);
        for (char c : cArr) {
            r = operation.invoke(r, Character.valueOf(c));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> scanIndexed(char[] cArr, R r, Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r);
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Character.valueOf(cArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        shuffle(zArr, Random.Default);
    }

    @Nullable
    public static final Boolean singleOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 1) {
            return Boolean.valueOf(zArr[0]);
        }
        return null;
    }

    @NotNull
    public static final List<Character> sorted(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Character[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(cArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Boolean> sortedBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(zArr, new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Boolean> sortedByDescending(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(zArr, new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Character> sortedDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        char[] cArrCopyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(cArrCopyOf);
        return reversed(cArrCopyOf);
    }

    @NotNull
    public static final List<Boolean> sortedWith(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Boolean[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(zArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final HashSet<Boolean> toHashSet(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        HashSet<Boolean> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(zArr.length));
        toCollection(zArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Long> toMutableList(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j : jArr) {
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    @NotNull
    public static final Set<Boolean> toMutableSet(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(zArr.length));
        toCollection(zArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Boolean>> withIndex(@NotNull final boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(zArr);
            }
        });
    }

    public static final boolean all(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return !(cArr.length == 0);
    }

    @NotNull
    public static final <K> Map<K, Short> associateBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (short s : sArr) {
            linkedHashMap.put(keySelector.invoke(Short.valueOf(s)), Short.valueOf(s));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Long>> M associateByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (long j : jArr) {
            destination.put(keySelector.invoke(Long.valueOf(j)), Long.valueOf(j));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Long, ? super V>> M associateWithTo(long[] jArr, M destination, Function1<? super Long, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (long j : jArr) {
            destination.put(Long.valueOf(j), valueSelector.invoke(Long.valueOf(j)));
        }
        return destination;
    }

    @InlineOnly
    public static final char component1(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[0];
    }

    @InlineOnly
    public static final char component2(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[1];
    }

    @InlineOnly
    public static final char component3(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[2];
    }

    @InlineOnly
    public static final char component4(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[3];
    }

    @InlineOnly
    public static final char component5(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[4];
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'any { it == element }' instead to continue using this behavior, or '.asList().contains(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "any { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ boolean contains(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        for (double d2 : dArr) {
            if (d2 == d) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    public static final int count(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length;
    }

    @NotNull
    public static final List<Character> distinct(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return CollectionsKt___CollectionsKt.toList(toMutableSet(cArr));
    }

    @NotNull
    public static final List<Short> dropLastWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Short.valueOf(sArr[length])).booleanValue());
        return take(sArr, length + 1);
    }

    @InlineOnly
    public static final char elementAtOrElse(char[] cArr, int i, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= cArr.length) ? defaultValue.invoke(Integer.valueOf(i)).charValue() : cArr[i];
    }

    @InlineOnly
    public static final Character elementAtOrNull(char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return getOrNull(cArr, i);
    }

    @NotNull
    public static final List<Long> filter(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                arrayList.add(Long.valueOf(j));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterIndexedTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            long j = jArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Long.valueOf(j)).booleanValue()) {
                destination.add(Long.valueOf(j));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Long> filterNot(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
                arrayList.add(Long.valueOf(j));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterNotTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                destination.add(Character.valueOf(c));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                destination.add(Character.valueOf(c));
            }
        }
        return destination;
    }

    @InlineOnly
    public static final Character find(char[] cArr, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return Character.valueOf(c);
            }
        }
        return null;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[0]);
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (short s : sArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Short.valueOf(s)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    public static final <R> List<R> flatMapIndexedIterable(char[] cArr, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <R> R fold(@NotNull char[] cArr, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (char c : cArr) {
            r = operation.invoke(r, Character.valueOf(c));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull char[] cArr, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            R r2 = r;
            r = operation.invoke(Integer.valueOf(i2), r2, Character.valueOf(cArr[i]));
            i++;
            i2++;
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull long[] jArr, R r, @NotNull Function2<? super Long, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = jArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Long.valueOf(jArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull long[] jArr, R r, @NotNull Function3<? super Integer, ? super Long, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = jArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Long.valueOf(jArr[length]), r);
        }
        return r;
    }

    public static final void forEach(@NotNull char[] cArr, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (char c : cArr) {
            action.invoke(Character.valueOf(c));
        }
    }

    public static final void forEachIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i]));
            i++;
            i2++;
        }
    }

    public static final int getLastIndex(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length - 1;
    }

    @InlineOnly
    public static final char getOrElse(char[] cArr, int i, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i < 0 || i >= cArr.length) ? defaultValue.invoke(Integer.valueOf(i)).charValue() : cArr[i];
    }

    @Nullable
    public static final Character getOrNull(@NotNull char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i < 0 || i >= cArr.length) {
            return null;
        }
        return Character.valueOf(cArr[i]);
    }

    public static final int indexOfFirst(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Long.valueOf(jArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Long.valueOf(jArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Long> intersect(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    public static final boolean isEmpty(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length == 0;
    }

    @InlineOnly
    public static final boolean isNotEmpty(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return !(cArr.length == 0);
    }

    public static /* synthetic */ Appendable joinTo$default(char[] cArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) throws IOException {
        joinTo(cArr, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? "" : charSequence2, (i2 & 8) == 0 ? charSequence3 : "", (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (Function1<? super Character, ? extends CharSequence>) ((i2 & 64) != 0 ? null : function1));
        return appendable;
    }

    @NotNull
    public static final String joinToString(@NotNull char[] cArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Character, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        joinTo(cArr, sb, separator, prefix, postfix, i, truncated, function1);
        return sb.toString();
    }

    public static /* synthetic */ String joinToString$default(char[] cArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function12 = function1;
        return joinToString(cArr, charSequence, charSequence2, charSequence3, i, charSequence5, (Function1<? super Character, ? extends CharSequence>) function12);
    }

    public static short last(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length != 0) {
            return sArr[sArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[cArr.length - 1]);
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j : jArr) {
            destination.add(transform.invoke(Long.valueOf(j)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> byte maxByOrThrow(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (length != 0) {
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
            }
            return b;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> byte minByOrThrow(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (length != 0) {
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
            }
            return b;
        }
        throw new NoSuchElementException();
    }

    public static final boolean none(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length == 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final char[] onEach(char[] cArr, Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (char c : cArr) {
            action.invoke(Character.valueOf(c));
        }
        return cArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final char[] onEachIndexed(char[] cArr, Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            action.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i]));
            i++;
            i2++;
        }
        return cArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final char random(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return random(cArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final Character randomOrNull(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return randomOrNull(cArr, (Random) Random.Default);
    }

    public static final short reduceRight(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i = length - 1;
        if (i >= 0) {
            short sShortValue = sArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                sShortValue = operation.invoke(Short.valueOf(sArr[i2]), Short.valueOf(sShortValue)).shortValue();
            }
            return sShortValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final short reduceRightIndexed(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i = length - 1;
        if (i >= 0) {
            short sShortValue = sArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                sShortValue = operation.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i2]), Short.valueOf(sShortValue)).shortValue();
            }
            return sShortValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Short> reversed(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Short> mutableList = toMutableList(sArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static short[] reversedArray(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return sArr;
        }
        short[] sArr2 = new short[sArr.length];
        int length = sArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                sArr2[length - i] = sArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> scan(@NotNull T[] tArr, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r);
        for (R.bool boolVar : tArr) {
            r = operation.invoke(r, boolVar);
            arrayList.add(r);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> scanIndexed(@NotNull T[] tArr, R r, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r);
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, tArr[i]);
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        shuffle(cArr, (Random) Random.Default);
    }

    public static short single(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return sArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @Nullable
    public static final Character singleOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 1) {
            return Character.valueOf(cArr[0]);
        }
        return null;
    }

    @SinceKotlin(version = "1.4")
    public static void sortDescending(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Arrays.sort(fArr, i, i2);
        reverse(fArr, i, i2);
    }

    @NotNull
    public static final long[] sortedArray(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return jArr;
        }
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(jArrCopyOf);
        return jArrCopyOf;
    }

    @NotNull
    public static final long[] sortedArrayDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return jArr;
        }
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        sortDescending(jArrCopyOf);
        return jArrCopyOf;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Character> sortedBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(cArr, (Comparator<? super Character>) new ComparisonsKt__ComparisonsKt.AnonymousClass2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Character> sortedByDescending(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(cArr, (Comparator<? super Character>) new ComparisonsKt__ComparisonsKt.AnonymousClass1(selector));
    }

    @NotNull
    public static final List<Character> sortedWith(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Character[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(cArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        return ArraysKt___ArraysJvmKt.asList(typedArray);
    }

    @NotNull
    public static final Set<Long> subtract(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull long[] jArr, @NotNull Function1<? super Long, Integer> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (long j : jArr) {
            iIntValue += selector.invoke(Long.valueOf(j)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull long[] jArr, @NotNull Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (long j : jArr) {
            dDoubleValue += selector.invoke(Long.valueOf(j)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (long j : jArr) {
            dDoubleValue += selector.invoke(Long.valueOf(j)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(long[] jArr, Function1<? super Long, Integer> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (long j : jArr) {
            iIntValue += selector.invoke(Long.valueOf(j)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(long[] jArr, Function1<? super Long, Long> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (long j : jArr) {
            jLongValue += selector.invoke(Long.valueOf(j)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final List<Short> takeLastWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(sArr);
            }
        } while (predicate.invoke(Short.valueOf(sArr[length])).booleanValue());
        return drop(sArr, length + 1);
    }

    @NotNull
    public static final List<Short> takeWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s : sArr) {
            if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
                break;
            }
            arrayList.add(Short.valueOf(s));
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C toCollection(@NotNull long[] jArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (long j : jArr) {
            destination.add(Long.valueOf(j));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length > 128) {
            length = 128;
        }
        HashSet<Character> hashSet = new HashSet<>(MapsKt__MapsJVMKt.mapCapacity(length));
        toCollection(cArr, hashSet);
        return hashSet;
    }

    @NotNull
    public static final List<Short> toList(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(sArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[0]));
    }

    @NotNull
    public static final Set<Character> toMutableSet(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length > 128) {
            length = 128;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(length));
        toCollection(cArr, linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final Set<Short> toSet(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(sArr.length));
            toCollection(sArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Short.valueOf(sArr[0]));
    }

    @NotNull
    public static final Set<Long> union(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull final char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ArrayIteratorsKt.iterator(cArr);
            }
        });
    }

    public static final <T> boolean any(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final Iterable<Integer> asIterable(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4(iArr);
    }

    @NotNull
    public static final Sequence<Integer> asSequence(@NotNull final int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Integer>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$4
            @Override // kotlin.sequences.Sequence
            public Iterator<Integer> iterator() {
                return ArrayIteratorsKt.iterator(iArr);
            }
        };
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Long, V> associateWith(long[] jArr, Function1<? super Long, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (long j : jArr) {
            linkedHashMap.put(Long.valueOf(j), valueSelector.invoke(Long.valueOf(j)));
        }
        return linkedHashMap;
    }

    public static final <T> int count(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final List<Byte> drop(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i >= 0) {
            int length = bArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(bArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Byte> dropLast(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i >= 0) {
            int length = bArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(bArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Integer> filterIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Integer.valueOf(i3)).booleanValue()) {
                arrayList.add(Integer.valueOf(i3));
            }
            i++;
            i2 = i4;
        }
        return arrayList;
    }

    @InlineOnly
    public static final Integer findLast(int[] iArr, Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            int i2 = iArr[length];
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return Integer.valueOf(i2);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static int first(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length != 0) {
            return iArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final <T> T firstOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(int[] iArr, C destination, Function2<? super Integer, ? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i : iArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i)));
        }
        return destination;
    }

    @NotNull
    public static IntRange getIndices(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return new IntRange(0, iArr.length - 1, 1);
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Byte>>> M groupByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (byte b : bArr) {
            K kInvoke = keySelector.invoke(Byte.valueOf(b));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Byte.valueOf(b));
        }
        return destination;
    }

    public static int indexOf(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static int lastIndexOf(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (i == iArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            T t = tArr[length];
            if (predicate.invoke(t).booleanValue()) {
                return t;
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(transform.invoke(Integer.valueOf(i)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    public static final <T> boolean none(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    public static final <T> T random(@NotNull T[] tArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (tArr.length != 0) {
            return tArr[random.nextInt(tArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T randomOrNull(@NotNull T[] tArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[random.nextInt(tArr.length)];
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceIndexedOrNull(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return null;
        }
        short sShortValue = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                sShortValue = operation.invoke(Integer.valueOf(i), Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(sShortValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceOrNull(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return null;
        }
        short sShortValue = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                sShortValue = operation.invoke(Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(sShortValue);
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void shuffle(@NotNull T[] tArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = tArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            T t = tArr[length];
            tArr[length] = tArr[iNextInt];
            tArr[iNextInt] = t;
        }
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        T t = null;
        boolean z = false;
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                if (z) {
                    return null;
                }
                z = true;
                t = t2;
            }
        }
        if (z) {
            return t;
        }
        return null;
    }

    @NotNull
    public static int[] sliceArray(@NotNull int[] iArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int[] iArr2 = new int[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr2[i] = iArr[it.next().intValue()];
            i++;
        }
        return iArr2;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(int[] iArr, Function1<? super Integer, UInt> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (int i2 : iArr) {
            i += selector.invoke(Integer.valueOf(i2)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(int[] iArr, Function1<? super Integer, ULong> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (int i : iArr) {
            j += selector.invoke(Integer.valueOf(i)).data;
        }
        return j;
    }

    @NotNull
    public static final List<Float> toMutableList(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull int[] iArr, @NotNull R[] other, @NotNull Function2<? super Integer, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Float>> M associateByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (float f : fArr) {
            destination.put(keySelector.invoke(Float.valueOf(f)), Float.valueOf(f));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s : sArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Short.valueOf(s));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Float, ? super V>> M associateWithTo(float[] fArr, M destination, Function1<? super Float, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (float f : fArr) {
            destination.put(Float.valueOf(f), valueSelector.invoke(Float.valueOf(f)));
        }
        return destination;
    }

    public static final int count(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final List<Short> dropWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (short s : sArr) {
            if (z) {
                arrayList.add(Short.valueOf(s));
            } else if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
                arrayList.add(Short.valueOf(s));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> filter(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                arrayList.add(Float.valueOf(f));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterIndexedTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            float f = fArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Float.valueOf(f)).booleanValue()) {
                destination.add(Float.valueOf(f));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Float> filterNot(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f : fArr) {
            if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                arrayList.add(Float.valueOf(f));
            }
        }
        return arrayList;
    }

    @Nullable
    public static final Byte firstOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return Byte.valueOf(b);
            }
        }
        return null;
    }

    public static final <R> R foldRight(@NotNull float[] fArr, R r, @NotNull Function2<? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = fArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Float.valueOf(fArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull float[] fArr, R r, @NotNull Function3<? super Integer, ? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = fArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Float.valueOf(fArr[length]), r);
        }
        return r;
    }

    @NotNull
    public static final <K> Map<K, List<Byte>> groupBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b : bArr) {
            K kInvoke = keySelector.invoke(Byte.valueOf(b));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Byte.valueOf(b));
        }
        return linkedHashMap;
    }

    public static final int indexOfFirst(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Float.valueOf(fArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Float.valueOf(fArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Float> intersect(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f : fArr) {
            destination.add(transform.invoke(Float.valueOf(f)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final boolean none(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final short reduce(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length != 0) {
            short sShortValue = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    sShortValue = operation.invoke(Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return sShortValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final short reduceIndexed(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length != 0) {
            short sShortValue = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    sShortValue = operation.invoke(Integer.valueOf(i), Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return sShortValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static void reverse(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = (sArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = sArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            short s = sArr[i];
            sArr[i] = sArr[length2];
            sArr[length2] = s;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(short[] sArr, R r, Function2<? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r);
        for (short s : sArr) {
            r = operation.invoke(r, Short.valueOf(s));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(short[] sArr, R r, Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r);
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Short.valueOf(sArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> slice(@NotNull short[] sArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(sArr, indices.first, indices.last + 1));
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Arrays.sort(dArr, i, i2);
        reverse(dArr, i, i2);
    }

    @NotNull
    public static final float[] sortedArray(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return fArr;
        }
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(fArrCopyOf);
        return fArrCopyOf;
    }

    @NotNull
    public static final float[] sortedArrayDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return fArr;
        }
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        sortDescending(fArrCopyOf);
        return fArrCopyOf;
    }

    @NotNull
    public static final Set<Float> subtract(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull float[] fArr, @NotNull Function1<? super Float, Integer> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (float f : fArr) {
            iIntValue += selector.invoke(Float.valueOf(f)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull float[] fArr, @NotNull Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (float f : fArr) {
            dDoubleValue += selector.invoke(Float.valueOf(f)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (float f : fArr) {
            dDoubleValue += selector.invoke(Float.valueOf(f)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(float[] fArr, Function1<? super Float, Integer> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (float f : fArr) {
            iIntValue += selector.invoke(Float.valueOf(f)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(float[] fArr, Function1<? super Float, Long> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (float f : fArr) {
            jLongValue += selector.invoke(Float.valueOf(f)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C toCollection(@NotNull float[] fArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (float f : fArr) {
            destination.add(Float.valueOf(f));
        }
        return destination;
    }

    @NotNull
    public static final Set<Float> union(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    public static final boolean any(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Float, V> associateWith(float[] fArr, Function1<? super Float, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (float f : fArr) {
            linkedHashMap.put(Float.valueOf(f), valueSelector.invoke(Float.valueOf(f)));
        }
        return linkedHashMap;
    }

    public static final int count(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @Nullable
    public static final Short firstOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return Short.valueOf(s);
            }
        }
        return null;
    }

    public static int indexOf(@NotNull long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            if (j == jArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(@NotNull long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (j == jArr[length]) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short maxWithOrNull(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                if (comparator.compare(Short.valueOf(s), Short.valueOf(s2)) < 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short minWithOrNull(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                if (comparator.compare(Short.valueOf(s), Short.valueOf(s2)) > 0) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    public static final boolean none(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte randomOrNull(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[random.nextInt(bArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceRightIndexedOrNull(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        int iIntValue = iArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            iIntValue = operation.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i2]), Integer.valueOf(iIntValue)).intValue();
        }
        return Integer.valueOf(iIntValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceRightOrNull(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        int iIntValue = iArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            iIntValue = operation.invoke(Integer.valueOf(iArr[i2]), Integer.valueOf(iIntValue)).intValue();
        }
        return Integer.valueOf(iIntValue);
    }

    @Nullable
    public static final Byte singleOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Byte bValueOf = null;
        boolean z = false;
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                if (z) {
                    return null;
                }
                bValueOf = Byte.valueOf(b);
                z = true;
            }
        }
        if (z) {
            return bValueOf;
        }
        return null;
    }

    @NotNull
    public static final List<Double> toMutableList(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(Double.valueOf(d));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final Iterable<Long> asIterable(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$5(jArr);
    }

    @NotNull
    public static final Sequence<Long> asSequence(@NotNull final long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Long>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$5
            @Override // kotlin.sequences.Sequence
            public Iterator<Long> iterator() {
                return ArrayIteratorsKt.iterator(jArr);
            }
        };
    }

    @NotNull
    public static final <K> Map<K, Integer> associateBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i : iArr) {
            linkedHashMap.put(keySelector.invoke(Integer.valueOf(i)), Integer.valueOf(i));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Double>> M associateByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (double d : dArr) {
            destination.put(keySelector.invoke(Double.valueOf(d)), Double.valueOf(d));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Double, ? super V>> M associateWithTo(double[] dArr, M destination, Function1<? super Double, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (double d : dArr) {
            destination.put(Double.valueOf(d), valueSelector.invoke(Double.valueOf(d)));
        }
        return destination;
    }

    public static final int count(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final <K> List<Short> distinctBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (short s : sArr) {
            if (hashSet.add(selector.invoke(Short.valueOf(s)))) {
                arrayList.add(Short.valueOf(s));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> dropLastWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Integer.valueOf(iArr[length])).booleanValue());
        return take(iArr, length + 1);
    }

    @NotNull
    public static final List<Double> filter(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                arrayList.add(Double.valueOf(d));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> filterIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            long j = jArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Long.valueOf(j)).booleanValue()) {
                arrayList.add(Long.valueOf(j));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterIndexedTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            double d = dArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Double.valueOf(d)).booleanValue()) {
                destination.add(Double.valueOf(d));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Double> filterNot(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d : dArr) {
            if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                arrayList.add(Double.valueOf(d));
            }
        }
        return arrayList;
    }

    @InlineOnly
    public static final Long findLast(long[] jArr, Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            long j = jArr[length];
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return Long.valueOf(j);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static long first(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length != 0) {
            return jArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Integer firstOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(long[] jArr, C destination, Function2<? super Integer, ? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j : jArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Long.valueOf(j)));
        }
        return destination;
    }

    public static final <R> R foldRight(@NotNull double[] dArr, R r, @NotNull Function2<? super Double, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = dArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Double.valueOf(dArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull double[] dArr, R r, @NotNull Function3<? super Integer, ? super Double, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = dArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Double.valueOf(dArr[length]), r);
        }
        return r;
    }

    @NotNull
    public static IntRange getIndices(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return new IntRange(0, jArr.length - 1, 1);
    }

    public static final int indexOfFirst(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Double.valueOf(dArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Double.valueOf(dArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Double> intersect(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    public static int last(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length != 0) {
            return iArr[iArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Byte lastOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            byte b = bArr[length];
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return Byte.valueOf(b);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j : jArr) {
            arrayList.add(transform.invoke(Long.valueOf(j)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(jArr.length);
        int length = jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d : dArr) {
            destination.add(transform.invoke(Double.valueOf(d)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            double dDoubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Short.valueOf(sArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m964maxOfOrNull(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Short.valueOf(sArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T maxOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (t.compareTo(t2) < 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOrThrow(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length != 0) {
            T t = tArr[0];
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    T t2 = tArr[i];
                    if (t.compareTo(t2) < 0) {
                        t = t2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return t;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final short maxWithOrThrow(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = sArr[i];
                    if (comparator.compare(Short.valueOf(s), Short.valueOf(s2)) < 0) {
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            double dDoubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Short.valueOf(sArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m1000minOfOrNull(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Short.valueOf(sArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T minOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        T t = tArr[0];
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                T t2 = tArr[i];
                if (t.compareTo(t2) > 0) {
                    t = t2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return t;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOrThrow(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length != 0) {
            T t = tArr[0];
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    T t2 = tArr[i];
                    if (t.compareTo(t2) > 0) {
                        t = t2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return t;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final short minWithOrThrow(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = sArr[i];
                    if (comparator.compare(Short.valueOf(s), Short.valueOf(s2)) > 0) {
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

    public static final boolean none(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    public static final byte random(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (bArr.length != 0) {
            return bArr[random.nextInt(bArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final int reduceRight(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i = length - 1;
        if (i >= 0) {
            int iIntValue = iArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                iIntValue = operation.invoke(Integer.valueOf(iArr[i2]), Integer.valueOf(iIntValue)).intValue();
            }
            return iIntValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final int reduceRightIndexed(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i = length - 1;
        if (i >= 0) {
            int iIntValue = iArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                iIntValue = operation.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i2]), Integer.valueOf(iIntValue)).intValue();
            }
            return iIntValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Integer> reversed(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Integer> mutableList = toMutableList(iArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static int[] reversedArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return iArr;
        }
        int[] iArr2 = new int[iArr.length];
        int length = iArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                iArr2[length - i] = iArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return iArr2;
    }

    public static int single(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return iArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @NotNull
    public static long[] sliceArray(@NotNull long[] jArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        long[] jArr2 = new long[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr2[i] = jArr[it.next().intValue()];
            i++;
        }
        return jArr2;
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Arrays.sort(cArr, i, i2);
        reverse(cArr, i, i2);
    }

    @NotNull
    public static final double[] sortedArray(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return dArr;
        }
        double[] dArrCopyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(dArrCopyOf);
        return dArrCopyOf;
    }

    @NotNull
    public static final double[] sortedArrayDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return dArr;
        }
        double[] dArrCopyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(...)");
        sortDescending(dArrCopyOf);
        return dArrCopyOf;
    }

    @NotNull
    public static final Set<Double> subtract(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull double[] dArr, @NotNull Function1<? super Double, Integer> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (double d : dArr) {
            iIntValue += selector.invoke(Double.valueOf(d)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull double[] dArr, @NotNull Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (double d : dArr) {
            dDoubleValue += selector.invoke(Double.valueOf(d)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (double d : dArr) {
            dDoubleValue += selector.invoke(Double.valueOf(d)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(double[] dArr, Function1<? super Double, Integer> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (double d : dArr) {
            iIntValue += selector.invoke(Double.valueOf(d)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(double[] dArr, Function1<? super Double, Long> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (double d : dArr) {
            jLongValue += selector.invoke(Double.valueOf(d)).longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(long[] jArr, Function1<? super Long, UInt> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (long j : jArr) {
            i += selector.invoke(Long.valueOf(j)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(long[] jArr, Function1<? super Long, ULong> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (long j2 : jArr) {
            j += selector.invoke(Long.valueOf(j2)).data;
        }
        return j;
    }

    @NotNull
    public static final List<Integer> takeLastWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(iArr);
            }
        } while (predicate.invoke(Integer.valueOf(iArr[length])).booleanValue());
        return drop(iArr, length + 1);
    }

    @NotNull
    public static final List<Integer> takeWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (!predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                break;
            }
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C toCollection(@NotNull double[] dArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (double d : dArr) {
            destination.add(Double.valueOf(d));
        }
        return destination;
    }

    @NotNull
    public static final List<Integer> toList(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(iArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[0]));
    }

    @NotNull
    public static final Set<Integer> toSet(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(iArr.length));
            toCollection(iArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Integer.valueOf(iArr[0]));
    }

    @NotNull
    public static final Set<Double> union(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull long[] jArr, @NotNull R[] other, @NotNull Function2<? super Long, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Long.valueOf(jArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Double, V> associateWith(double[] dArr, Function1<? super Double, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (double d : dArr) {
            linkedHashMap.put(Double.valueOf(d), valueSelector.invoke(Double.valueOf(d)));
        }
        return linkedHashMap;
    }

    public static final int count(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @Nullable
    public static final Long firstOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return Long.valueOf(j);
            }
        }
        return null;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfFirst { it == element }' instead to continue using this behavior, or '.asList().indexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfFirst { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ int indexOf(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (f == fArr[i]) {
                return i;
            }
        }
        return -1;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull short[] sArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Short, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (short s : sArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Short.valueOf(s)));
            } else {
                buffer.append(String.valueOf((int) s));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfLast { it == element }' instead to continue using this behavior, or '.asList().lastIndexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfLast { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ int lastIndexOf(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (f == fArr[length]) {
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

    public static final boolean none(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short randomOrNull(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[random.nextInt(sArr.length)]);
    }

    @NotNull
    public static final List<Boolean> toMutableList(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (short s : sArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Short.valueOf(s));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Boolean>> M associateByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (boolean z : zArr) {
            destination.put(keySelector.invoke(Boolean.valueOf(z)), Boolean.valueOf(z));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Boolean, ? super V>> M associateWithTo(boolean[] zArr, M destination, Function1<? super Boolean, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (boolean z : zArr) {
            destination.put(Boolean.valueOf(z), valueSelector.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    public static final int count(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final List<Boolean> filter(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterIndexedTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            boolean z = zArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Boolean> filterNot(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @Nullable
    public static final Float firstOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return Float.valueOf(f);
            }
        }
        return null;
    }

    public static final <R> R foldRight(@NotNull boolean[] zArr, R r, @NotNull Function2<? super Boolean, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = zArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Boolean.valueOf(zArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull boolean[] zArr, R r, @NotNull Function3<? super Integer, ? super Boolean, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = zArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Boolean.valueOf(zArr[length]), r);
        }
        return r;
    }

    public static final int indexOfFirst(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Boolean.valueOf(zArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Boolean.valueOf(zArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Boolean> intersect(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            destination.add(transform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    public static final boolean none(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final Pair<List<Short>, List<Short>> partition(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                arrayList.add(Short.valueOf(s));
            } else {
                arrayList2.add(Short.valueOf(s));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceIndexedOrNull(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return null;
        }
        int iIntValue = iArr[0];
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                iIntValue = operation.invoke(Integer.valueOf(i), Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Integer.valueOf(iIntValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceOrNull(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return null;
        }
        int iIntValue = iArr[0];
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                iIntValue = operation.invoke(Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Integer.valueOf(iIntValue);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Short> runningReduce(short[] sArr, Function2<? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        short sShortValue = sArr[0];
        ArrayList arrayList = new ArrayList(sArr.length);
        arrayList.add(Short.valueOf(sShortValue));
        int length = sArr.length;
        for (int i = 1; i < length; i++) {
            sShortValue = operation.invoke(Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
            arrayList.add(Short.valueOf(sShortValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Short> runningReduceIndexed(short[] sArr, Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        short sShortValue = sArr[0];
        ArrayList arrayList = new ArrayList(sArr.length);
        arrayList.add(Short.valueOf(sShortValue));
        int length = sArr.length;
        for (int i = 1; i < length; i++) {
            sShortValue = operation.invoke(Integer.valueOf(i), Short.valueOf(sShortValue), Short.valueOf(sArr[i])).shortValue();
            arrayList.add(Short.valueOf(sShortValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = bArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            byte b = bArr[length];
            bArr[length] = bArr[iNextInt];
            bArr[iNextInt] = b;
        }
    }

    @Nullable
    public static final Short singleOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Short shValueOf = null;
        boolean z = false;
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                if (z) {
                    return null;
                }
                shValueOf = Short.valueOf(s);
                z = true;
            }
        }
        if (z) {
            return shValueOf;
        }
        return null;
    }

    public static final <T extends Comparable<? super T>> void sortDescending(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        ArraysKt___ArraysJvmKt.sortWith(tArr, ComparisonsKt__ComparisonsKt.reverseOrder());
    }

    @NotNull
    public static final char[] sortedArray(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return cArr;
        }
        char[] cArrCopyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sort(cArrCopyOf);
        return cArrCopyOf;
    }

    @NotNull
    public static final char[] sortedArrayDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return cArr;
        }
        char[] cArrCopyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
        sortDescending(cArrCopyOf);
        return cArrCopyOf;
    }

    @NotNull
    public static final Set<Boolean> subtract(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Integer> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (boolean z : zArr) {
            iIntValue += selector.invoke(Boolean.valueOf(z)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (boolean z : zArr) {
            dDoubleValue += selector.invoke(Boolean.valueOf(z)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (boolean z : zArr) {
            dDoubleValue += selector.invoke(Boolean.valueOf(z)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(boolean[] zArr, Function1<? super Boolean, Integer> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (boolean z : zArr) {
            iIntValue += selector.invoke(Boolean.valueOf(z)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(boolean[] zArr, Function1<? super Boolean, Long> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (boolean z : zArr) {
            jLongValue += selector.invoke(Boolean.valueOf(z)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final List<Character> take(@NotNull char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= cArr.length) {
            return toList(cArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (char c : cArr) {
            arrayList.add(Character.valueOf(c));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> takeLast(@NotNull char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = cArr.length;
        if (i >= length) {
            return toList(cArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Character.valueOf(cArr[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C toCollection(@NotNull boolean[] zArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (boolean z : zArr) {
            destination.add(Boolean.valueOf(z));
        }
        return destination;
    }

    @NotNull
    public static final Set<Boolean> union(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    public static final boolean any(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final Iterable<Float> asIterable(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6(fArr);
    }

    @NotNull
    public static final Sequence<Float> asSequence(@NotNull final float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Float>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6
            @Override // kotlin.sequences.Sequence
            public Iterator<Float> iterator() {
                return ArrayIteratorsKt.iterator(fArr);
            }
        };
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i : iArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Integer.valueOf(i));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Boolean, V> associateWith(boolean[] zArr, Function1<? super Boolean, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (boolean z : zArr) {
            linkedHashMap.put(Boolean.valueOf(z), valueSelector.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    public static final int count(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final List<Integer> dropWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i : iArr) {
            if (z) {
                arrayList.add(Integer.valueOf(i));
            } else if (!predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                arrayList.add(Integer.valueOf(i));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> filterIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            float f = fArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Float.valueOf(f)).booleanValue()) {
                arrayList.add(Float.valueOf(f));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @InlineOnly
    public static final Float findLast(float[] fArr, Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            float f = fArr[length];
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return Float.valueOf(f);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final float first(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            return fArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Double firstOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return Double.valueOf(d);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(float[] fArr, C destination, Function2<? super Integer, ? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f : fArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Float.valueOf(f)));
        }
        return destination;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new IntRange(0, fArr.length - 1, 1);
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfFirst { it == element }' instead to continue using this behavior, or '.asList().indexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfFirst { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ int indexOf(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            if (d == dArr[i]) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfLast { it == element }' instead to continue using this behavior, or '.asList().lastIndexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfLast { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", hiddenSince = "1.7", warningSince = "1.4")
    public static final /* synthetic */ int lastIndexOf(double[] dArr, double d) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (d == dArr[length]) {
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

    @Nullable
    public static final Short lastOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            short s = sArr[length];
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return Short.valueOf(s);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(transform.invoke(Float.valueOf(f)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(fArr.length);
        int length = fArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final boolean none(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    public static final short random(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (sArr.length != 0) {
            return sArr[random.nextInt(sArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer randomOrNull(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[random.nextInt(iArr.length)]);
    }

    public static final int reduce(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length != 0) {
            int iIntValue = iArr[0];
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    iIntValue = operation.invoke(Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return iIntValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final int reduceIndexed(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length != 0) {
            int iIntValue = iArr[0];
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    iIntValue = operation.invoke(Integer.valueOf(i), Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return iIntValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceRightIndexedOrNull(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        long jLongValue = jArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            jLongValue = operation.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]), Long.valueOf(jLongValue)).longValue();
        }
        return Long.valueOf(jLongValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceRightOrNull(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        long jLongValue = jArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            jLongValue = operation.invoke(Long.valueOf(jArr[i2]), Long.valueOf(jLongValue)).longValue();
        }
        return Long.valueOf(jLongValue);
    }

    public static void reverse(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = (iArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = iArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = iArr[i];
            iArr[i] = iArr[length2];
            iArr[length2] = i2;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(int[] iArr, R r, Function2<? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r);
        for (int i : iArr) {
            r = operation.invoke(r, Integer.valueOf(i));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(int[] iArr, R r, Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r);
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Integer.valueOf(iArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> slice(@NotNull int[] iArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(iArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static final float[] sliceArray(@NotNull float[] fArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        float[] fArr2 = new float[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            fArr2[i] = fArr[it.next().intValue()];
            i++;
        }
        return fArr2;
    }

    public static final void sortDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(bArr);
            reverse(bArr);
        }
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(float[] fArr, Function1<? super Float, UInt> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (float f : fArr) {
            i += selector.invoke(Float.valueOf(f)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(float[] fArr, Function1<? super Float, ULong> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (float f : fArr) {
            j += selector.invoke(Float.valueOf(f)).data;
        }
        return j;
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c : cArr) {
            arrayList.add(Character.valueOf(c));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull float[] fArr, @NotNull R[] other, @NotNull Function2<? super Float, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Float.valueOf(fArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final boolean any(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final <K> Map<K, Long> associateBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (long j : jArr) {
            linkedHashMap.put(keySelector.invoke(Long.valueOf(j)), Long.valueOf(j));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (char c : cArr) {
            destination.put(keySelector.invoke(Character.valueOf(c)), Character.valueOf(c));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V, M extends Map<? super Character, ? super V>> M associateWithTo(char[] cArr, M destination, Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (char c : cArr) {
            destination.put(Character.valueOf(c), valueSelector.invoke(Character.valueOf(c)));
        }
        return destination;
    }

    public static final int count(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @NotNull
    public static final List<Long> dropLastWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Long.valueOf(jArr[length])).booleanValue());
        return take(jArr, length + 1);
    }

    @NotNull
    public static final List<Character> filter(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                arrayList.add(Character.valueOf(c));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterIndexedTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char c = cArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(c)).booleanValue()) {
                destination.add(Character.valueOf(c));
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    @NotNull
    public static final List<Character> filterNot(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c : cArr) {
            if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                arrayList.add(Character.valueOf(c));
            }
        }
        return arrayList;
    }

    @Nullable
    public static final Boolean firstOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
        }
        return null;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Long.valueOf(j)));
        }
        return arrayList;
    }

    public static final <R> R foldRight(@NotNull char[] cArr, R r, @NotNull Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = cArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Character.valueOf(cArr[length]), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull char[] cArr, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int length = cArr.length - 1; length >= 0; length--) {
            r = operation.invoke(Integer.valueOf(length), Character.valueOf(cArr[length]), r);
        }
        return r;
    }

    public static final int indexOfFirst(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Character.valueOf(cArr[i])).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (predicate.invoke(Character.valueOf(cArr[length])).booleanValue()) {
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

    @NotNull
    public static final Set<Character> intersect(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    public static long last(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length != 0) {
            return jArr[jArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            destination.add(transform.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c : cArr) {
            destination.add(transform.invoke(Character.valueOf(c)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Short maxByOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> short maxByOrThrow(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (length != 0) {
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
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Short minByOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> short minByOrThrow(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (length != 0) {
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
            }
            return s;
        }
        throw new NoSuchElementException();
    }

    public static final boolean none(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final long reduceRight(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i = length - 1;
        if (i >= 0) {
            long jLongValue = jArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                jLongValue = operation.invoke(Long.valueOf(jArr[i2]), Long.valueOf(jLongValue)).longValue();
            }
            return jLongValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final long reduceRightIndexed(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i = length - 1;
        if (i >= 0) {
            long jLongValue = jArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                jLongValue = operation.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]), Long.valueOf(jLongValue)).longValue();
            }
            return jLongValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Long> reversed(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Long> mutableList = toMutableList(jArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static long[] reversedArray(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return jArr;
        }
        long[] jArr2 = new long[jArr.length];
        int length = jArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                jArr2[length - i] = jArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return jArr2;
    }

    public static long single(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return jArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @NotNull
    public static final Set<Character> subtract(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull char[] cArr, @NotNull Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (char c : cArr) {
            iIntValue += selector.invoke(Character.valueOf(c)).intValue();
        }
        return iIntValue;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull char[] cArr, @NotNull Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (char c : cArr) {
            dDoubleValue += selector.invoke(Character.valueOf(c)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    public static final double sumOfDouble(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double dDoubleValue = 0.0d;
        for (char c : cArr) {
            dDoubleValue += selector.invoke(Character.valueOf(c)).doubleValue();
        }
        return dDoubleValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    public static final int sumOfInt(char[] cArr, Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int iIntValue = 0;
        for (char c : cArr) {
            iIntValue += selector.invoke(Character.valueOf(c)).intValue();
        }
        return iIntValue;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    public static final long sumOfLong(char[] cArr, Function1<? super Character, Long> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long jLongValue = 0;
        for (char c : cArr) {
            jLongValue += selector.invoke(Character.valueOf(c)).longValue();
        }
        return jLongValue;
    }

    @NotNull
    public static final List<Long> takeLastWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(jArr);
            }
        } while (predicate.invoke(Long.valueOf(jArr[length])).booleanValue());
        return drop(jArr, length + 1);
    }

    @NotNull
    public static final List<Long> takeWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
                break;
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull char[] cArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (char c : cArr) {
            destination.add(Character.valueOf(c));
        }
        return destination;
    }

    @NotNull
    public static final List<Long> toList(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(jArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[0]));
    }

    @NotNull
    public static final Set<Long> toSet(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(jArr.length));
            toCollection(jArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Long.valueOf(jArr[0]));
    }

    @NotNull
    public static final Set<Character> union(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    public static final boolean any(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <V> Map<Character, V> associateWith(char[] cArr, Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int length = cArr.length;
        if (length > 128) {
            length = 128;
        }
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (char c : cArr) {
            linkedHashMap.put(Character.valueOf(c), valueSelector.invoke(Character.valueOf(c)));
        }
        return linkedHashMap;
    }

    public static final int count(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i = 0;
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return Character.valueOf(c);
            }
        }
        return null;
    }

    public static final int indexOf(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (z == zArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int lastIndexOf(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (z == zArr[length]) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte maxOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                if (b < b2) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer maxWithOrNull(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                if (comparator.compare(Integer.valueOf(i), Integer.valueOf(i3)) < 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte minOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        byte b = bArr[0];
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                byte b2 = bArr[i];
                if (b > b2) {
                    b = b2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Byte.valueOf(b);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer minWithOrNull(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                if (comparator.compare(Integer.valueOf(i), Integer.valueOf(i3)) > 0) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    public static final boolean none(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long randomOrNull(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[random.nextInt(jArr.length)]);
    }

    @Nullable
    public static final Integer singleOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Integer numValueOf = null;
        boolean z = false;
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                if (z) {
                    return null;
                }
                numValueOf = Integer.valueOf(i);
                z = true;
            }
        }
        if (z) {
            return numValueOf;
        }
        return null;
    }

    @NotNull
    public static final Iterable<Double> asIterable(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7(dArr);
    }

    @NotNull
    public static final Sequence<Double> asSequence(@NotNull final double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Double>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$7
            @Override // kotlin.sequences.Sequence
            public Iterator<Double> iterator() {
                return ArrayIteratorsKt.iterator(dArr);
            }
        };
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : tArr) {
            destination.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return destination;
    }

    @NotNull
    public static final <K> List<Integer> distinctBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (hashSet.add(selector.invoke(Integer.valueOf(i)))) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> drop(@NotNull short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i >= 0) {
            int length = sArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(sArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Short> dropLast(@NotNull short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i >= 0) {
            int length = sArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(sArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Double> filterIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            double d = dArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Double.valueOf(d)).booleanValue()) {
                arrayList.add(Double.valueOf(d));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @InlineOnly
    public static final Double findLast(double[] dArr, Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            double d = dArr[length];
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return Double.valueOf(d);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final double first(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length != 0) {
            return dArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(double[] dArr, C destination, Function2<? super Integer, ? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d : dArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Double.valueOf(d)));
        }
        return destination;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return new IntRange(0, dArr.length - 1, 1);
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Short>>> M groupByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (short s : sArr) {
            K kInvoke = keySelector.invoke(Short.valueOf(s));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Short.valueOf(s));
        }
        return destination;
    }

    @Nullable
    public static final Integer lastOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            int i2 = iArr[length];
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return Integer.valueOf(i2);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(transform.invoke(Double.valueOf(d)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(dArr.length);
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            double dDoubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Integer.valueOf(iArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m961maxOfOrNull(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Integer.valueOf(iArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final byte maxOrThrow(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    byte b2 = bArr[i];
                    if (b < b2) {
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
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final int maxWithOrThrow(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = iArr[i2];
                    if (comparator.compare(Integer.valueOf(i), Integer.valueOf(i3)) < 0) {
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            double dDoubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Integer.valueOf(iArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m997minOfOrNull(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Integer.valueOf(iArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final byte minOrThrow(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length != 0) {
            byte b = bArr[0];
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    byte b2 = bArr[i];
                    if (b > b2) {
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
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final int minWithOrThrow(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = iArr[i2];
                    if (comparator.compare(Integer.valueOf(i), Integer.valueOf(i3)) > 0) {
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

    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (iArr.length != 0) {
            return iArr[random.nextInt(iArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @NotNull
    public static final double[] sliceArray(@NotNull double[] dArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        double[] dArr2 = new double[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            dArr2[i] = dArr[it.next().intValue()];
            i++;
        }
        return dArr2;
    }

    public static final void sortDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(sArr);
            reverse(sArr);
        }
    }

    @JvmName(name = "sumOfDouble")
    public static final double sumOfDouble(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double dDoubleValue = 0.0d;
        for (Double d : dArr) {
            dDoubleValue += d.doubleValue();
        }
        return dDoubleValue;
    }

    @JvmName(name = "sumOfInt")
    public static final int sumOfInt(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        int iIntValue = 0;
        for (Integer num : numArr) {
            iIntValue += num.intValue();
        }
        return iIntValue;
    }

    @JvmName(name = "sumOfLong")
    public static final long sumOfLong(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        long jLongValue = 0;
        for (Long l : lArr) {
            jLongValue += l.longValue();
        }
        return jLongValue;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(double[] dArr, Function1<? super Double, UInt> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (double d : dArr) {
            i += selector.invoke(Double.valueOf(d)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(double[] dArr, Function1<? super Double, ULong> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (double d : dArr) {
            j += selector.invoke(Double.valueOf(d)).data;
        }
        return j;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull double[] dArr, @NotNull R[] other, @NotNull Function2<? super Double, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Double.valueOf(dArr[i]), other[i]));
        }
        return arrayList;
    }

    public static final int indexOf(@NotNull char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            if (c == cArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int lastIndexOf(@NotNull char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                if (c == cArr[length]) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float randomOrNull(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[random.nextInt(fArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceIndexedOrNull(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return null;
        }
        long jLongValue = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                jLongValue = operation.invoke(Integer.valueOf(i), Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(jLongValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceOrNull(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return null;
        }
        long jLongValue = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                jLongValue = operation.invoke(Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(jLongValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceRightIndexedOrNull(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        float fFloatValue = fArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            fFloatValue = operation.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i2]), Float.valueOf(fFloatValue)).floatValue();
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceRightOrNull(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        float fFloatValue = fArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            fFloatValue = operation.invoke(Float.valueOf(fArr[i2]), Float.valueOf(fFloatValue)).floatValue();
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = sArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            short s = sArr[length];
            sArr[length] = sArr[iNextInt];
            sArr[iNextInt] = s;
        }
    }

    @NotNull
    public static final <K> Map<K, Float> associateBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (float f : fArr) {
            linkedHashMap.put(keySelector.invoke(Float.valueOf(f)), Float.valueOf(f));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (byte b : bArr) {
            destination.put(keySelector.invoke(Byte.valueOf(b)), valueTransform.invoke(Byte.valueOf(b)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j : jArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Long.valueOf(j));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @NotNull
    public static final List<Float> dropLastWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Float.valueOf(fArr[length])).booleanValue());
        return take(fArr, length + 1);
    }

    @NotNull
    public static final List<Long> dropWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (long j : jArr) {
            if (z) {
                arrayList.add(Long.valueOf(j));
            } else if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
                arrayList.add(Long.valueOf(j));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (float f : fArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Float.valueOf(f)));
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, List<Short>> groupBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s : sArr) {
            K kInvoke = keySelector.invoke(Short.valueOf(s));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Short.valueOf(s));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull int[] iArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Integer, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (int i3 : iArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Integer.valueOf(i3)));
            } else {
                buffer.append(String.valueOf(i3));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final float last(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            return fArr[fArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final long reduce(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length != 0) {
            long jLongValue = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    jLongValue = operation.invoke(Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return jLongValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final long reduceIndexed(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length != 0) {
            long jLongValue = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    jLongValue = operation.invoke(Integer.valueOf(i), Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return jLongValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final float reduceRight(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i = length - 1;
        if (i >= 0) {
            float fFloatValue = fArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                fFloatValue = operation.invoke(Float.valueOf(fArr[i2]), Float.valueOf(fFloatValue)).floatValue();
            }
            return fFloatValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final float reduceRightIndexed(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i = length - 1;
        if (i >= 0) {
            float fFloatValue = fArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                fFloatValue = operation.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i2]), Float.valueOf(fFloatValue)).floatValue();
            }
            return fFloatValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static void reverse(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = (jArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = jArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            jArr[i] = jArr[length2];
            jArr[length2] = j;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @NotNull
    public static final List<Float> reversed(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Float> mutableList = toMutableList(fArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final float[] reversedArray(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return fArr;
        }
        float[] fArr2 = new float[fArr.length];
        int length = fArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                fArr2[length - i] = fArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return fArr2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(long[] jArr, R r, Function2<? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r);
        for (long j : jArr) {
            r = operation.invoke(r, Long.valueOf(j));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(long[] jArr, R r, Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r);
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Long.valueOf(jArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    public static final float single(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return fArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @Nullable
    public static final Long singleOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Long lValueOf = null;
        boolean z = false;
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                if (z) {
                    return null;
                }
                lValueOf = Long.valueOf(j);
                z = true;
            }
        }
        if (z) {
            return lValueOf;
        }
        return null;
    }

    @NotNull
    public static final List<Long> slice(@NotNull long[] jArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(jArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static final List<Float> takeLastWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(fArr);
            }
        } while (predicate.invoke(Float.valueOf(fArr[length])).booleanValue());
        return drop(fArr, length + 1);
    }

    @NotNull
    public static final List<Float> takeWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f : fArr) {
            if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                break;
            }
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> toList(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(fArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[0]));
    }

    @NotNull
    public static final Set<Float> toSet(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(fArr.length));
            toCollection(fArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Float.valueOf(fArr[0]));
    }

    @NotNull
    public static final Iterable<Boolean> asIterable(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8(zArr);
    }

    @NotNull
    public static final Sequence<Boolean> asSequence(@NotNull final boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Boolean>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$8
            @Override // kotlin.sequences.Sequence
            public Iterator<Boolean> iterator() {
                return ArrayIteratorsKt.iterator(zArr);
            }
        };
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i : iArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Integer.valueOf(i));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final List<Boolean> filterIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            boolean z = zArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @InlineOnly
    public static final Boolean findLast(boolean[] zArr, Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            boolean z = zArr[length];
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final boolean first(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length != 0) {
            return zArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(boolean[] zArr, C destination, Function2<? super Integer, ? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return new IntRange(0, zArr.length - 1, 1);
    }

    @Nullable
    public static final Long lastOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            long j = jArr[length];
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return Long.valueOf(j);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(transform.invoke(Boolean.valueOf(z)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(zArr.length);
        int length = zArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final Pair<List<Integer>, List<Integer>> partition(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                arrayList.add(Integer.valueOf(i));
            } else {
                arrayList2.add(Integer.valueOf(i));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (jArr.length != 0) {
            return jArr[random.nextInt(jArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double randomOrNull(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[random.nextInt(dArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Integer> runningReduce(int[] iArr, Function2<? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        int iIntValue = iArr[0];
        ArrayList arrayList = new ArrayList(iArr.length);
        arrayList.add(Integer.valueOf(iIntValue));
        int length = iArr.length;
        for (int i = 1; i < length; i++) {
            iIntValue = operation.invoke(Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
            arrayList.add(Integer.valueOf(iIntValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Integer> runningReduceIndexed(int[] iArr, Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        int iIntValue = iArr[0];
        ArrayList arrayList = new ArrayList(iArr.length);
        arrayList.add(Integer.valueOf(iIntValue));
        int length = iArr.length;
        for (int i = 1; i < length; i++) {
            iIntValue = operation.invoke(Integer.valueOf(i), Integer.valueOf(iIntValue), Integer.valueOf(iArr[i])).intValue();
            arrayList.add(Integer.valueOf(iIntValue));
        }
        return arrayList;
    }

    @NotNull
    public static final boolean[] sliceArray(@NotNull boolean[] zArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        boolean[] zArr2 = new boolean[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            zArr2[i] = zArr[it.next().intValue()];
            i++;
        }
        return zArr2;
    }

    public static final void sortDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(iArr);
            reverse(iArr);
        }
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(boolean[] zArr, Function1<? super Boolean, UInt> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (boolean z : zArr) {
            i += selector.invoke(Boolean.valueOf(z)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(boolean[] zArr, Function1<? super Boolean, ULong> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (boolean z : zArr) {
            j += selector.invoke(Boolean.valueOf(z)).data;
        }
        return j;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull boolean[] zArr, @NotNull R[] other, @NotNull Function2<? super Boolean, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i]), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (short s : sArr) {
            destination.put(keySelector.invoke(Short.valueOf(s)), valueTransform.invoke(Short.valueOf(s)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short maxOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                if (s < s2) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short minOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        short s = sArr[0];
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                short s2 = sArr[i];
                if (s > s2) {
                    s = s2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Short.valueOf(s);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final short maxOrThrow(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = sArr[i];
                    if (s < s2) {
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
    @Nullable
    public static final Long maxWithOrNull(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                if (comparator.compare(Long.valueOf(j), Long.valueOf(j2)) < 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final short minOrThrow(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length != 0) {
            short s = sArr[0];
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    short s2 = sArr[i];
                    if (s > s2) {
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
    @Nullable
    public static final Long minWithOrNull(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                if (comparator.compare(Long.valueOf(j), Long.valueOf(j2)) > 0) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean randomOrNull(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[random.nextInt(zArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceRightIndexedOrNull(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        double dDoubleValue = dArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            dDoubleValue = operation.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i2]), Double.valueOf(dDoubleValue)).doubleValue();
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceRightOrNull(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        double dDoubleValue = dArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            dDoubleValue = operation.invoke(Double.valueOf(dArr[i2]), Double.valueOf(dDoubleValue)).doubleValue();
        }
        return Double.valueOf(dDoubleValue);
    }

    @Nullable
    public static final Float singleOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Float fValueOf = null;
        boolean z = false;
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                if (z) {
                    return null;
                }
                fValueOf = Float.valueOf(f);
                z = true;
            }
        }
        if (z) {
            return fValueOf;
        }
        return null;
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$9(cArr);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull final char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return EmptySequence.INSTANCE;
        }
        return new Sequence<Character>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$9
            @Override // kotlin.sequences.Sequence
            public Iterator<Character> iterator() {
                return ArrayIteratorsKt.iterator(cArr);
            }
        };
    }

    @NotNull
    public static final <K> Map<K, Double> associateBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (double d : dArr) {
            linkedHashMap.put(keySelector.invoke(Double.valueOf(d)), Double.valueOf(d));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i : iArr) {
            destination.put(keySelector.invoke(Integer.valueOf(i)), valueTransform.invoke(Integer.valueOf(i)));
        }
        return destination;
    }

    @NotNull
    public static final <K> List<Long> distinctBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            if (hashSet.add(selector.invoke(Long.valueOf(j)))) {
                arrayList.add(Long.valueOf(j));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> dropLastWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Double.valueOf(dArr[length])).booleanValue());
        return take(dArr, length + 1);
    }

    @NotNull
    public static final List<Character> filterIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char c = cArr[i];
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(c)).booleanValue()) {
                arrayList.add(Character.valueOf(c));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @InlineOnly
    public static final Character findLast(char[] cArr, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            char c = cArr[length];
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return Character.valueOf(c);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    public static final char first(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length != 0) {
            return cArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (double d : dArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Double.valueOf(d)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    public static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(char[] cArr, C destination, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i])));
            i++;
            i2++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c : cArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Character.valueOf(c)));
        }
        return destination;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new IntRange(0, cArr.length - 1, 1);
    }

    public static final double last(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length != 0) {
            return dArr[dArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @Nullable
    public static final Float lastOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            float f = fArr[length];
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return Float.valueOf(f);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c : cArr) {
            arrayList.add(transform.invoke(Character.valueOf(c)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(cArr.length);
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i])));
            i++;
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> int maxByOrThrow(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (length != 0) {
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
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            double dDoubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Long.valueOf(jArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m962maxOfOrNull(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Long.valueOf(jArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final long maxWithOrThrow(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = jArr[i];
                    if (comparator.compare(Long.valueOf(j), Long.valueOf(j2)) < 0) {
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
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> int minByOrThrow(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (length != 0) {
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
            }
            return i;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            double dDoubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Long.valueOf(jArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m998minOfOrNull(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Long.valueOf(jArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final long minWithOrThrow(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = jArr[i];
                    if (comparator.compare(Long.valueOf(j), Long.valueOf(j2)) > 0) {
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

    @SinceKotlin(version = "1.3")
    public static final float random(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (fArr.length != 0) {
            return fArr[random.nextInt(fArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceIndexedOrNull(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = operation.invoke(Integer.valueOf(i), Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceOrNull(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = operation.invoke(Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    public static final double reduceRight(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i = length - 1;
        if (i >= 0) {
            double dDoubleValue = dArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                dDoubleValue = operation.invoke(Double.valueOf(dArr[i2]), Double.valueOf(dDoubleValue)).doubleValue();
            }
            return dDoubleValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final double reduceRightIndexed(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i = length - 1;
        if (i >= 0) {
            double dDoubleValue = dArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                dDoubleValue = operation.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i2]), Double.valueOf(dDoubleValue)).doubleValue();
            }
            return dDoubleValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Double> reversed(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Double> mutableList = toMutableList(dArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final double[] reversedArray(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return dArr;
        }
        double[] dArr2 = new double[dArr.length];
        int length = dArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                dArr2[length - i] = dArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return dArr2;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = iArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            int i = iArr[length];
            iArr[length] = iArr[iNextInt];
            iArr[iNextInt] = i;
        }
    }

    public static final double single(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return dArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @NotNull
    public static final char[] sliceArray(@NotNull char[] cArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        char[] cArr2 = new char[indices.size()];
        Iterator<Integer> it = indices.iterator();
        int i = 0;
        while (it.hasNext()) {
            cArr2[i] = cArr[it.next().intValue()];
            i++;
        }
        return cArr2;
    }

    public static final void sortDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(jArr);
            reverse(jArr);
        }
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(char[] cArr, Function1<? super Character, UInt> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i = 0;
        for (char c : cArr) {
            i += selector.invoke(Character.valueOf(c)).data;
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(char[] cArr, Function1<? super Character, ULong> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j = 0;
        for (char c : cArr) {
            j += selector.invoke(Character.valueOf(c)).data;
        }
        return j;
    }

    @NotNull
    public static final List<Double> takeLastWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(dArr);
            }
        } while (predicate.invoke(Double.valueOf(dArr[length])).booleanValue());
        return drop(dArr, length + 1);
    }

    @NotNull
    public static final List<Double> takeWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d : dArr) {
            if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                break;
            }
            arrayList.add(Double.valueOf(d));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> toList(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(dArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[0]));
    }

    @NotNull
    public static final Set<Double> toSet(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(dArr.length));
            toCollection(dArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Double.valueOf(dArr[0]));
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull char[] cArr, @NotNull R[] other, @NotNull Function2<? super Character, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Character.valueOf(cArr[i]), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f : fArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Float.valueOf(f));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @NotNull
    public static final List<Float> dropWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (float f : fArr) {
            if (z) {
                arrayList.add(Float.valueOf(f));
            } else if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                arrayList.add(Float.valueOf(f));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Integer maxByOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Integer minByOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character randomOrNull(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[random.nextInt(cArr.length)]);
    }

    public static final float reduce(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length != 0) {
            float fFloatValue = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = operation.invoke(Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final float reduceIndexed(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length != 0) {
            float fFloatValue = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = operation.invoke(Integer.valueOf(i), Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fFloatValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final void reverse(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = (fArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = fArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            float f = fArr[i];
            fArr[i] = fArr[length2];
            fArr[length2] = f;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(float[] fArr, R r, Function2<? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r);
        for (float f : fArr) {
            r = operation.invoke(r, Float.valueOf(f));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(float[] fArr, R r, Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r);
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Float.valueOf(fArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> slice(@NotNull float[] fArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(fArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (long j : jArr) {
            destination.put(keySelector.invoke(Long.valueOf(j)), valueTransform.invoke(Long.valueOf(j)));
        }
        return destination;
    }

    @Nullable
    public static final Double singleOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Double dValueOf = null;
        boolean z = false;
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                if (z) {
                    return null;
                }
                dValueOf = Double.valueOf(d);
                z = true;
            }
        }
        if (z) {
            return dValueOf;
        }
        return null;
    }

    @NotNull
    public static final List<Integer> drop(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i >= 0) {
            int length = iArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(iArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Integer> dropLast(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i >= 0) {
            int length = iArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(iArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    public static final <T> T first(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t : tArr) {
            if (predicate.invoke(t).booleanValue()) {
                return t;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Integer>>> M groupByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i : iArr) {
            K kInvoke = keySelector.invoke(Integer.valueOf(i));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Integer.valueOf(i));
        }
        return destination;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull long[] jArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Long, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (long j : jArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Long.valueOf(j)));
            } else {
                buffer.append(String.valueOf(j));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @Nullable
    public static final Double lastOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            double d = dArr[length];
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return Double.valueOf(d);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer maxOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                if (i < i3) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer minOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                int i3 = iArr[i2];
                if (i > i3) {
                    i = i3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i);
    }

    @SinceKotlin(version = "1.3")
    public static final double random(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (dArr.length != 0) {
            return dArr[random.nextInt(dArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceRightIndexedOrNull(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        boolean zBooleanValue = zArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            zBooleanValue = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i2]), Boolean.valueOf(zBooleanValue)).booleanValue();
        }
        return Boolean.valueOf(zBooleanValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceRightOrNull(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        boolean zBooleanValue = zArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            zBooleanValue = operation.invoke(Boolean.valueOf(zArr[i2]), Boolean.valueOf(zBooleanValue)).booleanValue();
        }
        return Boolean.valueOf(zBooleanValue);
    }

    @NotNull
    public static final <T> T[] sliceArray(@NotNull T[] tArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? (T[]) ArraysKt___ArraysJvmKt.copyOfRange(tArr, 0, 0) : (T[]) ArraysKt___ArraysJvmKt.copyOfRange(tArr, indices.first, indices.last + 1);
    }

    public static final void sortDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(fArr);
            reverse(fArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull T[] tArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(tArr[i], r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (long j : jArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Long.valueOf(j));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Boolean> associateBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (boolean z : zArr) {
            linkedHashMap.put(keySelector.invoke(Boolean.valueOf(z)), Boolean.valueOf(z));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (float f : fArr) {
            destination.put(keySelector.invoke(Float.valueOf(f)), valueTransform.invoke(Float.valueOf(f)));
        }
        return destination;
    }

    @NotNull
    public static final List<Boolean> dropLastWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Boolean.valueOf(zArr[length])).booleanValue());
        return take(zArr, length + 1);
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Boolean.valueOf(z)));
        }
        return arrayList;
    }

    public static final boolean last(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length != 0) {
            return zArr[zArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final int maxOrThrow(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = iArr[i2];
                    if (i < i3) {
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
    @JvmName(name = "minOrThrow")
    public static final int minOrThrow(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length != 0) {
            int i = iArr[0];
            int i2 = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    int i3 = iArr[i2];
                    if (i > i3) {
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

    @NotNull
    public static final Pair<List<Long>, List<Long>> partition(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                arrayList.add(Long.valueOf(j));
            } else {
                arrayList2.add(Long.valueOf(j));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    public static final boolean reduceRight(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i = length - 1;
        if (i >= 0) {
            boolean zBooleanValue = zArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                zBooleanValue = operation.invoke(Boolean.valueOf(zArr[i2]), Boolean.valueOf(zBooleanValue)).booleanValue();
            }
            return zBooleanValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final boolean reduceRightIndexed(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i = length - 1;
        if (i >= 0) {
            boolean zBooleanValue = zArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                zBooleanValue = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i2]), Boolean.valueOf(zBooleanValue)).booleanValue();
            }
            return zBooleanValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Boolean> reversed(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Boolean> mutableList = toMutableList(zArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final boolean[] reversedArray(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return zArr;
        }
        boolean[] zArr2 = new boolean[zArr.length];
        int length = zArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                zArr2[length - i] = zArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return zArr2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Long> runningReduce(long[] jArr, Function2<? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        long jLongValue = jArr[0];
        ArrayList arrayList = new ArrayList(jArr.length);
        arrayList.add(Long.valueOf(jLongValue));
        int length = jArr.length;
        for (int i = 1; i < length; i++) {
            jLongValue = operation.invoke(Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
            arrayList.add(Long.valueOf(jLongValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Long> runningReduceIndexed(long[] jArr, Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        long jLongValue = jArr[0];
        ArrayList arrayList = new ArrayList(jArr.length);
        arrayList.add(Long.valueOf(jLongValue));
        int length = jArr.length;
        for (int i = 1; i < length; i++) {
            jLongValue = operation.invoke(Integer.valueOf(i), Long.valueOf(jLongValue), Long.valueOf(jArr[i])).longValue();
            arrayList.add(Long.valueOf(jLongValue));
        }
        return arrayList;
    }

    public static final boolean single(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return zArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @NotNull
    public static final List<Double> take(@NotNull double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= dArr.length) {
            return toList(dArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (double d : dArr) {
            arrayList.add(Double.valueOf(d));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> takeLast(@NotNull double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = dArr.length;
        if (i >= length) {
            return toList(dArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Double.valueOf(dArr[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> takeLastWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(zArr);
            }
        } while (predicate.invoke(Boolean.valueOf(zArr[length])).booleanValue());
        return drop(zArr, length + 1);
    }

    @NotNull
    public static final List<Boolean> takeWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                break;
            }
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> toList(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(zArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[0]));
    }

    @NotNull
    public static final Set<Boolean> toSet(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(zArr.length));
            toCollection(zArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Boolean.valueOf(zArr[0]));
    }

    public static final byte first(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                return b;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxWithOrNull(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (fArr.length == 0) {
            return null;
        }
        float f = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                float f2 = fArr[i];
                if (comparator.compare(Float.valueOf(f), Float.valueOf(f2)) < 0) {
                    f = f2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(f);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minWithOrNull(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (fArr.length == 0) {
            return null;
        }
        float f = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                float f2 = fArr[i];
                if (comparator.compare(Float.valueOf(f), Float.valueOf(f2)) > 0) {
                    f = f2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(f);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceIndexedOrNull(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = operation.invoke(Integer.valueOf(i), Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceOrNull(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = operation.invoke(Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dDoubleValue);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = jArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            long j = jArr[length];
            jArr[length] = jArr[iNextInt];
            jArr[iNextInt] = j;
        }
    }

    @Nullable
    public static final Boolean singleOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Boolean boolValueOf = null;
        boolean z = false;
        for (boolean z2 : zArr) {
            if (predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                if (z) {
                    return null;
                }
                boolValueOf = Boolean.valueOf(z2);
                z = true;
            }
        }
        if (z) {
            return boolValueOf;
        }
        return null;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (double d : dArr) {
            destination.put(keySelector.invoke(Double.valueOf(d)), valueTransform.invoke(Double.valueOf(d)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d : dArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Double.valueOf(d));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @NotNull
    public static final <K> List<Float> distinctBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (float f : fArr) {
            if (hashSet.add(selector.invoke(Float.valueOf(f)))) {
                arrayList.add(Float.valueOf(f));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> dropWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (double d : dArr) {
            if (z) {
                arrayList.add(Double.valueOf(d));
            } else if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                arrayList.add(Double.valueOf(d));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, List<Integer>> groupBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i : iArr) {
            K kInvoke = keySelector.invoke(Integer.valueOf(i));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Integer.valueOf(i));
        }
        return linkedHashMap;
    }

    @Nullable
    public static final Boolean lastOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            boolean z = zArr[length];
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            double dDoubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Float.valueOf(fArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m960maxOfOrNull(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Float.valueOf(fArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final float maxWithOrThrow(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (fArr.length != 0) {
            float f = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    float f2 = fArr[i];
                    if (comparator.compare(Float.valueOf(f), Float.valueOf(f2)) < 0) {
                        f = f2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return f;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            double dDoubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Float.valueOf(fArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m996minOfOrNull(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Float.valueOf(fArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final float minWithOrThrow(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (fArr.length != 0) {
            float f = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    float f2 = fArr[i];
                    if (comparator.compare(Float.valueOf(f), Float.valueOf(f2)) > 0) {
                        f = f2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return f;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.3")
    public static final boolean random(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (zArr.length != 0) {
            return zArr[random.nextInt(zArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final double reduce(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length != 0) {
            double dDoubleValue = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = operation.invoke(Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final double reduceIndexed(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length != 0) {
            double dDoubleValue = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = operation.invoke(Integer.valueOf(i), Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final void reverse(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = (dArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = dArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            double d = dArr[i];
            dArr[i] = dArr[length2];
            dArr[length2] = d;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(double[] dArr, R r, Function2<? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r);
        for (double d : dArr) {
            r = operation.invoke(r, Double.valueOf(d));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(double[] dArr, R r, Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r);
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Double.valueOf(dArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> slice(@NotNull double[] dArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(dArr, indices.first, indices.last + 1));
    }

    public static final void sortDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(dArr);
            reverse(dArr);
        }
    }

    public static final short first(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                return s;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceRightIndexedOrNull(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        char cCharValue = cArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            cCharValue = operation.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i2]), Character.valueOf(cCharValue)).charValue();
        }
        return Character.valueOf(cCharValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceRightOrNull(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i = length - 1;
        if (i < 0) {
            return null;
        }
        char cCharValue = cArr[i];
        for (int i2 = length - 2; i2 >= 0; i2--) {
            cCharValue = operation.invoke(Character.valueOf(cArr[i2]), Character.valueOf(cCharValue)).charValue();
        }
        return Character.valueOf(cCharValue);
    }

    @NotNull
    public static byte[] sliceArray(@NotNull byte[] bArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new byte[0] : ArraysKt___ArraysJvmKt.copyOfRange(bArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull byte[] bArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Byte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (char c : cArr) {
            linkedHashMap.put(keySelector.invoke(Character.valueOf(c)), Character.valueOf(c));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (boolean z : zArr) {
            destination.put(keySelector.invoke(Boolean.valueOf(z)), valueTransform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @NotNull
    public static final List<Character> dropLastWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        do {
            length--;
            if (-1 >= length) {
                return EmptyList.INSTANCE;
            }
        } while (predicate.invoke(Character.valueOf(cArr[length])).booleanValue());
        return take(cArr, length + 1);
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (char c : cArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Character.valueOf(c)));
        }
        return arrayList;
    }

    public static final char last(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length != 0) {
            return cArr[cArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> long maxByOrThrow(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (length != 0) {
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
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long maxOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                if (j < j2) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> long minByOrThrow(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (length != 0) {
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
            }
            return j;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long minOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        long j = jArr[0];
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                long j2 = jArr[i];
                if (j > j2) {
                    j = j2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Long.valueOf(j);
    }

    public static final char reduceRight(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i = length - 1;
        if (i >= 0) {
            char cCharValue = cArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                cCharValue = operation.invoke(Character.valueOf(cArr[i2]), Character.valueOf(cCharValue)).charValue();
            }
            return cCharValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final char reduceRightIndexed(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i = length - 1;
        if (i >= 0) {
            char cCharValue = cArr[i];
            for (int i2 = length - 2; i2 >= 0; i2--) {
                cCharValue = operation.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i2]), Character.valueOf(cCharValue)).charValue();
            }
            return cCharValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @NotNull
    public static final List<Character> reversed(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        List<Character> mutableList = toMutableList(cArr);
        Collections.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final char[] reversedArray(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return cArr;
        }
        char[] cArr2 = new char[cArr.length];
        int length = cArr.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                cArr2[length - i] = cArr[i];
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return cArr2;
    }

    public static char single(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    @Nullable
    public static final Character singleOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Character chValueOf = null;
        boolean z = false;
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                if (z) {
                    return null;
                }
                chValueOf = Character.valueOf(c);
                z = true;
            }
        }
        if (z) {
            return chValueOf;
        }
        return null;
    }

    @NotNull
    public static final List<Character> takeLastWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        do {
            length--;
            if (-1 >= length) {
                return toList(cArr);
            }
        } while (predicate.invoke(Character.valueOf(cArr[length])).booleanValue());
        return drop(cArr, length + 1);
    }

    @NotNull
    public static final List<Character> takeWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c : cArr) {
            if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                break;
            }
            arrayList.add(Character.valueOf(c));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> toList(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return toMutableList(cArr);
        }
        return CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[0]));
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            int length2 = cArr.length;
            if (length2 > 128) {
                length2 = 128;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(length2));
            toCollection(cArr, linkedHashSet);
            return linkedHashSet;
        }
        return SetsKt__SetsJVMKt.setOf(Character.valueOf(cArr[0]));
    }

    public static final int first(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                return i;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i = length - 1;
            char c = cArr[length];
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return Character.valueOf(c);
            }
            if (i < 0) {
                return null;
            }
            length = i;
        }
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final long maxOrThrow(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = jArr[i];
                    if (j < j2) {
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
    @JvmName(name = "minOrThrow")
    public static final long minOrThrow(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length != 0) {
            long j = jArr[0];
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    long j2 = jArr[i];
                    if (j > j2) {
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

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (cArr.length != 0) {
            return cArr[random.nextInt(cArr.length)];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final void sortDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(cArr);
            reverse(cArr);
        }
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (char c : cArr) {
            destination.put(keySelector.invoke(Character.valueOf(c)), valueTransform.invoke(Character.valueOf(c)));
        }
        return destination;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull float[] fArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Float, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (float f : fArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Float.valueOf(f)));
            } else {
                buffer.append(String.valueOf(f));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Long maxByOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Long minByOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceIndexedOrNull(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return null;
        }
        boolean zBooleanValue = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                zBooleanValue = operation.invoke(Integer.valueOf(i), Boolean.valueOf(zBooleanValue), Boolean.valueOf(zArr[i])).booleanValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Boolean.valueOf(zBooleanValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceOrNull(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return null;
        }
        boolean zBooleanValue = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                zBooleanValue = operation.invoke(Boolean.valueOf(zBooleanValue), Boolean.valueOf(zArr[i])).booleanValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Boolean.valueOf(zBooleanValue);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = fArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            float f = fArr[length];
            fArr[length] = fArr[iNextInt];
            fArr[iNextInt] = f;
        }
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (float f : fArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Float.valueOf(f));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Boolean.valueOf(z));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @NotNull
    public static final List<Boolean> dropWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (boolean z2 : zArr) {
            if (z) {
                arrayList.add(Boolean.valueOf(z2));
            } else if (!predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z2));
                z = true;
            }
        }
        return arrayList;
    }

    public static final long first(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                return j;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxWithOrNull(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (dArr.length == 0) {
            return null;
        }
        double d = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                double d2 = dArr[i];
                if (comparator.compare(Double.valueOf(d), Double.valueOf(d2)) < 0) {
                    d = d2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(d);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minWithOrNull(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (dArr.length == 0) {
            return null;
        }
        double d = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                double d2 = dArr[i];
                if (comparator.compare(Double.valueOf(d), Double.valueOf(d2)) > 0) {
                    d = d2;
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
    public static final Pair<List<Float>, List<Float>> partition(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                arrayList.add(Float.valueOf(f));
            } else {
                arrayList2.add(Float.valueOf(f));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    public static final boolean reduce(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length != 0) {
            boolean zBooleanValue = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    zBooleanValue = operation.invoke(Boolean.valueOf(zBooleanValue), Boolean.valueOf(zArr[i])).booleanValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return zBooleanValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final boolean reduceIndexed(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length != 0) {
            boolean zBooleanValue = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    zBooleanValue = operation.invoke(Integer.valueOf(i), Boolean.valueOf(zBooleanValue), Boolean.valueOf(zArr[i])).booleanValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return zBooleanValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final void reverse(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = (zArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = zArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            boolean z = zArr[i];
            zArr[i] = zArr[length2];
            zArr[length2] = z;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(boolean[] zArr, R r, Function2<? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r);
        for (boolean z : zArr) {
            r = operation.invoke(r, Boolean.valueOf(z));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(boolean[] zArr, R r, Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r);
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Boolean.valueOf(zArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Float> runningReduce(float[] fArr, Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        float fFloatValue = fArr[0];
        ArrayList arrayList = new ArrayList(fArr.length);
        arrayList.add(Float.valueOf(fFloatValue));
        int length = fArr.length;
        for (int i = 1; i < length; i++) {
            fFloatValue = operation.invoke(Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
            arrayList.add(Float.valueOf(fFloatValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Float> runningReduceIndexed(float[] fArr, Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        float fFloatValue = fArr[0];
        ArrayList arrayList = new ArrayList(fArr.length);
        arrayList.add(Float.valueOf(fFloatValue));
        int length = fArr.length;
        for (int i = 1; i < length; i++) {
            fFloatValue = operation.invoke(Integer.valueOf(i), Float.valueOf(fFloatValue), Float.valueOf(fArr[i])).floatValue();
            arrayList.add(Float.valueOf(fFloatValue));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> slice(@NotNull boolean[] zArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(zArr, indices.first, indices.last + 1));
    }

    @NotNull
    public static short[] sliceArray(@NotNull short[] sArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new short[0] : ArraysKt___ArraysJvmKt.copyOfRange(sArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull short[] sArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Short, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Short.valueOf(sArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associateBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (T t : tArr) {
            linkedHashMap.put(keySelector.invoke(t), valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> List<Double> distinctBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (double d : dArr) {
            if (hashSet.add(selector.invoke(Double.valueOf(d)))) {
                arrayList.add(Double.valueOf(d));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> drop(@NotNull long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i >= 0) {
            int length = jArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(jArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Long> dropLast(@NotNull long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i >= 0) {
            int length = jArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(jArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Long>>> M groupByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (long j : jArr) {
            K kInvoke = keySelector.invoke(Long.valueOf(j));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Long.valueOf(j));
        }
        return destination;
    }

    public static final <T> T last(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                T t = tArr[length];
                if (!predicate.invoke(t).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return t;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            double dDoubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Double.valueOf(dArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m959maxOfOrNull(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Double.valueOf(dArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final double maxWithOrThrow(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (dArr.length != 0) {
            double d = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    double d2 = dArr[i];
                    if (comparator.compare(Double.valueOf(d), Double.valueOf(d2)) < 0) {
                        d = d2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return d;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            double dDoubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Double.valueOf(dArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m995minOfOrNull(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Double.valueOf(dArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final double minWithOrThrow(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (dArr.length != 0) {
            double d = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    double d2 = dArr[i];
                    if (comparator.compare(Double.valueOf(d), Double.valueOf(d2)) > 0) {
                        d = d2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return d;
        }
        throw new NoSuchElementException();
    }

    public static final <T> T single(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        T t = null;
        boolean z = false;
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                t = t2;
                z = true;
            }
        }
        if (z) {
            return t;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> void sortDescending(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        ArraysKt___ArraysJvmKt.sortWith(tArr, ComparisonsKt__ComparisonsKt.reverseOrder(), i, i2);
    }

    public static final float first(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                return f;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        float fMax = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fMax = Math.max(fMax, fArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fMax);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        float fMin = fArr[0];
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fMin = Math.min(fMin, fArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fMin);
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final float maxOrThrow(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            float fMax = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fMax = Math.max(fMax, fArr[i]);
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fMax;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final float minOrThrow(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length != 0) {
            float fMin = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fMin = Math.min(fMin, fArr[i]);
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return fMin;
        }
        throw new NoSuchElementException();
    }

    public static final double first(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                return d;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceIndexedOrNull(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return null;
        }
        char cCharValue = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                cCharValue = operation.invoke(Integer.valueOf(i), Character.valueOf(cCharValue), Character.valueOf(cArr[i])).charValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceOrNull(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return null;
        }
        char cCharValue = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                cCharValue = operation.invoke(Character.valueOf(cCharValue), Character.valueOf(cArr[i])).charValue();
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(cCharValue);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = dArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            double d = dArr[length];
            dArr[length] = dArr[iNextInt];
            dArr[iNextInt] = d;
        }
    }

    @NotNull
    public static int[] sliceArray(@NotNull int[] iArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new int[0] : ArraysKt___ArraysJvmKt.copyOfRange(iArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull int[] iArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Integer, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (byte b : bArr) {
            linkedHashMap.put(keySelector.invoke(Byte.valueOf(b)), valueTransform.invoke(Byte.valueOf(b)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c : cArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Character.valueOf(c));
            destination.put(pairInvoke.first, pairInvoke.second);
        }
        return destination;
    }

    @NotNull
    public static final List<Character> dropWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (char c : cArr) {
            if (z) {
                arrayList.add(Character.valueOf(c));
            } else if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                arrayList.add(Character.valueOf(c));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, List<Long>> groupBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j : jArr) {
            K kInvoke = keySelector.invoke(Long.valueOf(j));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Long.valueOf(j));
        }
        return linkedHashMap;
    }

    public static final byte last(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                byte b = bArr[length];
                if (!predicate.invoke(Byte.valueOf(b)).booleanValue()) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> float maxByOrThrow(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            float f = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (length != 0) {
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
            }
            return f;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R maxOfWithOrNull(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> float minByOrThrow(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            float f = fArr[0];
            int i = 1;
            int length = fArr.length - 1;
            if (length != 0) {
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
            }
            return f;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R> R minOfWithOrNull(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                if (comparator.compare(rInvoke, rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final char reduce(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length != 0) {
            char cCharValue = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    cCharValue = operation.invoke(Character.valueOf(cCharValue), Character.valueOf(cArr[i])).charValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return cCharValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final char reduceIndexed(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length != 0) {
            char cCharValue = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    cCharValue = operation.invoke(Integer.valueOf(i), Character.valueOf(cCharValue), Character.valueOf(cArr[i])).charValue();
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return cCharValue;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final void reverse(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = (cArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int length2 = cArr.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            char c = cArr[i];
            cArr[i] = cArr[length2];
            cArr[length2] = c;
            length2--;
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFold(char[] cArr, R r, Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r);
        for (char c : cArr) {
            r = operation.invoke(r, Character.valueOf(c));
            arrayList.add(r);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final <R> List<R> runningFoldIndexed(char[] cArr, R r, Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return CollectionsKt__CollectionsJVMKt.listOf(r);
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r);
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            r = operation.invoke(Integer.valueOf(i), r, Character.valueOf(cArr[i]));
            arrayList.add(r);
        }
        return arrayList;
    }

    public static final byte single(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Byte bValueOf = null;
        boolean z = false;
        for (byte b : bArr) {
            if (predicate.invoke(Byte.valueOf(b)).booleanValue()) {
                if (!z) {
                    bValueOf = Byte.valueOf(b);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(bValueOf, "null cannot be cast to non-null type kotlin.Byte");
            return bValueOf.byteValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final List<Character> slice(@NotNull char[] cArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(cArr, indices.first, indices.last + 1));
    }

    public static final boolean first(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return z;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull double[] dArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Double, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (double d : dArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Double.valueOf(d)));
            } else {
                buffer.append(String.valueOf(d));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean maxWithOrNull(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                boolean z2 = zArr[i];
                if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) < 0) {
                    z = z2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean minWithOrNull(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                boolean z2 = zArr[i];
                if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) > 0) {
                    z = z2;
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
    public static final <K, V> Map<K, V> associate(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (double d : dArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Double.valueOf(d));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> List<Boolean> distinctBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (hashSet.add(selector.invoke(Boolean.valueOf(z)))) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            double dDoubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Boolean.valueOf(zArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m965maxOfOrNull(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Boolean.valueOf(zArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final boolean maxWithOrThrow(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (zArr.length != 0) {
            boolean z = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    boolean z2 = zArr[i];
                    if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) < 0) {
                        z = z2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return z;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            double dDoubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Boolean.valueOf(zArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m1001minOfOrNull(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Boolean.valueOf(zArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final boolean minWithOrThrow(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (zArr.length != 0) {
            boolean z = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    boolean z2 = zArr[i];
                    if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) > 0) {
                        z = z2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return z;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final Pair<List<Double>, List<Double>> partition(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                arrayList.add(Double.valueOf(d));
            } else {
                arrayList2.add(Double.valueOf(d));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Double> runningReduce(double[] dArr, Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        double dDoubleValue = dArr[0];
        ArrayList arrayList = new ArrayList(dArr.length);
        arrayList.add(Double.valueOf(dDoubleValue));
        int length = dArr.length;
        for (int i = 1; i < length; i++) {
            dDoubleValue = operation.invoke(Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
            arrayList.add(Double.valueOf(dDoubleValue));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Double> runningReduceIndexed(double[] dArr, Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        double dDoubleValue = dArr[0];
        ArrayList arrayList = new ArrayList(dArr.length);
        arrayList.add(Double.valueOf(dDoubleValue));
        int length = dArr.length;
        for (int i = 1; i < length; i++) {
            dDoubleValue = operation.invoke(Integer.valueOf(i), Double.valueOf(dDoubleValue), Double.valueOf(dArr[i])).doubleValue();
            arrayList.add(Double.valueOf(dDoubleValue));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> take(@NotNull float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= fArr.length) {
            return toList(fArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> takeLast(@NotNull float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = fArr.length;
        if (i >= length) {
            return toList(fArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Float.valueOf(fArr[i2]));
        }
        return arrayList;
    }

    public static final char first(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                return c;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Float maxByOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        double dMax = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dMax = Math.max(dMax, dArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dMax);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Float minByOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        double dMin = dArr[0];
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                dMin = Math.min(dMin, dArr[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Double.valueOf(dMin);
    }

    @NotNull
    public static long[] sliceArray(@NotNull long[] jArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new long[0] : ArraysKt___ArraysJvmKt.copyOfRange(jArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull long[] jArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Long, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Long.valueOf(jArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (short s : sArr) {
            linkedHashMap.put(keySelector.invoke(Short.valueOf(s)), valueTransform.invoke(Short.valueOf(s)));
        }
        return linkedHashMap;
    }

    public static final short last(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                short s = sArr[length];
                if (!predicate.invoke(Short.valueOf(s)).booleanValue()) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final double maxOrThrow(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length != 0) {
            double dMax = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dMax = Math.max(dMax, dArr[i]);
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dMax;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final double minOrThrow(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length != 0) {
            double dMin = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dMin = Math.min(dMin, dArr[i]);
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dMin;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = zArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            boolean z = zArr[length];
            zArr[length] = zArr[iNextInt];
            zArr[iNextInt] = z;
        }
    }

    @NotNull
    public static final List<Float> drop(@NotNull float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i >= 0) {
            int length = fArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(fArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Float> dropLast(@NotNull float[] fArr, int i) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i >= 0) {
            int length = fArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(fArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Float>>> M groupByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (float f : fArr) {
            K kInvoke = keySelector.invoke(Float.valueOf(f));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Float.valueOf(f));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void reverse(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, tArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            T t = tArr[i];
            tArr[i] = tArr[i4];
            tArr[i4] = t;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull T[] tArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(tArr[it.next().intValue()]);
        }
        return arrayList;
    }

    public static final short single(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Short shValueOf = null;
        boolean z = false;
        for (short s : sArr) {
            if (predicate.invoke(Short.valueOf(s)).booleanValue()) {
                if (!z) {
                    shValueOf = Short.valueOf(s);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(shValueOf, "null cannot be cast to non-null type kotlin.Short");
            return shValueOf.shortValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxWithOrNull(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                if (comparator.compare(Character.valueOf(c), Character.valueOf(c2)) < 0) {
                    c = c2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(c);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minWithOrNull(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                if (comparator.compare(Character.valueOf(c), Character.valueOf(c2)) > 0) {
                    c = c2;
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
    public static final float[] sliceArray(@NotNull float[] fArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new float[0] : ArraysKt___ArraysJvmKt.copyOfRange(fArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull float[] fArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Float, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Float.valueOf(fArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (int i : iArr) {
            linkedHashMap.put(keySelector.invoke(Integer.valueOf(i)), valueTransform.invoke(Integer.valueOf(i)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> List<Character> distinctBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (char c : cArr) {
            if (hashSet.add(selector.invoke(Character.valueOf(c)))) {
                arrayList.add(Character.valueOf(c));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull boolean[] zArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Boolean, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (boolean z : zArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Boolean.valueOf(z)));
            } else {
                buffer.append(String.valueOf(z));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final int last(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                int i2 = iArr[length];
                if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
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

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> double maxByOrThrow(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            double d = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (length != 0) {
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
            }
            return d;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double maxOf(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            double dDoubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.max(dDoubleValue, selector.invoke(Character.valueOf(cArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Double m958maxOfOrNull(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.max(dDoubleValue, selector.invoke(Character.valueOf(cArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R maxOfWith(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxWithOrThrow")
    public static final char maxWithOrThrow(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    char c2 = cArr[i];
                    if (comparator.compare(Character.valueOf(c), Character.valueOf(c2)) < 0) {
                        c = c2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> double minByOrThrow(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            double d = dArr[0];
            int i = 1;
            int length = dArr.length - 1;
            if (length != 0) {
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
            }
            return d;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final double minOf(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            double dDoubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    dDoubleValue = Math.min(dDoubleValue, selector.invoke(Character.valueOf(cArr[i])).doubleValue());
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return dDoubleValue;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Double m994minOfOrNull(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        double dDoubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                dDoubleValue = Math.min(dDoubleValue, selector.invoke(Character.valueOf(cArr[i])).doubleValue());
                if (i == length) {
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
    public static final <R> R minOfWith(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                    if (comparator.compare(rInvoke, rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minWithOrThrow")
    public static final char minWithOrThrow(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    char c2 = cArr[i];
                    if (comparator.compare(Character.valueOf(c), Character.valueOf(c2)) > 0) {
                        c = c2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (boolean z : zArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Boolean.valueOf(z));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                if (Intrinsics.compare((int) c, (int) c2) < 0) {
                    c = c2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(c);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        char c = cArr[0];
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                char c2 = cArr[i];
                if (Intrinsics.compare((int) c, (int) c2) > 0) {
                    c = c2;
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
    public static final Pair<List<Boolean>, List<Boolean>> partition(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            } else {
                arrayList2.add(Boolean.valueOf(z));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Boolean> runningReduce(boolean[] zArr, Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        boolean z = zArr[0];
        ArrayList arrayList = new ArrayList(zArr.length);
        arrayList.add(Boolean.valueOf(z));
        int length = zArr.length;
        int i = 1;
        while (i < length) {
            Boolean boolInvoke = operation.invoke(Boolean.valueOf(z), Boolean.valueOf(zArr[i]));
            boolean zBooleanValue = boolInvoke.booleanValue();
            arrayList.add(boolInvoke);
            i++;
            z = zBooleanValue;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Boolean> runningReduceIndexed(boolean[] zArr, Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        boolean z = zArr[0];
        ArrayList arrayList = new ArrayList(zArr.length);
        arrayList.add(Boolean.valueOf(z));
        int length = zArr.length;
        int i = 1;
        while (i < length) {
            Boolean boolInvoke = operation.invoke(Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(zArr[i]));
            boolean zBooleanValue = boolInvoke.booleanValue();
            arrayList.add(boolInvoke);
            i++;
            z = zBooleanValue;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int length = cArr.length - 1; length > 0; length--) {
            int iNextInt = random.nextInt(length + 1);
            char c = cArr[length];
            cArr[length] = cArr[iNextInt];
            cArr[iNextInt] = c;
        }
    }

    @NotNull
    public static final <K> Map<K, List<Float>> groupBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (float f : fArr) {
            K kInvoke = keySelector.invoke(Float.valueOf(f));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Float.valueOf(f));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxOrThrow")
    public static final char maxOrThrow(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    char c2 = cArr[i];
                    if (Intrinsics.compare((int) c, (int) c2) < 0) {
                        c = c2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minOrThrow")
    public static final char minOrThrow(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    char c2 = cArr[i];
                    if (Intrinsics.compare((int) c, (int) c2) > 0) {
                        c = c2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, bArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            byte b = bArr[i];
            bArr[i] = bArr[i4];
            bArr[i4] = b;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Byte> slice(@NotNull byte[] bArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf(bArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final double[] sliceArray(@NotNull double[] dArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new double[0] : ArraysKt___ArraysJvmKt.copyOfRange(dArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull double[] dArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Double, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Double.valueOf(dArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (long j : jArr) {
            linkedHashMap.put(keySelector.invoke(Long.valueOf(j)), valueTransform.invoke(Long.valueOf(j)));
        }
        return linkedHashMap;
    }

    public static final long last(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                long j = jArr[length];
                if (!predicate.invoke(Long.valueOf(j)).booleanValue()) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Double maxByOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Double minByOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
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

    public static final int single(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Integer numValueOf = null;
        boolean z = false;
        for (int i : iArr) {
            if (predicate.invoke(Integer.valueOf(i)).booleanValue()) {
                if (!z) {
                    numValueOf = Integer.valueOf(i);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(numValueOf, "null cannot be cast to non-null type kotlin.Int");
            return numValueOf.intValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final List<Double> drop(@NotNull double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i >= 0) {
            int length = dArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(dArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Double> dropLast(@NotNull double[] dArr, int i) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i >= 0) {
            int length = dArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(dArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Double>>> M groupByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (double d : dArr) {
            K kInvoke = keySelector.invoke(Double.valueOf(d));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Double.valueOf(d));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T> float m945maxOf(T[] tArr, Function1<? super T, Float> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length != 0) {
            float fFloatValue = selector.invoke(tArr[0]).floatValue();
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(tArr[i]).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final <T> Float m972maxOfOrNull(T[] tArr, Function1<? super T, Float> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(tArr[0]).floatValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(tArr[i]).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T> float m981minOf(T[] tArr, Function1<? super T, Float> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length != 0) {
            float fFloatValue = selector.invoke(tArr[0]).floatValue();
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(tArr[i]).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final <T> Float m1008minOfOrNull(T[] tArr, Function1<? super T, Float> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(tArr[0]).floatValue();
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(tArr[i]).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull char[] cArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i, @NotNull CharSequence truncated, @Nullable Function1<? super Character, ? extends CharSequence> function1) throws IOException {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (char c : cArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Character.valueOf(c)));
            } else {
                buffer.append(c);
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, sArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            short s = sArr[i];
            sArr[i] = sArr[i4];
            sArr[i4] = s;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Short> slice(@NotNull short[] sArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Short.valueOf(sArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final boolean[] sliceArray(@NotNull boolean[] zArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new boolean[0] : ArraysKt___ArraysJvmKt.copyOfRange(zArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull boolean[] zArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Boolean, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (char c : cArr) {
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Character.valueOf(c));
            linkedHashMap.put(pairInvoke.first, pairInvoke.second);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (float f : fArr) {
            linkedHashMap.put(keySelector.invoke(Float.valueOf(f)), valueTransform.invoke(Float.valueOf(f)));
        }
        return linkedHashMap;
    }

    public static final float last(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                float f = fArr[length];
                if (!predicate.invoke(Float.valueOf(f)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return f;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> boolean maxByOrThrow(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            boolean z = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (length != 0) {
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
            }
            return z;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> boolean minByOrThrow(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            boolean z = zArr[0];
            int i = 1;
            int length = zArr.length - 1;
            if (length != 0) {
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
            }
            return z;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final Pair<List<Character>, List<Character>> partition(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                arrayList.add(Character.valueOf(c));
            } else {
                arrayList2.add(Character.valueOf(c));
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Character> runningReduce(char[] cArr, Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        char c = cArr[0];
        ArrayList arrayList = new ArrayList(cArr.length);
        arrayList.add(Character.valueOf(c));
        int length = cArr.length;
        int i = 1;
        while (i < length) {
            Character chInvoke = operation.invoke(Character.valueOf(c), Character.valueOf(cArr[i]));
            char cCharValue = chInvoke.charValue();
            arrayList.add(chInvoke);
            i++;
            c = cCharValue;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    public static final List<Character> runningReduceIndexed(char[] cArr, Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            return EmptyList.INSTANCE;
        }
        char c = cArr[0];
        ArrayList arrayList = new ArrayList(cArr.length);
        arrayList.add(Character.valueOf(c));
        int length = cArr.length;
        int i = 1;
        while (i < length) {
            Character chInvoke = operation.invoke(Integer.valueOf(i), Character.valueOf(c), Character.valueOf(cArr[i]));
            char cCharValue = chInvoke.charValue();
            arrayList.add(chInvoke);
            i++;
            c = cCharValue;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> take(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= iArr.length) {
            return toList(iArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (int i3 : iArr) {
            arrayList.add(Integer.valueOf(i3));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> takeLast(@NotNull int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = iArr.length;
        if (i >= length) {
            return toList(iArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Integer.valueOf(iArr[i2]));
        }
        return arrayList;
    }

    public static final long single(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Long lValueOf = null;
        boolean z = false;
        for (long j : jArr) {
            if (predicate.invoke(Long.valueOf(j)).booleanValue()) {
                if (!z) {
                    lValueOf = Long.valueOf(j);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(lValueOf, "null cannot be cast to non-null type kotlin.Long");
            return lValueOf.longValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final char[] sliceArray(@NotNull char[] cArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new char[0] : ArraysKt___ArraysJvmKt.copyOfRange(cArr, indices.first, indices.last + 1);
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull char[] cArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Character, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(transform.invoke(Character.valueOf(cArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (double d : dArr) {
            linkedHashMap.put(keySelector.invoke(Double.valueOf(d)), valueTransform.invoke(Double.valueOf(d)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Double>> groupBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (double d : dArr) {
            K kInvoke = keySelector.invoke(Double.valueOf(d));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Double.valueOf(d));
        }
        return linkedHashMap;
    }

    public static final double last(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                double d = dArr[length];
                if (!predicate.invoke(Double.valueOf(d)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return d;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m939maxOf(byte[] bArr, Function1<? super Byte, Float> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            float fFloatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Byte.valueOf(bArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m966maxOfOrNull(byte[] bArr, Function1<? super Byte, Float> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Byte.valueOf(bArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m975minOf(byte[] bArr, Function1<? super Byte, Float> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            float fFloatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Byte.valueOf(bArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1002minOfOrNull(byte[] bArr, Function1<? super Byte, Float> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Byte.valueOf(bArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, iArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            int i5 = iArr[i];
            iArr[i] = iArr[i4];
            iArr[i4] = i5;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Integer> slice(@NotNull int[] iArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(iArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Boolean maxByOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Boolean minByOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
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
    public static final List<Boolean> drop(@NotNull boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i >= 0) {
            int length = zArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(zArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Boolean> dropLast(@NotNull boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i >= 0) {
            int length = zArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(zArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Boolean>>> M groupByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (boolean z : zArr) {
            K kInvoke = keySelector.invoke(Boolean.valueOf(z));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Boolean.valueOf(z));
        }
        return destination;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull byte[] bArr, @NotNull byte[] other, @NotNull Function2<? super Byte, ? super Byte, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i]), Byte.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (boolean z : zArr) {
            linkedHashMap.put(keySelector.invoke(Boolean.valueOf(z)), valueTransform.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    public static final boolean last(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                boolean z = zArr[length];
                if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return z;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "maxByOrThrow")
    public static final <R extends Comparable<? super R>> char maxByOrThrow(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (length != 0) {
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
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.7")
    @JvmName(name = "minByOrThrow")
    public static final <R extends Comparable<? super R>> char minByOrThrow(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            char c = cArr[0];
            int i = 1;
            int length = cArr.length - 1;
            if (length != 0) {
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
            }
            return c;
        }
        throw new NoSuchElementException();
    }

    public static final float single(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Float fValueOf = null;
        boolean z = false;
        for (float f : fArr) {
            if (predicate.invoke(Float.valueOf(f)).booleanValue()) {
                if (!z) {
                    fValueOf = Float.valueOf(f);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(fValueOf, "null cannot be cast to non-null type kotlin.Float");
            return fValueOf.floatValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, jArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            long j = jArr[i];
            jArr[i] = jArr[i4];
            jArr[i4] = j;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Long> slice(@NotNull long[] jArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(jArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m946maxOf(short[] sArr, Function1<? super Short, Float> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            float fFloatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Short.valueOf(sArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m973maxOfOrNull(short[] sArr, Function1<? super Short, Float> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Short.valueOf(sArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m982minOf(short[] sArr, Function1<? super Short, Float> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            float fFloatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Short.valueOf(sArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1009minOfOrNull(short[] sArr, Function1<? super Short, Float> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Short.valueOf(sArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull short[] sArr, @NotNull short[] other, @NotNull Function2<? super Short, ? super Short, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Short.valueOf(sArr[i]), Short.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (char c : cArr) {
            linkedHashMap.put(keySelector.invoke(Character.valueOf(c)), valueTransform.invoke(Character.valueOf(c)));
        }
        return linkedHashMap;
    }

    public static final char last(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i = length - 1;
                char c = cArr[length];
                if (!predicate.invoke(Character.valueOf(c)).booleanValue()) {
                    if (i < 0) {
                        break;
                    }
                    length = i;
                } else {
                    return c;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull int[] iArr, @NotNull int[] other, @NotNull Function2<? super Integer, ? super Integer, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i]), Integer.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, List<Boolean>> groupBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (boolean z : zArr) {
            K kInvoke = keySelector.invoke(Boolean.valueOf(z));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Boolean.valueOf(z));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character maxByOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character minByOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
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

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, fArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            float f = fArr[i];
            fArr[i] = fArr[i4];
            fArr[i4] = f;
            i4--;
            i++;
        }
    }

    public static final double single(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Double dValueOf = null;
        boolean z = false;
        for (double d : dArr) {
            if (predicate.invoke(Double.valueOf(d)).booleanValue()) {
                if (!z) {
                    dValueOf = Double.valueOf(d);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(dValueOf, "null cannot be cast to non-null type kotlin.Double");
            return dValueOf.doubleValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @NotNull
    public static final List<Float> slice(@NotNull float[] fArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(fArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> take(@NotNull long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= jArr.length) {
            return toList(jArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (long j : jArr) {
            arrayList.add(Long.valueOf(j));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> takeLast(@NotNull long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = jArr.length;
        if (i >= length) {
            return toList(jArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Long.valueOf(jArr[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> drop(@NotNull char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i >= 0) {
            int length = cArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return takeLast(cArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final List<Character> dropLast(@NotNull char[] cArr, int i) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i >= 0) {
            int length = cArr.length - i;
            if (length < 0) {
                length = 0;
            }
            return take(cArr, length);
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (char c : cArr) {
            K kInvoke = keySelector.invoke(Character.valueOf(c));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(Character.valueOf(c));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m943maxOf(int[] iArr, Function1<? super Integer, Float> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            float fFloatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Integer.valueOf(iArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m970maxOfOrNull(int[] iArr, Function1<? super Integer, Float> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Integer.valueOf(iArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m979minOf(int[] iArr, Function1<? super Integer, Float> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            float fFloatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Integer.valueOf(iArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1006minOfOrNull(int[] iArr, Function1<? super Integer, Float> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Integer.valueOf(iArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull long[] jArr, @NotNull long[] other, @NotNull Function2<? super Long, ? super Long, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Long.valueOf(jArr[i]), Long.valueOf(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, dArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            double d = dArr[i];
            dArr[i] = dArr[i4];
            dArr[i4] = d;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Double> slice(@NotNull double[] dArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(dArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull float[] fArr, @NotNull float[] other, @NotNull Function2<? super Float, ? super Float, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Float.valueOf(fArr[i]), Float.valueOf(other[i])));
        }
        return arrayList;
    }

    public static final boolean single(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Boolean boolValueOf = null;
        boolean z = false;
        for (boolean z2 : zArr) {
            if (predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                if (!z) {
                    boolValueOf = Boolean.valueOf(z2);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(boolValueOf, "null cannot be cast to non-null type kotlin.Boolean");
            return boolValueOf.booleanValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m944maxOf(long[] jArr, Function1<? super Long, Float> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            float fFloatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Long.valueOf(jArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m971maxOfOrNull(long[] jArr, Function1<? super Long, Float> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Long.valueOf(jArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m980minOf(long[] jArr, Function1<? super Long, Float> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            float fFloatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Long.valueOf(jArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1007minOfOrNull(long[] jArr, Function1<? super Long, Float> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Long.valueOf(jArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull double[] dArr, @NotNull double[] other, @NotNull Function2<? super Double, ? super Double, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Double.valueOf(dArr[i]), Double.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (char c : cArr) {
            K kInvoke = keySelector.invoke(Character.valueOf(c));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(Character.valueOf(c));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull boolean[] zArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, zArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            boolean z = zArr[i];
            zArr[i] = zArr[i4];
            zArr[i4] = z;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Boolean> slice(@NotNull boolean[] zArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(zArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t : tArr) {
            K kInvoke = keySelector.invoke(t);
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return destination;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull boolean[] zArr, @NotNull boolean[] other, @NotNull Function2<? super Boolean, ? super Boolean, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i]), Boolean.valueOf(other[i])));
        }
        return arrayList;
    }

    public static final char single(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Character chValueOf = null;
        boolean z = false;
        for (char c : cArr) {
            if (predicate.invoke(Character.valueOf(c)).booleanValue()) {
                if (!z) {
                    chValueOf = Character.valueOf(c);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
            }
        }
        if (z) {
            Intrinsics.checkNotNull(chValueOf, "null cannot be cast to non-null type kotlin.Char");
            return chValueOf.charValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m942maxOf(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            float fFloatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Float.valueOf(fArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m969maxOfOrNull(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Float.valueOf(fArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m978minOf(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            float fFloatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Float.valueOf(fArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1005minOfOrNull(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Float.valueOf(fArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <T> List<T> take(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= tArr.length) {
            return toList(tArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(tArr[0]);
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (T t : tArr) {
            arrayList.add(t);
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> takeLast(@NotNull T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = tArr.length;
        if (i >= length) {
            return toList(tArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(tArr[length - 1]);
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(tArr[i2]);
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull char[] cArr, @NotNull char[] other, @NotNull Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int iMin = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(transform.invoke(Character.valueOf(cArr[i]), Character.valueOf(other[i])));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, cArr.length);
        int i3 = (i + i2) / 2;
        if (i == i3) {
            return;
        }
        int i4 = i2 - 1;
        while (i < i3) {
            char c = cArr[i];
            cArr[i] = cArr[i4];
            cArr[i4] = c;
            i4--;
            i++;
        }
    }

    @NotNull
    public static final List<Character> slice(@NotNull char[] cArr, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int iCollectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (iCollectionSizeOrDefault == 0) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            arrayList.add(Character.valueOf(cArr[it.next().intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@NotNull T[] tArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(tArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(tArr[i], other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K, V> Map<K, List<V>> groupBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t : tArr) {
            K kInvoke = keySelector.invoke(t);
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(t));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (byte b : bArr) {
            K kInvoke = keySelector.invoke(Byte.valueOf(b));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Byte.valueOf(b)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m941maxOf(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            float fFloatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Double.valueOf(dArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m968maxOfOrNull(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Double.valueOf(dArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m977minOf(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            float fFloatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Double.valueOf(dArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1004minOfOrNull(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Double.valueOf(dArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <R> List<Pair<Byte, R>> zip(@NotNull byte[] bArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            byte b = bArr[i];
            arrayList.add(new Pair(Byte.valueOf(b), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m947maxOf(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            float fFloatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Boolean.valueOf(zArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m974maxOfOrNull(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Boolean.valueOf(zArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m983minOf(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            float fFloatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Boolean.valueOf(zArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1010minOfOrNull(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Boolean.valueOf(zArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final List<Short> take(@NotNull short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= sArr.length) {
            return toList(sArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (short s : sArr) {
            arrayList.add(Short.valueOf(s));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> takeLast(@NotNull short[] sArr, int i) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = sArr.length;
        if (i >= length) {
            return toList(sArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Short.valueOf(sArr[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Short, R>> zip(@NotNull short[] sArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            short s = sArr[i];
            arrayList.add(new Pair(Short.valueOf(s), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (short s : sArr) {
            K kInvoke = keySelector.invoke(Short.valueOf(s));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Short.valueOf(s)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b : bArr) {
            K kInvoke = keySelector.invoke(Byte.valueOf(b));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Byte.valueOf(b)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final float m940maxOf(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            float fFloatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.max(fFloatValue, selector.invoke(Character.valueOf(cArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOfOrNull, reason: collision with other method in class */
    public static final Float m967maxOfOrNull(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.max(fFloatValue, selector.invoke(Character.valueOf(cArr[i])).floatValue());
                if (i == length) {
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
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final float m976minOf(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            float fFloatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    fFloatValue = Math.min(fFloatValue, selector.invoke(Character.valueOf(cArr[i])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOfOrNull, reason: collision with other method in class */
    public static final Float m1003minOfOrNull(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        float fFloatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                fFloatValue = Math.min(fFloatValue, selector.invoke(Character.valueOf(cArr[i])).floatValue());
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return Float.valueOf(fFloatValue);
    }

    @NotNull
    public static final <R> List<Pair<Integer, R>> zip(@NotNull int[] iArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            int i2 = iArr[i];
            arrayList.add(new Pair(Integer.valueOf(i2), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i : iArr) {
            K kInvoke = keySelector.invoke(Integer.valueOf(i));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Integer.valueOf(i)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m954maxOf(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length != 0) {
            R rInvoke = selector.invoke(tArr[0]);
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(tArr[i]);
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R maxOfOrNull(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <T, R extends Comparable<? super R>> R m990minOf(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length != 0) {
            R rInvoke = selector.invoke(tArr[0]);
            int i = 1;
            int length = tArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(tArr[i]);
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <T, R extends Comparable<? super R>> R minOfOrNull(T[] tArr, Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(tArr[0]);
        int i = 1;
        int length = tArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(tArr[i]);
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s : sArr) {
            K kInvoke = keySelector.invoke(Short.valueOf(s));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Short.valueOf(s)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <R> List<Pair<Long, R>> zip(@NotNull long[] jArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            long j = jArr[i];
            arrayList.add(new Pair(Long.valueOf(j), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> take(@NotNull boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        if (i >= zArr.length) {
            return toList(zArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[0]));
        }
        ArrayList arrayList = new ArrayList(i);
        int i2 = 0;
        for (boolean z : zArr) {
            arrayList.add(Boolean.valueOf(z));
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> takeLast(@NotNull boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Requested element count ", i, " is less than zero.").toString());
        }
        if (i == 0) {
            return EmptyList.INSTANCE;
        }
        int length = zArr.length;
        if (i >= length) {
            return toList(zArr);
        }
        if (i == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[length - 1]));
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(Boolean.valueOf(zArr[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
        int i = 1;
        int length = bArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m948maxOf(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m984minOf(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (bArr.length != 0) {
            R rInvoke = selector.invoke(Byte.valueOf(bArr[0]));
            int i = 1;
            int length = bArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Byte.valueOf(bArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <R> List<Pair<Float, R>> zip(@NotNull float[] fArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            float f = fArr[i];
            arrayList.add(new Pair(Float.valueOf(f), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (long j : jArr) {
            K kInvoke = keySelector.invoke(Long.valueOf(j));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Long.valueOf(j)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
        int i = 1;
        int length = sArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i : iArr) {
            K kInvoke = keySelector.invoke(Integer.valueOf(i));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Integer.valueOf(i)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m955maxOf(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m991minOf(short[] sArr, Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (sArr.length != 0) {
            R rInvoke = selector.invoke(Short.valueOf(sArr[0]));
            int i = 1;
            int length = sArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Short.valueOf(sArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <R> List<Pair<Double, R>> zip(@NotNull double[] dArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            double d = dArr[i];
            arrayList.add(new Pair(Double.valueOf(d), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
        int i = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (float f : fArr) {
            K kInvoke = keySelector.invoke(Float.valueOf(f));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Float.valueOf(f)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m952maxOf(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m988minOf(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (iArr.length != 0) {
            R rInvoke = selector.invoke(Integer.valueOf(iArr[0]));
            int i = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Integer.valueOf(iArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
        int i = 1;
        int length = jArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <R> List<Pair<Boolean, R>> zip(@NotNull boolean[] zArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            boolean z = zArr[i];
            arrayList.add(new Pair(Boolean.valueOf(z), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j : jArr) {
            K kInvoke = keySelector.invoke(Long.valueOf(j));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Long.valueOf(j)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m953maxOf(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m989minOf(long[] jArr, Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (jArr.length != 0) {
            R rInvoke = selector.invoke(Long.valueOf(jArr[0]));
            int i = 1;
            int length = jArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Long.valueOf(jArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
        int i = 1;
        int length = fArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <R> List<Pair<Character, R>> zip(@NotNull char[] cArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            char c = cArr[i];
            arrayList.add(new Pair(Character.valueOf(c), other[i]));
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (double d : dArr) {
            K kInvoke = keySelector.invoke(Double.valueOf(d));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Double.valueOf(d)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m951maxOf(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m987minOf(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (fArr.length != 0) {
            R rInvoke = selector.invoke(Float.valueOf(fArr[0]));
            int i = 1;
            int length = fArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Float.valueOf(fArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
        int i = 1;
        int length = dArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (float f : fArr) {
            K kInvoke = keySelector.invoke(Float.valueOf(f));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Float.valueOf(f)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, R> List<Pair<T, R>> zip(@NotNull T[] tArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = tArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(tArr[i], r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int i = 1;
        int length = zArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (boolean z : zArr) {
            K kInvoke = keySelector.invoke(Boolean.valueOf(z));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m950maxOf(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m986minOf(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (dArr.length != 0) {
            R rInvoke = selector.invoke(Double.valueOf(dArr[0]));
            int i = 1;
            int length = dArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Double.valueOf(dArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <R> List<Pair<Byte, R>> zip(@NotNull byte[] bArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = bArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Byte.valueOf(bArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R maxOfOrNull(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    public static final <R extends Comparable<? super R>> R minOfOrNull(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length == 0) {
            return null;
        }
        R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
        int i = 1;
        int length = cArr.length - 1;
        if (1 <= length) {
            while (true) {
                R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (double d : dArr) {
            K kInvoke = keySelector.invoke(Double.valueOf(d));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Double.valueOf(d)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m956maxOf(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m992minOf(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (zArr.length != 0) {
            R rInvoke = selector.invoke(Boolean.valueOf(zArr[0]));
            int i = 1;
            int length = zArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Boolean.valueOf(zArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (char c : cArr) {
            K kInvoke = keySelector.invoke(Character.valueOf(c));
            Object objM = destination.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline0.m(destination, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Character.valueOf(c)));
        }
        return destination;
    }

    @NotNull
    public static final <R> List<Pair<Short, R>> zip(@NotNull short[] sArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = sArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Short.valueOf(sArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: maxOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m949maxOf(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                    if (rInvoke.compareTo(rInvoke2) < 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* JADX INFO: renamed from: minOf, reason: collision with other method in class */
    public static final <R extends Comparable<? super R>> R m985minOf(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (cArr.length != 0) {
            R rInvoke = selector.invoke(Character.valueOf(cArr[0]));
            int i = 1;
            int length = cArr.length - 1;
            if (1 <= length) {
                while (true) {
                    R rInvoke2 = selector.invoke(Character.valueOf(cArr[i]));
                    if (rInvoke.compareTo(rInvoke2) > 0) {
                        rInvoke = rInvoke2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return rInvoke;
        }
        throw new NoSuchElementException();
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (boolean z : zArr) {
            K kInvoke = keySelector.invoke(Boolean.valueOf(z));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <R> List<Pair<Integer, R>> zip(@NotNull int[] iArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = iArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Integer.valueOf(iArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Long, R>> zip(@NotNull long[] jArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = jArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Long.valueOf(jArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (char c : cArr) {
            K kInvoke = keySelector.invoke(Character.valueOf(c));
            Object objM = linkedHashMap.get(kInvoke);
            if (objM == null) {
                objM = ArraysKt___ArraysKt$$ExternalSyntheticOutline1.m(linkedHashMap, kInvoke);
            }
            ((List) objM).add(valueTransform.invoke(Character.valueOf(c)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <R> List<Pair<Float, R>> zip(@NotNull float[] fArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = fArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Float.valueOf(fArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Double, R>> zip(@NotNull double[] dArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = dArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Double.valueOf(dArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Boolean, R>> zip(@NotNull boolean[] zArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Boolean.valueOf(zArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Character, R>> zip(@NotNull char[] cArr, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = cArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (R r : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(new Pair(Character.valueOf(cArr[i]), r));
            i++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Byte, Byte>> zip(@NotNull byte[] bArr, @NotNull byte[] other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Byte.valueOf(bArr[i]), Byte.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Short, Short>> zip(@NotNull short[] sArr, @NotNull short[] other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Short.valueOf(sArr[i]), Short.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Integer, Integer>> zip(@NotNull int[] iArr, @NotNull int[] other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Integer.valueOf(iArr[i]), Integer.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Long, Long>> zip(@NotNull long[] jArr, @NotNull long[] other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Long.valueOf(jArr[i]), Long.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Float, Float>> zip(@NotNull float[] fArr, @NotNull float[] other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Float.valueOf(fArr[i]), Float.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Double, Double>> zip(@NotNull double[] dArr, @NotNull double[] other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Double.valueOf(dArr[i]), Double.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Boolean, Boolean>> zip(@NotNull boolean[] zArr, @NotNull boolean[] other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Boolean.valueOf(zArr[i]), Boolean.valueOf(other[i])));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull char[] cArr, @NotNull char[] other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(new Pair(Character.valueOf(cArr[i]), Character.valueOf(other[i])));
        }
        return arrayList;
    }
}
