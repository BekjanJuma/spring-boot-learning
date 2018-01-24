import java.util.ArrayList;
import java.util.List;

public class StringMatcher {
	private List<String> patterns;
	private List<String> text;
	private List<int[]> prefixTables;
	
	
	public StringMatcher(List<String> text, List<String> patterns, List<int[]> prefixTables) {
		this.text = text;
		this.patterns = patterns;
		this.prefixTables = prefixTables;
	}
	

	
	public void run() {
		System.out.println("Exact match:");
		exactMatch();
		System.out.println("Partial match:");
		KMP();
	}
	
	
	
	public List<String> exactMatch(){
		List<String> matches = new ArrayList<>();
		for(String txt : text) {
			for(String pat : patterns) {
				if(txt.equals(pat)) {
					matches.add(pat);
					System.out.println( pat );
				}
			}
		}
		
		return matches;
	}
	
	public List<String> KMP(){
		List<String> matches = new ArrayList<>();
		for(String txt : text) {
			for(int i=0; i<patterns.size(); i++) {
				String pat = patterns.get(i);
				if(KMP(txt, pat, prefixTables.get(i))) {
					matches.add(pat);
					System.out.println( txt );
				}
			}
		}
		return matches;
	}
	
	
	// KMP prefix table
	public static int[] prefixTable(char[] s) {
		int m = s.length;
		int[] T = new int[m];
		T[0] = 0;
		int j = 0;
		for(int i=1; i<m; i++) {
			while(j>0 && s[j] != s[i]) {
				j = T[j-1];
			}
			if(s[j] == s[i]) {
				j++;
			}
			T[i] = j;
		}
		return T;
	}
	
	
	
	// Knuth Morris Pratt - string searching algorithm
	// returns true if pattern exists in the text
	// Time complexity O(m+n) where m=text.length, n = pattern.length
	public boolean KMP(String text, String pattern, int[] pi) {
		char[] t = text.toCharArray();
		char[] p = pattern.toCharArray();
		
		int n = t.length;
		int m = p.length;
		
		int q = 0; // number of characters matched
		for(int i=0; i<n; i++) {
			while(q>0 && p[q] != t[i]) {
				q = pi[q-1];
			}
			
			if(p[q] == t[i]) {
				q++;
			}
			
			if(q==m) {
				return true;
			}
		}
		return false;
	}

}
