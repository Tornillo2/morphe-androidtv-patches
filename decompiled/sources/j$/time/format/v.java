package j$.time.format;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.c7;
import j$.util.stream.d7;
import j$.util.stream.l1;
import j$.util.stream.p1;
import j$.util.stream.q1;
import j$.util.stream.q7;
import j$.util.stream.r1;
import j$.util.stream.t8;
import j$.util.stream.w3;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class v implements BiConsumer, BiFunction, Consumer, Supplier, t8 {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ v(int i, Object obj, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.a) {
        }
        return BiConsumer$CC.$default$andThen(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return ((Function) this.c).apply(((BiFunction) this.b).apply(obj, obj2));
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.a) {
            case 1:
                ConcurrentMap concurrentMap = (ConcurrentMap) this.b;
                BiFunction biFunction = (BiFunction) this.c;
                while (!concurrentMap.replace(obj, obj2, biFunction.apply(obj, obj2)) && (obj2 = concurrentMap.get(obj)) != null) {
                }
                break;
            default:
                BiConsumer biConsumer = (BiConsumer) this.b;
                BiConsumer biConsumer2 = (BiConsumer) this.c;
                biConsumer.accept(obj, obj2);
                biConsumer2.accept(obj, obj2);
                break;
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        return new l1((q1) this.b, (Predicate) this.c);
    }

    public v(d7 d7Var, q1 q1Var, Supplier supplier) {
        this.a = 7;
        this.b = q1Var;
        this.c = supplier;
    }

    @Override // j$.util.stream.t8
    public int v() {
        return c7.u | c7.r;
    }

    @Override // j$.util.stream.t8
    public Object f(j$.util.stream.a aVar, Spliterator spliterator) {
        p1 p1Var = (p1) ((Supplier) this.c).get();
        aVar.v0(spliterator, p1Var);
        return Boolean.valueOf(p1Var.b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.util.stream.t8
    public Object i(w3 w3Var, Spliterator spliterator) {
        return (Boolean) new r1(this, (j$.util.stream.a) w3Var, spliterator).invoke();
    }

    public v(Map map) {
        this.a = 0;
        this.b = map;
        HashMap map2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            HashMap map3 = new HashMap();
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                String str = (String) entry2.getValue();
                String str2 = (String) entry2.getValue();
                Long l = (Long) entry2.getKey();
                u uVar = b.b;
                map3.put(str, new AbstractMap.SimpleImmutableEntry(str2, l));
            }
            ArrayList arrayList2 = new ArrayList(map3.values());
            Collections.sort(arrayList2, b.b);
            map2.put((a0) entry.getKey(), arrayList2);
            arrayList.addAll(arrayList2);
            map2.put(null, arrayList);
        }
        Collections.sort(arrayList, b.b);
        this.c = map2;
    }

    public String a(long j, a0 a0Var) {
        Map map = (Map) ((Map) this.b).get(a0Var);
        if (map != null) {
            return (String) map.get(Long.valueOf(j));
        }
        return null;
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.a) {
            case 4:
                Consumer consumer = (Consumer) this.b;
                Consumer consumer2 = (Consumer) this.c;
                consumer.accept(obj);
                consumer2.accept(obj);
                break;
            case 5:
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.b;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.c;
                if (obj != null) {
                    concurrentHashMap.putIfAbsent(obj, Boolean.TRUE);
                } else {
                    atomicBoolean.set(true);
                }
                break;
            case 6:
            case 7:
            default:
                q7 q7Var = (q7) this.b;
                Consumer consumer3 = (Consumer) this.c;
                if (q7Var.b.putIfAbsent(obj != null ? obj : q7.d, Boolean.TRUE) == null) {
                    consumer3.accept(obj);
                }
                break;
            case 8:
                ((BiConsumer) this.b).accept(this.c, obj);
                break;
        }
    }
}
