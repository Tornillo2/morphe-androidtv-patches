package kotlin.coroutines.jvm.internal;

import androidx.core.graphics.PaintCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugMetadata {
    @JvmName(name = "c")
    String c() default "";

    @JvmName(name = "f")
    String f() default "";

    @JvmName(name = "i")
    int[] i() default {};

    @JvmName(name = CmcdData.Factory.STREAM_TYPE_LIVE)
    int[] l() default {};

    @JvmName(name = PaintCompat.EM_STRING)
    String m() default "";

    @JvmName(name = GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION)
    String[] n() default {};

    @JvmName(name = CmcdData.Factory.STREAMING_FORMAT_SS)
    String[] s() default {};

    @JvmName(name = "v")
    int v() default 1;
}
