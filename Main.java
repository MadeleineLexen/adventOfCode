/**
 * Created by madeleine on 2016-12-04.
 */
public class Main {
    public static void main(final String[] args){

        System.out.println("Hello World");

        Puzzle santa = new Puzzle( "path");

        /*for( int i = 0; i < santa.getPath().length; i++){
            System.out.println(santa.getPath()[i]);
        }*/

        santa.position();

        System.out.println(santa.distance());

    }
}
