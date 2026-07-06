package org.apache.commons.text.lookup;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Properties;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class PropertiesStringLookup extends AbstractStringLookup {
    public static final PropertiesStringLookup INSTANCE = new PropertiesStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("::");
        if (strArrSplit.length < 2) {
            throw IllegalArgumentExceptions.format("Bad properties key format [%s]; expected format is DocumentPath::Key.", str);
        }
        String str2 = strArrSplit[0];
        String strSubstringAfter = substringAfter(str, "::");
        try {
            Properties properties = new Properties();
            InputStream inputStreamNewInputStream = Files.newInputStream(Paths.get(str2, new String[0]), new OpenOption[0]);
            try {
                properties.load(inputStreamNewInputStream);
                if (inputStreamNewInputStream != null) {
                    inputStreamNewInputStream.close();
                }
                return properties.getProperty(strSubstringAfter);
            } finally {
            }
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up properties [%s] and key [%s].", str2, strSubstringAfter);
        }
    }
}
