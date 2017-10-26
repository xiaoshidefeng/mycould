package com.example.mycould.util;

import java.io.File;

public class FoldOperate {

    private FileOperate fileOperate = new FileOperate();

    public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath() + " 大 小 = " + fileOperate.formatByte(file2.length()));
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
