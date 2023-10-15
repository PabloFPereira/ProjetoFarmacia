package farmacia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class InformarNovaSenha extends EsqueciMinhaSenha {

	private String email;

	private JLabel senha, confirmarSenha;
	private JButton botao;
	private JPasswordField novaSenha, confirmarNovaSenha;
	private JButton voltar;



	// Conexão com o banco de dados

	private final String DB_URL = "jdbc:mysql://localhost:3306/farmacia";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";

	// Criar método de construção

	public InformarNovaSenha(String textoEmail) {

		this.email = textoEmail;

		setTitle("Informar Nova Senha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(650, 350);

		JPanel painel = new JPanel(); // cria uma painel Java
		painel.setBackground(Color.WHITE);

		senha = new JLabel("Senha");
		senha.setBounds(75, 140, 47, 14);
		
		voltar = new JButton("<");
		voltar.setBounds(10, 15, 45, 25);
		voltar.setForeground(Color.WHITE);
		Color MinhaCorVoltar = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		voltar.setBackground(MinhaCorVoltar); // Define a cor do botão
		voltar.setContentAreaFilled(false);
		voltar.setOpaque(true);
		
		novaSenha = new JPasswordField();
		novaSenha.setBounds(75, 160, 500, 26);
	
		
		confirmarSenha = new JLabel("Confirmar Senha");
		confirmarSenha.setBounds(75, 100, 125, 14);
		
		confirmarNovaSenha = new JPasswordField();
		confirmarNovaSenha.setBounds(75, 220, 500, 26);
		
		botao = new JButton("Alterar Senha");
		botao.setBounds(235, 260, 180, 30);
		botao.setForeground(Color.WHITE);
		Color MinhaCorCadastro = new Color(114, 93, 252); // Cria uma cor com os valores RGB
		botao.setBackground(MinhaCorCadastro); // Define a cor do botão
		botao.setContentAreaFilled(false);
		botao.setOpaque(true);
		

		painel.add(senha);
		painel.add(novaSenha);
		painel.add(confirmarSenha);
		painel.add(confirmarNovaSenha);
		painel.add(botao);
		painel.add(voltar);

		painel.setLayout(null);
		setLocationRelativeTo(null);
		add(painel);

		botao.addActionListener(new ActionListener() { // ação no botão
			@Override
			public void actionPerformed(ActionEvent e) {
				String senha = new String(novaSenha.getPassword());
				String confirmarSenha = new String(confirmarNovaSenha.getPassword());

				if (!senha.equals(confirmarSenha)) {
					JOptionPane.showMessageDialog(null, "Favor verificar se as senhas coincidem.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					String query = "UPDATE usuario SET senha=? WHERE email=?;";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, senha);
					preparedStatement.setString(2, email);
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Senha Alterada com sucesso!");
					preparedStatement.close();

					Login login = new Login();
					login.setVisible(true);
					dispose();

				} catch (Exception e2) {
					System.out.println(e2);

				}
			}

		});

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EsqueciMinhaSenha esqueciMinhaSenha = new EsqueciMinhaSenha();
				esqueciMinhaSenha.setVisible(true);
				dispose();
			}
		});

	}

}
