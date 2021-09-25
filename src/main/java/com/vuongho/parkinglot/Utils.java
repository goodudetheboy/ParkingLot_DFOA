package com.vuongho.parkinglot;

import java.io.InputStream;

public class Utils {
    /**
     * Gets an {@link InputStream} of a file from ./resource folder
     * 
     * @param fileName a file name in the ./resource folder
     * @return an {@link InputStream} of a file from ./resource folder
     * @author mykong, https://mkyong.com/java/java-read-a-file-from-resources-folder/
     */
    public static InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = Utils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
