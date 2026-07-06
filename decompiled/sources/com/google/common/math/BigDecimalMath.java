package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public class BigDecimalMath {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BigDecimalToDoubleRounder extends ToDoubleRounder<BigDecimal> {
        public static final BigDecimalToDoubleRounder INSTANCE = new BigDecimalToDoubleRounder();

        @Override // com.google.common.math.ToDoubleRounder
        public BigDecimal minus(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }

        @Override // com.google.common.math.ToDoubleRounder
        public double roundToDoubleArbitrarily(BigDecimal bigDecimal) {
            return bigDecimal.doubleValue();
        }

        @Override // com.google.common.math.ToDoubleRounder
        public int sign(BigDecimal bigDecimal) {
            return bigDecimal.signum();
        }

        @Override // com.google.common.math.ToDoubleRounder
        public BigDecimal toX(double d, RoundingMode mode) {
            return new BigDecimal(d);
        }
    }

    public static double roundToDouble(BigDecimal x, RoundingMode mode) {
        return BigDecimalToDoubleRounder.INSTANCE.roundToDouble(x, mode);
    }
}
