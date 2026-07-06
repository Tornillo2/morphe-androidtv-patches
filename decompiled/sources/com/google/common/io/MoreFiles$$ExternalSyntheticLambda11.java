package com.google.common.io;

import com.google.common.graph.SuccessorsFunction;
import java.nio.file.Path;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class MoreFiles$$ExternalSyntheticLambda11 implements SuccessorsFunction {
    @Override // com.google.common.graph.SuccessorsFunction
    public final Iterable successors(Object obj) {
        return MoreFiles.fileTreeChildren((Path) obj);
    }
}
