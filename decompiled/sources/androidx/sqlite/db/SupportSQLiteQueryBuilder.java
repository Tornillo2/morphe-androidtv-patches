package androidx.sqlite.db;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SupportSQLiteQueryBuilder {
    public static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    public Object[] mBindArgs;
    public String mSelection;
    public final String mTable;
    public boolean mDistinct = false;
    public String[] mColumns = null;
    public String mGroupBy = null;
    public String mHaving = null;
    public String mOrderBy = null;
    public String mLimit = null;

    public SupportSQLiteQueryBuilder(String str) {
        this.mTable = str;
    }

    public static void appendClause(StringBuilder sb, String str, String str2) {
        if (isEmpty(str2)) {
            return;
        }
        sb.append(str);
        sb.append(str2);
    }

    public static void appendColumns(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(' ');
    }

    public static SupportSQLiteQueryBuilder builder(String str) {
        return new SupportSQLiteQueryBuilder(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public SupportSQLiteQueryBuilder columns(String[] strArr) {
        this.mColumns = strArr;
        return this;
    }

    public SupportSQLiteQuery create() {
        if (isEmpty(this.mGroupBy) && !isEmpty(this.mHaving)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("SELECT ");
        if (this.mDistinct) {
            sb.append("DISTINCT ");
        }
        String[] strArr = this.mColumns;
        if (strArr == null || strArr.length == 0) {
            sb.append(" * ");
        } else {
            appendColumns(sb, strArr);
        }
        sb.append(" FROM ");
        sb.append(this.mTable);
        appendClause(sb, " WHERE ", this.mSelection);
        appendClause(sb, " GROUP BY ", this.mGroupBy);
        appendClause(sb, " HAVING ", this.mHaving);
        appendClause(sb, " ORDER BY ", this.mOrderBy);
        appendClause(sb, " LIMIT ", this.mLimit);
        return new SimpleSQLiteQuery(sb.toString(), this.mBindArgs);
    }

    public SupportSQLiteQueryBuilder distinct() {
        this.mDistinct = true;
        return this;
    }

    public SupportSQLiteQueryBuilder groupBy(String str) {
        this.mGroupBy = str;
        return this;
    }

    public SupportSQLiteQueryBuilder having(String str) {
        this.mHaving = str;
        return this;
    }

    public SupportSQLiteQueryBuilder limit(String str) {
        if (!isEmpty(str) && !sLimitPattern.matcher(str).matches()) {
            throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("invalid LIMIT clauses:", str));
        }
        this.mLimit = str;
        return this;
    }

    public SupportSQLiteQueryBuilder orderBy(String str) {
        this.mOrderBy = str;
        return this;
    }

    public SupportSQLiteQueryBuilder selection(String str, Object[] objArr) {
        this.mSelection = str;
        this.mBindArgs = objArr;
        return this;
    }
}
