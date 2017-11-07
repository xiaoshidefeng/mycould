package com.example.mycould.utils;

import com.example.mycould.domain.FileFold;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FoldOperate {

    private FileOperate fileOperate = new FileOperate();

    List<List<FileFold>> fileList = new ArrayList<>();

    private List<FileFold> fileFoldList = new ArrayList<>();

    public List<FileFold> getFileFoldList(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();

            if (files == null) {
                return fileFoldList;
            }
            for (File f:
                 files) {
                FileFold fileFold = new FileFold();
                if (f.isDirectory()) {
                    fileFold.setFile(false);
                } else {
                    fileFold.setFile(true);
                }
                fileFold.setPath(f.getAbsolutePath());
//                System.out.println(fileFold.getPath());
                fileFoldList.add(fileFold);
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileFoldList;
    }

    public void traverseByBFS(String path) {
        File initFile = new File(path);
        Queue<File> fileQueue = new LinkedList<>();
        fileQueue.add(initFile);
        while (!fileQueue.isEmpty()) {
            File file = fileQueue.poll();
            List<FileFold> fileFoldList = new ArrayList<>();
            if (file.exists()) {
                File[] files = file.listFiles();
                if (files == null) {
                    return;
                }

                FileFold fileFold = new FileFold();
                if (files.length == 0) {
                    System.out.println("文件夹是空的!");
                    fileFold.setPath(path);
                    fileFold.setFile(false);
                    return;
                } else {
                    for (File f : files) {

                        fileQueue.add(f);
                        fileFold.setPath(f.getAbsolutePath());
                        fileFold.setFile(true);

                    }
                }
                fileFoldList.add(fileFold);
            } else {
                System.out.println("文件不存在!");
            }
            fileList.add(fileFoldList);
        }

    }


    public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
//            FileFold fileFold = new FileFold();
            if (files == null) {
                return;
            }
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
//                fileFold.setPath(path);
//                fileFold.setFile(false);

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
