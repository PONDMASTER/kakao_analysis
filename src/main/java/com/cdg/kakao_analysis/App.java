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
        System.out.print("�м��� ������ ��ο� ���ϸ��� �Է��ϼ��� : ");
        filePath = scan.nextLine();
        
        Analysis kakaoAnalysis = new Analysis(filePath);
        
        if(kakaoAnalysis.readFile()){
        	kakaoAnalysis.resultView();
        }else{
        	System.out.println("������ �о���µ� �����߽��ϴ�.");
        }
        
    }
}
