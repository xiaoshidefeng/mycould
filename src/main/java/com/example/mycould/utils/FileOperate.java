package com.example.mycould.utils;

import com.example.mycould.Const.UtilConst;

import java.text.DecimalFormat;

/**
 * 文件信息维护类
 */

public class FileOperate {

    DecimalFormat fnum   =   new   DecimalFormat("##0.00");

    /**
     * 转换文件大小信息
     * 保留两位小数
     * @param b
     * @return
     */
    public String formatByte(Long b) {
        if (b >= UtilConst.GB_SIZE) {
            return byteToGB(b);
        } else if (b >= UtilConst.MB_SIZE) {
            return byteToMB(b);
        } else if (b >= UtilConst.TO_KB_SIZE) {
            return byteToKB(b);
        }
        return b.toString() + "B";
    }
    public String byteToGB(Long b) {
        float f = b;
        return fnum.format(f / UtilConst.GB_SIZE).toString() + "GB";
    }

    public String byteToMB(Long b) {
        float f = b;
        return fnum.format(f / UtilConst.MB_SIZE).toString() + "MB";
    }

    public String byteToKB(Long b) {
        float f = b;
        return fnum.format(f / UtilConst.TO_KB_SIZE).toString() + "KB";
    }

}
