package androidx.core.content.res;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.Size;
import androidx.core.graphics.ColorUtils;
import kotlin.jvm.internal.DoubleCompanionObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CamColor {
    public static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    public static final float DE_MAX = 1.0f;
    public static final float DL_MAX = 0.2f;
    public static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    public final float mAstar;
    public final float mBstar;
    public final float mChroma;
    public final float mHue;
    public final float mJ;
    public final float mJstar;
    public final float mM;
    public final float mQ;
    public final float mS;

    public CamColor(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.mHue = f;
        this.mChroma = f2;
        this.mJ = f3;
        this.mQ = f4;
        this.mM = f5;
        this.mS = f6;
        this.mJstar = f7;
        this.mAstar = f8;
        this.mBstar = f9;
    }

    @Nullable
    public static CamColor findCamByJ(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f3) {
        float f4 = 100.0f;
        float f5 = 1000.0f;
        CamColor camColor = null;
        float f6 = 1000.0f;
        float f7 = 0.0f;
        while (Math.abs(f7 - f4) > 0.01f) {
            float f8 = ((f4 - f7) / 2.0f) + f7;
            ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
            int iViewed = fromJchInFrame(f8, f2, f, viewingConditions).viewed(viewingConditions);
            float fLStarFromInt = CamUtils.lStarFromInt(iViewed);
            float fAbs = Math.abs(f3 - fLStarFromInt);
            if (fAbs < 0.2f) {
                CamColor camColorFromColor = fromColor(iViewed);
                float fDistance = camColorFromColor.distance(fromJchInFrame(camColorFromColor.mJ, camColorFromColor.mChroma, f, viewingConditions));
                if (fDistance <= 1.0f) {
                    f6 = fDistance;
                    camColor = camColorFromColor;
                    f5 = fAbs;
                }
            }
            if (f5 == 0.0f && f6 == 0.0f) {
                return camColor;
            }
            if (fLStarFromInt < f3) {
                f7 = f8;
            } else {
                f4 = f8;
            }
        }
        return camColor;
    }

    @NonNull
    public static CamColor fromColor(@ColorInt int i) {
        float[] fArr = new float[7];
        float[] fArr2 = new float[3];
        fromColorInViewingConditions(i, ViewingConditions.DEFAULT, fArr, fArr2);
        return new CamColor(fArr2[0], fArr2[1], fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6]);
    }

    public static void fromColorInViewingConditions(@ColorInt int i, @NonNull ViewingConditions viewingConditions, @Nullable @Size(7) float[] fArr, @NonNull @Size(3) float[] fArr2) {
        CamUtils.xyzFromInt(i, fArr2);
        float[][] fArr3 = CamUtils.XYZ_TO_CAM16RGB;
        float f = fArr2[0];
        float[] fArr4 = fArr3[0];
        float f2 = fArr4[0] * f;
        float f3 = fArr2[1];
        float f4 = (fArr4[1] * f3) + f2;
        float f5 = fArr2[2];
        float f6 = (fArr4[2] * f5) + f4;
        float[] fArr5 = fArr3[1];
        float f7 = (fArr5[2] * f5) + (fArr5[1] * f3) + (fArr5[0] * f);
        float[] fArr6 = fArr3[2];
        float f8 = (f5 * fArr6[2]) + (f3 * fArr6[1]) + (f * fArr6[0]);
        float[] fArr7 = viewingConditions.mRgbD;
        float f9 = fArr7[0] * f6;
        float f10 = fArr7[1] * f7;
        float f11 = fArr7[2] * f8;
        float fPow = (float) Math.pow(((double) (Math.abs(f9) * viewingConditions.mFl)) / 100.0d, 0.42d);
        float fPow2 = (float) Math.pow(((double) (Math.abs(f10) * viewingConditions.mFl)) / 100.0d, 0.42d);
        float fPow3 = (float) Math.pow(((double) (Math.abs(f11) * viewingConditions.mFl)) / 100.0d, 0.42d);
        float fSignum = ((Math.signum(f9) * 400.0f) * fPow) / (fPow + 27.13f);
        float fSignum2 = ((Math.signum(f10) * 400.0f) * fPow2) / (fPow2 + 27.13f);
        float fSignum3 = ((Math.signum(f11) * 400.0f) * fPow3) / (fPow3 + 27.13f);
        double d = fSignum3;
        float f12 = ((float) (((((double) fSignum2) * (-12.0d)) + (((double) fSignum) * 11.0d)) + d)) / 11.0f;
        float f13 = ((float) (((double) (fSignum + fSignum2)) - (d * 2.0d))) / 9.0f;
        float f14 = fSignum2 * 20.0f;
        float f15 = ((21.0f * fSignum3) + ((fSignum * 20.0f) + f14)) / 20.0f;
        float f16 = (((fSignum * 40.0f) + f14) + fSignum3) / 20.0f;
        float fAtan2 = (((float) Math.atan2(f13, f12)) * 180.0f) / 3.1415927f;
        if (fAtan2 < 0.0f) {
            fAtan2 += 360.0f;
        } else if (fAtan2 >= 360.0f) {
            fAtan2 -= 360.0f;
        }
        float f17 = (3.1415927f * fAtan2) / 180.0f;
        float fPow4 = ((float) Math.pow((viewingConditions.mNbb * f16) / viewingConditions.mAw, viewingConditions.mC * viewingConditions.mZ)) * 100.0f;
        float fSqrt = (viewingConditions.mAw + 4.0f) * (4.0f / viewingConditions.mC) * ((float) Math.sqrt(fPow4 / 100.0f)) * viewingConditions.mFlRoot;
        float fSqrt2 = ((float) Math.sqrt(((double) fPow4) / 100.0d)) * ((float) Math.pow(1.64d - Math.pow(0.29d, viewingConditions.mN), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(((((double) (((double) fAtan2) < 20.14d ? 360.0f + fAtan2 : fAtan2)) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.mNc) * viewingConditions.mNcb) * ((float) Math.sqrt((f13 * f13) + (f12 * f12)))) / (f15 + 0.305f), 0.9d));
        float f18 = viewingConditions.mFlRoot * fSqrt2;
        float fSqrt3 = ((float) Math.sqrt((r8 * viewingConditions.mC) / (viewingConditions.mAw + 4.0f))) * 50.0f;
        float f19 = (1.7f * fPow4) / ((0.007f * fPow4) + 1.0f);
        float fLog = ((float) Math.log((0.0228f * f18) + 1.0f)) * 43.85965f;
        double d2 = f17;
        float fCos = ((float) Math.cos(d2)) * fLog;
        float fSin = fLog * ((float) Math.sin(d2));
        fArr2[0] = fAtan2;
        fArr2[1] = fSqrt2;
        if (fArr != null) {
            fArr[0] = fPow4;
            fArr[1] = fSqrt;
            fArr[2] = f18;
            fArr[3] = fSqrt3;
            fArr[4] = f19;
            fArr[5] = fCos;
            fArr[6] = fSin;
        }
    }

    @NonNull
    public static CamColor fromJch(@FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f, @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 360.0d) float f3) {
        return fromJchInFrame(f, f2, f3, ViewingConditions.DEFAULT);
    }

    @NonNull
    public static CamColor fromJchInFrame(@FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f, @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = 360.0d) float f3, ViewingConditions viewingConditions) {
        float fSqrt = (viewingConditions.mAw + 4.0f) * (4.0f / viewingConditions.mC) * ((float) Math.sqrt(((double) f) / 100.0d));
        float f4 = viewingConditions.mFlRoot;
        float f5 = fSqrt * f4;
        float f6 = f4 * f2;
        float fSqrt2 = ((float) Math.sqrt(((f2 / ((float) Math.sqrt(r4))) * viewingConditions.mC) / (viewingConditions.mAw + 4.0f))) * 50.0f;
        float f7 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float fLog = ((float) Math.log((((double) f6) * 0.0228d) + 1.0d)) * 43.85965f;
        double d = (3.1415927f * f3) / 180.0f;
        return new CamColor(f3, f2, f, f5, f6, fSqrt2, f7, ((float) Math.cos(d)) * fLog, fLog * ((float) Math.sin(d)));
    }

    public static void getM3HCTfromColor(@ColorInt int i, @NonNull @Size(3) float[] fArr) {
        fromColorInViewingConditions(i, ViewingConditions.DEFAULT, null, fArr);
        fArr[2] = CamUtils.lStarFromInt(i);
    }

    public static int toColor(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f3) {
        return toColor(f, f2, f3, ViewingConditions.DEFAULT);
    }

    public float distance(@NonNull CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((bStar * bStar) + (aStar * aStar) + (jStar * jStar)), 0.63d) * 1.41d);
    }

    @FloatRange(from = DoubleCompanionObject.NEGATIVE_INFINITY, fromInclusive = false, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getAStar() {
        return this.mAstar;
    }

    @FloatRange(from = DoubleCompanionObject.NEGATIVE_INFINITY, fromInclusive = false, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getBStar() {
        return this.mBstar;
    }

    @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getChroma() {
        return this.mChroma;
    }

    @FloatRange(from = 0.0d, to = 360.0d, toInclusive = false)
    public float getHue() {
        return this.mHue;
    }

    @FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y)
    public float getJ() {
        return this.mJ;
    }

    @FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y)
    public float getJStar() {
        return this.mJstar;
    }

    @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getM() {
        return this.mM;
    }

    @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getQ() {
        return this.mQ;
    }

    @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false)
    public float getS() {
        return this.mS;
    }

    @ColorInt
    public int viewed(@NonNull ViewingConditions viewingConditions) {
        float fPow = (float) Math.pow(((double) ((((double) getChroma()) == 0.0d || ((double) getJ()) == 0.0d) ? 0.0f : getChroma() / ((float) Math.sqrt(((double) getJ()) / 100.0d)))) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.mN), 0.73d), 1.1111111111111112d);
        double hue = (getHue() * 3.1415927f) / 180.0f;
        float fCos = ((float) (Math.cos(2.0d + hue) + 3.8d)) * 0.25f;
        float fPow2 = viewingConditions.mAw * ((float) Math.pow(((double) getJ()) / 100.0d, (1.0d / ((double) viewingConditions.mC)) / ((double) viewingConditions.mZ)));
        float f = fCos * 3846.1538f * viewingConditions.mNc * viewingConditions.mNcb;
        float f2 = fPow2 / viewingConditions.mNbb;
        float fSin = (float) Math.sin(hue);
        float fCos2 = (float) Math.cos(hue);
        float f3 = (((0.305f + f2) * 23.0f) * fPow) / (((fPow * 108.0f) * fSin) + (((11.0f * fPow) * fCos2) + (f * 23.0f)));
        float f4 = fCos2 * f3;
        float f5 = f3 * fSin;
        float f6 = f2 * 460.0f;
        float f7 = ((288.0f * f5) + ((451.0f * f4) + f6)) / 1403.0f;
        float f8 = ((f6 - (891.0f * f4)) - (261.0f * f5)) / 1403.0f;
        float f9 = ((f6 - (f4 * 220.0f)) - (f5 * 6300.0f)) / 1403.0f;
        float fSignum = (100.0f / viewingConditions.mFl) * Math.signum(f7) * ((float) Math.pow((float) Math.max(0.0d, (((double) Math.abs(f7)) * 27.13d) / (400.0d - ((double) Math.abs(f7)))), 2.380952380952381d));
        float fSignum2 = (100.0f / viewingConditions.mFl) * Math.signum(f8) * ((float) Math.pow((float) Math.max(0.0d, (((double) Math.abs(f8)) * 27.13d) / (400.0d - ((double) Math.abs(f8)))), 2.380952380952381d));
        float fSignum3 = (100.0f / viewingConditions.mFl) * Math.signum(f9) * ((float) Math.pow((float) Math.max(0.0d, (((double) Math.abs(f9)) * 27.13d) / (400.0d - ((double) Math.abs(f9)))), 2.380952380952381d));
        float[] fArr = viewingConditions.mRgbD;
        float f10 = fSignum / fArr[0];
        float f11 = fSignum2 / fArr[1];
        float f12 = fSignum3 / fArr[2];
        float[][] fArr2 = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr3 = fArr2[0];
        float f13 = (fArr3[2] * f12) + (fArr3[1] * f11) + (fArr3[0] * f10);
        float[] fArr4 = fArr2[1];
        float f14 = (fArr4[2] * f12) + (fArr4[1] * f11) + (fArr4[0] * f10);
        float[] fArr5 = fArr2[2];
        return ColorUtils.XYZToColor(f13, f14, (f12 * fArr5[2]) + (f11 * fArr5[1]) + (f10 * fArr5[0]));
    }

    @ColorInt
    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    @ColorInt
    public static int toColor(@FloatRange(from = 0.0d, to = 360.0d) float f, @FloatRange(from = 0.0d, to = DoubleCompanionObject.POSITIVE_INFINITY, toInclusive = false) float f2, @FloatRange(from = 0.0d, to = ColorUtils.XYZ_WHITE_REFERENCE_Y) float f3, @NonNull ViewingConditions viewingConditions) {
        if (f2 < 1.0d || Math.round(f3) <= 0.0d || Math.round(f3) >= 100.0d) {
            return CamUtils.intFromLStar(f3);
        }
        float fMin = f < 0.0f ? 0.0f : Math.min(360.0f, f);
        float f4 = f2;
        CamColor camColor = null;
        float f5 = 0.0f;
        boolean z = true;
        while (Math.abs(f5 - f2) >= 0.4f) {
            CamColor camColorFindCamByJ = findCamByJ(fMin, f4, f3);
            if (!z) {
                if (camColorFindCamByJ == null) {
                    f2 = f4;
                } else {
                    f5 = f4;
                    camColor = camColorFindCamByJ;
                }
                f4 = ((f2 - f5) / 2.0f) + f5;
            } else {
                if (camColorFindCamByJ != null) {
                    return camColorFindCamByJ.viewed(viewingConditions);
                }
                f4 = ((f2 - f5) / 2.0f) + f5;
                z = false;
            }
        }
        return camColor == null ? CamUtils.intFromLStar(f3) : camColor.viewed(viewingConditions);
    }
}
