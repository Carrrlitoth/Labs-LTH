/**
 ** Simple application to update cs/labs.jar to the most recent version.
 **
 ** The labs.jar file is fetched from the gitlab.com repository.
 **/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class UpdateLabCode extends JFrame {

    private static final URI DOWNLOAD_URI = URI.create("https://gitlab.com/api/v4/projects/edaf85%2Fedaf85-labs/repository/files/cs%2Flabs.jar/raw?ref=main");

    private final JLabel messageArea = new JLabel("", SwingConstants.CENTER);
    private final JPanel buttonArea = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

    // -----------------------------------------------------------------------

    public UpdateLabCode() {
        super("Update lab code (EDAF85)");

        add(messageArea, BorderLayout.CENTER);
        add(buttonArea, BorderLayout.SOUTH);

        try {
            List<Path> candidates = Files.walk(Path.of(System.getProperty("user.dir")).getParent())
                    .filter(Files::isRegularFile)
                    .filter(f -> f.endsWith(Path.of("cs", "labs.jar")))
                    .collect(Collectors.toList());
            if (candidates.size() == 1) {
                showDialog("Currently using version:    " + getJarVersion(candidates.get(0)),
                           "Check for update", () -> check(candidates.get(0)));
            } else {
                if (candidates.size() != 0) {
                    System.err.println("*** ERROR: found multiple 'cs/labs.jar', cannot decide which one to use:");
                    candidates.forEach(System.err::println);
                }
                throw new FileNotFoundException("cannot locate 'cs/labs.jar'");
            }
        } catch (IOException e) {
            fail(e);
        }

        setPreferredSize(new Dimension(600, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void showDialog(String message, String buttonLabel, Runnable onClick) {
        SwingUtilities.invokeLater(() -> {
            messageArea.setText(message);
            buttonArea.removeAll();
            buttonArea.repaint();
            JButton b = new JButton(buttonLabel);
            b.addActionListener(e -> {
                b.setEnabled(false);
                onClick.run();
            });
            buttonArea.add(b);
            getRootPane().setDefaultButton(b);
        });
    }

    private void fail(Throwable t) {
        t.printStackTrace(System.err);
        showDialog("Error: " + t.getClass().getSimpleName() + " (see console)",
                   "Quit", () -> System.exit(1));
    }

    private void check(Path labsJar) {
        try {
            messageArea.setText("Checking...");

            Path tempDir = labsJar.getParent().resolve("archive");
            Files.createDirectories(tempDir);

            Path downloadJar = Files.createTempFile(tempDir, ".download-", ".jar");
            downloadJar.toFile().deleteOnExit();

            HttpRequest req = HttpRequest.newBuilder(DOWNLOAD_URI).build();
            HttpClient.newHttpClient()
                    .sendAsync(req, BodyHandlers.ofFile(downloadJar))
                    .thenAccept(response -> {
                        try {
                            int status = response.statusCode();
                            if (status != HttpURLConnection.HTTP_OK) {
                                showDialog("Unexpected status code: " + status,
                                           "Quit", () -> System.exit(1));
                            } else if (Arrays.equals(Files.readAllBytes(labsJar), Files.readAllBytes(downloadJar))) { // same content
                                showDialog("No update available: you are using the latest version    (" + getJarVersion(labsJar) + ")",
                                           "OK", () -> System.exit(0));
                            } else {
                                showDialog("Update available:    " + getJarVersion(downloadJar),
                                           "Update now", () -> updateLabsJar(labsJar, downloadJar, tempDir));
                            }
                        } catch (IOException e) {
                            fail(e);
                        }
                    });
        } catch (IOException e) {
            fail(e);
        }
    }

    private void updateLabsJar(Path labsJar, Path downloadJar, Path tempDir) {
        try {
            Path tempFile = Files.createTempFile(tempDir, "labs_jar_", ".old");
            Files.move(labsJar, tempFile, StandardCopyOption.REPLACE_EXISTING);
            Files.move(downloadJar, labsJar, StandardCopyOption.REPLACE_EXISTING);
            showDialog("Success: you are now using the latest version!",
                       "OK", () -> System.exit(0));
        } catch (IOException e) {
            fail(e);
        }
    }

    private static String getJarVersion(Path jar) {
        try (JarFile f = new JarFile(jar.toFile())) {
            Attributes a = f.getManifest().getAttributes("common");
            return (a != null) ? a.getValue(Attributes.Name.IMPLEMENTATION_VERSION) : "unknown";
        } catch (IOException e) {
            return "unknown";
        }
    }

    // -----------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeLater(UpdateLabCode::new);
    }
}