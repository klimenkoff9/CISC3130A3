package homework;

// Class: CISC 3130
//  Section: MY9
//  EmplId: 23836677
//  Name: Denys Klimenkov

import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception{
        File file = new File("/Users/klimenkoff9/Desktop/Java/assigment3/movies.csv");
        Scanner sc = new Scanner(file);
        String [] row = new String[3];
        BinaryTree tree = new BinaryTree();
        String line;

        while(sc.hasNextLine()){ // process the csv file line by line
            String title = "";
            String releaseYear = "";

            line = sc.nextLine();
            row = line.split(",");
            /*parse the title and the realease year for each title extracted from the csv file*/
            for (int i = 0; i < row[1].length() ;i++ ) {
                if (Character.isLetter(row[1].charAt(i)) || Character.isWhitespace(row[1].charAt(i))) {
                    title = title + (row[1].charAt(i));
                }else{
                    releaseYear = releaseYear + (row[1].charAt(i));
                }
            }
            // insert the movie to the tree
            tree.insert(new Movie(title, releaseYear));
        }

        //tree.subset(tree.getRoot(),"Bug's Life", "Harry Potter");
        //tree.subset(tree.getRoot(),"Back to the Future", "Hulk");
        tree.subset(tree.getRoot(),"Toy Story", "WALL-E");


    }
}

class Movie {
    private String title;
    private String releaseYear;
    public Movie left;
    public Movie right;

    //constructor
    public Movie(String title, String releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
    }
    //getters
    public String getTitle(){
        return title;
    }
    public String getReleaseYear(){
        return releaseYear;
    }
    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

}

class BinaryTree{
    private Movie root;
    PrintStream ps ;

    // constructor
    public BinaryTree()throws Exception{
        root = null;
        ps = new PrintStream("/Users/klimenkoff9/Desktop/Java/assigment3/output/SAMPLE3.txt");


    }
    // getters
    public Movie getRoot(){
        return this.root;
    }
    // inserts movie in the tree
    public void insert(Movie movie){
        if(this.root == null){ // if tree is empty
            movie.left = this.root;
            movie.right = this.root;
            root = movie;
        }else{
			// if tree is not empty then another method is invoked which will insert the movie in the tree.
            add(movie, this.root);

        }
    }

    private void add(Movie movie, Movie root){

        if(movie.getTitle().compareToIgnoreCase(root.getTitle()) < 0){
            // once there are no more elements to traverse the movie is inserted on the left side of the parent
            if (root.left == null){
                movie.left = root.left;
                movie.right = root.left;
                root.left = movie;
            }else{
                // traverses the tree recursively until null
                add(movie, root.left);
            }
        }else{ // inserts the movie on the right
            if(root.right == null){
                movie.left = root.right;
                movie.right = root.right;
                root.right = movie;
            }else{
                add(movie, root.right);
            }
        }
    }
    /*The method below prints the elements of the tree using a inorder traversal*/
    public void displayTree(Movie head){

        if(head == null){ // if tree is empty
            ps.println("tree is empty");
        }else{

            if(head.left != null){ // prints the left child
                displayTree(head.left);
            }

            ps.println(head.getTitle()); // prints the parent

            if (head.right != null) { // prints the right child
                displayTree(head.right);
            }


        }
    }
    /* The method below prints the elements of any given range within the tree*/
    public void subset(Movie head, String str1, String str2){
        if(head == null){ // base
            return;
        }

        if(str1.compareToIgnoreCase(head.getTitle()) < 0){
            subset(head.left, str1, str2);
        }
        // the title of the movie is printed once the element inside the given range
        if((head.getTitle().compareToIgnoreCase(str1) >= 0) && (head.getTitle().compareToIgnoreCase(str2) <= 0)){
            ps.println(head.getTitle());
        }

        if(str2.compareToIgnoreCase(head.getTitle()) > 0){
            subset(head.right, str1, str2);
        }
    }

}