package androidx.media3.exoplayer.offline;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DownloadRequest implements Parcelable {
    public static final Parcelable.Creator<DownloadRequest> CREATOR = new AnonymousClass1();

    @Nullable
    public final String customCacheKey;
    public final byte[] data;
    public final String id;

    @Nullable
    public final byte[] keySetId;

    @Nullable
    public final String mimeType;
    public final List<StreamKey> streamKeys;
    public final Uri uri;

    /* JADX INFO: renamed from: androidx.media3.exoplayer.offline.DownloadRequest$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Parcelable.Creator<DownloadRequest> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadRequest createFromParcel(Parcel parcel) {
            return new DownloadRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadRequest[] newArray(int i) {
            return new DownloadRequest[i];
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {

        @Nullable
        public String customCacheKey;

        @Nullable
        public byte[] data;
        public final String id;

        @Nullable
        public byte[] keySetId;

        @Nullable
        public String mimeType;

        @Nullable
        public List<StreamKey> streamKeys;
        public final Uri uri;

        public Builder(String str, Uri uri) {
            this.id = str;
            this.uri = uri;
        }

        public DownloadRequest build() {
            String str = this.id;
            Uri uri = this.uri;
            String str2 = this.mimeType;
            List listOf = this.streamKeys;
            if (listOf == null) {
                listOf = ImmutableList.of();
            }
            return new DownloadRequest(str, uri, str2, listOf, this.keySetId, this.customCacheKey, this.data);
        }

        @CanIgnoreReturnValue
        public Builder setCustomCacheKey(@Nullable String str) {
            this.customCacheKey = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setData(@Nullable byte[] bArr) {
            this.data = bArr;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeySetId(@Nullable byte[] bArr) {
            this.keySetId = bArr;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMimeType(@Nullable String str) {
            this.mimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setStreamKeys(@Nullable List<StreamKey> list) {
            this.streamKeys = list;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnsupportedRequestException extends IOException {
    }

    public /* synthetic */ DownloadRequest(String str, Uri uri, String str2, List list, byte[] bArr, String str3, byte[] bArr2, AnonymousClass1 anonymousClass1) {
        this(str, uri, str2, list, bArr, str3, bArr2);
    }

    public DownloadRequest copyWithId(String str) {
        return new DownloadRequest(str, this.uri, this.mimeType, this.streamKeys, this.keySetId, this.customCacheKey, this.data);
    }

    public DownloadRequest copyWithKeySetId(@Nullable byte[] bArr) {
        return new DownloadRequest(this.id, this.uri, this.mimeType, this.streamKeys, bArr, this.customCacheKey, this.data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List] */
    public DownloadRequest copyWithMergedRequest(DownloadRequest downloadRequest) {
        ?? arrayList;
        Assertions.checkArgument(this.id.equals(downloadRequest.id));
        if (this.streamKeys.isEmpty() || downloadRequest.streamKeys.isEmpty()) {
            arrayList = Collections.EMPTY_LIST;
        } else {
            arrayList = new ArrayList(this.streamKeys);
            for (int i = 0; i < downloadRequest.streamKeys.size(); i++) {
                StreamKey streamKey = downloadRequest.streamKeys.get(i);
                if (!arrayList.contains(streamKey)) {
                    arrayList.add(streamKey);
                }
            }
        }
        return new DownloadRequest(this.id, downloadRequest.uri, downloadRequest.mimeType, arrayList, downloadRequest.keySetId, downloadRequest.customCacheKey, downloadRequest.data);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof DownloadRequest)) {
            return false;
        }
        DownloadRequest downloadRequest = (DownloadRequest) obj;
        return this.id.equals(downloadRequest.id) && this.uri.equals(downloadRequest.uri) && Util.areEqual(this.mimeType, downloadRequest.mimeType) && this.streamKeys.equals(downloadRequest.streamKeys) && Arrays.equals(this.keySetId, downloadRequest.keySetId) && Util.areEqual(this.customCacheKey, downloadRequest.customCacheKey) && Arrays.equals(this.data, downloadRequest.data);
    }

    public final int hashCode() {
        int iHashCode = (this.uri.hashCode() + (this.id.hashCode() * 961)) * 31;
        String str = this.mimeType;
        int iHashCode2 = (Arrays.hashCode(this.keySetId) + ((this.streamKeys.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31)) * 31)) * 31;
        String str2 = this.customCacheKey;
        return Arrays.hashCode(this.data) + ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public MediaItem toMediaItem() {
        MediaItem.Builder builder = new MediaItem.Builder();
        String str = this.id;
        str.getClass();
        builder.mediaId = str;
        builder.uri = this.uri;
        builder.customCacheKey = this.customCacheKey;
        builder.mimeType = this.mimeType;
        builder.setStreamKeys(this.streamKeys);
        return builder.build();
    }

    public String toString() {
        return this.mimeType + ":" + this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.uri.toString());
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.streamKeys.size());
        for (int i2 = 0; i2 < this.streamKeys.size(); i2++) {
            parcel.writeParcelable(this.streamKeys.get(i2), 0);
        }
        parcel.writeByteArray(this.keySetId);
        parcel.writeString(this.customCacheKey);
        parcel.writeByteArray(this.data);
    }

    public DownloadRequest(String str, Uri uri, @Nullable String str2, List<StreamKey> list, @Nullable byte[] bArr, @Nullable String str3, @Nullable byte[] bArr2) {
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(uri, str2);
        if (iInferContentTypeForUriAndMimeType == 0 || iInferContentTypeForUriAndMimeType == 2 || iInferContentTypeForUriAndMimeType == 1) {
            Assertions.checkArgument(str3 == null, "customCacheKey must be null for type: " + iInferContentTypeForUriAndMimeType);
        }
        this.id = str;
        this.uri = uri;
        this.mimeType = str2;
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        this.streamKeys = DesugarCollections.unmodifiableList(arrayList);
        this.keySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        this.customCacheKey = str3;
        this.data = bArr2 != null ? Arrays.copyOf(bArr2, bArr2.length) : Util.EMPTY_BYTE_ARRAY;
    }

    public DownloadRequest(Parcel parcel) {
        String string = parcel.readString();
        Util.castNonNull(string);
        this.id = string;
        this.uri = Uri.parse(parcel.readString());
        this.mimeType = parcel.readString();
        int i = parcel.readInt();
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add((StreamKey) parcel.readParcelable(StreamKey.class.getClassLoader()));
        }
        this.streamKeys = DesugarCollections.unmodifiableList(arrayList);
        this.keySetId = parcel.createByteArray();
        this.customCacheKey = parcel.readString();
        this.data = parcel.createByteArray();
    }
}
