class Solution {
    // Solution 1
    public int maxFreqSum(String s) {
        Map<String, Integer> vowelsFreqMap = new HashMap<>();
        vowelsFreqMap.put("a", 0);
        vowelsFreqMap.put("e", 0);
        vowelsFreqMap.put("i", 0);
        vowelsFreqMap.put("o", 0);
        vowelsFreqMap.put("u", 0);
        Map<String, Integer> consonantsFreqMap = new HashMap<>();
        int n = s.length();
        int[] maxFreq = { 0, 0 }; // maxFreq[0] for vowels, maxFreq[1] for consonants

        for (int i = 0; i < n; i++) {
            String ch = String.valueOf(s.charAt(i));
            int freq = 0;
            if (!vowelsFreqMap.containsKey(ch)) {
                freq = consonantsFreqMap.getOrDefault(ch, 0) + 1;
                consonantsFreqMap.put(ch, freq);
                maxFreq[1] = Math.max(maxFreq[1], freq);
            } else {
                freq = vowelsFreqMap.get(ch) + 1;
                vowelsFreqMap.put(ch, freq);
                maxFreq[0] = Math.max(maxFreq[0], freq);
            }
        }

        return maxFreq[0] + maxFreq[1];
    }

    // Solution 2
    public int maxFreqSum2(String s) {
        int numberOfLetters = 26;
        String vowelsString = "aeoui";
        int[] freq = new int[numberOfLetters];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;
        for (int i = 0; i < numberOfLetters; i++) {
            int ch = i + 'a';
            if (vowelsString.indexOf(ch) != -1) {
                maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
            }
        }

        return maxConsonantFreq + maxVowelFreq;
    }
}