//package com.algorithm.unicode;
//
//
//
//import com.groupdocs.conversion.Converter;
//import com.groupdocs.conversion.filetypes.ImageFileType;
//import com.groupdocs.conversion.options.convert.ImageConvertOptions;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//
//public class AIII {
//    public static void main(String[] args) throws IOException {
//// Load the source AI file to be converted
//// Get the convert options ready for the target JPEG format
//
//        File file = new File("D:/111.ai");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        Converter converter = new Converter(fileInputStream);
//        ImageConvertOptions options = new ImageConvertOptions();
//        options.setFormat(ImageFileType.Png);
//        // Convert to JPEG format
//        converter.convert("D:/111.png", options);
//
//    }
//}
