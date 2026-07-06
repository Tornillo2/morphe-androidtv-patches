package com.amazon.avod.mpb.media.playback.mediacodec;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.callback.PropertyChangedCallback;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.core.PropertyDefinition;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineBackendPropertyStore {

    @NotNull
    public final Map<String, PropertyImpl<?>> properties = new LinkedHashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Property<T> {

        @NotNull
        public static final Companion Companion = Companion.$$INSTANCE;

        /* JADX INFO: renamed from: com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore$Property$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            static {
                Companion companion = Property.Companion;
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public static <T> Property<T> of(@NotNull PropertyDefinition<T> propertyDefinition, @NotNull T t) {
                return Property.Companion.of(propertyDefinition, t);
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public static <T> Property<T> of(@NotNull PropertyDefinition<T> propertyDefinition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction) {
                return Property.Companion.of(propertyDefinition, t, propertyChangeAction);
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public static <T> Property<T> of(@NotNull PropertyDefinition<T> propertyDefinition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction, @Nullable PropertyChangedCallback propertyChangedCallback) {
                return Property.Companion.of(propertyDefinition, t, propertyChangeAction, propertyChangedCallback);
            }
        }

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public static final /* synthetic */ Companion $$INSTANCE = new Companion();

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Property of$default(Companion companion, PropertyDefinition propertyDefinition, Object obj, PropertyChangeAction propertyChangeAction, PropertyChangedCallback propertyChangedCallback, int i, Object obj2) {
                if ((i & 4) != 0) {
                    propertyChangeAction = null;
                }
                if ((i & 8) != 0) {
                    propertyChangedCallback = null;
                }
                return companion.of(propertyDefinition, obj, propertyChangeAction, propertyChangedCallback);
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public final <T> Property<T> of(@NotNull PropertyDefinition<T> definition, @NotNull T t) {
                Intrinsics.checkNotNullParameter(definition, "definition");
                Intrinsics.checkNotNullParameter(t, "default");
                return of$default(this, definition, t, null, null, 12, null);
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public final <T> Property<T> of(@NotNull PropertyDefinition<T> definition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction) {
                Intrinsics.checkNotNullParameter(definition, "definition");
                Intrinsics.checkNotNullParameter(t, "default");
                return of$default(this, definition, t, propertyChangeAction, null, 8, null);
            }

            @JvmStatic
            @JvmOverloads
            @NotNull
            public final <T> Property<T> of(@NotNull PropertyDefinition<T> definition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction, @Nullable PropertyChangedCallback propertyChangedCallback) {
                Intrinsics.checkNotNullParameter(definition, "definition");
                Intrinsics.checkNotNullParameter(t, "default");
                return new PropertyImpl(definition, t, propertyChangeAction, propertyChangedCallback);
            }
        }

        @NotNull
        T get();

        @NotNull
        String getAsString();

        @NotNull
        PropertyDefinition<T> getDefinition();

        void set(@Nullable T t);

        void setFromString(@Nullable String str);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface PropertyChangeAction<T> {
        void onChange(@NotNull T t);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nMediaPipelineBackendPropertyStore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MediaPipelineBackendPropertyStore.kt\ncom/amazon/avod/mpb/media/playback/mediacodec/MediaPipelineBackendPropertyStore$PropertyImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,174:1\n1#2:175\n*E\n"})
    public static final class PropertyImpl<T> implements Property<T> {

        /* JADX INFO: renamed from: default, reason: not valid java name */
        @NotNull
        public final T f0default;

        @NotNull
        public final PropertyDefinition<T> definition;

        @Nullable
        public final PropertyChangeAction<T> onChangeAction;

        @Nullable
        public final PropertyChangedCallback propertyChangedCallback;

        @Nullable
        public volatile T value;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public PropertyImpl(@NotNull PropertyDefinition<T> definition, @NotNull T t) {
            this(definition, t, null, null, 12, null);
            Intrinsics.checkNotNullParameter(definition, "definition");
            Intrinsics.checkNotNullParameter(t, "default");
        }

        @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.Property
        @NotNull
        public T get() {
            T t = this.value;
            return t == null ? this.f0default : t;
        }

        @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.Property
        @NotNull
        public String getAsString() {
            PropertyDefinition<T> propertyDefinition = this.definition;
            return propertyDefinition.serializer.toString(get());
        }

        @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.Property
        @NotNull
        public PropertyDefinition<T> getDefinition() {
            return this.definition;
        }

        @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.Property
        public void set(@Nullable T t) {
            T t2 = get();
            this.value = t;
            T t3 = get();
            if (Intrinsics.areEqual(t3, t2)) {
                return;
            }
            PropertyChangeAction<T> propertyChangeAction = this.onChangeAction;
            if (propertyChangeAction != null) {
                propertyChangeAction.onChange(t3);
            }
            if (this.definition.shouldNotifyOnChange) {
                PropertyChangedCallback propertyChangedCallback = this.propertyChangedCallback;
                Intrinsics.checkNotNull(propertyChangedCallback);
                propertyChangedCallback.onPropertyChanged(this.definition.key, getAsString());
            }
        }

        @Override // com.amazon.avod.mpb.media.playback.mediacodec.MediaPipelineBackendPropertyStore.Property
        public void setFromString(@Nullable String str) {
            set(str != null ? this.definition.fromString(str) : null);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public PropertyImpl(@NotNull PropertyDefinition<T> definition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction) {
            this(definition, t, propertyChangeAction, null, 8, null);
            Intrinsics.checkNotNullParameter(definition, "definition");
            Intrinsics.checkNotNullParameter(t, "default");
        }

        @JvmOverloads
        public PropertyImpl(@NotNull PropertyDefinition<T> definition, @NotNull T t, @Nullable PropertyChangeAction<T> propertyChangeAction, @Nullable PropertyChangedCallback propertyChangedCallback) {
            Intrinsics.checkNotNullParameter(definition, "definition");
            Intrinsics.checkNotNullParameter(t, "default");
            this.definition = definition;
            this.f0default = t;
            this.onChangeAction = propertyChangeAction;
            this.propertyChangedCallback = propertyChangedCallback;
            if (definition.shouldNotifyOnChange && propertyChangedCallback == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
        }

        public /* synthetic */ PropertyImpl(PropertyDefinition propertyDefinition, Object obj, PropertyChangeAction propertyChangeAction, PropertyChangedCallback propertyChangedCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(propertyDefinition, obj, (i & 4) != 0 ? null : propertyChangeAction, (i & 8) != 0 ? null : propertyChangedCallback);
        }
    }

    public final PropertyImpl<?> fromKey(String str) throws MediaPipelineBackendException {
        PropertyImpl<?> propertyImpl = this.properties.get(str);
        if (propertyImpl != null) {
            return propertyImpl;
        }
        throw new MediaPipelineBackendException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Property ", str, " is not supported"), MediaPipelineBackendResultCode.AV_MPB_KEY_NOT_FOUND);
    }

    @NotNull
    public final String getValue(@NotNull String key) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return fromKey(key).getAsString();
        } catch (Exception e) {
            throw new MediaPipelineBackendException("Unable to read value for property ".concat(key), MediaPipelineBackendResultCode.AV_MPB_VALUE_NOT_SUPPORTED, e);
        }
    }

    public final <T> void registerProperty(@NotNull Property<T> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        this.properties.put(property.getDefinition().key, (PropertyImpl) property);
    }

    public final void setValue(@NotNull String key, @Nullable String str) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(key, "key");
        PropertyImpl<?> propertyImplFromKey = fromKey(key);
        if (!propertyImplFromKey.definition.isMutable) {
            throw new MediaPipelineBackendException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Property ", propertyImplFromKey.definition.key, " is not mutable"), MediaPipelineBackendResultCode.AV_MPB_NOT_MUTABLE);
        }
        try {
            propertyImplFromKey.setFromString(str);
        } catch (Exception e) {
            MpbLog.exceptionf(e, "Incorrect value " + str + " for property " + propertyImplFromKey.definition.key, new Object[0]);
            throw new MediaPipelineBackendException("Incorrect value " + str + " for property " + propertyImplFromKey.definition.key, MediaPipelineBackendResultCode.AV_MPB_VALUE_NOT_SUPPORTED, e);
        }
    }
}
