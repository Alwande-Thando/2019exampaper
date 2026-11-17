package za.ac.prog6112.q1;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/** Displays petrol prices and a report of average prices for each city. */
public class PetrolPricesApp extends JFrame {
    private final String[] cities = {"Johannesburg", "Durban", "Cape Town"};
    private final String[] years = {"2017", "2018", "2019"};
    // Rows represent cities; columns represent years.
    private final double[][] prices = {
        {10.72, 10.35, 10.20},
        {12.75, 12.32, 12.22},
        {13.70, 13.31, 13.23}
    };
    private final JList<String> cityList = new JList<>(cities);
    private final JComboBox<String> yearCombo = new JComboBox<>(years);
    private final JLabel resultLabel = new JLabel("Select a city and year, then submit.");
    private final DecimalFormat money = new DecimalFormat("0.00");

    public PetrolPricesApp() {
        super("Petrol Price Lookup");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 300);
        setLocationRelativeTo(null);

        cityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cityList.setSelectedIndex(0);
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        form.add(new JLabel("Select city:"));
        form.add(new JLabel("Select year:"));
        form.add(new JScrollPane(cityList));
        form.add(yearCombo);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> submitSelection());
        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottom.add(submit, BorderLayout.WEST);
        bottom.add(resultLabel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem submit = new JMenuItem("Submit");
        submit.addActionListener(e -> submitSelection());
        JMenuItem report = new JMenuItem("Average Price Report");
        report.addActionListener(e -> showAverageReport());
        tools.add(submit);
        tools.add(report);
        bar.add(file);
        bar.add(tools);
        return bar;
    }

    private void submitSelection() {
        int cityIndex = cityList.getSelectedIndex();
        int yearIndex = yearCombo.getSelectedIndex();
        if (cityIndex < 0 || yearIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a city and a year.");
            return;
        }
        resultLabel.setText(String.format("%s petrol price in %s: R%s per litre",
                cities[cityIndex], years[yearIndex], money.format(prices[cityIndex][yearIndex])));
    }

    private void showAverageReport() {
        StringBuilder report = new StringBuilder("Average petrol price per litre\n\n");
        for (int city = 0; city < cities.length; city++) {
            double total = 0;
            for (int year = 0; year < prices[city].length; year++) {
                total += prices[city][year];
            }
            double average = total / prices[city].length;
            report.append(String.format("%s: R%s%n", cities[city], money.format(average)));
        }
        JOptionPane.showMessageDialog(this, report.toString(), "Average Price Report",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetrolPricesApp().setVisible(true));
    }
}
