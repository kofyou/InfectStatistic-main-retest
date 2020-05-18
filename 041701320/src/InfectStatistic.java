/**
 * InfectStatistic
 * TODO
 *
 * @author ���ν�
 * @version 1.0
 * @since 2020.02.17
 */

import java.io.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;


class InfectStatistic{
	
	public String path_in;//��ȡ��־Ŀ¼
	public String path_out;//�����־Ŀ¼
	
	//��ȡ��ʽ������
	Date dt = new Date(System.currentTimeMillis());
    String strDateFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
    String date = sdf.format(dt);
	
	public String[] province = {
			"ȫ��", "����", "����" ,"����", "����", "����","����","�㶫", "����",
			"����", "����", "�ӱ�", "����", "������", "����", "����", "����",
			"����", "����", "����", "���ɹ�", "����", "�ຣ", "ɽ��", "ɽ��", "����", 
			"�Ϻ�","�Ĵ�", "̨��", "���", "����", "���", "�½�", "����", "�㽭"};
	/*��ά����
	 * һά��ʾʡ��
	 * ��ά�ֱ��ʾ{�Ƿ���Ҫ�г�,ip,sp,cure,dead}*/
	public int[][] situation = new int[35][5];
	
	//�������˳��Ĭ��ip,sp,cure,dead
	public int type_order[] = {1,2,3,4};
	public String[] type = {"��Ⱦ����", "���ƻ���", "����", "����"};

	//�����ļ�
	class ProcessFile{
		
		//��ȡ��־
		public void readLog() {
			File file = new File(path_in);
			File[] listFiles = file.listFiles();
			String path;
			
			int i=0;
			while(i<listFiles.length){
				path=listFiles[i].getName();
				if(path.compareTo(date)<=0) {
					try {
						BufferedReader b = new BufferedReader(new InputStreamReader(
								new FileInputStream(new File(path_in+path)), "UTF-8"));
						String line;
						while((line=b.readLine())!=null) {
							if(!line.startsWith("//")) {
								String p1 = "(\\S+) ���� ��Ⱦ���� (\\d+)��";
						    	String p2 = "(\\S+) ���� ���ƻ��� (\\d+)��";
						    	String p3 = "(\\S+) ���� (\\d+)��";
						    	String p4 = "(\\S+) ���� (\\d+)��";
						    	String p5 = "(\\S+) ��Ⱦ���� ���� (\\S+) (\\d+)��";
						    	String p6 = "(\\S+) ���ƻ��� ���� (\\S+) (\\d+)��";
						    	String p7 = "(\\S+) ���ƻ��� ȷ���Ⱦ (\\d+)��";
						    	String p8 = "(\\S+) �ų� ���ƻ��� (\\d+)��";
						    	if(Pattern.matches(p1, line)) 
						    		IP(line);
						    	if(Pattern.matches(p2, line)) 
						    		SP(line);
						    	if(Pattern.matches(p3, line)) 
						    		Cure(line);
						    	if(Pattern.matches(p4, line)) 
						    		Dead(line);
						    	if(Pattern.matches(p5, line)) 
						    		IPgo(line);
						    	if(Pattern.matches(p6, line))
						    		SPgo(line);
						    	if(Pattern.matches(p7, line))
						    		SPtoIP(line);
						    	if(Pattern.matches(p8, line))
						    		notSP(line);
							}
						}
						b.close();
					} catch (UnsupportedEncodingException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				i++;
			}			
		}
		public void IP(String line) {
			String[] str = line.split(" ");    	
	    	int n = Integer.valueOf(str[3].replace("��", ""));
	    	int i;
	    	for(i = 1; i < 35; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][1] += n;
	    			situation[i][1] += n; 
	    			if(situation[0][0] == -1)
	    				situation[i][0] = 1;
	    			break;
	    		}
	    	}
	    	//System.out.println(line);

		}
		public void SP(String line) {
			String[] str = line.split(" ");	    	
	    	int n = Integer.valueOf(str[3].replace("��", ""));
	    	int i;
	    	for(i = 1; i < 35; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][2] += n;
	    			situation[i][2] += n; 
	    			if(situation[0][0] == -1)
	    				situation[i][0] = 1;
	    			break;
	    		}
	    	}
	    	//System.out.println(line);
	    	
		}
		public void Cure(String line) {
			String[] str = line.split(" ");	    	
	    	int n = Integer.valueOf(str[2].replace("��", ""));
	    	int i;
	    	for(i = 1; i < 35; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][3] += n;
	    			situation[0][1] -= n;
	    			situation[i][3] += n;
	    			situation[i][1] -= n;
	    			if(situation[0][0] == -1)
	    				situation[i][0] = 1;
	    			break;
	    		}
	    	}
		}
		public void Dead(String line) {
			String[] str = line.split(" ");	    	
	    	int n = Integer.valueOf(str[2].replace("��", ""));
	    	int i;
	    	for(i = 1; i < 35; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][4] += n;
	    			situation[0][1] -= n;
	    			situation[i][4] += n;
	    			situation[i][1] -= n;
	    			if(situation[0][0] == -1)
	    				situation[i][0] = 1;
	    			break;
	    		}
	    	}
		}
		public void IPgo(String line) {
	    	String[] str = line.split(" ");	    	
	    	int n = Integer.valueOf(str[4].replace("��", ""));
	    	int i;
	    	for(i = 1; i < province.length; i++) {
	    		if(str[0].equals(province[i])) {
	    			situation[i][1] -= n;
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    		}
	    		if(str[3].equals(province[i])) {
	    			situation[i][1] += n;
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    		}
	    	}		
		}
		public void SPgo(String line) {
	    	String[] str = line.split(" ");	    	
	    	int n = Integer.valueOf(str[4].replace("��", ""));
	    	int i;
	    	for(i = 1; i < province.length; i++) {
	    		if(str[0].equals(province[i])) {
	    			situation[i][2] -= n;
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    		}
	    		if(str[3].equals(province[i])) {
	    			situation[i][2] += n;
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    		}
	    	}			
		}
		public void SPtoIP(String line) {
	    	String[] str = line.split(" ");     	
	    	int n = Integer.valueOf(str[3].replace("��", ""));
	    	int i;
	    	for(i = 1; i < province.length; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][2] -= n; 
	    			situation[0][1] += n; 
	    			situation[i][2] -= n; 
	    			situation[i][1] += n; 
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    			break;
	    		}
	    	}
		}
		public void notSP(String line) {
	    	String[] str = line.split(" ");     	
	    	int n = Integer.valueOf(str[3].replace("��", ""));
	    	int i;
	    	for(i = 1; i < province.length; i++) {
	    		if(str[0].equals(province[i])) { 
	    			situation[0][2] -= n; 	    			
	    			situation[i][2] -= n; 
	    			if(situation[0][0] == -1)
	    				situation[i][0]=1;
	    			break;
	    		}
	    	}			
		}
		
		//�����־
		public void writeLog(){
			try {
				BufferedWriter fw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (path_out,true),"UTF-8"));
				//FileWriter fw=null;
				//fw=new FileWriter(path_out);
				if(situation[0][0] ==-1)
					situation[0][0] = 1;
				int i=0;
				while(i<35) {
					
					if(situation[i][0] == 1) {
						
						//System.out.println("a");
						
						fw.write(province[i]+" ");
						for(int k=1;k<5;k++)
							for(int j=0;j<4;j++) {
								if(type_order[j]==k) {
									String string=type[j]+situation[i][j+1]+"��"+" ";
									fw.write(string);
									break;
									//System.out.println(string);
								}
							}
						fw.write("\n");	
					}
					
					i++;
					
				}
				fw.write("// ���ĵ�������ʵ���ݣ���������ʹ��");
				fw.close();				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}			 
	
	
	//����������
	class ProcessCmd{
		String[] args;//�洢������
		
		ProcessCmd(String[] args){
			this.args=args;
			situation[0][0]=-1;
			for(int t=1;t<35;t++)
				situation[t][0]=0;
		}
		
		//�������
		public boolean ProcessPara(){
			
			//�ж������Ƿ���ȷ	
			if(!args[0].equals("list")){
				System.out.println("�������");
				return false;
			}
		
			int i;
			for(i = 1;i<args.length;i++){
				//��ȡ-log
				if(args[i].equals("-log")){
					path_in = args[++i];
					
				} 
				//��ȡ-out
				else if(args[i].equals("-out")){ 
					path_out = args[++i];
					
				} 
				//��ȡ-date
				else if(args[i].equals("-date")){
					i = getDate(++i);
					if(i == -1) {
						System.out.println("���ڳ�����Χ!");
						return false;
					}
	
				} 
				//����-type���˳��
				else if(args[i].equals("-type")){ 
					i = setType(++i); 
					
				} 
				//��ȡ-province
				else if(args[i].equals("-province")){ 
					i = getProvince(++i);
	
				}
			}
			return true;
		}	
	
//		public void getPathIn(int i){
//
//		}
//		public void getPathOut(int i){
//			
//		}
		public int  getDate(int i){
			if(isValid(args[i])) { 
				if(date.compareTo(args[i]) >= 0) //�ж��Ƿ񳬹���ǰ����
					date = args[i] + ".log.txt";
				else
					return -1;
			}
			else 
				return -1;
			return i;			
		}
		public boolean isValid(String s) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            sdf.setLenient(false);
	            sdf.parse(s);
	            String[] a = s.split("-");
	            for (String x : a) {
	                boolean is_num = x.matches("[0-9]+");
	                if (!is_num)
	                    return false;
	            }
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	    }
		
		public int setType(int i){
			int k;
			int flag=i;
			for(k=0;k<4;k++)
				type_order[k] = 0;
			k=1;
			while(i<args.length) {
				if(args[i].equals("ip")) {
					type_order[0] = k;
					i++;
					k++;
				} 
				else if(args[i].equals("sp")) {
					type_order[1] = k;
					i++;
					k++;
				} 
				else if(args[i].equals("cure")) {
					type_order[2] = k;
					i++;
					k++;
				} 
				else if(args[i].equals("dead")) {
					type_order[3] = k;
					i++;
					k++;
				} 
				else
					break;
			}				
			if(flag == i) {
				type_order[0] = 1;
				type_order[1] = 2;
				type_order[2] = 3;
				type_order[3] = 4;
			}
			return --i;
		}
		
		public int getProvince(int i){
			int flag=i;
			int j;
			for(int t=0;t<35;t++)
				situation[t][0]=0;
			while(i<args.length) {
				//System.out.println("province arg");
				for(j=0;j<35;j++) {
					if(args[i].equals(province[j])) {
						situation[j][0] = 1;
						i++;
						break;
					}
				}
				if(j == 35)
					break;
				
			}
			if(flag == i) {
				//System.out.println("flag==i");
				situation[0][0] = -1;
				
			}
			return --i;
		}
		
	}		
	

    public static void main(String[] args) {
    	InfectStatistic infectStatistic = new InfectStatistic();
    	InfectStatistic.ProcessCmd pCmd = infectStatistic.new ProcessCmd(args);
    	boolean x = pCmd.ProcessPara();
//    	if(x != true)
//    		return;
    	InfectStatistic.ProcessFile pFile = infectStatistic.new ProcessFile();
    	pFile.readLog();
    	pFile.writeLog();
    }	
}
