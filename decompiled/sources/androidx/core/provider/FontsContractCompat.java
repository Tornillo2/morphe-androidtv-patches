package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompat$$ExternalSyntheticBackport2;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.provider.RequestExecutor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FontsContractCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final String PARCEL_FONT_RESULTS = "font_results";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        public final List<FontInfo[]> mFonts;
        public final int mStatusCode;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = Collections.singletonList(fontInfoArr);
        }

        public static FontFamilyResult create(int i, @Nullable FontInfo[] fontInfoArr) {
            return new FontFamilyResult(i, fontInfoArr);
        }

        public FontInfo[] getFonts() {
            return this.mFonts.get(0);
        }

        @NonNull
        public List<FontInfo[]> getFontsWithFallbacks() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public boolean hasFallback() {
            return this.mFonts.size() > 1;
        }

        public static FontFamilyResult create(int i, @Nullable List<FontInfo[]> list) {
            return new FontFamilyResult(i, list);
        }

        public FontFamilyResult(int i, @NonNull List<FontInfo[]> list) {
            this.mStatusCode = i;
            this.mFonts = list;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FontInfo {
        public final boolean mItalic;
        public final int mResultCode;
        public final int mTtcIndex;
        public final Uri mUri;
        public final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            uri.getClass();
            this.mUri = uri;
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        public static FontInfo create(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            return new FontInfo(uri, i, i2, z, i3);
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        return FontProvider.getFontFamilyResult(context, TypefaceCompat$$ExternalSyntheticBackport2.m(new Object[]{fontRequest}), cancellationSignal);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static Typeface getFontSync(Context context, FontRequest fontRequest, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z, int i, int i2) {
        TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter = new TypefaceCompat.ResourcesCallbackAdapter(fontCallback);
        return requestFont(context, (List<FontRequest>) TypefaceCompat$$ExternalSyntheticBackport2.m(new Object[]{fontRequest}), i2, z, i, ResourcesCompat.FontCallback.getHandler(handler), resourcesCallbackAdapter);
    }

    @VisibleForTesting
    @Deprecated
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        return FontProvider.getProvider(packageManager, fontRequest, resources);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        return TypefaceCompatUtil.readFontInfoIntoByteBuffer(context, fontInfoArr, cancellationSignal);
    }

    @Deprecated
    public static void requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        CallbackWrapper callbackWrapper = new CallbackWrapper(fontRequestCallback);
        FontRequestWorker.requestFontAsync(context.getApplicationContext(), TypefaceCompat$$ExternalSyntheticBackport2.m(new Object[]{fontRequest}), 0, new RequestExecutor.HandlerExecutor(handler), callbackWrapper);
    }

    public static void requestFontWithFallbackChain(@NonNull Context context, @NonNull List<FontRequest> list, int i, @Nullable Executor executor, @NonNull Executor executor2, @NonNull FontRequestCallback fontRequestCallback) {
        FontRequestWorker.requestFontAsync(context.getApplicationContext(), list, i, executor, new CallbackWrapper(fontRequestCallback, executor2));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public static void resetCache() {
        FontRequestWorker.resetTypefaceCache();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    public static void resetTypefaceCache() {
        FontRequestWorker.resetTypefaceCache();
    }

    public static void requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, int i, @Nullable Executor executor, @NonNull Executor executor2, @NonNull FontRequestCallback fontRequestCallback) {
        FontRequestWorker.requestFontAsync(context.getApplicationContext(), TypefaceCompat$$ExternalSyntheticBackport2.m(new Object[]{fontRequest}), i, executor, new CallbackWrapper(fontRequestCallback, executor2));
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Typeface requestFont(@NonNull Context context, @NonNull List<FontRequest> list, int i, boolean z, @IntRange(from = 0) int i2, @NonNull Handler handler, @NonNull FontRequestCallback fontRequestCallback) {
        CallbackWrapper callbackWrapper = new CallbackWrapper(fontRequestCallback, new RequestExecutor.HandlerExecutor(handler));
        if (z) {
            if (list.size() <= 1) {
                return FontRequestWorker.requestFontSync(context, list.get(0), callbackWrapper, i, i2);
            }
            throw new IllegalArgumentException("Fallbacks with blocking fetches are not supported for performance reasons");
        }
        return FontRequestWorker.requestFontAsync(context, list, i, null, callbackWrapper);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, int i, boolean z, @IntRange(from = 0) int i2, @NonNull Handler handler, @NonNull FontRequestCallback fontRequestCallback) {
        return requestFont(context, (List<FontRequest>) TypefaceCompat$$ExternalSyntheticBackport2.m(new Object[]{fontRequest}), i, z, i2, handler, fontRequestCallback);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        @Deprecated
        public static final int RESULT_OK = 0;
        public static final int RESULT_SUCCESS = 0;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }
}
