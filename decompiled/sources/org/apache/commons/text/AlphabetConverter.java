package org.apache.commons.text;

import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class AlphabetConverter {
    public static final String ARROW = " -> ";
    public final int encodedLetterLength;
    public final Map<String, String> encodedToOriginal;
    public final Map<Integer, String> originalToEncoded;

    public AlphabetConverter(Map<Integer, String> map, Map<String, String> map2, int i) {
        this.originalToEncoded = map;
        this.encodedToOriginal = map2;
        this.encodedLetterLength = i;
    }

    public static String codePointToString(int i) {
        return Character.charCount(i) == 1 ? String.valueOf((char) i) : new String(Character.toChars(i));
    }

    public static Integer[] convertCharsToIntegers(Character[] chArr) {
        if (chArr == null || chArr.length == 0) {
            return new Integer[0];
        }
        Integer[] numArr = new Integer[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            numArr[i] = Integer.valueOf(chArr[i].charValue());
        }
        return numArr;
    }

    public static AlphabetConverter createConverter(Integer[] numArr, Integer[] numArr2, Integer[] numArr3) {
        Integer num;
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet(Arrays.asList(numArr));
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(Arrays.asList(numArr2));
        LinkedHashSet<Integer> linkedHashSet3 = new LinkedHashSet(Arrays.asList(numArr3));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        HashMap map = new HashMap();
        for (Integer num2 : linkedHashSet3) {
            int iIntValue = num2.intValue();
            if (!linkedHashSet.contains(num2)) {
                throw new IllegalArgumentException("Can not use 'do not encode' list because original alphabet does not contain '" + codePointToString(iIntValue) + "'");
            }
            if (!linkedHashSet2.contains(num2)) {
                throw new IllegalArgumentException("Can not use 'do not encode' list because encoding alphabet does not contain '" + codePointToString(iIntValue) + "'");
            }
            map.put(num2, codePointToString(iIntValue));
        }
        if (linkedHashSet2.size() < linkedHashSet.size()) {
            if (linkedHashSet2.size() - linkedHashSet3.size() < 2) {
                throw new IllegalArgumentException("Must have at least two encoding characters (excluding those in the 'do not encode' list), but has " + (linkedHashSet2.size() - linkedHashSet3.size()));
            }
            int size = (linkedHashSet.size() - linkedHashSet3.size()) / (linkedHashSet2.size() - linkedHashSet3.size());
            int i = 1;
            while (size / linkedHashSet2.size() >= 1) {
                size /= linkedHashSet2.size();
                i++;
            }
            int i2 = i + 1;
            AlphabetConverter alphabetConverter = new AlphabetConverter(linkedHashMap, linkedHashMap2, i2);
            alphabetConverter.addSingleEncoding(i2, "", linkedHashSet2, linkedHashSet.iterator(), map);
            return alphabetConverter;
        }
        Iterator it = linkedHashSet2.iterator();
        for (Integer num3 : linkedHashSet) {
            String strCodePointToString = codePointToString(num3.intValue());
            if (map.containsKey(num3)) {
                linkedHashMap.put(num3, strCodePointToString);
                linkedHashMap2.put(strCodePointToString, strCodePointToString);
            } else {
                Object next = it.next();
                while (true) {
                    num = (Integer) next;
                    if (!linkedHashSet3.contains(num)) {
                        break;
                    }
                    next = it.next();
                }
                String strCodePointToString2 = codePointToString(num.intValue());
                linkedHashMap.put(num3, strCodePointToString2);
                linkedHashMap2.put(strCodePointToString2, strCodePointToString);
            }
        }
        return new AlphabetConverter(linkedHashMap, linkedHashMap2, 1);
    }

    public static AlphabetConverter createConverterFromChars(Character[] chArr, Character[] chArr2, Character[] chArr3) {
        return createConverter(convertCharsToIntegers(chArr), convertCharsToIntegers(chArr2), convertCharsToIntegers(chArr3));
    }

    public static AlphabetConverter createConverterFromMap(Map<Integer, String> map) {
        Map mapUnmodifiableMap = DesugarCollections.unmodifiableMap(map);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = 1;
        for (Map.Entry entry : mapUnmodifiableMap.entrySet()) {
            linkedHashMap.put(entry.getValue(), codePointToString(((Integer) entry.getKey()).intValue()));
            if (((String) entry.getValue()).length() > length) {
                length = ((String) entry.getValue()).length();
            }
        }
        return new AlphabetConverter(mapUnmodifiableMap, linkedHashMap, length);
    }

    public final void addSingleEncoding(int i, String str, Collection<Integer> collection, Iterator<Integer> it, Map<Integer, String> map) {
        Collection<Integer> collection2;
        Iterator<Integer> it2;
        Map<Integer, String> map2;
        if (i > 0) {
            for (Integer num : collection) {
                int iIntValue = num.intValue();
                if (!it.hasNext()) {
                    break;
                }
                if (i == this.encodedLetterLength && map.containsKey(num)) {
                    collection2 = collection;
                    it2 = it;
                    map2 = map;
                } else {
                    StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m(str);
                    sbM.append(codePointToString(iIntValue));
                    collection2 = collection;
                    it2 = it;
                    map2 = map;
                    addSingleEncoding(i - 1, sbM.toString(), collection2, it2, map2);
                }
                collection = collection2;
                it = it2;
                map = map2;
            }
            return;
        }
        Integer next = it.next();
        while (true) {
            Integer num2 = next;
            if (!map.containsKey(num2)) {
                String strCodePointToString = codePointToString(num2.intValue());
                this.originalToEncoded.put(num2, str);
                this.encodedToOriginal.put(str, strCodePointToString);
                return;
            } else {
                String strCodePointToString2 = codePointToString(num2.intValue());
                this.originalToEncoded.put(num2, strCodePointToString2);
                this.encodedToOriginal.put(strCodePointToString2, strCodePointToString2);
                if (!it.hasNext()) {
                    return;
                } else {
                    next = it.next();
                }
            }
        }
    }

    public String decode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int iCodePointAt = str.codePointAt(i);
            Integer numValueOf = Integer.valueOf(iCodePointAt);
            String strCodePointToString = codePointToString(iCodePointAt);
            if (strCodePointToString.equals(this.originalToEncoded.get(numValueOf))) {
                sb.append(strCodePointToString);
                i++;
            } else {
                if (this.encodedLetterLength + i > str.length()) {
                    throw new UnsupportedEncodingException("Unexpected end of string while decoding ".concat(str));
                }
                String strSubstring = str.substring(i, this.encodedLetterLength + i);
                String str2 = this.encodedToOriginal.get(strSubstring);
                if (str2 == null) {
                    throw new UnsupportedEncodingException("Unexpected string without decoding (" + strSubstring + ") in " + str);
                }
                sb.append(str2);
                i += this.encodedLetterLength;
            }
        }
        return sb.toString();
    }

    public String encode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int iCharCount = 0;
        while (iCharCount < str.length()) {
            int iCodePointAt = str.codePointAt(iCharCount);
            String str2 = this.originalToEncoded.get(Integer.valueOf(iCodePointAt));
            if (str2 == null) {
                throw new UnsupportedEncodingException("Couldn't find encoding for '" + codePointToString(iCodePointAt) + "' in " + str);
            }
            sb.append(str2);
            iCharCount += Character.charCount(iCodePointAt);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlphabetConverter)) {
            return false;
        }
        AlphabetConverter alphabetConverter = (AlphabetConverter) obj;
        return this.originalToEncoded.equals(alphabetConverter.originalToEncoded) && this.encodedToOriginal.equals(alphabetConverter.encodedToOriginal) && this.encodedLetterLength == alphabetConverter.encodedLetterLength;
    }

    public int getEncodedCharLength() {
        return this.encodedLetterLength;
    }

    public Map<Integer, String> getOriginalToEncoded() {
        return DesugarCollections.unmodifiableMap(this.originalToEncoded);
    }

    public int hashCode() {
        return Objects.hash(this.originalToEncoded, this.encodedToOriginal, Integer.valueOf(this.encodedLetterLength));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : this.originalToEncoded.entrySet()) {
            sb.append(codePointToString(entry.getKey().intValue()));
            sb.append(ARROW);
            sb.append(entry.getValue());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
