package NotMC.src.models;

import NotMC.src.textures.ModelTextures;

public class TexturedModel{
    
    RawModel model;
    ModelTextures texture;

    public TexturedModel(RawModel model, ModelTextures texture){
        this.model = model;
        this.texture = texture;
    }
    


    public RawModel getModel() {
        return model;
    }

    public ModelTextures getTexture() {
        return texture;
    }

}