package androidx.core.app;

import android.app.Person;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Person {
    public static final String ICON_KEY = "icon";
    public static final String IS_BOT_KEY = "isBot";
    public static final String IS_IMPORTANT_KEY = "isImportant";
    public static final String KEY_KEY = "key";
    public static final String NAME_KEY = "name";
    public static final String URI_KEY = "uri";

    @Nullable
    public IconCompat mIcon;
    public boolean mIsBot;
    public boolean mIsImportant;

    @Nullable
    public String mKey;

    @Nullable
    public CharSequence mName;

    @Nullable
    public String mUri;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(22)
    public static class Api22Impl {
        public static Person fromPersistableBundle(PersistableBundle persistableBundle) {
            Builder builder = new Builder();
            builder.mName = persistableBundle.getString("name");
            builder.mUri = persistableBundle.getString("uri");
            builder.mKey = persistableBundle.getString("key");
            builder.mIsBot = persistableBundle.getBoolean(Person.IS_BOT_KEY);
            builder.mIsImportant = persistableBundle.getBoolean(Person.IS_IMPORTANT_KEY);
            return new Person(builder);
        }

        public static PersistableBundle toPersistableBundle(Person person) {
            PersistableBundle persistableBundle = new PersistableBundle();
            CharSequence charSequence = person.mName;
            persistableBundle.putString("name", charSequence != null ? charSequence.toString() : null);
            persistableBundle.putString("uri", person.mUri);
            persistableBundle.putString("key", person.mKey);
            persistableBundle.putBoolean(Person.IS_BOT_KEY, person.mIsBot);
            persistableBundle.putBoolean(Person.IS_IMPORTANT_KEY, person.mIsImportant);
            return persistableBundle;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static Person fromAndroidPerson(android.app.Person person) {
            Builder builder = new Builder();
            builder.mName = person.getName();
            builder.mIcon = person.getIcon() != null ? IconCompat.createFromIcon(person.getIcon()) : null;
            builder.mUri = person.getUri();
            builder.mKey = person.getKey();
            builder.mIsBot = person.isBot();
            builder.mIsImportant = person.isImportant();
            return new Person(builder);
        }

        public static android.app.Person toAndroidPerson(Person person) {
            return new Person.Builder().setName(person.getName()).setIcon(person.getIcon() != null ? person.getIcon().toIcon() : null).setUri(person.getUri()).setKey(person.getKey()).setBot(person.isBot()).setImportant(person.isImportant()).build();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {

        @Nullable
        public IconCompat mIcon;
        public boolean mIsBot;
        public boolean mIsImportant;

        @Nullable
        public String mKey;

        @Nullable
        public CharSequence mName;

        @Nullable
        public String mUri;

        public Builder() {
        }

        @NonNull
        public Person build() {
            return new Person(this);
        }

        @NonNull
        public Builder setBot(boolean z) {
            this.mIsBot = z;
            return this;
        }

        @NonNull
        public Builder setIcon(@Nullable IconCompat iconCompat) {
            this.mIcon = iconCompat;
            return this;
        }

        @NonNull
        public Builder setImportant(boolean z) {
            this.mIsImportant = z;
            return this;
        }

        @NonNull
        public Builder setKey(@Nullable String str) {
            this.mKey = str;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.mName = charSequence;
            return this;
        }

        @NonNull
        public Builder setUri(@Nullable String str) {
            this.mUri = str;
            return this;
        }

        public Builder(Person person) {
            this.mName = person.mName;
            this.mIcon = person.mIcon;
            this.mUri = person.mUri;
            this.mKey = person.mKey;
            this.mIsBot = person.mIsBot;
            this.mIsImportant = person.mIsImportant;
        }
    }

    public Person(Builder builder) {
        this.mName = builder.mName;
        this.mIcon = builder.mIcon;
        this.mUri = builder.mUri;
        this.mKey = builder.mKey;
        this.mIsBot = builder.mIsBot;
        this.mIsImportant = builder.mIsImportant;
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person fromAndroidPerson(@NonNull android.app.Person person) {
        return Api28Impl.fromAndroidPerson(person);
    }

    @NonNull
    public static Person fromBundle(@NonNull Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("icon");
        Builder builder = new Builder();
        builder.mName = bundle.getCharSequence("name");
        builder.mIcon = bundle2 != null ? IconCompat.createFromBundle(bundle2) : null;
        builder.mUri = bundle.getString("uri");
        builder.mKey = bundle.getString("key");
        builder.mIsBot = bundle.getBoolean(IS_BOT_KEY);
        builder.mIsImportant = bundle.getBoolean(IS_IMPORTANT_KEY);
        return new Person(builder);
    }

    @NonNull
    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person fromPersistableBundle(@NonNull PersistableBundle persistableBundle) {
        return Api22Impl.fromPersistableBundle(persistableBundle);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        String key = getKey();
        String key2 = person.getKey();
        return (key == null && key2 == null) ? Objects.equals(Objects.toString(getName()), Objects.toString(person.getName())) && Objects.equals(getUri(), person.getUri()) && Boolean.valueOf(isBot()).equals(Boolean.valueOf(person.isBot())) && Boolean.valueOf(isImportant()).equals(Boolean.valueOf(person.isImportant())) : Objects.equals(key, key2);
    }

    @Nullable
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getKey() {
        return this.mKey;
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    @Nullable
    public String getUri() {
        return this.mUri;
    }

    public int hashCode() {
        String key = getKey();
        return key != null ? key.hashCode() : Objects.hash(getName(), getUri(), Boolean.valueOf(isBot()), Boolean.valueOf(isImportant()));
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public String resolveToLegacyUri() {
        String str = this.mUri;
        if (str != null) {
            return str;
        }
        if (this.mName == null) {
            return "";
        }
        return "name:" + ((Object) this.mName);
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public android.app.Person toAndroidPerson() {
        return Api28Impl.toAndroidPerson(this);
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("name", this.mName);
        IconCompat iconCompat = this.mIcon;
        bundle.putBundle("icon", iconCompat != null ? iconCompat.toBundle() : null);
        bundle.putString("uri", this.mUri);
        bundle.putString("key", this.mKey);
        bundle.putBoolean(IS_BOT_KEY, this.mIsBot);
        bundle.putBoolean(IS_IMPORTANT_KEY, this.mIsImportant);
        return bundle;
    }

    @NonNull
    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PersistableBundle toPersistableBundle() {
        return Api22Impl.toPersistableBundle(this);
    }
}
