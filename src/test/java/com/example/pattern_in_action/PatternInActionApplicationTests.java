package com.example.pattern_in_action;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class PatternInActionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public  void main() {
        String str = "17611536109";
        String pattern = "0?(13|14|15|17|18|19)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

        String str1 = "6487794";
        String pattern1 = "[0-9-()（）]{7,18}";
        Pattern r1 = Pattern.compile(pattern1);
        Matcher m1 = r1.matcher(str1);
        System.out.println(m1.matches());
    }

}
