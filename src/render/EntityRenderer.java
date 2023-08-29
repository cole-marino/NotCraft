package NotMC.src.render;


import NotMC.src.models.RawModel;
import NotMC.src.models.TexturedModel;


import org.lwjgl.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.debug.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjglx.debug.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class EntityRenderer {
    

    public static void render(TexturedModel rawModel){
        //GL## types might need to change?
        
        GL30.glBindVertexArray(rawModel.getModel().getVaoID());
        GL30.glEnableVertexAttribArray(0);      //allows access to data from attribute #0
        GL30.glEnableVertexAttribArray(1);     

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, rawModel.getTexture().getTextureID());


        GL30.glDrawElements(GL30.GL_TRIANGLES, rawModel.getModel().getVertexCount(), GL30.GL_UNSIGNED_INT, 0);        //the 4 is for GL_TRIANGLES (basically tells it that it's makeing an epic triangle)
        GL30.glDisableVertexAttribArray(0);              //disable after render
        GL30.glDisableVertexAttribArray(1);              //disable after render
        GL30.glBindVertexArray(0);                  //unbind

    }


}