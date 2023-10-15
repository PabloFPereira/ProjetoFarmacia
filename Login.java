package farmacia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends Cadastro {


	private JTextField campoemail;
	private JPasswordField campoSenha;
	private JButton botaoLogin;
	private JLabel email;
	private JLabel senha;
	private JButton botaoCadastrar;
	private JButton botaoEsqueciMinhaSenha;

	ImageIcon image = new ImageIcon(getClass().getResource("FarmaViva.png"));
	JLabel label = new JLabel(image);

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";

	public Login() {

		setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(650, 350);

		JPanel painel = new JPanel();
		
		painel.setBackground(Color.WHITE);
		
		
		campoemail = new JTextField();
		campoemail.setBounds(384, 59, 242, 28);
		campoSenha = new JPasswordField();
		campoSenha.setBounds(384, 147, 242, 28);
		
		botaoLogin = new JButton("Login");
		botaoLogin.setBounds(415, 216, 180, 30);
		botaoLogin.setForeground(Color.WHITE); // Define a cor do texto do botão para branco
		Color minhaCor = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		botaoLogin.setBackground(minhaCor); // Define a cor do botão
		botaoLogin.setContentAreaFilled(false);
		botaoLogin.setOpaque(true);
		
		email = new JLabel("Email");
		email.setBounds(384, 31, 57, 14);
		
		senha = new JLabel("Senha");
		senha.setBounds(384, 119, 43, 14);
		
		botaoCadastrar = new JButton("Cadastre-se");
		botaoCadastrar.setBounds(415, 256, 180, 30);
		botaoCadastrar.setForeground(Color.WHITE); // Define a cor do texto do botão para branco
		Color minhaCorCadastrar = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		botaoCadastrar.setBackground(minhaCorCadastrar); // Define a cor do botão
		botaoCadastrar.setContentAreaFilled(false);
		botaoCadastrar.setOpaque(true);

		botaoEsqueciMinhaSenha = new JButton("Esqueci a Senha");
		botaoEsqueciMinhaSenha.setBounds(498, 175, 150, 12);
		Color minhaCorSenha = new Color(255, 255, 255); // Cria uma cor com os valores RGB
		botaoEsqueciMinhaSenha.setBackground(minhaCorSenha); // Define a cor do botão
		botaoEsqueciMinhaSenha.setContentAreaFilled(false);
		botaoEsqueciMinhaSenha.setOpaque(true);
		botaoEsqueciMinhaSenha.setBorderPainted(false);
		label.setBounds(5, 70, 375, 139);
		

		painel.add(new JLabel("Email:"));
		painel.add(campoemail);
		painel.add(new JLabel("Senha:"));
		painel.add(campoSenha);
		painel.add(botaoLogin);
		painel.add(email);
		painel.add(senha);
		painel.add(botaoCadastrar);
		painel.add(label);
		painel.add(botaoEsqueciMinhaSenha);

		painel.setLayout(null);
		setLocationRelativeTo(null);
		add(painel);

		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				cadastro.setVisible(true);
				dispose();
			}
		});

		botaoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String email = campoemail.getText();
				String Senha = new String(campoSenha.getPassword());

				try {

					Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					String query = "SELECT usuario, senha FROM email WHERE email=? AND senha=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, Senha);
					ResultSet rs = preparedStatement.executeQuery();
					if (rs.next()) {

						JOptionPane.showMessageDialog(null, "Login feito com sucesso");
						
						Home home = new Home();
						home.setVisible(true);
						dispose();
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
					}

					preparedStatement.close();

				} catch (Exception e2) {
				}
				

			}
			
		});
		
		botaoEsqueciMinhaSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EsqueciMinhaSenha EsqueciMinhaSenha = new EsqueciMinhaSenha();
				EsqueciMinhaSenha.setVisible(true);
				dispose();
			}
		});

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Login login = new Login();
			login.setVisible(true);
		});

	}

}
