package data_structure.tree.AVL.Lab529.L1;

import java.util.ArrayList;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/29
 * \* Time: 21:12
 * \* Description:
 * \
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt",words);
        System.out.println("Total words: " + words.size());
        AVLTree<String,Integer> wordsMap = new AVLTree();
        for (String word : words) {
            if(wordsMap.contains(word)){
                wordsMap.set(word,wordsMap.get(word)+1);
            }else {
                wordsMap.add(word,1);
            }
        }
        System.out.println("Total different words: " + wordsMap.getSize());
        System.out.println("Frequency of PRIDE "+wordsMap.get("pride"));
        System.out.println("Frequency of is "+wordsMap.get("is"));
    }
}
