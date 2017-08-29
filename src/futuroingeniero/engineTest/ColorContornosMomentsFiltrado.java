package futuroingeniero.engineTest;

import static org.bytedeco.javacpp.helper.opencv_core.CV_RGB;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_imgproc.CvMoments;

/**
 * 
 * @author Daniel Loza
 *
 */
public class ColorContornosMomentsFiltrado {
	
	public static int temp;
	
	public static IplImage filtro(
			IplImage imgOrigen,
			IplImage imgHsv,
			IplImage imgBin,
			CvScalar minColorHsv,
			CvScalar maxColorHsv,
			CvSeq contorno1,
			CvSeq contorno2,
			CvMemStorage almacenamiento,
			CvMoments momentos,
			int color1,
			int color2) throws AWTException{

		// objeto que permite controlar el computador
		Robot robot = new Robot();
		
		// variables máx y min para los objetos
		double areaMax, areaC;
		// variables para guardar los momentos
		double m10, m01, mArea;
		// variables para la posición centrar de los objetos en la pantalla
		int posX = 0, posY = 0;
		
		// convierte la imagen original (BGR) en HSV
		cvCvtColor(imgOrigen, imgHsv, CV_BGR2HSV);
		// calcula imagen binarizada a partir de los valores min y max de colores
		cvInRangeS(imgHsv, minColorHsv, maxColorHsv, imgBin);
		// objeto de lista enlazada para guardar los contornos
		contorno1 = new CvSeq();
		// área máxima 
		areaMax = 1000;
		// encuentra los contornos de los objetos encontrados
		cvFindContours(imgBin, almacenamiento, contorno1, Loader.sizeof(CvContour.class),
				CV_RETR_LIST, CV_LINK_RUNS, cvPoint(0, 0));
		// igualamos el contorno2
		contorno2 = contorno1;
		// mientras tenga información el contorno
		while(contorno1 != null && !contorno1.isNull()){
			// encuentra el área menor
			areaC = cvContourArea(contorno1, CV_WHOLE_SEQ, 1);
			if(areaC > areaMax){
				areaMax = areaC;
			}
			// continua con el siguiente objeto verificando el área mayor
			contorno1 = contorno1.h_next();
		}
		// bucle muy parecido al de arriba
		while(contorno2 != null && !contorno2.isNull()){
			areaC = cvContourArea(contorno2, CV_WHOLE_SEQ, 1);
			if(areaC < areaMax){
				cvDrawContours(imgBin, contorno2, CV_RGB(0, 0, 0), CV_RGB(0, 0, 0),
						0, CV_FILLED, 8, cvPoint(0, 0));
			}
			contorno2 = contorno2.h_next();
		}
		// enlistamos los momentos de la imagen binarizada, valor 1, img binaria
		cvMoments(imgBin, momentos, 1);
		// guardamos el momento correspondiente al eje X y eje Y respectivamente
		m10 = cvGetSpatialMoment(momentos, 1, 0);
		m01 = cvGetSpatialMoment(momentos, 0, 1);
		// guardamos el momento central
		mArea = cvGetCentralMoment(momentos, 0, 0);
		
		// cálculo del centro del color capturada con el área mayor
		posX = (int) (m10 / mArea);
		posY = (int) (m01 / mArea);
		
		// obtengo las dimensiones de la pantalla del computador
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// verifico si es un color u otro y haga las acciones de mover el mouse y dar clic 
		if(color1 == 1){
			if(posX > 0 && posY > 0){
				// movemos el mouse
				robot.mouseMove((int) (posX * screenSize.getWidth() / 640), (int) (posY * screenSize.getHeight() / 480));
			}
		}
		if(color2 == 1){
			if(posX > 0 && posY > 0){
				// presiona el botón del mouse
				robot.mousePress(InputEvent.BUTTON1_MASK);
				temp++;
			} else if(temp > 0){
				// liberación del botón del mouse
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				temp = 0;
			}
		}
		// dibujo un círculo en el centro de la imagen con el área mayor encontrado
		cvCircle(imgOrigen, cvPoint(posX, posY), 5, cvScalar(0, 255, 0, 0), 9, 0, 0);
		// devuelve imagen binarizada
		return imgBin;
	}
}
