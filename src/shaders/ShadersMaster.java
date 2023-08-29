package NotMC.src.shaders;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.xml.sax.InputSource;

public abstract class ShadersMaster {

    int programID; // ID of shader program
    int vertexShaderID;
    int fragmentShaderID;

    public ShadersMaster(String vertexFile, String fragmentFile) {

        programID = GL20.glCreateProgram(); // makes program ID
        vertexShaderID = loadShaders(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShaders(fragmentFile, GL20.GL_FRAGMENT_SHADER);

        // attaches the shaders to the program
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttrib();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID); // validates

    }

    public void startShaders() {
        GL20.glUseProgram(programID);
    }

    public void stopShaders() { // fin
        GL20.glUseProgram(0);
    }

    public void cleanUpShaders() {
        stopShaders();
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteProgram(programID);
    }

    // loads shaders (returns the shader ID as an int)
    private int loadShaders(String file, int shaderType) {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader reader = getReader(file);

        try {
            while ((file = reader.readLine()) != null) 
                shaderSource.append(file).append("\n");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR: Couldn't load shader file");
            System.exit(-1);
        }
        
        //creates shader
        int shaderID = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        
        checkForErrors(shaderID);
        return shaderID;
    }


    //reads from the given file
    private BufferedReader getReader(String file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            System.out.println(file + " shader loaded!");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            sendError(GL20.glGetShaderInfoLog(vertexShaderID, 5000), "ERROR: Couldn't find shader file");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in)); // reads from text files

        return reader;
    }





    protected abstract void bindAttrib(); //will be called in the sub classes
    //binds the attributes as the name implies :))))))))))))))))))))))))))
        protected void bindAttributes(String varName, int attribute){
            GL20.glBindAttribLocation(programID, attribute, varName);
        }



        //////////////////////
        //ERROR CHECKING STUFF
        //////////////////////
        private void checkForErrors(int shaderID){
            if(  (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) )  ==  GL11.GL_FALSE  )
                sendError(GL20.glGetShaderInfoLog(shaderID, 5000), "Couldn't compile shader");
        }
        private void sendError(String errorLog, String errorText){
            System.out.println(errorLog);
            System.err.println("ERROR: " + errorText);
            System.exit(-1);
        }
}