package javax.el;

import java.lang.reflect.Constructor;
import java.util.Properties;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ExpressionFactory {
    public static ExpressionFactory newInstance() {
        return newInstance(null);
    }

    public abstract Object coerceToType(Object obj, Class<?> cls);

    public abstract MethodExpression createMethodExpression(ELContext eLContext, String str, Class<?> cls, Class<?>[] clsArr);

    public abstract ValueExpression createValueExpression(Object obj, Class<?> cls);

    public abstract ValueExpression createValueExpression(ELContext eLContext, String str, Class<?> cls);

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:23:0x0045
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static javax.el.ExpressionFactory newInstance(java.util.Properties r7) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.el.ExpressionFactory.newInstance(java.util.Properties):javax.el.ExpressionFactory");
    }

    public static ExpressionFactory newInstance(Properties properties, String str, ClassLoader classLoader) {
        Constructor<?> constructor;
        try {
            Class<?> clsLoadClass = classLoader.loadClass(str.trim());
            if (ExpressionFactory.class.isAssignableFrom(clsLoadClass)) {
                try {
                    if (properties != null) {
                        try {
                            constructor = clsLoadClass.getConstructor(Properties.class);
                        } catch (Exception unused) {
                            constructor = null;
                        }
                        if (constructor != null) {
                            return (ExpressionFactory) constructor.newInstance(properties);
                        }
                    }
                    return (ExpressionFactory) clsLoadClass.newInstance();
                } catch (Exception e) {
                    throw new ELException("Could not create expression factory instance", e);
                }
            }
            throw new ELException("Invalid expression factory class: ".concat(clsLoadClass.getName()));
        } catch (ClassNotFoundException e2) {
            throw new ELException("Could not find expression factory class", e2);
        }
    }
}
