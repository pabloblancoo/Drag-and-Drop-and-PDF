package xyz.pabloblanco.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class About extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public About() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lbPdfImage = new JLabel("");
			ImageIcon img = new ImageIcon("pdf-logo.png");
			img = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			lbPdfImage.setIcon(img);
			contentPanel.add(lbPdfImage, BorderLayout.WEST);
		}
		{
			JPanel infoPane = new JPanel();
			contentPanel.add(infoPane, BorderLayout.CENTER);
			infoPane.setLayout(new BorderLayout(0, 0));
			{
				JLabel lbTittle = new JLabel("Drag and Drop and PDF");
				lbTittle.setHorizontalAlignment(SwingConstants.CENTER);
				lbTittle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				infoPane.add(lbTittle, BorderLayout.NORTH);
			}
			{
				JPanel panel_1 = new JPanel();
				infoPane.add(panel_1, BorderLayout.WEST);
				panel_1.setSize(panel_1.getWidth(), 100);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblVersion = new JLabel("Version 1.0");
						panel_2.add(lblVersion, BorderLayout.NORTH);
					}
					{
						JPanel panel_3 = new JPanel();
						panel_2.add(panel_3, BorderLayout.CENTER);
						panel_3.setLayout(new BorderLayout(0, 0));
						{
							JLabel lbGithub = new JLabel("github.com/pabloblancoo");
							lbGithub.setForeground(Color.BLUE);
							lbGithub.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									if (Desktop.isDesktopSupported()) {
						                Desktop desktop = Desktop.getDesktop();
						                try {
						                    URI uri = new URI("http://www.github.com/pabloblancoo");
						                    desktop.browse(uri);
						                } catch (IOException ex) {
						                    ex.printStackTrace();
						                } catch (URISyntaxException ex) {
						                    ex.printStackTrace();
						                }
						        }
								}
							});
							panel_3.add(lbGithub, BorderLayout.NORTH);
						}
					}
				}
				{
					JLabel lbAutor = new JLabel("Pablo Blanco Pacho");
					panel_1.add(lbAutor, BorderLayout.NORTH);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
