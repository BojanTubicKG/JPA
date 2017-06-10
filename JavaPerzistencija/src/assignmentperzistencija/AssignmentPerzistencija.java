package assignmentperzistencija;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AssignmentPerzistencija {

    public static void main(String[] args) {

        JFrame f = new JFrame("Firma");
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);

        JMenuBar bar = new JMenuBar();
        JMenu jm = new JMenu("Zaposleni");
        JMenuItem jmi1 = new JMenuItem("Prikaz svih zaposlenih");
        jmi1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Zaposleni z = null;
                ArrayList<Zaposleni> lista_zaposlenih = new ArrayList();
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firma", "root", "Bt15.01.86");) {
                    Statement st = conn.createStatement();
                    st.executeQuery("select * from zaposleni");
                    ResultSet rs = st.getResultSet();
                    while (rs.next()) {
                        z = new Zaposleni(rs.getInt("zaposleni_id"), rs.getString("ime"), rs.getInt("godine"), rs.getString("adresa"), rs.getString("zarada"));
                        lista_zaposlenih.add(z);
                    }
                } catch (Exception exc) {
                    System.out.println("Nije dobra konekcija :\n" + exc);
                }
                for (Zaposleni zaposleni : lista_zaposlenih) {
                    System.out.println(zaposleni);
                }
            }
        });
        JMenuItem jmi2 = new JMenuItem("Prikaz po selekciji");
        jmi2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Izaberi id zaposlenog : ");
                f1.setLayout(new FlowLayout());
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(300, 100);
                f1.setLocationRelativeTo(null);
                JLabel l = new JLabel("Id trazenog zaposlenog");
                JTextField tf = new JTextField(5);
                JButton button = new JButton("Prikazi");
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Zaposleni z1 = null;
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firma", "root", "Bt15.01.86");) {
                            Statement st = conn.createStatement();
                            st.executeQuery("select * from zaposleni where zaposleni_id='" + Integer.valueOf(tf.getText()) + "'");
                            ResultSet rs = st.getResultSet();
                            while (rs.next()) {
                                z1 = new Zaposleni(rs.getInt("zaposleni_id"), rs.getString("ime"), rs.getInt("godine"), rs.getString("adresa"), rs.getString("zarada"));
                            }
                        } catch (Exception exc) {
                            System.out.println("Nije dobra konekcija :\n" + exc);
                        }
                        System.out.println(z1);
                    }
                });
                f1.add(l);
                f1.add(tf);
                f1.add(button);
                f1.setVisible(true);
            }
        });
        JMenuItem jmi3 = new JMenuItem("Izmena zaposlenog");
        jmi3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Izmeni zaposlenog");
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                frame.setLocationRelativeTo(null);

                JLabel la1 = new JLabel("Id zaposlenog za izmenu : ");
                JTextField t1 = new JTextField(10);
                JLabel la2 = new JLabel("Novo ime zaposlenog : ");
                JTextField t2 = new JTextField(10);
                JLabel la3 = new JLabel("Godine   zaposlenog : ");
                JTextField t3 = new JTextField(10);
                JLabel la4 = new JLabel("Adresa   zaposlenog : ");
                JTextField t4 = new JTextField(10);
                JLabel la5 = new JLabel("Zarada   zaposlenog : ");
                JTextField t5 = new JTextField(10);
                JButton bb = new JButton("Izmeni");
                bb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firma", "root", "Bt15.01.86");) {

                            Statement stt = conn.createStatement();
                            stt.execute("update zaposleni set ime='" + String.valueOf(t2.getText()) + "',godine='" + Integer.valueOf(t3.getText()) + "',adresa='" + String.valueOf(t4.getText()) + "',zarada='" + String.valueOf(t5.getText()) + "' where zaposleni_id='" + Integer.valueOf(t1.getText()) + "' ");
                            JOptionPane.showMessageDialog(null, "Uspesne izmene !!!");
                        } catch (Exception exc) {
                            System.out.println("Nije dobra konekcija :\n" + exc);
                        }
                    }
                });
                frame.add(la1);
                frame.add(t1);
                frame.add(la2);
                frame.add(t2);
                frame.add(la3);
                frame.add(t3);
                frame.add(la4);
                frame.add(t4);
                frame.add(la5);
                frame.add(t5);
                frame.add(bb);
                frame.setVisible(true);
            }
        });
        JMenuItem jmi4 = new JMenuItem("Brisanje zaposlenog");
        jmi4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Izaberi id zaposlenog za brisanje");
                f1.setLayout(new FlowLayout());
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(400, 100);
                f1.setLocationRelativeTo(null);
                f1.setBackground(Color.darkGray);
                JLabel l = new JLabel("Id zaposlenog za brisanje : ");
                JTextField tf1 = new JTextField(5);
                JButton button1 = new JButton("Izbrisi");
                button1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firma", "root", "Bt15.01.86");) {
                            Statement st = conn.createStatement();
                            st.execute("delete from zaposleni where zaposleni_id='" + Integer.valueOf(tf1.getText()) + "'");
                            JOptionPane.showMessageDialog(null, "Brisnaje uspesno !!!");
                        } catch (Exception exc) {
                            System.out.println("Nije dobra konekcija :\n" + exc);
                        }
                    }
                });

                f1.add(l);
                f1.add(tf1);
                f1.add(button1);
                f1.setVisible(true);
            }
        });
        JMenuItem jmi5 = new JMenuItem("Dodavanje zaposlenog");
        jmi5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f2 = new JFrame("Dodavanje zaposlenog");
                f2.setSize(300, 300);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f2.setLayout(new FlowLayout());
                f2.setLocationRelativeTo(null);
                f2.setBackground(Color.ORANGE);

                JLabel l1 = new JLabel("Ime novog zaposlenog :   ");
                JTextField jtf1 = new JTextField(10);
                JLabel l2 = new JLabel("Godine novog zaposlenog : ");
                JTextField jtf2 = new JTextField(10);
                JLabel l3 = new JLabel("Adresa novog zaposlenog : ");
                JTextField jtf3 = new JTextField(10);
                JLabel l4 = new JLabel("Zarada novog zaposlenog : ");
                JTextField jtf4 = new JTextField(10);
                JButton b1 = new JButton("Dodaj zaposlenog");
                b1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/firma", "root", "Bt15.01.86");) {

                            Zaposleni zap = new Zaposleni(String.valueOf(jtf1.getText()), Integer.valueOf(jtf2.getText()), String.valueOf(jtf3.getText()), String.valueOf(jtf4.getText()));
                            PreparedStatement ps = conn.prepareStatement("insert into zaposleni (ime,godine,adresa,zarada)values(?,?,?,?)");
                            ps.setString(1, zap.getIme());
                            ps.setString(2, String.valueOf(zap.getGodine()));
                            ps.setString(3, zap.getAdresa());
                            ps.setString(4, zap.getZarada());
                            ps.execute();
                            JOptionPane.showMessageDialog(null, "Uspesno ste dodali zaposlenog !!!");
                        } catch (Exception exc) {
                            System.out.println("Nije dobra konekcija :\n" + exc);
                        }
                    }
                });

                f2.add(l1);
                f2.add(jtf1);
                f2.add(l2);
                f2.add(jtf2);
                f2.add(l3);
                f2.add(jtf3);
                f2.add(l4);
                f2.add(jtf4);
                f2.add(b1);
                f2.setVisible(true);
            }
        });

        jm.add(jmi1);
        jm.add(jmi2);
        jm.add(jmi3);
        jm.add(jmi4);
        jm.add(jmi5);
        bar.add(jm);
        f.setJMenuBar(bar);
        f.setVisible(true);

    }
}
