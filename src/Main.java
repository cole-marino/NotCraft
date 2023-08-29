package NotMC.src;


import NotMC.src.render.MasterRenderer;


import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;



public class Main 
{

    public static void main(String[] args){
        System.out.println("\n\nStarted\n");
        new Game().runGame(1920, 1080, "1.0.0");

    }
}