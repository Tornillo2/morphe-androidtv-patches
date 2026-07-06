package kotlin.math;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Constants {

    @NotNull
    public static final Constants INSTANCE = new Constants();

    @JvmField
    public static final double LN2 = Math.log(2.0d);

    @JvmField
    public static final double epsilon;

    @JvmField
    public static final double taylor_2_bound;

    @JvmField
    public static final double taylor_n_bound;

    @JvmField
    public static final double upper_taylor_2_bound;

    @JvmField
    public static final double upper_taylor_n_bound;

    static {
        double dUlp = Math.ulp(1.0d);
        epsilon = dUlp;
        double dSqrt = Math.sqrt(dUlp);
        taylor_2_bound = dSqrt;
        double dSqrt2 = Math.sqrt(dSqrt);
        taylor_n_bound = dSqrt2;
        double d = 1;
        upper_taylor_2_bound = d / dSqrt;
        upper_taylor_n_bound = d / dSqrt2;
    }
}
