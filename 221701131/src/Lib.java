//package abc;

public class Lib {
	public  String[] commands= {"-log","-out","-date","-type","-province"};
	public  String[] types= {"ip","sp","cure","dead"};
	public  String[] outTypes = {"感染患者","疑似患者","治愈","死亡"};
	public  String[] provinces= {
			"全国",
			"北京","天津","河北", "山西", "内蒙古",
			"辽宁", "吉林", "黑龙江",
			"上海", "江苏", "浙江", "安徽", "福建", "江西", "山东",
			"河南", "湖北", "湖南", "广东", "广西", "海南",
			"重庆", "四川", "贵州", "云南", "西藏",
			"陕西", "甘肃", "青海", "宁夏", "新疆",
			"香港", "澳门", "台湾"	
	};
	public String lastDayFlag="last-day";
}
