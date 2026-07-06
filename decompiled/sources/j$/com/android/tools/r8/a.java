package j$.com.android.tools.r8;

import androidx.exifinterface.media.ExifInterface;
import j$.time.b;
import j$.time.c;
import j$.time.chrono.h0;
import j$.time.chrono.i;
import j$.time.chrono.j;
import j$.time.chrono.m;
import j$.time.chrono.n;
import j$.time.chrono.p;
import j$.time.chrono.t;
import j$.time.chrono.w;
import j$.time.e;
import j$.time.temporal.r;
import j$.time.temporal.s;
import j$.time.temporal.u;
import j$.time.z;
import j$.util.DesugarTimeZone;
import j$.util.List;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.TimeZoneRetargetInterface;
import j$.util.b0;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.concurrent.l;
import j$.util.d0;
import j$.util.g0;
import j$.util.j0;
import j$.util.m1;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ long F(long j, long j2) {
        long j3 = j + j2;
        if (((j2 ^ j) < 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ List G(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static /* synthetic */ Map.Entry H(Object obj, Object obj2) {
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(obj), Objects.requireNonNull(obj2));
    }

    public static /* synthetic */ boolean I(Unsafe unsafe, Object obj, long j, l lVar) {
        while (true) {
            Unsafe unsafe2 = unsafe;
            Object obj2 = obj;
            long j2 = j;
            l lVar2 = lVar;
            if (unsafe2.compareAndSwapObject(obj2, j2, (Object) null, lVar2)) {
                return true;
            }
            if (unsafe2.getObject(obj2, j2) != null) {
                return false;
            }
            unsafe = unsafe2;
            obj = obj2;
            j = j2;
            lVar = lVar2;
        }
    }

    public static /* synthetic */ long J(long j, long j2) {
        long j3 = j % j2;
        if (j3 == 0) {
            return 0L;
        }
        return (((j ^ j2) >> 63) | 1) > 0 ? j3 : j3 + j2;
    }

    public static /* synthetic */ long K(long j, long j2) {
        long j3 = j / j2;
        return (j - (j2 * j3) != 0 && (((j ^ j2) >> 63) | 1) < 0) ? j3 - 1 : j3;
    }

    public static /* synthetic */ long L(long j, long j2) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j);
        if (iNumberOfLeadingZeros > 65) {
            return j * j2;
        }
        if (iNumberOfLeadingZeros >= 64) {
            if ((j >= 0) | (j2 != Long.MIN_VALUE)) {
                long j3 = j * j2;
                if (j == 0 || j3 / j == j2) {
                    return j3;
                }
            }
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long M(long j, long j2) {
        long j3 = j - j2;
        if (((j2 ^ j) >= 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ void P(List list, Comparator comparator) {
        if (list instanceof j$.util.List) {
            ((j$.util.List) list).sort(comparator);
        } else {
            List.CC.$default$sort(list, comparator);
        }
    }

    public static Optional z(j$.util.Optional optional) {
        if (optional == null) {
            return null;
        }
        if (optional.isPresent()) {
            return Optional.of(optional.get());
        }
        return Optional.empty();
    }

    public static j$.util.Optional v(Optional optional) {
        if (optional == null) {
            return null;
        }
        if (optional.isPresent()) {
            return j$.util.Optional.of(optional.get());
        }
        return j$.util.Optional.empty();
    }

    public static OptionalDouble A(j$.util.OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        if (optionalDouble.isPresent()) {
            return OptionalDouble.of(optionalDouble.getAsDouble());
        }
        return OptionalDouble.empty();
    }

    public static j$.util.OptionalDouble w(OptionalDouble optionalDouble) {
        if (optionalDouble == null) {
            return null;
        }
        if (optionalDouble.isPresent()) {
            return j$.util.OptionalDouble.of(optionalDouble.getAsDouble());
        }
        return j$.util.OptionalDouble.empty();
    }

    public static OptionalLong C(j$.util.OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        if (optionalLong.isPresent()) {
            return OptionalLong.of(optionalLong.getAsLong());
        }
        return OptionalLong.empty();
    }

    public static j$.util.OptionalLong y(OptionalLong optionalLong) {
        if (optionalLong == null) {
            return null;
        }
        if (optionalLong.isPresent()) {
            return j$.util.OptionalLong.of(optionalLong.getAsLong());
        }
        return j$.util.OptionalLong.empty();
    }

    public static OptionalInt B(j$.util.OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        if (optionalInt.isPresent()) {
            return OptionalInt.of(optionalInt.getAsInt());
        }
        return OptionalInt.empty();
    }

    public static j$.util.OptionalInt x(OptionalInt optionalInt) {
        if (optionalInt == null) {
            return null;
        }
        if (optionalInt.isPresent()) {
            return j$.util.OptionalInt.of(optionalInt.getAsInt());
        }
        return j$.util.OptionalInt.empty();
    }

    public static String N(Object obj, Object obj2) {
        String string;
        String string2;
        String str = AbstractJsonLexerKt.NULL;
        if (obj == null || (string = obj.toString()) == null) {
            string = AbstractJsonLexerKt.NULL;
        }
        int length = string.length();
        if (obj2 != null && (string2 = obj2.toString()) != null) {
            str = string2;
        }
        int length2 = str.length();
        char[] cArr = new char[length + length2 + 1];
        string.getChars(0, length, cArr, 0);
        cArr[length] = '=';
        str.getChars(0, length2, cArr, length + 1);
        return new String(cArr);
    }

    public static void D(Iterator it, Consumer consumer) {
        if (it instanceof b0) {
            ((b0) it).forEachRemaining(consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        while (it.hasNext()) {
            consumer.n(it.next());
        }
    }

    public static boolean l(n nVar, r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.ERA : rVar != null && rVar.j(nVar);
    }

    public static m E(j$.time.temporal.n nVar) {
        Objects.requireNonNull(nVar, "temporal");
        Object objRequireNonNull = (m) nVar.B(s.b);
        t tVar = t.c;
        if (objRequireNonNull == null) {
            objRequireNonNull = Objects.requireNonNull(tVar, "defaultObj");
        }
        return (m) objRequireNonNull;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static j$.time.a Q() {
        TimeZone timeZone = TimeZone.getDefault();
        return new j$.time.a(timeZone instanceof TimeZoneRetargetInterface ? ((TimeZoneRetargetInterface) timeZone).toZoneId() : DesugarTimeZone.toZoneId(timeZone));
    }

    public static int h(j jVar, r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = i.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new u("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i != 2) {
                return jVar.p().j(rVar);
            }
            return jVar.g().b;
        }
        return s.a(jVar, rVar);
    }

    public static int i(n nVar, r rVar) {
        if (rVar == j$.time.temporal.a.ERA) {
            return nVar.getValue();
        }
        return s.a(nVar, rVar);
    }

    public static long j(n nVar, r rVar) {
        if (rVar == j$.time.temporal.a.ERA) {
            return nVar.getValue();
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new u(c.a("Unsupported field: ", rVar));
        }
        return rVar.w(nVar);
    }

    public static m O(String str) {
        ConcurrentHashMap concurrentHashMap = j$.time.chrono.a.a;
        Objects.requireNonNull(str, "id");
        while (true) {
            ConcurrentHashMap concurrentHashMap2 = j$.time.chrono.a.a;
            m mVar = (m) concurrentHashMap2.get(str);
            if (mVar == null) {
                mVar = (m) j$.time.chrono.a.b.get(str);
            }
            if (mVar != null) {
                return mVar;
            }
            if (concurrentHashMap2.get(ExifInterface.TAG_RW2_ISO) != null) {
                for (m mVar2 : ServiceLoader.load(m.class)) {
                    if (str.equals(mVar2.i()) || str.equals(mVar2.m())) {
                        return mVar2;
                    }
                }
                throw new b("Unknown chronology: " + str);
            }
            p pVar = p.l;
            pVar.getClass();
            j$.time.chrono.a.l(pVar, "Hijrah-umalqura");
            w wVar = w.c;
            wVar.getClass();
            j$.time.chrono.a.l(wVar, "Japanese");
            j$.time.chrono.b0 b0Var = j$.time.chrono.b0.c;
            b0Var.getClass();
            j$.time.chrono.a.l(b0Var, "Minguo");
            h0 h0Var = h0.c;
            h0Var.getClass();
            j$.time.chrono.a.l(h0Var, "ThaiBuddhist");
            try {
                for (j$.time.chrono.a aVar : Arrays.asList(new j$.time.chrono.a[0])) {
                    if (!aVar.i().equals(ExifInterface.TAG_RW2_ISO)) {
                        j$.time.chrono.a.l(aVar, aVar.i());
                    }
                }
                t tVar = t.c;
                tVar.getClass();
                j$.time.chrono.a.l(tVar, ExifInterface.TAG_RW2_ISO);
            } catch (Throwable th) {
                throw new ServiceConfigurationError(th.getMessage(), th);
            }
        }
    }

    public static Object p(n nVar, e eVar) {
        if (eVar == s.c) {
            return j$.time.temporal.b.ERAS;
        }
        return s.c(nVar, eVar);
    }

    public static Object n(j$.time.chrono.e eVar, e eVar2) {
        if (eVar2 == s.a || eVar2 == s.e || eVar2 == s.d) {
            return null;
        }
        if (eVar2 == s.g) {
            return eVar.b();
        }
        if (eVar2 == s.b) {
            return eVar.a();
        }
        if (eVar2 == s.c) {
            return j$.time.temporal.b.NANOS;
        }
        return eVar2.g(eVar);
    }

    public static boolean k(j$.time.chrono.b bVar, r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).isDateBased();
        }
        return rVar != null && rVar.j(bVar);
    }

    public static long q(j$.time.chrono.e eVar, z zVar) {
        Objects.requireNonNull(zVar, "offset");
        return ((eVar.f().D() * 86400) + ((long) eVar.b().a0())) - ((long) zVar.b);
    }

    public static Object o(j jVar, e eVar) {
        if (eVar == s.e || eVar == s.a) {
            return jVar.A();
        }
        if (eVar == s.d) {
            return jVar.g();
        }
        if (eVar == s.g) {
            return jVar.b();
        }
        if (eVar == s.b) {
            return jVar.a();
        }
        if (eVar == s.c) {
            return j$.time.temporal.b.NANOS;
        }
        return eVar.g(jVar);
    }

    public static int c(j$.time.chrono.e eVar, j$.time.chrono.e eVar2) {
        int iL = eVar.f().compareTo(eVar2.f());
        return (iL == 0 && (iL = eVar.b().compareTo(eVar2.b())) == 0) ? ((j$.time.chrono.a) eVar.a()).i().compareTo(eVar2.a().i()) : iL;
    }

    public static Object m(j$.time.chrono.b bVar, e eVar) {
        if (eVar == s.a || eVar == s.e || eVar == s.d || eVar == s.g) {
            return null;
        }
        if (eVar == s.b) {
            return bVar.a();
        }
        if (eVar == s.c) {
            return j$.time.temporal.b.DAYS;
        }
        return eVar.g(bVar);
    }

    public static j$.time.temporal.m a(j$.time.chrono.b bVar, j$.time.temporal.m mVar) {
        return mVar.c(bVar.D(), j$.time.temporal.a.EPOCH_DAY);
    }

    public static long r(j jVar) {
        return ((jVar.f().D() * 86400) + ((long) jVar.b().a0())) - ((long) jVar.g().b);
    }

    public static int d(j jVar, j jVar2) {
        int iCompare = Long.compare(jVar.M(), jVar2.M());
        return (iCompare == 0 && (iCompare = jVar.b().d - jVar2.b().d) == 0 && (iCompare = jVar.p().G(jVar2.p())) == 0 && (iCompare = jVar.A().i().compareTo(jVar2.A().i())) == 0) ? ((j$.time.chrono.a) jVar.a()).i().compareTo(jVar2.a().i()) : iCompare;
    }

    public static boolean t(Spliterator.OfInt ofInt, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            return ofInt.tryAdvance((IntConsumer) consumer);
        }
        if (m1.a) {
            m1.a(ofInt.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return ofInt.tryAdvance((IntConsumer) new g0(consumer, 0));
    }

    public static void f(Spliterator.OfInt ofInt, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            ofInt.forEachRemaining((IntConsumer) consumer);
        } else {
            if (m1.a) {
                m1.a(ofInt.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            ofInt.forEachRemaining((IntConsumer) new g0(consumer, 0));
        }
    }

    public static int b(j$.time.chrono.b bVar, j$.time.chrono.b bVar2) {
        int iCompare = Long.compare(bVar.D(), bVar2.D());
        if (iCompare != 0) {
            return iCompare;
        }
        return ((j$.time.chrono.a) bVar.a()).i().compareTo(bVar2.a().i());
    }

    public static boolean u(Spliterator.OfLong ofLong, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            return ofLong.tryAdvance((LongConsumer) consumer);
        }
        if (m1.a) {
            m1.a(ofLong.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return ofLong.tryAdvance((LongConsumer) new j0(consumer, 0));
    }

    public static void g(Spliterator.OfLong ofLong, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            ofLong.forEachRemaining((LongConsumer) consumer);
        } else {
            if (m1.a) {
                m1.a(ofLong.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            ofLong.forEachRemaining((LongConsumer) new j0(consumer, 0));
        }
    }

    public static boolean s(Spliterator.OfDouble ofDouble, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            return ofDouble.tryAdvance((DoubleConsumer) consumer);
        }
        if (m1.a) {
            m1.a(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
            throw null;
        }
        Objects.requireNonNull(consumer);
        return ofDouble.tryAdvance((DoubleConsumer) new d0(consumer, 0));
    }

    public static void e(Spliterator.OfDouble ofDouble, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            ofDouble.forEachRemaining((DoubleConsumer) consumer);
        } else {
            if (m1.a) {
                m1.a(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
                throw null;
            }
            Objects.requireNonNull(consumer);
            ofDouble.forEachRemaining((DoubleConsumer) new d0(consumer, 0));
        }
    }

    public Spliterator trySplit() {
        return null;
    }

    public boolean tryAdvance(Object obj) {
        Objects.requireNonNull(obj);
        return false;
    }

    public void forEachRemaining(Object obj) {
        Objects.requireNonNull(obj);
    }

    public long estimateSize() {
        return 0L;
    }

    public int characteristics() {
        return 16448;
    }
}
