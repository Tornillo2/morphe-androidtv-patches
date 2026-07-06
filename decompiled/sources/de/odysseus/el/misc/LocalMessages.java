package de.odysseus.el.misc;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LocalMessages {
    public static final String BUNDLE_NAME = "de.odysseus.el.misc.LocalStrings";
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String get(String str, Object... objArr) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(str), objArr);
        } catch (MissingResourceException unused) {
            StringBuilder sb = new StringBuilder();
            try {
                sb.append(RESOURCE_BUNDLE.getString("message.unknown"));
                sb.append(": ");
            } catch (MissingResourceException unused2) {
            }
            sb.append(str);
            if (objArr != null && objArr.length > 0) {
                sb.append("(");
                sb.append(objArr[0]);
                for (int i = 1; i < objArr.length; i++) {
                    sb.append(", ");
                    sb.append(objArr[i]);
                }
                sb.append(")");
            }
            return sb.toString();
        }
    }
}
