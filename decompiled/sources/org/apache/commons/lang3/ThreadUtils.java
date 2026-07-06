package org.apache.commons.lang3;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ThreadUtils {
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AlwaysTruePredicate implements ThreadPredicate, ThreadGroupPredicate {
        public AlwaysTruePredicate() {
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return true;
        }

        public AlwaysTruePredicate(AnonymousClass1 anonymousClass1) {
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class NamePredicate implements ThreadPredicate, ThreadGroupPredicate {
        public final String name;

        public NamePredicate(String str) {
            Validate.isTrue(str != null, "The name must not be null", new Object[0]);
            this.name = str;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface ThreadGroupPredicate {
        boolean test(ThreadGroup threadGroup);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ThreadIdPredicate implements ThreadPredicate {
        public final long threadId;

        public ThreadIdPredicate(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("The thread id must be greater than zero");
            }
            this.threadId = j;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @FunctionalInterface
    public interface ThreadPredicate {
        boolean test(Thread thread);
    }

    public static Thread findThreadById(long j, ThreadGroup threadGroup) {
        Validate.isTrue(threadGroup != null, "The thread group must not be null", new Object[0]);
        Thread threadFindThreadById = findThreadById(j);
        if (threadFindThreadById == null || !threadGroup.equals(threadFindThreadById.getThreadGroup())) {
            return null;
        }
        return threadFindThreadById;
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate threadGroupPredicate) {
        return findThreadGroups(getSystemThreadGroup(), true, threadGroupPredicate);
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String str) {
        return findThreadGroups(new NamePredicate(str));
    }

    public static Collection<Thread> findThreads(ThreadPredicate threadPredicate) {
        return findThreads(getSystemThreadGroup(), true, threadPredicate);
    }

    public static Collection<Thread> findThreadsByName(String str, ThreadGroup threadGroup) {
        return findThreads(threadGroup, false, new NamePredicate(str));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return findThreadGroups(ALWAYS_TRUE_PREDICATE);
    }

    public static Collection<Thread> getAllThreads() {
        return findThreads(ALWAYS_TRUE_PREDICATE);
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean z, ThreadGroupPredicate threadGroupPredicate) {
        ThreadGroup[] threadGroupArr;
        int iEnumerate;
        Validate.isTrue(threadGroup != null, "The group must not be null", new Object[0]);
        Validate.isTrue(threadGroupPredicate != null, "The predicate must not be null", new Object[0]);
        int iActiveGroupCount = threadGroup.activeGroupCount();
        while (true) {
            int i = (iActiveGroupCount / 2) + iActiveGroupCount + 1;
            threadGroupArr = new ThreadGroup[i];
            iEnumerate = threadGroup.enumerate(threadGroupArr, z);
            if (iEnumerate < i) {
                break;
            }
            iActiveGroupCount = iEnumerate;
        }
        ArrayList arrayList = new ArrayList(iEnumerate);
        for (int i2 = 0; i2 < iEnumerate; i2++) {
            if (threadGroupPredicate.test(threadGroupArr[i2])) {
                arrayList.add(threadGroupArr[i2]);
            }
        }
        return DesugarCollections.unmodifiableCollection(arrayList);
    }

    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean z, ThreadPredicate threadPredicate) {
        Thread[] threadArr;
        int iEnumerate;
        Validate.isTrue(threadGroup != null, "The group must not be null", new Object[0]);
        Validate.isTrue(threadPredicate != null, "The predicate must not be null", new Object[0]);
        int iActiveCount = threadGroup.activeCount();
        while (true) {
            int i = (iActiveCount / 2) + iActiveCount + 1;
            threadArr = new Thread[i];
            iEnumerate = threadGroup.enumerate(threadArr, z);
            if (iEnumerate < i) {
                break;
            }
            iActiveCount = iEnumerate;
        }
        ArrayList arrayList = new ArrayList(iEnumerate);
        for (int i2 = 0; i2 < iEnumerate; i2++) {
            if (threadPredicate.test(threadArr[i2])) {
                arrayList.add(threadArr[i2]);
            }
        }
        return DesugarCollections.unmodifiableCollection(arrayList);
    }

    public static Collection<Thread> findThreadsByName(String str, String str2) {
        Validate.isTrue(str != null, "The thread name must not be null", new Object[0]);
        Validate.isTrue(str2 != null, "The thread group name must not be null", new Object[0]);
        Collection<ThreadGroup> collectionFindThreadGroups = findThreadGroups(new NamePredicate(str2));
        if (collectionFindThreadGroups.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        NamePredicate namePredicate = new NamePredicate(str);
        Iterator<ThreadGroup> it = collectionFindThreadGroups.iterator();
        while (it.hasNext()) {
            arrayList.addAll(findThreads(it.next(), false, namePredicate));
        }
        return DesugarCollections.unmodifiableCollection(arrayList);
    }

    public static Thread findThreadById(long j, String str) {
        Validate.isTrue(str != null, "The thread group name must not be null", new Object[0]);
        Thread threadFindThreadById = findThreadById(j);
        if (threadFindThreadById == null || threadFindThreadById.getThreadGroup() == null || !threadFindThreadById.getThreadGroup().getName().equals(str)) {
            return null;
        }
        return threadFindThreadById;
    }

    public static Thread findThreadById(long j) {
        Collection<Thread> collectionFindThreads = findThreads(new ThreadIdPredicate(j));
        if (collectionFindThreads.isEmpty()) {
            return null;
        }
        return collectionFindThreads.iterator().next();
    }

    public static Collection<Thread> findThreadsByName(String str) {
        return findThreads(new NamePredicate(str));
    }
}
