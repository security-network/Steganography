import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;



public class steganography extends JFrame {

	private JPanel contentPane;
	private JTextField txtEncSrc;
	private JTextField txtEncDes;
	private JTextField txtDecSrc;
	private JPasswordField txtEncPass;
	private JPasswordField txtDecPass;
	private JProgressBar pgb;
	String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					steganography frame = new steganography();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public steganography() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnl_enc = new JPanel();
		tabbedPane.addTab("Encode", null, pnl_enc, null);
		pnl_enc.setLayout(new MigLayout("", "[][][][67.00][grow]", "[][][7.00][19.00][11.00][][28.00][]"));
		
		JLabel lblSource = new JLabel("Source: ");
		pnl_enc.add(lblSource, "cell 3 1,alignx left");
		
		txtEncSrc = new JTextField();
		txtEncSrc.setToolTipText("\u0110\u01B0\u1EDDng d\u1EABn ch\u1EE9a d\u1EEF li\u1EC7u c\u1EA7n che gi\u1EA5u");
		pnl_enc.add(txtEncSrc, "flowx,cell 4 1,alignx left");
		txtEncSrc.setColumns(30);
		
		JLabel lblDestination_1 = new JLabel("Destination: ");
		pnl_enc.add(lblDestination_1, "cell 3 3,alignx left");
		
		txtEncDes = new JTextField();
		txtEncDes.setToolTipText("\u0110\u01B0\u1EDDng d\u1EABn file l\u00E0m v\u1ECF b\u1ECDc");
		txtEncDes.setColumns(30);
		pnl_enc.add(txtEncDes, "flowx,cell 4 3,alignx left");
		
		JButton btnEncSrc = new JButton("Browser");
		btnEncSrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser b = new Browser();
				path = b.OpenB();
				if(path != null) txtEncSrc.setText(path);
			}
		});
		pnl_enc.add(btnEncSrc, "cell 4 1");
		
		JButton btnEncDes = new JButton("Browser");
		btnEncDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser b = new Browser();
				path = b.OpenB();
				if(path != null) txtEncDes.setText(path);
			}
		});
		pnl_enc.add(btnEncDes, "cell 4 3");
		
		JLabel lblPassword = new JLabel("Password:");
		pnl_enc.add(lblPassword, "cell 3 5,alignx left");
		
		txtEncPass = new JPasswordField();
		txtEncPass.setColumns(20);
		pnl_enc.add(txtEncPass, "flowx,cell 4 5,alignx left");
		
		JButton btnEmb = new JButton("Embedded");
		btnEmb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(txtEncPass.getText().isEmpty() || txtEncPass.getText().length() < 6) throw new Exception() ;
//					long startTime = new Date().getTime();
					boolean bool = false;
					for(String t: ConstantValue.img){
						if(t.equals(Browser.Getextension(txtEncDes.getText()))) {
//							new ProgressWorker(pgb, new File(txtEncSrc.getText()).length()/);
							embTextToImages emb = new embTextToImages();
							//System.out.println(Browser.Getextension(txtEncDes.getText()));
							emb.Encoder(txtEncSrc.getText(), txtEncDes.getText(), txtEncPass.getText());
							bool = true;
							emb = null;
							System.gc();
						}
					}
					
					for(String t: ConstantValue.audio){
						if( t.equals(Browser.Getextension(txtEncDes.getText()))) {
							embTextToAudio emb = new embTextToAudio();
							try {
								emb.Encoder(txtEncSrc.getText(), txtEncDes.getText(),txtEncPass.getText());
								bool = true;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							finally {
								emb = null;
								System.gc();
							}
						}
					}
					if(bool == false) JOptionPane.showMessageDialog(null, "File khong hop le!", "Alert",JOptionPane.INFORMATION_MESSAGE);
//					long endTime = new Date().getTime();
//					System.out.println("Time embbeded run: "+ (endTime-startTime));	
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Password is not NULL and longer 8 character!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JRadioButton rdbtnEncPass = new JRadioButton("Gi\u1EA3i m\u00E3 v\u1EDBi m\u1EADt kh\u1EA9u");
		rdbtnEncPass.setSelected(true);
		rdbtnEncPass.setToolTipText("Th\u1EF1c hi\u1EC7n gi\u1EA3i m\u00E3 file v\u1EDBi password");
		pnl_enc.add(rdbtnEncPass, "cell 4 6");
		pnl_enc.add(btnEmb, "cell 4 7,alignx left");
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{pnl_enc, tabbedPane}));
		rdbtnEncPass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rdbtnEncPass.isSelected()) {
					txtEncPass.enable(true);
					txtEncPass.updateUI();
				}else{
					txtEncPass.enable(false);
					txtEncPass.updateUI();
				}
			}
		});
		
		JPanel pnl_dec = new JPanel();
		tabbedPane.addTab("Decode", null, pnl_dec, null);
		pnl_dec.setLayout(new MigLayout("", "[][][24.00][70.00][301.00][grow]", "[37.00][][11.00][][9.00][][18.00][]"));
		
		JLabel lblSource_1 = new JLabel("Source: ");
		pnl_dec.add(lblSource_1, "cell 3 1,alignx left");
		
		txtDecSrc = new JTextField();
		txtDecSrc.setToolTipText("\u0110\u01B0\u1EDDng d\u1EABn ch\u1EE9a file c\u1EA7n l\u1EA5y d\u1EEF li\u1EC7u che gi\u1EA5u");
		pnl_dec.add(txtDecSrc, "cell 4 1,alignx left");
		txtDecSrc.setColumns(30);
		
		JButton btnDecSrc = new JButton("Browser");
		btnDecSrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser b = new Browser();
				path = b.OpenB();
				if(path != null)txtDecSrc.setText(path);
			}
		});
		pnl_dec.add(btnDecSrc, "cell 5 1");
		
		JLabel label_1 = new JLabel("Password:");
		pnl_dec.add(label_1, "cell 3 3,alignx left");
		
		txtDecPass = new JPasswordField();
		txtDecPass.setColumns(20);
		pnl_dec.add(txtDecPass, "cell 4 3");
		
		JButton btnExt = new JButton("Extract");
		btnExt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bool = false;
				for(String t: ConstantValue.img){
					if(t.equals(Browser.Getextension(txtDecSrc.getText()))) {
						embTextToImages exct = new embTextToImages();
						exct.Decoder(txtDecSrc.getText(), txtDecPass.getText());
						bool = true;
					}
				}
				for(String t: ConstantValue.audio){
					if(t.equals(Browser.Getextension(txtDecSrc.getText()))) {
						embTextToAudio exct = new embTextToAudio();
						exct.Decoder(txtDecSrc.getText(),txtDecPass.getText());
						bool = true;
					}
				}	
				if(bool == false) JOptionPane.showMessageDialog(null, "File khong hop le!", "Alert",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JRadioButton rdbtnDecPass = new JRadioButton("Gi\u1EA3i m\u00E3 v\u1EDBi m\u1EADt kh\u1EA9u");
		rdbtnDecPass.setSelected(true);
		rdbtnDecPass.setToolTipText("Th\u1EF1c hi\u1EC7n gi\u1EA3i m\u00E3 file v\u1EDBi password");
		pnl_dec.add(rdbtnDecPass, "cell 4 5");
		rdbtnDecPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rdbtnDecPass.isSelected()) {
					txtDecPass.enable(true);
					txtDecPass.updateUI();
				}else{
					 txtDecPass.enable(false);
					 txtDecPass.updateUI();
				}
			}
		});
		pnl_dec.add(btnExt, "flowx,cell 4 7,aligny top");
		
		JPanel pnl_aut = new JPanel();
		tabbedPane.addTab("Author", null, pnl_aut, null);
		
		JTextArea txtrTeamStudentAt = new JTextArea();
		txtrTeamStudentAt.setBackground(UIManager.getColor("menu"));
		txtrTeamStudentAt.setText("Team student at University of Information Technology\r\nTrainer: Nguyen Duy\r\nMember: Nguyen Hung Nhuan\r\n\tLe Viet Hung\r\n\tPhung Nhuc Sau");
		txtrTeamStudentAt.setEditable(false);
		pnl_aut.add(txtrTeamStudentAt);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		pgb = new JProgressBar();
		panel_3.add(pgb);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel label = new JLabel("Size:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(label);
		
		JLabel lblSize = new JLabel("None");
		panel_5.add(lblSize);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(100);
		panel_3.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(128, 128, 128)), "", TitledBorder.TRAILING, TitledBorder.BOTTOM, null, new Color(128, 128, 128)));
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setVgap(15);
		contentPane.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblStegenogeaphy = new JLabel("WANNA \r\n\r\nSTEGANOGRAPHY");
		lblStegenogeaphy.setFont(new Font("Tekton Pro", Font.PLAIN, 30));
		panel_6.add(lblStegenogeaphy);

	}
	
}
