package com.cdg.kakao_analysis;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scan = new Scanner(System.in);
    	String filePath = "";
        System.out.print("분석할 파일의 경로와 파일명을 입력하세요 : ");
        filePath = scan.nextLine();
        
        Analysis kakaoAnalysis = new Analysis(filePath);
        
        if(kakaoAnalysis.readFile()){
        	kakaoAnalysis.analysisData();
        	kakaoAnalysis.resultView();
        }else{
        	System.out.println("파일을 읽어오는데 실패했습니다.");
        }
        
    }
}
