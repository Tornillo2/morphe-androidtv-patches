package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.LocalCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SubscriberRegistry {
    public static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache;
    public static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache;

    @Weak
    public final EventBus bus;
    public final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers = new ConcurrentHashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MethodIdentifier {
        public final String name;
        public final List<Class<?>> parameterTypes;

        public MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public boolean equals(Object o) {
            if (o instanceof MethodIdentifier) {
                MethodIdentifier methodIdentifier = (MethodIdentifier) o;
                if (this.name.equals(methodIdentifier.name) && this.parameterTypes.equals(methodIdentifier.parameterTypes)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.name, this.parameterTypes});
        }
    }

    static {
        CacheBuilder<Object, Object> cacheBuilderNewBuilder = CacheBuilder.newBuilder();
        LocalCache.Strength strength = LocalCache.Strength.WEAK;
        cacheBuilderNewBuilder.setKeyStrength(strength);
        subscriberMethodsCache = cacheBuilderNewBuilder.build(new CacheLoader.FunctionToCacheLoader(new SubscriberRegistry$$ExternalSyntheticLambda0()));
        CacheBuilder cacheBuilder = new CacheBuilder();
        cacheBuilder.setKeyStrength(strength);
        flattenHierarchyCache = cacheBuilder.build(new CacheLoader.FunctionToCacheLoader(new SubscriberRegistry$$ExternalSyntheticLambda1()));
    }

    public SubscriberRegistry(EventBus bus) {
        bus.getClass();
        this.bus = bus;
    }

    @VisibleForTesting
    public static ImmutableSet<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        return flattenHierarchyCache.getUnchecked(concreteClass);
    }

    public static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
        try {
            return subscriberMethodsCache.getUnchecked(clazz);
        } catch (UncheckedExecutionException e) {
            if (e.getCause() instanceof IllegalArgumentException) {
                throw new IllegalArgumentException(e.getCause().getMessage(), e.getCause());
            }
            throw e;
        }
    }

    public static ImmutableList<Method> getAnnotatedMethodsNotCached(Class<?> clazz) {
        Set setRawTypes = new TypeToken.TypeSet().rawTypes();
        HashMap map = new HashMap();
        Iterator it = setRawTypes.iterator();
        while (it.hasNext()) {
            for (Method method : ((Class) it.next()).getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Preconditions.checkArgument(parameterTypes.length == 1, "Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.", (Object) method, parameterTypes.length);
                    Preconditions.checkArgument(!parameterTypes[0].isPrimitive(), "@Subscribe method %s's parameter is %s. Subscriber methods cannot accept primitives. Consider changing the parameter to %s.", method, parameterTypes[0].getName(), Primitives.wrap(parameterTypes[0]).getSimpleName());
                    MethodIdentifier methodIdentifier = new MethodIdentifier(method);
                    if (!map.containsKey(methodIdentifier)) {
                        map.put(methodIdentifier, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(map.values());
    }

    public final Multimap<Class<?>, Subscriber> findAllSubscribers(Object listener) {
        HashMultimap hashMultimap = new HashMultimap();
        UnmodifiableIterator<Method> it = getAnnotatedMethods(listener.getClass()).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            hashMultimap.put(next.getParameterTypes()[0], Subscriber.create(this.bus, listener, next));
        }
        return hashMultimap;
    }

    public Iterator<Subscriber> getSubscribers(Object event) {
        ImmutableSet<Class<?>> immutableSetFlattenHierarchy = flattenHierarchy(event.getClass());
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(immutableSetFlattenHierarchy.size());
        UnmodifiableIterator<Class<?>> it = immutableSetFlattenHierarchy.iterator();
        while (it.hasNext()) {
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(it.next());
            if (copyOnWriteArraySet != null) {
                arrayListNewArrayListWithCapacity.add(copyOnWriteArraySet.iterator());
            }
        }
        return new Iterators.ConcatenatedIterator(arrayListNewArrayListWithCapacity.iterator());
    }

    @VisibleForTesting
    public Set<Subscriber> getSubscribersForTesting(Class<?> eventType) {
        return (Set) MoreObjects.firstNonNull(this.subscribers.get(eventType), ImmutableSet.of());
    }

    public void register(Object listener) {
        for (Map.Entry entry : ((HashMultimap) findAllSubscribers(listener)).asMap().entrySet()) {
            Class<?> cls = (Class) entry.getKey();
            Collection<? extends Subscriber> collection = (Collection) entry.getValue();
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(cls);
            if (copyOnWriteArraySet == null) {
                CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet2 = new CopyOnWriteArraySet<>();
                copyOnWriteArraySet = (CopyOnWriteArraySet) MoreObjects.firstNonNull(this.subscribers.putIfAbsent(cls, copyOnWriteArraySet2), copyOnWriteArraySet2);
            }
            copyOnWriteArraySet.addAll(collection);
        }
    }

    public void unregister(Object listener) {
        for (Map.Entry entry : ((HashMultimap) findAllSubscribers(listener)).asMap().entrySet()) {
            Class cls = (Class) entry.getKey();
            Collection<?> collection = (Collection) entry.getValue();
            CopyOnWriteArraySet<Subscriber> copyOnWriteArraySet = this.subscribers.get(cls);
            if (copyOnWriteArraySet == null || !copyOnWriteArraySet.removeAll(collection)) {
                throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + listener + " registered?");
            }
        }
    }
}
