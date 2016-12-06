import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.String;

/**
 * Created by madeleine on 2016-12-02.
 */
public class Puzzle {
    private int x = 0;
    private int y = 0;
    private String[] path;
    private int matrixIndex;

    private int[][] coordinates;
    /*private int[] previousX;
    private int[] previousY;
    */


    public Puzzle(String fileName){
        this.path = readFile(fileName);
        this.coordinates = new int[path.length][2];
        this.matrixIndex = 0;
        /*this.previousX = new int[2];
        this.previousY =  new int[2];*/
    }

    private String[] readFile(String fileName){
        String fileContent = "";
        File file = new File(fileName);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                fileContent = fileContent + i;
            }
            sc.close();
        }

        catch  (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.path = turnToArray(fileContent);
        System.out.println(this.path.toString());
        //this.path = turnToArray("R5, L5, R5, R3");

        return path;
    }

    private void increaseSize(){
        int[][] temp = new int[coordinates.length * 2][2];

        for(int i = 0; i < coordinates.length; i++){
            for(int p = 0; p < coordinates[i].length; p++){
                temp[i][p] = coordinates[i][p];
            }
        }

        this.coordinates = temp;
        /* int[] tempX = new  int[previousX.length * 2];
        int[] tempY = new  int[previousY.length * 2];

        for(int i = 0; i < this.previousX.length; i++){
            tempX[i] = previousX[i];
            tempY[i] = previousY[i];
        }
        */
    }

    public String[] getPath(){return path;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public int[][] getCoordinates(){return this.coordinates;}

    private String[] turnToArray(String fileContent){
        return fileContent.split(", ");
    }

    private boolean isInArray(){
        for(int i = 0; i < coordinates.length; i++){
            if( this.x == coordinates[i][0] && this.y == coordinates[i][1] ){
                return true;
            }

        }
        return false;
    }

   /* private void addToArrayY(int direction, int steps, int index, int p){
        if(index == previousY.length -1){
            increaseSize();
        }
        int newIndex = index;
        int count = 0;
        while(count < steps){
            previousY[newIndex] = this.y + p;
            newIndex++;

        }
    }*/

    private void addToArray(int direction, int steps, int p){
        if(matrixIndex == coordinates.length -5){
            increaseSize();
        }
        int count = 0;
        while(count < steps){
            coordinates[matrixIndex][0] = this.x + p;
            coordinates[matrixIndex][1] = this.y + p;
            this.matrixIndex++;
            count++;
        }
    }

    // 0 = north, 1 = east, 2 = south, 3 = west

    public void position(){
        int currentDirection = 0;
        int steps;
        boolean first = false;

        for(int i = 0; i < path.length; i++){
            String nextMove = this.path[i];
            steps = Integer.parseInt(nextMove.substring(1));
            System.out.println("Current: " + coordinates[i][0] + ", " + coordinates[i][1]);

            if(first) {
                if (isInArray()) {
                    break;
                }
            }
            first = true;
            if(nextMove.substring(0, 1).equals("R")){
                currentDirection = (currentDirection + 1) % 4;
            }
            else{
                currentDirection = (currentDirection + 3)  % 4;
            }

            if(currentDirection == 0){
                int p = - 1;
                addToArray(currentDirection, steps, p );
                this.y = this.y - steps;
            }
            else if (currentDirection == 1){
                int q = + 1;
                addToArray(currentDirection, steps, q );
                this.x = this.x + steps;
            }
            else if (currentDirection == 2){
                int w = + 1;
                addToArray(currentDirection, steps, w);
                this.y = this.y + steps;
            }
            else  if (currentDirection == 3){
                int e = - 1;
                addToArray(currentDirection, steps, e);
                this.x = this.x - steps;
            }
                matrixIndex++;
        }
    }

    public int distance(){
        return Math.abs(this.x) + Math.abs(this.y);
    }

    public int distance(int x, int y){
        return Math.abs(this.x) + Math.abs(this.y);
    }


    /*private String[] increaseSize(String[] old){
        String[] newVersion = new String[old.length * 2];
        for(int i = 0; i < old.length; i++){
            newVersion[i] = old[i];
        }

        return newVersion;
    }
    */

}
