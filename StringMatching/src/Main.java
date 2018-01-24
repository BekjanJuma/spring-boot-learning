import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	// used in KMP search, preload to memory
	public static List<int[]> prefixTables;
	public static List<String> patterns;
	
	public static void main(String[] args) {
		// preprocess
		patterns = readFile("resource/patterns.txt");
		prefixTables = new ArrayList<>();
		for(String pattern : patterns) {
			prefixTables.add(StringMatcher.prefixTable(pattern.toCharArray()));
		}
		
		for(String arg : args) {
			System.out.println("File: " + arg);
			List<String> text = readFile(arg);
			StringMatcher stringMatcher = new StringMatcher(text, patterns, prefixTables);
			stringMatcher.run();
		}
	}
	
	
	
	// read from file and load them to memory
	public static List<String> readFile(String path){
		List<String> output = new ArrayList<>();
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(path), 1000 * 8192);
			String line = "";
			while ((line = br.readLine()) != null) {
				output.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}


}
