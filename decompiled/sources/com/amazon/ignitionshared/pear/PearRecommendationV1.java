package com.amazon.ignitionshared.pear;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class PearRecommendationV1 {

    @NotNull
    public final Refresh refresh;

    @NotNull
    public final List<CollectionWidget> widgets;

    @NotNull
    public static final Companion Companion = new Companion();

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new PearRecommendationV1$$ExternalSyntheticLambda0())};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final KSerializer<PearRecommendationV1> serializer() {
            return PearRecommendationV1$$serializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ PearRecommendationV1(int i, Refresh refresh, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, PearRecommendationV1$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.refresh = refresh;
        this.widgets = list;
    }

    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(CollectionWidget$$serializer.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PearRecommendationV1 copy$default(PearRecommendationV1 pearRecommendationV1, Refresh refresh, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            refresh = pearRecommendationV1.refresh;
        }
        if ((i & 2) != 0) {
            list = pearRecommendationV1.widgets;
        }
        return pearRecommendationV1.copy(refresh, list);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$ignitionshared_release(PearRecommendationV1 pearRecommendationV1, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, Refresh$$serializer.INSTANCE, pearRecommendationV1.refresh);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), pearRecommendationV1.widgets);
    }

    @NotNull
    public final Refresh component1() {
        return this.refresh;
    }

    @NotNull
    public final List<CollectionWidget> component2() {
        return this.widgets;
    }

    @NotNull
    public final PearRecommendationV1 copy(@NotNull Refresh refresh, @NotNull List<CollectionWidget> widgets) {
        Intrinsics.checkNotNullParameter(refresh, "refresh");
        Intrinsics.checkNotNullParameter(widgets, "widgets");
        return new PearRecommendationV1(refresh, widgets);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PearRecommendationV1)) {
            return false;
        }
        PearRecommendationV1 pearRecommendationV1 = (PearRecommendationV1) obj;
        return Intrinsics.areEqual(this.refresh, pearRecommendationV1.refresh) && Intrinsics.areEqual(this.widgets, pearRecommendationV1.widgets);
    }

    @NotNull
    public final Refresh getRefresh() {
        return this.refresh;
    }

    @NotNull
    public final List<CollectionWidget> getWidgets() {
        return this.widgets;
    }

    public int hashCode() {
        return this.widgets.hashCode() + (this.refresh.refreshRate.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "PearRecommendationV1(refresh=" + this.refresh + ", widgets=" + this.widgets + ")";
    }

    public PearRecommendationV1(@NotNull Refresh refresh, @NotNull List<CollectionWidget> widgets) {
        Intrinsics.checkNotNullParameter(refresh, "refresh");
        Intrinsics.checkNotNullParameter(widgets, "widgets");
        this.refresh = refresh;
        this.widgets = widgets;
    }
}
