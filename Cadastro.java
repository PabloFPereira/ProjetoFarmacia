package farmacia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cadastro extends JFrame {

	private JTextField campoNome;
	private JTextField campoemail; // JTextField é campo de texto;
	private JPasswordField campoSenha;
	private JPasswordField campoConfirmaSenha;
	private JButton botaoCadastrar;
	private JButton botaoLogin;
	private JLabel nome;
	private JLabel email;
	private JLabel senha;
	private JLabel confirmarSenha;

	// Conexão com o banco de dados

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";

	// Criar método de construção

	public Cadastro() {

		setTitle("Cadastro de usuário");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(650, 350);

		JPanel painel = new JPanel(); // cria uma painel Java
		painel.setBackground(Color.WHITE);

		nome = new JLabel("Nome");
		nome.setBounds(75, 20, 39, 14);

		campoNome = new JTextField(); // objeto javaTField do campo nome
		campoNome.setBounds(75, 40, 500, 26);

		email = new JLabel("Email");
		email.setBounds(75, 80, 63, 14);

		campoemail = new JTextField();
		campoemail.setBounds(75, 100, 500, 26);

		senha = new JLabel("Senha");
		senha.setBounds(75, 140, 47, 14);

		campoSenha = new JPasswordField();
		campoSenha.setBounds(75, 160, 500, 26);

		confirmarSenha = new JLabel("Confirmar Senha");
		confirmarSenha.setBounds(75, 200, 125, 14);

		campoConfirmaSenha = new JPasswordField();
		campoConfirmaSenha.setBounds(75, 220, 500, 26);

		botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(235, 260, 180, 30);
		botaoCadastrar.setForeground(Color.WHITE);
		Color MinhaCorCadastro = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		botaoCadastrar.setBackground(MinhaCorCadastro); // Define a cor do botão
		botaoCadastrar.setContentAreaFilled(false);
		botaoCadastrar.setOpaque(true);

		botaoLogin = new JButton("<");
		botaoLogin.setBounds(10, 15, 45, 25);
		botaoLogin.setForeground(Color.WHITE);
		Color MinhaCorVoltar = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		botaoLogin.setBackground(MinhaCorVoltar); // Define a cor do botão
		botaoLogin.setContentAreaFilled(false);
		botaoLogin.setOpaque(true);

		painel.add(new JLabel("Nome:")); // adiciona os objetos criados anteriormente dentro do painel Java
		painel.add(campoNome);
		painel.add(new JLabel("Email:"));
		painel.add(campoemail);
		painel.add(new JLabel("Senha:"));
		painel.add(campoSenha);
		painel.add(new JLabel("Confirme sua senha:"));
		painel.add(campoConfirmaSenha);
		painel.add(botaoCadastrar);
		painel.add(botaoLogin);
		painel.add(nome);
		painel.add(senha);
		painel.add(email);
		painel.add(confirmarSenha);

		painel.setLayout(null);
		setLocationRelativeTo(null);
		add(painel);

		botaoCadastrar.addActionListener(new ActionListener() { //
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = campoNome.getText();
				String email = campoemail.getText();
				String senha = new String(campoSenha.getPassword());
				String confirmarSenha = new String(campoConfirmaSenha.getPassword());

				if (!senha.equals(confirmarSenha)) {
					JOptionPane.showMessageDialog(null, "Favor verificar se as senhas coincidem.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					String query = "INSERT INTO usuario(nome, email, senha) VALUES(?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, nome);
					preparedStatement.setString(2, email);
					preparedStatement.setString(3, senha);
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					preparedStatement.close();

					Login login = new Login();
					login.setVisible(true);
					dispose();

				} catch (Exception e2) {

				}
			}

		});

		botaoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});

	}

}
