package j$.time;

import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e implements j$.time.temporal.o, Function, IntFunction, Supplier, BiConsumer, BinaryOperator, DoubleBinaryOperator, ObjDoubleConsumer, DoubleFunction {
    public final /* synthetic */ int a;

    public /* synthetic */ e(int i) {
        this.a = i;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.a) {
            case 14:
                break;
            case 20:
                break;
            case 21:
                break;
            case 22:
                break;
            case 26:
                break;
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen, reason: collision with other method in class */
    public /* synthetic */ Function mo567andThen(Function function) {
        switch (this.a) {
        }
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d) {
        return Double.valueOf(d);
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        switch (this.a) {
            default:
                Set set = Collectors.a;
            case 10:
                return obj;
        }
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d, double d2) {
        return Math.min(d, d2);
    }

    public /* synthetic */ Function compose(Function function) {
        switch (this.a) {
        }
        return Function$CC.$default$compose(this, function);
    }

    @Override // j$.time.temporal.o
    public j$.time.temporal.m o(j$.time.temporal.m mVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
        return mVar.c(mVar.l(aVar).d, aVar);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        List list = (List) obj;
        Set set = Collectors.a;
        list.addAll((List) obj2);
        return list;
    }

    public Object g(j$.time.temporal.n nVar) {
        switch (this.a) {
            case 0:
                return Instant.O(nVar);
            case 1:
                ZoneId zoneId = (ZoneId) nVar.B(j$.time.temporal.s.a);
                if (zoneId == null || (zoneId instanceof z)) {
                    return null;
                }
                return zoneId;
            case 2:
            default:
                j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_DAY;
                if (nVar.e(aVar)) {
                    return l.S(nVar.C(aVar));
                }
                return null;
            case 3:
                return (ZoneId) nVar.B(j$.time.temporal.s.a);
            case 4:
                return (j$.time.chrono.m) nVar.B(j$.time.temporal.s.b);
            case 5:
                return (j$.time.temporal.t) nVar.B(j$.time.temporal.s.c);
            case 6:
                j$.time.temporal.a aVar2 = j$.time.temporal.a.OFFSET_SECONDS;
                if (nVar.e(aVar2)) {
                    return z.U(nVar.j(aVar2));
                }
                return null;
            case 7:
                ZoneId zoneId2 = (ZoneId) nVar.B(j$.time.temporal.s.a);
                return zoneId2 != null ? zoneId2 : (ZoneId) nVar.B(j$.time.temporal.s.d);
            case 8:
                j$.time.temporal.a aVar3 = j$.time.temporal.a.EPOCH_DAY;
                if (nVar.e(aVar3)) {
                    return h.Y(nVar.C(aVar3));
                }
                return null;
        }
    }

    public String toString() {
        switch (this.a) {
            case 3:
                return "ZoneId";
            case 4:
                return "Chronology";
            case 5:
                return "Precision";
            case 6:
                return "ZoneOffset";
            case 7:
                return "Zone";
            case 8:
                return "LocalDate";
            case 9:
                return "LocalTime";
            default:
                return super.toString();
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 12:
                return new j$.util.z();
            case 13:
                return new ArrayList();
            case 14:
            case 17:
            case 18:
            default:
                return new double[4];
            case 15:
                return new j$.util.a0();
            case 16:
                return new j$.util.c0();
            case 19:
                return new LinkedHashSet();
        }
    }

    @Override // java.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        switch (this.a) {
            case 25:
                double[] dArr = (double[]) obj;
                dArr[2] = dArr[2] + 1.0d;
                Collectors.a(dArr, d);
                dArr[3] = dArr[3] + d;
                break;
            default:
                ((j$.util.z) obj).accept(d);
                break;
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.a) {
            case 14:
                ((List) obj).add(obj2);
                break;
            case 20:
                ((LinkedHashSet) obj).add(obj2);
                break;
            case 21:
                ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                break;
            case 22:
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                Collectors.a(dArr, dArr2[0]);
                Collectors.a(dArr, dArr2[1]);
                dArr[2] = dArr[2] + dArr2[2];
                break;
            case 26:
                double[] dArr3 = (double[]) obj;
                double[] dArr4 = (double[]) obj2;
                Collectors.a(dArr3, dArr4[0]);
                Collectors.a(dArr3, dArr4[1]);
                dArr3[2] = dArr3[2] + dArr4[2];
                dArr3[3] = dArr3[3] + dArr4[3];
                break;
            default:
                ((j$.util.z) obj).a((j$.util.z) obj2);
                break;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i) {
        return new Object[i];
    }
}
