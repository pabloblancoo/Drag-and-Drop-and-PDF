package xyz.pabloblanco.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import xyz.pabloblanco.business.Writer;
import xyz.pabloblanco.business.write.impl.PDFWriter;
import java.awt.Font;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Swing objects
	private JPanel principalPane;
	private JPanel draggablePane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmAyuda;
	private JLabel lbDrapAndDrop;
	private JLabel lbError;
	
	//Java objects
	
	//Dropped files in Drag and Drop panel
	private List<File> droppedFiles = null;

	//Writer 
	Writer writer = null;
	
	//If the program has errors

	boolean error = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setMinimumSize(new Dimension(600, 400));
					frame.setMaximumSize(new Dimension(1200, 800));
					frame.setSize(600, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setJMenuBar(getMenuBar_1());
		principalPane = new JPanel();
		principalPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(principalPane);
		principalPane.setLayout(new BorderLayout(0, 0));
		principalPane.add(getDraggablePane(), BorderLayout.CENTER);
		principalPane.add(getLbDrapAndDrop(), BorderLayout.NORTH);
	}

	@SuppressWarnings("serial")
	private JPanel getDraggablePane() {
		
		if (draggablePane == null) {
			draggablePane = new JPanel();
			draggablePane.setLayout(new BorderLayout(0, 0));
			draggablePane.setDropTarget(new DropTarget() {
		        @SuppressWarnings("unchecked")
				public void drop(DropTargetDropEvent evt) {
		            try {
		                evt.acceptDrop(DnDConstants.ACTION_COPY);
		                droppedFiles = (List<File>) evt
		                        .getTransferable().getTransferData(
		                                DataFlavor.javaFileListFlavor);
		                
		                	writer = new PDFWriter();
		                	try {
								
							
		                	writer.writeFiles(droppedFiles);
		                	} catch (IOException ioEx){
		                		error = true;
		                		lbError.setVisible(true);
		                	}
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		            if(error)
		            	lbError.setVisible(true);
		            else
		            	lbError.setVisible(false);
		            error = false;
		        }
		    });
			draggablePane.setBackground(Color.LIGHT_GRAY);
			draggablePane.add(getLbError(), BorderLayout.SOUTH);
		}
		return draggablePane;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivo());
		}
		return menuBar;
	}
	private JMenu getMnArchivo() {
		if (mnArchivo == null) {
			mnArchivo = new JMenu("Archivo");
			mnArchivo.add(getMntmAyuda());
		}
		return mnArchivo;
	}
	private JMenuItem getMntmAyuda() {
		if (mntmAyuda == null) {
			mntmAyuda = new JMenuItem("Acerca de");
			mntmAyuda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					About aboutDialog = new About();
					aboutDialog.setModal(true);
					aboutDialog.setVisible(true);
				}
			});
		}
		return mntmAyuda;
	}
	private JLabel getLbDrapAndDrop() {
		if (lbDrapAndDrop == null) {
			lbDrapAndDrop = new JLabel("Arrastra aqui los ficheros pdf");
			lbDrapAndDrop.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDrapAndDrop;
	}
	private JLabel getLbError() {
		if (lbError == null) {
			lbError = new JLabel("El fichero debe ser PDF");
			lbError.setHorizontalAlignment(SwingConstants.CENTER);
			lbError.setForeground(Color.RED);
			lbError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lbError.setVisible(false);
		}
		return lbError;
	}
}
