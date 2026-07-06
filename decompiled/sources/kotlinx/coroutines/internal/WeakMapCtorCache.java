package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class WeakMapCtorCache extends CtorCache {

    @NotNull
    public static final WeakMapCtorCache INSTANCE = new WeakMapCtorCache();

    @NotNull
    public static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();

    @NotNull
    public static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        lock.lock();
        try {
            Function1<Throwable, Throwable> function1 = exceptionCtors.get(cls);
            if (function1 != null) {
                return function1;
            }
            ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
            int i = 0;
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                lock2.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> weakHashMap = exceptionCtors;
                Function1<Throwable, Throwable> function12 = weakHashMap.get(cls);
                if (function12 != null) {
                    return function12;
                }
                Function1<Throwable, Throwable> function1CreateConstructor = ExceptionsConstructorKt.createConstructor(cls);
                weakHashMap.put(cls, function1CreateConstructor);
                while (i < readHoldCount) {
                    lock2.lock();
                    i++;
                }
                writeLock.unlock();
                return function1CreateConstructor;
            } finally {
                while (i < readHoldCount) {
                    lock2.lock();
                    i++;
                }
                writeLock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }
}
