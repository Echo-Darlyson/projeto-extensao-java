import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

    private JPanel panelMain;
    private JPanel panelSouth;
    private JPanel panelNorth;
    private JLabel labelResult;
    private JPanel panelNorthWest;
    private JPanel panelNorthEast;
    private JPanel panelNorthCenter;
    private JButton button;
    private JCheckBox arborização50CheckBox;
    private JCheckBox vegetaçãoNativaCheckBox;
    private JCheckBox áreaM100CheckBox;
    private JLabel labelResume;
    private JTable table1;

    String[] header = {"Nome", "Categoria", "Estado", "Munícipio", "Área Verde Demarcada", "Área Arborizada > 50%", "Área com vegetação perturbada, escassa ou ausente > 50%", "Regulação da permeabilidade do solo", "Área pavimentada > 50%", "Área vegetada conservada  > 50%", "Sem vegetação", "Presença de fauna nativa"};
    static DefaultTableModel model;

    public Main() throws IOException {
        ExcelReader file = new ExcelReader("C:\\Users\\ADM\\IdeaProjects\\green-areas-analysis\\dados_areas_verdes_urbanas.xlsx");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.readExcel();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Análise de Dados das Áreas Verdes Cearenses");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADM\\IdeaProjects\\green-areas-analysis\\icon.png");
        frame.setIconImage(icon);
        frame.add(new Main().panelMain);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model = new DefaultTableModel(0, 13);
        model.setColumnIdentifiers(header);
        table1 = new JTable(model);
    }
}
