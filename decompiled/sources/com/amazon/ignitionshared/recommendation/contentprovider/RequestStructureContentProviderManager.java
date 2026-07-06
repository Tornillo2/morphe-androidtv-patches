package com.amazon.ignitionshared.recommendation.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.ignitionshared.pear.PearUpdateStructure;
import com.amazon.ignitionshared.recommendation.BootstrapRequestStructureBuilder;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.Names;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRequestStructureContentProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestStructureContentProviderManager.kt\ncom/amazon/ignitionshared/recommendation/contentprovider/RequestStructureContentProviderManager\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,102:1\n29#2:103\n41#3,12:104\n*S KotlinDebug\n*F\n+ 1 RequestStructureContentProviderManager.kt\ncom/amazon/ignitionshared/recommendation/contentprovider/RequestStructureContentProviderManager\n*L\n38#1:103\n79#1:104,12\n*E\n"})
public final class RequestStructureContentProviderManager {

    @NotNull
    public static final Companion Companion = new Companion();

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(RequestStructureContentProviderManager.class)).getSimpleName();

    @NotNull
    public final BootstrapRequestStructureBuilder bootstrapRequestStructureBuilder;

    @NotNull
    public final Context context;

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final PearUpdateStructure pearUpdateStructure;

    @NotNull
    public final SharedPreferences requestStructurePreference;

    @NotNull
    public final Uri requestStructureTableUri;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class MetricsEvent {

        @NotNull
        public static final String ERROR_MISSING_PARTNER_CREDENTIALS = "RequestStructureSharing.Write.Error.MissingPartnerCredentials";

        @NotNull
        public static final MetricsEvent INSTANCE = new MetricsEvent();

        @NotNull
        public static final String WRITE_SUCCESS = "RequestStructureSharing.Write.Success";
    }

    @Inject
    public RequestStructureContentProviderManager(@ApplicationContext @NotNull Context context, @Named(Names.RECOMMENDATION_REQUEST_STRUCTURE_CONTENT_PROVIDER) @NotNull SharedPreferences requestStructurePreference, @NotNull DeviceProperties deviceProperties, @NotNull PearUpdateStructure pearUpdateStructure, @NotNull BootstrapRequestStructureBuilder bootstrapRequestStructureBuilder, @NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestStructurePreference, "requestStructurePreference");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(pearUpdateStructure, "pearUpdateStructure");
        Intrinsics.checkNotNullParameter(bootstrapRequestStructureBuilder, "bootstrapRequestStructureBuilder");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.context = context;
        this.requestStructurePreference = requestStructurePreference;
        this.deviceProperties = deviceProperties;
        this.pearUpdateStructure = pearUpdateStructure;
        this.bootstrapRequestStructureBuilder = bootstrapRequestStructureBuilder;
        this.minervaMetrics = minervaMetrics;
        this.requestStructureTableUri = Uri.parse("content://" + ContentProviderAuthorityKt.getContentProviderAuthority(context) + "/recommendation_requests");
    }

    public final void clearRequestStructure() {
        SharedPreferences.Editor editorEdit = this.requestStructurePreference.edit();
        editorEdit.clear();
        editorEdit.apply();
        notifyChangeInContentProviderData();
    }

    public final void notifyChangeInContentProviderData() {
        try {
            this.context.getContentResolver().notifyChange(this.requestStructureTableUri, null);
        } catch (Exception e) {
            Log.e(TAG, "Unable to notify change in ContentProvider data", e);
        }
    }

    public final void recordMetric(String str) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.RECOMMENDATION_REQUEST_STRUCTURE_SHARING_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(str, 1L);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    public final void updateRequestStructure() {
        if (!((Boolean) this.deviceProperties.get(DeviceProperties.RECOMMENDATION_REQUEST_STRUCTURE_SHARING)).booleanValue()) {
            Log.w(TAG, "Sharing RequestStructure is disabled");
            clearRequestStructure();
            return;
        }
        String rawStoredUpdateMessage = this.pearUpdateStructure.getRawStoredUpdateMessage();
        if (rawStoredUpdateMessage != null) {
            writeRequestStructure(rawStoredUpdateMessage);
        } else {
            writeRequestStructure(this.bootstrapRequestStructureBuilder.buildRequestStructureForBootstrap());
        }
    }

    public final void writeRequestStructure(String str) {
        Object obj = this.deviceProperties.get(DeviceProperties.RECOMMENDATIONS_PARTNER_PACKAGE);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        String str2 = (String) obj;
        Object obj2 = this.deviceProperties.get(DeviceProperties.RECOMMENDATIONS_PARTNER_SIGNATURE);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        String str3 = (String) obj2;
        if (str2.length() == 0 || str3.length() == 0) {
            Log.e(TAG, "partner package name and signature must be present. Received: PartnerPackage=" + str2 + ", PartnerSignature=" + str3);
            clearRequestStructure();
            recordMetric(MetricsEvent.ERROR_MISSING_PARTNER_CREDENTIALS);
            return;
        }
        SharedPreferences.Editor editorEdit = this.requestStructurePreference.edit();
        editorEdit.putString(RequestStructureContentProviderConstants.REQUEST_STRUCTURE_KEY, str);
        editorEdit.putString(RequestStructureContentProviderConstants.PARTNER_PACKAGE_KEY, str2);
        editorEdit.putString(RequestStructureContentProviderConstants.PARTNER_SIGNATURE_KEY, str3);
        editorEdit.apply();
        Log.d(TAG, "Successfully written request structure to the provider data store");
        notifyChangeInContentProviderData();
        recordMetric(MetricsEvent.WRITE_SUCCESS);
    }
}
