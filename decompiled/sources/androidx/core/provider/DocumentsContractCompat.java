package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DocumentsContractCompat {
    public static final String PATH_TREE = "tree";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DocumentCompat {
        public static final int FLAG_VIRTUAL_DOCUMENT = 512;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static class DocumentsContractApi21Impl {
        public static Uri buildChildDocumentsUri(String str, String str2) {
            return DocumentsContract.buildChildDocumentsUri(str, str2);
        }

        public static Uri buildChildDocumentsUriUsingTree(Uri uri, String str) {
            return DocumentsContract.buildChildDocumentsUriUsingTree(uri, str);
        }

        public static Uri buildDocumentUriUsingTree(Uri uri, String str) {
            return DocumentsContract.buildDocumentUriUsingTree(uri, str);
        }

        public static Uri buildTreeDocumentUri(String str, String str2) {
            return DocumentsContract.buildTreeDocumentUri(str, str2);
        }

        public static Uri createDocument(ContentResolver contentResolver, Uri uri, String str, String str2) throws FileNotFoundException {
            return DocumentsContract.createDocument(contentResolver, uri, str, str2);
        }

        public static String getTreeDocumentId(Uri uri) {
            return DocumentsContract.getTreeDocumentId(uri);
        }

        public static Uri renameDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
            return DocumentsContract.renameDocument(contentResolver, uri, str);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class DocumentsContractApi24Impl {
        public static boolean isTreeUri(@NonNull Uri uri) {
            return DocumentsContract.isTreeUri(uri);
        }

        public static boolean removeDocument(ContentResolver contentResolver, Uri uri, Uri uri2) throws FileNotFoundException {
            return DocumentsContract.removeDocument(contentResolver, uri, uri2);
        }
    }

    @Nullable
    public static Uri buildChildDocumentsUri(@NonNull String str, @Nullable String str2) {
        return DocumentsContract.buildChildDocumentsUri(str, str2);
    }

    @Nullable
    public static Uri buildChildDocumentsUriUsingTree(@NonNull Uri uri, @NonNull String str) {
        return DocumentsContract.buildChildDocumentsUriUsingTree(uri, str);
    }

    @Nullable
    public static Uri buildDocumentUri(@NonNull String str, @NonNull String str2) {
        return DocumentsContract.buildDocumentUri(str, str2);
    }

    @Nullable
    public static Uri buildDocumentUriUsingTree(@NonNull Uri uri, @NonNull String str) {
        return DocumentsContract.buildDocumentUriUsingTree(uri, str);
    }

    @Nullable
    public static Uri buildTreeDocumentUri(@NonNull String str, @NonNull String str2) {
        return DocumentsContract.buildTreeDocumentUri(str, str2);
    }

    @Nullable
    public static Uri createDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str, @NonNull String str2) throws FileNotFoundException {
        return DocumentsContract.createDocument(contentResolver, uri, str, str2);
    }

    @Nullable
    public static String getDocumentId(@NonNull Uri uri) {
        return DocumentsContract.getDocumentId(uri);
    }

    @Nullable
    public static String getTreeDocumentId(@NonNull Uri uri) {
        return DocumentsContract.getTreeDocumentId(uri);
    }

    public static boolean isDocumentUri(@NonNull Context context, @Nullable Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }

    public static boolean isTreeUri(@NonNull Uri uri) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DocumentsContractApi24Impl.isTreeUri(uri);
        }
        List<String> pathSegments = uri.getPathSegments();
        return pathSegments.size() >= 2 && PATH_TREE.equals(pathSegments.get(0));
    }

    public static boolean removeDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull Uri uri2) throws FileNotFoundException {
        return Build.VERSION.SDK_INT >= 24 ? DocumentsContractApi24Impl.removeDocument(contentResolver, uri, uri2) : DocumentsContract.deleteDocument(contentResolver, uri);
    }

    @Nullable
    public static Uri renameDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        return DocumentsContract.renameDocument(contentResolver, uri, str);
    }
}
