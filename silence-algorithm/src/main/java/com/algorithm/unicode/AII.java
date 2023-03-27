//package com.algorithm.unicode;
//
//import com.aspose.imaging.Color;
//import com.aspose.imaging.Image;
//import com.aspose.imaging.SmoothingMode;
//import com.aspose.imaging.TextRenderingHint;
//import com.aspose.imaging.fileformats.png.PngColorType;
//import com.aspose.imaging.imageoptions.PngOptions;
//import com.aspose.imaging.imageoptions.VectorRasterizationOptions;
//
//
//public class AII {
//
//    public static void main(String[] args) {
//        String sourceFileName = "D:/0617-10支凝胶-180x165x25mm-最终版转曲.cdr";
//        String outFileName = "D:/SimpleShapes.png";
//        // AiImage就是将目标文件转为指定文件的类.
//        // load()方法可以加载的是路径也可以是流.
//        Image image = Image.load(sourceFileName);
//        System.out.println("读取成功");
//        // 设置一些转png的格式参数(比如图片宽高)
//        PngOptions options = new PngOptions();
//        options.setColorType(PngColorType.TruecolorWithAlpha);
//        VectorRasterizationOptions defaultOptions = (VectorRasterizationOptions) image.getDefaultOptions(new Object[]{Color.getWhite(), image.getWidth(), image.getHeight()});
//        options.setVectorRasterizationOptions(defaultOptions);
//        defaultOptions.setTextRenderingHint(TextRenderingHint.SingleBitPerPixel);
//        defaultOptions.setSmoothingMode(SmoothingMode.None);
//        // save()方法是保存图片.
//        image.save(outFileName, options);
//        System.out.println("转换成功");
//        // 也可以以流的方式保存,需要创建一个OutputStream来接收内容.
//        // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        // image.save(outStream, options);
//    }
//}
