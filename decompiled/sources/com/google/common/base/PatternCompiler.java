package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.RestrictedApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public interface PatternCompiler {
    @RestrictedApi(allowedOnPath = ".*/com/google/common/base/.*", explanation = "PatternCompiler is an implementation detail of com.google.common.base")
    CommonPattern compile(String pattern);

    @RestrictedApi(allowedOnPath = ".*/com/google/common/base/.*", explanation = "PatternCompiler is an implementation detail of com.google.common.base")
    boolean isPcreLike();
}
