package com.amazon.ignition.recommendation.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.startup.MetricGroup;
import com.amazon.ignitionshared.metrics.startup.MetricsRecorder;
import com.amazon.ignitionshared.recommendation.contentprovider.ContentProviderAuthorityKt;
import com.amazon.ignitionshared.recommendation.contentprovider.RequestStructureContentProviderConstants;
import com.amazon.livingroom.di.Names;
import com.amazon.primevideo.di.PrimeVideoApplicationComponent;
import com.amazon.reporting.Log;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRequestStructureContentProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestStructureContentProvider.kt\ncom/amazon/ignition/recommendation/contentprovider/RequestStructureContentProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,235:1\n1#2:236\n*E\n"})
public final class RequestStructureContentProvider extends ContentProvider {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int URI_TYPE_DIR = 1;

    @Inject
    public MetricsRecorder metricsRecorder;

    @Inject
    public SharedPreferences preferences;

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
        public static final String ERROR_MISSING_PARTNER_CREDENTIALS = "RequestStructureSharing.Read.Error.MissingPartnerCredentials";

        @NotNull
        public static final String ERROR_MISSING_REQUEST_STRUCTURE = "RequestStructureSharing.Read.Error.MissingRequestStructure";

        @NotNull
        public static final String ERROR_NULL_CONTEXT = "RequestStructureSharing.Read.Error.NullContext";

        @NotNull
        public static final String ERROR_PACKAGE_VERIFICATION = "RequestStructureSharing.Read.Error.PackageVerification";

        @NotNull
        public static final String ERROR_REGISTERED_PACKAGE_NOT_IN_CALLING_APP_PACKAGE_IDS = "RequestStructureSharing.Read.Error.RegisteredPackageIdNotInCallingAppPackageIds";

        @NotNull
        public static final String ERROR_REGISTERED_SIGNATURE_NOT_IN_CALLING_APP_SIGNATURES = "RequestStructureSharing.Read.Error.RegisteredSignatureNotInCallingAppSignatures";

        @NotNull
        public static final String ERROR_UNABLE_TO_RETRIEVE_CALLING_PACKAGE_IDS = "RequestStructureSharing.Read.Error.UnableToRetrieveCallingAppPackagedIds";

        @NotNull
        public static final String ERROR_URI_NOT_MATCHED = "RequestStructureSharing.Read.Error.UriNotMatched";

        @NotNull
        public static final MetricsEvent INSTANCE = new MetricsEvent();

        @NotNull
        public static final String PACKAGE_VERIFICATION_SUCCESS = "RequestStructureSharing.Read.PackageVerificationSuccess";

        @NotNull
        public static final String READ_SUCCESS = "RequestStructureSharing.Read.Success";
    }

    @Override // android.content.ContentProvider
    public int delete(@NotNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Log.e(RequestStructureContentProviderKt.TAG, "delete() - Not implemented and should not be called");
        return -1;
    }

    @NotNull
    public final MetricsRecorder getMetricsRecorder() {
        MetricsRecorder metricsRecorder = this.metricsRecorder;
        if (metricsRecorder != null) {
            return metricsRecorder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("metricsRecorder");
        throw null;
    }

    @NotNull
    public final SharedPreferences getPreferences() {
        SharedPreferences sharedPreferences = this.preferences;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("preferences");
        throw null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Log.d(RequestStructureContentProviderKt.TAG, "getType(): Uri=" + uri);
        Context context = getContext();
        if (context == null) {
            Log.e(RequestStructureContentProviderKt.TAG, "Context is null. Unable to get type for Uri");
            return null;
        }
        if (getUriMatcher(context).match(uri) == 1) {
            return MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("vnd.android.cursor.dir/vnd.", ContentProviderAuthorityKt.getContentProviderAuthority(context), ".recommendation_requests");
        }
        Log.e(RequestStructureContentProviderKt.TAG, "Unknown URI: " + uri);
        return null;
    }

    public final UriMatcher getUriMatcher(Context context) {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(ContentProviderAuthorityKt.getContentProviderAuthority(context), RequestStructureContentProviderConstants.TABLE_NAME, 1);
        return uriMatcher;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NotNull Uri uri, @Nullable ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Log.e(RequestStructureContentProviderKt.TAG, "insert() - Not implemented and should not be called");
        return null;
    }

    public final boolean isPackageAllowedToQueryProvider(Context context, int i, String str, String str2) {
        Set set;
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null || (set = ArraysKt___ArraysKt.toSet(packagesForUid)) == null) {
            Log.e(RequestStructureContentProviderKt.TAG, "Unable to retrieve package name that invoked this ContentProvider");
            recordCountMetric(MetricsEvent.ERROR_UNABLE_TO_RETRIEVE_CALLING_PACKAGE_IDS);
            return false;
        }
        Log.d(RequestStructureContentProviderKt.TAG, "Packages associated with the calling user: " + set);
        if (set.contains(context.getPackageName())) {
            recordCountMetric(MetricsEvent.PACKAGE_VERIFICATION_SUCCESS);
            return true;
        }
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            recordCountMetric(MetricsEvent.ERROR_MISSING_PARTNER_CREDENTIALS);
            Log.e(RequestStructureContentProviderKt.TAG, "Registered partner package \"" + str + "\" and registered partner signature \"" + str2 + "\" must not be null or empty");
            return false;
        }
        if (set.contains(str)) {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            if (RequestStructureContentProviderKt.getSHA256ApplicationSignatures(packageManager, str).contains(str2)) {
                recordCountMetric(MetricsEvent.PACKAGE_VERIFICATION_SUCCESS);
                return true;
            }
            recordCountMetric(MetricsEvent.ERROR_REGISTERED_SIGNATURE_NOT_IN_CALLING_APP_SIGNATURES);
            return false;
        }
        recordCountMetric(MetricsEvent.ERROR_REGISTERED_PACKAGE_NOT_IN_CALLING_APP_PACKAGE_IDS);
        Log.e(RequestStructureContentProviderKt.TAG, "Packages associated with calling uid \"" + set + "\" does not contain the registered package name \"" + str + ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
        return false;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        PrimeVideoApplicationComponent.Companion companion = PrimeVideoApplicationComponent.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        companion.init(context);
        companion.getInstance().inject(this);
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NotNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Log.d(RequestStructureContentProviderKt.TAG, "query() - Uri: " + uri);
        Context context = getContext();
        if (context == null) {
            Log.e(RequestStructureContentProviderKt.TAG, "Context is null. Unable to perform the query");
            recordCountMetric(MetricsEvent.ERROR_NULL_CONTEXT);
            return null;
        }
        if (!isPackageAllowedToQueryProvider(context, Binder.getCallingUid(), getPreferences().getString(RequestStructureContentProviderConstants.PARTNER_PACKAGE_KEY, null), getPreferences().getString(RequestStructureContentProviderConstants.PARTNER_SIGNATURE_KEY, null))) {
            Log.e(RequestStructureContentProviderKt.TAG, "Package not allowed to query this provider");
            recordCountMetric(MetricsEvent.ERROR_PACKAGE_VERIFICATION);
            return null;
        }
        String str3 = RequestStructureContentProviderKt.TAG;
        Log.d(str3, "Package is allowed to query provider");
        if (getUriMatcher(context).match(uri) != 1) {
            recordCountMetric(MetricsEvent.ERROR_URI_NOT_MATCHED);
            Log.e(str3, "URI not matched");
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{RequestStructureContentProviderConstants.REQUEST_STRUCTURE_KEY});
        String string = getPreferences().getString(RequestStructureContentProviderConstants.REQUEST_STRUCTURE_KEY, null);
        if (string == null) {
            Log.e(str3, "Request structure not available.");
            recordCountMetric(MetricsEvent.ERROR_MISSING_REQUEST_STRUCTURE);
            return matrixCursor;
        }
        recordCountMetric(MetricsEvent.READ_SUCCESS);
        matrixCursor.addRow(new String[]{string});
        return matrixCursor;
    }

    public final void recordCountMetric(String str) {
        MetricGroup metricGroupCreateMetricGroup = getMetricsRecorder().createMetricGroup(MinervaConstants.RECOMMENDATION_REQUEST_STRUCTURE_SHARING_SCHEMA_ID);
        MetricGroup.addCounterMetric$default(metricGroupCreateMetricGroup, str, 1, null, 4, null);
        getMetricsRecorder().recordMinerva(metricGroupCreateMetricGroup);
    }

    public final void setMetricsRecorder(@NotNull MetricsRecorder metricsRecorder) {
        Intrinsics.checkNotNullParameter(metricsRecorder, "<set-?>");
        this.metricsRecorder = metricsRecorder;
    }

    public final void setPreferences(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.preferences = sharedPreferences;
    }

    @Override // android.content.ContentProvider
    public int update(@NotNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Log.e(RequestStructureContentProviderKt.TAG, "update() - Not implemented and should not be called");
        return -1;
    }

    @Named(Names.RECOMMENDATION_REQUEST_STRUCTURE_CONTENT_PROVIDER)
    public static /* synthetic */ void getPreferences$annotations() {
    }
}
