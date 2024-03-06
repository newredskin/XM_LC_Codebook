class Solution {
    Map<Character, Set<Character>> adjList = new HashMap<>();
    Map<Character, Boolean> visited = new HashMap<>(); // visited = false; current path = true
    Stack<Character> resStack = new Stack<>();

    public String alienOrder(String[] words) {
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!adjList.containsKey(c)) adjList.put(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];

            int minLen = Math.min(word1.length(), word2.length());

            //[apple, app] edge case
            if (word2.length() < word1.length() && word1.substring(0, minLen).equals(word2)) return "";



            for (int j = 0; j < minLen; j++) {
                if(word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
            }
        }

        for (char ch : adjList.keySet()) {
            if(dfs(ch)) return "";
        }

        StringBuilder res = new StringBuilder();
        while (!resStack.isEmpty()) {
            res.append(resStack.pop());
        }

        return res.toString();
    }

    private boolean dfs(char c) {
        if (visited.containsKey(c)) return visited.get(c);

        visited.put(c, true);//in current path

        for (char ch : adjList.get(c)) {
            if (dfs(ch)) return true;
        }

        visited.put(c, false);//mark it as not current path
        resStack.add(c);

        return false;
    }
}

/*
Time - O(n*L+v)
Space - O(n+v)
*/
