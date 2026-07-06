package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalMultiplatform;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.TYPE})
@ExperimentalMultiplatform
@SinceKotlin(version = "1.9")
@Deprecated(message = "Please migrate to kotlin.jvm.KotlinActual in kotlin-annotations-jvm. ImplicitlyActualizedByJvmDeclaration will be dropped in future versions of Kotlin. See https://youtrack.jetbrains.com/issue/KT-67202")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@DeprecatedSinceKotlin(errorSince = "2.1")
public @interface ImplicitlyActualizedByJvmDeclaration {
}
