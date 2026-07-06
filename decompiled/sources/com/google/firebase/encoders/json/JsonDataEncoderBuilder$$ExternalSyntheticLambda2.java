package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class JsonDataEncoderBuilder$$ExternalSyntheticLambda2 implements ValueEncoder {
    @Override // com.google.firebase.encoders.Encoder
    public final void encode(Object obj, ValueEncoderContext valueEncoderContext) throws IOException {
        valueEncoderContext.add(((Boolean) obj).booleanValue());
    }
}
