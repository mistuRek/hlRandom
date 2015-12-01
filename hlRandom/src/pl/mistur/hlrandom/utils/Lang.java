package pl.mistur.hlrandom.utils;

public class Lang {
	
	private String name;
	
	public Lang(String name) {
		this.name = name;
		LangUtils.addLang(this);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Lang get(String lname){
		for(Lang l : LangUtils.getLangs()){
			if(l.getName().equalsIgnoreCase(lname)) {
				return l;
			}
			else if (!(l.getName().equalsIgnoreCase(lname))) {
				l.setName(lname);
			}
				return l;
		}
		return new Lang(lname);
	}
	

}
