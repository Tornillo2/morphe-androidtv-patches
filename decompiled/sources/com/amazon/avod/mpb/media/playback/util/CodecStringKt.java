package com.amazon.avod.mpb.media.playback.util;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nCodecString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodecString.kt\ncom/amazon/avod/mpb/media/playback/util/CodecStringKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,368:1\n739#2,9:369\n37#3:378\n36#3,3:379\n*S KotlinDebug\n*F\n+ 1 CodecString.kt\ncom/amazon/avod/mpb/media/playback/util/CodecStringKt\n*L\n11#1:369,9\n11#1:378\n11#1:379,3\n*E\n"})
public final class CodecStringKt {
    public static final String[] getParts(String str) {
        Collection collectionTake;
        List<String> listSplit = new Regex("\\.").split(str, 0);
        if (listSplit.isEmpty()) {
            collectionTake = EmptyList.INSTANCE;
        } else {
            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    collectionTake = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    break;
                }
            }
            collectionTake = EmptyList.INSTANCE;
        }
        return (String[]) collectionTake.toArray(new String[0]);
    }
}
