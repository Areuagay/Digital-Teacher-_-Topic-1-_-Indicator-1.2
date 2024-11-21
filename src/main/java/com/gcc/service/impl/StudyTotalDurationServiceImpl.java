package com.gcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcc.common.AsyncJob;
import com.gcc.mapper.StudentCourseRelationshipMapper;
import com.gcc.mapper.StudyDurationMapper;
import com.gcc.mapper.StudyTotalDurationMapper;
import com.gcc.pojo.StudyTotalDuration;
import com.gcc.service.StudentCourseRelationshipService;
import com.gcc.service.StudyTotalDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

import static com.zaxxer.hikari.util.ClockSource.currentTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gcc
 * @since 2023-02-06
 */

@Service
public class StudyTotalDurationServiceImpl extends ServiceImpl<StudyTotalDurationMapper, StudyTotalDuration> implements StudyTotalDurationService {


    @Resource
    private StudentCourseRelationshipService service;

    @Autowired
    private AsyncJob async;

//    @Override
//    public List<StudyTotalDuration> getTotalDurationByUserId(String id){
//        return stdm.getTotalDurationByUserId(id);
//    }
    @Resource
    private StudyTotalDurationMapper studyTotalDurationMapper;

    @Override
    public List<StudyTotalDuration> getTotalDurationByUserId(String id) {


        LambdaQueryWrapper<StudyTotalDuration> totalTimeWrapper = new LambdaQueryWrapper<>();
        totalTimeWrapper.eq(StudyTotalDuration::getStudentId, id);
        ArrayList<StudyTotalDuration> studyTotalDurations = new ArrayList<>();
        List<String> course_id = service.getCourseIdByStudentId(id);


        //        List<String> courseIdByStudentId = service.getCourseIdByStudentId(id);

      //        StudyTotalDuration studyTotalDuration = getToTalDuration(courseIdByStudentId, id);
        StudyTotalDuration studyTotalDuration=studyTotalDurationMapper.selectOne(totalTimeWrapper);


        boolean isNull;
        if (studyTotalDuration == null) {
            isNull = true;
            studyTotalDurations.add(new StudyTotalDuration(id, "1", "60"));

        } else {
            isNull = false;
            float totalMinutes = Float.parseFloat(studyTotalDuration.getTotalMinutes());
            if (totalMinutes == 0) {
                studyTotalDuration.setTotalMinutes("60");
                studyTotalDuration.setTotalHours("1");
            }
            studyTotalDurations.add(studyTotalDuration);

        }
        //异步更新总时长数据
        try {
            async.updateTotalDuration(id,course_id,isNull);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyTotalDurations;


//        LambdaQueryWrapper<StudyDuration> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(StudyDuration::getStudentId, id);
//        List<StudyDuration> studyDurations = studyDurationMapper.selectList(lqw);
//
//        if (studyDurations == null) {
//            return new ArrayList<>();
//        }
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
//        }
//
//        StudyTotalDuration studyTotalDuration = new StudyTotalDuration(id, String.valueOf(totalDuration / 3600), String.valueOf(totalDuration / 60));
//        List<StudyTotalDuration> studyTotalDurations = new ArrayList<>();
//        studyTotalDurations.add(studyTotalDuration);
//        // 更新数据库
////        stdm.update(studyTotalDuration, new LambdaQueryWrapper<StudyTotalDuration>().eq(StudyTotalDuration::getStudentId, studyTotalDuration.getStudentId()));
//        return studyTotalDurations;
    }
    @Override
    public StudyTotalDuration getToTalDuration( String student_id){
        List<String> course_id = service.getCourseIdByStudentId(student_id);



        double totalDuration = 0;
        for (String s:course_id
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


    public double getDuration(String course_id,String student_id ) throws Exception {
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

            Map<String, List<String>> map = connection.getHeaderFields();
            int responseCode = connection.getResponseCode();

            if(responseCode==301){
                connection.disconnect();
                String ultimate = RedirectResult(connection.getHeaderField("Location"));
                while(ultimate.equals("500")||ultimate.equals("301")){
                    ultimate = RedirectResult(connection.getHeaderField("Location"));
                }

//                long e = System.currentTimeMillis();
                Map mapType = JSON.parseObject(ultimate.replace("null",""),Map.class);

//                System.out.println(mapType.get("visit_duration"));
//                System.out.println("------->time costs: "+(-s+e)/1000+"s ");


                return Double.parseDouble(mapType.get("visit_duration").toString());





            }else {


                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));

                }
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



    private String RedirectResult(String url)throws Exception{
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
        String line,result=null;
        if (responseCode ==500||responseCode==301){
            return String.valueOf(responseCode);
        }
        while ((line = in.readLine()) != null) {
            System.out.println(line);
                result += line;
        }

        return result;

    }
}


