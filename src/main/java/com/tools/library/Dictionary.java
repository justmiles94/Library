package com.tools.library;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

	Map<String, Integer> dictionary = new HashMap<String, Integer>();

	String delimiter = ",";

	public static void main(String[] args) {
		new Dictionary(args);
	}
	
	public Dictionary(String ...args) {
		List<String> argList = Arrays.asList(args);
		if (argList.contains("-f")) {
			String file = argList.get(argList.indexOf("-f") + 1);
			try {
				Files.readAllLines(Paths.get(file)).forEach(line -> {
					try {
						dictionary.put(line.split(delimiter)[1], Integer.parseInt(line.split(delimiter)[0]));
					} catch (NumberFormatException e) {
						System.out.println("ERROR: " + line);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<String> lines = new ArrayList<String>();
		dictionary.forEach((k, v) -> {
			lines.add(k + delimiter + v);
		});
		try {
			Files.write(Paths.get("dictionary.output"), lines);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println();
			lines.forEach(System.out::println);
		}
	}

	public void addText(String filename) {
		File file = new File(filename);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String word = scanner.next();
				if (!dictionary.containsKey(word)) {
					dictionary.put(word, 0);
				}
				dictionary.put(word, dictionary.get(word) + 1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public void mergeDictionaries(String otherDictionaryName) {
		try {
			Files.readAllLines(Paths.get(otherDictionaryName)).forEach(line -> {
				String word = line.split(delimiter)[0];
				if (!dictionary.containsKey(line)) {
					dictionary.put(word, 1);
				} else {
					int num = Integer.parseInt(line.split(delimiter)[1]);// other dictionary word count
					dictionary.put(word, dictionary.get(word) + num);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void frequency(String word) {
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		try {
			Files.readAllLines(Paths.get("dictionary.txt")).forEach(line -> {
				dictionary.put(line.split(delimiter)[0], Integer.parseInt(line.split(delimiter)[1]));
			});
			int max = Collections.max(dictionary.values());
			int count = dictionary.get(word);
			System.out.println(count / max + " " + word);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> frequency() {
		List<String> list = new ArrayList<String>();
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		try {
			Files.readAllLines(Paths.get("dictionary.txt")).forEach(line -> {
				dictionary.put(line.split(delimiter)[0], Integer.parseInt(line.split(delimiter)[1]));
			});
			int max = Collections.max(dictionary.values());
			dictionary.forEach((str, count) -> {
				list.add(count / max + " " + str);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void fileTopic(int max) {
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		try {
			Files.readAllLines(Paths.get("dictionary.txt")).forEach(line -> {
				int entryCount = Integer.parseInt(line.split(delimiter)[1]);
				if (entryCount > max)
					dictionary.put(line.split(delimiter)[0], Integer.parseInt(line.split(delimiter)[1]));
			});
			dictionary.keySet().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * this will require some testing
	 */
	public void printAnagram(String phrase) {
		List<String> splitPhrase = Arrays.asList(phrase.split(""));
		List<String> dictionaryWordList = new ArrayList<String>();
		dictionary.forEach((word, in) -> {
			dictionaryWordList.add(word);
		});
		List<String> anagram = new ArrayList<String>();
		int count = 0;
		while (splitPhrase.size() > 0 && count < dictionaryWordList.size()) {
			count++;
			boolean match = true;
			int rand = (int) Math.random() * dictionaryWordList.size();
			String dictionaryWord = dictionaryWordList.get(rand);
			for (char letter : dictionaryWord.toCharArray()) {
				if (splitPhrase.contains(letter)) {
					match = false;
				}
			}
			if (match) {
				for (char letter : dictionaryWord.toCharArray()) {
					splitPhrase.remove(splitPhrase.indexOf(letter));
				}
				anagram.add(dictionaryWord);
			}
		}
		if (splitPhrase.isEmpty()) {
			anagram.forEach(System.out::println);
		} else {
			System.out.println("No anagrams found");
		}
	}

	public void longestPrefix() {

	}

	public void translation() {

	}

	public List<Point> ottendordCipher(String phrase, String fileText) {
		List<Point> coordinates = new ArrayList<Point>();
		try {
			List<String> list = Files.readAllLines(Paths.get("input.txt"));
			for(String letter : phrase.split("")) {
				int index = 0;
				List<String> listWithLetter = new ArrayList<String>();
				while(index < list.size() && list.get(index).contains(letter)) {
					listWithLetter.add(list.get(index));
					index++;
				}
				if(listWithLetter.size() > 0) {
					String line = listWithLetter.get((int)(Math.random() * listWithLetter.size()));
					List<Integer> dexes = new ArrayList<Integer>();
					int dex = line.indexOf(letter);
					while(dex >= 0) {
						dexes.add(dex);
						dex = line.indexOf(letter, dex + 1);
					}
					int column = dexes.get((int)(Math.random() * dexes.size()));
					int row = list.indexOf(line);
					coordinates.add(new Point(row, column));
				}else {
					System.out.println("No lines contain the letter " + letter);
					return coordinates;
				}
			}
			if(coordinates.size() == phrase.split("").length) {
				return coordinates;
			}else {
				System.out.println("Cipher could not be made");
				return coordinates;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coordinates;
	}

}
