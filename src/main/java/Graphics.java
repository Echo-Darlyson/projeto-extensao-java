import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class Graphics extends JFrame {

    public Graphics(String windowTitle) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(windowTitle);
        setSize(700,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(Main.iconFilePath.getAbsolutePath());
        setIconImage(icon);

    }

    public void pieChart (int valorColetado1, int valorColetado2, String titulo, String valor1, String valor2){

        DefaultPieDataset pizza = new DefaultPieDataset();
        pizza.setValue(valor1, valorColetado1);
        pizza.setValue(valor2, valorColetado2);

        JFreeChart grafico = ChartFactory.createPieChart(titulo, pizza, true, true, false);
        ChartPanel painel = new ChartPanel(grafico);
        add(painel);
    }

    public void barChart (double somatAreaP, double somaAreaI, double somatAreaM, double somatAreaC){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(somatAreaP, "Pereiro", "2020-2022");
        dataset.addValue(somaAreaI, "Irauçuba", "2020-2022");
        dataset.addValue(somatAreaM, "Mauriti", "2020-2022");
        dataset.addValue(somatAreaC, "Caucaia", "2020-2022");

        JFreeChart grafico = ChartFactory.createBarChart("Somatório das Áreas Verdes Demarcadas", "Ano", "Área (m²)", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel painel = new ChartPanel(grafico);
        add(painel);
    }

}
