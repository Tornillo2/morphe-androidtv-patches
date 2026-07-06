package kotlinx.serialization.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerialInfo;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Target({})
@ExperimentalSerializationApi
@SerialInfo
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonNames {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class Impl implements JsonNames {
        public final /* synthetic */ String[] names;

        public Impl(@NotNull String[] names) {
            Intrinsics.checkNotNullParameter(names, "names");
            this.names = names;
        }

        @Override // kotlinx.serialization.json.JsonNames
        public final /* synthetic */ String[] names() {
            return this.names;
        }
    }

    String[] names();
}
