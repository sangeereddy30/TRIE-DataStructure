### **Explanation of the Trie Implementation**
This Java program implements a **Trie (Prefix Tree)** and supports operations like:
1. **Inserting a word** into the Trie (`insertWord`).
2. **Checking if a word exists** in the Trie (`hasWord`).
3. **Retrieving all words** stored in the Trie (`getAllWords`).
4. **Retrieving words with a specific prefix** (`getAllWordsWithPrefix`).

---

### **Breakdown of the Code**
#### **1️⃣ TNode Class (Trie Node)**
```java
class TNode { // TRIE Node
    boolean isEOW = false; // Indicates if this node is the end of a word
    TNode[] children = new TNode[26]; // Array of pointers to child nodes (a-z)
}
```
- Each node has a **boolean flag (`isEOW`)** to mark if it's the end of a word.
- It also has an array `children` of size **26** (one for each letter a-z).

---

#### **2️⃣ Trie Class (Trie Operations)**
```java
class Trie {
    TNode root = new TNode(); // Root node of the Trie
```
- The Trie is initialized with an empty **root node**.

##### **📌 insertWord (Insert a Word into Trie)**
```java
void insertWord(String word){
    TNode temp = root;
    for(char ch : word.toCharArray()){
        int idx = ch - 'a'; // Convert character to index (0-25)
        if(temp.children[idx] == null){
            TNode nn = new TNode(); // Create new node if missing
            temp.children[idx] = nn;
            temp = nn;
        } else {
            temp = temp.children[idx]; // Move to next node
        }
    }
    temp.isEOW = true; // Mark the last node as the end of the word
}
```
✅ **Time Complexity:** **O(len(word))** (each character is processed once).  
✅ **Space Complexity:** **O(N × M)** (where N is the number of words and M is the avg. word length).  

📌 **How It Works?**
- Traverse through the Trie, creating new nodes as necessary.
- Set `isEOW = true` at the last character to mark it as a valid word.

---

##### **📌 hasWord (Check If Word Exists)**
```java
boolean hasWord(String word){
    TNode temp = root;
    for(char ch : word.toCharArray()){
        int idx = ch - 'a';
        if(temp.children[idx] == null){
            return false; // Word doesn't exist
        }
        temp = temp.children[idx];
    }
    return temp.isEOW; // Only return true if it's marked as end-of-word
}
```
✅ **Time Complexity:** **O(len(word))** (each character is checked once).  

📌 **How It Works?**
- Traverse through the Trie following each character.
- If a character is missing, return `false`.
- Return `true` **only if** `isEOW` is `true` (to ensure partial prefixes aren't mistaken for words).

---

##### **📌 getAllWords (Retrieve All Words)**
```java
List<String> getAllWords(){
    List<String> ans = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    helper(root, ans, path);
    return ans;
}
```
- Calls a recursive function `helper()` to collect all words.

##### **📌 helper (Recursive DFS for Trie Traversal)**
```java
void helper(TNode root, List<String> ans, StringBuilder path){
    if(root.isEOW){
        ans.add(path.toString()); // Store word when end is reached
    }
    for(int idx = 0; idx < 26; idx++){
        if(root.children[idx] != null){
            char ch = (char)(idx + 'a');
            path.append(ch);
            helper(root.children[idx], ans, path);
            path.setLength(path.length()-1); // Backtrack
        }
    }
}
```
✅ **Time Complexity:** **O(N × M)** (visits all nodes in the Trie).  

📌 **How It Works?**
- Performs a **Depth-First Search (DFS)** to find all words.
- Uses **StringBuilder** for efficient string manipulation.
- **Backtracking** (`path.setLength(path.length()-1)`) ensures we remove the last character after processing a branch.

---

##### **📌 getAllWordsWithPrefix (Find Words Matching a Prefix)**
```java
List<String> getAllWordsWithPrefix(String prefix){
    TNode temp = root;
    List<String> ans = new ArrayList<>();
    for(char ch : prefix.toCharArray()){
        int idx = ch - 'a';
        if(temp.children[idx] != null)
            temp = temp.children[idx]; // Move deeper
        else {
            return ans; // No words found
        }
    }
    StringBuilder path = new StringBuilder(prefix);
    helper(temp, ans, path); // Call DFS helper from prefix node
    return ans;
}
```
✅ **Time Complexity:** **O(len(prefix) + M)** (where M is the number of matching words).  

📌 **How It Works?**
1. Traverse Trie until reaching the last character of `prefix`.
2. Call `helper()` to collect all words starting from this node.

---

### **3️⃣ Main Function (Testing the Trie)**
```java
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
```
### **🛠 Example Run**
✅ **Input Words Inserted:**
```
["zebra", "nation", "nations", "national", "apple", "pretty", 
 "prettier", "prettiest", "car", "cards", "cart"]
```

✅ **getAllWordsWithPrefix("ca") Output:**
```
["car", "cards", "cart"]
```
### **🎯 Summary**
✅ **Efficient storage** (Trie avoids redundant prefixes).  
✅ **Fast lookup** (`O(M)`, much faster than `O(N)` in brute force search).  
✅ **Recursive DFS for collecting words**, using **backtracking**.  
✅ **Used prefix search**, allowing efficient autocomplete-like functionality.  

This is a **basic implementation** of a Trie, and it can be extended for more advanced applications like **word suggestions, spell checking, and even search engines!** 🚀
