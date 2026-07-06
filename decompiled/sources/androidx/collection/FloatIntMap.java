package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFloatIntMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatIntMap.kt\nandroidx/collection/FloatIntMap\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 3 FloatSet.kt\nandroidx/collection/FloatSetKt\n*L\n1#1,1047:1\n357#1,6:1050\n367#1,3:1057\n370#1,9:1061\n357#1,6:1070\n367#1,3:1077\n370#1,9:1081\n357#1,6:1090\n367#1,3:1097\n370#1,9:1101\n385#1,4:1110\n357#1,6:1114\n367#1,3:1121\n370#1,2:1125\n389#1,2:1127\n373#1,6:1129\n391#1:1135\n385#1,4:1136\n357#1,6:1140\n367#1,3:1147\n370#1,2:1151\n389#1,2:1153\n373#1,6:1155\n391#1:1161\n385#1,4:1162\n357#1,6:1166\n367#1,3:1173\n370#1,2:1177\n389#1,2:1179\n373#1,6:1181\n391#1:1187\n410#1,3:1188\n357#1,6:1191\n367#1,3:1198\n370#1,2:1202\n413#1,2:1204\n373#1,6:1206\n415#1:1212\n385#1,4:1213\n357#1,6:1217\n367#1,3:1224\n370#1,2:1228\n389#1,2:1230\n373#1,6:1232\n391#1:1238\n385#1,4:1239\n357#1,6:1243\n367#1,3:1250\n370#1,2:1254\n389#1,2:1256\n373#1,6:1258\n391#1:1264\n385#1,4:1265\n357#1,6:1269\n367#1,3:1276\n370#1,2:1280\n389#1,2:1282\n373#1,6:1284\n391#1:1290\n385#1,4:1291\n357#1,6:1295\n367#1,3:1302\n370#1,2:1306\n389#1,2:1308\n373#1,6:1310\n391#1:1316\n385#1,4:1317\n357#1,6:1321\n367#1,3:1328\n370#1,2:1332\n389#1,2:1334\n373#1,6:1336\n391#1:1342\n385#1,4:1343\n357#1,6:1347\n367#1,3:1354\n370#1,2:1358\n389#1,2:1360\n373#1,6:1362\n391#1:1368\n519#1,11:1385\n385#1,4:1396\n357#1,6:1400\n367#1,3:1407\n370#1,2:1411\n389#1:1413\n530#1,10:1414\n390#1:1424\n373#1,6:1425\n391#1:1431\n540#1,2:1432\n519#1,11:1434\n385#1,4:1445\n357#1,6:1449\n367#1,3:1456\n370#1,2:1460\n389#1:1462\n530#1,10:1463\n390#1:1473\n373#1,6:1474\n391#1:1480\n540#1,2:1481\n519#1,11:1483\n385#1,4:1494\n357#1,6:1498\n367#1,3:1505\n370#1,2:1509\n389#1:1511\n530#1,10:1512\n390#1:1522\n373#1,6:1523\n391#1:1529\n540#1,2:1530\n519#1,11:1532\n385#1,4:1543\n357#1,6:1547\n367#1,3:1554\n370#1,2:1558\n389#1:1560\n530#1,10:1561\n390#1:1571\n373#1,6:1572\n391#1:1578\n540#1,2:1579\n519#1,11:1581\n385#1,4:1592\n357#1,6:1596\n367#1,3:1603\n370#1,2:1607\n389#1:1609\n530#1,10:1610\n390#1:1620\n373#1,6:1621\n391#1:1627\n540#1,2:1628\n1826#2:1048\n1688#2:1049\n1826#2:1056\n1688#2:1060\n1826#2:1076\n1688#2:1080\n1826#2:1096\n1688#2:1100\n1826#2:1120\n1688#2:1124\n1826#2:1146\n1688#2:1150\n1826#2:1172\n1688#2:1176\n1826#2:1197\n1688#2:1201\n1826#2:1223\n1688#2:1227\n1826#2:1249\n1688#2:1253\n1826#2:1275\n1688#2:1279\n1826#2:1301\n1688#2:1305\n1826#2:1327\n1688#2:1331\n1826#2:1353\n1688#2:1357\n1619#2:1372\n1615#2:1373\n1795#2,3:1374\n1809#2,3:1377\n1733#2:1380\n1721#2:1381\n1715#2:1382\n1728#2:1383\n1818#2:1384\n1826#2:1406\n1688#2:1410\n1826#2:1455\n1688#2:1459\n1826#2:1504\n1688#2:1508\n1826#2:1553\n1688#2:1557\n1826#2:1602\n1688#2:1606\n849#3,3:1369\n*S KotlinDebug\n*F\n+ 1 FloatIntMap.kt\nandroidx/collection/FloatIntMap\n*L\n388#1:1050,6\n388#1:1057,3\n388#1:1061,9\n400#1:1070,6\n400#1:1077,3\n400#1:1081,9\n412#1:1090,6\n412#1:1097,3\n412#1:1101,9\n421#1:1110,4\n421#1:1114,6\n421#1:1121,3\n421#1:1125,2\n421#1:1127,2\n421#1:1129,6\n421#1:1135\n431#1:1136,4\n431#1:1140,6\n431#1:1147,3\n431#1:1151,2\n431#1:1153,2\n431#1:1155,6\n431#1:1161\n447#1:1162,4\n447#1:1166,6\n447#1:1173,3\n447#1:1177,2\n447#1:1179,2\n447#1:1181,6\n447#1:1187\n470#1:1188,3\n470#1:1191,6\n470#1:1198,3\n470#1:1202,2\n470#1:1204,2\n470#1:1206,6\n470#1:1212\n494#1:1213,4\n494#1:1217,6\n494#1:1224,3\n494#1:1228,2\n494#1:1230,2\n494#1:1232,6\n494#1:1238\n529#1:1239,4\n529#1:1243,6\n529#1:1250,3\n529#1:1254,2\n529#1:1256,2\n529#1:1258,6\n529#1:1264\n529#1:1265,4\n529#1:1269,6\n529#1:1276,3\n529#1:1280,2\n529#1:1282,2\n529#1:1284,6\n529#1:1290\n550#1:1291,4\n550#1:1295,6\n550#1:1302,3\n550#1:1306,2\n550#1:1308,2\n550#1:1310,6\n550#1:1316\n576#1:1317,4\n576#1:1321,6\n576#1:1328,3\n576#1:1332,2\n576#1:1334,2\n576#1:1336,6\n576#1:1342\n598#1:1343,4\n598#1:1347,6\n598#1:1354,3\n598#1:1358,2\n598#1:1360,2\n598#1:1362,6\n598#1:1368\n-1#1:1385,11\n-1#1:1396,4\n-1#1:1400,6\n-1#1:1407,3\n-1#1:1411,2\n-1#1:1413\n-1#1:1414,10\n-1#1:1424\n-1#1:1425,6\n-1#1:1431\n-1#1:1432,2\n-1#1:1434,11\n-1#1:1445,4\n-1#1:1449,6\n-1#1:1456,3\n-1#1:1460,2\n-1#1:1462\n-1#1:1463,10\n-1#1:1473\n-1#1:1474,6\n-1#1:1480\n-1#1:1481,2\n-1#1:1483,11\n-1#1:1494,4\n-1#1:1498,6\n-1#1:1505,3\n-1#1:1509,2\n-1#1:1511\n-1#1:1512,10\n-1#1:1522\n-1#1:1523,6\n-1#1:1529\n-1#1:1530,2\n-1#1:1532,11\n-1#1:1543,4\n-1#1:1547,6\n-1#1:1554,3\n-1#1:1558,2\n-1#1:1560\n-1#1:1561,10\n-1#1:1571\n-1#1:1572,6\n-1#1:1578\n-1#1:1579,2\n-1#1:1581,11\n-1#1:1592,4\n-1#1:1596,6\n-1#1:1603,3\n-1#1:1607,2\n-1#1:1609\n-1#1:1610,10\n-1#1:1620\n-1#1:1621,6\n-1#1:1627\n-1#1:1628,2\n362#1:1048\n369#1:1049\n388#1:1056\n388#1:1060\n400#1:1076\n400#1:1080\n412#1:1096\n412#1:1100\n421#1:1120\n421#1:1124\n431#1:1146\n431#1:1150\n447#1:1172\n447#1:1176\n470#1:1197\n470#1:1201\n494#1:1223\n494#1:1227\n529#1:1249\n529#1:1253\n529#1:1275\n529#1:1279\n550#1:1301\n550#1:1305\n576#1:1327\n576#1:1331\n598#1:1353\n598#1:1357\n618#1:1372\n621#1:1373\n625#1:1374,3\n626#1:1377,3\n627#1:1380\n628#1:1381\n628#1:1382\n632#1:1383\n635#1:1384\n-1#1:1406\n-1#1:1410\n-1#1:1455\n-1#1:1459\n-1#1:1504\n-1#1:1508\n-1#1:1553\n-1#1:1557\n-1#1:1602\n-1#1:1606\n617#1:1369,3\n*E\n"})
public abstract class FloatIntMap {

    @JvmField
    public int _capacity;

    @JvmField
    public int _size;

    @JvmField
    @NotNull
    public float[] keys;

    @JvmField
    @NotNull
    public long[] metadata;

    @JvmField
    @NotNull
    public int[] values;

    public /* synthetic */ FloatIntMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ String joinToString$default(FloatIntMap floatIntMap, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, int i2, Object obj) {
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
        return floatIntMap.joinToString(charSequence, charSequence2, charSequence6, i, charSequence5);
    }

    public final boolean all(@NotNull Function2<? super Float, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        if (!predicate.invoke(Float.valueOf(fArr[i4]), Integer.valueOf(iArr[i4])).booleanValue()) {
                            return false;
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return true;
                }
            }
            if (i == length) {
                return true;
            }
            i++;
        }
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final boolean contains(float f) {
        return findKeyIndex(f) >= 0;
    }

    public final boolean containsKey(float f) {
        return findKeyIndex(f) >= 0;
    }

    public final boolean containsValue(int i) {
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128 && i == iArr[(i2 << 3) + i4]) {
                            return true;
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int count() {
        return this._size;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = 1
            if (r1 != r0) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof androidx.collection.FloatIntMap
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.collection.FloatIntMap r1 = (androidx.collection.FloatIntMap) r1
            int r3 = r1._size
            int r5 = r0._size
            if (r3 == r5) goto L17
            return r4
        L17:
            float[] r3 = r0.keys
            int[] r5 = r0.values
            long[] r6 = r0.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L61
            r8 = 0
        L23:
            r9 = r6[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 == 0) goto L5c
            int r11 = r8 - r7
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L3d:
            if (r13 >= r11) goto L5a
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r18 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r18 >= 0) goto L56
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r3[r14]
            r14 = r5[r14]
            int r15 = r1.get(r15)
            if (r14 == r15) goto L56
            return r4
        L56:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3d
        L5a:
            if (r11 != r12) goto L61
        L5c:
            if (r8 == r7) goto L61
            int r8 = r8 + 1
            goto L23
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.equals(java.lang.Object):boolean");
    }

    @PublishedApi
    public final int findKeyIndex(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f) * (-862048943);
        int i = iFloatToIntBits ^ (iFloatToIntBits << 16);
        int i2 = i & 127;
        int i3 = this._capacity;
        int i4 = (i >>> 7) & i3;
        int i5 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i6 = i4 >> 3;
            int i7 = (i4 & 7) << 3;
            long j = ((jArr[i6 + 1] << (64 - i7)) & ((-i7) >> 63)) | (jArr[i6] >>> i7);
            long j2 = (((long) i2) * ScatterMapKt.BitmaskLsb) ^ j;
            for (long j3 = (~j2) & (j2 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j3 != 0; j3 &= j3 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j3) >> 3) + i4) & i3;
                if (this.keys[iNumberOfTrailingZeros] == f) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j & ((~j) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i5 += 8;
            i4 = (i4 + i5) & i3;
        }
    }

    public final void forEach(@NotNull Function2<? super Float, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        block.invoke(Float.valueOf(fArr[i4]), Integer.valueOf(iArr[i4]));
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @PublishedApi
    public final void forEachIndexed(@NotNull Function1<? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        FloatFloatMap$$ExternalSyntheticOutline0.m(i << 3, i3, block);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void forEachKey(@NotNull Function1<? super Float, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        float[] fArr = this.keys;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        block.invoke(Float.valueOf(fArr[(i << 3) + i3]));
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void forEachValue(@NotNull Function1<? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        block.invoke(Integer.valueOf(iArr[(i << 3) + i3]));
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final int get(float f) {
        int iFindKeyIndex = findKeyIndex(f);
        if (iFindKeyIndex >= 0) {
            return this.values[iFindKeyIndex];
        }
        throw new NoSuchElementException("Cannot find value for key " + f);
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public final int getOrDefault(float f, int i) {
        int iFindKeyIndex = findKeyIndex(f);
        return iFindKeyIndex >= 0 ? this.values[iFindKeyIndex] : i;
    }

    public final int getOrElse(float f, @NotNull Function0<Integer> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        int iFindKeyIndex = findKeyIndex(f);
        return iFindKeyIndex < 0 ? defaultValue.invoke().intValue() : this.values[iFindKeyIndex];
    }

    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        float[] fArr = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return 0;
        }
        int i = 0;
        int iFloatToIntBits = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        iFloatToIntBits += iArr[i4] ^ Float.floatToIntBits(fArr[i4]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return iFloatToIntBits;
                }
            }
            if (i == length) {
                return iFloatToIntBits;
            }
            i++;
        }
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

    public final boolean none() {
        return this._size == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006a A[PHI: r8
      0x006a: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x0030, B:19:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r19 = this;
            r0 = r19
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto Lc
            java.lang.String r1 = "{}"
            return r1
        Lc:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "{"
            r1.<init>(r2)
            float[] r2 = r0.keys
            int[] r3 = r0.values
            long[] r4 = r0.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L6f
            r6 = 0
            r7 = 0
            r8 = 0
        L22:
            r9 = r4[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 == 0) goto L6a
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L3c:
            if (r13 >= r11) goto L68
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r18 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r18 >= 0) goto L64
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0._size
            if (r8 >= r14) goto L64
            java.lang.String r14 = ", "
            r1.append(r14)
        L64:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3c
        L68:
            if (r11 != r12) goto L6f
        L6a:
            if (r7 == r5) goto L6f
            int r7 = r7 + 1
            goto L22
        L6f:
            r2 = 125(0x7d, float:1.75E-43)
            java.lang.String r3 = "s.append('}').toString()"
            java.lang.String r1 = androidx.collection.ArraySet$$ExternalSyntheticOutline0.m(r1, r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.toString():java.lang.String");
    }

    public FloatIntMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = FloatSetKt.getEmptyFloatArray();
        this.values = IntSetKt.getEmptyIntArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean any(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, java.lang.Boolean> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "predicate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            float[] r2 = r0.keys
            int[] r3 = r0.values
            long[] r4 = r0.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            r6 = 0
            if (r5 < 0) goto L63
            r7 = 0
        L16:
            r8 = r4[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 == 0) goto L5e
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = 0
        L30:
            if (r12 >= r10) goto L5c
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 >= 0) goto L58
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r2[r13]
            r13 = r3[r13]
            java.lang.Float r14 = java.lang.Float.valueOf(r14)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            java.lang.Object r13 = r1.invoke(r14, r13)
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L58
            r1 = 1
            return r1
        L58:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L30
        L5c:
            if (r10 != r11) goto L63
        L5e:
            if (r7 == r5) goto L63
            int r7 = r7 + 1
            goto L16
        L63:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.any(kotlin.jvm.functions.Function2):boolean");
    }

    public final int count(@NotNull Function2<? super Float, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        float[] fArr = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i3 = 8 - ((~(i - length)) >>> 31);
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((255 & j) < 128) {
                        int i5 = (i << 3) + i4;
                        if (predicate.invoke(Float.valueOf(fArr[i5]), Integer.valueOf(iArr[i5])).booleanValue()) {
                            i2++;
                        }
                    }
                    j >>= 8;
                }
                if (i3 != 8) {
                    return i2;
                }
            }
            if (i == length) {
                return i2;
            }
            i++;
        }
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

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        return joinToString$default(this, separator, prefix, postfix, 0, null, 24, null);
    }

    public static /* synthetic */ String joinToString$default(FloatIntMap floatIntMap, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function2 function2, int i2, Object obj) {
        long[] jArr;
        long[] jArr2;
        char c;
        if (obj == null) {
            CharSequence separator = (i2 & 1) != 0 ? ", " : charSequence;
            CharSequence prefix = (i2 & 2) != 0 ? "" : charSequence2;
            CharSequence postfix = (i2 & 4) == 0 ? charSequence3 : "";
            int i3 = (i2 & 8) != 0 ? -1 : i;
            CharSequence charSequence5 = (i2 & 16) != 0 ? "..." : charSequence4;
            Intrinsics.checkNotNullParameter(separator, "separator");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(postfix, "postfix");
            StringBuilder sbM = FloatFloatMap$$ExternalSyntheticOutline1.m(charSequence5, "truncated", function2, "transform", prefix);
            float[] fArr = floatIntMap.keys;
            int[] iArr = floatIntMap.values;
            long[] jArr3 = floatIntMap.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i4 = 0;
                int i5 = 0;
                loop0: while (true) {
                    long j = jArr3[i4];
                    int i6 = i4;
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i7 = 8 - ((~(i6 - length)) >>> 31);
                        int i8 = 0;
                        while (i8 < i7) {
                            if ((j & 255) < 128) {
                                int i9 = (i6 << 3) + i8;
                                float f = fArr[i9];
                                int i10 = iArr[i9];
                                if (i5 == i3) {
                                    sbM.append(charSequence5);
                                    break loop0;
                                }
                                if (i5 != 0) {
                                    sbM.append(separator);
                                }
                                c = '\b';
                                Float fValueOf = Float.valueOf(f);
                                jArr2 = jArr3;
                                sbM.append((CharSequence) function2.invoke(fValueOf, Integer.valueOf(i10)));
                                i5++;
                            } else {
                                jArr2 = jArr3;
                                c = '\b';
                            }
                            j >>= c;
                            i8++;
                            jArr3 = jArr2;
                        }
                        jArr = jArr3;
                        if (i7 != 8) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                    }
                    if (i6 == length) {
                        break;
                    }
                    i4 = i6 + 1;
                    jArr3 = jArr;
                }
                sbM.append(postfix);
            } else {
                sbM.append(postfix);
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

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2) {
        float[] fArr;
        float[] fArr2;
        char c;
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        StringBuilder sbM = FloatFloatMap$$ExternalSyntheticOutline2.m(charSequence, "postfix", charSequence2, "truncated", prefix);
        float[] fArr3 = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            loop0: while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((j & 255) < 128) {
                            int i6 = (i2 << 3) + i5;
                            c = '\b';
                            float f = fArr3[i6];
                            int i7 = iArr[i6];
                            fArr2 = fArr3;
                            if (i3 == i) {
                                sbM.append(charSequence2);
                                break loop0;
                            }
                            if (i3 != 0) {
                                sbM.append(separator);
                            }
                            sbM.append(f);
                            sbM.append('=');
                            sbM.append(i7);
                            i3++;
                        } else {
                            fArr2 = fArr3;
                            c = '\b';
                        }
                        j >>= c;
                        i5++;
                        fArr3 = fArr2;
                    }
                    fArr = fArr3;
                    if (i4 != 8) {
                        break;
                    }
                } else {
                    fArr = fArr3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                fArr3 = fArr;
            }
            sbM.append(charSequence);
        } else {
            sbM.append(charSequence);
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @PublishedApi
    public static /* synthetic */ void getKeys$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMetadata$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getValues$annotations() {
    }

    public static /* synthetic */ void get_capacity$collection$annotations() {
    }

    public static /* synthetic */ void get_size$collection$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0091 A[PHI: r12
      0x0091: PHI (r12v2 int) = (r12v1 int), (r12v3 int) binds: [B:6:0x0042, B:19:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String joinToString(@org.jetbrains.annotations.NotNull java.lang.CharSequence r23, @org.jetbrains.annotations.NotNull java.lang.CharSequence r24, @org.jetbrains.annotations.NotNull java.lang.CharSequence r25, int r26, @org.jetbrains.annotations.NotNull java.lang.CharSequence r27, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, ? extends java.lang.CharSequence> r28) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r4 = r27
            r5 = r28
            java.lang.String r6 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r6)
            java.lang.String r6 = "prefix"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r6)
            java.lang.String r6 = "postfix"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r6)
            java.lang.String r6 = "truncated"
            java.lang.String r7 = "transform"
            java.lang.StringBuilder r2 = androidx.collection.FloatFloatMap$$ExternalSyntheticOutline1.m(r4, r6, r5, r7, r2)
            float[] r6 = r0.keys
            int[] r7 = r0.values
            long[] r8 = r0.metadata
            int r9 = r8.length
            int r9 = r9 + (-2)
            if (r9 < 0) goto L9a
            r11 = 0
            r12 = 0
        L30:
            r13 = r8[r11]
            r15 = r11
            long r10 = ~r13
            r16 = 7
            long r10 = r10 << r16
            long r10 = r10 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r16
            int r18 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r18 == 0) goto L91
            int r11 = r15 - r9
            int r10 = ~r11
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r11 = 0
            r16 = 8
        L50:
            if (r11 >= r10) goto L8d
            r17 = 255(0xff, double:1.26E-321)
            long r17 = r13 & r17
            r19 = 128(0x80, double:6.3E-322)
            int r21 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r21 >= 0) goto L84
            int r17 = r15 << 3
            int r17 = r17 + r11
            r18 = r6[r17]
            r17 = r7[r17]
            r0 = r26
            if (r12 != r0) goto L6c
            r2.append(r4)
            goto L9d
        L6c:
            if (r12 == 0) goto L71
            r2.append(r1)
        L71:
            java.lang.Float r0 = java.lang.Float.valueOf(r18)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r17)
            java.lang.Object r0 = r5.invoke(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.append(r0)
            int r12 = r12 + 1
        L84:
            long r13 = r13 >> r16
            int r11 = r11 + 1
            r0 = r22
            r1 = r23
            goto L50
        L8d:
            r0 = 8
            if (r10 != r0) goto L9a
        L91:
            if (r15 == r9) goto L9a
            int r11 = r15 + 1
            r0 = r22
            r1 = r23
            goto L30
        L9a:
            r2.append(r3)
        L9d:
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.joinToString(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, int, java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    @JvmOverloads
    @NotNull
    public final String joinToString(@NotNull CharSequence charSequence, @NotNull CharSequence prefix, @NotNull CharSequence charSequence2, int i, @NotNull Function2<? super Float, ? super Integer, ? extends CharSequence> function2) {
        char c;
        CharSequence separator = charSequence;
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        StringBuilder sbM = FloatFloatMap$$ExternalSyntheticOutline1.m(charSequence2, "postfix", function2, "transform", prefix);
        float[] fArr = this.keys;
        int[] iArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            loop0: while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((j & 255) < 128) {
                            int i6 = (i2 << 3) + i5;
                            float f = fArr[i6];
                            int i7 = iArr[i6];
                            c = '\b';
                            if (i3 == i) {
                                sbM.append((CharSequence) "...");
                                break loop0;
                            }
                            if (i3 != 0) {
                                sbM.append(separator);
                            }
                            sbM.append(function2.invoke(Float.valueOf(f), Integer.valueOf(i7)));
                            i3++;
                        } else {
                            c = '\b';
                        }
                        j >>= c;
                        i5++;
                        separator = charSequence;
                    }
                    if (i4 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                separator = charSequence;
            }
            sbM.append(charSequence2);
        } else {
            sbM.append(charSequence2);
        }
        String string = sbM.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008d A[PHI: r11
      0x008d: PHI (r11v2 int) = (r11v1 int), (r11v3 int) binds: [B:6:0x003a, B:20:0x008b] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String joinToString(@org.jetbrains.annotations.NotNull java.lang.CharSequence r22, @org.jetbrains.annotations.NotNull java.lang.CharSequence r23, @org.jetbrains.annotations.NotNull java.lang.CharSequence r24, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, ? extends java.lang.CharSequence> r25) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            java.lang.String r5 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r5)
            java.lang.String r5 = "prefix"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r5)
            java.lang.String r5 = "postfix"
            java.lang.String r6 = "transform"
            java.lang.StringBuilder r2 = androidx.collection.FloatFloatMap$$ExternalSyntheticOutline1.m(r3, r5, r4, r6, r2)
            float[] r5 = r0.keys
            int[] r6 = r0.values
            long[] r7 = r0.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto L94
            r10 = 0
            r11 = 0
        L29:
            r12 = r7[r10]
            long r14 = ~r12
            r16 = 7
            long r14 = r14 << r16
            long r14 = r14 & r12
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r14 = r14 & r16
            int r18 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r18 == 0) goto L8d
            int r14 = r10 - r8
            int r14 = ~r14
            int r14 = r14 >>> 31
            r15 = 8
            int r14 = 8 - r14
            r9 = 0
        L46:
            if (r9 >= r14) goto L89
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r12 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 >= 0) goto L7e
            int r16 = r10 << 3
            int r16 = r16 + r9
            r17 = r5[r16]
            r16 = r6[r16]
            r18 = 8
            r15 = -1
            if (r11 != r15) goto L65
            java.lang.String r1 = "..."
            r2.append(r1)
            goto L97
        L65:
            if (r11 == 0) goto L6a
            r2.append(r1)
        L6a:
            java.lang.Float r15 = java.lang.Float.valueOf(r17)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r16)
            java.lang.Object r0 = r4.invoke(r15, r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.append(r0)
            int r11 = r11 + 1
            goto L80
        L7e:
            r18 = 8
        L80:
            long r12 = r12 >> r18
            int r9 = r9 + 1
            r0 = r21
            r15 = 8
            goto L46
        L89:
            r0 = 8
            if (r14 != r0) goto L94
        L8d:
            if (r10 == r8) goto L94
            int r10 = r10 + 1
            r0 = r21
            goto L29
        L94:
            r2.append(r3)
        L97:
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.joinToString(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0081 A[PHI: r10
      0x0081: PHI (r10v2 int) = (r10v1 int), (r10v3 int) binds: [B:6:0x0030, B:20:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String joinToString(@org.jetbrains.annotations.NotNull java.lang.CharSequence r22, @org.jetbrains.annotations.NotNull java.lang.CharSequence r23, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, ? extends java.lang.CharSequence> r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r24
            java.lang.String r3 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "prefix"
            java.lang.String r4 = "transform"
            r5 = r23
            java.lang.StringBuilder r3 = androidx.collection.FloatFloatMap$$ExternalSyntheticOutline1.m(r5, r3, r2, r4, r5)
            float[] r4 = r0.keys
            int[] r5 = r0.values
            long[] r6 = r0.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L86
            r9 = 0
            r10 = 0
        L22:
            r11 = r6[r9]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 == 0) goto L81
            int r13 = r9 - r7
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L3c:
            if (r15 >= r13) goto L7d
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 >= 0) goto L74
            int r16 = r9 << 3
            int r16 = r16 + r15
            r17 = r4[r16]
            r16 = r5[r16]
            r8 = -1
            if (r10 != r8) goto L59
            java.lang.String r1 = "..."
            r3.append(r1)
            goto L8b
        L59:
            if (r10 == 0) goto L5e
            r3.append(r1)
        L5e:
            java.lang.Float r8 = java.lang.Float.valueOf(r17)
            r17 = 8
            java.lang.Integer r14 = java.lang.Integer.valueOf(r16)
            java.lang.Object r8 = r2.invoke(r8, r14)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r3.append(r8)
            int r10 = r10 + 1
            goto L76
        L74:
            r17 = 8
        L76:
            long r11 = r11 >> r17
            int r15 = r15 + 1
            r14 = 8
            goto L3c
        L7d:
            r8 = 8
            if (r13 != r8) goto L86
        L81:
            if (r9 == r7) goto L86
            int r9 = r9 + 1
            goto L22
        L86:
            java.lang.String r1 = ""
            r3.append(r1)
        L8b:
            java.lang.String r1 = r3.toString()
            java.lang.String r2 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.joinToString(java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0088 A[PHI: r11
      0x0088: PHI (r11v2 int) = (r11v1 int), (r11v3 int) binds: [B:6:0x0035, B:20:0x0086] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String joinToString(@org.jetbrains.annotations.NotNull java.lang.CharSequence r23, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, ? extends java.lang.CharSequence> r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            java.lang.String r3 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "transform"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = ""
            r3.<init>(r4)
            float[] r5 = r0.keys
            int[] r6 = r0.values
            long[] r7 = r0.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto L8f
            r10 = 0
            r11 = 0
        L24:
            r12 = r7[r10]
            long r14 = ~r12
            r16 = 7
            long r14 = r14 << r16
            long r14 = r14 & r12
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r14 = r14 & r16
            int r18 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r18 == 0) goto L88
            int r14 = r10 - r8
            int r14 = ~r14
            int r14 = r14 >>> 31
            r15 = 8
            int r14 = 8 - r14
            r9 = 0
        L41:
            if (r9 >= r14) goto L84
            r17 = 255(0xff, double:1.26E-321)
            long r17 = r12 & r17
            r19 = 128(0x80, double:6.3E-322)
            int r21 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r21 >= 0) goto L79
            int r17 = r10 << 3
            int r17 = r17 + r9
            r18 = r5[r17]
            r17 = r6[r17]
            r19 = 8
            r15 = -1
            if (r11 != r15) goto L60
            java.lang.String r1 = "..."
            r3.append(r1)
            goto L92
        L60:
            if (r11 == 0) goto L65
            r3.append(r1)
        L65:
            java.lang.Float r15 = java.lang.Float.valueOf(r18)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            java.lang.Object r0 = r2.invoke(r15, r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3.append(r0)
            int r11 = r11 + 1
            goto L7b
        L79:
            r19 = 8
        L7b:
            long r12 = r12 >> r19
            int r9 = r9 + 1
            r0 = r22
            r15 = 8
            goto L41
        L84:
            r0 = 8
            if (r14 != r0) goto L8f
        L88:
            if (r10 == r8) goto L8f
            int r10 = r10 + 1
            r0 = r22
            goto L24
        L8f:
            r3.append(r4)
        L92:
            java.lang.String r0 = r3.toString()
            java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.joinToString(java.lang.CharSequence, kotlin.jvm.functions.Function2):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007e A[PHI: r10
      0x007e: PHI (r10v2 int) = (r10v1 int), (r10v3 int) binds: [B:6:0x002b, B:20:0x007c] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmOverloads
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String joinToString(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Float, ? super java.lang.Integer, ? extends java.lang.CharSequence> r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            java.lang.String r2 = "transform"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = ""
            r2.<init>(r3)
            float[] r4 = r0.keys
            int[] r5 = r0.values
            long[] r6 = r0.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L83
            r9 = 0
            r10 = 0
        L1d:
            r11 = r6[r9]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 == 0) goto L7e
            int r13 = r9 - r7
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L37:
            if (r15 >= r13) goto L7a
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 >= 0) goto L71
            int r16 = r9 << 3
            int r16 = r16 + r15
            r17 = r4[r16]
            r16 = r5[r16]
            r8 = -1
            if (r10 != r8) goto L54
            java.lang.String r1 = "..."
            r2.append(r1)
            goto L86
        L54:
            if (r10 == 0) goto L5b
            java.lang.String r8 = ", "
            r2.append(r8)
        L5b:
            java.lang.Float r8 = java.lang.Float.valueOf(r17)
            r17 = 8
            java.lang.Integer r14 = java.lang.Integer.valueOf(r16)
            java.lang.Object r8 = r1.invoke(r8, r14)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r2.append(r8)
            int r10 = r10 + 1
            goto L73
        L71:
            r17 = 8
        L73:
            long r11 = r11 >> r17
            int r15 = r15 + 1
            r14 = 8
            goto L37
        L7a:
            r8 = 8
            if (r13 != r8) goto L83
        L7e:
            if (r9 == r7) goto L83
            int r9 = r9 + 1
            goto L1d
        L83:
            r2.append(r3)
        L86:
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatIntMap.joinToString(kotlin.jvm.functions.Function2):java.lang.String");
    }
}
