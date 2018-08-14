package com.acfun.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件上传
 */
public class FileUploadUtils {


    public static String  uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        String pathas=filePath+fileName;
        FileOutputStream out = new FileOutputStream(pathas);

        out.write(file);
        out.flush();
        out.close();
        return pathas;
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param path          路径
     * @param filename      名称
     * @return 结果
     * @throws IOException 异常
     */
    public static String uploadFile(MultipartFile multipartFile, String path, String filename) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        BufferedOutputStream buffStream =
                new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + filename)));
        byte[] bytes = multipartFile.getBytes();
        buffStream.write(bytes);
        buffStream.close();

//        BufferedInputStream fileInputStream = (BufferedInputStream) multipartFile.getInputStream();
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + filename));
//        byte[] bs = new byte[1024];
//        int len;
//        while ((len = fileInputStream.read(bs)) != -1) {
//            bos.write(bs, 0, len);
//        }
//        bos.flush();
//        bos.close();
        return filename;
    }

    /**
     * Java文件操作 获取文件扩展名
     * <p>
     * Created on: 2011-8-2
     * Author: blueeagle
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     *  获取文件后缀名
     * @param filename 文件名
     * @return
     */
    public static String getEndName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot,filename.length());
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     * <p>
     * Created on: 2011-8-2
     * Author: blueeagle
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;

    }
}