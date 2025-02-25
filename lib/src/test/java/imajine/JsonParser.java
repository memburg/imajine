package imajine;

public class JsonParser {
    public static boolean isValidJson(String json) {
        json = json.trim();
        if (json.isEmpty())
            return false;
        return isValidObject(json) || isValidArray(json);
    }

    private static boolean isValidObject(String json) {
        if (!json.startsWith("{") || !json.endsWith("}"))
            return false;
        json = json.substring(1, json.length() - 1).trim();
        if (json.isEmpty())
            return true;
        String[] pairs = json.split(",(?=\"|\s*\\w)");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2)
                return false;
            if (!isValidString(keyValue[0].trim()))
                return false;
            if (!isValidValue(keyValue[1].trim()))
                return false;
        }
        return true;
    }

    private static boolean isValidArray(String json) {
        if (!json.startsWith("[") || !json.endsWith("]"))
            return false;
        json = json.substring(1, json.length() - 1).trim();
        if (json.isEmpty())
            return true;
        String[] values = json.split(",(?=\"|\s*\\w)");
        for (String value : values) {
            if (!isValidValue(value.trim()))
                return false;
        }
        return true;
    }

    private static boolean isValidValue(String value) {
        return isValidString(value) || isValidNumber(value) || isValidBoolean(value) || "null".equals(value)
                || isValidObject(value) || isValidArray(value);
    }

    private static boolean isValidString(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    private static boolean isValidNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidBoolean(String value) {
        return "true".equals(value) || "false".equals(value);
    }
}
