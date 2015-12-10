package com.longteng.qa.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CsvDataProvider extends DataProviderImpl{
public final char SEPARATE = '|'; 
public final String ANNOTATION="#";
	
	/**
	 * 重写 generateData 方法
	 * @param caseName 方法名称
	 * @param dataFile 测试数据
	 */
	@Override
    protected void generateData(String caseName, String dataFile) {
		List<String[]> csvList = getCSVList(caseName,dataFile);
        if (csvList.equals(null) || csvList.size() == 0) {
            return;
        }
        for (int i = 1; i <= csvList.size(); i++) {
        	 if (i >= start && i <= max) {
        		 String[] line = csvList.get(i-1);
        		 data.add(line);
        	 }
        }
		}
	
	/**
	 * 获取CSV list 列表
	 * @param caseName
	 * @param dataFile
	 * @return
	 */
	@SuppressWarnings("resource")
	private List<String[]> getCSVList(String caseName,String dataFile){
		List<String[]> csvList = new ArrayList<String[]>();
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(defaultPath+dataFile+File.separator+caseName),"UTF-8"),SEPARATE);
			String[] line=null;
			while ((line=reader.readNext())!=null) {
				//过滤掉空行或以ANNOTATION定义的字符开头的行
				if(line.length==1&&line[0].isEmpty()||line[0].startsWith(ANNOTATION)){
					continue;
				}
				csvList.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csvList;
	}
}
