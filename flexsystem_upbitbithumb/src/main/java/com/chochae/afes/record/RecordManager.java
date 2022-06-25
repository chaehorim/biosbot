/**
 * 
 */
package com.chochae.afes.record;

import java.util.ArrayList;
import java.util.List;

import com.chochae.afes.record.dao.RecordInfo;

/**
 * @author banba
 *
 */
public class RecordManager {
	private static List<RecordInfo>  recordList = new ArrayList<RecordInfo> ();
	private static List<RecordInfo>  RiskyList = new ArrayList<RecordInfo> ();
	private static final int RECORD_SIZE = 200;
	
	public static void addRecord(MsgCode code, String Message, String marketId, String coinType) {
		RecordInfo record = new RecordInfo(System.currentTimeMillis(), code, Message, marketId, coinType);
		recordList.add(record);
		if (recordList.size() > RECORD_SIZE) {
			recordList.remove(0);
		}
		
		if (code == MsgCode.SERVER_ABNORMAL || code == MsgCode.TRADE_ERROR) {
			RiskyList.add(record);
			if (RiskyList.size() > RECORD_SIZE) {
				RiskyList.remove(0);
			}
//			System.out.println(RiskyList.size());
		}
	}
	
	public static List<RecordInfo> getrRecordList() {
		return recordList;
	}
	
	public static List<RecordInfo> getrRiskyList() {
		return RiskyList;
	}
}
