package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.errorprone.annotations.DoNotMock;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@DoNotMock("Use Escapers.nullEscaper() or another methods from the *Escapers classes")
@GwtCompatible
public abstract class Escaper {
    public final Function<String, String> asFunction = new Function() { // from class: com.google.common.escape.Escaper$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Function
        public final Object apply(Object obj) {
            return this.f$0.escape((String) obj);
        }
    };

    public final Function<String, String> asFunction() {
        return this.asFunction;
    }

    public abstract String escape(String string);
}
