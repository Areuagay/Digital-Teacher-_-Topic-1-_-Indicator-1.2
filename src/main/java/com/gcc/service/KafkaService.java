package com.gcc.service;

import com.alibaba.fastjson.JSON;
import com.gcc.common.DateUtils;
import com.gcc.mapper.DwiLearningActivitysMapper;
import com.gcc.mapper.DwiPlaybackRecordVideoResourcesMapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.pojo.DwiLearningActivitys;
import com.gcc.pojo.DwiPlaybackRecordVideoResources;
import com.gcc.pojo.DwiUserVisit;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消费kafka
 */
@Slf4j
@ConditionalOnExpression("${kafka.enabled:true}")
@Service
public class KafkaService {

    @Autowired
    DwiLearningActivitysMapper dwiLearningActivitysMapper;

    @Autowired
    DwiPlaybackRecordVideoResourcesMapper playbackRecordVideoResourcesMapper;

    @Autowired
    DwiUserVisitMapper userVisitMapper;

    /**
     * 手动提交批量消费
     *
     * @param list     (list 的大小和 batch-size（单位） 有关)
     * @param consumer
     */
    @KafkaListener(topics = "user_visit", groupId = "${spring.kafka.consumer.group-id}")
    public void user_visit(List<String> list,
                           @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                           @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                           @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                           @Header(KafkaHeaders.OFFSET) List<Long> offsets, Consumer consumer) {
        try {
            List<DwiUserVisit> list1 = new ArrayList<>();
            list.stream().forEach(t -> {
                DwiUserVisit resources = JSON.parseObject(t, DwiUserVisit.class);
                resources.setDay(fomartTime(resources.getCreatedAt()));
                list1.add(resources);
            });
            consumer.commitAsync();
            batchUserVisit(list1);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("user_visit -nta  消费失败");
        } finally {
            consumer.commitSync();
        }
    }


    public static final String FORMAT3 = "yyyy-MM-dd";//常量字符串型


    public static String fomartTime(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT3);
            Date date = format.parse(time);//字符串类型转日期类型

            String str = DateUtils.parseDateToStr(FORMAT3, date);//将date按照FORMAT4格式转化为字符串
            return str;
        } catch (ParseException e) {
            return null;
        }
    }

    @KafkaListener(topics = "learning_activity", groupId = "${spring.kafka.consumer.group-id}")
    public void learning_activity(List<String> list,
                                  @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                  @Header(KafkaHeaders.OFFSET) List<Long> offsets, Consumer consumer) {
        try {
            List<DwiLearningActivitys> list1 = new ArrayList<>();
            list.stream().forEach(t -> {
                log.info("11111{}",t);

                //2023-04-15T06:53:17.640005Z
                DwiLearningActivitys resources = JSON.parseObject(t, DwiLearningActivitys.class);
                System.out.printf(""+resources.getCreatedAt());
                resources.setDay(fomartTime(resources.getCreatedAt()));
                list1.add(resources);
            });
            consumer.commitAsync();
            batchLearningActivity(list1);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("learning_activity -nta  消费失败");
        } finally {
            consumer.commitSync();
        }
    }


    @KafkaListener(topics = "learning_activity", groupId = "${spring.kafka.consumer.group-id}")
    public void online_video(List<String> list,
                             @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                             @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                             @Header(KafkaHeaders.OFFSET) List<Long> offsets, Consumer consumer) {
        try {
            List<DwiPlaybackRecordVideoResources> list1 = new ArrayList<>();
            list.stream().forEach(t -> {
                DwiPlaybackRecordVideoResources resources = JSON.parseObject(t, DwiPlaybackRecordVideoResources.class);

                list1.add(resources);
            });
            batchOnlineVideo(list1);
            consumer.commitAsync();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("online_video -nta  消费失败");
        } finally {
            consumer.commitSync();
        }
    }

    /**
     * @param list
     */
    public void batchOnlineVideo(List<DwiPlaybackRecordVideoResources> list) {
            int result = playbackRecordVideoResourcesMapper.batchInsert(list);
        log.info("" + result);
    }


    /**
     * @param list
     */
    public void batchLearningActivity(List<DwiLearningActivitys> list) {
        int result = dwiLearningActivitysMapper.batchInsert(list);
        log.info("" + result);
    }

    /**
     * @param list
     */
    public void batchUserVisit(List<DwiUserVisit> list) {
        int result = userVisitMapper.batchInsert(list);
        log.info("" + result);
    }

}