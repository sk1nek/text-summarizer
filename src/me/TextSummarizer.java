package me;

import java.util.*;

public class TextSummarizer {

    public String summarizeText(String input, int maxSentences) {

        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(input.split(" ")));

        HashSet<String> keywords = getKeywords(words);

        ArrayList<String> sentenceList = new ArrayList<>();
        sentenceList.addAll(Arrays.asList(input.split(" *[\\.\\?!][\\'\"\\)\\]]* *")));

//        sentenceList.forEach(System.out::println);

//        SortedMap<Double, String> sentenceWeightMap = new TreeMap<>();
//
//        for (String sentence : sentenceList)
//            sentenceWeightMap.put(computeSentenceWeight(sentence, keywords), sentence);


        List<Map.Entry<Double, String>> entryList = new ArrayList<>();

        for(String sentence: sentenceList)
            entryList.add(Map.entry(computeSentenceWeight(sentence, keywords), sentence));

//        entryList.forEach(System.out::println);

//        entryList.addAll(sentenceWeightMap.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getKey));

        StringBuilder ret = new StringBuilder();
        int sentenceCount = maxSentences < sentenceList.size() ? maxSentences : sentenceList.size();

        for (int i = 0; i < sentenceCount; i++)
            ret.append('*' + entryList.get(i).getValue() + '.');

        System.out.println("Size reduced by :" + ret.length() / input.length() * 1.0 + '%');

        return ret.toString();

    }

    private HashSet<String> getKeywords(ArrayList<String> words){
        HashSet<String> ret = new HashSet<>();

        HashMap<String, Integer> countMap = new HashMap<>();

//        for(String s: words){
//            countMap.computeIfPresent(s, (k,v) -> v+1);
//            countMap.computeIfAbsent(s, (key) -> countMap.put(s, 0));
//        }

        for(String s: words){
            if(countMap.get(s) != null)
                countMap.compute(s, (k,v) -> v++);
            else
                countMap.put(s, 0);
        }

        countMap.forEach( (word,count) -> {
            double wordPercentage = countMap.get(word) * 1.0 / words.size();
            if( wordPercentage <= 0.5 && wordPercentage >= 0.05)
                ret.add(word);
        });

        return ret;
    }

    @SuppressWarnings("ConstantConditions")
    private double computeSentenceWeight(String sentence, Set<String> keywords){

        int windowEnd = 0, windowStart = 0;

        String[] sentenceArray = sentence.split(" ");

        //compute window end
        for(int i=0; i < sentenceArray.length; i++ ) {
            if(keywords.contains(sentenceArray[i])){
                windowEnd = i;
                break;
            }
        }

        //compute window start
        for (int i = sentenceArray.length - 1; i > 0; i--) {
            if(keywords.contains(sentenceArray[i])){
                windowEnd = i;
                break;
            }
        }

        if(windowStart > windowEnd) return 0;

        int windowSize = windowEnd - windowStart + 1;

        //number of keywords
        int keywordsCount = 0;
        for(String word: sentenceArray){
            if(keywords.contains(word))
                keywordsCount++;
        }

        return keywordsCount * keywordsCount * 1.0 / windowSize;



    }

    private ArrayList<String> tokenizeString(String s){
        ArrayList<String> ret = new ArrayList<>();
        StringTokenizer t = new StringTokenizer(s);
        while(t.hasMoreTokens())
            ret.add(t.nextToken());
        return ret;

    }



}
