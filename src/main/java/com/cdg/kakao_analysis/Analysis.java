package com.cdg.kakao_analysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Analysis {
	
	String filePath;
	ArrayList<String> fileData;
	
	Analysis(String filePath){
		this.filePath = filePath;
		fileData = new ArrayList<String>();
	}
	
	//file의 내용을 불러와 문자열 Data를 보관
	public boolean readFile(){
		boolean result = true;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
			String strTemp;
			while((strTemp=reader.readLine()) != null){
				System.out.println(strTemp);
				// 빈줄은 제거
				if(!strTemp.equals("")){
					fileData.add(strTemp);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	//보관중인 문자열 Data를 분석
	public void analysisData(){
		
	}
	
	//분석 내용을 출력
	public void resultView(){
		for(String str : fileData){
			System.out.println(str);
		}
	}

}
