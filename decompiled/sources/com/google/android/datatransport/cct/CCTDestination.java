package com.google.android.datatransport.cct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedDestination;
import j$.util.DesugarCollections;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class CCTDestination implements EncodedDestination {
    public static final String DEFAULT_API_KEY;
    public static final String DEFAULT_END_POINT;
    public static final String DESTINATION_NAME = "cct";
    public static final String EXTRAS_DELIMITER = "\\";
    public static final String EXTRAS_VERSION_MARKER = "1$";
    public static final CCTDestination INSTANCE;
    public static final String LEGACY_END_POINT;
    public static final CCTDestination LEGACY_INSTANCE;
    public static final Set<Encoding> SUPPORTED_ENCODINGS;

    @Nullable
    public final String apiKey;

    @NonNull
    public final String endPoint;

    static {
        String strMergeStrings = StringMerger.mergeStrings("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        DEFAULT_END_POINT = strMergeStrings;
        String strMergeStrings2 = StringMerger.mergeStrings("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        LEGACY_END_POINT = strMergeStrings2;
        String strMergeStrings3 = StringMerger.mergeStrings("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        DEFAULT_API_KEY = strMergeStrings3;
        SUPPORTED_ENCODINGS = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(new Encoding("proto"), new Encoding("json"))));
        INSTANCE = new CCTDestination(strMergeStrings, null);
        LEGACY_INSTANCE = new CCTDestination(strMergeStrings2, strMergeStrings3);
    }

    public CCTDestination(@NonNull String str, @Nullable String str2) {
        this.endPoint = str;
        this.apiKey = str2;
    }

    @NonNull
    public static String decodeExtras(@NonNull byte[] bArr) {
        return new String(bArr, Charset.forName("UTF-8"));
    }

    @NonNull
    public static byte[] encodeString(@NonNull String str) {
        return str.getBytes(Charset.forName("UTF-8"));
    }

    @NonNull
    public static CCTDestination fromByteArray(@NonNull byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (!str.startsWith(EXTRAS_VERSION_MARKER)) {
            throw new IllegalArgumentException("Version marker missing from extras");
        }
        String[] strArrSplit = str.substring(2).split(Pattern.quote(EXTRAS_DELIMITER), 2);
        if (strArrSplit.length != 2) {
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        String str2 = strArrSplit[0];
        if (str2.isEmpty()) {
            throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
        }
        String str3 = strArrSplit[1];
        if (str3.isEmpty()) {
            str3 = null;
        }
        return new CCTDestination(str2, str3);
    }

    @Nullable
    public byte[] asByteArray() {
        String str = this.apiKey;
        if (str == null && this.endPoint == null) {
            return null;
        }
        String str2 = this.endPoint;
        if (str == null) {
            str = "";
        }
        return String.format("%s%s%s%s", EXTRAS_VERSION_MARKER, str2, EXTRAS_DELIMITER, str).getBytes(Charset.forName("UTF-8"));
    }

    @Nullable
    public String getAPIKey() {
        return this.apiKey;
    }

    @NonNull
    public String getEndPoint() {
        return this.endPoint;
    }

    @Override // com.google.android.datatransport.runtime.Destination
    @Nullable
    public byte[] getExtras() {
        return asByteArray();
    }

    @Override // com.google.android.datatransport.runtime.Destination
    @NonNull
    public String getName() {
        return "cct";
    }

    @Override // com.google.android.datatransport.runtime.EncodedDestination
    public Set<Encoding> getSupportedEncodings() {
        return SUPPORTED_ENCODINGS;
    }
}
