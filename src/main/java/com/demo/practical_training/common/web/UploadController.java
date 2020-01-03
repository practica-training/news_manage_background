package com.demo.practical_training.common.web;

import com.demo.practical_training.common.Const;
import com.demo.practical_training.common.response.UploadResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("upload")
public class UploadController {
    //定义文件上传白名单
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg","image/png","image/jpg");

    @PostMapping("/image")
    public UploadResult uplaod(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
//        File fileDir = UploadUtils.getImgDirFile();
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            return new UploadResult(null,10000,"文件格式不对",false);
        }
        try {
            //检验文件的内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read == null){
                return new UploadResult(null,10000,"文件内容不对",false);
            }
            //3.把浏览器上传的文件复制到希望的位置
            // 构建真实的文件路径
            File newFile = new File(Const.UploadURL+ fileName);
//            System.out.println(newFile.getAbsolutePath());
            file.transferTo(newFile);
            //4.把文件名放在model里，以便后续显示用
            m.addAttribute("fileName", fileName);
            System.out.println(Const.UploadURL+ fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
           return new UploadResult(null,10000,"上传失败",false);
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadResult(null,10000,"上传失败",false);
        }
        return new UploadResult(fileName,10000,"上传成功",true);
    }

    @GetMapping("/getImage")
    //path 为图片在服务器的绝对路径
    public void getImage(HttpServletResponse response, @RequestParam("fileName") String fileName) throws Exception {
        //通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例
        String path = null;
        path = Const.UploadURL+ fileName;
        File file = new File(path);
        //通过打开与实际文件的连接来创建一个文件输入流
        FileInputStream fis;
        fis = new FileInputStream(file);
        //返回由此File表示的文件的长度
        long size = file.length();
        //新建文件的长度字节数组
        byte[] temp = new byte[(int) size];
        //从输入流中读取一些字节数，并将它们存储到字节数组 temp中 。
        fis.read(temp, 0, (int) size);
        //释放系统资源
        fis.close();
        //把temp复制给data
        byte[] data = temp;
        //设置响应给前端的类型
        response.setContentType("image/png");
        //利用response获取一个字节流输出对象
        OutputStream out = response.getOutputStream();
        //将 b.length字节从指定的字节数组写入此输出流。
        out.write(data);
        //写出到请求的地方 刷新此输出流并强制任何缓冲的输出字节被写出。
        out.flush();
        //释放系统资源
        out.close();
    }
}