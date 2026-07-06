package com.amazon.livingroom.deviceproperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.expression.ExpressionEvaluator;
import com.amazon.reporting.Log;
import java.util.Locale;
import java.util.Map;
import javax.el.ELException;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class OverridableDeviceProperties extends AbstractDeviceProperties {
    public static final String LOG_TAG = "OverridableDeviceProperties";
    public final AbstractDeviceProperties defaultProperties;
    public final Provider<ExpressionEvaluator> evaluator;
    public final OverridesProvider overridesProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OverridesProvider {
        @NonNull
        Map<String, String> getOverrides();
    }

    public OverridableDeviceProperties(AbstractDeviceProperties abstractDeviceProperties, OverridesProvider overridesProvider, Provider<ExpressionEvaluator> provider) {
        this.defaultProperties = abstractDeviceProperties;
        this.overridesProvider = overridesProvider;
        this.evaluator = provider;
    }

    @Override // com.amazon.livingroom.deviceproperties.AbstractDeviceProperties
    @NonNull
    public <T> T get(@NonNull DeviceProperties.Property<T> property, @NonNull DeviceProperties deviceProperties) {
        T t = (T) getOverride(property);
        return t == null ? (T) this.defaultProperties.get(property, deviceProperties) : t;
    }

    @Nullable
    public final <T> T getOverride(@NonNull DeviceProperties.Property<T> property) {
        String str = this.overridesProvider.getOverrides().get(property.getName());
        if (str == null) {
            return null;
        }
        try {
            return (T) this.evaluator.get().evaluate(str, property.getValueClass());
        } catch (ELException e) {
            Log.e(LOG_TAG, String.format(Locale.US, "Failed to evaluate property '%s' with expression '%s'", property.getName(), str), e);
            return null;
        }
    }
}
