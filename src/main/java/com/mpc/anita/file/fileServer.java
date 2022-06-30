package com.mpc.anita.file;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Description:
 * @Auther: mapengcheng
 * @Date: 2022/06/30/3:23 PM
 */
public final class fileServer extends ServletUtil {
    private fileServer(){
    }

    /**
     * @author mapengcheng
     * @Description("文件下载")
     * @Date 6/30/2022 3:43 PM
     * @param response
     * @param in
     * @param fileName 下载后的文件名
     * @return void
     **/
    public static void download(HttpServletResponse response, InputStream in,String fileName) {
        write(response, in, "application/octet-stream;charset=UTF-8", fileName);
    }

    public static void write(HttpServletResponse response, InputStream in, String contentType, String fileName) {
        //直接强制使用utf-8
        String charset = "UTF-8";//(String)ObjectUtil.defaultIfNull(resp.getCharacterEncoding(), "UTF-8");
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", StrUtil.format("attachment;filename={}", new Object[]{URLUtil.encode(fileName, charset)}));
        write(response, in);
    }

}
