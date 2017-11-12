package com.example.mycould.service.Impl;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.FoldOperate;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileFoldServiceImpl implements FileFoldService {

    @Value("${config.me.fileroot}")
    public String fileRoot;

    @Override
    public List<FileFold> getFileFoldList(String path) {
        FoldOperate foldOperate = new FoldOperate();
        List<FileFold> fileFolds = foldOperate.getFileFoldList(path);
        for (FileFold fileFold:
             fileFolds) {
            String pth = fileFold.getPath();
            pth = pth.replace("\\","/");
            pth = pth.replace(fileRoot, "");
            System.out.println(pth);
            fileFold.setPath(pth);
            fileFold.setFileType(getFileType(pth));
        }
        System.out.println("--------------------------------");
        return fileFolds;
    }

    /**
     * 根据文件后缀名 设置文件类型
     * @param pth
     * @return
     */
    private String getFileType(String pth) {
        Boolean isVideo = pth.contains(".mp4") || pth.contains(".mkv")
                || pth.contains(".rmvb") || pth.contains(".avi") || pth.contains(".mpeg")
                || pth.contains(".wmv");
        Boolean isImage = pth.contains(".png") || pth.contains(".jpg")
                || pth.contains(".jpeg") || pth.contains(".bmp") || pth.contains(".gif");
        Boolean isText = pth.contains(".txt") || pth.contains(".doc")
                || pth.contains(".docx") || pth.contains(".pdf") || pth.contains(".ppt")
                || pth.contains(".xls");
        Boolean isDoFile = pth.contains(".exe") || pth.contains(".bat")
                || pth.contains(".sh");
        Boolean isMusic = pth.contains(".mp3") || pth.contains(".wma")
                || pth.contains(".wav") || pth.contains(".flac");
        if (isVideo) {
            return "video";
        } else if (isImage) {
            return "image";
        } else if (isText) {
            return "text";
        } else if (isDoFile) {
            return "doFile";
        } else if (isMusic) {
            return "music";
        } else {
            return "file";
        }
    }

    @Override
    public String fileService(String path) {

        return null;
    }

    @Override
    public String downFile(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");
        FileInputStream fis = null;
        String path = request.getServletPath();
        if (path.length() < 10) {
            path += "/";
        }
        path = path.substring(10, path.length());

        if (path.contains(".mp4") || path.contains(".avi") || path.contains("rmvb")) {
            path = path.replace(fileRoot, "");
            System.out.println(path);
            model.addAttribute("path", "../../../../../../../../../../../../../../../../" + path);
            return "play";

        } else if (path.contains(".png")) {
            path = path.replace(fileRoot, "");
            System.out.println(path);
            model.addAttribute("path", "/" + path);
            return "showImg";
        }
        try {
            File file = new File(fileRoot + path);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20"));
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
