package com.gcc.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiStudentCourseMapper;
import com.gcc.mapper.UserWordcloud1Mapper;
import com.gcc.pojo.DwiStudentCourse;
import com.gcc.pojo.UserWordcloud1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yp
 * @date: 2023/10/26
 */
@Component
@Slf4j
public class WordCloudTask {

    @Resource
    private DwiStudentCourseMapper dwiStudentCourseMapper;

    @Resource
    private UserWordcloud1Mapper userWordcloud1Mapper;

    /**
     * 词云更新
     */
    @Scheduled(cron = "${scheduled.onceAWeek}")
    void genWordTest() {
        Map<String, String> wordMap = new HashMap<>();
        wordMap.put("00808", "计算机, 电脑, 计算机系, 电子计算, 计算机技术, 计算机程序, 嵌入式, 电脑系统, 电脑程式, 计算机软件, 鼠标, 滑鼠, 遥控器, 触摸, 桌面, U盘, 快捷键, 触控笔, 触屏, 摇杆, 网络, 互联网, 网路, 网络平台, 网际网路, 因特网, 平台, 计算机网, P2P, 在线视频, 操作系统, 作业系统, 应用程序, Linux, OS, 虚拟机, 软件包, 固件, 应用软件, 内核, 电子邮件, 邮件, 电邮, 邮箱, 信件, 传真, 信函, emai, 简讯, Gmai, 软件, 应用软件, 软体, 应用程序, 软件包, 开发工具, 硬件, 插件, 软件产品, 计算机系, 输入法, 注音, 中文系统, 简体中文, 搜索引擎, 字符集, 应用软件, 繁体字, 五笔, 仓颉, 寄存器, 暂存器, 缓存, 字符串, 内存, 数组, 高速缓存, 初始化, 位元, 堆栈, 多媒体, 影音, 数码, 数位, 视讯, 音视频, 互动式, 多媒体技术, 交互式, 视像, 数据结构, 数据类型, 数组, 正则表达, 计算机, 实例, 程序语言, 数据流, 模板, 示例");
        wordMap.put("00815", "计算机, 电脑, 计算机系, 电子计算, 计算机技术, 计算机程序, 嵌入式, 电脑系统, 电脑程式, 计算机软件, 鼠标, 滑鼠, 遥控器, 触摸, 桌面, U盘, 快捷键, 触控笔, 触屏, 摇杆, 网络, 互联网, 网路, 网络平台, 网际网路, 因特网, 平台, 计算机网, P2P, 在线视频, 操作系统, 作业系统, 应用程序, Linux, OS, 虚拟机, 软件包, 固件, 应用软件, 内核, 电子邮件, 邮件, 电邮, 邮箱, 信件, 传真, 信函, emai, 简讯, Gmai, 软件, 应用软件, 软体, 应用程序, 软件包, 开发工具, 硬件, 插件, 软件产品, 计算机系, 输入法, 注音, 中文系统, 简体中文, 搜索引擎, 字符集, 应用软件, 繁体字, 五笔, 仓颉, 寄存器, 暂存器, 缓存, 字符串, 内存, 数组, 高速缓存, 初始化, 位元, 堆栈, 多媒体, 影音, 数码, 数位, 视讯, 音视频, 互动式, 多媒体技术, 交互式, 视像, 数据结构, 数据类型, 数组, 正则表达, 计算机, 实例, 程序语言, 数据流, 模板, 示例");
        wordMap.put("04848", "人工智能, 人工智慧, AI, 机器人, 认知科学, 机器翻译, 人工神经, 神经科学, 计算机系, 纳米技术, 机器人, 机器, 人型, 人工智能, 机械, 人工智慧, 仿生, 虚拟现实, AI, 虚拟实境, 神经, 脊髓, 神经系统, 小脑, 神经细胞, 脑部, 神经节, 迷走神经, 神经纤维, 脑神经, 概率, 机率, 可能性, 几率, 幅度, 波动性, 振幅, 波动率, 期望值, 波幅, 语音, 音频, 交互, 自然语言, 人机交互, 界面, 声控, 离线, 话音, 图形化, 机器, 电脑, 机器人, 计算机, 机械, 微电脑, 电脑系统, 电脑程式, 印刷机, 应用软体, 博弈论, 计量经济, 概率论, 可计算性, 赛局, 微观经济, 数理逻辑, 统计力学, 分析方法, 逻辑学, 自然语言, 语义, 语法, 神经网络, 计算机程, 机器翻译, 用例, 形式语言, 正则表达, 程序语言, 智慧, 灵性, 睿智, 才智, 创造力, 创造性, 面向未来, 现代科技, 意念, 智识, 视觉, 听觉, 感官, 视觉效果, 美感, 影像, 光影, 图像, 美学, 触觉");
        wordMap.put("02379", "植物, 真菌, 药用植物, 豆科植物, 昆虫, 植物种子, 菌类, 木本植物, 花粉, 苔藓, 光合作用, 呼吸作用, 叶绿体, 微生物, 叶绿素, 浮游植物, 有性生殖, 花粉, 厌氧, 营养物, 叶绿体, 细胞核, 细胞器, 细胞质, 真核细胞, 动物细胞, 线粒体, 细胞壁, 原核, 叶绿素, 矿物质, 微量元素, 营养物质, 营养素, 营养元素, 钙质, 钙, 糖分, 碳水化合物, 铁质, 灌木, 乔木, 草本植物, 多年生, 常绿, 一年生, 小乔木, 草本, 落叶, 落叶乔木, 线粒体, 细胞核, 细胞质, 真核细胞, 核糖体, 细胞器, 核酸, 糖蛋白, 上皮细胞, 脂质, 化肥, 肥料, 饲料, 氮肥, 化工产品, 磷肥, 棉花, 农用, 造纸, 纸浆, 生长, 繁殖, 发育, 生存, 发芽, 土壤, 繁衍, 生长发育, 植株, 栽种, 蒸腾作用, 氮素, 湿气, 浮游植物, 呼吸作用, 皱胃, 湍流, 光呼吸, 营养物, 生物群落, 种植, 栽种, 栽植, 养殖, 耕作, 作物, 种植业, 造林, 耕种, 果树");
        wordMap.put("01776", "医学, 药理学, 病理学, 生物学, 生物化学, 临床医学, 药学, 营养学, 生物医学, 免疫学, 免疫, 免疫系统, 抗体, 胰岛素, 甲状腺, 特异性, 干扰素, 抗病毒, 胰岛, 自体, 疾病, 病症, 并发症, 传染病, 慢性病, 癌症, 糖尿病, 该病, 慢性, 妇科疾病, 细胞, 细胞核, 生物体, 蛋白, 蛋白质, 线粒体, 神经细胞, 肝细胞, 神经元, 体细胞, 器官, 肾脏, 人体器官, 脏器, 干细胞, 骨髓, 脑部, 肝脏, 肿瘤, 内脏, 球蛋白, 干扰素, 核糖体, 生长因子, 多肽, 脂质, 淋巴细胞, IgG, 核酸, 胸腺, 微生物, 细菌, 生物, 菌种, 酵母, 病原体, 有机物, 寄生虫, 大肠杆菌, 酵母菌, 细菌, 病菌, 微生物, 病原体, 霉菌, 寄生虫, 真菌, 毒素, 病原, 化学物质, 流感, 禽流感, H1N1, 登革热, 霍乱, 流行病, 鼠疫, 肺炎, 流行性, 甲型, 临床, 医学, 病理, 临床试验, 临床实验, 放射治疗, 临床医学, 流行病学, 病理学, 药理学");
        wordMap.put("02473", "医学, 药理学, 病理学, 生物学, 生物化学, 临床医学, 药学, 营养学, 生物医学, 免疫学, 免疫, 免疫系统, 抗体, 胰岛素, 甲状腺, 特异性, 干扰素, 抗病毒, 胰岛, 自体, 疾病, 病症, 并发症, 传染病, 慢性病, 癌症, 糖尿病, 该病, 慢性, 妇科疾病, 细胞, 细胞核, 生物体, 蛋白, 蛋白质, 线粒体, 神经细胞, 肝细胞, 神经元, 体细胞, 器官, 肾脏, 人体器官, 脏器, 干细胞, 骨髓, 脑部, 肝脏, 肿瘤, 内脏, 球蛋白, 干扰素, 核糖体, 生长因子, 多肽, 脂质, 淋巴细胞, IgG, 核酸, 胸腺, 微生物, 细菌, 生物, 菌种, 酵母, 病原体, 有机物, 寄生虫, 大肠杆菌, 酵母菌, 细菌, 病菌, 微生物, 病原体, 霉菌, 寄生虫, 真菌, 毒素, 病原, 化学物质, 流感, 禽流感, H1N1, 登革热, 霍乱, 流行病, 鼠疫, 肺炎, 流行性, 甲型, 临床, 医学, 病理, 临床试验, 临床实验, 放射治疗, 临床医学, 流行病学, 病理学, 药理学");
        wordMap.put("04159", "农村, 贫困地区, 城乡, 小城镇, 乡村, 乡镇企业, 县域, 农牧区, 贫困户, 边远地区, 发展, 经济发展, 产业发展, 蓬勃发展, 健康发展, 发展战略, 转型, 发展壮大, 推进, 国际化, 城乡, 农村, 全区, 小城镇, 县域, 建成区, 新城区, 土地利用, 农业产业, 两区, 田园, 闲适, 绿意, 田园生活, 山野, 绿荫, 斜阳, 山居, 原野, 乡野, 农业, 林业, 畜牧业, 农牧业, 农牧, 种植业, 工业, 现代农业, 畜牧, 农业产业, 社会保障, 社会福利, 社保, 医疗卫生, 社会保险, 医疗保险, 医疗保障, 财税, 福利, 失业保险, 流动人口, 农村居民, 外来人口, 城镇居民, 外来人员, 外来工, 城市居民, 妇女儿童, 建成区, 老年, 生态, 生态环境, 自然生态, 生态系统, 自然环境, 湿地, 生态景观, 生态建设, 生态旅游, 绿色生态, 政策, 经济政策, 措施, 相关政策, 举措, 新政, 产业政策, 财政政策, 政策措施, 配套措施, 脱贫, 扶贫, 脱贫致富, 扶贫开发, 三农, 贫困地区, 援藏, 奔小康, 贫困户, 扫盲");
        wordMap.put("", "你好, 我爱你, 再见, 晚安, 谢谢, 噢, 嗯, 嘿, 哈哈, 喔, 欢迎, 热烈欢迎, 亲睐, 喜爱, 赞赏, 尊敬, 赞许, 爱戴, 祝贺, 广泛支持, 感谢, 非常感谢, 特别感谢, 表示感谢, 谢谢, 感激, 祝贺, 诚挚, 衷心, 答谢, 政策, 经济政策, 措施, 相关政策, 举措, 新政, 产业政策, 财政政策, 政策措施, 配套措施, 安全, 安全可靠, 安全管理, 公共安全, 生命安全, 防护, 系统安全, 人身安全, 数据安全, 交通安全, 社会, 道德, 社会风气, 社会制度, 价值观念, 伦理, 弱势群体, 社会变革, 自由民主, 社会性, 价值, 商业价值, 内在价值, 品牌价值, 稀缺性, 收藏价值, 实用价值, 效用, 资产价值, 投资回报, 感动, 敬佩, 感激, 动容, 感人, 深深感动, 怀念, 敬仰, 真挚, 景仰, 课程, 专业课程, 培训课程, 课程内容, 本科课程, 科目, 学程, 英语课程, 预科, 选修, 教育, 基础教育, 高等教育, 教育工作, 幼儿教育, 家庭教育, 素质教育, 终身教育, 文化教育, 德育");
        log.info("开始统计user_wold_cloud~");
        int total = dwiStudentCourseMapper.selectCount(new LambdaQueryWrapper<>());
        int pageSize = 10000;
        int totalPageNum = (total + pageSize - 1) / pageSize;
        for (int i = 0; i < totalPageNum; i++) {
            log.info("page" + i);
            System.out.println("当前页为：" + i + ",每页限制：" + pageSize);
            LambdaQueryWrapper<DwiStudentCourse> lqw = new LambdaQueryWrapper<>();
            lqw.last("limit " + i * pageSize + "," + pageSize);
            List<DwiStudentCourse> list = dwiStudentCourseMapper.selectList(lqw);
            try {
                for (DwiStudentCourse dwiStudentCourse : list) {
                    String studentNumber = dwiStudentCourse.getStudentNumber();
                    String courseCode = dwiStudentCourse.getCourseCode();
                    String wordCloud = wordMap.get(courseCode);

                    LambdaQueryWrapper<UserWordcloud1> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(UserWordcloud1::getStudentId, studentNumber);
                    // 需要新增的课程id
                    UserWordcloud1 userWordcloud1 = userWordcloud1Mapper.selectOne(lambdaQueryWrapper);
                    if (userWordcloud1 == null) {
                        userWordcloud1 = new UserWordcloud1();
                        userWordcloud1.setStudentId(studentNumber);
                        userWordcloud1.setWordCloud(wordCloud);
                        userWordcloud1.setCourseId(courseCode);
                        userWordcloud1Mapper.insertWordCloud(userWordcloud1);
                    } else {
                        // 数据库已有的课程id
                        String courses = userWordcloud1.getCourseId();
                        if (courses.contains("00808") && courseCode.equals("00815")) continue;
                        if (courses.contains("00815") && courseCode.equals("00808")) continue;
                        if (courses.contains("02473") && courseCode.equals("01776")) continue;
                        if (courses.contains("01776") && courseCode.equals("02473")) continue;
                        userWordcloud1.setWordCloud(userWordcloud1.getWordCloud() + "," + wordCloud);
                        userWordcloud1.setCourseId(courseCode + "," + courses);
                        userWordcloud1Mapper.updateById(userWordcloud1);
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

        log.info("完成统计user_wold_cloud~");
    }

}
