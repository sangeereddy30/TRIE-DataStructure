import java.util.*;

class TNode { // TRIE Node
    boolean isEOW = false; // is end of word 
    TNode[] children = new TNode[26]; // all 26 pointers are null
}

class Trie {
    TNode root = new TNode(); // TRIE always has a root node

    void insertWord(String word) { // TC: O(len(word))
        System.out.println("Inserting word: " + word);
        TNode temp = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (temp.children[idx] == null) {
                temp.children[idx] = new TNode();
                System.out.println("  Created new node for character: " + ch);
            }
            temp = temp.children[idx];
        }
        temp.isEOW = true;
        System.out.println("  Marked end of word: " + word + "\n");
    }

    boolean hasWord(String word) { // TC: O(len(word))
        System.out.println("Searching for word: " + word);
        TNode temp = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (temp.children[idx] == null) {
                System.out.println("  Word not found: " + word);
                return false;
            }
            temp = temp.children[idx];
        }
        if (temp.isEOW) {
            System.out.println("  Word found: " + word);
            return true;
        }
        System.out.println("  Word exists as a prefix but not a full word: " + word);
        return false;
    }

    List<String> getAllWords() {
        System.out.println("Fetching all words in Trie...");
        List<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        helper(root, ans, path);
        return ans;
    }

    void helper(TNode root, List<String> ans, StringBuilder path) {
        if (root.isEOW) {
            ans.add(path.toString());
            System.out.println("  Found word: " + path.toString());
        }
        for (int idx = 0; idx < 26; idx++) {
            if (root.children[idx] != null) {
                char ch = (char) (idx + 'a');
                path.append(ch);
                helper(root.children[idx], ans, path);
                path.setLength(path.length() - 1);
            }
        }
    }

    List<String> getAllWordsWithPrefix(String prefix) {
        System.out.println("Fetching words with prefix: " + prefix);
        TNode temp = root;
        List<String> ans = new ArrayList<>();
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (temp.children[idx] != null)
                temp = temp.children[idx];
            else {
                System.out.println("  No words found with prefix: " + prefix);
                return ans;
            }
        }
        StringBuilder path = new StringBuilder(prefix);
        helper(temp, ans, path);
        return ans;
    }
}

class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insertWord("zebra"); t.insertWord("nation"); t.insertWord("nations");
        t.insertWord("national"); t.insertWord("apple");
        t.insertWord("pretty"); t.insertWord("prettier");
        t.insertWord("prettiest");
        t.insertWord("car"); t.insertWord("cards"); t.insertWord("cart");
        
        System.out.println("\nAll words in Trie: " + t.getAllWords());
        
        System.out.println("\nChecking if words exist:");
        System.out.println("Is 'car' in Trie? " + t.hasWord("car"));
        System.out.println("Is 'cart' in Trie? " + t.hasWord("cart"));
        System.out.println("Is 'carry' in Trie? " + t.hasWord("carry"));
        
        System.out.println("\nWords with prefix 'ca': " + t.getAllWordsWithPrefix("ca"));
        System.out.println("Words with prefix 'pre': " + t.getAllWordsWithPrefix("pre"));
        System.out.println("Words with prefix 'nat': " + t.getAllWordsWithPrefix("nat"));
        System.out.println("Words with prefix 'ze': " + t.getAllWordsWithPrefix("ze"));
        System.out.println("Words with prefix 'xy': " + t.getAllWordsWithPrefix("xy"));
    }
}

  
