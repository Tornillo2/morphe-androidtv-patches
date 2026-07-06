package com.amazon.avod.mpb.api.query;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class AudioCodecQueryAttributes$Factory$from$samplerate$1 extends FunctionReferenceImpl implements Function1<String, Integer> {
    public static final AudioCodecQueryAttributes$Factory$from$samplerate$1 INSTANCE = new AudioCodecQueryAttributes$Factory$from$samplerate$1();

    public AudioCodecQueryAttributes$Factory$from$samplerate$1() {
        super(1, StringsKt.class, "toInt", "toInt(Ljava/lang/String;)I", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Integer invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Integer.valueOf(Integer.parseInt(p0));
    }
}
