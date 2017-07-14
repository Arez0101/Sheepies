package net.arez0101.sheepies;

@SuppressWarnings("javadoc")
public class Reference {
	
	public static final String MOD_ID = "";
	public static final String MOD_NAME = "";
	public static final String MOD_VERSION = "@VERSION@";
	public static final String BUILD_VERSION = "@BUILD_NUMBER@";
	public static final String VERSION_URL = "https://raw.githubusercontent.com/Arez0101/Versions/master/1.11.2/Sheepies.txt";
	
	public static final String FORGE = "forge";
	public static final String FORGE_VERSION = "@FORGE_VERSION@";
	public static final String FORGE_MIN_VERSION = "13.20.0.2282";
	
	public static final String CYCLOPSCORE = "cyclopscore";
	public static final String CYCLOPSCORE_VERSION = "@CYCLOPSCORE_VERSION@";
	public static final String CYCLOPSCORE_MIN_VERSION = "0.10.6";
	
	public static final String DEPENDENCIES =
			"required-after:" + FORGE + "@[" + FORGE_MIN_VERSION + ",);"
			+ "required-after:" + CYCLOPSCORE + "@[" + CYCLOPSCORE_MIN_VERSION + ",);";
	
	public static final String CLIENT_PROXY = "net.arez0101.sheepies.proxy.ClientProxy";
	public static final String COMMON_PROXY = "net.arez0101.sheepies.proxy.CommonProxy";
}
