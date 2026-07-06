package androidx.profileinstaller;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
public class ProfileTranscoder {
    public static final int HOT = 1;
    public static final int INLINE_CACHE_MEGAMORPHIC_ENCODING = 7;
    public static final int INLINE_CACHE_MISSING_TYPES_ENCODING = 6;
    public static final byte[] MAGIC_PROF = {IonRawBinaryWriter.SYMBOL_TYPE, 114, 111, 0};
    public static final byte[] MAGIC_PROFM = {IonRawBinaryWriter.SYMBOL_TYPE, 114, 109, 0};
    public static final int POST_STARTUP = 4;
    public static final int STARTUP = 2;

    public static int computeMethodFlags(@NonNull DexProfileData dexProfileData) {
        Iterator<Map.Entry<Integer, Integer>> it = dexProfileData.methods.entrySet().iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            iIntValue |= it.next().getValue().intValue();
        }
        return iIntValue;
    }

    @NonNull
    public static byte[] createCompressibleBody(@NonNull DexProfileData[] dexProfileDataArr, @NonNull byte[] bArr) throws IOException {
        int i = 0;
        int methodBitmapStorageSize = 0;
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            methodBitmapStorageSize += getMethodBitmapStorageSize(dexProfileData.numMethodIds) + (dexProfileData.classSetSize * 2) + Encoding.utf8Length(generateDexKey(dexProfileData.apkName, dexProfileData.dexName, bArr)) + 16 + dexProfileData.hotMethodRegionSize;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(methodBitmapStorageSize);
        if (Arrays.equals(bArr, ProfileVersion.V009_O_MR1)) {
            int length = dexProfileDataArr.length;
            while (i < length) {
                DexProfileData dexProfileData2 = dexProfileDataArr[i];
                writeLineHeader(byteArrayOutputStream, dexProfileData2, generateDexKey(dexProfileData2.apkName, dexProfileData2.dexName, bArr));
                writeLineData(byteArrayOutputStream, dexProfileData2);
                i++;
            }
        } else {
            for (DexProfileData dexProfileData3 : dexProfileDataArr) {
                writeLineHeader(byteArrayOutputStream, dexProfileData3, generateDexKey(dexProfileData3.apkName, dexProfileData3.dexName, bArr));
            }
            int length2 = dexProfileDataArr.length;
            while (i < length2) {
                writeLineData(byteArrayOutputStream, dexProfileDataArr[i]);
                i++;
            }
        }
        if (byteArrayOutputStream.size() == methodBitmapStorageSize) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + methodBitmapStorageSize);
    }

    public static WritableFileSection createCompressibleClassSection(@NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        for (int i2 = 0; i2 < dexProfileDataArr.length; i2++) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i2];
                Encoding.writeUInt16(byteArrayOutputStream, i2);
                Encoding.writeUInt16(byteArrayOutputStream, dexProfileData.classSetSize);
                i = i + 4 + (dexProfileData.classSetSize * 2);
                writeClasses(byteArrayOutputStream, dexProfileData);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.CLASSES, i, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw new IllegalStateException("Expected size " + i + ", does not match actual size " + byteArray.length);
    }

    public static WritableFileSection createCompressibleMethodsSection(@NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        for (int i2 = 0; i2 < dexProfileDataArr.length; i2++) {
            try {
                DexProfileData dexProfileData = dexProfileDataArr[i2];
                int iComputeMethodFlags = computeMethodFlags(dexProfileData);
                byte[] bArrCreateMethodBitmapRegion = createMethodBitmapRegion(dexProfileData);
                byte[] bArrCreateMethodsWithInlineCaches = createMethodsWithInlineCaches(dexProfileData);
                Encoding.writeUInt16(byteArrayOutputStream, i2);
                int length = bArrCreateMethodBitmapRegion.length + 2 + bArrCreateMethodsWithInlineCaches.length;
                Encoding.writeUInt(byteArrayOutputStream, length, 4);
                Encoding.writeUInt16(byteArrayOutputStream, iComputeMethodFlags);
                byteArrayOutputStream.write(bArrCreateMethodBitmapRegion);
                byteArrayOutputStream.write(bArrCreateMethodsWithInlineCaches);
                i = i + 6 + length;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i == byteArray.length) {
            WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.METHODS, i, byteArray, true);
            byteArrayOutputStream.close();
            return writableFileSection;
        }
        throw new IllegalStateException("Expected size " + i + ", does not match actual size " + byteArray.length);
    }

    public static byte[] createMethodBitmapRegion(@NonNull DexProfileData dexProfileData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeMethodBitmap(byteArrayOutputStream, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] createMethodsWithInlineCaches(@NonNull DexProfileData dexProfileData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeMethodsWithInlineCaches(byteArrayOutputStream, dexProfileData);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public static String enforceSeparator(@NonNull String str, @NonNull String str2) {
        return "!".equals(str2) ? str.replace(":", "!") : ":".equals(str2) ? str.replace("!", ":") : str;
    }

    @NonNull
    public static String extractKey(@NonNull String str) {
        int iIndexOf = str.indexOf("!");
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(":");
        }
        return iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
    }

    @Nullable
    public static DexProfileData findByDexName(@NonNull DexProfileData[] dexProfileDataArr, @NonNull String str) {
        if (dexProfileDataArr.length <= 0) {
            return null;
        }
        String strExtractKey = extractKey(str);
        for (int i = 0; i < dexProfileDataArr.length; i++) {
            if (dexProfileDataArr[i].dexName.equals(strExtractKey)) {
                return dexProfileDataArr[i];
            }
        }
        return null;
    }

    @NonNull
    public static String generateDexKey(@NonNull String str, @NonNull String str2, @NonNull byte[] bArr) {
        String strDexKeySeparator = ProfileVersion.dexKeySeparator(bArr);
        if (str.length() <= 0) {
            return enforceSeparator(str2, strDexKeySeparator);
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return enforceSeparator(str2, strDexKeySeparator);
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
        sbM.append(ProfileVersion.dexKeySeparator(bArr));
        sbM.append(str2);
        return sbM.toString();
    }

    public static int getMethodBitmapStorageSize(int i) {
        return roundUpToByte(i * 2) / 8;
    }

    public static int methodFlagBitmapIndex(int i, int i2, int i3) {
        if (i == 1) {
            throw new IllegalStateException("HOT methods are not stored in the bitmap");
        }
        if (i == 2) {
            return i2;
        }
        if (i == 4) {
            return i2 + i3;
        }
        throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unexpected flag: ", i));
    }

    public static int[] readClasses(@NonNull InputStream inputStream, int i) throws IOException {
        int[] iArr = new int[i];
        int uInt = 0;
        for (int i2 = 0; i2 < i; i2++) {
            uInt += (int) Encoding.readUInt(inputStream, 2);
            iArr[i2] = uInt;
        }
        return iArr;
    }

    public static int readFlagsFromBitmap(@NonNull BitSet bitSet, int i, int i2) {
        int i3 = bitSet.get(i) ? 2 : 0;
        return bitSet.get(i + i2) ? i3 | 4 : i3;
    }

    public static byte[] readHeader(@NonNull InputStream inputStream, @NonNull byte[] bArr) throws IOException {
        if (Arrays.equals(bArr, Encoding.read(inputStream, bArr.length))) {
            return Encoding.read(inputStream, ProfileVersion.V010_P.length);
        }
        throw new IllegalStateException("Invalid magic");
    }

    public static void readHotMethodRegion(@NonNull InputStream inputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        int iAvailable = inputStream.available() - dexProfileData.hotMethodRegionSize;
        int uInt = 0;
        while (inputStream.available() > iAvailable) {
            uInt += (int) Encoding.readUInt(inputStream, 2);
            dexProfileData.methods.put(Integer.valueOf(uInt), 1);
            for (int uInt2 = (int) Encoding.readUInt(inputStream, 2); uInt2 > 0; uInt2--) {
                skipInlineCache(inputStream);
            }
        }
        if (inputStream.available() != iAvailable) {
            throw new IllegalStateException("Read too much data during profile line parse");
        }
    }

    @NonNull
    public static DexProfileData[] readMeta(@NonNull InputStream inputStream, @NonNull byte[] bArr, @NonNull byte[] bArr2, DexProfileData[] dexProfileDataArr) throws IOException {
        if (Arrays.equals(bArr, ProfileVersion.METADATA_V001_N)) {
            if (Arrays.equals(ProfileVersion.V015_S, bArr2)) {
                throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            }
            return readMetadata001(inputStream, bArr, dexProfileDataArr);
        }
        if (Arrays.equals(bArr, ProfileVersion.METADATA_V002)) {
            return readMetadataV002(inputStream, bArr2, dexProfileDataArr);
        }
        throw new IllegalStateException("Unsupported meta version");
    }

    @NonNull
    public static DexProfileData[] readMetadata001(@NonNull InputStream inputStream, @NonNull byte[] bArr, DexProfileData[] dexProfileDataArr) throws IOException {
        if (!Arrays.equals(bArr, ProfileVersion.METADATA_V001_N)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int uInt = (int) Encoding.readUInt(inputStream, 1);
        byte[] compressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt(inputStream, 4), (int) Encoding.readUInt(inputStream, 4));
        if (inputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
        try {
            DexProfileData[] metadataForNBody = readMetadataForNBody(byteArrayInputStream, uInt, dexProfileDataArr);
            byteArrayInputStream.close();
            return metadataForNBody;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public static DexProfileData[] readMetadataForNBody(@NonNull InputStream inputStream, int i, DexProfileData[] dexProfileDataArr) throws IOException {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i != dexProfileDataArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int uInt = (int) Encoding.readUInt(inputStream, 2);
            iArr[i2] = (int) Encoding.readUInt(inputStream, 2);
            strArr[i2] = Encoding.readString(inputStream, uInt);
        }
        for (int i3 = 0; i3 < i; i3++) {
            DexProfileData dexProfileData = dexProfileDataArr[i3];
            if (!dexProfileData.dexName.equals(strArr[i3])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i4 = iArr[i3];
            dexProfileData.classSetSize = i4;
            dexProfileData.classes = readClasses(inputStream, i4);
        }
        return dexProfileDataArr;
    }

    @NonNull
    public static DexProfileData[] readMetadataV002(@NonNull InputStream inputStream, @NonNull byte[] bArr, DexProfileData[] dexProfileDataArr) throws IOException {
        int uInt = (int) Encoding.readUInt(inputStream, 2);
        byte[] compressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt(inputStream, 4), (int) Encoding.readUInt(inputStream, 4));
        if (inputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
        try {
            DexProfileData[] metadataV002Body = readMetadataV002Body(byteArrayInputStream, bArr, uInt, dexProfileDataArr);
            byteArrayInputStream.close();
            return metadataV002Body;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public static DexProfileData[] readMetadataV002Body(@NonNull InputStream inputStream, @NonNull byte[] bArr, int i, DexProfileData[] dexProfileDataArr) throws IOException {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i != dexProfileDataArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i2 = 0; i2 < i; i2++) {
            Encoding.readUInt16(inputStream);
            String string = Encoding.readString(inputStream, (int) Encoding.readUInt(inputStream, 2));
            long uInt = Encoding.readUInt(inputStream, 4);
            int uInt2 = (int) Encoding.readUInt(inputStream, 2);
            DexProfileData dexProfileDataFindByDexName = findByDexName(dexProfileDataArr, string);
            if (dexProfileDataFindByDexName == null) {
                throw new IllegalStateException("Missing profile key: ".concat(string));
            }
            dexProfileDataFindByDexName.mTypeIdCount = uInt;
            int[] classes = readClasses(inputStream, uInt2);
            if (Arrays.equals(bArr, ProfileVersion.V001_N)) {
                dexProfileDataFindByDexName.classSetSize = uInt2;
                dexProfileDataFindByDexName.classes = classes;
            }
        }
        return dexProfileDataArr;
    }

    public static void readMethodBitmap(@NonNull InputStream inputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        BitSet bitSetValueOf = BitSet.valueOf(Encoding.read(inputStream, Encoding.bitsToBytes(dexProfileData.numMethodIds * 2)));
        int i = 0;
        while (true) {
            int i2 = dexProfileData.numMethodIds;
            if (i >= i2) {
                return;
            }
            int flagsFromBitmap = readFlagsFromBitmap(bitSetValueOf, i, i2);
            if (flagsFromBitmap != 0) {
                Integer num = dexProfileData.methods.get(Integer.valueOf(i));
                if (num == null) {
                    num = 0;
                }
                dexProfileData.methods.put(Integer.valueOf(i), Integer.valueOf(flagsFromBitmap | num.intValue()));
            }
            i++;
        }
    }

    @NonNull
    public static DexProfileData[] readProfile(@NonNull InputStream inputStream, @NonNull byte[] bArr, @NonNull String str) throws IOException {
        if (!Arrays.equals(bArr, ProfileVersion.V010_P)) {
            throw new IllegalStateException("Unsupported version");
        }
        int uInt = (int) Encoding.readUInt(inputStream, 1);
        byte[] compressed = Encoding.readCompressed(inputStream, (int) Encoding.readUInt(inputStream, 4), (int) Encoding.readUInt(inputStream, 4));
        if (inputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
        try {
            DexProfileData[] uncompressedBody = readUncompressedBody(byteArrayInputStream, str, uInt);
            byteArrayInputStream.close();
            return uncompressedBody;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public static DexProfileData[] readUncompressedBody(@NonNull InputStream inputStream, @NonNull String str, int i) throws IOException {
        if (inputStream.available() == 0) {
            return new DexProfileData[0];
        }
        DexProfileData[] dexProfileDataArr = new DexProfileData[i];
        for (int i2 = 0; i2 < i; i2++) {
            int uInt = (int) Encoding.readUInt(inputStream, 2);
            int uInt2 = (int) Encoding.readUInt(inputStream, 2);
            long uInt3 = Encoding.readUInt(inputStream, 4);
            dexProfileDataArr[i2] = new DexProfileData(str, Encoding.readString(inputStream, uInt), Encoding.readUInt(inputStream, 4), 0L, uInt2, (int) uInt3, (int) Encoding.readUInt(inputStream, 4), new int[uInt2], new TreeMap());
        }
        for (int i3 = 0; i3 < i; i3++) {
            DexProfileData dexProfileData = dexProfileDataArr[i3];
            readHotMethodRegion(inputStream, dexProfileData);
            dexProfileData.classes = readClasses(inputStream, dexProfileData.classSetSize);
            readMethodBitmap(inputStream, dexProfileData);
        }
        return dexProfileDataArr;
    }

    public static int roundUpToByte(int i) {
        return (i + 7) & (-8);
    }

    public static void setMethodBitmapBit(@NonNull byte[] bArr, int i, int i2, @NonNull DexProfileData dexProfileData) {
        int iMethodFlagBitmapIndex = methodFlagBitmapIndex(i, i2, dexProfileData.numMethodIds);
        int i3 = iMethodFlagBitmapIndex / 8;
        bArr[i3] = (byte) ((1 << (iMethodFlagBitmapIndex % 8)) | bArr[i3]);
    }

    public static void skipInlineCache(@NonNull InputStream inputStream) throws IOException {
        Encoding.readUInt16(inputStream);
        int uInt = (int) Encoding.readUInt(inputStream, 1);
        if (uInt == 6 || uInt == 7) {
            return;
        }
        while (uInt > 0) {
            Encoding.readUInt8(inputStream);
            for (int uInt2 = (int) Encoding.readUInt(inputStream, 1); uInt2 > 0; uInt2--) {
                Encoding.readUInt16(inputStream);
            }
            uInt--;
        }
    }

    public static boolean transcodeAndWriteBody(@NonNull OutputStream outputStream, @NonNull byte[] bArr, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        if (Arrays.equals(bArr, ProfileVersion.V015_S)) {
            writeProfileSections(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.V010_P)) {
            writeProfileForP(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.V005_O)) {
            writeProfileForO(outputStream, dexProfileDataArr);
            return true;
        }
        if (Arrays.equals(bArr, ProfileVersion.V009_O_MR1)) {
            writeProfileForO_MR1(outputStream, dexProfileDataArr);
            return true;
        }
        if (!Arrays.equals(bArr, ProfileVersion.V001_N)) {
            return false;
        }
        writeProfileForN(outputStream, dexProfileDataArr);
        return true;
    }

    public static void writeClasses(@NonNull OutputStream outputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        int[] iArr = dexProfileData.classes;
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            Encoding.writeUInt16(outputStream, i3 - i2);
            i++;
            i2 = i3;
        }
    }

    public static WritableFileSection writeDexFileSection(@NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Encoding.writeUInt16(byteArrayOutputStream, dexProfileDataArr.length);
            int i = 2;
            for (DexProfileData dexProfileData : dexProfileDataArr) {
                Encoding.writeUInt(byteArrayOutputStream, dexProfileData.dexChecksum, 4);
                Encoding.writeUInt(byteArrayOutputStream, dexProfileData.mTypeIdCount, 4);
                Encoding.writeUInt(byteArrayOutputStream, dexProfileData.numMethodIds, 4);
                String strGenerateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V015_S);
                int iUtf8Length = Encoding.utf8Length(strGenerateDexKey);
                Encoding.writeUInt16(byteArrayOutputStream, iUtf8Length);
                i = i + 14 + iUtf8Length;
                Encoding.writeString(byteArrayOutputStream, strGenerateDexKey);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (i == byteArray.length) {
                WritableFileSection writableFileSection = new WritableFileSection(FileSectionType.DEX_FILES, i, byteArray, false);
                byteArrayOutputStream.close();
                return writableFileSection;
            }
            throw new IllegalStateException("Expected size " + i + ", does not match actual size " + byteArray.length);
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void writeHeader(@NonNull OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(MAGIC_PROF);
        outputStream.write(bArr);
    }

    public static void writeLineData(@NonNull OutputStream outputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        writeMethodsWithInlineCaches(outputStream, dexProfileData);
        writeClasses(outputStream, dexProfileData);
        writeMethodBitmap(outputStream, dexProfileData);
    }

    public static void writeLineHeader(@NonNull OutputStream outputStream, @NonNull DexProfileData dexProfileData, @NonNull String str) throws IOException {
        Encoding.writeUInt16(outputStream, Encoding.utf8Length(str));
        Encoding.writeUInt16(outputStream, dexProfileData.classSetSize);
        Encoding.writeUInt(outputStream, dexProfileData.hotMethodRegionSize, 4);
        Encoding.writeUInt(outputStream, dexProfileData.dexChecksum, 4);
        Encoding.writeUInt(outputStream, dexProfileData.numMethodIds, 4);
        Encoding.writeString(outputStream, str);
    }

    public static void writeMethodBitmap(@NonNull OutputStream outputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        byte[] bArr = new byte[getMethodBitmapStorageSize(dexProfileData.numMethodIds)];
        for (Map.Entry<Integer, Integer> entry : dexProfileData.methods.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            int iIntValue2 = entry.getValue().intValue();
            if ((iIntValue2 & 2) != 0) {
                setMethodBitmapBit(bArr, 2, iIntValue, dexProfileData);
            }
            if ((iIntValue2 & 4) != 0) {
                setMethodBitmapBit(bArr, 4, iIntValue, dexProfileData);
            }
        }
        outputStream.write(bArr);
    }

    public static void writeMethodsWithInlineCaches(@NonNull OutputStream outputStream, @NonNull DexProfileData dexProfileData) throws IOException {
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : dexProfileData.methods.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            if ((entry.getValue().intValue() & 1) != 0) {
                Encoding.writeUInt16(outputStream, iIntValue - i);
                Encoding.writeUInt16(outputStream, 0);
                i = iIntValue;
            }
        }
    }

    public static void writeProfileForN(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        Encoding.writeUInt16(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            String strGenerateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V001_N);
            Encoding.writeUInt16(outputStream, Encoding.utf8Length(strGenerateDexKey));
            Encoding.writeUInt16(outputStream, dexProfileData.methods.size());
            Encoding.writeUInt16(outputStream, dexProfileData.classes.length);
            Encoding.writeUInt(outputStream, dexProfileData.dexChecksum, 4);
            Encoding.writeString(outputStream, strGenerateDexKey);
            Iterator<Integer> it = dexProfileData.methods.keySet().iterator();
            while (it.hasNext()) {
                Encoding.writeUInt16(outputStream, it.next().intValue());
            }
            for (int i : dexProfileData.classes) {
                Encoding.writeUInt16(outputStream, i);
            }
        }
    }

    public static void writeProfileForO(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            int size = dexProfileData.methods.size() * 4;
            String strGenerateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, ProfileVersion.V005_O);
            Encoding.writeUInt16(outputStream, Encoding.utf8Length(strGenerateDexKey));
            Encoding.writeUInt16(outputStream, dexProfileData.classes.length);
            Encoding.writeUInt(outputStream, size, 4);
            Encoding.writeUInt(outputStream, dexProfileData.dexChecksum, 4);
            Encoding.writeString(outputStream, strGenerateDexKey);
            Iterator<Integer> it = dexProfileData.methods.keySet().iterator();
            while (it.hasNext()) {
                Encoding.writeUInt16(outputStream, it.next().intValue());
                Encoding.writeUInt16(outputStream, 0);
            }
            for (int i : dexProfileData.classes) {
                Encoding.writeUInt16(outputStream, i);
            }
        }
    }

    public static void writeProfileForO_MR1(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        byte[] bArrCreateCompressibleBody = createCompressibleBody(dexProfileDataArr, ProfileVersion.V009_O_MR1);
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        Encoding.writeCompressed(outputStream, bArrCreateCompressibleBody);
    }

    public static void writeProfileForP(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        byte[] bArrCreateCompressibleBody = createCompressibleBody(dexProfileDataArr, ProfileVersion.V010_P);
        Encoding.writeUInt8(outputStream, dexProfileDataArr.length);
        Encoding.writeCompressed(outputStream, bArrCreateCompressibleBody);
    }

    public static void writeProfileForS(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        writeProfileSections(outputStream, dexProfileDataArr);
    }

    public static void writeProfileSections(@NonNull OutputStream outputStream, @NonNull DexProfileData[] dexProfileDataArr) throws IOException {
        int length;
        ArrayList arrayList = new ArrayList(3);
        ArrayList arrayList2 = new ArrayList(3);
        arrayList.add(writeDexFileSection(dexProfileDataArr));
        arrayList.add(createCompressibleClassSection(dexProfileDataArr));
        arrayList.add(createCompressibleMethodsSection(dexProfileDataArr));
        long length2 = ((long) ProfileVersion.V015_S.length) + ((long) MAGIC_PROF.length) + 4 + ((long) (arrayList.size() * 16));
        Encoding.writeUInt(outputStream, arrayList.size(), 4);
        for (int i = 0; i < arrayList.size(); i++) {
            WritableFileSection writableFileSection = (WritableFileSection) arrayList.get(i);
            Encoding.writeUInt(outputStream, writableFileSection.mType.mValue, 4);
            Encoding.writeUInt(outputStream, length2, 4);
            if (writableFileSection.mNeedsCompression) {
                byte[] bArr = writableFileSection.mContents;
                long length3 = bArr.length;
                byte[] bArrCompress = Encoding.compress(bArr);
                arrayList2.add(bArrCompress);
                Encoding.writeUInt(outputStream, bArrCompress.length, 4);
                Encoding.writeUInt(outputStream, length3, 4);
                length = bArrCompress.length;
            } else {
                arrayList2.add(writableFileSection.mContents);
                Encoding.writeUInt(outputStream, writableFileSection.mContents.length, 4);
                Encoding.writeUInt(outputStream, 0L, 4);
                length = writableFileSection.mContents.length;
            }
            length2 += (long) length;
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            outputStream.write((byte[]) arrayList2.get(i2));
        }
    }
}
