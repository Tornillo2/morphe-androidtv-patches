package com.amazon.avod.mpb.api.query;

import com.amazon.avod.mpb.media.playback.util.CodecString;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class AudioCodecQueryAttributes$Factory$from$codecString$1 extends FunctionReferenceImpl implements Function1<String, CodecString> {
    public static final AudioCodecQueryAttributes$Factory$from$codecString$1 INSTANCE = new AudioCodecQueryAttributes$Factory$from$codecString$1();

    public AudioCodecQueryAttributes$Factory$from$codecString$1() {
        super(1, CodecString.class, "<init>", "<init>(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final CodecString invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new CodecString(p0);
    }
}
