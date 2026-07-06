package j$.time.chrono;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends j$.time.temporal.m, j$.time.temporal.o, Comparable {
    long D();

    e E(j$.time.l lVar);

    n F();

    b I(j$.time.temporal.q qVar);

    /* JADX INFO: renamed from: L */
    int compareTo(b bVar);

    m a();

    @Override // j$.time.temporal.m
    b c(long j, j$.time.temporal.r rVar);

    @Override // j$.time.temporal.m
    b d(long j, j$.time.temporal.t tVar);

    @Override // j$.time.temporal.n
    boolean e(j$.time.temporal.r rVar);

    boolean equals(Object obj);

    int hashCode();

    String toString();

    b v(j$.time.temporal.o oVar);
}
