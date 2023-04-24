import javax.swing.*;
        import java.awt.*;
import java.text.DateFormat;
import java.util.*;

public class CountryInfoGUI extends JFrame {
    private JLabel countryNameLabel, languageLabel, hashcodeLabel, dateLabel, timeLabel;
    private JComboBox<String> countryComboBox;

    public CountryInfoGUI() {
        setTitle("Country Info");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        countryNameLabel = new JLabel("Country Name:");
        languageLabel = new JLabel("Language:");
        hashcodeLabel = new JLabel("Hashcode:");
        dateLabel = new JLabel("Date:");
        timeLabel = new JLabel("Time:");

        panel.add(new JLabel()); // empty label for spacing
        panel.add(new JLabel());

        panel.add(countryNameLabel);
        panel.add(languageLabel);
        panel.add(hashcodeLabel);
        panel.add(dateLabel);
        panel.add(timeLabel);

        Locale[] locales = Locale.getAvailableLocales();
        String[] countryNames = new String[locales.length];
        for (int i = 0; i < locales.length; i++) {
            countryNames[i] = locales[i].getDisplayCountry();
        }

        Arrays.sort(countryNames);

        countryComboBox = new JComboBox<>(countryNames);
        countryComboBox.addActionListener(e -> updateLabels());

        panel.add(new JLabel("Select Country:"));
        panel.add(countryComboBox);

        add(panel);
        setVisible(true);
    }

    private void updateLabels() {
        String countryName = (String) countryComboBox.getSelectedItem();
        Locale locale = new Locale(countryName);

        countryNameLabel.setText("Country Name: " + countryName);
        languageLabel.setText("Language: " + locale.getDisplayLanguage());
        hashcodeLabel.setText("Hashcode: " + locale.hashCode());

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        Date currentDate = new Date();
        dateLabel.setText("Date: " + dateFormat.format(currentDate));

        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        timeLabel.setText("Time: " + timeFormat.format(currentDate));
    }

    public static void main(String[] args) {
        new CountryInfoGUI();
    }
}