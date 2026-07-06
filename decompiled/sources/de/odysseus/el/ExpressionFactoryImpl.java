package de.odysseus.el;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.tree.TreeBuilder;
import de.odysseus.el.tree.TreeStore;
import de.odysseus.el.tree.impl.Builder;
import de.odysseus.el.tree.impl.Cache;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.EnumSet;
import java.util.Properties;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import org.apache.commons.lang3.SystemUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ExpressionFactoryImpl extends ExpressionFactory {
    public static final String PROP_CACHE_SIZE = "javax.el.cacheSize";
    public static final String PROP_IGNORE_RETURN_TYPE = "javax.el.ignoreReturnType";
    public static final String PROP_METHOD_INVOCATIONS = "javax.el.methodInvocations";
    public static final String PROP_NULL_PROPERTIES = "javax.el.nullProperties";
    public static final String PROP_VAR_ARGS = "javax.el.varArgs";
    public final TypeConverter converter;
    public final TreeStore store;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Profile {
        JEE5(EnumSet.noneOf(Builder.Feature.class)),
        JEE6(EnumSet.of(Builder.Feature.METHOD_INVOCATIONS, Builder.Feature.VARARGS));

        public final EnumSet<Builder.Feature> features;

        Profile(EnumSet enumSet) {
            this.features = enumSet;
        }

        public boolean contains(Builder.Feature feature) {
            return this.features.contains(feature);
        }

        public Builder.Feature[] features() {
            EnumSet<Builder.Feature> enumSet = this.features;
            return (Builder.Feature[]) enumSet.toArray(new Builder.Feature[enumSet.size()]);
        }
    }

    public ExpressionFactoryImpl(Profile profile) {
        Properties propertiesLoadProperties = loadProperties("el.properties");
        this.store = createTreeStore(1000, profile, propertiesLoadProperties);
        this.converter = createTypeConverter(propertiesLoadProperties);
    }

    @Override // javax.el.ExpressionFactory
    public final Object coerceToType(Object obj, Class<?> cls) {
        return this.converter.convert(obj, cls);
    }

    @Override // javax.el.ExpressionFactory
    public /* bridge */ /* synthetic */ MethodExpression createMethodExpression(ELContext eLContext, String str, Class cls, Class[] clsArr) {
        return createMethodExpression(eLContext, str, (Class<?>) cls, (Class<?>[]) clsArr);
    }

    public TreeBuilder createTreeBuilder(Properties properties, Builder.Feature... featureArr) {
        Class<?> clsLoad = load(TreeBuilder.class, properties);
        if (clsLoad == null) {
            return new Builder(featureArr);
        }
        try {
            if (!Builder.class.isAssignableFrom(clsLoad)) {
                return (TreeBuilder) TreeBuilder.class.cast(clsLoad.newInstance());
            }
            Constructor<?> constructor = clsLoad.getConstructor(Builder.Feature[].class);
            if (constructor != null) {
                return (TreeBuilder) TreeBuilder.class.cast(constructor.newInstance(featureArr));
            }
            if (featureArr != null && featureArr.length != 0) {
                throw new ELException("Builder " + clsLoad + " is missing constructor (can't pass features)");
            }
            return (TreeBuilder) TreeBuilder.class.cast(clsLoad.newInstance());
        } catch (Exception e) {
            throw new ELException("TreeBuilder " + clsLoad + " could not be instantiated", e);
        }
    }

    public TreeStore createTreeStore(int i, Profile profile, Properties properties) {
        TreeBuilder treeBuilderCreateTreeBuilder;
        if (properties == null) {
            treeBuilderCreateTreeBuilder = createTreeBuilder(null, profile.features());
        } else {
            EnumSet enumSetNoneOf = EnumSet.noneOf(Builder.Feature.class);
            Builder.Feature feature = Builder.Feature.METHOD_INVOCATIONS;
            if (getFeatureProperty(profile, properties, feature, PROP_METHOD_INVOCATIONS)) {
                enumSetNoneOf.add(feature);
            }
            Builder.Feature feature2 = Builder.Feature.VARARGS;
            if (getFeatureProperty(profile, properties, feature2, PROP_VAR_ARGS)) {
                enumSetNoneOf.add(feature2);
            }
            Builder.Feature feature3 = Builder.Feature.NULL_PROPERTIES;
            if (getFeatureProperty(profile, properties, feature3, PROP_NULL_PROPERTIES)) {
                enumSetNoneOf.add(feature3);
            }
            Builder.Feature feature4 = Builder.Feature.IGNORE_RETURN_TYPE;
            if (getFeatureProperty(profile, properties, feature4, PROP_IGNORE_RETURN_TYPE)) {
                enumSetNoneOf.add(feature4);
            }
            treeBuilderCreateTreeBuilder = createTreeBuilder(properties, (Builder.Feature[]) enumSetNoneOf.toArray(new Builder.Feature[0]));
        }
        if (properties != null && properties.containsKey(PROP_CACHE_SIZE)) {
            try {
                i = Integer.parseInt(properties.getProperty(PROP_CACHE_SIZE));
            } catch (NumberFormatException e) {
                throw new ELException("Cannot parse EL property javax.el.cacheSize", e);
            }
        }
        return new TreeStore(treeBuilderCreateTreeBuilder, i > 0 ? new Cache(i) : null);
    }

    public TypeConverter createTypeConverter(Properties properties) {
        Class<?> clsLoad = load(TypeConverter.class, properties);
        if (clsLoad == null) {
            return TypeConverter.DEFAULT;
        }
        try {
            return (TypeConverter) TypeConverter.class.cast(clsLoad.newInstance());
        } catch (Exception e) {
            throw new ELException("TypeConverter " + clsLoad + " could not be instantiated", e);
        }
    }

    @Override // javax.el.ExpressionFactory
    public /* bridge */ /* synthetic */ ValueExpression createValueExpression(Object obj, Class cls) {
        return createValueExpression(obj, (Class<?>) cls);
    }

    public final boolean getFeatureProperty(Profile profile, Properties properties, Builder.Feature feature, String str) {
        return Boolean.valueOf(properties.getProperty(str, String.valueOf(profile.features.contains(feature)))).booleanValue();
    }

    public final Class<?> load(Class<?> cls, Properties properties) {
        String property;
        if (properties == null || (property = properties.getProperty(cls.getName())) == null) {
            return null;
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                return contextClassLoader == null ? Class.forName(property) : contextClassLoader.loadClass(property);
            } catch (ClassNotFoundException e) {
                throw new ELException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Class ", property, " not found"), e);
            } catch (Exception e2) {
                throw new ELException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Class ", property, " could not be instantiated"), e2);
            }
        } catch (Exception e3) {
            throw new ELException("Could not get context class loader", e3);
        }
    }

    public final Properties loadDefaultProperties() throws Throwable {
        Properties properties;
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(System.getProperty(SystemUtils.JAVA_HOME_KEY));
        String str = File.separator;
        sbM.append(str);
        sbM.append("lib");
        sbM.append(str);
        sbM.append("el.properties");
        File file = new File(sbM.toString());
        InputStream inputStream = null;
        try {
            if (file.exists()) {
                try {
                    properties = new Properties();
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream);
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                        }
                        if (getClass().getName().equals(properties.getProperty("javax.el.ExpressionFactory"))) {
                            return properties;
                        }
                    } catch (IOException e) {
                        e = e;
                        throw new ELException("Cannot read default EL properties", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            }
        } catch (SecurityException unused3) {
        }
        if (getClass().getName().equals(System.getProperty("javax.el.ExpressionFactory"))) {
            return System.getProperties();
        }
        return null;
    }

    public final Properties loadProperties(String str) {
        InputStream systemResourceAsStream;
        Properties properties = new Properties(loadDefaultProperties());
        try {
            systemResourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
        } catch (SecurityException unused) {
            systemResourceAsStream = ClassLoader.getSystemResourceAsStream(str);
        }
        try {
            if (systemResourceAsStream != null) {
                try {
                    properties.load(systemResourceAsStream);
                } catch (IOException e) {
                    throw new ELException("Cannot read EL properties", e);
                }
            }
            return properties;
        } finally {
            try {
                systemResourceAsStream.close();
            } catch (IOException unused2) {
            }
        }
    }

    @Override // javax.el.ExpressionFactory
    public final TreeMethodExpression createMethodExpression(ELContext eLContext, String str, Class<?> cls, Class<?>[] clsArr) {
        return new TreeMethodExpression(this.store, eLContext.getFunctionMapper(), eLContext.getVariableMapper(), this.converter, str, cls, clsArr);
    }

    @Override // javax.el.ExpressionFactory
    public /* bridge */ /* synthetic */ ValueExpression createValueExpression(ELContext eLContext, String str, Class cls) {
        return createValueExpression(eLContext, str, (Class<?>) cls);
    }

    @Override // javax.el.ExpressionFactory
    public final ObjectValueExpression createValueExpression(Object obj, Class<?> cls) {
        return new ObjectValueExpression(this.converter, obj, cls);
    }

    @Override // javax.el.ExpressionFactory
    public final TreeValueExpression createValueExpression(ELContext eLContext, String str, Class<?> cls) {
        return new TreeValueExpression(this.store, eLContext.getFunctionMapper(), eLContext.getVariableMapper(), this.converter, str, cls);
    }

    public ExpressionFactoryImpl(Profile profile, Properties properties) {
        this.store = createTreeStore(1000, profile, properties);
        this.converter = createTypeConverter(properties);
    }

    public ExpressionFactoryImpl(Profile profile, Properties properties, TypeConverter typeConverter) {
        this.store = createTreeStore(1000, profile, properties);
        this.converter = typeConverter;
    }

    public ExpressionFactoryImpl(TreeStore treeStore, TypeConverter typeConverter) {
        this.store = treeStore;
        this.converter = typeConverter;
    }

    public ExpressionFactoryImpl() {
        this(Profile.JEE6);
    }

    public ExpressionFactoryImpl(Properties properties) {
        this(Profile.JEE6, properties);
    }

    public ExpressionFactoryImpl(Properties properties, TypeConverter typeConverter) {
        this(Profile.JEE6, properties, typeConverter);
    }

    public ExpressionFactoryImpl(TreeStore treeStore) {
        this(treeStore, TypeConverter.DEFAULT);
    }
}
