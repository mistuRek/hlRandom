package pl.mistur.hlrandom.utils;

public class Messages {
	
	private static String teleportmessage;
	private static String invalidarguments;
	private static String dontpermissions;
	private static String isnotnumber;
	private static String greater;
	
	public static String getTeleportMessage() {
		return teleportmessage;
	}

	public static void setTeleportMessage(String teleportmessage) {
		Messages.teleportmessage = teleportmessage;
	}

	public static String getInvalidarguments() {
		return invalidarguments;
	}

	public static void setInvalidarguments(String invalidarguments) {
		Messages.invalidarguments = invalidarguments;
	}

	public static String getDontpermissions() {
		return dontpermissions;
	}

	public static void setDontpermissions(String dontpermissions) {
		Messages.dontpermissions = dontpermissions;
	}

	public static String getIsnotnumber() {
		return isnotnumber;
	}

	public static void setIsnotnumber(String isnotnumber) {
		Messages.isnotnumber = isnotnumber;
	}

	public static String getGreater() {
		return greater;
	}

	public static void setGreater(String greater) {
		Messages.greater = greater;
	}

}
