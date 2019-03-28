package sa.zad.pre_work_g10;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Utils {

    public static boolean isNull(final @Nullable Object object) {
        return object == null;
    }

    public static boolean isNotNull(final @Nullable Object object) {
        return object != null;
    }

    @NonNull
    public static <T> T coalesce(final @Nullable T value, final @NonNull T theDefault) {
        if (value != null) {
            return value;
        }
        return theDefault;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }


    public static String trimEnd(String value) {
        // Use replaceFirst to remove trailing spaces.
        return value.replaceFirst("\\s+$", "");
    }

    public static String trimStart(String value) {
        // Remove leading spaces.
        return value.replaceFirst("^\\s+", "");
    }

    public static String stripTrailingLeadingNewLines(String text) {
        return trimEnd(trimStart(text));
    }
}
