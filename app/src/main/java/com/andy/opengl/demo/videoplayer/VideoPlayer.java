package com.andy.opengl.demo.videoplayer;

/**
 * VideoPlayer
 *
 * @author andyqtchen <br/>
 * 创建日期：2018/5/11 10:11
 */
public class VideoPlayer {
    private final static String VERTEX_SHADER_CODE =
            "attribute vec4 aPosition;//顶点位置\n" +
            "attribute vec4 aTexCoord;//S T 纹理坐标\n" +
            "varying vec2 vTexCoord;\n" +
            "uniform mat4 uMatrix;\n" +
            "uniform mat4 uSTMatrix;\n" +
            "void main() {\n" +
            "    vTexCoord = (uSTMatrix * aTexCoord).xy;\n" +
            "    gl_Position = uMatrix*aPosition;\n" +
            "}";

    private final static String FRAGMENT_SHADER_CODE =
            "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;\n" +
            "varying vec2 vTexCoord;\n" +
            "uniform samplerExternalOES sTexture;\n" +
            "void main() {\n" +
            "    gl_FragColor=texture2D(sTexture, vTexCoord);\n" +
            "}";

    public VideoPlayer() {

    }
}
