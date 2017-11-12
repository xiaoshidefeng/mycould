package com.example.mycould.controller;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.FileOperate;
import com.example.mycould.utils.IsNull;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;


@Controller
public class FileController {

    @Autowired
    private FileFoldService fileFoldService;

    @Autowired
    private HttpServletRequest request;

    @Value("${config.me.fileroot}")
    public String fileRoot;


    @RequestMapping(value = "/filePath/**",method = RequestMethod.GET)
    public String getFilePath(Model model) {
        String path = request.getServletPath();
        path = FileOperate.getFormatPath(path, fileRoot);
        model.addAttribute("files", fileFoldService.getFileFoldList(path));
        return "file";
    }

    @RequestMapping(value = "/download/**",method = RequestMethod.GET)
    public String downloadFile(Model model, HttpServletRequest request, HttpServletResponse response) {
        return fileFoldService.downFile(model, request, response);
    }


}
