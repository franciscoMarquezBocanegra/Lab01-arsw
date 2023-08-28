package edu.eci.arsw.blacklistvalidator;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class HostBlackListThread extends Thread{
	
	private int ini;
	private int fini;
	private String ipAddress;
	private int ocurrencesCount = 0;
	private LinkedList<Integer> blackListOcurrences=new LinkedList<>();
	
	public HostBlackListThread(int ini, int fini, String ipAddress) {
		this.ini = ini;
		this.fini = fini;
		this.ipAddress = ipAddress;
	}
	
	public void run() {
		
		HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
		
		for(int i = ini; i<=fini;i++) {
			if(skds.isInBlackListServer(i, ipAddress)) {
				blackListOcurrences.add(i);
				ocurrencesCount++;
			}
		}
	}
	
	public int ocurrences() {
		return ocurrencesCount;
	}
	
	public List<Integer> getBlackListOcurrences(){
		return blackListOcurrences;
	}
}