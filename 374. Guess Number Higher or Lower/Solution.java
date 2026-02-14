public class Solution extends GuessGame {
    // Brute force
    public int guessNumber(int n) {
        for(int i = 1; i <= n; i++) {
            if(guess(i) == 0) {
                return i;
            }
        }

        return -1;
    }

    // Binary Search
    public int guessNumberByBinarySearch(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left - (left - right) / 2;
            int guessResult= guess(mid);
            if(guessResult == 0) {
                return mid;
            } else if(guessResult == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
