/**
 * 
 */
package futuroingeniero.engineTest;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacpp.opencv_videoio.CvCapture;

import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import static org.bytedeco.javacpp.opencv_videoio.*;

/**
 * @author Daniel Loza
 *
 */
public class MainCaptureFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		// creo una variable captura para guardar la información del video que se carga en la webCam
		CvCapture captura = cvCreateCameraCapture(CV_CAP_ANY);
		// los videos están constituidos de mínimo 24 cuadros por segundo
		// así que se crea una variable de tipo IplImage para guardar cada cuadro del video
		IplImage frame;
		// creo una imagen con grises
		IplImage grayImg = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
		
		// creo una ventana para mostrar la captura del video
		cvNamedWindow("Video", CV_WINDOW_AUTOSIZE);
		
		while(true){
			// captura cada frame del video
			frame = cvQueryFrame(captura);
			// comprobamos que tenga un frame para no salirdel bucle infinito 
			if(frame == null){
				System.err.println("ERROR: No hay video que mostrar");
				break;
			}
			// calcula la imagen como espejo, el valor 1 representa que será en forma horizontal 
			cvFlip(frame, frame, 1);
			cvCvtColor(frame, grayImg, CV_BGR2GRAY);
			
			//Mostramos en una ventana los frame del video
			cvShowImage("Video", grayImg);
			// controla la velocidad de reproducción de los videos controlado en milisegundos 
			char c = (char) cvWaitKey(1);
			// si se presiona la tecla ESC el video finaliza
			if(c == 27) break;
		}
		
		// liberar memoria del computador
		cvReleaseCapture(captura);
		// cerrar la ventana del video
		cvDestroyWindow("Video");
	}
}
