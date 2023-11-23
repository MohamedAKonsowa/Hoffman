// Mohamed Ashraf Rabie Mohamed Saad Konsowa 21100854
// Mohamed Ahmed Salah Eldin 21100806

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int[] frequencyNum(String priorSplit){
		String[] input = priorSplit.split(" ");
		int[] priorSort = new int[26];
		int[] postSort = new int[26];
		for (int j = 0; j < input.length; j++) {
			for (int i = 0; i < input[j].length(); i++) {
				priorSort[(int) input[j].charAt(i) - 97]++;
			}
		}
		System.arraycopy(priorSort, 0, postSort, 0, priorSort.length); // copy both arrays to sort the prior sort
		Arrays.sort(postSort);
		int counter = 0;
		for (int i = 0; i < 26; i++){
			if (postSort[i] != 0)counter++;
		}
		int[] postSort2 = new int[counter];
		int counter2 = 0;
		for (int i = 25; i >= 0; i--){
			if (postSort[i] != 0) {
				postSort2[counter2] = postSort[i];
				counter2++;
			}
		}

		return postSort2;
	}

	public static char[] frequency(String priorSplit){
		String[] input = priorSplit.split(" ");
		int[] priorSort = new int[26];
		int[] postSort = new int[26];
		for (int j = 0; j < input.length; j++) {
			for (int i = 0; i < input[j].length(); i++) {
				priorSort[(int) input[j].charAt(i) - 97]++;
			}
		}
		System.arraycopy(priorSort, 0, postSort, 0, priorSort.length); // copy both arrays to sort the prior sort
		Arrays.sort(postSort);
		char[] frequency = new char[26];
		int counter1 = 0;
		for (int i = 0; i < 26; i++){
			for (int j = 0; j < 26; j++){
				if (postSort[i] != 0){
					if (postSort[i] == priorSort[j]) {
						frequency[counter1] = (char)(j+97);
						priorSort[j] = 0;
						counter1++;
						break;
					}
				}
			}
		}
		int counter2 = 0;
		for (int i = 0; i < 26; i++){
			if (frequency[i] != '\u0000') counter2++;
		}
		char[] frequencyDescending = new char[counter2];
		int counter3 = 0;
		for (int i = 25; i >= 0; i--){
			if (frequency[i] != '\u0000') {
				frequencyDescending[counter3] = frequency[i];
				counter3++;
			}
		}
		return frequencyDescending;
	}

	public static void writeOriginal(String input) throws FileNotFoundException {
		File file = new File("original.txt");
		PrintWriter pw = new PrintWriter(file);
		pw.write(input);
		pw.close();
	}
	public static void writeNew(String priorSplit, char[] frequencyChar) throws FileNotFoundException {
		String[] input = priorSplit.split(" ");
		File file = new File("new.txt");
		PrintWriter pw = new PrintWriter(file);
		int counter = 0;
		for (int i = 0; i < frequencyChar.length; i++){
			pw.write(frequencyChar[i] + "|" + counter + "\n");
			counter++;
		}
		pw.write("//\n");
		for (int k = 0; k < input.length; k++) {
			for (int i = 0; i < input[k].length(); i++) {
				int index;
				for (int j = 0; j < frequencyChar.length; j++) {
					if (input[k].charAt(i) == frequencyChar[j]) {
						index = j;
						pw.print(index + " ");
						break;
					}
				}
			}
			pw.print("\n");
		}

		pw.close();
	}

	public static void readNew() throws FileNotFoundException {
		File file = new File("new.txt");
		Scanner s1 = new Scanner(file);
		char[] letters = new char[26];
		int counter = 0;
		while (s1.hasNextLine()){
			String read = s1.nextLine();
			if (read.equals("//")) counter++;
			else if (counter == 0){
				String[] split = read.split("");
				letters[Integer.parseInt(split[2])] = split[0].charAt(0);
			}
			else if (counter == 1){
				String[] split = read.split(" ");
				for (int i = 0; i < split.length; i++){
					System.out.print(""+letters[Integer.parseInt(split[i])]);
				}
				System.out.print(" ");
			}
		}
	}

	public static void main(String[]args) throws FileNotFoundException {
		Scanner s1 = new Scanner(System.in);
		s1.useDelimiter("\n");
		String input = s1.next();
		//String input = "bccccccdddaaaaa";
		char[] frequency = frequency(input);
		System.out.println(Arrays.toString(frequency));
		int[] frequencyNum = frequencyNum(input);
		System.out.println(Arrays.toString(frequencyNum));
		for (int i = 0; i < frequency.length; i++){
			System.out.println(frequency[i] + " " + frequencyNum[i]);
		}
		writeOriginal(input);
		writeNew(input, frequency);
		readNew();

	}
}
