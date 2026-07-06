package com.amazon.livingroom.deviceproperties.expression;

import androidx.annotation.Nullable;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.util.RootPropertyResolver;
import java.util.Properties;
import javax.el.ArrayELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.inject.Inject;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class ExpressionEvaluator {
    public final ExpressionFactory expressionFactory = new ExpressionFactoryImpl(buildExpressionFactoryConfig());
    public final FunctionMapper functionMapper = new FunctionMapperImpl();
    public final ELResolver resolver;

    @Inject
    public ExpressionEvaluator(DeviceProperties deviceProperties, DeviceProperties deviceProperties2) {
        this.resolver = buildResolver(deviceProperties, deviceProperties2);
    }

    public final Properties buildExpressionFactoryConfig() {
        Properties properties = new Properties();
        properties.setProperty(TypeConverter.class.getName(), TypeConverterImpl.class.getName());
        return properties;
    }

    public final ELResolver buildResolver(DeviceProperties deviceProperties, DeviceProperties deviceProperties2) {
        RootPropertyResolver rootPropertyResolver = new RootPropertyResolver(true);
        rootPropertyResolver.setProperty(StringLookupFactory.KEY_PROPERTIES, deviceProperties);
        rootPropertyResolver.setProperty("default", deviceProperties2);
        CompositeELResolver compositeELResolver = new CompositeELResolver();
        compositeELResolver.add(rootPropertyResolver);
        compositeELResolver.add(new DevicePropertiesResolver(deviceProperties));
        compositeELResolver.add(new DevicePropertiesResolver(deviceProperties2));
        compositeELResolver.add(new ArrayELResolver(true));
        compositeELResolver.add(new ListELResolver(true));
        compositeELResolver.add(new MapELResolver(true));
        compositeELResolver.add(new MethodPropertyResolver());
        return compositeELResolver;
    }

    @Nullable
    public <T> T evaluate(String str, Class<T> cls) {
        ELContextImpl eLContextImpl = new ELContextImpl(this.resolver, this.functionMapper);
        return (T) this.expressionFactory.createValueExpression(eLContextImpl, str, cls).getValue(eLContextImpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T castGeneric(Object obj) {
        return obj;
    }
}
