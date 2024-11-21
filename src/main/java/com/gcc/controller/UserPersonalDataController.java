package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.UserPersonalData;
import com.gcc.service.UserPersonalDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetPersonInfoByUserId")
public class UserPersonalDataController {
    @Resource
    private UserPersonalDataService upds;

    @GetMapping("/{user_id}")
    public Result<List<UserPersonalData>> getPersonInfoByUserId(@PathVariable String user_id) {
//        if (upds.getPersonInfoByUserId(user_id).size()==0){
//            List<UserPersonalData>u=new ArrayList<>();
//            UserPersonalData uu = new UserPersonalData(user_id,"无名氏",
//                    "1","40","01","01204010101",
//                    "03","国开总部","计算机",
//                    "2022-03-09","国家开放大学总部",
//                    "大专","7","13549003920",
//                    "wumingshi@163.com","国家开放大学总部");
//            u.add(uu);
//            return Result.success(u);
//        }
//        else
//        return Result.success(upds.getPersonInfoByUserId(user_id));

        List<UserPersonalData> upds_ = upds.getPersonInfoByUserId(user_id);
        UserPersonalData userPersonalData = upds_.get(0);
        String nation = userPersonalData.getNationality();
        String gender = userPersonalData.getUserGender();
        String political_status = userPersonalData.getPoliticalStatus();
        String degree = userPersonalData.getDegree();
        String status = userPersonalData.getStudentStatus();
        String phonenumber = userPersonalData.getPhoneNumber();
        String email = userPersonalData.getEMail();

        //        电话号码隐藏
        if (phonenumber == null)
            userPersonalData.setPhoneNumber(phonenumber);
        else if (phonenumber.startsWith("*"))
            userPersonalData.setPhoneNumber(null);
        else
            userPersonalData.setPhoneNumber(phonenumber);

        //        邮箱号码隐藏
        if (email == null)
            userPersonalData.setEMail(email);
        else if (email.startsWith("*"))
            userPersonalData.setEMail(null);
        else
            userPersonalData.setEMail(email);

        //        判断性别
        if ("2".equals(gender)) {
            userPersonalData.setUserGender("女");
        } else {
            userPersonalData.setUserGender("男");
        }

        //        判断民族
        switch (nation) {
            case "01":
                userPersonalData.setNationality("汉族");
                break;
            case "02":
                userPersonalData.setNationality("蒙古族");
                break;
            case "03":
                userPersonalData.setNationality("回族");
                break;
            case "04":
                userPersonalData.setNationality("藏族");
                break;
            case "05":
                userPersonalData.setNationality("维吾尔族");
                break;
            case "06":
                userPersonalData.setNationality("苗族");
                break;
            case "07":
                userPersonalData.setNationality("彝族");
                break;
            case "08":
                userPersonalData.setNationality("壮族");
                break;
            case "09":
                userPersonalData.setNationality("布依族");
                break;
            case "10":
                userPersonalData.setNationality("朝鲜族");
                break;
            case "11":
                userPersonalData.setNationality("满族");
                break;
            case "12":
                userPersonalData.setNationality("侗族");
                break;
            case "13":
                userPersonalData.setNationality("瑶族");
                break;
            case "14":
                userPersonalData.setNationality("白族");
                break;
            case "15":
                userPersonalData.setNationality("土家族");
                break;
            case "16":
                userPersonalData.setNationality("哈尼族");
                break;
            case "17":
                userPersonalData.setNationality("哈萨克族");
                break;
            case "18":
                userPersonalData.setNationality("傣族");
                break;
            case "19":
                userPersonalData.setNationality("黎族");
                break;
            case "20":
                userPersonalData.setNationality("傈僳族");
                break;
            case "21":
                userPersonalData.setNationality("佤族");
                break;
            case "22":
                userPersonalData.setNationality("畲族");
                break;
            case "23":
                userPersonalData.setNationality("高山族");
                break;
            case "24":
                userPersonalData.setNationality("拉祜族");
                break;
            case "25":
                userPersonalData.setNationality("水族");
                break;
            case "26":
                userPersonalData.setNationality("东乡族");
                break;
            case "27":
                userPersonalData.setNationality("纳西族");
                break;
            case "28":
                userPersonalData.setNationality("景颇族");
                break;
            case "29":
                userPersonalData.setNationality("柯尔克孜族");
                break;
            case "30":
                userPersonalData.setNationality("土族");
                break;
            case "31":
                userPersonalData.setNationality("达斡尔族");
                break;
            case "32":
                userPersonalData.setNationality("仫佬族");
                break;
            case "33":
                userPersonalData.setNationality("羌族");
                break;
            case "34":
                userPersonalData.setNationality("布朗族");
                break;
            case "35":
                userPersonalData.setNationality("撒拉族");
                break;
            case "36":
                userPersonalData.setNationality("毛南族");
                break;
            case "37":
                userPersonalData.setNationality("仡佬族");
                break;
            case "38":
                userPersonalData.setNationality("锡伯族");
                break;
            case "39":
                userPersonalData.setNationality("阿昌族");
                break;
            case "40":
                userPersonalData.setNationality("普米族");
                break;
            case "41":
                userPersonalData.setNationality("塔吉克族");
                break;
            case "42":
                userPersonalData.setNationality("怒族");
                break;
            case "43":
                userPersonalData.setNationality("乌孜别克族");
                break;
            case "44":
                userPersonalData.setNationality("俄罗斯族");
                break;
            case "45":
                userPersonalData.setNationality("鄂温克族");
                break;
            case "46":
                userPersonalData.setNationality("德昂族");
                break;
            case "47":
                userPersonalData.setNationality("保安族");
                break;
            case "48":
                userPersonalData.setNationality("裕固族");
                break;
            case "49":
                userPersonalData.setNationality("京族");
                break;
            case "50":
                userPersonalData.setNationality("塔塔尔族");
                break;
            case "51":
                userPersonalData.setNationality("独龙族");
                break;
            case "52":
                userPersonalData.setNationality("鄂伦春族");
                break;
            case "53":
                userPersonalData.setNationality("赫哲族");
                break;
            case "54":
                userPersonalData.setNationality("门巴族");
                break;
            case "55":
                userPersonalData.setNationality("珞巴族");
                break;
            case "56":
                userPersonalData.setNationality("基诺族");
                break;
            case "81":
                userPersonalData.setNationality("穿青人族");
                break;
            case "98":
                userPersonalData.setNationality("外国血统中国籍人士");
                break;
            default:
                userPersonalData.setNationality("其他");
                break;
        }

        //        判断是否党员
        switch (political_status) {
            case "01":
                userPersonalData.setPoliticalStatus("中国共产党党员");
                break;
            case "02":
                userPersonalData.setPoliticalStatus("中国共产党预备党员");
                break;
            case "03":
                userPersonalData.setPoliticalStatus("中国共产主义青年团团员");
                break;
            case "04":
                userPersonalData.setPoliticalStatus("中国国民党革命委员会会员");
                break;
            case "05":
                userPersonalData.setPoliticalStatus("中国民主同盟盟员");
                break;
            case "06":
                userPersonalData.setPoliticalStatus("中国民主建国会会员");
                break;
            case "07":
                userPersonalData.setPoliticalStatus("中国民主促进会会员");
                break;
            case "08":
                userPersonalData.setPoliticalStatus("中国农工民主党党员");
                break;
            case "09":
                userPersonalData.setPoliticalStatus("中国致公党党员");
                break;
            case "10":
                userPersonalData.setPoliticalStatus("九三学社社员");
                break;
            case "11":
                userPersonalData.setPoliticalStatus("台湾民主自治同盟盟员");
                break;
            case "12":
                userPersonalData.setPoliticalStatus("无党派人士");
                break;
            default:
                userPersonalData.setPoliticalStatus("群众");
                break;
        }

        //        判断学籍
        switch (status) {
            case "00":
                userPersonalData.setStudentStatus("取消毕业资格");
                break;
            case "02":
                userPersonalData.setStudentStatus("未注册");
                break;
            case "03":
                userPersonalData.setStudentStatus("转出");
                break;
            case "04":
                userPersonalData.setStudentStatus("休学");
                break;
            case "05":
                userPersonalData.setStudentStatus("退学");
                break;
            case "06":
                userPersonalData.setStudentStatus("开除");
                break;
            case "07":
                userPersonalData.setStudentStatus("毕业");
                break;
            case "08":
                userPersonalData.setStudentStatus("异动中");
                break;
            case "09":
                userPersonalData.setStudentStatus("取消学籍");
                break;
            default:
                userPersonalData.setStudentStatus("在籍");
                break;
        }

        //        判断专业层次
        if (degree.indexOf("1") == 2) {
            userPersonalData.setDegree("研究生");
        } else if (degree.indexOf("2") == 2) {
            userPersonalData.setDegree("本科（专科起点）");
        } else if (degree.indexOf("3") == 2) {
            userPersonalData.setDegree("本科（高中起点）");
        } else if (degree.indexOf("4") == 1 || degree.indexOf("4") == 2) {
            userPersonalData.setDegree("专科");
        } else if (degree.indexOf("5") == 2) {
            userPersonalData.setDegree("中专");
        } else if (degree.indexOf("6") == 2) {
            userPersonalData.setDegree("本专");
        } else
            userPersonalData.setDegree("其他");

        return Result.success(upds_);
    }

}

