package org.apache.commons.lang3;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class LocaleUtils {
    public static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
    public static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SyncAvoid {
        public static final List<Locale> AVAILABLE_LOCALE_LIST;
        public static final Set<Locale> AVAILABLE_LOCALE_SET;

        static {
            ArrayList arrayList = new ArrayList(Arrays.asList(Locale.getAvailableLocales()));
            AVAILABLE_LOCALE_LIST = DesugarCollections.unmodifiableList(arrayList);
            AVAILABLE_LOCALE_SET = DesugarCollections.unmodifiableSet(new HashSet(arrayList));
        }
    }

    public static List<Locale> availableLocaleList() {
        return SyncAvoid.AVAILABLE_LOCALE_LIST;
    }

    public static Set<Locale> availableLocaleSet() {
        return SyncAvoid.AVAILABLE_LOCALE_SET;
    }

    public static List<Locale> countriesByLanguage(String str) {
        if (str == null) {
            return Collections.EMPTY_LIST;
        }
        List<Locale> list = cCountriesByLanguage.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Locale locale : SyncAvoid.AVAILABLE_LOCALE_LIST) {
            if (str.equals(locale.getLanguage()) && !locale.getCountry().isEmpty() && locale.getVariant().isEmpty()) {
                arrayList.add(locale);
            }
        }
        List<Locale> listUnmodifiableList = DesugarCollections.unmodifiableList(arrayList);
        ConcurrentMap<String, List<Locale>> concurrentMap = cCountriesByLanguage;
        concurrentMap.putIfAbsent(str, listUnmodifiableList);
        return concurrentMap.get(str);
    }

    public static boolean isAvailableLocale(Locale locale) {
        return SyncAvoid.AVAILABLE_LOCALE_LIST.contains(locale);
    }

    public static boolean isISO3166CountryCode(String str) {
        return StringUtils.isAllUpperCase(str) && str.length() == 2;
    }

    public static boolean isISO639LanguageCode(String str) {
        if (StringUtils.isAllLowerCase(str)) {
            return str.length() == 2 || str.length() == 3;
        }
        return false;
    }

    public static boolean isNumericAreaCode(String str) {
        return StringUtils.isNumeric(str) && str.length() == 3;
    }

    public static List<Locale> languagesByCountry(String str) {
        if (str == null) {
            return Collections.EMPTY_LIST;
        }
        List<Locale> list = cLanguagesByCountry.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Locale locale : SyncAvoid.AVAILABLE_LOCALE_LIST) {
            if (str.equals(locale.getCountry()) && locale.getVariant().isEmpty()) {
                arrayList.add(locale);
            }
        }
        List<Locale> listUnmodifiableList = DesugarCollections.unmodifiableList(arrayList);
        ConcurrentMap<String, List<Locale>> concurrentMap = cLanguagesByCountry;
        concurrentMap.putIfAbsent(str, listUnmodifiableList);
        return concurrentMap.get(str);
    }

    public static List<Locale> localeLookupList(Locale locale) {
        return localeLookupList(locale, locale);
    }

    public static Locale parseLocale(String str) {
        if (isISO639LanguageCode(str)) {
            return new Locale(str);
        }
        String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, -1);
        String str2 = strArrSplit[0];
        if (strArrSplit.length == 2) {
            String str3 = strArrSplit[1];
            if ((isISO639LanguageCode(str2) && isISO3166CountryCode(str3)) || isNumericAreaCode(str3)) {
                return new Locale(str2, str3);
            }
        } else if (strArrSplit.length == 3) {
            String str4 = strArrSplit[1];
            String str5 = strArrSplit[2];
            if (isISO639LanguageCode(str2) && ((str4.isEmpty() || isISO3166CountryCode(str4) || isNumericAreaCode(str4)) && !str5.isEmpty())) {
                return new Locale(str2, str4, str5);
            }
        }
        throw new IllegalArgumentException("Invalid locale format: ".concat(str));
    }

    public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return new Locale("", "");
        }
        if (str.contains("#")) {
            throw new IllegalArgumentException("Invalid locale format: ".concat(str));
        }
        int length = str.length();
        if (length < 2) {
            throw new IllegalArgumentException("Invalid locale format: ".concat(str));
        }
        if (str.charAt(0) != '_') {
            return parseLocale(str);
        }
        if (length < 3) {
            throw new IllegalArgumentException("Invalid locale format: ".concat(str));
        }
        char cCharAt = str.charAt(1);
        char cCharAt2 = str.charAt(2);
        if (!Character.isUpperCase(cCharAt) || !Character.isUpperCase(cCharAt2)) {
            throw new IllegalArgumentException("Invalid locale format: ".concat(str));
        }
        if (length == 3) {
            return new Locale("", str.substring(1, 3));
        }
        if (length < 5) {
            throw new IllegalArgumentException("Invalid locale format: ".concat(str));
        }
        if (str.charAt(3) == '_') {
            return new Locale("", str.substring(1, 3), str.substring(4));
        }
        throw new IllegalArgumentException("Invalid locale format: ".concat(str));
    }

    public static List<Locale> localeLookupList(Locale locale, Locale locale2) {
        ArrayList arrayList = new ArrayList(4);
        if (locale != null) {
            arrayList.add(locale);
            if (!locale.getVariant().isEmpty()) {
                arrayList.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (!locale.getCountry().isEmpty()) {
                arrayList.add(new Locale(locale.getLanguage(), ""));
            }
            if (!arrayList.contains(locale2)) {
                arrayList.add(locale2);
            }
        }
        return DesugarCollections.unmodifiableList(arrayList);
    }
}
