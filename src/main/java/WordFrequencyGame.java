import java.util.*;

// todo 2024//11/20 14:05:
// useless - if else
// rename, getResult, input, arr, exception msg
// magic string '/'
// stream instead of for loop
// extract: method
// temp field? inputList = list;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {

        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the wordToFrequency for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToFrequency = getListMap(wordFrequencyList);

                List<WordFrequency> tempWordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToFrequency.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    tempWordFrequencyList.add(wordFrequency);
                }
                wordFrequencyList = tempWordFrequencyList;

                wordFrequencyList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String s = wordFrequency.getValue() + " " + wordFrequency.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> frequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       frequencyMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!frequencyMap.containsKey(wordFrequency.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                frequencyMap.put(wordFrequency.getValue(), arr);
            } else {
                frequencyMap.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }

        return frequencyMap;
    }

}
