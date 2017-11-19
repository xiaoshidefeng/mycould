package com.example.mycould.controller;

import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.FileOperate;
import com.example.mycould.utils.FileUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterIOException;


@Controller
public class FileController {

    private String nowPath = "";

    @Autowired
    private FileFoldService fileFoldService;

    @Autowired
    private HttpServletRequest request;

    @Value("${config.me.fileroot}")
    private String fileRoot;

    @Value("${config.me.host}")
    private String host;

    /**
     * 获取文件列表
     * @param model
     * @param path
     * @return
     */
    @org.jetbrains.annotations.NotNull
    private String getFileListHtml(Model model, String path) {
        model.addAttribute("foldHost", "http://" + host + ":8080/filePath/");
        model.addAttribute("fileHost", "http://" + host + ":8080/download/");
        model.addAttribute("files", fileFoldService.getFileFoldList(path));
        return "file";
    }

    @RequestMapping(value = "/filePath/**",method = RequestMethod.GET)
    public String getFilePath(Model model) {
        String path = request.getServletPath();
        path = FileOperate.getFormatPath(path, fileRoot, 10);
        nowPath = path;
        return getFileListHtml(model, path);
    }



    @RequestMapping(value = "/download/**",method = RequestMethod.GET)
    public String downloadFile(Model model, HttpServletRequest request, HttpServletResponse response) {
        return fileFoldService.downFile(model, request, response);
    }

    //处理文件上传
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, Model model) {
        String fileName = file.getOriginalFilename();
        if (!nowPath.equals(fileRoot)) {
            nowPath = nowPath + "/";
        }
        try {
            FileUtil.uploadFile(file.getBytes(), nowPath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getFileListHtml(model, nowPath);
    }

    //创建文件夹
    @RequestMapping(value="/mkdir", method = RequestMethod.POST)
    public String mkdir(@RequestParam("fold") String fold, Model model) {
        if (!nowPath.equals(fileRoot)) {
            nowPath = nowPath + "/";
        }
        try {
            FileUtil.makeDir(nowPath + fold);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return getFileListHtml(model, nowPath);
    }
}
