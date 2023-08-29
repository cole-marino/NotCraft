package NotMC.src;

import NotMC.src.models.RawModel;
import NotMC.src.models.TexturedModel;
import NotMC.src.render.Loader;
import NotMC.src.render.MasterRenderer;
import NotMC.src.shaders.StaticShaderBLOCKS;
import NotMC.src.textures.ModelTextures;
import NotMC.src.window.GameWindow;

import org.lwjgl.glfw.GLFW;

public class Game
{

    public static MasterRenderer renderer1;
    public static Loader loader1; 
    public static StaticShaderBLOCKS shader1;


    public float[] vertices = {
        -0.5f, 0.5f, 0,
        -0.5f, -0.5f, 0,
        0.5f, -0.5f, 0,
        0.5f, 0.5f, 0,
    };

    int[] indices = {

        0,1,2,
        2,3,0

    };
    
    public float[] uvCoords = {
        0,0,
        0,1,
        1,1,
        1,0
    };

    GameWindow gamer;


    MasterRenderer renderer;
    Loader loader;
    StaticShaderBLOCKS shader;


    RawModel model;
    ModelTextures textures;
    TexturedModel texturedModel;
    
    

    private int width, height;
    private String title;
    protected long window;

    

    public void runGame(int width, int height, String version){
        ///////////////////////////////////////////////////////////////////
        //LATER:  enter stuff that needs to happen before starting the game 
        ///////////////////////////////////////////////////////////////////
        gamer = new GameWindow(1920, 1080, "Not Minecraft (version " + version + ")");
        window = gamer.createWindow();
        
        renderer = new MasterRenderer();
        loader = new Loader();
        shader = new StaticShaderBLOCKS();
        renderer1 = renderer;
        loader1 = loader;
        shader1 = shader;
        

        model = loader.loadToVAO(vertices, indices, uvCoords);
        textures = new ModelTextures(loader.loadTexture("dirtTex"));
        texturedModel = new TexturedModel(loader1.loadToVAO(vertices, indices, uvCoords), textures);

        gameLoop();
    }


    private void gameLoop(){
        
        while(true){
            checkIfClosed(); //checks if the window was closed 
            
            update();
            System.out.println("In game loop");
            
            renderer.prepareRender();

            shader.startShaders();
            renderer.render(texturedModel);
            shader.stopShaders();
            
            swapBuffers();


        }
    }




    private void update() {
        GLFW.glfwPollEvents();
    }

        private void swapBuffers() {
            GLFW.glfwSwapBuffers(window);
        }



                //Closing window stuff
                private void checkIfClosed(){
                    if(detectClosed()){
                        closeDisplay();}
                }

                private boolean detectClosed() {
                    return GLFW.glfwWindowShouldClose(window);
                }

                private void closeDisplay(){
                    shader1.cleanUpShaders();
                    loader1.clean();
                    System.out.println("\nClosing display :'(");
                    System.exit(0);
                }

                

                //getting that good shit I guess? it's pretty self explanatory 
                public int getWidth(){
                    return width;
                }
            
                public int getHeight(){
                    return height;
                }
            
                public String getTitle(){
                    return title;
                }
}