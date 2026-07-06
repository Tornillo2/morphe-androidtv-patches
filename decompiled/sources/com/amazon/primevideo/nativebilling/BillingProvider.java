package com.amazon.primevideo.nativebilling;

import android.os.Handler;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.IgnitionContext;
import com.amazon.ignitionshared.IgnitionContextProvider;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.primevideo.nativebilling.BillingClientProvider;
import com.amazon.primevideo.nativebilling.BillingConstants;
import com.amazon.primevideo.nativebilling.BillingProvider;
import com.amazon.primevideo.nativebilling.BillingUtils;
import com.amazon.primevideo.nativebilling.messages.AlternativeBillingMessageParameters;
import com.amazon.primevideo.nativebilling.messages.AlternativeBillingResponse;
import com.amazon.primevideo.nativebilling.messages.GetAlternativeBillingTokenResponse;
import com.amazon.primevideo.nativebilling.messages.StoreCountryQueryResponse;
import com.amazon.primevideo.nativebilling.messages.SvodProductMessageParameters;
import com.amazon.primevideo.nativebilling.messages.TvodPurchaseMessageParameters;
import com.amazon.reporting.Log;
import com.android.billingclient.api.AlternativeBillingOnlyAvailabilityListener;
import com.android.billingclient.api.AlternativeBillingOnlyInformationDialogListener;
import com.android.billingclient.api.AlternativeBillingOnlyReportingDetails;
import com.android.billingclient.api.AlternativeBillingOnlyReportingDetailsListener;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingConfig;
import com.android.billingclient.api.BillingConfigResponseListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.GetBillingConfigParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryProductDetailsResult;
import com.android.billingclient.api.UnfetchedProduct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonElementSerializer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nBillingProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BillingProvider.kt\ncom/amazon/primevideo/nativebilling/BillingProvider\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1282:1\n205#2:1283\n205#2:1288\n205#2:1289\n1563#3:1284\n1634#3,3:1285\n*S KotlinDebug\n*F\n+ 1 BillingProvider.kt\ncom/amazon/primevideo/nativebilling/BillingProvider\n*L\n1179#1:1283\n1192#1:1288\n1207#1:1289\n1195#1:1284\n1195#1:1285,3\n*E\n"})
public final class BillingProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String METRIC_SOURCE = "BillingProvider";
    public static final String TAG = "BillingProvider";
    public static final long TIMEOUT_IN_SECONDS = 10;

    @Nullable
    public BillingClientProvider.BillingClientHolder billingClientHolder;

    @NotNull
    public final BillingClientProvider billingClientProvider;

    @NotNull
    public BillingState billingState;

    @NotNull
    public final Condition condition;

    @NotNull
    public final IgnitionContextProvider contextProvider;
    public boolean fetchingCountry;

    @NotNull
    public final GMBMessageSender gmbMessageSender;

    @NotNull
    public final Handler handler;
    public boolean isAvailable;
    public boolean isDoneInitializing;

    @NotNull
    public final ReentrantLock lock;

    @NotNull
    public final MetricsRecorder metricsRecorder;

    @NotNull
    public final BillingConstants.Companion.MinervaMetricConstantsBuilder minervaMetricConstantsBuilder;

    @NotNull
    public final ProductDetailParamsFactory productDetailParamsFactory;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Serializable
    public static final class GmbPurchaseEventResponsePayload {

        @Nullable
        public final String error;

        @Nullable
        public final List<JsonElement> purchases;
        public final boolean success;

        @NotNull
        public static final Companion Companion = new Companion();

        @JvmField
        @NotNull
        public static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new BillingProvider$GmbPurchaseEventResponsePayload$$ExternalSyntheticLambda0()), null};

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final KSerializer<GmbPurchaseEventResponsePayload> serializer() {
                return BillingProvider$GmbPurchaseEventResponsePayload$$serializer.INSTANCE;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public /* synthetic */ GmbPurchaseEventResponsePayload(int i, boolean z, List list, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, BillingProvider$GmbPurchaseEventResponsePayload$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
            this.success = z;
            this.purchases = list;
            if ((i & 4) == 0) {
                this.error = null;
            } else {
                this.error = str;
            }
        }

        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(JsonElementSerializer.INSTANCE);
        }

        public static GmbPurchaseEventResponsePayload copy$default(GmbPurchaseEventResponsePayload gmbPurchaseEventResponsePayload, boolean z, List list, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = gmbPurchaseEventResponsePayload.success;
            }
            if ((i & 2) != 0) {
                list = gmbPurchaseEventResponsePayload.purchases;
            }
            if ((i & 4) != 0) {
                str = gmbPurchaseEventResponsePayload.error;
            }
            gmbPurchaseEventResponsePayload.getClass();
            return new GmbPurchaseEventResponsePayload(z, list, str);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(GmbPurchaseEventResponsePayload gmbPurchaseEventResponsePayload, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            compositeEncoder.encodeBooleanElement(serialDescriptor, 0, gmbPurchaseEventResponsePayload.success);
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, lazyArr[1].getValue(), gmbPurchaseEventResponsePayload.purchases);
            if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) && gmbPurchaseEventResponsePayload.error == null) {
                return;
            }
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, gmbPurchaseEventResponsePayload.error);
        }

        public final boolean component1() {
            return this.success;
        }

        @Nullable
        public final List<JsonElement> component2() {
            return this.purchases;
        }

        @Nullable
        public final String component3() {
            return this.error;
        }

        @NotNull
        public final GmbPurchaseEventResponsePayload copy(boolean z, @Nullable List<? extends JsonElement> list, @Nullable String str) {
            return new GmbPurchaseEventResponsePayload(z, list, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GmbPurchaseEventResponsePayload)) {
                return false;
            }
            GmbPurchaseEventResponsePayload gmbPurchaseEventResponsePayload = (GmbPurchaseEventResponsePayload) obj;
            return this.success == gmbPurchaseEventResponsePayload.success && Intrinsics.areEqual(this.purchases, gmbPurchaseEventResponsePayload.purchases) && Intrinsics.areEqual(this.error, gmbPurchaseEventResponsePayload.error);
        }

        @Nullable
        public final String getError() {
            return this.error;
        }

        @Nullable
        public final List<JsonElement> getPurchases() {
            return this.purchases;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public int hashCode() {
            int iM = MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.success) * 31;
            List<JsonElement> list = this.purchases;
            int iHashCode = (iM + (list == null ? 0 : list.hashCode())) * 31;
            String str = this.error;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            boolean z = this.success;
            List<JsonElement> list = this.purchases;
            String str = this.error;
            StringBuilder sb = new StringBuilder("GmbPurchaseEventResponsePayload(success=");
            sb.append(z);
            sb.append(", purchases=");
            sb.append(list);
            sb.append(", error=");
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, ")");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public GmbPurchaseEventResponsePayload(boolean z, @Nullable List<? extends JsonElement> list, @Nullable String str) {
            this.success = z;
            this.purchases = list;
            this.error = str;
        }

        public /* synthetic */ GmbPurchaseEventResponsePayload(boolean z, List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, list, (i & 4) != 0 ? null : str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Serializable
    public static final class GmbPurchaseRequestResponsePayload {

        @NotNull
        public static final Companion Companion = new Companion();

        @Nullable
        public final String debugMessage;

        @Nullable
        public final String error;

        @Nullable
        public final String requestId;

        @Nullable
        public final Integer responseCode;
        public final boolean success;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final KSerializer<GmbPurchaseRequestResponsePayload> serializer() {
                return BillingProvider$GmbPurchaseRequestResponsePayload$$serializer.INSTANCE;
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            }
        }

        public /* synthetic */ GmbPurchaseRequestResponsePayload(int i, String str, boolean z, String str2, Integer num, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, BillingProvider$GmbPurchaseRequestResponsePayload$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
            this.requestId = str;
            this.success = z;
            if ((i & 4) == 0) {
                this.error = null;
            } else {
                this.error = str2;
            }
            if ((i & 8) == 0) {
                this.responseCode = null;
            } else {
                this.responseCode = num;
            }
            if ((i & 16) == 0) {
                this.debugMessage = null;
            } else {
                this.debugMessage = str3;
            }
        }

        public static GmbPurchaseRequestResponsePayload copy$default(GmbPurchaseRequestResponsePayload gmbPurchaseRequestResponsePayload, String str, boolean z, String str2, Integer num, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = gmbPurchaseRequestResponsePayload.requestId;
            }
            if ((i & 2) != 0) {
                z = gmbPurchaseRequestResponsePayload.success;
            }
            if ((i & 4) != 0) {
                str2 = gmbPurchaseRequestResponsePayload.error;
            }
            if ((i & 8) != 0) {
                num = gmbPurchaseRequestResponsePayload.responseCode;
            }
            if ((i & 16) != 0) {
                str3 = gmbPurchaseRequestResponsePayload.debugMessage;
            }
            String str4 = str3;
            gmbPurchaseRequestResponsePayload.getClass();
            String str5 = str2;
            return new GmbPurchaseRequestResponsePayload(str, z, str5, num, str4);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$primevideo_armv7aRelease(GmbPurchaseRequestResponsePayload gmbPurchaseRequestResponsePayload, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, stringSerializer, gmbPurchaseRequestResponsePayload.requestId);
            compositeEncoder.encodeBooleanElement(serialDescriptor, 1, gmbPurchaseRequestResponsePayload.success);
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || gmbPurchaseRequestResponsePayload.error != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, gmbPurchaseRequestResponsePayload.error);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || gmbPurchaseRequestResponsePayload.responseCode != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, gmbPurchaseRequestResponsePayload.responseCode);
            }
            if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) && gmbPurchaseRequestResponsePayload.debugMessage == null) {
                return;
            }
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, stringSerializer, gmbPurchaseRequestResponsePayload.debugMessage);
        }

        @Nullable
        public final String component1() {
            return this.requestId;
        }

        public final boolean component2() {
            return this.success;
        }

        @Nullable
        public final String component3() {
            return this.error;
        }

        @Nullable
        public final Integer component4() {
            return this.responseCode;
        }

        @Nullable
        public final String component5() {
            return this.debugMessage;
        }

        @NotNull
        public final GmbPurchaseRequestResponsePayload copy(@Nullable String str, boolean z, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
            return new GmbPurchaseRequestResponsePayload(str, z, str2, num, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GmbPurchaseRequestResponsePayload)) {
                return false;
            }
            GmbPurchaseRequestResponsePayload gmbPurchaseRequestResponsePayload = (GmbPurchaseRequestResponsePayload) obj;
            return Intrinsics.areEqual(this.requestId, gmbPurchaseRequestResponsePayload.requestId) && this.success == gmbPurchaseRequestResponsePayload.success && Intrinsics.areEqual(this.error, gmbPurchaseRequestResponsePayload.error) && Intrinsics.areEqual(this.responseCode, gmbPurchaseRequestResponsePayload.responseCode) && Intrinsics.areEqual(this.debugMessage, gmbPurchaseRequestResponsePayload.debugMessage);
        }

        @Nullable
        public final String getDebugMessage() {
            return this.debugMessage;
        }

        @Nullable
        public final String getError() {
            return this.error;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        @Nullable
        public final Integer getResponseCode() {
            return this.responseCode;
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public int hashCode() {
            String str = this.requestId;
            int iM = (MediaPipelineBackendCapabilities$$ExternalSyntheticBackport0.m(this.success) + ((str == null ? 0 : str.hashCode()) * 31)) * 31;
            String str2 = this.error;
            int iHashCode = (iM + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num = this.responseCode;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            String str3 = this.debugMessage;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            String str = this.requestId;
            boolean z = this.success;
            String str2 = this.error;
            Integer num = this.responseCode;
            String str3 = this.debugMessage;
            StringBuilder sb = new StringBuilder("GmbPurchaseRequestResponsePayload(requestId=");
            sb.append(str);
            sb.append(", success=");
            sb.append(z);
            sb.append(", error=");
            sb.append(str2);
            sb.append(", responseCode=");
            sb.append(num);
            sb.append(", debugMessage=");
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, str3, ")");
        }

        public GmbPurchaseRequestResponsePayload(@Nullable String str, boolean z, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
            this.requestId = str;
            this.success = z;
            this.error = str2;
            this.responseCode = num;
            this.debugMessage = str3;
        }

        public /* synthetic */ GmbPurchaseRequestResponsePayload(String str, boolean z, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : str3);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class PurchaseType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ PurchaseType[] $VALUES;
        public static final PurchaseType ALTERNATIVE_BILLING = new PurchaseType("ALTERNATIVE_BILLING", 0);
        public static final PurchaseType GOOGLE_BILLING = new PurchaseType("GOOGLE_BILLING", 1);

        public static final /* synthetic */ PurchaseType[] $values() {
            return new PurchaseType[]{ALTERNATIVE_BILLING, GOOGLE_BILLING};
        }

        static {
            PurchaseType[] purchaseTypeArr$values = $values();
            $VALUES = purchaseTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(purchaseTypeArr$values);
        }

        public PurchaseType(String str, int i) {
        }

        @NotNull
        public static EnumEntries<PurchaseType> getEntries() {
            return $ENTRIES;
        }

        public static PurchaseType valueOf(String str) {
            return (PurchaseType) Enum.valueOf(PurchaseType.class, str);
        }

        public static PurchaseType[] values() {
            return (PurchaseType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PurchaseType.values().length];
            try {
                iArr[PurchaseType.ALTERNATIVE_BILLING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PurchaseType.GOOGLE_BILLING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[BillingState.values().length];
            try {
                iArr2[BillingState.PURCHASE_FLOW_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[BillingState.PENDING_VALIDATION_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[BillingState.SHOWN_ALTERNATIVE_BILLING_DIALOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.amazon.primevideo.nativebilling.BillingProvider$initializeGooglePlayBilling$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements BillingClientStateListener {
        public final /* synthetic */ Ref.ObjectRef<BillingProviderMetricRecorder> $initGPBMetricRecorder;
        public final /* synthetic */ Ref.BooleanRef $receivedCallback;
        public final /* synthetic */ int $tries;

        public AnonymousClass1(int i, Ref.ObjectRef<BillingProviderMetricRecorder> objectRef, Ref.BooleanRef booleanRef) {
            this.$tries = i;
            this.$initGPBMetricRecorder = objectRef;
            this.$receivedCallback = booleanRef;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit onBillingSetupFinished$lambda$2(BillingProvider billingProvider, Ref.ObjectRef objectRef, BillingResult billingResult) {
            ReentrantLock reentrantLock = billingProvider.lock;
            reentrantLock.lock();
            try {
                billingProvider.isAvailable = false;
                billingProvider.isDoneInitializing = true;
                billingProvider.condition.signalAll();
                reentrantLock.unlock();
                ((BillingProviderMetricRecorder) objectRef.element).record(false, BillingConstants.Companion.getMetricNameFromResponse(billingResult.zza));
                return Unit.INSTANCE;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit onBillingSetupFinished$lambda$3(Ref.ObjectRef objectRef, BillingProvider billingProvider, int i) {
            ((BillingProviderMetricRecorder) objectRef.element).addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
            BillingClientProvider.BillingClientHolder billingClientHolder = billingProvider.billingClientHolder;
            Intrinsics.checkNotNull(billingClientHolder);
            billingClientHolder.close();
            billingProvider.initializeGooglePlayBilling(i + 1, (BillingProviderMetricRecorder) objectRef.element);
            return Unit.INSTANCE;
        }

        @Override // com.android.billingclient.api.BillingClientStateListener
        public void onBillingServiceDisconnected() {
            BillingProvider billingProvider = BillingProvider.this;
            ReentrantLock reentrantLock = billingProvider.lock;
            reentrantLock.lock();
            try {
                billingProvider.isAvailable = false;
                billingProvider.isDoneInitializing = false;
            } finally {
                reentrantLock.unlock();
            }
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [T, com.amazon.primevideo.nativebilling.BillingProviderMetricRecorder] */
        @Override // com.android.billingclient.api.BillingClientStateListener
        public void onBillingSetupFinished(final BillingResult billingResult) {
            Intrinsics.checkNotNullParameter(billingResult, "billingResult");
            BillingProvider.this.printBillingResultDebugInformation("Client initialization finished", billingResult);
            BillingProvider billingProvider = BillingProvider.this;
            ReentrantLock reentrantLock = billingProvider.lock;
            Ref.BooleanRef booleanRef = this.$receivedCallback;
            Ref.ObjectRef<BillingProviderMetricRecorder> objectRef = this.$initGPBMetricRecorder;
            reentrantLock.lock();
            try {
                if (booleanRef.element) {
                    objectRef.element = new BillingProviderMetricRecorder(billingProvider.metricsRecorder, MinervaConstants.BILLING_PROVIDER_REINITIALIZATION_SCHEMA_ID, billingProvider.minervaMetricConstantsBuilder.reinitilizationEventName);
                } else {
                    booleanRef.element = true;
                }
                reentrantLock.unlock();
                BillingConstants.Companion companion = BillingConstants.Companion;
                companion.getClass();
                if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
                    BillingUtils.Companion companion2 = BillingUtils.Companion;
                    final int i = this.$tries;
                    final BillingProvider billingProvider2 = BillingProvider.this;
                    Handler handler = billingProvider2.handler;
                    final Ref.ObjectRef<BillingProviderMetricRecorder> objectRef2 = this.$initGPBMetricRecorder;
                    companion2.retryWithExponentialBackoff(i, handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$initializeGooglePlayBilling$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BillingProvider.AnonymousClass1.onBillingSetupFinished$lambda$2(billingProvider2, objectRef2, billingResult);
                        }
                    }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$initializeGooglePlayBilling$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BillingProvider.AnonymousClass1.onBillingSetupFinished$lambda$3(objectRef2, billingProvider2, i);
                        }
                    });
                    return;
                }
                int i2 = billingResult.zza;
                boolean z = i2 == 0;
                this.$initGPBMetricRecorder.element.record(z, companion.getMetricNameFromResponse(i2));
                BillingProvider billingProvider3 = BillingProvider.this;
                reentrantLock = billingProvider3.lock;
                reentrantLock.lock();
                try {
                    billingProvider3.isAvailable = z;
                    billingProvider3.isDoneInitializing = true;
                    billingProvider3.condition.signalAll();
                } finally {
                    reentrantLock.unlock();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Inject
    public BillingProvider(@NotNull GMBMessageSender gmbMessageSender, @NotNull MetricsRecorder metricsRecorder, @NotNull BillingClientProvider billingClientProvider, @NotNull ProductDetailParamsFactory productDetailParamsFactory, @NotNull Handler handler, @NotNull IgnitionContextProvider contextProvider) {
        Intrinsics.checkNotNullParameter(gmbMessageSender, "gmbMessageSender");
        Intrinsics.checkNotNullParameter(metricsRecorder, "metricsRecorder");
        Intrinsics.checkNotNullParameter(billingClientProvider, "billingClientProvider");
        Intrinsics.checkNotNullParameter(productDetailParamsFactory, "productDetailParamsFactory");
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(contextProvider, "contextProvider");
        this.gmbMessageSender = gmbMessageSender;
        this.metricsRecorder = metricsRecorder;
        this.billingClientProvider = billingClientProvider;
        this.productDetailParamsFactory = productDetailParamsFactory;
        this.handler = handler;
        this.contextProvider = contextProvider;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        Intrinsics.checkNotNullExpressionValue(conditionNewCondition, "newCondition(...)");
        this.condition = conditionNewCondition;
        this.billingState = BillingState.IDLE;
        this.minervaMetricConstantsBuilder = new BillingConstants.Companion.MinervaMetricConstantsBuilder(METRIC_SOURCE);
    }

    public static /* synthetic */ ProductDetails.SubscriptionOfferDetails findCorrectOffer$default(BillingProvider billingProvider, List list, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return billingProvider.findCorrectOffer(list, str, str2);
    }

    public static /* synthetic */ String generateGmbPurchaseRequestResponsePayload$default(BillingProvider billingProvider, String str, boolean z, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            num = null;
        }
        if ((i & 16) != 0) {
            str3 = null;
        }
        return billingProvider.generateGmbPurchaseRequestResponsePayload(str, z, str2, num, str3);
    }

    public static final void getAlternativeBillingStatusRetryable$lambda$35(final BillingProvider billingProvider, final int i, final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final BillingResult billingResult) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        billingProvider.printBillingResultDebugInformation("Alternative billing status finished", billingResult);
        BillingConstants.Companion.getClass();
        if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
            BillingUtils.Companion.retryWithExponentialBackoff(i, billingProvider.handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.getAlternativeBillingStatusRetryable$lambda$35$lambda$33(this.f$0, alternativeBillingMessageParameters, billingResult, billingProviderMetricRecorder);
                }
            }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.getAlternativeBillingStatusRetryable$lambda$35$lambda$34(billingProviderMetricRecorder, billingProvider, alternativeBillingMessageParameters, i);
                }
            });
            return;
        }
        String str = alternativeBillingMessageParameters.requestId;
        int i2 = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingStatusResponse$default(billingProvider, str, i2, str2, billingProviderMetricRecorder, null, null, 48, null);
    }

    public static final Unit getAlternativeBillingStatusRetryable$lambda$35$lambda$33(BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, BillingResult billingResult, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        String str = alternativeBillingMessageParameters.requestId;
        int i = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingStatusResponse$default(billingProvider, str, i, str2, billingProviderMetricRecorder, null, null, 48, null);
        return Unit.INSTANCE;
    }

    public static final Unit getAlternativeBillingStatusRetryable$lambda$35$lambda$34(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, int i) {
        billingProviderMetricRecorder.addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
        billingProvider.getAlternativeBillingStatusRetryable(alternativeBillingMessageParameters, billingProviderMetricRecorder, i + 1);
        return Unit.INSTANCE;
    }

    public static final void getAlternativeBillingTokenRetryable$lambda$31(final BillingProvider billingProvider, final int i, final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final BillingResult billingResult, AlternativeBillingOnlyReportingDetails alternativeBillingOnlyReportingDetails) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        billingProvider.printBillingResultDebugInformation("Get alternative billing token finished", billingResult);
        BillingConstants.Companion.getClass();
        if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
            BillingUtils.Companion.retryWithExponentialBackoff(i, billingProvider.handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.getAlternativeBillingTokenRetryable$lambda$31$lambda$29(this.f$0, alternativeBillingMessageParameters, billingResult, billingProviderMetricRecorder);
                }
            }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.getAlternativeBillingTokenRetryable$lambda$31$lambda$30(billingProviderMetricRecorder, billingProvider, alternativeBillingMessageParameters, i);
                }
            });
            return;
        }
        String str = alternativeBillingMessageParameters.requestId;
        int i2 = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingTokenResponse$default(billingProvider, str, i2, str2, billingProviderMetricRecorder, alternativeBillingOnlyReportingDetails != null ? alternativeBillingOnlyReportingDetails.externalTransactionToken : null, null, 32, null);
    }

    public static final Unit getAlternativeBillingTokenRetryable$lambda$31$lambda$29(BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, BillingResult billingResult, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        String str = alternativeBillingMessageParameters.requestId;
        int i = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingTokenResponse$default(billingProvider, str, i, str2, billingProviderMetricRecorder, null, BillingConstants.Companion.getMetricNameFromResponse(billingResult.zza), 16, null);
        return Unit.INSTANCE;
    }

    public static final Unit getAlternativeBillingTokenRetryable$lambda$31$lambda$30(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, int i) {
        billingProviderMetricRecorder.addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
        billingProvider.getAlternativeBillingTokenRetryable(alternativeBillingMessageParameters, billingProviderMetricRecorder, i + 1);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void handleAlternativeBillingDialogResponse$default(BillingProvider billingProvider, String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, Boolean bool, String str3, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            bool = Boolean.TRUE;
        }
        Boolean bool2 = bool;
        if ((i2 & 32) != 0) {
            str3 = null;
        }
        billingProvider.handleAlternativeBillingDialogResponse(str, i, str2, billingProviderMetricRecorder, bool2, str3);
    }

    public static /* synthetic */ void handleAlternativeBillingStatusResponse$default(BillingProvider billingProvider, String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, Boolean bool, String str3, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            bool = Boolean.TRUE;
        }
        Boolean bool2 = bool;
        if ((i2 & 32) != 0) {
            str3 = null;
        }
        billingProvider.handleAlternativeBillingStatusResponse(str, i, str2, billingProviderMetricRecorder, bool2, str3);
    }

    public static /* synthetic */ void handleAlternativeBillingTokenResponse$default(BillingProvider billingProvider, String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, String str3, String str4, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            str3 = null;
        }
        if ((i2 & 32) != 0) {
            str4 = null;
        }
        billingProvider.handleAlternativeBillingTokenResponse(str, i, str2, billingProviderMetricRecorder, str3, str4);
    }

    public static /* synthetic */ void handlePlayStoreCountryResponse$default(BillingProvider billingProvider, BillingProviderMetricRecorder billingProviderMetricRecorder, StoreCountryQueryResponse storeCountryQueryResponse, int i, String str, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str = null;
        }
        billingProvider.handlePlayStoreCountryResponse(billingProviderMetricRecorder, storeCountryQueryResponse, i, str);
    }

    public static final void provisionBillingClientHolder$lambda$0(BillingProvider billingProvider, BillingResult billingResult, List list) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        Log.d(TAG, "Purchase updated with billing result response code " + billingResult.zza + StringUtils.SPACE);
        GMBMessageSender.sendGMBMessageToClient$default(billingProvider.gmbMessageSender, BillingConstants.REPORT_PURCHASE_EVENT_MESSAGE_TYPE, billingProvider.generatePurchaseEventGmbPayload(billingResult, list), 0L, 4, null);
        BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(billingProvider.metricsRecorder, MinervaConstants.BILLING_PROVIDER_PURCHASE_UPDATED_SCHEMA_ID, billingProvider.minervaMetricConstantsBuilder.purchaseUpdatedEventName);
        billingProvider.releaseBillingClient();
        billingProvider.closeBillingClient();
        int i = billingResult.zza;
        billingProviderMetricRecorder.record(i == 0, BillingConstants.Companion.getMetricNameFromResponse(i));
    }

    public static final void queryCountryRetryable$lambda$24(final BillingProvider billingProvider, final int i, final BillingProviderMetricRecorder billingProviderMetricRecorder, final GetBillingConfigParams getBillingConfigParams, final BillingResult billingResult, BillingConfig billingConfig) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        billingProvider.printBillingResultDebugInformation("Query Play Store country finished", billingResult);
        BillingConstants.Companion.getClass();
        if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
            BillingUtils.Companion.retryWithExponentialBackoff(i, billingProvider.handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.queryCountryRetryable$lambda$24$lambda$22(this.f$0, billingProviderMetricRecorder, billingResult);
                }
            }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.queryCountryRetryable$lambda$24$lambda$23(billingProviderMetricRecorder, billingProvider, getBillingConfigParams, i);
                }
            });
        } else {
            int i2 = billingResult.zza;
            billingProvider.handlePlayStoreCountryResponse(billingProviderMetricRecorder, new StoreCountryQueryResponse(i2 == 0, billingConfig != null ? billingConfig.countryCode : null), i2, null);
        }
    }

    public static final Unit queryCountryRetryable$lambda$24$lambda$22(BillingProvider billingProvider, BillingProviderMetricRecorder billingProviderMetricRecorder, BillingResult billingResult) {
        handlePlayStoreCountryResponse$default(billingProvider, billingProviderMetricRecorder, new StoreCountryQueryResponse(false, null), billingResult.zza, null, 8, null);
        return Unit.INSTANCE;
    }

    public static final Unit queryCountryRetryable$lambda$24$lambda$23(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, GetBillingConfigParams getBillingConfigParams, int i) {
        billingProviderMetricRecorder.addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
        billingProvider.queryCountryRetryable(billingProviderMetricRecorder, getBillingConfigParams, i + 1);
        return Unit.INSTANCE;
    }

    public static final Unit queryPlayStoreCountry$lambda$20(BillingProvider billingProvider, BillingProviderMetricRecorder billingProviderMetricRecorder, int i, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        billingProvider.handlePlayStoreCountryResponse(billingProviderMetricRecorder, new StoreCountryQueryResponse(false, null), 5, billingProvider.minervaMetricConstantsBuilder.billingFailedInitMetric);
        Log.e(TAG, "Querying playStore country failed due initialization error : " + i);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void queryProduct$default(BillingProvider billingProvider, BillingProviderMetricRecorder billingProviderMetricRecorder, QueryProductDetailsParams queryProductDetailsParams, int i, Function2 function2, Function1 function1, String str, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            str = null;
        }
        billingProvider.queryProduct(billingProviderMetricRecorder, queryProductDetailsParams, i, function2, function1, str);
    }

    public static final void queryProduct$lambda$12(final BillingProvider billingProvider, final int i, final BillingProviderMetricRecorder billingProviderMetricRecorder, final Function2 function2, final Function1 function1, final QueryProductDetailsParams queryProductDetailsParams, final String str, final BillingResult billingResult, QueryProductDetailsResult queryProductDetailsResult) {
        Integer numValueOf = Integer.valueOf(BillingConstants.PRODUCT_NOT_FOUND);
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        Intrinsics.checkNotNullParameter(queryProductDetailsResult, "queryProductDetailsResult");
        billingProvider.printBillingResultDebugInformation("Query product finished", billingResult);
        BillingConstants.Companion companion = BillingConstants.Companion;
        companion.getClass();
        if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
            BillingUtils.Companion.retryWithExponentialBackoff(i, billingProvider.handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.queryProduct$lambda$12$lambda$10(billingProviderMetricRecorder, billingResult, function2);
                }
            }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.queryProduct$lambda$12$lambda$11(billingProviderMetricRecorder, billingProvider, queryProductDetailsParams, i, function2, function1, str);
                }
            });
            return;
        }
        int i2 = billingResult.zza;
        if (i2 != 0) {
            billingProviderMetricRecorder.record(false, companion.getMetricNameFromResponse(i2));
            Integer numValueOf2 = Integer.valueOf(billingResult.zza);
            String str2 = billingResult.zzc;
            Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
            function2.invoke(numValueOf2, str2);
            return;
        }
        List<ProductDetails> list = queryProductDetailsResult.productDetailsList;
        Intrinsics.checkNotNullExpressionValue(list, "getProductDetailsList(...)");
        List<UnfetchedProduct> list2 = queryProductDetailsResult.unfetchedProductList;
        Intrinsics.checkNotNullExpressionValue(list2, "getUnfetchedProductList(...)");
        if (!list2.isEmpty()) {
            Log.w(TAG, "Some products could not be fetched: " + list2.size());
            billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.noProductFoundMetric);
            function2.invoke(numValueOf, "Some products could not be fetched");
            return;
        }
        if (list.isEmpty()) {
            Log.d(TAG, "Product details is empty");
            billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.noProductFoundMetric);
            function2.invoke(numValueOf, "No products returned");
        } else {
            ProductDetails productDetails = list.get(0);
            Intrinsics.checkNotNull(productDetails);
            function1.invoke(productDetails);
        }
    }

    public static final Unit queryProduct$lambda$12$lambda$10(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingResult billingResult, Function2 function2) {
        billingProviderMetricRecorder.record(false, BillingConstants.Companion.getMetricNameFromResponse(billingResult.zza));
        Integer numValueOf = Integer.valueOf(billingResult.zza);
        String str = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str, "getDebugMessage(...)");
        function2.invoke(numValueOf, str);
        return Unit.INSTANCE;
    }

    public static final Unit queryProduct$lambda$12$lambda$11(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, QueryProductDetailsParams queryProductDetailsParams, int i, Function2 function2, Function1 function1, String str) {
        billingProviderMetricRecorder.addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
        billingProvider.queryProduct(billingProviderMetricRecorder, queryProductDetailsParams, i + 1, function2, function1, str);
        return Unit.INSTANCE;
    }

    public static final Unit setupPurchase$lambda$16(BillingProvider billingProvider, PurchaseType purchaseType, String str, BillingProviderMetricRecorder billingProviderMetricRecorder, int i, String debugMessage) {
        Intrinsics.checkNotNullParameter(debugMessage, "debugMessage");
        int i2 = WhenMappings.$EnumSwitchMapping$0[purchaseType.ordinal()];
        if (i2 == 1) {
            handleAlternativeBillingStatusResponse$default(billingProvider, str, 5, "Failed to initialize billing client", billingProviderMetricRecorder, null, billingProvider.minervaMetricConstantsBuilder.billingFailedInitMetric, 16, null);
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.billingFailedInitMetric);
            billingProvider.handleStartPurchaseError(str, i, debugMessage);
        }
        return Unit.INSTANCE;
    }

    public static final void showAlternativeBillingDialogRetryable$lambda$28(final BillingProvider billingProvider, final int i, final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final BillingResult billingResult) {
        Intrinsics.checkNotNullParameter(billingResult, "billingResult");
        billingProvider.printBillingResultDebugInformation("Show alternative billing dialog finished", billingResult);
        BillingConstants.Companion.getClass();
        if (BillingConstants.TRANSIENT_ERRORS.contains(Integer.valueOf(billingResult.zza))) {
            BillingUtils.Companion.retryWithExponentialBackoff(i, billingProvider.handler, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.showAlternativeBillingDialogRetryable$lambda$28$lambda$26(this.f$0, alternativeBillingMessageParameters, billingResult, billingProviderMetricRecorder);
                }
            }, new Function0() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BillingProvider.showAlternativeBillingDialogRetryable$lambda$28$lambda$27(billingProviderMetricRecorder, billingProvider, alternativeBillingMessageParameters, i);
                }
            });
            return;
        }
        String str = alternativeBillingMessageParameters.requestId;
        int i2 = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingDialogResponse$default(billingProvider, str, i2, str2, billingProviderMetricRecorder, null, null, 48, null);
    }

    public static final Unit showAlternativeBillingDialogRetryable$lambda$28$lambda$26(BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, BillingResult billingResult, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        String str = alternativeBillingMessageParameters.requestId;
        int i = billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        handleAlternativeBillingDialogResponse$default(billingProvider, str, i, str2, billingProviderMetricRecorder, null, BillingConstants.Companion.getMetricNameFromResponse(billingResult.zza), 16, null);
        return Unit.INSTANCE;
    }

    public static final Unit showAlternativeBillingDialogRetryable$lambda$28$lambda$27(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, AlternativeBillingMessageParameters alternativeBillingMessageParameters, int i) {
        billingProviderMetricRecorder.addCounterToMetricGroup(billingProvider.minervaMetricConstantsBuilder.retry);
        billingProvider.showAlternativeBillingDialogRetryable(alternativeBillingMessageParameters, billingProviderMetricRecorder, i + 1);
        return Unit.INSTANCE;
    }

    public static final Unit startPurchaseFlowWithDetails$lambda$13(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        Log.e(TAG, "Failed to reconnect billing client before launchBillingFlow");
        return Unit.INSTANCE;
    }

    public static final Unit startSvodPurchase$lambda$1(BillingProvider billingProvider, SvodProductMessageParameters svodProductMessageParameters, int i, String debugMessage) {
        Intrinsics.checkNotNullParameter(debugMessage, "debugMessage");
        billingProvider.handleStartPurchaseError(svodProductMessageParameters.requestId, i, debugMessage);
        return Unit.INSTANCE;
    }

    public static final Unit startSvodPurchase$lambda$5(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, SvodProductMessageParameters svodProductMessageParameters, ProductDetails product) {
        Intrinsics.checkNotNullParameter(product, "product");
        List<ProductDetails.SubscriptionOfferDetails> list = product.zzl;
        if (list == null || list.isEmpty()) {
            billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.noOffersMetric);
            billingProvider.handleStartPurchaseError(svodProductMessageParameters.requestId, BillingConstants.OFFER_NOT_FOUND, "No offers returned");
        } else {
            ProductDetails.SubscriptionOfferDetails subscriptionOfferDetailsFindCorrectOffer = billingProvider.findCorrectOffer(list, svodProductMessageParameters.basePlanId, svodProductMessageParameters.offerId);
            if (subscriptionOfferDetailsFindCorrectOffer == null) {
                Log.d(TAG, "No correct offer found");
                billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.noMatchingOfferMetric);
                billingProvider.handleStartPurchaseError(svodProductMessageParameters.requestId, BillingConstants.OFFER_NOT_FOUND, "No correct offer found");
            } else {
                Log.d(TAG, "Offer found");
                BillingProviderMetricRecorder.record$default(billingProviderMetricRecorder, true, null, 2, null);
                BillingResult billingResultStartPurchaseFlowWithDetails = billingProvider.startPurchaseFlowWithDetails(billingProvider.productDetailParamsFactory.makeProductDetailParams(product, subscriptionOfferDetailsFindCorrectOffer.zzc), svodProductMessageParameters.obfuscatedAccountId, new BillingProviderMetricRecorder(billingProvider.metricsRecorder, MinervaConstants.BILLING_PROVIDER_LAUNCH_PURCHASE_SCHEMA_ID, billingProvider.minervaMetricConstantsBuilder.launchPurchaseEventName));
                if (billingResultStartPurchaseFlowWithDetails != null) {
                    String str = svodProductMessageParameters.requestId;
                    int i = billingResultStartPurchaseFlowWithDetails.zza;
                    String str2 = billingResultStartPurchaseFlowWithDetails.zzc;
                    Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
                    billingProvider.handleStartPurchaseError(str, i, str2);
                } else {
                    GMBMessageSender.sendGMBMessageToClient$default(billingProvider.gmbMessageSender, BillingConstants.REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE, generateGmbPurchaseRequestResponsePayload$default(billingProvider, svodProductMessageParameters.requestId, true, null, null, null, 24, null), 0L, 4, null);
                }
            }
        }
        return Unit.INSTANCE;
    }

    public static final Unit startTvodPurchase$lambda$6(BillingProvider billingProvider, TvodPurchaseMessageParameters tvodPurchaseMessageParameters, int i, String debugMessage) {
        Intrinsics.checkNotNullParameter(debugMessage, "debugMessage");
        billingProvider.handleStartPurchaseError(tvodPurchaseMessageParameters.requestId, i, debugMessage);
        return Unit.INSTANCE;
    }

    public static final Unit startTvodPurchase$lambda$9(BillingProviderMetricRecorder billingProviderMetricRecorder, BillingProvider billingProvider, TvodPurchaseMessageParameters tvodPurchaseMessageParameters, ProductDetails product) {
        Intrinsics.checkNotNullParameter(product, "product");
        if (product.getOneTimePurchaseOfferDetails() == null) {
            billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.noMatchingOfferMetric);
            billingProvider.handleStartPurchaseError(tvodPurchaseMessageParameters.requestId, BillingConstants.OFFER_NOT_FOUND, "No offers returned");
            return Unit.INSTANCE;
        }
        BillingProviderMetricRecorder.record$default(billingProviderMetricRecorder, true, null, 2, null);
        BillingResult billingResultStartPurchaseFlowWithDetails = billingProvider.startPurchaseFlowWithDetails(billingProvider.productDetailParamsFactory.makeProductDetailParams(product, tvodPurchaseMessageParameters.offerIdToken), tvodPurchaseMessageParameters.obfuscatedAccountId, new BillingProviderMetricRecorder(billingProvider.metricsRecorder, MinervaConstants.BILLING_PROVIDER_LAUNCH_PURCHASE_SCHEMA_ID, billingProvider.minervaMetricConstantsBuilder.launchPurchaseEventName));
        if (billingResultStartPurchaseFlowWithDetails != null) {
            String str = tvodPurchaseMessageParameters.requestId;
            int i = billingResultStartPurchaseFlowWithDetails.zza;
            String str2 = billingResultStartPurchaseFlowWithDetails.zzc;
            Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
            billingProvider.handleStartPurchaseError(str, i, str2);
        } else {
            GMBMessageSender.sendGMBMessageToClient$default(billingProvider.gmbMessageSender, BillingConstants.REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE, generateGmbPurchaseRequestResponsePayload$default(billingProvider, tvodPurchaseMessageParameters.requestId, true, null, null, null, 24, null), 0L, 4, null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit verifyAndUpdateBillingClientState$lambda$17(BillingProvider billingProvider, String str, BillingProviderMetricRecorder billingProviderMetricRecorder, int i, String str2) {
        Intrinsics.checkNotNullParameter(str2, "<unused var>");
        handleAlternativeBillingTokenResponse$default(billingProvider, str, 5, "Billing failed to init", billingProviderMetricRecorder, null, billingProvider.minervaMetricConstantsBuilder.billingFailedInitMetric, 16, null);
        return Unit.INSTANCE;
    }

    public final void cancelCurrentTransaction() {
        releaseBillingClient();
        closeBillingClient();
    }

    public final void closeBillingClient() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            BillingState billingState = this.billingState;
            if (billingState != BillingState.IDLE || this.fetchingCountry) {
                Log.d(TAG, "Attempting to close the billing client while in state " + billingState + " and fetchingCountry is " + this.fetchingCountry);
            } else {
                Log.d(TAG, "Ending connection with billing client!");
                BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
                if (billingClientHolder != null) {
                    billingClientHolder.close();
                }
                this.billingClientHolder = null;
                this.isAvailable = false;
                this.isDoneInitializing = false;
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final boolean ensureNoPurchaseInProgress() {
        int i = WhenMappings.$EnumSwitchMapping$1[this.billingState.ordinal()];
        if (i == 1) {
            Log.e(TAG, "Purchase in progress, aborting purchase");
            return false;
        }
        if (i == 2 || i == 3) {
            Log.e(TAG, "Billing client in unexpected state: " + this.billingState + ", cleaning up and proceeding");
            this.billingState = BillingState.IDLE;
            closeBillingClient();
        } else {
            Log.i(TAG, "No purchase in progress");
        }
        return true;
    }

    public final ProductDetails.SubscriptionOfferDetails findCorrectOffer(List<ProductDetails.SubscriptionOfferDetails> list, String str, String str2) {
        Object next;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails = (ProductDetails.SubscriptionOfferDetails) next;
            if (Intrinsics.areEqual(subscriptionOfferDetails.zza, str) && Intrinsics.areEqual(subscriptionOfferDetails.zzb, str2)) {
                break;
            }
        }
        return (ProductDetails.SubscriptionOfferDetails) next;
    }

    public final String generateGmbPurchaseRequestResponsePayload(String str, boolean z, String str2, Integer num, String str3) {
        Json.Default r0 = Json.Default;
        GmbPurchaseRequestResponsePayload gmbPurchaseRequestResponsePayload = new GmbPurchaseRequestResponsePayload(str, z, str2, num, str3);
        r0.getClass();
        return r0.encodeToString(GmbPurchaseRequestResponsePayload.Companion.serializer(), gmbPurchaseRequestResponsePayload);
    }

    public final String generatePurchaseEventGmbPayload(BillingResult billingResult, List<? extends Purchase> list) {
        ArrayList arrayList;
        boolean z = billingResult.zza == 0;
        Json.Default r1 = Json.Default;
        if (list != null) {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (Purchase purchase : list) {
                Json.Default r5 = Json.Default;
                String originalJson = purchase.getOriginalJson();
                Intrinsics.checkNotNullExpressionValue(originalJson, "getOriginalJson(...)");
                arrayList.add(r5.parseToJsonElement(originalJson));
            }
        } else {
            arrayList = null;
        }
        GmbPurchaseEventResponsePayload gmbPurchaseEventResponsePayload = new GmbPurchaseEventResponsePayload(z, arrayList, z ? null : BillingConstants.Companion.getGmbErrorStringFromResponse(billingResult.zza));
        r1.getClass();
        return r1.encodeToString(GmbPurchaseEventResponsePayload.Companion.serializer(), gmbPurchaseEventResponsePayload);
    }

    public final String generateSerializationErrorGmbPayload(String str) {
        Json.Default r0 = Json.Default;
        GmbPurchaseRequestResponsePayload gmbPurchaseRequestResponsePayload = new GmbPurchaseRequestResponsePayload(str, false, BillingConstants.Companion.getGmbErrorStringFromResponse(BillingConstants.FAILED_TO_PARSE_PARAMETERS), 5, "Json parse of the input failed");
        r0.getClass();
        return r0.encodeToString(GmbPurchaseRequestResponsePayload.Companion.serializer(), gmbPurchaseRequestResponsePayload);
    }

    public final void getAlternativeBillingStatus(@NotNull String rawParameters) {
        Intrinsics.checkNotNullParameter(rawParameters, "rawParameters");
        BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_ALTERNATIVE_BILLING_STATUS_SCHEMA_ID, this.minervaMetricConstantsBuilder.getAlternativeBillingStatusEventName);
        try {
            AlternativeBillingMessageParameters alternativeBillingMessageParametersOf = AlternativeBillingMessageParameters.Companion.of(rawParameters);
            if (setupPurchase(alternativeBillingMessageParametersOf.requestId, billingProviderMetricRecorder, PurchaseType.ALTERNATIVE_BILLING)) {
                getAlternativeBillingStatusRetryable(alternativeBillingMessageParametersOf, billingProviderMetricRecorder, 1);
            } else {
                Log.e(TAG, "Failed setting up alternative billing purchase");
            }
        } catch (SerializationException unused) {
            billingProviderMetricRecorder.record(false, this.minervaMetricConstantsBuilder.deserializationFaultMetricName);
            handleSerializationError(BillingConstants.REPORT_ALTERNATIVE_BILLING_STATUS_MESSAGE_TYPE, rawParameters);
        }
    }

    public final void getAlternativeBillingStatusRetryable(final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final int i) {
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.billingClient.isAlternativeBillingOnlyAvailableAsync(new AlternativeBillingOnlyAvailabilityListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda0
            @Override // com.android.billingclient.api.AlternativeBillingOnlyAvailabilityListener
            public final void onAlternativeBillingOnlyAvailabilityResponse(BillingResult billingResult) {
                BillingProvider.getAlternativeBillingStatusRetryable$lambda$35(this.f$0, i, alternativeBillingMessageParameters, billingProviderMetricRecorder, billingResult);
            }
        });
    }

    public final void getAlternativeBillingToken(@NotNull String rawParameters) {
        BillingProvider billingProvider;
        Intrinsics.checkNotNullParameter(rawParameters, "rawParameters");
        BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_ALTERNATIVE_BILLING_TOKEN_SCHEMA_ID, this.minervaMetricConstantsBuilder.getAlternativeBillingTokenEventName);
        try {
            AlternativeBillingMessageParameters alternativeBillingMessageParametersOf = AlternativeBillingMessageParameters.Companion.of(rawParameters);
            if (verifyAndUpdateBillingClientState(alternativeBillingMessageParametersOf.requestId, billingProviderMetricRecorder, CollectionsKt__CollectionsKt.listOf((Object[]) new BillingState[]{BillingState.SHOWN_ALTERNATIVE_BILLING_DIALOG, BillingState.REQUESTED_ANOTHER_BILLING_TOKEN}), Intrinsics.areEqual(alternativeBillingMessageParametersOf.willRequestAnotherToken, Boolean.TRUE) ? BillingState.REQUESTED_ANOTHER_BILLING_TOKEN : BillingState.PURCHASE_FLOW_IN_PROGRESS)) {
                billingProvider = this;
                try {
                    getAlternativeBillingTokenRetryable(alternativeBillingMessageParametersOf, billingProviderMetricRecorder, 1);
                    return;
                } catch (SerializationException unused) {
                }
            } else {
                try {
                    handleAlternativeBillingTokenResponse$default(this, alternativeBillingMessageParametersOf.requestId, 5, "Billing client illegal state", billingProviderMetricRecorder, null, this.minervaMetricConstantsBuilder.billingClientIllegalState, 16, null);
                    return;
                } catch (SerializationException unused2) {
                    billingProvider = this;
                }
            }
        } catch (SerializationException unused3) {
            billingProvider = this;
        }
        billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.deserializationFaultMetricName);
        handleSerializationError(BillingConstants.REPORT_GET_ALTERNATIVE_TOKEN_MESSAGE_TYPE, rawParameters);
    }

    public final void getAlternativeBillingTokenRetryable(final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final int i) {
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.billingClient.createAlternativeBillingOnlyReportingDetailsAsync(new AlternativeBillingOnlyReportingDetailsListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda14
            @Override // com.android.billingclient.api.AlternativeBillingOnlyReportingDetailsListener
            public final void onAlternativeBillingOnlyTokenResponse(BillingResult billingResult, AlternativeBillingOnlyReportingDetails alternativeBillingOnlyReportingDetails) {
                BillingProvider.getAlternativeBillingTokenRetryable$lambda$31(this.f$0, i, alternativeBillingMessageParameters, billingProviderMetricRecorder, billingResult, alternativeBillingOnlyReportingDetails);
            }
        });
    }

    public final void handleAlternativeBillingDialogResponse(String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, Boolean bool, String str3) {
        Log.d(TAG, "Sending alternative billing dialog response to client " + i);
        boolean z = i == 0;
        if (str3 == null) {
            str3 = BillingConstants.Companion.getMetricNameFromResponse(i);
        }
        billingProviderMetricRecorder.record(z, str3);
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, BillingConstants.REPORT_SHOW_ALTERNATIVE_BILLING_DIALOG_MESSAGE_TYPE, new AlternativeBillingResponse(str, i, str2).toJsonString(), 0L, 4, null);
        if (z || !Intrinsics.areEqual(bool, Boolean.TRUE)) {
            return;
        }
        releaseBillingClient();
        closeBillingClient();
    }

    public final void handleAlternativeBillingStatusResponse(String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, Boolean bool, String str3) {
        Log.d(TAG, "Sending alternative billing status to client " + i);
        boolean z = i == 0;
        if (str3 == null) {
            str3 = BillingConstants.Companion.getMetricNameFromResponse(i);
        }
        billingProviderMetricRecorder.record(z, str3);
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, BillingConstants.REPORT_ALTERNATIVE_BILLING_STATUS_MESSAGE_TYPE, new AlternativeBillingResponse(str, i, str2).toJsonString(), 0L, 4, null);
        if (z || !Intrinsics.areEqual(bool, Boolean.TRUE)) {
            return;
        }
        releaseBillingClient();
        closeBillingClient();
    }

    public final void handleAlternativeBillingTokenResponse(String str, int i, String str2, BillingProviderMetricRecorder billingProviderMetricRecorder, String str3, String str4) {
        Log.d(TAG, "Sending alternative billing token response to client " + i);
        boolean z = i == 0;
        if (str4 == null) {
            str4 = BillingConstants.Companion.getMetricNameFromResponse(i);
        }
        billingProviderMetricRecorder.record(z, str4);
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, BillingConstants.REPORT_GET_ALTERNATIVE_TOKEN_MESSAGE_TYPE, new GetAlternativeBillingTokenResponse(str, i, str2, str3).toJsonString(), 0L, 4, null);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.billingState != BillingState.REQUESTED_ANOTHER_BILLING_TOKEN) {
                releaseBillingClient();
                closeBillingClient();
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void handlePlayStoreCountryResponse(BillingProviderMetricRecorder billingProviderMetricRecorder, StoreCountryQueryResponse storeCountryQueryResponse, int i, String str) {
        Log.d(TAG, "Sending store country to client " + storeCountryQueryResponse.storeCountry);
        boolean z = i == 0;
        if (str == null) {
            str = BillingConstants.Companion.getMetricNameFromResponse(i);
        }
        billingProviderMetricRecorder.record(z, str);
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, BillingConstants.REPORT_PLAY_STORE_COUNTRY_MESSAGE_TYPE, storeCountryQueryResponse.toJsonString(), 0L, 4, null);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.fetchingCountry = false;
            closeBillingClient();
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void handleSerializationError(String str, String str2) {
        String contentOrNull = null;
        try {
            JsonElement jsonElement = (JsonElement) JsonElementKt.getJsonObject(Json.Default.parseToJsonElement(str2)).get((Object) "requestId");
            if (jsonElement != null) {
                contentOrNull = JsonElementKt.getContentOrNull(JsonElementKt.getJsonPrimitive(jsonElement));
            }
        } catch (SerializationException unused) {
        }
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, str, generateSerializationErrorGmbPayload(contentOrNull), 0L, 4, null);
    }

    public final void handleStartPurchaseError(String str, int i, String str2) {
        String gmbErrorStringFromResponse = BillingConstants.Companion.getGmbErrorStringFromResponse(i);
        Log.e(TAG, "An error occurred while trying to launch a purchase: ".concat(gmbErrorStringFromResponse));
        if (i != 99905) {
            releaseBillingClient();
            closeBillingClient();
        }
        GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, BillingConstants.REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE, generateGmbPurchaseRequestResponsePayload(str, false, gmbErrorStringFromResponse, Integer.valueOf(i), str2), 0L, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initializeGooglePlayBilling(int i, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = billingProviderMetricRecorder;
        BillingClientProvider.BillingClientHolder billingClientHolderProvisionBillingClientHolder = provisionBillingClientHolder();
        this.billingClientHolder = billingClientHolderProvisionBillingClientHolder;
        billingClientHolderProvisionBillingClientHolder.billingClient.startConnection(new AnonymousClass1(i, objectRef, booleanRef));
    }

    public final void printBillingResultDebugInformation(String str, BillingResult billingResult) {
        String strM = str + " with response code: " + billingResult.zza;
        String str2 = billingResult.zzc;
        Intrinsics.checkNotNullExpressionValue(str2, "getDebugMessage(...)");
        if (str2.length() > 0) {
            strM = AbstractResolvableFuture$$ExternalSyntheticOutline1.m(strM, " and debug message: ", billingResult.zzc);
        }
        if (billingResult.zza == 0) {
            Log.d(TAG, strM);
        } else {
            Log.e(TAG, strM);
        }
    }

    public final BillingClientProvider.BillingClientHolder provisionBillingClientHolder() {
        return this.billingClientProvider.provideBillingClientHolder(new PurchasesUpdatedListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda19
            @Override // com.android.billingclient.api.PurchasesUpdatedListener
            public final void onPurchasesUpdated(BillingResult billingResult, List list) {
                BillingProvider.provisionBillingClientHolder$lambda$0(this.f$0, billingResult, list);
            }
        });
    }

    public final void queryCountryRetryable(final BillingProviderMetricRecorder billingProviderMetricRecorder, final GetBillingConfigParams getBillingConfigParams, final int i) {
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.billingClient.getBillingConfigAsync(getBillingConfigParams, new BillingConfigResponseListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda12
            @Override // com.android.billingclient.api.BillingConfigResponseListener
            public final void onBillingConfigResponse(BillingResult billingResult, BillingConfig billingConfig) {
                BillingProvider.queryCountryRetryable$lambda$24(this.f$0, i, billingProviderMetricRecorder, getBillingConfigParams, billingResult, billingConfig);
            }
        });
    }

    public final void queryPlayStoreCountry() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.fetchingCountry = true;
            reentrantLock.unlock();
            final BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_QUERY_COUNTRY_SCHEMA_ID, this.minervaMetricConstantsBuilder.queryPlayStoreCountryEventName);
            if (tryInitClientIfNecessaryAndWaitForInitialization(new Function2() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BillingProvider.queryPlayStoreCountry$lambda$20(this.f$0, billingProviderMetricRecorder, ((Integer) obj).intValue(), (String) obj2);
                }
            })) {
                queryCountryRetryable(billingProviderMetricRecorder, new GetBillingConfigParams(), 1);
                return;
            }
            this.lock.lock();
            try {
                this.fetchingCountry = false;
                closeBillingClient();
            } finally {
            }
        } finally {
        }
    }

    public final void queryProduct(final BillingProviderMetricRecorder billingProviderMetricRecorder, final QueryProductDetailsParams queryProductDetailsParams, final int i, final Function2<? super Integer, ? super String, Unit> function2, final Function1<? super ProductDetails, Unit> function1, final String str) {
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.billingClient.queryProductDetailsAsync(queryProductDetailsParams, new ProductDetailsResponseListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda9
            @Override // com.android.billingclient.api.ProductDetailsResponseListener
            public final void onProductDetailsResponse(BillingResult billingResult, QueryProductDetailsResult queryProductDetailsResult) {
                BillingProvider.queryProduct$lambda$12(this.f$0, i, billingProviderMetricRecorder, function2, function1, queryProductDetailsParams, str, billingResult, queryProductDetailsResult);
            }
        });
    }

    public final void registerStartPurchase() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Log.d(TAG, "Billing state changing to PURCHASE_FLOW_IN_PROGRESS");
            this.billingState = BillingState.PURCHASE_FLOW_IN_PROGRESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void releaseBillingClient() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Log.d(TAG, "Billing state changing to IDLE");
            this.billingState = BillingState.IDLE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean setupPurchase(final String str, final BillingProviderMetricRecorder billingProviderMetricRecorder, final PurchaseType purchaseType) throws Throwable {
        Throwable th;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!ensureNoPurchaseInProgress()) {
                int i = WhenMappings.$EnumSwitchMapping$0[purchaseType.ordinal()];
                if (i != 1) {
                    try {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        handleStartPurchaseError(str, BillingConstants.PURCHASE_ALREADY_IN_PROGRESS, "Purchase was attempted while another one had not finished");
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    try {
                        handleAlternativeBillingStatusResponse(str, 5, "Purchase already in progress", billingProviderMetricRecorder, Boolean.FALSE, this.minervaMetricConstantsBuilder.purchaseAlreadyInProgressMetric);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                reentrantLock.unlock();
                return false;
            }
            try {
                Log.d(TAG, "Billing state changing to PENDING_VALIDATION_REQUEST");
                this.billingState = BillingState.PENDING_VALIDATION_REQUEST;
                reentrantLock.unlock();
                if (tryInitClientIfNecessaryAndWaitForInitialization(new Function2() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BillingProvider.setupPurchase$lambda$16(this.f$0, purchaseType, str, billingProviderMetricRecorder, ((Integer) obj).intValue(), (String) obj2);
                    }
                })) {
                    return true;
                }
                releaseBillingClient();
                return false;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        th = th;
        reentrantLock.unlock();
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [boolean] */
    public final void showAlternativeBillingDialog(@NotNull String rawParameters) {
        BillingProvider billingProviderVerifyAndUpdateBillingClientState;
        Intrinsics.checkNotNullParameter(rawParameters, "rawParameters");
        BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_ALTERNATIVE_BILLING_DIALOG_SCHEMA_ID, this.minervaMetricConstantsBuilder.showAlternativeBillingDialogEventName);
        try {
            AlternativeBillingMessageParameters alternativeBillingMessageParametersOf = AlternativeBillingMessageParameters.Companion.of(rawParameters);
            billingProviderVerifyAndUpdateBillingClientState = verifyAndUpdateBillingClientState(alternativeBillingMessageParametersOf.requestId, billingProviderMetricRecorder, CollectionsKt__CollectionsJVMKt.listOf(BillingState.PENDING_VALIDATION_REQUEST), BillingState.SHOWN_ALTERNATIVE_BILLING_DIALOG);
            try {
                if (billingProviderVerifyAndUpdateBillingClientState == 0) {
                    handleAlternativeBillingTokenResponse$default(this, alternativeBillingMessageParametersOf.requestId, 5, "Billing client illegal state", billingProviderMetricRecorder, null, this.minervaMetricConstantsBuilder.billingClientIllegalState, 16, null);
                    return;
                } else {
                    showAlternativeBillingDialogRetryable(alternativeBillingMessageParametersOf, billingProviderMetricRecorder, 1);
                    return;
                }
            } catch (SerializationException unused) {
            }
        } catch (SerializationException unused2) {
            billingProviderVerifyAndUpdateBillingClientState = this;
        }
        billingProviderMetricRecorder.record(false, billingProviderVerifyAndUpdateBillingClientState.minervaMetricConstantsBuilder.deserializationFaultMetricName);
        handleSerializationError(BillingConstants.REPORT_SHOW_ALTERNATIVE_BILLING_DIALOG_MESSAGE_TYPE, rawParameters);
    }

    public final void showAlternativeBillingDialogRetryable(final AlternativeBillingMessageParameters alternativeBillingMessageParameters, final BillingProviderMetricRecorder billingProviderMetricRecorder, final int i) {
        IgnitionContext ignitionContext = this.contextProvider.context;
        if (ignitionContext == null) {
            Log.w(TAG, "Activity has been destroyed, skipping showing billing dialog.");
            return;
        }
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        billingClientHolder.billingClient.showAlternativeBillingOnlyInformationDialog(ignitionContext.activity, new AlternativeBillingOnlyInformationDialogListener() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda20
            @Override // com.android.billingclient.api.AlternativeBillingOnlyInformationDialogListener
            public final void onAlternativeBillingOnlyInformationDialogResponse(BillingResult billingResult) {
                BillingProvider.showAlternativeBillingDialogRetryable$lambda$28(this.f$0, i, alternativeBillingMessageParameters, billingProviderMetricRecorder, billingResult);
            }
        });
    }

    public final BillingResult startPurchaseFlowWithDetails(BillingFlowParams.ProductDetailsParams productDetailsParams, String str, BillingProviderMetricRecorder billingProviderMetricRecorder) {
        registerStartPurchase();
        List<BillingFlowParams.ProductDetailsParams> listListOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(productDetailsParams);
        BillingFlowParams.Builder builderNewBuilder = BillingFlowParams.newBuilder();
        builderNewBuilder.setProductDetailsParamsList(listListOfNotNull);
        builderNewBuilder.zza = str;
        BillingFlowParams billingFlowParamsBuild = builderNewBuilder.build();
        IgnitionContext ignitionContext = this.contextProvider.context;
        if (ignitionContext == null) {
            Log.w(TAG, "Activity has been destroyed, aborting launching billing flow.");
            return null;
        }
        BillingClientProvider.BillingClientHolder billingClientHolder = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder);
        if (!billingClientHolder.billingClient.isReady()) {
            Log.w(TAG, "BillingClient not ready before launchBillingFlow, attempting reconnection");
            if (!tryInitClientIfNecessaryAndWaitForInitialization(new BillingProvider$$ExternalSyntheticLambda23())) {
                billingProviderMetricRecorder.record(false, this.minervaMetricConstantsBuilder.billingFailedInitMetric);
                releaseBillingClient();
                BillingResult.Builder builderNewBuilder2 = BillingResult.newBuilder();
                builderNewBuilder2.zza = -1;
                builderNewBuilder2.zzc = "BillingClient not ready and reconnection failed";
                return builderNewBuilder2.build();
            }
        }
        BillingClientProvider.BillingClientHolder billingClientHolder2 = this.billingClientHolder;
        Intrinsics.checkNotNull(billingClientHolder2);
        BillingResult billingResultLaunchBillingFlow = billingClientHolder2.billingClient.launchBillingFlow(ignitionContext.activity, billingFlowParamsBuild);
        Intrinsics.checkNotNullExpressionValue(billingResultLaunchBillingFlow, "launchBillingFlow(...)");
        int i = billingResultLaunchBillingFlow.zza;
        billingProviderMetricRecorder.record(i == 0, BillingConstants.Companion.getMetricNameFromResponse(i));
        if (billingResultLaunchBillingFlow.zza == 0) {
            return null;
        }
        releaseBillingClient();
        return billingResultLaunchBillingFlow;
    }

    public final void startSvodPurchase(@NotNull String rawParameters) {
        BillingProvider billingProvider;
        Intrinsics.checkNotNullParameter(rawParameters, "rawParameters");
        final BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_QUERY_SUBSCRIPTION_SCHEMA_ID, this.minervaMetricConstantsBuilder.querySubscriptionEventName);
        try {
            final SvodProductMessageParameters svodProductMessageParametersOf = SvodProductMessageParameters.Companion.of(rawParameters);
            String str = TAG;
            Log.d(str, "Starting SVOD purchase for productId " + svodProductMessageParametersOf.productId);
            if (!setupPurchase(svodProductMessageParametersOf.requestId, billingProviderMetricRecorder, PurchaseType.GOOGLE_BILLING)) {
                Log.e(str, "Failed setting up SVOD purchase");
                return;
            }
            QueryProductDetailsParams.Product.Builder builder = new QueryProductDetailsParams.Product.Builder();
            builder.zza = svodProductMessageParametersOf.productId;
            builder.zzb = "subs";
            QueryProductDetailsParams.Builder builder2 = new QueryProductDetailsParams.Builder();
            builder2.setProductList(CollectionsKt__CollectionsJVMKt.listOf(builder.build()));
            QueryProductDetailsParams queryProductDetailsParamsBuild = builder2.build();
            billingProvider = this;
            try {
                billingProvider.queryProduct(billingProviderMetricRecorder, queryProductDetailsParamsBuild, 1, new Function2() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BillingProvider.startSvodPurchase$lambda$1(this.f$0, svodProductMessageParametersOf, ((Integer) obj).intValue(), (String) obj2);
                    }
                }, new Function1() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BillingProvider.startSvodPurchase$lambda$5(billingProviderMetricRecorder, this, svodProductMessageParametersOf, (ProductDetails) obj);
                    }
                }, MinervaConstants.BILLING_PROVIDER_QUERY_SUBSCRIPTION_GOOGLE_ERROR_SCHEMA_ID);
            } catch (SerializationException unused) {
                billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.deserializationFaultMetricName);
                handleSerializationError(BillingConstants.REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE, rawParameters);
            }
        } catch (SerializationException unused2) {
            billingProvider = this;
        }
    }

    public final void startTvodPurchase(@NotNull String rawParameters) {
        BillingProvider billingProvider;
        Intrinsics.checkNotNullParameter(rawParameters, "rawParameters");
        final BillingProviderMetricRecorder billingProviderMetricRecorder = new BillingProviderMetricRecorder(this.metricsRecorder, MinervaConstants.BILLING_PROVIDER_QUERY_TVOD_SCHEMA_ID, this.minervaMetricConstantsBuilder.queryTvodEventName);
        try {
            final TvodPurchaseMessageParameters tvodPurchaseMessageParametersOf = TvodPurchaseMessageParameters.Companion.of(rawParameters);
            String str = TAG;
            Log.d(str, "Starting TVOD purchase for productId " + tvodPurchaseMessageParametersOf.productId);
            if (!setupPurchase(tvodPurchaseMessageParametersOf.requestId, billingProviderMetricRecorder, PurchaseType.GOOGLE_BILLING)) {
                Log.e(str, "Failed setting up TVOD purchase");
                return;
            }
            QueryProductDetailsParams.Product.Builder builder = new QueryProductDetailsParams.Product.Builder();
            builder.zza = tvodPurchaseMessageParametersOf.productId;
            builder.zzb = "inapp";
            QueryProductDetailsParams.Builder builder2 = new QueryProductDetailsParams.Builder();
            builder2.setProductList(CollectionsKt__CollectionsJVMKt.listOf(builder.build()));
            QueryProductDetailsParams queryProductDetailsParamsBuild = builder2.build();
            billingProvider = this;
            try {
                billingProvider.queryProduct(billingProviderMetricRecorder, queryProductDetailsParamsBuild, 1, new Function2() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BillingProvider.startTvodPurchase$lambda$6(this.f$0, tvodPurchaseMessageParametersOf, ((Integer) obj).intValue(), (String) obj2);
                    }
                }, new Function1() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BillingProvider.startTvodPurchase$lambda$9(billingProviderMetricRecorder, this, tvodPurchaseMessageParametersOf, (ProductDetails) obj);
                    }
                }, MinervaConstants.BILLING_PROVIDER_QUERY_TVOD_GOOGLE_ERROR_SCHEMA_ID);
            } catch (SerializationException unused) {
                billingProviderMetricRecorder.record(false, billingProvider.minervaMetricConstantsBuilder.deserializationFaultMetricName);
                handleSerializationError(BillingConstants.REPORT_PURCHASE_LAUNCH_STATE_MESSAGE_TYPE, rawParameters);
            }
        } catch (SerializationException unused2) {
            billingProvider = this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean tryInitClientIfNecessaryAndWaitForInitialization(kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> r12) {
        /*
            r11 = this;
            java.lang.String r0 = "Failed to initialize client"
            r1 = 99903(0x1863f, float:1.39994E-40)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "Timeout waiting for client"
            com.amazon.primevideo.nativebilling.BillingClientProvider$BillingClientHolder r3 = r11.billingClientHolder
            r4 = 1
            if (r3 == 0) goto L29
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.android.billingclient.api.BillingClient r3 = r3.billingClient
            int r3 = r3.getConnectionState()
            r5 = 2
            if (r3 == r5) goto L82
            com.amazon.primevideo.nativebilling.BillingClientProvider$BillingClientHolder r3 = r11.billingClientHolder
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.android.billingclient.api.BillingClient r3 = r3.billingClient
            int r3 = r3.getConnectionState()
            if (r3 == r4) goto L82
        L29:
            com.amazon.primevideo.nativebilling.BillingProviderMetricRecorder r3 = new com.amazon.primevideo.nativebilling.BillingProviderMetricRecorder
            com.amazon.ignitionshared.metrics.startup.MetricsRecorder r5 = r11.metricsRecorder
            com.amazon.primevideo.nativebilling.BillingConstants$Companion$MinervaMetricConstantsBuilder r6 = r11.minervaMetricConstantsBuilder
            java.lang.String r6 = r6.initializationEventName
            java.lang.String r7 = "h6y1/2/02330410"
            r3.<init>(r5, r7, r6)
            r11.initializeGooglePlayBilling(r4, r3)
            r5 = 0
            java.util.concurrent.locks.ReentrantLock r6 = r11.lock     // Catch: java.lang.InterruptedException -> L62
            r6.lock()     // Catch: java.lang.InterruptedException -> L62
        L3f:
            boolean r7 = r11.isDoneInitializing     // Catch: java.lang.Throwable -> L64
            if (r7 != 0) goto L66
            java.util.concurrent.locks.Condition r7 = r11.condition     // Catch: java.lang.Throwable -> L64
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L64
            r9 = 10
            boolean r7 = r7.await(r9, r8)     // Catch: java.lang.Throwable -> L64
            if (r7 != 0) goto L3f
            java.lang.String r4 = com.amazon.primevideo.nativebilling.BillingProvider.TAG     // Catch: java.lang.Throwable -> L64
            com.amazon.reporting.Log.d(r4, r2)     // Catch: java.lang.Throwable -> L64
            r12.invoke(r1, r2)     // Catch: java.lang.Throwable -> L64
            com.amazon.primevideo.nativebilling.BillingConstants$Companion$MinervaMetricConstantsBuilder r2 = r11.minervaMetricConstantsBuilder     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = r2.initializationTimeoutMetric     // Catch: java.lang.Throwable -> L64
            r3.record(r5, r2)     // Catch: java.lang.Throwable -> L64
            r6.unlock()     // Catch: java.lang.InterruptedException -> L62
            return r5
        L62:
            r2 = move-exception
            goto L8e
        L64:
            r2 = move-exception
            goto L8a
        L66:
            boolean r2 = r11.isAvailable     // Catch: java.lang.Throwable -> L64
            if (r2 != 0) goto L7f
            java.lang.String r2 = com.amazon.primevideo.nativebilling.BillingProvider.TAG     // Catch: java.lang.Throwable -> L64
            com.amazon.reporting.Log.d(r2, r0)     // Catch: java.lang.Throwable -> L64
            r2 = 99904(0x18640, float:1.39995E-40)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L64
            java.lang.String r4 = "Billing client initialisation failed"
            r12.invoke(r2, r4)     // Catch: java.lang.Throwable -> L64
            r6.unlock()     // Catch: java.lang.InterruptedException -> L62
            return r5
        L7f:
            r6.unlock()     // Catch: java.lang.InterruptedException -> L62
        L82:
            java.lang.String r12 = com.amazon.primevideo.nativebilling.BillingProvider.TAG
            java.lang.String r0 = "Client initialised"
            com.amazon.reporting.Log.d(r12, r0)
            return r4
        L8a:
            r6.unlock()     // Catch: java.lang.InterruptedException -> L62
            throw r2     // Catch: java.lang.InterruptedException -> L62
        L8e:
            java.lang.String r4 = com.amazon.primevideo.nativebilling.BillingProvider.TAG
            com.amazon.reporting.Log.e(r4, r0, r2)
            java.lang.String r0 = "Operation timed out"
            r12.invoke(r1, r0)
            com.amazon.primevideo.nativebilling.BillingConstants$Companion$MinervaMetricConstantsBuilder r12 = r11.minervaMetricConstantsBuilder
            java.lang.String r12 = r12.initializationTimeoutMetric
            r3.record(r5, r12)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.primevideo.nativebilling.BillingProvider.tryInitClientIfNecessaryAndWaitForInitialization(kotlin.jvm.functions.Function2):boolean");
    }

    public final boolean verifyAndUpdateBillingClientState(final String str, final BillingProviderMetricRecorder billingProviderMetricRecorder, List<? extends BillingState> list, BillingState billingState) {
        if (!tryInitClientIfNecessaryAndWaitForInitialization(new Function2() { // from class: com.amazon.primevideo.nativebilling.BillingProvider$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return BillingProvider.verifyAndUpdateBillingClientState$lambda$17(this.f$0, str, billingProviderMetricRecorder, ((Integer) obj).intValue(), (String) obj2);
            }
        })) {
            releaseBillingClient();
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!list.contains(this.billingState)) {
                return false;
            }
            this.billingState = billingState;
            reentrantLock.unlock();
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }
}
