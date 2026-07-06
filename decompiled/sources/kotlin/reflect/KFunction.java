package kotlin.reflect;

import kotlin.Function;
import kotlin.SinceKotlin;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface KFunction<R> extends KCallable<R>, Function<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.KCallable
    boolean isSuspend();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isExternal$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInfix$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInline$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOperator$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isSuspend$annotations() {
        }
    }
}
