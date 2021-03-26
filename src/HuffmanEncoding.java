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
            assert HuffmanTree != null;
            System.out.print(HuffmanTree.getCode(sentence.charAt(i)));
        }

    }
}









