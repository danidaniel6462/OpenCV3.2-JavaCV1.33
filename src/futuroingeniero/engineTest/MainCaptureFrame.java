/**
 * 
 */
package futuroingeniero.engineTest;

import static futuroingeniero.engineTest.MainGUI.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacpp.opencv_videoio.CvCapture;

import static org.bytedeco.javacpp.opencv_highgui.*;

import static org.bytedeco.javacpp.opencv_videoio.*;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.bytedeco.javacpp.Loader;

/**
 * @author Daniel Loza
 *
 */
public class MainCaptureFrame {

	public static void run() {

		minHSV = new int[] { 95, 150, 75 };
		maxHSV = new int[] { 145, 255, 255 };
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.getWidth() + "," + screenSize.getHeight());
		
		// matriz de imagenes vacías para realizar los cáclulos en ellas
		IplImage imgOrigen, imgHsv, imgBinA, imgBinV, imgC;
		// lista enlazada para guardar cualquier tipo de objeto, lista dinámica
		CvSeq contorno1 = null, contorno2 = null;
		// Almacenamiento de memoria donde reside la secuencia
		CvMemStorage almacenamiento = CvMemStorage.create();
		// variable con la lista de momentos para utilizarlos
		CvMoments momentos = new CvMoments(Loader.sizeof(CvMoments.class));
		// valores del tamaño de las imagenes que se inicializan
		int width = 640, height = 480;
		// inicialización de las imagenes HSV e imagen Binarizada en escala de gris
		imgHsv = cvCreateImage(cvSize(width, height), 8, 3);
		imgBinA = cvCreateImage(cvSize(width, height), 8, 1);
		imgBinV = cvCreateImage(cvSize(width, height), 8, 1);
		imgC = cvCreateImage(cvSize(width, height), 8, 1);
		
		// valores mín y máx de los colores, azul y verde, respectivamente 
		CvScalar AminC = cvScalar(minHSV[0], minHSV[1], minHSV[2], 0);
		CvScalar AmaxC = cvScalar(maxHSV[0], maxHSV[1], maxHSV[2], 0);
		CvScalar VminC = cvScalar(40, 50, 60, 0);
		CvScalar VmaxC = cvScalar(80, 255, 255, 0);
		
		// captura de webCam
		CvCapture cap = cvCreateCameraCapture(CV_CAP_ANY);
		while(true){
			// captura del un frame del video
			imgOrigen = cvQueryFrame(cap);
			// comprobamos que siempre tenga una imagen, si no es así la aplicación se cierra
			if (imgOrigen == null){
				System.err.println("mo hay imagen");
				break;
			}
			
			try {
				imgBinA = ColorContornosMomentsFiltrado.filtro(imgOrigen, imgHsv, imgBinA, AminC, AmaxC, contorno1, contorno2, almacenamiento, momentos, 1, 0);
				imgBinV = ColorContornosMomentsFiltrado.filtro(imgOrigen, imgHsv, imgBinV, VminC, VmaxC, contorno1, contorno2, almacenamiento, momentos, 0, 1);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			
			// combinamos las dos imágenes sin ninguna máscara
			cvOr(imgBinA, imgBinV, imgC, null);
			
			// muestra la imagen
			cvShowImage("Origen", imgOrigen);
			cvShowImage("combinado", imgC);

			char c = (char) cvWaitKey(30);
			
			if(c == 27) break;
		}
		// liberamos la memoria que utiliza el video
		cvReleaseCapture(cap);
		// liberamos el almacenamiento reservado para los cáculos de los contornos
		cvReleaseMemStorage(almacenamiento);
		cvDestroyWindow("Origen");
		cvDestroyWindow("Imagen Binaria");
	}

	public static void main(String[] args) {
		run();
	}
}