package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends e5 {
    public static h2 L0(w3 w3Var, Spliterator spliterator) {
        j$.time.e eVar = new j$.time.e(19);
        j$.time.e eVar2 = new j$.time.e(20);
        j$.time.e eVar3 = new j$.time.e(21);
        Objects.requireNonNull(eVar);
        Objects.requireNonNull(eVar2);
        Objects.requireNonNull(eVar3);
        return new h2((Collection) new b4(d7.REFERENCE, eVar3, eVar2, eVar, 3).i(w3Var, spliterator));
    }

    @Override // j$.util.stream.a
    public final d2 E0(w3 w3Var, Spliterator spliterator, IntFunction intFunction) {
        a aVar = (a) w3Var;
        if (c7.DISTINCT.o(aVar.m)) {
            return w3Var.g0(spliterator, false, intFunction);
        }
        if (c7.ORDERED.o(aVar.m)) {
            return L0(w3Var, spliterator);
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        j$.time.format.v vVar = new j$.time.format.v(5, atomicBoolean, concurrentHashMap);
        Objects.requireNonNull(vVar);
        new n0(vVar, false).a(w3Var, spliterator);
        Collection collectionKeySet = concurrentHashMap.keySet();
        if (atomicBoolean.get()) {
            HashSet hashSet = new HashSet(collectionKeySet);
            hashSet.add(null);
            collectionKeySet = hashSet;
        }
        return new h2(collectionKeySet);
    }

    @Override // j$.util.stream.a
    public final Spliterator F0(a aVar, Spliterator spliterator) {
        if (c7.DISTINCT.o(aVar.m)) {
            return aVar.x0(spliterator);
        }
        if (c7.ORDERED.o(aVar.m)) {
            return L0(aVar, spliterator).spliterator();
        }
        return new q7(aVar.x0(spliterator), new ConcurrentHashMap());
    }

    @Override // j$.util.stream.a
    public final o5 H0(int i, o5 o5Var) {
        Objects.requireNonNull(o5Var);
        if (c7.DISTINCT.o(i)) {
            return o5Var;
        }
        if (c7.SORTED.o(i)) {
            return new k(o5Var);
        }
        return new l(o5Var);
    }
}
