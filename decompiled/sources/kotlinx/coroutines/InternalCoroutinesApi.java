package kotlinx.coroutines;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.RequiresOptIn;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.TYPE, ElementType.METHOD})
@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "This is an internal kotlinx.coroutines API that should not be used from outside of kotlinx.coroutines. No compatibility guarantees are provided. It is recommended to report your use-case of internal API to kotlinx.coroutines issue tracker, so stable API could be provided instead")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
public @interface InternalCoroutinesApi {
}
