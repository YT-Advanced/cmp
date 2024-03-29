package app.revanced.integrations.youtube.utils;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class StringRef {
    /**
     * Shorthand for <code>constant("")</code>
     * Its value always resolves to empty string
     */
    @NonNull
    public static final StringRef empty = constant("");
    private static final HashMap<String, StringRef> strings = new HashMap<>();
    @NonNull
    private String value;
    private boolean resolved;


    public StringRef(@NonNull String resName) {
        this.value = resName;
    }

    /**
     * Gets strings reference from shared collection or creates if not exists yet,
     * this method should be called if you want to get StringRef
     *
     * @param id string resource name/id
     * @return String reference that'll resolve to excepted string, may be from cache
     */
    @NonNull
    public static StringRef sf(@NonNull String id) {
        StringRef ref = strings.get(id);
        if (ref == null) {
            ref = new StringRef(id);
            strings.put(id, ref);
        }
        return ref;
    }

    /**
     * Gets string value by string id, shorthand for <code>sf(id).toString()</code>
     *
     * @param id string resource name/id
     * @return String value from string.xml
     */
    @NonNull
    public static String str(@NonNull String id) {
        return sf(id).toString();
    }

    /**
     * Gets string value by string id, shorthand for <code>sf(id).toString()</code> and formats the string
     * with given args.
     *
     * @param id   string resource name/id
     * @param args the args to format the string with
     * @return String value from string.xml formatted with given args
     */
    @NonNull
    public static String str(@NonNull String id, Object... args) {
        return String.format(str(id), args);
    }

    /**
     * Creates a StringRef object that'll not change it's value
     *
     * @param value value which toString() method returns when invoked on returned object
     * @return Unique StringRef instance, its value will never change
     */
    @NonNull
    public static StringRef constant(@NonNull String value) {
        final StringRef ref = new StringRef(value);
        ref.resolved = true;
        return ref;
    }

    @Override
    @NonNull
    public String toString() {
        if (!resolved) {
            resolved = true;
            value = ResourceUtils.string(value);
        }
        return value;
    }
}