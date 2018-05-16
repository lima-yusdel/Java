package lse;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * This class builds an index of keywords. Each keyword maps to a set of pages in
 * which it occurs, with frequency of occurrence in each page.
 * 
 * By Yusdel Lima Lorenzo
 *
 */
public class SearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in 
	 * DESCENDING order of frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash set of all noise words.
	 */
	HashSet<String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashSet<String>(100,2.0f);
	}
	
	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeywordsFromDocument(String docFile) 
	throws FileNotFoundException 
	{
		if(docFile == null)
		{
			throw new FileNotFoundException();
		}
		HashMap<String, Occurrence> map = new HashMap<String, Occurrence>();
		Scanner sc = new Scanner(new File(docFile));
		while (sc.hasNext()) 
		{
			
			String word = sc.next();
			word = getKeyword(word);
			if(word != null)
			{
				if(map.containsKey(word))
				{
					Occurrence temp = map.get(word);
					temp.frequency++;
				}
				else
				{
					Occurrence temp = new Occurrence(docFile, 1);
					map.put(word, temp);
				}
			}

		}
		sc.close();
		return map;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeywords(HashMap<String,Occurrence> kws) 
	{
		for(String key : kws.keySet())
		{
			if(keywordsIndex.containsKey(key)== false)
			{
				ArrayList<Occurrence> tempArray = new ArrayList<Occurrence>();
				Occurrence tempOccur = kws.get(key);
				tempArray.add(tempOccur);
				insertLastOccurrence(tempArray);
				keywordsIndex.put(key, tempArray);
			}
			else
			{
				ArrayList<Occurrence> tempArray2 = keywordsIndex.get(key);
				Occurrence tempOccur2 = kws.get(key);
				tempArray2.add(tempOccur2);
				insertLastOccurrence(tempArray2);
				keywordsIndex.put(key, tempArray2);
			}
		}
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * trailing punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyword(String word) {
		int length = word.length();
		int i = 0;
		boolean bool = false;
		for(i = 0; i < length; i++)
		{
			if(i+1 == length)
			{
				bool = true;
			}
			if(Character.isLetter(word.charAt(i))==false && word.charAt(i)!= ',' && word.charAt(i)!= '.'&& word.charAt(i)!= '?'&& word.charAt(i)!= ':'&& word.charAt(i)!= ';'&& word.charAt(i)!= '!')
			{
				//is a number
				return null;
			}
			if(word.charAt(i) == ':' || word.charAt(i) == ',' || word.charAt(i) == ';')
			{
				if(i+1 != length)
				{
					return null;
				}
				else
				{
					word = word.substring(0, length-1);
					break;
				}
			}
			if(word.charAt(i) == '.' || word.charAt(i) == '!' || word.charAt(i) == '?')
			{
				int k = 0;
				for(k = i; k < length; k++)
				{
					if(k+1 == length)
					{
						bool = true;
					}

				}
				if(bool = true)
				{
					word = word.substring(0, i);
					
				}
				break;
			}
		}
		word = word.toLowerCase();
		
		if(noiseWords.contains(word))
		{
			return null;
		}
		else
		{
			return word;
		}
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion is done by
	 * first finding the correct spot using binary search, then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		
		ArrayList<Integer> array = new ArrayList<Integer>(5);
		int size = occs.size();
		if(size == 1)
		{
			return null;
		}
		else
		{
			Occurrence temp = occs.get(occs.size()-1);
			occs.remove(occs.size()-1);
			
			int lower = occs.size()-1;
			int mid = 0;
			int higher = 0;
			int i = temp.frequency;
			
			while(higher <= lower)
			{
				mid = (lower + higher) / 2;
				Occurrence midOccur = occs.get(mid);
				int last = midOccur.frequency;
				if(last == i)
				{
					array.add(mid);
					break;
				}
				if(last > i)
				{
					higher = mid + 1;
					array.add(mid);
					mid = mid+1;
				}
				if(last < i)
				{
					lower = mid-1;
					array.add(mid);
				}
			}
			occs.add(mid, temp);	
			
		}
		return array;
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.add(word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeywordsFromDocument(docFile);
			mergeKeywords(kws);
		}
		sc.close();
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of document frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will take precedence over doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matches at all, result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matches, returns null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		ArrayList<String> finalArray = new ArrayList<String>();
		ArrayList<Occurrence> array1 = new ArrayList<Occurrence>();
		ArrayList<Occurrence> array2 = new ArrayList<Occurrence>();
		ArrayList<Occurrence> sortedArray = new ArrayList<Occurrence>();
		
		if(keywordsIndex.containsKey(kw1))
		{
			array1 = keywordsIndex.get(kw1);
		}
		if(keywordsIndex.containsKey(kw2))
		{
			array2 = keywordsIndex.get(kw2);
		}
		sortedArray.addAll(array1);
		sortedArray.addAll(array2);
		
		if(!(array1.isEmpty()) && (array2.isEmpty()))
		{
			for(int i = 0; i<sortedArray.size()-1; i++)
			{
				for(int k = 1; k < sortedArray.size()-i; k++)
				{
					Occurrence sort = sortedArray.get(k-1);
					sortedArray.set(k-1, sortedArray.get(k));
					sortedArray.set(k, sort);
				}	
			}
		}
		
		for(int i = 0; i<sortedArray.size()-1; i++)
		{
			for(int k = i +1; k < sortedArray.size(); k++)
			{
				if(sortedArray.get(i).document == sortedArray.get(k).document)
				{
					sortedArray.remove(k);
				}
			}
		}
		
		while(sortedArray.size() > 5)
		{
			sortedArray.remove(sortedArray.size()-1);	
		}
		
		for(Occurrence temp : sortedArray)
		{
			finalArray.add(temp.document);
		}
		return finalArray;
	
	}
}
