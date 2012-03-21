package com.silenistudios.silenus;

import com.silenistudios.silenus.dom.Bitmap;
import com.silenistudios.silenus.dom.FillStyle;
import com.silenistudios.silenus.dom.Path;
import com.silenistudios.silenus.dom.StrokeStyle;
import com.silenistudios.silenus.raw.ColorManipulation;

/**
 * This interface must be implemented to render a scene through the SceneRenderer.
 * SceneRenderer will call the appropriate functions at the right time, to ensure that images
 * are drawn in the correct position and transformation.
 * Notes:
 * 1. If there's a feature you don't need or can't support, leave the function empty. Save and restore are mandatory, though.
 * 2. Rotation is counterclockwise.
 * @author Karel
 *
 */
public interface RenderInterface {
	
	// push transformation matrix on stack
	public void save();
	
	// restore transformation matrix from stack
	public void restore();
	
	// scale
	public void scale(double x, double y);
	
	// translate
	public void translate(double x, double y);
	
	// rotate in counterclockwise direction, in radians
	public void rotate(double theta);
	
	// apply transformation
	//public void transform(double m00, double m01, double m10, double m11, double tx, double ty);
	
	// draw the provided image to the screen, so that the topleft of the image is in the current origin
	// path is relative to the root of the xfl directory (where DOMDocument.xml resides)
	public void drawImage(Bitmap img);
	
	// draw the provided image to the screen, so that the topleft of the image is in the current origin
	// path is relative to the root of the xfl directory (where DOMDocument.xml resides)
	// Before drawing the image, the data contained in ColorManipulation must be applied to the entire
	// image.
	public void drawImage(Bitmap img, ColorManipulation colorManipulation);
	
	// draw a path
	public void drawPath(Path path);
	
	// fill the path with the given fill style
	public void fill(FillStyle fillStyle);
	
	// stroke the path with the given stroke style
	public void stroke(StrokeStyle strokeStyle);
}