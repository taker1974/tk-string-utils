package ru.spb.tksoft.utils.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * {@code List<String>} converter.
 * 
 * @author Konstantin Terskikh, kostus.online.1974@yandex.ru, 2025
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    /** Default delimiter string. */
    public static final String SPLIT_STRING = ";";

    /**
     * {@inheritDoc} Convert string list to database column.
     * 
     * @param stringList List of strings to be converted to database column.
     * @return Converted delimited string.
     */
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {

        if (stringList == null) {
            return "";
        }
        return String.join(SPLIT_STRING, stringList);
    }

    /**
     * {@inheritDoc} Convert database column to string list.
     * 
     * @param string Delimited string to be converted to string list.
     * @return Converted string list.
     */
    @Override
    public List<String> convertToEntityAttribute(String string) {

        if (string == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(string.split(SPLIT_STRING));
    }
}
