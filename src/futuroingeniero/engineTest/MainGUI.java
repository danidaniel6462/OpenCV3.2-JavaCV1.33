package futuroingeniero.engineTest;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI {

	private JFrame frame;
	public static int[] minHSV, maxHSV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		minHSV = new int[] {95, 150, 75};
		maxHSV = new int[] {145, 255, 255};
		
		start();
		MainCaptureFrame.run();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		frame.setSize(640, 480);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);

		Panel panel = new Panel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		JSlider sMinMatiz = new JSlider();
		sMinMatiz.setMajorTickSpacing(50);
		sMinMatiz.setMinorTickSpacing(10);
		sMinMatiz.setPaintTicks(true);
		sMinMatiz.setPaintLabels(true);
		sMinMatiz.setValue(95);
		sMinMatiz.setMaximum(255);
		sMinMatiz.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				minHSV[0] = sMinMatiz.getValue();
			}
		});

		JLabel lblMinValores = new JLabel("M\u00EDnimos valores");
		lblMinValores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMinValores.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinValores.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblMinValores);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(10, 0));
		panel.add(horizontalStrut);
		panel.add(sMinMatiz);

		JSlider sMinSaturacion = new JSlider();
		sMinSaturacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				minHSV[1] = sMinSaturacion.getValue();
			}
		});
		sMinSaturacion.setMinorTickSpacing(10);
		sMinSaturacion.setMajorTickSpacing(50);
		sMinSaturacion.setPaintTicks(true);
		sMinSaturacion.setPaintLabels(true);
		sMinSaturacion.setValue(150);
		sMinSaturacion.setMaximum(255);
		panel.add(sMinSaturacion);

		JSlider sMinBrillo = new JSlider();
		sMinBrillo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				minHSV[2] = sMinBrillo.getValue();
			}
		});
		sMinBrillo.setMajorTickSpacing(50);
		sMinBrillo.setMinorTickSpacing(10);
		sMinBrillo.setPaintTicks(true);
		sMinBrillo.setPaintLabels(true);
		sMinBrillo.setValue(75);
		sMinBrillo.setMaximum(255);
		panel.add(sMinBrillo);

		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(5, 1, 0, 0));

		JLabel lblMximosValores = new JLabel("M\u00E1ximos valores");
		lblMximosValores.setHorizontalAlignment(SwingConstants.CENTER);
		lblMximosValores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMximosValores.setAlignmentX(0.5f);
		panel_1.add(lblMximosValores);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(10, 0));
		panel_1.add(horizontalStrut_1);

		JSlider sMaxMatiz = new JSlider();
		sMaxMatiz.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				maxHSV[0] = sMaxMatiz.getValue();
			}
		});
		sMaxMatiz.setMinorTickSpacing(10);
		sMaxMatiz.setMajorTickSpacing(50);
		sMaxMatiz.setPaintTicks(true);
		sMaxMatiz.setPaintLabels(true);
		sMaxMatiz.setValue(145);
		sMaxMatiz.setMaximum(255);
		panel_1.add(sMaxMatiz);

		JSlider sMaxSaturacion = new JSlider();
		sMaxSaturacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				maxHSV[1] = sMaxSaturacion.getValue();
			}
		});
		sMaxSaturacion.setName("");
		sMaxSaturacion.setMinorTickSpacing(10);
		sMaxSaturacion.setPaintTicks(true);
		sMaxSaturacion.setPaintLabels(true);
		sMaxSaturacion.setMajorTickSpacing(50);
		sMaxSaturacion.setValue(255);
		sMaxSaturacion.setMaximum(255);
		panel_1.add(sMaxSaturacion);

		JSlider sMaxBrillo = new JSlider();
		sMaxBrillo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				maxHSV[2] = sMaxBrillo.getValue();
			}
		});
		sMaxBrillo.setMinorTickSpacing(10);
		sMaxBrillo.setPaintTicks(true);
		sMaxBrillo.setMajorTickSpacing(50);
		sMaxBrillo.setName("");
		sMaxBrillo.setPaintLabels(true);
		sMaxBrillo.setValue(255);
		sMaxBrillo.setMaximum(255);
		panel_1.add(sMaxBrillo);
	}

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {

		initialize();
	}
}
