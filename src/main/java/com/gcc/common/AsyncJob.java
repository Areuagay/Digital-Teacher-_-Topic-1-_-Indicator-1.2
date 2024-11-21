package com.gcc.common;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.StudentCourseRelationshipMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.mapper.StudyTotalDurationMapper;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.pojo.StudyDuration;
import com.gcc.pojo.StudyTotalDuration;
import com.gcc.service.StudentCourseRelationshipService;
import com.gcc.service.StudyTotalDurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
public class AsyncJob {
    @Resource
    private StudyDurationMapper studyDurationMapper;

    @Resource
    private StudyTotalDurationMapper studyTotalDurationMapper;

    @Resource
    private StudentCourseRelationshipMapper studentCourseRelationshipMapper;
    @Async(value = "123")
        public void updateTotalDuration(String id,List<String> course,boolean isNull) throws Exception {
        long beginTime = System.currentTimeMillis();
//
//        LambdaQueryWrapper<StudyDuration> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(StudyDuration::getStudentId, id);
//        List<StudyDuration> studyDurations = studyDurationMapper.selectList(lqw);
//
////        if (studyDurations == null) {
////
////        }
//
//        // 总时长
//        double totalDuration = 0;
//
//        for (StudyDuration studyDuration : studyDurations) {
//            double videoWatchScore = Double.parseDouble(studyDuration.getVideoWatchScore());
//            double fileWatchScore = Double.parseDouble(studyDuration.getFileWatchScore());
//            double testTimeScore = Double.parseDouble(studyDuration.getTestTimeScore());
//            double discussionScore = Double.parseDouble(studyDuration.getDiscussionScore());
//            double examinationTimeScore = Double.parseDouble(studyDuration.getExaminationTimeScore());
//            totalDuration += videoWatchScore;
//            totalDuration += fileWatchScore;
//            totalDuration += testTimeScore;
//            totalDuration += discussionScore;
//            totalDuration += examinationTimeScore;
//
//        }
//        List<StudentCourseRelationship> course = studentCourseRelationshipMapper.selectList(new LambdaQueryWrapper<StudentCourseRelationship>().eq(StudentCourseRelationship::getStudentId,id));

//        StudyTotalDuration studyTotalDuration = getToTalDuration(id,course);
//        StudyTotalDuration studyTotalDuration = new StudyTotalDuration(id, String.valueOf(Math.floor(totalDuration / 3600)), String.valueOf(totalDuration / 60));


        double totalDuration = 0;
        for (String s:course
        ) {
            try {
                totalDuration += getDuration(s, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        StudyTotalDuration studyTotalDuration;
        if(totalDuration-0>0.01)
             studyTotalDuration=new StudyTotalDuration(id,"0",String.valueOf((int)(totalDuration/60)));
        else  studyTotalDuration=new StudyTotalDuration(id,"0",String.valueOf(60));



        if(!isNull) {
            studyTotalDurationMapper.update(studyTotalDuration, new LambdaQueryWrapper<StudyTotalDuration>().eq(StudyTotalDuration::getStudentId, id));
            System.out.println("----async update------\n\n");
        }else{
            studyTotalDurationMapper.insert(studyTotalDuration);
            System.out.println("----async loaded------\n\n");

        }
        System.out.println("/n/n/n"+studyTotalDuration);
        long endTime = System.currentTimeMillis();
        log.info("update cost{} ms + {}",beginTime-endTime,studyTotalDuration);
    }

//    @Async
//    public void job1() throws InterruptedException {
//        long beginTime = System.currentTimeMillis();
//        Thread.sleep(2000);
//        long endTime = System.currentTimeMillis();
//        log.info("job1 cost {} ms", endTime - beginTime);
//    }
//
//    @Async
//    public void job2() throws InterruptedException {
//        long beginTime = System.currentTimeMillis();
//        Thread.sleep(2000);
//        long endTime = System.currentTimeMillis();
//        log.info("job2 cost {} ms", endTime - beginTime);
//    }

    private StudyTotalDuration getToTalDuration( String student_id,List<String> course)throws Exception{




        double totalDuration = 0;
        for (String s:course
        ) {
            try {
                totalDuration += getDuration(s, student_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(totalDuration-0>0.01)
            return new StudyTotalDuration(student_id,"0",String.valueOf((int)(totalDuration/60)));
        else return new StudyTotalDuration(student_id,"0",String.valueOf(60));
    }


    private double getDuration(String course_id,String student_id ) throws Exception {
        String result =null;
        BufferedReader in = null;
        int timestamp = (int)(System.currentTimeMillis() /1000);
        long s = System.currentTimeMillis();
        String url = "http://lms.ouchn.cn/external-api/v2/courses/"+course_id+"/user/"+student_id+"/metrics?app_key="+app_key+"&ts="
                +timestamp+"&token="+getToken(course_id,student_id,timestamp);
        try {
            String urlNameString = url ;
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            // 建立实际的连接
//            connection.connect();

            // 获取所有响应头字段
            try {
                Map<String, List<String>> map = connection.getHeaderFields();

            int responseCode = connection.getResponseCode();

            if(responseCode==301){
//
                String ultimate = RedirectResult(connection.getHeaderField("Location"));
                while(ultimate.equals("500")||ultimate.equals("301")){
                    ultimate = RedirectResult(connection.getHeaderField("Location"));
                }
                connection.disconnect();
//                long e = System.currentTimeMillis();
                Map mapType = JSON.parseObject(ultimate.replace("null",""),Map.class);

//                System.out.println(mapType.get("visit_duration"));
//                System.out.println("------->time costs: "+(-s+e)/1000+"s ");


                return Double.parseDouble(mapType.get("visit_duration").toString());





            }else {


                // 遍历所有的响应头字段
//                for (String key : map.keySet()) {
//                    System.out.println(key + "--->" + map.get(key));
//
//                }
                System.out.println(responseCode);

                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    result += line;
                }

                Map maps=(Map)JSON.parse(result);


                return Double.parseDouble(maps.get("visit_duration").toString());

            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }


    @Value("${interact.app_key}")
    private String app_key;

    @Value("${interact.secret_key}")
    private String secret_key;

    String getToken(String courseid,String studentid,int timestamp) throws Exception{


        String url_partial = String.format("/external-api/v2/courses/%s/user/%s/metrics?app_key=%s&ts=%s",courseid,studentid, app_key, String.valueOf(timestamp));

        byte[] digest = MessageDigest.getInstance("MD5").digest((url_partial + secret_key).getBytes("UTF-8"));

        return DatatypeConverter.printHexBinary(digest).toLowerCase().substring(0,20);
    }



    private String RedirectResult(String url){
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");

            Map<String, List<String>> map = connection.getHeaderFields();
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            for (String key : map.keySet()) {
                System.out.println(key + "-- D --->" + map.get(key));

            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line, result = null;
            if (responseCode == 500 || responseCode == 301) {
                return String.valueOf(responseCode);
            }
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }
}