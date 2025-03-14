import java.util.*;

class TNode { // TRIE Node
    boolean isEOW = false; // is end of word 
    TNode[] children = new TNode[26]; // all 26 pointers are null
}

class Trie {
    TNode root = new TNode(); // TRIE always has a root node

    void insertWord(String word){ // TC: O(len(word))
        TNode temp = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(temp.children[idx] == null){
                TNode nn = new TNode();
                temp.children[idx] = nn;
                temp = nn;
            } else {
                temp = temp.children[idx];
            }
        }
        temp.isEOW = true;
    }
    boolean hasWord(String word){ // TC: O(len(word))
        TNode temp = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(temp.children[idx] == null){
                return false;
            } else {
                temp = temp.children[idx];
            }
        }
        if(temp.isEOW == true) return true;
        else return false;
    }
    List<String> getAllWords(){
        List<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        helper(root, ans, path);
        return ans;
    }
    void helper(TNode root, List<String> ans, StringBuilder path){
        if(root.isEOW == true){
            ans.add(path.toString());
        }
        for(int idx = 0; idx < 26; idx++){
            if(root.children[idx] != null){
                char ch = (char)(idx + 'a');
                path.append(ch);
                helper(root.children[idx], ans, path);
                path.setLength(path.length()-1);
            }
        }
    }

    List<String> getAllWordsWithPrefix(String prefix){
        TNode temp = root;
        List<String> ans = new ArrayList<>();
        for(char ch : prefix.toCharArray()){
            int idx = ch - 'a';
            if(temp.children[idx] != null)
                temp = temp.children[idx];
            else {
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
        System.out.println(t.getAllWordsWithPrefix("ca"));
    }
}
