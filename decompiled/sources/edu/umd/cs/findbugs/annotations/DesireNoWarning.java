package edu.umd.cs.findbugs.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.CLASS)
@Deprecated
public @interface DesireNoWarning {
    Confidence confidence() default Confidence.LOW;

    int num() default 0;

    @Deprecated
    Priority priority() default Priority.LOW;

    String value();
}
