package j$.util;

import j$.util.Map;
import j$.util.function.BiFunction$CC;
import j$.util.function.Consumer$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import j$.util.stream.DoubleStream;
import j$.util.stream.IntStream;
import j$.util.stream.LongStream;
import j$.util.stream.Stream;
import j$.util.stream.b7;
import j$.util.stream.l8;
import j$.util.stream.o5;
import j$.util.stream.s7;
import j$.util.stream.u7;
import j$.util.stream.w7;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class q implements Consumer, Predicate, Supplier, BinaryOperator, DoubleFunction, Function, LongFunction, BooleanSupplier {
    public final /* synthetic */ int a;
    public Object b;

    public /* synthetic */ q(int i) {
        this.a = i;
    }

    public /* synthetic */ q(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
            case 0:
                break;
            case 8:
                break;
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen */
    public /* synthetic */ Function mo567andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        return !((Predicate) this.b).test(obj);
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Object objApply = ((Function) this.b).apply(obj);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof Stream) {
            return Stream.Wrapper.convert((Stream) objApply);
        }
        if (objApply instanceof java.util.stream.Stream) {
            return Stream.VivifiedWrapper.convert((java.util.stream.Stream) objApply);
        }
        if (objApply instanceof IntStream) {
            return IntStream.Wrapper.convert((IntStream) objApply);
        }
        if (objApply instanceof java.util.stream.IntStream) {
            return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) objApply);
        }
        if (objApply instanceof DoubleStream) {
            return j$.util.stream.b0.f((DoubleStream) objApply);
        }
        if (objApply instanceof java.util.stream.DoubleStream) {
            return j$.util.stream.a0.f((java.util.stream.DoubleStream) objApply);
        }
        if (objApply instanceof LongStream) {
            return j$.util.stream.j1.f((LongStream) objApply);
        }
        if (objApply instanceof java.util.stream.LongStream) {
            return j$.util.stream.i1.f((java.util.stream.LongStream) objApply);
        }
        g.a(objApply.getClass(), "java.util.stream.*Stream");
        throw null;
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d) {
        Object objApply = ((DoubleFunction) this.b).apply(d);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof DoubleStream) {
            return j$.util.stream.b0.f((DoubleStream) objApply);
        }
        if (objApply instanceof java.util.stream.DoubleStream) {
            return j$.util.stream.a0.f((java.util.stream.DoubleStream) objApply);
        }
        g.a(objApply.getClass(), "java.util.stream.DoubleStream");
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        Object objApply = ((LongFunction) this.b).apply(j);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof LongStream) {
            return j$.util.stream.j1.f((LongStream) objApply);
        }
        if (objApply instanceof java.util.stream.LongStream) {
            return j$.util.stream.i1.f((java.util.stream.LongStream) objApply);
        }
        g.a(objApply.getClass(), "java.util.stream.LongStream");
        throw null;
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        switch (this.a) {
            case 11:
                s7 s7Var = (s7) this.b;
                return s7Var.d.tryAdvance(s7Var.e);
            case 12:
                u7 u7Var = (u7) this.b;
                return u7Var.d.tryAdvance(u7Var.e);
            case 13:
                w7 w7Var = (w7) this.b;
                return w7Var.d.tryAdvance(w7Var.e);
            default:
                l8 l8Var = (l8) this.b;
                return l8Var.d.tryAdvance(l8Var.e);
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.a) {
            case 2:
                return ((j$.util.stream.a) this.b).I0(0);
            default:
                return (Spliterator) this.b;
        }
    }

    public void a(b7 b7Var) {
        ((EnumMap) ((java.util.Map) this.b)).put(b7Var, 1);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        BinaryOperator binaryOperator = (BinaryOperator) this.b;
        java.util.Map map = (java.util.Map) obj;
        java.util.Set set = Collectors.a;
        for (Map.Entry entry : ((java.util.Map) obj2).entrySet()) {
            Map.EL.merge(map, entry.getKey(), entry.getValue(), binaryOperator);
        }
        return map;
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((Consumer) this.b).accept(new r((Map.Entry) obj));
                break;
            case 8:
                ((o5) this.b).accept(obj);
                break;
            default:
                ((java.util.List) this.b).add(obj);
                break;
        }
    }
}
