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


    public Puzzle(String fileName){
        this.path = readFile(fileName);
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

        return path;
    }

    public String[] getPath(){return path;}

    private String[] turnToArray(String fileContent){
        return fileContent.split(", ");
    }


    // 0 = north, 1 = east, 2 = south, 3 = west

    public void position(){
        int currentDirection = 0;
        int steps;

        for(int i = 0; i < path.length; i++){
            String nextMove = this.path[i];
            steps = Integer.parseInt(nextMove.substring(1,1));

            if(nextMove.substring(0, 0).equals("R")){
                currentDirection = currentDirection + 1 % 4;
            }
            else{
                currentDirection = currentDirection - 1 % 4;
            }

            if(currentDirection == 0){
                this.y = this.y - steps;
            }
            else if (currentDirection == 1){
                this.x = this.x + steps;
            }
            else if (currentDirection == 2){
                this.y = this.y + steps;
            }
            else  if (currentDirection == 3){
                this.x = this.x - steps;
            }
        }
    }

    public int distance(){
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
