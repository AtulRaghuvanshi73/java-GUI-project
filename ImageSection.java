import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageSection extends JFrame {

    public ImageSection() {
        // Set the title of the window
        setTitle("Manga DB");

        // Set the default operation when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create six ImageIcons with images
        ImageIcon image1 = new ImageIcon("/home/ronin/Pictures/image1.jpg"); // Replace with actual path
        ImageIcon image2 = new ImageIcon("/home/ronin/Pictures/image2.jpg"); // Replace with actual path
        ImageIcon image3 = new ImageIcon("/home/ronin/Pictures/image4.jpg"); // Replace with actual path
        ImageIcon image4 = new ImageIcon("/home/ronin/Pictures/image1.jpg"); // Replace with actual path
        ImageIcon image5 = new ImageIcon("/home/ronin/Pictures/image2.jpg"); // Replace with actual path
        ImageIcon image6 = new ImageIcon("/home/ronin/Pictures/image4.jpg"); // Replace with actual path

        // Create JLabels with ImageIcons and set their size
        JLabel label1 = createImageLabel(image1, "Product 1 - Description goes here", "John Doe", "10", true, "Product One");
        JLabel label2 = createImageLabel(image2, "Product 2 - Description goes here", "Jane Smith", "15", false, "Product Two");
        JLabel label3 = createImageLabel(image3, "Product 3 - Description goes here", "Sam Johnson", "8", true, "Product Three");
        JLabel label4 = createImageLabel(image4, "Product 4 - Description goes here", "Alex White", "12", false, "Product Four");
        JLabel label5 = createImageLabel(image5, "Product 5 - Description goes here", "Emily Brown", "20", true, "Product Five");
        JLabel label6 = createImageLabel(image6, "Product 6 - Description goes here", "Michael Black", "5", false, "Product Six");

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));

        panel.add(createShoppingWindowPanel(label1));
        panel.add(createShoppingWindowPanel(label2));
        panel.add(createShoppingWindowPanel(label3));
        panel.add(createShoppingWindowPanel(label4));
        panel.add(createShoppingWindowPanel(label5));
        panel.add(createShoppingWindowPanel(label6));

        // Add the panel to the content pane of the frame
        getContentPane().add(panel);

        // Pack the components and set the location of the window
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen

        // Make the window visible
        setVisible(true);
    }

    private JLabel createImageLabel(ImageIcon imageIcon, String description, String creator, String numOfChapters, boolean ongoing, String productName) {
        JLabel label = new JLabel(imageIcon);
        label.setPreferredSize(new Dimension(150, 150)); // Adjust the size as needed
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a JLabel for the name section
        JLabel nameLabel = new JLabel("<html><b>Name:</b> " + productName + "</html>");
        nameLabel.setVerticalAlignment(SwingConstants.TOP);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Create a JLabel for the description (About section)
        JLabel descriptionLabel = new JLabel("<html>" + description + "</html>");
        descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setForeground(Color.GRAY);

        // Add a mouse listener to make the image label clickable
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event by showing a dialog with creator, chapters, and ongoing options
                showDetailsDialog(productName, creator, numOfChapters, ongoing);
            }
        });

        // Create a JPanel to hold the image and additional information labels
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(label, BorderLayout.CENTER);
        imagePanel.add(nameLabel, BorderLayout.NORTH);
        imagePanel.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.CENTER); // Add space
        imagePanel.add(descriptionLabel, BorderLayout.SOUTH);

        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        imagePanel.setBackground(Color.WHITE);

        return label;
    }

    private JPanel createShoppingWindowPanel(JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void showDetailsDialog(String productName, String creator, String numOfChapters, boolean ongoing) {
        // Create input fields for the dialog
        JTextField creatorField = new JTextField(creator);
        JTextField chaptersField = new JTextField(numOfChapters);
        JCheckBox ongoingCheckBox = new JCheckBox("Ongoing", ongoing);

        // Create an array of components to display in the JOptionPane
        Object[] message = {
                "<html><b>Name:</b> " + productName + "</html>",
                "Creator:", creatorField,
                "Number of Chapters:", chaptersField,
                "Ongoing:", ongoingCheckBox
        };

        // Show the JOptionPane with input fields
        int option = JOptionPane.showConfirmDialog(this, message, "Image Details", JOptionPane.OK_CANCEL_OPTION);

        // Handle the OK button click
        if (option == JOptionPane.OK_OPTION) {
            // Retrieve the updated values from the input fields
            String updatedCreator = creatorField.getText();
            String updatedChapters = chaptersField.getText();
            boolean updatedOngoing = ongoingCheckBox.isSelected();

            // Update the data as needed (you can perform any action with the updated values)
            System.out.println("Updated Creator: " + updatedCreator);
            System.out.println("Updated Number of Chapters: " + updatedChapters);
            System.out.println("Updated Ongoing: " + updatedOngoing);
        }
    }

    public static void main(String[] args) {
        // Create and run the Swing program on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new ImageSection());
    }
}
