// wild card chars type. pick and do not pick. we get repeated sub problems. So DP
class Solution {
    // 2 test cases discussed
    // s - adceb; p - *a*b
    // s - acdcb; p - a*c?b 
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        if( p == null || p.length() == 0) {
            return false;
        }

        int m = p.length();
        int n = s.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i < m + 1; ++i) {
            for (int j = 0; j < n + 1; ++j) {
                // when checking *a* and ad
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j]; // 0 case: *a with ad
                    if (j > 0) {
                        dp[i][j] = dp[i][j] || dp[i][j - 1]; // 1 case: *a*d with ad. So *a* with a.
                    }
                } else {
                    // when j = 0, trying to match a empty string in s with lowecase or ?. So false.
                    if ((j > 0) && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?')) {
                        dp[i][j] = dp[i - 1][j - 1]; // diagonal
                    }
                }
            }
        }

        return dp[m][n];

    }
}