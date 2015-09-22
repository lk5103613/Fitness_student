package com.honestwalker.androidutils.system;

import android.os.Process;

public class ProcessUtil {

	public static void killMyProcess() {
		Process.killProcess(Process.myPid());
	}
	
	public static int getMyPid() {
		return Process.myPid();
	}
	
	public static void killProcessById(int pid) {
		Process.killProcess(pid);
	}
	

}
