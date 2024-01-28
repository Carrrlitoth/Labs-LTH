package fractal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class FractalView {
    private Fractal actFractal;

    public FractalView(Fractal[] fractals, String title, int width, int height) {
        SwingUtilities.invokeLater(() -> createWindow(fractals, title, width, height));
    }

    /**
     * Private helper method, to confine all Swing-related work to
     * Swing's Event Dispatch Thread (EDT).
     */
    private void createWindow(Fractal[] fractals, String title, int width, int height) {
        actFractal = fractals[0];

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel drawingPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Fractal fractal = actFractal;

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                fractal.draw(new TurtleGraphics(g2d));
            }
        };
        drawingPanel.setPreferredSize(new Dimension(width, height));
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        JLabel orderLabel = new JLabel("---") {
            @Override
            public void paintComponent(Graphics g) {
                setText("ordning " + actFractal.getOrder());
                super.paintComponent(g);
            }
        };

        JButton upButton = new JButton("\u25B2");
        upButton.setFocusPainted(false);
        upButton.addActionListener(e -> {
            actFractal.setOrder(actFractal.getOrder() + 1);
            drawingPanel.repaint();
            orderLabel.repaint();
        });

        JButton downButton = new JButton("\u25BC");
        downButton.setFocusPainted(false);
        downButton.addActionListener(e -> {
            if (actFractal.getOrder() > 0) {
                actFractal.setOrder(actFractal.getOrder() - 1);
                drawingPanel.repaint();
                orderLabel.repaint();
            }
        });

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        commandPanel.add(upButton);
        commandPanel.add(downButton);

        JComboBox<Fractal> comboBox = new JComboBox<>(fractals);
        comboBox.addActionListener(e -> {
            actFractal = (Fractal) comboBox.getSelectedItem();
            actFractal.setOrder(0);
            drawingPanel.repaint();
            orderLabel.repaint();
        });
        commandPanel.add(comboBox);
        commandPanel.add(orderLabel);

        frame.add(commandPanel, BorderLayout.SOUTH);
        frame.add(drawingPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
