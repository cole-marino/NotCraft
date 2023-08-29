package NotMC.src.window;


import org.lwjgl.opengl.GL11;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;



public class GameWindow {
    
    private int width, height;
    private String title;
    private long window;


    boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    //CONSTRUCTOR
    public GameWindow(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
    }


    public long createWindow(){
                if(!GLFW.glfwInit()){
                    System.err.println("ERROR: couldn't initialize GLFW");
                    System.exit(-1);
                }
                

                

                GLFW.glfwDefaultWindowHints();
                GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
                GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);


//CREATES GAME WINDOW
window = GLFW.glfwCreateWindow(width, height, title, 0, 0); //first zero is if the window is in fill screen while the second zer

		
                    if(window == 0) {
                        System.err.println("ERROR: Window couldn't be created.");
                        System.exit(-1);
                    }
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width()-width)/2 , (videoMode.height()-height)/2);
        
        
        //shows the window
        GLFW.glfwShowWindow(window);
        

        GLFW.glfwMakeContextCurrent(window);    
        GL.createCapabilities();

        return window;
    }

}