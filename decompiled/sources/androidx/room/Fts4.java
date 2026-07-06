package androidx.room;

import androidx.annotation.RequiresApi;
import androidx.room.FtsOptions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.TYPE})
@RequiresApi(16)
@Retention(RetentionPolicy.CLASS)
public @interface Fts4 {
    Class<?> contentEntity() default Object.class;

    String languageId() default "";

    FtsOptions.MatchInfo matchInfo() default FtsOptions.MatchInfo.FTS4;

    String[] notIndexed() default {};

    FtsOptions.Order order() default FtsOptions.Order.ASC;

    int[] prefix() default {};

    String tokenizer() default "simple";

    String[] tokenizerArgs() default {};
}
