package visao;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Retention;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

import controle.PegaArquivos;

/**
 * Interface grafica.
 * 
 * @author
 * 
 */
public class InterfaceSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2761919576643655915L;

	private PegaArquivos arq = null;

	private JLabel digite;
	private JLabel qtdArquivos;
	private JTextField diretorio;
	private JButton analisar;
	private JButton cancelar;
	private Container pane;
	private JScrollPane scrollpane = new JScrollPane();
	private String[] colunas = { "KeyWords", "Quantidade" };
	private JTable listPalavras = new JTable() {
		@Override
		public boolean isCellEditable(int row, int column) { // para que as celulas nao possam ser editadas
			return false;
		}
	};

	/**
	 * Criando/Iniciando todos os componentes da interface
	 */
	public InterfaceSwing() {

		super("Contador de KeyWords");

		// Rótulos
		digite = new JLabel("Digite o diretorio desejado: ");
		qtdArquivos = new JLabel("0 arquivos processados de 0 achados");

		// Campos de Texto
		diretorio = new JTextField(25);

		// Botões
		analisar = new JButton("Analisar");
		cancelar = new JButton("Cancelar");

		// evento
		analisar.addActionListener(new Analisador());
		cancelar.addActionListener(new Cancelador());// manda apagar a caixinha
														// de texto);

		// Captura painel do JFrame
		pane = this.getContentPane();
		// define o layout
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));

		listPalavras.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, colunas));

		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setViewportView(listPalavras);
		listPalavras.getColumnModel().getColumn(1).setResizable(false);

		// adiciona componentes ao painel
		pane.add(digite);
		pane.add(diretorio);
		pane.add(analisar);
		pane.add(cancelar);
		pane.add(scrollpane);
		pane.add(qtdArquivos);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(485, 550);
		this.setResizable(false); // não pode ter seu tamanho redefinido
		this.setVisible(true);
		this.setLayout(null);// define o layout do painel

	}

	/**
	 * Executa a inteface
	 * 
	 */
	public static void main(String[] args) {
		InterfaceSwing view = new InterfaceSwing();
	}

	/**
	 * Classe que executa a acao do botao cancelar da interface
	 * 
	 * @author
	 * 
	 */
	private class Cancelador implements ActionListener {

		/**
		 * Metodo que executa a acao do botao cancelar da interface, apagando o
		 * campo onde o usuario digitou o diretorio a ser buscado, e finalizando
		 * a execucao da thread caso esta ja tenha sido inicializada
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			diretorio.setText("");// apaga o campo do diretorio digitado
			if (arq != null) {
				arq.stop();
			}
		}
	}

	/**
	 * Classe que executa a acao do botao analisar da interface, chamando a
	 * execucao das threads que coletaram as informacoes desejadas
	 * 
	 * @author
	 * 
	 */
	private class Analisador implements ActionListener {

		Map<String, Integer> result = null;

		/**
		 * Medoto que executa a acao do botao analisar da interface
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!(diretorio.getText().trim().equals("") || diretorio.getText() == null)) {

				arq = new PegaArquivos(diretorio.getText());
				arq.start();
				
				while(arq.isAlive()){
					qtdArquivos.setText(arq.totalDearquivosProcessados()
							+ " arquivos processados de "
							+ arq.totalDeArquivosEncontrados() + " achados");
				}

				try {
					arq.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				result = arq.getResultado();
				if (result != null) {
					String[][] linhas = transformaEmMatriz(result);

					for (int i = 0; i < linhas.length; i++) {
						System.out.println("linha: " + toString(linhas[i]));
					}

					// atualiza a jtable
					listPalavras
							.setModel(new javax.swing.table.DefaultTableModel(
									linhas, colunas));
					// atualiza as informacoes sobre quantos arquivos foram
					// encontrados e quantos foram lidos
					qtdArquivos.setText(arq.totalDearquivosProcessados()
							+ " arquivos processados de "
							+ arq.totalDeArquivosEncontrados() + " achados");
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Nao foram encontrados arquivos '.java' neste diretorio. ");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Digite o diretorio que deseja pesquisar.");
			}

		}

		/**
		 * ToString so pra verificar se o resultado esta sendo retornados
		 * 
		 * @param array
		 * @return
		 */
		public String toString(String[] array) {
			String retorno = "[";
			for (int i = 0; i < array.length; i++) {
				retorno += array[i] + ",";
			}
			return retorno + "]";
		}

		/**
		 * Transforma o map resultado da leitura dos arquivos e contagem das
		 * keywords em uma matriz para ser passado a JTable
		 * 
		 * @param result
		 *            - map contendo as keyWords e sua quantidade de repeticoes
		 *            no documento
		 * @return o resultado em forma de matriz
		 */
		private String[][] transformaEmMatriz(Map<String, Integer> result) {
			String[][] retorno = null;
			if (result == null) {
				retorno = new String[][] {};
			} else {
				retorno = new String[result.size()][2];

				Object[] chaves = result.keySet().toArray();

				for (int i = 0; i < result.size(); i++) {
					String[] linha = { chaves[i].toString(),
							(result.get(chaves[i])).toString() };
					retorno[i] = linha;
				}
			}

			return retorno;

		}

	}

}
