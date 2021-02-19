import java.util.*;

public class HuffmanEncoding {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();

        int[] array = new int[256]; //an array to store all the frequencies

        for (int i = 0; i < sentence.length(); i++) { //go through the sentence
            array[(int) sentence.charAt(i)]++; //increment the appropriate frequencies
        }

        PriorityQueue<Tree> PQ = new PriorityQueue<Tree>(); //make a priority queue to hold the forest of trees

        for (int i = 0; i < array.length; i++) { //go through frequency array
            if (array[i] > 0) { //print out non-zero frequencies - cast to a char

                char b = (char)(i);
                Node n1 = new Node();
                n1.letter = b;
                n1.smallestLetter = b;
                Tree huff = new Tree();
                huff.frequency = array[i];
                huff.root = n1;
                PQ.add(huff);


            }
        }


        while (PQ.size() > 1) { //while there are two or more Trees left in the forest

            Tree first = PQ.poll();
            char firstSmall = first.root.smallestLetter;
            Tree second = PQ.poll();
            char secondSmall = second.root.smallestLetter;


            Tree combo = new Tree();
            Node n2 = new Node();
            if (firstSmall > secondSmall ) {
                n2.smallestLetter = secondSmall;
            } else {
                n2.smallestLetter = firstSmall;
            }
            n2.leftChild = first.root;
            n2.rightChild = second.root;

            combo.root = n2;
            combo.frequency = first.frequency + second.frequency;
            PQ.add(combo);


        }

        Tree HuffmanTree = PQ.poll(); //now there's only one tree left - get its codes

        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence

        for (int i = 0; i < sentence.length(); i++) {
            System.out.print(HuffmanTree.getCode(sentence.charAt(i)));
        }

    }
}


class Node {


    public char letter = '@'; //stores letter
    public char smallestLetter = '@';  //a variable to keep track of the tree's smallest letter

    public Node leftChild; // this node's left child
    public Node rightChild; // this node's right child


} // end class Node





class Tree implements Comparable < Tree > {
    public Node root; // first node of tree
    public int frequency = 0;

    public Tree() // constructor
    {
        root = null;
    } // no nodes in tree yet

    //the PriorityQueue needs to be able to somehow rank the objects in it
    //thus, the objects in the PriorityQueue must implement an interface called Comparable

    public int compareTo(Tree object) {
        if (frequency - object.frequency > 0) { //compare the cumulative frequencies of the tree
            return 1;
        } else if (frequency - object.frequency < 0) {
            return -1; //return 1 or -1 depending on whether these frequencies are bigger or smaller
        } else {
            // Sort based on letters
            char a = this.root.smallestLetter;
            char b = object.root.smallestLetter;

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
            return 0;
        }
    }


    String path = "error"; //this variable will track the path to the letter we're looking for

    public String getCode(char letter) { //we want the code for this letter

        return this._getCode(letter, this.root, ""); //return the path that results
    }

    private String _getCode(char letter, Node current, String path) {
        if (current == null) {
            return null;
        }

        if (current.letter == letter) {
            return path;
        }

        String leftPath = this._getCode(letter, current.leftChild, path + "0");
        if (leftPath != null) {
            return leftPath;
        }

        String rightPath = this._getCode(letter, current.rightChild, path + "1");
        return rightPath;
    }

} // end class Tree
