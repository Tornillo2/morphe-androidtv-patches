package com.amazon.avod.mpb.api.query;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class VideoCodecQueryAttributes$Factory$from$framerate$1 extends FunctionReferenceImpl implements Function1<String, Float> {
    public static final VideoCodecQueryAttributes$Factory$from$framerate$1 INSTANCE = new VideoCodecQueryAttributes$Factory$from$framerate$1();

    public VideoCodecQueryAttributes$Factory$from$framerate$1() {
        super(1, StringsKt.class, "toFloat", "toFloat(Ljava/lang/String;)F", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Float invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Float.valueOf(Float.parseFloat(p0));
    }
}
