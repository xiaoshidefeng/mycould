package com.example.mycould.service.Impl;

import com.example.mycould.domain.FileFold;
import com.example.mycould.service.FileFoldService;
import com.example.mycould.utils.FoldOperate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFoldServiceImpl implements FileFoldService {


    @Override
    public List<FileFold> getFileFoldList(String path) {
        FoldOperate foldOperate = new FoldOperate();
        List<FileFold> fileFolds = foldOperate.getFileFoldList(path);
        for (FileFold fileFold:
             fileFolds) {
            String pth = fileFold.getPath();
            pth.replace("file:///", "");
            pth = pth.substring(3, pth.length());
            fileFold.setPath(pth);
        }

        return fileFolds;
    }


}
