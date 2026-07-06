package com.amazon.ignition.recommendation.contentprovider;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.reporting.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRequestStructureContentProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestStructureContentProvider.kt\ncom/amazon/ignition/recommendation/contentprovider/RequestStructureContentProviderKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,235:1\n11228#2:236\n11563#2,3:237\n13479#2,2:241\n1#3:240\n*S KotlinDebug\n*F\n+ 1 RequestStructureContentProvider.kt\ncom/amazon/ignition/recommendation/contentprovider/RequestStructureContentProviderKt\n*L\n208#1:236\n208#1:237,3\n232#1:241,2\n*E\n"})
public final class RequestStructureContentProviderKt {

    @Nullable
    public static final String TAG = ((ClassReference) Reflection.getOrCreateKotlinClass(RequestStructureContentProvider.class)).getSimpleName();

    public static final String encodeAsSHA256(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr);
        byte[] bArrDigest = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return toHex(bArrDigest);
    }

    @VisibleForTesting
    @NotNull
    public static final Set<String> getSHA256ApplicationSignatures(@NotNull PackageManager packageManager, @NotNull String packageId) {
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(packageId, "packageId");
        try {
            Signature[] signatures = getSignatures(packageManager, packageId);
            if (signatures != null) {
                ArrayList arrayList = new ArrayList(signatures.length);
                for (Signature signature : signatures) {
                    byte[] byteArray = signature.toByteArray();
                    Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                    arrayList.add(encodeAsSHA256(byteArray));
                }
                Set<String> set = CollectionsKt___CollectionsKt.toSet(arrayList);
                if (set != null) {
                    return set;
                }
            }
            return EmptySet.INSTANCE;
        } catch (Exception e) {
            Log.e(TAG, "Unable to retrieve app signing certificates for packageId=".concat(packageId), e);
            return EmptySet.INSTANCE;
        }
    }

    public static final Signature[] getSignatures(PackageManager packageManager, String str) {
        if (Build.VERSION.SDK_INT < 28) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }
        SigningInfo signingInfo = packageManager.getPackageInfo(str, 134217728).signingInfo;
        if (signingInfo != null) {
            return signingInfo.hasMultipleSigners() ? signingInfo.getApkContentsSigners() : signingInfo.getSigningCertificateHistory();
        }
        return null;
    }

    public static final String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1)));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
