package org.apache.commons.lang3.concurrent;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class MultiBackgroundInitializer extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    public final Map<String, BackgroundInitializer<?>> childInitializers;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MultiBackgroundInitializerResults {
        public final Map<String, ConcurrentException> exceptions;
        public final Map<String, BackgroundInitializer<?>> initializers;
        public final Map<String, Object> resultObjects;

        public final BackgroundInitializer<?> checkName(String str) {
            BackgroundInitializer<?> backgroundInitializer = this.initializers.get(str);
            if (backgroundInitializer != null) {
                return backgroundInitializer;
            }
            throw new NoSuchElementException(RemoteInput$$ExternalSyntheticOutline0.m("No child initializer with name ", str));
        }

        public ConcurrentException getException(String str) {
            checkName(str);
            return this.exceptions.get(str);
        }

        public BackgroundInitializer<?> getInitializer(String str) {
            return checkName(str);
        }

        public Object getResultObject(String str) {
            checkName(str);
            return this.resultObjects.get(str);
        }

        public Set<String> initializerNames() {
            return DesugarCollections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isException(String str) {
            checkName(str);
            return this.exceptions.containsKey(str);
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }

        public MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> map, Map<String, Object> map2, Map<String, ConcurrentException> map3) {
            this.initializers = map;
            this.resultObjects = map2;
            this.exceptions = map3;
        }
    }

    public MultiBackgroundInitializer() {
        super(null);
        this.childInitializers = new HashMap();
    }

    public void addInitializer(String str, BackgroundInitializer<?> backgroundInitializer) {
        Validate.isTrue(str != null, "Name of child initializer must not be null!", new Object[0]);
        Validate.isTrue(backgroundInitializer != null, "Child initializer must not be null!", new Object[0]);
        synchronized (this) {
            try {
                if (isStarted()) {
                    throw new IllegalStateException("addInitializer() must not be called after start()!");
                }
                this.childInitializers.put(str, backgroundInitializer);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public int getTaskCount() {
        Iterator<BackgroundInitializer<?>> it = this.childInitializers.values().iterator();
        int taskCount = 1;
        while (it.hasNext()) {
            taskCount += it.next().getTaskCount();
        }
        return taskCount;
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public MultiBackgroundInitializerResults initialize() throws Exception {
        HashMap map;
        synchronized (this) {
            map = new HashMap(this.childInitializers);
        }
        ExecutorService activeExecutor = getActiveExecutor();
        for (BackgroundInitializer backgroundInitializer : map.values()) {
            if (backgroundInitializer.getExternalExecutor() == null) {
                backgroundInitializer.setExternalExecutor(activeExecutor);
            }
            backgroundInitializer.start();
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            try {
                map2.put((String) entry.getKey(), ((BackgroundInitializer) entry.getValue()).get());
            } catch (ConcurrentException e) {
                map3.put((String) entry.getKey(), e);
            }
        }
        return new MultiBackgroundInitializerResults(map, map2, map3);
    }

    public MultiBackgroundInitializer(ExecutorService executorService) {
        super(executorService);
        this.childInitializers = new HashMap();
    }
}
