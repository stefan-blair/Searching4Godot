package fileManagment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

public final class Save {
	
	static String directory = "";
	
	public static void handleDirectory(){
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		if(operatingSystem.startsWith("mac os x")){
			File file = new File(System.getProperty("user.home")+"/Applications/"+"Searching4Godot");
			directory = System.getProperty("user.home")+"/Applications/"+"Searching4Godot";
			if(!file.exists()){
				if(file.mkdir()){
					System.out.println("Created Directory");
				}
			}else{
				System.out.println("Directory already exists");
			}
		}else{	
			File file = new File("C:\\Searching4Godot");
			directory = "C:\\Searching4Godot";
			if(!file.exists()){
				if(file.mkdir()){
					System.out.println("Created Directory");
				}
			}else{
				System.out.println("Directory already exists");
			}
		}
		File saveData = new File(directory+"/save data");
		if(!saveData.exists()){
			if(saveData.mkdir()){
				System.out.println("Created Save Directory");
				createSaveData();
			}
		}
		
	}
	
	public static void createSaveData(){
		PrintWriter save = null;
		try{
			save = new PrintWriter(new File(directory+"/save data/1.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		save.flush();
		save.print("1");
		save.close();
		try{
			save = new PrintWriter(new File(directory+"/save data/2.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		save.flush();
		save.print("1");
		save.close();
		try{
			save = new PrintWriter(new File(directory+"/save data/3.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		save.flush();
		save.print("1");
		save.close();
	}

	public static void save(int level, int save) throws IOException{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(directory+"/save data/"+save+".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.flush();
		out.println(level);
		out.close();
	}
	
	public static int readSave(int save) throws NumberFormatException, IOException{
		int level;
		String file = directory+"/save data/"+save+".txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		level = Integer.valueOf(reader.readLine());
		reader.close();
		return level;
	}
	
}