package pl.mistur.hlrandom.lang.utils;

import java.util.ArrayList;
import java.util.List;

import pl.mistur.hlrandom.lang.Lang;

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
