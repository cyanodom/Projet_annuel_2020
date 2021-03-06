package steinerGraphJava;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import steinerGraphJava.graph.GraphException;
import steinerGraphJava.graphics.GraphicGraph;
import steinerGraphJava.model.ISteinerModel;
import steinerGraphJava.model.SteinerModel;



@SuppressWarnings("deprecation")
public class Steiner {

	private final int V_DEFAULT_SIZE = 1200;
	private final int H_DEFAULT_SIZE = 580;

	// VIEWS :

	private final int ID_GRAPH_VIEW = 2;
	private final int ID_EQUAL_VIEW = 1;
	private final int ID_CONTROL_VIEW = 0;
	private final int ID_DEFAULT_VIEW = ID_EQUAL_VIEW;

	private int idActualView;

	private JFrame mainFrame;
	private JPanel mainPanel;

	private JMenuBar menuBar;
	private JMenuItem fileAddMenu;
	private JMenuItem fileRemoveMenu;
	private JMenuItem fileSaveMenu;
	private JMenuItem editUndoMenu;
	private JMenuItem editRedoMenu;
	private JMenuItem graphEmptyMenu;
	private JMenuItem graphAddMenu;
	private JMenuItem graphRemoveMenu;
	private JMenuItem graphRenameMenu;
	private JMenuItem steinerSolveMenu;
	private JMenuItem steinerSeeOriginalMenu;
	private JMenuItem statsSeeMenu;
	private JMenuItem viewSwitchGraphMenu;
	private JMenuItem viewSwitchEqualMenu;
	private JMenuItem viewSwitchControlMenu;

	private JButton fileAddButton;
	private JButton fileRemoveButton;
	private JButton fileSaveButton;
	private JButton editUndoButton;
	private JButton editRedoButton;
	private JButton graphEmptyButton;
	private JButton graphAddButton;
	private JButton graphRemoveButton;
	private JButton graphRenameButton;
	private JButton steinerSolveButton;
	private JButton steinerSeeOriginalButton;
	private JButton statsSeeButton;

	private JTextField graphAddField;
	private JTextField graphRemoveField;
	private JTextField graphRenameSourceField;
	private JTextField graphRenameDestField;

	private GraphicGraph graphicGraph;
	private JPanel filesPanel;

	private JLabel statNbArc;
	private JLabel statNbNode;
	private JLabel statNbModification;
	private JLabel statEfficiency;
	private JLabel statTimeSolve;

	private ISteinerModel model;

	public Steiner() {
		createModel();
		createView();
		placeComponents();
		createController();
	}

	public void createView() {
		mainFrame = new JFrame("Steiner");
		mainFrame.setPreferredSize(new Dimension(V_DEFAULT_SIZE, H_DEFAULT_SIZE));

		graphicGraph = new GraphicGraph(model);

		idActualView = ID_DEFAULT_VIEW;
		mainPanel = new JPanel();

		fileAddButton = new JButton("Ajouter");
		fileRemoveButton = new JButton("Retirer");
		fileSaveButton = new JButton("Sauvegarder");
		editUndoButton = new JButton("Annuler");
		editRedoButton = new JButton("Refaire");
		graphEmptyButton = new JButton("Vider");
		graphAddButton = new JButton("Ajouter");
		graphRemoveButton = new JButton("Retirer");
		graphRenameButton = new JButton("Renommer");
		steinerSolveButton = new JButton("Résoudre");
		steinerSeeOriginalButton = new JButton("Voir l'original");
		statsSeeButton = new JButton("Voir");

		steinerSolveMenu = new JMenuItem("Résoudre le modèle");
		steinerSeeOriginalMenu = new JMenuItem("Voir l'original");
		fileAddMenu = new JMenuItem("Ajouter un fichier");
		fileRemoveMenu = new JMenuItem("Retirer un fichier");
		fileSaveMenu = new JMenuItem("Sauvegarder dans un fichier");
		editUndoMenu = new JMenuItem("Annuler la dernière action");
		editRedoMenu = new JMenuItem("Refaire la dernière action annulée");
		graphEmptyMenu = new JMenuItem("Vider le graph");
		graphAddMenu = new JMenuItem("Ajouter un élément");
		graphRemoveMenu =new JMenuItem("Retirer un élément");
		graphRenameMenu = new JMenuItem("Renommer un noeud");
		statsSeeMenu = new JMenuItem("Voir les stats");
		viewSwitchGraphMenu = new JMenuItem("Basculer vers la vue en mode < graph >");
		viewSwitchEqualMenu = new JMenuItem("Basculer vers la vue en mode < équilibrée >");
		viewSwitchControlMenu = new JMenuItem("Basculer vers la vue en mode < contrôle >");

		graphAddField = new JTextField();
		graphAddField.setColumns(10);
		graphRemoveField = new JTextField();
		graphRemoveField.setColumns(10);
		graphRenameSourceField = new JTextField();
		graphRenameSourceField.setColumns(10);
		graphRenameDestField = new JTextField();
		graphRenameDestField.setColumns(10);

		filesPanel = new JPanel();
		filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));

		statNbArc = new JLabel();
		statNbNode = new JLabel();
		statNbModification = new JLabel();
		statEfficiency = new JLabel();
		statTimeSolve = new JLabel();

		menuBar = new JMenuBar(); {
			JMenu m = new JMenu("Steiner"); {
				m.add(steinerSolveMenu);
				m.add(steinerSeeOriginalMenu);
			}
			menuBar.add(m);
			m = new JMenu("Fichier"); {
				m.add(fileAddMenu);
				m.add(fileRemoveMenu);
				m.add(fileSaveMenu);
			}
			menuBar.add(m);
			m = new JMenu("Edition"); {
				m.add(editUndoMenu);
				m.add(editRedoMenu);
			}
			menuBar.add(m);
			m = new JMenu("Graph"); {
				m.add(graphEmptyMenu);
				m.add(graphAddMenu);
				m.add(graphRemoveMenu);
				m.add(graphRenameMenu);
			}
			menuBar.add(m);
			m = new JMenu("View"); {
				m.add(viewSwitchGraphMenu);
				m.add(viewSwitchEqualMenu);
				m.add(viewSwitchControlMenu);
			}
			menuBar.add(m);
			m = new JMenu("Stats"); {
				m.add(statsSeeMenu);
			}
			menuBar.add(m);
		}
	}

	public void createModel() {
		model = new SteinerModel();
	}

	public void switchToView(int viewId) {
		idActualView = viewId;
		rebuildView();
	}

	public void rebuildView() {
		mainPanel.removeAll();
		placeComponentsView(idActualView);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	public void placeComponents() {
		placeComponentsView(ID_DEFAULT_VIEW);
		mainFrame.add(mainPanel);
	}

	private void placeComponentsView(int viewId) {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuBar, BorderLayout.NORTH);

		if (viewId == ID_GRAPH_VIEW) {

			JPanel contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());

			contentPane.add(new JScrollPane(graphicGraph));

			mainPanel.add(contentPane, BorderLayout.CENTER);

		} else if (viewId == ID_EQUAL_VIEW) {

			JPanel contentPane = new JPanel(); {


				//-----------EQUAL-VIEW------------------


				contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

				JSplitPane splitPane = new JSplitPane();

				JScrollPane scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);

				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				Component verticalStrut = Box.createVerticalStrut(30);
				verticalStrut.setMinimumSize(new Dimension(0, 30));
				panel.add(verticalStrut);

				Component verticalGlue_1 = Box.createVerticalGlue();
				panel.add(verticalGlue_1);

				JPanel panel_15 = new JPanel();
				panel.add(panel_15);
				panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));

				Component horizontalStrut_9 = Box.createHorizontalStrut(30);
				horizontalStrut_9.setMinimumSize(new Dimension(30, 0));
				panel_15.add(horizontalStrut_9);

				Component horizontalGlue_2 = Box.createHorizontalGlue();
				panel_15.add(horizontalGlue_2);

				JPanel panel_16 = new JPanel();
				panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_15.add(panel_16);
				panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.Y_AXIS));

				JPanel Fichier = new JPanel();
				panel_16.add(Fichier);
				Fichier.setLayout(new BoxLayout(Fichier, BoxLayout.Y_AXIS));

				JPanel panel_3 = new JPanel();
				Fichier.add(panel_3);
				panel_3.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

				panel_3.add(new JLabel("Fichier"));

				JPanel panel_2 = new JPanel();
				Fichier.add(panel_2);
				FlowLayout flowLayout_7 = (FlowLayout) panel_2.getLayout();
				flowLayout_7.setAlignment(FlowLayout.LEADING);

				Component horizontalStrut = Box.createHorizontalStrut(30);
				panel_2.add(horizontalStrut);

				panel_2.add(fileAddButton);
				panel_2.add(fileRemoveButton);
				panel_2.add(fileSaveButton);

				JSeparator separator_1 = new JSeparator();
				panel_16.add(separator_1);

				JPanel Edition = new JPanel();
				panel_16.add(Edition);
				Edition.setLayout(new BoxLayout(Edition, BoxLayout.Y_AXIS));

				JPanel panel_9 = new JPanel();
				FlowLayout flowLayout_5 = (FlowLayout) panel_9.getLayout();
				flowLayout_5.setAlignment(FlowLayout.LEADING);
				Edition.add(panel_9);

				panel_9.add(new JLabel("Edition"));

				JPanel panel_10 = new JPanel();
				FlowLayout flowLayout_6 = (FlowLayout) panel_10.getLayout();
				flowLayout_6.setAlignment(FlowLayout.LEADING);
				Edition.add(panel_10);

				Component horizontalStrut_1 = Box.createHorizontalStrut(30);
				panel_10.add(horizontalStrut_1);

				panel_10.add(editUndoButton);
				panel_10.add(editRedoButton);

				JSeparator separator_2 = new JSeparator();
				panel_16.add(separator_2);

				JPanel Graph = new JPanel();
				panel_16.add(Graph);
				Graph.setLayout(new BoxLayout(Graph, BoxLayout.Y_AXIS));

				JPanel panel_4 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				Graph.add(panel_4);

				panel_4.add(new JLabel("Graph"));

				JPanel panel_5 = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEADING);
				Graph.add(panel_5);

				Component horizontalStrut_2 = Box.createHorizontalStrut(30);
				panel_5.add(horizontalStrut_2);

				panel_5.add(graphEmptyButton);

				JPanel panel_6 = new JPanel();
				FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
				flowLayout_2.setAlignment(FlowLayout.LEADING);
				Graph.add(panel_6);

				Component horizontalStrut_3 = Box.createHorizontalStrut(30);
				panel_6.add(horizontalStrut_3);

				panel_6.add(graphAddButton);

				panel_6.add(new JLabel("l'élément :"));

				panel_6.add(graphAddField);

				JPanel panel_8 = new JPanel();
				FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
				flowLayout_3.setAlignment(FlowLayout.LEADING);
				Graph.add(panel_8);

				Component horizontalStrut_4 = Box.createHorizontalStrut(30);
				panel_8.add(horizontalStrut_4);

				panel_8.add(graphRemoveButton);

				JLabel lblLeNoeud_2 = new JLabel("l'élément :");
				panel_8.add(lblLeNoeud_2);

				panel_8.add(graphRemoveField);

				JPanel panel_7 = new JPanel();
				FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
				flowLayout_4.setAlignment(FlowLayout.LEADING);
				Graph.add(panel_7);

				Component horizontalStrut_5 = Box.createHorizontalStrut(30);
				panel_7.add(horizontalStrut_5);

				panel_7.add(graphRenameButton);
				JLabel lblDe = new JLabel("de :");
				panel_7.add(lblDe);
				panel_7.add(graphRenameSourceField );
				JLabel lblEn = new JLabel("en");
				panel_7.add(lblEn);
				panel_7.add(graphRenameDestField);

				JSeparator separator_3 = new JSeparator();
				panel_16.add(separator_3);

				JPanel Stats = new JPanel();
				panel_16.add(Stats);
				Stats.setLayout(new BoxLayout(Stats, BoxLayout.Y_AXIS));

				JPanel panel_13 = new JPanel();
				FlowLayout flowLayout_9 = (FlowLayout) panel_13.getLayout();
				flowLayout_9.setAlignment(FlowLayout.LEADING);
				Stats.add(panel_13);

				JLabel lblStats = new JLabel("Stats");
				panel_13.add(lblStats);

				JPanel panel_14 = new JPanel();
				FlowLayout flowLayout_10 = (FlowLayout) panel_14.getLayout();
				flowLayout_10.setAlignment(FlowLayout.LEADING);
				Stats.add(panel_14);

				Component horizontalStrut_6 = Box.createHorizontalStrut(30);
				panel_14.add(horizontalStrut_6);

				panel_14.add(statsSeeButton);

				Component horizontalGlue_1 = Box.createHorizontalGlue();
				panel_14.add(horizontalGlue_1);

				JSeparator separator = new JSeparator();
				panel_16.add(separator);

				JPanel Steiner = new JPanel();
				panel_16.add(Steiner);
				Steiner.setLayout(new BoxLayout(Steiner, BoxLayout.Y_AXIS));

				JPanel panel_11 = new JPanel();
				FlowLayout flowLayout_8 = (FlowLayout) panel_11.getLayout();
				flowLayout_8.setAlignment(FlowLayout.LEADING);
				Steiner.add(panel_11);

				panel_11.add(new JLabel("Steiner"));

				JPanel panel_12 = new JPanel();
				Steiner.add(panel_12);
				panel_12.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

				Component horizontalStrut_7 = Box.createHorizontalStrut(30);
				panel_12.add(horizontalStrut_7);

				panel_12.add(steinerSolveButton);

				panel_12.add(steinerSeeOriginalButton);

				Component horizontalStrut_8 = Box.createHorizontalStrut(30);
				horizontalStrut_8.setMinimumSize(new Dimension(30, 0));
				panel_15.add(horizontalStrut_8);

				Component horizontalGlue = Box.createHorizontalGlue();
				panel_15.add(horizontalGlue);

				Component verticalStrut_1 = Box.createVerticalStrut(30);
				verticalStrut_1.setMinimumSize(new Dimension(0, 30));
				panel.add(verticalStrut_1);

				Component verticalGlue = Box.createVerticalGlue();
				panel.add(verticalGlue);

				splitPane.setRightComponent(new JScrollPane(graphicGraph));

				contentPane.add(splitPane);

				//-----------EQUAL-VIEW------------------


			}
			mainPanel.add(contentPane);

		} else if (viewId == ID_CONTROL_VIEW) {

			//--------------CONTROL-VIEW-----------------

			JPanel contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());

			JSplitPane splitPane = new JSplitPane();
			contentPane.add(splitPane, BorderLayout.CENTER);

			JPanel leftPanel = new JPanel();

			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			leftPanel.setLayout(gridBagLayout);

			JPanel panel_4 = new JPanel();
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.insets = new Insets(0, 0, 5, 5);
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 1;
			gbc_panel_4.gridy = 0;
			leftPanel.add(panel_4, gbc_panel_4);
			panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

			Component verticalStrut = Box.createVerticalStrut(30);
			panel_4.add(verticalStrut);

			Component verticalGlue = Box.createVerticalGlue();
			panel_4.add(verticalGlue);

			JPanel panel_6 = new JPanel();
			GridBagConstraints gbc_panel_6 = new GridBagConstraints();
			gbc_panel_6.insets = new Insets(0, 0, 5, 5);
			gbc_panel_6.fill = GridBagConstraints.BOTH;
			gbc_panel_6.gridx = 0;
			gbc_panel_6.gridy = 1;
			leftPanel.add(panel_6, gbc_panel_6);
			panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

			Component horizontalStrut = Box.createHorizontalStrut(30);
			horizontalStrut.setPreferredSize(new Dimension(30, 0));
			panel_6.add(horizontalStrut);

			Component horizontalGlue_2 = Box.createHorizontalGlue();
			panel_6.add(horizontalGlue_2);

			JLabel lblFichier = new JLabel("Fichier");
			GridBagConstraints gbc_lblFichier = new GridBagConstraints();
			gbc_lblFichier.insets = new Insets(0, 0, 5, 5);
			gbc_lblFichier.gridx = 1;
			gbc_lblFichier.gridy = 1;
			leftPanel.add(lblFichier, gbc_lblFichier);

			Component horizontalGlue_1 = Box.createHorizontalGlue();
			GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
			gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 5);
			gbc_horizontalGlue_1.gridx = 2;
			gbc_horizontalGlue_1.gridy = 1;
			leftPanel.add(horizontalGlue_1, gbc_horizontalGlue_1);

			GridBagConstraints gbc_btnAjouter_1 = new GridBagConstraints();
			gbc_btnAjouter_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAjouter_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnAjouter_1.gridx = 3;
			gbc_btnAjouter_1.gridy = 1;
			leftPanel.add(fileAddButton, gbc_btnAjouter_1);

			GridBagConstraints gbc_btnSauvegarder = new GridBagConstraints();
			gbc_btnSauvegarder.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSauvegarder.insets = new Insets(0, 0, 5, 5);
			gbc_btnSauvegarder.gridx = 4;
			gbc_btnSauvegarder.gridy = 1;
			leftPanel.add(fileSaveButton, gbc_btnSauvegarder);

			JPanel panel_7 = new JPanel();
			GridBagConstraints gbc_panel_7 = new GridBagConstraints();
			gbc_panel_7.insets = new Insets(0, 0, 5, 0);
			gbc_panel_7.fill = GridBagConstraints.BOTH;
			gbc_panel_7.gridx = 7;
			gbc_panel_7.gridy = 1;
			leftPanel.add(panel_7, gbc_panel_7);
			panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

			Component horizontalStrut_1 = Box.createHorizontalStrut(30);
			panel_7.add(horizontalStrut_1);

			Component horizontalGlue = Box.createHorizontalGlue();
			panel_7.add(horizontalGlue);

			Component verticalGlue_2 = Box.createVerticalGlue();
			GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
			gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
			gbc_verticalGlue_2.gridx = 1;
			gbc_verticalGlue_2.gridy = 2;
			leftPanel.add(verticalGlue_2, gbc_verticalGlue_2);

			JLabel lblEdition = new JLabel("Edition");
			GridBagConstraints gbc_lblEdition = new GridBagConstraints();
			gbc_lblEdition.insets = new Insets(0, 0, 5, 5);
			gbc_lblEdition.gridx = 1;
			gbc_lblEdition.gridy = 3;
			leftPanel.add(lblEdition, gbc_lblEdition);

			GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
			gbc_btnAnnuler.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAnnuler.insets = new Insets(0, 0, 5, 5);
			gbc_btnAnnuler.gridx = 3;
			gbc_btnAnnuler.gridy = 3;
			leftPanel.add(editUndoButton, gbc_btnAnnuler);

			GridBagConstraints gbc_btnRefaire = new GridBagConstraints();
			gbc_btnRefaire.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRefaire.insets = new Insets(0, 0, 5, 5);
			gbc_btnRefaire.gridx = 4;
			gbc_btnRefaire.gridy = 3;
			leftPanel.add(editRedoButton, gbc_btnRefaire);

			Component verticalGlue_3 = Box.createVerticalGlue();
			GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
			gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
			gbc_verticalGlue_3.gridx = 1;
			gbc_verticalGlue_3.gridy = 4;
			leftPanel.add(verticalGlue_3, gbc_verticalGlue_3);

			JLabel lblGraph = new JLabel("Graph");
			GridBagConstraints gbc_lblGraph = new GridBagConstraints();
			gbc_lblGraph.insets = new Insets(0, 0, 5, 5);
			gbc_lblGraph.gridx = 1;
			gbc_lblGraph.gridy = 5;
			leftPanel.add(lblGraph, gbc_lblGraph);

			GridBagConstraints gbc_btnVider = new GridBagConstraints();
			gbc_btnVider.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVider.insets = new Insets(0, 0, 5, 5);
			gbc_btnVider.gridx = 3;
			gbc_btnVider.gridy = 5;
			leftPanel.add(graphEmptyButton, gbc_btnVider);

			GridBagConstraints gbc_btnAjouter = new GridBagConstraints();
			gbc_btnAjouter.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAjouter.insets = new Insets(0, 0, 5, 5);
			gbc_btnAjouter.gridx = 3;
			gbc_btnAjouter.gridy = 6;
			leftPanel.add(graphAddButton, gbc_btnAjouter);

			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 4;
			gbc_panel.gridy = 6;
			leftPanel.add(panel, gbc_panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

			JLabel lblLeNoeud = new JLabel("l'élément :");
			panel.add(lblLeNoeud);

			panel.add(graphAddField);

			GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
			gbc_btnSupprimer.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSupprimer.insets = new Insets(0, 0, 5, 5);
			gbc_btnSupprimer.gridx = 5;
			gbc_btnSupprimer.gridy = 6;
			leftPanel.add(graphRemoveButton, gbc_btnSupprimer);

			JPanel panel_1 = new JPanel();
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.insets = new Insets(0, 0, 5, 5);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 6;
			gbc_panel_1.gridy = 6;
			leftPanel.add(panel_1, gbc_panel_1);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

			JLabel lblLeNoeud_1 = new JLabel("l'élément :");
			panel_1.add(lblLeNoeud_1);

			panel_1.add(graphRemoveField);

			GridBagConstraints gbc_btnRenommer = new GridBagConstraints();
			gbc_btnRenommer.insets = new Insets(0, 0, 5, 5);
			gbc_btnRenommer.gridx = 3;
			gbc_btnRenommer.gridy = 7;
			leftPanel.add(graphRenameButton, gbc_btnRenommer);

			JPanel panel_2 = new JPanel();
			GridBagConstraints gbc_panel_2 = new GridBagConstraints();
			gbc_panel_2.insets = new Insets(0, 0, 5, 5);
			gbc_panel_2.fill = GridBagConstraints.BOTH;
			gbc_panel_2.gridx = 4;
			gbc_panel_2.gridy = 7;
			leftPanel.add(panel_2, gbc_panel_2);
			panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

			JLabel lblDe = new JLabel("de :");
			panel_2.add(lblDe);

			panel_2.add(graphRenameSourceField);

			JPanel panel_3 = new JPanel();
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.insets = new Insets(0, 0, 5, 5);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 5;
			gbc_panel_3.gridy = 7;
			leftPanel.add(panel_3, gbc_panel_3);
			panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

			JLabel lblEn = new JLabel("en :");
			panel_3.add(lblEn);

			panel_3.add(graphRenameDestField);

			GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
			gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
			gbc_verticalGlue_4.gridx = 1;
			gbc_verticalGlue_4.gridy = 8;
			leftPanel.add(Box.createVerticalGlue(), gbc_verticalGlue_4);

			JLabel lblSteiner = new JLabel("Steiner");
			GridBagConstraints gbc_lblSteiner = new GridBagConstraints();
			gbc_lblSteiner.insets = new Insets(0, 0, 5, 5);
			gbc_lblSteiner.gridx = 1;
			gbc_lblSteiner.gridy = 9;
			leftPanel.add(lblSteiner, gbc_lblSteiner);

			GridBagConstraints gbc_btnRsoudre = new GridBagConstraints();
			gbc_btnRsoudre.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRsoudre.insets = new Insets(0, 0, 5, 5);
			gbc_btnRsoudre.gridx = 3;
			gbc_btnRsoudre.gridy = 9;
			leftPanel.add(steinerSolveButton, gbc_btnRsoudre);

			GridBagConstraints gbc_btnVoirLoriginal = new GridBagConstraints();
			gbc_btnVoirLoriginal.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnVoirLoriginal.insets = new Insets(0, 0, 5, 5);
			gbc_btnVoirLoriginal.gridx = 4;
			gbc_btnVoirLoriginal.gridy = 9;
			leftPanel.add(steinerSeeOriginalButton, gbc_btnVoirLoriginal);

			JPanel panel_5 = new JPanel();
			GridBagConstraints gbc_panel_5 = new GridBagConstraints();
			gbc_panel_5.insets = new Insets(0, 0, 0, 5);
			gbc_panel_5.fill = GridBagConstraints.BOTH;
			gbc_panel_5.gridx = 1;
			gbc_panel_5.gridy = 10;
			leftPanel.add(panel_5, gbc_panel_5);
			panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

			panel_5.add(Box.createVerticalStrut(30));
			panel_5.add(Box.createVerticalGlue());

			splitPane.setLeftComponent(leftPanel);

			JPanel panel_8 = new JPanel();

			JPanel panel_15 = new JPanel();
			panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));
			panel_15.add(Box.createHorizontalStrut(30));
			panel_15.add(Box.createHorizontalGlue());
			panel_15.add(panel_8);
			panel_15.add(Box.createHorizontalStrut(30));
			panel_15.add(Box.createHorizontalGlue());

			panel_8.setLayout(new BoxLayout(panel_8 ,BoxLayout.Y_AXIS));

			panel_8.add(Box.createVerticalStrut(30));
			panel_8.add(Box.createVerticalGlue());


			FlowLayout flowLwt = new FlowLayout();
			flowLwt.setAlignment(FlowLayout.LEADING);

			JPanel panel_14 = new JPanel(flowLwt);
			panel_14.add(new JLabel("Fichiers chargés :"));
			panel_8.add(panel_14);

			panel_8.add(filesPanel);

			JPanel panel_9 = new JPanel(flowLwt);
			panel_9.add(new JLabel("Nombre d'arcs :"));
			panel_9.add(statNbArc);
			panel_8.add(panel_9);

			JPanel panel_10 = new JPanel(flowLwt);
			panel_10.add(new JLabel("Nombre de Noeuds :"));
			panel_10.add(statNbNode);
			panel_8.add(panel_10);

			JPanel panel_11 = new JPanel(flowLwt);
			panel_11.add(new JLabel("Poids de l'arbre :"));
			panel_11.add(statNbModification);
			panel_8.add(panel_11);

			JPanel panel_12 = new JPanel(flowLwt);
			panel_12.add(new JLabel("Pénalité :"));
			panel_12.add(statEfficiency);
			panel_12.add(new JLabel(" Arbres"));
			panel_8.add(panel_12);

			JPanel panel_13 = new JPanel(flowLwt);
			panel_13.add(new JLabel("Temps pour résoudre :"));
			panel_13.add(statTimeSolve);
			panel_8.add(panel_13);

			panel_8.add(Box.createVerticalStrut(30));
			panel_8.add(Box.createVerticalGlue());


			splitPane.setRightComponent(new JScrollPane(panel_15));

			mainPanel.add(contentPane, BorderLayout.CENTER);


			//--------------CONTROL-VIEW-----------------
		}
	}

	public void createController() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionListener fileAddAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();

				FileNameExtensionFilter graphFilter = new FileNameExtensionFilter("steiner graph file (*.gstein)", "gstein");
				fc.setFileFilter(graphFilter);


				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						model.addFile(fc.getSelectedFile());
					} catch (GraphException e) {
						errorOccured(e);
					}
				}
			}

		};
		fileAddMenu.addActionListener(fileAddAction);
		fileAddButton.addActionListener(fileAddAction);

		ActionListener fileRemoveAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame removeFileFrame = new JFrame();
				removeFileFrame.setTitle("Retirer un fichier");
				removeFileFrame.setResizable(false);

				removeFileFrame.setPreferredSize(new Dimension(400, 400));

				JPanel panel_1 = new JPanel(new BorderLayout());

				class HackedString implements CharSequence {

					String value;
					Integer correspondingIndex;

					public HackedString(String s, Integer i) {
						correspondingIndex = i;
						value = s;
					}

					@Override
					public char charAt(int arg0) {
						return value.charAt(arg0);
					}

					@Override
					public int length() {
						return value.length();
					}

					@Override
					public CharSequence subSequence(int arg0, int arg1) {
						return value.subSequence(arg0, arg1);
					}

					@Override
					public String toString() {
						return value;
					}

					public Integer getCorrespondingIndex() {
						return correspondingIndex;
					}

				}

				DefaultListModel<HackedString> listModel = new DefaultListModel<HackedString>();

				int i = 0;

				for (String e : model.getFileNames()) {
					listModel.addElement(new HackedString(e, i));
					++i;
				}

				JList<HackedString> fileList = new JList<HackedString>(listModel);
				fileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

				panel_1.add(fileList,BorderLayout.CENTER);
				JButton buttonRemove = new JButton("retirer !");
				JButton buttonOk = new JButton("Ok !");
				JButton buttonAbort = new JButton("Annuler tout !");

				JPanel panel_2 = new JPanel();
				panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
				panel_2.add(buttonRemove);
				panel_2.add(Box.createHorizontalGlue());
				panel_2.add(buttonOk);
				panel_2.add(Box.createHorizontalGlue());
				panel_2.add(buttonAbort);


				panel_1.add(panel_2, BorderLayout.SOUTH);


				//FRAME CONTROLLER :

				List<HackedString> removedItems = new LinkedList<HackedString>();

				buttonRemove.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						removedItems.add(fileList.getSelectedValue());
						listModel.remove(fileList.getSelectedIndex());
					}

				});

				buttonOk.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String message = new String();
						for (HackedString e : removedItems) {
							message += "Retirer : " + e.getCorrespondingIndex() + " : " + e + "\n";
						}
						int answer = JOptionPane.showConfirmDialog(null, "êtes vous sûr d'appliquer ces modifications ? \n" + message, "Attention !",
								JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							for (HackedString e : removedItems) {
								try {
									model.removeFileAtIndex(e.getCorrespondingIndex());
								} catch (GraphException e1) {
									errorOccured(e1);
								}
							}
							removeFileFrame.dispose();
						}
					}

				});

				removeFileFrame.add(panel_1);
				removeFileFrame.pack();
				removeFileFrame.setLocationRelativeTo(null);
				removeFileFrame.setVisible(true);

			}

		};
		fileRemoveMenu.addActionListener(fileRemoveAction);
		fileRemoveButton.addActionListener(fileRemoveAction);

		ActionListener fileSaveAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();

				FileNameExtensionFilter graphFilter = new FileNameExtensionFilter("steiner graph file (*.gstein)", "gstein");
				fc.setFileFilter(graphFilter);


				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						model.saveFileTo(fc.getSelectedFile());
					} catch (GraphException e) {
						errorOccured(e);
					}
				}
			}

		};
		fileSaveMenu.addActionListener(fileSaveAction);;
		fileSaveButton.addActionListener(fileSaveAction);

		ActionListener editUndoAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.undo();
			}

		};
		editUndoMenu.addActionListener(editUndoAction);
		editUndoButton.addActionListener(editUndoAction);

		ActionListener editRedoAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.redo();
			}

		};
		editRedoMenu.addActionListener(editRedoAction);
		editRedoButton.addActionListener(editRedoAction);

		ActionListener graphEmptyAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.emptyGraph();
			}

		};
		graphEmptyMenu.addActionListener(graphEmptyAction);
		graphEmptyButton.addActionListener(graphEmptyAction);

		ActionListener graphAddFrameAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String answer = JOptionPane.showInputDialog(null, "Ajouter cet élément : ", "Ajouter un élément", JOptionPane.YES_NO_OPTION);
				if (answer != null) {
					try {
						model.addElement(answer);
					} catch (GraphException e) {
						errorOccured(e);
					}
				}
			}

		};
		ActionListener graphAddAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.addElement(graphAddField.getText());
				} catch (GraphException e) {
					errorOccured(e);
				}
				graphAddField.setText("");
			}

		};
		graphAddMenu.addActionListener(graphAddFrameAction);
		graphAddButton.addActionListener(graphAddAction);
		graphAddField.addActionListener(graphAddAction);

		ActionListener graphRemoveFrameAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String answer = JOptionPane.showInputDialog(null, "Retirer cet élément : ", "Retirer un élément", JOptionPane.YES_NO_OPTION);
				if (answer != null) {
					try {
						model.removeElement(answer);
					} catch (GraphException e) {
						errorOccured(e);
					}
				}
			}

		};
		ActionListener graphRemoveAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.removeElement(graphRemoveField.getText());
				} catch (GraphException e) {
					errorOccured(e);
				}
				graphRemoveField.setText("");
			}

		};
		graphRemoveMenu.addActionListener(graphRemoveFrameAction);
		graphRemoveButton.addActionListener(graphRemoveAction);
		graphRemoveField.addActionListener(graphRemoveAction);

		ActionListener graphRenameFrameAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String answerSource = null;
				do {
					String warning = "";
					if (answerSource != null) {
						warning = "Ce noeud n'existe pas ! Réessayez ! \n";
					}
					answerSource = JOptionPane.showInputDialog(null, warning + "Renommer ce Noeud : ", "Renommer un noeud", JOptionPane.YES_NO_OPTION);
					if (answerSource == null) {
						return;
					}
				} while (model.checkNodeExist(answerSource) == false);

				String answerDest = null;

				do {
					String warning = "";
					if (answerDest != null) {
						warning = "Ce noeud existe déja ! Réessayez !\n";
					}
					answerDest = JOptionPane.showInputDialog(null, warning + "Renommer ce Noeud : ", "Renommer un noeud", JOptionPane.YES_NO_OPTION);
					if (answerDest == null) {
						return;
					}
				} while (model.checkNodeExist(answerDest));
				try {
					model.renameNode(answerSource, answerDest);
				} catch (GraphException e) {
					errorOccured(e);
				}
			}

		};
		ActionListener graphRenameAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.renameNode(graphRenameSourceField.getText(), graphRenameDestField.getText());
				} catch (GraphException e) {
					errorOccured(e);
				}
			}

		};
		graphRenameMenu.addActionListener(graphRenameFrameAction);
		graphRenameButton.addActionListener(graphRenameAction);
		graphRenameDestField.addActionListener(graphRenameAction);


		ActionListener steinerSolveAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.solve();
				} catch (GraphException e) {
					errorOccured(e);
				}
			}

		};
		steinerSolveMenu.addActionListener(steinerSolveAction);
		steinerSolveButton.addActionListener(steinerSolveAction);

		ActionListener steinerSeeOriginalAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					model.switchGraphToOriginal();
				} catch (GraphException e) {
					errorOccured(e);
				}
			}

		};
		steinerSeeOriginalMenu.addActionListener(steinerSeeOriginalAction);;
		steinerSeeOriginalButton.addActionListener(steinerSeeOriginalAction);;

		ActionListener statSeeAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame statFrame = new JFrame();

				JPanel panel_8 = new JPanel();

				JPanel panel_15 = new JPanel();
				panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));
				panel_15.add(Box.createHorizontalStrut(30));
				panel_15.add(Box.createHorizontalGlue());
				panel_15.add(panel_8);
				panel_15.add(Box.createHorizontalStrut(30));
				panel_15.add(Box.createHorizontalGlue());

				panel_8.setLayout(new BoxLayout(panel_8 ,BoxLayout.Y_AXIS));

				panel_8.add(Box.createVerticalStrut(30));
				panel_8.add(Box.createVerticalGlue());


				FlowLayout flowLwt = new FlowLayout();
				flowLwt.setAlignment(FlowLayout.LEADING);

				JPanel panel_14 = new JPanel(flowLwt);
				panel_14.add(new JLabel("Fichiers chargés :"));
				panel_8.add(panel_14);

				panel_8.add(filesPanel);

				JPanel panel_9 = new JPanel(flowLwt);
				panel_9.add(new JLabel("Nombre d'arcs :"));
				panel_9.add(statNbArc);
				panel_8.add(panel_9);

				JPanel panel_10 = new JPanel(flowLwt);
				panel_10.add(new JLabel("Nombre de Noeuds :"));
				panel_10.add(statNbNode);
				panel_8.add(panel_10);

				JPanel panel_11 = new JPanel(flowLwt);
				panel_11.add(new JLabel("poids de l'arbre :"));
				panel_11.add(statNbModification);
				panel_8.add(panel_11);

				JPanel panel_12 = new JPanel(flowLwt);
				panel_12.add(new JLabel("Efficacitée de la solution :"));
				panel_12.add(statEfficiency);
				panel_12.add(new JLabel("Noeuds"));
				panel_8.add(panel_12);

				JPanel panel_13 = new JPanel(flowLwt);
				panel_13.add(new JLabel("Temps pour résoudre :"));
				panel_13.add(statTimeSolve);
				panel_8.add(panel_13);

				panel_8.add(Box.createVerticalStrut(30));
				panel_8.add(Box.createVerticalGlue());

				JPanel panel_16 = new JPanel(new BorderLayout());

				panel_16.add(panel_15, BorderLayout.CENTER);

				statFrame.add(new JScrollPane(panel_16));
				statFrame.setPreferredSize(new Dimension(400, 400));
				statFrame.setTitle("Stats");
				statFrame.pack();
				statFrame.setLocationRelativeTo(null);
				statFrame.setVisible(true);
			}

		};

		statsSeeMenu.addActionListener(statSeeAction);
		statsSeeButton.addActionListener(statSeeAction);

		viewSwitchGraphMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToView(ID_GRAPH_VIEW);
			}

		});
		viewSwitchEqualMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToView(ID_EQUAL_VIEW);
			}

		});
		viewSwitchControlMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchToView(ID_CONTROL_VIEW);
			}

		});

		model.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				refresh();
			}

		});
	}

	public void display() {
		refresh();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void refresh() {
		if (model.getFileNames().isEmpty()) {
			fileRemoveButton.setEnabled(false);
			fileRemoveMenu.setEnabled(false);
		} else {
			fileRemoveButton.setEnabled(true);
			fileRemoveMenu.setEnabled(true);
		}
		if (model.canUndo()) {
			editUndoButton.setEnabled(true);
			editUndoMenu.setEnabled(true);
		} else {
			editUndoButton.setEnabled(false);
			editUndoMenu.setEnabled(false);
		}
		if (model.canRedo()) {
			editRedoButton.setEnabled(true);
			editRedoMenu.setEnabled(true);
		} else {
			editRedoButton.setEnabled(false);
			editRedoMenu.setEnabled(false);
		}
		if (model.getGraph().getNodes().length == 0) {
			graphEmptyButton.setEnabled(false);
			graphEmptyMenu.setEnabled(false);
			graphRemoveButton.setEnabled(false);
			graphRemoveMenu.setEnabled(false);
			graphRenameButton.setEnabled(false);
			graphRenameMenu.setEnabled(false);
		} else {
			graphEmptyButton.setEnabled(true);
			graphEmptyMenu.setEnabled(true);
			graphRemoveButton.setEnabled(true);
			graphRemoveMenu.setEnabled(true);
			graphRenameButton.setEnabled(true);
			graphRenameMenu.setEnabled(false);
		}

		statNbArc.setText(Integer.toString(model.getGraph().getShape().size()));
		statNbNode.setText(Integer.toString(model.getGraph().getNodes().length));
		if (model.isSolved()) {
			steinerSeeOriginalButton.setEnabled(true);
			statTimeSolve.setText(model.getTimeToSolve() + "ms");
			statEfficiency.setText(model.getPenality());
			statNbModification.setText(model.getPoids());
		} else {
			steinerSeeOriginalButton.setEnabled(false);
			statTimeSolve.setText("[Le modèle n'est pas résolu]");
			statEfficiency.setText("[Le modèle n'est pas résolu]");
			statNbModification.setText("[Le modèle n'est pas résolu]");
		}

		filesPanel.removeAll();

		for (String e : model.getFileNames()) {
			filesPanel.add(new JLabel(e));
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					new Steiner().display();
				} catch (Throwable e) {
					try {
						errorOccured(new GraphException("C'est la fin des haricots", 
								GraphException.ErrorType.BIG_BAD_ERROR));
					} catch (Throwable e2) {
						e.printStackTrace();
					} finally {
						System.exit(GraphException.ErrorType.BIG_BAD_ERROR.ordinal());
					}
				}
			}

		});
	}
	
	public static void errorOccured(GraphException e) {
		String message = "Un probleme est survenu";
		String messageSeverity = "Erreur";
		switch (e.getError()) {
		case ADD_ELEMENT_INVALID_SYNTAX:
			message = "La syntaxe est invalide !\nVeuillez ééssayez !";
			messageSeverity = "Attention";
			break;
		case BIG_BAD_ERROR:
			message = "Ce n'etait pas censé se produire !\n Le programme va quitter !";
			messageSeverity = "!!! Erreur !!!";
			break;
		case INSERT_NODE_ALREADY_IN:
			message = "Vous essayez d'insérer un noeud qui est déja présent !\n Pensez à renommer l'existant ?";
			messageSeverity = "Attention";
			break;
		case IO_ERROR:
			message = "Une erreur d'entrée sortie s'est produite ... \nVerifiez que le fichier vous appartien bien ? ";
			messageSeverity = "Erreur";
			break;
		case REMOVE_ARC_NOT_FOUND:
			message = "L'arc que vous tentez de retirer n'est pas dans le graph ...\n Vérifiez l'orthographe ?";
			messageSeverity = "Attention";
			break;
		case REMOVE_NODE_NOT_FOUND:
			message = "Le noeud que vous tentez de retirer n'est pas dans le graph ...\n Vérifiez l'orthographe ?";
			messageSeverity = "Attention";
			break;
		case RENAME_BUT_NODE_NOT_FOUND:
			message = "Le noeud que vous tentez de renommer n'est pas dans le graph ...\n Vérifiez l'orthographe ?";
			messageSeverity = "Attention";
			break;
		case SEE_ALREADY_ORIGINAL:
			message = "Vous tentez de voir l'original ??? Vous l'avez déjà sous vos yeux !\nEnjoy ^^";
			messageSeverity = "Attention";
			break;
		case SOLVE_ARRAY_OUT_OF_BOUNDS:
			message = "Cela est notre faute ... ce n'est pas normal, mais pas bien dangeureux\n"
					+ "PS: cette erreur se produit souvent lorsque le graph est vide\nlors de la résolution";
			messageSeverity = "Attention";
		case ASSERTION_ERROR:
			message = "Cela est notre faute ... je ne pensais pas cela possible\n";
			messageSeverity = "Attention";
		default:
			break;
		}
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(messageSeverity);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

}
