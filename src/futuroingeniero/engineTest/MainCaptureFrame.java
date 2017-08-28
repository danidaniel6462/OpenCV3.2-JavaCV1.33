/**
 * 
 */
package futuroingeniero.engineTest;

import static futuroingeniero.engineTest.MainGUI.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacpp.opencv_videoio.CvCapture;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.OpenCVFrameRecorder;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import static org.bytedeco.javacpp.opencv_videoio.*;
import static org.bytedeco.javacpp.avcodec.*;

/**
 * @author Daniel Loza
 *
 */
public class MainCaptureFrame {

	public static void run() {
		IplImage imgOrigen, imgHsv, imgBin;
		CvCapture cap = cvCreateCameraCapture(CV_CAP_ANY);
		// crear la imagen vac�a del tama�o de la imagen original
		// de 8 bits y 3 canales
		imgHsv = cvCreateImage(cvSize(640, 480), 8, 3);
		// 1 canal xq ser� imagen binarizada
		imgBin = cvCreateImage(cvSize(640, 480), 8, 1);

		while (true) {
			imgOrigen = cvQueryFrame(cap);

			if (imgOrigen == null) break;

			cvCvtColor(imgOrigen, imgHsv, CV_BGR2HSV);

			// rango de colores m�n y m�x para binarizar la imagen
			CvScalar minC = cvScalar(minHSV[0], minHSV[1], minHSV[2], 0);
			CvScalar maxC = cvScalar(maxHSV[0], maxHSV[1], maxHSV[2], 0);

			// calcula la imagen binarizada a partir de los par�metros
			// ingresados
			cvInRangeS(imgHsv, minC, maxC, imgBin);

			// muestra la imagen
			cvShowImage("Origen", imgOrigen);
			cvShowImage("Imagen Binaria", imgBin);

			// deja abierta las ventanas hasta que se cierren
			char c = (char) cvWaitKey(30);

			if (c == 27) break;

		}
		
		cvReleaseCapture(cap);

		cvDestroyWindow("Origen");
		cvDestroyWindow("Imagen Binaria");
	}

	public static void main(String[] args) {
		run();
	}

}