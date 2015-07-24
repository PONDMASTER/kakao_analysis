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
	
	//file�� ������ �ҷ��� ���ڿ� Data�� ����
	public boolean readFile(){
		boolean result = true;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
			String strTemp;
			while((strTemp=reader.readLine()) != null){
				System.out.println(strTemp);
				// ������ ����
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
	
	//�������� ���ڿ� Data�� �м�
	public void analysisData(){
		
	}
	
	//�м� ������ ���
	public void resultView(){
		for(String str : fileData){
			System.out.println(str);
		}
	}

}
