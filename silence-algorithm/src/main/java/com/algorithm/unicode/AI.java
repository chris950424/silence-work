//package com.algorithm.unicode;
//
//import com.aspose.psd.Image;
//import com.aspose.psd.LoadOptions;
//import com.aspose.psd.fileformats.ai.AiImage;
//import com.aspose.psd.fileformats.ai.AiLayerSection;
//import com.aspose.psd.fileformats.png.PngColorType;
//import com.aspose.psd.fileformats.psd.PsdImage;
//import com.aspose.psd.imageloadoptions.PsdLoadOptions;
//import com.aspose.psd.imageoptions.PngOptions;
//
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//
//
//public class AI {
//
//    public static void main(String[] args) throws IOException {
//        String sourceFileName = "D:/timeline-psd.psd";
//        String outFileName = "D:/abc.png";
//
//        //打开FileChannel
//        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\gwt.txt","rw");
//        FileChannel channel = randomAccessFile.getChannel();
//        //创建buffer对象
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        channel.read(buffer);
//
//        File file = new File("D:/111.ai");
//        FileInputStream fileInputStream = new FileInputStream(file);
//
//
//
//
//        // AiImage就是将目标文件转为指定文件的类.
//        // load()方法可以加载的是路径也可以是流.
////        PsdLoadOptions psdLoadOptions = new PsdLoadOptions();
////        psdLoadOptions.setLoadEffectsResource(true);
////        PsdImage image = (PsdImage) Image.load(sourceFileName);
//        System.out.println("开始读取流");
//
//
//        LoadOptions l = new LoadOptions();
//        l.setBufferSizeHint(1024);
//        Image image = AiImage.load(fileInputStream,l);
////        AiImage image = (AiImage) Image.load(fileInputStream);
////        AiImage image = (AiImage) Image.load(sourceFileName);
//        System.out.println("读取成功");
//        // 设置一些转png的格式参数(比如图片宽高)
//        PngOptions options = new PngOptions();
//        options.setColorType(PngColorType.TruecolorWithAlpha);
//        // save()方法是保存图片.
//        image.save(outFileName, options);
//        System.out.println("转入成功");
//    }
//}
