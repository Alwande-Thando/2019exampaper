package za.ac.prog6112.q3;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/** Loads the vehicle records and displays the selected vehicle's details. */
public class SportVehiclesApp extends JFrame {
    private final List<SportVehicle> vehicles = new ArrayList<>();
    private final JList<String> detailList = new JList<>();

    public SportVehiclesApp() {
        super("Sport Vehicle Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 330);
        setLocationRelativeTo(null);

        loadVehicles();
        JPanel choices = new JPanel(new GridLayout(1, 3, 8, 8));
        ButtonGroup group = new ButtonGroup();

        // Each radio button selects one vehicle record loaded from the text file.
        for (int i = 0; i < vehicles.size(); i++) {
            final int selectedIndex = i;
            JRadioButton radio = new JRadioButton(vehicles.get(i).getName());
            group.add(radio);
            choices.add(radio);
            radio.addActionListener(e -> displayVehicle(selectedIndex));
            if (i == 0) radio.setSelected(true);
        }

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            int selected = getSelectedIndex(group, choices);
            if (selected >= 0) displayVehicle(selected);
        });

        JPanel bottom = new JPanel();
        bottom.add(submit);
        setLayout(new BorderLayout(10, 10));
        add(choices, BorderLayout.NORTH);
        add(new JScrollPane(detailList), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        if (!vehicles.isEmpty()) displayVehicle(0);
        else JOptionPane.showMessageDialog(this,
                "No vehicle records were loaded. Check cars.txt.", "File error",
                JOptionPane.ERROR_MESSAGE);
    }

    private int getSelectedIndex(ButtonGroup group, JPanel choices) {
        Component[] components = choices.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JRadioButton radio && radio.isSelected()) return i;
        }
        return -1;
    }

    private void loadVehicles() {
        // Try the project-root file first, then the bundled resource file.
        Path file = Paths.get("cars.txt");
        try (BufferedReader reader = Files.exists(file)
                ? Files.newBufferedReader(file)
                : new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/cars.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] fields = line.split("\\|");
                if (fields.length != 4) continue;
                vehicles.add(new SportVehicle(fields[0].trim(),
                        Double.parseDouble(fields[1].trim()),
                        Double.parseDouble(fields[2].trim()), fields[3].trim()));
            }
        } catch (IOException | NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Could not read cars.txt: " + ex.getMessage());
        }
    }

    private void displayVehicle(int index) {
        if (index >= 0 && index < vehicles.size()) {
            detailList.setListData(vehicles.get(index).toDisplayLines());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SportVehiclesApp().setVisible(true));
    }
}
