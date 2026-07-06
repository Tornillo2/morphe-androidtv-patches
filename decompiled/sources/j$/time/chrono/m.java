package j$.time.chrono;

import j$.time.Instant;
import j$.time.ZoneId;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface m extends Comparable {
    b H(int i, int i2, int i3);

    b J(Map map, j$.time.format.y yVar);

    j K(Instant instant, ZoneId zoneId);

    boolean equals(Object obj);

    b h(long j);

    int hashCode();

    String i();

    String m();

    b n(int i, int i2);

    j$.time.temporal.v q(j$.time.temporal.a aVar);

    List r();

    n s(int i);

    int t(n nVar, int i);

    String toString();

    b y(j$.time.temporal.n nVar);

    e z(j$.time.j jVar);
}
