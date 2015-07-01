package com.myorg.commonapp.utils;

import java.io.*;

/**
 * Created by huyan on 2015/6/30.
 * 文件工具类
 */
public class FileOperationUtils {

    /**
     * 删除文件或者文件夹
     * @param filePath 文件路径
     * @return 是否成功
     */
    public static boolean deleteFolder(String filePath){

        if (filePath == null){
            return false;
        }
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        }

        if (file.isFile()) {
            return deleteFile(filePath);
        } else {
            return deleteDirectory(filePath);
        }


    }


    /**
     * 删除文件
     */
    public static boolean deleteFile(String filePath) {

        if (filePath == null){
            return false;
        }
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        return file.isFile() && file.exists()&& file.delete();

    }

    /**
     * 删除目录以及目录下面的文件
     */
    public static boolean deleteDirectory(String filePath) {

        boolean flag;

        if (filePath == null){
            return false;
        }

        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null){
            for (File file : files){
                //删除子文件
                if (file.isFile()) {
                    flag = deleteFile(file.getAbsolutePath());
                    if (!flag) break;
                } else {//删除子目录
                    flag = deleteDirectory(file.getAbsolutePath());
                    if (!flag) break;
                }
            }
        }

        if (!flag) return false;

        //最后删除当前目录
        return dirFile.delete();

    }

    /**
     * 拷贝文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws java.io.IOException
     */
    public static boolean copyFile(File sourceFile, File targetFile) throws IOException{

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] bufferBytes = new byte[1024 * 5];
            int len;
            while ( (len = inputStream.read(bufferBytes)) != -1) {
                outputStream.write(bufferBytes, 0, len);
            }
            // 刷新此缓冲的输出流
            outputStream.flush();
            return true;

        }  finally {

            if (inputStream != null){
                inputStream.close();
            }
            if (outputStream != null){
                outputStream.close();
            }
        }

    }

    /**
     * 复制文件夹
     * @param sourceDir 原始文件夹
     * @param targetDir 目标文件夹
     * @throws java.io.IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {

        // 新建目标目录
        if ( !new File(targetDir).mkdirs() ){
            return;
        }

        // 获取源文件夹当前下的文件或目录
        File[] sourceFileList = new File(sourceDir).listFiles();
        if (sourceFileList == null){
            return;
        }
        for (File sourceFile : sourceFileList){
            if (sourceFile.isFile()) {
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + sourceFile.getName());
                copyFile(sourceFile, targetFile);
            }
            if (sourceFile.isDirectory()) {
                // 准备复制的源文件夹
                String prepareSourceDir = sourceDir + File.separator + sourceFile.getName();
                // 准备复制的目标文件夹
                String prepareTargetDir = targetDir + File.separator + sourceFile.getName();
                copyDirectiory(prepareSourceDir, prepareTargetDir);
            }
        }

    }


}
