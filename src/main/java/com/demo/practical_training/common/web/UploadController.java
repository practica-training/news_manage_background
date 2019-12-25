package com.demo.practical_training.common.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("upload")
public class UploadController {
    //定义文件上传白名单
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg","image/gif");

    @PostMapping("/image")
    public String uplaod(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File fileDir = UploadUtils.getImgDirFile();
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            return null;
        }
        try {
            //检验文件的内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read == null){
                return null;
            }
            //3.把浏览器上传的文件复制到希望的位置
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);
//            System.out.println(newFile.getAbsolutePath());
            file.transferTo(newFile);
            //4.把文件名放在model里，以便后续显示用
            m.addAttribute("fileName", fileName);
//            System.out.println(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }

        return "/static/img/"+fileName;
    }
}