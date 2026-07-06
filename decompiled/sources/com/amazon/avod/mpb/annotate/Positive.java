package com.amazon.avod.mpb.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@TypeQualifier(applicableTo = Number.class)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.FIELD})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
public @interface Positive {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Checker implements TypeQualifierValidator<Positive> {
        @Override // javax.annotation.meta.TypeQualifierValidator
        @NotNull
        public When forConstantValue(@NotNull Positive annotation, @NotNull Object value) {
            Intrinsics.checkNotNullParameter(annotation, "annotation");
            Intrinsics.checkNotNullParameter(value, "value");
            if (!(value instanceof Number)) {
                return When.NEVER;
            }
            Number number = (Number) value;
            return (!(number instanceof Long) ? !(!(number instanceof Double) ? !(!(number instanceof Float) ? number.intValue() > 0 : number.floatValue() > 0.0f) : number.doubleValue() > 0.0d) : number.longValue() > 0) ? When.NEVER : When.ALWAYS;
        }
    }

    When when() default When.ALWAYS;
}
