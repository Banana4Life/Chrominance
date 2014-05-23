vec4 color;
float averageColorValue;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float Saturation;

float getColorValue(float value) {
    return (value - ((value - averageColorValue) * Saturation));
}

void main()
{
    color = texture2D(u_texture, v_texCoords);
    averageColorValue = (color.r + color.g + color.b) / 3.0;
	color.r = getColorValue(color.r);
	color.g = getColorValue(color.g);
	color.b = getColorValue(color.b);
	gl_FragColor = color;
}