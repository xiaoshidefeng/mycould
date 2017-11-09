package com.example.mycould.controller;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.IsNull;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {

    @Autowired
    private FileFoldService fileFoldService;

    @Autowired
    private HttpServletRequest request;

    @Value("${config.me.fileroot}")
    public String fileRoot;


    @RequestMapping(value = "/filePath/**",method = RequestMethod.GET)
    public String getFilePath(RedirectAttributes attr, Map<String,Object> map, Model model, ModelMap modelMap, ModelAndView modelAndView) {
        String path = request.getServletPath();
        if (path.length() < 10) {
            path += "/";
        }
        path = path.substring(10, path.length());
        if (IsNull.isNull(path)) {
            path = fileRoot;
        } else {
            path = fileRoot + path;
        }

        if (path.contains(".mp4")) {
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
        List<FileFold> fileFolds = fileFoldService.getFileFoldList(path);
        model.addAttribute("files", fileFolds);
        return "file";
    }

    @RequestMapping(value = "/download/**",method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");
        FileInputStream fis = null;
        String path = request.getServletPath();
        if (path.length() < 10) {
            path += "/";
        }
        path = path.substring(10, path.length());
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
    }

}
