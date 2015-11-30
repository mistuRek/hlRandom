package pl.mistur.hlrandom.utils;

import java.util.ArrayList;
import java.util.List;

public class LangUtils {
	
	private static List<Lang> langs = new ArrayList<Lang>();
	
	public static List<Lang> getLangs(){
		return new ArrayList<Lang>(langs);
	}
	
	public static void addLang(Lang u){
		if(!langs.contains(u)) {
			langs.add(u);
		}
	}
	
	public static void removeLang(Lang u){
		if(langs.contains(u)) {
			langs.remove(u);
		}
	}

}
