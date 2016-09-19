package test.myorg.commonapp;

import com.myorg.commonapp.bean.po.UserInfo;
import com.myorg.commonapp.utils.ExcelTool;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huyan on 16/9/19.
 */
public class ExcelTest {


    @Test
    public void generateExcel() throws FileNotFoundException {

        List<TestBvo> testBvos = generateData();

        String filePath = "/Users/huyan/StudyBench/commonApp/module/commonapp-web/src/main/resources/excelTemplate/aaa.xls";
        String templatePath = "excelTemplate/test.xls";

        Map data = new HashMap();

        data.put("testBvos",testBvos);
        OutputStream out = new FileOutputStream(new File(filePath));
        ExcelTool.buildExcelByTemplate(templatePath,data, out);
    }


    private List<TestBvo> generateData(){

        List<TestBvo> testBvos = new ArrayList<TestBvo>();

        for(int i=0; i<3; i++){
            TestBvo testBvo = new TestBvo();
            testBvo.setTestName("TestName"+i);
            List<UserInfo> userInfos = new ArrayList<UserInfo>();
            for (int n=0; n<2; n++){
                UserInfo userInfo = new UserInfo();

                userInfo.setId(n);
                userInfo.setUserName("useName" + n);
                userInfo.setPassword("passWd"+n);
                userInfos.add(userInfo);
            }

            testBvo.setUserInfos(userInfos);
            testBvos.add(testBvo);
        }

        return testBvos;
    }

    public class TestBvo{
        private String testName ;
        private List<UserInfo> userInfos;

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public List<UserInfo> getUserInfos() {
            return userInfos;
        }

        public void setUserInfos(List<UserInfo> userInfos) {
            this.userInfos = userInfos;
        }
    }
}
