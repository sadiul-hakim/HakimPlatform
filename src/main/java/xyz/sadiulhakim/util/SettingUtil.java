package xyz.sadiulhakim.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.sadiulhakim.file.FileAccessor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SettingUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String ACCESS_PIN = "accessPin";
    public static final String PROPERTIES = "properties";
    public static final String SETTING_FILE_PATH = "app/setting.json";

    private SettingUtil() {
    }

    public static Map<String, Object> getSetting() {
        File setting = FileAccessor.getFile(SETTING_FILE_PATH);

        try {
            var settingMap = MAPPER.readValue(setting, new TypeReference<Map<String, Object>>() {
            });
            return new ConcurrentHashMap<>(settingMap);
        } catch (Exception ex) {
            return new ConcurrentHashMap<>();
        }
    }

    public void save(Map<String, Object> setting) {

        try {
            String settingText = MAPPER.writeValueAsString(setting);
            FileAccessor.write(FileAccessor.getFile(SETTING_FILE_PATH), settingText);
        } catch (Exception ignore) {
        }
    }
}
