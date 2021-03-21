package medicaments.controllers;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import medicaments.DAO.client.MedicamentDAO;
import medicaments.classes.Generique;
import medicaments.classes.Titulaire;

/**
 * Servlet implementation class Statistique
 * @author Basile
 */
/**
 * @author Basile
 *
 */
/**
 * @author Basile
 *
 */
@WebServlet("/Statistique")
public class Statistique extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MedicamentDAO medDAO;
	ArrayList<Titulaire> list;
       
	//REQUETE SQL NECESSAIRE "SELECT Titulaire, COUNT(*) FROM medicament GROUP BY Titulaire"
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistique() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//medDAO = new MedicamentDAO();
		//list = medDAO.getListTitulaire();
		//Collections.sort(list, Comparator.comparing(Titulaire::getNombre));
		//Collections.reverse(list);
		/*for (Titulaire t : list)
			System.out.println(t.getNom() +" : "+t.getNombre());*/
		//getPieChart(list);
		request.getRequestDispatcher("/Statistique.jsp").forward(request, response);
	}

	/**
	 * Permit to draw and save a piechart graphic, not usued actually
	 * @param list of titulaire with number of medicament they own
	 */
	public void getPieChart(ArrayList<Titulaire> list) {

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        int sub=0;
        int sum10=0;
        int sum50=0;
        int sum100=0;
        int sup=0;
        for (Titulaire t : list) {
        	int nbre = t.getNombre();
        	if(nbre < 5) {
        		sub++;
        	}else if (nbre >= 5 && nbre<10) {
        		sum10 ++;
        	}else if(nbre>=10 && nbre<50) {
        		sum50 ++;
        	}else if(nbre>=50 && nbre<100) {
        		sum100 ++;
        	}else if(nbre>=100) {
        		sup ++;
        	}
        }
        pieDataset.setValue("Moins de 5", sub);
        pieDataset.setValue("Entre 5 et 10", sum10);
        pieDataset.setValue("Entre 10 et 50", sum50);
        pieDataset.setValue("Entre 50 et 100", sum100);
        pieDataset.setValue("Plus de 200", sup);
        //System.out.println(sum);
        //pieDataset.setValue("Other", new Integer(25));

        JFreeChart chart = ChartFactory.createPieChart("", pieDataset, true, true, false);
        //chart.setBackgroundPaint(new Color(222, 222, 255));
            final PiePlot plot = (PiePlot) chart.getPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setCircular(true);
            
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(  
                "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));  
            ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator); 

        try {

            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File(getServletContext().getRealPath(".") + "\\donnees\\piechart2.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 640, 480, info);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

	
}
