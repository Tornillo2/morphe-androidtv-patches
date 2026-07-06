package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FontProvider {
    public static final LruCache<ProviderCacheKey, ProviderInfo> sProviderCache = new LruCache<>(2);
    public static final Comparator<byte[]> sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ContentQueryWrapper {

        /* JADX INFO: renamed from: androidx.core.provider.FontProvider$ContentQueryWrapper$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static ContentQueryWrapper make(Context context, Uri uri) {
                return Build.VERSION.SDK_INT < 24 ? new ContentQueryWrapperApi16Impl(context, uri) : new ContentQueryWrapperApi24Impl(context, uri);
            }
        }

        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        public final ContentProviderClient mClient;

        public ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        public final ContentProviderClient mClient;

        public ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() throws Exception {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                FontProvider$ContentQueryWrapperApi24Impl$$ExternalSyntheticAutoCloseableDispatcher0.m(contentProviderClient);
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ProviderCacheKey {
        public String mAuthority;
        public List<List<byte[]>> mCertificates;
        public String mPackageName;

        public ProviderCacheKey(String str, String str2, List<List<byte[]>> list) {
            this.mAuthority = str;
            this.mPackageName = str2;
            this.mCertificates = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProviderCacheKey)) {
                return false;
            }
            ProviderCacheKey providerCacheKey = (ProviderCacheKey) obj;
            return Objects.equals(this.mAuthority, providerCacheKey.mAuthority) && Objects.equals(this.mPackageName, providerCacheKey.mPackageName) && Objects.equals(this.mCertificates, providerCacheKey.mCertificates);
        }

        public int hashCode() {
            return Objects.hash(this.mAuthority, this.mPackageName, this.mCertificates);
        }
    }

    public static /* synthetic */ int $r8$lambda$YnOn4sMaJN6i8fkk9HOHIkI5PVE(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return b - b2;
            }
        }
        return 0;
    }

    @VisibleForTesting
    public static void clearProviderCache() {
        sProviderCache.trimToSize(-1);
    }

    public static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    public static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        List<List<byte[]>> list = fontRequest.mCertificates;
        return list != null ? list : FontResourcesParserCompat.readCerts(resources, fontRequest.mCertificatesArray);
    }

    @NonNull
    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(@NonNull Context context, @NonNull List<FontRequest> list, @Nullable CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        Trace.beginSection(androidx.tracing.Trace.truncatedTraceSectionLabel("FontProvider.getFontFamilyResult"));
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                FontRequest fontRequest = list.get(i);
                ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
                if (provider == null) {
                    return new FontsContractCompat.FontFamilyResult(1, (FontsContractCompat.FontInfo[]) null);
                }
                arrayList.add(query(context, fontRequest, provider.authority, cancellationSignal));
            }
            return new FontsContractCompat.FontFamilyResult(0, arrayList);
        } finally {
            Trace.endSection();
        }
    }

    @Nullable
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        Trace.beginSection(androidx.tracing.Trace.truncatedTraceSectionLabel("FontProvider.getProvider"));
        try {
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            ProviderCacheKey providerCacheKey = new ProviderCacheKey(fontRequest.mProviderAuthority, fontRequest.mProviderPackage, certificates);
            ProviderInfo providerInfo = sProviderCache.get(providerCacheKey);
            if (providerInfo != null) {
                return providerInfo;
            }
            String str = fontRequest.mProviderAuthority;
            ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
            if (providerInfoResolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
            }
            if (!providerInfoResolveContentProvider.packageName.equals(fontRequest.mProviderPackage)) {
                throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + fontRequest.mProviderPackage);
            }
            List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
            Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                    sProviderCache.put(providerCacheKey, providerInfoResolveContentProvider);
                    return providerInfoResolveContentProvider;
                }
            }
            Trace.endSection();
            return null;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v10 */
    /* JADX WARN: Type inference failed for: r18v2, types: [androidx.core.provider.FontProvider$ContentQueryWrapper] */
    @NonNull
    @VisibleForTesting
    public static FontsContractCompat.FontInfo[] query(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        ?? r18;
        String[] strArr;
        ContentQueryWrapper contentQueryWrapper;
        ContentQueryWrapper contentQueryWrapper2;
        Uri uriWithAppendedId;
        Trace.beginSection(androidx.tracing.Trace.truncatedTraceSectionLabel("FontProvider.query"));
        try {
            ArrayList arrayList = new ArrayList();
            Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
            Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath(StringLookupFactory.KEY_FILE).build();
            ContentQueryWrapper contentQueryWrapperMake = ContentQueryWrapper.CC.make(context, uriBuild);
            Cursor cursorQuery = null;
            try {
                strArr = new String[]{"_id", FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE};
                Trace.beginSection(androidx.tracing.Trace.truncatedTraceSectionLabel("ContentQueryWrapper.query"));
            } catch (Throwable th) {
                th = th;
                r18 = contentQueryWrapperMake;
            }
            try {
                try {
                    cursorQuery = contentQueryWrapperMake.query(uriBuild, strArr, "query = ?", new String[]{fontRequest.mQuery}, null, cancellationSignal);
                    if (cursorQuery == null || cursorQuery.getCount() <= 0) {
                        contentQueryWrapper = contentQueryWrapperMake;
                    } else {
                        int columnIndex = cursorQuery.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE);
                        ArrayList arrayList2 = new ArrayList();
                        int columnIndex2 = cursorQuery.getColumnIndex("_id");
                        int columnIndex3 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.FILE_ID);
                        int columnIndex4 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX);
                        int columnIndex5 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.WEIGHT);
                        int columnIndex6 = cursorQuery.getColumnIndex(FontsContractCompat.Columns.ITALIC);
                        while (cursorQuery.moveToNext()) {
                            int i = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                            int i2 = columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0;
                            if (columnIndex3 == -1) {
                                contentQueryWrapper2 = contentQueryWrapperMake;
                                uriWithAppendedId = ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2));
                            } else {
                                contentQueryWrapper2 = contentQueryWrapperMake;
                                uriWithAppendedId = ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3));
                            }
                            arrayList2.add(new FontsContractCompat.FontInfo(uriWithAppendedId, i2, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i));
                            contentQueryWrapperMake = contentQueryWrapper2;
                        }
                        contentQueryWrapper = contentQueryWrapperMake;
                        arrayList = arrayList2;
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    contentQueryWrapper.close();
                    return (FontsContractCompat.FontInfo[]) arrayList.toArray(new FontsContractCompat.FontInfo[0]);
                } finally {
                }
            } catch (Throwable th2) {
                th = th2;
                r18 = context;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                r18.close();
                throw th;
            }
        } finally {
            Trace.endSection();
        }
    }
}
