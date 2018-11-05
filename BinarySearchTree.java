import java.util.*;
import java.io.*;
/**
 * This is a basic tree program that will take string inputs from a file and 
 * build a binary search tree with them. Then it prints out the traversal for 
 * pre-order, in-order and post-order to a text file named OutputFile.txt.
 *
 * @author Bryant Vaughn
 * @version 10/2017
 */
public class BinarySearchTree
{
    private static Node root;

    //BinarySearchTree constructor
    public BinarySearchTree()
    {
        root = null;
    }

    //This method builds a binary search tree with recursion
    public void insertTreeNodeRec(Node recRoot, String str)
    {
        if(root == null)
        {
            root = new Node(str);
        }
        else if(str.equals(recRoot.data))
        {
            recRoot.counter++;
        }
        else if(str.compareTo(recRoot.data) < 0)
        {
            if(recRoot.left == null)
            {
                recRoot.left = new Node(str);
            }
            else
            {
                insertTreeNodeRec(recRoot.left, str);
            }
        }
        else
        {
            if(recRoot.right == null)
            {
                recRoot.right = new Node(str);
            }
            else
            {
                insertTreeNodeRec(recRoot.right, str);
            }
        }
    }
    
    //This method builds the string for pre-order traversal
    public String printPreOrder(Node node)
    {
        String preOrder = "";
        if(node != null)
        {
            preOrder += "word: " + node.data + ", count: " + node.counter + "\r\n";
            preOrder += printPreOrder(node.left);
            preOrder += printPreOrder(node.right);
        }
        return preOrder;
    }

    //This method builds the string for in-order traversal
    public String printInOrder(Node node)
    {
        String inOrder = "";
        if(node != null)
        {
            inOrder += printInOrder(node.left);
            inOrder += "word: " + node.data + ", count: " + node.counter + "\r\n";
            inOrder += printInOrder(node.right);
        }
        return inOrder;
    }
    
    //This method builds the string for post-order traversal
    public String printPostOrder(Node node)
    {
        String postOrder = "";
        if(node != null)
        {
            postOrder += printPostOrder(node.left);
            postOrder += printPostOrder(node.right);
            postOrder += "word: " + node.data + ", count: " + node.counter + "\r\n";
        }
        return postOrder;
    }

    //Main method
    public static void main(String[] args)
    {
        BinarySearchTree myTree = new BinarySearchTree();
        try
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter your file path and file name with extension: ");
            String fileInputName = keyboard.nextLine();
            FileInputStream file = new FileInputStream(fileInputName);
            BufferedWriter writer = new BufferedWriter(new FileWriter("OutputFile.txt"));
            Scanner input = new Scanner(file);
            
            //While loop to read in all words from input file
            while(input.hasNextLine())
            {
                StringTokenizer strToken = new StringTokenizer(input.nextLine());
                while(strToken.hasMoreTokens())
                {
                    myTree.insertTreeNodeRec(root, strToken.nextToken());
                }
            }
            input.close();
            
            //Formatting and building the output file string
            String output = "Bryant Vaughn\r\n";
            output += "CS 2 Fall 2017\r\n";
            output += "Binary Search Tree Output File\r\n";
            output += "\r\nPre-order Traversal Output:\r\n";
            output += myTree.printPreOrder(root) + "\r\n";
            output += "In-order Traversal Output:\r\n";
            output += myTree.printInOrder(root) + "\r\n";
            output += "Post-order Traversal Output:\r\n";
            output += myTree.printInOrder(root) + "\r\n";
            //writer.write(output);
            System.out.print(output);
            
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Private class for Nodes
    private class Node
    {
        private String data;
        private int counter;
        private Node left;
        private Node right;

    //Node class constructor
        public Node(String data)
        {
            this.data = data;
            counter = 1;
            left = null;
            right = null;
        }
        
    //This method will print an individual Node's data
        public void printNode(Node node)
        {
            System.out.print(node.data);
        }
    }
}