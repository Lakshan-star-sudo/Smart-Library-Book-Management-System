package library.ui;

import library.datastructure.BookBST;
import library.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TreeAnalysisDialog extends JDialog {

    private final BookBST tree;

    public TreeAnalysisDialog(
            JFrame parent,
            BookBST tree
    ) {

        super(parent, "BST Analysis", true);

        this.tree = tree;

        setSize(720, 560);
        setLocationRelativeTo(parent);

        createUI();
    }


    private void createUI() {

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        // TITLE

        JLabel titleLabel =
                new JLabel(
                        "Binary Search Tree Analysis"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                new Color(
                        31,
                        41,
                        55
                )
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        mainPanel.add(titleLabel);


        JLabel description =
                new JLabel(
                        "Technical view of the book index structure"
                );

        description.setForeground(
                new Color(
                        107,
                        114,
                        128
                )
        );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        mainPanel.add(description);

        mainPanel.add(
                Box.createVerticalStrut(20)
        );


        // TREE INFORMATION

        JPanel informationPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        informationPanel.setOpaque(false);

        informationPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );

        Book minBook =
                tree.findMin();

        Book maxBook =
                tree.findMax();

        String min =
                minBook == null
                        ? "-"
                        : String.valueOf(
                        minBook.getBookId()
                );

        String max =
                maxBook == null
                        ? "-"
                        : String.valueOf(
                        maxBook.getBookId()
                );

        informationPanel.add(
                createInfoCard(
                        "Minimum Key",
                        min
                )
        );

        informationPanel.add(
                createInfoCard(
                        "Maximum Key",
                        max
                )
        );

        informationPanel.add(
                createInfoCard(
                        "Tree Height",
                        tree.isEmpty()
                                ? "-"
                                : String.valueOf(
                                tree.getHeight()
                        )
                )
        );

        mainPanel.add(informationPanel);

        mainPanel.add(
                Box.createVerticalStrut(22)
        );


        // TRAVERSALS

        mainPanel.add(
                createTraversalPanel(
                        "Inorder Traversal",
                        "Left → Root → Right",
                        tree.inorderTraversal(),
                        "Produces books sorted by Book ID."
                )
        );

        mainPanel.add(
                Box.createVerticalStrut(12)
        );

        mainPanel.add(
                createTraversalPanel(
                        "Preorder Traversal",
                        "Root → Left → Right",
                        tree.preorderTraversal(),
                        "Visits each subtree root before its children."
                )
        );

        mainPanel.add(
                Box.createVerticalStrut(12)
        );

        mainPanel.add(
                createTraversalPanel(
                        "Postorder Traversal",
                        "Left → Right → Root",
                        tree.postorderTraversal(),
                        "Processes child nodes before their parent."
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(mainPanel);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(15);

        add(scrollPane);
    }


    private JPanel createTraversalPanel(
            String title,
            String rule,
            List<Book> books,
            String explanation
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                14,
                                16,
                                14,
                                16
                        )
                )
        );

        panel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        125
                )
        );


        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );


        JLabel ruleLabel =
                new JLabel(rule);

        ruleLabel.setForeground(
                new Color(
                        37,
                        99,
                        235
                )
        );

        ruleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        JLabel traversalResult =
                new JLabel(
                        buildTraversalString(
                                books
                        )
                );

        traversalResult.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        JLabel explanationLabel =
                new JLabel(explanation);

        explanationLabel.setForeground(
                new Color(
                        107,
                        114,
                        128
                )
        );

        explanationLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        panel.add(heading);
        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(ruleLabel);
        panel.add(
                Box.createVerticalStrut(7)
        );

        panel.add(traversalResult);
        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(explanationLabel);

        return panel;
    }


    private String buildTraversalString(
            List<Book> books
    ) {

        if (books.isEmpty()) {
            return "BST is empty";
        }

        StringBuilder result =
                new StringBuilder();

        for (int i = 0;
             i < books.size();
             i++) {

            result.append(
                    books.get(i)
                            .getBookId()
            );

            if (i < books.size() - 1) {
                result.append("  →  ");
            }
        }

        return result.toString();
    }


    private JPanel createInfoCard(
            String title,
            String value
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                new Color(
                        107,
                        114,
                        128
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(valueLabel);

        return panel;
    }
}