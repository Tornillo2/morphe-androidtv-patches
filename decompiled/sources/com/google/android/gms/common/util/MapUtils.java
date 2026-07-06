package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(@NonNull StringBuilder sb, @NonNull HashMap<String, String> map) {
        sb.append("{");
        boolean z = true;
        for (String str : map.keySet()) {
            if (!z) {
                sb.append(",");
            }
            String str2 = map.get(str);
            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
            sb.append(str);
            sb.append("\":");
            if (str2 == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else {
                sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                sb.append(str2);
                sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
            }
            z = false;
        }
        sb.append("}");
    }
}
