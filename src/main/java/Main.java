import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {

    private JPanel panelMain;
    private JPanel panelSouth;
    private JPanel panelNorth;
    private JLabel labelResult;
    private JPanel panelNorthWest;
    private JPanel panelNorthCenter;
    private JButton button;
    private JCheckBox arborCheckBox;
    private JCheckBox faunaNativaCheckBox;
    private JCheckBox vegetaPerturbadaCheckBox;
    private JLabel labelResume;
    private JTable table1;
    private JCheckBox vegetaConservadaCheckBox;
    private JPanel panelNorthEast;

    String[] header = {"Nome", "Categoria", "Estado", "Munícipio", "Área Verde Demarcada (m²)", "Área Arborizada > 50%", "Área com vegetação perturbada, escassa ou ausente > 50%",  "Área vegetada conservada  > 50%", "Presença de fauna nativa"};
    static DefaultTableModel model;

    static int qtdAreaArborizadaMaior50 = 0;
    static int qtdAreaArborizadaMenor50 = 0;

    static int qtdAreaVegetPerturbadaMaior50 = 0;
    static int qtdAreaVegetPerturbadaMenor50 = 0;

    static int qtdAreaVegetConservadaMaior50 = 0;
    static int qtdAreaVegetConservadaMenor50 = 0;

    static int presencaFaunaNativa = 0;
    static int semPresencaFaunaNativa = 0;

    File excelFilePath = new File("dados_areas_verdes_urbanas.xlsx");
    static File iconFilePath = new File("icon.png");

    static double[] somatAreas = {0, 0, 0, 0}; //Pereiro, Irauçuba, Mauriti, Caucaia

    public Main() throws IOException {
        ExcelReader file = new ExcelReader(excelFilePath.getAbsolutePath());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.readExcel();

                Graphics area = new Graphics("Somatório de Áreas");
                area.barChart(somatAreas[0], somatAreas[1], somatAreas[2], somatAreas[3]);

                if(arborCheckBox.isSelected()){
                    Graphics areaArborizada = new Graphics("Área Arborizada");
                    areaArborizada.pieChart(qtdAreaArborizadaMaior50, qtdAreaArborizadaMenor50, "Arborização das Áreas", "Arborização > 50%", "Arborização < 50%");
                }

                if(vegetaPerturbadaCheckBox.isSelected()){
                    Graphics vegetacaoPerturbada = new Graphics("Vegetação Perturbada, Escassa ou Ausente");
                    vegetacaoPerturbada.pieChart(qtdAreaVegetPerturbadaMaior50, qtdAreaVegetPerturbadaMenor50, "Vegetação Perturbada, Escassa ou Ausente", "Vegetação Perturbada, Escassa ou Ausente > 50%", "Vegetação Perturbada, Escassa ou Ausente < 50%");
                }

                if(vegetaConservadaCheckBox.isSelected()){
                    Graphics vegetacaoConservada = new Graphics("Vegetação Conservada");
                    vegetacaoConservada.pieChart(qtdAreaVegetConservadaMaior50, qtdAreaVegetConservadaMenor50, "Vegetação Conservada", "Conservação > 50%", "Conservação < 50%");
                }

                if(faunaNativaCheckBox.isSelected()){
                    Graphics faunaNativa = new Graphics("Presença de Fauna Nativa");
                    faunaNativa.pieChart(presencaFaunaNativa, semPresencaFaunaNativa, "Fauna Nativa", "Com Fauna Nativa", "Sem Fauna Nativa");
                }

                String resume = "<html>" + "Áreas com Arborização &gt; 50%: " + qtdAreaArborizadaMaior50 + "<br>";
                resume += "Áreas com Arborização &lt; 50%: " + qtdAreaArborizadaMenor50 + "<br><br>";
                resume += "Áreas com Vegetação Perturbada &gt; 50%: " + qtdAreaVegetPerturbadaMaior50 + "<br>";
                resume += "Áreas com Vegetação Perturbada &lt; 50%: " + qtdAreaVegetPerturbadaMenor50 + "<br><br>";
                resume += "Áreas com Vegetação Conservada &gt; 50%: " + qtdAreaVegetConservadaMaior50 + "<br>";
                resume += "Áreas com Vegetação Conservada &lt; 50%: " + qtdAreaVegetConservadaMenor50 + "<br><br>";
                resume += "Áreas com Fauna Nativa: " + presencaFaunaNativa + "<br>";
                resume += "Áreas sem Fauna Nativa: " + semPresencaFaunaNativa + "<br>";

                labelResume.setText(resume);

                qtdAreaArborizadaMaior50 = 0;
                qtdAreaArborizadaMenor50 = 0;

                qtdAreaVegetPerturbadaMaior50 = 0;
                qtdAreaVegetPerturbadaMenor50 = 0;

                qtdAreaVegetConservadaMaior50 = 0;
                qtdAreaVegetConservadaMenor50 = 0;

                presencaFaunaNativa = 0;
                semPresencaFaunaNativa = 0;
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Análise de Dados das Áreas Verdes Cearenses");
        Image icon = Toolkit.getDefaultToolkit().getImage(iconFilePath.getAbsolutePath());
        frame.setIconImage(icon);
        frame.add(new Main().panelMain);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model = new DefaultTableModel(0, 9);
        model.setColumnIdentifiers(header);
        table1 = new JTable(model);
    }
}
