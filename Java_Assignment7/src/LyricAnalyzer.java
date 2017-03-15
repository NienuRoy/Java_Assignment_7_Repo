/*
Since you have not learned File class yet, I have written the main method for you
to test your written code. Please don't forget to change the file path
"/Users/luqifei/IdeaProjects/untitled/Paint/test1.txt" to test 4 test txt
files. The 4 test txt files are already saved in assignment 7 zip. The deadline
for this assignment is 15 Mach 2017.
 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class LyricAnalyzer {

	public static void add(HashMap<String,ArrayList<Integer>> map, String lyricWord, int wordPosition) {

		if(!map.containsKey(lyricWord)){
			map.put(lyricWord,new ArrayList<Integer>());
			map.get(lyricWord).add(wordPosition);
		}
		else {
			map.get(lyricWord).add(wordPosition);
		}
	}

	public static void displayWords(HashMap<String, ArrayList<Integer>> map) {
		System.out.println();

		Set<String> keys = new TreeSet<String>(map.keySet());
		for(String word: keys){
			System.out.print(word +": ");
			Object[] value = map.get(word).toArray();
				for(int i=0;i<value.length;i++){
					if(i!=value.length-1){
						System.out.print(value[i] +",");
					}
					else{
						System.out.println(value[i]);
					}
				}
			}
		}
	

	public static void displayLyrics(HashMap<String, ArrayList<Integer>> map) {
		
		int length = 0;
		for(ArrayList<Integer> value: map.values()){
			length +=value.size();
		}
		
		String[] words = new String[length+1];
		Arrays.fill(words, "");

		Set<String> keys = map.keySet();
		for(String key : keys){
			for(Integer v1 : map.get(key)){
				if(v1>0){
					words[v1]= key +" ";
				}
				else
				{
					words[-v1] = key +"\n";
				}
			}
		}
		for(int i =1;i<words.length;i++){
			System.out.print(words[i]); //Printing lyrics 
		}
	} 

	public static int count(HashMap<String, ArrayList<Integer>> map) {

		ArrayList<String> arr = new ArrayList<String>();
		for(Entry<String, ArrayList<Integer>> entry: map.entrySet()){
			if(!arr.contains(entry.getKey())){
				arr.add(entry.getKey());
			}
		}
		return arr.size();   //Or simply do map.size() - keys will be unique in map
	}

	public static String mostFrequentWord(HashMap<String, ArrayList<Integer>> map) {

		int maxcount=0;
		String freqWord = "";

		for(String word: map.keySet()){
			ArrayList<Integer> num = map.get(word);
			if(num.size()>maxcount){
				maxcount=num.size();
				freqWord=word;
			}
		}
		return freqWord;
	}

	public static void main(String[] args) throws IOException {

		HashMap<String,ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();
		int position = 1;  //word position index start from 1
		String pathname = "C:/Users/ninet/Java_Assign/test1.txt/";
		File f = new File(pathname);
		BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
		String line = "";
		while (true) {
			line = br.readLine();
			if (line == null || line.length() == 0) {
				break;
			}
			String[] arr = line.trim().split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (i != arr.length - 1) {
					add(map, arr[i].toUpperCase(), position++);
				} else {
					add(map, arr[i].toUpperCase(), (-1) * position);
					position++;
				}
			}
		}

		br.close();
		displayLyrics(map);
		displayWords(map);
		System.out.println();
		System.out.println("The number of unique words in the lyric is: " + count(map));
		System.out.println();
		System.out.println("Most frequent word: " + mostFrequentWord(map));
	}
}