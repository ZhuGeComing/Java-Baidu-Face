package com.face;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;

/**
 * @author Henry
 * @date 2019/12/16
 */

/**
 * 人脸检测与属性分析
 */
public class FaceDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceDetect() throws Exception {
        // 请求url
//        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        String url = "https://aip.baidubce.com/rest/2.0/face/v2";
        String Filepath = "F:/ttt.jpg";
        String image = Base64Util.encode(FileUtil.readFileByBytes(Filepath));
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", "027d8308a2ec665acb1bdf63e513bcb9");
            map.put("face_field", "faceshape,facetype");
            map.put("image_type", "FACE_TOKEN");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "25.69147779e9dc916b6fe91a593288cc83.315360000.1891872901.282335-18040424";
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String match(String face_token) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
//            byte[] bytes1 = FileUtil.readFileByBytes(Environment.getExternalStorageDirectory() + "/DCIM/Camera/"
//                    + "temp" + ".jpg");
//            byte[] bytes2 = FileUtil.readFileByBytes("【本地文件2地址】");
            byte[] temp = {123,123};
            String image1 = Base64Util.encode(temp);
//            String image2 = Base64Util.encode(bytes2);
            String image2 = face_token;

            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "HIGH");
            map1.put("liveness_control", "HIGH");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "FACE_TOKEN");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "HIGH");
            map2.put("liveness_control", "HIGH");


            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String dett() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v2/detect";
        try {
            // 本地文件路径
            String filePath = "D:\\FOLDER\\桌面\\2019-12-14 汇报\\comface\\src\\main\\java\\com\\face\\IMG_20181004_151203.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            // String filePath2 = "E:/workSpace/face/src/main/resources/1.JPG";
            // byte[] imgData2 = FileUtil.readFileByBytes(filePath2);
            // String imgStr2 = Base64Util.encode(imgData2);
            // String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");

            String param = "images=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间，
            // 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
//        FaceDetect.match("123");
//        FaceDetect.faceDetect();
        FaceDetect.dett();
    }
}