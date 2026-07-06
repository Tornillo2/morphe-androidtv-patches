package j$.time.format;

import com.amazon.ion.Timestamp;
import j$.time.ZoneId;
import java.text.ParsePosition;
import java.util.AbstractMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements f {
    public static volatile Map.Entry b;
    public static volatile Map.Entry c;
    public final /* synthetic */ int a;

    public /* synthetic */ h(int i) {
        this.a = i;
    }

    @Override // j$.time.format.f
    public final boolean j(t tVar, StringBuilder sb) {
        switch (this.a) {
            case 0:
                Long lA = tVar.a(j$.time.temporal.a.INSTANT_SECONDS);
                j$.time.temporal.n nVar = tVar.a;
                j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_SECOND;
                Long lValueOf = nVar.e(aVar) ? Long.valueOf(nVar.C(aVar)) : null;
                int i = 0;
                if (lA == null) {
                    return false;
                }
                long jLongValue = lA.longValue();
                int iA = aVar.b.a(lValueOf != null ? lValueOf.longValue() : 0L, aVar);
                if (jLongValue >= -62167219200L) {
                    long j = jLongValue - Timestamp.MAXIMUM_TIMESTAMP_IN_EPOCH_SECONDS;
                    long jK = j$.com.android.tools.r8.a.K(j, 315569520000L) + 1;
                    j$.time.j jVarR = j$.time.j.R(j$.com.android.tools.r8.a.J(j, 315569520000L) - 62167219200L, 0, j$.time.z.f);
                    if (jK > 0) {
                        sb.append('+');
                        sb.append(jK);
                    }
                    sb.append(jVarR);
                    if (jVarR.b.c == 0) {
                        sb.append(":00");
                    }
                } else {
                    long j2 = jLongValue + 62167219200L;
                    long j3 = j2 / 315569520000L;
                    long j4 = j2 % 315569520000L;
                    j$.time.j jVarR2 = j$.time.j.R(j4 - 62167219200L, 0, j$.time.z.f);
                    int length = sb.length();
                    sb.append(jVarR2);
                    if (jVarR2.b.c == 0) {
                        sb.append(":00");
                    }
                    if (j3 < 0) {
                        if (jVarR2.a.a == -10000) {
                            sb.replace(length, length + 2, Long.toString(j3 - 1));
                        } else if (j4 == 0) {
                            sb.insert(length, j3);
                        } else {
                            sb.insert(length + 1, Math.abs(j3));
                        }
                    }
                }
                if (iA > 0) {
                    sb.append('.');
                    int i2 = 100000000;
                    while (true) {
                        if (iA > 0 || i % 3 != 0 || i < -2) {
                            int i3 = iA / i2;
                            sb.append((char) (i3 + 48));
                            iA -= i3 * i2;
                            i2 /= 10;
                            i++;
                        }
                    }
                }
                sb.append('Z');
                return true;
            default:
                j$.time.e eVar = p.f;
                j$.time.temporal.n nVar2 = tVar.a;
                Object objB = nVar2.B(eVar);
                if (objB == null && tVar.c == 0) {
                    throw new j$.time.b("Unable to extract " + eVar + " from temporal " + nVar2);
                }
                ZoneId zoneId = (ZoneId) objB;
                if (zoneId == null) {
                    return false;
                }
                sb.append(zoneId.i());
                return true;
        }
    }

    @Override // j$.time.format.f
    public final int k(q qVar, CharSequence charSequence, int i) {
        int i2;
        int i3 = 1;
        switch (this.a) {
            case 0:
                p pVar = new p();
                pVar.a(a.f);
                pVar.c('T');
                j$.time.temporal.a aVar = j$.time.temporal.a.HOUR_OF_DAY;
                pVar.g(aVar, 2);
                pVar.c(':');
                j$.time.temporal.a aVar2 = j$.time.temporal.a.MINUTE_OF_HOUR;
                pVar.g(aVar2, 2);
                pVar.c(':');
                j$.time.temporal.a aVar3 = j$.time.temporal.a.SECOND_OF_MINUTE;
                pVar.g(aVar3, 2);
                j$.time.temporal.a aVar4 = j$.time.temporal.a.NANO_OF_SECOND;
                pVar.b(new g(aVar4));
                pVar.c('Z');
                e eVar = pVar.l(Locale.getDefault(), y.SMART, null).a;
                if (eVar.b) {
                    eVar = new e(eVar.a, false);
                }
                q qVar2 = new q(qVar.a);
                qVar2.b = qVar.b;
                qVar2.c = qVar.c;
                int iK = eVar.k(qVar2, charSequence, i);
                if (iK < 0) {
                    return iK;
                }
                long jLongValue = qVar2.d(j$.time.temporal.a.YEAR).longValue();
                int iIntValue = qVar2.d(j$.time.temporal.a.MONTH_OF_YEAR).intValue();
                int iIntValue2 = qVar2.d(j$.time.temporal.a.DAY_OF_MONTH).intValue();
                int iIntValue3 = qVar2.d(aVar).intValue();
                int iIntValue4 = qVar2.d(aVar2).intValue();
                Long lD = qVar2.d(aVar3);
                Long lD2 = qVar2.d(aVar4);
                int iIntValue5 = lD != null ? lD.intValue() : 0;
                int iIntValue6 = lD2 != null ? lD2.intValue() : 0;
                if (iIntValue3 == 24 && iIntValue4 == 0 && iIntValue5 == 0 && iIntValue6 == 0) {
                    iIntValue3 = 0;
                } else if (iIntValue3 == 23 && iIntValue4 == 59 && iIntValue5 == 60) {
                    qVar.c().d = true;
                    i3 = 0;
                    iIntValue5 = 59;
                } else {
                    i3 = 0;
                }
                int i4 = ((int) jLongValue) % 10000;
                try {
                    j$.time.j jVar = j$.time.j.c;
                    j$.time.h hVarX = j$.time.h.X(i4, iIntValue, iIntValue2);
                    j$.time.l lVarR = j$.time.l.R(iIntValue3, iIntValue4, iIntValue5, 0);
                    return qVar.f(aVar4, iIntValue6, i, qVar.f(j$.time.temporal.a.INSTANT_SECONDS, j$.com.android.tools.r8.a.q(new j$.time.j(hVarX, lVarR).W(hVarX.b0(i3), lVarR), j$.time.z.f) + j$.com.android.tools.r8.a.L(jLongValue / 10000, 315569520000L), i, iK));
                } catch (RuntimeException unused) {
                    return ~i;
                }
            default:
                int length = charSequence.length();
                if (i > length) {
                    throw new IndexOutOfBoundsException();
                }
                if (i != length) {
                    char cCharAt = charSequence.charAt(i);
                    if (cCharAt == '+' || cCharAt == '-') {
                        return a(qVar, charSequence, i, i, j.e);
                    }
                    int i5 = i + 2;
                    if (length >= i5) {
                        char cCharAt2 = charSequence.charAt(i + 1);
                        if (qVar.a(cCharAt, 'U') && qVar.a(cCharAt2, 'T')) {
                            int i6 = i + 3;
                            return (length < i6 || !qVar.a(charSequence.charAt(i5), 'C')) ? a(qVar, charSequence, i, i5, j.f) : a(qVar, charSequence, i, i6, j.f);
                        }
                        if (qVar.a(cCharAt, 'G') && length >= (i2 = i + 3) && qVar.a(cCharAt2, 'M') && qVar.a(charSequence.charAt(i5), 'T')) {
                            int i7 = i + 4;
                            if (length < i7 || !qVar.a(charSequence.charAt(i2), '0')) {
                                return a(qVar, charSequence, i, i2, j.f);
                            }
                            qVar.e(ZoneId.O("GMT0", true));
                            return i7;
                        }
                    }
                    Set<String> set = j$.time.zone.i.d;
                    int size = set.size();
                    Map.Entry simpleImmutableEntry = qVar.b ? b : c;
                    if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
                        synchronized (this) {
                            try {
                                simpleImmutableEntry = qVar.b ? b : c;
                                if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
                                    Integer numValueOf = Integer.valueOf(size);
                                    l lVar = qVar.b ? new l("", null, null) : new k("", null, null);
                                    for (String str : set) {
                                        lVar.a(str, str);
                                    }
                                    simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry(numValueOf, lVar);
                                    if (qVar.b) {
                                        b = simpleImmutableEntry;
                                    } else {
                                        c = simpleImmutableEntry;
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    l lVar2 = (l) simpleImmutableEntry.getValue();
                    ParsePosition parsePosition = new ParsePosition(i);
                    String strC = lVar2.c(charSequence, parsePosition);
                    if (strC != null) {
                        qVar.e(ZoneId.O(strC, true));
                        return parsePosition.getIndex();
                    }
                    if (qVar.a(cCharAt, 'Z')) {
                        qVar.e(j$.time.z.f);
                        return i + 1;
                    }
                    break;
                }
                return ~i;
        }
    }

    public static int a(q qVar, CharSequence charSequence, int i, int i2, j jVar) {
        String upperCase = charSequence.subSequence(i, i2).toString().toUpperCase();
        if (i2 >= charSequence.length()) {
            qVar.e(ZoneId.O(upperCase, true));
            return i2;
        }
        if (charSequence.charAt(i2) != '0' && !qVar.a(charSequence.charAt(i2), 'Z')) {
            q qVar2 = new q(qVar.a);
            qVar2.b = qVar.b;
            qVar2.c = qVar.c;
            int iK = jVar.k(qVar2, charSequence, i2);
            try {
                if (iK < 0) {
                    if (jVar == j.e) {
                        return ~i;
                    }
                    qVar.e(ZoneId.O(upperCase, true));
                    return i2;
                }
                qVar.e(ZoneId.P(upperCase, j$.time.z.U((int) qVar2.d(j$.time.temporal.a.OFFSET_SECONDS).longValue())));
                return iK;
            } catch (j$.time.b unused) {
                return ~i;
            }
        }
        qVar.e(ZoneId.O(upperCase, true));
        return i2;
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return "Instant()";
            default:
                return "ZoneRegionId()";
        }
    }
}
