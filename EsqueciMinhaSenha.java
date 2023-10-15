package farmacia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EsqueciMinhaSenha extends Login {

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";

	private String textoEmail;
	private JButton voltar;
	private JLabel informacao;
	private JLabel email;
	protected JTextField campoemail;
	private JButton recuperarSenha;

	public EsqueciMinhaSenha() {

		setTitle("Recuperar Senha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(650, 350);

		JPanel painel = new JPanel();
		painel.setBackground(Color.WHITE);


		voltar = new JButton("<");
		voltar.setBounds(10, 15, 45, 25);
		voltar.setForeground(Color.WHITE);
		Color MinhaCorVoltar = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		voltar.setBackground(MinhaCorVoltar); // Define a cor do botão
		voltar.setContentAreaFilled(false);
		voltar.setOpaque(true);

		informacao = new JLabel("PREENCHA SEU E-MAIL PARA RECUPERAR SUA SENHA");
		informacao.setBounds(150, 84, 400, 15);

		email = new JLabel("E-MAIL");
		email.setBounds(75, 150, 47, 14);

		campoemail = new JTextField();
		campoemail.setBounds(75, 173, 500, 26);

		recuperarSenha = new JButton("Recuperar senha");
		recuperarSenha.setBounds(225, 251, 180, 30);
		recuperarSenha.setForeground(Color.WHITE);
		Color MinhaCorEsqueciSenha = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		recuperarSenha.setBackground(MinhaCorEsqueciSenha); // Define a cor do botão
		recuperarSenha.setContentAreaFilled(false);
		recuperarSenha.setOpaque(true);
		

		painel.add(voltar);
		painel.add(informacao);
		painel.add(campoemail);
		painel.add(recuperarSenha);

		painel.setLayout(null);
		setLocationRelativeTo(null);
		add(painel);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});

		recuperarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 textoEmail = campoemail.getText();

				try {

					Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					String query = "SELECT email FROM usuario WHERE email=?;";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, textoEmail);
					ResultSet rs = preparedStatement.executeQuery();
					if (rs.next()) {

						InformarNovaSenha informarNovaSenha = new InformarNovaSenha(textoEmail);
						informarNovaSenha.setVisible(true);
						dispose();

					} 
					

					preparedStatement.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "E-mail incorreto");


				}
				

			}
		});

	}

	
}
