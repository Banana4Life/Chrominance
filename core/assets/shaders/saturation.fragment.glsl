vec4 color;
float averageColorValue;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float Saturation;
uniform vec4 tower_color;

float getColorValue(float value) {
    return (value - ((value - averageColorValue) * Saturation));
}

void main()
{
    color = texture2D(u_texture, v_texCoords);
    averageColorValue = (color.r + color.g + color.b) / 3.0;
    if (color.r == 1.0 && color.g == 0.0 && color.b == 198.0 / 255.0) {
        color.r = getColorValue(tower_color.r);
        color.g = getColorValue(tower_color.g);
        color.b = getColorValue(tower_color.b);
        color.a = tower_color.a;
    } else {
        color.r = getColorValue(color.r);
        color.g = getColorValue(color.g);
        color.b = getColorValue(color.b);
	}
	gl_FragColor = color;
}