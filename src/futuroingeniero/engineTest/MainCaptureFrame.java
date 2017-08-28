/**
 * 
 */
package futuroingeniero.engineTest;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

/**
 * @author Daniel Loza
 *
 */
public class MainCaptureFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		/*
		IplImage img = cvLoadImage("res/uce.png");
		cvShowImage("Hola funciona¿", img);
		cvSmooth(img, img, CV_GAUSSIAN, 13, 0, 0, 0);
		cvShowImage("BlurImage", img);
		cvWaitKey();
		cvReleaseImage(img);
		*/
		
		IplImage img = cvLoadImage("res/uce.png");
		IplImage hsvImg = cvCreateImage(cvGetSize(img), IPL_DEPTH_8U, 3);
		IplImage grayImg = cvCreateImage(cvGetSize(img), IPL_DEPTH_8U, 1);
		
		cvCvtColor(img, hsvImg, CV_BGR2HSV);
		cvCvtColor(img, grayImg, CV_BGR2GRAY);
		
		cvShowImage("Original", img);
		cvShowImage("HSV IMAGE", hsvImg);
		cvShowImage("GRAY IMAGE", grayImg);
		
		cvWaitKey();
		cvReleaseImage(img);
		cvReleaseImage(hsvImg);
		cvReleaseImage(grayImg);
		
	}

}
