package main.classes.other.opponents;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager implements Serializable {
    private transient ResourceBundle bundle; //not serialize
    private String languageCode;

    /**
     * Constructor to initialize the language manager with a specific language.
     *
     * @param languageCode the language code (e.g., "en" for English, "vi" for Vietnamese)
     */
    public LanguageManager(String languageCode) {
        // Create a Locale object based on the provided language code
        Locale locale = new Locale(languageCode);
        this.languageCode = languageCode;
        // Load the corresponding resource bundle (e.g., messages_en.properties)
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        Locale locale = new Locale(languageCode);
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Translates a given text from the base language (English) to the target language.
     * If the translation does not exist, the original text is returned.
     *
     * @param originalText the text to be translated
     * @return the translated text or the original text if no translation is found
     */
    public String translate(String originalText) {
        // Check if the translation exists; otherwise, return the original text
        return bundle.containsKey(originalText) ? bundle.getString(originalText) : originalText;
    }
}
