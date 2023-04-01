package com.example;

import com.example.controleurs.ControleurDirectrice.ControleurDirectrice;
import com.example.controleurs.ControleurImmeuble.ControleurImmeuble;
import com.example.controleurs.ControleurPortes.ControleurPorte;
import com.example.controleurs.ControleurPreposes.ControleurPrepose;
import com.example.controleurs.ControleurServices.ControleurService;
import com.example.controleurs.controleurLocataire.ControleurLocataire;
import com.example.model.modelDirectrice.DaoDirectrice;
import com.example.model.modelDirectrice.Directrice;
import com.example.model.modelImmeuble.Immeuble;
import com.example.model.modelLocataire.Locataire;
import com.example.model.modelPorte.Portes;
import com.example.model.modelPrepose.Prepose;
import com.example.model.modelServices.Service;
import com.example.view.GestionHorraire;
import com.example.view.GestionServices;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class App {
	
    private App() {}

	public static void login() {

		JLabel usernameLabel = new JLabel("Nom d'utilisateur");
		usernameLabel.setBounds(500, 300, 100, 25);
		JTextField usernameInputText = new JTextField();
		usernameInputText.setBounds(620, 300, 100, 25);
		JLabel passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setBounds(500, 350, 100, 25);
		JPasswordField passwordInputText = new JPasswordField();
		passwordInputText.setBounds(620, 350, 100, 25);
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(500, 400, 100, 25);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setBounds(0, 0, 1200, 800);
		loginFormPanel.setLayout(null);
		JFrame loginFrame = new JFrame("Authentification");
		loginFrame.setSize(1200, 800);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLayout(new BorderLayout());
		loginFrame.setVisible(true);

		loginFrame.add(loginFormPanel, BorderLayout.CENTER);
	
		loginFormPanel.add(usernameLabel);
		loginFormPanel.add(usernameInputText);
		loginFormPanel.add(passwordLabel);
		loginFormPanel.add(passwordInputText);
		loginFormPanel.add(loginButton);

		loginButton.addActionListener(e -> {

			String username = usernameInputText.getText();
		
			String password = String.valueOf(passwordInputText.getPassword());

			DaoDirectrice daoDirectrice = DaoDirectrice.getDirectriceDao();
			Directrice directriceLog = daoDirectrice.MdlD_GetAll().get(0);
			
			if(username.equals(directriceLog.getUserName()) && password.equals(directriceLog.getPwUtil())) {
				loginFrame.dispose();
				password = "";
				assemble();
			} else {
				JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect");
			}
		});
    }

    public static void assemble() {

		JFrame frame = new JFrame();
		frame.setSize(1200, 900);
		ImageIcon toggleImg = new ImageIcon("./src/main/java/com/example/view/menu.png");
		JButton toggleButton = new JButton(toggleImg);
		toggleButton.setBackground(null);
		toggleButton.setBorder(null);
		toggleButton.setFocusPainted(false);
		toggleButton.setContentAreaFilled(false);
		toggleButton.setBounds(15, 30, 60, 60);
		ImageIcon homeImg = new ImageIcon("./src/main/java/com/example/view/home.png");
		JButton homeButton = new JButton(homeImg);
		homeButton.setBackground(null);
		homeButton.setBorder(null);
		homeButton.setFocusPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBounds(15, 130, 60, 60);
		ImageIcon buildingImg = new ImageIcon("./src/main/java/com/example/view/apartment.png");
		JButton buildingButton = new JButton(buildingImg);
		buildingButton.setBackground(null);
		buildingButton.setBorder(null);
		buildingButton.setFocusPainted(false);
		buildingButton.setContentAreaFilled(false);
		buildingButton.setBounds(15, 210, 60, 60);
		ImageIcon doorImg = new ImageIcon("./src/main/java/com/example/view/door.png");
		JButton doorButton = new JButton(doorImg);
		doorButton.setBackground(null);
		doorButton.setBorder(null);
		doorButton.setFocusPainted(false);
		doorButton.setContentAreaFilled(false);
		doorButton.setBounds(15, 290, 60, 60);
		ImageIcon serviceImg = new ImageIcon("./src/main/java/com/example/view/amenities.png");
		JButton serviceButton = new JButton(serviceImg);
		serviceButton.setBackground(null);
		serviceButton.setBorder(null);
		serviceButton.setFocusPainted(false);
		serviceButton.setContentAreaFilled(false);
		serviceButton.setBounds(15, 370, 60, 60);
		ImageIcon tenantImg = new ImageIcon("./src/main/java/com/example/view/wheelchair.png");
		JButton tenantButton = new JButton(tenantImg);
		tenantButton.setBackground(null);
		tenantButton.setBorder(null);
		tenantButton.setFocusPainted(false);
		tenantButton.setContentAreaFilled(false);
		tenantButton.setBounds(15, 450, 60, 60);
		ImageIcon preposeImg = new ImageIcon("./src/main/java/com/example/view/heart.png");
		JButton preposeButton = new JButton(preposeImg);
		preposeButton.setBackground(null);
		preposeButton.setBorder(null);
		preposeButton.setFocusPainted(false);
		preposeButton.setContentAreaFilled(false);
		preposeButton.setBounds(15, 530, 60, 60);

		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(90, 900));
		menuPanel.setBackground(new Color(0, 25, 51));
		menuPanel.setLayout(null);
		menuPanel.add(toggleButton);
		menuPanel.add(homeButton);
		menuPanel.add(buildingButton);
		menuPanel.add(doorButton);
		menuPanel.add(serviceButton);
		menuPanel.add(tenantButton);
		menuPanel.add(preposeButton);

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1110, 900));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();

		layeredPane.setLayout(new BorderLayout());
		layeredPane.add(menuPanel, BorderLayout.WEST);
		layeredPane.add(mainPanel, BorderLayout.CENTER);
		frame.setLayout(new BorderLayout());

		frame.add(layeredPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        toggleButton.addActionListener(a -> {

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenHeight = (int) screenSize.getHeight();

            if (menuPanel.getWidth() == 90) {
                menuPanel.setSize(new Dimension(250,screenHeight));

				JLabel acceuiLabel = new JLabel("Accueil");
				acceuiLabel.setBounds(100, 150, 200, 25);
				acceuiLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				acceuiLabel.setForeground(Color.WHITE);
				JLabel batimentLabel = new JLabel("Batiments");
				batimentLabel.setBounds(100, 230, 200, 25);
				batimentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				batimentLabel.setForeground(Color.WHITE);
				JLabel porteLabel = new JLabel("Portes");
				porteLabel.setBounds(100, 310, 200, 25);
				porteLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				porteLabel.setForeground(Color.WHITE);
				JLabel serviceLabel = new JLabel("Services");
				serviceLabel.setBounds(100, 390, 200, 25);
				serviceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				serviceLabel.setForeground(Color.WHITE);
				JLabel locataireLabel = new JLabel("Locataires");
				locataireLabel.setBounds(100, 470, 200, 25);
				locataireLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				locataireLabel.setForeground(Color.WHITE);
				JLabel preposeLabel = new JLabel("Préposés");
				preposeLabel.setBounds(100, 550, 200, 25);
				preposeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
				preposeLabel.setForeground(Color.WHITE);

				menuPanel.add(acceuiLabel);
				menuPanel.add(batimentLabel);
				menuPanel.add(porteLabel);
				menuPanel.add(serviceLabel);
				menuPanel.add(locataireLabel);
				menuPanel.add(preposeLabel);
            } else {
                menuPanel.setSize(new Dimension(90, screenHeight));
            }
        });

		frame.setVisible(true);

		homeButton.addActionListener(e -> {
			mainPanel.removeAll();

			GestionHorraire gestionHorraire = new GestionHorraire();
			GestionServices gestionServices = new GestionServices();

			JLabel homeLabel = new JLabel("Bienvenue");
			homeLabel.setBounds(500, 25, 200, 25);
			mainPanel.add(homeLabel);

			mainPanel.repaint();
			mainPanel.revalidate();
		});

		buildingButton.addActionListener(e -> {
			mainPanel.removeAll();

			ControleurImmeuble CtrI = ControleurImmeuble.getControleurImmeuble();

			JLabel buildingLabel = new JLabel("Liste des Immeubles");
			buildingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			buildingLabel.setBounds(450, 25, 200, 25);
			mainPanel.add(buildingLabel);

			JTable buildingTable = new JTable();
			buildingTable.setBounds(100, 100, 900, 500);

			String[] columnNames = {"Id", "Nom", "Adresse", "Ville", "Province", "Code Postal"};
			
			Object[][] data = new Object[CtrI.CtrI_GetAllImmeubles().size()][6];

			for (int i = 0; i < CtrI.CtrI_GetAllImmeubles().size(); i++) {
				data[i][0] = CtrI.CtrI_GetAllImmeubles().get(i).getIdImeuble();
				data[i][1] = CtrI.CtrI_GetAllImmeubles().get(i).getNomImmeuble();
				data[i][2] = CtrI.CtrI_GetAllImmeubles().get(i).getAddresse();
				data[i][3] = CtrI.CtrI_GetAllImmeubles().get(i).getVille();
				data[i][4] = CtrI.CtrI_GetAllImmeubles().get(i).getProvince();
				data[i][5] = CtrI.CtrI_GetAllImmeubles().get(i).getCodePostal();
			}
			
			DefaultTableModel model = new DefaultTableModel(data, columnNames){
				public boolean isCellEditable(int row, int column) {
					return column != 0;
				}
			};
	
			buildingTable.setModel(model);
			JScrollPane scrollPane = new JScrollPane(buildingTable);
		
			scrollPane.setBounds(100, 100, 900, 500);
			mainPanel.add(scrollPane);

			JButton buildingDeleteButton = new JButton("Supprimer");
			buildingDeleteButton.setBounds(100, 70, 100, 25);
			mainPanel.add(buildingDeleteButton);

			JButton buildingEditButton = new JButton("Modifier");
			buildingEditButton.setBounds(210, 70, 100, 25);
			mainPanel.add(buildingEditButton);

			JButton buildingAddButton = new JButton("Ajouter");
			buildingAddButton.setBounds(100, 800, 100, 25);
			mainPanel.add(buildingAddButton);

			JLabel buildingNameLabel = new JLabel("Nom :");
			JTextField buildingNameTextField = new JTextField();
			JLabel buildingAddressLabel = new JLabel("Adresse :");
			JTextField buildingAddressTextField = new JTextField();
			JLabel buildingCityLabel = new JLabel("Ville :");
			JTextField buildingCityTextField = new JTextField();
			JLabel buildingProvinceLabel = new JLabel("Province :");
			JTextField buildingProvinceTextField = new JTextField();
			JLabel buildingPostalCodeLabel = new JLabel("Code Postal :");
			JTextField buildingPostalCodeTextField = new JTextField();

			buildingNameLabel.setBounds(100, 650, 100, 25);
			buildingNameTextField.setBounds(250, 650, 100, 25);
			buildingAddressLabel.setBounds(100, 680, 100, 25);
			buildingAddressTextField.setBounds(250, 680, 100, 25);
			buildingCityLabel.setBounds(100, 710, 100, 25);
			buildingCityTextField.setBounds(250, 710, 100, 25);
			buildingProvinceLabel.setBounds(100, 740, 100, 25);
			buildingProvinceTextField.setBounds(250, 740, 100, 25);
			buildingPostalCodeLabel.setBounds(100, 770, 100, 25);
			buildingPostalCodeTextField.setBounds(250, 770, 100, 25);

			mainPanel.add(buildingNameLabel);
			mainPanel.add(buildingNameTextField);
			mainPanel.add(buildingAddressLabel);
			mainPanel.add(buildingAddressTextField);
			mainPanel.add(buildingCityLabel);
			mainPanel.add(buildingCityTextField);
			mainPanel.add(buildingProvinceLabel);
			mainPanel.add(buildingProvinceTextField);
			mainPanel.add(buildingPostalCodeLabel);
			mainPanel.add(buildingPostalCodeTextField);

			buildingAddButton.addActionListener(e1 ->{

				Immeuble immeuble = new Immeuble();

				immeuble.setNomImmeuble(buildingNameTextField.getText());
				immeuble.setAddresse(buildingAddressTextField.getText());
				immeuble.setVille(buildingCityTextField.getText());
				immeuble.setProvince(buildingProvinceTextField.getText());
				immeuble.setCodePostal(buildingPostalCodeTextField.getText());

				CtrI.CtrI_Enregistrer(immeuble);
				mainPanel.removeAll();
				buildingButton.doClick();
			});

			buildingEditButton.addActionListener(e2 ->{	
				
				HashMap<String, Object> buldingHashMap = new HashMap<>();

				int row = buildingTable.getSelectedRow();
				row = buildingTable.convertRowIndexToModel(row);
				int id = (int) buildingTable.getModel().getValueAt(row, 0);

				String name = (String) buildingTable.getModel().getValueAt(row, 1);
				String address = (String) buildingTable.getModel().getValueAt(row, 2);
				String city = (String) buildingTable.getModel().getValueAt(row, 3);
				String province = (String) buildingTable.getModel().getValueAt(row, 4);
				String postalCode = (String) buildingTable.getModel().getValueAt(row, 5);

				buldingHashMap.put("ImmeubleNom", name);
				buldingHashMap.put("Adresse", address);
				buldingHashMap.put("Ville",city);
				buldingHashMap.put("Province", province);
				buldingHashMap.put("Code Postal", postalCode);

				CtrI.CtrI_Modifier(id, buldingHashMap);

				mainPanel.removeAll();
				buildingButton.doClick();	
			});

			buildingDeleteButton.addActionListener(e3 ->{
				
				int row = buildingTable.getSelectedRow();
				row = buildingTable.convertRowIndexToModel(row);
				int id = (int) buildingTable.getModel().getValueAt(row, 0);

				CtrI.CtrI_Supprimer(id);

				model.removeRow(row);

			});
		
			mainPanel.revalidate();
			mainPanel.repaint();
		});

		doorButton.addActionListener(f -> {

			mainPanel.removeAll();

			ControleurPorte CtrP = ControleurPorte.getControleurPorte();

			JLabel doorLabel = new JLabel("Liste des portes");
			doorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			doorLabel.setBounds(475, 25, 200, 25);
			mainPanel.add(doorLabel);

			JTable doorTable = new JTable();
			doorTable.setBounds(100, 100, 900, 500);

			String columnNames[] = { "Id", "Numéro de porte", "Étage", "Vacant", "Immeuble" };

			Object[][] data = new Object[CtrP.CtrPo_GetAllPortes().size()][5];

			for (int i = 0; i < CtrP.CtrPo_GetAllPortes().size(); i++) {
				data[i][0] = CtrP.CtrPo_GetAllPortes().get(i).getIdPorte();
				data[i][1] = CtrP.CtrPo_GetAllPortes().get(i).getNumPorte();
				data[i][2] = CtrP.CtrPo_GetAllPortes().get(i).getEtage();
				data[i][3] = CtrP.CtrPo_GetAllPortes().get(i).isVacant();
				data[i][4] = CtrP.CtrPo_GetAllPortes().get(i).getIdImmeuble();
			}

			DefaultTableModel model = new DefaultTableModel(data, columnNames){
				public boolean isCellEditable(int row, int column) {
					return column == 3;
				}
			};

			doorTable.setModel(model);
			JScrollPane scrollPane = new JScrollPane(doorTable);
			scrollPane.setBounds(100, 100, 900, 500);
			mainPanel.add(scrollPane);

			JButton doorDeleteButton = new JButton("Supprimer");
			doorDeleteButton.setBounds(100, 70, 100, 25);
			mainPanel.add(doorDeleteButton);

			JButton doorEditButton = new JButton("Modifier");
			doorEditButton.setBounds(210, 70, 100, 25);
			mainPanel.add(doorEditButton);

			JButton doorAddButton = new JButton("Ajouter");
			doorAddButton.setBounds(100, 770, 100, 25);
			mainPanel.add(doorAddButton);

			JLabel doorNumberLabel = new JLabel("Numéro de porte :");
			JTextField doorNumberTextField = new JTextField();
			JLabel doorFloorLabel = new JLabel("Étage :");
			JTextField doorFloorTextField = new JTextField();
			JLabel doorVacantLabel = new JLabel("Vacant :");
			JTextField doorVacantTextField = new JTextField();
			JLabel doorBuildingLabel = new JLabel("Immeuble :");
			JTextField doorBuildingTextField = new JTextField();

			doorNumberLabel.setBounds(100, 650, 150, 25);
			doorNumberTextField.setBounds(250, 650, 100, 25);
			doorFloorLabel.setBounds(100, 680, 100, 25);
			doorFloorTextField.setBounds(250, 680, 100, 25);
			doorVacantLabel.setBounds(100, 710, 100, 25);
			doorVacantTextField.setBounds(250, 710, 100, 25);
			doorBuildingLabel.setBounds(100, 740, 100, 25);
			doorBuildingTextField.setBounds(250, 740, 100, 25);

			mainPanel.add(doorNumberLabel);
			mainPanel.add(doorNumberTextField);
			mainPanel.add(doorFloorLabel);
			mainPanel.add(doorFloorTextField);
			mainPanel.add(doorVacantLabel);
			mainPanel.add(doorVacantTextField);
			mainPanel.add(doorBuildingLabel);
			mainPanel.add(doorBuildingTextField);

			doorAddButton.addActionListener(f1 -> {

					Portes porte = new Portes();

					porte.setNumPorte(Integer.parseInt(doorNumberTextField.getText()));
					porte.setEtage(Integer.parseInt( doorFloorTextField.getText()));
					porte.setVacant(Boolean.parseBoolean(doorVacantTextField.getText()));
					porte.setIdImmeuble(Integer.parseInt(doorBuildingTextField.getText()));
				
					CtrP.CtrPo_Enregistrer(porte);

					mainPanel.removeAll();
					doorButton.doClick();
			});

			doorEditButton.addActionListener(f2 -> {

				HashMap<String, Object> porteMap = new HashMap<String, Object>();

				int row = doorTable.getSelectedRow();
				row = doorTable.convertRowIndexToModel(row);
				int id = (int) doorTable.getValueAt(row, 0);
				String vacant = doorTable.getValueAt(row, 3) + "";
				Boolean isVacant = Boolean.parseBoolean(vacant);		

				porteMap.put("Vacant", isVacant);

				CtrP.CtrPo_Modifier(id, porteMap);
				System.out.println(CtrP.CtrPo_Modifier(id, porteMap));

				mainPanel.removeAll();
				doorButton.doClick();
			});

			doorDeleteButton.addActionListener(f3 -> {
				int row = doorTable.getSelectedRow();
				row = doorTable.convertRowIndexToModel(row);
				int id = (int) doorTable.getModel().getValueAt(row, 0);

				CtrP.CtrPo_Supprimer(id);

				model.removeRow(row);

			});

			mainPanel.revalidate();
			mainPanel.repaint();
		});

		serviceButton.addActionListener(g -> {

			mainPanel.removeAll();

			ControleurService CtrS = ControleurService.getControleurService();

			JLabel serviceLabel = new JLabel("Liste des services");
			serviceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			serviceLabel.setBounds(475, 25, 200, 25);
			mainPanel.add(serviceLabel);

			JTable serviceTable = new JTable();

			String columnNames[] = { "Service ID", "Service Nom", "Jour", "Locataire ID", "Préposer ID", "Heure d'exécution Min", "Heure d'exécution Max", "Heure de début", "Heure de fin", "Durée", "Journalier", "Description"};

			Object[][] data = new Object[CtrS.CtrS_GetAllServices().size()][12];

			for (int i = 0; i < CtrS.CtrS_GetAllServices().size(); i++) {
				data[i][0] = CtrS.CtrS_GetAllServices().get(i).getServiceID();
				data[i][1] = CtrS.CtrS_GetAllServices().get(i).getNomService();
				data[i][2] = CtrS.CtrS_GetAllServices().get(i).getNomJour();
				data[i][3] = CtrS.CtrS_GetAllServices().get(i).getLocataireID();
				data[i][4] = CtrS.CtrS_GetAllServices().get(i).getPreposeID();
				data[i][5] = CtrS.CtrS_GetAllServices().get(i).getHeureExecutionMin();
				data[i][6] = CtrS.CtrS_GetAllServices().get(i).getHeureExecutionMax();
				data[i][7] = CtrS.CtrS_GetAllServices().get(i).getHeureDebut();
				data[i][8] = CtrS.CtrS_GetAllServices().get(i).getHeureFin();
				data[i][9] = CtrS.CtrS_GetAllServices().get(i).getDuree();
				data[i][10] = CtrS.CtrS_GetAllServices().get(i).isJournalier();
				data[i][11] = CtrS.CtrS_GetAllServices().get(i).getDescription();

			}

			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return column != 0 && column != 3;
				}
			};
			serviceTable.setModel(model);
			JScrollPane scrollPane = new JScrollPane(serviceTable);
			scrollPane.setBounds(100, 100, 1000, 500);
			mainPanel.add(scrollPane);

			JButton serviceDeleteButton = new JButton("Supprimer");
			serviceDeleteButton.setBounds(100, 70, 100, 25);
			mainPanel.add(serviceDeleteButton);

			JButton serviceEditButton = new JButton("Modifier");
			serviceEditButton.setBounds(210, 70, 100, 25);
			mainPanel.add(serviceEditButton);

			JButton serviceAddButton = new JButton("Ajouter");
			serviceAddButton.setBounds(425, 800, 100, 25);
			mainPanel.add(serviceAddButton);

			JLabel serviceNomLabel = new JLabel("Service Nom :");
			JTextField serviceNomTextField = new JTextField();
			JLabel serviceJourLabel = new JLabel("Jour :");
			JTextField serviceJourTextField = new JTextField();
			JLabel serviceLocataireLabel = new JLabel("Locataire ID :");
			JTextField serviceLocataireTextField = new JTextField();
			JLabel servicePreposeLabel = new JLabel("Prépose ID :");
			JTextField servicePreposeTextField = new JTextField();
			JLabel serviceHeureExecutionMinLabel = new JLabel("Heure d'exécution Min :");
			JTextField serviceHeureExecutionMinTextField = new JTextField();
			JLabel serviceHeureExecutionMaxLabel = new JLabel("Heure d'exécution Max :");
			JTextField serviceHeureExecutionMaxTextField = new JTextField();
			JLabel serviceHeureDebutLabel = new JLabel("Heure de début :");
			JTextField serviceHeureDebutTextField = new JTextField();
			JLabel serviceHeureFinLabel = new JLabel("Heure de fin :");
			JTextField serviceHeureFinTextField = new JTextField();
			JLabel serviceDureeLabel = new JLabel("Durée :");
			JTextField serviceDureeTextField = new JTextField();
			JLabel serviceJournalierLabel = new JLabel("Journalier :");
			JTextField serviceJournalierTextField = new JTextField("Vrai ou Faux");
			JLabel serviceDescriptionLabel = new JLabel("Description :");
			JTextField serviceDescriptionTextField = new JTextField();

			serviceNomLabel.setBounds(100, 650, 100, 25);
			serviceNomTextField.setBounds(275, 650, 100, 25);
			serviceJourLabel.setBounds(100, 680, 100, 25);
			serviceJourTextField.setBounds(275, 680, 100, 25);
			serviceLocataireLabel.setBounds(100, 710, 100, 25);
			serviceLocataireTextField.setBounds(275, 710, 100, 25);
			servicePreposeLabel.setBounds(100, 740, 250, 25);
			servicePreposeTextField.setBounds(275, 740, 100, 25);
			serviceHeureExecutionMinLabel.setBounds(100, 770, 250, 25);
			serviceHeureExecutionMinTextField.setBounds(275, 770, 100, 25);
			serviceHeureExecutionMaxLabel.setBounds(100, 800, 200, 25);
			serviceHeureExecutionMaxTextField.setBounds(275, 800, 100, 25);
			serviceHeureDebutLabel.setBounds(425, 650, 200, 25);
			serviceHeureDebutTextField.setBounds(560, 650, 100, 25);
			serviceHeureFinLabel.setBounds(425, 680, 150, 25);
			serviceHeureFinTextField.setBounds(560, 680, 100, 25);
			serviceDureeLabel.setBounds(425, 710, 150, 25);
			serviceDureeTextField.setBounds(560, 710, 100, 25);
			serviceJournalierLabel.setBounds(425, 740, 150, 25);
			serviceJournalierTextField.setBounds(560, 740, 100, 25);
			serviceDescriptionLabel.setBounds(425, 770, 150, 25);
			serviceDescriptionTextField.setBounds(560, 770, 100, 25);
	
			mainPanel.add(serviceNomLabel);
			mainPanel.add(serviceNomTextField);
			mainPanel.add(serviceJourLabel);
			mainPanel.add(serviceJourTextField);
			mainPanel.add(serviceLocataireLabel);
			mainPanel.add(serviceLocataireTextField);
			mainPanel.add(servicePreposeLabel);
			mainPanel.add(servicePreposeTextField);
			mainPanel.add(serviceHeureExecutionMinLabel);
			mainPanel.add(serviceHeureExecutionMinTextField);
			mainPanel.add(serviceHeureExecutionMaxLabel);
			mainPanel.add(serviceHeureExecutionMaxTextField);
			mainPanel.add(serviceHeureDebutLabel);
			mainPanel.add(serviceHeureDebutTextField);
			mainPanel.add(serviceHeureFinLabel);
			mainPanel.add(serviceHeureFinTextField);
			mainPanel.add(serviceDureeLabel);
			mainPanel.add(serviceDureeTextField);
			mainPanel.add(serviceJournalierLabel);
			mainPanel.add(serviceJournalierTextField);
			mainPanel.add(serviceDescriptionLabel);
			mainPanel.add(serviceDescriptionTextField);

			serviceAddButton.addActionListener(g1 -> {

				Service service = new Service();
				service.setNomService(serviceNomTextField.getText());
				service.setNomJour(serviceJourTextField.getText());
				service.setLocataireID(Integer.parseInt(serviceLocataireTextField.getText()));
				service.setPreposeID(Integer.parseInt(servicePreposeTextField.getText()));
				service.setHeureExecutionMin(serviceHeureExecutionMinTextField.getText());
				service.setHeureExecutionMax(serviceHeureExecutionMaxTextField.getText());
				service.setHeureDebut(serviceHeureDebutTextField.getText());
				service.setHeureFin(serviceHeureFinTextField.getText());
				service.setDuree(serviceDureeTextField.getText());
				service.setJournalier(Boolean.parseBoolean(serviceJournalierTextField.getText()));
				service.setDescription(serviceDescriptionTextField.getText());

				CtrS.CtrS_Enregistrer(service);

				mainPanel.removeAll();
				serviceButton.doClick();
			});

			serviceEditButton.addActionListener(g2 -> {

				HashMap<String, Object> serviceMap = new HashMap<String, Object>();

				int row = serviceTable.getSelectedRow();
				row = serviceTable.convertRowIndexToModel(row);
				int id = (int) serviceTable.getValueAt(row, 0);

				serviceMap.put("ServiceNom", ((String) serviceTable.getValueAt(row, 1)));
				serviceMap.put("NomJour", ((String) serviceTable.getValueAt(row, 2)));
				serviceMap.put("PreposerID", (Integer.parseInt(serviceTable.getValueAt(row, 4)+ "")));
				serviceMap.put("HeureExecutionMin", ((String) serviceTable.getValueAt(row, 5)));
				serviceMap.put("HeureExecutionMax", ((String) serviceTable.getValueAt(row, 6)));
				serviceMap.put("HeureDebut", ((String) serviceTable.getValueAt(row, 7)));
				serviceMap.put("HeureFin", ((String) serviceTable.getValueAt(row, 8)));
				serviceMap.put("Duree", ((String) serviceTable.getValueAt(row, 9)));
				serviceMap.put("Journalier", (Boolean.parseBoolean(serviceTable.getValueAt(row, 10)+"")));
				serviceMap.put("Description", ((String) serviceTable.getValueAt(row, 11)));

				CtrS.CtrS_Modifier(id, serviceMap);

				mainPanel.removeAll();
				serviceButton.doClick();
			});

			serviceDeleteButton.addActionListener(g3 -> {

				int row = serviceTable.getSelectedRow();
				row = serviceTable.convertRowIndexToModel(row);
				int id = (int) serviceTable.getValueAt(row, 0);

				CtrS.CtrS_Supprimer(id);

				model.removeRow(row);
			});

			mainPanel.revalidate();
			mainPanel.repaint();
		});

		tenantButton.addActionListener(h -> {

			mainPanel.removeAll();

			ControleurLocataire CtrL = ControleurLocataire.getControleurLocataire();

			JLabel tenantLabel = new JLabel("Liste des locataires");
			tenantLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			tenantLabel.setBounds(460, 25, 200, 25);
			mainPanel.add(tenantLabel);

			JTable tenantTable = new JTable();
			tenantTable.setBounds(100, 100, 900, 500);

			String columnNames[] = {"Locataire ID", "Nom", "Prénom", "Téléphone", "Porte ID", "Adresse"};

			Object[][] data = new Object[CtrL.CtrL_GetAllLocataires().size()][6];

			for (int i = 0; i < CtrL.CtrL_GetAllLocataires().size(); i++) {
				data[i][0] = CtrL.CtrL_GetAllLocataires().get(i).getId();
				data[i][1] = CtrL.CtrL_GetAllLocataires().get(i).getNom();
				data[i][2] = CtrL.CtrL_GetAllLocataires().get(i).getPrenom();
				data[i][3] = CtrL.CtrL_GetAllLocataires().get(i).getTelephone();
				data[i][4] = CtrL.CtrL_GetAllLocataires().get(i).getNumPorte();
				data[i][5] = CtrL.CtrL_GetAllLocataires().get(i).getAddresse();
			}

			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return column != 0;
				}
			};
			tenantTable.setModel(model);
			JScrollPane scrollPane = new JScrollPane(tenantTable);
			scrollPane.setBounds(100, 100, 900, 500);
			mainPanel.add(scrollPane);

			JButton tenantDeleteButton = new JButton("Supprimer");
			tenantDeleteButton.setBounds(100, 70, 100, 25);
			mainPanel.add(tenantDeleteButton);

			JButton tenantEditButton = new JButton("Modifier");
			tenantEditButton.setBounds(210, 70, 100, 25);
			mainPanel.add(tenantEditButton);

			JButton tenantAddButton = new JButton("Ajouter");
			tenantAddButton.setBounds(100, 800, 100, 25);
			mainPanel.add(tenantAddButton);

			JLabel tenantNomLabel = new JLabel("Nom :");
			JTextField tenantNomTextField = new JTextField();
			JLabel tenantPrenomLabel = new JLabel("Prénom :");
			JTextField tenantPrenomTextField = new JTextField();
			JLabel tenantTelephoneLabel = new JLabel("Téléphone :");
			JTextField tenantTelephoneTextField = new JTextField();
			JLabel tenantNumPorteLabel = new JLabel("Numéro de porte :");
			JTextField tenantNumPorteTextField = new JTextField();
			JLabel tenantAddresseLabel = new JLabel("Addresse :");
			JTextField tenantAddresseTextField = new JTextField();

			tenantNomLabel.setBounds(100, 650, 100, 25);
			tenantNomTextField.setBounds(250, 650, 100, 25);
			tenantPrenomLabel.setBounds(100, 680, 150, 25);
			tenantPrenomTextField.setBounds(250, 680, 100, 25);
			tenantTelephoneLabel.setBounds(100, 710, 200, 25);
			tenantTelephoneTextField.setBounds(250, 710, 100, 25);
			tenantNumPorteLabel.setBounds(100, 740, 200, 25);
			tenantNumPorteTextField.setBounds(250, 740, 100, 25);
			tenantAddresseLabel.setBounds(100, 770, 200, 25);
			tenantAddresseTextField.setBounds(250, 770, 100, 25);

			mainPanel.add(tenantNomLabel);
			mainPanel.add(tenantNomTextField);
			mainPanel.add(tenantPrenomLabel);
			mainPanel.add(tenantPrenomTextField);
			mainPanel.add(tenantTelephoneLabel);
			mainPanel.add(tenantTelephoneTextField);
			mainPanel.add(tenantNumPorteLabel);
			mainPanel.add(tenantNumPorteTextField);
			mainPanel.add(tenantAddresseLabel);
			mainPanel.add(tenantAddresseTextField);
			mainPanel.add(tenantAddButton);

				tenantAddButton.addActionListener(k -> {
					Locataire locataire = new Locataire();
					locataire.setNom(tenantNomTextField.getText());
					locataire.setPrenom(tenantPrenomTextField.getText());
					locataire.setTelephone(tenantTelephoneTextField.getText());
					locataire.setNumPorte(Integer.parseInt(tenantNumPorteTextField.getText()));
					locataire.setAddresse(tenantAddresseTextField.getText());

					CtrL.CtrL_Enregistrer(locataire);

					mainPanel.removeAll();
					tenantButton.doClick();

				});

				tenantEditButton.addActionListener(k2 -> {

					HashMap<String, Object> locataireHashMap = new HashMap<String, Object>();

					int row = tenantTable.getSelectedRow();
					row = tenantTable.convertRowIndexToModel(row);
					int id = (int) tenantTable.getValueAt(row, 0);

					locataireHashMap.put("LocataireNom", ((String) tenantTable.getValueAt(row, 1)));
					locataireHashMap.put("LocatairePrenom", ((String) tenantTable.getValueAt(row, 2)));
					locataireHashMap.put("Telephone", ((String) tenantTable.getValueAt(row, 3)));
					locataireHashMap.put("PorteID", ((int) tenantTable.getValueAt(row, 4)));
					locataireHashMap.put("Adresse", ((String) tenantTable.getValueAt(row, 5)));

					CtrL.CtrL_Modifier(id, locataireHashMap);

					mainPanel.removeAll();
					tenantButton.doClick();

				});

				tenantDeleteButton.addActionListener(k3 -> {
					int row = tenantTable.getSelectedRow();
					row = tenantTable.convertRowIndexToModel(row);
					int id = (int) tenantTable.getValueAt(row, 0);

					CtrL.CtrL_Supprimer(id);

					model.removeRow(row);
				});
		
			mainPanel.revalidate();
			mainPanel.repaint();
		});

		preposeButton.addActionListener(k -> { 

			mainPanel.removeAll();

			ControleurPrepose CtrP = ControleurPrepose.getControleurPrepose();

			JLabel preposeLabel = new JLabel("Liste des préposés");
			preposeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			preposeLabel.setBounds(465, 25, 200, 25);
			mainPanel.add(preposeLabel);

			JTable preposeTable = new JTable();
			preposeTable.setBounds(100, 100, 900, 500);

			String columnNames[] = {"Préposé ID", "Immeubles ID", "Téléphone", "Début Quart", "Fin Quart", "Repas", "Pause"};

			Object[][] data = new Object[CtrP.CtrPr_GetAllPreposes().size()][7];

			for (int i = 0; i < CtrP.CtrPr_GetAllPreposes().size(); i++) {
				data[i][0] = CtrP.CtrPr_GetAllPreposes().get(i).getIdPrepose();
				data[i][1] = CtrP.CtrPr_GetAllPreposes().get(i).getImmeubleID();
				data[i][2] = CtrP.CtrPr_GetAllPreposes().get(i).getNumtelephone();
				data[i][3] = CtrP.CtrPr_GetAllPreposes().get(i).getShiftDebut();
				data[i][4] = CtrP.CtrPr_GetAllPreposes().get(i).getShiftFin();
				data[i][5] = CtrP.CtrPr_GetAllPreposes().get(i).getRepas();
				data[i][6] = CtrP.CtrPr_GetAllPreposes().get(i).getPause();
			}

			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return column != 0 && column != 1;
				}
			};

			preposeTable.setModel(model);
			JScrollPane scrollPane = new JScrollPane(preposeTable);
			scrollPane.setBounds(100, 100, 900, 500);
			mainPanel.add(scrollPane);

			JButton preposeDeleteButton = new JButton("Supprimer");
			preposeDeleteButton.setBounds(100, 70, 100, 25);
			mainPanel.add(preposeDeleteButton);

			JButton preposeEditButton = new JButton("Modifier");
			preposeEditButton.setBounds(210, 70, 100, 25);
			mainPanel.add(preposeEditButton);

			JButton preposeAddButton = new JButton("Ajouter");
			preposeAddButton.setBounds(400, 800, 100, 25);
			mainPanel.add(preposeAddButton);

			JLabel preposeImmeubleLabel = new JLabel("Immeuble ID :");
			JTextField preposeImmeubleTextField = new JTextField();
			JLabel preposeTelephoneLabel = new JLabel("Téléphone :");
			JTextField preposeTelephoneTextField = new JTextField();
			JLabel preposeDebutQuartLabel = new JLabel("Début Quart :");
			JTextField preposeDebutQuartTextField = new JTextField();
			JLabel preposeFinQuartLabel = new JLabel("Fin Quart :");
			JTextField preposeFinQuartTextField = new JTextField();
			JLabel preposeRepasLabel = new JLabel("Repas :");
			JTextField preposeRepasTextField = new JTextField();
			JLabel preposePauseLabel = new JLabel("Pause :");
			JTextField preposePauseTextField = new JTextField();

			preposeImmeubleLabel.setBounds(100, 650, 100, 25);
			preposeImmeubleTextField.setBounds(250, 650, 100, 25);
			preposeTelephoneLabel.setBounds(100, 680, 100, 25);
			preposeTelephoneTextField.setBounds(250, 680, 100, 25);
			preposeDebutQuartLabel.setBounds(100, 710, 200, 25);
			preposeDebutQuartTextField.setBounds(250, 710, 100, 25);
			preposeFinQuartLabel.setBounds(100, 740, 100, 25);
			preposeFinQuartTextField.setBounds(250, 740, 100, 25);
			preposeRepasLabel.setBounds(100, 770, 200, 25);
			preposeRepasTextField.setBounds(250, 770, 100, 25);
			preposePauseLabel.setBounds(100, 800, 200, 25);
			preposePauseTextField.setBounds(250, 800, 100, 25);
	
			mainPanel.add(preposeImmeubleLabel);
			mainPanel.add(preposeImmeubleTextField);
			mainPanel.add(preposeTelephoneLabel);
			mainPanel.add(preposeTelephoneTextField);
			mainPanel.add(preposeDebutQuartLabel);
			mainPanel.add(preposeDebutQuartTextField);
			mainPanel.add(preposeFinQuartLabel);
			mainPanel.add(preposeFinQuartTextField);
			mainPanel.add(preposeRepasLabel);
			mainPanel.add(preposeRepasTextField);
			mainPanel.add(preposePauseLabel);
			mainPanel.add(preposePauseTextField);
			
			preposeAddButton.addActionListener(e -> {

				Prepose prepose = new Prepose();
				prepose.setImmeubleID(Integer.parseInt(preposeImmeubleTextField.getText()));
				prepose.setNumtelephone(preposeTelephoneTextField.getText());
				prepose.setShiftDebut(preposeDebutQuartTextField.getText());
				prepose.setShiftFin(preposeFinQuartTextField.getText());
				prepose.setRepas(preposeRepasTextField.getText());
				prepose.setPause(preposePauseTextField.getText());

				CtrP.CtrPr_Enregistrer(prepose);

				mainPanel.removeAll();
				preposeButton.doClick();
				
			});

			preposeEditButton.addActionListener(e2 -> {

				HashMap<String, Object> preposeHashMap = new HashMap<>();

				int row = preposeTable.getSelectedRow();
				row = preposeTable.convertRowIndexToModel(row);
				int id = (int) preposeTable.getValueAt(row, 0);

				preposeHashMap.put("Telephone", ((String) preposeTable.getValueAt(row, 2)));
				preposeHashMap.put("DebutQuart", ((String) preposeTable.getValueAt(row, 3)));
				preposeHashMap.put("FinQuart", ((String) preposeTable.getValueAt(row, 4)));
				preposeHashMap.put("RepasHeureDebut", ((String) preposeTable.getValueAt(row, 5)));
				preposeHashMap.put("PauseHeureDebut", ((String) preposeTable.getValueAt(row, 6)));

				CtrP.CtrPr_Modifier(id, preposeHashMap);

				mainPanel.removeAll();
				preposeButton.doClick();

			});

			preposeDeleteButton.addActionListener(e3 -> {
				
				int row = preposeTable.getSelectedRow();
				row = preposeTable.convertRowIndexToModel(row);
				int id = (int) preposeTable.getValueAt(row, 0);

				CtrP.CtrPr_Supprimer(id);

				model.removeRow(row);
			});

			mainPanel.revalidate();
			mainPanel.repaint();
		}); 	
    }

    public static void testerCrudLocataire() {
    	ControleurLocataire CtrL = ControleurLocataire.getControleurLocataire();
		  
	      ArrayList<Locataire> listeLocataires = CtrL.CtrL_GetAllLocataires();
	      for (Locataire unLoc: listeLocataires){
	    	  System.out.println(unLoc);
	      }
    }
    
    
    public static void testerCrudService() {
    	ControleurService CtrS = ControleurService.getControleurService();
		  
	    ArrayList<Service> listeServices = CtrS.CtrS_GetAllServices();
	    for (Service unSer: listeServices){
	    	  System.out.println(unSer);
	    }
    }
    
    public static void testerCrudImmeuble() {
    	ControleurImmeuble CtrI = ControleurImmeuble.getControleurImmeuble();

	      HashMap<String, Object> test = new HashMap<>();
	      test.put("ImmeubleNom", "TiPapout");
	      test.put("Adresse", "666 rue du diable");
	      test.put("Ville", "Drummondville");
	      test.put("Province", "Qc");
	      test.put("Code Postal", "J0K3E0");
	      
	      int modifier = CtrI.CtrI_Modifier(2, test);
	      System.out.println(modifier);
	      
	      ArrayList<Immeuble> listeImmeubles = CtrI.CtrI_GetAllImmeubles();
	      for (Immeuble unImmeuble: listeImmeubles){
	    	  System.out.println(unImmeuble);
	      }
    }
    
    public static void testerCrudPorte() {
    	ControleurPorte CtrPo = ControleurPorte.getControleurPorte();  

	      HashMap<String, Object> test = new HashMap<>();
	      test.put("Vacant", false);
	      
	      ArrayList<Portes> listePortes = CtrPo.CtrPo_GetAllPortes();
	      for (Portes unePorte: listePortes){
	    	  System.out.println(unePorte);
	      }
    }
    
    public static void testerCrudPrepose() {
    	ControleurPrepose CtrPr = ControleurPrepose.getControleurPrepose();
		  
	      ArrayList<Prepose> listePrepose = CtrPr.CtrPr_GetAllPreposes();
	      for (Prepose unPrepose: listePrepose){
	    	  System.out.println(unPrepose);
	      }
    }
    
    public static void testerCrudDirectrice(String username, String password) {
    	ControleurDirectrice CtrD = ControleurDirectrice.getControleurDirectrice();
		  
	      ArrayList<Directrice> listeDirectrices = CtrD.CtrD_GetAllDirectrices();
	      for (Directrice uneDirectrice: listeDirectrices){
	    	  System.out.println(uneDirectrice);
	      }
    }
    
    public static void main(String[] args) {
    	login();
    }
}
