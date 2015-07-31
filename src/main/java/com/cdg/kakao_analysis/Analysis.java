package com.cdg.kakao_analysis;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Analysis {
	
	String filePath;
	ArrayList<String> fileData;
	TreeMap<String, Integer> timeTreeMap;
	TreeMap<String, TreeMap<String, Integer>> userTreeMap;
	
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
				//System.out.println(strTemp);
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
		int timeStart = 0;
		int timeEnd = 0;
		int userStart = 0;
		int talkCheck = 0;
		String userTalk;
		String[] userTalkWord;
		
		timeTreeMap = new TreeMap<String, Integer>();
		userTreeMap = new TreeMap<String, TreeMap<String, Integer>>();
				
		for(String str : fileData){
			
			//시간대 분석
			if((timeStart = str.indexOf("오전")) == -1){
				timeStart = str.indexOf("오후");
			}
			timeEnd = str.indexOf(":", timeStart);
			if(timeStart > 0 && timeEnd > timeStart){
				if(timeTreeMap.containsKey(str.substring(timeStart, timeEnd))){
					Integer value = (Integer)timeTreeMap.get(str.substring(timeStart, timeEnd));
					timeTreeMap.put(str.substring(timeStart, timeEnd), new Integer(value.intValue() + 1));				
				}else{
					timeTreeMap.put(str.substring(timeStart, timeEnd), new Integer(1));
				}
				
				//사용자 사용 단어 분석
				userStart = str.indexOf(",", timeEnd);
				talkCheck = str.indexOf(":", userStart);
				if(userStart > timeEnd && talkCheck > userStart){
					userTalkWord = str.substring(userStart + 2).split(" ");
					if(!userTreeMap.containsKey(userTalkWord[0])){
						userTreeMap.put(userTalkWord[0], new TreeMap<String, Integer>());
					}
					TreeMap<String, Integer> treeMapTemp = userTreeMap.get(userTalkWord[0]);
					for(int i = 2; i < userTalkWord.length; i++){
						if(treeMapTemp.containsKey(userTalkWord[i])){
							Integer value = (Integer)treeMapTemp.get(userTalkWord[i]);
							treeMapTemp.put(userTalkWord[i], new Integer(value.intValue() + 1));
						}else{
							treeMapTemp.put(userTalkWord[i], new Integer(1));
						}
					}
				}
				
			}
			
		}
		
	}
	
	static class ValueComparator implements Comparator {

		public int compare(Object o1, Object o2){
			if(o1 instanceof Map.Entry && o2 instanceof Map.Entry){
				Map.Entry e1 = (Map.Entry)o1;
				Map.Entry e2 = (Map.Entry)o2;
				
				int v1 = ((Integer)e1.getValue()).intValue();
				int v2 = ((Integer)e2.getValue()).intValue();
				
				return v2 - v1;
			}
			return -1;
		}
	}
	
	//분석 내용을 출력
	public void resultView(){

		int viewTop = 5;
		
		// 시간대 출력
		Set setTime = timeTreeMap.entrySet();
		List listTime = new ArrayList(setTime);
		Collections.sort(listTime, new ValueComparator());
		Iterator itTime = listTime.iterator();
		
		System.out.println("==가장많이 대화하는 시간대==");
		for(int i = 0; i < viewTop; i++){
			if(itTime.hasNext()){
				Map.Entry entry = (Map.Entry)itTime.next();
				int value = ((Integer)entry.getValue()).intValue();
				System.out.println((i + 1) + ". " + entry.getKey() + "시(" + value + "회)");
			}
		}
		
		// 유저별 출력
		Iterator itUser = userTreeMap.entrySet().iterator();
		
		while(itUser.hasNext()){
			Map.Entry entry = (Map.Entry)itUser.next();
			System.out.println("");
			System.out.println(entry.getKey() + " 님이 자주 사용하는 단어");
			
			TreeMap<String, Integer> treeMapTemp = userTreeMap.get(entry.getKey());
			Set setWord = treeMapTemp.entrySet();
			List listWord = new ArrayList(setWord);
			Collections.sort(listWord, new ValueComparator());
			Iterator itWord = listWord.iterator();
			
			for(int i = 0; i < viewTop; i++){
				if(itWord.hasNext()){
					Map.Entry entryWord = (Map.Entry)itWord.next();
					int value = ((Integer)entryWord.getValue()).intValue();
					System.out.println((i + 1) + ". " + entryWord.getKey() + "(" + value + "회)");
				}
			}
			
		}		
		
	}

}
