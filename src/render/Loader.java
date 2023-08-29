package NotMC.src.render;
package NotMC.resources.res;
import NotMC.src.Game;
import NotMC.src.models.RawModel;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.debug.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;


//things from slick jar 
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class Loader {

    public List<Integer> VAOs = new ArrayList<Integer>(); // stores VAO IDs
    public List<Integer> VBOs = new ArrayList<Integer>(); // stores VBO IDs
    List<Integer> textures = new ArrayList<Integer>(); // list of textures


    public RawModel loadToVAO(float[] vertices, int[] indices, float[] uvCoords) {    //uv is coords
        int vaoID = this.createVAO();
        storeDataInAttributeList(vertices, 0, 3); // 3 is for 3D data
        storeDataInAttributeList(uvCoords, 1, 2); // 2D data

        bindIndicesBuffer(indices);
        GL30.glBindVertexArray(0); // unbinds

        return new RawModel(vaoID, indices.length);

    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays(); // creates VAO
        VAOs.add(vaoID); // adds vaoID to the list

        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    // makes VBO
    private void storeDataInAttributeList(float[] data, int attributeNumber, int dimentions) {
        int vboID = GL15.glGenBuffers(); // creates VBO
        VBOs.add(vboID); // adds the vboID to the list

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); // binds VBO
        FloatBuffer floatBuffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW); // saves

        GL30.glVertexAttribPointer(attributeNumber, dimentions, GL15.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // unbinds buffer

    }

    private void bindIndicesBuffer(int[] indices) {
        int vboID = GL15.glGenBuffers();
        VBOs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);

        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    // makes an IntBuffer
    IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);

        buffer.put(data);
        buffer.flip(); // makes it readable

        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer fBuffer = BufferUtils.createFloatBuffer(data.length);
        fBuffer.put(data);
        fBuffer.flip(); // Makes buffer readable

        return fBuffer;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////TEXTURE STUFF///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public int loadTexture(final String fileName) {
            Texture texture = null;
            // FILE DIMENTIONS MUST ALWAYS BE A POWER OF TWO
            // (https://youtu.be/roXB1Snb6sM?list=PL80Zqpd23vJfyWQi-8FKDbeO_ZQamLKJL&t=490)
                try {
                    texture = TextureLoader.getTexture("PNG", Class.class.getResourceAsStream("/NotMC/resources/res/" + fileName + ".PNG"));
                    System.out.println("\n\ntextures\n\n");
                }catch(IOException e) {
                    e.printStackTrace();        
                }
            int textureID = texture.getTextureID();
            textures.add(textureID);

            return textureID;
        }



//////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //TEXTURE STUFF END//////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////


//clears the VAOs and the VBOs
    public void clean(){

        for(int vao : VAOs)
            GL30.glDeleteVertexArrays(vao);

        for(int vbo : VBOs)
            GL15.glDeleteBuffers(vbo);
        
        for(int texture : textures)
            GL11.glDeleteTextures(texture);

    }


}