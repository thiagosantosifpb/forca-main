import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class tela extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tela frame = new tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public tela() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		
		JogoDaForca jogo = new JogoDaForca("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\bin\\dados\\palavras.txt");

		ImageIcon i0 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\0.png");
		ImageIcon i1 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\1.png");
		ImageIcon i2 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\2.png");
		ImageIcon i3 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\3.png");
		ImageIcon i4 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\4.png");
		ImageIcon i5 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\5.png");
		ImageIcon i6 = new ImageIcon("C:\\Users\\thiag\\Downloads\\forca-main\\forca-main\\projeto1-Jogo-da-forca\\projeto1-Jogo-da-forca\\src\\imagens\\6.png");
		
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(105, 10, 37, 18);
		contentPane.add(textArea);
		
		
		JLabel lbl_dica = new JLabel("");
		lbl_dica.setBounds(10, 198, 160, 13);
		contentPane.add(lbl_dica);
		
		JLabel lbl_tamanho = new JLabel("");
		lbl_tamanho.setBounds(152, 262, 18, 13);
		contentPane.add(lbl_tamanho);
		
		JLabel lbl_penalidade = new JLabel("");
		lbl_penalidade.setBounds(10, 116, 105, 13);
		contentPane.add(lbl_penalidade);
		
		JLabel lbl_aviso = new JLabel("");
		lbl_aviso.setBounds(486, 135, 121, 13);
		contentPane.add(lbl_aviso);
		
		JLabel lbl_total_acertos = new JLabel("");
		lbl_total_acertos.setBounds(573, 221, 62, 13);
		contentPane.add(lbl_total_acertos);
		
		
//		JLabel lbl_palavra = new JLabel("");
//		contentPane.add(lbl_total_acertos);
		
		
		JLabel lbl_img = new JLabel("");
		lbl_img.setBounds(223, 103, 205, 188);
		contentPane.add(lbl_img);
		lbl_img.setIcon(i0);
		
		JLabel label = new JLabel("Dica : ");
		label.setBounds(10, 160, 45, 13);
		contentPane.add(label);
		
		JLabel label_4 = new JLabel("...");
		label_4.setBounds(247, 369, 92, 13);
		contentPane.add(label_4);
		
		JButton button_1 = new JButton("Adivinhar");
		button_1.setBounds(152, 10, 121, 21);
		button_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String letra = textArea.getText();

		        if (letra != null && !letra.isEmpty()) {
		            try {
		                List<Integer> ocorrencias = jogo.getOcorrencias(letra.charAt(0));

		                if (ocorrencias.isEmpty())
		                	
		                    lbl_penalidade.setText(jogo.getNomePenalidade());
		                	
		                switch (jogo.getNomePenalidade()) {
		                case "Perna esquerda":
		                	lbl_img.setIcon(i1);
		                	break;
		                	
		                case "Perna direita":
		                	lbl_img.setIcon(i2);
		                	break;
		                case "Braço esquerdo":
		                	lbl_img.setIcon(i3);
		                	break;
		                case "Braço direito":
		                	lbl_img.setIcon(i4);
		                	break;
		                case "Tronco":
		                	lbl_img.setIcon(i5);
		                	break;
		                case "Cabeça":
		                	lbl_img.setIcon(i6);
		                	contentPane.setBackground(Color.RED);
		                	break;
		                }
	                

		                if (jogo.getResultado().equals("Jogo em andamento")) {
				        	button_1.setEnabled(true);
				        	
		                } else {
		                	button_1.setEnabled(false);
		                    lbl_total_acertos.setText(""+jogo.getAcertos());
		                    label_4.setText(""+jogo.getOcorrencias(letra.charAt(0)));
		                }
		            } catch (Exception e1) {
		                lbl_aviso.setText(e1.getMessage());
		            }
		        } else {
		        	
		            lbl_aviso.setText("Digite uma letra.");
		        }lbl_aviso.setText(jogo.getResultado());
		        lbl_total_acertos.setText(""+jogo.getAcertos());
		        label_4.setText(jogo.getPalavraAdivinhada());
		        
		        if (jogo.getResultado().equals("Você venceu!"))
		        	contentPane.setBackground(Color.green);
		        
		        
		        
		        textArea.setText("");
		    }
		});

		JLabel label_1 = new JLabel("Tamanho da palavra : ");
		label_1.setBounds(10, 262, 132, 13);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Penalidade :");
		label_2.setBounds(10, 82, 85, 13);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Palavra :");
		label_3.setBounds(152, 369, 85, 13);
		contentPane.add(label_3);
		
		
		contentPane.add(button_1);
		JLabel label_5 = new JLabel("Acertos : ");
		label_5.setBounds(486, 221, 77, 13);
		contentPane.add(label_5);
	
		
		JButton button = new JButton("Iniciar");
		button.setBounds(10, 10, 85, 21);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo.iniciar();
				lbl_img.setIcon(i0);
				contentPane.setBackground(Color.LIGHT_GRAY);
				lbl_total_acertos.setText("");
				label_4.setText(jogo.getPalavraAdivinhada());
				lbl_dica.setText(jogo.getDica());
				lbl_tamanho.setText(""+jogo.getTamanho());
				lbl_penalidade.setText("");
				button_1.setEnabled(true);
				lbl_aviso.setText("");
				
			}
		});
		contentPane.add(button);
		
		
		
		
		
	}
}
