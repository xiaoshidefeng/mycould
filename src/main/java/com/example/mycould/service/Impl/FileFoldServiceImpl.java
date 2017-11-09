package com.example.mycould.service.Impl;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.FoldOperate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        }
        System.out.println("--------------------------------");
        return fileFolds;
    }

    @Override
    public String fileService(String path) {

        return null;
    }


}
