package com.google.common.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractInvocationHandler implements InvocationHandler {
    public static final Object[] NO_ARGS = new Object[0];

    public static boolean isProxyOfSameInterfaces(Object arg, Class<?> proxyClass) {
        if (proxyClass.isInstance(arg)) {
            return true;
        }
        return Proxy.isProxyClass(arg.getClass()) && Arrays.equals(arg.getClass().getInterfaces(), proxyClass.getInterfaces());
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable;

    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args == null) {
            args = NO_ARGS;
        }
        if (args.length == 0 && method.getName().equals("hashCode")) {
            return Integer.valueOf(hashCode());
        }
        if (args.length != 1 || !method.getName().equals("equals") || method.getParameterTypes()[0] != Object.class) {
            return (args.length == 0 && method.getName().equals("toString")) ? toString() : handleInvocation(proxy, method, args);
        }
        Object obj = args[0];
        if (obj == null) {
            return Boolean.FALSE;
        }
        if (proxy == obj) {
            return Boolean.TRUE;
        }
        return Boolean.valueOf(isProxyOfSameInterfaces(obj, proxy.getClass()) && equals(Proxy.getInvocationHandler(obj)));
    }

    public String toString() {
        return super.toString();
    }
}
