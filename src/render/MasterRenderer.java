package NotMC.src.render;

import NotMC.src.models.RawModel;
import NotMC.src.models.TexturedModel;

import org.lwjgl.opengl.GL15;

public class MasterRenderer {
    
    public void prepareRender(){
        GL15.glClearColor(0.4f, 0.7f, 1.0f, 1); 
        GL15.glClear(GL15.GL_COLOR_BUFFER_BIT);
    }


    //calls renderer() method in entity renderer
    public void render(TexturedModel rawModel){
        EntityRenderer.render(rawModel);
    } 


}