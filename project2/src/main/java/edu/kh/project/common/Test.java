package edu.kh.project.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

   public static void main(String[] args) throws ParseException {
    
       // Date : 날짜용 객체
        
       // Calendar : Date 업그레이드 객체
       // SimpleDateFormat : 날짜를 원하는 형태의 문자열로 변환
       
       // 오늘 23시 59분 59초까지 남은시간을 초단위로 구하기

       Date a = new Date();
//       Date b = new Date((long)1669045945123L);
       Date b = new Date((long)1669132470018L);                        
       // 기준 시간 : 1970년 1월 1일 9시 0분 0초
       // new DAte(ms) : 기준시간 + ms만큼 지난 시간
       
       Calendar cal = Calendar.getInstance();
//       cal.add(단위, 추가할 값);
       cal.add(cal.DATE, 1); // 날짜에 1 추가
       
       // simpleDateFormat을 이용해서 cal 날짜 중 시,분, 초를 0:0:0으로 바꿈
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date temp = new Date(cal.getTimeInMillis());
       // 하루 증가한 내일 날짜의 ms 값을 이용해서 Date객체 생성
       
       
       System.out.println(sdf.format(temp));
       Date c = sdf.parse(sdf.format(temp));
                   //날짜 형식 String을 -> Date로 변환
       
       System.out.println(a);
       System.out.println(temp);
//       System.out.println(cal);
       System.out.println(c);
       
       // 날짜끼리는 -가 안됨
       // 내일 자정 ms - 현재 시간 ms
       long diff = c.getTime() - a.getTime();
       System.out.println(diff / 1000 -1); // 초 -1초 23시 59분 59초까지 남은 시간(s)
       
}
}
