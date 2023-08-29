package NotMC.src.shaders;

import NotMC.src.shaders.ShadersMaster;

public class StaticShaderBLOCKS extends ShadersMaster
{
    private static final String vertexFile = "NotMC\\src\\shaders\\types\\vertexShader.txt"; 
    private static final String fragmentFile = "NotMC\\src\\shaders\\types\\fragmentShader.txt";

    public StaticShaderBLOCKS() {
        super(vertexFile, fragmentFile);
    }




    protected void bindAttrib(){
        super.bindAttributes("position", 0);   //from attribute 0
        super.bindAttributes("textureCoords", 1);   // :)
    }

}