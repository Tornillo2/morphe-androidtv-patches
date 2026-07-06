package com.google.common.hash;

import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LongAddables {
    public static final Supplier<LongAddable> SUPPLIER;

    /* JADX INFO: renamed from: com.google.common.hash.LongAddables$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Supplier<LongAddable> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public LongAddable get() {
            return new LongAdder();
        }
    }

    /* JADX INFO: renamed from: com.google.common.hash.LongAddables$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements Supplier<LongAddable> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public LongAddable get() {
            return new PureJavaLongAddable();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PureJavaLongAddable extends AtomicLong implements LongAddable {
        public PureJavaLongAddable() {
        }

        @Override // com.google.common.hash.LongAddable
        public void add(long x) {
            getAndAdd(x);
        }

        @Override // com.google.common.hash.LongAddable
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.hash.LongAddable
        public long sum() {
            return get();
        }

        public PureJavaLongAddable(AnonymousClass1 anonymousClass1) {
        }
    }

    static {
        Supplier<LongAddable> anonymousClass2;
        try {
            new LongAdder();
            anonymousClass2 = new AnonymousClass1();
        } catch (Throwable unused) {
            anonymousClass2 = new AnonymousClass2();
        }
        SUPPLIER = anonymousClass2;
    }

    public static LongAddable create() {
        return SUPPLIER.get();
    }
}
