package com.amazon.minerva.client.thirdparty.configuration;

import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DenyListConfiguration {
    public static final MinervaLogger log = new MinervaLogger(MetricsConfigurationConstants.DENYLIST_CONFIGURATION);
    public String mDenyListBits;
    public Set<GroupSchemaPair> mDenylist = new HashSet();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class GroupSchemaPair {
        public final String mGroup;
        public final String mSchema;

        public GroupSchemaPair(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("Group may not be null");
            }
            this.mGroup = str;
            this.mSchema = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            GroupSchemaPair groupSchemaPair = (GroupSchemaPair) obj;
            String str = this.mGroup;
            if (str != null) {
                if (!str.equals(groupSchemaPair.getGroupId())) {
                    return false;
                }
            } else if (groupSchemaPair.getGroupId() != null) {
                return false;
            }
            String str2 = this.mSchema;
            String schemaId = groupSchemaPair.getSchemaId();
            return str2 != null ? str2.equals(schemaId) : schemaId == null;
        }

        public String getGroupId() {
            return this.mGroup;
        }

        public String getSchemaId() {
            return this.mSchema;
        }

        public int hashCode() {
            String str = this.mGroup;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mSchema;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }
    }

    public DenyListConfiguration(JSONArray jSONArray, String str, int i) {
        log.info("DenyListConfiguration : Syncing from Remote configuration Denylist JSON");
        if (isValidInput(str, i)) {
            this.mDenyListBits = str;
            updateDenylist(jSONArray);
        }
    }

    public boolean isDenylisted(String str, String str2) {
        if (str != null) {
            return this.mDenylist.contains(new GroupSchemaPair(str, null)) || this.mDenylist.contains(new GroupSchemaPair(str, str2));
        }
        log.error(String.format(Locale.US, "groupID can't be null. group: %s; Schema: %s", str, str2));
        return true;
    }

    public final boolean isValidInput(String str, int i) {
        if (str != null && str.length() == i) {
            return true;
        }
        log.error(String.format("Invalid denyListBits: %s.", str));
        return false;
    }

    public void updateDenylist(JSONArray jSONArray) {
        this.mDenylist.clear();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    String strOptString = jSONObjectOptJSONObject.optString("metricGroupId", null);
                    String strOptString2 = jSONObjectOptJSONObject.optString("schemaId", null);
                    if (strOptString != null) {
                        this.mDenylist.add(new GroupSchemaPair(strOptString, strOptString2));
                        log.debug(String.format(Locale.US, "The GroupID = %s SchemaID = %s has been added for blocklisting", strOptString, strOptString2));
                    } else {
                        log.error(String.format(Locale.US, "groupID can't be null. group: %s", strOptString));
                    }
                }
            }
        }
    }
}
